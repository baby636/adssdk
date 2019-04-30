package com.appsfeature.adssdk;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import com.adssdk.advertisment.AdsRewardedVideo;
import com.adssdk.listener.RewardListener;


public class RewardedVideoAdActivity extends AppCompatActivity {


    private AdsRewardedVideo adsRewardVideo;
    private boolean mVideoAvailable = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rewarded_video_ad);
        (findViewById(R.id.btnopenrewardvideo)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mVideoAvailable){
                    adsRewardVideo.showRewardedVideo();
                }else {
                    Toast.makeText(RewardedVideoAdActivity.this, "Video not available", Toast.LENGTH_SHORT).show();
                    adsRewardVideo.initVideo(rewardListener);
                }
            }
        });

        adsRewardVideo = MyApplication.get()
                .getAdsSdk().getAdsRewardVideo().initVideo(rewardListener);

    }

    RewardListener rewardListener =  new RewardListener() {
        @Override
        public void onRewarded(int amount) {

        }

        @Override
        public void onClosed(boolean isCompleted) {

        }

        @Override
        public void onVideoAdLoaded(boolean isVideoAvailable) {
            mVideoAvailable=isVideoAvailable;
        }
    };


    @Override
    public void onResume() {
        adsRewardVideo.resume();
        super.onResume();
    }

    @Override
    public void onPause() {
        adsRewardVideo.pause();
        super.onPause();
    }

    @Override
    public void onDestroy() {
        adsRewardVideo.destroy();
        super.onDestroy();
    }
}
