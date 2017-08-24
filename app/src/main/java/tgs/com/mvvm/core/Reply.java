package tgs.com.mvvm.core;

import android.view.View;

import io.reactivex.functions.BiConsumer;
import io.reactivex.functions.Consumer;

/**
 * Created by 田桂森 on 2017/8/24.
 */

public class Reply<T> {
    private Consumer<T> execute1;
    private BiConsumer<View, Integer> execute2;
    public Reply(Consumer<T> execute) {
        this.execute1 = execute;
    }
    
    public Reply(BiConsumer<View, Integer> execute) {
        this.execute2 = execute;
    }
    
    public void execute(T t) {
        if (execute1 != null) {
            try {
                execute1.accept(t);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    
    public void execute(View view, Integer position) {
        if (execute2 != null ) {
            try {
                execute2.accept(view, position);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
