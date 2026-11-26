package android.support.v4.media.session;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.v4.media.MediaMetadataCompat;
import java.util.List;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_2_1633180.dex */
public final class f extends Handler {

    /* renamed from: a, reason: collision with root package name */
    public boolean f591a;

    /* renamed from: b, reason: collision with root package name */
    public final /* synthetic */ h f592b;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public f(h hVar, Looper looper) {
        super(looper);
        this.f592b = hVar;
        this.f591a = false;
    }

    @Override // android.os.Handler
    public final void handleMessage(Message message) {
        if (this.f591a) {
            int i7 = message.what;
            h hVar = this.f592b;
            switch (i7) {
                case 1:
                    Bundle data = message.getData();
                    u.a(data);
                    hVar.onSessionEvent((String) message.obj, data);
                    break;
                case 2:
                    hVar.onPlaybackStateChanged((PlaybackStateCompat) message.obj);
                    break;
                case 3:
                    hVar.onMetadataChanged((MediaMetadataCompat) message.obj);
                    break;
                case 4:
                    hVar.onAudioInfoChanged((l) message.obj);
                    break;
                case 5:
                    hVar.onQueueChanged((List) message.obj);
                    break;
                case 6:
                    hVar.onQueueTitleChanged((CharSequence) message.obj);
                    break;
                case 7:
                    Bundle bundle = (Bundle) message.obj;
                    u.a(bundle);
                    hVar.onExtrasChanged(bundle);
                    break;
                case 8:
                    hVar.onSessionDestroyed();
                    break;
                case 9:
                    hVar.onRepeatModeChanged(((Integer) message.obj).intValue());
                    break;
                case 11:
                    hVar.onCaptioningEnabledChanged(((Boolean) message.obj).booleanValue());
                    break;
                case 12:
                    hVar.onShuffleModeChanged(((Integer) message.obj).intValue());
                    break;
                case 13:
                    hVar.onSessionReady();
                    break;
            }
        }
    }
}
