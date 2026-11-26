package android.support.v4.media.session;

import android.media.MediaDescription;
import android.media.session.MediaSession;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_2_1633180.dex */
public abstract class s {
    public static MediaSession.QueueItem a(MediaDescription mediaDescription, long j7) {
        return new MediaSession.QueueItem(mediaDescription, j7);
    }

    public static MediaDescription b(MediaSession.QueueItem queueItem) {
        return queueItem.getDescription();
    }

    public static long c(MediaSession.QueueItem queueItem) {
        return queueItem.getQueueId();
    }
}
