package androidx.media;

import android.app.Service;
import android.content.Intent;
import android.os.Build;
import android.os.IBinder;
import android.util.Log;
import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.util.ArrayList;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_2_1633180.dex */
public abstract class MediaBrowserServiceCompat extends Service {
    public static final String KEY_MEDIA_ITEM = "media_item";
    public static final String KEY_SEARCH_RESULTS = "search_results";
    public static final int RESULT_ERROR = -1;
    public static final int RESULT_OK = 0;
    public static final int RESULT_PROGRESS_UPDATE = 1;
    public static final String SERVICE_INTERFACE = "android.media.browse.MediaBrowserService";

    /* renamed from: a, reason: collision with root package name */
    public h f2226a;

    /* renamed from: b, reason: collision with root package name */
    public final androidx.fragment.app.o f2227b = new androidx.fragment.app.o(this);

    /* renamed from: c, reason: collision with root package name */
    public final e f2228c = new e(this, "android.media.session.MediaController", -1, -1, null);

    /* renamed from: d, reason: collision with root package name */
    public final ArrayList f2229d = new ArrayList();

    /* renamed from: e, reason: collision with root package name */
    public final q.b f2230e = new q.b();

    /* renamed from: f, reason: collision with root package name */
    public final androidx.appcompat.app.k f2231f = new androidx.appcompat.app.k(this);

    static {
        Log.isLoggable("MBServiceCompat", 3);
    }

    public abstract c a();

    public abstract void b();

    @Override // android.app.Service
    public final void dump(FileDescriptor fileDescriptor, PrintWriter printWriter, String[] strArr) {
    }

    @Override // android.app.Service
    public IBinder onBind(Intent intent) {
        return this.f2226a.f2252b.onBind(intent);
    }

    @Override // android.app.Service
    public final void onCreate() {
        super.onCreate();
        if (Build.VERSION.SDK_INT >= 28) {
            this.f2226a = new i(this);
        } else {
            this.f2226a = new h(this);
        }
        this.f2226a.a();
    }

    @Override // android.app.Service
    public void onDestroy() {
        this.f2231f.f795b = null;
    }
}
