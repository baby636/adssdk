package com.appsfeature.adssdk;


import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.adssdk.R;
import com.adssdk.fragment.AdsCompactFragment;
import com.adssdk.util.AdsUtil;


/**
 * Created by Amit on 3/9/2018.
 */

public class InterstitialAdFragment extends AdsCompactFragment {


    private Activity activity;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        activity = getActivity();
        return (inflater.inflate(R.layout.fragment_sample, container, false));
    }


}
