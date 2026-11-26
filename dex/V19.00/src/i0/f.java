package i0;

import android.graphics.Path;
import com.yalantis.ucrop.view.CropImageView;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final class f {

    /* renamed from: a, reason: collision with root package name */
    public char f6778a;

    /* renamed from: b, reason: collision with root package name */
    public float[] f6779b;

    public static void a(Path path, float f7, float f8, float f9, float f10, float f11, float f12, float f13, boolean z6, boolean z7) {
        double d7;
        double d8;
        double radians = Math.toRadians(f13);
        double cos = Math.cos(radians);
        double sin = Math.sin(radians);
        double d9 = f7;
        double d10 = f8;
        double d11 = (d10 * sin) + (d9 * cos);
        double d12 = d9;
        double d13 = f11;
        double d14 = d11 / d13;
        double d15 = f12;
        double d16 = ((d10 * cos) + ((-f7) * sin)) / d15;
        double d17 = d10;
        double d18 = f10;
        double d19 = ((d18 * sin) + (f9 * cos)) / d13;
        double d20 = ((d18 * cos) + ((-f9) * sin)) / d15;
        double d21 = d14 - d19;
        double d22 = d16 - d20;
        double d23 = (d14 + d19) / 2.0d;
        double d24 = (d16 + d20) / 2.0d;
        double d25 = (d22 * d22) + (d21 * d21);
        if (d25 == 0.0d) {
            return;
        }
        double d26 = (1.0d / d25) - 0.25d;
        if (d26 < 0.0d) {
            float sqrt = (float) (Math.sqrt(d25) / 1.99999d);
            a(path, f7, f8, f9, f10, f11 * sqrt, f12 * sqrt, f13, z6, z7);
            return;
        }
        double sqrt2 = Math.sqrt(d26);
        double d27 = d21 * sqrt2;
        double d28 = sqrt2 * d22;
        if (z6 == z7) {
            d7 = d23 - d28;
            d8 = d24 + d27;
        } else {
            d7 = d23 + d28;
            d8 = d24 - d27;
        }
        double atan2 = Math.atan2(d16 - d8, d14 - d7);
        double atan22 = Math.atan2(d20 - d8, d19 - d7) - atan2;
        if (z7 != (atan22 >= 0.0d)) {
            atan22 = atan22 > 0.0d ? atan22 - 6.283185307179586d : atan22 + 6.283185307179586d;
        }
        double d29 = d7 * d13;
        double d30 = d8 * d15;
        double d31 = (d29 * cos) - (d30 * sin);
        double d32 = (d30 * cos) + (d29 * sin);
        int ceil = (int) Math.ceil(Math.abs((atan22 * 4.0d) / 3.141592653589793d));
        double cos2 = Math.cos(radians);
        double sin2 = Math.sin(radians);
        double cos3 = Math.cos(atan2);
        double sin3 = Math.sin(atan2);
        double d33 = -d13;
        double d34 = d33 * cos2;
        double d35 = d15 * sin2;
        double d36 = (d34 * sin3) - (d35 * cos3);
        double d37 = d33 * sin2;
        double d38 = d15 * cos2;
        double d39 = (cos3 * d38) + (sin3 * d37);
        double d40 = d38;
        double d41 = atan22 / ceil;
        int i7 = 0;
        while (i7 < ceil) {
            double d42 = atan2 + d41;
            double sin4 = Math.sin(d42);
            double cos4 = Math.cos(d42);
            double d43 = d41;
            double d44 = (((d13 * cos2) * cos4) + d31) - (d35 * sin4);
            double d45 = d40;
            double d46 = d31;
            double d47 = (d45 * sin4) + (d13 * sin2 * cos4) + d32;
            double d48 = (d34 * sin4) - (d35 * cos4);
            double d49 = (cos4 * d45) + (sin4 * d37);
            double d50 = d42 - atan2;
            double tan = Math.tan(d50 / 2.0d);
            double sqrt3 = ((Math.sqrt(((tan * 3.0d) * tan) + 4.0d) - 1.0d) * Math.sin(d50)) / 3.0d;
            path.rLineTo(CropImageView.DEFAULT_ASPECT_RATIO, CropImageView.DEFAULT_ASPECT_RATIO);
            path.cubicTo((float) ((d36 * sqrt3) + d12), (float) ((d39 * sqrt3) + d17), (float) (d44 - (sqrt3 * d48)), (float) (d47 - (sqrt3 * d49)), (float) d44, (float) d47);
            i7++;
            atan2 = d42;
            d37 = d37;
            cos2 = cos2;
            ceil = ceil;
            d39 = d49;
            d13 = d13;
            d36 = d48;
            d12 = d44;
            d17 = d47;
            d31 = d46;
            d41 = d43;
            d40 = d45;
        }
    }

    public static void b(f[] fVarArr, Path path) {
        int i7;
        int i8;
        float[] fArr;
        char c7;
        int i9;
        float f7;
        float f8;
        float f9;
        float f10;
        float f11;
        float f12;
        float f13;
        float f14;
        float f15;
        float f16;
        float f17;
        float f18;
        float f19;
        float f20;
        f[] fVarArr2 = fVarArr;
        int i10 = 6;
        float[] fArr2 = new float[6];
        char c8 = 'm';
        int i11 = 0;
        char c9 = 'm';
        int i12 = 0;
        while (i12 < fVarArr2.length) {
            f fVar = fVarArr2[i12];
            char c10 = fVar.f6778a;
            float[] fArr3 = fVar.f6779b;
            float f21 = fArr2[i11];
            float f22 = fArr2[1];
            float f23 = fArr2[2];
            float f24 = fArr2[3];
            float f25 = fArr2[4];
            float f26 = fArr2[5];
            switch (c10) {
                case a0.e.LAYOUT_CONSTRAINT_HEIGHT /* 65 */:
                case z1.c.SGET_WIDE /* 97 */:
                    i7 = 7;
                    break;
                case a0.e.GUIDELINE_USE_RTL /* 67 */:
                case z1.c.SGET_BOOLEAN /* 99 */:
                    i7 = i10;
                    break;
                case z1.c.AGET_BYTE /* 72 */:
                case z1.c.IGET_BYTE /* 86 */:
                case z1.c.SPUT_WIDE /* 104 */:
                case z1.c.INVOKE_DIRECT_RANGE /* 118 */:
                    i7 = 1;
                    break;
                case z1.c.APUT_SHORT /* 81 */:
                case z1.c.IGET_WIDE /* 83 */:
                case z1.c.INVOKE_STATIC /* 113 */:
                case 's':
                    i7 = 4;
                    break;
                case 'Z':
                case 'z':
                    path.close();
                    path.moveTo(f25, f26);
                    f21 = f25;
                    f23 = f21;
                    f22 = f26;
                    f24 = f22;
                default:
                    i7 = 2;
                    break;
            }
            float f27 = f25;
            float f28 = f26;
            float f29 = f21;
            float f30 = f22;
            int i13 = i11;
            while (i13 < fArr3.length) {
                if (c10 != 'A') {
                    if (c10 != 'C') {
                        if (c10 == 'H') {
                            i8 = i13;
                            fArr = fArr3;
                            c7 = c10;
                            i9 = i12;
                            path.lineTo(fArr[i8], f30);
                            f29 = fArr[i8];
                        } else if (c10 == 'Q') {
                            i8 = i13;
                            fArr = fArr3;
                            c7 = c10;
                            i9 = i12;
                            int i14 = i8 + 1;
                            int i15 = i8 + 2;
                            int i16 = i8 + 3;
                            path.quadTo(fArr[i8], fArr[i14], fArr[i15], fArr[i16]);
                            f7 = fArr[i8];
                            f8 = fArr[i14];
                            f29 = fArr[i15];
                            f30 = fArr[i16];
                        } else if (c10 == 'V') {
                            i8 = i13;
                            fArr = fArr3;
                            c7 = c10;
                            i9 = i12;
                            path.lineTo(f29, fArr[i8]);
                            f30 = fArr[i8];
                        } else if (c10 != 'a') {
                            if (c10 != 'c') {
                                if (c10 == 'h') {
                                    i8 = i13;
                                    path.rLineTo(fArr3[i8], CropImageView.DEFAULT_ASPECT_RATIO);
                                    f29 += fArr3[i8];
                                } else if (c10 == 'q') {
                                    i8 = i13;
                                    float f31 = f30;
                                    float f32 = f29;
                                    int i17 = i8 + 1;
                                    int i18 = i8 + 2;
                                    int i19 = i8 + 3;
                                    path.rQuadTo(fArr3[i8], fArr3[i17], fArr3[i18], fArr3[i19]);
                                    float f33 = f32 + fArr3[i8];
                                    float f34 = fArr3[i17] + f31;
                                    float f35 = f32 + fArr3[i18];
                                    f30 = f31 + fArr3[i19];
                                    f24 = f34;
                                    f23 = f33;
                                    fArr = fArr3;
                                    c7 = c10;
                                    i9 = i12;
                                    f29 = f35;
                                } else if (c10 == 'v') {
                                    i8 = i13;
                                    path.rLineTo(CropImageView.DEFAULT_ASPECT_RATIO, fArr3[i8]);
                                    f30 += fArr3[i8];
                                } else if (c10 == 'L') {
                                    i8 = i13;
                                    int i20 = i8 + 1;
                                    path.lineTo(fArr3[i8], fArr3[i20]);
                                    f29 = fArr3[i8];
                                    f30 = fArr3[i20];
                                } else if (c10 == 'M') {
                                    i8 = i13;
                                    f29 = fArr3[i8];
                                    f30 = fArr3[i8 + 1];
                                    if (i8 > 0) {
                                        path.lineTo(f29, f30);
                                    } else {
                                        path.moveTo(f29, f30);
                                        f28 = f30;
                                        f27 = f29;
                                    }
                                } else if (c10 == 'S') {
                                    i8 = i13;
                                    float f36 = f30;
                                    float f37 = f29;
                                    if (c9 == 'c' || c9 == 's' || c9 == 'C' || c9 == 'S') {
                                        f15 = (f36 * 2.0f) - f24;
                                        f16 = (f37 * 2.0f) - f23;
                                    } else {
                                        f16 = f37;
                                        f15 = f36;
                                    }
                                    int i21 = i8 + 1;
                                    int i22 = i8 + 2;
                                    int i23 = i8 + 3;
                                    path.cubicTo(f16, f15, fArr3[i8], fArr3[i21], fArr3[i22], fArr3[i23]);
                                    float f38 = fArr3[i8];
                                    float f39 = fArr3[i21];
                                    f29 = fArr3[i22];
                                    f30 = fArr3[i23];
                                    f24 = f39;
                                    f23 = f38;
                                } else if (c10 == 'T') {
                                    i8 = i13;
                                    float f40 = f30;
                                    float f41 = f29;
                                    if (c9 == 'q' || c9 == 't' || c9 == 'Q' || c9 == 'T') {
                                        f10 = (f41 * 2.0f) - f23;
                                        f11 = (f40 * 2.0f) - f24;
                                    } else {
                                        f10 = f41;
                                        f11 = f40;
                                    }
                                    int i24 = i8 + 1;
                                    path.quadTo(f10, f11, fArr3[i8], fArr3[i24]);
                                    f12 = fArr3[i8];
                                    f14 = fArr3[i24];
                                    f24 = f11;
                                    f23 = f10;
                                    fArr = fArr3;
                                    c7 = c10;
                                    i9 = i12;
                                    f29 = f12;
                                    f30 = f14;
                                } else if (c10 == 'l') {
                                    i8 = i13;
                                    int i25 = i8 + 1;
                                    path.rLineTo(fArr3[i8], fArr3[i25]);
                                    f29 += fArr3[i8];
                                    f30 += fArr3[i25];
                                } else if (c10 == c8) {
                                    i8 = i13;
                                    float f42 = fArr3[i8];
                                    f29 += f42;
                                    float f43 = fArr3[i8 + 1];
                                    f30 += f43;
                                    if (i8 > 0) {
                                        path.rLineTo(f42, f43);
                                    } else {
                                        path.rMoveTo(f42, f43);
                                        f28 = f30;
                                        f27 = f29;
                                    }
                                } else if (c10 == 's') {
                                    if (c9 == 'c' || c9 == 's' || c9 == 'C' || c9 == 'S') {
                                        f17 = f30 - f24;
                                        f18 = f29 - f23;
                                    } else {
                                        f18 = CropImageView.DEFAULT_ASPECT_RATIO;
                                        f17 = CropImageView.DEFAULT_ASPECT_RATIO;
                                    }
                                    int i26 = i13 + 1;
                                    int i27 = i13 + 2;
                                    int i28 = i13 + 3;
                                    i8 = i13;
                                    f9 = f30;
                                    float f44 = f29;
                                    path.rCubicTo(f18, f17, fArr3[i13], fArr3[i26], fArr3[i27], fArr3[i28]);
                                    f10 = f44 + fArr3[i8];
                                    f11 = f9 + fArr3[i26];
                                    f12 = f44 + fArr3[i27];
                                    f13 = fArr3[i28];
                                } else if (c10 != 't') {
                                    i8 = i13;
                                } else {
                                    if (c9 == 'q' || c9 == 't' || c9 == 'Q' || c9 == 'T') {
                                        f19 = f29 - f23;
                                        f20 = f30 - f24;
                                    } else {
                                        f20 = CropImageView.DEFAULT_ASPECT_RATIO;
                                        f19 = CropImageView.DEFAULT_ASPECT_RATIO;
                                    }
                                    int i29 = i13 + 1;
                                    path.rQuadTo(f19, f20, fArr3[i13], fArr3[i29]);
                                    float f45 = f19 + f29;
                                    float f46 = f20 + f30;
                                    f29 += fArr3[i13];
                                    f30 += fArr3[i29];
                                    f24 = f46;
                                    i8 = i13;
                                    fArr = fArr3;
                                    c7 = c10;
                                    i9 = i12;
                                    f23 = f45;
                                }
                                fArr = fArr3;
                                c7 = c10;
                                i9 = i12;
                            } else {
                                i8 = i13;
                                f9 = f30;
                                float f47 = f29;
                                int i30 = i8 + 2;
                                int i31 = i8 + 3;
                                int i32 = i8 + 4;
                                int i33 = i8 + 5;
                                path.rCubicTo(fArr3[i8], fArr3[i8 + 1], fArr3[i30], fArr3[i31], fArr3[i32], fArr3[i33]);
                                f10 = f47 + fArr3[i30];
                                f11 = f9 + fArr3[i31];
                                f12 = f47 + fArr3[i32];
                                f13 = fArr3[i33];
                            }
                            f14 = f13 + f9;
                            f24 = f11;
                            f23 = f10;
                            fArr = fArr3;
                            c7 = c10;
                            i9 = i12;
                            f29 = f12;
                            f30 = f14;
                        } else {
                            i8 = i13;
                            float f48 = f30;
                            float f49 = f29;
                            int i34 = i8 + 5;
                            int i35 = i8 + 6;
                            fArr = fArr3;
                            c7 = c10;
                            i9 = i12;
                            a(path, f49, f48, fArr3[i34] + f49, fArr3[i35] + f48, fArr3[i8], fArr3[i8 + 1], fArr3[i8 + 2], fArr3[i8 + 3] != CropImageView.DEFAULT_ASPECT_RATIO, fArr3[i8 + 4] != CropImageView.DEFAULT_ASPECT_RATIO);
                            f29 = f49 + fArr[i34];
                            f30 = f48 + fArr[i35];
                        }
                        i13 = i8 + i7;
                        c9 = c7;
                        c10 = c9;
                        fArr3 = fArr;
                        i12 = i9;
                        c8 = 'm';
                        i11 = 0;
                    } else {
                        i8 = i13;
                        fArr = fArr3;
                        c7 = c10;
                        i9 = i12;
                        int i36 = i8 + 2;
                        int i37 = i8 + 3;
                        int i38 = i8 + 4;
                        int i39 = i8 + 5;
                        path.cubicTo(fArr[i8], fArr[i8 + 1], fArr[i36], fArr[i37], fArr[i38], fArr[i39]);
                        f29 = fArr[i38];
                        f30 = fArr[i39];
                        f7 = fArr[i36];
                        f8 = fArr[i37];
                    }
                    f23 = f7;
                    f24 = f8;
                    i13 = i8 + i7;
                    c9 = c7;
                    c10 = c9;
                    fArr3 = fArr;
                    i12 = i9;
                    c8 = 'm';
                    i11 = 0;
                } else {
                    i8 = i13;
                    fArr = fArr3;
                    c7 = c10;
                    i9 = i12;
                    int i40 = i8 + 5;
                    int i41 = i8 + 6;
                    a(path, f29, f30, fArr[i40], fArr[i41], fArr[i8], fArr[i8 + 1], fArr[i8 + 2], fArr[i8 + 3] != CropImageView.DEFAULT_ASPECT_RATIO, fArr[i8 + 4] != CropImageView.DEFAULT_ASPECT_RATIO);
                    f29 = fArr[i40];
                    f30 = fArr[i41];
                }
                f24 = f30;
                f23 = f29;
                i13 = i8 + i7;
                c9 = c7;
                c10 = c9;
                fArr3 = fArr;
                i12 = i9;
                c8 = 'm';
                i11 = 0;
            }
            int i42 = i12;
            int i43 = i11;
            fArr2[i43] = f29;
            fArr2[1] = f30;
            fArr2[2] = f23;
            fArr2[3] = f24;
            fArr2[4] = f27;
            fArr2[5] = f28;
            i12 = i42 + 1;
            i10 = 6;
            c8 = 'm';
            i11 = i43;
            c9 = fVarArr[i42].f6778a;
            fVarArr2 = fVarArr;
        }
    }
}
