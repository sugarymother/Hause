package fun.moyujian.hause.exception;

import lombok.Getter;

/**
 * token认证异常类
 * @author 田
 * @date 2021/5/1
 */
@Getter
public class AuthTokenException extends RuntimeException {

    private String token;

    public AuthTokenException(String msg, String token) {
        super(msg);
        this.token = token;
    }

    public AuthTokenException() {
        super();
    }
}
