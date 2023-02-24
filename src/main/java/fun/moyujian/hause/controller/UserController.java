package fun.moyujian.hause.controller;

import fun.moyujian.hause.annotation.AuthToken;
import fun.moyujian.hause.common.Constants;
import fun.moyujian.hause.common.ResponseCode;
import fun.moyujian.hause.common.ResponseView;
import fun.moyujian.hause.entity.form.UserInfoForm;
import fun.moyujian.hause.entity.form.UserLoginForm;
import fun.moyujian.hause.entity.form.UserRegisterForm;
import fun.moyujian.hause.service.UserService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 用户功能控制器
 * @author Tian
 * @date 2023/2/14
 */
@RestController
@RequestMapping("/bev1/user")
public class UserController {

    @Value("${cookie.expire-time}")
    private int cookieExpireTime;

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping(path = "/login")
    public ResponseView login(UserLoginForm form, HttpServletResponse resp) {
        String token = userService.login(form);
        return signTokenInCookie(resp, token);
    }

    @PostMapping(path = "/register")
    public ResponseView register(UserRegisterForm form, HttpServletResponse resp) {
        String token = userService.register(form);
        return signTokenInCookie(resp, token);
    }

    @PostMapping(path = "/modify")
    @AuthToken
    public ResponseView modifyInfo(UserInfoForm form,
                                   @CookieValue(Constants.COOKIE_NAME) String token) {
        userService.modifyInfo(token, form);
        return ResponseView.getInstance(ResponseCode.SUC);
    }

    /**
     * 将token签入cookie
     * @param resp httpResponse
     * @param token token
     * @return 响应数据
     */
    private ResponseView signTokenInCookie(HttpServletResponse resp, String token) {
        if (Strings.isEmpty(token)) {
            return ResponseView.getInstance(ResponseCode.LOGIN_FAILED);
        }

        Cookie cookie = new Cookie(Constants.COOKIE_NAME, token);
        cookie.setPath("/");
        cookie.setMaxAge(cookieExpireTime);
        resp.addCookie(cookie);
        return ResponseView.getInstance(ResponseCode.SUC);
    }
}
