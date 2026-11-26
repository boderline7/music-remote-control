package m0;

import android.content.Context;
import androidx.appcompat.widget.w;
import java.util.concurrent.Callable;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final class d implements Callable {

    /* renamed from: a, reason: collision with root package name */
    public final /* synthetic */ int f7488a;

    /* renamed from: b, reason: collision with root package name */
    public final /* synthetic */ String f7489b;

    /* renamed from: c, reason: collision with root package name */
    public final /* synthetic */ Context f7490c;

    /* renamed from: d, reason: collision with root package name */
    public final /* synthetic */ w f7491d;

    /* renamed from: e, reason: collision with root package name */
    public final /* synthetic */ int f7492e;

    public /* synthetic */ d(String str, Context context, w wVar, int i7, int i8) {
        this.f7488a = i8;
        this.f7489b = str;
        this.f7490c = context;
        this.f7491d = wVar;
        this.f7492e = i7;
    }

    @Override // java.util.concurrent.Callable
    public final Object call() {
        String str = this.f7489b;
        Context context = this.f7490c;
        w wVar = this.f7491d;
        int i7 = this.f7492e;
        int i8 = this.f7488a;
        switch (i8) {
            case 0:
                switch (i8) {
                }
            default:
                switch (i8) {
                }
        }
        return g.a(str, context, wVar, i7);
    }
}
