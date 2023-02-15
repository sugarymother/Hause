package fun.moyujian.hause.entity.form;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * 用户注册表单
 * @author Tian
 * @date 2023/2/15
 */
@Data
@AllArgsConstructor
public class UserRegisterForm {

    private String username;

    private String password;

    private String phone;

    private String wechat;
}
