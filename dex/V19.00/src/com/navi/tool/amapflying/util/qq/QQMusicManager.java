package com.navi.tool.amapflying.util.qq;

import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.Uri;
import android.os.Handler;
import android.os.Looper;
import com.navi.tool.amapflying.receiver.QQMusicLyricReceiver;
import com.navi.tool.service.PhoneNotificationRelayService;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public class QQMusicManager {
    private static final String TAG = "QQMusicManager";
    private static volatile QQMusicManager sInstance;
    private Context mContext;
    private QQMusicLyricReceiver mReceiver;
    private int mReferenceCount = 0;
    private boolean mIsAction100Sent = false;
    private boolean mIsSending = false;
    private int mSendCount = 0;
    private Handler mHandler = new Handler(Looper.getMainLooper());
    private Runnable mSendRunnable = new Runnable() { // from class: com.navi.tool.amapflying.util.qq.QQMusicManager.1
        @Override // java.lang.Runnable
        public void run() {
            if (QQMusicManager.this.mSendCount < 3) {
                QQMusicManager.this.sendAction100Broadcast();
                QQMusicManager.access$008(QQMusicManager.this);
                if (QQMusicManager.this.mSendCount < 3) {
                    QQMusicManager.this.mHandler.postDelayed(this, PhoneNotificationRelayService.CONNECTION_MODE_TRANSITION_GRACE_MS);
                } else {
                    QQMusicManager.this.mIsAction100Sent = true;
                    QQMusicManager.this.mIsSending = false;
                }
            }
        }
    };

    private QQMusicManager(Context context) {
        this.mContext = context.getApplicationContext();
    }

    public static /* synthetic */ int access$008(QQMusicManager qQMusicManager) {
        int i7 = qQMusicManager.mSendCount;
        qQMusicManager.mSendCount = i7 + 1;
        return i7;
    }

    public static QQMusicManager getInstance(Context context) {
        if (sInstance == null) {
            synchronized (QQMusicManager.class) {
                try {
                    if (sInstance == null) {
                        sInstance = new QQMusicManager(context);
                    }
                } finally {
                }
            }
        }
        return sInstance;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void sendAction100Broadcast() {
        try {
            Intent intent = new Intent("com.tencent.qqmusiccar.action");
            intent.setClassName("com.tencent.qqmusiccar", "com.tencent.qqmusiccar.app.reciver.BroadcastReceiverCenterForThird");
            intent.setData(Uri.parse("qqmusiccar://asdasd?action=100"));
            this.mContext.sendBroadcast(intent);
        } catch (Exception unused) {
        }
    }

    private void startSendSequence() {
        this.mIsSending = true;
        this.mSendCount = 0;
        this.mHandler.removeCallbacks(this.mSendRunnable);
        this.mHandler.post(this.mSendRunnable);
    }

    public void queryCurrentState() {
        if (this.mContext != null) {
            Intent intent = new Intent("com.tencent.qqmusiccar.action");
            intent.setClassName("com.tencent.qqmusiccar", "com.tencent.qqmusiccar.app.reciver.BroadcastReceiverCenterForThird");
            intent.setData(Uri.parse("qqmusiccar://asdasd?action=100"));
            this.mContext.sendBroadcast(intent);
        }
    }

    public synchronized void start() {
        try {
            this.mReferenceCount++;
            if (this.mReceiver == null) {
                this.mReceiver = new QQMusicLyricReceiver();
                IntentFilter intentFilter = new IntentFilter();
                intentFilter.addAction("com.tencent.qqmusiccar.action.PLAY_COMMAND_SEND_FOR_THIRD");
                intentFilter.addAction("com.tencent.qqmusiccar.ACTION_PLAYSONG_CHANGEDQQMusicCar");
                this.mContext.registerReceiver(this.mReceiver, intentFilter, 2);
            }
            if (!this.mIsAction100Sent && !this.mIsSending) {
                startSendSequence();
            }
        } catch (Throwable th) {
            throw th;
        }
    }

    public synchronized void stop() {
        int i7 = this.mReferenceCount - 1;
        this.mReferenceCount = i7;
        if (i7 <= 0) {
            this.mReferenceCount = 0;
            QQMusicLyricReceiver qQMusicLyricReceiver = this.mReceiver;
            if (qQMusicLyricReceiver != null) {
                try {
                    this.mContext.unregisterReceiver(qQMusicLyricReceiver);
                } catch (Exception unused) {
                }
                this.mReceiver = null;
                QQMusicLyricReceiver.stop();
            }
        }
        this.mIsAction100Sent = false;
        this.mIsSending = false;
        this.mSendCount = 0;
        Handler handler = this.mHandler;
        if (handler != null) {
            handler.removeCallbacks(this.mSendRunnable);
        }
    }
}
