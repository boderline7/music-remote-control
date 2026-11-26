package h0;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Typeface;
import android.util.TypedValue;
import java.io.IOException;
import java.util.WeakHashMap;
import org.xmlpull.v1.XmlPullParserException;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public abstract class q {
    public static final int ID_NULL = 0;

    /* renamed from: a, reason: collision with root package name */
    public static final ThreadLocal f6642a = new ThreadLocal();

    /* renamed from: b, reason: collision with root package name */
    public static final WeakHashMap f6643b = new WeakHashMap(0);

    /* renamed from: c, reason: collision with root package name */
    public static final Object f6644c = new Object();

    public static Typeface a(Context context, int i7) {
        if (context.isRestricted()) {
            return null;
        }
        return b(context, i7, new TypedValue(), 0, null, false, false);
    }

    public static Typeface b(Context context, int i7, TypedValue typedValue, int i8, l6.f fVar, boolean z6, boolean z7) {
        Resources resources = context.getResources();
        resources.getValue(i7, typedValue, true);
        CharSequence charSequence = typedValue.string;
        if (charSequence == null) {
            throw new Resources.NotFoundException("Resource \"" + resources.getResourceName(i7) + "\" (" + Integer.toHexString(i7) + ") is not a Font: " + typedValue);
        }
        String charSequence2 = charSequence.toString();
        Typeface typeface = null;
        if (charSequence2.startsWith("res/")) {
            int i9 = typedValue.assetCookie;
            q.f fVar2 = i0.h.f6782b;
            Typeface typeface2 = (Typeface) fVar2.a(i0.h.b(resources, i7, charSequence2, i9, i8));
            if (typeface2 != null) {
                if (fVar != null) {
                    fVar.i(typeface2);
                }
                typeface = typeface2;
            } else if (!z7) {
                try {
                    if (charSequence2.toLowerCase().endsWith(".xml")) {
                        e a7 = i.a(resources.getXml(i7), resources);
                        if (a7 != null) {
                            typeface = i0.h.a(context, a7, resources, i7, charSequence2, typedValue.assetCookie, i8, fVar, z6);
                        } else if (fVar != null) {
                            fVar.h(-3);
                        }
                    } else {
                        int i10 = typedValue.assetCookie;
                        Typeface w6 = i0.h.f6781a.w(context, resources, i7, charSequence2, i8);
                        if (w6 != null) {
                            fVar2.b(i0.h.b(resources, i7, charSequence2, i10, i8), w6);
                        }
                        if (fVar != null) {
                            if (w6 != null) {
                                fVar.i(w6);
                            } else {
                                fVar.h(-3);
                            }
                        }
                        typeface = w6;
                    }
                } catch (IOException | XmlPullParserException unused) {
                    if (fVar != null) {
                        fVar.h(-3);
                    }
                }
            }
        } else if (fVar != null) {
            fVar.h(-3);
        }
        if (typeface != null || fVar != null || z7) {
            return typeface;
        }
        throw new Resources.NotFoundException("Font resource ID #0x" + Integer.toHexString(i7) + " could not be retrieved.");
    }
}
