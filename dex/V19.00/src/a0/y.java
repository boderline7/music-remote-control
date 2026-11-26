package a0;

import android.content.Context;
import android.content.res.TypedArray;
import android.content.res.XmlResourceParser;
import android.util.SparseArray;
import android.util.Xml;
import java.io.IOException;
import java.util.ArrayList;
import org.xmlpull.v1.XmlPullParserException;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final class y {
    public static final String TAG = "ConstraintLayoutStates";

    /* renamed from: a, reason: collision with root package name */
    public final int f193a;

    /* renamed from: b, reason: collision with root package name */
    public final SparseArray f194b = new SparseArray();

    public y(Context context, XmlResourceParser xmlResourceParser) {
        this.f193a = -1;
        new SparseArray();
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(Xml.asAttributeSet(xmlResourceParser), t.StateSet);
        int indexCount = obtainStyledAttributes.getIndexCount();
        for (int i7 = 0; i7 < indexCount; i7++) {
            int index = obtainStyledAttributes.getIndex(i7);
            if (index == t.StateSet_defaultState) {
                this.f193a = obtainStyledAttributes.getResourceId(index, this.f193a);
            }
        }
        obtainStyledAttributes.recycle();
        try {
            int eventType = xmlResourceParser.getEventType();
            w wVar = null;
            while (true) {
                char c7 = 1;
                if (eventType == 1) {
                    return;
                }
                if (eventType == 0) {
                    xmlResourceParser.getName();
                } else if (eventType == 2) {
                    String name = xmlResourceParser.getName();
                    switch (name.hashCode()) {
                        case 80204913:
                            if (name.equals("State")) {
                                c7 = 2;
                                break;
                            }
                            c7 = 65535;
                            break;
                        case 1301459538:
                            if (name.equals("LayoutDescription")) {
                                c7 = 0;
                                break;
                            }
                            c7 = 65535;
                            break;
                        case 1382829617:
                            if (name.equals("StateSet")) {
                                break;
                            }
                            c7 = 65535;
                            break;
                        case 1901439077:
                            if (name.equals("Variant")) {
                                c7 = 3;
                                break;
                            }
                            c7 = 65535;
                            break;
                        default:
                            c7 = 65535;
                            break;
                    }
                    if (c7 == 2) {
                        wVar = new w(context, xmlResourceParser);
                        this.f194b.put(wVar.f185a, wVar);
                    } else if (c7 == 3) {
                        x xVar = new x(context, xmlResourceParser);
                        if (wVar != null) {
                            wVar.f186b.add(xVar);
                        }
                    }
                } else if (eventType != 3) {
                    continue;
                } else if ("StateSet".equals(xmlResourceParser.getName())) {
                    return;
                }
                eventType = xmlResourceParser.next();
            }
        } catch (IOException e7) {
            e7.printStackTrace();
        } catch (XmlPullParserException e8) {
            e8.printStackTrace();
        }
    }

    public final int a(int i7) {
        int i8;
        float f7 = -1;
        SparseArray sparseArray = this.f194b;
        int i9 = 0;
        if (-1 == i7) {
            w wVar = i7 == -1 ? (w) sparseArray.valueAt(0) : (w) sparseArray.get(-1);
            if (wVar == null) {
                return -1;
            }
            while (true) {
                ArrayList arrayList = wVar.f186b;
                if (i9 >= arrayList.size()) {
                    i9 = -1;
                    break;
                }
                if (((x) arrayList.get(i9)).a(f7, f7)) {
                    break;
                }
                i9++;
            }
            if (-1 == i9) {
                return -1;
            }
            i8 = i9 == -1 ? wVar.f187c : ((x) wVar.f186b.get(i9)).f192e;
        } else {
            w wVar2 = (w) sparseArray.get(i7);
            if (wVar2 == null) {
                return -1;
            }
            while (true) {
                ArrayList arrayList2 = wVar2.f186b;
                if (i9 >= arrayList2.size()) {
                    i9 = -1;
                    break;
                }
                if (((x) arrayList2.get(i9)).a(f7, f7)) {
                    break;
                }
                i9++;
            }
            i8 = i9 == -1 ? wVar2.f187c : ((x) wVar2.f186b.get(i9)).f192e;
        }
        return i8;
    }
}
