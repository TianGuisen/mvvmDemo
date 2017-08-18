package tgs.com.mvvm.bean;

import java.io.Serializable;

import lombok.Data;

/**
 * Created by 田桂森 on 2017/5/9.
 */
@Data
public class BaseBean<T> implements Serializable {
    private int code;
    private String message;
    private T data;
}
