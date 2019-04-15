package com.adssdk.listener;

public interface RewardListener {
    void onRewarded(int amount);
    void onClosed(boolean isCompleted);
    void onVideoAdLoaded(boolean isVideoAvailable);
}
