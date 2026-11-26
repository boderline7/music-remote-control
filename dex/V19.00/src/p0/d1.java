package p0;

import android.app.Activity;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.res.TypedArray;
import android.os.Build;
import android.util.AttributeSet;
import android.util.Log;
import android.util.SparseArray;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewParent;
import android.view.Window;
import android.view.WindowInsets;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityManager;
import java.lang.ref.WeakReference;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Objects;
import java.util.WeakHashMap;
import java.util.concurrent.atomic.AtomicInteger;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public abstract class d1 {
    public static final int ACCESSIBILITY_LIVE_REGION_ASSERTIVE = 2;
    public static final int ACCESSIBILITY_LIVE_REGION_NONE = 0;
    public static final int ACCESSIBILITY_LIVE_REGION_POLITE = 1;
    public static final int IMPORTANT_FOR_ACCESSIBILITY_AUTO = 0;
    public static final int IMPORTANT_FOR_ACCESSIBILITY_NO = 2;
    public static final int IMPORTANT_FOR_ACCESSIBILITY_NO_HIDE_DESCENDANTS = 4;
    public static final int IMPORTANT_FOR_ACCESSIBILITY_YES = 1;

    @Deprecated
    public static final int LAYER_TYPE_HARDWARE = 2;

    @Deprecated
    public static final int LAYER_TYPE_NONE = 0;

    @Deprecated
    public static final int LAYER_TYPE_SOFTWARE = 1;
    public static final int LAYOUT_DIRECTION_INHERIT = 2;
    public static final int LAYOUT_DIRECTION_LOCALE = 3;
    public static final int LAYOUT_DIRECTION_LTR = 0;
    public static final int LAYOUT_DIRECTION_RTL = 1;

    @Deprecated
    public static final int MEASURED_HEIGHT_STATE_SHIFT = 16;

    @Deprecated
    public static final int MEASURED_SIZE_MASK = 16777215;

    @Deprecated
    public static final int MEASURED_STATE_MASK = -16777216;

    @Deprecated
    public static final int MEASURED_STATE_TOO_SMALL = 16777216;

    @Deprecated
    public static final int OVER_SCROLL_ALWAYS = 0;

    @Deprecated
    public static final int OVER_SCROLL_IF_CONTENT_SCROLLS = 1;

    @Deprecated
    public static final int OVER_SCROLL_NEVER = 2;
    public static final int SCROLL_AXIS_HORIZONTAL = 1;
    public static final int SCROLL_AXIS_NONE = 0;
    public static final int SCROLL_AXIS_VERTICAL = 2;
    public static final int SCROLL_INDICATOR_BOTTOM = 2;
    public static final int SCROLL_INDICATOR_END = 32;
    public static final int SCROLL_INDICATOR_LEFT = 4;
    public static final int SCROLL_INDICATOR_RIGHT = 8;
    public static final int SCROLL_INDICATOR_START = 16;
    public static final int SCROLL_INDICATOR_TOP = 1;
    public static final int TYPE_NON_TOUCH = 1;
    public static final int TYPE_TOUCH = 0;

    /* renamed from: a, reason: collision with root package name */
    public static WeakHashMap f8110a;

    /* renamed from: b, reason: collision with root package name */
    public static Field f8111b;

    /* renamed from: c, reason: collision with root package name */
    public static boolean f8112c;

    /* renamed from: d, reason: collision with root package name */
    public static final int[] f8113d;

    /* renamed from: e, reason: collision with root package name */
    public static final g0 f8114e;

    /* renamed from: f, reason: collision with root package name */
    public static final i0 f8115f;

    static {
        new AtomicInteger(1);
        f8110a = null;
        f8112c = false;
        f8113d = new int[]{d0.c.accessibility_custom_action_0, d0.c.accessibility_custom_action_1, d0.c.accessibility_custom_action_2, d0.c.accessibility_custom_action_3, d0.c.accessibility_custom_action_4, d0.c.accessibility_custom_action_5, d0.c.accessibility_custom_action_6, d0.c.accessibility_custom_action_7, d0.c.accessibility_custom_action_8, d0.c.accessibility_custom_action_9, d0.c.accessibility_custom_action_10, d0.c.accessibility_custom_action_11, d0.c.accessibility_custom_action_12, d0.c.accessibility_custom_action_13, d0.c.accessibility_custom_action_14, d0.c.accessibility_custom_action_15, d0.c.accessibility_custom_action_16, d0.c.accessibility_custom_action_17, d0.c.accessibility_custom_action_18, d0.c.accessibility_custom_action_19, d0.c.accessibility_custom_action_20, d0.c.accessibility_custom_action_21, d0.c.accessibility_custom_action_22, d0.c.accessibility_custom_action_23, d0.c.accessibility_custom_action_24, d0.c.accessibility_custom_action_25, d0.c.accessibility_custom_action_26, d0.c.accessibility_custom_action_27, d0.c.accessibility_custom_action_28, d0.c.accessibility_custom_action_29, d0.c.accessibility_custom_action_30, d0.c.accessibility_custom_action_31};
        f8114e = new g0();
        f8115f = new i0();
    }

    public static l1 a(View view) {
        if (f8110a == null) {
            f8110a = new WeakHashMap();
        }
        l1 l1Var = (l1) f8110a.get(view);
        if (l1Var != null) {
            return l1Var;
        }
        l1 l1Var2 = new l1(view);
        f8110a.put(view, l1Var2);
        return l1Var2;
    }

    public static l2 b(View view, l2 l2Var) {
        WindowInsets g7 = l2Var.g();
        if (g7 != null) {
            WindowInsets a7 = p0.a(view, g7);
            if (!a7.equals(g7)) {
                return l2.h(view, a7);
            }
        }
        return l2Var;
    }

    public static boolean c(View view, KeyEvent keyEvent) {
        if (Build.VERSION.SDK_INT >= 28) {
            return false;
        }
        ArrayList arrayList = c1.f8105d;
        c1 c1Var = (c1) view.getTag(d0.c.tag_unhandled_key_event_manager);
        if (c1Var == null) {
            c1Var = new c1();
            c1Var.f8106a = null;
            c1Var.f8107b = null;
            c1Var.f8108c = null;
            view.setTag(d0.c.tag_unhandled_key_event_manager, c1Var);
        }
        if (keyEvent.getAction() == 0) {
            WeakHashMap weakHashMap = c1Var.f8106a;
            if (weakHashMap != null) {
                weakHashMap.clear();
            }
            ArrayList arrayList2 = c1.f8105d;
            if (!arrayList2.isEmpty()) {
                synchronized (arrayList2) {
                    try {
                        if (c1Var.f8106a == null) {
                            c1Var.f8106a = new WeakHashMap();
                        }
                        for (int size = arrayList2.size() - 1; size >= 0; size--) {
                            ArrayList arrayList3 = c1.f8105d;
                            View view2 = (View) ((WeakReference) arrayList3.get(size)).get();
                            if (view2 == null) {
                                arrayList3.remove(size);
                            } else {
                                c1Var.f8106a.put(view2, Boolean.TRUE);
                                for (ViewParent parent = view2.getParent(); parent instanceof View; parent = parent.getParent()) {
                                    c1Var.f8106a.put((View) parent, Boolean.TRUE);
                                }
                            }
                        }
                    } finally {
                    }
                }
            }
        }
        View a7 = c1Var.a(view);
        if (keyEvent.getAction() == 0) {
            int keyCode = keyEvent.getKeyCode();
            if (a7 != null && !KeyEvent.isModifierKey(keyCode)) {
                if (c1Var.f8107b == null) {
                    c1Var.f8107b = new SparseArray();
                }
                c1Var.f8107b.put(keyCode, new WeakReference(a7));
            }
        }
        return a7 != null;
    }

    public static View.AccessibilityDelegate d(View view) {
        if (Build.VERSION.SDK_INT >= 29) {
            return x0.a(view);
        }
        if (f8112c) {
            return null;
        }
        if (f8111b == null) {
            try {
                Field declaredField = View.class.getDeclaredField("mAccessibilityDelegate");
                f8111b = declaredField;
                declaredField.setAccessible(true);
            } catch (Throwable unused) {
                f8112c = true;
                return null;
            }
        }
        try {
            Object obj = f8111b.get(view);
            if (obj instanceof View.AccessibilityDelegate) {
                return (View.AccessibilityDelegate) obj;
            }
            return null;
        } catch (Throwable unused2) {
            f8112c = true;
            return null;
        }
    }

    public static CharSequence e(View view) {
        Object tag;
        int i7 = d0.c.tag_accessibility_pane_title;
        if (Build.VERSION.SDK_INT >= 28) {
            tag = w0.b(view);
        } else {
            tag = view.getTag(i7);
            if (!CharSequence.class.isInstance(tag)) {
                tag = null;
            }
        }
        return (CharSequence) tag;
    }

    public static ArrayList f(View view) {
        ArrayList arrayList = (ArrayList) view.getTag(d0.c.tag_accessibility_actions);
        if (arrayList != null) {
            return arrayList;
        }
        ArrayList arrayList2 = new ArrayList();
        view.setTag(d0.c.tag_accessibility_actions, arrayList2);
        return arrayList2;
    }

    public static String[] g(View view) {
        return Build.VERSION.SDK_INT >= 31 ? z0.a(view) : (String[]) view.getTag(d0.c.tag_on_receive_content_mime_types);
    }

    public static s2 h(View view) {
        if (Build.VERSION.SDK_INT >= 30) {
            return y0.b(view);
        }
        for (Context context = view.getContext(); context instanceof ContextWrapper; context = ((ContextWrapper) context).getBaseContext()) {
            if (context instanceof Activity) {
                Window window = ((Activity) context).getWindow();
                if (window != null) {
                    return new s2(window, view);
                }
                return null;
            }
        }
        return null;
    }

    public static void i(int i7, View view) {
        AccessibilityManager accessibilityManager = (AccessibilityManager) view.getContext().getSystemService("accessibility");
        if (accessibilityManager.isEnabled()) {
            boolean z6 = e(view) != null && view.isShown() && view.getWindowVisibility() == 0;
            if (o0.a(view) != 0 || z6) {
                AccessibilityEvent obtain = AccessibilityEvent.obtain();
                obtain.setEventType(z6 ? 32 : 2048);
                o0.g(obtain, i7);
                if (z6) {
                    obtain.getText().add(e(view));
                    if (l0.c(view) == 0) {
                        l0.s(view, 1);
                    }
                    ViewParent parent = view.getParent();
                    while (true) {
                        if (!(parent instanceof View)) {
                            break;
                        }
                        if (l0.c((View) parent) == 4) {
                            l0.s(view, 2);
                            break;
                        }
                        parent = parent.getParent();
                    }
                }
                view.sendAccessibilityEventUnchecked(obtain);
                return;
            }
            if (i7 != 32) {
                if (view.getParent() != null) {
                    try {
                        o0.e(view.getParent(), view, view, i7);
                        return;
                    } catch (AbstractMethodError unused) {
                        view.getParent().getClass();
                        return;
                    }
                }
                return;
            }
            AccessibilityEvent obtain2 = AccessibilityEvent.obtain();
            view.onInitializeAccessibilityEvent(obtain2);
            obtain2.setEventType(32);
            o0.g(obtain2, i7);
            obtain2.setSource(view);
            view.onPopulateAccessibilityEvent(obtain2);
            obtain2.getText().add(e(view));
            accessibilityManager.sendAccessibilityEvent(obtain2);
        }
    }

    public static l2 j(View view, l2 l2Var) {
        WindowInsets g7 = l2Var.g();
        if (g7 != null) {
            WindowInsets b7 = p0.b(view, g7);
            if (!b7.equals(g7)) {
                return l2.h(view, b7);
            }
        }
        return l2Var;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static i k(View view, i iVar) {
        if (Log.isLoggable("ViewCompat", 3)) {
            Objects.toString(iVar);
            view.getClass();
            view.getId();
        }
        if (Build.VERSION.SDK_INT >= 31) {
            return z0.b(view, iVar);
        }
        c0 c0Var = (c0) view.getTag(d0.c.tag_on_receive_content_listener);
        d0 d0Var = f8114e;
        if (c0Var == null) {
            if (view instanceof d0) {
                d0Var = (d0) view;
            }
            return d0Var.a(iVar);
        }
        i a7 = ((androidx.core.widget.x) c0Var).a(view, iVar);
        if (a7 == null) {
            return null;
        }
        if (view instanceof d0) {
            d0Var = (d0) view;
        }
        return d0Var.a(a7);
    }

    public static void l(int i7, View view) {
        ArrayList f7 = f(view);
        for (int i8 = 0; i8 < f7.size(); i8++) {
            if (((q0.g) f7.get(i8)).a() == i7) {
                f7.remove(i8);
                return;
            }
        }
    }

    public static void m(View view, q0.g gVar, String str, q0.w wVar) {
        if (wVar == null && str == null) {
            l(gVar.a(), view);
            i(0, view);
            return;
        }
        q0.g gVar2 = new q0.g(null, gVar.f8422b, str, wVar, gVar.f8423c);
        View.AccessibilityDelegate d7 = d(view);
        c cVar = d7 == null ? null : d7 instanceof a ? ((a) d7).f8091a : new c(d7);
        if (cVar == null) {
            cVar = new c();
        }
        o(view, cVar);
        l(gVar2.a(), view);
        f(view).add(gVar2);
        i(0, view);
    }

    public static void n(View view, Context context, int[] iArr, AttributeSet attributeSet, TypedArray typedArray, int i7) {
        if (Build.VERSION.SDK_INT >= 29) {
            x0.c(view, context, iArr, attributeSet, typedArray, i7, 0);
        }
    }

    public static void o(View view, c cVar) {
        if (cVar == null && (d(view) instanceof a)) {
            cVar = new c();
        }
        view.setAccessibilityDelegate(cVar == null ? null : cVar.f8104b);
    }

    public static void p(View view, CharSequence charSequence) {
        new h0(d0.c.tag_accessibility_pane_title, 8, 28, 1 == true ? 1 : 0).b(view, charSequence);
        i0 i0Var = f8115f;
        if (charSequence == null) {
            i0Var.f8144a.remove(view);
            view.removeOnAttachStateChangeListener(i0Var);
            l0.o(view.getViewTreeObserver(), i0Var);
        } else {
            i0Var.f8144a.put(view, Boolean.valueOf(view.isShown() && view.getWindowVisibility() == 0));
            view.addOnAttachStateChangeListener(i0Var);
            if (o0.b(view)) {
                view.getViewTreeObserver().addOnGlobalLayoutListener(i0Var);
            }
        }
    }
}
