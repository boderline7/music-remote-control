package android.support.v4.media;

import android.content.ComponentName;
import android.content.Context;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.util.Log;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_2_1633180.dex */
public final class m {
    public static final String CUSTOM_ACTION_DOWNLOAD = "android.support.v4.media.action.DOWNLOAD";
    public static final String CUSTOM_ACTION_REMOVE_DOWNLOADED_FILE = "android.support.v4.media.action.REMOVE_DOWNLOADED_FILE";
    public static final String EXTRA_DOWNLOAD_PROGRESS = "android.media.browse.extra.DOWNLOAD_PROGRESS";
    public static final String EXTRA_MEDIA_ID = "android.media.browse.extra.MEDIA_ID";
    public static final String EXTRA_PAGE = "android.media.browse.extra.PAGE";
    public static final String EXTRA_PAGE_SIZE = "android.media.browse.extra.PAGE_SIZE";

    /* renamed from: b, reason: collision with root package name */
    public static final boolean f550b = Log.isLoggable("MediaBrowserCompat", 3);

    /* renamed from: a, reason: collision with root package name */
    public final g f551a;

    public m(Context context, ComponentName componentName, d dVar) {
        this.f551a = new g(context, componentName, dVar);
    }

    public final void a() {
        Messenger messenger;
        g gVar = this.f551a;
        i6.i iVar = gVar.f537f;
        if (iVar != null && (messenger = gVar.f538g) != null) {
            try {
                Message obtain = Message.obtain();
                obtain.what = 7;
                obtain.arg1 = 1;
                obtain.setData(null);
                obtain.replyTo = messenger;
                ((Messenger) iVar.f6848b).send(obtain);
            } catch (RemoteException unused) {
            }
        }
        gVar.f533b.disconnect();
    }
}
