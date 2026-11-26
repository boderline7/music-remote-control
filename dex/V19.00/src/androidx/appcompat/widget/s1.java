package androidx.appcompat.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListAdapter;
import android.widget.ListView;
import java.lang.reflect.InvocationTargetException;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_2_1633180.dex */
public class s1 extends ListView {
    public static final int INVALID_POSITION = -1;
    public static final int NO_POSITION = -1;

    /* renamed from: a, reason: collision with root package name */
    public final Rect f1397a;

    /* renamed from: b, reason: collision with root package name */
    public int f1398b;

    /* renamed from: c, reason: collision with root package name */
    public int f1399c;

    /* renamed from: d, reason: collision with root package name */
    public int f1400d;

    /* renamed from: e, reason: collision with root package name */
    public int f1401e;

    /* renamed from: f, reason: collision with root package name */
    public int f1402f;

    /* renamed from: g, reason: collision with root package name */
    public q1 f1403g;

    /* renamed from: h, reason: collision with root package name */
    public boolean f1404h;

    /* renamed from: i, reason: collision with root package name */
    public final boolean f1405i;

    /* renamed from: j, reason: collision with root package name */
    public boolean f1406j;

    /* renamed from: k, reason: collision with root package name */
    public androidx.core.widget.i f1407k;

    /* renamed from: l, reason: collision with root package name */
    public androidx.appcompat.app.s0 f1408l;

    public s1(Context context, boolean z6) {
        super(context, null, f.a.dropDownListViewStyle);
        this.f1397a = new Rect();
        this.f1398b = 0;
        this.f1399c = 0;
        this.f1400d = 0;
        this.f1401e = 0;
        this.f1405i = z6;
        setCacheColorHint(0);
    }

    public final int a(int i7, int i8) {
        int listPaddingTop = getListPaddingTop();
        int listPaddingBottom = getListPaddingBottom();
        int dividerHeight = getDividerHeight();
        Drawable divider = getDivider();
        ListAdapter adapter = getAdapter();
        if (adapter == null) {
            return listPaddingTop + listPaddingBottom;
        }
        int i9 = listPaddingTop + listPaddingBottom;
        if (dividerHeight <= 0 || divider == null) {
            dividerHeight = 0;
        }
        int count = adapter.getCount();
        int i10 = 0;
        View view = null;
        for (int i11 = 0; i11 < count; i11++) {
            int itemViewType = adapter.getItemViewType(i11);
            if (itemViewType != i10) {
                view = null;
                i10 = itemViewType;
            }
            view = adapter.getView(i11, view, this);
            ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
            if (layoutParams == null) {
                layoutParams = generateDefaultLayoutParams();
                view.setLayoutParams(layoutParams);
            }
            int i12 = layoutParams.height;
            view.measure(i7, i12 > 0 ? View.MeasureSpec.makeMeasureSpec(i12, v.c.EXACTLY) : View.MeasureSpec.makeMeasureSpec(0, 0));
            view.forceLayout();
            if (i11 > 0) {
                i9 += dividerHeight;
            }
            i9 += view.getMeasuredHeight();
            if (i9 >= i8) {
                return i8;
            }
        }
        return i9;
    }

    /* JADX WARN: Removed duplicated region for block: B:11:0x0164  */
    /* JADX WARN: Removed duplicated region for block: B:17:0x0179  */
    /* JADX WARN: Removed duplicated region for block: B:25:0x015f  */
    /* JADX WARN: Removed duplicated region for block: B:9:0x0147 A[ADDED_TO_REGION] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final boolean b(android.view.MotionEvent r17, int r18) {
        /*
            Method dump skipped, instructions count: 392
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.appcompat.widget.s1.b(android.view.MotionEvent, int):boolean");
    }

    @Override // android.widget.ListView, android.widget.AbsListView, android.view.ViewGroup, android.view.View
    public final void dispatchDraw(Canvas canvas) {
        Drawable selector;
        Rect rect = this.f1397a;
        if (!rect.isEmpty() && (selector = getSelector()) != null) {
            selector.setBounds(rect);
            selector.draw(canvas);
        }
        super.dispatchDraw(canvas);
    }

    @Override // android.widget.AbsListView, android.view.ViewGroup, android.view.View
    public final void drawableStateChanged() {
        if (this.f1408l != null) {
            return;
        }
        super.drawableStateChanged();
        q1 q1Var = this.f1403g;
        if (q1Var != null) {
            q1Var.f1379b = true;
        }
        Drawable selector = getSelector();
        if (selector != null && this.f1406j && isPressed()) {
            selector.setState(getDrawableState());
        }
    }

    @Override // android.view.ViewGroup, android.view.View
    public final boolean hasFocus() {
        return this.f1405i || super.hasFocus();
    }

    @Override // android.view.View
    public final boolean hasWindowFocus() {
        return this.f1405i || super.hasWindowFocus();
    }

    @Override // android.view.View
    public final boolean isFocused() {
        return this.f1405i || super.isFocused();
    }

    @Override // android.view.View
    public final boolean isInTouchMode() {
        return (this.f1405i && this.f1404h) || super.isInTouchMode();
    }

    @Override // android.widget.ListView, android.widget.AbsListView, android.widget.AdapterView, android.view.ViewGroup, android.view.View
    public final void onDetachedFromWindow() {
        this.f1408l = null;
        super.onDetachedFromWindow();
    }

    @Override // android.view.View
    public boolean onHoverEvent(MotionEvent motionEvent) {
        int i7 = Build.VERSION.SDK_INT;
        int actionMasked = motionEvent.getActionMasked();
        int i8 = 2;
        if (actionMasked == 10 && this.f1408l == null) {
            androidx.appcompat.app.s0 s0Var = new androidx.appcompat.app.s0(i8, this);
            this.f1408l = s0Var;
            post(s0Var);
        }
        boolean onHoverEvent = super.onHoverEvent(motionEvent);
        if (actionMasked == 9 || actionMasked == 7) {
            int pointToPosition = pointToPosition((int) motionEvent.getX(), (int) motionEvent.getY());
            if (pointToPosition != -1 && pointToPosition != getSelectedItemPosition()) {
                View childAt = getChildAt(pointToPosition - getFirstVisiblePosition());
                if (childAt.isEnabled()) {
                    requestFocus();
                    if (i7 < 30 || !o1.f1368d) {
                        setSelectionFromTop(pointToPosition, childAt.getTop() - getTop());
                    } else {
                        try {
                            o1.f1365a.invoke(this, Integer.valueOf(pointToPosition), childAt, Boolean.FALSE, -1, -1);
                            o1.f1366b.invoke(this, Integer.valueOf(pointToPosition));
                            o1.f1367c.invoke(this, Integer.valueOf(pointToPosition));
                        } catch (IllegalAccessException e7) {
                            e7.printStackTrace();
                        } catch (InvocationTargetException e8) {
                            e8.printStackTrace();
                        }
                    }
                }
                Drawable selector = getSelector();
                if (selector != null && this.f1406j && isPressed()) {
                    selector.setState(getDrawableState());
                }
            }
        } else {
            setSelection(-1);
        }
        return onHoverEvent;
    }

    @Override // android.widget.AbsListView, android.view.View
    public final boolean onTouchEvent(MotionEvent motionEvent) {
        if (motionEvent.getAction() == 0) {
            this.f1402f = pointToPosition((int) motionEvent.getX(), (int) motionEvent.getY());
        }
        androidx.appcompat.app.s0 s0Var = this.f1408l;
        if (s0Var != null) {
            s1 s1Var = (s1) s0Var.f852b;
            s1Var.f1408l = null;
            s1Var.removeCallbacks(s0Var);
        }
        return super.onTouchEvent(motionEvent);
    }

    public void setListSelectionHidden(boolean z6) {
        this.f1404h = z6;
    }

    @Override // android.widget.AbsListView
    public void setSelector(Drawable drawable) {
        q1 q1Var;
        if (drawable != null) {
            q1Var = new q1(drawable);
            q1Var.f1379b = true;
        } else {
            q1Var = null;
        }
        this.f1403g = q1Var;
        super.setSelector(q1Var);
        Rect rect = new Rect();
        if (drawable != null) {
            drawable.getPadding(rect);
        }
        this.f1398b = rect.left;
        this.f1399c = rect.top;
        this.f1400d = rect.right;
        this.f1401e = rect.bottom;
    }
}
