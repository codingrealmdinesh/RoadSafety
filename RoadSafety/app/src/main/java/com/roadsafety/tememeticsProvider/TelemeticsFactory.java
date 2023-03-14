package com.roadsafety.tememeticsProvider;

import android.app.Activity;

public class TelemeticsFactory{

    private static ITelemeticsProvider _zenRoadProvider = null;
    public static ITelemeticsProvider GetZenRoadProvider(Activity activity, String deviceId){
        if(_zenRoadProvider == null) _zenRoadProvider =  new ZenRoadTelemeticsProvider(activity, deviceId);

        return _zenRoadProvider;
    }

    public ITelemeticsProvider GetGamificationProvider(Activity activity){
        return null;
    }



    private static ITelemeticsProvider _mockRoadProvider = null;
    public  static  ITelemeticsProvider getMockTelematicsProvider()
    {
        if(_mockRoadProvider == null) _mockRoadProvider = new MocktelematicsProvider();
        return _mockRoadProvider;
    }
}
