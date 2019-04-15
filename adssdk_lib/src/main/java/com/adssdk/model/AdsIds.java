package com.adssdk.model;

public class AdsIds {

    private String adMobAppId;
    private String adMobBannerId;
    private String adMobInterstitialId;
    private String adMobRewardedVideoId;

    public String getAdMobAppId() {
        return adMobAppId;
    }

    public AdsIds setAdMobAppId(String adMobAppId) {
        this.adMobAppId = adMobAppId;
        return this;
    }

    public String getAdMobBannerId() {
        return adMobBannerId;
    }

    public AdsIds setAdMobBannerId(String adMobBannerId) {
        this.adMobBannerId = adMobBannerId;
        return this;
    }

    public String getAdMobInterstitialId() {
        return adMobInterstitialId;
    }

    public AdsIds setAdMobInterstitialId(String adMobInterstitialId) {
        this.adMobInterstitialId = adMobInterstitialId;
        return this;
    }

    public String getAdMobRewardedVideoId() {
        return adMobRewardedVideoId;
    }

    public AdsIds setAdMobRewardedVideoId(String adMobRewardedVideoId) {
        this.adMobRewardedVideoId = adMobRewardedVideoId;
        return this;
    }

    public static AdsIds Builder() {
        return new AdsIds();
    }
}
