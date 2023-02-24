package fun.moyujian.hause.conf;

import fun.moyujian.hause.interceptor.AuthTokenInterceptor;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * MVC组件配置类
 * @author Tian
 * @date 2021/5/1
 */
@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    /**
     * 跨域允通源
     */
    @Value("${cors.access-control-allow-origin}")
    private String allowedOrigin;

    /**
     * 用户token认证拦截器
     */
    @Resource(type = AuthTokenInterceptor.class)
    private AuthTokenInterceptor authTokenInterceptor;

    /**
     * 配置拦截器
     * @param registry 拦截器注册表
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(this.authTokenInterceptor)
                //配置拦截路径
                .addPathPatterns("/**");
    }

    /**
     * 配置跨域资源共享
     * @param registry cors注册表
     */
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                //允许的请求源
                .allowedOriginPatterns(allowedOrigin)
                //允许的请求方式
                .allowedMethods("POST","GET")
                //允许的请求头
                .allowedHeaders("*")
                //允许携带验证信息
                .allowCredentials(true)
                //允许的最大响应时间
                .maxAge(3600);
    }
}
