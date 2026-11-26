package v;

import java.util.ArrayList;
import java.util.Iterator;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public class h implements e {

    /* renamed from: d, reason: collision with root package name */
    public final v f9342d;

    /* renamed from: f, reason: collision with root package name */
    public int f9344f;

    /* renamed from: g, reason: collision with root package name */
    public int f9345g;

    /* renamed from: a, reason: collision with root package name */
    public v f9339a = null;

    /* renamed from: b, reason: collision with root package name */
    public boolean f9340b = false;

    /* renamed from: c, reason: collision with root package name */
    public boolean f9341c = false;

    /* renamed from: e, reason: collision with root package name */
    public g f9343e = g.UNKNOWN;

    /* renamed from: h, reason: collision with root package name */
    public int f9346h = 1;

    /* renamed from: i, reason: collision with root package name */
    public i f9347i = null;

    /* renamed from: j, reason: collision with root package name */
    public boolean f9348j = false;

    /* renamed from: k, reason: collision with root package name */
    public final ArrayList f9349k = new ArrayList();

    /* renamed from: l, reason: collision with root package name */
    public final ArrayList f9350l = new ArrayList();

    public h(v vVar) {
        this.f9342d = vVar;
    }

    @Override // v.e
    public final void a(e eVar) {
        ArrayList arrayList = this.f9350l;
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            if (!((h) it.next()).f9348j) {
                return;
            }
        }
        this.f9341c = true;
        v vVar = this.f9339a;
        if (vVar != null) {
            vVar.a(this);
        }
        if (this.f9340b) {
            this.f9342d.a(this);
            return;
        }
        Iterator it2 = arrayList.iterator();
        h hVar = null;
        int i7 = 0;
        while (it2.hasNext()) {
            h hVar2 = (h) it2.next();
            if (!(hVar2 instanceof i)) {
                i7++;
                hVar = hVar2;
            }
        }
        if (hVar != null && i7 == 1 && hVar.f9348j) {
            i iVar = this.f9347i;
            if (iVar != null) {
                if (!iVar.f9348j) {
                    return;
                } else {
                    this.f9344f = this.f9346h * iVar.f9345g;
                }
            }
            d(hVar.f9345g + this.f9344f);
        }
        v vVar2 = this.f9339a;
        if (vVar2 != null) {
            vVar2.a(this);
        }
    }

    public final void b(e eVar) {
        this.f9349k.add(eVar);
        if (this.f9348j) {
            eVar.a(eVar);
        }
    }

    public final void c() {
        this.f9350l.clear();
        this.f9349k.clear();
        this.f9348j = false;
        this.f9345g = 0;
        this.f9341c = false;
        this.f9340b = false;
    }

    public void d(int i7) {
        if (this.f9348j) {
            return;
        }
        this.f9348j = true;
        this.f9345g = i7;
        Iterator it = this.f9349k.iterator();
        while (it.hasNext()) {
            e eVar = (e) it.next();
            eVar.a(eVar);
        }
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(this.f9342d.f9377b.f9149k0);
        sb.append(":");
        sb.append(this.f9343e);
        sb.append("(");
        sb.append(this.f9348j ? Integer.valueOf(this.f9345g) : "unresolved");
        sb.append(") <t=");
        sb.append(this.f9350l.size());
        sb.append(":d=");
        sb.append(this.f9349k.size());
        sb.append(">");
        return sb.toString();
    }
}
