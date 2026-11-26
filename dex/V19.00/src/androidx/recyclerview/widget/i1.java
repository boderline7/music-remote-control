package androidx.recyclerview.widget;

import android.database.Observable;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_2_1633180.dex */
public final class i1 extends Observable {
    public final boolean a() {
        return !((Observable) this).mObservers.isEmpty();
    }

    public final void b() {
        for (int size = ((Observable) this).mObservers.size() - 1; size >= 0; size--) {
            ((j1) ((Observable) this).mObservers.get(size)).a();
        }
    }

    public final void c(int i7, int i8) {
        for (int size = ((Observable) this).mObservers.size() - 1; size >= 0; size--) {
            ((j1) ((Observable) this).mObservers.get(size)).d(i7, i8);
        }
    }

    public final void d(int i7, int i8, Object obj) {
        for (int size = ((Observable) this).mObservers.size() - 1; size >= 0; size--) {
            ((j1) ((Observable) this).mObservers.get(size)).b(i7, i8, obj);
        }
    }

    public final void e(int i7, int i8) {
        for (int size = ((Observable) this).mObservers.size() - 1; size >= 0; size--) {
            ((j1) ((Observable) this).mObservers.get(size)).c(i7, i8);
        }
    }

    public final void f(int i7, int i8) {
        for (int size = ((Observable) this).mObservers.size() - 1; size >= 0; size--) {
            ((j1) ((Observable) this).mObservers.get(size)).e(i7, i8);
        }
    }
}
