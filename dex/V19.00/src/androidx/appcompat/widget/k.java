package androidx.appcompat.widget;

import android.content.Context;
import android.graphics.drawable.Drawable;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_2_1633180.dex */
public final class k extends AppCompatImageView implements n {

    /* renamed from: d, reason: collision with root package name */
    public final /* synthetic */ m f1293d;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public k(m mVar, Context context) {
        super(context, null, f.a.actionOverflowButtonStyle);
        this.f1293d = mVar;
        setClickable(true);
        setFocusable(true);
        setVisibility(0);
        setEnabled(true);
        x3.a(this, getContentDescription());
        setOnTouchListener(new j(this, this, mVar, 0));
    }

    @Override // androidx.appcompat.widget.n
    public final boolean a() {
        return false;
    }

    @Override // androidx.appcompat.widget.n
    public final boolean b() {
        return false;
    }

    @Override // android.view.View
    public final boolean performClick() {
        if (super.performClick()) {
            return true;
        }
        playSoundEffect(0);
        this.f1293d.n();
        return true;
    }

    @Override // android.widget.ImageView
    public final boolean setFrame(int i7, int i8, int i9, int i10) {
        boolean frame = super.setFrame(i7, i8, i9, i10);
        Drawable drawable = getDrawable();
        Drawable background = getBackground();
        if (drawable != null && background != null) {
            int width = getWidth();
            int height = getHeight();
            int max = Math.max(width, height) / 2;
            int paddingLeft = (width + (getPaddingLeft() - getPaddingRight())) / 2;
            int paddingTop = (height + (getPaddingTop() - getPaddingBottom())) / 2;
            j0.b.f(background, paddingLeft - max, paddingTop - max, paddingLeft + max, paddingTop + max);
        }
        return frame;
    }
}
