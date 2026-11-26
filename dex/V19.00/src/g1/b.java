package g1;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.support.v4.media.d;
import android.support.v4.media.g;
import android.support.v4.media.m;
import android.support.v4.media.session.MediaSessionCompat$Token;
import android.support.v4.media.session.j;
import android.support.v4.media.session.k;
import android.view.KeyEvent;
import java.util.Collections;
import java.util.HashSet;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final class b extends d {

    /* renamed from: a, reason: collision with root package name */
    public final Context f6427a;

    /* renamed from: b, reason: collision with root package name */
    public final Intent f6428b;

    /* renamed from: c, reason: collision with root package name */
    public final BroadcastReceiver.PendingResult f6429c;

    /* renamed from: d, reason: collision with root package name */
    public m f6430d;

    public b(Context context, Intent intent, BroadcastReceiver.PendingResult pendingResult) {
        this.f6427a = context;
        this.f6428b = intent;
        this.f6429c = pendingResult;
    }

    @Override // android.support.v4.media.d
    public final void onConnected() {
        g gVar = this.f6430d.f551a;
        if (gVar.f539h == null) {
            gVar.f539h = MediaSessionCompat$Token.l(gVar.f533b.getSessionToken(), null);
        }
        MediaSessionCompat$Token mediaSessionCompat$Token = gVar.f539h;
        if (mediaSessionCompat$Token == null) {
            throw new IllegalArgumentException("sessionToken must not be null");
        }
        Collections.synchronizedSet(new HashSet());
        int i7 = Build.VERSION.SDK_INT;
        Context context = this.f6427a;
        j kVar = i7 >= 29 ? new k(context, mediaSessionCompat$Token) : new j(context, mediaSessionCompat$Token);
        KeyEvent keyEvent = (KeyEvent) this.f6428b.getParcelableExtra("android.intent.extra.KEY_EVENT");
        if (keyEvent == null) {
            throw new IllegalArgumentException("KeyEvent may not be null");
        }
        kVar.f594a.dispatchMediaButtonEvent(keyEvent);
        this.f6430d.a();
        this.f6429c.finish();
    }

    @Override // android.support.v4.media.d
    public final void onConnectionFailed() {
        this.f6430d.a();
        this.f6429c.finish();
    }

    @Override // android.support.v4.media.d
    public final void onConnectionSuspended() {
        this.f6430d.a();
        this.f6429c.finish();
    }
}
