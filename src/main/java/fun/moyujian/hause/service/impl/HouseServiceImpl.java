package fun.moyujian.hause.service.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import fun.moyujian.hause.common.HouseStatus;
import fun.moyujian.hause.entity.House;
import fun.moyujian.hause.entity.form.HouseInfoForm;
import fun.moyujian.hause.entity.view.HouseDetailView;
import fun.moyujian.hause.entity.view.HouseListView;
import fun.moyujian.hause.mapper.HouseMapper;
import fun.moyujian.hause.service.HouseService;
import fun.moyujian.hause.util.Base64PicUtil;
import fun.moyujian.hause.util.JwtUtil;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * 租房服务实现
 * @author Tian
 * @date 2023/2/16
 */
@Service("houseService")
public class HouseServiceImpl implements HouseService {

    private final HouseMapper houseMapper;

    @Value("${upload.pic-saving-path}")
    private String picSavingPath;

    @Value("${upload.pic-url-suffix}")
    private String picUrlSuffix;

    public HouseServiceImpl(HouseMapper houseMapper) {
        this.houseMapper = houseMapper;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void publishHouse(String token, HouseInfoForm form) {
        untiXssForHouseInfoForm(form);
        House house = new House();
        BeanUtils.copyProperties(form, house);
        house.setPubUserId(JwtUtil.getChaimUserId(token));

        // 房子文本信息入库
        houseMapper.insert(house);
        // 房子图片保存到指定位置并生成url
        List<String> urlList = new ArrayList<>();
        for(var base64 : form.getBase64PicList()) {
            if (Strings.isEmpty(base64)) {
                continue;
            }
            String filename = Base64PicUtil.decodeAndSavePic(base64, picSavingPath);
            urlList.add(picUrlSuffix + filename);
        }
        // 房子图片入库
        houseMapper.insertHousePics(house.getId(), urlList);
    }

    @Override
    public void deleteHouse(String token, Long houseId) {
        // ps：这里做了真删除，一般建议做成状态删除，不要将数据物理的删除掉
        houseMapper.delete(
                Wrappers.<House>update().lambda()
                        .eq(House::getId, houseId)
                        .eq(House::getPubUserId, JwtUtil.getChaimUserId(token))
        );
    }

    @Override
    public void updateHouse(Long houseId, HouseInfoForm form) {
        House house = new House();
        BeanUtils.copyProperties(form, house);
        house.setId(houseId);

        houseMapper.updateById(house);
    }

    @Override
    public List<HouseListView> getHouseList(ListOf type, SortBy sortKey, Order order,
                                            int pageNum, int pageSize,
                                            String token, String keyword) {
        Long userId;
        if (Strings.isEmpty(token)) {
            if (type != ListOf.ALL_HOUSE) {
                throw new RuntimeException("参数有误，获取‘我发布的租房列表’或‘我收藏的租房列表’时未给定token");
            }

            userId = null;
        } else {
            userId = JwtUtil.getChaimUserId(token);
        }

        // 确定排序基准
        boolean isOrderByTime = (sortKey == SortBy.TIME);
        // 确定排序序向
        boolean isAsc = (order == Order.ASC);
        // 确定分页
        Page<HouseListView> page = Page.of(pageNum, pageSize);

        houseMapper.selectFavorHouseList(page, type.getType(),
                isOrderByTime, isAsc, userId, keyword);

        List<HouseListView> houseListViewList = page.getRecords();
        for (HouseListView houseListView : houseListViewList) {
            HouseStatus houseStatus = HouseStatus.getByCode(houseListView.getStatusCode());
            if (houseStatus != null) {
                houseListView.setStatus(houseStatus.getStatus());
            }
        }

        return houseListViewList;
    }

    @Override
    public HouseDetailView getHouseDetail(Long houseId) {
        return houseMapper.selectHouseDetail(houseId);
    }

    @Override
    public void favorHouse(String token, Long houseId, boolean favor) {
        Long userId = JwtUtil.getChaimUserId(token);
        if (isFavor(token, houseId)) {
            if (!favor) {
                houseMapper.deleteFavorHouse(userId, houseId);
            }
        } else {
            if (favor) {
                houseMapper.insertFavorHouse(userId, houseId);
            }
        }
    }

    @Override
    public boolean isFavor(String token, Long houseId) {
        return houseMapper.selectFavorCount(JwtUtil.getChaimUserId(token), houseId) > 0;
    }

    private void untiXssForHouseInfoForm(HouseInfoForm form) {
        form.setTitle(replaceJsChar(form.getTitle()));
        form.setSpecs(replaceJsChar(form.getSpecs()));
        form.setArea(replaceJsChar(form.getArea()));
        form.setFloor(replaceJsChar(form.getFloor()));
        form.setType(replaceJsChar(form.getType()));
        form.setAddr(replaceJsChar(form.getAddr()));
        form.setTowards(replaceJsChar(form.getTowards()));
        form.setCommunity(replaceJsChar(form.getCommunity()));
        form.setDescription(replaceJsChar(form.getDescription()));
    }

    private String replaceJsChar(String str) {
        return str.replace("<", " ")
                .replace(">", " ")
                .replace("\"", "“");
    }
}
