package com.appsfeature.adssdk;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.RelativeLayout;

import com.adssdk.advertisment.AdsBanner;


public class MainActivity extends AppCompatActivity {

    private AdsBanner adsBanner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RelativeLayout relativeAdView = findViewById(R.id.relative_ad_view);

        addLickListener();

        adsBanner = MyApplication.get()
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


    private void addLickListener() {
        (findViewById(R.id.btn_fullscreen_ad)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, InterstitialAdActivity.class));
            }
        });

        (findViewById(R.id.btn_show_rewarded_video)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, RewardedVideoAdActivity.class));
            }
        });
    }

}
