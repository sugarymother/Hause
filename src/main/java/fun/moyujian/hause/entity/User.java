package fun.moyujian.hause.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.util.Date;

/**
 * 用户实体
 * @author Tian
 * @date 2023/2/15
 */
@Data
public class User {

    @TableId(type = IdType.AUTO)
    private Long id;

    private String username;

    private String password;

    private String phone;

    private String wechat;

    private Boolean admin;

    private Date createTime;

    private Date updateTime;
}
