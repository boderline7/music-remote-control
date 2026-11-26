package android.support.v4.media;

import android.content.Context;
import android.media.browse.MediaBrowser;
import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Message;
import android.os.Messenger;
import android.os.Process;
import android.os.RemoteException;
import android.support.v4.media.session.MediaSessionCompat$Token;
import java.lang.ref.WeakReference;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_2_1633180.dex */
public final class b extends MediaBrowser.ConnectionCallback {

    /* renamed from: a, reason: collision with root package name */
    public final /* synthetic */ d f531a;

    public b(d dVar) {
        this.f531a = dVar;
    }

    @Override // android.media.browse.MediaBrowser.ConnectionCallback
    public final void onConnected() {
        android.support.v4.media.session.d dVar;
        d dVar2 = this.f531a;
        c cVar = dVar2.mConnectionCallbackInternal;
        if (cVar != null) {
            e eVar = (e) cVar;
            MediaBrowser mediaBrowser = eVar.f533b;
            try {
                Bundle extras = mediaBrowser.getExtras();
                if (extras != null) {
                    extras.getInt("extra_service_version", 0);
                    IBinder a7 = e0.l.a(extras, "extra_messenger");
                    if (a7 != null) {
                        eVar.f537f = new i6.i(a7, eVar.f534c);
                        a aVar = eVar.f535d;
                        Messenger messenger = new Messenger(aVar);
                        eVar.f538g = messenger;
                        aVar.getClass();
                        aVar.f530b = new WeakReference(messenger);
                        try {
                            i6.i iVar = eVar.f537f;
                            Context context = eVar.f532a;
                            Messenger messenger2 = eVar.f538g;
                            iVar.getClass();
                            Bundle bundle = new Bundle();
                            bundle.putString("data_package_name", context.getPackageName());
                            bundle.putInt("data_calling_pid", Process.myPid());
                            bundle.putBundle("data_root_hints", (Bundle) iVar.f6849c);
                            Message obtain = Message.obtain();
                            obtain.what = 6;
                            obtain.arg1 = 1;
                            obtain.setData(bundle);
                            obtain.replyTo = messenger2;
                            ((Messenger) iVar.f6848b).send(obtain);
                        } catch (RemoteException unused) {
                        }
                    }
                    IBinder a8 = e0.l.a(extras, "extra_session_binder");
                    int i7 = android.support.v4.media.session.c.f589a;
                    if (a8 == null) {
                        dVar = null;
                    } else {
                        IInterface queryLocalInterface = a8.queryLocalInterface(android.support.v4.media.session.d.DESCRIPTOR);
                        if (queryLocalInterface == null || !(queryLocalInterface instanceof android.support.v4.media.session.d)) {
                            android.support.v4.media.session.b bVar = new android.support.v4.media.session.b();
                            bVar.f588a = a8;
                            dVar = bVar;
                        } else {
                            dVar = (android.support.v4.media.session.d) queryLocalInterface;
                        }
                    }
                    if (dVar != null) {
                        eVar.f539h = MediaSessionCompat$Token.l(mediaBrowser.getSessionToken(), dVar);
                    }
                }
            } catch (IllegalStateException unused2) {
            }
        }
        dVar2.onConnected();
    }

    @Override // android.media.browse.MediaBrowser.ConnectionCallback
    public final void onConnectionFailed() {
        d dVar = this.f531a;
        c cVar = dVar.mConnectionCallbackInternal;
        if (cVar != null) {
            cVar.getClass();
        }
        dVar.onConnectionFailed();
    }

    @Override // android.media.browse.MediaBrowser.ConnectionCallback
    public final void onConnectionSuspended() {
        d dVar = this.f531a;
        c cVar = dVar.mConnectionCallbackInternal;
        if (cVar != null) {
            e eVar = (e) cVar;
            eVar.f537f = null;
            eVar.f538g = null;
            eVar.f539h = null;
            a aVar = eVar.f535d;
            aVar.getClass();
            aVar.f530b = new WeakReference(null);
        }
        dVar.onConnectionSuspended();
    }
}
