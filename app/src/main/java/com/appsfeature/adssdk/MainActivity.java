package com.appsfeature.adssdk;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;

import com.adssdk.advertisment.AdsBanner;


public class MainActivity extends AppCompatActivity {

    private Button btnFullscreenAd, btnShowRewardedVideoAd;
    private RelativeLayout relativeAdView;
    private AdsBanner adsBanner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        relativeAdView = (RelativeLayout) findViewById(R.id.relativeAdView);
        btnFullscreenAd = (Button) findViewById(R.id.btn_fullscreen_ad);
        btnShowRewardedVideoAd = (Button) findViewById(R.id.btn_show_rewarded_video);
        btnFullscreenAd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, InterstitialAdActivity.class));
            }
        });

        btnShowRewardedVideoAd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, RewardedVideoAdActivity.class));
            }
        });


        adsBanner = MyApplication.getInstance()
                .getAdsSdk().getAdsBanner().showBanner(relativeAdView);


    }

    @Override
    public void onPause() {
        adsBanner.pause();
        super.onPause();
    }

    @Override
    public void onResume() {
        super.onResume();
        adsBanner.resume();
    }

    @Override
    public void onDestroy() {
        adsBanner.destroy();
        super.onDestroy();
    }
}
