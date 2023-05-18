package fun.moyujian.hause.entity.view;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 房子列表信息视图
 * @author Tian
 * @date 2023/2/15
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class HouseListView {

    private Long id;

    private String title;

    private String specs;

    private String area;

    private String floor;

    private String type;

    private String addr;

    private Double price;

    private String towards;

    private Integer statusCode;

    private String status;

    /**
     * 首张图片url
     */
    private String firstPicUrl;
}
