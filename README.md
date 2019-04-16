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
    implementation 'com.google.android.gms:play-services-ads:17.2.0'
}
```   

In your <b>AndroidManifest.xml</b> class:
#### Usage method
```xml 
      <application
        android:name=".AppApplication"
           ...
             <meta-data
                   android:name="com.google.android.gms.ads.APPLICATION_ID"
                   android:value="@string/ads_admob_app_id" />
      </application>
```

#### Usage method
```java 
public class AppApplication extends Application {

    private AppApplication _instance;
    private AdsSDK adsSdk;

    public AppApplication getInstance() {
        return _instance;
    }

    public AdsSDK getAdsSdk() {
        return adsSdk;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        _instance = this;
        AdsIds adsIds = AdsIds.Builder()
                .setAdMobAppId(Constant.AD_MOB_ID)
                .setAdMobBannerId(Constant.BANNER_ID)
                .setAdMobInterstitialId(Constant.INTERSTITIAL_ID)
                .setAdMobRewardedVideoId(Constant.REWARDED_VIDEO_ID);
        adsSdk = AdsSDK.getInstance(this)
                .setEnableTestDevice(false)
                .setAdsEnabled(true)
                .setAdsId(adsIds)
                .initAds();
                
                
        Note: -> when run on emulator use
                 .setEnableTestDevice(false)
              -> when run on device use
                 .setEnableTestDevice(true)
              -> run app and find keyword 'addTestDevice' in logcat
              -> after getting addTestDevice id use
                 .setEnableTestDevice(true)
                 .addTestDevice("1D3BBA07CC14A4EC5442EB2B3A2CBE7D")
        
    }
}
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

#### Rewarded Video Ad  
```java
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

        adsRewardVideo = MyApplication.getInstance()
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

```
