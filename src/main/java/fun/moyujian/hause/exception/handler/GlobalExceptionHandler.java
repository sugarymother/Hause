package fun.moyujian.hause.exception.handler;

import fun.moyujian.hause.common.ResponseCode;
import fun.moyujian.hause.common.ResponseView;
import fun.moyujian.hause.exception.AuthTokenException;
import jakarta.servlet.ServletException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import java.io.PrintWriter;
import java.io.StringWriter;

/**
 * 全局异常处理类
 * @author Tian
 */
@ControllerAdvice
@ResponseBody
@Slf4j(topic = "全局异常处理")
public class GlobalExceptionHandler {

    /**
     * Token认证异常处理
     * @param e AuthTokenException
     * @return 视图
     */
    @ExceptionHandler(AuthTokenException.class)
    public ResponseView handleUserAuthException(AuthTokenException e) {
        log.info("token无效或过期，TOKEN: {}", e.getToken());
        return ResponseView.getInstance(ResponseCode.TOKEN_INVALID_ERR);
    }

    /**
     * 客户端调用异常处理
     * 此情况属于接口调用者调用出错，其未仔细参照api文档，这里返回一个提示错误信息
     * @param e ServletException 此异常及其子类总是在客户端调用出问题时出现
     * @return 视图
     */
    @ExceptionHandler(ServletException.class)
    @ResponseStatus(HttpStatus.I_AM_A_TEAPOT)
    public ResponseView handleClientCallException(ServletException e) {
        return ResponseView.getInstance(ResponseCode.CLIENT_CALL_ERR, e.getMessage());
    }

    /**
     * 未知异常处理
     * @param e unknown exception
     * @return 视图
     */
    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseView handleUnknownException(Exception e) {
        StringWriter sw = new StringWriter();
        PrintWriter printWriter = new PrintWriter(sw, true);
        e.printStackTrace(printWriter);
        log.error("\n未知异常：{}", sw);
        return ResponseView.getInstance(ResponseCode.ERR);
    }
}
