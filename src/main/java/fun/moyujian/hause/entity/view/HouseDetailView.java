package fun.moyujian.hause.entity.view;

import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * 房子详情信息视图
 * @author Tian
 * @date 2023/2/15
 */
@Data
public class HouseDetailView {

    private Long id;

    private String title;

    private String specs;

    private String area;

    private String floor;

    private String type;

    private String addr;

    private Double price;

    private String towards;

    private String community;

    private String description;

    /**
     * 发布者信息
     */
    private UserInfoView pubUserInfo;

    /**
     * 最后更新时间
     */
    private Date updateTime;

    /**
     * 图片url列表
     */
    private List<String> picUrlList;
}
