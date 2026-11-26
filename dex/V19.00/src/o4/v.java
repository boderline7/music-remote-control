package o4;

import com.navi.tool.carinfo.TirePollingService;
import com.navi.tool.ui.extra.PermissionManagerActivity;
import com.navi.tool.ui.extra.t1;
import java.util.concurrent.atomic.AtomicBoolean;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final /* synthetic */ class v implements Runnable {

    /* renamed from: a, reason: collision with root package name */
    public final /* synthetic */ int f8036a;

    /* renamed from: b, reason: collision with root package name */
    public final /* synthetic */ boolean f8037b;

    /* renamed from: c, reason: collision with root package name */
    public final /* synthetic */ boolean f8038c;

    /* renamed from: d, reason: collision with root package name */
    public final /* synthetic */ Object f8039d;

    /* renamed from: e, reason: collision with root package name */
    public final /* synthetic */ Object f8040e;

    public /* synthetic */ v(Object obj, boolean z6, boolean z7, Object obj2, int i7) {
        this.f8036a = i7;
        this.f8039d = obj;
        this.f8037b = z6;
        this.f8038c = z7;
        this.f8040e = obj2;
    }

    @Override // java.lang.Runnable
    public final void run() {
        int i7 = this.f8036a;
        boolean z6 = this.f8038c;
        boolean z7 = this.f8037b;
        Object obj = this.f8040e;
        Object obj2 = this.f8039d;
        switch (i7) {
            case 0:
                AtomicBoolean atomicBoolean = TirePollingService.f4725g;
                ((t1) obj2).f5463b.lambda$refreshTireManually$47(z7, z6, (String) obj);
                break;
            default:
                ((PermissionManagerActivity) obj2).lambda$showRemoveOwnerDialog$2(z7, z6, (androidx.appcompat.app.o) obj);
                break;
        }
    }
}
