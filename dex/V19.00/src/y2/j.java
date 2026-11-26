package y2;

import com.yalantis.ucrop.view.CropImageView;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final class j {

    /* renamed from: a, reason: collision with root package name */
    public final i f10160a;

    /* renamed from: b, reason: collision with root package name */
    public final List f10161b;

    /* renamed from: c, reason: collision with root package name */
    public final List f10162c;

    /* renamed from: d, reason: collision with root package name */
    public final float[] f10163d;

    /* renamed from: e, reason: collision with root package name */
    public final float[] f10164e;

    /* renamed from: f, reason: collision with root package name */
    public final float f10165f;

    /* renamed from: g, reason: collision with root package name */
    public final float f10166g;

    public j(i iVar, ArrayList arrayList, ArrayList arrayList2) {
        this.f10160a = iVar;
        this.f10161b = Collections.unmodifiableList(arrayList);
        this.f10162c = Collections.unmodifiableList(arrayList2);
        float f7 = ((i) arrayList.get(arrayList.size() - 1)).b().f10150a - iVar.b().f10150a;
        this.f10165f = f7;
        float f8 = iVar.d().f10150a - ((i) arrayList2.get(arrayList2.size() - 1)).d().f10150a;
        this.f10166g = f8;
        this.f10163d = b(f7, arrayList, true);
        this.f10164e = b(f8, arrayList2, false);
    }

    public static float[] b(float f7, ArrayList arrayList, boolean z6) {
        int size = arrayList.size();
        float[] fArr = new float[size];
        int i7 = 1;
        while (i7 < size) {
            int i8 = i7 - 1;
            i iVar = (i) arrayList.get(i8);
            i iVar2 = (i) arrayList.get(i7);
            fArr[i7] = i7 == size + (-1) ? 1.0f : fArr[i8] + ((z6 ? iVar2.b().f10150a - iVar.b().f10150a : iVar.d().f10150a - iVar2.d().f10150a) / f7);
            i7++;
        }
        return fArr;
    }

    public static i c(i iVar, int i7, int i8, float f7, int i9, int i10, float f8) {
        ArrayList arrayList = new ArrayList(iVar.f10157b);
        arrayList.add(i8, (h) arrayList.remove(i7));
        g gVar = new g(iVar.f10156a, f8);
        int i11 = 0;
        while (i11 < arrayList.size()) {
            h hVar = (h) arrayList.get(i11);
            float f9 = hVar.f10153d;
            gVar.b((f9 / 2.0f) + f7, hVar.f10152c, f9, i11 >= i9 && i11 <= i10, hVar.f10154e, hVar.f10155f);
            f7 += hVar.f10153d;
            i11++;
        }
        return gVar.d();
    }

    public final i a(float f7, float f8, float f9) {
        float b7;
        List list;
        float[] fArr;
        float[] fArr2;
        float f10 = this.f10165f + f8;
        float f11 = f9 - this.f10166g;
        if (f7 < f10) {
            b7 = r2.a.b(1.0f, CropImageView.DEFAULT_ASPECT_RATIO, f8, f10, f7);
            list = this.f10161b;
            fArr = this.f10163d;
        } else {
            if (f7 <= f11) {
                return this.f10160a;
            }
            b7 = r2.a.b(CropImageView.DEFAULT_ASPECT_RATIO, 1.0f, f11, f9, f7);
            list = this.f10162c;
            fArr = this.f10164e;
        }
        int size = list.size();
        float f12 = fArr[0];
        int i7 = 1;
        while (true) {
            if (i7 >= size) {
                fArr2 = new float[]{CropImageView.DEFAULT_ASPECT_RATIO, CropImageView.DEFAULT_ASPECT_RATIO, CropImageView.DEFAULT_ASPECT_RATIO};
                break;
            }
            float f13 = fArr[i7];
            if (b7 <= f13) {
                fArr2 = new float[]{r2.a.b(CropImageView.DEFAULT_ASPECT_RATIO, 1.0f, f12, f13, b7), i7 - 1, i7};
                break;
            }
            i7++;
            f12 = f13;
        }
        i iVar = (i) list.get((int) fArr2[1]);
        i iVar2 = (i) list.get((int) fArr2[2]);
        float f14 = fArr2[0];
        if (iVar.f10156a != iVar2.f10156a) {
            throw new IllegalArgumentException("Keylines being linearly interpolated must have the same item size.");
        }
        List list2 = iVar.f10157b;
        int size2 = list2.size();
        List list3 = iVar2.f10157b;
        if (size2 != list3.size()) {
            throw new IllegalArgumentException("Keylines being linearly interpolated must have the same number of keylines.");
        }
        ArrayList arrayList = new ArrayList();
        for (int i8 = 0; i8 < list2.size(); i8++) {
            h hVar = (h) list2.get(i8);
            h hVar2 = (h) list3.get(i8);
            arrayList.add(new h(r2.a.a(hVar.f10150a, hVar2.f10150a, f14), r2.a.a(hVar.f10151b, hVar2.f10151b, f14), r2.a.a(hVar.f10152c, hVar2.f10152c, f14), r2.a.a(hVar.f10153d, hVar2.f10153d, f14), false, CropImageView.DEFAULT_ASPECT_RATIO));
        }
        return new i(iVar.f10156a, arrayList, r2.a.c(iVar.f10158c, f14, iVar2.f10158c), r2.a.c(iVar.f10159d, f14, iVar2.f10159d));
    }
}
