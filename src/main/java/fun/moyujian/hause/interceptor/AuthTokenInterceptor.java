package fun.moyujian.hause.interceptor;

import fun.moyujian.hause.annotation.AuthToken;
import fun.moyujian.hause.exception.AuthTokenException;
import fun.moyujian.hause.util.JwtUtil;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import java.lang.reflect.Method;

/**
 * token认证拦截器
 * @author Tian
 * @date 2021/5/1
 */
@Component
public class AuthTokenInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {

        //非映射方法直接跳过
        if (!(handler instanceof HandlerMethod)) {
            return true;
        }

        Method method = ((HandlerMethod) handler).getMethod();

        if (method.isAnnotationPresent(AuthToken.class) && method.getAnnotation(AuthToken.class).required()) {
            Cookie[] cookies = request.getCookies();
            String cookieValue = null;

            if (cookies != null) {
                for (Cookie cookie : cookies) {
                    if ("token".equals(cookie.getName())) {
                        cookieValue = cookie.getValue();
                        break;
                    }
                }
            }

            if (cookieValue == null || !JwtUtil.verifyToken(cookieValue)) {
                throw new AuthTokenException("token无效或缺失", cookieValue);
            }
        }

        return true;
    }
}
