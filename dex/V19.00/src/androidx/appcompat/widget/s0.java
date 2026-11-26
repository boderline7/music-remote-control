package androidx.appcompat.widget;

import android.graphics.Typeface;
import android.widget.TextView;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_2_1633180.dex */
public final class s0 implements Runnable {

    /* renamed from: a, reason: collision with root package name */
    public final /* synthetic */ TextView f1394a;

    /* renamed from: b, reason: collision with root package name */
    public final /* synthetic */ Typeface f1395b;

    /* renamed from: c, reason: collision with root package name */
    public final /* synthetic */ int f1396c;

    public s0(TextView textView, Typeface typeface, int i7) {
        this.f1394a = textView;
        this.f1395b = typeface;
        this.f1396c = i7;
    }

    @Override // java.lang.Runnable
    public final void run() {
        this.f1394a.setTypeface(this.f1395b, this.f1396c);
    }
}
