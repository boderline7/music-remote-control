package android.support.v4.media;

import android.os.BadParcelableException;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.Messenger;
import android.support.v4.media.session.u;
import java.lang.ref.WeakReference;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_2_1633180.dex */
public final class a extends Handler {

    /* renamed from: a, reason: collision with root package name */
    public final WeakReference f529a;

    /* renamed from: b, reason: collision with root package name */
    public WeakReference f530b;

    public a(k kVar) {
        this.f529a = new WeakReference(kVar);
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // android.os.Handler
    public final void handleMessage(Message message) {
        WeakReference weakReference = this.f530b;
        if (weakReference == null || weakReference.get() == null) {
            return;
        }
        WeakReference weakReference2 = this.f529a;
        if (weakReference2.get() == null) {
            return;
        }
        Bundle data = message.getData();
        u.a(data);
        k kVar = (k) weakReference2.get();
        Messenger messenger = (Messenger) this.f530b.get();
        try {
            int i7 = message.what;
            if (i7 == 1) {
                u.a(data.getBundle("data_root_hints"));
                data.getString("data_media_item_id");
                kVar.getClass();
                message = message;
            } else if (i7 == 2) {
                kVar.getClass();
                message = message;
            } else if (i7 != 3) {
                message.toString();
                message = message;
            } else {
                u.a(data.getBundle("data_options"));
                u.a(data.getBundle("data_notify_children_changed_options"));
                String string = data.getString("data_media_item_id");
                data.getParcelableArrayList("data_media_item_list");
                e eVar = (e) kVar;
                if (eVar.f538g != messenger) {
                    message = message;
                } else {
                    a.a.u(eVar.f536e.getOrDefault(string, null));
                    message = m.f550b;
                }
            }
        } catch (BadParcelableException unused) {
            if (message.what == 1) {
                kVar.getClass();
            }
        }
    }
}
