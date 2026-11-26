package androidx.appcompat.widget;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.widget.HeaderViewListAdapter;
import android.widget.ListAdapter;
import android.widget.PopupWindow;
import androidx.appcompat.view.menu.ListMenuItemView;
import java.lang.reflect.Method;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_2_1633180.dex */
public final class j2 extends e2 implements f2 {
    public static final Method D;
    public f2 C;

    static {
        try {
            if (Build.VERSION.SDK_INT <= 28) {
                D = PopupWindow.class.getDeclaredMethod("setTouchModal", Boolean.TYPE);
            }
        } catch (NoSuchMethodException unused) {
        }
    }

    /* JADX WARN: Type inference failed for: r0v0, types: [androidx.appcompat.widget.MenuPopupWindow$MenuDropDownListView, androidx.appcompat.widget.s1] */
    @Override // androidx.appcompat.widget.e2
    public final s1 a(final Context context, final boolean z6) {
        ?? r02 = new s1(context, z6) { // from class: androidx.appcompat.widget.MenuPopupWindow$MenuDropDownListView

            /* renamed from: m, reason: collision with root package name */
            public final int f1089m;

            /* renamed from: n, reason: collision with root package name */
            public final int f1090n;

            /* renamed from: o, reason: collision with root package name */
            public f2 f1091o;

            /* renamed from: p, reason: collision with root package name */
            public k.q f1092p;

            {
                super(context, z6);
                if (1 == i2.a(context.getResources().getConfiguration())) {
                    this.f1089m = 21;
                    this.f1090n = 22;
                } else {
                    this.f1089m = 22;
                    this.f1090n = 21;
                }
            }

            @Override // androidx.appcompat.widget.s1, android.view.View
            public final boolean onHoverEvent(MotionEvent motionEvent) {
                k.l lVar;
                int i7;
                int pointToPosition;
                int i8;
                if (this.f1091o != null) {
                    ListAdapter adapter = getAdapter();
                    if (adapter instanceof HeaderViewListAdapter) {
                        HeaderViewListAdapter headerViewListAdapter = (HeaderViewListAdapter) adapter;
                        i7 = headerViewListAdapter.getHeadersCount();
                        lVar = (k.l) headerViewListAdapter.getWrappedAdapter();
                    } else {
                        lVar = (k.l) adapter;
                        i7 = 0;
                    }
                    k.q b7 = (motionEvent.getAction() == 10 || (pointToPosition = pointToPosition((int) motionEvent.getX(), (int) motionEvent.getY())) == -1 || (i8 = pointToPosition - i7) < 0 || i8 >= lVar.getCount()) ? null : lVar.b(i8);
                    k.q qVar = this.f1092p;
                    if (qVar != b7) {
                        k.o oVar = lVar.f7132a;
                        if (qVar != null) {
                            this.f1091o.k(oVar, qVar);
                        }
                        this.f1092p = b7;
                        if (b7 != null) {
                            this.f1091o.d(oVar, b7);
                        }
                    }
                }
                return super.onHoverEvent(motionEvent);
            }

            @Override // android.widget.ListView, android.widget.AbsListView, android.view.View, android.view.KeyEvent.Callback
            public final boolean onKeyDown(int i7, KeyEvent keyEvent) {
                ListMenuItemView listMenuItemView = (ListMenuItemView) getSelectedView();
                if (listMenuItemView != null && i7 == this.f1089m) {
                    if (listMenuItemView.isEnabled() && listMenuItemView.getItemData().hasSubMenu()) {
                        performItemClick(listMenuItemView, getSelectedItemPosition(), getSelectedItemId());
                    }
                    return true;
                }
                if (listMenuItemView == null || i7 != this.f1090n) {
                    return super.onKeyDown(i7, keyEvent);
                }
                setSelection(-1);
                ListAdapter adapter = getAdapter();
                (adapter instanceof HeaderViewListAdapter ? (k.l) ((HeaderViewListAdapter) adapter).getWrappedAdapter() : (k.l) adapter).f7132a.c(false);
                return true;
            }

            public void setHoverListener(f2 f2Var) {
                this.f1091o = f2Var;
            }

            @Override // androidx.appcompat.widget.s1, android.widget.AbsListView
            public /* bridge */ /* synthetic */ void setSelector(Drawable drawable) {
                super.setSelector(drawable);
            }
        };
        r02.setHoverListener(this);
        return r02;
    }

    @Override // androidx.appcompat.widget.f2
    public final void d(k.o oVar, k.q qVar) {
        f2 f2Var = this.C;
        if (f2Var != null) {
            f2Var.d(oVar, qVar);
        }
    }

    @Override // androidx.appcompat.widget.f2
    public final void k(k.o oVar, MenuItem menuItem) {
        f2 f2Var = this.C;
        if (f2Var != null) {
            f2Var.k(oVar, menuItem);
        }
    }
}
