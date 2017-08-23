package tgs.com.mvvm.weight.adapter;

import android.databinding.ViewDataBinding;

import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;

import java.util.List;
import java.util.Map;

import tgs.com.mvvm.databinding.ItemBannerBinding;
import tgs.com.mvvm.utils.BannerLoader;

/**
 * Created by 田桂森 on 2017/8/22.
 */

public class LiveAdapter extends SimpleMultiAdapter {
    public static final int TYPE_BANNER = 0;
    public static final int TYPE_SECTION_HEADER = 1;
    public static final int TYPE_SECTION_FOOTER = 2;
    public static final int TYPE_STANDARD_CARD = 3;
    public static final int TYPE_LONG_CARD = 4;
    private List<String> mListBannerImgUrl;
    
    public LiveAdapter(Map<Integer, Integer> itemTypeLayoutMap) {
        super(itemTypeLayoutMap);
    }
    
    public void setBannerImg(List<String> listBannerImgUrl) {
        mListBannerImgUrl=listBannerImgUrl;
        notifyDataSetChanged();
    }
    
    @Override
    public void onCreateViewHolderDecorate(ViewDataBinding binding, int viewType) {
        if (viewType == TYPE_BANNER) {
            ItemBannerBinding itemBannerBinding = (ItemBannerBinding) binding;
            itemBannerBinding.banner.setImages(mListBannerImgUrl)
                    .setImageLoader(new BannerLoader())
                    .setIndicatorGravity(BannerConfig.RIGHT)
                    .setBannerAnimation(Transformer.DepthPage)
                    .start();
        }
    }
    
    public int getSpanSize(int position) {
        switch (getItemViewType(position)) {
            case TYPE_STANDARD_CARD:
                return 1;
            default:
                return 2;
        }
    }
}
