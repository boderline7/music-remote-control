package androidx.appcompat.widget;

import android.database.DataSetObserver;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_2_1633180.dex */
public final class s extends DataSetObserver {

    /* renamed from: a, reason: collision with root package name */
    public final /* synthetic */ int f1392a;

    /* renamed from: b, reason: collision with root package name */
    public final /* synthetic */ ActivityChooserView f1393b;

    public /* synthetic */ s(ActivityChooserView activityChooserView, int i7) {
        this.f1392a = i7;
        this.f1393b = activityChooserView;
    }

    @Override // android.database.DataSetObserver
    public final void onChanged() {
        int i7 = this.f1392a;
        ActivityChooserView activityChooserView = this.f1393b;
        switch (i7) {
            case 0:
                super.onChanged();
                activityChooserView.f992a.notifyDataSetChanged();
                return;
            default:
                super.onChanged();
                activityChooserView.f992a.getClass();
                throw null;
        }
    }

    @Override // android.database.DataSetObserver
    public final void onInvalidated() {
        switch (this.f1392a) {
            case 0:
                super.onInvalidated();
                this.f1393b.f992a.notifyDataSetInvalidated();
                break;
            default:
                super.onInvalidated();
                break;
        }
    }
}
