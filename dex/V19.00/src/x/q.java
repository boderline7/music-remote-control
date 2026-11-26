package x;

import android.view.View;
import com.yalantis.ucrop.view.CropImageView;
import java.util.Arrays;
import java.util.HashMap;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public abstract class q extends t.q {
    public q() {
        this.f9008c = new float[3];
        this.f9009d = false;
        this.f9011f = Float.NaN;
    }

    public final float b(float f7, long j7, View view, t.g gVar) {
        float[] fArr = this.f9008c;
        this.f9006a.d(f7, fArr);
        boolean z6 = true;
        float f8 = fArr[1];
        if (f8 == CropImageView.DEFAULT_ASPECT_RATIO) {
            this.f9009d = false;
            return fArr[2];
        }
        if (Float.isNaN(this.f9011f)) {
            float a7 = gVar.a(view, this.f9007b);
            this.f9011f = a7;
            if (Float.isNaN(a7)) {
                this.f9011f = CropImageView.DEFAULT_ASPECT_RATIO;
            }
        }
        float f9 = (float) (((((j7 - this.f9010e) * 1.0E-9d) * f8) + this.f9011f) % 1.0d);
        this.f9011f = f9;
        String str = this.f9007b;
        if (gVar.f8960a.containsKey(view)) {
            HashMap hashMap = (HashMap) gVar.f8960a.get(view);
            if (hashMap == null) {
                hashMap = new HashMap();
            }
            if (hashMap.containsKey(str)) {
                float[] fArr2 = (float[]) hashMap.get(str);
                if (fArr2 == null) {
                    fArr2 = new float[0];
                }
                if (fArr2.length <= 0) {
                    fArr2 = Arrays.copyOf(fArr2, 1);
                }
                fArr2[0] = f9;
                hashMap.put(str, fArr2);
            } else {
                hashMap.put(str, new float[]{f9});
                gVar.f8960a.put(view, hashMap);
            }
        } else {
            HashMap hashMap2 = new HashMap();
            hashMap2.put(str, new float[]{f9});
            gVar.f8960a.put(view, hashMap2);
        }
        this.f9010e = j7;
        float f10 = fArr[0];
        float sin = (((float) Math.sin(this.f9011f * 6.2831855f)) * f10) + fArr[2];
        if (f10 == CropImageView.DEFAULT_ASPECT_RATIO && f8 == CropImageView.DEFAULT_ASPECT_RATIO) {
            z6 = false;
        }
        this.f9009d = z6;
        return sin;
    }

    public abstract boolean c(float f7, long j7, View view, t.g gVar);
}
