package fun.moyujian.hause.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 用户身份验证Token注解
 * @author Tian
 */
@Target({ElementType.METHOD,ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface AuthToken {
    boolean required() default true;
    boolean redirect() default false;

    /**
     * 检查是否为系统管理员用户
     */
    boolean checkAdmin() default false;
}
