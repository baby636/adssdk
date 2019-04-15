package com.adssdk.activity;


import android.support.v7.app.AppCompatActivity;

import com.adssdk.AdsSDK;


/**
 * Created by Amit on 3/9/2018.
 */

public class AdsAppCompactActivity extends AppCompatActivity {

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        if (AdsSDK.getInstance() != null && AdsSDK.getInstance().getAdsInterstitial() != null) {
            AdsSDK.getInstance().getAdsInterstitial().showInterstitial(this);
        }
    }
}
