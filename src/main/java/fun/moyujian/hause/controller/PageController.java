package fun.moyujian.hause.controller;

import fun.moyujian.hause.annotation.AuthToken;
import fun.moyujian.hause.common.Constants;
import fun.moyujian.hause.entity.view.HouseDetailView;
import fun.moyujian.hause.entity.view.UserInfoView;
import fun.moyujian.hause.service.HouseService;
import fun.moyujian.hause.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

/**
 * 页面控制器
 * @author Tian
 * @date 2023/2/17
 */
@Controller
public class PageController {

    private final UserService userService;

    private final HouseService houseService;

    public PageController(UserService userService, HouseService houseService) {
        this.userService = userService;
        this.houseService = houseService;
    }

    /**
     * 主页
     */
    @RequestMapping(path = "/", method = {RequestMethod.GET, RequestMethod.POST})
    @AuthToken(redirect = true)
    public String indexPage(@CookieValue(Constants.COOKIE_NAME) String token, Model model) {
        UserInfoView userInfo = userService.getUserInfo(token);
        model.addAttribute("userInfo", userInfo);
        return "index";
    }

    /**
     * 登录页
     */
    @RequestMapping(path = "/user/login", method = {RequestMethod.GET, RequestMethod.POST})
    public String loginPage() {
        return "page/login";
    }

    /**
     * 注册页
     */
    @RequestMapping(path = "/user/register", method = {RequestMethod.GET, RequestMethod.POST})
    public String registerPage() {
        return "page/register";
    }

    /**
     * 房子详情页
     */
    @RequestMapping(path = "/house/detail/{id}", method = {RequestMethod.GET, RequestMethod.POST})
    @AuthToken(redirect = true)
    public String detailPage(@CookieValue(Constants.COOKIE_NAME) String token,
                             @PathVariable("id") Long houseId, Model model) {
        UserInfoView userInfo = userService.getUserInfo(token);
        HouseDetailView houseDetail = houseService.getHouseDetail(houseId);
        model.addAttribute("houseDetail", houseDetail);
        model.addAttribute("userInfo", userInfo);
        return "page/detail";
    }

    /**
     * 搜索页
     */
    @RequestMapping(path = "/house/search", method = {RequestMethod.GET, RequestMethod.POST})
    @AuthToken(redirect = true)
    public String searchPage(@CookieValue(Constants.COOKIE_NAME) String token,
                             @RequestParam("key") String keyword, Model model) {
        UserInfoView userInfo = userService.getUserInfo(token);
        model.addAttribute("keyword", keyword);
        model.addAttribute("userInfo", userInfo);
        return "page/search";
    }

    /**
     * 发布页
     */
    @RequestMapping(path = "/house/publish", method = {RequestMethod.GET, RequestMethod.POST})
    @AuthToken(redirect = true)
    public String publishPage(@CookieValue(Constants.COOKIE_NAME) String token, Model model) {
        UserInfoView userInfo = userService.getUserInfo(token);
        model.addAttribute("userInfo", userInfo);
        return "page/publish";
    }

    /**
     * 我的房源页
     */
    @RequestMapping(path = "/house/my", method = {RequestMethod.GET, RequestMethod.POST})
    @AuthToken(redirect = true)
    public String myHousePage(@CookieValue(Constants.COOKIE_NAME) String token, Model model) {
        UserInfoView userInfo = userService.getUserInfo(token);
        model.addAttribute("userInfo", userInfo);
        return "page/myhouse";
    }

    /**
     * 我的信息页
     */
    @RequestMapping(path = "/user/info", method = {RequestMethod.GET, RequestMethod.POST})
    @AuthToken(redirect = true)
    public String myInfoPage(@CookieValue(Constants.COOKIE_NAME) String token, Model model) {
        UserInfoView userInfo = userService.getUserInfo(token);
        model.addAttribute("userInfo", userInfo);
        return "page/user";
    }
}
