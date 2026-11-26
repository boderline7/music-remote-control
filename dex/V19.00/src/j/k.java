package j;

import android.content.res.ColorStateList;
import android.graphics.PorterDuff;
import android.view.InflateException;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import k.w;
import p0.t;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final class k {
    public CharSequence A;
    public CharSequence B;
    public final /* synthetic */ l E;

    /* renamed from: a, reason: collision with root package name */
    public final Menu f6899a;

    /* renamed from: h, reason: collision with root package name */
    public boolean f6906h;

    /* renamed from: i, reason: collision with root package name */
    public int f6907i;

    /* renamed from: j, reason: collision with root package name */
    public int f6908j;

    /* renamed from: k, reason: collision with root package name */
    public CharSequence f6909k;

    /* renamed from: l, reason: collision with root package name */
    public CharSequence f6910l;

    /* renamed from: m, reason: collision with root package name */
    public int f6911m;

    /* renamed from: n, reason: collision with root package name */
    public char f6912n;

    /* renamed from: o, reason: collision with root package name */
    public int f6913o;

    /* renamed from: p, reason: collision with root package name */
    public char f6914p;

    /* renamed from: q, reason: collision with root package name */
    public int f6915q;

    /* renamed from: r, reason: collision with root package name */
    public int f6916r;

    /* renamed from: s, reason: collision with root package name */
    public boolean f6917s;

    /* renamed from: t, reason: collision with root package name */
    public boolean f6918t;

    /* renamed from: u, reason: collision with root package name */
    public boolean f6919u;

    /* renamed from: v, reason: collision with root package name */
    public int f6920v;

    /* renamed from: w, reason: collision with root package name */
    public int f6921w;

    /* renamed from: x, reason: collision with root package name */
    public String f6922x;

    /* renamed from: y, reason: collision with root package name */
    public String f6923y;

    /* renamed from: z, reason: collision with root package name */
    public p0.d f6924z;
    public ColorStateList C = null;
    public PorterDuff.Mode D = null;

    /* renamed from: b, reason: collision with root package name */
    public int f6900b = 0;

    /* renamed from: c, reason: collision with root package name */
    public int f6901c = 0;

    /* renamed from: d, reason: collision with root package name */
    public int f6902d = 0;

    /* renamed from: e, reason: collision with root package name */
    public int f6903e = 0;

    /* renamed from: f, reason: collision with root package name */
    public boolean f6904f = true;

    /* renamed from: g, reason: collision with root package name */
    public boolean f6905g = true;

    public k(l lVar, Menu menu) {
        this.E = lVar;
        this.f6899a = menu;
    }

    public final Object a(String str, Class[] clsArr, Object[] objArr) {
        try {
            Constructor<?> constructor = Class.forName(str, false, this.E.f6929c.getClassLoader()).getConstructor(clsArr);
            constructor.setAccessible(true);
            return constructor.newInstance(objArr);
        } catch (Exception unused) {
            return null;
        }
    }

    public final void b(MenuItem menuItem) {
        boolean z6 = false;
        menuItem.setChecked(this.f6917s).setVisible(this.f6918t).setEnabled(this.f6919u).setCheckable(this.f6916r >= 1).setTitleCondensed(this.f6910l).setIcon(this.f6911m);
        int i7 = this.f6920v;
        if (i7 >= 0) {
            menuItem.setShowAsAction(i7);
        }
        String str = this.f6923y;
        l lVar = this.E;
        if (str != null) {
            if (lVar.f6929c.isRestricted()) {
                throw new IllegalStateException("The android:onClick attribute cannot be used within a restricted context");
            }
            if (lVar.f6930d == null) {
                lVar.f6930d = l.a(lVar.f6929c);
            }
            Object obj = lVar.f6930d;
            String str2 = this.f6923y;
            j jVar = new j();
            jVar.f6897a = obj;
            Class<?> cls = obj.getClass();
            try {
                jVar.f6898b = cls.getMethod(str2, j.f6896c);
                menuItem.setOnMenuItemClickListener(jVar);
            } catch (Exception e7) {
                InflateException inflateException = new InflateException("Couldn't resolve menu item onClick handler " + str2 + " in class " + cls.getName());
                inflateException.initCause(e7);
                throw inflateException;
            }
        }
        if (this.f6916r >= 2) {
            if (menuItem instanceof k.q) {
                ((k.q) menuItem).g(true);
            } else if (menuItem instanceof w) {
                w wVar = (w) menuItem;
                try {
                    Method method = wVar.f7201e;
                    k0.b bVar = wVar.f7200d;
                    if (method == null) {
                        wVar.f7201e = bVar.getClass().getDeclaredMethod("setExclusiveCheckable", Boolean.TYPE);
                    }
                    wVar.f7201e.invoke(bVar, Boolean.TRUE);
                } catch (Exception unused) {
                }
            }
        }
        String str3 = this.f6922x;
        if (str3 != null) {
            menuItem.setActionView((View) a(str3, l.f6925e, lVar.f6927a));
            z6 = true;
        }
        int i8 = this.f6921w;
        if (i8 > 0 && !z6) {
            menuItem.setActionView(i8);
        }
        p0.d dVar = this.f6924z;
        if (dVar != null && (menuItem instanceof k0.b)) {
            ((k0.b) menuItem).b(dVar);
        }
        CharSequence charSequence = this.A;
        boolean z7 = menuItem instanceof k0.b;
        if (z7) {
            ((k0.b) menuItem).setContentDescription(charSequence);
        } else {
            t.h(menuItem, charSequence);
        }
        CharSequence charSequence2 = this.B;
        if (z7) {
            ((k0.b) menuItem).setTooltipText(charSequence2);
        } else {
            t.m(menuItem, charSequence2);
        }
        char c7 = this.f6912n;
        int i9 = this.f6913o;
        if (z7) {
            ((k0.b) menuItem).setAlphabeticShortcut(c7, i9);
        } else {
            t.g(menuItem, c7, i9);
        }
        char c8 = this.f6914p;
        int i10 = this.f6915q;
        if (z7) {
            ((k0.b) menuItem).setNumericShortcut(c8, i10);
        } else {
            t.k(menuItem, c8, i10);
        }
        PorterDuff.Mode mode = this.D;
        if (mode != null) {
            if (z7) {
                ((k0.b) menuItem).setIconTintMode(mode);
            } else {
                t.j(menuItem, mode);
            }
        }
        ColorStateList colorStateList = this.C;
        if (colorStateList != null) {
            if (z7) {
                ((k0.b) menuItem).setIconTintList(colorStateList);
            } else {
                t.i(menuItem, colorStateList);
            }
        }
    }
}
