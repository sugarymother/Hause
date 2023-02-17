package fun.moyujian.hause.service;

import fun.moyujian.hause.entity.form.HouseInfoForm;
import fun.moyujian.hause.entity.view.HouseDetailView;
import fun.moyujian.hause.entity.view.HouseListView;

import java.util.List;

/**
 * 出租房服务
 * @author Tian
 * @date 2023/2/15
 */
public interface HouseService {

    /**
     * 发布出租房
     * @param token token
     * @param form 出租房信息表单
     */
    void publishHouse(String token, HouseInfoForm form);

    /**
     * 删除出租房
     * @param houseId 出租房id
     */
    void deleteHouse(Long houseId);

    /**
     * 更新出租房
     * @param houseId 出租房id
     * @param form 出租房信息表单
     */
    void updateHouse(Long houseId, HouseInfoForm form);

    /**
     * 获取房子列表
     * @param type 查找类型：所有/我发布的/我的收藏
     * @param sortKey 排序基准：按时间/按价格
     * @param order  顺序/倒序
     * @param pageNum 分页 页数
     * @param pageSize 分页 单页条数
     * @param token token 如果查找类型为所有房子，此处填null
     * @param keyword 匹配关键词，如无需匹配关键词，此处填null
     * @return 房子信息列表
     */
    List<HouseListView> getHouseList(ListOf type, SortBy sortKey, Order order,
                                     int pageNum, int pageSize,
                                     String token, String keyword);

    /**
     * 获取房子详情信息
     * @param houseId 房屋id
     * @return 房子详情
     */
    HouseDetailView getHouseDetail(Long houseId);

    /**
     * 添加房子到收藏
     * @param token token
     * @param houseId 房子id
     */
    void favorHouse(String token, Long houseId);

    /**
     * 获取租房列表的类型
     */
    enum ListOf {
        ALL_HOUSE("all"),      // 所有房子
        MY_HOUSE("my"),        // 我发布的房子
        FAVOR_HOUSE("favor"),  // 我收藏的房子
        ;

        private final String type;

        ListOf(String type) {
            this.type = type;
        }

        public String getType() {
            return type;
        }
    }

    /**
     * 排序基准
     */
    enum SortBy {
        TIME,         // 按时间
        PRICE,        // 按价格
    }

    /**
     * 序向
     */
    enum Order {
        DESC,         // 顺序
        ASC,          // 倒序
    }
}
