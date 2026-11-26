package k4;

import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.res.ColorStateList;
import android.graphics.Shader;
import com.navi.tool.amapflying.receiver.LunaMusicLyricReceiver;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final class a {

    /* renamed from: d, reason: collision with root package name */
    public static volatile a f7257d;

    /* renamed from: a, reason: collision with root package name */
    public int f7258a;

    /* renamed from: b, reason: collision with root package name */
    public final Object f7259b;

    /* renamed from: c, reason: collision with root package name */
    public Object f7260c;

    public a(Context context) {
        this.f7258a = 0;
        this.f7259b = context.getApplicationContext();
    }

    public a(Shader shader, ColorStateList colorStateList, int i7) {
        this.f7259b = shader;
        this.f7260c = colorStateList;
        this.f7258a = i7;
    }

    /* JADX WARN: Code restructure failed: missing block: B:63:0x01dd, code lost:
    
        if (r15.size() <= 0) goto L91;
     */
    /* JADX WARN: Code restructure failed: missing block: B:64:0x01df, code lost:
    
        r0 = new i6.i(r15, r8);
     */
    /* JADX WARN: Code restructure failed: missing block: B:65:0x01e6, code lost:
    
        if (r0 == null) goto L94;
     */
    /* JADX WARN: Code restructure failed: missing block: B:67:0x01f8, code lost:
    
        if (r9 == 1) goto L109;
     */
    /* JADX WARN: Code restructure failed: missing block: B:69:0x01fb, code lost:
    
        if (r9 == 2) goto L108;
     */
    /* JADX WARN: Code restructure failed: missing block: B:70:0x01fd, code lost:
    
        r16 = (int[]) r0.f6848b;
        r17 = (float[]) r0.f6849c;
     */
    /* JADX WARN: Code restructure failed: missing block: B:71:0x020b, code lost:
    
        if (r5 == 1) goto L106;
     */
    /* JADX WARN: Code restructure failed: missing block: B:72:0x020d, code lost:
    
        if (r5 == 2) goto L105;
     */
    /* JADX WARN: Code restructure failed: missing block: B:73:0x020f, code lost:
    
        r0 = android.graphics.Shader.TileMode.CLAMP;
     */
    /* JADX WARN: Code restructure failed: missing block: B:75:0x021a, code lost:
    
        r3 = new android.graphics.LinearGradient(r12, r26, r25, r24, r16, r17, r0);
     */
    /* JADX WARN: Code restructure failed: missing block: B:77:0x0270, code lost:
    
        return new k4.a(r3, null, 0);
     */
    /* JADX WARN: Code restructure failed: missing block: B:78:0x0214, code lost:
    
        r0 = android.graphics.Shader.TileMode.MIRROR;
     */
    /* JADX WARN: Code restructure failed: missing block: B:79:0x0217, code lost:
    
        r0 = android.graphics.Shader.TileMode.REPEAT;
     */
    /* JADX WARN: Code restructure failed: missing block: B:80:0x0225, code lost:
    
        r3 = new android.graphics.SweepGradient(r20, r21, (int[]) r0.f6848b, (float[]) r0.f6849c);
     */
    /* JADX WARN: Code restructure failed: missing block: B:82:0x023e, code lost:
    
        if (r22 <= com.yalantis.ucrop.view.CropImageView.DEFAULT_ASPECT_RATIO) goto L121;
     */
    /* JADX WARN: Code restructure failed: missing block: B:83:0x0240, code lost:
    
        r20 = (int[]) r0.f6848b;
        r21 = (float[]) r0.f6849c;
     */
    /* JADX WARN: Code restructure failed: missing block: B:84:0x024f, code lost:
    
        if (r5 == 1) goto L117;
     */
    /* JADX WARN: Code restructure failed: missing block: B:86:0x0252, code lost:
    
        if (r5 == 2) goto L116;
     */
    /* JADX WARN: Code restructure failed: missing block: B:87:0x0254, code lost:
    
        r0 = android.graphics.Shader.TileMode.CLAMP;
     */
    /* JADX WARN: Code restructure failed: missing block: B:88:0x025c, code lost:
    
        r3 = new android.graphics.RadialGradient(r20, r21, r22, r20, r21, r0);
     */
    /* JADX WARN: Code restructure failed: missing block: B:89:0x0257, code lost:
    
        r0 = android.graphics.Shader.TileMode.MIRROR;
     */
    /* JADX WARN: Code restructure failed: missing block: B:90:0x025a, code lost:
    
        r0 = android.graphics.Shader.TileMode.REPEAT;
     */
    /* JADX WARN: Code restructure failed: missing block: B:92:0x0278, code lost:
    
        throw new org.xmlpull.v1.XmlPullParserException("<gradient> tag requires 'gradientRadius' attribute with radial type");
     */
    /* JADX WARN: Code restructure failed: missing block: B:93:0x01ea, code lost:
    
        if (r18 == false) goto L96;
     */
    /* JADX WARN: Code restructure failed: missing block: B:94:0x01ec, code lost:
    
        r0 = new i6.i(r6, r10, r11);
     */
    /* JADX WARN: Code restructure failed: missing block: B:95:0x01f2, code lost:
    
        r0 = new i6.i(r6, r11);
     */
    /* JADX WARN: Code restructure failed: missing block: B:96:0x01e5, code lost:
    
        r0 = null;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static k4.a a(android.content.res.Resources r27, int r28, android.content.res.Resources.Theme r29) {
        /*
            Method dump skipped, instructions count: 671
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: k4.a.a(android.content.res.Resources, int, android.content.res.Resources$Theme):k4.a");
    }

    public static a c(Context context) {
        if (f7257d == null) {
            synchronized (a.class) {
                try {
                    if (f7257d == null) {
                        f7257d = new a(context);
                    }
                } finally {
                }
            }
        }
        return f7257d;
    }

    public final void b(int i7) {
        Intent intent = new Intent("com.luna.music.car.PLAYER_CONTROL");
        intent.putExtra("controlType", i7);
        try {
            ((Context) this.f7259b).sendBroadcast(intent);
        } catch (Exception unused) {
        }
    }

    public final boolean d() {
        if (((Shader) this.f7259b) == null) {
            Object obj = this.f7260c;
            if (((ColorStateList) obj) != null && ((ColorStateList) obj).isStateful()) {
                return true;
            }
        }
        return false;
    }

    public final boolean e(int[] iArr) {
        if (d()) {
            ColorStateList colorStateList = (ColorStateList) this.f7260c;
            int colorForState = colorStateList.getColorForState(iArr, colorStateList.getDefaultColor());
            if (colorForState != this.f7258a) {
                this.f7258a = colorForState;
                return true;
            }
        }
        return false;
    }

    public final void f() {
        try {
            ((Context) this.f7259b).sendBroadcast(new Intent("com.luna.music.car.PLAYER_SYNC"));
        } catch (Exception unused) {
        }
    }

    public final synchronized void g() {
        this.f7258a++;
        if (((LunaMusicLyricReceiver) this.f7260c) != null) {
            f();
            return;
        }
        this.f7260c = new LunaMusicLyricReceiver();
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("com.luna.music.car.LYRIC_FULL");
        intentFilter.addAction("com.luna.music.car.LYRIC_LINE");
        intentFilter.addAction("com.luna.music.car.PLAYER_STATE");
        try {
            ((Context) this.f7259b).registerReceiver((LunaMusicLyricReceiver) this.f7260c, intentFilter, 2);
            f();
        } catch (Exception unused) {
            this.f7260c = null;
            this.f7258a = Math.max(0, this.f7258a - 1);
        }
    }

    public final synchronized void h() {
        int i7 = this.f7258a - 1;
        this.f7258a = i7;
        if (i7 > 0) {
            return;
        }
        this.f7258a = 0;
        Object obj = this.f7260c;
        if (((LunaMusicLyricReceiver) obj) != null) {
            try {
                ((Context) this.f7259b).unregisterReceiver((LunaMusicLyricReceiver) obj);
            } catch (Exception unused) {
            }
            this.f7260c = null;
        }
        LunaMusicLyricReceiver.g();
    }
}
