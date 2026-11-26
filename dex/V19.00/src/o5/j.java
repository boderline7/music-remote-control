package o5;

import h5.p;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final class j extends i5.h implements p {

    /* renamed from: a, reason: collision with root package name */
    public final /* synthetic */ int f8063a;

    /* renamed from: b, reason: collision with root package name */
    public final /* synthetic */ boolean f8064b;

    /* renamed from: c, reason: collision with root package name */
    public final /* synthetic */ Object f8065c;

    public j(Object obj, boolean z6, int i7) {
        this.f8063a = i7;
        this.f8065c = obj;
        this.f8064b = z6;
    }

    public final b5.a c(int i7, CharSequence charSequence) {
        Object obj;
        b5.a aVar;
        Object obj2;
        int i8 = this.f8063a;
        Object obj3 = this.f8065c;
        switch (i8) {
            case 0:
                i5.a.g("$this$$receiver", charSequence);
                int H = k.H(i7, charSequence, this.f8064b, (char[]) obj3);
                if (H < 0) {
                    return null;
                }
                return new b5.a(Integer.valueOf(H), 1);
            default:
                i5.a.g("$this$$receiver", charSequence);
                List list = (List) obj3;
                boolean z6 = this.f8064b;
                if (z6 || list.size() != 1) {
                    if (i7 < 0) {
                        i7 = 0;
                    }
                    l5.e eVar = new l5.e(i7, charSequence.length(), 1);
                    boolean z7 = charSequence instanceof String;
                    int i9 = eVar.f7452c;
                    int i10 = eVar.f7451b;
                    if (z7) {
                        if ((i9 > 0 && i7 <= i10) || (i9 < 0 && i10 <= i7)) {
                            while (true) {
                                Iterator it = list.iterator();
                                while (true) {
                                    if (it.hasNext()) {
                                        obj2 = it.next();
                                        String str = (String) obj2;
                                        if (k.K(0, i7, str.length(), str, (String) charSequence, z6)) {
                                        }
                                    } else {
                                        obj2 = null;
                                    }
                                }
                                String str2 = (String) obj2;
                                if (str2 != null) {
                                    aVar = new b5.a(Integer.valueOf(i7), str2);
                                } else if (i7 != i10) {
                                    i7 += i9;
                                }
                            }
                        }
                        aVar = null;
                    } else {
                        if ((i9 > 0 && i7 <= i10) || (i9 < 0 && i10 <= i7)) {
                            while (true) {
                                Iterator it2 = list.iterator();
                                while (true) {
                                    if (it2.hasNext()) {
                                        obj = it2.next();
                                        String str3 = (String) obj;
                                        if (k.L(str3, 0, charSequence, i7, str3.length(), z6)) {
                                        }
                                    } else {
                                        obj = null;
                                    }
                                }
                                String str4 = (String) obj;
                                if (str4 != null) {
                                    aVar = new b5.a(Integer.valueOf(i7), str4);
                                } else if (i7 != i10) {
                                    i7 += i9;
                                }
                            }
                        }
                        aVar = null;
                    }
                } else {
                    int size = list.size();
                    if (size == 0) {
                        throw new NoSuchElementException("List is empty.");
                    }
                    if (size != 1) {
                        throw new IllegalArgumentException("List has more than one element.");
                    }
                    String str5 = (String) list.get(0);
                    int G = k.G(charSequence, str5, i7, false, 4);
                    if (G >= 0) {
                        aVar = new b5.a(Integer.valueOf(G), str5);
                    }
                    aVar = null;
                }
                if (aVar == null) {
                    return null;
                }
                return new b5.a(aVar.f2975a, Integer.valueOf(((String) aVar.f2976b).length()));
        }
    }
}
