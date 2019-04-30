package com.adssdk.advertisment;

import android.app.Activity;
import android.content.Context;
import android.support.v4.app.Fragment;
import android.text.TextUtils;

import com.adssdk.AdsSDK;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.InterstitialAd;


/**
 * Created by Amit on 3/9/2018.
 */

public class AdsInterstitial {

    private Context context;
    private String adId;
    private Activity activity;
    private boolean isActivityCloseWithAd = true;
    private int failCount = 0;
    public static final int FAILED_MAX_COUNT = 5;

    public AdsInterstitial(Context context, String adId) {
        this.context = context;
        this.adId = adId;
        this.isActivityCloseWithAd = true;
        if (!TextUtils.isEmpty(adId)) {
            initFullAds();
        }
    }

    private InterstitialAd mInterstitialAd;

    private void initFullAds() {
        if (AdsSDK.getInstance().isAdsEnabled()) {
            mInterstitialAd = new InterstitialAd(context);
            mInterstitialAd.setAdUnitId(adId);
            mInterstitialAd.loadAd(AdsSDK.getAdRequest());
            mInterstitialAd.setAdListener(new AdListener() {
                @Override
                public void onAdFailedToLoad(int i) {
                    super.onAdFailedToLoad(i);
                    if (failCount < FAILED_MAX_COUNT) {
                        failCount++;
                        mInterstitialAd.loadAd(AdsSDK.getAdRequest());
                    }
                }

                @Override
                public void onAdLoaded() {
                    super.onAdLoaded();
                    failCount = 0;
                }

                @Override
                public void onAdClosed() {
                    super.onAdClosed();
                    if (activity != null && isActivityCloseWithAd) {
                        activity.finish();
                    }
                    initFullAds();
                }
            });
        }
    }

    public void showInterstitial(Activity activity) {
        showInterstitial(activity, true);
    }
    public void showInterstitial(Activity activity, boolean isActivityCloseWithAd) {
        this.isActivityCloseWithAd = isActivityCloseWithAd;
        this.activity = activity;
        if (mInterstitialAd != null && mInterstitialAd.isLoaded()) {
            mInterstitialAd.show();
        } else if (activity != null) {
            if(isActivityCloseWithAd) {
                activity.finish();
            }
        }
    }
    public void showInterstitial(Fragment fragment, boolean isActivityCloseWithAd) {
        this.isActivityCloseWithAd = isActivityCloseWithAd;
        if (mInterstitialAd != null && mInterstitialAd.isLoaded()) {
            mInterstitialAd.show();
        }  else if (activity != null) {
            if(isActivityCloseWithAd) {
                activity.finish();
            }
        }
    }
}
