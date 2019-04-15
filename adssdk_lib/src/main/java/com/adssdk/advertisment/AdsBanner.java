package com.adssdk.advertisment;

import android.content.Context;
import android.text.TextUtils;
import android.widget.RelativeLayout;

import com.adssdk.AdsSDK;
import com.adssdk.util.AdsUtil;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;


/**
 * Created by Amit on 3/9/2018.
 */

public class AdsBanner {

    private final Context context;
    private final String adId;
    private final AdSize adSize;
    private int failCount = 0;
    public static final int FAILED_MAX_COUNT = 3;
    private AdView adView;

    public AdsBanner(Context context, String adId) {
        this.context = context;
        this.adId = adId;
        this.adSize = AdSize.BANNER;
    }

    public AdsBanner showBanner(RelativeLayout view) {
        return showBanner(view, adId, adSize);
    }

    public AdsBanner showBanner(RelativeLayout view, String id) {
        return showBanner(view, id, adSize);
    }


    public AdsBanner showSmartBanner(RelativeLayout view) {
        return showBanner(view, adId, AdSize.SMART_BANNER);
    }

    public AdsBanner showBanner(final RelativeLayout view, String id, AdSize adSize) {
        if (AdsSDK.getInstance().isAdsEnabled() && !TextUtils.isEmpty(adId) && context != null && view != null) {
            failCount = 0;
            adView = new AdView(context);
            adView.setAdUnitId(id);
            adView.setAdSize(adSize);
            adView.loadAd(AdsSDK.getAdRequest());
            adView.setAdListener(new AdListener() {
                @Override
                public void onAdFailedToLoad(int i) {
                    super.onAdFailedToLoad(i);
                    if (failCount < FAILED_MAX_COUNT && AdsUtil.isConnected(context)) {
                        failCount++;
                        adView.loadAd(AdsSDK.getAdRequest());
                    }
                }

                @Override
                public void onAdLoaded() {
                    super.onAdLoaded();
                    failCount = 0;
                    if (view != null) {
                        view.removeAllViews();
                        view.addView(adView);
                    }
                }
            });
            if (view != null) {
                view.removeAllViews();
                view.addView(adView);
            }
        }
        return this;
    }

    public void pause() {
        if (adView != null) {
            adView.pause();
        }
    }

    public void resume() {
        if (adView != null) {
            adView.resume();
        }
    }

    public void destroy() {
        if (adView != null) {
            adView.destroy();
        }
    }
}
