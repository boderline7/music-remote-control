package android.support.v4.media;

import android.content.ComponentName;
import android.content.Context;
import android.media.browse.MediaBrowser;
import android.os.Bundle;
import android.os.Messenger;
import android.os.Process;
import android.support.v4.media.session.MediaSessionCompat$Token;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_2_1633180.dex */
public abstract class e implements k, c {

    /* renamed from: a, reason: collision with root package name */
    public final Context f532a;

    /* renamed from: b, reason: collision with root package name */
    public final MediaBrowser f533b;

    /* renamed from: c, reason: collision with root package name */
    public final Bundle f534c;

    /* renamed from: d, reason: collision with root package name */
    public final a f535d = new a(this);

    /* renamed from: e, reason: collision with root package name */
    public final q.b f536e = new q.b();

    /* renamed from: f, reason: collision with root package name */
    public i6.i f537f;

    /* renamed from: g, reason: collision with root package name */
    public Messenger f538g;

    /* renamed from: h, reason: collision with root package name */
    public MediaSessionCompat$Token f539h;

    public e(Context context, ComponentName componentName, d dVar) {
        this.f532a = context;
        Bundle bundle = new Bundle();
        this.f534c = bundle;
        bundle.putInt("extra_client_version", 1);
        bundle.putInt("extra_calling_pid", Process.myPid());
        dVar.setInternalConnectionCallback(this);
        this.f533b = new MediaBrowser(context, componentName, dVar.mConnectionCallbackFwk, bundle);
    }
}
