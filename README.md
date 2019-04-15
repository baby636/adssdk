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

In your <b>activity</b> class:
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


#### SQLite Helper Refrence
For gatting SupportSQLiteOpenHelper refrence from Room database
```java 
    public SupportSQLiteOpenHelper getDBHelper() {
        return mRoomDatabase.getOpenHelper();
    }
```

For gatting SQLiteOpenHelper refrence from SQLite database 
```java 
    public SQLiteOpenHelper getDbHelper() {
        return dbHelper;
    }
```
