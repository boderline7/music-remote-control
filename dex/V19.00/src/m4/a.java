package m4;

import android.content.Context;
import android.media.audiofx.Visualizer;
import com.navi.tool.ui.extra.z;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final class a implements Visualizer.OnDataCaptureListener {

    /* renamed from: a, reason: collision with root package name */
    public final Context f7551a;

    /* renamed from: c, reason: collision with root package name */
    public Visualizer f7553c;

    /* renamed from: d, reason: collision with root package name */
    public z f7554d;

    /* renamed from: b, reason: collision with root package name */
    public final Object f7552b = new Object();

    /* renamed from: e, reason: collision with root package name */
    public float[] f7555e = new float[0];

    /* renamed from: f, reason: collision with root package name */
    public float[] f7556f = new float[0];

    /* renamed from: g, reason: collision with root package name */
    public int f7557g = 0;

    /* renamed from: h, reason: collision with root package name */
    public boolean f7558h = false;

    /* renamed from: i, reason: collision with root package name */
    public int f7559i = 0;

    public a(Context context) {
        this.f7551a = context.getApplicationContext();
    }

    public static float a(double d7, double d8, double d9, int i7, int i8) {
        if (i8 - i7 <= 0) {
            return -1.0f;
        }
        double log = Math.log(d8);
        double log2 = Math.log(d9) - log;
        double d10 = 0.0d;
        if (log2 <= 0.0d) {
            return i7;
        }
        double log3 = (Math.log(d7) - log) / log2;
        if (log3 >= 0.0d) {
            d10 = 0.999999d;
            if (log3 <= 0.999999d) {
                d10 = log3;
            }
        }
        return (float) ((d10 * Math.max(1, r10 - 1)) + i7);
    }

    public final void b() {
        int i7 = 0;
        int max = Math.max(0, this.f7557g);
        if (this.f7555e.length != max) {
            this.f7555e = new float[max];
        }
        int max2 = Math.max(0, this.f7557g);
        if (this.f7556f.length != max2) {
            this.f7556f = new float[max2];
        }
        int i8 = 0;
        while (true) {
            float[] fArr = this.f7555e;
            if (i8 >= fArr.length) {
                break;
            }
            fArr[i8] = 0.0f;
            i8++;
        }
        while (true) {
            float[] fArr2 = this.f7556f;
            if (i7 >= fArr2.length) {
                this.f7559i = 20;
                return;
            } else {
                fArr2[i7] = 0.0f;
                i7++;
            }
        }
    }

    public final void c(int i7) {
        if (i7 <= 0) {
            return;
        }
        synchronized (this.f7552b) {
            try {
                if (this.f7557g == i7) {
                    return;
                }
                this.f7557g = i7;
                this.f7555e = new float[i7];
                this.f7556f = new float[i7];
                this.f7559i = 20;
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:30:0x00f0  */
    /* JADX WARN: Removed duplicated region for block: B:45:0x0124 A[SYNTHETIC] */
    @Override // android.media.audiofx.Visualizer.OnDataCaptureListener
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void onFftDataCapture(android.media.audiofx.Visualizer r31, byte[] r32, int r33) {
        /*
            Method dump skipped, instructions count: 549
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: m4.a.onFftDataCapture(android.media.audiofx.Visualizer, byte[], int):void");
    }

    @Override // android.media.audiofx.Visualizer.OnDataCaptureListener
    public final void onWaveFormDataCapture(Visualizer visualizer, byte[] bArr, int i7) {
    }
}
