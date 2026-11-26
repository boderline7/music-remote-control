package androidx.appcompat.widget;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_2_1633180.dex */
public final class z0 extends e.a {

    /* renamed from: c, reason: collision with root package name */
    public final /* synthetic */ AppCompatTextView f1495c;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public z0(AppCompatTextView appCompatTextView) {
        super(7, appCompatTextView);
        this.f1495c = appCompatTextView;
    }

    @Override // e.a, androidx.appcompat.widget.y0
    public final void o(int i7) {
        super/*android.widget.TextView*/.setLastBaselineToBottomHeight(i7);
    }

    @Override // e.a, androidx.appcompat.widget.y0
    public final void p(int i7) {
        super/*android.widget.TextView*/.setFirstBaselineToTopHeight(i7);
    }
}
