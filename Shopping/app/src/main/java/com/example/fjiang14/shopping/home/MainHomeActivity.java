package com.example.fjiang14.shopping.home;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;

import com.cleveroad.fanlayoutmanager.FanLayoutManager;
import com.cleveroad.fanlayoutmanager.FanLayoutManagerSettings;
import com.cleveroad.fanlayoutmanager.callbacks.FanChildDrawingOrderCallback;
import com.dalong.carrousellayout.CarrouselLayout;
import com.example.fjiang14.shopping.base.BaseActivitiy;
import com.example.fjiang14.shopping.util.SportCardsUtils;
import com.oushangfeng.marqueelayout.MarqueeLayout;
import com.oushangfeng.marqueelayout.MarqueeLayoutAdapter;
import com.oushangfeng.marqueelayout.OnItemClickListener;
import com.squareup.picasso.Picasso;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;
import com.youth.banner.listener.OnBannerListener;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;

import com.example.fjiang14.shopping.R;

import static android.R.attr.width;

public class MainHomeActivity extends BaseActivitiy{

    @InjectView(R.id.banner)
    Banner banner;
    @InjectView(R.id.home_toolbar_main)
    Toolbar toolbar;
    @InjectView(R.id.marquee_layout1)
    MarqueeLayout mMarqueeLayout1;
    //@InjectView(R.id.recyclerview)
    RecyclerView recyclerview;
    @InjectView(R.id.carrousel)
    CarrouselLayout carrousel;


    private SportCardsAdapter adapter;
    private FanLayoutManager fanLayoutManager;

    private String[] mURLs;

    private List<String> mTitles;

    public static String[] notices = new String[]{
            "Save up to 10% on select hand vacuums",
            "Save up to 10% on select cosmetic",
            "Save up to 10% on select drinking water",
    };



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
        //initNotice();
        initMarqueeLayout();
        //initRecyclerView();
        carrousel.setR(width/3400)//设置R的大小
                .setAutoRotation(true)//是否自动切换
                .setAutoRotationTime(1500);//自动切换的时间  单位毫秒
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
        banner.setBannerStyle(BannerConfig.NOT_INDICATOR);
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
        //banner.setIndicatorGravity(BannerConfig.CENTER);
        //banner设置方法全部调用完毕时最后调用
        banner.start();
        banner.setOnBannerListener(new OnBannerListener() {
            @Override
            public void OnBannerClick(int position) {
                showToast("你点击了："+position);
            }
        });
    }

    /*
    private void initNotice(){
        vNotice.start(Arrays.asList(notices));
        vNotice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showToast(notices[vNotice.getIndex()]);
            }
        });
    }
    */
    private void initMarqueeLayout(){
        final List<String> imgs = new ArrayList<>();
        imgs.add("https://images-na.ssl-images-amazon.com/images/G/01/shazam/00029878-Stanley-Black-Decker-SiteStripe-980-55-ALqF0._V509360654_.jpg");
        imgs.add("https://images-na.ssl-images-amazon.com/images/G/01/shazam/00029878-Stanley-Black-Decker-SiteStripe-980-55-ALqF0._V509360654_.jpg");
        imgs.add("https://images-na.ssl-images-amazon.com/images/G/01/shazam/00029878-Stanley-Black-Decker-SiteStripe-980-55-ALqF0._V509360654_.jpg");
        MarqueeLayoutAdapter<String> adapter1 = new MarqueeLayoutAdapter<String>(imgs) {
            @Override
            public int getItemLayoutId() {
                return R.layout.main_home_marquee_image;
            }

            @Override
            public void initView(View view, int position, String item) {
                Picasso.with(view.getContext()).load(item).into((ImageView) view);
            }
        };
        // 设置点击事件，第二个参数为不定长id，为想要点击的view的id，若为空或者不传的话默认为点击最外层的view
        adapter1.setItemClickListener(new OnItemClickListener() {
            @Override
            public void onClick(View view, int position) {
                showLog("MainActivity-74行-onClick(): " + position);
            }
        }, R.id.iv);
        mMarqueeLayout1.setAdapter(adapter1);
        mMarqueeLayout1.start();
    }

    private void initRecyclerView(){
        FanLayoutManagerSettings fanLayoutManagerSettings = FanLayoutManagerSettings
                .newBuilder(this)
                .withFanRadius(true)
                .withAngleItemBounce(5)
                .withViewWidthDp(120)
                .withViewHeightDp(160)
                .build();
        fanLayoutManager = new FanLayoutManager(this,fanLayoutManagerSettings);
        recyclerview.setLayoutManager(fanLayoutManager);

        adapter = new SportCardsAdapter(this);
        adapter.addAll(SportCardsUtils.generateSportCards());
        recyclerview.setAdapter(adapter);
        recyclerview.setChildDrawingOrderCallback(new FanChildDrawingOrderCallback(fanLayoutManager));
    }

}
