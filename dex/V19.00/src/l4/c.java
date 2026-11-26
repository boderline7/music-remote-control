package l4;

import android.media.session.MediaSessionManager;
import java.util.List;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final class c implements MediaSessionManager.OnActiveSessionsChangedListener {

    /* renamed from: a, reason: collision with root package name */
    public final /* synthetic */ e f7424a;

    public c(e eVar) {
        this.f7424a = eVar;
    }

    @Override // android.media.session.MediaSessionManager.OnActiveSessionsChangedListener
    public final void onActiveSessionsChanged(List list) {
        this.f7424a.g(list);
    }
}
