package q0;

import android.os.Bundle;
import android.text.style.ClickableSpan;
import android.view.View;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final class a extends ClickableSpan {
    public static final String SPAN_ID = "ACCESSIBILITY_CLICKABLE_SPAN_ID";

    /* renamed from: a, reason: collision with root package name */
    public final int f8417a;

    /* renamed from: b, reason: collision with root package name */
    public final i f8418b;

    /* renamed from: c, reason: collision with root package name */
    public final int f8419c;

    public a(int i7, i iVar, int i8) {
        this.f8417a = i7;
        this.f8418b = iVar;
        this.f8419c = i8;
    }

    @Override // android.text.style.ClickableSpan
    public final void onClick(View view) {
        Bundle bundle = new Bundle();
        bundle.putInt(SPAN_ID, this.f8417a);
        this.f8418b.f8426a.performAction(this.f8419c, bundle);
    }
}
