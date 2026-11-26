package a0;

import android.view.ViewGroup;
import com.yalantis.ucrop.view.CropImageView;
import java.util.HashMap;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final class k {

    /* renamed from: a, reason: collision with root package name */
    public int f81a;

    /* renamed from: b, reason: collision with root package name */
    public String f82b;

    /* renamed from: c, reason: collision with root package name */
    public final n f83c;

    /* renamed from: d, reason: collision with root package name */
    public final m f84d;

    /* renamed from: e, reason: collision with root package name */
    public final l f85e;

    /* renamed from: f, reason: collision with root package name */
    public final o f86f;

    /* renamed from: g, reason: collision with root package name */
    public HashMap f87g;

    /* renamed from: h, reason: collision with root package name */
    public j f88h;

    public k() {
        n nVar = new n();
        nVar.f146a = false;
        nVar.f147b = 0;
        nVar.f148c = 0;
        nVar.f149d = 1.0f;
        nVar.f150e = Float.NaN;
        this.f83c = nVar;
        m mVar = new m();
        mVar.f133a = false;
        mVar.f134b = -1;
        mVar.f135c = 0;
        mVar.f136d = null;
        mVar.f137e = -1;
        mVar.f138f = 0;
        mVar.f139g = Float.NaN;
        mVar.f140h = Float.NaN;
        mVar.f141i = Float.NaN;
        mVar.f142j = -1;
        mVar.f143k = null;
        mVar.f144l = -3;
        mVar.f145m = -1;
        this.f84d = mVar;
        l lVar = new l();
        lVar.f90a = false;
        lVar.f92b = false;
        lVar.f98e = -1;
        lVar.f100f = -1;
        lVar.f102g = -1.0f;
        lVar.f104h = true;
        lVar.f106i = -1;
        lVar.f108j = -1;
        lVar.f110k = -1;
        lVar.f112l = -1;
        lVar.f114m = -1;
        lVar.f116n = -1;
        lVar.f118o = -1;
        lVar.f120p = -1;
        lVar.f122q = -1;
        lVar.f123r = -1;
        lVar.f124s = -1;
        lVar.f125t = -1;
        lVar.f126u = -1;
        lVar.f127v = -1;
        lVar.f128w = -1;
        lVar.f129x = 0.5f;
        lVar.f130y = 0.5f;
        lVar.f131z = null;
        lVar.A = -1;
        lVar.B = 0;
        lVar.C = CropImageView.DEFAULT_ASPECT_RATIO;
        lVar.D = -1;
        lVar.E = -1;
        lVar.F = -1;
        lVar.G = 0;
        lVar.H = 0;
        lVar.I = 0;
        lVar.J = 0;
        lVar.K = 0;
        lVar.L = 0;
        lVar.M = 0;
        lVar.N = Integer.MIN_VALUE;
        lVar.O = Integer.MIN_VALUE;
        lVar.P = Integer.MIN_VALUE;
        lVar.Q = Integer.MIN_VALUE;
        lVar.R = Integer.MIN_VALUE;
        lVar.S = Integer.MIN_VALUE;
        lVar.T = Integer.MIN_VALUE;
        lVar.U = -1.0f;
        lVar.V = -1.0f;
        lVar.W = 0;
        lVar.X = 0;
        lVar.Y = 0;
        lVar.Z = 0;
        lVar.f91a0 = 0;
        lVar.f93b0 = 0;
        lVar.f95c0 = 0;
        lVar.f97d0 = 0;
        lVar.f99e0 = 1.0f;
        lVar.f101f0 = 1.0f;
        lVar.f103g0 = -1;
        lVar.f105h0 = 0;
        lVar.f107i0 = -1;
        lVar.f115m0 = false;
        lVar.f117n0 = false;
        lVar.f119o0 = true;
        lVar.f121p0 = 0;
        this.f85e = lVar;
        o oVar = new o();
        oVar.f152a = false;
        oVar.f153b = CropImageView.DEFAULT_ASPECT_RATIO;
        oVar.f154c = CropImageView.DEFAULT_ASPECT_RATIO;
        oVar.f155d = CropImageView.DEFAULT_ASPECT_RATIO;
        oVar.f156e = 1.0f;
        oVar.f157f = 1.0f;
        oVar.f158g = Float.NaN;
        oVar.f159h = Float.NaN;
        oVar.f160i = -1;
        oVar.f161j = CropImageView.DEFAULT_ASPECT_RATIO;
        oVar.f162k = CropImageView.DEFAULT_ASPECT_RATIO;
        oVar.f163l = CropImageView.DEFAULT_ASPECT_RATIO;
        oVar.f164m = false;
        oVar.f165n = CropImageView.DEFAULT_ASPECT_RATIO;
        this.f86f = oVar;
        this.f87g = new HashMap();
    }

    public final void a(f fVar) {
        l lVar = this.f85e;
        fVar.f19e = lVar.f106i;
        fVar.f21f = lVar.f108j;
        fVar.f23g = lVar.f110k;
        fVar.f25h = lVar.f112l;
        fVar.f27i = lVar.f114m;
        fVar.f29j = lVar.f116n;
        fVar.f31k = lVar.f118o;
        fVar.f33l = lVar.f120p;
        fVar.f35m = lVar.f122q;
        fVar.f37n = lVar.f123r;
        fVar.f39o = lVar.f124s;
        fVar.f46s = lVar.f125t;
        fVar.f47t = lVar.f126u;
        fVar.f48u = lVar.f127v;
        fVar.f49v = lVar.f128w;
        ((ViewGroup.MarginLayoutParams) fVar).leftMargin = lVar.G;
        ((ViewGroup.MarginLayoutParams) fVar).rightMargin = lVar.H;
        ((ViewGroup.MarginLayoutParams) fVar).topMargin = lVar.I;
        ((ViewGroup.MarginLayoutParams) fVar).bottomMargin = lVar.J;
        fVar.A = lVar.S;
        fVar.B = lVar.R;
        fVar.f51x = lVar.O;
        fVar.f53z = lVar.Q;
        fVar.E = lVar.f129x;
        fVar.F = lVar.f130y;
        fVar.f41p = lVar.A;
        fVar.f43q = lVar.B;
        fVar.f45r = lVar.C;
        fVar.G = lVar.f131z;
        fVar.T = lVar.D;
        fVar.U = lVar.E;
        fVar.I = lVar.U;
        fVar.H = lVar.V;
        fVar.K = lVar.X;
        fVar.J = lVar.W;
        fVar.W = lVar.f115m0;
        fVar.X = lVar.f117n0;
        fVar.L = lVar.Y;
        fVar.M = lVar.Z;
        fVar.P = lVar.f91a0;
        fVar.Q = lVar.f93b0;
        fVar.N = lVar.f95c0;
        fVar.O = lVar.f97d0;
        fVar.R = lVar.f99e0;
        fVar.S = lVar.f101f0;
        fVar.V = lVar.F;
        fVar.f15c = lVar.f102g;
        fVar.f11a = lVar.f98e;
        fVar.f13b = lVar.f100f;
        ((ViewGroup.MarginLayoutParams) fVar).width = lVar.f94c;
        ((ViewGroup.MarginLayoutParams) fVar).height = lVar.f96d;
        String str = lVar.f113l0;
        if (str != null) {
            fVar.Y = str;
        }
        fVar.Z = lVar.f121p0;
        fVar.setMarginStart(lVar.L);
        fVar.setMarginEnd(lVar.K);
        fVar.a();
    }

    /* renamed from: b, reason: merged with bridge method [inline-methods] */
    public final k clone() {
        k kVar = new k();
        kVar.f85e.a(this.f85e);
        kVar.f84d.a(this.f84d);
        n nVar = kVar.f83c;
        nVar.getClass();
        n nVar2 = this.f83c;
        nVar.f146a = nVar2.f146a;
        nVar.f147b = nVar2.f147b;
        nVar.f149d = nVar2.f149d;
        nVar.f150e = nVar2.f150e;
        nVar.f148c = nVar2.f148c;
        kVar.f86f.a(this.f86f);
        kVar.f81a = this.f81a;
        kVar.f88h = this.f88h;
        return kVar;
    }

    public final void c(int i7, f fVar) {
        this.f81a = i7;
        int i8 = fVar.f19e;
        l lVar = this.f85e;
        lVar.f106i = i8;
        lVar.f108j = fVar.f21f;
        lVar.f110k = fVar.f23g;
        lVar.f112l = fVar.f25h;
        lVar.f114m = fVar.f27i;
        lVar.f116n = fVar.f29j;
        lVar.f118o = fVar.f31k;
        lVar.f120p = fVar.f33l;
        lVar.f122q = fVar.f35m;
        lVar.f123r = fVar.f37n;
        lVar.f124s = fVar.f39o;
        lVar.f125t = fVar.f46s;
        lVar.f126u = fVar.f47t;
        lVar.f127v = fVar.f48u;
        lVar.f128w = fVar.f49v;
        lVar.f129x = fVar.E;
        lVar.f130y = fVar.F;
        lVar.f131z = fVar.G;
        lVar.A = fVar.f41p;
        lVar.B = fVar.f43q;
        lVar.C = fVar.f45r;
        lVar.D = fVar.T;
        lVar.E = fVar.U;
        lVar.F = fVar.V;
        lVar.f102g = fVar.f15c;
        lVar.f98e = fVar.f11a;
        lVar.f100f = fVar.f13b;
        lVar.f94c = ((ViewGroup.MarginLayoutParams) fVar).width;
        lVar.f96d = ((ViewGroup.MarginLayoutParams) fVar).height;
        lVar.G = ((ViewGroup.MarginLayoutParams) fVar).leftMargin;
        lVar.H = ((ViewGroup.MarginLayoutParams) fVar).rightMargin;
        lVar.I = ((ViewGroup.MarginLayoutParams) fVar).topMargin;
        lVar.J = ((ViewGroup.MarginLayoutParams) fVar).bottomMargin;
        lVar.M = fVar.D;
        lVar.U = fVar.I;
        lVar.V = fVar.H;
        lVar.X = fVar.K;
        lVar.W = fVar.J;
        lVar.f115m0 = fVar.W;
        lVar.f117n0 = fVar.X;
        lVar.Y = fVar.L;
        lVar.Z = fVar.M;
        lVar.f91a0 = fVar.P;
        lVar.f93b0 = fVar.Q;
        lVar.f95c0 = fVar.N;
        lVar.f97d0 = fVar.O;
        lVar.f99e0 = fVar.R;
        lVar.f101f0 = fVar.S;
        lVar.f113l0 = fVar.Y;
        lVar.O = fVar.f51x;
        lVar.Q = fVar.f53z;
        lVar.N = fVar.f50w;
        lVar.P = fVar.f52y;
        lVar.S = fVar.A;
        lVar.R = fVar.B;
        lVar.T = fVar.C;
        lVar.f121p0 = fVar.Z;
        lVar.K = fVar.getMarginEnd();
        lVar.L = fVar.getMarginStart();
    }

    public final void d(int i7, q qVar) {
        c(i7, qVar);
        this.f83c.f149d = qVar.f175r0;
        float f7 = qVar.f178u0;
        o oVar = this.f86f;
        oVar.f153b = f7;
        oVar.f154c = qVar.f179v0;
        oVar.f155d = qVar.f180w0;
        oVar.f156e = qVar.f181x0;
        oVar.f157f = qVar.f182y0;
        oVar.f158g = qVar.f183z0;
        oVar.f159h = qVar.A0;
        oVar.f161j = qVar.B0;
        oVar.f162k = qVar.C0;
        oVar.f163l = qVar.D0;
        oVar.f165n = qVar.f177t0;
        oVar.f164m = qVar.f176s0;
    }
}
