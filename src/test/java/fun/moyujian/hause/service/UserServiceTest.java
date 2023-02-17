package fun.moyujian.hause.service;

import fun.moyujian.hause.entity.form.UserInfoForm;
import fun.moyujian.hause.entity.form.UserLoginForm;
import fun.moyujian.hause.entity.form.UserRegisterForm;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @author Tian
 * @date 2023/2/16
 */
@SpringBootTest(args = "--spring.profiles.active=dev")
public class UserServiceTest {

    @Autowired
    private UserService userService;

    private final String username = "test02";
    private final String password = "123456";
    private final String phone    = "17766666666";
    private final String wechat   = "myjtzr";

    @Test
    public void registerTest() {
        System.out.println(
                userService.register(new UserRegisterForm(username, password, phone, wechat))
        );
    }

    @Test
    public void loginTest() {
        System.out.println(
                userService.login(new UserLoginForm(username, password))
        );
    }

    @Test
    public void updateTest() {
        userService.modifyInfo(
                userService.login(new UserLoginForm(username, password)),
                new UserInfoForm(phone, wechat)
        );
    }

    @Test
    public void getInfoTest() {
        System.out.println(
                userService.getUserInfo(
                        userService.login(new UserLoginForm(username, password))
                )
        );
    }
}
