package y;

import android.content.Context;
import android.content.res.XmlResourceParser;
import android.util.Xml;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import org.xmlpull.v1.XmlPullParserException;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final class g {
    public static final int UNSET = -1;

    /* renamed from: b, reason: collision with root package name */
    public static final HashMap f9833b;

    /* renamed from: a, reason: collision with root package name */
    public HashMap f9834a = new HashMap();

    static {
        HashMap hashMap = new HashMap();
        f9833b = hashMap;
        try {
            hashMap.put("KeyAttribute", e.class.getConstructor(new Class[0]));
            hashMap.put("KeyPosition", i.class.getConstructor(new Class[0]));
            hashMap.put("KeyCycle", f.class.getConstructor(new Class[0]));
            hashMap.put("KeyTimeCycle", j.class.getConstructor(new Class[0]));
            hashMap.put("KeyTrigger", k.class.getConstructor(new Class[0]));
        } catch (NoSuchMethodException unused) {
        }
    }

    public g(Context context, XmlResourceParser xmlResourceParser) {
        HashMap hashMap;
        HashMap hashMap2;
        try {
            int eventType = xmlResourceParser.getEventType();
            c cVar = null;
            while (eventType != 1) {
                if (eventType == 2) {
                    String name = xmlResourceParser.getName();
                    HashMap hashMap3 = f9833b;
                    if (hashMap3.containsKey(name)) {
                        try {
                            Constructor constructor = (Constructor) hashMap3.get(name);
                            if (constructor == null) {
                                throw new NullPointerException("Keymaker for " + name + " not found");
                            }
                            c cVar2 = (c) constructor.newInstance(new Object[0]);
                            try {
                                cVar2.d(context, Xml.asAttributeSet(xmlResourceParser));
                                b(cVar2);
                            } catch (Exception unused) {
                            }
                            cVar = cVar2;
                        } catch (Exception unused2) {
                        }
                    } else if (name.equalsIgnoreCase(e0.CUSTOM_ATTRIBUTE)) {
                        if (cVar != null && (hashMap2 = cVar.f9756d) != null) {
                            a0.c.d(context, xmlResourceParser, hashMap2);
                        }
                    } else if (name.equalsIgnoreCase(e0.CUSTOM_METHOD) && cVar != null && (hashMap = cVar.f9756d) != null) {
                        a0.c.d(context, xmlResourceParser, hashMap);
                    }
                } else if (eventType == 3 && e0.KEY_FRAME_SET_TAG.equals(xmlResourceParser.getName())) {
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

    public final void a(n nVar) {
        Integer valueOf = Integer.valueOf(nVar.f9868c);
        HashMap hashMap = this.f9834a;
        ArrayList arrayList = (ArrayList) hashMap.get(valueOf);
        if (arrayList != null) {
            nVar.f9888w.addAll(arrayList);
        }
        ArrayList arrayList2 = (ArrayList) hashMap.get(-1);
        if (arrayList2 != null) {
            Iterator it = arrayList2.iterator();
            while (it.hasNext()) {
                c cVar = (c) it.next();
                String str = ((a0.f) nVar.f9867b.getLayoutParams()).Y;
                String str2 = cVar.f9755c;
                if (str2 != null && str != null && str.matches(str2)) {
                    nVar.a(cVar);
                }
            }
        }
    }

    public final void b(c cVar) {
        Integer valueOf = Integer.valueOf(cVar.f9754b);
        HashMap hashMap = this.f9834a;
        if (!hashMap.containsKey(valueOf)) {
            hashMap.put(Integer.valueOf(cVar.f9754b), new ArrayList());
        }
        ArrayList arrayList = (ArrayList) hashMap.get(Integer.valueOf(cVar.f9754b));
        if (arrayList != null) {
            arrayList.add(cVar);
        }
    }
}
