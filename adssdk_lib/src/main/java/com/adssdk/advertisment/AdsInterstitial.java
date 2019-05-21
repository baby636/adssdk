package com.adssdk.advertisment;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.FragmentActivity;
import android.text.TextUtils;

import com.adssdk.AdsSDK;
import com.adssdk.activity.OfflineAdsActivity;
import com.adssdk.fragment.OfflineAds;
import com.adssdk.util.AdsUtil;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.InterstitialAd;


/**
 * Created by Amit on 3/9/2018.
 */

public class AdsInterstitial {

    private Context context;
    private String adId;
    private Activity mActivity;
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
                    if (mActivity != null && isActivityCloseWithAd) {
                        mActivity.finish();
                    }
                    initFullAds();
                }
            });
        }
    }

    public void showInterstitial(Activity activity) {
        showInterstitial(null, activity, true, false);
    }

    public void showInterstitial(FragmentActivity activity) {
        showInterstitial(activity, true);
    }

    public void showInterstitial(FragmentActivity activity, boolean addingFragment) {
        showInterstitial(activity, null, false, addingFragment);
    }

    public void showInterstitial(Activity activity, boolean isActivityCloseWithAd) {
        showInterstitial(null, activity, isActivityCloseWithAd, false);
    }

    private void showInterstitial(FragmentActivity fragmentActivity, Activity activity, boolean isActivityCloseWithAd, boolean addingFragment) {
        this.isActivityCloseWithAd = isActivityCloseWithAd;
        if (activity != null) {
            this.mActivity = activity;
        } else {
            this.mActivity = fragmentActivity;
        }
        if (mInterstitialAd != null && mInterstitialAd.isLoaded()) {
            mInterstitialAd.show();
        } else if (mActivity != null) {
            if (activity != null) {
                if(!OfflineAdsActivity.isActivityVisible) {
                    activity.startActivity(new Intent(activity, OfflineAdsActivity.class));
                }
            } else if (fragmentActivity != null) {
                if(addingFragment){
                    AdsUtil.addFragment(fragmentActivity, new OfflineAds());
                }else {
                    fragmentActivity.startActivity(new Intent(fragmentActivity, OfflineAdsActivity.class));
                }
            }
        }
    }
}
