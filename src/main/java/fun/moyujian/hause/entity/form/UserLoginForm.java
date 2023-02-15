package fun.moyujian.hause.entity.form;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * 用户登录表单
 * @author Tian
 * @date 2023/2/15
 */
@Data
@AllArgsConstructor
public class UserLoginForm {

    private String username;

    private String password;
}
