package x0;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final class j {
    public static final float DAMPING_RATIO_HIGH_BOUNCY = 0.2f;
    public static final float DAMPING_RATIO_LOW_BOUNCY = 0.75f;
    public static final float DAMPING_RATIO_MEDIUM_BOUNCY = 0.5f;
    public static final float DAMPING_RATIO_NO_BOUNCY = 1.0f;
    public static final float STIFFNESS_HIGH = 10000.0f;
    public static final float STIFFNESS_LOW = 200.0f;
    public static final float STIFFNESS_MEDIUM = 1500.0f;
    public static final float STIFFNESS_VERY_LOW = 50.0f;

    /* renamed from: a, reason: collision with root package name */
    public double f9573a;

    /* renamed from: b, reason: collision with root package name */
    public double f9574b;

    /* renamed from: c, reason: collision with root package name */
    public boolean f9575c;

    /* renamed from: d, reason: collision with root package name */
    public double f9576d;

    /* renamed from: e, reason: collision with root package name */
    public double f9577e;

    /* renamed from: f, reason: collision with root package name */
    public double f9578f;

    /* renamed from: g, reason: collision with root package name */
    public double f9579g;

    /* renamed from: h, reason: collision with root package name */
    public double f9580h;

    /* renamed from: i, reason: collision with root package name */
    public double f9581i;

    /* renamed from: j, reason: collision with root package name */
    public final f f9582j;

    public j() {
        this.f9573a = Math.sqrt(1500.0d);
        this.f9574b = 0.5d;
        this.f9575c = false;
        this.f9581i = Double.MAX_VALUE;
        this.f9582j = new f();
    }

    public j(float f7) {
        this.f9573a = Math.sqrt(1500.0d);
        this.f9574b = 0.5d;
        this.f9575c = false;
        this.f9582j = new f();
        this.f9581i = f7;
    }

    public final f a(double d7, double d8, long j7) {
        double cos;
        double d9;
        if (!this.f9575c) {
            if (this.f9581i == Double.MAX_VALUE) {
                throw new IllegalStateException("Error: Final position of the spring must be set before the animation starts");
            }
            double d10 = this.f9574b;
            if (d10 > 1.0d) {
                double d11 = this.f9573a;
                this.f9578f = (Math.sqrt((d10 * d10) - 1.0d) * d11) + ((-d10) * d11);
                double d12 = this.f9574b;
                double d13 = this.f9573a;
                this.f9579g = ((-d12) * d13) - (Math.sqrt((d12 * d12) - 1.0d) * d13);
            } else if (d10 >= 0.0d && d10 < 1.0d) {
                this.f9580h = Math.sqrt(1.0d - (d10 * d10)) * this.f9573a;
            }
            this.f9575c = true;
        }
        double d14 = j7 / 1000.0d;
        double d15 = d7 - this.f9581i;
        double d16 = this.f9574b;
        if (d16 > 1.0d) {
            double d17 = this.f9579g;
            double d18 = this.f9578f;
            double d19 = d15 - (((d17 * d15) - d8) / (d17 - d18));
            double d20 = ((d15 * d17) - d8) / (d17 - d18);
            d9 = (Math.pow(2.718281828459045d, this.f9578f * d14) * d20) + (Math.pow(2.718281828459045d, d17 * d14) * d19);
            double d21 = this.f9579g;
            double pow = Math.pow(2.718281828459045d, d21 * d14) * d19 * d21;
            double d22 = this.f9578f;
            cos = (Math.pow(2.718281828459045d, d22 * d14) * d20 * d22) + pow;
        } else if (d16 == 1.0d) {
            double d23 = this.f9573a;
            double d24 = (d23 * d15) + d8;
            double d25 = (d24 * d14) + d15;
            double pow2 = Math.pow(2.718281828459045d, (-d23) * d14) * d25;
            double pow3 = Math.pow(2.718281828459045d, (-this.f9573a) * d14) * d25;
            double d26 = this.f9573a;
            cos = (Math.pow(2.718281828459045d, (-d26) * d14) * d24) + (pow3 * (-d26));
            d9 = pow2;
        } else {
            double d27 = 1.0d / this.f9580h;
            double d28 = this.f9573a;
            double d29 = ((d16 * d28 * d15) + d8) * d27;
            double sin = ((Math.sin(this.f9580h * d14) * d29) + (Math.cos(this.f9580h * d14) * d15)) * Math.pow(2.718281828459045d, (-d16) * d28 * d14);
            double d30 = this.f9573a;
            double d31 = this.f9574b;
            double d32 = (-d30) * sin * d31;
            double pow4 = Math.pow(2.718281828459045d, (-d31) * d30 * d14);
            double d33 = this.f9580h;
            double sin2 = Math.sin(d33 * d14) * (-d33) * d15;
            double d34 = this.f9580h;
            cos = (((Math.cos(d34 * d14) * d29 * d34) + sin2) * pow4) + d32;
            d9 = sin;
        }
        float f7 = (float) (d9 + this.f9581i);
        f fVar = this.f9582j;
        fVar.f9557a = f7;
        fVar.f9558b = (float) cos;
        return fVar;
    }
}
