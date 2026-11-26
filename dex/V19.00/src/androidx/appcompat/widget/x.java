package androidx.appcompat.widget;

import android.content.res.ColorStateList;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.widget.CheckedTextView;
import android.widget.CompoundButton;
import android.widget.TextView;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_2_1633180.dex */
public final class x {

    /* renamed from: a, reason: collision with root package name */
    public final /* synthetic */ int f1458a;

    /* renamed from: b, reason: collision with root package name */
    public ColorStateList f1459b = null;

    /* renamed from: c, reason: collision with root package name */
    public PorterDuff.Mode f1460c = null;

    /* renamed from: d, reason: collision with root package name */
    public boolean f1461d = false;

    /* renamed from: e, reason: collision with root package name */
    public boolean f1462e = false;

    /* renamed from: f, reason: collision with root package name */
    public boolean f1463f;

    /* renamed from: g, reason: collision with root package name */
    public final TextView f1464g;

    public /* synthetic */ x(TextView textView, int i7) {
        this.f1458a = i7;
        this.f1464g = textView;
    }

    public final void a() {
        TextView textView = this.f1464g;
        Drawable a7 = androidx.core.widget.d.a((CompoundButton) textView);
        if (a7 != null) {
            if (this.f1461d || this.f1462e) {
                Drawable mutate = a7.mutate();
                if (this.f1461d) {
                    j0.b.h(mutate, this.f1459b);
                }
                if (this.f1462e) {
                    j0.b.i(mutate, this.f1460c);
                }
                if (mutate.isStateful()) {
                    mutate.setState(((CompoundButton) textView).getDrawableState());
                }
                ((CompoundButton) textView).setButtonDrawable(mutate);
            }
        }
    }

    public final void b() {
        TextView textView = this.f1464g;
        Drawable checkMarkDrawable = ((CheckedTextView) textView).getCheckMarkDrawable();
        if (checkMarkDrawable != null) {
            if (this.f1461d || this.f1462e) {
                Drawable mutate = checkMarkDrawable.mutate();
                if (this.f1461d) {
                    j0.b.h(mutate, this.f1459b);
                }
                if (this.f1462e) {
                    j0.b.i(mutate, this.f1460c);
                }
                if (mutate.isStateful()) {
                    mutate.setState(((CheckedTextView) textView).getDrawableState());
                }
                ((CheckedTextView) textView).setCheckMarkDrawable(mutate);
            }
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:13:0x0074 A[Catch: all -> 0x0049, TryCatch #1 {all -> 0x0049, blocks: (B:5:0x0027, B:7:0x002f, B:10:0x0037, B:11:0x006c, B:13:0x0074, B:14:0x0080, B:16:0x0088, B:23:0x004b, B:25:0x0053, B:27:0x005b), top: B:4:0x0027 }] */
    /* JADX WARN: Removed duplicated region for block: B:16:0x0088 A[Catch: all -> 0x0049, TRY_LEAVE, TryCatch #1 {all -> 0x0049, blocks: (B:5:0x0027, B:7:0x002f, B:10:0x0037, B:11:0x006c, B:13:0x0074, B:14:0x0080, B:16:0x0088, B:23:0x004b, B:25:0x0053, B:27:0x005b), top: B:4:0x0027 }] */
    /* JADX WARN: Removed duplicated region for block: B:41:0x0109 A[Catch: all -> 0x00de, TryCatch #3 {all -> 0x00de, blocks: (B:33:0x00bc, B:35:0x00c4, B:38:0x00cc, B:39:0x0101, B:41:0x0109, B:42:0x0115, B:44:0x011d, B:50:0x00e0, B:52:0x00e8, B:54:0x00f0), top: B:32:0x00bc }] */
    /* JADX WARN: Removed duplicated region for block: B:44:0x011d A[Catch: all -> 0x00de, TRY_LEAVE, TryCatch #3 {all -> 0x00de, blocks: (B:33:0x00bc, B:35:0x00c4, B:38:0x00cc, B:39:0x0101, B:41:0x0109, B:42:0x0115, B:44:0x011d, B:50:0x00e0, B:52:0x00e8, B:54:0x00f0), top: B:32:0x00bc }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void c(android.util.AttributeSet r12, int r13) {
        /*
            Method dump skipped, instructions count: 314
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.appcompat.widget.x.c(android.util.AttributeSet, int):void");
    }
}
