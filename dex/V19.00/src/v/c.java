package v;

import com.yalantis.ucrop.view.CropImageView;
import java.util.ArrayList;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final class c {
    public static final int AT_MOST = Integer.MIN_VALUE;
    public static final int EXACTLY = 1073741824;
    public static final int FIXED = -3;
    public static final int MATCH_PARENT = -1;
    public static final int UNSPECIFIED = 0;
    public static final int WRAP_CONTENT = -2;

    /* renamed from: a, reason: collision with root package name */
    public final ArrayList f9325a = new ArrayList();

    /* renamed from: b, reason: collision with root package name */
    public final b f9326b = new b();

    /* renamed from: c, reason: collision with root package name */
    public final u.j f9327c;

    public c(u.j jVar) {
        this.f9327c = jVar;
    }

    public final boolean a(int i7, u.i iVar, r rVar) {
        u.h[] hVarArr = iVar.U;
        u.h hVar = hVarArr[0];
        b bVar = this.f9326b;
        bVar.f9315a = hVar;
        bVar.f9316b = hVarArr[1];
        bVar.f9317c = iVar.s();
        bVar.f9318d = iVar.m();
        bVar.f9323i = false;
        bVar.f9324j = i7;
        u.h hVar2 = bVar.f9315a;
        u.h hVar3 = u.h.MATCH_CONSTRAINT;
        boolean z6 = hVar2 == hVar3;
        boolean z7 = bVar.f9316b == hVar3;
        boolean z8 = z6 && iVar.Y > CropImageView.DEFAULT_ASPECT_RATIO;
        boolean z9 = z7 && iVar.Y > CropImageView.DEFAULT_ASPECT_RATIO;
        int[] iArr = iVar.f9166t;
        if (z8 && iArr[0] == 4) {
            bVar.f9315a = u.h.FIXED;
        }
        if (z9 && iArr[1] == 4) {
            bVar.f9316b = u.h.FIXED;
        }
        rVar.b(iVar, bVar);
        iVar.Q(bVar.f9319e);
        iVar.N(bVar.f9320f);
        iVar.E = bVar.f9322h;
        iVar.K(bVar.f9321g);
        bVar.f9324j = 0;
        return bVar.f9323i;
    }

    public final void b(u.j jVar, int i7, int i8, int i9) {
        int i10 = jVar.f9135d0;
        int i11 = jVar.f9137e0;
        jVar.f9135d0 = 0;
        jVar.f9137e0 = 0;
        jVar.Q(i8);
        jVar.N(i9);
        if (i10 < 0) {
            jVar.f9135d0 = 0;
        } else {
            jVar.f9135d0 = i10;
        }
        if (i11 < 0) {
            jVar.f9137e0 = 0;
        } else {
            jVar.f9137e0 = i11;
        }
        u.j jVar2 = this.f9327c;
        jVar2.f9177y0 = i7;
        jVar2.T();
    }

    public final void c(u.j jVar) {
        ArrayList arrayList = this.f9325a;
        arrayList.clear();
        int size = jVar.f9216v0.size();
        for (int i7 = 0; i7 < size; i7++) {
            u.i iVar = (u.i) jVar.f9216v0.get(i7);
            u.h[] hVarArr = iVar.U;
            u.h hVar = hVarArr[0];
            u.h hVar2 = u.h.MATCH_CONSTRAINT;
            if (hVar == hVar2 || hVarArr[1] == hVar2) {
                arrayList.add(iVar);
            }
        }
        jVar.f9176x0.f9331b = true;
    }
}
