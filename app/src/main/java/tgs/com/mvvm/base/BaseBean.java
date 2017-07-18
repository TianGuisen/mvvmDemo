package tgs.com.mvvm.base;

import java.io.Serializable;

/**
 * Created by 田桂森 on 2017/5/9.
 */

public class BaseBean<T> implements Serializable {
    private int code;
    private String message;
    private T data;
    
    @Override
    public String toString() {
        return "BaseBean{" +
                "code=" + code +
                ", message='" + message + '\'' +
                ", data=" + data +
                '}';
    }
    
    public int getCode() {
        return code;
    }
    
    public void setCode(int code) {
        this.code = code;
    }
    
    public String getMessage() {
        return message;
    }
    
    public void setMessage(String message) {
        this.message = message;
    }
    
    public T getData() {
        return data;
    }
    
    public void setData(T data) {
        this.data = data;
    }
}
