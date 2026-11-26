package y2;

import com.yalantis.ucrop.view.CropImageView;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final class a {

    /* renamed from: a, reason: collision with root package name */
    public final int f10121a;

    /* renamed from: b, reason: collision with root package name */
    public final float f10122b;

    /* renamed from: c, reason: collision with root package name */
    public int f10123c;

    /* renamed from: d, reason: collision with root package name */
    public int f10124d;

    /* renamed from: e, reason: collision with root package name */
    public final float f10125e;

    /* renamed from: f, reason: collision with root package name */
    public final float f10126f;

    /* renamed from: g, reason: collision with root package name */
    public final int f10127g;

    /* renamed from: h, reason: collision with root package name */
    public final float f10128h;

    /* JADX WARN: Code restructure failed: missing block: B:28:0x00b4, code lost:
    
        if (r6 > r3.f10122b) goto L44;
     */
    /* JADX WARN: Code restructure failed: missing block: B:38:0x00c3, code lost:
    
        if (r3.f10126f <= r3.f10122b) goto L43;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public a(int r4, float r5, float r6, float r7, int r8, float r9, int r10, float r11, int r12, float r13) {
        /*
            Method dump skipped, instructions count: 214
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: y2.a.<init>(int, float, float, float, int, float, int, float, int, float):void");
    }

    public static a a(float f7, float f8, float f9, float f10, int[] iArr, float f11, int[] iArr2, float f12, int[] iArr3) {
        a aVar = null;
        int i7 = 1;
        for (int i8 : iArr3) {
            int length = iArr2.length;
            int i9 = 0;
            while (i9 < length) {
                int i10 = iArr2[i9];
                int length2 = iArr.length;
                int i11 = 0;
                while (i11 < length2) {
                    int i12 = i11;
                    int i13 = length2;
                    int i14 = i9;
                    int i15 = length;
                    a aVar2 = new a(i7, f8, f9, f10, iArr[i11], f11, i10, f12, i8, f7);
                    float f13 = aVar2.f10128h;
                    if (aVar == null || f13 < aVar.f10128h) {
                        if (f13 == CropImageView.DEFAULT_ASPECT_RATIO) {
                            return aVar2;
                        }
                        aVar = aVar2;
                    }
                    i7++;
                    i11 = i12 + 1;
                    length2 = i13;
                    i9 = i14;
                    length = i15;
                }
                i9++;
            }
        }
        return aVar;
    }

    public final String toString() {
        return "Arrangement [priority=" + this.f10121a + ", smallCount=" + this.f10123c + ", smallSize=" + this.f10122b + ", mediumCount=" + this.f10124d + ", mediumSize=" + this.f10125e + ", largeCount=" + this.f10127g + ", largeSize=" + this.f10126f + ", cost=" + this.f10128h + "]";
    }
}
