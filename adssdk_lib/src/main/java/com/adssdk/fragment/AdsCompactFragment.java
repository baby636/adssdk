package com.adssdk.fragment;


import android.support.v4.app.Fragment;

import com.adssdk.AdsSDK;


/**
 * Created by Amit on 3/9/2018.
 */

public class AdsCompactFragment extends Fragment {

    protected boolean isAdVisible = true;
    protected boolean isAddingFragment = false;

    @Override
    public void onDestroy() {
        super.onDestroy();
        showAds();
    }

    protected void showAds() {
        if (isAdVisible && AdsSDK.getInstance().isAdsEnabled() && AdsSDK.getInstance() != null && AdsSDK.getInstance().getAdsInterstitial() != null) {
            AdsSDK.getInstance().getAdsInterstitial().showInterstitial(getActivity(), isAddingFragment);
        }
    }


    //    @Override
//    public void onBackPressed() {
//        super.onBackPressed();
//        if (AdsSDK.getInstance() != null && AdsSDK.getInstance().getAdsInterstitial() != null) {
//            AdsSDK.getInstance().getAdsInterstitial().showInterstitial(this);
//        }
//    }
}
