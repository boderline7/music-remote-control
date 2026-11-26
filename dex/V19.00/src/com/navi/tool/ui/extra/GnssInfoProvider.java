package com.navi.tool.ui.extra;

import android.content.Context;
import android.location.GnssStatus;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Handler;
import android.os.Looper;
import com.yalantis.ucrop.view.CropImageView;
import java.util.Locale;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public class GnssInfoProvider {
    private static volatile GnssInfoProvider sInstance;
    private final androidx.lifecycle.g0 accuracy;
    private final androidx.lifecycle.g0 altitude;
    private GnssStatus.Callback gnssCallback;
    private LocationListener locationListener;
    private LocationManager locationManager;
    private boolean running;
    private final androidx.lifecycle.g0 satellites;

    private GnssInfoProvider() {
        androidx.lifecycle.g0 g0Var = new androidx.lifecycle.g0();
        this.altitude = g0Var;
        androidx.lifecycle.g0 g0Var2 = new androidx.lifecycle.g0();
        this.accuracy = g0Var2;
        androidx.lifecycle.g0 g0Var3 = new androidx.lifecycle.g0();
        this.satellites = g0Var3;
        g0Var.i("--");
        g0Var2.i("--");
        g0Var3.i("--");
    }

    public static GnssInfoProvider getInstance() {
        if (sInstance == null) {
            synchronized (GnssInfoProvider.class) {
                try {
                    if (sInstance == null) {
                        sInstance = new GnssInfoProvider();
                    }
                } finally {
                }
            }
        }
        return sInstance;
    }

    private void postUnavailable() {
        this.altitude.h("--");
        this.accuracy.h("--");
        this.satellites.h("--");
    }

    public androidx.lifecycle.g0 getAccuracy() {
        return this.accuracy;
    }

    public androidx.lifecycle.g0 getAltitude() {
        return this.altitude;
    }

    public androidx.lifecycle.g0 getSatellites() {
        return this.satellites;
    }

    public synchronized void start(Context context) {
        if (this.running) {
            return;
        }
        Context applicationContext = context.getApplicationContext();
        if (g0.g.a(applicationContext, "android.permission.ACCESS_FINE_LOCATION") != 0 && g0.g.a(applicationContext, "android.permission.ACCESS_COARSE_LOCATION") != 0) {
            postUnavailable();
            return;
        }
        LocationManager locationManager = (LocationManager) applicationContext.getSystemService("location");
        this.locationManager = locationManager;
        if (locationManager != null && locationManager.isProviderEnabled("gps")) {
            postUnavailable();
            Handler handler = new Handler(Looper.getMainLooper());
            LocationListener locationListener = new LocationListener() { // from class: com.navi.tool.ui.extra.GnssInfoProvider.1
                @Override // android.location.LocationListener
                public void onLocationChanged(Location location) {
                    if (location == null || !location.hasAltitude()) {
                        GnssInfoProvider.this.altitude.h("--");
                    } else {
                        GnssInfoProvider.this.altitude.h(String.format(Locale.US, "%.1f m", Double.valueOf(location.getAltitude())));
                    }
                    if (location == null || !location.hasAccuracy()) {
                        GnssInfoProvider.this.accuracy.h("--");
                    } else {
                        GnssInfoProvider.this.accuracy.h(String.format(Locale.US, "%.1f m", Float.valueOf(location.getAccuracy())));
                    }
                }
            };
            this.locationListener = locationListener;
            try {
                this.locationManager.requestLocationUpdates("gps", 1000L, CropImageView.DEFAULT_ASPECT_RATIO, locationListener, Looper.getMainLooper());
                GnssStatus.Callback callback = new GnssStatus.Callback() { // from class: com.navi.tool.ui.extra.GnssInfoProvider.2
                    @Override // android.location.GnssStatus.Callback
                    public void onSatelliteStatusChanged(GnssStatus gnssStatus) {
                        int satelliteCount = gnssStatus.getSatelliteCount();
                        int i7 = 0;
                        for (int i8 = 0; i8 < satelliteCount; i8++) {
                            if (gnssStatus.usedInFix(i8)) {
                                i7++;
                            }
                        }
                        GnssInfoProvider.this.satellites.h(satelliteCount > 0 ? i7 + "/" + satelliteCount : "--");
                    }
                };
                this.gnssCallback = callback;
                try {
                    this.locationManager.registerGnssStatusCallback(callback, handler);
                } catch (Exception unused) {
                    this.gnssCallback = null;
                }
                this.running = true;
                return;
            } catch (Exception unused2) {
                this.locationListener = null;
                this.locationManager = null;
                return;
            }
        }
        postUnavailable();
    }

    public synchronized void stop(Context context) {
        LocationManager locationManager;
        LocationListener locationListener;
        if (this.running) {
            this.running = false;
            try {
                LocationManager locationManager2 = this.locationManager;
                if (locationManager2 != null && (locationListener = this.locationListener) != null) {
                    locationManager2.removeUpdates(locationListener);
                }
            } catch (Exception unused) {
            }
            GnssStatus.Callback callback = this.gnssCallback;
            if (callback != null && (locationManager = this.locationManager) != null) {
                try {
                    locationManager.unregisterGnssStatusCallback(callback);
                } catch (Exception unused2) {
                }
            }
            this.gnssCallback = null;
            this.locationListener = null;
            this.locationManager = null;
        }
    }
}
