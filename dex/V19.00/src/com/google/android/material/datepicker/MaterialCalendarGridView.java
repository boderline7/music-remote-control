package com.google.android.material.datepicker;

import android.R;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.View;
import android.widget.GridView;
import android.widget.ListAdapter;
import java.util.Calendar;
import java.util.Iterator;
import p0.d1;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_2_1633180.dex */
final class MaterialCalendarGridView extends GridView {

    /* renamed from: a, reason: collision with root package name */
    public final Calendar f3577a;

    /* renamed from: b, reason: collision with root package name */
    public final boolean f3578b;

    public MaterialCalendarGridView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet, 0);
        this.f3577a = j0.g(null);
        if (x.g(getContext(), R.attr.windowFullscreen)) {
            setNextFocusLeftId(q2.g.cancel_button);
            setNextFocusRightId(q2.g.confirm_button);
        }
        this.f3578b = x.g(getContext(), q2.c.nestedScrollable);
        d1.o(this, new m(3, this));
    }

    public final a0 a() {
        return (a0) super.getAdapter();
    }

    public final View b(int i7) {
        return getChildAt(i7 - getFirstVisiblePosition());
    }

    @Override // android.widget.GridView, android.widget.AdapterView
    public final ListAdapter getAdapter() {
        return (a0) super.getAdapter();
    }

    @Override // android.widget.GridView, android.widget.AdapterView
    /* renamed from: getAdapter, reason: avoid collision after fix types in other method */
    public final ListAdapter getAdapter2() {
        return (a0) super.getAdapter();
    }

    @Override // android.widget.AbsListView, android.view.ViewGroup, android.view.View
    public final void onAttachedToWindow() {
        super.onAttachedToWindow();
        ((a0) super.getAdapter()).notifyDataSetChanged();
    }

    @Override // android.view.View
    public final void onDraw(Canvas canvas) {
        int a7;
        int width;
        int a8;
        int width2;
        int i7;
        int i8;
        MaterialCalendarGridView materialCalendarGridView = this;
        super.onDraw(canvas);
        a0 a0Var = (a0) super.getAdapter();
        DateSelector dateSelector = a0Var.f3595b;
        android.support.v4.media.p pVar = a0Var.f3597d;
        int max = Math.max(a0Var.a(), getFirstVisiblePosition());
        int min = Math.min(a0Var.c(), getLastVisiblePosition());
        Long item = a0Var.getItem(max);
        Long item2 = a0Var.getItem(min);
        Iterator it = dateSelector.e().iterator();
        while (it.hasNext()) {
            o0.c cVar = (o0.c) it.next();
            Object obj = cVar.f7788a;
            if (obj != null) {
                Object obj2 = cVar.f7789b;
                if (obj2 != null) {
                    long longValue = ((Long) obj).longValue();
                    long longValue2 = ((Long) obj2).longValue();
                    Long valueOf = Long.valueOf(longValue);
                    Long valueOf2 = Long.valueOf(longValue2);
                    if (item == null || item2 == null || valueOf == null || valueOf2 == null || valueOf.longValue() > item2.longValue() || valueOf2.longValue() < item.longValue()) {
                        materialCalendarGridView = this;
                        max = max;
                        a0Var = a0Var;
                        it = it;
                    } else {
                        boolean f7 = com.google.android.material.internal.k0.f(this);
                        long longValue3 = item.longValue();
                        Calendar calendar = materialCalendarGridView.f3577a;
                        Month month = a0Var.f3594a;
                        if (longValue < longValue3) {
                            width = max % month.f3582d == 0 ? 0 : !f7 ? materialCalendarGridView.b(max - 1).getRight() : materialCalendarGridView.b(max - 1).getLeft();
                            a7 = max;
                        } else {
                            calendar.setTimeInMillis(longValue);
                            a7 = a0Var.a() + (calendar.get(5) - 1);
                            View b7 = materialCalendarGridView.b(a7);
                            width = (b7.getWidth() / 2) + b7.getLeft();
                        }
                        if (longValue2 > item2.longValue()) {
                            width2 = (min + 1) % month.f3582d == 0 ? getWidth() : !f7 ? materialCalendarGridView.b(min).getRight() : materialCalendarGridView.b(min).getLeft();
                            a8 = min;
                        } else {
                            calendar.setTimeInMillis(longValue2);
                            a8 = a0Var.a() + (calendar.get(5) - 1);
                            View b8 = materialCalendarGridView.b(a8);
                            width2 = (b8.getWidth() / 2) + b8.getLeft();
                        }
                        int itemId = (int) a0Var.getItemId(a7);
                        int itemId2 = (int) a0Var.getItemId(a8);
                        while (itemId <= itemId2) {
                            int numColumns = getNumColumns() * itemId;
                            a0 a0Var2 = a0Var;
                            int numColumns2 = (getNumColumns() + numColumns) - 1;
                            View b9 = materialCalendarGridView.b(numColumns);
                            int top = ((Rect) ((androidx.appcompat.widget.w) pVar.f552a).f1436c).top + b9.getTop();
                            Iterator it2 = it;
                            int bottom = b9.getBottom() - ((Rect) ((androidx.appcompat.widget.w) pVar.f552a).f1436c).bottom;
                            if (f7) {
                                int i9 = a8 > numColumns2 ? 0 : width2;
                                int width3 = numColumns > a7 ? getWidth() : width;
                                i7 = i9;
                                i8 = width3;
                            } else {
                                i7 = numColumns > a7 ? 0 : width;
                                i8 = a8 > numColumns2 ? getWidth() : width2;
                            }
                            canvas.drawRect(i7, top, i8, bottom, (Paint) pVar.f559h);
                            itemId++;
                            materialCalendarGridView = this;
                            max = max;
                            a0Var = a0Var2;
                            it = it2;
                        }
                    }
                }
            }
            materialCalendarGridView = this;
        }
    }

    @Override // android.widget.GridView, android.widget.AbsListView, android.view.View
    public final void onFocusChanged(boolean z6, int i7, Rect rect) {
        if (!z6) {
            super.onFocusChanged(false, i7, rect);
            return;
        }
        if (i7 == 33) {
            setSelection(((a0) super.getAdapter()).c());
        } else if (i7 == 130) {
            setSelection(((a0) super.getAdapter()).a());
        } else {
            super.onFocusChanged(true, i7, rect);
        }
    }

    @Override // android.widget.GridView, android.widget.AbsListView, android.view.View, android.view.KeyEvent.Callback
    public final boolean onKeyDown(int i7, KeyEvent keyEvent) {
        if (!super.onKeyDown(i7, keyEvent)) {
            return false;
        }
        if (getSelectedItemPosition() == -1 || getSelectedItemPosition() >= ((a0) super.getAdapter()).a()) {
            return true;
        }
        if (19 != i7) {
            return false;
        }
        setSelection(((a0) super.getAdapter()).a());
        return true;
    }

    @Override // android.widget.GridView, android.widget.AbsListView, android.view.View
    public final void onMeasure(int i7, int i8) {
        if (!this.f3578b) {
            super.onMeasure(i7, i8);
            return;
        }
        super.onMeasure(i7, View.MeasureSpec.makeMeasureSpec(d1.MEASURED_SIZE_MASK, Integer.MIN_VALUE));
        getLayoutParams().height = getMeasuredHeight();
    }

    @Override // android.widget.AdapterView
    public final void setAdapter(ListAdapter listAdapter) {
        if (!(listAdapter instanceof a0)) {
            throw new IllegalArgumentException(String.format("%1$s must have its Adapter set to a %2$s", MaterialCalendarGridView.class.getCanonicalName(), a0.class.getCanonicalName()));
        }
        super.setAdapter(listAdapter);
    }

    @Override // android.widget.GridView, android.widget.AdapterView
    public final void setSelection(int i7) {
        if (i7 < ((a0) super.getAdapter()).a()) {
            super.setSelection(((a0) super.getAdapter()).a());
        } else {
            super.setSelection(i7);
        }
    }
}
