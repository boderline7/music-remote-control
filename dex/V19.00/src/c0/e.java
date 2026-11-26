package c0;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Rect;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import java.lang.reflect.Constructor;
import java.util.HashMap;
import java.util.Map;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final class e extends ViewGroup.MarginLayoutParams {

    /* renamed from: a, reason: collision with root package name */
    public b f3007a;

    /* renamed from: b, reason: collision with root package name */
    public boolean f3008b;

    /* renamed from: c, reason: collision with root package name */
    public final int f3009c;

    /* renamed from: d, reason: collision with root package name */
    public int f3010d;

    /* renamed from: e, reason: collision with root package name */
    public final int f3011e;

    /* renamed from: f, reason: collision with root package name */
    public final int f3012f;

    /* renamed from: g, reason: collision with root package name */
    public final int f3013g;

    /* renamed from: h, reason: collision with root package name */
    public int f3014h;

    /* renamed from: i, reason: collision with root package name */
    public int f3015i;

    /* renamed from: j, reason: collision with root package name */
    public int f3016j;

    /* renamed from: k, reason: collision with root package name */
    public View f3017k;

    /* renamed from: l, reason: collision with root package name */
    public View f3018l;

    /* renamed from: m, reason: collision with root package name */
    public boolean f3019m;

    /* renamed from: n, reason: collision with root package name */
    public boolean f3020n;

    /* renamed from: o, reason: collision with root package name */
    public boolean f3021o;

    /* renamed from: p, reason: collision with root package name */
    public boolean f3022p;

    /* renamed from: q, reason: collision with root package name */
    public final Rect f3023q;

    public e() {
        super(-2, -2);
        this.f3008b = false;
        this.f3009c = 0;
        this.f3010d = 0;
        this.f3011e = -1;
        this.f3012f = -1;
        this.f3013g = 0;
        this.f3014h = 0;
        this.f3023q = new Rect();
    }

    public e(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        b bVar;
        this.f3008b = false;
        this.f3009c = 0;
        this.f3010d = 0;
        this.f3011e = -1;
        this.f3012f = -1;
        this.f3013g = 0;
        this.f3014h = 0;
        this.f3023q = new Rect();
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, b0.c.CoordinatorLayout_Layout);
        this.f3009c = obtainStyledAttributes.getInteger(b0.c.CoordinatorLayout_Layout_android_layout_gravity, 0);
        this.f3012f = obtainStyledAttributes.getResourceId(b0.c.CoordinatorLayout_Layout_layout_anchor, -1);
        this.f3010d = obtainStyledAttributes.getInteger(b0.c.CoordinatorLayout_Layout_layout_anchorGravity, 0);
        this.f3011e = obtainStyledAttributes.getInteger(b0.c.CoordinatorLayout_Layout_layout_keyline, -1);
        this.f3013g = obtainStyledAttributes.getInt(b0.c.CoordinatorLayout_Layout_layout_insetEdge, 0);
        this.f3014h = obtainStyledAttributes.getInt(b0.c.CoordinatorLayout_Layout_layout_dodgeInsetEdges, 0);
        boolean hasValue = obtainStyledAttributes.hasValue(b0.c.CoordinatorLayout_Layout_layout_behavior);
        this.f3008b = hasValue;
        if (hasValue) {
            String string = obtainStyledAttributes.getString(b0.c.CoordinatorLayout_Layout_layout_behavior);
            String str = CoordinatorLayout.f1714t;
            if (TextUtils.isEmpty(string)) {
                bVar = null;
            } else {
                if (string.startsWith(".")) {
                    string = context.getPackageName() + string;
                } else if (string.indexOf(46) < 0) {
                    String str2 = CoordinatorLayout.f1714t;
                    if (!TextUtils.isEmpty(str2)) {
                        string = str2 + '.' + string;
                    }
                }
                try {
                    ThreadLocal threadLocal = CoordinatorLayout.f1716v;
                    Map map = (Map) threadLocal.get();
                    if (map == null) {
                        map = new HashMap();
                        threadLocal.set(map);
                    }
                    Constructor<?> constructor = (Constructor) map.get(string);
                    if (constructor == null) {
                        constructor = Class.forName(string, false, context.getClassLoader()).getConstructor(CoordinatorLayout.f1715u);
                        constructor.setAccessible(true);
                        map.put(string, constructor);
                    }
                    bVar = (b) constructor.newInstance(context, attributeSet);
                } catch (Exception e7) {
                    throw new RuntimeException(a.a.B("Could not inflate Behavior subclass ", string), e7);
                }
            }
            this.f3007a = bVar;
        }
        obtainStyledAttributes.recycle();
        b bVar2 = this.f3007a;
        if (bVar2 != null) {
            bVar2.g(this);
        }
    }

    public e(ViewGroup.LayoutParams layoutParams) {
        super(layoutParams);
        this.f3008b = false;
        this.f3009c = 0;
        this.f3010d = 0;
        this.f3011e = -1;
        this.f3012f = -1;
        this.f3013g = 0;
        this.f3014h = 0;
        this.f3023q = new Rect();
    }

    public e(ViewGroup.MarginLayoutParams marginLayoutParams) {
        super(marginLayoutParams);
        this.f3008b = false;
        this.f3009c = 0;
        this.f3010d = 0;
        this.f3011e = -1;
        this.f3012f = -1;
        this.f3013g = 0;
        this.f3014h = 0;
        this.f3023q = new Rect();
    }

    public e(e eVar) {
        super((ViewGroup.MarginLayoutParams) eVar);
        this.f3008b = false;
        this.f3009c = 0;
        this.f3010d = 0;
        this.f3011e = -1;
        this.f3012f = -1;
        this.f3013g = 0;
        this.f3014h = 0;
        this.f3023q = new Rect();
    }

    public final boolean a(int i7) {
        if (i7 == 0) {
            return this.f3020n;
        }
        if (i7 != 1) {
            return false;
        }
        return this.f3021o;
    }
}
