package android.support.v4.media.session;

import android.content.Context;
import android.media.session.MediaController;
import android.media.session.MediaSession;
import android.os.RemoteException;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_2_1633180.dex */
public class j {

    /* renamed from: a, reason: collision with root package name */
    public final MediaController f594a;

    /* renamed from: b, reason: collision with root package name */
    public final Object f595b = new Object();

    /* renamed from: c, reason: collision with root package name */
    public final ArrayList f596c = new ArrayList();

    /* renamed from: d, reason: collision with root package name */
    public final HashMap f597d = new HashMap();

    /* renamed from: e, reason: collision with root package name */
    public final MediaSessionCompat$Token f598e;

    public j(Context context, MediaSessionCompat$Token mediaSessionCompat$Token) {
        this.f598e = mediaSessionCompat$Token;
        MediaController mediaController = new MediaController(context, (MediaSession.Token) mediaSessionCompat$Token.f565b);
        this.f594a = mediaController;
        if (mediaSessionCompat$Token.m() == null) {
            MediaControllerCompat$MediaControllerImplApi21$ExtraBinderRequestResultReceiver mediaControllerCompat$MediaControllerImplApi21$ExtraBinderRequestResultReceiver = new MediaControllerCompat$MediaControllerImplApi21$ExtraBinderRequestResultReceiver(null);
            mediaControllerCompat$MediaControllerImplApi21$ExtraBinderRequestResultReceiver.f560a = new WeakReference(this);
            mediaController.sendCommand(r.COMMAND_GET_EXTRA_BINDER, null, mediaControllerCompat$MediaControllerImplApi21$ExtraBinderRequestResultReceiver);
        }
    }

    public final void a() {
        MediaSessionCompat$Token mediaSessionCompat$Token = this.f598e;
        if (mediaSessionCompat$Token.m() == null) {
            return;
        }
        ArrayList arrayList = this.f596c;
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            h hVar = (h) it.next();
            i iVar = new i(hVar);
            this.f597d.put(hVar, iVar);
            hVar.mIControllerCallback = iVar;
            try {
                ((b) mediaSessionCompat$Token.m()).i(iVar);
                hVar.postToHandler(13, null, null);
            } catch (RemoteException unused) {
            }
        }
        arrayList.clear();
    }

    public final void b(h hVar) {
        this.f594a.unregisterCallback(hVar.mCallbackFwk);
        synchronized (this.f595b) {
            if (this.f598e.m() != null) {
                try {
                    i iVar = (i) this.f597d.remove(hVar);
                    if (iVar != null) {
                        hVar.mIControllerCallback = null;
                        ((b) this.f598e.m()).j(iVar);
                    }
                } catch (RemoteException unused) {
                }
            } else {
                this.f596c.remove(hVar);
            }
        }
    }
}
