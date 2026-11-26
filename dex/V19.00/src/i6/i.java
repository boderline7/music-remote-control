package i6;

import android.R;
import android.app.ActionBar;
import android.app.Activity;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Messenger;
import android.text.method.KeyListener;
import android.text.method.NumberKeyListener;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputConnection;
import android.widget.EditText;
import android.widget.TextView;
import androidx.cardview.widget.CardView;
import androidx.viewpager.widget.ViewPager;
import b2.r;
import b2.u;
import b2.v;
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;
import com.google.android.material.internal.j0;
import com.yalantis.ucrop.view.CropImageView;
import java.util.ArrayList;
import java.util.List;
import n1.z;
import p0.b0;
import p0.d1;
import p0.l2;
import w1.t;
import y1.w;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final class i implements androidx.appcompat.app.d, b0, z2.i, g3.h {

    /* renamed from: a, reason: collision with root package name */
    public final /* synthetic */ int f6847a;

    /* renamed from: b, reason: collision with root package name */
    public Object f6848b;

    /* renamed from: c, reason: collision with root package name */
    public Object f6849c;

    public i() {
        this.f6847a = 14;
        this.f6848b = new Rect();
        this.f6849c = new Rect();
    }

    public /* synthetic */ i(int i7) {
        this.f6847a = i7;
    }

    public i(int i7, int i8) {
        this.f6847a = 6;
        this.f6848b = new int[]{i7, i8};
        this.f6849c = new float[]{CropImageView.DEFAULT_ASPECT_RATIO, 1.0f};
    }

    public i(int i7, int i8, int i9) {
        this.f6847a = 6;
        this.f6848b = new int[]{i7, i8, i9};
        this.f6849c = new float[]{CropImageView.DEFAULT_ASPECT_RATIO, 0.5f, 1.0f};
    }

    public /* synthetic */ i(int i7, Object obj, Object obj2) {
        this.f6847a = i7;
        this.f6848b = obj;
        this.f6849c = obj2;
    }

    public i(Activity activity) {
        this.f6847a = 2;
        this.f6848b = activity;
    }

    public i(IBinder iBinder, Bundle bundle) {
        this.f6847a = 1;
        this.f6848b = new Messenger(iBinder);
        this.f6849c = bundle;
    }

    public i(EditText editText) {
        this.f6847a = 3;
        this.f6848b = editText;
        this.f6849c = new a1.b(editText);
    }

    public i(TextView textView) {
        this.f6847a = 4;
        textView.getClass();
        this.f6848b = textView;
    }

    public i(CardView cardView) {
        this.f6847a = 5;
        this.f6849c = cardView;
    }

    public i(ViewPager viewPager) {
        this.f6847a = 9;
        this.f6849c = viewPager;
        this.f6848b = new Rect();
    }

    public /* synthetic */ i(Object obj, Object obj2, int i7) {
        this.f6847a = i7;
        this.f6849c = obj;
        this.f6848b = obj2;
    }

    public i(ArrayList arrayList, ArrayList arrayList2) {
        this.f6847a = 6;
        int size = arrayList.size();
        this.f6848b = new int[size];
        this.f6849c = new float[size];
        for (int i7 = 0; i7 < size; i7++) {
            ((int[]) this.f6848b)[i7] = ((Integer) arrayList.get(i7)).intValue();
            ((float[]) this.f6849c)[i7] = ((Float) arrayList2.get(i7)).floatValue();
        }
    }

    public i(v1.a aVar, int i7, int i8, int i9, int i10) {
        this.f6847a = 11;
        this.f6848b = new t(aVar, i7, i9, i10);
        this.f6849c = new ArrayList(i8);
    }

    public i(y1.f fVar, e2.c cVar) {
        this.f6847a = 13;
        if (fVar == null) {
            throw new NullPointerException("file == null");
        }
        this.f6848b = fVar;
        this.f6849c = cVar;
    }

    public i(y2.h hVar, y2.h hVar2) {
        this.f6847a = 15;
        if (hVar.f10150a > hVar2.f10150a) {
            throw new IllegalArgumentException();
        }
        this.f6848b = hVar;
        this.f6849c = hVar2;
    }

    public i(z3.a aVar) {
        this.f6847a = 21;
        this.f6848b = aVar;
        ArrayList arrayList = new ArrayList();
        this.f6849c = arrayList;
        arrayList.add(new z3.b(aVar, new int[]{1}));
    }

    public static void a(y1.f fVar, b2.a aVar) {
        if (!(aVar instanceof b2.c)) {
            fVar.a(aVar);
            return;
        }
        b2.b bVar = ((b2.c) aVar).f2930a;
        int length = bVar.f6354b.length;
        for (int i7 = 0; i7 < length; i7++) {
            a(fVar, (b2.a) bVar.f(i7));
        }
    }

    public static int c(b2.a aVar) {
        if (aVar instanceof b2.f) {
            return 0;
        }
        if (aVar instanceof b2.t) {
            return 2;
        }
        if (aVar instanceof b2.g) {
            return 3;
        }
        if (aVar instanceof b2.k) {
            return 4;
        }
        if (aVar instanceof b2.p) {
            return 6;
        }
        if (aVar instanceof b2.j) {
            return 16;
        }
        if (aVar instanceof b2.h) {
            return 17;
        }
        if (aVar instanceof u) {
            return 23;
        }
        if (aVar instanceof v) {
            return 24;
        }
        if (aVar instanceof b2.i) {
            return 25;
        }
        if (aVar instanceof r) {
            return 26;
        }
        if (aVar instanceof b2.c) {
            return 28;
        }
        if (aVar instanceof b2.l) {
            return 30;
        }
        if (aVar instanceof b2.e) {
            return 31;
        }
        throw new RuntimeException("Shouldn't happen");
    }

    @Override // androidx.appcompat.app.d
    public final Drawable B() {
        TypedArray obtainStyledAttributes = m().obtainStyledAttributes(null, new int[]{R.attr.homeAsUpIndicator}, R.attr.actionBarStyle, 0);
        Drawable drawable = obtainStyledAttributes.getDrawable(0);
        obtainStyledAttributes.recycle();
        return drawable;
    }

    @Override // androidx.appcompat.app.d
    public final void b(int i7) {
        ActionBar actionBar = ((Activity) this.f6848b).getActionBar();
        if (actionBar != null) {
            androidx.appcompat.app.f.a(actionBar, i7);
        }
    }

    public final void d(int i7, int[] iArr) {
        z3.b bVar;
        z3.b bVar2;
        z3.b bVar3;
        if (i7 == 0) {
            throw new IllegalArgumentException("No error correction bytes");
        }
        int length = iArr.length - i7;
        if (length <= 0) {
            throw new IllegalArgumentException("No data bytes provided");
        }
        int i8 = 0;
        int i9 = 1;
        if (i7 >= ((List) this.f6849c).size()) {
            List list = (List) this.f6849c;
            z3.b bVar4 = (z3.b) list.get(list.size() - 1);
            int size = ((List) this.f6849c).size();
            while (size <= i7) {
                z3.a aVar = (z3.a) this.f6848b;
                int[] iArr2 = {i9, aVar.f10288a[(size - 1) + aVar.f10293f]};
                if (iArr2[i8] == 0) {
                    int i10 = i9;
                    while (i10 < 2 && iArr2[i10] == 0) {
                        i10++;
                    }
                    if (i10 == 2) {
                        iArr2 = new int[]{i8};
                    } else {
                        int i11 = 2 - i10;
                        int[] iArr3 = new int[i11];
                        System.arraycopy(iArr2, i10, iArr3, i8, i11);
                        iArr2 = iArr3;
                    }
                }
                bVar4.getClass();
                z3.a aVar2 = bVar4.f10294a;
                if (!aVar2.equals(aVar)) {
                    throw new IllegalArgumentException("GenericGFPolys do not have same GenericGF field");
                }
                if (bVar4.c() || iArr2[i8] == 0) {
                    bVar4 = aVar2.f10290c;
                } else {
                    int[] iArr4 = bVar4.f10295b;
                    int length2 = iArr4.length;
                    int length3 = iArr2.length;
                    int[] iArr5 = new int[(length2 + length3) - i9];
                    int i12 = i8;
                    while (i12 < length2) {
                        int i13 = iArr4[i12];
                        for (int i14 = i8; i14 < length3; i14++) {
                            int i15 = i12 + i14;
                            iArr5[i15] = iArr5[i15] ^ aVar2.a(i13, iArr2[i14]);
                        }
                        i12++;
                        i8 = 0;
                    }
                    bVar4 = new z3.b(aVar2, iArr5);
                }
                ((List) this.f6849c).add(bVar4);
                size++;
                i8 = 0;
                i9 = 1;
            }
        }
        z3.b bVar5 = (z3.b) ((List) this.f6849c).get(i7);
        int[] iArr6 = new int[length];
        System.arraycopy(iArr, 0, iArr6, 0, length);
        z3.a aVar3 = (z3.a) this.f6848b;
        if (length == 0) {
            throw new IllegalArgumentException();
        }
        if (length > 1 && iArr6[0] == 0) {
            int i16 = 1;
            while (i16 < length && iArr6[i16] == 0) {
                i16++;
            }
            if (i16 == length) {
                iArr6 = new int[]{0};
            } else {
                int i17 = length - i16;
                int[] iArr7 = new int[i17];
                System.arraycopy(iArr6, i16, iArr7, 0, i17);
                iArr6 = iArr7;
            }
        }
        if (i7 < 0) {
            throw new IllegalArgumentException();
        }
        int length4 = iArr6.length;
        int[] iArr8 = new int[length4 + i7];
        for (int i18 = 0; i18 < length4; i18++) {
            iArr8[i18] = aVar3.a(iArr6[i18], 1);
        }
        z3.b bVar6 = new z3.b(aVar3, iArr8);
        if (!aVar3.equals(bVar5.f10294a)) {
            throw new IllegalArgumentException("GenericGFPolys do not have same GenericGF field");
        }
        if (bVar5.c()) {
            throw new IllegalArgumentException("Divide by 0");
        }
        int b7 = bVar5.b();
        int[] iArr9 = bVar5.f10295b;
        int i19 = iArr9[(iArr9.length - 1) - b7];
        if (i19 == 0) {
            throw new ArithmeticException();
        }
        int i20 = aVar3.f10288a[(aVar3.f10291d - aVar3.f10289b[i19]) - 1];
        z3.b bVar7 = aVar3.f10290c;
        z3.b bVar8 = bVar7;
        while (bVar6.b() >= bVar5.b() && !bVar6.c()) {
            int b8 = bVar6.b() - bVar5.b();
            int b9 = bVar6.b();
            int[] iArr10 = bVar6.f10295b;
            int a7 = aVar3.a(iArr10[(iArr10.length - 1) - b9], i20);
            if (b8 < 0) {
                throw new IllegalArgumentException();
            }
            z3.a aVar4 = bVar5.f10294a;
            if (a7 == 0) {
                bVar2 = aVar4.f10290c;
                bVar = bVar5;
            } else {
                int length5 = iArr9.length;
                int[] iArr11 = new int[length5 + b8];
                int i21 = 0;
                while (i21 < length5) {
                    iArr11[i21] = aVar4.a(iArr9[i21], a7);
                    i21++;
                    bVar5 = bVar5;
                }
                bVar = bVar5;
                bVar2 = new z3.b(aVar4, iArr11);
            }
            if (b8 < 0) {
                throw new IllegalArgumentException();
            }
            if (a7 == 0) {
                bVar3 = bVar7;
            } else {
                int[] iArr12 = new int[b8 + 1];
                iArr12[0] = a7;
                bVar3 = new z3.b(aVar3, iArr12);
            }
            bVar8 = bVar8.a(bVar3);
            bVar6 = bVar6.a(bVar2);
            bVar5 = bVar;
        }
        int[] iArr13 = new z3.b[]{bVar8, bVar6}[1].f10295b;
        int length6 = i7 - iArr13.length;
        for (int i22 = 0; i22 < length6; i22++) {
            iArr[length + i22] = 0;
        }
        System.arraycopy(iArr13, 0, iArr, length + length6, iArr13.length);
    }

    @Override // p0.b0
    public final l2 e(View view, l2 l2Var) {
        switch (this.f6847a) {
            case 9:
                l2 j7 = d1.j(view, l2Var);
                if (j7.f8161a.m()) {
                    return j7;
                }
                Rect rect = (Rect) this.f6848b;
                rect.left = j7.b();
                rect.top = j7.d();
                rect.right = j7.c();
                rect.bottom = j7.a();
                int childCount = ((ViewPager) this.f6849c).getChildCount();
                for (int i7 = 0; i7 < childCount; i7++) {
                    l2 b7 = d1.b(((ViewPager) this.f6849c).getChildAt(i7), j7);
                    rect.left = Math.min(b7.b(), rect.left);
                    rect.top = Math.min(b7.d(), rect.top);
                    rect.right = Math.min(b7.c(), rect.right);
                    rect.bottom = Math.min(b7.a(), rect.bottom);
                }
                return j7.f(rect.left, rect.top, rect.right, rect.bottom);
            default:
                j0 j0Var = (j0) this.f6848b;
                androidx.media.a aVar = (androidx.media.a) this.f6849c;
                androidx.media.a aVar2 = new androidx.media.a();
                aVar2.f2235a = aVar.f2235a;
                aVar2.f2236b = aVar.f2236b;
                aVar2.f2237c = aVar.f2237c;
                aVar2.f2238d = aVar.f2238d;
                return j0Var.d(view, l2Var, aVar2);
        }
    }

    public final synchronized void f(j jVar) {
        try {
            Object obj = this.f6849c;
            if (((j) obj) != null) {
                ((j) obj).f6853c = jVar;
                this.f6849c = jVar;
            } else {
                if (((j) this.f6848b) != null) {
                    throw new IllegalStateException("Head present, but no tail");
                }
                this.f6849c = jVar;
                this.f6848b = jVar;
            }
            notifyAll();
        } catch (Throwable th) {
            throw th;
        }
    }

    @Override // androidx.appcompat.app.d
    public final void g(Drawable drawable, int i7) {
        ActionBar actionBar = ((Activity) this.f6848b).getActionBar();
        if (actionBar != null) {
            androidx.appcompat.app.f.b(actionBar, drawable);
            androidx.appcompat.app.f.a(actionBar, i7);
        }
    }

    public final int h(b2.a aVar) {
        y1.o oVar;
        y1.f fVar = (y1.f) this.f6848b;
        fVar.getClass();
        if (aVar instanceof u) {
            oVar = fVar.f10077f.l(aVar);
        } else if (aVar instanceof v) {
            oVar = fVar.f10078g.l(aVar);
        } else if (aVar instanceof b2.d) {
            w wVar = fVar.f10081j;
            if (aVar == null) {
                wVar.getClass();
                throw new NullPointerException("cst == null");
            }
            wVar.f();
            oVar = (y1.o) wVar.f10113f.get((b2.d) aVar);
            if (oVar == null) {
                throw new IllegalArgumentException("not found");
            }
        } else if (aVar instanceof b2.i) {
            y1.l lVar = fVar.f10080i;
            if (aVar == null) {
                lVar.getClass();
                throw new NullPointerException("cst == null");
            }
            lVar.f();
            oVar = (y1.o) lVar.f10099f.get((b2.i) aVar);
            if (oVar == null) {
                throw new IllegalArgumentException("not found");
            }
        } else {
            oVar = null;
        }
        if (oVar == null) {
            return -1;
        }
        return oVar.e();
    }

    public final KeyListener i(KeyListener keyListener) {
        return (keyListener instanceof NumberKeyListener) ^ true ? ((p3.f) ((a1.b) this.f6849c).f200d).s(keyListener) : keyListener;
    }

    public final void j(AttributeSet attributeSet, int i7) {
        TypedArray obtainStyledAttributes = ((EditText) this.f6848b).getContext().obtainStyledAttributes(attributeSet, f.j.AppCompatTextView, i7, 0);
        try {
            boolean z6 = obtainStyledAttributes.hasValue(f.j.AppCompatTextView_emojiCompatEnabled) ? obtainStyledAttributes.getBoolean(f.j.AppCompatTextView_emojiCompatEnabled, true) : true;
            obtainStyledAttributes.recycle();
            ((p3.f) ((a1.b) this.f6849c).f200d).G(z6);
        } catch (Throwable th) {
            obtainStyledAttributes.recycle();
            throw th;
        }
    }

    @Override // g3.h
    public final int k() {
        ViewGroup.MarginLayoutParams marginLayoutParams;
        if (!(((ExtendedFloatingActionButton) this.f6849c).getParent() instanceof View)) {
            return ((g3.h) this.f6848b).k();
        }
        View view = (View) ((ExtendedFloatingActionButton) this.f6849c).getParent();
        ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
        if (layoutParams == null || layoutParams.width != -2) {
            return (view.getWidth() - ((!(((ExtendedFloatingActionButton) this.f6849c).getLayoutParams() instanceof ViewGroup.MarginLayoutParams) || (marginLayoutParams = (ViewGroup.MarginLayoutParams) ((ExtendedFloatingActionButton) this.f6849c).getLayoutParams()) == null) ? 0 : marginLayoutParams.leftMargin + marginLayoutParams.rightMargin)) - (view.getPaddingRight() + view.getPaddingLeft());
        }
        return ((g3.h) this.f6848b).k();
    }

    @Override // g3.h
    public final int l() {
        ViewGroup.MarginLayoutParams marginLayoutParams;
        Object obj = this.f6849c;
        if (((ExtendedFloatingActionButton) obj).I != -1) {
            return (((ExtendedFloatingActionButton) obj).I == 0 || ((ExtendedFloatingActionButton) obj).I == -2) ? ((g3.h) this.f6848b).l() : ((ExtendedFloatingActionButton) obj).I;
        }
        if (!(((ExtendedFloatingActionButton) obj).getParent() instanceof View)) {
            return ((g3.h) this.f6848b).l();
        }
        View view = (View) ((ExtendedFloatingActionButton) this.f6849c).getParent();
        ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
        if (layoutParams == null || layoutParams.height != -2) {
            return (view.getHeight() - ((!(((ExtendedFloatingActionButton) this.f6849c).getLayoutParams() instanceof ViewGroup.MarginLayoutParams) || (marginLayoutParams = (ViewGroup.MarginLayoutParams) ((ExtendedFloatingActionButton) this.f6849c).getLayoutParams()) == null) ? 0 : marginLayoutParams.topMargin + marginLayoutParams.bottomMargin)) - (view.getPaddingBottom() + view.getPaddingTop());
        }
        return ((g3.h) this.f6848b).l();
    }

    @Override // androidx.appcompat.app.d
    public final Context m() {
        ActionBar actionBar = ((Activity) this.f6848b).getActionBar();
        return actionBar != null ? actionBar.getThemedContext() : (Activity) this.f6848b;
    }

    @Override // g3.h
    public final int n() {
        return ((ExtendedFloatingActionButton) this.f6849c).B;
    }

    @Override // androidx.appcompat.app.d
    public final boolean o() {
        ActionBar actionBar = ((Activity) this.f6848b).getActionBar();
        return (actionBar == null || (actionBar.getDisplayOptions() & 4) == 0) ? false : true;
    }

    @Override // g3.h
    public final int p() {
        return ((ExtendedFloatingActionButton) this.f6849c).A;
    }

    public final InputConnection q(InputConnection inputConnection, EditorInfo editorInfo) {
        a1.b bVar = (a1.b) this.f6849c;
        if (inputConnection != null) {
            return ((p3.f) bVar.f200d).B(inputConnection, editorInfo);
        }
        bVar.getClass();
        return null;
    }

    public final void r(m0.f fVar) {
        int i7 = fVar.f7496b;
        if (i7 != 0) {
            ((Handler) this.f6849c).post(new b.d(this, (m0.j) this.f6848b, i7, 4));
        } else {
            m0.j jVar = (m0.j) this.f6848b;
            ((Handler) this.f6849c).post(new android.support.v4.media.h(this, jVar, fVar.f7495a, 1));
        }
    }

    @Override // g3.h
    public final ViewGroup.LayoutParams s() {
        Object obj = this.f6849c;
        return new ViewGroup.LayoutParams(-1, ((ExtendedFloatingActionButton) obj).I == 0 ? -2 : ((ExtendedFloatingActionButton) obj).I);
    }

    public final synchronized j t() {
        j jVar;
        Object obj = this.f6848b;
        jVar = (j) obj;
        if (((j) obj) != null) {
            j jVar2 = ((j) obj).f6853c;
            this.f6848b = jVar2;
            if (jVar2 == null) {
                this.f6849c = null;
            }
        }
        return jVar;
    }

    public final String toString() {
        switch (this.f6847a) {
            case 8:
                return "Bounds{lower=" + ((i0.c) this.f6848b) + " upper=" + ((i0.c) this.f6849c) + "}";
            default:
                return super.toString();
        }
    }

    public final synchronized j u() {
        try {
            if (((j) this.f6848b) == null) {
                wait(1000);
            }
        } catch (Throwable th) {
            throw th;
        }
        return t();
    }

    public final void v(int i7, int i8, int i9, int i10) {
        ((CardView) this.f6849c).f1501d.set(i7, i8, i9, i10);
        Object obj = this.f6849c;
        super/*android.view.View*/.setPadding(i7 + ((CardView) obj).f1500c.left, i8 + ((CardView) obj).f1500c.top, i9 + ((CardView) obj).f1500c.right, i10 + ((CardView) obj).f1500c.bottom);
    }

    public final void w(b2.c cVar, boolean z6) {
        boolean z7 = z6 && ((e2.c) ((e2.a) this.f6849c)).d();
        b2.b bVar = cVar.f2930a;
        int length = bVar.f6354b.length;
        if (z7) {
            ((e2.c) ((e2.a) this.f6849c)).c("  size: ".concat(z.m1(length)));
        }
        ((e2.c) ((e2.a) this.f6849c)).m(length);
        for (int i7 = 0; i7 < length; i7++) {
            b2.a aVar = (b2.a) bVar.f(i7);
            if (z7) {
                e2.a aVar2 = (e2.a) this.f6849c;
                StringBuilder sb = new StringBuilder("  [");
                sb.append(Integer.toHexString(i7));
                sb.append("] ");
                sb.append(c(aVar) == 30 ? "null" : aVar.f() + ' ' + aVar.d());
                ((e2.c) aVar2).c(sb.toString());
            }
            int c7 = c(aVar);
            if (c7 != 0 && c7 != 6 && c7 != 2) {
                if (c7 == 3) {
                    z.w1((e2.a) this.f6849c, c7, ((b2.o) aVar).i());
                } else if (c7 != 4) {
                    if (c7 == 16) {
                        z.s1((e2.a) this.f6849c, c7, ((b2.j) aVar).f2934a << 32);
                    } else if (c7 != 17) {
                        switch (c7) {
                            case 21:
                                y1.u uVar = ((y1.f) this.f6848b).f10079h;
                                a.a.C(aVar);
                                throw null;
                            case 22:
                                y1.u uVar2 = ((y1.f) this.f6848b).f10085n;
                                a.a.C(aVar);
                                throw null;
                            case 23:
                                z.w1((e2.a) this.f6849c, c7, ((y1.f) this.f6848b).f10077f.m((u) aVar));
                                break;
                            case 24:
                                z.w1((e2.a) this.f6849c, c7, ((y1.f) this.f6848b).f10078g.n((v) aVar));
                                break;
                            case 25:
                                z.w1((e2.a) this.f6849c, c7, ((y1.f) this.f6848b).f10080i.l((b2.i) aVar));
                                break;
                            case 26:
                                z.w1((e2.a) this.f6849c, c7, ((y1.f) this.f6848b).f10081j.l((r) aVar));
                                break;
                            case 27:
                                a.a.C(aVar);
                                throw null;
                            case 28:
                                ((e2.c) ((e2.a) this.f6849c)).i(c7);
                                w((b2.c) aVar, false);
                                break;
                            case 29:
                                ((e2.c) ((e2.a) this.f6849c)).i(c7);
                                a.a.C(aVar);
                                throw null;
                            case 30:
                                ((e2.c) ((e2.a) this.f6849c)).i(c7);
                                break;
                            case 31:
                                ((e2.c) ((e2.a) this.f6849c)).i((((b2.e) aVar).f2934a << 5) | c7);
                                break;
                            default:
                                throw new RuntimeException("Shouldn't happen");
                        }
                    } else {
                        z.s1((e2.a) this.f6849c, c7, ((b2.h) aVar).f2935a);
                    }
                }
            }
            long i8 = ((b2.o) aVar).i();
            e2.a aVar3 = (e2.a) this.f6849c;
            int numberOfLeadingZeros = (72 - Long.numberOfLeadingZeros((i8 >> 63) ^ i8)) >> 3;
            e2.c cVar2 = (e2.c) aVar3;
            cVar2.i(c7 | ((numberOfLeadingZeros - 1) << 5));
            while (numberOfLeadingZeros > 0) {
                cVar2.i((byte) i8);
                i8 >>= 8;
                numberOfLeadingZeros--;
            }
        }
        if (z7) {
            ((e2.c) ((e2.a) this.f6849c)).e();
        }
    }
}
