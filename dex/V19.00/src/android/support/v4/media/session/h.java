package android.support.v4.media.session;

import android.media.session.MediaController;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.support.v4.media.MediaMetadataCompat;
import java.util.List;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_2_1633180.dex */
public abstract class h implements IBinder.DeathRecipient {
    final MediaController.Callback mCallbackFwk = new e(this);
    f mHandler;
    a mIControllerCallback;

    @Override // android.os.IBinder.DeathRecipient
    public void binderDied() {
        postToHandler(8, null, null);
    }

    public a getIControllerCallback() {
        return this.mIControllerCallback;
    }

    public void onAudioInfoChanged(l lVar) {
    }

    public void onCaptioningEnabledChanged(boolean z6) {
    }

    public void onExtrasChanged(Bundle bundle) {
    }

    public abstract void onMetadataChanged(MediaMetadataCompat mediaMetadataCompat);

    public abstract void onPlaybackStateChanged(PlaybackStateCompat playbackStateCompat);

    public void onQueueChanged(List<MediaSessionCompat$QueueItem> list) {
    }

    public void onQueueTitleChanged(CharSequence charSequence) {
    }

    public void onRepeatModeChanged(int i7) {
    }

    public void onSessionDestroyed() {
    }

    public void onSessionEvent(String str, Bundle bundle) {
    }

    public void onSessionReady() {
    }

    public void onShuffleModeChanged(int i7) {
    }

    public void postToHandler(int i7, Object obj, Bundle bundle) {
        f fVar = this.mHandler;
        if (fVar != null) {
            Message obtainMessage = fVar.obtainMessage(i7, obj);
            obtainMessage.setData(bundle);
            obtainMessage.sendToTarget();
        }
    }

    public void setHandler(Handler handler) {
        if (handler != null) {
            f fVar = new f(this, handler.getLooper());
            this.mHandler = fVar;
            fVar.f591a = true;
        } else {
            f fVar2 = this.mHandler;
            if (fVar2 != null) {
                fVar2.f591a = false;
                fVar2.removeCallbacksAndMessages(null);
                this.mHandler = null;
            }
        }
    }
}
