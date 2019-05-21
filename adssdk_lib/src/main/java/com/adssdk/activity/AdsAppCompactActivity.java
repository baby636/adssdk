package com.adssdk.activity;


import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.adssdk.AdsSDK;


/**
 * Created by Amit on 3/9/2018.
 */

public class AdsAppCompactActivity extends AppCompatActivity {

    private Activity activity;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activity = this;
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        if (AdsSDK.getInstance() != null && AdsSDK.getInstance().getAdsInterstitial() != null) {
            AdsSDK.getInstance().getAdsInterstitial().showInterstitial(activity, true);
        }
    }

}
