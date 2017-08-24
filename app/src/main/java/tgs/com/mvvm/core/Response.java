package tgs.com.mvvm.core;

import io.reactivex.functions.Function;

/**
 * Created by 田桂森 on 2017/8/24.
 */

public class Response<T, R> {
    private Function<T, R> execute;
    
    public Response(Function<T, R> execute) {
        this.execute = execute;
    }
    
    public R execute(T parameter) {
        if (execute != null) {
            try {
                return execute.apply(parameter);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return null;
    }
}
