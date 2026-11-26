package androidx.appcompat.widget;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.PorterDuff;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.InsetDrawable;
import android.graphics.drawable.RippleDrawable;
import android.util.AttributeSet;
import android.util.Base64;
import android.view.View;
import android.widget.TextView;
import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import java.util.List;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_2_1633180.dex */
public final class w {

    /* renamed from: a, reason: collision with root package name */
    public final /* synthetic */ int f1434a;

    /* renamed from: b, reason: collision with root package name */
    public int f1435b;

    /* renamed from: c, reason: collision with root package name */
    public final Object f1436c;

    /* renamed from: d, reason: collision with root package name */
    public final Object f1437d;

    /* renamed from: e, reason: collision with root package name */
    public Object f1438e;

    /* renamed from: f, reason: collision with root package name */
    public Object f1439f;

    /* renamed from: g, reason: collision with root package name */
    public Object f1440g;

    public w(ColorStateList colorStateList, ColorStateList colorStateList2, ColorStateList colorStateList3, int i7, p3.p pVar, Rect rect) {
        this.f1434a = 3;
        n1.z.u(rect.left);
        n1.z.u(rect.top);
        n1.z.u(rect.right);
        n1.z.u(rect.bottom);
        this.f1436c = rect;
        this.f1437d = colorStateList2;
        this.f1438e = colorStateList;
        this.f1439f = colorStateList3;
        this.f1435b = i7;
        this.f1440g = pVar;
    }

    public w(View view) {
        this.f1434a = 0;
        this.f1435b = -1;
        this.f1436c = view;
        this.f1437d = z.a();
    }

    public w(StringWriter stringWriter, int i7, int i8, String str) {
        this.f1434a = 2;
        if (stringWriter == null) {
            throw new NullPointerException("out == null");
        }
        if (i7 < 1) {
            throw new IllegalArgumentException("leftWidth < 1");
        }
        if (i8 < 1) {
            throw new IllegalArgumentException("rightWidth < 1");
        }
        StringWriter stringWriter2 = new StringWriter(1000);
        StringWriter stringWriter3 = new StringWriter(1000);
        this.f1436c = stringWriter;
        this.f1435b = i7;
        this.f1437d = stringWriter2.getBuffer();
        this.f1438e = stringWriter3.getBuffer();
        this.f1439f = new e2.e(stringWriter2, i7, s5.h0.FRAGMENT_ENCODE_SET);
        this.f1440g = new e2.e(stringWriter3, i8, str);
    }

    public w(String str, String str2, String str3, List list) {
        this.f1434a = 1;
        str.getClass();
        this.f1436c = str;
        str2.getClass();
        this.f1437d = str2;
        this.f1438e = str3;
        list.getClass();
        this.f1439f = list;
        this.f1435b = 0;
        this.f1440g = str + "-" + str2 + "-" + str3;
    }

    public static void a(StringBuffer stringBuffer, e2.e eVar) {
        int length = stringBuffer.length();
        if (length == 0 || stringBuffer.charAt(length - 1) == '\n') {
            return;
        }
        eVar.write(10);
    }

    public static w c(Context context, int i7) {
        n1.z.t("Cannot create a CalendarItemStyle with a styleResId of 0", i7 != 0);
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(i7, q2.m.MaterialCalendarItem);
        Rect rect = new Rect(obtainStyledAttributes.getDimensionPixelOffset(q2.m.MaterialCalendarItem_android_insetLeft, 0), obtainStyledAttributes.getDimensionPixelOffset(q2.m.MaterialCalendarItem_android_insetTop, 0), obtainStyledAttributes.getDimensionPixelOffset(q2.m.MaterialCalendarItem_android_insetRight, 0), obtainStyledAttributes.getDimensionPixelOffset(q2.m.MaterialCalendarItem_android_insetBottom, 0));
        ColorStateList w6 = l6.f.w(context, obtainStyledAttributes, q2.m.MaterialCalendarItem_itemFillColor);
        ColorStateList w7 = l6.f.w(context, obtainStyledAttributes, q2.m.MaterialCalendarItem_itemTextColor);
        ColorStateList w8 = l6.f.w(context, obtainStyledAttributes, q2.m.MaterialCalendarItem_itemStrokeColor);
        int dimensionPixelSize = obtainStyledAttributes.getDimensionPixelSize(q2.m.MaterialCalendarItem_itemStrokeWidth, 0);
        p3.p a7 = p3.p.a(context, obtainStyledAttributes.getResourceId(q2.m.MaterialCalendarItem_itemShapeAppearance, 0), obtainStyledAttributes.getResourceId(q2.m.MaterialCalendarItem_itemShapeAppearanceOverlay, 0)).a();
        obtainStyledAttributes.recycle();
        return new w(w6, w7, w8, dimensionPixelSize, a7, rect);
    }

    public final void b() {
        View view = (View) this.f1436c;
        Drawable background = view.getBackground();
        if (background != null) {
            if (((n3) this.f1438e) != null) {
                if (((n3) this.f1440g) == null) {
                    this.f1440g = new n3(0);
                }
                n3 n3Var = (n3) this.f1440g;
                n3Var.f1363c = null;
                n3Var.f1362b = false;
                n3Var.f1364d = null;
                n3Var.f1361a = false;
                int i7 = p0.d1.OVER_SCROLL_ALWAYS;
                ColorStateList g7 = p0.r0.g(view);
                if (g7 != null) {
                    n3Var.f1362b = true;
                    n3Var.f1363c = g7;
                }
                PorterDuff.Mode h7 = p0.r0.h(view);
                if (h7 != null) {
                    n3Var.f1361a = true;
                    n3Var.f1364d = h7;
                }
                if (n3Var.f1362b || n3Var.f1361a) {
                    z.e(background, n3Var, view.getDrawableState());
                    return;
                }
            }
            n3 n3Var2 = (n3) this.f1439f;
            if (n3Var2 != null) {
                z.e(background, n3Var2, view.getDrawableState());
                return;
            }
            n3 n3Var3 = (n3) this.f1438e;
            if (n3Var3 != null) {
                z.e(background, n3Var3, view.getDrawableState());
            }
        }
    }

    public final void d() {
        Object obj = this.f1437d;
        try {
            a((StringBuffer) obj, (e2.e) this.f1439f);
            a((StringBuffer) this.f1438e, (e2.e) this.f1440g);
            j();
            a((StringBuffer) obj, (e2.e) this.f1439f);
            while (((StringBuffer) obj).length() != 0) {
                ((e2.e) this.f1440g).write(10);
                j();
            }
            a((StringBuffer) this.f1438e, (e2.e) this.f1440g);
            while (((StringBuffer) this.f1438e).length() != 0) {
                ((e2.e) this.f1439f).write(10);
                j();
            }
        } catch (IOException e7) {
            throw new RuntimeException(e7);
        }
    }

    public final ColorStateList e() {
        Object obj = this.f1439f;
        if (((n3) obj) != null) {
            return (ColorStateList) ((n3) obj).f1363c;
        }
        return null;
    }

    public final PorterDuff.Mode f() {
        Object obj = this.f1439f;
        if (((n3) obj) != null) {
            return (PorterDuff.Mode) ((n3) obj).f1364d;
        }
        return null;
    }

    public final void g(AttributeSet attributeSet, int i7) {
        ColorStateList h7;
        Object obj = this.f1436c;
        View view = (View) obj;
        androidx.appcompat.app.g S = androidx.appcompat.app.g.S(view.getContext(), attributeSet, f.j.ViewBackgroundHelper, i7);
        p0.d1.n(view, view.getContext(), f.j.ViewBackgroundHelper, attributeSet, (TypedArray) S.f715c, i7);
        try {
            if (S.R(f.j.ViewBackgroundHelper_android_background)) {
                this.f1435b = S.N(f.j.ViewBackgroundHelper_android_background, -1);
                z zVar = (z) this.f1437d;
                Context context = ((View) obj).getContext();
                int i8 = this.f1435b;
                synchronized (zVar) {
                    h7 = zVar.f1494a.h(context, i8);
                }
                if (h7 != null) {
                    k(h7);
                }
            }
            if (S.R(f.j.ViewBackgroundHelper_backgroundTint)) {
                p0.r0.q((View) obj, S.F(f.j.ViewBackgroundHelper_backgroundTint));
            }
            if (S.R(f.j.ViewBackgroundHelper_backgroundTintMode)) {
                p0.r0.r((View) obj, m1.c(S.L(f.j.ViewBackgroundHelper_backgroundTintMode, -1), null));
            }
            S.V();
        } catch (Throwable th) {
            S.V();
            throw th;
        }
    }

    public final void h() {
        this.f1435b = -1;
        k(null);
        b();
    }

    public final void i(int i7) {
        ColorStateList colorStateList;
        this.f1435b = i7;
        z zVar = (z) this.f1437d;
        if (zVar != null) {
            Context context = ((View) this.f1436c).getContext();
            synchronized (zVar) {
                colorStateList = zVar.f1494a.h(context, i7);
            }
        } else {
            colorStateList = null;
        }
        k(colorStateList);
        b();
    }

    public final void j() {
        int indexOf;
        while (true) {
            Object obj = this.f1437d;
            int indexOf2 = ((StringBuffer) obj).indexOf("\n");
            if (indexOf2 < 0 || (indexOf = ((StringBuffer) this.f1438e).indexOf("\n")) < 0) {
                return;
            }
            Object obj2 = this.f1436c;
            if (indexOf2 != 0) {
                ((Writer) obj2).write(((StringBuffer) obj).substring(0, indexOf2));
            }
            if (indexOf != 0) {
                Writer writer = (Writer) obj2;
                for (int i7 = this.f1435b - indexOf2; i7 > 0; i7--) {
                    writer.write(32);
                }
                ((Writer) obj2).write(((StringBuffer) this.f1438e).substring(0, indexOf));
            }
            ((Writer) obj2).write(10);
            ((StringBuffer) obj).delete(0, indexOf2 + 1);
            ((StringBuffer) this.f1438e).delete(0, indexOf + 1);
        }
    }

    public final void k(ColorStateList colorStateList) {
        if (colorStateList != null) {
            if (((n3) this.f1438e) == null) {
                this.f1438e = new n3(0);
            }
            Object obj = this.f1438e;
            ((n3) obj).f1363c = colorStateList;
            ((n3) obj).f1362b = true;
        } else {
            this.f1438e = null;
        }
        b();
    }

    public final void l(ColorStateList colorStateList) {
        if (((n3) this.f1439f) == null) {
            this.f1439f = new n3(0);
        }
        n3 n3Var = (n3) this.f1439f;
        n3Var.f1363c = colorStateList;
        n3Var.f1362b = true;
        b();
    }

    public final void m(PorterDuff.Mode mode) {
        if (((n3) this.f1439f) == null) {
            this.f1439f = new n3(0);
        }
        n3 n3Var = (n3) this.f1439f;
        n3Var.f1364d = mode;
        n3Var.f1361a = true;
        b();
    }

    public final void n(TextView textView) {
        p3.j jVar = new p3.j();
        p3.j jVar2 = new p3.j();
        jVar.setShapeAppearanceModel((p3.p) this.f1440g);
        jVar2.setShapeAppearanceModel((p3.p) this.f1440g);
        jVar.o((ColorStateList) this.f1438e);
        float f7 = this.f1435b;
        ColorStateList colorStateList = (ColorStateList) this.f1439f;
        jVar.u(f7);
        jVar.t(colorStateList);
        Object obj = this.f1437d;
        textView.setTextColor((ColorStateList) obj);
        RippleDrawable rippleDrawable = new RippleDrawable(((ColorStateList) obj).withAlpha(30), jVar, jVar2);
        Rect rect = (Rect) this.f1436c;
        InsetDrawable insetDrawable = new InsetDrawable((Drawable) rippleDrawable, rect.left, rect.top, rect.right, rect.bottom);
        int i7 = p0.d1.OVER_SCROLL_ALWAYS;
        p0.l0.q(textView, insetDrawable);
    }

    public final String toString() {
        switch (this.f1434a) {
            case 1:
                StringBuilder sb = new StringBuilder();
                sb.append("FontRequest {mProviderAuthority: " + ((String) this.f1436c) + ", mProviderPackage: " + ((String) this.f1437d) + ", mQuery: " + ((String) this.f1438e) + ", mCertificates:");
                for (int i7 = 0; i7 < ((List) this.f1439f).size(); i7++) {
                    sb.append(" [");
                    List list = (List) ((List) this.f1439f).get(i7);
                    for (int i8 = 0; i8 < list.size(); i8++) {
                        sb.append(" \"");
                        sb.append(Base64.encodeToString((byte[]) list.get(i8), 0));
                        sb.append("\"");
                    }
                    sb.append(" ]");
                }
                sb.append("}");
                sb.append("mCertificatesArray: " + this.f1435b);
                return sb.toString();
            default:
                return super.toString();
        }
    }
}
