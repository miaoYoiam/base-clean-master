package com.mine.master.utils;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationListener;
import com.mine.master.App;

public class AMapLocationUtils implements AMapLocationListener {

    private AMapLocationClient mLocationClient;
    private AMapOnLocationChangedListener listener;

    private static volatile AMapLocationUtils Instance = null;

    public static AMapLocationUtils getInstance() {
        AMapLocationUtils instance = Instance;
        if (instance == null) {
            synchronized (AMapLocationUtils.class) {
                instance = Instance;
                if (instance == null) {
                    Instance = instance = new AMapLocationUtils();
                }
            }
        }
        return instance;
    }


    public void initAMap(App app) {
        mLocationClient = new AMapLocationClient(app);
        //设置定位回调监听
        mLocationClient.setLocationListener(this);
    }


    @Override
    public void onLocationChanged(AMapLocation aMapLocation) {
        if (listener != null) {
            listener.onLocationChanged(aMapLocation);
        }
    }

    public AMapLocationClient getLocationClient() {
        return mLocationClient;
    }

    public AMapOnLocationChangedListener getListener() {
        return listener;
    }

    public void setListener(AMapOnLocationChangedListener listener) {
        this.listener = listener;
    }

    public interface AMapOnLocationChangedListener {
        void onLocationChanged(AMapLocation aMapLocation);
    }
}
