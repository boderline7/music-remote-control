package p0;

import android.view.View;
import android.view.ViewParent;
import java.util.Objects;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final class x {

    /* renamed from: a, reason: collision with root package name */
    public ViewParent f8198a;

    /* renamed from: b, reason: collision with root package name */
    public ViewParent f8199b;

    /* renamed from: c, reason: collision with root package name */
    public final View f8200c;

    /* renamed from: d, reason: collision with root package name */
    public boolean f8201d;

    /* renamed from: e, reason: collision with root package name */
    public int[] f8202e;

    public x(View view) {
        this.f8200c = view;
    }

    public final boolean a(float f7, float f8, boolean z6) {
        ViewParent f9;
        if (!this.f8201d || (f9 = f(0)) == null) {
            return false;
        }
        try {
            return h1.a(f9, this.f8200c, f7, f8, z6);
        } catch (AbstractMethodError unused) {
            Objects.toString(f9);
            return false;
        }
    }

    public final boolean b(float f7, float f8) {
        ViewParent f9;
        if (!this.f8201d || (f9 = f(0)) == null) {
            return false;
        }
        try {
            return h1.b(f9, this.f8200c, f7, f8);
        } catch (AbstractMethodError unused) {
            Objects.toString(f9);
            return false;
        }
    }

    public final boolean c(int i7, int i8, int[] iArr, int[] iArr2, int i9) {
        ViewParent f7;
        int i10;
        int i11;
        int[] iArr3;
        if (!this.f8201d || (f7 = f(i9)) == null) {
            return false;
        }
        if (i7 == 0 && i8 == 0) {
            if (iArr2 == null) {
                return false;
            }
            iArr2[0] = 0;
            iArr2[1] = 0;
            return false;
        }
        View view = this.f8200c;
        if (iArr2 != null) {
            view.getLocationInWindow(iArr2);
            i10 = iArr2[0];
            i11 = iArr2[1];
        } else {
            i10 = 0;
            i11 = 0;
        }
        if (iArr == null) {
            if (this.f8202e == null) {
                this.f8202e = new int[2];
            }
            iArr3 = this.f8202e;
        } else {
            iArr3 = iArr;
        }
        iArr3[0] = 0;
        iArr3[1] = 0;
        View view2 = this.f8200c;
        if (f7 instanceof y) {
            ((y) f7).d(view2, i7, i8, iArr3, i9);
        } else if (i9 == 0) {
            try {
                h1.c(f7, view2, i7, i8, iArr3);
            } catch (AbstractMethodError unused) {
                Objects.toString(f7);
            }
        }
        if (iArr2 != null) {
            view.getLocationInWindow(iArr2);
            iArr2[0] = iArr2[0] - i10;
            iArr2[1] = iArr2[1] - i11;
        }
        return (iArr3[0] == 0 && iArr3[1] == 0) ? false : true;
    }

    public final void d(int i7, int i8, int i9, int i10, int[] iArr, int i11, int[] iArr2) {
        e(i7, i8, i9, i10, iArr, i11, iArr2);
    }

    public final boolean e(int i7, int i8, int i9, int i10, int[] iArr, int i11, int[] iArr2) {
        ViewParent f7;
        int i12;
        int i13;
        int[] iArr3;
        if (!this.f8201d || (f7 = f(i11)) == null) {
            return false;
        }
        if (i7 == 0 && i8 == 0 && i9 == 0 && i10 == 0) {
            if (iArr != null) {
                iArr[0] = 0;
                iArr[1] = 0;
            }
            return false;
        }
        View view = this.f8200c;
        if (iArr != null) {
            view.getLocationInWindow(iArr);
            i12 = iArr[0];
            i13 = iArr[1];
        } else {
            i12 = 0;
            i13 = 0;
        }
        if (iArr2 == null) {
            if (this.f8202e == null) {
                this.f8202e = new int[2];
            }
            int[] iArr4 = this.f8202e;
            iArr4[0] = 0;
            iArr4[1] = 0;
            iArr3 = iArr4;
        } else {
            iArr3 = iArr2;
        }
        View view2 = this.f8200c;
        if (f7 instanceof z) {
            ((z) f7).f(view2, i7, i8, i9, i10, i11, iArr3);
        } else {
            iArr3[0] = iArr3[0] + i9;
            iArr3[1] = iArr3[1] + i10;
            if (f7 instanceof y) {
                ((y) f7).i(view2, i7, i8, i9, i10, i11);
            } else if (i11 == 0) {
                try {
                    h1.d(f7, view2, i7, i8, i9, i10);
                } catch (AbstractMethodError unused) {
                    Objects.toString(f7);
                }
            }
        }
        if (iArr != null) {
            view.getLocationInWindow(iArr);
            iArr[0] = iArr[0] - i12;
            iArr[1] = iArr[1] - i13;
        }
        return true;
    }

    public final ViewParent f(int i7) {
        if (i7 == 0) {
            return this.f8198a;
        }
        if (i7 != 1) {
            return null;
        }
        return this.f8199b;
    }

    public final boolean g(int i7) {
        return f(i7) != null;
    }

    /* JADX WARN: Removed duplicated region for block: B:15:0x004c  */
    /* JADX WARN: Removed duplicated region for block: B:18:0x004f A[SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final boolean h(int r7, int r8) {
        /*
            r6 = this;
            boolean r0 = r6.g(r8)
            r1 = 1
            if (r0 == 0) goto L8
            return r1
        L8:
            boolean r0 = r6.f8201d
            if (r0 == 0) goto L54
            android.view.View r0 = r6.f8200c
            android.view.ViewParent r2 = r0.getParent()
            r3 = r0
        L13:
            if (r2 == 0) goto L54
            boolean r4 = r2 instanceof p0.y
            if (r4 == 0) goto L21
            r5 = r2
            p0.y r5 = (p0.y) r5
            boolean r5 = r5.j(r3, r0, r7, r8)
            goto L27
        L21:
            if (r8 != 0) goto L48
            boolean r5 = p0.h1.f(r2, r3, r0, r7)     // Catch: java.lang.AbstractMethodError -> L45
        L27:
            if (r5 == 0) goto L48
            if (r8 == 0) goto L31
            if (r8 == r1) goto L2e
            goto L33
        L2e:
            r6.f8199b = r2
            goto L33
        L31:
            r6.f8198a = r2
        L33:
            if (r4 == 0) goto L3b
            p0.y r2 = (p0.y) r2
            r2.b(r3, r0, r7, r8)
            goto L44
        L3b:
            if (r8 != 0) goto L44
            p0.h1.e(r2, r3, r0, r7)     // Catch: java.lang.AbstractMethodError -> L41
            goto L44
        L41:
            java.util.Objects.toString(r2)
        L44:
            return r1
        L45:
            java.util.Objects.toString(r2)
        L48:
            boolean r4 = r2 instanceof android.view.View
            if (r4 == 0) goto L4f
            r3 = r2
            android.view.View r3 = (android.view.View) r3
        L4f:
            android.view.ViewParent r2 = r2.getParent()
            goto L13
        L54:
            r7 = 0
            return r7
        */
        throw new UnsupportedOperationException("Method not decompiled: p0.x.h(int, int):boolean");
    }

    public final void i(int i7) {
        ViewParent f7 = f(i7);
        if (f7 != null) {
            boolean z6 = f7 instanceof y;
            View view = this.f8200c;
            if (z6) {
                ((y) f7).e(i7, view);
            } else if (i7 == 0) {
                try {
                    h1.g(f7, view);
                } catch (AbstractMethodError unused) {
                    Objects.toString(f7);
                }
            }
            if (i7 == 0) {
                this.f8198a = null;
            } else {
                if (i7 != 1) {
                    return;
                }
                this.f8199b = null;
            }
        }
    }
}
