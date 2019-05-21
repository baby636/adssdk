package com.appsfeature.adssdk;

import android.os.Bundle;

import com.adssdk.activity.AdsAppCompactActivity;


public class InterstitialAdActivity extends AdsAppCompactActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
    }

}
