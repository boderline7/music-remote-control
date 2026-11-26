package com.google.android.material.search;

import android.view.View;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_2_1633180.dex */
public final /* synthetic */ class c implements View.OnTouchListener {

    /* renamed from: a, reason: collision with root package name */
    public final /* synthetic */ int f4082a;

    /* renamed from: b, reason: collision with root package name */
    public final /* synthetic */ Object f4083b;

    public /* synthetic */ c(int i7, Object obj) {
        this.f4082a = i7;
        this.f4083b = obj;
    }

    /* JADX WARN: Code restructure failed: missing block: B:9:0x0032, code lost:
    
        if (r11 != 6) goto L23;
     */
    @Override // android.view.View.OnTouchListener
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final boolean onTouch(android.view.View r10, android.view.MotionEvent r11) {
        /*
            r9 = this;
            int r10 = r9.f4082a
            r0 = 1
            java.lang.Object r1 = r9.f4083b
            r2 = 0
            switch(r10) {
                case 0: goto Laa;
                case 1: goto L7e;
                default: goto L9;
            }
        L9:
            com.navi.tool.view.ZoomableImageView r1 = (com.navi.tool.view.ZoomableImageView) r1
            android.view.ScaleGestureDetector r10 = r1.f6093h
            r10.onTouchEvent(r11)
            android.view.GestureDetector r10 = r1.f6094i
            r10.onTouchEvent(r11)
            android.graphics.PointF r10 = new android.graphics.PointF
            float r3 = r11.getX()
            float r4 = r11.getY()
            r10.<init>(r3, r4)
            int r11 = r11.getActionMasked()
            android.graphics.Matrix r3 = r1.f6089d
            android.graphics.PointF r4 = r1.f6091f
            if (r11 == 0) goto L6d
            if (r11 == r0) goto L6a
            r5 = 2
            if (r11 == r5) goto L35
            r10 = 6
            if (r11 == r10) goto L6a
            goto L77
        L35:
            int r11 = r1.f6102q
            if (r11 != r0) goto L77
            float r11 = r10.x
            float r2 = r4.x
            float r11 = r11 - r2
            float r2 = r10.y
            float r5 = r4.y
            float r2 = r2 - r5
            int r5 = r1.f6095j
            float r5 = (float) r5
            float r6 = r1.f6100o
            float r7 = r1.f6097l
            float r6 = r6 * r7
            int r5 = (r6 > r5 ? 1 : (r6 == r5 ? 0 : -1))
            r6 = 0
            if (r5 > 0) goto L51
            r11 = r6
        L51:
            int r5 = r1.f6096k
            float r5 = (float) r5
            float r8 = r1.f6101p
            float r8 = r8 * r7
            int r5 = (r8 > r5 ? 1 : (r8 == r5 ? 0 : -1))
            if (r5 > 0) goto L5c
            r2 = r6
        L5c:
            r3.postTranslate(r11, r2)
            r1.d()
            float r11 = r10.x
            float r10 = r10.y
            r4.set(r11, r10)
            goto L77
        L6a:
            r1.f6102q = r2
            goto L77
        L6d:
            r4.set(r10)
            android.graphics.PointF r10 = r1.f6092g
            r10.set(r4)
            r1.f6102q = r0
        L77:
            r1.setImageMatrix(r3)
            r1.invalidate()
            return r0
        L7e:
            com.google.android.material.textfield.i r1 = (com.google.android.material.textfield.i) r1
            r1.getClass()
            int r10 = r11.getAction()
            if (r10 != r0) goto La9
            long r10 = java.lang.System.currentTimeMillis()
            long r3 = r1.f4351o
            long r10 = r10 - r3
            r3 = 0
            int r3 = (r10 > r3 ? 1 : (r10 == r3 ? 0 : -1))
            if (r3 < 0) goto L9c
            r3 = 300(0x12c, double:1.48E-321)
            int r10 = (r10 > r3 ? 1 : (r10 == r3 ? 0 : -1))
            if (r10 <= 0) goto L9e
        L9c:
            r1.f4349m = r2
        L9e:
            r1.u()
            r1.f4349m = r0
            long r10 = java.lang.System.currentTimeMillis()
            r1.f4351o = r10
        La9:
            return r2
        Laa:
            com.google.android.material.search.SearchView r1 = (com.google.android.material.search.SearchView) r1
            int r10 = com.google.android.material.search.SearchView.D
            boolean r10 = r1.h()
            if (r10 == 0) goto Lb7
            r1.f()
        Lb7:
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.material.search.c.onTouch(android.view.View, android.view.MotionEvent):boolean");
    }
}
