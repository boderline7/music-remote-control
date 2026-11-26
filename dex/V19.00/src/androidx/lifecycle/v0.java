package androidx.lifecycle;

import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;
import android.os.Parcelable;
import android.util.Size;
import android.util.SizeF;
import android.util.SparseArray;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_2_1633180.dex */
public final class v0 {
    public static final u0 Companion = new u0();

    /* renamed from: f, reason: collision with root package name */
    public static final Class[] f2206f = {Boolean.TYPE, boolean[].class, Double.TYPE, double[].class, Integer.TYPE, int[].class, Long.TYPE, long[].class, String.class, String[].class, Binder.class, Bundle.class, Byte.TYPE, byte[].class, Character.TYPE, char[].class, CharSequence.class, CharSequence[].class, ArrayList.class, Float.TYPE, float[].class, Parcelable.class, Parcelable[].class, Serializable.class, Short.TYPE, short[].class, SparseArray.class, Size.class, SizeF.class};

    /* renamed from: a, reason: collision with root package name */
    public final LinkedHashMap f2207a;

    /* renamed from: b, reason: collision with root package name */
    public final LinkedHashMap f2208b;

    /* renamed from: c, reason: collision with root package name */
    public final LinkedHashMap f2209c;

    /* renamed from: d, reason: collision with root package name */
    public final LinkedHashMap f2210d;

    /* renamed from: e, reason: collision with root package name */
    public final t0 f2211e;

    public v0() {
        this.f2207a = new LinkedHashMap();
        this.f2208b = new LinkedHashMap();
        this.f2209c = new LinkedHashMap();
        this.f2210d = new LinkedHashMap();
        this.f2211e = new t0(1, this);
    }

    public v0(HashMap hashMap) {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        this.f2207a = linkedHashMap;
        this.f2208b = new LinkedHashMap();
        this.f2209c = new LinkedHashMap();
        this.f2210d = new LinkedHashMap();
        this.f2211e = new t0(0, this);
        linkedHashMap.putAll(hashMap);
    }

    public static Bundle a(v0 v0Var) {
        i5.a.g("this$0", v0Var);
        LinkedHashMap linkedHashMap = v0Var.f2208b;
        i5.a.g("<this>", linkedHashMap);
        int size = linkedHashMap.size();
        Iterator it = (size != 0 ? size != 1 ? new LinkedHashMap(linkedHashMap) : n1.z.i1(linkedHashMap) : c5.l.B1()).entrySet().iterator();
        while (true) {
            boolean hasNext = it.hasNext();
            LinkedHashMap linkedHashMap2 = v0Var.f2207a;
            int i7 = 0;
            if (!hasNext) {
                Set<String> keySet = linkedHashMap2.keySet();
                ArrayList arrayList = new ArrayList(keySet.size());
                ArrayList arrayList2 = new ArrayList(arrayList.size());
                for (String str : keySet) {
                    arrayList.add(str);
                    arrayList2.add(linkedHashMap2.get(str));
                }
                b5.a[] aVarArr = {new b5.a("keys", arrayList), new b5.a("values", arrayList2)};
                Bundle bundle = new Bundle(2);
                while (i7 < 2) {
                    b5.a aVar = aVarArr[i7];
                    String str2 = (String) aVar.f2975a;
                    Object obj = aVar.f2976b;
                    if (obj == null) {
                        bundle.putString(str2, null);
                    } else if (obj instanceof Boolean) {
                        bundle.putBoolean(str2, ((Boolean) obj).booleanValue());
                    } else if (obj instanceof Byte) {
                        bundle.putByte(str2, ((Number) obj).byteValue());
                    } else if (obj instanceof Character) {
                        bundle.putChar(str2, ((Character) obj).charValue());
                    } else if (obj instanceof Double) {
                        bundle.putDouble(str2, ((Number) obj).doubleValue());
                    } else if (obj instanceof Float) {
                        bundle.putFloat(str2, ((Number) obj).floatValue());
                    } else if (obj instanceof Integer) {
                        bundle.putInt(str2, ((Number) obj).intValue());
                    } else if (obj instanceof Long) {
                        bundle.putLong(str2, ((Number) obj).longValue());
                    } else if (obj instanceof Short) {
                        bundle.putShort(str2, ((Number) obj).shortValue());
                    } else if (obj instanceof Bundle) {
                        bundle.putBundle(str2, (Bundle) obj);
                    } else if (obj instanceof CharSequence) {
                        bundle.putCharSequence(str2, (CharSequence) obj);
                    } else if (obj instanceof Parcelable) {
                        bundle.putParcelable(str2, (Parcelable) obj);
                    } else if (obj instanceof boolean[]) {
                        bundle.putBooleanArray(str2, (boolean[]) obj);
                    } else if (obj instanceof byte[]) {
                        bundle.putByteArray(str2, (byte[]) obj);
                    } else if (obj instanceof char[]) {
                        bundle.putCharArray(str2, (char[]) obj);
                    } else if (obj instanceof double[]) {
                        bundle.putDoubleArray(str2, (double[]) obj);
                    } else if (obj instanceof float[]) {
                        bundle.putFloatArray(str2, (float[]) obj);
                    } else if (obj instanceof int[]) {
                        bundle.putIntArray(str2, (int[]) obj);
                    } else if (obj instanceof long[]) {
                        bundle.putLongArray(str2, (long[]) obj);
                    } else if (obj instanceof short[]) {
                        bundle.putShortArray(str2, (short[]) obj);
                    } else if (obj instanceof Object[]) {
                        Class<?> componentType = obj.getClass().getComponentType();
                        i5.a.d(componentType);
                        if (Parcelable.class.isAssignableFrom(componentType)) {
                            bundle.putParcelableArray(str2, (Parcelable[]) obj);
                        } else if (String.class.isAssignableFrom(componentType)) {
                            bundle.putStringArray(str2, (String[]) obj);
                        } else if (CharSequence.class.isAssignableFrom(componentType)) {
                            bundle.putCharSequenceArray(str2, (CharSequence[]) obj);
                        } else {
                            if (!Serializable.class.isAssignableFrom(componentType)) {
                                throw new IllegalArgumentException("Illegal value array type " + componentType.getCanonicalName() + " for key \"" + str2 + '\"');
                            }
                            bundle.putSerializable(str2, (Serializable) obj);
                        }
                    } else if (obj instanceof Serializable) {
                        bundle.putSerializable(str2, (Serializable) obj);
                    } else if (obj instanceof IBinder) {
                        l0.a.a(bundle, str2, (IBinder) obj);
                    } else if (obj instanceof Size) {
                        l0.b.a(bundle, str2, (Size) obj);
                    } else {
                        if (!(obj instanceof SizeF)) {
                            throw new IllegalArgumentException("Illegal value type " + obj.getClass().getCanonicalName() + " for key \"" + str2 + '\"');
                        }
                        l0.b.b(bundle, str2, (SizeF) obj);
                    }
                    i7++;
                }
                return bundle;
            }
            Map.Entry entry = (Map.Entry) it.next();
            String str3 = (String) entry.getKey();
            Bundle a7 = ((j1.f) entry.getValue()).a();
            i5.a.g("key", str3);
            Companion.getClass();
            if (a7 != null) {
                Class[] clsArr = f2206f;
                while (i7 < 29) {
                    Class cls = clsArr[i7];
                    i5.a.d(cls);
                    if (!cls.isInstance(a7)) {
                        i7++;
                    }
                }
                throw new IllegalArgumentException("Can't put value with type " + a7.getClass() + " into saved state");
            }
            Object obj2 = v0Var.f2209c.get(str3);
            g0 g0Var = obj2 instanceof g0 ? (g0) obj2 : null;
            if (g0Var != null) {
                g0Var.h(a7);
            } else {
                linkedHashMap2.put(str3, a7);
            }
            p5.a aVar2 = (p5.a) v0Var.f2210d.get(str3);
            if (aVar2 != null) {
                ((p5.b) aVar2).e(a7);
            }
        }
    }
}
