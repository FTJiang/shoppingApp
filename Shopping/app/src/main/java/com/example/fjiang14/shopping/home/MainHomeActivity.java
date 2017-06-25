package com.example.fjiang14.shopping.home;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.fjiang14.shopping.R;
import com.example.fjiang14.shopping.base.BaseActivitiy;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;
import com.youth.banner.listener.OnBannerListener;

import java.util.Arrays;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class MainHomeActivity extends BaseActivitiy implements OnBannerListener {

    @InjectView(R.id.banner)
    Banner banner;
    @InjectView(R.id.home_toolbar_main)
    Toolbar toolbar;


    private String[] mURLs;

    private List<String> mTitles;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_home_act);
        init();
    }

    private void init(){
        ButterKnife.inject(this);
        mURLs = getResources().getStringArray(R.array.url);
        String[] temp = new String[]{"1","2","3","4","5"};
        mTitles = Arrays.asList(temp);
        initBanner();
        setSupportActionBar(toolbar);
        AlphaTitleScrollView scroll = (AlphaTitleScrollView) findViewById(R.id.scrollview);
        scroll.setTitleAndHead(toolbar, banner);
    }

    @Override
    protected void onStart() {
        super.onStart();
        //开始轮播
        banner.startAutoPlay();
    }

    @Override
    protected void onStop() {
        super.onStop();
        //结束轮播
        banner.stopAutoPlay();
    }

    @Override
    public void OnBannerClick(int position) {
        showToast("你点击了："+position);
    }

    public void onRadioButtonClicked(View view){
        //setCurrentItem will cause onPageSelected event
        switch (view.getId()){
            case R.id.rdoBtn_home:
                //mInformationRadioButton.setChecked(true);
                break;
            case R.id.rdoBtn_category:
                //mMessageRadioButton.setChecked(true);
                break;
            case R.id.rdoBtn_cart:
                //mMessageRadioButton.setChecked(true);
                break;
            case R.id.rdoBtn_me:
                //mSettingRadioButton.setChecked(true);
                break;
        }
    }

    private void initBanner(){
        Banner banner = (Banner) findViewById(R.id.banner);
        //设置banner样式
        banner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR_TITLE);
        //设置图片加载器
        banner.setImageLoader(new GlideImageLoader());
        //设置图片集合
        banner.setImages(Arrays.asList(mURLs));
        banner.setBannerTitles(mTitles);
        //设置banner动画效果
        banner.setBannerAnimation(Transformer.DepthPage);
        //设置标题集合（当banner样式有显示title时）
        //设置自动轮播，默认为true
        banner.isAutoPlay(true);
        //设置轮播时间
        banner.setDelayTime(2000);
        //设置指示器位置（当banner模式中有指示器时）
        banner.setIndicatorGravity(BannerConfig.CENTER);
        //banner设置方法全部调用完毕时最后调用
        banner.start();
    }
}
