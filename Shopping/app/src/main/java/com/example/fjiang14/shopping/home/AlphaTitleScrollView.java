package com.example.fjiang14.shopping.home;

import android.content.Context;
import android.support.v7.widget.Toolbar;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.ScrollView;

import com.example.fjiang14.shopping.R;
import com.youth.banner.Banner;

/**
 * Created by fjiang14 on 6/24/2017.
 */

public class AlphaTitleScrollView extends ScrollView {

    public static final String TAG = "AlphaTitleScrollView";
    private int mSlop;
    private Toolbar toolbar;
    private Banner headView;

    public AlphaTitleScrollView(Context context, AttributeSet attrs,
                                int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    public AlphaTitleScrollView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
        // TODO Auto-generated constructor stub
    }

    public AlphaTitleScrollView(Context context) {
        this(context, null);
    }

    private void init(Context context) {
        // mSlop = ViewConfiguration.get(context).getScaledDoubleTapSlop();
        mSlop = 10;
        Log.i(TAG, mSlop + "");
    }

    /**
     *
     * @param headLayout
     *            头部布局
     * @param imageview
     *            标题
     */
    public void setTitleAndHead(Toolbar toolbar, Banner headView) {
        this.toolbar = toolbar;
        this.headView = headView;
    }

    @Override
    protected void onScrollChanged(int l, int t, int oldl, int oldt) {
        float headHeight = headView.getMeasuredHeight() /2;
        float headHeight2 = headHeight * 2;
        int alpha = 0;
        if(t < headHeight) {
             alpha = 255 - (int) (((float) t / headHeight) * 255);
            toolbar.setBackground(getResources().getDrawable(R.drawable.bg_toolbar));
        }else{
            alpha = (int) (((float) (t-headHeight) / (headHeight) ) * 255);
            toolbar.setBackground(getResources().getDrawable(R.color.colorWhite));
        }
        if (alpha >= 255)
            alpha = 255;
        if (alpha <= mSlop)
            alpha = 0;
        if(t > headHeight2)
            alpha = 255;
        toolbar.setAlpha(alpha/255f);

        super.onScrollChanged(l, t, oldl, oldt);
    }
}
