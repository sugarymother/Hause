package fun.moyujian.hause.common;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;

import java.io.Serializable;

/**
 * 服务器响应视图类
 * @author  Tian
 */
@Getter
public class ResponseView implements Serializable {
    /**
     * 响应码
     */
    private int code;
    /**
     * 响应简讯
     */
    private String msg;
    /**
     * 响应数据
     */
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Object data;

    /**
     * 不允许直接创建实例
     */
    private ResponseView() {}

    private ResponseView(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    private ResponseView(int code, String msg, Object data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public static ResponseView getInstance(ResponseCode responseCode) {
        return new ResponseView(responseCode.getCode(), responseCode.getMsg());
    }

    public static ResponseView getInstance(ResponseCode responseCode, Object data) {
        return new ResponseView(responseCode.getCode(), responseCode.getMsg(), data);
    }

    @JsonIgnore
    public boolean isSuc() {
        return this.code == ResponseCode.SUC.getCode();
    }

    @JsonIgnore
    public boolean isErr() {
        return this.code != ResponseCode.SUC.getCode();
    }

    public void setCodeAndMsg(ResponseCode responseCode) {
        this.code = responseCode.getCode();
        this.msg = responseCode.getMsg();
    }

    public void setData(Object data) {
        this.data = data;
    }
}
