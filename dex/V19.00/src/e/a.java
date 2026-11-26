package e;

import a1.i;
import android.content.ClipData;
import android.content.ClipDescription;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.SystemClock;
import android.view.ContentInfo;
import android.view.GestureDetector;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputContentInfo;
import android.widget.TextView;
import androidx.appcompat.app.t0;
import androidx.appcompat.widget.ActionMenuView;
import androidx.appcompat.widget.Toolbar;
import androidx.appcompat.widget.f2;
import androidx.appcompat.widget.q3;
import androidx.appcompat.widget.u;
import androidx.appcompat.widget.u3;
import androidx.appcompat.widget.y0;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.profileinstaller.ProfileInstallReceiver;
import androidx.recyclerview.widget.m0;
import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.google.android.material.behavior.SwipeDismissBehavior;
import com.yalantis.ucrop.view.CropImageView;
import h0.g;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;
import java.util.concurrent.ConcurrentHashMap;
import k.b0;
import k.i0;
import k.m;
import k.o;
import k.q;
import k2.n;
import n1.z;
import p0.d1;
import p0.h;
import p0.j2;
import p0.l;
import p0.l0;
import p0.l2;
import q0.w;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public class a implements f2, b0, m, y0, p0.b0, h, l, s0.d, w, h1.e {

    /* renamed from: a, reason: collision with root package name */
    public final /* synthetic */ int f6289a;

    /* renamed from: b, reason: collision with root package name */
    public final Object f6290b;

    public a() {
        this.f6289a = 10;
        this.f6290b = new ConcurrentHashMap();
    }

    public /* synthetic */ a(int i7, Object obj) {
        this.f6289a = i7;
        this.f6290b = obj;
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public a(Context context, m0 m0Var) {
        this(context, m0Var, 14);
        this.f6289a = 14;
    }

    public a(Context context, m0 m0Var, int i7) {
        this.f6289a = i7;
        if (i7 != 14) {
            this.f6290b = new GestureDetector(context, m0Var, null);
        } else {
            this.f6290b = new a(context, m0Var, 13);
        }
    }

    public a(ContentInfo contentInfo) {
        this.f6289a = 12;
        contentInfo.getClass();
        this.f6290b = e0.w.h(contentInfo);
    }

    public a(TextView textView) {
        this.f6289a = 22;
        if (textView == null) {
            throw new NullPointerException("textView cannot be null");
        }
        this.f6290b = new i(textView);
    }

    public a(Object obj) {
        this.f6289a = 16;
        this.f6290b = (InputContentInfo) obj;
    }

    public static Object x(Object[] objArr, int i7, p3.f fVar) {
        int i8;
        boolean z6;
        int i9 = (i7 & 1) == 0 ? 400 : 700;
        boolean z7 = (i7 & 2) != 0;
        Object obj = null;
        int i10 = u.MAX_ACTIVITY_COUNT_UNLIMITED;
        for (Object obj2 : objArr) {
            int i11 = fVar.f8238a;
            switch (i11) {
                case 0:
                    i8 = ((m0.i) obj2).f7505c;
                    break;
                default:
                    i8 = ((g) obj2).f6622b;
                    break;
            }
            int abs = Math.abs(i8 - i9) * 2;
            switch (i11) {
                case 0:
                    z6 = ((m0.i) obj2).f7506d;
                    break;
                default:
                    z6 = ((g) obj2).f6623c;
                    break;
            }
            int i12 = abs + (z6 == z7 ? 0 : 1);
            if (obj == null || i10 > i12) {
                obj = obj2;
                i10 = i12;
            }
        }
        return obj;
    }

    public m0.i A(int i7, m0.i[] iVarArr) {
        return (m0.i) x(iVarArr, i7, new p3.f(0));
    }

    public final p2.a B(h2.d dVar, p2.a aVar) {
        if (aVar == null) {
            return new p2.a((File) this.f6290b);
        }
        boolean z6 = (dVar.f6711e.f7213d & 16) == 0 || dVar.f6710d.f7242g == n.OLD;
        String absolutePath = aVar.f8223a.getAbsolutePath();
        String str = null;
        if (z6) {
            int length = absolutePath.length();
            if (length > 4 && absolutePath.charAt(length - 4) == '.') {
                StringBuilder sb = new StringBuilder();
                int i7 = length - 3;
                sb.append((CharSequence) absolutePath, 0, i7);
                if (z.D0(absolutePath.charAt(length - 2)) && z.D0(absolutePath.charAt(length - 1))) {
                    char[] cArr = new char[3];
                    absolutePath.getChars(i7, length, cArr, 0);
                    int i8 = 2;
                    while (true) {
                        char c7 = (char) (cArr[i8] + 1);
                        cArr[i8] = c7;
                        if (c7 != ':') {
                            break;
                        }
                        cArr[i8] = '0';
                        i8--;
                    }
                    sb.append(cArr);
                } else {
                    sb.append("r00");
                }
                str = sb.toString();
            }
        } else {
            int length2 = absolutePath.length();
            int i9 = length2 - 1;
            while (i9 >= 0 && !z.D0(absolutePath.charAt(i9))) {
                i9--;
            }
            int i10 = i9 + 1;
            int i11 = i9 - 1;
            while (i11 >= 0 && z.D0(absolutePath.charAt(i11))) {
                i11--;
            }
            if (i11 >= 0) {
                int i12 = i11 + 1;
                StringBuilder sb2 = new StringBuilder(length2);
                sb2.append((CharSequence) absolutePath, 0, i12);
                int i13 = i9 - i12;
                char[] cArr2 = new char[i13 + 1];
                absolutePath.getChars(i12, i10, cArr2, 0);
                while (i13 >= 0) {
                    char c8 = (char) (cArr2[i13] + 1);
                    cArr2[i13] = c8;
                    if (c8 != ':') {
                        break;
                    }
                    cArr2[i13] = '0';
                    i13--;
                }
                if (i13 < 0) {
                    sb2.append('1');
                }
                sb2.append(cArr2);
                sb2.append((CharSequence) absolutePath, i10, length2);
                str = sb2.toString();
            }
        }
        return new p2.a(new File(str));
    }

    public final boolean C(MotionEvent motionEvent) {
        switch (this.f6289a) {
            case 13:
                return ((GestureDetector) this.f6290b).onTouchEvent(motionEvent);
            default:
                return ((a) ((l) this.f6290b)).C(motionEvent);
        }
    }

    @Override // k.b0
    public final void a(o oVar, boolean z6) {
        if (oVar instanceof i0) {
            oVar.k().c(false);
        }
        b0 b0Var = ((androidx.appcompat.widget.m) this.f6290b).f1327e;
        if (b0Var != null) {
            b0Var.a(oVar, z6);
        }
    }

    @Override // p0.h
    public final ClipData b() {
        ClipData clip;
        clip = ((ContentInfo) this.f6290b).getClip();
        return clip;
    }

    @Override // k.b0
    public final boolean c(o oVar) {
        Object obj = this.f6290b;
        if (oVar == ((androidx.appcompat.widget.m) obj).f1325c) {
            return false;
        }
        ((androidx.appcompat.widget.m) obj).f1347y = ((i0) oVar).A.f7166a;
        b0 b0Var = ((androidx.appcompat.widget.m) obj).f1327e;
        if (b0Var != null) {
            return b0Var.c(oVar);
        }
        return false;
    }

    @Override // androidx.appcompat.widget.f2
    public final void d(o oVar, q qVar) {
        ((k.i) this.f6290b).f7102g.removeCallbacksAndMessages(null);
        int size = ((k.i) this.f6290b).f7104i.size();
        int i7 = 0;
        while (true) {
            if (i7 >= size) {
                i7 = -1;
                break;
            } else if (oVar == ((k.h) ((k.i) this.f6290b).f7104i.get(i7)).f7074b) {
                break;
            } else {
                i7++;
            }
        }
        if (i7 == -1) {
            return;
        }
        int i8 = i7 + 1;
        ((k.i) this.f6290b).f7102g.postAtTime(new k.g(this, i8 < ((k.i) this.f6290b).f7104i.size() ? (k.h) ((k.i) this.f6290b).f7104i.get(i8) : null, qVar, oVar, 0), oVar, SystemClock.uptimeMillis() + 200);
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // p0.b0
    public final l2 e(View view, l2 l2Var) {
        int i7 = 0;
        j2 j2Var = l2Var.f8161a;
        switch (this.f6289a) {
            case 8:
                CoordinatorLayout coordinatorLayout = (CoordinatorLayout) this.f6290b;
                if (!o0.b.a(coordinatorLayout.f1732n, l2Var)) {
                    coordinatorLayout.f1732n = l2Var;
                    boolean z6 = l2Var.d() > 0;
                    coordinatorLayout.f1733o = z6;
                    coordinatorLayout.setWillNotDraw(!z6 && coordinatorLayout.getBackground() == null);
                    if (!j2Var.m()) {
                        int childCount = coordinatorLayout.getChildCount();
                        while (i7 < childCount) {
                            View childAt = coordinatorLayout.getChildAt(i7);
                            int i8 = d1.OVER_SCROLL_ALWAYS;
                            if (!l0.b(childAt) || ((c0.e) childAt.getLayoutParams()).f3007a == null || !j2Var.m()) {
                                i7++;
                            }
                        }
                    }
                    coordinatorLayout.requestLayout();
                }
                return l2Var;
            case 27:
                AppBarLayout appBarLayout = (AppBarLayout) this.f6290b;
                appBarLayout.getClass();
                int i9 = d1.OVER_SCROLL_ALWAYS;
                l2 l2Var2 = l0.b(appBarLayout) ? l2Var : null;
                if (!o0.b.a(appBarLayout.f3210g, l2Var2)) {
                    appBarLayout.f3210g = l2Var2;
                    if (appBarLayout.f3225v != null && appBarLayout.getTopInset() > 0) {
                        i7 = 1;
                    }
                    appBarLayout.setWillNotDraw(i7 ^ 1);
                    appBarLayout.requestLayout();
                }
                return l2Var;
            default:
                CollapsingToolbarLayout collapsingToolbarLayout = (CollapsingToolbarLayout) this.f6290b;
                collapsingToolbarLayout.getClass();
                int i10 = d1.OVER_SCROLL_ALWAYS;
                if (!l0.b(collapsingToolbarLayout)) {
                    l2Var = null;
                }
                if (!o0.b.a(collapsingToolbarLayout.A, l2Var)) {
                    collapsingToolbarLayout.A = l2Var;
                    collapsingToolbarLayout.requestLayout();
                }
                return j2Var.c();
        }
    }

    @Override // s0.d
    public final void f() {
        switch (this.f6289a) {
            case 16:
                ((InputContentInfo) this.f6290b).requestPermission();
                break;
            default:
                ((s0.d) this.f6290b).f();
                break;
        }
    }

    @Override // q0.w
    public final boolean g(View view) {
        switch (this.f6289a) {
            case 18:
                ((DrawerLayout) this.f6290b).getClass();
                if (DrawerLayout.o(view) && ((DrawerLayout) this.f6290b).j(view) != 2) {
                    ((DrawerLayout) this.f6290b).c(view, true);
                    break;
                } else {
                    break;
                }
            default:
                if (!((SwipeDismissBehavior) this.f6290b).w(view)) {
                    break;
                } else {
                    int i7 = d1.OVER_SCROLL_ALWAYS;
                    boolean z6 = p0.m0.d(view) == 1;
                    int i8 = ((SwipeDismissBehavior) this.f6290b).f3322d;
                    view.offsetLeftAndRight((!(i8 == 0 && z6) && (i8 != 1 || z6)) ? view.getWidth() : -view.getWidth());
                    view.setAlpha(CropImageView.DEFAULT_ASPECT_RATIO);
                    ((SwipeDismissBehavior) this.f6290b).getClass();
                    break;
                }
        }
        return false;
    }

    @Override // s0.d
    public final Uri h() {
        switch (this.f6289a) {
            case 16:
                return ((InputContentInfo) this.f6290b).getLinkUri();
            default:
                return ((s0.d) this.f6290b).h();
        }
    }

    @Override // p0.h
    public final int i() {
        int flags;
        flags = ((ContentInfo) this.f6290b).getFlags();
        return flags;
    }

    @Override // h1.e
    public final void j() {
    }

    @Override // androidx.appcompat.widget.f2
    public final void k(o oVar, MenuItem menuItem) {
        ((k.i) this.f6290b).f7102g.removeCallbacksAndMessages(oVar);
    }

    @Override // k.m
    public final boolean l(o oVar, MenuItem menuItem) {
        androidx.appcompat.widget.q qVar = ((ActionMenuView) this.f6290b).A;
        if (qVar == null) {
            return false;
        }
        Toolbar toolbar = ((q3) qVar).f1384a;
        Iterator it = toolbar.G.f8184b.iterator();
        if (it.hasNext()) {
            a.a.u(it.next());
            throw null;
        }
        u3 u3Var = toolbar.I;
        return u3Var != null && ((t0) u3Var).f855a.f859b.onMenuItemSelected(0, menuItem);
    }

    @Override // p0.h
    public final ContentInfo m() {
        return (ContentInfo) this.f6290b;
    }

    @Override // h1.e
    public final void n(int i7, Object obj) {
        if (i7 == 6 || i7 == 7 || i7 == 8) {
        }
        ((ProfileInstallReceiver) this.f6290b).setResultCode(i7);
    }

    public void o(int i7) {
    }

    public void p(int i7) {
    }

    @Override // p0.h
    public final int q() {
        int source;
        source = ((ContentInfo) this.f6290b).getSource();
        return source;
    }

    @Override // s0.d
    public final ClipDescription r() {
        switch (this.f6289a) {
            case 16:
                return ((InputContentInfo) this.f6290b).getDescription();
            default:
                return ((s0.d) this.f6290b).r();
        }
    }

    @Override // k.m
    public final void s(o oVar) {
        m mVar = ((ActionMenuView) this.f6290b).f987v;
        if (mVar != null) {
            mVar.s(oVar);
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:13:0x004c  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public android.graphics.Typeface t(android.content.Context r10, h0.f r11, android.content.res.Resources r12, int r13) {
        /*
            r9 = this;
            p3.f r0 = new p3.f
            r1 = 1
            r0.<init>(r1)
            h0.g[] r2 = r11.f6620a
            java.lang.Object r0 = x(r2, r13, r0)
            h0.g r0 = (h0.g) r0
            if (r0 != 0) goto L12
            r10 = 0
            return r10
        L12:
            int r8 = r0.f6626f
            java.lang.String r0 = r0.f6621a
            e.a r2 = i0.h.f6781a
            r3 = r10
            r4 = r12
            r5 = r8
            r6 = r0
            r7 = r13
            android.graphics.Typeface r10 = r2.w(r3, r4, r5, r6, r7)
            if (r10 == 0) goto L2d
            r2 = 0
            java.lang.String r12 = i0.h.b(r12, r8, r0, r2, r13)
            q.f r13 = i0.h.f6782b
            r13.b(r12, r10)
        L2d:
            r12 = 0
            if (r10 != 0) goto L33
        L31:
            r0 = r12
            goto L48
        L33:
            java.lang.Class<android.graphics.Typeface> r0 = android.graphics.Typeface.class
            java.lang.String r2 = "native_instance"
            java.lang.reflect.Field r0 = r0.getDeclaredField(r2)     // Catch: java.lang.Throwable -> L31
            r0.setAccessible(r1)     // Catch: java.lang.Throwable -> L31
            java.lang.Object r0 = r0.get(r10)     // Catch: java.lang.Throwable -> L31
            java.lang.Number r0 = (java.lang.Number) r0     // Catch: java.lang.Throwable -> L31
            long r0 = r0.longValue()     // Catch: java.lang.Throwable -> L31
        L48:
            int r12 = (r0 > r12 ? 1 : (r0 == r12 ? 0 : -1))
            if (r12 == 0) goto L57
            java.lang.Object r12 = r9.f6290b
            java.util.concurrent.ConcurrentHashMap r12 = (java.util.concurrent.ConcurrentHashMap) r12
            java.lang.Long r13 = java.lang.Long.valueOf(r0)
            r12.put(r13, r11)
        L57:
            return r10
        */
        throw new UnsupportedOperationException("Method not decompiled: e.a.t(android.content.Context, h0.f, android.content.res.Resources, int):android.graphics.Typeface");
    }

    public final String toString() {
        switch (this.f6289a) {
            case 12:
                return "ContentInfoCompat{" + ((ContentInfo) this.f6290b) + "}";
            default:
                return super.toString();
        }
    }

    public Typeface u(Context context, m0.i[] iVarArr, int i7) {
        InputStream inputStream;
        InputStream inputStream2 = null;
        if (iVarArr.length < 1) {
            return null;
        }
        try {
            inputStream = context.getContentResolver().openInputStream(A(i7, iVarArr).f7503a);
            try {
                Typeface v6 = v(context, inputStream);
                z.B(inputStream);
                return v6;
            } catch (IOException unused) {
                z.B(inputStream);
                return null;
            } catch (Throwable th) {
                th = th;
                inputStream2 = inputStream;
                z.B(inputStream2);
                throw th;
            }
        } catch (IOException unused2) {
            inputStream = null;
        } catch (Throwable th2) {
            th = th2;
        }
    }

    public Typeface v(Context context, InputStream inputStream) {
        File s02 = z.s0(context);
        if (s02 == null) {
            return null;
        }
        try {
            if (z.J(s02, inputStream)) {
                return Typeface.createFromFile(s02.getPath());
            }
            return null;
        } catch (RuntimeException unused) {
            return null;
        } finally {
            s02.delete();
        }
    }

    public Typeface w(Context context, Resources resources, int i7, String str, int i8) {
        File s02 = z.s0(context);
        if (s02 == null) {
            return null;
        }
        try {
            if (z.I(s02, resources, i7)) {
                return Typeface.createFromFile(s02.getPath());
            }
            return null;
        } catch (RuntimeException unused) {
            return null;
        } finally {
            s02.delete();
        }
    }

    @Override // s0.d
    public final Object y() {
        return (InputContentInfo) this.f6290b;
    }

    @Override // s0.d
    public final Uri z() {
        switch (this.f6289a) {
            case 16:
                return ((InputContentInfo) this.f6290b).getContentUri();
            default:
                return ((s0.d) this.f6290b).z();
        }
    }
}
