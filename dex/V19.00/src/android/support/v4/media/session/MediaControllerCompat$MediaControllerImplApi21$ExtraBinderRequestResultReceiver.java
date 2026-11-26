package android.support.v4.media.session;

import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcelable;
import android.os.ResultReceiver;
import androidx.versionedparcelable.ParcelImpl;
import java.lang.ref.WeakReference;
import n1.z;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_2_1633180.dex */
class MediaControllerCompat$MediaControllerImplApi21$ExtraBinderRequestResultReceiver extends ResultReceiver {

    /* renamed from: a, reason: collision with root package name */
    public WeakReference f560a;

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r4v3, types: [android.support.v4.media.session.d] */
    @Override // android.os.ResultReceiver
    public final void onReceiveResult(int i7, Bundle bundle) {
        b bVar;
        j jVar = (j) this.f560a.get();
        if (jVar == null || bundle == null) {
            return;
        }
        synchronized (jVar.f595b) {
            MediaSessionCompat$Token mediaSessionCompat$Token = jVar.f598e;
            IBinder a7 = e0.l.a(bundle, u.KEY_EXTRA_BINDER);
            int i8 = c.f589a;
            p1.c cVar = null;
            if (a7 == null) {
                bVar = null;
            } else {
                IInterface queryLocalInterface = a7.queryLocalInterface(d.DESCRIPTOR);
                if (queryLocalInterface == null || !(queryLocalInterface instanceof d)) {
                    b bVar2 = new b();
                    bVar2.f588a = a7;
                    bVar = bVar2;
                } else {
                    bVar = (d) queryLocalInterface;
                }
            }
            synchronized (mediaSessionCompat$Token.f564a) {
                mediaSessionCompat$Token.f566c = bVar;
            }
            MediaSessionCompat$Token mediaSessionCompat$Token2 = jVar.f598e;
            try {
                Bundle bundle2 = (Bundle) bundle.getParcelable(u.KEY_SESSION2_TOKEN);
                if (bundle2 != null) {
                    bundle2.setClassLoader(z.class.getClassLoader());
                    Parcelable parcelable = bundle2.getParcelable("a");
                    if (!(parcelable instanceof ParcelImpl)) {
                        throw new IllegalArgumentException("Invalid parcel");
                    }
                    cVar = ((ParcelImpl) parcelable).f2797a;
                }
            } catch (RuntimeException unused) {
            }
            synchronized (mediaSessionCompat$Token2.f564a) {
                mediaSessionCompat$Token2.f567d = cVar;
            }
            jVar.a();
        }
    }
}
