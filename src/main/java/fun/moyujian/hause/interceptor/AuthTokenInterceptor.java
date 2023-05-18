package fun.moyujian.hause.interceptor;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import fun.moyujian.hause.annotation.AuthToken;
import fun.moyujian.hause.common.Constants;
import fun.moyujian.hause.entity.User;
import fun.moyujian.hause.exception.AuthTokenException;
import fun.moyujian.hause.mapper.UserMapper;
import fun.moyujian.hause.util.JwtUtil;
import jakarta.annotation.Resource;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import java.io.IOException;
import java.lang.reflect.Method;

/**
 * token认证拦截器
 * @author Tian
 * @date 2021/5/1
 */
@Component
public class AuthTokenInterceptor implements HandlerInterceptor {

    @Resource
    private UserMapper userMapper;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws IOException {

        //非映射方法直接跳过
        if (!(handler instanceof HandlerMethod)) {
            return true;
        }

        Method method = ((HandlerMethod) handler).getMethod();

        if (method.isAnnotationPresent(AuthToken.class)) {
            AuthToken annotation = method.getAnnotation(AuthToken.class);
            if (!annotation.required()) {
                return true;
            }

            Cookie[] cookies = request.getCookies();
            String cookieValue = null;

            if (cookies != null) {
                for (Cookie cookie : cookies) {
                    if (Constants.COOKIE_NAME.equals(cookie.getName())) {
                        cookieValue = cookie.getValue();
                        break;
                    }
                }
            }

            if (cookieValue == null || !JwtUtil.verifyToken(cookieValue)) {
                if (annotation.redirect()) {
                    response.sendRedirect(Constants.AUTH_REDIRECT_URI);
                    return false;
                }
                throw new AuthTokenException("token无效或缺失", cookieValue);
            }

            if (annotation.checkAdmin()) {
                Long userId = JwtUtil.getChaimUserId(cookieValue);
                User user = userMapper.selectOne(Wrappers.<User>query().lambda().eq(User::getId, userId));
                return user.getAdmin() != null && user.getAdmin();
            }
        }

        return true;
    }
}
