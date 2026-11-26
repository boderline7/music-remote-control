package a0;

import android.content.Context;
import android.content.res.XmlResourceParser;
import android.util.SparseArray;
import androidx.constraintlayout.widget.ConstraintLayout;
import java.io.IOException;
import java.util.ArrayList;
import org.xmlpull.v1.XmlPullParserException;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final class i {
    public static final String TAG = "ConstraintLayoutStates";

    /* renamed from: a, reason: collision with root package name */
    public final ConstraintLayout f64a;

    /* renamed from: b, reason: collision with root package name */
    public int f65b = -1;

    /* renamed from: c, reason: collision with root package name */
    public int f66c = -1;

    /* renamed from: d, reason: collision with root package name */
    public final SparseArray f67d = new SparseArray();

    /* renamed from: e, reason: collision with root package name */
    public final SparseArray f68e = new SparseArray();

    public i(Context context, ConstraintLayout constraintLayout, int i7) {
        this.f64a = constraintLayout;
        XmlResourceParser xml = context.getResources().getXml(i7);
        try {
            int eventType = xml.getEventType();
            g gVar = null;
            while (true) {
                char c7 = 1;
                if (eventType == 1) {
                    return;
                }
                if (eventType == 0) {
                    xml.getName();
                } else if (eventType == 2) {
                    String name = xml.getName();
                    switch (name.hashCode()) {
                        case -1349929691:
                            if (name.equals("ConstraintSet")) {
                                c7 = 4;
                                break;
                            }
                            c7 = 65535;
                            break;
                        case 80204913:
                            if (name.equals("State")) {
                                c7 = 2;
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
                        case 1657696882:
                            if (name.equals("layoutDescription")) {
                                c7 = 0;
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
                        gVar = new g(context, xml);
                        this.f67d.put(gVar.f54a, gVar);
                    } else if (c7 == 3) {
                        h hVar = new h(context, xml);
                        if (gVar != null) {
                            gVar.f55b.add(hVar);
                        }
                    } else if (c7 == 4) {
                        a(context, xml);
                    }
                }
                eventType = xml.next();
            }
        } catch (IOException e7) {
            e7.printStackTrace();
        } catch (XmlPullParserException e8) {
            e8.printStackTrace();
        }
    }

    public final void a(Context context, XmlResourceParser xmlResourceParser) {
        p pVar = new p();
        int attributeCount = xmlResourceParser.getAttributeCount();
        for (int i7 = 0; i7 < attributeCount; i7++) {
            String attributeName = xmlResourceParser.getAttributeName(i7);
            String attributeValue = xmlResourceParser.getAttributeValue(i7);
            if (attributeName != null && attributeValue != null && "id".equals(attributeName)) {
                int identifier = attributeValue.contains("/") ? context.getResources().getIdentifier(attributeValue.substring(attributeValue.indexOf(47) + 1), "id", context.getPackageName()) : -1;
                if (identifier == -1 && attributeValue.length() > 1) {
                    identifier = Integer.parseInt(attributeValue.substring(1));
                }
                pVar.k(context, xmlResourceParser);
                this.f68e.put(identifier, pVar);
                return;
            }
        }
    }

    public final void b(float f7, float f8, int i7) {
        int i8 = this.f65b;
        SparseArray sparseArray = this.f67d;
        int i9 = 0;
        ConstraintLayout constraintLayout = this.f64a;
        if (i8 != i7) {
            this.f65b = i7;
            g gVar = (g) sparseArray.get(i7);
            while (true) {
                ArrayList arrayList = gVar.f55b;
                if (i9 >= arrayList.size()) {
                    i9 = -1;
                    break;
                } else if (((h) arrayList.get(i9)).a(f7, f8)) {
                    break;
                } else {
                    i9++;
                }
            }
            ArrayList arrayList2 = gVar.f55b;
            p pVar = i9 == -1 ? gVar.f57d : ((h) arrayList2.get(i9)).f63f;
            if (i9 != -1) {
                int i10 = ((h) arrayList2.get(i9)).f62e;
            }
            if (pVar == null) {
                return;
            }
            this.f66c = i9;
            pVar.b(constraintLayout);
            return;
        }
        g gVar2 = i7 == -1 ? (g) sparseArray.valueAt(0) : (g) sparseArray.get(i8);
        int i11 = this.f66c;
        if (i11 == -1 || !((h) gVar2.f55b.get(i11)).a(f7, f8)) {
            while (true) {
                ArrayList arrayList3 = gVar2.f55b;
                if (i9 >= arrayList3.size()) {
                    i9 = -1;
                    break;
                } else if (((h) arrayList3.get(i9)).a(f7, f8)) {
                    break;
                } else {
                    i9++;
                }
            }
            if (this.f66c == i9) {
                return;
            }
            ArrayList arrayList4 = gVar2.f55b;
            p pVar2 = i9 == -1 ? null : ((h) arrayList4.get(i9)).f63f;
            if (i9 != -1) {
                int i12 = ((h) arrayList4.get(i9)).f62e;
            }
            if (pVar2 == null) {
                return;
            }
            this.f66c = i9;
            pVar2.b(constraintLayout);
        }
    }
}
