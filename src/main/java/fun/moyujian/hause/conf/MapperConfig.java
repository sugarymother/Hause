package fun.moyujian.hause.conf;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

/**
 * mapper扫描配置类
 * @author Tian
 * @date 2021/8/17
 */
@Configuration
@MapperScan("fun.moyujian.hause.mapper")
public class MapperConfig {
}
