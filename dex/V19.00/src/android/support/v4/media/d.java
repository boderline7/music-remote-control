package android.support.v4.media;

import android.media.browse.MediaBrowser;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_2_1633180.dex */
public abstract class d {
    final MediaBrowser.ConnectionCallback mConnectionCallbackFwk = new b(this);
    c mConnectionCallbackInternal;

    public abstract void onConnected();

    public abstract void onConnectionFailed();

    public abstract void onConnectionSuspended();

    public void setInternalConnectionCallback(c cVar) {
        this.mConnectionCallbackInternal = cVar;
    }
}
