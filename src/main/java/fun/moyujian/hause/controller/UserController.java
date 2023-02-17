package fun.moyujian.hause.controller;

import fun.moyujian.hause.entity.form.UserLoginForm;
import fun.moyujian.hause.entity.form.UserRegisterForm;
import fun.moyujian.hause.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 用户功能控制器
 * @author Tian
 * @date 2023/2/14
 */
@Controller
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }


    /**
     * 登录页
     */
    @RequestMapping("/login")
    public String loginPage() {

        System.out.println(userService.login(new UserLoginForm(
                "yo", "123")));

        return "login";
    }
}
