package com.adssdk;

import android.content.Context;
import android.text.TextUtils;

import com.adssdk.activity.OfflineAdsActivity;
import com.adssdk.advertisment.AdsBanner;
import com.adssdk.advertisment.AdsInterstitial;
import com.adssdk.advertisment.AdsRewardedVideo;
import com.adssdk.util.AdsTestIds;
import com.adssdk.model.AdsIds;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.MobileAds;

public class AdsSDK {

    private static AdsSDK adsSDK ;
    private static boolean enableTestDevice = false;
    private final Context context;
    private boolean isTestAds;
    private AdsIds adsIds;
    private boolean isAdsEnabled = false;
    private AdsInterstitial adsInterstitial;
    private AdsBanner adsBanner;
    private AdsRewardedVideo adsRewardVideo;
    private static String testDeviceId;

    public AdsSDK(Context context, boolean isTestAds) {
        this.context = context;
        this.isTestAds = isTestAds;
        OfflineAdsActivity.isActivityVisible = false;
    }


    public static AdsSDK getInstance(Context context) {
        if(adsSDK == null){
            adsSDK = new AdsSDK(context, BuildConfig.DEBUG);
        }
        return adsSDK;
    }

    public static AdsSDK getInstance() {
        return adsSDK;
    }

    public static AdRequest getAdRequest(){
        AdRequest.Builder builder = new AdRequest.Builder();
        if(enableTestDevice) {
            builder.addTestDevice("5E254AC1CF02E640413645E46C8A1A64");
            builder.addTestDevice("2C374DC7F25FC3474B235578C446D36F");
            builder.addTestDevice("A3B72905D6C2F896FBDB8A01F186A77D");
            if(!TextUtils.isEmpty(testDeviceId)){
                builder.addTestDevice(testDeviceId);
            }else{
                builder.addTestDevice(AdRequest.DEVICE_ID_EMULATOR);
            }
        }
        return builder.build();
    }


    public AdsSDK initAds() {
        MobileAds.initialize(context, isTestAds ? AdsTestIds.TEST_ID_APP : adsIds.getAdMobAppId());
        adsInterstitial = new AdsInterstitial(context , isTestAds ? AdsTestIds.TEST_ID_INTERSTITIAL : adsIds.getAdMobInterstitialId() );
        adsBanner = new AdsBanner(context , isTestAds ? AdsTestIds.TEST_ID_BANNER : adsIds.getAdMobBannerId() );
        adsRewardVideo = new AdsRewardedVideo(context , isTestAds ? AdsTestIds.TEST_ID_REWARDED_VIDEO : adsIds.getAdMobRewardedVideoId() );
        return this;
    }


    public AdsSDK setAdsId(AdsIds adsIds) {
        this.adsIds=adsIds;
        return this;
    }

    public boolean isAdsEnabled() {
        return isAdsEnabled;
    }

    public AdsSDK setAdsEnabled(boolean adsEnabled) {
        isAdsEnabled = adsEnabled;
        return this;
    }
    public AdsSDK addTestDevice(String testDeviceId) {
        AdsSDK.testDeviceId = testDeviceId;
        return this;
    }

    public AdsSDK setEnableTestDevice(boolean enableTestDevice) {
        AdsSDK.enableTestDevice = enableTestDevice;
        return this;
    }

    public AdsBanner getAdsBanner() {
        return adsBanner;
    }


    public AdsInterstitial getAdsInterstitial() {
        return adsInterstitial;
    }

    public AdsRewardedVideo getAdsRewardVideo() {
        return adsRewardVideo;
    }
}
