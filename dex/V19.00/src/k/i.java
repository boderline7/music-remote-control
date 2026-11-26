package k;

import android.content.Context;
import android.content.res.Resources;
import android.os.Handler;
import android.os.Parcelable;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.HeaderViewListAdapter;
import android.widget.ListAdapter;
import android.widget.PopupWindow;
import androidx.appcompat.widget.g2;
import androidx.appcompat.widget.j2;
import androidx.appcompat.widget.s1;
import java.util.ArrayList;
import java.util.Iterator;
import p0.d1;
import p0.m0;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final class i extends x implements View.OnKeyListener, PopupWindow.OnDismissListener {
    public static final int B = f.g.abc_cascading_menu_item_layout;
    public boolean A;

    /* renamed from: b, reason: collision with root package name */
    public final Context f7097b;

    /* renamed from: c, reason: collision with root package name */
    public final int f7098c;

    /* renamed from: d, reason: collision with root package name */
    public final int f7099d;

    /* renamed from: e, reason: collision with root package name */
    public final int f7100e;

    /* renamed from: f, reason: collision with root package name */
    public final boolean f7101f;

    /* renamed from: g, reason: collision with root package name */
    public final Handler f7102g;

    /* renamed from: j, reason: collision with root package name */
    public final e f7105j;

    /* renamed from: k, reason: collision with root package name */
    public final f f7106k;

    /* renamed from: o, reason: collision with root package name */
    public View f7110o;

    /* renamed from: p, reason: collision with root package name */
    public View f7111p;

    /* renamed from: q, reason: collision with root package name */
    public int f7112q;

    /* renamed from: r, reason: collision with root package name */
    public boolean f7113r;

    /* renamed from: s, reason: collision with root package name */
    public boolean f7114s;

    /* renamed from: t, reason: collision with root package name */
    public int f7115t;

    /* renamed from: u, reason: collision with root package name */
    public int f7116u;

    /* renamed from: w, reason: collision with root package name */
    public boolean f7118w;

    /* renamed from: x, reason: collision with root package name */
    public b0 f7119x;

    /* renamed from: y, reason: collision with root package name */
    public ViewTreeObserver f7120y;

    /* renamed from: z, reason: collision with root package name */
    public PopupWindow.OnDismissListener f7121z;

    /* renamed from: h, reason: collision with root package name */
    public final ArrayList f7103h = new ArrayList();

    /* renamed from: i, reason: collision with root package name */
    public final ArrayList f7104i = new ArrayList();

    /* renamed from: l, reason: collision with root package name */
    public final e.a f7107l = new e.a(3, this);

    /* renamed from: m, reason: collision with root package name */
    public int f7108m = 0;

    /* renamed from: n, reason: collision with root package name */
    public int f7109n = 0;

    /* renamed from: v, reason: collision with root package name */
    public boolean f7117v = false;

    public i(Context context, View view, int i7, int i8, boolean z6) {
        this.f7105j = new e(r1, this);
        this.f7106k = new f(r1, this);
        this.f7097b = context;
        this.f7110o = view;
        this.f7099d = i7;
        this.f7100e = i8;
        this.f7101f = z6;
        int i9 = d1.OVER_SCROLL_ALWAYS;
        this.f7112q = m0.d(view) != 1 ? 1 : 0;
        Resources resources = context.getResources();
        this.f7098c = Math.max(resources.getDisplayMetrics().widthPixels / 2, resources.getDimensionPixelSize(f.d.abc_config_prefDialogWidth));
        this.f7102g = new Handler();
    }

    @Override // k.c0
    public final void a(o oVar, boolean z6) {
        ArrayList arrayList = this.f7104i;
        int size = arrayList.size();
        int i7 = 0;
        while (true) {
            if (i7 >= size) {
                i7 = -1;
                break;
            } else if (oVar == ((h) arrayList.get(i7)).f7074b) {
                break;
            } else {
                i7++;
            }
        }
        if (i7 < 0) {
            return;
        }
        int i8 = i7 + 1;
        if (i8 < arrayList.size()) {
            ((h) arrayList.get(i8)).f7074b.c(false);
        }
        h hVar = (h) arrayList.remove(i7);
        hVar.f7074b.r(this);
        boolean z7 = this.A;
        j2 j2Var = hVar.f7073a;
        if (z7) {
            g2.b(j2Var.f1237z, null);
            j2Var.f1237z.setAnimationStyle(0);
        }
        j2Var.dismiss();
        int size2 = arrayList.size();
        if (size2 > 0) {
            this.f7112q = ((h) arrayList.get(size2 - 1)).f7075c;
        } else {
            View view = this.f7110o;
            int i9 = d1.OVER_SCROLL_ALWAYS;
            this.f7112q = m0.d(view) == 1 ? 0 : 1;
        }
        if (size2 != 0) {
            if (z6) {
                ((h) arrayList.get(0)).f7074b.c(false);
                return;
            }
            return;
        }
        dismiss();
        b0 b0Var = this.f7119x;
        if (b0Var != null) {
            b0Var.a(oVar, true);
        }
        ViewTreeObserver viewTreeObserver = this.f7120y;
        if (viewTreeObserver != null) {
            if (viewTreeObserver.isAlive()) {
                this.f7120y.removeGlobalOnLayoutListener(this.f7105j);
            }
            this.f7120y = null;
        }
        this.f7111p.removeOnAttachStateChangeListener(this.f7106k);
        this.f7121z.onDismiss();
    }

    @Override // k.g0
    public final boolean b() {
        ArrayList arrayList = this.f7104i;
        return arrayList.size() > 0 && ((h) arrayList.get(0)).f7073a.f1237z.isShowing();
    }

    @Override // k.c0
    public final boolean d(i0 i0Var) {
        Iterator it = this.f7104i.iterator();
        while (it.hasNext()) {
            h hVar = (h) it.next();
            if (i0Var == hVar.f7074b) {
                hVar.f7073a.f1214c.requestFocus();
                return true;
            }
        }
        if (!i0Var.hasVisibleItems()) {
            return false;
        }
        n(i0Var);
        b0 b0Var = this.f7119x;
        if (b0Var != null) {
            b0Var.c(i0Var);
        }
        return true;
    }

    @Override // k.g0
    public final void dismiss() {
        ArrayList arrayList = this.f7104i;
        int size = arrayList.size();
        if (size > 0) {
            h[] hVarArr = (h[]) arrayList.toArray(new h[size]);
            for (int i7 = size - 1; i7 >= 0; i7--) {
                h hVar = hVarArr[i7];
                if (hVar.f7073a.f1237z.isShowing()) {
                    hVar.f7073a.dismiss();
                }
            }
        }
    }

    @Override // k.g0
    public final void g() {
        if (b()) {
            return;
        }
        ArrayList arrayList = this.f7103h;
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            x((o) it.next());
        }
        arrayList.clear();
        View view = this.f7110o;
        this.f7111p = view;
        if (view != null) {
            boolean z6 = this.f7120y == null;
            ViewTreeObserver viewTreeObserver = view.getViewTreeObserver();
            this.f7120y = viewTreeObserver;
            if (z6) {
                viewTreeObserver.addOnGlobalLayoutListener(this.f7105j);
            }
            this.f7111p.addOnAttachStateChangeListener(this.f7106k);
        }
    }

    @Override // k.c0
    public final boolean h() {
        return false;
    }

    @Override // k.c0
    public final Parcelable i() {
        return null;
    }

    @Override // k.c0
    public final void j(Parcelable parcelable) {
    }

    @Override // k.c0
    public final void k(b0 b0Var) {
        this.f7119x = b0Var;
    }

    @Override // k.g0
    public final s1 l() {
        ArrayList arrayList = this.f7104i;
        if (arrayList.isEmpty()) {
            return null;
        }
        return ((h) arrayList.get(arrayList.size() - 1)).f7073a.f1214c;
    }

    @Override // k.c0
    public final void m(boolean z6) {
        Iterator it = this.f7104i.iterator();
        while (it.hasNext()) {
            ListAdapter adapter = ((h) it.next()).f7073a.f1214c.getAdapter();
            if (adapter instanceof HeaderViewListAdapter) {
                adapter = ((HeaderViewListAdapter) adapter).getWrappedAdapter();
            }
            ((l) adapter).notifyDataSetChanged();
        }
    }

    @Override // k.x
    public final void n(o oVar) {
        oVar.b(this, this.f7097b);
        if (b()) {
            x(oVar);
        } else {
            this.f7103h.add(oVar);
        }
    }

    @Override // android.widget.PopupWindow.OnDismissListener
    public final void onDismiss() {
        h hVar;
        ArrayList arrayList = this.f7104i;
        int size = arrayList.size();
        int i7 = 0;
        while (true) {
            if (i7 >= size) {
                hVar = null;
                break;
            }
            hVar = (h) arrayList.get(i7);
            if (!hVar.f7073a.f1237z.isShowing()) {
                break;
            } else {
                i7++;
            }
        }
        if (hVar != null) {
            hVar.f7074b.c(false);
        }
    }

    @Override // android.view.View.OnKeyListener
    public final boolean onKey(View view, int i7, KeyEvent keyEvent) {
        if (keyEvent.getAction() != 1 || i7 != 82) {
            return false;
        }
        dismiss();
        return true;
    }

    @Override // k.x
    public final void p(View view) {
        if (this.f7110o != view) {
            this.f7110o = view;
            int i7 = this.f7108m;
            int i8 = d1.OVER_SCROLL_ALWAYS;
            this.f7109n = Gravity.getAbsoluteGravity(i7, m0.d(view));
        }
    }

    @Override // k.x
    public final void q(boolean z6) {
        this.f7117v = z6;
    }

    @Override // k.x
    public final void r(int i7) {
        if (this.f7108m != i7) {
            this.f7108m = i7;
            View view = this.f7110o;
            int i8 = d1.OVER_SCROLL_ALWAYS;
            this.f7109n = Gravity.getAbsoluteGravity(i7, m0.d(view));
        }
    }

    @Override // k.x
    public final void s(int i7) {
        this.f7113r = true;
        this.f7115t = i7;
    }

    @Override // k.x
    public final void t(PopupWindow.OnDismissListener onDismissListener) {
        this.f7121z = onDismissListener;
    }

    @Override // k.x
    public final void u(boolean z6) {
        this.f7118w = z6;
    }

    @Override // k.x
    public final void v(int i7) {
        this.f7114s = true;
        this.f7116u = i7;
    }

    /* JADX WARN: Removed duplicated region for block: B:18:0x00e8  */
    /* JADX WARN: Removed duplicated region for block: B:28:0x014c  */
    /* JADX WARN: Removed duplicated region for block: B:31:0x0159  */
    /* JADX WARN: Removed duplicated region for block: B:46:0x0164  */
    /* JADX WARN: Removed duplicated region for block: B:49:0x014e  */
    /* JADX WARN: Removed duplicated region for block: B:64:0x0178  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void x(k.o r18) {
        /*
            Method dump skipped, instructions count: 468
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: k.i.x(k.o):void");
    }
}
