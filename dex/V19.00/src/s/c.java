package s;

import com.yalantis.ucrop.view.CropImageView;
import java.util.ArrayList;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public class c {

    /* renamed from: d, reason: collision with root package name */
    public final b f8608d;

    /* renamed from: a, reason: collision with root package name */
    public i f8605a = null;

    /* renamed from: b, reason: collision with root package name */
    public float f8606b = CropImageView.DEFAULT_ASPECT_RATIO;

    /* renamed from: c, reason: collision with root package name */
    public final ArrayList f8607c = new ArrayList();

    /* renamed from: e, reason: collision with root package name */
    public boolean f8609e = false;

    public c(j.h hVar) {
        this.f8608d = new a(this, hVar);
    }

    public final void a(d dVar, int i7) {
        this.f8608d.i(dVar.j(i7), 1.0f);
        this.f8608d.i(dVar.j(i7), -1.0f);
    }

    public final void b(i iVar, i iVar2, i iVar3, int i7) {
        boolean z6;
        if (i7 != 0) {
            if (i7 < 0) {
                i7 *= -1;
                z6 = true;
            } else {
                z6 = false;
            }
            this.f8606b = i7;
            if (z6) {
                this.f8608d.i(iVar, 1.0f);
                this.f8608d.i(iVar2, -1.0f);
                this.f8608d.i(iVar3, -1.0f);
                return;
            }
        }
        this.f8608d.i(iVar, -1.0f);
        this.f8608d.i(iVar2, 1.0f);
        this.f8608d.i(iVar3, 1.0f);
    }

    public final void c(i iVar, i iVar2, i iVar3, int i7) {
        boolean z6;
        if (i7 != 0) {
            if (i7 < 0) {
                i7 *= -1;
                z6 = true;
            } else {
                z6 = false;
            }
            this.f8606b = i7;
            if (z6) {
                this.f8608d.i(iVar, 1.0f);
                this.f8608d.i(iVar2, -1.0f);
                this.f8608d.i(iVar3, 1.0f);
                return;
            }
        }
        this.f8608d.i(iVar, -1.0f);
        this.f8608d.i(iVar2, 1.0f);
        this.f8608d.i(iVar3, -1.0f);
    }

    public i d(boolean[] zArr) {
        return f(zArr, null);
    }

    public boolean e() {
        return this.f8605a == null && this.f8606b == CropImageView.DEFAULT_ASPECT_RATIO && this.f8608d.k() == 0;
    }

    public final i f(boolean[] zArr, i iVar) {
        h hVar;
        int k7 = this.f8608d.k();
        i iVar2 = null;
        float f7 = 0.0f;
        for (int i7 = 0; i7 < k7; i7++) {
            float a7 = this.f8608d.a(i7);
            if (a7 < CropImageView.DEFAULT_ASPECT_RATIO) {
                i d7 = this.f8608d.d(i7);
                if ((zArr == null || !zArr[d7.f8638b]) && d7 != iVar && (((hVar = d7.f8645i) == h.SLACK || hVar == h.ERROR) && a7 < f7)) {
                    f7 = a7;
                    iVar2 = d7;
                }
            }
        }
        return iVar2;
    }

    public final void g(i iVar) {
        i iVar2 = this.f8605a;
        if (iVar2 != null) {
            this.f8608d.i(iVar2, -1.0f);
            this.f8605a.f8639c = -1;
            this.f8605a = null;
        }
        float c7 = this.f8608d.c(iVar, true) * (-1.0f);
        this.f8605a = iVar;
        if (c7 == 1.0f) {
            return;
        }
        this.f8606b /= c7;
        this.f8608d.g(c7);
    }

    public final void h(d dVar, i iVar, boolean z6) {
        if (iVar == null || !iVar.f8642f) {
            return;
        }
        float e7 = this.f8608d.e(iVar);
        this.f8606b = (iVar.f8641e * e7) + this.f8606b;
        this.f8608d.c(iVar, z6);
        if (z6) {
            iVar.b(this);
        }
        if (this.f8608d.k() == 0) {
            this.f8609e = true;
            dVar.f8612a = true;
        }
    }

    public void i(d dVar, c cVar, boolean z6) {
        float b7 = this.f8608d.b(cVar, z6);
        this.f8606b = (cVar.f8606b * b7) + this.f8606b;
        if (z6) {
            cVar.f8605a.b(this);
        }
        if (this.f8605a == null || this.f8608d.k() != 0) {
            return;
        }
        this.f8609e = true;
        dVar.f8612a = true;
    }

    /* JADX WARN: Removed duplicated region for block: B:21:0x007c  */
    /* JADX WARN: Removed duplicated region for block: B:25:0x0081  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public java.lang.String toString() {
        /*
            r10 = this;
            s.i r0 = r10.f8605a
            if (r0 != 0) goto L7
            java.lang.String r0 = "0"
            goto L17
        L7:
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            java.lang.String r1 = ""
            r0.<init>(r1)
            s.i r1 = r10.f8605a
            r0.append(r1)
            java.lang.String r0 = r0.toString()
        L17:
            java.lang.String r1 = " = "
            java.lang.String r0 = a.a.g(r0, r1)
            float r1 = r10.f8606b
            r2 = 0
            int r1 = (r1 > r2 ? 1 : (r1 == r2 ? 0 : -1))
            r3 = 1
            r4 = 0
            if (r1 == 0) goto L35
            java.lang.StringBuilder r0 = a.a.j(r0)
            float r1 = r10.f8606b
            r0.append(r1)
            java.lang.String r0 = r0.toString()
            r1 = r3
            goto L36
        L35:
            r1 = r4
        L36:
            s.b r5 = r10.f8608d
            int r5 = r5.k()
        L3c:
            if (r4 >= r5) goto L9c
            s.b r6 = r10.f8608d
            s.i r6 = r6.d(r4)
            if (r6 != 0) goto L47
            goto L99
        L47:
            s.b r7 = r10.f8608d
            float r7 = r7.a(r4)
            int r8 = (r7 > r2 ? 1 : (r7 == r2 ? 0 : -1))
            if (r8 != 0) goto L52
            goto L99
        L52:
            java.lang.String r6 = r6.toString()
            r9 = -1082130432(0xffffffffbf800000, float:-1.0)
            if (r1 != 0) goto L66
            int r1 = (r7 > r2 ? 1 : (r7 == r2 ? 0 : -1))
            if (r1 >= 0) goto L76
            java.lang.String r1 = "- "
            java.lang.String r0 = a.a.g(r0, r1)
        L64:
            float r7 = r7 * r9
            goto L76
        L66:
            if (r8 <= 0) goto L6f
            java.lang.String r1 = " + "
            java.lang.String r0 = a.a.g(r0, r1)
            goto L76
        L6f:
            java.lang.String r1 = " - "
            java.lang.String r0 = a.a.g(r0, r1)
            goto L64
        L76:
            r1 = 1065353216(0x3f800000, float:1.0)
            int r1 = (r7 > r1 ? 1 : (r7 == r1 ? 0 : -1))
            if (r1 != 0) goto L81
            java.lang.String r0 = a.a.g(r0, r6)
            goto L98
        L81:
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            r1.append(r0)
            r1.append(r7)
            java.lang.String r0 = " "
            r1.append(r0)
            r1.append(r6)
            java.lang.String r0 = r1.toString()
        L98:
            r1 = r3
        L99:
            int r4 = r4 + 1
            goto L3c
        L9c:
            if (r1 != 0) goto La4
            java.lang.String r1 = "0.0"
            java.lang.String r0 = a.a.g(r0, r1)
        La4:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: s.c.toString():java.lang.String");
    }
}
