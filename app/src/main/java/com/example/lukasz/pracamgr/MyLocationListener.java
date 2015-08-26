package com.example.lukasz.pracamgr;

import android.location.Location;
import android.location.LocationListener;
import android.os.Bundle;
import android.util.Log;

/**
 * Created by Lukasz on 2015-08-25.
 */
public class MyLocationListener implements LocationListener {

    final String _logTag = "Monitor Location";

    @Override
    public void onLocationChanged(Location location) {
        String provider = location.getProvider();
        double lat = location.getLatitude();
        double lng = location.getLongitude();
        float accuracy = location.getAccuracy();
        long time = location.getTime();

        String logMessage = LogHelper.FormatLocationInfo(provider, lat, lng, accuracy, time);

        Log.d(_logTag, "Monitor Location:" + logMessage);
    }

    @Override
    public void onStatusChanged(String s, int i, Bundle bundle) {

    }

    @Override
    public void onProviderEnabled(String s) {
        Log.d(_logTag, "Monitor Location - Provider Enabled:" + s);
    }

    @Override
    public void onProviderDisabled(String s) {
        Log.d(_logTag, "Monitor Location - Provider Disabled:" + s);
    }
}
