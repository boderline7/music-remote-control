package com.navi.tool.ui.extra;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import com.google.zxing.WriterException;
import com.navi.tool.model.CloudDiskCredential;
import com.navi.tool.utils.Pan123Api;
import java.util.HashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public class QrCodeLoginDialog extends Dialog {
    private Button btnClose;
    private Button btnRefresh;
    private CloudDiskCredential credential;
    private ExecutorService executor;
    private OnLoginFailedListener failedListener;
    private Handler handler;
    private volatile boolean isDestroyed;
    private volatile boolean isPolling;
    private ImageView ivQrCode;
    private ProgressBar progressLoading;
    private OnLoginSuccessListener successListener;
    private TextView tvStatus;

    public interface OnLoginFailedListener {
        void onFailed(String str);
    }

    public interface OnLoginSuccessListener {
        void onSuccess(CloudDiskCredential cloudDiskCredential);
    }

    public QrCodeLoginDialog(Context context) {
        super(context);
        this.executor = Executors.newSingleThreadExecutor();
        this.handler = new Handler(Looper.getMainLooper());
        this.isPolling = false;
        this.isDestroyed = false;
        this.credential = new CloudDiskCredential(2, "123网盘");
    }

    private Bitmap generateQrCodeBitmap(String str, int i7) {
        try {
            HashMap hashMap = new HashMap();
            hashMap.put(w3.b.CHARACTER_SET, "UTF-8");
            hashMap.put(w3.b.ERROR_CORRECTION, d4.a.M);
            hashMap.put(w3.b.MARGIN, 1);
            y3.b a7 = new p3.f(28, 0).a(str, w3.a.QR_CODE, i7, i7, hashMap);
            int i8 = a7.f10174a;
            int i9 = a7.f10175b;
            int[] iArr = new int[i8 * i9];
            for (int i10 = 0; i10 < i9; i10++) {
                for (int i11 = 0; i11 < i8; i11++) {
                    iArr[(i10 * i8) + i11] = a7.a(i11, i10) ? p0.d1.MEASURED_STATE_MASK : -1;
                }
            }
            Bitmap createBitmap = Bitmap.createBitmap(i8, i9, Bitmap.Config.ARGB_8888);
            createBitmap.setPixels(iArr, 0, i8, 0, 0, i8, i9);
            return createBitmap;
        } catch (WriterException e7) {
            e7.getMessage();
            return null;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$onCreate$0(View view) {
        startLogin();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$onCreate$1(View view) {
        stopPolling();
        dismiss();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$startLogin$2(Bitmap bitmap) {
        if (this.isDestroyed) {
            return;
        }
        if (bitmap == null) {
            this.tvStatus.setText("二维码生成失败，请重试");
            this.progressLoading.setVisibility(8);
            this.btnRefresh.setEnabled(true);
        } else {
            this.ivQrCode.setImageBitmap(bitmap);
            this.tvStatus.setText("请使用123网盘App或微信扫描二维码");
            this.progressLoading.setVisibility(8);
            this.btnRefresh.setEnabled(true);
            startPolling();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$startLogin$3(Exception exc) {
        if (this.isDestroyed) {
            return;
        }
        this.tvStatus.setText("请求失败: " + exc.getMessage());
        this.progressLoading.setVisibility(8);
        this.btnRefresh.setEnabled(true);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$startLogin$4() {
        int i7 = 0;
        try {
            this.handler.post(new p5(this, generateQrCodeBitmap(Pan123Api.generateQrCode(this.credential), 400), i7));
        } catch (Exception e7) {
            this.handler.post(new q5(this, e7, i7));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$startPolling$10(Pan123Api.QrCodeResult qrCodeResult) {
        if (this.isDestroyed) {
            return;
        }
        stopPolling();
        this.tvStatus.setText(qrCodeResult.message);
        this.progressLoading.setVisibility(8);
        OnLoginFailedListener onLoginFailedListener = this.failedListener;
        if (onLoginFailedListener != null) {
            onLoginFailedListener.onFailed(qrCodeResult.message);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$startPolling$11(Exception exc) {
        if (this.isDestroyed) {
            return;
        }
        this.tvStatus.setText("连接出错: " + exc.getMessage());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$startPolling$12() {
        Pan123Api.QrCodeResult pollQrCodeResult;
        String str;
        int i7 = 0;
        int i8 = 0;
        while (this.isPolling && !this.isDestroyed && i8 < 600) {
            i8++;
            int i9 = 2;
            try {
                pollQrCodeResult = Pan123Api.pollQrCodeResult(this.credential);
            } catch (Exception e7) {
                this.handler.post(new q5(this, e7, i9));
                Thread.sleep(2000L);
            }
            if (pollQrCodeResult.code == 200 && (str = pollQrCodeResult.token) != null) {
                this.credential.token = str;
                this.handler.post(new r5(this, i7));
                return;
            }
            int i10 = pollQrCodeResult.loginStatus;
            int i11 = 1;
            if (i10 == 3) {
                this.handler.post(new r5(this, i11));
            } else if (i10 == 4) {
                this.handler.post(new r5(this, i9));
                this.isPolling = false;
                try {
                    this.handler.post(new p5(this, generateQrCodeBitmap(Pan123Api.generateQrCode(this.credential), 400), i11));
                    Thread.sleep(500L);
                    this.isPolling = true;
                    i8 = 0;
                } catch (Exception e8) {
                    this.handler.post(new q5(this, e8, i11));
                }
            }
            String str2 = pollQrCodeResult.message;
            if (str2 != null && !str2.isEmpty()) {
                this.handler.post(new z0(21, this, pollQrCodeResult));
                return;
            } else {
                try {
                    Thread.sleep(500L);
                } catch (InterruptedException unused) {
                    return;
                }
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$startPolling$5() {
        if (this.isDestroyed) {
            return;
        }
        stopPolling();
        this.tvStatus.setText("登录成功！");
        OnLoginSuccessListener onLoginSuccessListener = this.successListener;
        if (onLoginSuccessListener != null) {
            onLoginSuccessListener.onSuccess(this.credential);
        }
        dismiss();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$startPolling$6() {
        if (this.isDestroyed) {
            return;
        }
        this.tvStatus.setText("正在验证...");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$startPolling$7() {
        if (this.isDestroyed) {
            return;
        }
        this.tvStatus.setText("二维码已过期，正在刷新...");
        this.progressLoading.setVisibility(0);
        this.ivQrCode.setImageBitmap(null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$startPolling$8(Bitmap bitmap) {
        if (this.isDestroyed || bitmap == null) {
            return;
        }
        this.ivQrCode.setImageBitmap(bitmap);
        this.tvStatus.setText("请使用123网盘App或微信扫描二维码");
        this.progressLoading.setVisibility(8);
        this.btnRefresh.setEnabled(true);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$startPolling$9(Exception exc) {
        if (this.isDestroyed) {
            return;
        }
        this.tvStatus.setText("刷新失败: " + exc.getMessage());
        this.progressLoading.setVisibility(8);
        this.btnRefresh.setEnabled(true);
    }

    private void startLogin() {
        this.isPolling = false;
        this.tvStatus.setText("正在获取二维码...");
        this.progressLoading.setVisibility(0);
        this.ivQrCode.setImageBitmap(null);
        this.btnRefresh.setEnabled(false);
        this.executor.execute(new r5(this, 3));
    }

    private void startPolling() {
        if (this.isDestroyed) {
            return;
        }
        this.isPolling = true;
        this.executor.execute(new r5(this, 4));
    }

    private void stopPolling() {
        this.isPolling = false;
    }

    @Override // android.app.Dialog, android.content.DialogInterface
    public void dismiss() {
        this.isDestroyed = true;
        stopPolling();
        this.executor.shutdownNow();
        super.dismiss();
    }

    @Override // android.app.Dialog
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        final int i7 = 1;
        requestWindowFeature(1);
        setContentView(g4.q.dialog_login_qrcode);
        final int i8 = 0;
        setCanceledOnTouchOutside(false);
        setCancelable(false);
        this.ivQrCode = (ImageView) findViewById(g4.p.iv_qr_code);
        this.tvStatus = (TextView) findViewById(g4.p.tv_qr_status);
        this.progressLoading = (ProgressBar) findViewById(g4.p.progress_loading);
        this.btnRefresh = (Button) findViewById(g4.p.btn_refresh_qr);
        this.btnClose = (Button) findViewById(g4.p.btn_close);
        this.btnRefresh.setOnClickListener(new View.OnClickListener(this) { // from class: com.navi.tool.ui.extra.s5

            /* renamed from: b, reason: collision with root package name */
            public final /* synthetic */ QrCodeLoginDialog f5456b;

            {
                this.f5456b = this;
            }

            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                int i9 = i8;
                QrCodeLoginDialog qrCodeLoginDialog = this.f5456b;
                switch (i9) {
                    case 0:
                        qrCodeLoginDialog.lambda$onCreate$0(view);
                        break;
                    default:
                        qrCodeLoginDialog.lambda$onCreate$1(view);
                        break;
                }
            }
        });
        this.btnClose.setOnClickListener(new View.OnClickListener(this) { // from class: com.navi.tool.ui.extra.s5

            /* renamed from: b, reason: collision with root package name */
            public final /* synthetic */ QrCodeLoginDialog f5456b;

            {
                this.f5456b = this;
            }

            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                int i9 = i7;
                QrCodeLoginDialog qrCodeLoginDialog = this.f5456b;
                switch (i9) {
                    case 0:
                        qrCodeLoginDialog.lambda$onCreate$0(view);
                        break;
                    default:
                        qrCodeLoginDialog.lambda$onCreate$1(view);
                        break;
                }
            }
        });
        startLogin();
    }

    @Override // android.app.Dialog
    public void onStop() {
        super.onStop();
        this.isDestroyed = true;
        stopPolling();
    }

    public void setOnLoginFailedListener(OnLoginFailedListener onLoginFailedListener) {
        this.failedListener = onLoginFailedListener;
    }

    public void setOnLoginSuccessListener(OnLoginSuccessListener onLoginSuccessListener) {
        this.successListener = onLoginSuccessListener;
    }
}
