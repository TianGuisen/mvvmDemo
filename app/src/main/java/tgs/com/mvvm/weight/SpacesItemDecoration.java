package tgs.com.mvvm.weight;

import android.graphics.Rect;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by Neil on 2016/8/7.
 */
public class SpacesItemDecoration extends RecyclerView.ItemDecoration {
    private final int mSpace;
    private GridLayoutManager.SpanSizeLookup spansizeloopup;

    public SpacesItemDecoration(int space, GridLayoutManager.SpanSizeLookup spansizelookUp) {
        this.mSpace = space;
        this.spansizeloopup = spansizelookUp;
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {

        int childAdapterPosition = parent.getChildAdapterPosition(view);
        int itemViewType = parent.getAdapter().getItemViewType(childAdapterPosition);

        switch (itemViewType) {
            case 0:
                outRect.set(0, 0, 0, mSpace);
                break;

            case 1:
                outRect.set(mSpace, 0, mSpace, mSpace);
                break;

            case 2:
                outRect.set(mSpace, 0, mSpace, mSpace);
                break;

            case 3:
                int spanIndex = spansizeloopup.getSpanIndex(childAdapterPosition, 2);
                if (spanIndex == 0) {
                    outRect.set(mSpace, 0, mSpace / 2, mSpace);
                } else {
                    outRect.set(mSpace / 2, 0, mSpace, mSpace);
                }
                break;

            case 4:
                outRect.set(mSpace, 0, mSpace, mSpace);
                break;
        }
    }
}


