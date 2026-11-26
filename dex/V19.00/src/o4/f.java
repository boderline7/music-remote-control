package o4;

import android.content.ContextWrapper;
import android.os.Handler;
import android.provider.Settings;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;
import com.navi.tool.carinfo.CarInfoService;
import com.navi.tool.service.LogLinkageManager;
import com.navi.tool.ui.extra.FileManagerActivity;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.List;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final /* synthetic */ class f implements Runnable {

    /* renamed from: a, reason: collision with root package name */
    public final /* synthetic */ int f7963a;

    /* renamed from: b, reason: collision with root package name */
    public final /* synthetic */ int f7964b;

    /* renamed from: c, reason: collision with root package name */
    public final /* synthetic */ int f7965c;

    /* renamed from: d, reason: collision with root package name */
    public final /* synthetic */ Object f7966d;

    /* renamed from: e, reason: collision with root package name */
    public final /* synthetic */ Object f7967e;

    /* renamed from: f, reason: collision with root package name */
    public final /* synthetic */ Object f7968f;

    public /* synthetic */ f(ContextWrapper contextWrapper, int i7, Object obj, int i8, Object obj2, int i9) {
        this.f7963a = i9;
        this.f7966d = contextWrapper;
        this.f7964b = i7;
        this.f7967e = obj;
        this.f7965c = i8;
        this.f7968f = obj2;
    }

    public /* synthetic */ f(TextView textView, File file, TextView textView2, int i7, int i8) {
        this.f7963a = 1;
        this.f7966d = textView;
        this.f7967e = file;
        this.f7968f = textView2;
        this.f7964b = i7;
        this.f7965c = i8;
    }

    @Override // java.lang.Runnable
    public final void run() {
        int i7 = this.f7963a;
        int i8 = this.f7965c;
        int i9 = this.f7964b;
        Object obj = this.f7968f;
        Object obj2 = this.f7967e;
        Object obj3 = this.f7966d;
        switch (i7) {
            case 0:
                CarInfoService carInfoService = (CarInfoService) obj3;
                String str = (String) obj2;
                String str2 = (String) obj;
                int[] iArr = CarInfoService.f4686z;
                carInfoService.getClass();
                if (Settings.canDrawOverlays(carInfoService)) {
                    carInfoService.e();
                    String str3 = null;
                    View inflate = LayoutInflater.from(carInfoService).inflate(g4.q.view_wireless_charging_capsule, (ViewGroup) null);
                    ImageView imageView = (ImageView) inflate.findViewById(g4.p.wireless_capsule_icon);
                    TextView textView = (TextView) inflate.findViewById(g4.p.wireless_capsule_text);
                    if (imageView != null) {
                        imageView.setImageResource(i9);
                    }
                    if (textView != null) {
                        textView.setText(str);
                    }
                    WindowManager.LayoutParams layoutParams = new WindowManager.LayoutParams(-2, -2, 2038, 264, -3);
                    layoutParams.gravity = 49;
                    layoutParams.y = (int) (carInfoService.getResources().getDisplayMetrics().density * 100.0f);
                    try {
                        ((WindowManager) carInfoService.getSystemService("window")).addView(inflate, layoutParams);
                        carInfoService.f4707u = inflate;
                    } catch (Exception unused) {
                        carInfoService.f4707u = null;
                    }
                    try {
                        File file = new File(carInfoService.getFilesDir(), "wireless_sounds");
                        if (file.exists() || file.mkdirs()) {
                            File file2 = new File(file, str2);
                            if (!file2.exists() || file2.length() <= 0) {
                                InputStream openRawResource = carInfoService.getResources().openRawResource(i8);
                                try {
                                    FileOutputStream fileOutputStream = new FileOutputStream(file2);
                                    try {
                                        byte[] bArr = new byte[8192];
                                        while (true) {
                                            int read = openRawResource.read(bArr);
                                            if (read > 0) {
                                                fileOutputStream.write(bArr, 0, read);
                                            } else {
                                                String absolutePath = file2.getAbsolutePath();
                                                fileOutputStream.close();
                                                openRawResource.close();
                                                str3 = absolutePath;
                                            }
                                        }
                                    } finally {
                                    }
                                } finally {
                                }
                            } else {
                                str3 = file2.getAbsolutePath();
                            }
                        }
                    } catch (Exception unused2) {
                    }
                    if (str3 != null) {
                        LogLinkageManager.playMediaFile(carInfoService, str3);
                    }
                    Handler handler = carInfoService.f4692f;
                    d dVar = carInfoService.f4709w;
                    handler.removeCallbacks(dVar);
                    handler.postDelayed(dVar, 5000L);
                    return;
                }
                return;
            case 1:
                FileManagerActivity.lambda$uploadToCloudDisk$90((TextView) obj3, (File) obj2, (TextView) obj, i9, i8);
                return;
            default:
                ((FileManagerActivity) obj3).lambda$streamNextBatch$78(i9, (File) obj2, i8, (List) obj);
                return;
        }
    }
}
