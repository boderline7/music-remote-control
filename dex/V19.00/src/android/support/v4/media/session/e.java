package android.support.v4.media.session;

import android.media.MediaMetadata;
import android.media.session.MediaController;
import android.media.session.MediaSession;
import android.media.session.PlaybackState;
import android.os.Bundle;
import android.support.v4.media.MediaDescriptionCompat;
import android.support.v4.media.MediaMetadataCompat;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_2_1633180.dex */
public final class e extends MediaController.Callback {

    /* renamed from: a, reason: collision with root package name */
    public final WeakReference f590a;

    public e(h hVar) {
        this.f590a = new WeakReference(hVar);
    }

    @Override // android.media.session.MediaController.Callback
    public final void onAudioInfoChanged(MediaController.PlaybackInfo playbackInfo) {
        h hVar = (h) this.f590a.get();
        if (hVar != null) {
            playbackInfo.getPlaybackType();
            playbackInfo.getAudioAttributes();
            playbackInfo.getVolumeControl();
            playbackInfo.getMaxVolume();
            playbackInfo.getCurrentVolume();
            hVar.onAudioInfoChanged(new l());
        }
    }

    @Override // android.media.session.MediaController.Callback
    public final void onExtrasChanged(Bundle bundle) {
        u.a(bundle);
        h hVar = (h) this.f590a.get();
        if (hVar != null) {
            hVar.onExtrasChanged(bundle);
        }
    }

    @Override // android.media.session.MediaController.Callback
    public final void onMetadataChanged(MediaMetadata mediaMetadata) {
        h hVar = (h) this.f590a.get();
        if (hVar != null) {
            hVar.onMetadataChanged(MediaMetadataCompat.l(mediaMetadata));
        }
    }

    @Override // android.media.session.MediaController.Callback
    public final void onPlaybackStateChanged(PlaybackState playbackState) {
        h hVar = (h) this.f590a.get();
        if (hVar == null || hVar.mIControllerCallback != null) {
            return;
        }
        hVar.onPlaybackStateChanged(PlaybackStateCompat.l(playbackState));
    }

    @Override // android.media.session.MediaController.Callback
    public final void onQueueChanged(List list) {
        MediaSessionCompat$QueueItem mediaSessionCompat$QueueItem;
        h hVar = (h) this.f590a.get();
        if (hVar != null) {
            ArrayList arrayList = null;
            if (list != null) {
                ArrayList arrayList2 = new ArrayList(list.size());
                for (Object obj : list) {
                    if (obj != null) {
                        MediaSession.QueueItem queueItem = (MediaSession.QueueItem) obj;
                        mediaSessionCompat$QueueItem = new MediaSessionCompat$QueueItem(MediaDescriptionCompat.l(s.b(queueItem)), s.c(queueItem));
                    } else {
                        mediaSessionCompat$QueueItem = null;
                    }
                    arrayList2.add(mediaSessionCompat$QueueItem);
                }
                arrayList = arrayList2;
            }
            hVar.onQueueChanged(arrayList);
        }
    }

    @Override // android.media.session.MediaController.Callback
    public final void onQueueTitleChanged(CharSequence charSequence) {
        h hVar = (h) this.f590a.get();
        if (hVar != null) {
            hVar.onQueueTitleChanged(charSequence);
        }
    }

    @Override // android.media.session.MediaController.Callback
    public final void onSessionDestroyed() {
        h hVar = (h) this.f590a.get();
        if (hVar != null) {
            hVar.onSessionDestroyed();
        }
    }

    @Override // android.media.session.MediaController.Callback
    public final void onSessionEvent(String str, Bundle bundle) {
        u.a(bundle);
        h hVar = (h) this.f590a.get();
        if (hVar != null) {
            hVar.onSessionEvent(str, bundle);
        }
    }
}
