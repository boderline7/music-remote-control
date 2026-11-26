package com.google.android.material.button;

import android.content.Context;
import android.graphics.Canvas;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.view.accessibility.AccessibilityNodeInfo;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.ToggleButton;
import com.google.android.material.internal.k0;
import com.yalantis.ucrop.view.CropImageView;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.TreeMap;
import p0.d1;
import p0.m0;
import p0.o;
import p3.n;
import p3.p;
import q1.h;
import q2.l;
import s.f;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_2_1633180.dex */
public class MaterialButtonToggleGroup extends LinearLayout {

    /* renamed from: k, reason: collision with root package name */
    public static final int f3445k = l.Widget_MaterialComponents_MaterialButtonToggleGroup;

    /* renamed from: a, reason: collision with root package name */
    public final ArrayList f3446a;

    /* renamed from: b, reason: collision with root package name */
    public final v2.a f3447b;

    /* renamed from: c, reason: collision with root package name */
    public final LinkedHashSet f3448c;

    /* renamed from: d, reason: collision with root package name */
    public final f f3449d;

    /* renamed from: e, reason: collision with root package name */
    public Integer[] f3450e;

    /* renamed from: f, reason: collision with root package name */
    public boolean f3451f;

    /* renamed from: g, reason: collision with root package name */
    public boolean f3452g;

    /* renamed from: h, reason: collision with root package name */
    public boolean f3453h;

    /* renamed from: i, reason: collision with root package name */
    public final int f3454i;

    /* renamed from: j, reason: collision with root package name */
    public HashSet f3455j;

    public MaterialButtonToggleGroup(Context context) {
        this(context, null);
    }

    public MaterialButtonToggleGroup(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, q2.c.materialButtonToggleGroupStyle);
    }

    /* JADX WARN: Illegal instructions before constructor call */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public MaterialButtonToggleGroup(android.content.Context r7, android.util.AttributeSet r8, int r9) {
        /*
            r6 = this;
            int r4 = com.google.android.material.button.MaterialButtonToggleGroup.f3445k
            android.content.Context r7 = t3.a.a(r7, r8, r9, r4)
            r6.<init>(r7, r8, r9)
            java.util.ArrayList r7 = new java.util.ArrayList
            r7.<init>()
            r6.f3446a = r7
            v2.a r7 = new v2.a
            r7.<init>(r6)
            r6.f3447b = r7
            java.util.LinkedHashSet r7 = new java.util.LinkedHashSet
            r7.<init>()
            r6.f3448c = r7
            s.f r7 = new s.f
            r0 = 4
            r7.<init>(r0, r6)
            r6.f3449d = r7
            r7 = 0
            r6.f3451f = r7
            java.util.HashSet r0 = new java.util.HashSet
            r0.<init>()
            r6.f3455j = r0
            android.content.Context r0 = r6.getContext()
            int[] r2 = q2.m.MaterialButtonToggleGroup
            int[] r5 = new int[r7]
            r1 = r8
            r3 = r9
            android.content.res.TypedArray r8 = com.google.android.material.internal.e0.d(r0, r1, r2, r3, r4, r5)
            int r9 = q2.m.MaterialButtonToggleGroup_singleSelection
            boolean r9 = r8.getBoolean(r9, r7)
            r6.setSingleSelection(r9)
            int r9 = q2.m.MaterialButtonToggleGroup_checkedButton
            r0 = -1
            int r9 = r8.getResourceId(r9, r0)
            r6.f3454i = r9
            int r9 = q2.m.MaterialButtonToggleGroup_selectionRequired
            boolean r7 = r8.getBoolean(r9, r7)
            r6.f3453h = r7
            r7 = 1
            r6.setChildrenDrawingOrderEnabled(r7)
            int r9 = q2.m.MaterialButtonToggleGroup_android_enabled
            boolean r9 = r8.getBoolean(r9, r7)
            r6.setEnabled(r9)
            r8.recycle()
            int r8 = p0.d1.OVER_SCROLL_ALWAYS
            p0.l0.s(r6, r7)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.material.button.MaterialButtonToggleGroup.<init>(android.content.Context, android.util.AttributeSet, int):void");
    }

    private int getFirstVisibleChildIndex() {
        int childCount = getChildCount();
        for (int i7 = 0; i7 < childCount; i7++) {
            if (c(i7)) {
                return i7;
            }
        }
        return -1;
    }

    private int getLastVisibleChildIndex() {
        for (int childCount = getChildCount() - 1; childCount >= 0; childCount--) {
            if (c(childCount)) {
                return childCount;
            }
        }
        return -1;
    }

    private int getVisibleButtonCount() {
        int i7 = 0;
        for (int i8 = 0; i8 < getChildCount(); i8++) {
            if ((getChildAt(i8) instanceof MaterialButton) && c(i8)) {
                i7++;
            }
        }
        return i7;
    }

    private void setGeneratedIdIfNeeded(MaterialButton materialButton) {
        if (materialButton.getId() == -1) {
            int i7 = d1.OVER_SCROLL_ALWAYS;
            materialButton.setId(m0.a());
        }
    }

    private void setupButtonChild(MaterialButton materialButton) {
        materialButton.setMaxLines(1);
        materialButton.setEllipsize(TextUtils.TruncateAt.END);
        materialButton.setCheckable(true);
        materialButton.setOnPressedChangeListenerInternal(this.f3447b);
        materialButton.setShouldDrawSurfaceColorStroke(true);
    }

    public final void a() {
        int firstVisibleChildIndex = getFirstVisibleChildIndex();
        if (firstVisibleChildIndex == -1) {
            return;
        }
        for (int i7 = firstVisibleChildIndex + 1; i7 < getChildCount(); i7++) {
            MaterialButton materialButton = (MaterialButton) getChildAt(i7);
            int min = Math.min(materialButton.getStrokeWidth(), ((MaterialButton) getChildAt(i7 - 1)).getStrokeWidth());
            ViewGroup.LayoutParams layoutParams = materialButton.getLayoutParams();
            LinearLayout.LayoutParams layoutParams2 = layoutParams instanceof LinearLayout.LayoutParams ? (LinearLayout.LayoutParams) layoutParams : new LinearLayout.LayoutParams(layoutParams.width, layoutParams.height);
            if (getOrientation() == 0) {
                o.g(layoutParams2, 0);
                o.h(layoutParams2, -min);
                layoutParams2.topMargin = 0;
            } else {
                layoutParams2.bottomMargin = 0;
                layoutParams2.topMargin = -min;
                o.h(layoutParams2, 0);
            }
            materialButton.setLayoutParams(layoutParams2);
        }
        if (getChildCount() == 0 || firstVisibleChildIndex == -1) {
            return;
        }
        LinearLayout.LayoutParams layoutParams3 = (LinearLayout.LayoutParams) ((MaterialButton) getChildAt(firstVisibleChildIndex)).getLayoutParams();
        if (getOrientation() == 1) {
            layoutParams3.topMargin = 0;
            layoutParams3.bottomMargin = 0;
        } else {
            o.g(layoutParams3, 0);
            o.h(layoutParams3, 0);
            layoutParams3.leftMargin = 0;
            layoutParams3.rightMargin = 0;
        }
    }

    @Override // android.view.ViewGroup
    public final void addView(View view, int i7, ViewGroup.LayoutParams layoutParams) {
        if (view instanceof MaterialButton) {
            super.addView(view, i7, layoutParams);
            MaterialButton materialButton = (MaterialButton) view;
            setGeneratedIdIfNeeded(materialButton);
            setupButtonChild(materialButton);
            b(materialButton.getId(), materialButton.isChecked());
            p shapeAppearanceModel = materialButton.getShapeAppearanceModel();
            this.f3446a.add(new d(shapeAppearanceModel.f8305e, shapeAppearanceModel.f8308h, shapeAppearanceModel.f8306f, shapeAppearanceModel.f8307g));
            materialButton.setEnabled(isEnabled());
            d1.o(materialButton, new h(2, this));
        }
    }

    public final void b(int i7, boolean z6) {
        if (i7 == -1) {
            return;
        }
        HashSet hashSet = new HashSet(this.f3455j);
        if (z6 && !hashSet.contains(Integer.valueOf(i7))) {
            if (this.f3452g && !hashSet.isEmpty()) {
                hashSet.clear();
            }
            hashSet.add(Integer.valueOf(i7));
        } else {
            if (z6 || !hashSet.contains(Integer.valueOf(i7))) {
                return;
            }
            if (!this.f3453h || hashSet.size() > 1) {
                hashSet.remove(Integer.valueOf(i7));
            }
        }
        d(hashSet);
    }

    public final boolean c(int i7) {
        return getChildAt(i7).getVisibility() != 8;
    }

    public final void d(Set set) {
        HashSet hashSet = this.f3455j;
        this.f3455j = new HashSet(set);
        for (int i7 = 0; i7 < getChildCount(); i7++) {
            int id = ((MaterialButton) getChildAt(i7)).getId();
            boolean contains = set.contains(Integer.valueOf(id));
            View findViewById = findViewById(id);
            if (findViewById instanceof MaterialButton) {
                this.f3451f = true;
                ((MaterialButton) findViewById).setChecked(contains);
                this.f3451f = false;
            }
            if (hashSet.contains(Integer.valueOf(id)) != set.contains(Integer.valueOf(id))) {
                set.contains(Integer.valueOf(id));
                Iterator it = this.f3448c.iterator();
                while (it.hasNext()) {
                    ((com.google.android.material.timepicker.h) it.next()).a();
                }
            }
        }
        invalidate();
    }

    @Override // android.view.ViewGroup, android.view.View
    public final void dispatchDraw(Canvas canvas) {
        TreeMap treeMap = new TreeMap(this.f3449d);
        int childCount = getChildCount();
        for (int i7 = 0; i7 < childCount; i7++) {
            treeMap.put((MaterialButton) getChildAt(i7), Integer.valueOf(i7));
        }
        this.f3450e = (Integer[]) treeMap.values().toArray(new Integer[0]);
        super.dispatchDraw(canvas);
    }

    public final void e() {
        d dVar;
        int childCount = getChildCount();
        int firstVisibleChildIndex = getFirstVisibleChildIndex();
        int lastVisibleChildIndex = getLastVisibleChildIndex();
        for (int i7 = 0; i7 < childCount; i7++) {
            MaterialButton materialButton = (MaterialButton) getChildAt(i7);
            if (materialButton.getVisibility() != 8) {
                n g7 = materialButton.getShapeAppearanceModel().g();
                d dVar2 = (d) this.f3446a.get(i7);
                if (firstVisibleChildIndex != lastVisibleChildIndex) {
                    boolean z6 = getOrientation() == 0;
                    p3.a aVar = d.f3476e;
                    if (i7 == firstVisibleChildIndex) {
                        dVar = z6 ? k0.f(this) ? new d(aVar, aVar, dVar2.f3478b, dVar2.f3479c) : new d(dVar2.f3477a, dVar2.f3480d, aVar, aVar) : new d(dVar2.f3477a, aVar, dVar2.f3478b, aVar);
                    } else if (i7 == lastVisibleChildIndex) {
                        dVar = z6 ? k0.f(this) ? new d(dVar2.f3477a, dVar2.f3480d, aVar, aVar) : new d(aVar, aVar, dVar2.f3478b, dVar2.f3479c) : new d(aVar, dVar2.f3480d, aVar, dVar2.f3479c);
                    } else {
                        dVar2 = null;
                    }
                    dVar2 = dVar;
                }
                if (dVar2 == null) {
                    g7.c(CropImageView.DEFAULT_ASPECT_RATIO);
                } else {
                    g7.f8293e = dVar2.f3477a;
                    g7.f8296h = dVar2.f3480d;
                    g7.f8294f = dVar2.f3478b;
                    g7.f8295g = dVar2.f3479c;
                }
                materialButton.setShapeAppearanceModel(g7.a());
            }
        }
    }

    public int getCheckedButtonId() {
        if (!this.f3452g || this.f3455j.isEmpty()) {
            return -1;
        }
        return ((Integer) this.f3455j.iterator().next()).intValue();
    }

    public List<Integer> getCheckedButtonIds() {
        ArrayList arrayList = new ArrayList();
        for (int i7 = 0; i7 < getChildCount(); i7++) {
            int id = ((MaterialButton) getChildAt(i7)).getId();
            if (this.f3455j.contains(Integer.valueOf(id))) {
                arrayList.add(Integer.valueOf(id));
            }
        }
        return arrayList;
    }

    @Override // android.view.ViewGroup
    public final int getChildDrawingOrder(int i7, int i8) {
        Integer[] numArr = this.f3450e;
        return (numArr == null || i8 >= numArr.length) ? i8 : numArr[i8].intValue();
    }

    @Override // android.view.View
    public final void onFinishInflate() {
        super.onFinishInflate();
        int i7 = this.f3454i;
        if (i7 != -1) {
            d(Collections.singleton(Integer.valueOf(i7)));
        }
    }

    @Override // android.view.View
    public final void onInitializeAccessibilityNodeInfo(AccessibilityNodeInfo accessibilityNodeInfo) {
        super.onInitializeAccessibilityNodeInfo(accessibilityNodeInfo);
        accessibilityNodeInfo.setCollectionInfo((AccessibilityNodeInfo.CollectionInfo) q0.h.a(1, getVisibleButtonCount(), this.f3452g ? 1 : 2).f8425a);
    }

    @Override // android.widget.LinearLayout, android.view.View
    public final void onMeasure(int i7, int i8) {
        e();
        a();
        super.onMeasure(i7, i8);
    }

    @Override // android.view.ViewGroup
    public final void onViewRemoved(View view) {
        super.onViewRemoved(view);
        if (view instanceof MaterialButton) {
            ((MaterialButton) view).setOnPressedChangeListenerInternal(null);
        }
        int indexOfChild = indexOfChild(view);
        if (indexOfChild >= 0) {
            this.f3446a.remove(indexOfChild);
        }
        e();
        a();
    }

    @Override // android.view.View
    public void setEnabled(boolean z6) {
        super.setEnabled(z6);
        for (int i7 = 0; i7 < getChildCount(); i7++) {
            ((MaterialButton) getChildAt(i7)).setEnabled(z6);
        }
    }

    public void setSelectionRequired(boolean z6) {
        this.f3453h = z6;
    }

    public void setSingleSelection(int i7) {
        setSingleSelection(getResources().getBoolean(i7));
    }

    public void setSingleSelection(boolean z6) {
        if (this.f3452g != z6) {
            this.f3452g = z6;
            d(new HashSet());
        }
        for (int i7 = 0; i7 < getChildCount(); i7++) {
            ((MaterialButton) getChildAt(i7)).setA11yClassName((this.f3452g ? RadioButton.class : ToggleButton.class).getName());
        }
    }
}
