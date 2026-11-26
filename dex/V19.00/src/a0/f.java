package a0;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.ViewGroup;
import com.yalantis.ucrop.view.CropImageView;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public class f extends ViewGroup.MarginLayoutParams {
    public static final int BASELINE = 5;
    public static final int BOTTOM = 4;
    public static final int CHAIN_PACKED = 2;
    public static final int CHAIN_SPREAD = 0;
    public static final int CHAIN_SPREAD_INSIDE = 1;
    public static final int CIRCLE = 8;
    public static final int END = 7;
    public static final int GONE_UNSET = Integer.MIN_VALUE;
    public static final int HORIZONTAL = 0;
    public static final int LEFT = 1;
    public static final int MATCH_CONSTRAINT = 0;
    public static final int MATCH_CONSTRAINT_PERCENT = 2;
    public static final int MATCH_CONSTRAINT_SPREAD = 0;
    public static final int MATCH_CONSTRAINT_WRAP = 1;
    public static final int PARENT_ID = 0;
    public static final int RIGHT = 2;
    public static final int START = 6;
    public static final int TOP = 3;
    public static final int UNSET = -1;
    public static final int VERTICAL = 1;
    public static final int WRAP_BEHAVIOR_HORIZONTAL_ONLY = 1;
    public static final int WRAP_BEHAVIOR_INCLUDED = 0;
    public static final int WRAP_BEHAVIOR_SKIPPED = 3;
    public static final int WRAP_BEHAVIOR_VERTICAL_ONLY = 2;
    public int A;
    public int B;
    public final int C;
    public final int D;
    public float E;
    public float F;
    public String G;
    public float H;
    public float I;
    public int J;
    public int K;
    public int L;
    public int M;
    public int N;
    public int O;
    public int P;
    public int Q;
    public float R;
    public float S;
    public int T;
    public int U;
    public int V;
    public boolean W;
    public boolean X;
    public String Y;
    public int Z;

    /* renamed from: a, reason: collision with root package name */
    public int f11a;

    /* renamed from: a0, reason: collision with root package name */
    public boolean f12a0;

    /* renamed from: b, reason: collision with root package name */
    public int f13b;

    /* renamed from: b0, reason: collision with root package name */
    public boolean f14b0;

    /* renamed from: c, reason: collision with root package name */
    public float f15c;

    /* renamed from: c0, reason: collision with root package name */
    public boolean f16c0;

    /* renamed from: d, reason: collision with root package name */
    public final boolean f17d;

    /* renamed from: d0, reason: collision with root package name */
    public boolean f18d0;

    /* renamed from: e, reason: collision with root package name */
    public int f19e;

    /* renamed from: e0, reason: collision with root package name */
    public boolean f20e0;

    /* renamed from: f, reason: collision with root package name */
    public int f21f;

    /* renamed from: f0, reason: collision with root package name */
    public boolean f22f0;

    /* renamed from: g, reason: collision with root package name */
    public int f23g;

    /* renamed from: g0, reason: collision with root package name */
    public int f24g0;

    /* renamed from: h, reason: collision with root package name */
    public int f25h;

    /* renamed from: h0, reason: collision with root package name */
    public int f26h0;

    /* renamed from: i, reason: collision with root package name */
    public int f27i;

    /* renamed from: i0, reason: collision with root package name */
    public int f28i0;

    /* renamed from: j, reason: collision with root package name */
    public int f29j;

    /* renamed from: j0, reason: collision with root package name */
    public int f30j0;

    /* renamed from: k, reason: collision with root package name */
    public int f31k;

    /* renamed from: k0, reason: collision with root package name */
    public int f32k0;

    /* renamed from: l, reason: collision with root package name */
    public int f33l;

    /* renamed from: l0, reason: collision with root package name */
    public int f34l0;

    /* renamed from: m, reason: collision with root package name */
    public int f35m;

    /* renamed from: m0, reason: collision with root package name */
    public float f36m0;

    /* renamed from: n, reason: collision with root package name */
    public int f37n;

    /* renamed from: n0, reason: collision with root package name */
    public int f38n0;

    /* renamed from: o, reason: collision with root package name */
    public int f39o;

    /* renamed from: o0, reason: collision with root package name */
    public int f40o0;

    /* renamed from: p, reason: collision with root package name */
    public int f41p;

    /* renamed from: p0, reason: collision with root package name */
    public float f42p0;

    /* renamed from: q, reason: collision with root package name */
    public int f43q;

    /* renamed from: q0, reason: collision with root package name */
    public u.i f44q0;

    /* renamed from: r, reason: collision with root package name */
    public float f45r;

    /* renamed from: s, reason: collision with root package name */
    public int f46s;

    /* renamed from: t, reason: collision with root package name */
    public int f47t;

    /* renamed from: u, reason: collision with root package name */
    public int f48u;

    /* renamed from: v, reason: collision with root package name */
    public int f49v;

    /* renamed from: w, reason: collision with root package name */
    public final int f50w;

    /* renamed from: x, reason: collision with root package name */
    public int f51x;

    /* renamed from: y, reason: collision with root package name */
    public final int f52y;

    /* renamed from: z, reason: collision with root package name */
    public int f53z;

    public f() {
        super(-2, -2);
        this.f11a = -1;
        this.f13b = -1;
        this.f15c = -1.0f;
        this.f17d = true;
        this.f19e = -1;
        this.f21f = -1;
        this.f23g = -1;
        this.f25h = -1;
        this.f27i = -1;
        this.f29j = -1;
        this.f31k = -1;
        this.f33l = -1;
        this.f35m = -1;
        this.f37n = -1;
        this.f39o = -1;
        this.f41p = -1;
        this.f43q = 0;
        this.f45r = CropImageView.DEFAULT_ASPECT_RATIO;
        this.f46s = -1;
        this.f47t = -1;
        this.f48u = -1;
        this.f49v = -1;
        this.f50w = Integer.MIN_VALUE;
        this.f51x = Integer.MIN_VALUE;
        this.f52y = Integer.MIN_VALUE;
        this.f53z = Integer.MIN_VALUE;
        this.A = Integer.MIN_VALUE;
        this.B = Integer.MIN_VALUE;
        this.C = Integer.MIN_VALUE;
        this.D = 0;
        this.E = 0.5f;
        this.F = 0.5f;
        this.G = null;
        this.H = -1.0f;
        this.I = -1.0f;
        this.J = 0;
        this.K = 0;
        this.L = 0;
        this.M = 0;
        this.N = 0;
        this.O = 0;
        this.P = 0;
        this.Q = 0;
        this.R = 1.0f;
        this.S = 1.0f;
        this.T = -1;
        this.U = -1;
        this.V = -1;
        this.W = false;
        this.X = false;
        this.Y = null;
        this.Z = 0;
        this.f12a0 = true;
        this.f14b0 = true;
        this.f16c0 = false;
        this.f18d0 = false;
        this.f20e0 = false;
        this.f22f0 = false;
        this.f24g0 = -1;
        this.f26h0 = -1;
        this.f28i0 = -1;
        this.f30j0 = -1;
        this.f32k0 = Integer.MIN_VALUE;
        this.f34l0 = Integer.MIN_VALUE;
        this.f36m0 = 0.5f;
        this.f44q0 = new u.i();
    }

    public f(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.f11a = -1;
        this.f13b = -1;
        this.f15c = -1.0f;
        this.f17d = true;
        this.f19e = -1;
        this.f21f = -1;
        this.f23g = -1;
        this.f25h = -1;
        this.f27i = -1;
        this.f29j = -1;
        this.f31k = -1;
        this.f33l = -1;
        this.f35m = -1;
        this.f37n = -1;
        this.f39o = -1;
        this.f41p = -1;
        this.f43q = 0;
        this.f45r = CropImageView.DEFAULT_ASPECT_RATIO;
        this.f46s = -1;
        this.f47t = -1;
        this.f48u = -1;
        this.f49v = -1;
        this.f50w = Integer.MIN_VALUE;
        this.f51x = Integer.MIN_VALUE;
        this.f52y = Integer.MIN_VALUE;
        this.f53z = Integer.MIN_VALUE;
        this.A = Integer.MIN_VALUE;
        this.B = Integer.MIN_VALUE;
        this.C = Integer.MIN_VALUE;
        this.D = 0;
        this.E = 0.5f;
        this.F = 0.5f;
        this.G = null;
        this.H = -1.0f;
        this.I = -1.0f;
        this.J = 0;
        this.K = 0;
        this.L = 0;
        this.M = 0;
        this.N = 0;
        this.O = 0;
        this.P = 0;
        this.Q = 0;
        this.R = 1.0f;
        this.S = 1.0f;
        this.T = -1;
        this.U = -1;
        this.V = -1;
        this.W = false;
        this.X = false;
        this.Y = null;
        this.Z = 0;
        this.f12a0 = true;
        this.f14b0 = true;
        this.f16c0 = false;
        this.f18d0 = false;
        this.f20e0 = false;
        this.f22f0 = false;
        this.f24g0 = -1;
        this.f26h0 = -1;
        this.f28i0 = -1;
        this.f30j0 = -1;
        this.f32k0 = Integer.MIN_VALUE;
        this.f34l0 = Integer.MIN_VALUE;
        this.f36m0 = 0.5f;
        this.f44q0 = new u.i();
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, t.ConstraintLayout_Layout);
        int indexCount = obtainStyledAttributes.getIndexCount();
        for (int i7 = 0; i7 < indexCount; i7++) {
            int index = obtainStyledAttributes.getIndex(i7);
            int i8 = e.map.get(index);
            switch (i8) {
                case 1:
                    this.V = obtainStyledAttributes.getInt(index, this.V);
                    break;
                case 2:
                    int resourceId = obtainStyledAttributes.getResourceId(index, this.f41p);
                    this.f41p = resourceId;
                    if (resourceId == -1) {
                        this.f41p = obtainStyledAttributes.getInt(index, -1);
                        break;
                    } else {
                        break;
                    }
                case 3:
                    this.f43q = obtainStyledAttributes.getDimensionPixelSize(index, this.f43q);
                    break;
                case 4:
                    float f7 = obtainStyledAttributes.getFloat(index, this.f45r) % 360.0f;
                    this.f45r = f7;
                    if (f7 < CropImageView.DEFAULT_ASPECT_RATIO) {
                        this.f45r = (360.0f - f7) % 360.0f;
                        break;
                    } else {
                        break;
                    }
                case 5:
                    this.f11a = obtainStyledAttributes.getDimensionPixelOffset(index, this.f11a);
                    break;
                case 6:
                    this.f13b = obtainStyledAttributes.getDimensionPixelOffset(index, this.f13b);
                    break;
                case 7:
                    this.f15c = obtainStyledAttributes.getFloat(index, this.f15c);
                    break;
                case 8:
                    int resourceId2 = obtainStyledAttributes.getResourceId(index, this.f19e);
                    this.f19e = resourceId2;
                    if (resourceId2 == -1) {
                        this.f19e = obtainStyledAttributes.getInt(index, -1);
                        break;
                    } else {
                        break;
                    }
                case 9:
                    int resourceId3 = obtainStyledAttributes.getResourceId(index, this.f21f);
                    this.f21f = resourceId3;
                    if (resourceId3 == -1) {
                        this.f21f = obtainStyledAttributes.getInt(index, -1);
                        break;
                    } else {
                        break;
                    }
                case 10:
                    int resourceId4 = obtainStyledAttributes.getResourceId(index, this.f23g);
                    this.f23g = resourceId4;
                    if (resourceId4 == -1) {
                        this.f23g = obtainStyledAttributes.getInt(index, -1);
                        break;
                    } else {
                        break;
                    }
                case 11:
                    int resourceId5 = obtainStyledAttributes.getResourceId(index, this.f25h);
                    this.f25h = resourceId5;
                    if (resourceId5 == -1) {
                        this.f25h = obtainStyledAttributes.getInt(index, -1);
                        break;
                    } else {
                        break;
                    }
                case 12:
                    int resourceId6 = obtainStyledAttributes.getResourceId(index, this.f27i);
                    this.f27i = resourceId6;
                    if (resourceId6 == -1) {
                        this.f27i = obtainStyledAttributes.getInt(index, -1);
                        break;
                    } else {
                        break;
                    }
                case 13:
                    int resourceId7 = obtainStyledAttributes.getResourceId(index, this.f29j);
                    this.f29j = resourceId7;
                    if (resourceId7 == -1) {
                        this.f29j = obtainStyledAttributes.getInt(index, -1);
                        break;
                    } else {
                        break;
                    }
                case 14:
                    int resourceId8 = obtainStyledAttributes.getResourceId(index, this.f31k);
                    this.f31k = resourceId8;
                    if (resourceId8 == -1) {
                        this.f31k = obtainStyledAttributes.getInt(index, -1);
                        break;
                    } else {
                        break;
                    }
                case 15:
                    int resourceId9 = obtainStyledAttributes.getResourceId(index, this.f33l);
                    this.f33l = resourceId9;
                    if (resourceId9 == -1) {
                        this.f33l = obtainStyledAttributes.getInt(index, -1);
                        break;
                    } else {
                        break;
                    }
                case 16:
                    int resourceId10 = obtainStyledAttributes.getResourceId(index, this.f35m);
                    this.f35m = resourceId10;
                    if (resourceId10 == -1) {
                        this.f35m = obtainStyledAttributes.getInt(index, -1);
                        break;
                    } else {
                        break;
                    }
                case 17:
                    int resourceId11 = obtainStyledAttributes.getResourceId(index, this.f46s);
                    this.f46s = resourceId11;
                    if (resourceId11 == -1) {
                        this.f46s = obtainStyledAttributes.getInt(index, -1);
                        break;
                    } else {
                        break;
                    }
                case 18:
                    int resourceId12 = obtainStyledAttributes.getResourceId(index, this.f47t);
                    this.f47t = resourceId12;
                    if (resourceId12 == -1) {
                        this.f47t = obtainStyledAttributes.getInt(index, -1);
                        break;
                    } else {
                        break;
                    }
                case 19:
                    int resourceId13 = obtainStyledAttributes.getResourceId(index, this.f48u);
                    this.f48u = resourceId13;
                    if (resourceId13 == -1) {
                        this.f48u = obtainStyledAttributes.getInt(index, -1);
                        break;
                    } else {
                        break;
                    }
                case 20:
                    int resourceId14 = obtainStyledAttributes.getResourceId(index, this.f49v);
                    this.f49v = resourceId14;
                    if (resourceId14 == -1) {
                        this.f49v = obtainStyledAttributes.getInt(index, -1);
                        break;
                    } else {
                        break;
                    }
                case 21:
                    this.f50w = obtainStyledAttributes.getDimensionPixelSize(index, this.f50w);
                    break;
                case 22:
                    this.f51x = obtainStyledAttributes.getDimensionPixelSize(index, this.f51x);
                    break;
                case 23:
                    this.f52y = obtainStyledAttributes.getDimensionPixelSize(index, this.f52y);
                    break;
                case 24:
                    this.f53z = obtainStyledAttributes.getDimensionPixelSize(index, this.f53z);
                    break;
                case 25:
                    this.A = obtainStyledAttributes.getDimensionPixelSize(index, this.A);
                    break;
                case 26:
                    this.B = obtainStyledAttributes.getDimensionPixelSize(index, this.B);
                    break;
                case 27:
                    this.W = obtainStyledAttributes.getBoolean(index, this.W);
                    break;
                case 28:
                    this.X = obtainStyledAttributes.getBoolean(index, this.X);
                    break;
                case 29:
                    this.E = obtainStyledAttributes.getFloat(index, this.E);
                    break;
                case 30:
                    this.F = obtainStyledAttributes.getFloat(index, this.F);
                    break;
                case 31:
                    this.L = obtainStyledAttributes.getInt(index, 0);
                    break;
                case 32:
                    this.M = obtainStyledAttributes.getInt(index, 0);
                    break;
                case 33:
                    try {
                        this.N = obtainStyledAttributes.getDimensionPixelSize(index, this.N);
                        break;
                    } catch (Exception unused) {
                        if (obtainStyledAttributes.getInt(index, this.N) == -2) {
                            this.N = -2;
                            break;
                        } else {
                            break;
                        }
                    }
                case 34:
                    try {
                        this.P = obtainStyledAttributes.getDimensionPixelSize(index, this.P);
                        break;
                    } catch (Exception unused2) {
                        if (obtainStyledAttributes.getInt(index, this.P) == -2) {
                            this.P = -2;
                            break;
                        } else {
                            break;
                        }
                    }
                case 35:
                    this.R = Math.max(CropImageView.DEFAULT_ASPECT_RATIO, obtainStyledAttributes.getFloat(index, this.R));
                    this.L = 2;
                    break;
                case 36:
                    try {
                        this.O = obtainStyledAttributes.getDimensionPixelSize(index, this.O);
                        break;
                    } catch (Exception unused3) {
                        if (obtainStyledAttributes.getInt(index, this.O) == -2) {
                            this.O = -2;
                            break;
                        } else {
                            break;
                        }
                    }
                case 37:
                    try {
                        this.Q = obtainStyledAttributes.getDimensionPixelSize(index, this.Q);
                        break;
                    } catch (Exception unused4) {
                        if (obtainStyledAttributes.getInt(index, this.Q) == -2) {
                            this.Q = -2;
                            break;
                        } else {
                            break;
                        }
                    }
                case 38:
                    this.S = Math.max(CropImageView.DEFAULT_ASPECT_RATIO, obtainStyledAttributes.getFloat(index, this.S));
                    this.M = 2;
                    break;
                default:
                    switch (i8) {
                        case 44:
                            p.n(this, obtainStyledAttributes.getString(index));
                            break;
                        case 45:
                            this.H = obtainStyledAttributes.getFloat(index, this.H);
                            break;
                        case 46:
                            this.I = obtainStyledAttributes.getFloat(index, this.I);
                            break;
                        case 47:
                            this.J = obtainStyledAttributes.getInt(index, 0);
                            break;
                        case 48:
                            this.K = obtainStyledAttributes.getInt(index, 0);
                            break;
                        case 49:
                            this.T = obtainStyledAttributes.getDimensionPixelOffset(index, this.T);
                            break;
                        case 50:
                            this.U = obtainStyledAttributes.getDimensionPixelOffset(index, this.U);
                            break;
                        case 51:
                            this.Y = obtainStyledAttributes.getString(index);
                            break;
                        case 52:
                            int resourceId15 = obtainStyledAttributes.getResourceId(index, this.f37n);
                            this.f37n = resourceId15;
                            if (resourceId15 == -1) {
                                this.f37n = obtainStyledAttributes.getInt(index, -1);
                                break;
                            } else {
                                break;
                            }
                        case 53:
                            int resourceId16 = obtainStyledAttributes.getResourceId(index, this.f39o);
                            this.f39o = resourceId16;
                            if (resourceId16 == -1) {
                                this.f39o = obtainStyledAttributes.getInt(index, -1);
                                break;
                            } else {
                                break;
                            }
                        case 54:
                            this.D = obtainStyledAttributes.getDimensionPixelSize(index, this.D);
                            break;
                        case 55:
                            this.C = obtainStyledAttributes.getDimensionPixelSize(index, this.C);
                            break;
                        default:
                            switch (i8) {
                                case 64:
                                    p.m(this, obtainStyledAttributes, index, 0);
                                    break;
                                case e.LAYOUT_CONSTRAINT_HEIGHT /* 65 */:
                                    p.m(this, obtainStyledAttributes, index, 1);
                                    break;
                                case e.LAYOUT_WRAP_BEHAVIOR_IN_PARENT /* 66 */:
                                    this.Z = obtainStyledAttributes.getInt(index, this.Z);
                                    break;
                                case e.GUIDELINE_USE_RTL /* 67 */:
                                    this.f17d = obtainStyledAttributes.getBoolean(index, this.f17d);
                                    break;
                            }
                    }
            }
        }
        obtainStyledAttributes.recycle();
        a();
    }

    public f(ViewGroup.LayoutParams layoutParams) {
        super(layoutParams);
        this.f11a = -1;
        this.f13b = -1;
        this.f15c = -1.0f;
        this.f17d = true;
        this.f19e = -1;
        this.f21f = -1;
        this.f23g = -1;
        this.f25h = -1;
        this.f27i = -1;
        this.f29j = -1;
        this.f31k = -1;
        this.f33l = -1;
        this.f35m = -1;
        this.f37n = -1;
        this.f39o = -1;
        this.f41p = -1;
        this.f43q = 0;
        this.f45r = CropImageView.DEFAULT_ASPECT_RATIO;
        this.f46s = -1;
        this.f47t = -1;
        this.f48u = -1;
        this.f49v = -1;
        this.f50w = Integer.MIN_VALUE;
        this.f51x = Integer.MIN_VALUE;
        this.f52y = Integer.MIN_VALUE;
        this.f53z = Integer.MIN_VALUE;
        this.A = Integer.MIN_VALUE;
        this.B = Integer.MIN_VALUE;
        this.C = Integer.MIN_VALUE;
        this.D = 0;
        this.E = 0.5f;
        this.F = 0.5f;
        this.G = null;
        this.H = -1.0f;
        this.I = -1.0f;
        this.J = 0;
        this.K = 0;
        this.L = 0;
        this.M = 0;
        this.N = 0;
        this.O = 0;
        this.P = 0;
        this.Q = 0;
        this.R = 1.0f;
        this.S = 1.0f;
        this.T = -1;
        this.U = -1;
        this.V = -1;
        this.W = false;
        this.X = false;
        this.Y = null;
        this.Z = 0;
        this.f12a0 = true;
        this.f14b0 = true;
        this.f16c0 = false;
        this.f18d0 = false;
        this.f20e0 = false;
        this.f22f0 = false;
        this.f24g0 = -1;
        this.f26h0 = -1;
        this.f28i0 = -1;
        this.f30j0 = -1;
        this.f32k0 = Integer.MIN_VALUE;
        this.f34l0 = Integer.MIN_VALUE;
        this.f36m0 = 0.5f;
        this.f44q0 = new u.i();
    }

    public final void a() {
        this.f18d0 = false;
        this.f12a0 = true;
        this.f14b0 = true;
        int i7 = ((ViewGroup.MarginLayoutParams) this).width;
        if (i7 == -2 && this.W) {
            this.f12a0 = false;
            if (this.L == 0) {
                this.L = 1;
            }
        }
        int i8 = ((ViewGroup.MarginLayoutParams) this).height;
        if (i8 == -2 && this.X) {
            this.f14b0 = false;
            if (this.M == 0) {
                this.M = 1;
            }
        }
        if (i7 == 0 || i7 == -1) {
            this.f12a0 = false;
            if (i7 == 0 && this.L == 1) {
                ((ViewGroup.MarginLayoutParams) this).width = -2;
                this.W = true;
            }
        }
        if (i8 == 0 || i8 == -1) {
            this.f14b0 = false;
            if (i8 == 0 && this.M == 1) {
                ((ViewGroup.MarginLayoutParams) this).height = -2;
                this.X = true;
            }
        }
        if (this.f15c == -1.0f && this.f11a == -1 && this.f13b == -1) {
            return;
        }
        this.f18d0 = true;
        this.f12a0 = true;
        this.f14b0 = true;
        if (!(this.f44q0 instanceof u.n)) {
            this.f44q0 = new u.n();
        }
        ((u.n) this.f44q0).U(this.V);
    }

    /* JADX WARN: Removed duplicated region for block: B:12:0x004a  */
    /* JADX WARN: Removed duplicated region for block: B:15:0x0051  */
    /* JADX WARN: Removed duplicated region for block: B:18:0x0058  */
    /* JADX WARN: Removed duplicated region for block: B:21:0x005e  */
    /* JADX WARN: Removed duplicated region for block: B:24:0x0064  */
    /* JADX WARN: Removed duplicated region for block: B:33:0x007a  */
    /* JADX WARN: Removed duplicated region for block: B:34:0x0082  */
    @Override // android.view.ViewGroup.MarginLayoutParams, android.view.ViewGroup.LayoutParams
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void resolveLayoutDirection(int r11) {
        /*
            Method dump skipped, instructions count: 259
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: a0.f.resolveLayoutDirection(int):void");
    }
}
