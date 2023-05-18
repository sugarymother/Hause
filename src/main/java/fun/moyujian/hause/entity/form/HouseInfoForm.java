package fun.moyujian.hause.entity.form;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * 房子信息表单
 * @author Tian
 * @date 2023/2/15
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class HouseInfoForm {

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

    private Integer status;

    /**
     * 房子base64图片列表
     */
    private List<String> base64PicList;
}
