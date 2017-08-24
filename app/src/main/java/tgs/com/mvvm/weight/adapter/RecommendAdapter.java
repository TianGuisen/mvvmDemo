package tgs.com.mvvm.weight.adapter;

import android.databinding.ViewDataBinding;

import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;

import java.util.List;
import java.util.Map;

import tgs.com.mvvm.core.BannerLoader;
import tgs.com.mvvm.databinding.ItemBannerBinding;
import tgs.com.mvvm.databinding.ItemStandardCardBinding;

/**
 * Created by 田桂森 on 2017/8/22.
 */

public class RecommendAdapter extends SimpleMultiAdapter {
    /**
     * 轮播图
     */
    public static final int TYPE_BANNER = 0;
    /**
     * card的头
     */
    public static final int TYPE_SECTION_HEADER = 1;
    /**
     * card的脚
     */
    public static final int TYPE_SECTION_FOOTER = 2;
    /**
     * card的内容
     */
    public static final int TYPE_STANDARD_CARD = 3;
    /**
     * 长card
     */
    public static final int TYPE_LONG_CARD = 4;
    private List<String> mListBannerImgUrl;
    
    public RecommendAdapter(Map<Integer, Integer> itemTypeLayoutMap) {
        super(itemTypeLayoutMap);
    }
    
    public void setBannerImg(List<String> listBannerImgUrl) {
        mListBannerImgUrl = listBannerImgUrl;
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
    
    /**
     * 在TYPE_STANDARD_CARD布局的时候，返回item的大小为1，其实可以一行占2个item，其他布局为2。
     *
     * @param position
     * @return
     */
    public int getSpanSize(int position) {
        switch (getItemViewType(position)) {
            case TYPE_STANDARD_CARD:
                return 1;
            default:
                return 2;
        }
    }
    private OnBannerClickListener bannerClickListener;
    
    public void setBannerClickListener(OnBannerClickListener bannerClickListener) {
        this.bannerClickListener = bannerClickListener;
    }
    
    public interface OnBannerClickListener{
        void bannerClick(int position);
    }
    
    @Override
    public void decorator(Object bean, BaseAdapter.BindViewHolder holder, int position) {
        switch (mResLayout.get(position)) {
            case TYPE_BANNER:
                ItemBannerBinding itemBannerBinding = (ItemBannerBinding) holder.binding;
                itemBannerBinding.banner.setOnBannerListener(position1 -> bannerClickListener.bannerClick(position1));
                break;
            case TYPE_SECTION_HEADER:
                
                break;
            case TYPE_STANDARD_CARD:
                ItemStandardCardBinding itemStandardCardBinding = (ItemStandardCardBinding) holder.binding;
                itemStandardCardBinding.cardView.setOnClickListener(v -> {
                    itemClickLisener.itemClick(bean,itemStandardCardBinding.getRoot(),position);
                });
                break;
            case TYPE_SECTION_FOOTER:
                
                break;
            case TYPE_LONG_CARD:
                
                break;
            
        }
    }
}
