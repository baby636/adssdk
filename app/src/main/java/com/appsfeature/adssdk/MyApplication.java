package com.appsfeature.adssdk;

import android.app.Application;

import com.adssdk.AdsSDK;
import com.adssdk.model.AdsIds;


/**
 * Created by ravi on 25/12/17.
 */

public class MyApplication extends Application {


    private AdsSDK adsSdk;
    private static MyApplication instance;
//
//    public MyApplication() {
//        instance = this;
//    }

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        getAdsSdk();
    }

    public static MyApplication get() {
        return instance;
    }

    public AdsSDK getAdsSdk() {
        if(adsSdk == null){
            AdsIds adsIds = AdsIds.Builder()
                    .setAdMobAppId(Const.AD_MOB_APP_ID)
                    .setAdMobBannerId(Const.AD_MOB_BANNER_ID)
                    .setAdMobInterstitialId(Const.AD_MOB_INTERSTITIAL_ID)
                    .setAdMobRewardedVideoId(Const.AD_MOB_REWARDED_VIDEO_ID);
            adsSdk = AdsSDK.getInstance(instance)
                    .setAdsEnabled(true)
                    .setAdsId(adsIds)
                    .initAds();
        }
        return adsSdk;
    }
}
