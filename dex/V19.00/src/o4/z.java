package o4;

import android.content.Context;
import android.content.SharedPreferences;
import com.navi.tool.tire.api.TireAccount;
import com.navi.tool.tire.api.TireBrand;
import s5.h0;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public abstract class z {
    public static final int DEFAULT_INTERVAL_MIN = 10;
    public static final int MAX_INTERVAL_MIN = 60;
    public static final int MIN_INTERVAL_MIN = 10;

    public static void a(Context context) {
        context.getApplicationContext().getSharedPreferences("tire_account", 0).edit().remove("tire_access_token").remove("tire_sdk_token").remove("tire_tsp_uid").remove("tire_vin").remove("tire_expire_at").remove("tire_phone").remove("tire_last_snapshot").putBoolean("tire_login_expired", false).putInt("tire_consecutive_fail", 0).apply();
    }

    public static void b(Context context) {
        if (context == null) {
            return;
        }
        context.getApplicationContext().getSharedPreferences("tire", 0).edit().clear().apply();
        c.c().f7948p.i(null);
    }

    public static TireBrand c(Context context) {
        try {
            return TireBrand.valueOf(context.getApplicationContext().getSharedPreferences("tire_account", 0).getString("tire_brand", TireBrand.EXEED.name()));
        } catch (Exception unused) {
            return TireBrand.EXEED;
        }
    }

    public static int d(Context context) {
        int i7 = context.getApplicationContext().getSharedPreferences("tire_account", 0).getInt("tire_interval_min", 10);
        if (i7 < 10) {
            return 10;
        }
        if (i7 > 60) {
            return 60;
        }
        return i7;
    }

    public static boolean e(Context context) {
        TireAccount f7 = f(context);
        return f7 != null && f7.isUsable();
    }

    public static TireAccount f(Context context) {
        SharedPreferences sharedPreferences = context.getApplicationContext().getSharedPreferences("tire_account", 0);
        String string = sharedPreferences.getString("tire_access_token", h0.FRAGMENT_ENCODE_SET);
        if (string == null || string.isEmpty()) {
            return null;
        }
        TireAccount tireAccount = new TireAccount();
        tireAccount.brand = c(context);
        tireAccount.phone = sharedPreferences.getString("tire_phone", h0.FRAGMENT_ENCODE_SET);
        tireAccount.accessToken = string;
        tireAccount.sdkToken = sharedPreferences.getString("tire_sdk_token", h0.FRAGMENT_ENCODE_SET);
        tireAccount.tspUid = sharedPreferences.getString("tire_tsp_uid", h0.FRAGMENT_ENCODE_SET);
        tireAccount.vin = sharedPreferences.getString("tire_vin", h0.FRAGMENT_ENCODE_SET);
        tireAccount.expireAt = sharedPreferences.getLong("tire_expire_at", 0L);
        return tireAccount;
    }

    public static void g(Context context) {
        context.getApplicationContext().getSharedPreferences("tire_account", 0).edit().putBoolean("tire_login_expired", true).remove("tire_access_token").remove("tire_sdk_token").remove("tire_tsp_uid").remove("tire_expire_at").remove("tire_last_snapshot").apply();
    }

    public static void h(Context context) {
        context.getApplicationContext().getSharedPreferences("tire_account", 0).edit().putInt("tire_consecutive_fail", 0).apply();
    }

    public static void i(Context context, TireAccount tireAccount) {
        context.getApplicationContext().getSharedPreferences("tire_account", 0).edit().putString("tire_brand", tireAccount.brand.name()).putString("tire_phone", tireAccount.phone).putString("tire_access_token", tireAccount.accessToken).putString("tire_sdk_token", tireAccount.sdkToken).putString("tire_tsp_uid", tireAccount.tspUid).putString("tire_vin", tireAccount.vin).putLong("tire_expire_at", tireAccount.expireAt).putBoolean("tire_login_expired", false).putInt("tire_consecutive_fail", 0).commit();
    }

    public static void j(Context context, long j7) {
        context.getApplicationContext().getSharedPreferences("tire_account", 0).edit().putLong("tire_last_fetch_at", j7).apply();
    }
}
