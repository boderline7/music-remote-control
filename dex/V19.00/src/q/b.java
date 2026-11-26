package q;

import java.util.Collection;
import java.util.Map;
import java.util.Set;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final class b extends k implements Map {

    /* renamed from: h, reason: collision with root package name */
    public a f8371h;

    public b(b bVar) {
        if (bVar != null) {
            int i7 = bVar.f8412c;
            b(i7);
            if (this.f8412c != 0) {
                for (int i8 = 0; i8 < i7; i8++) {
                    put(bVar.h(i8), bVar.j(i8));
                }
            } else if (i7 > 0) {
                System.arraycopy(bVar.f8410a, 0, this.f8410a, 0, i7);
                System.arraycopy(bVar.f8411b, 0, this.f8411b, 0, i7 << 1);
                this.f8412c = i7;
            }
        }
    }

    @Override // java.util.Map
    public final Set entrySet() {
        int i7 = 0;
        if (this.f8371h == null) {
            this.f8371h = new a(0, this);
        }
        a aVar = this.f8371h;
        if (((h) aVar.f7060a) == null) {
            aVar.f7060a = new h(aVar, i7);
        }
        return (h) aVar.f7060a;
    }

    @Override // java.util.Map
    public final Set keySet() {
        if (this.f8371h == null) {
            this.f8371h = new a(0, this);
        }
        a aVar = this.f8371h;
        if (((h) aVar.f7061b) == null) {
            aVar.f7061b = new h(aVar, 1);
        }
        return (h) aVar.f7061b;
    }

    @Override // java.util.Map
    public final void putAll(Map map) {
        b(map.size() + this.f8412c);
        for (Map.Entry entry : map.entrySet()) {
            put(entry.getKey(), entry.getValue());
        }
    }

    @Override // java.util.Map
    public final Collection values() {
        if (this.f8371h == null) {
            this.f8371h = new a(0, this);
        }
        a aVar = this.f8371h;
        if (((j) aVar.f7062c) == null) {
            aVar.f7062c = new j(aVar);
        }
        return (j) aVar.f7062c;
    }
}
