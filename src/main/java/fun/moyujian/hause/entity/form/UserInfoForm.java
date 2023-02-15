package fun.moyujian.hause.entity.form;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * 用户信息表单
 * @author Tian
 * @date 2023/2/15
 */
@Data
@AllArgsConstructor
public class UserInfoForm {

    private String phone;

    private String wechat;
}
