package androidx.fragment.app;

import android.app.Activity;
import android.content.Context;
import android.os.Handler;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_2_1633180.dex */
public abstract class l0 extends j0 {

    /* renamed from: a, reason: collision with root package name */
    public final Activity f2007a;

    /* renamed from: b, reason: collision with root package name */
    public final Context f2008b;

    /* renamed from: c, reason: collision with root package name */
    public final Handler f2009c;

    /* renamed from: d, reason: collision with root package name */
    public final y0 f2010d;

    public l0(androidx.appcompat.app.r rVar) {
        Handler handler = new Handler();
        this.f2010d = new y0();
        this.f2007a = rVar;
        this.f2008b = rVar;
        this.f2009c = handler;
    }
}
