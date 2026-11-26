package androidx.appcompat.app;

import android.content.Context;
import android.content.DialogInterface;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.os.Message;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.view.ViewStub;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.TextView;
import androidx.core.widget.NestedScrollView;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_2_1633180.dex */
public final class m {
    public TextView A;
    public TextView B;
    public View C;
    public ListAdapter D;
    public final int F;
    public final int G;
    public final int H;
    public final int I;
    public final boolean J;
    public final k K;

    /* renamed from: a, reason: collision with root package name */
    public final Context f799a;

    /* renamed from: b, reason: collision with root package name */
    public final l0 f800b;

    /* renamed from: c, reason: collision with root package name */
    public final Window f801c;

    /* renamed from: d, reason: collision with root package name */
    public final int f802d;

    /* renamed from: e, reason: collision with root package name */
    public CharSequence f803e;

    /* renamed from: f, reason: collision with root package name */
    public CharSequence f804f;

    /* renamed from: g, reason: collision with root package name */
    public AlertController$RecycleListView f805g;

    /* renamed from: h, reason: collision with root package name */
    public View f806h;

    /* renamed from: i, reason: collision with root package name */
    public int f807i;

    /* renamed from: k, reason: collision with root package name */
    public Button f809k;

    /* renamed from: l, reason: collision with root package name */
    public CharSequence f810l;

    /* renamed from: m, reason: collision with root package name */
    public Message f811m;

    /* renamed from: n, reason: collision with root package name */
    public Drawable f812n;

    /* renamed from: o, reason: collision with root package name */
    public Button f813o;

    /* renamed from: p, reason: collision with root package name */
    public CharSequence f814p;

    /* renamed from: q, reason: collision with root package name */
    public Message f815q;

    /* renamed from: r, reason: collision with root package name */
    public Drawable f816r;

    /* renamed from: s, reason: collision with root package name */
    public Button f817s;

    /* renamed from: t, reason: collision with root package name */
    public CharSequence f818t;

    /* renamed from: u, reason: collision with root package name */
    public Message f819u;

    /* renamed from: v, reason: collision with root package name */
    public Drawable f820v;

    /* renamed from: w, reason: collision with root package name */
    public NestedScrollView f821w;

    /* renamed from: y, reason: collision with root package name */
    public Drawable f823y;

    /* renamed from: z, reason: collision with root package name */
    public ImageView f824z;

    /* renamed from: j, reason: collision with root package name */
    public boolean f808j = false;

    /* renamed from: x, reason: collision with root package name */
    public int f822x = 0;
    public int E = -1;
    public final c L = new c(1, this);

    public m(Context context, l0 l0Var, Window window) {
        this.f799a = context;
        this.f800b = l0Var;
        this.f801c = window;
        this.K = new k(l0Var);
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(null, f.j.AlertDialog, f.a.alertDialogStyle, 0);
        this.F = obtainStyledAttributes.getResourceId(f.j.AlertDialog_android_layout, 0);
        obtainStyledAttributes.getResourceId(f.j.AlertDialog_buttonPanelSideLayout, 0);
        this.G = obtainStyledAttributes.getResourceId(f.j.AlertDialog_listLayout, 0);
        obtainStyledAttributes.getResourceId(f.j.AlertDialog_multiChoiceItemLayout, 0);
        this.H = obtainStyledAttributes.getResourceId(f.j.AlertDialog_singleChoiceItemLayout, 0);
        this.I = obtainStyledAttributes.getResourceId(f.j.AlertDialog_listItemLayout, 0);
        this.J = obtainStyledAttributes.getBoolean(f.j.AlertDialog_showTitle, true);
        this.f802d = obtainStyledAttributes.getDimensionPixelSize(f.j.AlertDialog_buttonIconDimen, 0);
        obtainStyledAttributes.recycle();
        l0Var.c().f(1);
    }

    public static boolean a(View view) {
        if (view.onCheckIsTextEditor()) {
            return true;
        }
        if (!(view instanceof ViewGroup)) {
            return false;
        }
        ViewGroup viewGroup = (ViewGroup) view;
        int childCount = viewGroup.getChildCount();
        while (childCount > 0) {
            childCount--;
            if (a(viewGroup.getChildAt(childCount))) {
                return true;
            }
        }
        return false;
    }

    public static void b(View view, View view2, View view3) {
        if (view2 != null) {
            view2.setVisibility(view.canScrollVertically(-1) ? 0 : 4);
        }
        if (view3 != null) {
            view3.setVisibility(view.canScrollVertically(1) ? 0 : 4);
        }
    }

    public static ViewGroup c(View view, View view2) {
        if (view == null) {
            if (view2 instanceof ViewStub) {
                view2 = ((ViewStub) view2).inflate();
            }
            return (ViewGroup) view2;
        }
        if (view2 != null) {
            ViewParent parent = view2.getParent();
            if (parent instanceof ViewGroup) {
                ((ViewGroup) parent).removeView(view2);
            }
        }
        if (view instanceof ViewStub) {
            view = ((ViewStub) view).inflate();
        }
        return (ViewGroup) view;
    }

    public final void d(int i7, CharSequence charSequence, DialogInterface.OnClickListener onClickListener) {
        Message obtainMessage = onClickListener != null ? this.K.obtainMessage(i7, onClickListener) : null;
        if (i7 == -3) {
            this.f818t = charSequence;
            this.f819u = obtainMessage;
            this.f820v = null;
        } else if (i7 == -2) {
            this.f814p = charSequence;
            this.f815q = obtainMessage;
            this.f816r = null;
        } else {
            if (i7 != -1) {
                throw new IllegalArgumentException("Button does not exist");
            }
            this.f810l = charSequence;
            this.f811m = obtainMessage;
            this.f812n = null;
        }
    }
}
