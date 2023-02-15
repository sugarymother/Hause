package fun.moyujian.hause.service;

import fun.moyujian.hause.entity.form.UserInfoForm;
import fun.moyujian.hause.entity.form.UserLoginForm;
import fun.moyujian.hause.entity.form.UserRegisterForm;
import fun.moyujian.hause.entity.view.UserInfoView;

/**
 * 用户服务
 * @author Tian
 * @date 2023/2/15
 */
public interface UserService {

    /**
     * 用户注册
     * @param form 注册表单
     * @return token
     */
    String register(UserRegisterForm form);

    /**
     * 用户登录
     * @param form 登录表单
     * @return token
     */
    String login(UserLoginForm form);

    /**
     * 修改用户信息
     * @param token token
     * @param form 用户信息表单
     */
    void modifyInfo(String token, UserInfoForm form);

    /**
     * 获取用户信息
     * @param token token
     * @return 用户信息
     */
    UserInfoView getUserInfo(String token);
}
