package u;

import androidx.appcompat.widget.u;
import com.navi.tool.service.PhoneNotificationRelayService;
import com.yalantis.ucrop.view.CropImageView;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import s5.h0;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public class i {
    public static final int ANCHOR_BASELINE = 4;
    public static final int ANCHOR_BOTTOM = 3;
    public static final int ANCHOR_LEFT = 0;
    public static final int ANCHOR_RIGHT = 1;
    public static final int ANCHOR_TOP = 2;
    public static final int BOTH = 2;
    public static final int CHAIN_PACKED = 2;
    public static final int CHAIN_SPREAD = 0;
    public static final int CHAIN_SPREAD_INSIDE = 1;
    public static final int GONE = 8;
    public static final int HORIZONTAL = 0;
    public static final int INVISIBLE = 4;
    public static final int MATCH_CONSTRAINT_PERCENT = 2;
    public static final int MATCH_CONSTRAINT_RATIO = 3;
    public static final int MATCH_CONSTRAINT_RATIO_RESOLVED = 4;
    public static final int MATCH_CONSTRAINT_SPREAD = 0;
    public static final int MATCH_CONSTRAINT_WRAP = 1;
    public static final int UNKNOWN = -1;
    public static final int VERTICAL = 1;
    public static final int VISIBLE = 0;
    public static final int WRAP_BEHAVIOR_HORIZONTAL_ONLY = 1;
    public static final int WRAP_BEHAVIOR_INCLUDED = 0;
    public static final int WRAP_BEHAVIOR_SKIPPED = 3;
    public static final int WRAP_BEHAVIOR_VERTICAL_ONLY = 2;
    public int A;
    public float B;
    public int[] C;
    public float D;
    public boolean E;
    public boolean F;
    public boolean G;
    public int H;
    public int I;
    public final f J;
    public final f K;
    public final f L;
    public final f M;
    public final f N;
    public final f O;
    public final f P;
    public final f Q;
    public final f[] R;
    public final ArrayList S;
    public final boolean[] T;
    public h[] U;
    public i V;
    public int W;
    public int X;
    public float Y;
    public int Z;

    /* renamed from: a0, reason: collision with root package name */
    public int f9129a0;

    /* renamed from: b, reason: collision with root package name */
    public v.d f9130b;

    /* renamed from: b0, reason: collision with root package name */
    public int f9131b0;

    /* renamed from: c, reason: collision with root package name */
    public v.d f9132c;

    /* renamed from: c0, reason: collision with root package name */
    public int f9133c0;

    /* renamed from: d0, reason: collision with root package name */
    public int f9135d0;

    /* renamed from: e0, reason: collision with root package name */
    public int f9137e0;

    /* renamed from: f0, reason: collision with root package name */
    public float f9139f0;

    /* renamed from: g0, reason: collision with root package name */
    public float f9141g0;

    /* renamed from: h0, reason: collision with root package name */
    public Object f9143h0;

    /* renamed from: i0, reason: collision with root package name */
    public int f9145i0;

    /* renamed from: j, reason: collision with root package name */
    public String f9146j;

    /* renamed from: j0, reason: collision with root package name */
    public boolean f9147j0;

    /* renamed from: k, reason: collision with root package name */
    public boolean f9148k;

    /* renamed from: k0, reason: collision with root package name */
    public String f9149k0;

    /* renamed from: l, reason: collision with root package name */
    public boolean f9150l;

    /* renamed from: l0, reason: collision with root package name */
    public String f9151l0;

    /* renamed from: m, reason: collision with root package name */
    public boolean f9152m;

    /* renamed from: m0, reason: collision with root package name */
    public int f9153m0;

    /* renamed from: n, reason: collision with root package name */
    public boolean f9154n;

    /* renamed from: n0, reason: collision with root package name */
    public int f9155n0;

    /* renamed from: o, reason: collision with root package name */
    public int f9156o;

    /* renamed from: o0, reason: collision with root package name */
    public final float[] f9157o0;

    /* renamed from: p, reason: collision with root package name */
    public int f9158p;

    /* renamed from: p0, reason: collision with root package name */
    public final i[] f9159p0;

    /* renamed from: q, reason: collision with root package name */
    public int f9160q;

    /* renamed from: q0, reason: collision with root package name */
    public final i[] f9161q0;

    /* renamed from: r, reason: collision with root package name */
    public int f9162r;

    /* renamed from: r0, reason: collision with root package name */
    public i f9163r0;

    /* renamed from: s, reason: collision with root package name */
    public int f9164s;

    /* renamed from: s0, reason: collision with root package name */
    public i f9165s0;

    /* renamed from: t, reason: collision with root package name */
    public final int[] f9166t;

    /* renamed from: t0, reason: collision with root package name */
    public int f9167t0;

    /* renamed from: u, reason: collision with root package name */
    public int f9168u;

    /* renamed from: u0, reason: collision with root package name */
    public int f9169u0;

    /* renamed from: v, reason: collision with root package name */
    public int f9170v;

    /* renamed from: w, reason: collision with root package name */
    public float f9171w;

    /* renamed from: x, reason: collision with root package name */
    public int f9172x;

    /* renamed from: y, reason: collision with root package name */
    public int f9173y;

    /* renamed from: z, reason: collision with root package name */
    public float f9174z;

    /* renamed from: a, reason: collision with root package name */
    public boolean f9128a = false;

    /* renamed from: d, reason: collision with root package name */
    public v.n f9134d = null;

    /* renamed from: e, reason: collision with root package name */
    public v.q f9136e = null;

    /* renamed from: f, reason: collision with root package name */
    public final boolean[] f9138f = {true, true};

    /* renamed from: g, reason: collision with root package name */
    public boolean f9140g = true;

    /* renamed from: h, reason: collision with root package name */
    public int f9142h = -1;

    /* renamed from: i, reason: collision with root package name */
    public int f9144i = -1;

    public i() {
        new HashMap();
        this.f9148k = false;
        this.f9150l = false;
        this.f9152m = false;
        this.f9154n = false;
        this.f9156o = -1;
        this.f9158p = -1;
        this.f9160q = 0;
        this.f9162r = 0;
        this.f9164s = 0;
        this.f9166t = new int[2];
        this.f9168u = 0;
        this.f9170v = 0;
        this.f9171w = 1.0f;
        this.f9172x = 0;
        this.f9173y = 0;
        this.f9174z = 1.0f;
        this.A = -1;
        this.B = 1.0f;
        this.C = new int[]{u.MAX_ACTIVITY_COUNT_UNLIMITED, u.MAX_ACTIVITY_COUNT_UNLIMITED};
        this.D = CropImageView.DEFAULT_ASPECT_RATIO;
        this.E = false;
        this.G = false;
        this.H = 0;
        this.I = 0;
        f fVar = new f(this, e.LEFT);
        this.J = fVar;
        f fVar2 = new f(this, e.TOP);
        this.K = fVar2;
        f fVar3 = new f(this, e.RIGHT);
        this.L = fVar3;
        f fVar4 = new f(this, e.BOTTOM);
        this.M = fVar4;
        f fVar5 = new f(this, e.BASELINE);
        this.N = fVar5;
        f fVar6 = new f(this, e.CENTER_X);
        this.O = fVar6;
        f fVar7 = new f(this, e.CENTER_Y);
        this.P = fVar7;
        f fVar8 = new f(this, e.CENTER);
        this.Q = fVar8;
        this.R = new f[]{fVar, fVar3, fVar2, fVar4, fVar5, fVar8};
        ArrayList arrayList = new ArrayList();
        this.S = arrayList;
        this.T = new boolean[2];
        h hVar = h.FIXED;
        this.U = new h[]{hVar, hVar};
        this.V = null;
        this.W = 0;
        this.X = 0;
        this.Y = CropImageView.DEFAULT_ASPECT_RATIO;
        this.Z = -1;
        this.f9129a0 = 0;
        this.f9131b0 = 0;
        this.f9133c0 = 0;
        this.f9139f0 = 0.5f;
        this.f9141g0 = 0.5f;
        this.f9145i0 = 0;
        this.f9147j0 = false;
        this.f9149k0 = null;
        this.f9151l0 = null;
        this.f9153m0 = 0;
        this.f9155n0 = 0;
        this.f9157o0 = new float[]{-1.0f, -1.0f};
        this.f9159p0 = new i[]{null, null};
        this.f9161q0 = new i[]{null, null};
        this.f9163r0 = null;
        this.f9165s0 = null;
        this.f9167t0 = -1;
        this.f9169u0 = -1;
        arrayList.add(fVar);
        arrayList.add(fVar2);
        arrayList.add(fVar3);
        arrayList.add(fVar4);
        arrayList.add(fVar6);
        arrayList.add(fVar7);
        arrayList.add(fVar8);
        arrayList.add(fVar5);
    }

    public static void I(int i7, int i8, String str, StringBuilder sb) {
        if (i7 == i8) {
            return;
        }
        sb.append(str);
        sb.append(" :   ");
        sb.append(i7);
        sb.append(",\n");
    }

    public static void J(StringBuilder sb, String str, float f7, float f8) {
        if (f7 == f8) {
            return;
        }
        sb.append(str);
        sb.append(" :   ");
        sb.append(f7);
        sb.append(",\n");
    }

    public static void q(StringBuilder sb, String str, int i7, int i8, int i9, int i10, int i11, float f7) {
        sb.append(str);
        sb.append(" :  {\n");
        I(i7, 0, "      size", sb);
        I(i8, 0, "      min", sb);
        I(i9, u.MAX_ACTIVITY_COUNT_UNLIMITED, "      max", sb);
        I(i10, 0, "      matchMin", sb);
        I(i11, 0, "      matchDef", sb);
        J(sb, "      matchPercent", f7, 1.0f);
        sb.append("    },\n");
    }

    public static void r(StringBuilder sb, String str, f fVar) {
        if (fVar.f9121f == null) {
            return;
        }
        sb.append("    ");
        sb.append(str);
        sb.append(" : [ '");
        sb.append(fVar.f9121f);
        sb.append("'");
        if (fVar.f9123h != Integer.MIN_VALUE || fVar.f9122g != 0) {
            sb.append(",");
            sb.append(fVar.f9122g);
            if (fVar.f9123h != Integer.MIN_VALUE) {
                sb.append(",");
                sb.append(fVar.f9123h);
                sb.append(",");
            }
        }
        sb.append(" ] ,\n");
    }

    public final boolean A() {
        f fVar = this.K;
        f fVar2 = fVar.f9121f;
        if (fVar2 != null && fVar2.f9121f == fVar) {
            return true;
        }
        f fVar3 = this.M;
        f fVar4 = fVar3.f9121f;
        return fVar4 != null && fVar4.f9121f == fVar3;
    }

    public final boolean B() {
        return this.f9140g && this.f9145i0 != 8;
    }

    public boolean C() {
        return this.f9148k || (this.J.f9118c && this.L.f9118c);
    }

    public boolean D() {
        return this.f9150l || (this.K.f9118c && this.M.f9118c);
    }

    public void E() {
        this.J.j();
        this.K.j();
        this.L.j();
        this.M.j();
        this.N.j();
        this.O.j();
        this.P.j();
        this.Q.j();
        this.V = null;
        this.D = CropImageView.DEFAULT_ASPECT_RATIO;
        this.W = 0;
        this.X = 0;
        this.Y = CropImageView.DEFAULT_ASPECT_RATIO;
        this.Z = -1;
        this.f9129a0 = 0;
        this.f9131b0 = 0;
        this.f9133c0 = 0;
        this.f9135d0 = 0;
        this.f9137e0 = 0;
        this.f9139f0 = 0.5f;
        this.f9141g0 = 0.5f;
        h[] hVarArr = this.U;
        h hVar = h.FIXED;
        hVarArr[0] = hVar;
        hVarArr[1] = hVar;
        this.f9143h0 = null;
        this.f9145i0 = 0;
        this.f9151l0 = null;
        this.f9153m0 = 0;
        this.f9155n0 = 0;
        float[] fArr = this.f9157o0;
        fArr[0] = -1.0f;
        fArr[1] = -1.0f;
        this.f9156o = -1;
        this.f9158p = -1;
        int[] iArr = this.C;
        iArr[0] = Integer.MAX_VALUE;
        iArr[1] = Integer.MAX_VALUE;
        this.f9162r = 0;
        this.f9164s = 0;
        this.f9171w = 1.0f;
        this.f9174z = 1.0f;
        this.f9170v = u.MAX_ACTIVITY_COUNT_UNLIMITED;
        this.f9173y = u.MAX_ACTIVITY_COUNT_UNLIMITED;
        this.f9168u = 0;
        this.f9172x = 0;
        this.A = -1;
        this.B = 1.0f;
        boolean[] zArr = this.f9138f;
        zArr[0] = true;
        zArr[1] = true;
        this.G = false;
        boolean[] zArr2 = this.T;
        zArr2[0] = false;
        zArr2[1] = false;
        this.f9140g = true;
        int[] iArr2 = this.f9166t;
        iArr2[0] = 0;
        iArr2[1] = 0;
        this.f9142h = -1;
        this.f9144i = -1;
    }

    public final void F() {
        i iVar = this.V;
        if (iVar != null && (iVar instanceof j)) {
            ((j) iVar).getClass();
        }
        ArrayList arrayList = this.S;
        int size = arrayList.size();
        for (int i7 = 0; i7 < size; i7++) {
            ((f) arrayList.get(i7)).j();
        }
    }

    public final void G() {
        this.f9148k = false;
        this.f9150l = false;
        this.f9152m = false;
        this.f9154n = false;
        ArrayList arrayList = this.S;
        int size = arrayList.size();
        for (int i7 = 0; i7 < size; i7++) {
            f fVar = (f) arrayList.get(i7);
            fVar.f9118c = false;
            fVar.f9117b = 0;
        }
    }

    public void H(j.h hVar) {
        this.J.k();
        this.K.k();
        this.L.k();
        this.M.k();
        this.N.k();
        this.Q.k();
        this.O.k();
        this.P.k();
    }

    public final void K(int i7) {
        this.f9133c0 = i7;
        this.E = i7 > 0;
    }

    public final void L(int i7, int i8) {
        if (this.f9148k) {
            return;
        }
        this.J.l(i7);
        this.L.l(i8);
        this.f9129a0 = i7;
        this.W = i8 - i7;
        this.f9148k = true;
    }

    public final void M(int i7, int i8) {
        if (this.f9150l) {
            return;
        }
        this.K.l(i7);
        this.M.l(i8);
        this.f9131b0 = i7;
        this.X = i8 - i7;
        if (this.E) {
            this.N.l(i7 + this.f9133c0);
        }
        this.f9150l = true;
    }

    public final void N(int i7) {
        this.X = i7;
        int i8 = this.f9137e0;
        if (i7 < i8) {
            this.X = i8;
        }
    }

    public final void O(h hVar) {
        this.U[0] = hVar;
    }

    public final void P(h hVar) {
        this.U[1] = hVar;
    }

    public final void Q(int i7) {
        this.W = i7;
        int i8 = this.f9135d0;
        if (i7 < i8) {
            this.W = i8;
        }
    }

    public void R(boolean z6, boolean z7) {
        int i7;
        int i8;
        v.n nVar = this.f9134d;
        boolean z8 = z6 & nVar.f9382g;
        v.q qVar = this.f9136e;
        boolean z9 = z7 & qVar.f9382g;
        int i9 = nVar.f9383h.f9345g;
        int i10 = qVar.f9383h.f9345g;
        int i11 = nVar.f9384i.f9345g;
        int i12 = qVar.f9384i.f9345g;
        int i13 = i12 - i10;
        if (i11 - i9 < 0 || i13 < 0 || i9 == Integer.MIN_VALUE || i9 == Integer.MAX_VALUE || i10 == Integer.MIN_VALUE || i10 == Integer.MAX_VALUE || i11 == Integer.MIN_VALUE || i11 == Integer.MAX_VALUE || i12 == Integer.MIN_VALUE || i12 == Integer.MAX_VALUE) {
            i11 = 0;
            i12 = 0;
            i9 = 0;
            i10 = 0;
        }
        int i14 = i11 - i9;
        int i15 = i12 - i10;
        if (z8) {
            this.f9129a0 = i9;
        }
        if (z9) {
            this.f9131b0 = i10;
        }
        if (this.f9145i0 == 8) {
            this.W = 0;
            this.X = 0;
            return;
        }
        if (z8) {
            if (this.U[0] == h.FIXED && i14 < (i8 = this.W)) {
                i14 = i8;
            }
            this.W = i14;
            int i16 = this.f9135d0;
            if (i14 < i16) {
                this.W = i16;
            }
        }
        if (z9) {
            if (this.U[1] == h.FIXED && i15 < (i7 = this.X)) {
                i15 = i7;
            }
            this.X = i15;
            int i17 = this.f9137e0;
            if (i15 < i17) {
                this.X = i17;
            }
        }
    }

    public void S(s.d dVar, boolean z6) {
        int i7;
        int i8;
        v.q qVar;
        v.n nVar;
        f fVar = this.J;
        dVar.getClass();
        int n6 = s.d.n(fVar);
        int n7 = s.d.n(this.K);
        int n8 = s.d.n(this.L);
        int n9 = s.d.n(this.M);
        if (z6 && (nVar = this.f9134d) != null) {
            v.h hVar = nVar.f9383h;
            if (hVar.f9348j) {
                v.h hVar2 = nVar.f9384i;
                if (hVar2.f9348j) {
                    n6 = hVar.f9345g;
                    n8 = hVar2.f9345g;
                }
            }
        }
        if (z6 && (qVar = this.f9136e) != null) {
            v.h hVar3 = qVar.f9383h;
            if (hVar3.f9348j) {
                v.h hVar4 = qVar.f9384i;
                if (hVar4.f9348j) {
                    n7 = hVar3.f9345g;
                    n9 = hVar4.f9345g;
                }
            }
        }
        int i9 = n9 - n7;
        if (n8 - n6 < 0 || i9 < 0 || n6 == Integer.MIN_VALUE || n6 == Integer.MAX_VALUE || n7 == Integer.MIN_VALUE || n7 == Integer.MAX_VALUE || n8 == Integer.MIN_VALUE || n8 == Integer.MAX_VALUE || n9 == Integer.MIN_VALUE || n9 == Integer.MAX_VALUE) {
            n6 = 0;
            n7 = 0;
            n8 = 0;
            n9 = 0;
        }
        int i10 = n8 - n6;
        int i11 = n9 - n7;
        this.f9129a0 = n6;
        this.f9131b0 = n7;
        if (this.f9145i0 == 8) {
            this.W = 0;
            this.X = 0;
            return;
        }
        h[] hVarArr = this.U;
        h hVar5 = hVarArr[0];
        h hVar6 = h.FIXED;
        if (hVar5 == hVar6 && i10 < (i8 = this.W)) {
            i10 = i8;
        }
        if (hVarArr[1] == hVar6 && i11 < (i7 = this.X)) {
            i11 = i7;
        }
        this.W = i10;
        this.X = i11;
        int i12 = this.f9137e0;
        if (i11 < i12) {
            this.X = i12;
        }
        int i13 = this.f9135d0;
        if (i10 < i13) {
            this.W = i13;
        }
        int i14 = this.f9170v;
        if (i14 > 0 && hVar5 == h.MATCH_CONSTRAINT) {
            this.W = Math.min(this.W, i14);
        }
        int i15 = this.f9173y;
        if (i15 > 0 && this.U[1] == h.MATCH_CONSTRAINT) {
            this.X = Math.min(this.X, i15);
        }
        int i16 = this.W;
        if (i10 != i16) {
            this.f9142h = i16;
        }
        int i17 = this.X;
        if (i11 != i17) {
            this.f9144i = i17;
        }
    }

    public final void b(j jVar, s.d dVar, HashSet hashSet, int i7, boolean z6) {
        if (z6) {
            if (!hashSet.contains(this)) {
                return;
            }
            q.a(jVar, dVar, this);
            hashSet.remove(this);
            c(dVar, jVar.Y(64));
        }
        if (i7 == 0) {
            HashSet hashSet2 = this.J.f9116a;
            if (hashSet2 != null) {
                Iterator it = hashSet2.iterator();
                while (it.hasNext()) {
                    ((f) it.next()).f9119d.b(jVar, dVar, hashSet, i7, true);
                }
            }
            HashSet hashSet3 = this.L.f9116a;
            if (hashSet3 != null) {
                Iterator it2 = hashSet3.iterator();
                while (it2.hasNext()) {
                    ((f) it2.next()).f9119d.b(jVar, dVar, hashSet, i7, true);
                }
                return;
            }
            return;
        }
        HashSet hashSet4 = this.K.f9116a;
        if (hashSet4 != null) {
            Iterator it3 = hashSet4.iterator();
            while (it3.hasNext()) {
                ((f) it3.next()).f9119d.b(jVar, dVar, hashSet, i7, true);
            }
        }
        HashSet hashSet5 = this.M.f9116a;
        if (hashSet5 != null) {
            Iterator it4 = hashSet5.iterator();
            while (it4.hasNext()) {
                ((f) it4.next()).f9119d.b(jVar, dVar, hashSet, i7, true);
            }
        }
        HashSet hashSet6 = this.N.f9116a;
        if (hashSet6 != null) {
            Iterator it5 = hashSet6.iterator();
            while (it5.hasNext()) {
                ((f) it5.next()).f9119d.b(jVar, dVar, hashSet, i7, true);
            }
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:104:0x028e  */
    /* JADX WARN: Removed duplicated region for block: B:107:0x0298  */
    /* JADX WARN: Removed duplicated region for block: B:110:0x02a9  */
    /* JADX WARN: Removed duplicated region for block: B:113:0x02b4  */
    /* JADX WARN: Removed duplicated region for block: B:183:0x0406  */
    /* JADX WARN: Removed duplicated region for block: B:188:0x0415  */
    /* JADX WARN: Removed duplicated region for block: B:194:0x042a  */
    /* JADX WARN: Removed duplicated region for block: B:198:0x0433  */
    /* JADX WARN: Removed duplicated region for block: B:201:0x044d  */
    /* JADX WARN: Removed duplicated region for block: B:211:0x046f  */
    /* JADX WARN: Removed duplicated region for block: B:222:0x056c  */
    /* JADX WARN: Removed duplicated region for block: B:22:0x006a  */
    /* JADX WARN: Removed duplicated region for block: B:238:0x05e4 A[ADDED_TO_REGION] */
    /* JADX WARN: Removed duplicated region for block: B:247:0x05fb  */
    /* JADX WARN: Removed duplicated region for block: B:250:0x0601  */
    /* JADX WARN: Removed duplicated region for block: B:253:0x060f  */
    /* JADX WARN: Removed duplicated region for block: B:261:0x0673  */
    /* JADX WARN: Removed duplicated region for block: B:265:0x06be  */
    /* JADX WARN: Removed duplicated region for block: B:270:0x0723  */
    /* JADX WARN: Removed duplicated region for block: B:274:0x071b  */
    /* JADX WARN: Removed duplicated region for block: B:275:0x0676  */
    /* JADX WARN: Removed duplicated region for block: B:278:0x0627  */
    /* JADX WARN: Removed duplicated region for block: B:282:0x0646  */
    /* JADX WARN: Removed duplicated region for block: B:286:0x0617  */
    /* JADX WARN: Removed duplicated region for block: B:287:0x0609  */
    /* JADX WARN: Removed duplicated region for block: B:294:0x05ce  */
    /* JADX WARN: Removed duplicated region for block: B:295:0x04b7  */
    /* JADX WARN: Removed duplicated region for block: B:299:0x04d0  */
    /* JADX WARN: Removed duplicated region for block: B:302:0x04df  */
    /* JADX WARN: Removed duplicated region for block: B:305:0x0506  */
    /* JADX WARN: Removed duplicated region for block: B:307:0x0509  */
    /* JADX WARN: Removed duplicated region for block: B:308:0x04e8  */
    /* JADX WARN: Removed duplicated region for block: B:309:0x04d9  */
    /* JADX WARN: Removed duplicated region for block: B:30:0x0085 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:311:0x0436  */
    /* JADX WARN: Removed duplicated region for block: B:315:0x0410  */
    /* JADX WARN: Removed duplicated region for block: B:342:0x02b7  */
    /* JADX WARN: Removed duplicated region for block: B:343:0x02ac  */
    /* JADX WARN: Removed duplicated region for block: B:347:0x0283  */
    /* JADX WARN: Removed duplicated region for block: B:350:0x009a  */
    /* JADX WARN: Removed duplicated region for block: B:370:0x00f9  */
    /* JADX WARN: Removed duplicated region for block: B:75:0x0211  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void c(s.d r64, boolean r65) {
        /*
            Method dump skipped, instructions count: 2015
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: u.i.c(s.d, boolean):void");
    }

    public boolean d() {
        return this.f9145i0 != 8;
    }

    /* JADX WARN: Removed duplicated region for block: B:136:0x03b1 A[ADDED_TO_REGION] */
    /* JADX WARN: Removed duplicated region for block: B:140:0x03c0  */
    /* JADX WARN: Removed duplicated region for block: B:149:0x0424  */
    /* JADX WARN: Removed duplicated region for block: B:157:0x0435  */
    /* JADX WARN: Removed duplicated region for block: B:167:0x045b A[ADDED_TO_REGION] */
    /* JADX WARN: Removed duplicated region for block: B:176:0x0475  */
    /* JADX WARN: Removed duplicated region for block: B:207:0x04c4  */
    /* JADX WARN: Removed duplicated region for block: B:214:0x04d7 A[ADDED_TO_REGION] */
    /* JADX WARN: Removed duplicated region for block: B:225:0x0457  */
    /* JADX WARN: Removed duplicated region for block: B:227:0x0409  */
    /* JADX WARN: Removed duplicated region for block: B:298:0x050e  */
    /* JADX WARN: Removed duplicated region for block: B:300:0x00de  */
    /* JADX WARN: Removed duplicated region for block: B:30:0x0091  */
    /* JADX WARN: Removed duplicated region for block: B:32:0x0099  */
    /* JADX WARN: Removed duplicated region for block: B:350:0x00b9  */
    /* JADX WARN: Removed duplicated region for block: B:351:0x0095  */
    /* JADX WARN: Removed duplicated region for block: B:38:0x00be  */
    /* JADX WARN: Removed duplicated region for block: B:48:0x01af  */
    /* JADX WARN: Removed duplicated region for block: B:51:0x051d A[ADDED_TO_REGION] */
    /* JADX WARN: Removed duplicated region for block: B:79:? A[ADDED_TO_REGION, RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:88:0x04f8 A[ADDED_TO_REGION] */
    /* JADX WARN: Removed duplicated region for block: B:98:? A[ADDED_TO_REGION, RETURN, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void e(s.d r31, boolean r32, boolean r33, boolean r34, boolean r35, s.i r36, s.i r37, u.h r38, boolean r39, u.f r40, u.f r41, int r42, int r43, int r44, int r45, float r46, boolean r47, boolean r48, boolean r49, boolean r50, boolean r51, int r52, int r53, int r54, int r55, float r56, boolean r57) {
        /*
            Method dump skipped, instructions count: 1371
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: u.i.e(s.d, boolean, boolean, boolean, boolean, s.i, s.i, u.h, boolean, u.f, u.f, int, int, int, int, float, boolean, boolean, boolean, boolean, boolean, int, int, int, int, float, boolean):void");
    }

    public final void f(e eVar, i iVar, e eVar2, int i7) {
        e eVar3;
        e eVar4;
        boolean z6;
        e eVar5 = e.CENTER;
        if (eVar == eVar5) {
            if (eVar2 != eVar5) {
                e eVar6 = e.LEFT;
                if (eVar2 == eVar6 || eVar2 == e.RIGHT) {
                    f(eVar6, iVar, eVar2, 0);
                    f(e.RIGHT, iVar, eVar2, 0);
                    k(eVar5).a(iVar.k(eVar2), 0);
                    return;
                }
                e eVar7 = e.TOP;
                if (eVar2 == eVar7 || eVar2 == e.BOTTOM) {
                    f(eVar7, iVar, eVar2, 0);
                    f(e.BOTTOM, iVar, eVar2, 0);
                    k(eVar5).a(iVar.k(eVar2), 0);
                    return;
                }
                return;
            }
            e eVar8 = e.LEFT;
            f k7 = k(eVar8);
            e eVar9 = e.RIGHT;
            f k8 = k(eVar9);
            e eVar10 = e.TOP;
            f k9 = k(eVar10);
            e eVar11 = e.BOTTOM;
            f k10 = k(eVar11);
            boolean z7 = true;
            if ((k7 == null || !k7.h()) && (k8 == null || !k8.h())) {
                f(eVar8, iVar, eVar8, 0);
                f(eVar9, iVar, eVar9, 0);
                z6 = true;
            } else {
                z6 = false;
            }
            if ((k9 == null || !k9.h()) && (k10 == null || !k10.h())) {
                f(eVar10, iVar, eVar10, 0);
                f(eVar11, iVar, eVar11, 0);
            } else {
                z7 = false;
            }
            if (z6 && z7) {
                k(eVar5).a(iVar.k(eVar5), 0);
                return;
            }
            if (z6) {
                e eVar12 = e.CENTER_X;
                k(eVar12).a(iVar.k(eVar12), 0);
                return;
            } else {
                if (z7) {
                    e eVar13 = e.CENTER_Y;
                    k(eVar13).a(iVar.k(eVar13), 0);
                    return;
                }
                return;
            }
        }
        e eVar14 = e.CENTER_X;
        if (eVar == eVar14 && (eVar2 == (eVar4 = e.LEFT) || eVar2 == e.RIGHT)) {
            f k11 = k(eVar4);
            f k12 = iVar.k(eVar2);
            f k13 = k(e.RIGHT);
            k11.a(k12, 0);
            k13.a(k12, 0);
            k(eVar14).a(k12, 0);
            return;
        }
        e eVar15 = e.CENTER_Y;
        if (eVar == eVar15 && (eVar2 == (eVar3 = e.TOP) || eVar2 == e.BOTTOM)) {
            f k14 = iVar.k(eVar2);
            k(eVar3).a(k14, 0);
            k(e.BOTTOM).a(k14, 0);
            k(eVar15).a(k14, 0);
            return;
        }
        if (eVar == eVar14 && eVar2 == eVar14) {
            e eVar16 = e.LEFT;
            k(eVar16).a(iVar.k(eVar16), 0);
            e eVar17 = e.RIGHT;
            k(eVar17).a(iVar.k(eVar17), 0);
            k(eVar14).a(iVar.k(eVar2), 0);
            return;
        }
        if (eVar == eVar15 && eVar2 == eVar15) {
            e eVar18 = e.TOP;
            k(eVar18).a(iVar.k(eVar18), 0);
            e eVar19 = e.BOTTOM;
            k(eVar19).a(iVar.k(eVar19), 0);
            k(eVar15).a(iVar.k(eVar2), 0);
            return;
        }
        f k15 = k(eVar);
        f k16 = iVar.k(eVar2);
        if (k15.i(k16)) {
            e eVar20 = e.BASELINE;
            if (eVar == eVar20) {
                f k17 = k(e.TOP);
                f k18 = k(e.BOTTOM);
                if (k17 != null) {
                    k17.j();
                }
                if (k18 != null) {
                    k18.j();
                }
            } else if (eVar == e.TOP || eVar == e.BOTTOM) {
                f k19 = k(eVar20);
                if (k19 != null) {
                    k19.j();
                }
                f k20 = k(eVar5);
                if (k20.f9121f != k16) {
                    k20.j();
                }
                f f7 = k(eVar).f();
                f k21 = k(eVar15);
                if (k21.h()) {
                    f7.j();
                    k21.j();
                }
            } else if (eVar == e.LEFT || eVar == e.RIGHT) {
                f k22 = k(eVar5);
                if (k22.f9121f != k16) {
                    k22.j();
                }
                f f8 = k(eVar).f();
                f k23 = k(eVar14);
                if (k23.h()) {
                    f8.j();
                    k23.j();
                }
            }
            k15.a(k16, i7);
        }
    }

    public final void g(f fVar, f fVar2, int i7) {
        if (fVar.f9119d == this) {
            f(fVar.f9120e, fVar2.f9119d, fVar2.f9120e, i7);
        }
    }

    public void h(i iVar, HashMap hashMap) {
        this.f9156o = iVar.f9156o;
        this.f9158p = iVar.f9158p;
        this.f9162r = iVar.f9162r;
        this.f9164s = iVar.f9164s;
        int[] iArr = iVar.f9166t;
        int i7 = iArr[0];
        int[] iArr2 = this.f9166t;
        iArr2[0] = i7;
        iArr2[1] = iArr[1];
        this.f9168u = iVar.f9168u;
        this.f9170v = iVar.f9170v;
        this.f9172x = iVar.f9172x;
        this.f9173y = iVar.f9173y;
        this.f9174z = iVar.f9174z;
        this.A = iVar.A;
        this.B = iVar.B;
        int[] iArr3 = iVar.C;
        this.C = Arrays.copyOf(iArr3, iArr3.length);
        this.D = iVar.D;
        this.E = iVar.E;
        this.F = iVar.F;
        this.J.j();
        this.K.j();
        this.L.j();
        this.M.j();
        this.N.j();
        this.O.j();
        this.P.j();
        this.Q.j();
        this.U = (h[]) Arrays.copyOf(this.U, 2);
        this.V = this.V == null ? null : (i) hashMap.get(iVar.V);
        this.W = iVar.W;
        this.X = iVar.X;
        this.Y = iVar.Y;
        this.Z = iVar.Z;
        this.f9129a0 = iVar.f9129a0;
        this.f9131b0 = iVar.f9131b0;
        this.f9133c0 = iVar.f9133c0;
        this.f9135d0 = iVar.f9135d0;
        this.f9137e0 = iVar.f9137e0;
        this.f9139f0 = iVar.f9139f0;
        this.f9141g0 = iVar.f9141g0;
        this.f9143h0 = iVar.f9143h0;
        this.f9145i0 = iVar.f9145i0;
        this.f9147j0 = iVar.f9147j0;
        this.f9149k0 = iVar.f9149k0;
        this.f9151l0 = iVar.f9151l0;
        this.f9153m0 = iVar.f9153m0;
        this.f9155n0 = iVar.f9155n0;
        float[] fArr = iVar.f9157o0;
        float f7 = fArr[0];
        float[] fArr2 = this.f9157o0;
        fArr2[0] = f7;
        fArr2[1] = fArr[1];
        i[] iVarArr = iVar.f9159p0;
        i iVar2 = iVarArr[0];
        i[] iVarArr2 = this.f9159p0;
        iVarArr2[0] = iVar2;
        iVarArr2[1] = iVarArr[1];
        i[] iVarArr3 = iVar.f9161q0;
        i iVar3 = iVarArr3[0];
        i[] iVarArr4 = this.f9161q0;
        iVarArr4[0] = iVar3;
        iVarArr4[1] = iVarArr3[1];
        i iVar4 = iVar.f9163r0;
        this.f9163r0 = iVar4 == null ? null : (i) hashMap.get(iVar4);
        i iVar5 = iVar.f9165s0;
        this.f9165s0 = iVar5 != null ? (i) hashMap.get(iVar5) : null;
    }

    public final void i(s.d dVar) {
        dVar.k(this.J);
        dVar.k(this.K);
        dVar.k(this.L);
        dVar.k(this.M);
        if (this.f9133c0 > 0) {
            dVar.k(this.N);
        }
    }

    public final void j() {
        if (this.f9134d == null) {
            v.n nVar = new v.n(this);
            nVar.f9383h.f9343e = v.g.LEFT;
            nVar.f9384i.f9343e = v.g.RIGHT;
            nVar.f9381f = 0;
            this.f9134d = nVar;
        }
        if (this.f9136e == null) {
            v.q qVar = new v.q(this);
            v.h hVar = new v.h(qVar);
            qVar.f9358k = hVar;
            qVar.f9359l = null;
            qVar.f9383h.f9343e = v.g.TOP;
            qVar.f9384i.f9343e = v.g.BOTTOM;
            hVar.f9343e = v.g.BASELINE;
            qVar.f9381f = 1;
            this.f9136e = qVar;
        }
    }

    public f k(e eVar) {
        switch (g.f9125a[eVar.ordinal()]) {
            case 1:
                return this.J;
            case 2:
                return this.K;
            case 3:
                return this.L;
            case 4:
                return this.M;
            case 5:
                return this.N;
            case 6:
                return this.Q;
            case 7:
                return this.O;
            case 8:
                return this.P;
            case 9:
                return null;
            default:
                throw new AssertionError(eVar.name());
        }
    }

    public final h l(int i7) {
        if (i7 == 0) {
            return this.U[0];
        }
        if (i7 == 1) {
            return this.U[1];
        }
        return null;
    }

    public final int m() {
        if (this.f9145i0 == 8) {
            return 0;
        }
        return this.X;
    }

    public final i n(int i7) {
        f fVar;
        f fVar2;
        if (i7 != 0) {
            if (i7 == 1 && (fVar2 = (fVar = this.M).f9121f) != null && fVar2.f9121f == fVar) {
                return fVar2.f9119d;
            }
            return null;
        }
        f fVar3 = this.L;
        f fVar4 = fVar3.f9121f;
        if (fVar4 == null || fVar4.f9121f != fVar3) {
            return null;
        }
        return fVar4.f9119d;
    }

    public final i o(int i7) {
        f fVar;
        f fVar2;
        if (i7 != 0) {
            if (i7 == 1 && (fVar2 = (fVar = this.K).f9121f) != null && fVar2.f9121f == fVar) {
                return fVar2.f9119d;
            }
            return null;
        }
        f fVar3 = this.J;
        f fVar4 = fVar3.f9121f;
        if (fVar4 == null || fVar4.f9121f != fVar3) {
            return null;
        }
        return fVar4.f9119d;
    }

    public void p(StringBuilder sb) {
        sb.append("  " + this.f9146j + ":{\n");
        StringBuilder sb2 = new StringBuilder("    actualWidth:");
        sb2.append(this.W);
        sb.append(sb2.toString());
        sb.append("\n");
        sb.append("    actualHeight:" + this.X);
        sb.append("\n");
        sb.append("    actualLeft:" + this.f9129a0);
        sb.append("\n");
        sb.append("    actualTop:" + this.f9131b0);
        sb.append("\n");
        r(sb, PhoneNotificationRelayService.OVERLAY_POSITION_LEFT, this.J);
        r(sb, "top", this.K);
        r(sb, PhoneNotificationRelayService.OVERLAY_POSITION_RIGHT, this.L);
        r(sb, "bottom", this.M);
        r(sb, "baseline", this.N);
        r(sb, "centerX", this.O);
        r(sb, "centerY", this.P);
        int i7 = this.W;
        int i8 = this.f9135d0;
        int i9 = this.C[0];
        int i10 = this.f9168u;
        int i11 = this.f9162r;
        float f7 = this.f9171w;
        float[] fArr = this.f9157o0;
        float f8 = fArr[0];
        q(sb, "    width", i7, i8, i9, i10, i11, f7);
        int i12 = this.X;
        int i13 = this.f9137e0;
        int i14 = this.C[1];
        int i15 = this.f9172x;
        int i16 = this.f9164s;
        float f9 = this.f9174z;
        float f10 = fArr[1];
        q(sb, "    height", i12, i13, i14, i15, i16, f9);
        float f11 = this.Y;
        int i17 = this.Z;
        if (f11 != CropImageView.DEFAULT_ASPECT_RATIO) {
            sb.append("    dimensionRatio");
            sb.append(" :  [");
            sb.append(f11);
            sb.append(",");
            sb.append(i17);
            sb.append(h0.FRAGMENT_ENCODE_SET);
            sb.append("],\n");
        }
        J(sb, "    horizontalBias", this.f9139f0, 0.5f);
        J(sb, "    verticalBias", this.f9141g0, 0.5f);
        I(this.f9153m0, 0, "    horizontalChainStyle", sb);
        I(this.f9155n0, 0, "    verticalChainStyle", sb);
        sb.append("  }");
    }

    public final int s() {
        if (this.f9145i0 == 8) {
            return 0;
        }
        return this.W;
    }

    public final int t() {
        i iVar = this.V;
        return (iVar == null || !(iVar instanceof j)) ? this.f9129a0 : ((j) iVar).C0 + this.f9129a0;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        String str = this.f9151l0;
        String str2 = h0.FRAGMENT_ENCODE_SET;
        sb.append(str != null ? a.a.i(new StringBuilder("type: "), this.f9151l0, " ") : h0.FRAGMENT_ENCODE_SET);
        if (this.f9149k0 != null) {
            str2 = a.a.i(new StringBuilder("id: "), this.f9149k0, " ");
        }
        sb.append(str2);
        sb.append("(");
        sb.append(this.f9129a0);
        sb.append(", ");
        sb.append(this.f9131b0);
        sb.append(") - (");
        sb.append(this.W);
        sb.append(" x ");
        sb.append(this.X);
        sb.append(")");
        return sb.toString();
    }

    public final int u() {
        i iVar = this.V;
        return (iVar == null || !(iVar instanceof j)) ? this.f9131b0 : ((j) iVar).D0 + this.f9131b0;
    }

    public final boolean v(int i7) {
        if (i7 == 0) {
            return (this.J.f9121f != null ? 1 : 0) + (this.L.f9121f != null ? 1 : 0) < 2;
        }
        return ((this.K.f9121f != null ? 1 : 0) + (this.M.f9121f != null ? 1 : 0)) + (this.N.f9121f != null ? 1 : 0) < 2;
    }

    public final boolean w(int i7, int i8) {
        f fVar;
        f fVar2;
        f fVar3;
        f fVar4;
        if (i7 == 0) {
            f fVar5 = this.J;
            f fVar6 = fVar5.f9121f;
            if (fVar6 != null && fVar6.f9118c && (fVar4 = (fVar3 = this.L).f9121f) != null && fVar4.f9118c) {
                return (fVar4.d() - fVar3.e()) - (fVar5.e() + fVar5.f9121f.d()) >= i8;
            }
        } else {
            f fVar7 = this.K;
            f fVar8 = fVar7.f9121f;
            if (fVar8 != null && fVar8.f9118c && (fVar2 = (fVar = this.M).f9121f) != null && fVar2.f9118c) {
                return (fVar2.d() - fVar.e()) - (fVar7.e() + fVar7.f9121f.d()) >= i8;
            }
        }
        return false;
    }

    public final void x(e eVar, i iVar, e eVar2, int i7, int i8) {
        k(eVar).b(iVar.k(eVar2), i7, i8, true);
    }

    public final boolean y(int i7) {
        f fVar;
        f fVar2;
        int i8 = i7 * 2;
        f[] fVarArr = this.R;
        f fVar3 = fVarArr[i8];
        f fVar4 = fVar3.f9121f;
        return (fVar4 == null || fVar4.f9121f == fVar3 || (fVar2 = (fVar = fVarArr[i8 + 1]).f9121f) == null || fVar2.f9121f != fVar) ? false : true;
    }

    public final boolean z() {
        f fVar = this.J;
        f fVar2 = fVar.f9121f;
        if (fVar2 != null && fVar2.f9121f == fVar) {
            return true;
        }
        f fVar3 = this.L;
        f fVar4 = fVar3.f9121f;
        return fVar4 != null && fVar4.f9121f == fVar3;
    }
}
