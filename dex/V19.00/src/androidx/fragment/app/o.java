package androidx.fragment.app;

import android.app.Dialog;
import android.media.browse.MediaBrowser;
import android.os.IBinder;
import android.os.Messenger;
import android.os.Parcel;
import android.service.media.MediaBrowserService;
import android.util.Log;
import android.view.View;
import android.view.accessibility.AccessibilityNodeInfo;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_2_1633180.dex */
public class o implements androidx.lifecycle.h0, l0.c {

    /* renamed from: a, reason: collision with root package name */
    public final Object f2027a;

    public /* synthetic */ o(Object obj) {
        this.f2027a = obj;
    }

    public static o b(int i7, int i8, int i9, int i10, boolean z6, boolean z7) {
        return new o(AccessibilityNodeInfo.CollectionItemInfo.obtain(i7, i8, i9, i10, z6, z7));
    }

    public IBinder a() {
        return ((Messenger) this.f2027a).getBinder();
    }

    public void c(Object obj) {
        ArrayList arrayList = null;
        if (!(obj instanceof List)) {
            if (!(obj instanceof Parcel)) {
                ((MediaBrowserService.Result) this.f2027a).sendResult(null);
                return;
            }
            Parcel parcel = (Parcel) obj;
            parcel.setDataPosition(0);
            ((MediaBrowserService.Result) this.f2027a).sendResult(MediaBrowser.MediaItem.CREATOR.createFromParcel(parcel));
            parcel.recycle();
            return;
        }
        MediaBrowserService.Result result = (MediaBrowserService.Result) this.f2027a;
        List<Parcel> list = (List) obj;
        if (list != null) {
            arrayList = new ArrayList(list.size());
            for (Parcel parcel2 : list) {
                parcel2.setDataPosition(0);
                arrayList.add((MediaBrowser.MediaItem) MediaBrowser.MediaItem.CREATOR.createFromParcel(parcel2));
                parcel2.recycle();
            }
        }
        result.sendResult(arrayList);
    }

    @Override // l0.c
    public void h() {
        ((v1) this.f2027a).a();
    }

    @Override // androidx.lifecycle.h0
    public void onChanged(Object obj) {
        boolean z6;
        Dialog dialog;
        Dialog dialog2;
        Dialog dialog3;
        if (((androidx.lifecycle.x) obj) != null) {
            q qVar = (q) this.f2027a;
            z6 = qVar.mShowsDialog;
            if (z6) {
                View requireView = qVar.requireView();
                if (requireView.getParent() != null) {
                    throw new IllegalStateException("DialogFragment can not be attached to a container view");
                }
                dialog = qVar.mDialog;
                if (dialog != null) {
                    if (Log.isLoggable("FragmentManager", 3)) {
                        dialog3 = qVar.mDialog;
                        Objects.toString(dialog3);
                    }
                    dialog2 = qVar.mDialog;
                    dialog2.setContentView(requireView);
                }
            }
        }
    }
}
