package com.yumi.android.sdk.ads.adapter.mobvista;

import android.app.Activity;

import com.mintegral.msdk.MIntegralSDK;
import com.mintegral.msdk.out.MIntegralSDKFactory;
import com.mintegral.msdk.out.MTGRewardVideoHandler;
import com.mintegral.msdk.out.RewardVideoListener;

import java.util.Map;

/**
 * Created by hjl on 2017/11/28.
 */
public class MobvistaMediaAdapter {

    private static final String TAG = "MobvistaMediaAdapter";
    private static final int REQUEST_NEXT_MEDIA = 0x001;
    private MTGRewardVideoHandler mMvRewardVideoHandler;
    private Activity mActivity;
    private String key1;
    private String key2;
    private String key3;
    private String key4;


    public MobvistaMediaAdapter(Activity activity, String key1, String key2, String key3, String key4) {
        mActivity = activity;
        this.key1 = key1;
        this.key2 = key2;
        this.key3 = key3;
        this.key4 = key4;
    }

    public void onPrepareMedia() {
        try {
            if (mMvRewardVideoHandler != null) {
                mMvRewardVideoHandler.load();
            } else {
                initHandler();
                mMvRewardVideoHandler.load();
            }
        } catch (Exception e) {
        }
    }

    public void onShowMedia() {
        try {
            if (mMvRewardVideoHandler != null) {
                if (mMvRewardVideoHandler.isReady()) {
                    mMvRewardVideoHandler.show(key4);//"rewardid"
                } else {
                    mMvRewardVideoHandler.load();
                }
            }
        } catch (Exception e) {
        }
    }

    public boolean isMediaReady() {
        if (mMvRewardVideoHandler != null) {
            boolean isReady= mMvRewardVideoHandler.isReady();
            return isReady;
        }
        return false;
    }

    public void init() {
        try {
            MIntegralSDK sdk = MIntegralSDKFactory.getMIntegralSDK();
            Map<String, String> map = sdk.getMTGConfigurationMap(key1, key2); //appId, appKey
            sdk.init(map, mActivity);
            initHandler();
        } catch (Exception e) {
        }
    }

    public void callOnActivityDestroy() {
        mMvRewardVideoHandler = null;
    }

    public void onActivityPause() {

    }

    public void onActivityResume() {

    }

    private void initHandler() {
        try {
            mMvRewardVideoHandler = new MTGRewardVideoHandler(mActivity, key3); //UnitId
            mMvRewardVideoHandler.setRewardVideoListener(new RewardVideoListener() {

                @Override
                public void onVideoLoadSuccess(String unitId) {
                }

                @Override
                public void onVideoLoadFail(String errorMsg) {
                }

                @Override
                public void onShowFail(String errorMsg) {
                }

                @Override
                public void onAdShow() {
                }

                @Override
                public void onAdClose(boolean isCompleteView, String RewardName, float RewardAmout) {
                    //三个参数为：是否播放完，奖励名，奖励积分
                }

                @Override
                public void onVideoAdClicked(String unitId) {
                }

            });
        } catch (Exception e) {
        }
    }
}