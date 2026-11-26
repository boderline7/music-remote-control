package com.navi.tool.utils;

import android.graphics.Bitmap;
import android.graphics.Color;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final class CoverPaletteExtractor {
    private static final int BIN_COUNT = 4096;
    private static final int SAMPLE_SIZE = 48;

    private CoverPaletteExtractor() {
    }

    private static int averageColor(int i7, int[] iArr, int[] iArr2, int[] iArr3, int[] iArr4) {
        int i8;
        if (i7 < 0 || (i8 = iArr[i7]) == 0) {
            return -15326914;
        }
        return Color.rgb(iArr2[i7] / i8, iArr3[i7] / i8, iArr4[i7] / i8);
    }

    private static int boostSaturation(int i7, float f7) {
        float[] fArr = new float[3];
        Color.RGBToHSV(Color.red(i7), Color.green(i7), Color.blue(i7), fArr);
        fArr[1] = Math.min(1.0f, fArr[1] * f7);
        return Color.HSVToColor(fArr);
    }

    private static int clamp(int i7) {
        if (i7 < 0) {
            return 0;
        }
        return Math.min(i7, 255);
    }

    private static int clampLuminance(int i7, float f7, float f8) {
        float luminance = luminance(i7);
        return luminance > f8 ? scaleRgb(i7, f8 / Math.max(luminance, 0.001f)) : luminance < f7 ? lighten(i7, (f7 - luminance) / Math.max(1.0f - luminance, 0.001f)) : i7;
    }

    public static int[] defaultPalette(boolean z6) {
        return z6 ? new int[]{-14668736, -15458509, -11899234} : new int[]{-14338980, -15326657, -10779452};
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v2 */
    /* JADX WARN: Type inference failed for: r0v5, types: [android.graphics.Bitmap$Config] */
    public static int[] extract(Bitmap bitmap, boolean z6) {
        Bitmap bitmap2;
        Bitmap bitmap3;
        if (bitmap == null || bitmap.isRecycled()) {
            return defaultPalette(z6);
        }
        try {
            bitmap2 = bitmap.getConfig();
            int i7 = 0;
            try {
                if (bitmap2 == Bitmap.Config.HARDWARE) {
                    bitmap3 = bitmap.copy(Bitmap.Config.ARGB_8888, false);
                    if (bitmap3 == null) {
                        return defaultPalette(z6);
                    }
                } else {
                    bitmap3 = bitmap;
                }
                int i8 = 48;
                Bitmap createScaledBitmap = Bitmap.createScaledBitmap(bitmap3, 48, 48, true);
                if (bitmap3 != bitmap && bitmap3 != createScaledBitmap) {
                    bitmap3.recycle();
                }
                int[] iArr = new int[4096];
                int[] iArr2 = new int[4096];
                int[] iArr3 = new int[4096];
                int[] iArr4 = new int[4096];
                int i9 = 0;
                int i10 = 0;
                while (i9 < i8) {
                    int i11 = i7;
                    while (i11 < i8) {
                        try {
                            try {
                                int pixel = createScaledBitmap.getPixel(i11, i9);
                                if (((pixel >>> 24) & 255) >= 128) {
                                    int i12 = (pixel >> 16) & 255;
                                    int i13 = (pixel >> 8) & 255;
                                    int i14 = pixel & 255;
                                    int max = Math.max(i12, Math.max(i13, i14));
                                    int min = max - Math.min(i12, Math.min(i13, i14));
                                    if ((max <= 245 || min >= 12) && max >= 12) {
                                        int i15 = ((i12 >> 4) << 8) | ((i13 >> 4) << 4) | (i14 >> 4);
                                        iArr[i15] = iArr[i15] + 1;
                                        iArr2[i15] = iArr2[i15] + i12;
                                        iArr3[i15] = iArr3[i15] + i13;
                                        iArr4[i15] = iArr4[i15] + i14;
                                        i10++;
                                        i11++;
                                        i8 = 48;
                                    }
                                }
                                i11++;
                                i8 = 48;
                            } finally {
                            }
                        } catch (Throwable unused) {
                            int[] defaultPalette = defaultPalette(z6);
                            if (createScaledBitmap != bitmap) {
                                createScaledBitmap.recycle();
                            }
                            return defaultPalette;
                        }
                    }
                    i9++;
                    i8 = 48;
                    i7 = 0;
                }
                if (createScaledBitmap != bitmap) {
                    createScaledBitmap.recycle();
                }
                if (i10 < 16) {
                    return defaultPalette(z6);
                }
                int i16 = -1;
                float f7 = -1.0f;
                int i17 = -1;
                int i18 = 0;
                for (int i19 = 0; i19 < 4096; i19++) {
                    int i20 = iArr[i19];
                    if (i20 != 0) {
                        if (i20 > i18) {
                            i17 = i19;
                            i18 = i20;
                        }
                        if (i20 >= Math.max(4, i10 / 100)) {
                            int i21 = iArr2[i19] / i20;
                            int i22 = iArr3[i19] / i20;
                            int i23 = iArr4[i19] / i20;
                            float max2 = ((Math.max(i21, Math.max(i22, i23)) - Math.min(i21, Math.min(i22, i23))) / 255.0f) * i20;
                            if (max2 > f7) {
                                i16 = i19;
                                f7 = max2;
                            }
                        }
                    }
                }
                int averageColor = averageColor(i17, iArr, iArr2, iArr3, iArr4);
                int findSecondColor = findSecondColor(i17, iArr, iArr2, iArr3, iArr4, i10);
                int averageColor2 = i16 >= 0 ? averageColor(i16, iArr, iArr2, iArr3, iArr4) : lighten(averageColor, 0.35f);
                int clampLuminance = clampLuminance(boostSaturation(averageColor, 1.15f), 0.22f, 0.5f);
                int mix = mix(clampLuminance(boostSaturation(findSecondColor, 1.1f), 0.12f, 0.32f), clampLuminance, 0.35f);
                int clampLuminance2 = clampLuminance(averageColor2, 0.35f, 0.75f);
                if (z6) {
                    clampLuminance = scaleRgb(clampLuminance, 0.94f);
                    mix = scaleRgb(mix, 0.94f);
                    clampLuminance2 = scaleRgb(clampLuminance2, 0.95f);
                }
                return new int[]{clampLuminance, mix, clampLuminance2};
            } catch (Throwable unused2) {
                if (bitmap2 != bitmap && bitmap2 != 0) {
                    bitmap2.recycle();
                }
                return defaultPalette(z6);
            }
        } catch (Throwable unused3) {
            bitmap2 = bitmap;
        }
    }

    private static int findSecondColor(int i7, int[] iArr, int[] iArr2, int[] iArr3, int[] iArr4, int i8) {
        int max = iArr2[i7] / Math.max(1, iArr[i7]);
        int max2 = iArr3[i7] / Math.max(1, iArr[i7]);
        int max3 = iArr4[i7] / Math.max(1, iArr[i7]);
        int i9 = -1;
        int i10 = 0;
        for (int i11 = 0; i11 < 4096; i11++) {
            int i12 = iArr[i11];
            if (i12 != 0 && i12 >= Math.max(3, i8 / 200)) {
                int i13 = iArr2[i11] / i12;
                int i14 = iArr3[i11] / i12;
                if (Math.abs((iArr4[i11] / i12) - max3) + Math.abs(i14 - max2) + Math.abs(i13 - max) >= 90 && i12 > i10) {
                    i9 = i11;
                    i10 = i12;
                }
            }
        }
        if (i9 >= 0) {
            return averageColor(i9, iArr, iArr2, iArr3, iArr4);
        }
        if (i7 >= 0) {
            return averageColor(i7, iArr, iArr2, iArr3, iArr4);
        }
        return -15722456;
    }

    private static int lighten(int i7, float f7) {
        return Color.rgb(clamp((int) (((255 - r0) * f7) + Color.red(i7))), clamp((int) (((255 - r1) * f7) + Color.green(i7))), clamp((int) (((255 - r3) * f7) + Color.blue(i7))));
    }

    private static float luminance(int i7) {
        return ((Color.blue(i7) / 255.0f) * 0.0722f) + ((Color.green(i7) / 255.0f) * 0.7152f) + ((Color.red(i7) / 255.0f) * 0.2126f);
    }

    private static int mix(int i7, int i8, float f7) {
        float f8 = 1.0f - f7;
        return Color.rgb(clamp((int) ((Color.red(i8) * f7) + (Color.red(i7) * f8))), clamp((int) ((Color.green(i8) * f7) + (Color.green(i7) * f8))), clamp((int) ((Color.blue(i8) * f7) + (Color.blue(i7) * f8))));
    }

    private static int scaleRgb(int i7, float f7) {
        return Color.rgb(clamp((int) (Color.red(i7) * f7)), clamp((int) (Color.green(i7) * f7)), clamp((int) (Color.blue(i7) * f7)));
    }
}
