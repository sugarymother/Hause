package fun.moyujian.hause.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.util.Date;

/**
 * 房子实体
 * @author Tian
 * @date 2023/2/15
 */
@Data
public class House {

    @TableId(type = IdType.AUTO)
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

    private Long pubUserId;

    private Integer status;

    private Date createTime;

    private Date updateTime;
}
