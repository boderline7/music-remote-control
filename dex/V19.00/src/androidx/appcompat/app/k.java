package androidx.appcompat.app;

import android.content.DialogInterface;
import android.os.Binder;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.os.ResultReceiver;
import android.text.TextUtils;
import androidx.media.MediaBrowserServiceCompat;
import java.lang.ref.WeakReference;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_2_1633180.dex */
public final class k extends Handler {

    /* renamed from: a, reason: collision with root package name */
    public final /* synthetic */ int f794a = 0;

    /* renamed from: b, reason: collision with root package name */
    public Object f795b;

    public k(DialogInterface dialogInterface) {
        this.f795b = new WeakReference(dialogInterface);
    }

    public k(MediaBrowserServiceCompat mediaBrowserServiceCompat) {
        this.f795b = mediaBrowserServiceCompat;
    }

    public final void a(Runnable runnable) {
        if (Thread.currentThread() == getLooper().getThread()) {
            runnable.run();
        } else {
            post(runnable);
        }
    }

    @Override // android.os.Handler
    public final void handleMessage(Message message) {
        int i7 = 1;
        switch (this.f794a) {
            case 0:
                int i8 = message.what;
                if (i8 == -3 || i8 == -2 || i8 == -1) {
                    ((DialogInterface.OnClickListener) message.obj).onClick((DialogInterface) ((WeakReference) this.f795b).get(), message.what);
                    return;
                } else {
                    if (i8 != 1) {
                        return;
                    }
                    ((DialogInterface) message.obj).dismiss();
                    return;
                }
            case 1:
                int i9 = message.what;
                if (i9 == 1) {
                    a.a.u(this.f795b);
                    throw null;
                }
                if (i9 == 2) {
                    a.a.u(this.f795b);
                    throw null;
                }
                if (i9 == 3) {
                    a.a.u(this.f795b);
                    throw null;
                }
                throw new RuntimeException("Unknown message " + message);
            default:
                MediaBrowserServiceCompat mediaBrowserServiceCompat = (MediaBrowserServiceCompat) this.f795b;
                if (mediaBrowserServiceCompat == null) {
                    removeCallbacksAndMessages(null);
                    return;
                }
                Bundle data = message.getData();
                int i10 = message.what;
                androidx.fragment.app.o oVar = mediaBrowserServiceCompat.f2227b;
                int i11 = 0;
                switch (i10) {
                    case 1:
                        Bundle bundle = data.getBundle("data_root_hints");
                        android.support.v4.media.session.u.a(bundle);
                        String string = data.getString("data_package_name");
                        int i12 = data.getInt("data_calling_pid");
                        int i13 = data.getInt("data_calling_uid");
                        androidx.fragment.app.o oVar2 = new androidx.fragment.app.o(message.replyTo);
                        MediaBrowserServiceCompat mediaBrowserServiceCompat2 = (MediaBrowserServiceCompat) oVar.f2027a;
                        if (string != null) {
                            String[] packagesForUid = mediaBrowserServiceCompat2.getPackageManager().getPackagesForUid(i13);
                            int length = packagesForUid.length;
                            while (i11 < length) {
                                if (packagesForUid[i11].equals(string)) {
                                    ((MediaBrowserServiceCompat) oVar.f2027a).f2231f.a(new androidx.media.k(oVar, oVar2, string, i12, i13, bundle));
                                    return;
                                }
                                i11++;
                            }
                        } else {
                            mediaBrowserServiceCompat2.getClass();
                        }
                        throw new IllegalArgumentException("Package/uid mismatch: uid=" + i13 + " package=" + string);
                    case 2:
                        ((MediaBrowserServiceCompat) oVar.f2027a).f2231f.a(new androidx.media.l(oVar, new androidx.fragment.app.o(message.replyTo), i11));
                        return;
                    case 3:
                        Bundle bundle2 = data.getBundle("data_options");
                        android.support.v4.media.session.u.a(bundle2);
                        ((MediaBrowserServiceCompat) oVar.f2027a).f2231f.a(new androidx.media.n(oVar, new androidx.fragment.app.o(message.replyTo), data.getString("data_media_item_id"), e0.l.a(data, "data_callback_token"), bundle2));
                        return;
                    case 4:
                        ((MediaBrowserServiceCompat) oVar.f2027a).f2231f.a(new androidx.media.m(oVar, new androidx.fragment.app.o(message.replyTo), data.getString("data_media_item_id"), e0.l.a(data, "data_callback_token"), 0));
                        return;
                    case 5:
                        String string2 = data.getString("data_media_item_id");
                        ResultReceiver resultReceiver = (ResultReceiver) data.getParcelable("data_result_receiver");
                        androidx.fragment.app.o oVar3 = new androidx.fragment.app.o(message.replyTo);
                        oVar.getClass();
                        if (TextUtils.isEmpty(string2) || resultReceiver == null) {
                            return;
                        }
                        ((MediaBrowserServiceCompat) oVar.f2027a).f2231f.a(new androidx.media.m(oVar, oVar3, string2, resultReceiver, 1));
                        return;
                    case 6:
                        Bundle bundle3 = data.getBundle("data_root_hints");
                        android.support.v4.media.session.u.a(bundle3);
                        ((MediaBrowserServiceCompat) oVar.f2027a).f2231f.a(new androidx.media.k(oVar, new androidx.fragment.app.o(message.replyTo), data.getInt("data_calling_uid"), data.getString("data_package_name"), data.getInt("data_calling_pid"), bundle3));
                        return;
                    case 7:
                        ((MediaBrowserServiceCompat) oVar.f2027a).f2231f.a(new androidx.media.l(oVar, new androidx.fragment.app.o(message.replyTo), i7));
                        return;
                    case 8:
                        Bundle bundle4 = data.getBundle("data_search_extras");
                        android.support.v4.media.session.u.a(bundle4);
                        String string3 = data.getString("data_search_query");
                        ResultReceiver resultReceiver2 = (ResultReceiver) data.getParcelable("data_result_receiver");
                        androidx.fragment.app.o oVar4 = new androidx.fragment.app.o(message.replyTo);
                        oVar.getClass();
                        if (TextUtils.isEmpty(string3) || resultReceiver2 == null) {
                            return;
                        }
                        ((MediaBrowserServiceCompat) oVar.f2027a).f2231f.a(new androidx.media.n(oVar, oVar4, string3, bundle4, resultReceiver2, 0));
                        return;
                    case 9:
                        Bundle bundle5 = data.getBundle("data_custom_action_extras");
                        android.support.v4.media.session.u.a(bundle5);
                        String string4 = data.getString("data_custom_action");
                        ResultReceiver resultReceiver3 = (ResultReceiver) data.getParcelable("data_result_receiver");
                        androidx.fragment.app.o oVar5 = new androidx.fragment.app.o(message.replyTo);
                        oVar.getClass();
                        if (TextUtils.isEmpty(string4) || resultReceiver3 == null) {
                            return;
                        }
                        ((MediaBrowserServiceCompat) oVar.f2027a).f2231f.a(new androidx.media.n(oVar, oVar5, string4, bundle5, resultReceiver3, 1));
                        return;
                    default:
                        message.toString();
                        return;
                }
        }
    }

    @Override // android.os.Handler
    public final boolean sendMessageAtTime(Message message, long j7) {
        switch (this.f794a) {
            case 2:
                Bundle data = message.getData();
                data.setClassLoader(android.support.v4.media.m.class.getClassLoader());
                data.putInt("data_calling_uid", Binder.getCallingUid());
                int callingPid = Binder.getCallingPid();
                if (callingPid > 0) {
                    data.putInt("data_calling_pid", callingPid);
                } else if (!data.containsKey("data_calling_pid")) {
                    data.putInt("data_calling_pid", -1);
                }
                break;
        }
        return super.sendMessageAtTime(message, j7);
    }
}
