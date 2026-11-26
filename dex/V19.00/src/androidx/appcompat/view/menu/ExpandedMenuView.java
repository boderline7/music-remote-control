package androidx.appcompat.view.menu;

import android.R;
import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import androidx.appcompat.app.g;
import k.e0;
import k.n;
import k.o;
import k.q;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_2_1633180.dex */
public final class ExpandedMenuView extends ListView implements n, e0, AdapterView.OnItemClickListener {

    /* renamed from: b, reason: collision with root package name */
    public static final int[] f905b = {R.attr.background, R.attr.divider};

    /* renamed from: a, reason: collision with root package name */
    public o f906a;

    public ExpandedMenuView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, R.attr.listViewStyle);
    }

    public ExpandedMenuView(Context context, AttributeSet attributeSet, int i7) {
        super(context, attributeSet);
        setOnItemClickListener(this);
        g gVar = new g(context, context.obtainStyledAttributes(attributeSet, f905b, i7, 0));
        if (gVar.R(0)) {
            setBackgroundDrawable(gVar.I(0));
        }
        if (gVar.R(1)) {
            setDivider(gVar.I(1));
        }
        gVar.V();
    }

    @Override // k.n
    public final boolean a(q qVar) {
        return this.f906a.q(qVar, null, 0);
    }

    @Override // k.e0
    public final void d(o oVar) {
        this.f906a = oVar;
    }

    public int getWindowAnimations() {
        return 0;
    }

    @Override // android.widget.ListView, android.widget.AbsListView, android.widget.AdapterView, android.view.ViewGroup, android.view.View
    public final void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        setChildrenDrawingCacheEnabled(false);
    }

    @Override // android.widget.AdapterView.OnItemClickListener
    public final void onItemClick(AdapterView adapterView, View view, int i7, long j7) {
        a((q) getAdapter().getItem(i7));
    }
}
