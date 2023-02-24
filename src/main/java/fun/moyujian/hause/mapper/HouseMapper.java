package fun.moyujian.hause.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import fun.moyujian.hause.entity.House;
import fun.moyujian.hause.entity.view.HouseDetailView;
import fun.moyujian.hause.entity.view.HouseListView;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 房屋Mapper
 * @author Tian
 * @date 2023/2/15
 */
@Repository
public interface HouseMapper extends BaseMapper<House> {

    /**
     * 插入房子图片
     * @param houseId 房子id
     * @param picUrlList 图片url列表
     */
    void insertHousePics(Long houseId, List<String> picUrlList);

    /**
     * 分页查询收藏房子列表
     * @param page 分页参数
     * @param type 查找列表类型
     * @param isOrderByTime 是否按时间排序
     * @param isAsc   是否顺序
     * @param userId 用户id
     * @param keyword 匹配关键字
     * @return 分页列表
     */
    Page<HouseListView> selectFavorHouseList(Page<HouseListView> page, String type,
                                             Boolean isOrderByTime,
                                             Boolean isAsc, Long userId,
                                             String keyword);

    /**
     * 查询房子详情
     * @param houseId 房子id
     * @return 房子详情
     */
    HouseDetailView selectHouseDetail(Long houseId);

    /**
     * 插入房子收藏
     * @param userId 用户id
     * @param houseId 房子id
     */
    void insertFavorHouse(Long userId, Long houseId);

    /**
     * 删除房子收藏
     * @param userId 用户id
     * @param houseId 房子id
     */
    void deleteFavorHouse(Long userId, Long houseId);

    /**
     * 查找房子收藏
     * @param userId 用户id
     * @param houseId 房子id
     * @return 条数
     */
    int selectFavorCount(Long userId, Long houseId);
}
