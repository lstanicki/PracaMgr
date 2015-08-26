package com.example.lukasz.pracamgr;

import android.content.Context;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationManager;
import android.location.LocationProvider;

import java.text.SimpleDateFormat;
import java.util.TimeZone;

/**
 * Created by Lukasz on 2015-08-25.
 */
public class LogHelper {
    static final String _timeStampFormat = "yyyy-MM-dd'T'HH:mm:ss";
    static final String _timeStampTimeZoneId = "UTC";

    public static String FormatLocationInfo(String provider, double lat, double lng, float accuracy, long time) {
        SimpleDateFormat timeStampFormatter = new SimpleDateFormat(_timeStampFormat);
        timeStampFormatter.setTimeZone(TimeZone.getTimeZone(_timeStampTimeZoneId));

        String timeStamp = timeStampFormatter.format(time);

        String logMessage = String.format("%s | lat/lng=%f | accuracy=%f | Time=%s",
                provider, lat, lng, accuracy, timeStamp);

        return logMessage;
    }

    public static String FormatLocationInfo(Location location) {
        String provider = location.getProvider();
        double lat = location.getLatitude();
        double lng = location.getLongitude();
        float accurancy = location.getAccuracy();
        long time = location.getTime();

        return LogHelper.FormatLocationInfo(provider, lat, lng, accurancy, time);
    }

    public static String formatLocationProvider(Context context, LocationProvider provider) {
        String name = provider.getName();
        int horizontalAccuracy = provider.getAccuracy();
        int powerRequirements = provider.getPowerRequirement();
        boolean hasMonetaryCost = provider.hasMonetaryCost();
        boolean requiresCell = provider.requiresCell();
        boolean requiresNetwork = provider.requiresNetwork();
        boolean requiresSatellite = provider.requiresSatellite();
        boolean supportsAltitude = provider.supportsAltitude();
        boolean supportsBearing = provider.supportsBearing();
        boolean supportsSpeed = provider.supportsSpeed();

        String enabledMessage = "UNKOWN";
        if (context != null) {
            LocationManager lm = (LocationManager) context.getSystemService(context.LOCATION_SERVICE);
            enabledMessage = yOrN(lm.isProviderEnabled(name));
        }

        String horizontalAccuracyDisplay = translateAccuracyFineCourse(horizontalAccuracy);
        String powerRequirementsDisplay = translatePower(powerRequirements);

        String logMessage = String.format("%s | enabled:%s | horizontal accuracy:%s | power:%s |" +
                "cost:%s | uses cell:%s | uses network:%s | uses satellite:%s | " +
                "has altitude:%s | has bearing:%s | has speed:%s",
                name, enabledMessage, horizontalAccuracyDisplay, powerRequirementsDisplay,
                yOrN(hasMonetaryCost), yOrN(requiresCell), yOrN(requiresNetwork), yOrN(requiresSatellite),
                yOrN(supportsAltitude), yOrN(supportsBearing), yOrN(supportsSpeed));

        return logMessage;
    }

    public static String yOrN(boolean value) {
        return value ? "Y" : "N";
    }

    public static String yOrN(int value) {
        return value != 0 ? "Y" : "N";
    }

    public static String translateStatus(int value) {
        String message = "UNDEFINED";
        switch (value) {
            case LocationProvider.AVAILABLE:
                message = "AVAILABLE";
                break;
            case LocationProvider.OUT_OF_SERVICE:
                message = "OUT_OF_SERVICE";
                break;
            case LocationProvider.TEMPORARILY_UNAVAILABLE:
                message = "TEMPORARILY_UNAVAILABLE";
                break;
        }

        return message;
    }

    public static String translateAccuracyFineCourse(int value) {
        String message = "UNDEFINED";
        switch (value) {
            case Criteria.ACCURACY_COARSE:
                message = "COARSE";
                break;
            case Criteria.ACCURACY_FINE:
                message = "FINE";
                break;
        }
        return message;
    }

    public static String translatePower(int value) {
        String message = "UNDEFINED";
        switch (value) {
            case Criteria.POWER_HIGH:
                message = "POWER_HIGH";
                break;
            case Criteria.POWER_MEDIUM:
                message = "POWER_MEDIUM";
                break;
            case Criteria.POWER_LOW:
                message = "POWER_LOW";
                break;
        }
        return message;
    }

}
