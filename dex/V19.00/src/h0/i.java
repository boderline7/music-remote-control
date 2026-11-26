package h0;

import android.content.res.Resources;
import android.content.res.TypedArray;
import android.content.res.XmlResourceParser;
import android.util.Base64;
import android.util.Xml;
import androidx.appcompat.widget.w;
import com.yalantis.ucrop.view.CropImageView;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.xmlpull.v1.XmlPullParserException;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public abstract class i {
    public static final int FETCH_STRATEGY_ASYNC = 1;
    public static final int FETCH_STRATEGY_BLOCKING = 0;
    public static final int INFINITE_TIMEOUT_VALUE = -1;

    public static e a(XmlResourceParser xmlResourceParser, Resources resources) {
        int next;
        do {
            next = xmlResourceParser.next();
            if (next == 2) {
                break;
            }
        } while (next != 1);
        if (next != 2) {
            throw new XmlPullParserException("No start tag found");
        }
        xmlResourceParser.require(2, null, "font-family");
        if (!xmlResourceParser.getName().equals("font-family")) {
            c(xmlResourceParser);
            return null;
        }
        TypedArray obtainAttributes = resources.obtainAttributes(Xml.asAttributeSet(xmlResourceParser), d0.d.FontFamily);
        String string = obtainAttributes.getString(d0.d.FontFamily_fontProviderAuthority);
        String string2 = obtainAttributes.getString(d0.d.FontFamily_fontProviderPackage);
        String string3 = obtainAttributes.getString(d0.d.FontFamily_fontProviderQuery);
        int resourceId = obtainAttributes.getResourceId(d0.d.FontFamily_fontProviderCerts, 0);
        int integer = obtainAttributes.getInteger(d0.d.FontFamily_fontProviderFetchStrategy, 1);
        int integer2 = obtainAttributes.getInteger(d0.d.FontFamily_fontProviderFetchTimeout, CropImageView.DEFAULT_IMAGE_TO_CROP_BOUNDS_ANIM_DURATION);
        String string4 = obtainAttributes.getString(d0.d.FontFamily_fontProviderSystemFontFamily);
        obtainAttributes.recycle();
        if (string != null && string2 != null && string3 != null) {
            while (xmlResourceParser.next() != 3) {
                c(xmlResourceParser);
            }
            return new h(new w(string, string2, string3, b(resources, resourceId)), integer, integer2, string4);
        }
        ArrayList arrayList = new ArrayList();
        while (xmlResourceParser.next() != 3) {
            if (xmlResourceParser.getEventType() == 2) {
                if (xmlResourceParser.getName().equals("font")) {
                    TypedArray obtainAttributes2 = resources.obtainAttributes(Xml.asAttributeSet(xmlResourceParser), d0.d.FontFamilyFont);
                    int i7 = obtainAttributes2.getInt(obtainAttributes2.hasValue(d0.d.FontFamilyFont_fontWeight) ? d0.d.FontFamilyFont_fontWeight : d0.d.FontFamilyFont_android_fontWeight, 400);
                    boolean z6 = 1 == obtainAttributes2.getInt(obtainAttributes2.hasValue(d0.d.FontFamilyFont_fontStyle) ? d0.d.FontFamilyFont_fontStyle : d0.d.FontFamilyFont_android_fontStyle, 0);
                    int i8 = obtainAttributes2.hasValue(d0.d.FontFamilyFont_ttcIndex) ? d0.d.FontFamilyFont_ttcIndex : d0.d.FontFamilyFont_android_ttcIndex;
                    String string5 = obtainAttributes2.getString(obtainAttributes2.hasValue(d0.d.FontFamilyFont_fontVariationSettings) ? d0.d.FontFamilyFont_fontVariationSettings : d0.d.FontFamilyFont_android_fontVariationSettings);
                    int i9 = obtainAttributes2.getInt(i8, 0);
                    int i10 = obtainAttributes2.hasValue(d0.d.FontFamilyFont_font) ? d0.d.FontFamilyFont_font : d0.d.FontFamilyFont_android_font;
                    int resourceId2 = obtainAttributes2.getResourceId(i10, 0);
                    String string6 = obtainAttributes2.getString(i10);
                    obtainAttributes2.recycle();
                    while (xmlResourceParser.next() != 3) {
                        c(xmlResourceParser);
                    }
                    arrayList.add(new g(i7, i9, resourceId2, string6, string5, z6));
                } else {
                    c(xmlResourceParser);
                }
            }
        }
        if (arrayList.isEmpty()) {
            return null;
        }
        return new f((g[]) arrayList.toArray(new g[0]));
    }

    public static List b(Resources resources, int i7) {
        if (i7 == 0) {
            return Collections.emptyList();
        }
        TypedArray obtainTypedArray = resources.obtainTypedArray(i7);
        try {
            if (obtainTypedArray.length() == 0) {
                return Collections.emptyList();
            }
            ArrayList arrayList = new ArrayList();
            if (d.a(obtainTypedArray, 0) == 1) {
                for (int i8 = 0; i8 < obtainTypedArray.length(); i8++) {
                    int resourceId = obtainTypedArray.getResourceId(i8, 0);
                    if (resourceId != 0) {
                        String[] stringArray = resources.getStringArray(resourceId);
                        ArrayList arrayList2 = new ArrayList();
                        for (String str : stringArray) {
                            arrayList2.add(Base64.decode(str, 0));
                        }
                        arrayList.add(arrayList2);
                    }
                }
            } else {
                String[] stringArray2 = resources.getStringArray(i7);
                ArrayList arrayList3 = new ArrayList();
                for (String str2 : stringArray2) {
                    arrayList3.add(Base64.decode(str2, 0));
                }
                arrayList.add(arrayList3);
            }
            return arrayList;
        } finally {
            obtainTypedArray.recycle();
        }
    }

    public static void c(XmlResourceParser xmlResourceParser) {
        int i7 = 1;
        while (i7 > 0) {
            int next = xmlResourceParser.next();
            if (next == 2) {
                i7++;
            } else if (next == 3) {
                i7--;
            }
        }
    }
}
