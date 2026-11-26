package androidx.viewpager2.adapter;

import androidx.viewpager2.widget.j;
import com.navi.tool.MainActivity;
import java.util.ArrayList;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.List;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_2_1633180.dex */
public final class d extends j {

    /* renamed from: a, reason: collision with root package name */
    public final /* synthetic */ int f2858a;

    /* renamed from: b, reason: collision with root package name */
    public final Object f2859b;

    public d() {
        this.f2858a = 1;
        this.f2859b = new ArrayList(3);
    }

    public /* synthetic */ d(int i7, Object obj) {
        this.f2858a = i7;
        this.f2859b = obj;
    }

    @Override // androidx.viewpager2.widget.j
    public final void onPageScrollStateChanged(int i7) {
        int i8 = this.f2858a;
        Object obj = this.f2859b;
        switch (i8) {
            case 0:
                ((f) obj).b(false);
                return;
            case 1:
                try {
                    Iterator it = ((List) obj).iterator();
                    while (it.hasNext()) {
                        ((j) it.next()).onPageScrollStateChanged(i7);
                    }
                    return;
                } catch (ConcurrentModificationException e7) {
                    throw new IllegalStateException("Adding and removing callbacks during dispatch to callbacks is not supported", e7);
                }
            default:
                super.onPageScrollStateChanged(i7);
                return;
        }
    }

    @Override // androidx.viewpager2.widget.j
    public final void onPageScrolled(int i7, float f7, int i8) {
        switch (this.f2858a) {
            case 1:
                try {
                    Iterator it = ((List) this.f2859b).iterator();
                    while (it.hasNext()) {
                        ((j) it.next()).onPageScrolled(i7, f7, i8);
                    }
                    return;
                } catch (ConcurrentModificationException e7) {
                    throw new IllegalStateException("Adding and removing callbacks during dispatch to callbacks is not supported", e7);
                }
            default:
                super.onPageScrolled(i7, f7, i8);
                return;
        }
    }

    @Override // androidx.viewpager2.widget.j
    public final void onPageSelected(int i7) {
        int i8 = this.f2858a;
        Object obj = this.f2859b;
        switch (i8) {
            case 0:
                ((f) obj).b(false);
                return;
            case 1:
                try {
                    Iterator it = ((List) obj).iterator();
                    while (it.hasNext()) {
                        ((j) it.next()).onPageSelected(i7);
                    }
                    return;
                } catch (ConcurrentModificationException e7) {
                    throw new IllegalStateException("Adding and removing callbacks during dispatch to callbacks is not supported", e7);
                }
            default:
                ((MainActivity) obj).updateTtsTestButtonVisibility();
                return;
        }
    }
}
