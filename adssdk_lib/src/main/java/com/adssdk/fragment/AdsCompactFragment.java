package com.adssdk.fragment;


import android.support.v4.app.Fragment;

import com.adssdk.AdsSDK;


/**
 * Created by Amit on 3/9/2018.
 */

public class AdsCompactFragment extends Fragment {

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (AdsSDK.getInstance() != null && AdsSDK.getInstance().getAdsInterstitial() != null) {
            AdsSDK.getInstance().getAdsInterstitial().showInterstitial(getActivity(), false);
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
