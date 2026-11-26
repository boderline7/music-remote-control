package c5;

import java.util.Iterator;
import java.util.NoSuchElementException;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public class d implements Iterator {

    /* renamed from: a, reason: collision with root package name */
    public final /* synthetic */ int f3066a = 0;

    /* renamed from: b, reason: collision with root package name */
    public int f3067b;

    /* renamed from: c, reason: collision with root package name */
    public final Object f3068c;

    public d(g gVar) {
        this.f3068c = gVar;
    }

    public d(n5.a aVar) {
        this.f3068c = aVar.f7785a.iterator();
        this.f3067b = aVar.f7786b;
    }

    public d(Object[] objArr) {
        i5.a.g("array", objArr);
        this.f3068c = objArr;
    }

    @Override // java.util.Iterator
    public final boolean hasNext() {
        int i7 = this.f3066a;
        Object obj = this.f3068c;
        switch (i7) {
            case 0:
                return this.f3067b < ((g) obj).a();
            case 1:
                return this.f3067b < ((Object[]) obj).length;
        }
        while (this.f3067b > 0) {
            Iterator it = (Iterator) obj;
            if (!it.hasNext()) {
                return ((Iterator) obj).hasNext();
            }
            it.next();
            this.f3067b--;
        }
        return ((Iterator) obj).hasNext();
    }

    @Override // java.util.Iterator
    public final Object next() {
        int i7 = this.f3066a;
        Object obj = this.f3068c;
        switch (i7) {
            case 0:
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                int i8 = this.f3067b;
                this.f3067b = i8 + 1;
                return ((g) obj).get(i8);
            case 1:
                try {
                    int i9 = this.f3067b;
                    this.f3067b = i9 + 1;
                    return ((Object[]) obj)[i9];
                } catch (ArrayIndexOutOfBoundsException e7) {
                    this.f3067b--;
                    throw new NoSuchElementException(e7.getMessage());
                }
        }
        while (this.f3067b > 0) {
            Iterator it = (Iterator) obj;
            if (!it.hasNext()) {
                return ((Iterator) obj).next();
            }
            it.next();
            this.f3067b--;
        }
        return ((Iterator) obj).next();
    }

    @Override // java.util.Iterator
    public final void remove() {
        switch (this.f3066a) {
            case 0:
                throw new UnsupportedOperationException("Operation is not supported for read-only collection");
            case 1:
                throw new UnsupportedOperationException("Operation is not supported for read-only collection");
            default:
                throw new UnsupportedOperationException("Operation is not supported for read-only collection");
        }
    }
}
