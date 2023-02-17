package fun.moyujian.hause.mapper;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Tian
 * @date 2023/2/16
 */
@SpringBootTest(args = "--spring.profiles.active=dev")
public class HouseMapperTest {

    @Autowired
    private HouseMapper houseMapper;

    @Test
    public void insertHousePicsTest() {
        List<String> urlList = new ArrayList<>();
        urlList.add("D:\\WENDANG\\文本文档\\docfile\\study\\毕业设计及论文\\房屋租赁系统\\Project\\picUpload\\cat.jpg");
        urlList.add("D:\\WENDANG\\文本文档\\docfile\\study\\毕业设计及论文\\房屋租赁系统\\Project\\picUpload\\dog.jpg");
        houseMapper.insertHousePics((long) 1, urlList);
    }
}
