package i0;

import android.content.ContentResolver;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Typeface;
import android.graphics.fonts.Font;
import android.graphics.fonts.FontFamily;
import android.graphics.fonts.FontStyle;
import android.os.ParcelFileDescriptor;
import java.io.IOException;
import java.io.InputStream;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final class l extends e.a {
    public static Font D(FontFamily fontFamily, int i7) {
        FontStyle fontStyle = new FontStyle((i7 & 1) != 0 ? 700 : 400, (i7 & 2) != 0 ? 1 : 0);
        Font font = fontFamily.getFont(0);
        int E = E(fontStyle, font.getStyle());
        for (int i8 = 1; i8 < fontFamily.getSize(); i8++) {
            Font font2 = fontFamily.getFont(i8);
            int E2 = E(fontStyle, font2.getStyle());
            if (E2 < E) {
                font = font2;
                E = E2;
            }
        }
        return font;
    }

    public static int E(FontStyle fontStyle, FontStyle fontStyle2) {
        return (Math.abs(fontStyle.getWeight() - fontStyle2.getWeight()) / 100) + (fontStyle.getSlant() == fontStyle2.getSlant() ? 0 : 2);
    }

    @Override // e.a
    public final m0.i A(int i7, m0.i[] iVarArr) {
        throw new RuntimeException("Do not use this function in API 29 or later.");
    }

    @Override // e.a
    public final Typeface t(Context context, h0.f fVar, Resources resources, int i7) {
        try {
            FontFamily.Builder builder = null;
            for (h0.g gVar : fVar.f6620a) {
                try {
                    Font build = new Font.Builder(resources, gVar.f6626f).setWeight(gVar.f6622b).setSlant(gVar.f6623c ? 1 : 0).setTtcIndex(gVar.f6625e).setFontVariationSettings(gVar.f6624d).build();
                    if (builder == null) {
                        builder = new FontFamily.Builder(build);
                    } else {
                        builder.addFont(build);
                    }
                } catch (IOException unused) {
                }
            }
            if (builder == null) {
                return null;
            }
            FontFamily build2 = builder.build();
            return new Typeface.CustomFallbackBuilder(build2).setStyle(D(build2, i7).getStyle()).build();
        } catch (Exception unused2) {
            return null;
        }
    }

    @Override // e.a
    public final Typeface u(Context context, m0.i[] iVarArr, int i7) {
        int i8;
        ParcelFileDescriptor openFileDescriptor;
        ContentResolver contentResolver = context.getContentResolver();
        try {
            int length = iVarArr.length;
            FontFamily.Builder builder = null;
            while (i8 < length) {
                m0.i iVar = iVarArr[i8];
                try {
                    openFileDescriptor = contentResolver.openFileDescriptor(iVar.f7503a, "r", null);
                } catch (IOException unused) {
                }
                if (openFileDescriptor == null) {
                    i8 = openFileDescriptor == null ? i8 + 1 : 0;
                } else {
                    try {
                        Font build = new Font.Builder(openFileDescriptor).setWeight(iVar.f7505c).setSlant(iVar.f7506d ? 1 : 0).setTtcIndex(iVar.f7504b).build();
                        if (builder == null) {
                            builder = new FontFamily.Builder(build);
                        } else {
                            builder.addFont(build);
                        }
                    } catch (Throwable th) {
                        try {
                            openFileDescriptor.close();
                        } catch (Throwable th2) {
                            th.addSuppressed(th2);
                        }
                        throw th;
                    }
                }
                openFileDescriptor.close();
            }
            if (builder == null) {
                return null;
            }
            FontFamily build2 = builder.build();
            return new Typeface.CustomFallbackBuilder(build2).setStyle(D(build2, i7).getStyle()).build();
        } catch (Exception unused2) {
            return null;
        }
    }

    @Override // e.a
    public final Typeface v(Context context, InputStream inputStream) {
        throw new RuntimeException("Do not use this function in API 29 or later.");
    }

    @Override // e.a
    public final Typeface w(Context context, Resources resources, int i7, String str, int i8) {
        try {
            Font build = new Font.Builder(resources, i7).build();
            return new Typeface.CustomFallbackBuilder(new FontFamily.Builder(build).build()).setStyle(build.getStyle()).build();
        } catch (Exception unused) {
            return null;
        }
    }
}
