package tgs.com.mvvm.utils;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.Priority;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.bumptech.glide.request.RequestOptions;
import com.youth.banner.loader.ImageLoader;

import tgs.com.mvvm.R;

/**
 * Created by 田桂森 on 2017/8/22.
 */

public class BannerLoader extends ImageLoader {
    
    @Override
    public void displayImage(Context context, Object path, ImageView imageView) {
        RequestOptions options = new RequestOptions()
                .centerCrop()
                .placeholder(R.drawable.bili_default_image_tv)
                .error(R.drawable.img_tips_error_banner_tv)
                .priority(Priority.HIGH)
                .diskCacheStrategy(DiskCacheStrategy.NONE);
        Glide.with(context.getApplicationContext())
                .load(path)
                .apply(options)
                .transition(new DrawableTransitionOptions().crossFade(500))
                .into(imageView);
    }
}
