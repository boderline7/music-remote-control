package u;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public class p extends i implements o {

    /* renamed from: v0, reason: collision with root package name */
    public i[] f9210v0 = new i[4];

    /* renamed from: w0, reason: collision with root package name */
    public int f9211w0 = 0;

    public final void T(i iVar) {
        if (iVar == this || iVar == null) {
            return;
        }
        int i7 = this.f9211w0 + 1;
        i[] iVarArr = this.f9210v0;
        if (i7 > iVarArr.length) {
            this.f9210v0 = (i[]) Arrays.copyOf(iVarArr, iVarArr.length * 2);
        }
        i[] iVarArr2 = this.f9210v0;
        int i8 = this.f9211w0;
        iVarArr2[i8] = iVar;
        this.f9211w0 = i8 + 1;
    }

    public final void U(int i7, v.s sVar, ArrayList arrayList) {
        for (int i8 = 0; i8 < this.f9211w0; i8++) {
            i iVar = this.f9210v0[i8];
            ArrayList arrayList2 = sVar.f9369a;
            if (!arrayList2.contains(iVar)) {
                arrayList2.add(iVar);
            }
        }
        for (int i9 = 0; i9 < this.f9211w0; i9++) {
            l6.f.t(this.f9210v0[i9], i7, arrayList, sVar);
        }
    }

    @Override // u.o
    public void a() {
    }

    @Override // u.i
    public void h(i iVar, HashMap hashMap) {
        super.h(iVar, hashMap);
        p pVar = (p) iVar;
        this.f9211w0 = 0;
        int i7 = pVar.f9211w0;
        for (int i8 = 0; i8 < i7; i8++) {
            T((i) hashMap.get(pVar.f9210v0[i8]));
        }
    }
}
