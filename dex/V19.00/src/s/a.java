package s;

import com.yalantis.ucrop.view.CropImageView;
import java.util.Arrays;
import s5.h0;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final class a implements b {

    /* renamed from: b, reason: collision with root package name */
    public final c f8596b;

    /* renamed from: c, reason: collision with root package name */
    public final j.h f8597c;

    /* renamed from: a, reason: collision with root package name */
    public int f8595a = 0;

    /* renamed from: d, reason: collision with root package name */
    public int f8598d = 8;

    /* renamed from: e, reason: collision with root package name */
    public int[] f8599e = new int[8];

    /* renamed from: f, reason: collision with root package name */
    public int[] f8600f = new int[8];

    /* renamed from: g, reason: collision with root package name */
    public float[] f8601g = new float[8];

    /* renamed from: h, reason: collision with root package name */
    public int f8602h = -1;

    /* renamed from: i, reason: collision with root package name */
    public int f8603i = -1;

    /* renamed from: j, reason: collision with root package name */
    public boolean f8604j = false;

    public a(c cVar, j.h hVar) {
        this.f8596b = cVar;
        this.f8597c = hVar;
    }

    @Override // s.b
    public final float a(int i7) {
        int i8 = this.f8602h;
        for (int i9 = 0; i8 != -1 && i9 < this.f8595a; i9++) {
            if (i9 == i7) {
                return this.f8601g[i8];
            }
            i8 = this.f8600f[i8];
        }
        return CropImageView.DEFAULT_ASPECT_RATIO;
    }

    @Override // s.b
    public final float b(c cVar, boolean z6) {
        float e7 = e(cVar.f8605a);
        c(cVar.f8605a, z6);
        b bVar = cVar.f8608d;
        int k7 = bVar.k();
        for (int i7 = 0; i7 < k7; i7++) {
            i d7 = bVar.d(i7);
            h(d7, bVar.e(d7) * e7, z6);
        }
        return e7;
    }

    @Override // s.b
    public final float c(i iVar, boolean z6) {
        int i7 = this.f8602h;
        if (i7 == -1) {
            return CropImageView.DEFAULT_ASPECT_RATIO;
        }
        int i8 = 0;
        int i9 = -1;
        while (i7 != -1 && i8 < this.f8595a) {
            if (this.f8599e[i7] == iVar.f8638b) {
                if (i7 == this.f8602h) {
                    this.f8602h = this.f8600f[i7];
                } else {
                    int[] iArr = this.f8600f;
                    iArr[i9] = iArr[i7];
                }
                if (z6) {
                    iVar.b(this.f8596b);
                }
                iVar.f8648l--;
                this.f8595a--;
                this.f8599e[i7] = -1;
                if (this.f8604j) {
                    this.f8603i = i7;
                }
                return this.f8601g[i7];
            }
            i8++;
            i9 = i7;
            i7 = this.f8600f[i7];
        }
        return CropImageView.DEFAULT_ASPECT_RATIO;
    }

    @Override // s.b
    public final void clear() {
        int i7 = this.f8602h;
        for (int i8 = 0; i7 != -1 && i8 < this.f8595a; i8++) {
            i iVar = ((i[]) this.f8597c.f6893d)[this.f8599e[i7]];
            if (iVar != null) {
                iVar.b(this.f8596b);
            }
            i7 = this.f8600f[i7];
        }
        this.f8602h = -1;
        this.f8603i = -1;
        this.f8604j = false;
        this.f8595a = 0;
    }

    @Override // s.b
    public final i d(int i7) {
        int i8 = this.f8602h;
        for (int i9 = 0; i8 != -1 && i9 < this.f8595a; i9++) {
            if (i9 == i7) {
                return ((i[]) this.f8597c.f6893d)[this.f8599e[i8]];
            }
            i8 = this.f8600f[i8];
        }
        return null;
    }

    @Override // s.b
    public final float e(i iVar) {
        int i7 = this.f8602h;
        for (int i8 = 0; i7 != -1 && i8 < this.f8595a; i8++) {
            if (this.f8599e[i7] == iVar.f8638b) {
                return this.f8601g[i7];
            }
            i7 = this.f8600f[i7];
        }
        return CropImageView.DEFAULT_ASPECT_RATIO;
    }

    @Override // s.b
    public final boolean f(i iVar) {
        int i7 = this.f8602h;
        if (i7 == -1) {
            return false;
        }
        for (int i8 = 0; i7 != -1 && i8 < this.f8595a; i8++) {
            if (this.f8599e[i7] == iVar.f8638b) {
                return true;
            }
            i7 = this.f8600f[i7];
        }
        return false;
    }

    @Override // s.b
    public final void g(float f7) {
        int i7 = this.f8602h;
        for (int i8 = 0; i7 != -1 && i8 < this.f8595a; i8++) {
            float[] fArr = this.f8601g;
            fArr[i7] = fArr[i7] / f7;
            i7 = this.f8600f[i7];
        }
    }

    @Override // s.b
    public final void h(i iVar, float f7, boolean z6) {
        if (f7 <= -0.001f || f7 >= 0.001f) {
            int i7 = this.f8602h;
            c cVar = this.f8596b;
            if (i7 == -1) {
                this.f8602h = 0;
                this.f8601g[0] = f7;
                this.f8599e[0] = iVar.f8638b;
                this.f8600f[0] = -1;
                iVar.f8648l++;
                iVar.a(cVar);
                this.f8595a++;
                if (this.f8604j) {
                    return;
                }
                int i8 = this.f8603i + 1;
                this.f8603i = i8;
                int[] iArr = this.f8599e;
                if (i8 >= iArr.length) {
                    this.f8604j = true;
                    this.f8603i = iArr.length - 1;
                    return;
                }
                return;
            }
            int i9 = -1;
            for (int i10 = 0; i7 != -1 && i10 < this.f8595a; i10++) {
                int i11 = this.f8599e[i7];
                int i12 = iVar.f8638b;
                if (i11 == i12) {
                    float[] fArr = this.f8601g;
                    float f8 = fArr[i7] + f7;
                    if (f8 > -0.001f && f8 < 0.001f) {
                        f8 = 0.0f;
                    }
                    fArr[i7] = f8;
                    if (f8 == CropImageView.DEFAULT_ASPECT_RATIO) {
                        if (i7 == this.f8602h) {
                            this.f8602h = this.f8600f[i7];
                        } else {
                            int[] iArr2 = this.f8600f;
                            iArr2[i9] = iArr2[i7];
                        }
                        if (z6) {
                            iVar.b(cVar);
                        }
                        if (this.f8604j) {
                            this.f8603i = i7;
                        }
                        iVar.f8648l--;
                        this.f8595a--;
                        return;
                    }
                    return;
                }
                if (i11 < i12) {
                    i9 = i7;
                }
                i7 = this.f8600f[i7];
            }
            int i13 = this.f8603i;
            int i14 = i13 + 1;
            if (this.f8604j) {
                int[] iArr3 = this.f8599e;
                if (iArr3[i13] != -1) {
                    i13 = iArr3.length;
                }
            } else {
                i13 = i14;
            }
            int[] iArr4 = this.f8599e;
            if (i13 >= iArr4.length && this.f8595a < iArr4.length) {
                int i15 = 0;
                while (true) {
                    int[] iArr5 = this.f8599e;
                    if (i15 >= iArr5.length) {
                        break;
                    }
                    if (iArr5[i15] == -1) {
                        i13 = i15;
                        break;
                    }
                    i15++;
                }
            }
            int[] iArr6 = this.f8599e;
            if (i13 >= iArr6.length) {
                i13 = iArr6.length;
                int i16 = this.f8598d * 2;
                this.f8598d = i16;
                this.f8604j = false;
                this.f8603i = i13 - 1;
                this.f8601g = Arrays.copyOf(this.f8601g, i16);
                this.f8599e = Arrays.copyOf(this.f8599e, this.f8598d);
                this.f8600f = Arrays.copyOf(this.f8600f, this.f8598d);
            }
            this.f8599e[i13] = iVar.f8638b;
            this.f8601g[i13] = f7;
            if (i9 != -1) {
                int[] iArr7 = this.f8600f;
                iArr7[i13] = iArr7[i9];
                iArr7[i9] = i13;
            } else {
                this.f8600f[i13] = this.f8602h;
                this.f8602h = i13;
            }
            iVar.f8648l++;
            iVar.a(cVar);
            this.f8595a++;
            if (!this.f8604j) {
                this.f8603i++;
            }
            int i17 = this.f8603i;
            int[] iArr8 = this.f8599e;
            if (i17 >= iArr8.length) {
                this.f8604j = true;
                this.f8603i = iArr8.length - 1;
            }
        }
    }

    @Override // s.b
    public final void i(i iVar, float f7) {
        if (f7 == CropImageView.DEFAULT_ASPECT_RATIO) {
            c(iVar, true);
            return;
        }
        int i7 = this.f8602h;
        c cVar = this.f8596b;
        if (i7 == -1) {
            this.f8602h = 0;
            this.f8601g[0] = f7;
            this.f8599e[0] = iVar.f8638b;
            this.f8600f[0] = -1;
            iVar.f8648l++;
            iVar.a(cVar);
            this.f8595a++;
            if (this.f8604j) {
                return;
            }
            int i8 = this.f8603i + 1;
            this.f8603i = i8;
            int[] iArr = this.f8599e;
            if (i8 >= iArr.length) {
                this.f8604j = true;
                this.f8603i = iArr.length - 1;
                return;
            }
            return;
        }
        int i9 = -1;
        for (int i10 = 0; i7 != -1 && i10 < this.f8595a; i10++) {
            int i11 = this.f8599e[i7];
            int i12 = iVar.f8638b;
            if (i11 == i12) {
                this.f8601g[i7] = f7;
                return;
            }
            if (i11 < i12) {
                i9 = i7;
            }
            i7 = this.f8600f[i7];
        }
        int i13 = this.f8603i;
        int i14 = i13 + 1;
        if (this.f8604j) {
            int[] iArr2 = this.f8599e;
            if (iArr2[i13] != -1) {
                i13 = iArr2.length;
            }
        } else {
            i13 = i14;
        }
        int[] iArr3 = this.f8599e;
        if (i13 >= iArr3.length && this.f8595a < iArr3.length) {
            int i15 = 0;
            while (true) {
                int[] iArr4 = this.f8599e;
                if (i15 >= iArr4.length) {
                    break;
                }
                if (iArr4[i15] == -1) {
                    i13 = i15;
                    break;
                }
                i15++;
            }
        }
        int[] iArr5 = this.f8599e;
        if (i13 >= iArr5.length) {
            i13 = iArr5.length;
            int i16 = this.f8598d * 2;
            this.f8598d = i16;
            this.f8604j = false;
            this.f8603i = i13 - 1;
            this.f8601g = Arrays.copyOf(this.f8601g, i16);
            this.f8599e = Arrays.copyOf(this.f8599e, this.f8598d);
            this.f8600f = Arrays.copyOf(this.f8600f, this.f8598d);
        }
        this.f8599e[i13] = iVar.f8638b;
        this.f8601g[i13] = f7;
        if (i9 != -1) {
            int[] iArr6 = this.f8600f;
            iArr6[i13] = iArr6[i9];
            iArr6[i9] = i13;
        } else {
            this.f8600f[i13] = this.f8602h;
            this.f8602h = i13;
        }
        iVar.f8648l++;
        iVar.a(cVar);
        int i17 = this.f8595a + 1;
        this.f8595a = i17;
        if (!this.f8604j) {
            this.f8603i++;
        }
        int[] iArr7 = this.f8599e;
        if (i17 >= iArr7.length) {
            this.f8604j = true;
        }
        if (this.f8603i >= iArr7.length) {
            this.f8604j = true;
            this.f8603i = iArr7.length - 1;
        }
    }

    @Override // s.b
    public final void j() {
        int i7 = this.f8602h;
        for (int i8 = 0; i7 != -1 && i8 < this.f8595a; i8++) {
            float[] fArr = this.f8601g;
            fArr[i7] = fArr[i7] * (-1.0f);
            i7 = this.f8600f[i7];
        }
    }

    @Override // s.b
    public final int k() {
        return this.f8595a;
    }

    public final String toString() {
        int i7 = this.f8602h;
        String str = h0.FRAGMENT_ENCODE_SET;
        for (int i8 = 0; i7 != -1 && i8 < this.f8595a; i8++) {
            StringBuilder j7 = a.a.j(a.a.g(str, " -> "));
            j7.append(this.f8601g[i7]);
            j7.append(" : ");
            StringBuilder j8 = a.a.j(j7.toString());
            j8.append(((i[]) this.f8597c.f6893d)[this.f8599e[i7]]);
            str = j8.toString();
            i7 = this.f8600f[i7];
        }
        return str;
    }
}
