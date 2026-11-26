package com.navi.tool.service;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothProfile;
import android.bluetooth.BluetoothSocket;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.os.Build;
import com.navi.tool.utils.SharedPreferencesUtils;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public class MobileHudBluetoothServer {
    private static final String LAST_DEVICE_KEY = "mobile_hud_bt_last_connected_device";
    private static final long RETRY_DELAY_MS = 3000;
    private static final UUID SERVICE_UUID = UUID.fromString("9d3a5c7f-4b7c-4f43-a37a-4d9d6e874001");
    private static final String TAG = "MobileHudBtCar";
    private String activeDeviceAddress;
    private ConnectionListener connectionListener;
    private final Context context;
    private DataInputStream input;
    private MessageListener messageListener;
    private DataOutputStream output;
    private final SharedPreferences prefs;
    private BluetoothSocket socket;
    private volatile String targetDeviceAddress;
    private final ExecutorService executor = Executors.newSingleThreadExecutor();
    private final ExecutorService readExecutor = Executors.newSingleThreadExecutor();
    private final Object socketLock = new Object();
    private final BroadcastReceiver bluetoothReceiver = new BroadcastReceiver() { // from class: com.navi.tool.service.MobileHudBluetoothServer.1
        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            MobileHudBluetoothServer.this.handleBluetoothIntent(intent);
        }
    };
    private volatile boolean running = false;
    private volatile boolean receiverRegistered = false;

    public interface ConnectionListener {
        void onClientConnected();

        void onClientDisconnected();
    }

    public interface MessageListener {
        void onMessageReceived(String str);
    }

    public MobileHudBluetoothServer(Context context) {
        Context applicationContext = context.getApplicationContext();
        this.context = applicationContext;
        this.prefs = SharedPreferencesUtils.getAppPrefs(applicationContext);
    }

    private void bootstrapConnectedDevice() {
        BluetoothAdapter defaultAdapter = BluetoothAdapter.getDefaultAdapter();
        if (defaultAdapter == null || !defaultAdapter.isEnabled()) {
            return;
        }
        String string = this.prefs.getString(LAST_DEVICE_KEY, null);
        int i7 = 1;
        if (string != null && !string.trim().isEmpty()) {
            this.targetDeviceAddress = string;
            this.executor.execute(new c0(this, string, i7));
        }
        requestConnectedProfileDevice(defaultAdapter, 1, string);
        requestConnectedProfileDevice(defaultAdapter, 2, string);
        BluetoothDevice findConnectedBondedDevice = findConnectedBondedDevice(defaultAdapter, string);
        if (findConnectedBondedDevice != null) {
            safeAddress(findConnectedBondedDevice);
            onSystemDeviceConnected(findConnectedBondedDevice);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public BluetoothDevice chooseConnectedDevice(List<BluetoothDevice> list, String str) {
        if (list == null || list.isEmpty()) {
            return null;
        }
        if (str != null) {
            for (BluetoothDevice bluetoothDevice : list) {
                if (str.equals(safeAddress(bluetoothDevice))) {
                    return bluetoothDevice;
                }
            }
        }
        return list.get(0);
    }

    private void clearSocket(String str, boolean z6) {
        synchronized (this.socketLock) {
            if (str != null) {
                try {
                    String str2 = this.activeDeviceAddress;
                    if (str2 != null && !str.equals(str2)) {
                        return;
                    }
                } catch (Throwable th) {
                    throw th;
                }
            }
            clearSocketLocked(z6);
        }
    }

    private void clearSocketLocked(boolean z6) {
        closeQuietly(this.input);
        this.input = null;
        closeQuietly(this.output);
        this.output = null;
        closeQuietly(this.socket);
        this.socket = null;
        this.activeDeviceAddress = null;
        if (z6) {
            notifyClientDisconnected();
        }
    }

    private void closeQuietly(BluetoothSocket bluetoothSocket) {
        if (bluetoothSocket == null) {
            return;
        }
        try {
            bluetoothSocket.close();
        } catch (IOException unused) {
        }
    }

    private void closeQuietly(DataInputStream dataInputStream) {
        if (dataInputStream == null) {
            return;
        }
        try {
            dataInputStream.close();
        } catch (IOException unused) {
        }
    }

    private void closeQuietly(DataOutputStream dataOutputStream) {
        if (dataOutputStream == null) {
            return;
        }
        try {
            dataOutputStream.close();
        } catch (IOException unused) {
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: connectLoop, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
    public void lambda$sendText$0(String str) {
        BluetoothDevice resolveDevice;
        DataInputStream dataInputStream;
        DataOutputStream dataOutputStream;
        Throwable e7;
        BluetoothSocket bluetoothSocket;
        while (this.running && str.equals(this.targetDeviceAddress) && !isConnectedTo(str) && (resolveDevice = resolveDevice(str)) != null) {
            BluetoothAdapter defaultAdapter = BluetoothAdapter.getDefaultAdapter();
            if (defaultAdapter != null) {
                try {
                    defaultAdapter.cancelDiscovery();
                } catch (SecurityException unused) {
                }
            }
            try {
                bluetoothSocket = connectSocket(resolveDevice);
                try {
                    dataInputStream = new DataInputStream(new BufferedInputStream(bluetoothSocket.getInputStream()));
                    try {
                        dataOutputStream = new DataOutputStream(new BufferedOutputStream(bluetoothSocket.getOutputStream()));
                        try {
                            synchronized (this.socketLock) {
                                if (this.running && str.equals(this.targetDeviceAddress)) {
                                    clearSocketLocked(true);
                                    this.socket = bluetoothSocket;
                                    this.input = dataInputStream;
                                    this.output = dataOutputStream;
                                    this.activeDeviceAddress = str;
                                    this.readExecutor.execute(new o4.w(this, bluetoothSocket, dataInputStream, str, 3));
                                    notifyClientConnected();
                                    return;
                                }
                                closeQuietly(dataInputStream);
                                closeQuietly(dataOutputStream);
                                closeQuietly(bluetoothSocket);
                                return;
                            }
                        } catch (IOException e8) {
                            e7 = e8;
                            e7.getMessage();
                            closeQuietly(dataInputStream);
                            closeQuietly(dataOutputStream);
                            closeQuietly(bluetoothSocket);
                            sleepQuietly(3000L);
                        } catch (SecurityException e9) {
                            e7 = e9;
                            e7.getMessage();
                            closeQuietly(dataInputStream);
                            closeQuietly(dataOutputStream);
                            closeQuietly(bluetoothSocket);
                            sleepQuietly(3000L);
                        }
                    } catch (IOException | SecurityException e10) {
                        e = e10;
                        dataOutputStream = null;
                        e7 = e;
                        e7.getMessage();
                        closeQuietly(dataInputStream);
                        closeQuietly(dataOutputStream);
                        closeQuietly(bluetoothSocket);
                        sleepQuietly(3000L);
                    }
                } catch (IOException | SecurityException e11) {
                    e = e11;
                    dataInputStream = null;
                    dataOutputStream = null;
                }
            } catch (IOException | SecurityException e12) {
                dataInputStream = null;
                dataOutputStream = null;
                e7 = e12;
                bluetoothSocket = null;
            }
        }
    }

    private BluetoothSocket connectSocket(BluetoothDevice bluetoothDevice) {
        BluetoothSocket bluetoothSocket;
        BluetoothSocket bluetoothSocket2 = null;
        try {
            safeAddress(bluetoothDevice);
            bluetoothSocket = bluetoothDevice.createInsecureRfcommSocketToServiceRecord(SERVICE_UUID);
        } catch (IOException e7) {
            e = e7;
            bluetoothSocket = null;
        }
        try {
            bluetoothSocket.connect();
            safeAddress(bluetoothDevice);
            return bluetoothSocket;
        } catch (IOException e8) {
            e = e8;
            closeQuietly(bluetoothSocket);
            safeAddress(bluetoothDevice);
            e.getMessage();
            try {
                safeAddress(bluetoothDevice);
                bluetoothSocket2 = bluetoothDevice.createRfcommSocketToServiceRecord(SERVICE_UUID);
                bluetoothSocket2.connect();
                safeAddress(bluetoothDevice);
                return bluetoothSocket2;
            } catch (IOException e9) {
                closeQuietly(bluetoothSocket2);
                e9.addSuppressed(e);
                throw e9;
            }
        }
    }

    private BluetoothDevice findConnectedBondedDevice(BluetoothAdapter bluetoothAdapter, String str) {
        BluetoothDevice bluetoothDevice = null;
        try {
            Set<BluetoothDevice> bondedDevices = bluetoothAdapter.getBondedDevices();
            if (bondedDevices != null && !bondedDevices.isEmpty()) {
                for (BluetoothDevice bluetoothDevice2 : bondedDevices) {
                    String safeAddress = safeAddress(bluetoothDevice2);
                    if (isDeviceConnectedReflectively(bluetoothDevice2)) {
                        if (str != null && str.equals(safeAddress)) {
                            return bluetoothDevice2;
                        }
                        if (bluetoothDevice == null) {
                            bluetoothDevice = bluetoothDevice2;
                        }
                    }
                }
            }
        } catch (SecurityException unused) {
        }
        return bluetoothDevice;
    }

    private BluetoothDevice getBluetoothDevice(Intent intent) {
        Object parcelableExtra;
        if (Build.VERSION.SDK_INT < 33) {
            return (BluetoothDevice) intent.getParcelableExtra("android.bluetooth.device.extra.DEVICE");
        }
        parcelableExtra = intent.getParcelableExtra("android.bluetooth.device.extra.DEVICE", BluetoothDevice.class);
        return (BluetoothDevice) parcelableExtra;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void handleBluetoothIntent(Intent intent) {
        if (intent == null || !this.running) {
            return;
        }
        String action = intent.getAction();
        if ("android.bluetooth.adapter.action.STATE_CHANGED".equals(action)) {
            int intExtra = intent.getIntExtra("android.bluetooth.adapter.extra.STATE", Integer.MIN_VALUE);
            if (intExtra == 10 || intExtra == 13) {
                this.targetDeviceAddress = null;
                clearSocket(null, true);
                return;
            } else {
                if (intExtra == 12) {
                    bootstrapConnectedDevice();
                    return;
                }
                return;
            }
        }
        BluetoothDevice bluetoothDevice = getBluetoothDevice(intent);
        if ("android.bluetooth.adapter.action.CONNECTION_STATE_CHANGED".equals(action)) {
            int intExtra2 = intent.getIntExtra("android.bluetooth.adapter.extra.CONNECTION_STATE", Integer.MIN_VALUE);
            safeAddress(bluetoothDevice);
            if (intExtra2 == 2) {
                onSystemDeviceConnected(bluetoothDevice);
                return;
            } else {
                if (intExtra2 == 0) {
                    onSystemDeviceDisconnected(bluetoothDevice);
                    return;
                }
                return;
            }
        }
        if ("android.bluetooth.device.action.ACL_CONNECTED".equals(action)) {
            safeAddress(bluetoothDevice);
            onSystemDeviceConnected(bluetoothDevice);
        } else if ("android.bluetooth.device.action.ACL_DISCONNECTED".equals(action)) {
            safeAddress(bluetoothDevice);
            onSystemDeviceDisconnected(bluetoothDevice);
        }
    }

    private boolean isConnectedTo(String str) {
        boolean z6;
        BluetoothSocket bluetoothSocket;
        synchronized (this.socketLock) {
            try {
                z6 = this.output != null && (bluetoothSocket = this.socket) != null && bluetoothSocket.isConnected() && str.equals(this.activeDeviceAddress);
            } finally {
            }
        }
        return z6;
    }

    private boolean isDeviceConnectedReflectively(BluetoothDevice bluetoothDevice) {
        if (bluetoothDevice == null) {
            return false;
        }
        try {
            Object invoke = bluetoothDevice.getClass().getMethod("isConnected", new Class[0]).invoke(bluetoothDevice, new Object[0]);
            if (invoke instanceof Boolean) {
                return ((Boolean) invoke).booleanValue();
            }
            return false;
        } catch (Exception e7) {
            safeAddress(bluetoothDevice);
            e7.getMessage();
            return false;
        }
    }

    private void notifyClientConnected() {
        ConnectionListener connectionListener = this.connectionListener;
        if (connectionListener != null) {
            connectionListener.onClientConnected();
        }
    }

    private void notifyClientDisconnected() {
        ConnectionListener connectionListener = this.connectionListener;
        if (connectionListener != null) {
            connectionListener.onClientDisconnected();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void onSystemDeviceConnected(BluetoothDevice bluetoothDevice) {
        String safeAddress = safeAddress(bluetoothDevice);
        if (safeAddress == null || safeAddress.isEmpty()) {
            return;
        }
        this.targetDeviceAddress = safeAddress;
        this.prefs.edit().putString(LAST_DEVICE_KEY, safeAddress).apply();
        this.executor.execute(new c0(this, safeAddress, 0));
    }

    private void onSystemDeviceDisconnected(BluetoothDevice bluetoothDevice) {
        String safeAddress = safeAddress(bluetoothDevice);
        if (safeAddress == null || safeAddress.isEmpty()) {
            return;
        }
        if (safeAddress.equals(this.targetDeviceAddress)) {
            this.targetDeviceAddress = null;
        }
        clearSocket(safeAddress, true);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public String profileName(int i7) {
        return i7 == 1 ? "HEADSET" : i7 == 2 ? "A2DP" : String.valueOf(i7);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: readLoop, reason: merged with bridge method [inline-methods] */
    public void lambda$connectLoop$2(BluetoothSocket bluetoothSocket, DataInputStream dataInputStream, String str) {
        boolean z6;
        ExecutorService executorService;
        c0 c0Var;
        while (true) {
            z6 = false;
            try {
                try {
                    if (!this.running) {
                        break;
                    }
                    String readUTF = dataInputStream.readUTF();
                    MessageListener messageListener = this.messageListener;
                    if (messageListener != null) {
                        messageListener.onMessageReceived(readUTF);
                    }
                } catch (IOException e7) {
                    e7.getMessage();
                    synchronized (this.socketLock) {
                        try {
                            if (this.socket == bluetoothSocket) {
                                if (this.running && str.equals(this.targetDeviceAddress)) {
                                    z6 = true;
                                }
                                clearSocketLocked(true);
                            }
                            if (!z6) {
                                return;
                            }
                            executorService = this.executor;
                            c0Var = new c0(this, str, 3);
                        } finally {
                        }
                    }
                }
            } catch (Throwable th) {
                synchronized (this.socketLock) {
                    try {
                        if (this.socket == bluetoothSocket) {
                            if (this.running && str.equals(this.targetDeviceAddress)) {
                                z6 = true;
                            }
                            clearSocketLocked(true);
                        }
                        if (z6) {
                            this.executor.execute(new c0(this, str, 4));
                        }
                        throw th;
                    } finally {
                    }
                }
            }
        }
        synchronized (this.socketLock) {
            try {
                if (this.socket == bluetoothSocket) {
                    if (this.running && str.equals(this.targetDeviceAddress)) {
                        z6 = true;
                    }
                    clearSocketLocked(true);
                }
            } finally {
            }
        }
        if (z6) {
            executorService = this.executor;
            c0Var = new c0(this, str, 2);
            executorService.execute(c0Var);
        }
    }

    private void registerReceiver() {
        if (this.receiverRegistered) {
            return;
        }
        IntentFilter intentFilter = new IntentFilter("android.bluetooth.adapter.action.CONNECTION_STATE_CHANGED");
        intentFilter.addAction("android.bluetooth.adapter.action.STATE_CHANGED");
        intentFilter.addAction("android.bluetooth.device.action.ACL_CONNECTED");
        intentFilter.addAction("android.bluetooth.device.action.ACL_DISCONNECTED");
        this.context.registerReceiver(this.bluetoothReceiver, intentFilter);
        this.receiverRegistered = true;
    }

    private void requestConnectedProfileDevice(final BluetoothAdapter bluetoothAdapter, int i7, final String str) {
        try {
            bluetoothAdapter.getProfileProxy(this.context, new BluetoothProfile.ServiceListener() { // from class: com.navi.tool.service.MobileHudBluetoothServer.2
                @Override // android.bluetooth.BluetoothProfile.ServiceListener
                public void onServiceConnected(int i8, BluetoothProfile bluetoothProfile) {
                    try {
                        BluetoothDevice chooseConnectedDevice = MobileHudBluetoothServer.this.chooseConnectedDevice(bluetoothProfile.getConnectedDevices(), str);
                        MobileHudBluetoothServer.this.profileName(i8);
                        bluetoothProfile.getConnectedDevices().size();
                        MobileHudBluetoothServer.this.safeAddress(chooseConnectedDevice);
                        if (chooseConnectedDevice != null) {
                            MobileHudBluetoothServer.this.onSystemDeviceConnected(chooseConnectedDevice);
                        }
                    } catch (SecurityException unused) {
                    } catch (Throwable th) {
                        bluetoothAdapter.closeProfileProxy(i8, bluetoothProfile);
                        throw th;
                    }
                    bluetoothAdapter.closeProfileProxy(i8, bluetoothProfile);
                }

                @Override // android.bluetooth.BluetoothProfile.ServiceListener
                public void onServiceDisconnected(int i8) {
                }
            }, i7);
        } catch (SecurityException unused) {
        }
    }

    private BluetoothDevice resolveDevice(String str) {
        BluetoothAdapter defaultAdapter;
        if (str == null || str.trim().isEmpty() || (defaultAdapter = BluetoothAdapter.getDefaultAdapter()) == null) {
            return null;
        }
        try {
            return defaultAdapter.getRemoteDevice(str);
        } catch (IllegalArgumentException unused) {
            return null;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public String safeAddress(BluetoothDevice bluetoothDevice) {
        if (bluetoothDevice == null) {
            return null;
        }
        try {
            return bluetoothDevice.getAddress();
        } catch (SecurityException unused) {
            return null;
        }
    }

    private void sleepQuietly(long j7) {
        try {
            Thread.sleep(j7);
        } catch (InterruptedException unused) {
            Thread.currentThread().interrupt();
        }
    }

    private void unregisterReceiver() {
        if (this.receiverRegistered) {
            try {
                this.context.unregisterReceiver(this.bluetoothReceiver);
            } catch (IllegalArgumentException unused) {
            }
            this.receiverRegistered = false;
        }
    }

    public boolean sendText(String str) {
        BluetoothSocket bluetoothSocket;
        synchronized (this.socketLock) {
            try {
                if (this.output == null || (bluetoothSocket = this.socket) == null || !bluetoothSocket.isConnected()) {
                    return false;
                }
                try {
                    this.output.writeUTF(str);
                    this.output.flush();
                    return true;
                } catch (IOException unused) {
                    String str2 = this.activeDeviceAddress;
                    clearSocketLocked(true);
                    if (this.running && str2 != null && str2.equals(this.targetDeviceAddress)) {
                        this.executor.execute(new c0(this, str2, 5));
                    }
                    return false;
                }
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    public void setConnectionListener(ConnectionListener connectionListener) {
        this.connectionListener = connectionListener;
    }

    public void setMessageListener(MessageListener messageListener) {
        this.messageListener = messageListener;
    }

    public void start() {
        if (this.running) {
            return;
        }
        this.running = true;
        registerReceiver();
        bootstrapConnectedDevice();
    }

    public void stop() {
        this.running = false;
        this.targetDeviceAddress = null;
        unregisterReceiver();
        clearSocket(null, false);
    }
}
