package tgs.com.mvvm.base;

import java.io.Serializable;

/**
 * Created by 田桂森 on 2017/5/9.
 */

public class BaseBean<T> implements Serializable {
    private int status;
    private String message;
    private T data;
    
    @Override
    public String toString() {
        return "BaseBean{" +
                "status=" + status +
                ", message='" + message + '\'' +
                ", data=" + data +
                '}';
    }
    
    public int getStatus() {
        return status;
    }
    
    public void setStatus(int status) {
        this.status = status;
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
