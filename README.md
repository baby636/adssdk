# AdsSDK
Easy way to show AdMob ads in Android.
 
<b>Also Support Android Room Library</b>
  
## Setup

Add this to your project build.gradle
``` gradle
allprojects {
    repositories {
        maven { url 'https://jitpack.io' }
    }
}
```
#### Dependency 
[![](https://jitpack.io/v/appsfeature/adssdk.svg)](https://jitpack.io/#appsfeature/adssdk)
```gradle
dependencies {
    implementation 'com.github.appsfeature:adssdk:x.y'
}
```   

In your <b>AndroidManifest.xml</b> class:
#### Usage method
```java 
      <application
           ...
             <meta-data
                   android:name="com.google.android.gms.ads.APPLICATION_ID"
                   android:value="@string/ads_admob_app_id" />
      </application>
```

#### Usage method
```java 
      AdsIds adsIds = AdsIds.Builder()
                    .setAdMobAppId(Const.AD_MOB_APP_ID)
                    .setAdMobBannerId(Const.AD_MOB_BANNER_ID)
                    .setAdMobInterstitialId(Const.AD_MOB_INTERSTITIAL_ID)
                    .setAdMobRewardedVideoId(Const.AD_MOB_REWARDED_VIDEO_ID);
      AdsSDK adsSdk = AdsSDK.getInstance(this)
                .setAdsEnabled(true)
                .setAdsId(adsIds)
                .initAds();
```


#### Banner Ad
```java 
    adsBanner = MyApplication.getInstance()
                .getAdsSdk().getAdsBanner().showBanner(relativeLayout);
    @Override
    public void onPause() {
        adsBanner.pause();
        super.onPause();
    }

    @Override
    public void onResume() {
        super.onResume();
        adsBanner.resume();
    }

    @Override
    public void onDestroy() {
        adsBanner.destroy();
        super.onDestroy();
    }
```

#### Interstitial Ad  
```java 
    MyApplication.getInstance()
                .getAdsSdk().getAdsInterstitial().showInterstitial(this);
                
        ----------- OR -------------
   Activity extends AdsAppCompactActivity
```

#### Interstitial Ad  
```java 
    MyApplication.getInstance()
                .getAdsSdk().getAdsInterstitial().showInterstitial(this);
                
        ----------- OR -------------
   Activity extends AdsAppCompactActivity
```
