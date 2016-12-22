package com.example.administrator.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RelativeLayout;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private BannerManager mBanner;
    private RelativeLayout rlContainer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        rlContainer = (RelativeLayout) findViewById(R.id.rl_container);
        mBanner = BannerManager.getInstance();
        mBanner.initView(this,rlContainer).attachViewPager(R.id.vp_ad).setLocalBanner(getLocalBanner()).startLoop(true);
    }


    private List<Banner> getLocalBanner(){
        List<Banner> localBanners = new ArrayList<>();
        Banner banner1 = new Banner();
        banner1.setId(R.drawable.banner01);
        String link01 = "1";
        banner1.setLink(link01);

        Banner banner2 = new Banner();
        banner2.setId(R.drawable.banner03);
        String link02 = "2";
        banner2.setLink(link02);

        Banner banner3 = new Banner();
        banner3.setId(R.drawable.banner02);
        String link03 = "3";
        banner3.setLink(link03);

        localBanners.add(banner1);
        localBanners.add(banner2);
        localBanners.add(banner3);

        return localBanners;
    }

}
