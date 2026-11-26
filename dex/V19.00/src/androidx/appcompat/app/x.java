package androidx.appcompat.app;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_2_1633180.dex */
public final class x implements Runnable {

    /* renamed from: a, reason: collision with root package name */
    public final /* synthetic */ int f879a;

    /* renamed from: b, reason: collision with root package name */
    public final /* synthetic */ j0 f880b;

    public /* synthetic */ x(j0 j0Var, int i7) {
        this.f879a = i7;
        this.f880b = j0Var;
    }

    /* JADX WARN: Code restructure failed: missing block: B:11:0x0027, code lost:
    
        if (p0.o0.c(r1) != false) goto L15;
     */
    @Override // java.lang.Runnable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void run() {
        /*
            r6 = this;
            r0 = 1
            int r1 = r6.f879a
            r2 = 0
            androidx.appcompat.app.j0 r3 = r6.f880b
            switch(r1) {
                case 0: goto L54;
                default: goto L9;
            }
        L9:
            android.widget.PopupWindow r1 = r3.f790w
            androidx.appcompat.widget.ActionBarContextView r4 = r3.f789v
            r5 = 55
            r1.showAtLocation(r4, r5, r2, r2)
            p0.l1 r1 = r3.f792y
            if (r1 == 0) goto L19
            r1.b()
        L19:
            boolean r1 = r3.f793z
            if (r1 == 0) goto L2a
            android.view.ViewGroup r1 = r3.A
            if (r1 == 0) goto L2a
            int r4 = p0.d1.OVER_SCROLL_ALWAYS
            boolean r1 = p0.o0.c(r1)
            if (r1 == 0) goto L2a
            goto L2b
        L2a:
            r0 = r2
        L2b:
            r1 = 1065353216(0x3f800000, float:1.0)
            if (r0 == 0) goto L49
            androidx.appcompat.widget.ActionBarContextView r0 = r3.f789v
            r4 = 0
            r0.setAlpha(r4)
            androidx.appcompat.widget.ActionBarContextView r0 = r3.f789v
            p0.l1 r0 = p0.d1.a(r0)
            r0.a(r1)
            r3.f792y = r0
            androidx.appcompat.app.z r1 = new androidx.appcompat.app.z
            r1.<init>(r2, r6)
            r0.d(r1)
            goto L53
        L49:
            androidx.appcompat.widget.ActionBarContextView r0 = r3.f789v
            r0.setAlpha(r1)
            androidx.appcompat.widget.ActionBarContextView r0 = r3.f789v
            r0.setVisibility(r2)
        L53:
            return
        L54:
            int r1 = r3.Z
            r0 = r0 & r1
            if (r0 == 0) goto L5c
            r3.w(r2)
        L5c:
            int r0 = r3.Z
            r0 = r0 & 4096(0x1000, float:5.74E-42)
            if (r0 == 0) goto L67
            r0 = 108(0x6c, float:1.51E-43)
            r3.w(r0)
        L67:
            r3.Y = r2
            r3.Z = r2
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.appcompat.app.x.run():void");
    }
}
