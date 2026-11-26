package s;

import com.yalantis.ucrop.view.CropImageView;
import java.util.Arrays;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final class g extends c {

    /* renamed from: f, reason: collision with root package name */
    public i[] f8632f;

    /* renamed from: g, reason: collision with root package name */
    public i[] f8633g;

    /* renamed from: h, reason: collision with root package name */
    public int f8634h;

    /* renamed from: i, reason: collision with root package name */
    public androidx.appcompat.app.g f8635i;

    @Override // s.c
    public final i d(boolean[] zArr) {
        int i7 = -1;
        for (int i8 = 0; i8 < this.f8634h; i8++) {
            i[] iVarArr = this.f8632f;
            i iVar = iVarArr[i8];
            if (!zArr[iVar.f8638b]) {
                androidx.appcompat.app.g gVar = this.f8635i;
                gVar.f714b = iVar;
                int i9 = 8;
                if (i7 == -1) {
                    while (i9 >= 0) {
                        float f7 = ((i) gVar.f714b).f8644h[i9];
                        if (f7 <= CropImageView.DEFAULT_ASPECT_RATIO) {
                            if (f7 < CropImageView.DEFAULT_ASPECT_RATIO) {
                                i7 = i8;
                                break;
                            }
                            i9--;
                        }
                    }
                } else {
                    i iVar2 = iVarArr[i7];
                    while (true) {
                        if (i9 >= 0) {
                            float f8 = iVar2.f8644h[i9];
                            float f9 = ((i) gVar.f714b).f8644h[i9];
                            if (f9 == f8) {
                                i9--;
                            } else if (f9 >= f8) {
                            }
                        }
                    }
                }
            }
        }
        if (i7 == -1) {
            return null;
        }
        return this.f8632f[i7];
    }

    @Override // s.c
    public final boolean e() {
        return this.f8634h == 0;
    }

    @Override // s.c
    public final void i(d dVar, c cVar, boolean z6) {
        i iVar = cVar.f8605a;
        if (iVar == null) {
            return;
        }
        b bVar = cVar.f8608d;
        int k7 = bVar.k();
        for (int i7 = 0; i7 < k7; i7++) {
            i d7 = bVar.d(i7);
            float a7 = bVar.a(i7);
            androidx.appcompat.app.g gVar = this.f8635i;
            gVar.f714b = d7;
            boolean z7 = d7.f8637a;
            float[] fArr = iVar.f8644h;
            if (z7) {
                boolean z8 = true;
                for (int i8 = 0; i8 < 9; i8++) {
                    float[] fArr2 = ((i) gVar.f714b).f8644h;
                    float f7 = (fArr[i8] * a7) + fArr2[i8];
                    fArr2[i8] = f7;
                    if (Math.abs(f7) < 1.0E-4f) {
                        ((i) gVar.f714b).f8644h[i8] = 0.0f;
                    } else {
                        z8 = false;
                    }
                }
                if (z8) {
                    ((g) gVar.f716d).k((i) gVar.f714b);
                }
            } else {
                for (int i9 = 0; i9 < 9; i9++) {
                    float f8 = fArr[i9];
                    if (f8 != CropImageView.DEFAULT_ASPECT_RATIO) {
                        float f9 = f8 * a7;
                        if (Math.abs(f9) < 1.0E-4f) {
                            f9 = 0.0f;
                        }
                        ((i) gVar.f714b).f8644h[i9] = f9;
                    } else {
                        ((i) gVar.f714b).f8644h[i9] = 0.0f;
                    }
                }
                j(d7);
            }
            this.f8606b = (cVar.f8606b * a7) + this.f8606b;
        }
        k(iVar);
    }

    public final void j(i iVar) {
        int i7;
        int i8 = this.f8634h + 1;
        i[] iVarArr = this.f8632f;
        if (i8 > iVarArr.length) {
            i[] iVarArr2 = (i[]) Arrays.copyOf(iVarArr, iVarArr.length * 2);
            this.f8632f = iVarArr2;
            this.f8633g = (i[]) Arrays.copyOf(iVarArr2, iVarArr2.length * 2);
        }
        i[] iVarArr3 = this.f8632f;
        int i9 = this.f8634h;
        iVarArr3[i9] = iVar;
        int i10 = i9 + 1;
        this.f8634h = i10;
        if (i10 > 1 && iVarArr3[i9].f8638b > iVar.f8638b) {
            int i11 = 0;
            while (true) {
                i7 = this.f8634h;
                if (i11 >= i7) {
                    break;
                }
                this.f8633g[i11] = this.f8632f[i11];
                i11++;
            }
            Arrays.sort(this.f8633g, 0, i7, new f(0, this));
            for (int i12 = 0; i12 < this.f8634h; i12++) {
                this.f8632f[i12] = this.f8633g[i12];
            }
        }
        iVar.f8637a = true;
        iVar.a(this);
    }

    public final void k(i iVar) {
        int i7 = 0;
        while (i7 < this.f8634h) {
            if (this.f8632f[i7] == iVar) {
                while (true) {
                    int i8 = this.f8634h;
                    if (i7 >= i8 - 1) {
                        this.f8634h = i8 - 1;
                        iVar.f8637a = false;
                        return;
                    } else {
                        i[] iVarArr = this.f8632f;
                        int i9 = i7 + 1;
                        iVarArr[i7] = iVarArr[i9];
                        i7 = i9;
                    }
                }
            } else {
                i7++;
            }
        }
    }

    @Override // s.c
    public final String toString() {
        String str = " goal -> (" + this.f8606b + ") : ";
        for (int i7 = 0; i7 < this.f8634h; i7++) {
            i iVar = this.f8632f[i7];
            androidx.appcompat.app.g gVar = this.f8635i;
            gVar.f714b = iVar;
            str = str + gVar + " ";
        }
        return str;
    }
}
