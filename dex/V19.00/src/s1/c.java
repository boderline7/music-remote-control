package s1;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.Parcel;
import android.os.RemoteException;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import cn.kuwo.base.bean.Music;
import com.navi.tool.service.MobileHudService;
import java.io.File;
import java.util.Random;
import s5.h0;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final class c {

    /* renamed from: e, reason: collision with root package name */
    public static c f8654e;

    /* renamed from: a, reason: collision with root package name */
    public Handler f8655a;

    /* renamed from: b, reason: collision with root package name */
    public cn.kuwo.autosdk.d f8656b;

    /* renamed from: c, reason: collision with root package name */
    public e f8657c;

    /* renamed from: d, reason: collision with root package name */
    public Context f8658d;

    public static c b(Context context) {
        String str;
        if (f8654e == null) {
            t1.k.f9052f = a.a.i(new StringBuilder(), t1.k.f9051e, "_auto.apk");
            try {
                String macAddress = ((WifiManager) context.getSystemService(MobileHudService.CONNECTION_MODE_WIFI)).getConnectionInfo().getMacAddress();
                t1.k.f9049c = macAddress;
                if (TextUtils.isEmpty(macAddress)) {
                    t1.k.f9049c = h0.FRAGMENT_ENCODE_SET;
                }
            } catch (Throwable th) {
                th.printStackTrace();
                t1.k.f9049c = h0.FRAGMENT_ENCODE_SET;
            }
            try {
                str = ((TelephonyManager) context.getSystemService("phone")).getDeviceId();
            } catch (Throwable th2) {
                th2.printStackTrace();
                str = h0.FRAGMENT_ENCODE_SET;
            }
            if (TextUtils.isEmpty(str)) {
                if (TextUtils.isEmpty(t1.k.f9049c)) {
                    Random random = new Random(System.currentTimeMillis());
                    StringBuilder sb = new StringBuilder();
                    int nextInt = random.nextInt(5);
                    if (nextInt == 0) {
                        nextInt = 1;
                    }
                    int i7 = nextInt * 10000;
                    sb.append(random.nextInt(i7) + i7);
                    int nextInt2 = (random.nextInt(5) + 5) * u1.a.PER_USER_RANGE;
                    sb.append(random.nextInt(nextInt2) + nextInt2);
                    str = sb.toString();
                } else {
                    str = t1.k.f9049c.replace(":", h0.FRAGMENT_ENCODE_SET) + "000";
                }
            }
            t1.k.f9050d = str;
            c cVar = new c();
            cVar.f8658d = context;
            cVar.f8655a = new Handler(context.getMainLooper());
            cn.kuwo.autosdk.m mVar = cn.kuwo.autosdk.l.f3128a;
            try {
                mVar.f3129a = context.createPackageContext(cn.kuwo.autosdk.m.PREFERENCE_PACKAGE, 2);
            } catch (PackageManager.NameNotFoundException e7) {
                e7.printStackTrace();
            }
            Context context2 = mVar.f3129a;
            if (context2 != null) {
                context2.getSharedPreferences(cn.kuwo.autosdk.m.PREFERENCE_NAME, 4).getAll();
            }
            if (mVar.f3129a != null) {
                new File("/data/data/cn.kuwo.kwmusiccar/shared_prefs/cn.kuwo.kwmusiccar_preferences.xml");
            }
            cn.kuwo.autosdk.d dVar = new cn.kuwo.autosdk.d(cVar.f8655a);
            cVar.f8656b = dVar;
            f8654e = cVar;
            b bVar = new b(cVar);
            if (!dVar.f3119m) {
                cn.kuwo.autosdk.d.a(context, "cn.kuwo.kwmusicauto.action.ENTER", dVar.f3110d);
                dVar.f3119m = true;
            }
            dVar.f3112f = bVar;
            b bVar2 = new b(cVar);
            if (!dVar.f3118l) {
                cn.kuwo.autosdk.d.a(context, "cn.kuwo.kwmusicauto.action.EXIT", dVar.f3111e);
                dVar.f3118l = true;
            }
            dVar.f3113g = bVar2;
        }
        return f8654e;
    }

    public final void a() {
        this.f8656b.b(this.f8658d);
    }

    public final int c() {
        this.f8656b.getClass();
        a a7 = a.a();
        a7.getClass();
        try {
            cn.kuwo.autosdk.c cVar = a7.f8651a;
            if (cVar == null) {
                return 0;
            }
            cn.kuwo.autosdk.a aVar = (cn.kuwo.autosdk.a) cVar;
            Parcel obtain = Parcel.obtain();
            Parcel obtain2 = Parcel.obtain();
            try {
                obtain.writeInterfaceToken("cn.kuwo.autosdk.IAutoSdkAidlInterface");
                aVar.f3098a.transact(4, obtain, obtain2, 0);
                obtain2.readException();
                int readInt = obtain2.readInt();
                obtain2.recycle();
                obtain.recycle();
                return readInt;
            } catch (Throwable th) {
                obtain2.recycle();
                obtain.recycle();
                throw th;
            }
        } catch (RemoteException e7) {
            e7.printStackTrace();
            return 0;
        }
    }

    public final Music d() {
        this.f8656b.getClass();
        a a7 = a.a();
        a7.getClass();
        try {
            cn.kuwo.autosdk.c cVar = a7.f8651a;
            if (cVar != null) {
                return ((cn.kuwo.autosdk.a) cVar).i();
            }
            return null;
        } catch (RemoteException e7) {
            e7.printStackTrace();
            return null;
        }
    }

    public final v e() {
        int h7;
        this.f8656b.getClass();
        a a7 = a.a();
        a7.getClass();
        v vVar = v.INIT;
        try {
            cn.kuwo.autosdk.c cVar = a7.f8651a;
            if (cVar != null && (h7 = ((cn.kuwo.autosdk.a) cVar).h()) != 0) {
                if (h7 == 1) {
                    vVar = v.PLAYING;
                } else if (h7 == 2) {
                    vVar = v.BUFFERING;
                } else if (h7 == 3) {
                    vVar = v.PAUSE;
                } else if (h7 == 4) {
                    vVar = v.STOP;
                }
            }
        } catch (RemoteException e7) {
            e7.printStackTrace();
        } catch (Throwable th) {
            th.printStackTrace();
        }
        return vVar;
    }

    public final void f() {
        this.f8656b.getClass();
        Intent intent = new Intent("cn.kuwo.kwmusicauto.action.CLOSE_TOAST");
        intent.putExtra("cn.kuwo.kwmusicauto.version", 3);
        intent.putExtra(cn.kuwo.autosdk.d.KUWO_KEY, cn.kuwo.autosdk.d.f3106n);
        this.f8658d.sendBroadcast(intent);
    }

    public final void g(String str) {
        Context context = this.f8658d;
        this.f8656b.getClass();
        Intent intent = new Intent("cn.kuwo.kwmusicauto.action.SEARCH_MUSIC");
        intent.setFlags(268435456);
        Bundle bundle = new Bundle();
        bundle.putString("name", str);
        bundle.putString("singer", null);
        bundle.putString("album", null);
        bundle.putInt("cn.kuwo.kwmusicauto.version", 3);
        bundle.putString(cn.kuwo.autosdk.d.KUWO_KEY, cn.kuwo.autosdk.d.f3106n);
        intent.putExtras(bundle);
        try {
            context.startActivity(intent);
        } catch (ActivityNotFoundException unused) {
        }
    }

    public final void h(Context context) {
        this.f8656b.getClass();
        Intent intent = new Intent("cn.kuwo.kwmusicauto.action.SET_FAVORITE_MUSIC");
        intent.putExtra(cn.kuwo.autosdk.d.KUWO_KEY, cn.kuwo.autosdk.d.f3106n);
        intent.putExtra("cn.kuwo.kwmusicauto.version", 3);
        context.sendBroadcast(intent);
    }

    public final void i(Context context, n nVar) {
        this.f8656b.getClass();
        if (nVar == null) {
            return;
        }
        Intent intent = new Intent("cn.kuwo.kwmusicauto.action.MEDIA_BUTTON");
        intent.putExtra("EXTRA", nVar.a());
        intent.putExtra(cn.kuwo.autosdk.d.KUWO_KEY, cn.kuwo.autosdk.d.f3106n);
        intent.putExtra("cn.kuwo.kwmusicauto.version", 3);
        context.sendBroadcast(intent);
    }

    public final void j(u uVar) {
        this.f8656b.getClass();
        Intent intent = new Intent("cn.kuwo.kwmusicauto.action.MEDIA_BUTTON");
        intent.putExtra("EXTRA", uVar.a());
        intent.putExtra(cn.kuwo.autosdk.d.KUWO_KEY, cn.kuwo.autosdk.d.f3106n);
        intent.putExtra("cn.kuwo.kwmusicauto.version", 3);
        this.f8658d.sendBroadcast(intent);
    }
}
