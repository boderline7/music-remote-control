package com.navi.tool.protocol;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import com.navi.tool.MainActivity;
import com.navi.tool.receiver.Auto3DBroadcastReceiver;
import com.navi.tool.utils.AmapBroadcastConstants;
import com.navi.tool.utils.AppDetectionUtils;
import com.navi.tool.utils.SharedPreferencesUtils;
import h4.h;
import h4.i;
import i6.e;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONObject;
import s5.h0;
import t1.f;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public class AmapBroadcastReceiver {
    private static final long CRUISE_RESET_DELAY_MS = 5000;
    private static final HashMap<Integer, Integer> DIRECTION_PRIORITY;
    private static final int TTS_COUNT_DOWN = 5;
    private HashMap<Integer, DirectionInfo> broadcastedDirections;
    private volatile int currentLimitedSpeed;
    private volatile int currentSpeed;
    private volatile boolean hasAnnouncedAtZeroSpeed;
    private volatile boolean hasAnnouncedTrafficLight;
    private String lastRoadName;
    private volatile int lastSpeed;
    private String lastZoneSpeedEndDistanceText;
    private int lastZoneSpeedLimitedSpeed;
    private long lastZoneSpeedVoiceAt;
    private MyBroadcastReceiver mBroadcastReceiver;
    private Context mContext;
    private Handler mCruiseResetHandler;
    private volatile int trafficLightNum;
    private boolean zoneSpeedVoiceActive;
    private int zoneSpeedVoiceCount;

    public static class DirectionInfo {
        public final int countdown;
        public final int currentState;
        public final String directionName;
        public final int directionValue;

        public DirectionInfo(int i7, String str, int i8, int i9) {
            this.directionValue = i7;
            this.directionName = str;
            this.countdown = i8;
            this.currentState = i9;
        }
    }

    public static class Helper {
        public static final AmapBroadcastReceiver Inst = new AmapBroadcastReceiver();

        private Helper() {
        }
    }

    public class MyBroadcastReceiver extends BroadcastReceiver {
        private MyBroadcastReceiver() {
        }

        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            String stringExtra;
            try {
                if ("NAVITOOL_CRUISE_TRAFFIC_LIGHTS".equals(intent.getAction())) {
                    intent.getStringExtra("data");
                    String stringExtra2 = intent.getStringExtra("data");
                    AmapBroadcastReceiver.this.dispatchCruiseTrafficLightEvent(stringExtra2);
                    if (!SharedPreferencesUtils.isCruiseTrafficLightVoiceEnabled(context) || stringExtra2 == null) {
                        return;
                    }
                    AmapBroadcastReceiver.this.processCruiseTrafficLight(stringExtra2);
                    return;
                }
                if (intent.getAction().equals(AmapBroadcastConstants.getAmapBroadcast())) {
                    if (intent.getIntExtra(Auto3DBroadcastReceiver.KEY_TYPE, -1) == 60073) {
                        AmapBroadcastReceiver.this.dispatchNaviTrafficLightEvent(intent);
                        if (SharedPreferencesUtils.isTtsEnabled(context)) {
                            int intExtra = intent.getIntExtra("trafficLightStatus", -1);
                            int intExtra2 = intent.getIntExtra("dir", -1);
                            int intExtra3 = intent.getIntExtra("redLightCountDownSeconds", 0);
                            if (AmapBroadcastReceiver.this.trafficLightNum == 0) {
                                AmapBroadcastReceiver.this.hasAnnouncedTrafficLight = false;
                                if (AmapBroadcastReceiver.this.currentSpeed > 0) {
                                    AmapBroadcastReceiver.this.hasAnnouncedAtZeroSpeed = false;
                                    return;
                                }
                                return;
                            }
                            if (intExtra3 == 5) {
                                if (AmapBroadcastReceiver.this.currentSpeed == 0 && !AmapBroadcastReceiver.this.hasAnnouncedAtZeroSpeed) {
                                    AmapBroadcastReceiver.this.hasAnnouncedAtZeroSpeed = true;
                                    AmapBroadcastReceiver.this.hasAnnouncedTrafficLight = true;
                                    AmapBroadcastReceiver.this.playTrafficLightTts(context, intExtra, intExtra2);
                                    return;
                                } else {
                                    if (AmapBroadcastReceiver.this.hasAnnouncedTrafficLight || AmapBroadcastReceiver.this.currentSpeed <= 0 || AmapBroadcastReceiver.this.currentSpeed > SharedPreferencesUtils.getTrafficLightSpeedLimit(context)) {
                                        return;
                                    }
                                    AmapBroadcastReceiver.this.hasAnnouncedTrafficLight = true;
                                    AmapBroadcastReceiver.this.playTrafficLightTts(context, intExtra, intExtra2);
                                    return;
                                }
                            }
                            return;
                        }
                        return;
                    }
                    int intExtra4 = intent.getIntExtra(Auto3DBroadcastReceiver.KEY_TYPE, -1);
                    if (intExtra4 == 12110) {
                        AmapBroadcastReceiver.this.handleZoneSpeedVoice(context, intent);
                        return;
                    }
                    if (intExtra4 != 10001) {
                        if (intExtra4 == 10019 && intent.getIntExtra(Auto3DBroadcastReceiver.EXTRA_STATE, -1) == 9) {
                            AmapBroadcastReceiver.this.resetZoneSpeedVoiceState();
                            AmapBroadcastReceiver.this.currentLimitedSpeed = 0;
                            return;
                        }
                        return;
                    }
                    int intExtra5 = intent.getIntExtra("CUR_SPEED", 0);
                    if (intExtra5 < 0) {
                        intExtra5 = 0;
                    }
                    AmapBroadcastReceiver.this.currentSpeed = intExtra5;
                    AmapBroadcastReceiver.this.trafficLightNum = intent.getIntExtra("TRAFFIC_LIGHT_NUM", 0);
                    int readSpeedExtra = AmapBroadcastReceiver.this.readSpeedExtra(intent, "LIMITED_SPEED");
                    if (readSpeedExtra > 0) {
                        AmapBroadcastReceiver.this.currentLimitedSpeed = readSpeedExtra;
                    }
                    if (AmapBroadcastReceiver.this.lastSpeed != -1) {
                        if (AmapBroadcastReceiver.this.lastSpeed == 0 && intExtra5 > 0) {
                            AmapBroadcastReceiver.this.hasAnnouncedAtZeroSpeed = false;
                            AmapBroadcastReceiver.this.hasAnnouncedTrafficLight = false;
                        } else if (AmapBroadcastReceiver.this.lastSpeed > 0 && intExtra5 == 0) {
                            AmapBroadcastReceiver.this.hasAnnouncedTrafficLight = false;
                        } else if (AmapBroadcastReceiver.this.lastSpeed > 0 && intExtra5 > 0 && AmapBroadcastReceiver.this.lastSpeed != intExtra5) {
                            AmapBroadcastReceiver.this.hasAnnouncedTrafficLight = false;
                        }
                    }
                    AmapBroadcastReceiver.this.lastSpeed = intExtra5;
                    if (!SharedPreferencesUtils.isTtsRoadNameEnabled(context) || (stringExtra = intent.getStringExtra("CUR_ROAD_NAME")) == null || stringExtra.isEmpty() || stringExtra.equals("当前道路") || stringExtra.equals("无名道路") || stringExtra.equals(AmapBroadcastReceiver.this.lastRoadName)) {
                        return;
                    }
                    AmapBroadcastReceiver.this.lastRoadName = stringExtra;
                    String concat = "当前在".concat(stringExtra);
                    AmapBroadcastReceiver.this.initializeAutoProtocolManagerIfNeeded(context);
                    List<String> enabledAutoNaviPackages = AmapBroadcastReceiver.this.getEnabledAutoNaviPackages(context);
                    if (enabledAutoNaviPackages.isEmpty()) {
                        return;
                    }
                    AutoProtocolManager.getInstance().initializeMultiple(context, enabledAutoNaviPackages);
                    AutoProtocolManager.getInstance().playSoundTextToAll(concat);
                }
            } catch (Exception e7) {
                e7.printStackTrace();
            }
        }
    }

    static {
        HashMap<Integer, Integer> hashMap = new HashMap<>();
        DIRECTION_PRIORITY = hashMap;
        hashMap.put(2, 4);
        hashMap.put(1, 3);
        hashMap.put(3, 2);
        hashMap.put(0, 1);
    }

    private AmapBroadcastReceiver() {
        this.currentSpeed = 0;
        this.currentLimitedSpeed = 0;
        this.lastSpeed = -1;
        this.trafficLightNum = 0;
        this.hasAnnouncedTrafficLight = false;
        this.hasAnnouncedAtZeroSpeed = false;
        this.zoneSpeedVoiceActive = false;
        this.zoneSpeedVoiceCount = 0;
        this.lastZoneSpeedVoiceAt = 0L;
        this.lastZoneSpeedEndDistanceText = null;
        this.lastZoneSpeedLimitedSpeed = -1;
        this.lastRoadName = h0.FRAGMENT_ENCODE_SET;
        this.broadcastedDirections = new HashMap<>();
        this.mCruiseResetHandler = new Handler(Looper.getMainLooper());
        this.mContext = null;
        this.mBroadcastReceiver = null;
    }

    private String buildTtsMessage(int i7, int i8, int i9) {
        if (i9 != 5) {
            return null;
        }
        String str = i7 == 1 ? "即将绿灯" : "即将红灯";
        if (i8 == 1) {
            return "前方左转".concat(str);
        }
        if (i8 == 2) {
            return "前方右转".concat(str);
        }
        if (i8 == 3) {
            return "前方掉头".concat(str);
        }
        if (i8 != 4) {
            return null;
        }
        return "前方直行".concat(str);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void dispatchCruiseTrafficLightEvent(String str) {
        if (str != null) {
            try {
                if (!str.isEmpty()) {
                    JSONArray optJSONArray = new JSONObject(str).optJSONArray("lights");
                    ArrayList arrayList = new ArrayList();
                    if (optJSONArray != null) {
                        for (int i7 = 0; i7 < optJSONArray.length(); i7++) {
                            JSONObject jSONObject = optJSONArray.getJSONObject(i7);
                            int optInt = jSONObject.optInt("direction", -1);
                            int optInt2 = jSONObject.optInt("status", -1);
                            int optInt3 = jSONObject.optInt("countdown", 0);
                            if (optInt >= 0 && optInt <= 3) {
                                int i8 = 1;
                                if (optInt2 != 1) {
                                    i8 = 0;
                                }
                                arrayList.add(new h(optInt, i8, optInt3));
                            }
                        }
                    }
                    e.b().f(new i(arrayList, i.SOURCE_CRUISE));
                    return;
                }
            } catch (Exception unused) {
                return;
            }
        }
        e.b().f(new i(new ArrayList(), i.SOURCE_CRUISE));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void dispatchNaviTrafficLightEvent(Intent intent) {
        int i7;
        try {
            int intExtra = intent.getIntExtra("dir", -1);
            int intExtra2 = intent.getIntExtra("trafficLightStatus", -1);
            int intExtra3 = intent.getIntExtra("redLightCountDownSeconds", 0);
            if (intExtra != 1) {
                i7 = 3;
                if (intExtra != 2) {
                    if (intExtra == 3) {
                        i7 = 0;
                    } else if (intExtra != 4) {
                        return;
                    } else {
                        i7 = 2;
                    }
                }
            } else {
                i7 = 1;
            }
            int i8 = intExtra2 == 4 ? 1 : 0;
            ArrayList arrayList = new ArrayList();
            arrayList.add(new h(i7, i8, intExtra3));
            e.b().f(new i(arrayList, i.SOURCE_NAVI));
        } catch (Exception unused) {
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public String getDirectionName(int i7) {
        return i7 == 0 ? "掉头" : i7 == 1 ? "左转" : i7 == 2 ? "直行" : "右转";
    }

    /* JADX INFO: Access modifiers changed from: private */
    public List<String> getEnabledAutoNaviPackages(Context context) {
        ArrayList arrayList = new ArrayList();
        for (String str : AppDetectionUtils.getAutoNaviPackages(context)) {
            if (SharedPreferencesUtils.isTtsAppEnabled(context, str)) {
                arrayList.add(str);
            }
        }
        return arrayList;
    }

    public static AmapBroadcastReceiver getInstance() {
        return Helper.Inst;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void handleZoneSpeedVoice(Context context, Intent intent) {
        if (!SharedPreferencesUtils.isZoneSpeedVoiceEnabled(context)) {
            resetZoneSpeedVoiceState();
            return;
        }
        String stringExtra = intent.getStringExtra("END_DISTANCE_TEXT");
        int readSpeedExtra = readSpeedExtra(intent, "AVERAGE_SPEED");
        boolean z6 = (stringExtra == null || stringExtra.trim().isEmpty()) ? false : true;
        if (readSpeedExtra <= 0 || !z6) {
            resetZoneSpeedVoiceState();
            return;
        }
        int i7 = this.currentLimitedSpeed;
        if (i7 <= 0) {
            return;
        }
        int i8 = this.lastZoneSpeedLimitedSpeed;
        if (i8 > 0 && i8 != i7) {
            this.zoneSpeedVoiceActive = false;
            this.zoneSpeedVoiceCount = 0;
            this.lastZoneSpeedVoiceAt = 0L;
        }
        this.lastZoneSpeedEndDistanceText = stringExtra;
        this.lastZoneSpeedLimitedSpeed = i7;
        if (!this.zoneSpeedVoiceActive) {
            this.zoneSpeedVoiceActive = true;
            this.zoneSpeedVoiceCount = 0;
            this.lastZoneSpeedVoiceAt = 0L;
        }
        int round = (int) Math.round(((readSpeedExtra - i7) * 100.0d) / i7);
        if (round < SharedPreferencesUtils.getZoneSpeedVoiceThreshold(context)) {
            return;
        }
        if (this.zoneSpeedVoiceCount >= SharedPreferencesUtils.getZoneSpeedVoiceMaxCount(context)) {
            return;
        }
        long zoneSpeedVoiceIntervalMinutes = SharedPreferencesUtils.getZoneSpeedVoiceIntervalMinutes(context) * f.T_MS_MINUTE;
        long currentTimeMillis = System.currentTimeMillis();
        long j7 = this.lastZoneSpeedVoiceAt;
        if (j7 <= 0 || currentTimeMillis - j7 >= zoneSpeedVoiceIntervalMinutes) {
            initializeAutoProtocolManagerIfNeeded(context);
            List<String> enabledAutoNaviPackages = getEnabledAutoNaviPackages(context);
            if (enabledAutoNaviPackages.isEmpty()) {
                return;
            }
            String e7 = a.a.e("你已超过区间限速的百分只", round, "，请减速");
            AutoProtocolManager.getInstance().initializeMultiple(context, enabledAutoNaviPackages);
            AutoProtocolManager.getInstance().playSoundTextToAll(e7);
            this.zoneSpeedVoiceCount++;
            this.lastZoneSpeedVoiceAt = currentTimeMillis;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void initializeAutoProtocolManagerIfNeeded(Context context) {
        List<String> enabledAutoNaviPackages = getEnabledAutoNaviPackages(context);
        if (enabledAutoNaviPackages.isEmpty()) {
            return;
        }
        AutoProtocolManager.getInstance().initializeMultiple(context, enabledAutoNaviPackages);
        enabledAutoNaviPackages.toString();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ int lambda$processCruiseTrafficLight$0(DirectionInfo directionInfo, DirectionInfo directionInfo2) {
        HashMap<Integer, Integer> hashMap = DIRECTION_PRIORITY;
        return (hashMap.containsKey(Integer.valueOf(directionInfo2.directionValue)) ? hashMap.get(Integer.valueOf(directionInfo2.directionValue)).intValue() : 0) - (hashMap.containsKey(Integer.valueOf(directionInfo.directionValue)) ? hashMap.get(Integer.valueOf(directionInfo.directionValue)).intValue() : 0);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void playTrafficLightTts(Context context, int i7, int i8) {
        String buildTtsMessage = buildTtsMessage(i7, i8, 5);
        if (buildTtsMessage != null) {
            initializeAutoProtocolManagerIfNeeded(context);
            List<String> enabledAutoNaviPackages = getEnabledAutoNaviPackages(context);
            if (enabledAutoNaviPackages.isEmpty()) {
                return;
            }
            AutoProtocolManager.getInstance().initializeMultiple(context, enabledAutoNaviPackages);
            AutoProtocolManager.getInstance().playSoundTextToAll(buildTtsMessage);
            enabledAutoNaviPackages.toString();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void processCruiseTrafficLight(String str) {
        int i7;
        try {
            JSONArray optJSONArray = new JSONObject(str).optJSONArray("lights");
            if (optJSONArray == null) {
                return;
            }
            ArrayList arrayList = new ArrayList();
            for (int i8 = 0; i8 < optJSONArray.length(); i8++) {
                JSONObject jSONObject = optJSONArray.getJSONObject(i8);
                int optInt = jSONObject.optInt("direction");
                arrayList.add(new DirectionInfo(optInt, getDirectionName(optInt), jSONObject.optInt("countdown"), jSONObject.optInt("status")));
            }
            ArrayList arrayList2 = new ArrayList();
            ArrayList arrayList3 = new ArrayList();
            Iterator it = arrayList.iterator();
            while (it.hasNext()) {
                DirectionInfo directionInfo = (DirectionInfo) it.next();
                if (!this.broadcastedDirections.containsKey(Integer.valueOf(directionInfo.directionValue)) && (i7 = directionInfo.countdown) != -1 && i7 >= 4 && i7 <= 6) {
                    if (directionInfo.currentState == 1) {
                        arrayList2.add(directionInfo);
                    } else {
                        arrayList3.add(directionInfo);
                    }
                }
            }
            if (arrayList2.isEmpty() && arrayList3.isEmpty()) {
                return;
            }
            a aVar = new a();
            Collections.sort(arrayList2, aVar);
            Collections.sort(arrayList3, aVar);
            StringBuilder sb = new StringBuilder();
            if (!arrayList2.isEmpty()) {
                sb.append("前方");
                for (int i9 = 0; i9 < arrayList2.size(); i9++) {
                    sb.append(((DirectionInfo) arrayList2.get(i9)).directionName);
                    if (i9 < arrayList2.size() - 1) {
                        sb.append("、");
                    }
                }
                sb.append("即将红灯");
                if (!arrayList3.isEmpty()) {
                    sb.append("、");
                }
            }
            if (!arrayList3.isEmpty()) {
                if (arrayList2.isEmpty()) {
                    sb.append("前方");
                }
                for (int i10 = 0; i10 < arrayList3.size(); i10++) {
                    sb.append(((DirectionInfo) arrayList3.get(i10)).directionName);
                    if (i10 < arrayList3.size() - 1) {
                        sb.append("、");
                    }
                }
                sb.append("即将绿灯");
            }
            String sb2 = sb.toString();
            boolean z6 = this.currentSpeed <= SharedPreferencesUtils.getTrafficLightSpeedLimit(this.mContext);
            if (sb2.isEmpty() || !z6) {
                return;
            }
            initializeAutoProtocolManagerIfNeeded(this.mContext);
            List<String> enabledAutoNaviPackages = getEnabledAutoNaviPackages(this.mContext);
            if (!enabledAutoNaviPackages.isEmpty()) {
                AutoProtocolManager.getInstance().initializeMultiple(this.mContext, enabledAutoNaviPackages);
                AutoProtocolManager.getInstance().playSoundTextToAll(sb2);
            }
            Iterator it2 = arrayList2.iterator();
            while (it2.hasNext()) {
                DirectionInfo directionInfo2 = (DirectionInfo) it2.next();
                this.broadcastedDirections.put(Integer.valueOf(directionInfo2.directionValue), directionInfo2);
                scheduleResetDirection(directionInfo2.directionValue);
            }
            Iterator it3 = arrayList3.iterator();
            while (it3.hasNext()) {
                DirectionInfo directionInfo3 = (DirectionInfo) it3.next();
                this.broadcastedDirections.put(Integer.valueOf(directionInfo3.directionValue), directionInfo3);
                scheduleResetDirection(directionInfo3.directionValue);
            }
        } catch (Exception e7) {
            e7.printStackTrace();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public int readSpeedExtra(Intent intent, String str) {
        String stringExtra = intent.getStringExtra(str);
        if (stringExtra != null && !stringExtra.trim().isEmpty()) {
            try {
                return Integer.parseInt(stringExtra.trim());
            } catch (NumberFormatException unused) {
            }
        }
        int intExtra = intent.getIntExtra(str, -1);
        if (intExtra >= 0) {
            return intExtra;
        }
        return -1;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void resetZoneSpeedVoiceState() {
        this.zoneSpeedVoiceActive = false;
        this.zoneSpeedVoiceCount = 0;
        this.lastZoneSpeedVoiceAt = 0L;
        this.lastZoneSpeedEndDistanceText = null;
        this.lastZoneSpeedLimitedSpeed = -1;
    }

    private void scheduleResetDirection(final int i7) {
        this.mCruiseResetHandler.postDelayed(new Runnable() { // from class: com.navi.tool.protocol.AmapBroadcastReceiver.1
            @Override // java.lang.Runnable
            public void run() {
                AmapBroadcastReceiver.this.broadcastedDirections.remove(Integer.valueOf(i7));
                AmapBroadcastReceiver.this.getDirectionName(i7);
            }
        }, CRUISE_RESET_DELAY_MS);
    }

    public void initBroadcast(MainActivity mainActivity) {
        if (this.mBroadcastReceiver != null) {
            return;
        }
        this.mContext = mainActivity.getApplicationContext();
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(AmapBroadcastConstants.getAmapBroadcast());
        intentFilter.addAction("NAVITOOL_CRUISE_TRAFFIC_LIGHTS");
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
        intentFilter.addAction("NAVITOOL_CRUISE_TRAFFIC_LIGHTS");
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
        if (context == null || (myBroadcastReceiver = this.mBroadcastReceiver) == null) {
            return;
        }
        context.unregisterReceiver(myBroadcastReceiver);
        this.mBroadcastReceiver = null;
    }

    public void unregisterReceiver() {
        unregisterBroadcast();
    }
}
