package tgs.com.mvvm.utils;

import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.Priority;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.bumptech.glide.request.RequestOptions;

import tgs.com.mvvm.MyApplication;
import tgs.com.mvvm.R;

/**
 * Created by 田桂森 on 2017/8/31.
 */

public class GlideUtil {
    
    public static void setImg(ImageView iv, Object url) {
        Glide.with(MyApplication.getAppContext())
                .load(url)
                .into(iv);
    }
    
    public static RequestOptions getRequestOptions() {
        RequestOptions options = new RequestOptions()
                .centerCrop()
                .error(R.drawable.img_tips_error_banner_tv)
                .priority(Priority.HIGH)
                .diskCacheStrategy(DiskCacheStrategy.NONE);
        return options;
    }
}
