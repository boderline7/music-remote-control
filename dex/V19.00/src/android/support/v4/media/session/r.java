package android.support.v4.media.session;

import android.content.Context;
import android.media.MediaMetadata;
import android.media.session.MediaController;
import android.media.session.PlaybackState;
import android.os.Build;
import android.os.Handler;
import android.os.RemoteException;
import android.support.v4.media.MediaMetadataCompat;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_2_1633180.dex */
public final class r {
    public static final String COMMAND_ADD_QUEUE_ITEM = "android.support.v4.media.session.command.ADD_QUEUE_ITEM";
    public static final String COMMAND_ADD_QUEUE_ITEM_AT = "android.support.v4.media.session.command.ADD_QUEUE_ITEM_AT";
    public static final String COMMAND_ARGUMENT_INDEX = "android.support.v4.media.session.command.ARGUMENT_INDEX";
    public static final String COMMAND_ARGUMENT_MEDIA_DESCRIPTION = "android.support.v4.media.session.command.ARGUMENT_MEDIA_DESCRIPTION";
    public static final String COMMAND_GET_EXTRA_BINDER = "android.support.v4.media.session.command.GET_EXTRA_BINDER";
    public static final String COMMAND_REMOVE_QUEUE_ITEM = "android.support.v4.media.session.command.REMOVE_QUEUE_ITEM";
    public static final String COMMAND_REMOVE_QUEUE_ITEM_AT = "android.support.v4.media.session.command.REMOVE_QUEUE_ITEM_AT";

    /* renamed from: a, reason: collision with root package name */
    public final j f600a;

    /* renamed from: b, reason: collision with root package name */
    public final Set f601b;

    public r(Context context, MediaSessionCompat$Token mediaSessionCompat$Token) {
        if (mediaSessionCompat$Token == null) {
            throw new IllegalArgumentException("sessionToken must not be null");
        }
        this.f601b = Collections.synchronizedSet(new HashSet());
        if (Build.VERSION.SDK_INT >= 29) {
            this.f600a = new k(context, mediaSessionCompat$Token);
        } else {
            this.f600a = new j(context, mediaSessionCompat$Token);
        }
    }

    public final MediaMetadataCompat a() {
        MediaMetadata metadata = this.f600a.f594a.getMetadata();
        if (metadata != null) {
            return MediaMetadataCompat.l(metadata);
        }
        return null;
    }

    public final PlaybackStateCompat b() {
        j jVar = this.f600a;
        MediaSessionCompat$Token mediaSessionCompat$Token = jVar.f598e;
        if (mediaSessionCompat$Token.m() != null) {
            try {
                return ((b) mediaSessionCompat$Token.m()).h();
            } catch (RemoteException unused) {
            }
        }
        PlaybackState playbackState = jVar.f594a.getPlaybackState();
        if (playbackState != null) {
            return PlaybackStateCompat.l(playbackState);
        }
        return null;
    }

    public final p c() {
        MediaController.TransportControls transportControls = this.f600a.f594a.getTransportControls();
        return Build.VERSION.SDK_INT >= 29 ? new q(transportControls) : new p(transportControls);
    }

    public final void d(h hVar) {
        if (hVar == null) {
            throw new IllegalArgumentException("callback must not be null");
        }
        if (this.f601b.add(hVar)) {
            Handler handler = new Handler();
            hVar.setHandler(handler);
            j jVar = this.f600a;
            jVar.f594a.registerCallback(hVar.mCallbackFwk, handler);
            synchronized (jVar.f595b) {
                if (jVar.f598e.m() != null) {
                    i iVar = new i(hVar);
                    jVar.f597d.put(hVar, iVar);
                    hVar.mIControllerCallback = iVar;
                    try {
                        ((b) jVar.f598e.m()).i(iVar);
                        hVar.postToHandler(13, null, null);
                    } catch (RemoteException unused) {
                    }
                } else {
                    hVar.mIControllerCallback = null;
                    jVar.f596c.add(hVar);
                }
            }
        }
    }

    public final void e(h hVar) {
        if (hVar == null) {
            throw new IllegalArgumentException("callback must not be null");
        }
        if (this.f601b.remove(hVar)) {
            try {
                this.f600a.b(hVar);
            } finally {
                hVar.setHandler(null);
            }
        }
    }
}
