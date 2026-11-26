package com.navi.tool.protocol;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;
import com.navi.tool.service.PhoneNotificationRelayService;
import com.navi.tool.utils.AppDetectionUtils;
import com.navi.tool.utils.SharedPreferencesUtils;
import f2.c;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import y0.n;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public class AutoProtocolManager {
    private static final AutoProtocolManager instance = new AutoProtocolManager();
    private Context context;
    private Handler handler;
    private HandlerThread handlerThread;
    private IBinder mBinder;
    private Map<String, IBinder> mBinders = new HashMap();
    public final ServiceConnection serviceConnection = new JsonProtocolServiceConnection();
    public Map<String, Boolean> isServiceBound = new HashMap();
    private int receiveCount = 0;
    private int rebindCount = 0;
    private String packageName = "com.autonavi.amapauto";
    private List<String> boundPackages = new ArrayList();
    private Map<String, ServiceConnection> serviceConnections = new HashMap();
    public boolean isServiceBoundLegacy = false;

    public class JsonProtocolServiceConnection implements ServiceConnection {
        private String packageName;

        public JsonProtocolServiceConnection() {
            this.packageName = AutoProtocolManager.this.packageName;
        }

        public JsonProtocolServiceConnection(String str) {
            this.packageName = str;
        }

        @Override // android.content.ServiceConnection
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            AutoProtocolManager.this.mBinders.put(this.packageName, iBinder);
            AutoProtocolManager.this.isServiceBound.put(this.packageName, Boolean.TRUE);
            if (this.packageName.equals(AutoProtocolManager.this.packageName)) {
                AutoProtocolManager.this.mBinder = iBinder;
            }
        }

        @Override // android.content.ServiceConnection
        public void onServiceDisconnected(ComponentName componentName) {
            AutoProtocolManager.this.isServiceBound.put(this.packageName, Boolean.FALSE);
            AutoProtocolManager.this.mBinders.remove(this.packageName);
            AutoProtocolManager.this.serviceConnections.remove(this.packageName);
            if (this.packageName.equals(AutoProtocolManager.this.packageName)) {
                AutoProtocolManager.this.mBinder = null;
            }
        }
    }

    private AutoProtocolManager() {
    }

    public static AutoProtocolManager getInstance() {
        return instance;
    }

    public void bindService(Context context) {
        lambda$bindServiceToPackage$0(context, this.packageName);
    }

    /* renamed from: bindServiceToPackage, reason: merged with bridge method [inline-methods] */
    public void lambda$bindServiceToPackage$0(Context context, String str) {
        if (str == null || str.isEmpty() || !str.startsWith(AppDetectionUtils.AUTONAVI_PREFIX) || !SharedPreferencesUtils.isTtsAppEnabled(context, str)) {
            return;
        }
        if (this.isServiceBound.containsKey(str) && this.isServiceBound.get(str).booleanValue()) {
            return;
        }
        Intent intent = new Intent();
        intent.setAction("com.autonavi.amapauto.aidl.json_protocol_service");
        intent.setComponent(new ComponentName(str, "com.autonavi.amapauto.protocol.service.JsonProtocolService"));
        JsonProtocolServiceConnection jsonProtocolServiceConnection = new JsonProtocolServiceConnection(str);
        this.serviceConnections.put(str, jsonProtocolServiceConnection);
        if (context.bindService(intent, jsonProtocolServiceConnection, 1)) {
            return;
        }
        this.serviceConnections.remove(str);
        if (this.handlerThread == null) {
            HandlerThread handlerThread = new HandlerThread("AutoProtocolManager-RetryThread");
            this.handlerThread = handlerThread;
            handlerThread.start();
            this.handler = new Handler(this.handlerThread.getLooper());
        }
        this.handler.postDelayed(new n(this, context, str, 6), PhoneNotificationRelayService.CONNECTION_MODE_TRANSITION_GRACE_MS);
    }

    public void initialize(Context context) {
        if (context == null) {
            return;
        }
        this.context = context;
        List<String> autoNaviPackages = AppDetectionUtils.getAutoNaviPackages(context);
        if (!autoNaviPackages.isEmpty()) {
            this.packageName = autoNaviPackages.get(0);
        }
        bindService(context);
    }

    public void initializeMultiple(Context context, List<String> list) {
        if (context == null) {
            return;
        }
        this.context = context;
        Iterator<String> it = list.iterator();
        while (it.hasNext()) {
            lambda$bindServiceToPackage$0(context, it.next());
        }
    }

    public boolean isServiceBound() {
        return this.isServiceBound.containsKey(this.packageName) && this.isServiceBound.get(this.packageName).booleanValue();
    }

    public void playSoundText(String str) {
        sendProtocolMessage("80082", String.format("{\"playSoundText\":\"%s\"}", str));
    }

    public void playSoundTextToAll(String str) {
        for (String str2 : this.isServiceBound.keySet()) {
            if (this.isServiceBound.get(str2).booleanValue()) {
                playSoundTextToPackage(str, str2);
            }
        }
    }

    public void playSoundTextToPackage(String str, String str2) {
        sendProtocolMessageToPackage(String.format("{\"protocolId\":\"80082\",\"data\":{\"playSoundText\":\"%s\"}}", str), str2);
    }

    public void sendProtocolMessage(String str) {
        sendProtocolMessageToPackage(str, this.packageName);
    }

    public void sendProtocolMessage(String str, String str2) {
        int i7 = this.receiveCount + 1;
        this.receiveCount = i7;
        sendProtocolMessage(String.format("{\"requestCode\":\"%s\",\"needResponse\":true,\"protocolId\":%s,\"versionName\":\"AutoMapSDK_1.0.10_1234\",\"requestAuthor\":\"ecarx.naviservice\",\"messageType\":\"request\",\"data\":%s}", String.valueOf(i7), str, str2));
    }

    public void sendProtocolMessageToAll(String str) {
        for (String str2 : this.isServiceBound.keySet()) {
            if (this.isServiceBound.get(str2).booleanValue()) {
                sendProtocolMessageToPackage(str, str2);
            }
        }
    }

    public void sendProtocolMessageToPackage(String str, String str2) {
        IBinder iBinder;
        if (this.isServiceBound.containsKey(str2) && this.isServiceBound.get(str2).booleanValue() && (iBinder = this.mBinders.get(str2)) != null) {
            Parcel obtain = Parcel.obtain();
            Parcel obtain2 = Parcel.obtain();
            try {
                try {
                    obtain.writeInterfaceToken(c.DESCRIPTOR);
                    obtain.writeString(this.context.getPackageName());
                    obtain.writeString(str);
                    iBinder.transact(1, obtain, obtain2, 0);
                    obtain2.readException();
                } catch (RemoteException unused) {
                    this.isServiceBound.put(str2, Boolean.FALSE);
                    this.mBinders.remove(str2);
                    this.serviceConnections.remove(str2);
                }
            } finally {
                obtain2.recycle();
                obtain.recycle();
            }
        }
    }

    public void setPackageName(String str) {
        this.packageName = str;
    }

    public void unbindAllServices(Context context) {
        if (context == null) {
            return;
        }
        for (Map.Entry entry : new HashMap(this.serviceConnections).entrySet()) {
            try {
                String str = (String) entry.getKey();
                ServiceConnection serviceConnection = (ServiceConnection) entry.getValue();
                this.isServiceBound.put(str, Boolean.FALSE);
                this.mBinders.remove(str);
                this.serviceConnections.remove(str);
                if (str.equals(this.packageName)) {
                    this.mBinder = null;
                }
                context.unbindService(serviceConnection);
            } catch (Exception unused) {
            }
        }
    }
}
