package j;

import android.app.Activity;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.res.TypedArray;
import android.content.res.XmlResourceParser;
import android.util.AttributeSet;
import android.util.Xml;
import android.view.InflateException;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.SubMenu;
import androidx.appcompat.widget.m1;
import java.io.IOException;
import k.r;
import org.xmlpull.v1.XmlPullParserException;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final class l extends MenuInflater {

    /* renamed from: e, reason: collision with root package name */
    public static final Class[] f6925e;

    /* renamed from: f, reason: collision with root package name */
    public static final Class[] f6926f;

    /* renamed from: a, reason: collision with root package name */
    public final Object[] f6927a;

    /* renamed from: b, reason: collision with root package name */
    public final Object[] f6928b;

    /* renamed from: c, reason: collision with root package name */
    public final Context f6929c;

    /* renamed from: d, reason: collision with root package name */
    public Object f6930d;

    static {
        Class[] clsArr = {Context.class};
        f6925e = clsArr;
        f6926f = clsArr;
    }

    public l(Context context) {
        super(context);
        this.f6929c = context;
        Object[] objArr = {context};
        this.f6927a = objArr;
        this.f6928b = objArr;
    }

    public static Object a(Context context) {
        return (!(context instanceof Activity) && (context instanceof ContextWrapper)) ? a(((ContextWrapper) context).getBaseContext()) : context;
    }

    public final void b(XmlResourceParser xmlResourceParser, AttributeSet attributeSet, Menu menu) {
        k kVar = new k(this, menu);
        int eventType = xmlResourceParser.getEventType();
        while (true) {
            if (eventType == 2) {
                String name = xmlResourceParser.getName();
                if (!name.equals("menu")) {
                    throw new RuntimeException("Expecting menu, got ".concat(name));
                }
                eventType = xmlResourceParser.next();
            } else {
                eventType = xmlResourceParser.next();
                if (eventType == 1) {
                    break;
                }
            }
        }
        boolean z6 = false;
        boolean z7 = false;
        String str = null;
        while (!z6) {
            if (eventType == 1) {
                throw new RuntimeException("Unexpected end of document");
            }
            if (eventType != 2) {
                if (eventType == 3) {
                    String name2 = xmlResourceParser.getName();
                    if (z7 && name2.equals(str)) {
                        z7 = false;
                        str = null;
                    } else if (name2.equals("group")) {
                        kVar.f6900b = 0;
                        kVar.f6901c = 0;
                        kVar.f6902d = 0;
                        kVar.f6903e = 0;
                        kVar.f6904f = true;
                        kVar.f6905g = true;
                    } else if (name2.equals("item")) {
                        if (!kVar.f6906h) {
                            p0.d dVar = kVar.f6924z;
                            if (dVar == null || !((r) dVar).f7192b.hasSubMenu()) {
                                kVar.f6906h = true;
                                kVar.b(kVar.f6899a.add(kVar.f6900b, kVar.f6907i, kVar.f6908j, kVar.f6909k));
                            } else {
                                kVar.f6906h = true;
                                kVar.b(kVar.f6899a.addSubMenu(kVar.f6900b, kVar.f6907i, kVar.f6908j, kVar.f6909k).getItem());
                            }
                        }
                    } else if (name2.equals("menu")) {
                        z6 = true;
                    }
                    eventType = xmlResourceParser.next();
                    z6 = z6;
                    z7 = z7;
                }
                eventType = xmlResourceParser.next();
                z6 = z6;
                z7 = z7;
            } else {
                if (!z7) {
                    String name3 = xmlResourceParser.getName();
                    boolean equals = name3.equals("group");
                    l lVar = kVar.E;
                    if (equals) {
                        TypedArray obtainStyledAttributes = lVar.f6929c.obtainStyledAttributes(attributeSet, f.j.MenuGroup);
                        kVar.f6900b = obtainStyledAttributes.getResourceId(f.j.MenuGroup_android_id, 0);
                        kVar.f6901c = obtainStyledAttributes.getInt(f.j.MenuGroup_android_menuCategory, 0);
                        kVar.f6902d = obtainStyledAttributes.getInt(f.j.MenuGroup_android_orderInCategory, 0);
                        kVar.f6903e = obtainStyledAttributes.getInt(f.j.MenuGroup_android_checkableBehavior, 0);
                        kVar.f6904f = obtainStyledAttributes.getBoolean(f.j.MenuGroup_android_visible, true);
                        kVar.f6905g = obtainStyledAttributes.getBoolean(f.j.MenuGroup_android_enabled, true);
                        obtainStyledAttributes.recycle();
                    } else if (name3.equals("item")) {
                        Context context = lVar.f6929c;
                        androidx.appcompat.app.g gVar = new androidx.appcompat.app.g(context, context.obtainStyledAttributes(attributeSet, f.j.MenuItem));
                        kVar.f6907i = gVar.N(f.j.MenuItem_android_id, 0);
                        kVar.f6908j = (gVar.L(f.j.MenuItem_android_menuCategory, kVar.f6901c) & k0.a.CATEGORY_MASK) | (gVar.L(f.j.MenuItem_android_orderInCategory, kVar.f6902d) & 65535);
                        kVar.f6909k = gVar.Q(f.j.MenuItem_android_title);
                        kVar.f6910l = gVar.Q(f.j.MenuItem_android_titleCondensed);
                        kVar.f6911m = gVar.N(f.j.MenuItem_android_icon, 0);
                        String P = gVar.P(f.j.MenuItem_android_alphabeticShortcut);
                        kVar.f6912n = P == null ? (char) 0 : P.charAt(0);
                        kVar.f6913o = gVar.L(f.j.MenuItem_alphabeticModifiers, 4096);
                        String P2 = gVar.P(f.j.MenuItem_android_numericShortcut);
                        kVar.f6914p = P2 == null ? (char) 0 : P2.charAt(0);
                        kVar.f6915q = gVar.L(f.j.MenuItem_numericModifiers, 4096);
                        if (gVar.R(f.j.MenuItem_android_checkable)) {
                            kVar.f6916r = gVar.E(f.j.MenuItem_android_checkable, false) ? 1 : 0;
                        } else {
                            kVar.f6916r = kVar.f6903e;
                        }
                        kVar.f6917s = gVar.E(f.j.MenuItem_android_checked, false);
                        kVar.f6918t = gVar.E(f.j.MenuItem_android_visible, kVar.f6904f);
                        kVar.f6919u = gVar.E(f.j.MenuItem_android_enabled, kVar.f6905g);
                        kVar.f6920v = gVar.L(f.j.MenuItem_showAsAction, -1);
                        kVar.f6923y = gVar.P(f.j.MenuItem_android_onClick);
                        kVar.f6921w = gVar.N(f.j.MenuItem_actionLayout, 0);
                        kVar.f6922x = gVar.P(f.j.MenuItem_actionViewClass);
                        String P3 = gVar.P(f.j.MenuItem_actionProviderClass);
                        if (P3 != null && kVar.f6921w == 0 && kVar.f6922x == null) {
                            kVar.f6924z = (p0.d) kVar.a(P3, f6926f, lVar.f6928b);
                        } else {
                            kVar.f6924z = null;
                        }
                        kVar.A = gVar.Q(f.j.MenuItem_contentDescription);
                        kVar.B = gVar.Q(f.j.MenuItem_tooltipText);
                        if (gVar.R(f.j.MenuItem_iconTintMode)) {
                            kVar.D = m1.c(gVar.L(f.j.MenuItem_iconTintMode, -1), kVar.D);
                        } else {
                            kVar.D = null;
                        }
                        if (gVar.R(f.j.MenuItem_iconTint)) {
                            kVar.C = gVar.F(f.j.MenuItem_iconTint);
                        } else {
                            kVar.C = null;
                        }
                        gVar.V();
                        kVar.f6906h = false;
                    } else {
                        if (name3.equals("menu")) {
                            kVar.f6906h = true;
                            SubMenu addSubMenu = kVar.f6899a.addSubMenu(kVar.f6900b, kVar.f6907i, kVar.f6908j, kVar.f6909k);
                            kVar.b(addSubMenu.getItem());
                            b(xmlResourceParser, attributeSet, addSubMenu);
                        } else {
                            str = name3;
                            z7 = true;
                        }
                        eventType = xmlResourceParser.next();
                        z6 = z6;
                        z7 = z7;
                    }
                }
                eventType = xmlResourceParser.next();
                z6 = z6;
                z7 = z7;
            }
        }
    }

    @Override // android.view.MenuInflater
    public final void inflate(int i7, Menu menu) {
        if (!(menu instanceof k0.a)) {
            super.inflate(i7, menu);
            return;
        }
        XmlResourceParser xmlResourceParser = null;
        try {
            try {
                try {
                    xmlResourceParser = this.f6929c.getResources().getLayout(i7);
                    b(xmlResourceParser, Xml.asAttributeSet(xmlResourceParser), menu);
                    xmlResourceParser.close();
                } catch (IOException e7) {
                    throw new InflateException("Error inflating menu XML", e7);
                }
            } catch (XmlPullParserException e8) {
                throw new InflateException("Error inflating menu XML", e8);
            }
        } catch (Throwable th) {
            if (xmlResourceParser != null) {
                xmlResourceParser.close();
            }
            throw th;
        }
    }
}
