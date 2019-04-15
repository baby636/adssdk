package com.adssdk.advertisment;

import android.content.Context;

import com.adssdk.AdsSDK;
import com.adssdk.listener.RewardListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.reward.RewardItem;
import com.google.android.gms.ads.reward.RewardedVideoAd;
import com.google.android.gms.ads.reward.RewardedVideoAdListener;


/**
 * Created by Amit on 3/9/2018.
 */

public class AdsRewardedVideo {

    private final Context context;
    private final String adId;
    private RewardedVideoAd adView;
    private boolean isRewardedVideoLoaded = false;

    public AdsRewardedVideo(Context context, String adId) {
        this.context = context;
        this.adId = adId;
        this.initVideo(null);
    }

    public AdsRewardedVideo initVideo(final RewardListener callback) {
        if(AdsSDK.getInstance().isAdsEnabled()) {
            adView = MobileAds.getRewardedVideoAdInstance(context);
            adView.setRewardedVideoAdListener(new RewardedVideoAdListener() {

                @Override
                public void onRewarded(RewardItem rewardItem) {
                    int rewardAmount = rewardItem.getAmount();
                    callback.onRewarded(rewardAmount);
                }

                @Override
                public void onRewardedVideoAdLeftApplication() {
                    if(callback!=null)
                    callback.onClosed(false);
                }

                @Override
                public void onRewardedVideoAdClosed() {
                    if(callback!=null)
                        callback.onClosed(false);
                }

                @Override
                public void onRewardedVideoAdFailedToLoad(int errorCode) {
                    isRewardedVideoLoaded = false;
                    if(callback!=null)
                        callback.onClosed(false);
                }

                @Override
                public void onRewardedVideoCompleted() {
                    if(callback!=null)
                        callback.onClosed(true);
                }

                @Override
                public void onRewardedVideoAdLoaded() {
                    isRewardedVideoLoaded = true;
                    if(callback!=null)
                        callback.onVideoAdLoaded(true);
                }

                @Override
                public void onRewardedVideoAdOpened() {

                }

                @Override
                public void onRewardedVideoStarted() {

                }
            });

            loadRewardedVideoAd();
        }
        return this;
    }

    private void loadRewardedVideoAd() {
        adView.loadAd(adId,
                new AdRequest.Builder().build());

        // showing the ad to user
        showRewardedVideo();
    }

    public void showRewardedVideo() {
        // make sure the ad is loaded completely before showing it
        if (isRewardedVideoLoaded && adView!=null && adView.isLoaded()) {
            adView.show();
        }
    }

    public void pause() {
        if (adView != null) {
            adView.pause(context);
        }
    }

    public void resume() {
        if (adView != null) {
            adView.resume(context);
        }
    }

    public void destroy() {
        if (adView != null) {
            adView.destroy(context);
        }
    }
}
