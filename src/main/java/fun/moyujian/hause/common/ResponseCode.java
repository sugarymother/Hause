package fun.moyujian.hause.common;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 响应码枚举
 * @author Tian
 */
@Getter
@AllArgsConstructor
public enum ResponseCode {

    /**
     * 成功
     */
    SUC(10000, "操作成功"),
    /**
     * 失败
     */
    ERR(11111, "操作失败"),


    /**
     * token无效或者缺失
     */
    TOKEN_INVALID_ERR(10001, "token无效或者缺失"),
    /**
     * 接口请求不合规范，请求无效
     */
    CLIENT_CALL_ERR(10002, "客户端接口请求不合规范，请求无效"),
    /**
     * 登陆失败，账号或密码错误
     */
    LOGIN_FAILED(10003, "登陆失败，用户名或密码错误"),
    /**
     * 用户名已存在
     */
    USERNAME_EXIST(10004, "用户名已存在"),
    /**
     * 图片上传失败
     */
    PIC_UPLOAD_FAILED(10005, "图片上传失败")
    ;

    private final int code;
    private final String msg;
}
