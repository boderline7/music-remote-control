package androidx.appcompat.app;

import android.content.Context;
import android.location.Location;
import android.os.PowerManager;
import java.util.Calendar;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_2_1633180.dex */
public final class e0 extends f0 {

    /* renamed from: c, reason: collision with root package name */
    public final /* synthetic */ int f707c = 0;

    /* renamed from: d, reason: collision with root package name */
    public final /* synthetic */ j0 f708d;

    /* renamed from: e, reason: collision with root package name */
    public final Object f709e;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public e0(j0 j0Var, Context context) {
        super(j0Var);
        this.f708d = j0Var;
        this.f709e = (PowerManager) context.getApplicationContext().getSystemService("power");
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public e0(j0 j0Var, g gVar) {
        super(j0Var);
        this.f708d = j0Var;
        this.f709e = gVar;
    }

    @Override // androidx.appcompat.app.f0
    public final int b() {
        long j7;
        boolean z6;
        int i7 = this.f707c;
        Object obj = this.f709e;
        switch (i7) {
            case 0:
                if (!((PowerManager) obj).isPowerSaveMode()) {
                    break;
                }
                break;
            default:
                g gVar = (g) obj;
                x0 x0Var = (x0) gVar.f716d;
                if (x0Var.f882b <= System.currentTimeMillis()) {
                    Location M = g0.j.a((Context) gVar.f714b, "android.permission.ACCESS_COARSE_LOCATION") == 0 ? gVar.M("network") : null;
                    Location M2 = g0.j.a((Context) gVar.f714b, "android.permission.ACCESS_FINE_LOCATION") == 0 ? gVar.M("gps") : null;
                    if (M2 == null || M == null ? M2 != null : M2.getTime() > M.getTime()) {
                        M = M2;
                    }
                    if (M == null) {
                        int i8 = Calendar.getInstance().get(11);
                        if (i8 < 6 || i8 >= 22) {
                        }
                        break;
                    } else {
                        x0 x0Var2 = (x0) gVar.f716d;
                        long currentTimeMillis = System.currentTimeMillis();
                        if (w0.f875d == null) {
                            w0.f875d = new w0();
                        }
                        w0 w0Var = w0.f875d;
                        w0Var.a(M.getLatitude(), M.getLongitude(), currentTimeMillis - t1.f.T_MS_DAY);
                        w0Var.a(M.getLatitude(), M.getLongitude(), currentTimeMillis);
                        boolean z7 = w0Var.f878c == 1;
                        long j8 = w0Var.f877b;
                        long j9 = w0Var.f876a;
                        w0Var.a(M.getLatitude(), M.getLongitude(), currentTimeMillis + t1.f.T_MS_DAY);
                        long j10 = w0Var.f877b;
                        if (j8 == -1 || j9 == -1) {
                            j7 = 43200000 + currentTimeMillis;
                        } else {
                            if (currentTimeMillis > j9) {
                                j8 = j10;
                            } else if (currentTimeMillis > j8) {
                                j8 = j9;
                            }
                            j7 = j8 + t1.f.T_MS_MINUTE;
                        }
                        x0Var2.f881a = z7;
                        x0Var2.f882b = j7;
                        z6 = x0Var.f881a;
                    }
                } else {
                    z6 = x0Var.f881a;
                }
                if (z6) {
                }
                break;
        }
        return 1;
    }
}
