package com.adssdk.fragment;


import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.adssdk.AdsSDK;
import com.adssdk.R;
import com.adssdk.util.AdsUtil;


/**
 * Created by Amit on 3/9/2018.
 */

public class OfflineAds extends Fragment {


    private Activity activity;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        activity = getActivity();
        return viewHolder(inflater.inflate(R.layout.fragment_offline_ads, container, false));
    }

    private View viewHolder(View inflate) {
        (inflate.findViewById(R.id.ib_close)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (activity != null)
                    activity.onBackPressed();
            }
        });
        (inflate.findViewById(R.id.rl_ad)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (activity != null)
                    AdsUtil.openExternalBrowser(activity, AdsUtil.BASE_URL);
            }
        });

        return inflate;
    }
}
