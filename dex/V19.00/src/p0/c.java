package p0;

import android.os.Bundle;
import android.text.Spanned;
import android.text.style.ClickableSpan;
import android.util.SparseArray;
import android.view.View;
import android.view.ViewGroup;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityNodeProvider;
import java.lang.ref.WeakReference;
import java.util.Collections;
import java.util.List;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public class c {

    /* renamed from: c, reason: collision with root package name */
    public static final View.AccessibilityDelegate f8102c = new View.AccessibilityDelegate();

    /* renamed from: a, reason: collision with root package name */
    public final View.AccessibilityDelegate f8103a;

    /* renamed from: b, reason: collision with root package name */
    public final a f8104b;

    public c() {
        this(f8102c);
    }

    public c(View.AccessibilityDelegate accessibilityDelegate) {
        this.f8103a = accessibilityDelegate;
        this.f8104b = new a(this);
    }

    public boolean a(View view, AccessibilityEvent accessibilityEvent) {
        return this.f8103a.dispatchPopulateAccessibilityEvent(view, accessibilityEvent);
    }

    public q0.m b(View view) {
        AccessibilityNodeProvider a7 = b.a(this.f8103a, view);
        if (a7 != null) {
            return new q0.m(a7);
        }
        return null;
    }

    public void c(View view, AccessibilityEvent accessibilityEvent) {
        this.f8103a.onInitializeAccessibilityEvent(view, accessibilityEvent);
    }

    public void d(View view, q0.i iVar) {
        this.f8103a.onInitializeAccessibilityNodeInfo(view, iVar.f8426a);
    }

    public void e(View view, AccessibilityEvent accessibilityEvent) {
        this.f8103a.onPopulateAccessibilityEvent(view, accessibilityEvent);
    }

    public boolean f(ViewGroup viewGroup, View view, AccessibilityEvent accessibilityEvent) {
        return this.f8103a.onRequestSendAccessibilityEvent(viewGroup, view, accessibilityEvent);
    }

    public boolean g(View view, int i7, Bundle bundle) {
        boolean z6;
        WeakReference weakReference;
        ClickableSpan clickableSpan;
        List list = (List) view.getTag(d0.c.tag_accessibility_actions);
        if (list == null) {
            list = Collections.emptyList();
        }
        boolean z7 = false;
        int i8 = 0;
        while (true) {
            if (i8 >= list.size()) {
                break;
            }
            q0.g gVar = (q0.g) list.get(i8);
            if (gVar.a() == i7) {
                q0.w wVar = gVar.f8424d;
                if (wVar != null) {
                    Class cls = gVar.f8423c;
                    if (cls != null) {
                        try {
                            a.a.u(cls.getDeclaredConstructor(new Class[0]).newInstance(new Object[0]));
                            throw null;
                        } catch (Exception unused) {
                        }
                    }
                    z6 = wVar.g(view);
                }
            } else {
                i8++;
            }
        }
        z6 = false;
        if (!z6) {
            z6 = b.b(this.f8103a, view, i7, bundle);
        }
        if (z6 || i7 != d0.c.accessibility_action_clickable_span || bundle == null) {
            return z6;
        }
        int i9 = bundle.getInt(q0.a.SPAN_ID, -1);
        SparseArray sparseArray = (SparseArray) view.getTag(d0.c.tag_accessibility_clickable_spans);
        if (sparseArray != null && (weakReference = (WeakReference) sparseArray.get(i9)) != null && (clickableSpan = (ClickableSpan) weakReference.get()) != null) {
            CharSequence text = view.createAccessibilityNodeInfo().getText();
            ClickableSpan[] clickableSpanArr = text instanceof Spanned ? (ClickableSpan[]) ((Spanned) text).getSpans(0, text.length(), ClickableSpan.class) : null;
            int i10 = 0;
            while (true) {
                if (clickableSpanArr == null || i10 >= clickableSpanArr.length) {
                    break;
                }
                if (clickableSpan.equals(clickableSpanArr[i10])) {
                    clickableSpan.onClick(view);
                    z7 = true;
                    break;
                }
                i10++;
            }
        }
        return z7;
    }

    public void h(View view, int i7) {
        this.f8103a.sendAccessibilityEvent(view, i7);
    }

    public void i(View view, AccessibilityEvent accessibilityEvent) {
        this.f8103a.sendAccessibilityEventUnchecked(view, accessibilityEvent);
    }
}
