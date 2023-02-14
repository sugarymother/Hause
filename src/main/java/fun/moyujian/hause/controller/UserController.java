package fun.moyujian.hause.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 用户功能控制器
 * @author Tian
 * @date 2023/2/14
 */
@Controller
public class UserController {

    /**
     * 登录页
     */
    @RequestMapping("/login")
    public String loginPage() {
        return "login";
    }
}
