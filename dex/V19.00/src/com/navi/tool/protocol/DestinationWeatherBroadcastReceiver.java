package com.navi.tool.protocol;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Build;
import com.navi.tool.MainActivity;
import com.navi.tool.receiver.Auto3DBroadcastReceiver;
import com.navi.tool.service.ConfigBackupService;
import com.navi.tool.utils.AmapBroadcastConstants;
import com.navi.tool.utils.AppDetectionUtils;
import com.navi.tool.utils.SharedPreferencesUtils;
import com.navi.tool.utils.ToastHelper;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.zip.GZIPInputStream;
import n1.z;
import org.json.JSONException;
import org.json.JSONObject;
import y0.n;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public class DestinationWeatherBroadcastReceiver {
    private static final String TAG = "DestinationWeather";
    private ExecutorService executorService;
    private MyBroadcastReceiver mBroadcastReceiver;
    private Context mContext;
    private boolean navigationActive;
    private boolean weatherReported;
    private boolean weatherRequested;

    public static class Helper {
        public static final DestinationWeatherBroadcastReceiver Inst = new DestinationWeatherBroadcastReceiver();

        private Helper() {
        }
    }

    public class MyBroadcastReceiver extends BroadcastReceiver {
        private MyBroadcastReceiver() {
        }

        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            try {
                if (intent.getAction().equals(AmapBroadcastConstants.getAmapBroadcast())) {
                    int intExtra = intent.getIntExtra(Auto3DBroadcastReceiver.KEY_TYPE, -1);
                    if (!SharedPreferencesUtils.isDestinationWeatherEnabled(context)) {
                        return;
                    }
                    if (intExtra == 10019) {
                        int intExtra2 = intent.getIntExtra(Auto3DBroadcastReceiver.EXTRA_STATE, -1);
                        if (intExtra2 == 8) {
                            DestinationWeatherBroadcastReceiver.this.startNavigationSession();
                        } else if (intExtra2 == 9) {
                            DestinationWeatherBroadcastReceiver.this.resetNavigationSession("导航结束");
                        }
                    } else if (intExtra == 10001) {
                        if (DestinationWeatherBroadcastReceiver.this.navigationActive && !DestinationWeatherBroadcastReceiver.this.weatherRequested) {
                            String stringExtra = intent.getStringExtra("endPOIName");
                            double doubleExtra = intent.getDoubleExtra("endPOILatitude", 0.0d);
                            double doubleExtra2 = intent.getDoubleExtra("endPOILongitude", 0.0d);
                            int intExtra3 = intent.getIntExtra("ROUTE_ALL_DIS", 0);
                            if (stringExtra != null && !stringExtra.isEmpty() && doubleExtra != 0.0d && doubleExtra2 != 0.0d && intExtra3 > 0) {
                                if (Math.round(intExtra3 / 1000.0d) >= SharedPreferencesUtils.getDestinationWeatherThreshold(context)) {
                                    String format = String.format("%.2f", Double.valueOf(doubleExtra));
                                    String format2 = String.format("%.2f", Double.valueOf(doubleExtra2));
                                    DestinationWeatherBroadcastReceiver.this.weatherRequested = true;
                                    DestinationWeatherBroadcastReceiver.this.requestWeatherApi(format, format2, stringExtra);
                                }
                            }
                        }
                    }
                }
            } catch (Exception e7) {
                e7.printStackTrace();
            }
        }
    }

    private DestinationWeatherBroadcastReceiver() {
        this.navigationActive = false;
        this.weatherRequested = false;
        this.weatherReported = false;
        this.mContext = null;
        this.mBroadcastReceiver = null;
        this.executorService = Executors.newSingleThreadExecutor();
    }

    /*  JADX ERROR: JadxRuntimeException in pass: RegionMakerVisitor
        jadx.core.utils.exceptions.JadxRuntimeException: Can't find top splitter block for handler:B:10:0x003d
        	at jadx.core.utils.BlockUtils.getTopSplitterForHandler(BlockUtils.java:1179)
        	at jadx.core.dex.visitors.regions.maker.ExcHandlersRegionMaker.collectHandlerRegions(ExcHandlersRegionMaker.java:53)
        	at jadx.core.dex.visitors.regions.maker.ExcHandlersRegionMaker.process(ExcHandlersRegionMaker.java:38)
        	at jadx.core.dex.visitors.regions.RegionMakerVisitor.visit(RegionMakerVisitor.java:27)
        */
    private java.lang.String buildWeatherTtsMessage(java.lang.String r6, java.lang.String r7, java.lang.String r8, java.lang.String r9, java.lang.String r10) {
        /*
            r5 = this;
            java.lang.String r0 = "体感温度"
            java.lang.String r1 = "温度"
            java.lang.String r2 = "摄氏度，"
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            java.lang.String r4 = "此次前往"
            r3.<init>(r4)
            r3.append(r6)
            java.lang.String r6 = "，当前天气"
            r3.append(r6)
            r3.append(r7)
            java.lang.String r6 = "，"
            r3.append(r6)
            int r6 = java.lang.Integer.parseInt(r8)     // Catch: java.lang.NumberFormatException -> L3d
            if (r6 >= 0) goto L33
            java.lang.String r7 = "温度零下"
            r3.append(r7)     // Catch: java.lang.NumberFormatException -> L3d
            int r6 = java.lang.Math.abs(r6)     // Catch: java.lang.NumberFormatException -> L3d
            r3.append(r6)     // Catch: java.lang.NumberFormatException -> L3d
            r3.append(r2)     // Catch: java.lang.NumberFormatException -> L3d
            goto L46
        L33:
            r3.append(r1)     // Catch: java.lang.NumberFormatException -> L3d
            r3.append(r6)     // Catch: java.lang.NumberFormatException -> L3d
            r3.append(r2)     // Catch: java.lang.NumberFormatException -> L3d
            goto L46
        L3d:
            r3.append(r1)
            r3.append(r8)
            r3.append(r2)
        L46:
            int r6 = java.lang.Integer.parseInt(r9)     // Catch: java.lang.NumberFormatException -> L66
            if (r6 >= 0) goto L5c
            java.lang.String r7 = "体感温度零下"
            r3.append(r7)     // Catch: java.lang.NumberFormatException -> L66
            int r6 = java.lang.Math.abs(r6)     // Catch: java.lang.NumberFormatException -> L66
            r3.append(r6)     // Catch: java.lang.NumberFormatException -> L66
            r3.append(r2)     // Catch: java.lang.NumberFormatException -> L66
            goto L6f
        L5c:
            r3.append(r0)     // Catch: java.lang.NumberFormatException -> L66
            r3.append(r6)     // Catch: java.lang.NumberFormatException -> L66
            r3.append(r2)     // Catch: java.lang.NumberFormatException -> L66
            goto L6f
        L66:
            r3.append(r0)
            r3.append(r9)
            r3.append(r2)
        L6f:
            java.lang.String r6 = "能见度"
            r3.append(r6)
            r3.append(r10)
            java.lang.String r6 = "公里"
            r3.append(r6)
            java.lang.String r6 = r3.toString()
            return r6
        */
        throw new UnsupportedOperationException("Method not decompiled: com.navi.tool.protocol.DestinationWeatherBroadcastReceiver.buildWeatherTtsMessage(java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String):java.lang.String");
    }

    private List<String> getEnabledAutoNaviPackages(Context context) {
        ArrayList arrayList = new ArrayList();
        for (String str : AppDetectionUtils.getAutoNaviPackages(context)) {
            if (SharedPreferencesUtils.isTtsAppEnabled(context, str)) {
                arrayList.add(str);
            }
        }
        return arrayList;
    }

    public static DestinationWeatherBroadcastReceiver getInstance() {
        return Helper.Inst;
    }

    private void initializeAutoProtocolManagerIfNeeded(Context context) {
        List<String> enabledAutoNaviPackages = getEnabledAutoNaviPackages(context);
        if (enabledAutoNaviPackages.isEmpty()) {
            return;
        }
        AutoProtocolManager.getInstance().initializeMultiple(context, enabledAutoNaviPackages);
        enabledAutoNaviPackages.toString();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$requestWeatherApi$0(String str, String str2) {
        try {
            HttpURLConnection httpURLConnection = (HttpURLConnection) new URL(str).openConnection();
            httpURLConnection.setRequestMethod("GET");
            httpURLConnection.setRequestProperty("Accept-Encoding", "gzip");
            httpURLConnection.setConnectTimeout(10000);
            httpURLConnection.setReadTimeout(10000);
            if (httpURLConnection.getResponseCode() != 200) {
                try {
                    InputStream errorStream = httpURLConnection.getErrorStream();
                    if (errorStream != null) {
                        String contentEncoding = httpURLConnection.getContentEncoding();
                        if (contentEncoding != null && contentEncoding.contains("gzip")) {
                            errorStream = new GZIPInputStream(errorStream);
                        }
                        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(errorStream, "UTF-8"));
                        StringBuilder sb = new StringBuilder();
                        while (true) {
                            String readLine = bufferedReader.readLine();
                            if (readLine == null) {
                                break;
                            } else {
                                sb.append(readLine);
                            }
                        }
                        bufferedReader.close();
                    }
                } catch (Exception unused) {
                }
                sendTtsMessage("当前天气服务不可用");
                return;
            }
            String contentEncoding2 = httpURLConnection.getContentEncoding();
            BufferedReader bufferedReader2 = new BufferedReader(new InputStreamReader((contentEncoding2 == null || !contentEncoding2.contains("gzip")) ? httpURLConnection.getInputStream() : new GZIPInputStream(httpURLConnection.getInputStream()), "UTF-8"));
            StringBuilder sb2 = new StringBuilder();
            while (true) {
                String readLine2 = bufferedReader2.readLine();
                if (readLine2 == null) {
                    bufferedReader2.close();
                    parseWeatherResponse(sb2.toString(), str2);
                    return;
                }
                sb2.append(readLine2);
            }
        } catch (IOException unused2) {
            sendTtsMessage("当前天气服务不可用");
        }
    }

    private void parseWeatherResponse(String str, String str2) {
        if (str != null) {
            try {
                if (!str.isEmpty()) {
                    JSONObject jSONObject = new JSONObject(str);
                    if ("200".equals(jSONObject.getString("code"))) {
                        JSONObject jSONObject2 = jSONObject.getJSONObject("now");
                        sendTtsMessage(buildWeatherTtsMessage(str2, jSONObject2.getString("text"), jSONObject2.getString("temp"), jSONObject2.getString("feelsLike"), jSONObject2.getString("vis")));
                        return;
                    } else {
                        jSONObject.optString(ConfigBackupService.EXTRA_MESSAGE, "未知错误");
                        sendTtsMessage("当前天气服务不可用");
                        return;
                    }
                }
            } catch (JSONException unused) {
                sendTtsMessage("当前天气服务不可用");
                return;
            }
        }
        sendTtsMessage("当前天气服务不可用");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void requestWeatherApi(String str, String str2, String str3) {
        this.executorService.execute(new n(this, z.N0(Double.parseDouble(str), Double.parseDouble(str2)), str3, 7));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void resetNavigationSession(String str) {
        this.navigationActive = false;
        this.weatherRequested = false;
        this.weatherReported = false;
    }

    private void sendTtsMessage(String str) {
        if (this.weatherReported) {
            return;
        }
        initializeAutoProtocolManagerIfNeeded(this.mContext);
        List<String> enabledAutoNaviPackages = getEnabledAutoNaviPackages(this.mContext);
        if (enabledAutoNaviPackages.isEmpty()) {
            return;
        }
        AutoProtocolManager.getInstance().initializeMultiple(this.mContext, enabledAutoNaviPackages);
        try {
            AutoProtocolManager.getInstance().playSoundTextToAll(str);
            enabledAutoNaviPackages.toString();
            this.weatherReported = true;
        } catch (Exception unused) {
            ToastHelper.show(this.mContext, "无法发送TTS消息，请确保高德地图正在运行");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void startNavigationSession() {
        this.navigationActive = true;
        this.weatherRequested = false;
        this.weatherReported = false;
    }

    public void initBroadcast(MainActivity mainActivity) {
        if (this.mBroadcastReceiver != null) {
            return;
        }
        this.mContext = mainActivity.getApplicationContext();
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(AmapBroadcastConstants.getAmapBroadcast());
        MyBroadcastReceiver myBroadcastReceiver = new MyBroadcastReceiver();
        this.mBroadcastReceiver = myBroadcastReceiver;
        if (Build.VERSION.SDK_INT >= 33) {
            this.mContext.registerReceiver(myBroadcastReceiver, intentFilter, 2);
        } else {
            this.mContext.registerReceiver(myBroadcastReceiver, intentFilter);
        }
    }

    public void initBroadcastForService(Context context) {
        if (this.mBroadcastReceiver != null) {
            return;
        }
        this.mContext = context.getApplicationContext();
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(AmapBroadcastConstants.getAmapBroadcast());
        MyBroadcastReceiver myBroadcastReceiver = new MyBroadcastReceiver();
        this.mBroadcastReceiver = myBroadcastReceiver;
        if (Build.VERSION.SDK_INT >= 33) {
            this.mContext.registerReceiver(myBroadcastReceiver, intentFilter, 2);
        } else {
            this.mContext.registerReceiver(myBroadcastReceiver, intentFilter);
        }
    }

    public void unregisterBroadcast() {
        MyBroadcastReceiver myBroadcastReceiver;
        Context context = this.mContext;
        if (context != null && (myBroadcastReceiver = this.mBroadcastReceiver) != null) {
            try {
                context.unregisterReceiver(myBroadcastReceiver);
            } catch (IllegalArgumentException unused) {
            }
            this.mBroadcastReceiver = null;
        }
        this.mContext = null;
    }

    public void unregisterReceiver() {
        unregisterBroadcast();
    }
}
