package fun.moyujian.hause.entity.view;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 用户信息视图
 * @author Tian
 * @date 2023/2/15
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserInfoView {

    private String username;

    private String phone;

    private String wechat;
}
