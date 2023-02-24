package fun.moyujian.hause.controller;

import fun.moyujian.hause.annotation.AuthToken;
import fun.moyujian.hause.common.Constants;
import fun.moyujian.hause.common.ResponseCode;
import fun.moyujian.hause.common.ResponseView;
import fun.moyujian.hause.entity.form.HouseInfoForm;
import fun.moyujian.hause.entity.view.HouseListView;
import fun.moyujian.hause.mapper.HouseMapper;
import fun.moyujian.hause.service.HouseService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 房子功能控制器
 * @author Tian
 * @date 2023/2/18
 */
@RestController
@RequestMapping("/bev1/house")
public class HouseController {

    private final HouseService houseService;

    public HouseController(HouseService houseService) {this.houseService = houseService;}

    @GetMapping(path = "/list")
    @AuthToken
    public ResponseView getHouseList(@RequestParam("favor") Boolean favor,
                                        @RequestParam("sortByPrice") Boolean sortByPrice,
                                        @RequestParam("asc") Boolean asc,
                                        @CookieValue(Constants.COOKIE_NAME) String token,
                                        @RequestParam(name = "pageNum", defaultValue = "1") Integer pageNum,
                                        @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize) {
        List<HouseListView> houseList = houseService.getHouseList(
                favor ? HouseService.ListOf.FAVOR_HOUSE : HouseService.ListOf.ALL_HOUSE,
                sortByPrice ? HouseService.SortBy.PRICE : HouseService.SortBy.TIME,
                asc ? HouseService.Order.ASC : HouseService.Order.DESC,
                pageNum,
                pageSize,
                token,
                null
        );
        return ResponseView.getInstance(ResponseCode.SUC, houseList);
    }

    @GetMapping(path = "/my")
    @AuthToken
    public ResponseView getMyHouseList(@CookieValue(Constants.COOKIE_NAME) String token,
                                       @RequestParam(name = "pageNum", defaultValue = "1") Integer pageNum,
                                       @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize) {
        List<HouseListView> houseList = houseService.getHouseList(
                HouseService.ListOf.MY_HOUSE,
                HouseService.SortBy.TIME,
                HouseService.Order.DESC,
                pageNum,
                pageSize,
                token,
                null
        );
        return ResponseView.getInstance(ResponseCode.SUC, houseList);
    }

    @GetMapping(path = "/search")
    @AuthToken
    public ResponseView searchHouse(@RequestParam("keyword") String keyword,
                                    @RequestParam(name = "pageNum", defaultValue = "1") Integer pageNum,
                                    @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize) {
        List<HouseListView> houseList = houseService.getHouseList(
                HouseService.ListOf.ALL_HOUSE,
                HouseService.SortBy.TIME,
                HouseService.Order.DESC,
                pageNum,
                pageSize,
                null,
                keyword
        );
        return ResponseView.getInstance(ResponseCode.SUC, houseList);
    }

    @PostMapping(path="/favor")
    @AuthToken
    public ResponseView favorHouse(@RequestParam("houseId") Long houseId,
                                   @RequestParam("favor") Boolean favor,
                                   @CookieValue(Constants.COOKIE_NAME) String token) {
        houseService.favorHouse(token, houseId, favor);
        return ResponseView.getInstance(ResponseCode.SUC);
    }

    @GetMapping(path = "/isFavor")
    @AuthToken
    public ResponseView isFavor(@RequestParam("houseId") Long houseId,
                                @CookieValue(Constants.COOKIE_NAME) String token) {
        return ResponseView.getInstance(ResponseCode.SUC, houseService.isFavor(token, houseId));
    }

    @PostMapping(path = "/publish")
    @AuthToken
    public ResponseView publishHouse(HouseInfoForm form,
                                     @CookieValue(Constants.COOKIE_NAME) String token) {
        houseService.publishHouse(token, form);
        return ResponseView.getInstance(ResponseCode.SUC);
    }

    @PostMapping(path = "/delete")
    @AuthToken
    public ResponseView deleteHouse(@RequestParam("houseId") Long houseId) {
        houseService.deleteHouse(houseId);
        return ResponseView.getInstance(ResponseCode.SUC);
    }
}
