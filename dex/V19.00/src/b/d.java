package b;

import android.app.Activity;
import android.content.Intent;
import android.content.IntentSender;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v4.os.ResultReceiver;
import android.view.View;
import androidx.activity.h;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.j0;
import androidx.recyclerview.widget.j2;
import androidx.recyclerview.widget.m1;
import androidx.recyclerview.widget.p0;
import cn.kuwo.autosdk.k;
import com.google.android.material.bottomsheet.BottomSheetBehavior;
import d.e;
import i0.g;
import java.util.ArrayList;
import java.util.List;
import l6.f;
import m0.j;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final class d implements Runnable {

    /* renamed from: a, reason: collision with root package name */
    public final /* synthetic */ int f2926a;

    /* renamed from: b, reason: collision with root package name */
    public final int f2927b;

    /* renamed from: c, reason: collision with root package name */
    public final Object f2928c;

    /* renamed from: d, reason: collision with root package name */
    public final Object f2929d;

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public d(int i7, ArrayList arrayList) {
        this(arrayList, i7, null);
        this.f2926a = 5;
    }

    public /* synthetic */ d(Object obj, int i7, Object obj2, int i8) {
        this.f2926a = i8;
        this.f2929d = obj;
        this.f2927b = i7;
        this.f2928c = obj2;
    }

    public /* synthetic */ d(Object obj, Object obj2, int i7, int i8) {
        this.f2926a = i8;
        this.f2929d = obj;
        this.f2928c = obj2;
        this.f2927b = i7;
    }

    public d(List list, int i7, Throwable th) {
        this.f2926a = 5;
        if (list == null) {
            throw new NullPointerException("initCallbacks cannot be null");
        }
        this.f2928c = new ArrayList(list);
        this.f2927b = i7;
        this.f2929d = th;
    }

    /* JADX WARN: Illegal instructions before constructor call */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public d(y0.j r3, int r4) {
        /*
            r2 = this;
            r0 = 5
            r2.f2926a = r0
            r0 = 1
            y0.j[] r0 = new y0.j[r0]
            if (r3 == 0) goto L14
            r1 = 0
            r0[r1] = r3
            java.util.List r3 = java.util.Arrays.asList(r0)
            r0 = 0
            r2.<init>(r3, r4, r0)
            return
        L14:
            java.lang.NullPointerException r3 = new java.lang.NullPointerException
            java.lang.String r4 = "initCallback cannot be null"
            r3.<init>(r4)
            throw r3
        */
        throw new UnsupportedOperationException("Method not decompiled: b.d.<init>(y0.j, int):void");
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // java.lang.Runnable
    public final void run() {
        d.a aVar;
        int i7 = this.f2926a;
        int i8 = 0;
        Object obj = this.f2929d;
        int i9 = this.f2927b;
        Object obj2 = this.f2928c;
        switch (i7) {
            case 0:
                ((ResultReceiver) obj).l(i9, (Bundle) obj2);
                break;
            case 1:
                h hVar = (h) obj;
                Object obj3 = ((e.a) obj2).f6290b;
                String str = (String) hVar.f6250a.get(Integer.valueOf(i9));
                if (str != null) {
                    e eVar = (e) hVar.f6254e.get(str);
                    if (eVar != null && (aVar = eVar.f6246a) != null) {
                        if (hVar.f6253d.remove(str)) {
                            aVar.a(obj3);
                            break;
                        }
                    } else {
                        hVar.f6256g.remove(str);
                        hVar.f6255f.put(str, obj3);
                        break;
                    }
                }
                break;
            case 2:
                ((h) obj).a(i9, 0, new Intent().setAction("androidx.activity.result.contract.action.INTENT_SENDER_REQUEST").putExtra("androidx.activity.result.contract.extra.SEND_INTENT_EXCEPTION", (IntentSender.SendIntentException) obj2));
                break;
            case 3:
                String[] strArr = (String[]) obj2;
                int[] iArr = new int[strArr.length];
                Activity activity = (Activity) obj;
                PackageManager packageManager = activity.getPackageManager();
                String packageName = activity.getPackageName();
                int length = strArr.length;
                while (i8 < length) {
                    iArr[i8] = packageManager.checkPermission(strArr[i8], packageName);
                    i8++;
                }
                ((e0.d) activity).onRequestPermissionsResult(i9, strArr, iArr);
                break;
            case 4:
                f fVar = ((g) ((j) obj2)).f6780a;
                if (fVar != null) {
                    fVar.a0(i9);
                    break;
                }
                break;
            case 5:
                List list = (List) obj2;
                int size = list.size();
                if (i9 == 1) {
                    while (i8 < size) {
                        ((y0.j) list.get(i8)).b();
                        i8++;
                    }
                    break;
                } else {
                    while (i8 < size) {
                        ((y0.j) list.get(i8)).a();
                        i8++;
                    }
                    break;
                }
            case 6:
                p0 p0Var = (p0) obj;
                RecyclerView recyclerView = p0Var.f2588r;
                if (recyclerView != null && recyclerView.isAttachedToWindow()) {
                    j0 j0Var = (j0) obj2;
                    if (!j0Var.f2511k) {
                        j2 j2Var = j0Var.f2505e;
                        if (j2Var.getAdapterPosition() != -1) {
                            m1 itemAnimator = p0Var.f2588r.getItemAnimator();
                            if (itemAnimator == null || !itemAnimator.f()) {
                                ArrayList arrayList = p0Var.f2586p;
                                int size2 = arrayList.size();
                                while (i8 < size2) {
                                    if (((j0) arrayList.get(i8)).f2512l) {
                                        i8++;
                                    }
                                }
                                p0Var.f2583m.onSwiped(j2Var, i9);
                                break;
                            }
                            p0Var.f2588r.post(this);
                            break;
                        }
                    }
                }
                break;
            case 7:
                int i10 = k.f3127a[i9 - 1];
                if (i10 == 1) {
                    cn.kuwo.autosdk.e eVar2 = (cn.kuwo.autosdk.e) obj;
                    ((s1.f) eVar2.f3121d).sendSyncNotice_LyricsStart(eVar2.f3122a);
                    break;
                } else if (i10 == 2) {
                    cn.kuwo.autosdk.e eVar3 = (cn.kuwo.autosdk.e) obj;
                    ((s1.f) eVar3.f3121d).sendSyncNotice_LyricsFinished(eVar3.f3122a, (String) obj2);
                    break;
                } else if (i10 == 3) {
                    cn.kuwo.autosdk.e eVar4 = (cn.kuwo.autosdk.e) obj;
                    ((s1.f) eVar4.f3121d).sendSyncNotice_LyricsFailed(eVar4.f3122a);
                    break;
                } else if (i10 == 4) {
                    cn.kuwo.autosdk.e eVar5 = (cn.kuwo.autosdk.e) obj;
                    ((s1.f) eVar5.f3121d).sendSyncNotice_LyricsNone(eVar5.f3122a);
                    break;
                }
                break;
            default:
                int i11 = BottomSheetBehavior.STATE_DRAGGING;
                ((BottomSheetBehavior) obj).L((View) obj2, i9, false);
                break;
        }
    }
}
