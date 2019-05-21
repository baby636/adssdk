package com.adssdk.activity;


import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.adssdk.AdsSDK;
import com.adssdk.R;
import com.adssdk.util.AdsUtil;


/**
 * Created by Amit on 3/9/2018.
 */

public class OfflineAdsActivity extends AppCompatActivity {

    private OfflineAdsActivity activity;
    private boolean isClose = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_offline_ads);
        activity = this;
        initUi();
    }
    private void initUi() {
        (findViewById(R.id.ib_close)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (activity != null) {
                    isClose = true;
                    onBackPressed();
                }
            }
        });
        (findViewById(R.id.rl_ad)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (activity != null)
                    AdsUtil.openExternalBrowser(activity, AdsUtil.BASE_URL);
            }
        });
    }

    @Override
    public void onBackPressed() {
        if(isClose)
        super.onBackPressed();
    }
}
