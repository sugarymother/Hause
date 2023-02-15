package fun.moyujian.hause.mapper;

import fun.moyujian.hause.entity.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @author Tian
 * @date 2023/2/15
 */
@SpringBootTest(args = "--spring.profiles.active=dev")
public class UserMapperTest {

    @Autowired
    private UserMapper userMapper;

    @Test
    public void testInsert() {
        var user = new User();
        user.setUsername("me");
        user.setPassword("123");
        user.setPhone("17786778489");
        userMapper.insert(user);
    }
}
