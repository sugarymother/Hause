package fun.moyujian.hause.service;

import fun.moyujian.hause.entity.form.HouseInfoForm;
import fun.moyujian.hause.entity.form.UserLoginForm;
import fun.moyujian.hause.entity.view.HouseListView;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.List;

/**
 * @author Tian
 * @date 2023/2/16
 */
@SpringBootTest(args = "--spring.profiles.active=dev")
public class HouseServiceTest {

    @Autowired
    private HouseService houseService;

    @Autowired
    private UserService userService;

    private String pic1;
    private String pic2;

    public HouseServiceTest() {
        try {
            FileInputStream fis = new FileInputStream("./picUpload/cat.txt");
            int len = fis.available();
            byte[] buff = new byte[len];
            int i = fis.read(buff);
            fis.close();
            pic1 = new String(buff, StandardCharsets.UTF_8);
            fis = new FileInputStream("./picUpload/dog.txt");
            len = fis.available();
            buff = new byte[len];
            i = fis.read(buff);
            fis.close();
            pic2 = new String(buff, StandardCharsets.UTF_8);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void publishHouseTest() {
        HouseInfoForm houseInfoForm = new HouseInfoForm();
        houseInfoForm.setTitle("测试房11");
        houseInfoForm.setSpecs("3室1厅2卫");
        houseInfoForm.setArea("20平");
        houseInfoForm.setFloor("6/6层");
        houseInfoForm.setType("合租");
        houseInfoForm.setAddr("西红柿 叉叉区 老头环路2号");
        houseInfoForm.setPrice(1000.00);
        houseInfoForm.setTowards("朝东");
        houseInfoForm.setCommunity("老奶奶花园");
        houseInfoForm.setDescription("另一个b，嘎嘎嘎嘎嘎");
        houseInfoForm.setBase64PicList(List.of(pic2));

        houseService.publishHouse(userService.login(
                new UserLoginForm("test02", "123456")
        ), houseInfoForm);
    }

    @Test
    public void deleteHouseTest() {
        //houseService.deleteHouse(16L);
    }

    @Test
    public void updateHouseTest() {
        HouseInfoForm houseInfoForm = new HouseInfoForm();
        houseInfoForm.setDescription("三人合租测试房");

        houseService.updateHouse(1L, houseInfoForm);
    }

    @Test
    public void favorHouseTest() {
        houseService.favorHouse(
                userService.login(new UserLoginForm("test02", "123456")),
                17L,
                true
        );
    }

    @Test
    public void getHouseListTest() {

        List<HouseListView> houseList = houseService.getHouseList(
                HouseService.ListOf.FAVOR_HOUSE,
                HouseService.SortBy.PRICE,
                HouseService.Order.DESC,
                1,
                3,
                userService.login(new UserLoginForm("test02", "123456")),
                null
        );
        for (var house : houseList) {
            System.out.println(house);
        }
    }

    @Test
    public void getHouseDetailTest() {
        System.out.println(
                houseService.getHouseDetail(1L)
        );
    }
}
