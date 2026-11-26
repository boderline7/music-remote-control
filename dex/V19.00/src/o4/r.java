package o4;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Environment;
import androidx.lifecycle.h0;
import com.google.android.material.timepicker.TimeModel;
import com.navi.tool.utils.SharedPreferencesUtils;
import com.yalantis.ucrop.view.CropImageView;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final class r {
    public static final String ACTION_OIL_RECORD_UPDATED = "com.navi.tool.OIL_RECORD_UPDATED";
    public static final String PREF_OIL_RECORD_CALENDAR_ENABLED = "oil_record_calendar_enabled";
    public static final String PREF_OIL_RECORD_ENABLED = "oil_record_enabled";
    public static final String PREF_OIL_TANK_CAPACITY = "oil_record_tank_capacity";

    /* renamed from: m, reason: collision with root package name */
    public static final DecimalFormat f8005m;

    /* renamed from: n, reason: collision with root package name */
    public static final DecimalFormat f8006n;

    /* renamed from: a, reason: collision with root package name */
    public final Context f8007a;

    /* renamed from: b, reason: collision with root package name */
    public final SharedPreferences f8008b;

    /* renamed from: c, reason: collision with root package name */
    public final c f8009c;

    /* renamed from: d, reason: collision with root package name */
    public n f8010d;

    /* renamed from: e, reason: collision with root package name */
    public double f8011e = Double.NaN;

    /* renamed from: f, reason: collision with root package name */
    public double f8012f = Double.NaN;

    /* renamed from: g, reason: collision with root package name */
    public String f8013g;

    /* renamed from: h, reason: collision with root package name */
    public boolean f8014h;

    /* renamed from: i, reason: collision with root package name */
    public final m f8015i;

    /* renamed from: j, reason: collision with root package name */
    public final m f8016j;

    /* renamed from: k, reason: collision with root package name */
    public final m f8017k;

    /* renamed from: l, reason: collision with root package name */
    public final g4.f f8018l;

    static {
        Locale locale = Locale.US;
        f8005m = new DecimalFormat("0.###", DecimalFormatSymbols.getInstance(locale));
        f8006n = new DecimalFormat("0.0##", DecimalFormatSymbols.getInstance(locale));
    }

    /* JADX WARN: Type inference failed for: r0v1, types: [o4.m] */
    /* JADX WARN: Type inference failed for: r0v2, types: [o4.m] */
    /* JADX WARN: Type inference failed for: r0v3, types: [o4.m] */
    public r(Context context) {
        final int i7 = 0;
        this.f8015i = new h0(this) { // from class: o4.m

            /* renamed from: b, reason: collision with root package name */
            public final /* synthetic */ r f7977b;

            {
                this.f7977b = this;
            }

            @Override // androidx.lifecycle.h0
            public final void onChanged(Object obj) {
                int i8 = i7;
                r rVar = this.f7977b;
                switch (i8) {
                    case 0:
                        rVar.getClass();
                        rVar.f8011e = r.k((String) obj);
                        rVar.j();
                        break;
                    case 1:
                        rVar.getClass();
                        rVar.f8012f = r.k((String) obj);
                        rVar.j();
                        break;
                    default:
                        String str = (String) obj;
                        String str2 = rVar.f8013g;
                        rVar.f8013g = str == null ? null : str.trim();
                        rVar.j();
                        if (rVar.g() && str2 != null && !"熄火".equals(str2) && "熄火".equals(rVar.f8013g) && rVar.f() && r.h(rVar.f8011e) && r.h(rVar.f8012f)) {
                            double d7 = rVar.f8011e - rVar.f8010d.f7980c;
                            if (d7 < 2.0d) {
                                if (Math.abs(d7) >= 1.0E-4d) {
                                    double d8 = rVar.f8012f;
                                    n nVar = rVar.f8010d;
                                    double d9 = d8 - nVar.f7981d;
                                    if (d9 > 0.0d) {
                                        double d10 = ((nVar.f7980c - rVar.f8011e) * nVar.f7979b) / 100.0d;
                                        if (d10 > 0.0d) {
                                            long currentTimeMillis = System.currentTimeMillis();
                                            String str3 = new SimpleDateFormat("yyyyMMdd_HHmmss", Locale.US).format(Long.valueOf(currentTimeMillis)) + "_" + Math.round(d9) + "km.json";
                                            n nVar2 = rVar.f8010d;
                                            String str4 = nVar2.f7984g;
                                            long j7 = nVar2.f7982e;
                                            if (j7 <= 0) {
                                                j7 = currentTimeMillis;
                                            }
                                            double d11 = nVar2.f7980c;
                                            double d12 = rVar.f8011e;
                                            double d13 = nVar2.f7979b;
                                            double d14 = nVar2.f7981d;
                                            double d15 = rVar.f8012f;
                                            double d16 = (d10 / d9) * 100.0d;
                                            String p6 = r.p(nVar2.f7983f, "未知");
                                            String p7 = r.p(rVar.f8013g, "熄火");
                                            StringBuilder sb = new StringBuilder("(");
                                            DecimalFormat decimalFormat = r.f8005m;
                                            sb.append(decimalFormat.format(rVar.f8010d.f7980c));
                                            sb.append(" - ");
                                            sb.append(decimalFormat.format(rVar.f8011e));
                                            sb.append(") * ");
                                            sb.append(decimalFormat.format(rVar.f8010d.f7979b));
                                            sb.append(" / 100");
                                            String sb2 = sb.toString();
                                            String str5 = decimalFormat.format(d10) + " / " + decimalFormat.format(d9) + " * 100";
                                            Calendar calendar = Calendar.getInstance();
                                            calendar.setTimeInMillis(currentTimeMillis);
                                            File file = new File(r.b(calendar.get(1), calendar.get(2) + 1), str3);
                                            JSONObject jSONObject = new JSONObject();
                                            try {
                                                jSONObject.put("recordType", "trip");
                                                jSONObject.put("tripId", str3);
                                                jSONObject.put("cycleId", str4);
                                                jSONObject.put("startTime", j7);
                                                jSONObject.put("endTime", currentTimeMillis);
                                                jSONObject.put("startFuelPercent", d11);
                                                jSONObject.put("endFuelPercent", d12);
                                                jSONObject.put("tankCapacityLiters", d13);
                                                jSONObject.put("startOdoKm", d14);
                                                jSONObject.put("endOdoKm", d15);
                                                jSONObject.put("deltaKm", d9);
                                                jSONObject.put("usedLiters", d10);
                                                jSONObject.put("fuelPer100Km", d16);
                                                JSONObject jSONObject2 = new JSONObject();
                                                jSONObject2.put("formulaUsedLiters", sb2);
                                                jSONObject2.put("formulaFuelPer100Km", str5);
                                                jSONObject.put("calculation", jSONObject2);
                                                JSONObject jSONObject3 = new JSONObject();
                                                jSONObject3.put("startStatus", p6);
                                                jSONObject3.put("endStatus", p7);
                                                jSONObject.put("engineCycle", jSONObject3);
                                            } catch (JSONException unused) {
                                            }
                                            r.r(file, jSONObject);
                                            n nVar3 = rVar.f8010d;
                                            nVar3.f7980c = rVar.f8011e;
                                            nVar3.f7981d = rVar.f8012f;
                                            nVar3.f7982e = currentTimeMillis;
                                            nVar3.f7983f = rVar.f8013g;
                                            nVar3.f7988k++;
                                            nVar3.f7989l += d9;
                                            nVar3.f7990m += d10;
                                            r.q(nVar3);
                                            rVar.f8007a.sendBroadcast(new Intent(r.ACTION_OIL_RECORD_UPDATED));
                                            break;
                                        }
                                    }
                                }
                            } else {
                                rVar.e();
                                break;
                            }
                        }
                        break;
                }
            }
        };
        final int i8 = 1;
        this.f8016j = new h0(this) { // from class: o4.m

            /* renamed from: b, reason: collision with root package name */
            public final /* synthetic */ r f7977b;

            {
                this.f7977b = this;
            }

            @Override // androidx.lifecycle.h0
            public final void onChanged(Object obj) {
                int i82 = i8;
                r rVar = this.f7977b;
                switch (i82) {
                    case 0:
                        rVar.getClass();
                        rVar.f8011e = r.k((String) obj);
                        rVar.j();
                        break;
                    case 1:
                        rVar.getClass();
                        rVar.f8012f = r.k((String) obj);
                        rVar.j();
                        break;
                    default:
                        String str = (String) obj;
                        String str2 = rVar.f8013g;
                        rVar.f8013g = str == null ? null : str.trim();
                        rVar.j();
                        if (rVar.g() && str2 != null && !"熄火".equals(str2) && "熄火".equals(rVar.f8013g) && rVar.f() && r.h(rVar.f8011e) && r.h(rVar.f8012f)) {
                            double d7 = rVar.f8011e - rVar.f8010d.f7980c;
                            if (d7 < 2.0d) {
                                if (Math.abs(d7) >= 1.0E-4d) {
                                    double d8 = rVar.f8012f;
                                    n nVar = rVar.f8010d;
                                    double d9 = d8 - nVar.f7981d;
                                    if (d9 > 0.0d) {
                                        double d10 = ((nVar.f7980c - rVar.f8011e) * nVar.f7979b) / 100.0d;
                                        if (d10 > 0.0d) {
                                            long currentTimeMillis = System.currentTimeMillis();
                                            String str3 = new SimpleDateFormat("yyyyMMdd_HHmmss", Locale.US).format(Long.valueOf(currentTimeMillis)) + "_" + Math.round(d9) + "km.json";
                                            n nVar2 = rVar.f8010d;
                                            String str4 = nVar2.f7984g;
                                            long j7 = nVar2.f7982e;
                                            if (j7 <= 0) {
                                                j7 = currentTimeMillis;
                                            }
                                            double d11 = nVar2.f7980c;
                                            double d12 = rVar.f8011e;
                                            double d13 = nVar2.f7979b;
                                            double d14 = nVar2.f7981d;
                                            double d15 = rVar.f8012f;
                                            double d16 = (d10 / d9) * 100.0d;
                                            String p6 = r.p(nVar2.f7983f, "未知");
                                            String p7 = r.p(rVar.f8013g, "熄火");
                                            StringBuilder sb = new StringBuilder("(");
                                            DecimalFormat decimalFormat = r.f8005m;
                                            sb.append(decimalFormat.format(rVar.f8010d.f7980c));
                                            sb.append(" - ");
                                            sb.append(decimalFormat.format(rVar.f8011e));
                                            sb.append(") * ");
                                            sb.append(decimalFormat.format(rVar.f8010d.f7979b));
                                            sb.append(" / 100");
                                            String sb2 = sb.toString();
                                            String str5 = decimalFormat.format(d10) + " / " + decimalFormat.format(d9) + " * 100";
                                            Calendar calendar = Calendar.getInstance();
                                            calendar.setTimeInMillis(currentTimeMillis);
                                            File file = new File(r.b(calendar.get(1), calendar.get(2) + 1), str3);
                                            JSONObject jSONObject = new JSONObject();
                                            try {
                                                jSONObject.put("recordType", "trip");
                                                jSONObject.put("tripId", str3);
                                                jSONObject.put("cycleId", str4);
                                                jSONObject.put("startTime", j7);
                                                jSONObject.put("endTime", currentTimeMillis);
                                                jSONObject.put("startFuelPercent", d11);
                                                jSONObject.put("endFuelPercent", d12);
                                                jSONObject.put("tankCapacityLiters", d13);
                                                jSONObject.put("startOdoKm", d14);
                                                jSONObject.put("endOdoKm", d15);
                                                jSONObject.put("deltaKm", d9);
                                                jSONObject.put("usedLiters", d10);
                                                jSONObject.put("fuelPer100Km", d16);
                                                JSONObject jSONObject2 = new JSONObject();
                                                jSONObject2.put("formulaUsedLiters", sb2);
                                                jSONObject2.put("formulaFuelPer100Km", str5);
                                                jSONObject.put("calculation", jSONObject2);
                                                JSONObject jSONObject3 = new JSONObject();
                                                jSONObject3.put("startStatus", p6);
                                                jSONObject3.put("endStatus", p7);
                                                jSONObject.put("engineCycle", jSONObject3);
                                            } catch (JSONException unused) {
                                            }
                                            r.r(file, jSONObject);
                                            n nVar3 = rVar.f8010d;
                                            nVar3.f7980c = rVar.f8011e;
                                            nVar3.f7981d = rVar.f8012f;
                                            nVar3.f7982e = currentTimeMillis;
                                            nVar3.f7983f = rVar.f8013g;
                                            nVar3.f7988k++;
                                            nVar3.f7989l += d9;
                                            nVar3.f7990m += d10;
                                            r.q(nVar3);
                                            rVar.f8007a.sendBroadcast(new Intent(r.ACTION_OIL_RECORD_UPDATED));
                                            break;
                                        }
                                    }
                                }
                            } else {
                                rVar.e();
                                break;
                            }
                        }
                        break;
                }
            }
        };
        final int i9 = 2;
        this.f8017k = new h0(this) { // from class: o4.m

            /* renamed from: b, reason: collision with root package name */
            public final /* synthetic */ r f7977b;

            {
                this.f7977b = this;
            }

            @Override // androidx.lifecycle.h0
            public final void onChanged(Object obj) {
                int i82 = i9;
                r rVar = this.f7977b;
                switch (i82) {
                    case 0:
                        rVar.getClass();
                        rVar.f8011e = r.k((String) obj);
                        rVar.j();
                        break;
                    case 1:
                        rVar.getClass();
                        rVar.f8012f = r.k((String) obj);
                        rVar.j();
                        break;
                    default:
                        String str = (String) obj;
                        String str2 = rVar.f8013g;
                        rVar.f8013g = str == null ? null : str.trim();
                        rVar.j();
                        if (rVar.g() && str2 != null && !"熄火".equals(str2) && "熄火".equals(rVar.f8013g) && rVar.f() && r.h(rVar.f8011e) && r.h(rVar.f8012f)) {
                            double d7 = rVar.f8011e - rVar.f8010d.f7980c;
                            if (d7 < 2.0d) {
                                if (Math.abs(d7) >= 1.0E-4d) {
                                    double d8 = rVar.f8012f;
                                    n nVar = rVar.f8010d;
                                    double d9 = d8 - nVar.f7981d;
                                    if (d9 > 0.0d) {
                                        double d10 = ((nVar.f7980c - rVar.f8011e) * nVar.f7979b) / 100.0d;
                                        if (d10 > 0.0d) {
                                            long currentTimeMillis = System.currentTimeMillis();
                                            String str3 = new SimpleDateFormat("yyyyMMdd_HHmmss", Locale.US).format(Long.valueOf(currentTimeMillis)) + "_" + Math.round(d9) + "km.json";
                                            n nVar2 = rVar.f8010d;
                                            String str4 = nVar2.f7984g;
                                            long j7 = nVar2.f7982e;
                                            if (j7 <= 0) {
                                                j7 = currentTimeMillis;
                                            }
                                            double d11 = nVar2.f7980c;
                                            double d12 = rVar.f8011e;
                                            double d13 = nVar2.f7979b;
                                            double d14 = nVar2.f7981d;
                                            double d15 = rVar.f8012f;
                                            double d16 = (d10 / d9) * 100.0d;
                                            String p6 = r.p(nVar2.f7983f, "未知");
                                            String p7 = r.p(rVar.f8013g, "熄火");
                                            StringBuilder sb = new StringBuilder("(");
                                            DecimalFormat decimalFormat = r.f8005m;
                                            sb.append(decimalFormat.format(rVar.f8010d.f7980c));
                                            sb.append(" - ");
                                            sb.append(decimalFormat.format(rVar.f8011e));
                                            sb.append(") * ");
                                            sb.append(decimalFormat.format(rVar.f8010d.f7979b));
                                            sb.append(" / 100");
                                            String sb2 = sb.toString();
                                            String str5 = decimalFormat.format(d10) + " / " + decimalFormat.format(d9) + " * 100";
                                            Calendar calendar = Calendar.getInstance();
                                            calendar.setTimeInMillis(currentTimeMillis);
                                            File file = new File(r.b(calendar.get(1), calendar.get(2) + 1), str3);
                                            JSONObject jSONObject = new JSONObject();
                                            try {
                                                jSONObject.put("recordType", "trip");
                                                jSONObject.put("tripId", str3);
                                                jSONObject.put("cycleId", str4);
                                                jSONObject.put("startTime", j7);
                                                jSONObject.put("endTime", currentTimeMillis);
                                                jSONObject.put("startFuelPercent", d11);
                                                jSONObject.put("endFuelPercent", d12);
                                                jSONObject.put("tankCapacityLiters", d13);
                                                jSONObject.put("startOdoKm", d14);
                                                jSONObject.put("endOdoKm", d15);
                                                jSONObject.put("deltaKm", d9);
                                                jSONObject.put("usedLiters", d10);
                                                jSONObject.put("fuelPer100Km", d16);
                                                JSONObject jSONObject2 = new JSONObject();
                                                jSONObject2.put("formulaUsedLiters", sb2);
                                                jSONObject2.put("formulaFuelPer100Km", str5);
                                                jSONObject.put("calculation", jSONObject2);
                                                JSONObject jSONObject3 = new JSONObject();
                                                jSONObject3.put("startStatus", p6);
                                                jSONObject3.put("endStatus", p7);
                                                jSONObject.put("engineCycle", jSONObject3);
                                            } catch (JSONException unused) {
                                            }
                                            r.r(file, jSONObject);
                                            n nVar3 = rVar.f8010d;
                                            nVar3.f7980c = rVar.f8011e;
                                            nVar3.f7981d = rVar.f8012f;
                                            nVar3.f7982e = currentTimeMillis;
                                            nVar3.f7983f = rVar.f8013g;
                                            nVar3.f7988k++;
                                            nVar3.f7989l += d9;
                                            nVar3.f7990m += d10;
                                            r.q(nVar3);
                                            rVar.f8007a.sendBroadcast(new Intent(r.ACTION_OIL_RECORD_UPDATED));
                                            break;
                                        }
                                    }
                                }
                            } else {
                                rVar.e();
                                break;
                            }
                        }
                        break;
                }
            }
        };
        Context applicationContext = context.getApplicationContext();
        this.f8007a = applicationContext;
        this.f8008b = SharedPreferencesUtils.getAppPrefs(applicationContext);
        this.f8009c = c.c();
        this.f8018l = new g4.f(2, this);
    }

    public static String a(long j7) {
        return "cycle_" + new SimpleDateFormat("yyyyMMdd_HHmmss", Locale.US).format(Long.valueOf(j7));
    }

    public static File b(int i7, int i8) {
        File file = new File(c(), String.valueOf(i7));
        if (!file.exists() && !file.mkdirs()) {
            file.getAbsolutePath();
        }
        File file2 = new File(file, String.format(Locale.US, TimeModel.ZERO_LEADING_NUMBER_FORMAT, Integer.valueOf(i8)));
        if (!file2.exists() && !file2.mkdirs()) {
            file2.getAbsolutePath();
        }
        return file2;
    }

    public static File c() {
        File file = new File(Environment.getExternalStorageDirectory(), "navitool/oilrecord");
        if (!file.exists() && !file.mkdirs()) {
            file.getAbsolutePath();
        }
        return file;
    }

    public static double d(SharedPreferences sharedPreferences) {
        return sharedPreferences.getFloat(PREF_OIL_TANK_CAPACITY, CropImageView.DEFAULT_ASPECT_RATIO);
    }

    public static boolean h(double d7) {
        return (Double.isNaN(d7) || Double.isInfinite(d7)) ? false : true;
    }

    public static double k(String str) {
        if (str == null) {
            return Double.NaN;
        }
        String replaceAll = str.replaceAll("[^0-9.\\-]", s5.h0.FRAGMENT_ENCODE_SET);
        if (replaceAll.isEmpty() || ".".equals(replaceAll) || "-".equals(replaceAll) || "-.".equals(replaceAll)) {
            return Double.NaN;
        }
        try {
            return Double.parseDouble(replaceAll);
        } catch (NumberFormatException unused) {
            return Double.NaN;
        }
    }

    public static n l() {
        JSONObject m6 = m(new File(c(), ".base.json"));
        if (m6 == null) {
            return null;
        }
        n nVar = new n();
        nVar.f7978a = m6.optInt("schemaVersion", 1);
        nVar.f7979b = m6.has("tankCapacityLiters") ? m6.optDouble("tankCapacityLiters", Double.NaN) : Double.NaN;
        nVar.f7980c = m6.has("baseFuelPercent") ? m6.optDouble("baseFuelPercent", Double.NaN) : Double.NaN;
        nVar.f7981d = m6.has("baseOdoKm") ? m6.optDouble("baseOdoKm", Double.NaN) : Double.NaN;
        nVar.f7982e = m6.optLong("baseTime", 0L);
        nVar.f7983f = m6.optString("lastEngineStatus", null);
        nVar.f7984g = m6.optString("currentCycleId", s5.h0.FRAGMENT_ENCODE_SET);
        nVar.f7985h = m6.optLong("cycleStartTime", 0L);
        nVar.f7986i = m6.has("cycleStartFuelPercent") ? m6.optDouble("cycleStartFuelPercent", Double.NaN) : Double.NaN;
        nVar.f7987j = m6.has("cycleStartOdoKm") ? m6.optDouble("cycleStartOdoKm", Double.NaN) : Double.NaN;
        nVar.f7988k = m6.optInt("cycleTripCount", 0);
        nVar.f7989l = m6.optDouble("cycleTotalKm", 0.0d);
        nVar.f7990m = m6.optDouble("cycleTotalUsedLiters", 0.0d);
        return nVar;
    }

    public static JSONObject m(File file) {
        if (file.exists() && file.isFile()) {
            try {
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(file), StandardCharsets.UTF_8));
                try {
                    StringBuilder sb = new StringBuilder();
                    while (true) {
                        String readLine = bufferedReader.readLine();
                        if (readLine == null) {
                            JSONObject jSONObject = new JSONObject(sb.toString());
                            bufferedReader.close();
                            return jSONObject;
                        }
                        sb.append(readLine);
                    }
                } finally {
                }
            } catch (Exception unused) {
                file.getAbsolutePath();
            }
        }
        return null;
    }

    public static boolean n(Context context) {
        SharedPreferences appPrefs = SharedPreferencesUtils.getAppPrefs(context);
        double d7 = d(appPrefs);
        if (d7 > 0.0d && !appPrefs.getBoolean("lion_sync_service_enabled", false)) {
            c c7 = c.c();
            double k7 = k((String) c7.f7941i.d());
            double k8 = k((String) c7.f7943k.d());
            if (h(k7) && h(k8)) {
                String str = (String) c7.f7949q.d();
                String trim = str == null ? null : str.trim();
                long currentTimeMillis = System.currentTimeMillis();
                n nVar = new n();
                nVar.f7979b = d7;
                nVar.f7980c = k7;
                nVar.f7981d = k8;
                nVar.f7982e = currentTimeMillis;
                nVar.f7983f = trim;
                nVar.f7984g = a(currentTimeMillis);
                nVar.f7985h = currentTimeMillis;
                nVar.f7986i = k7;
                nVar.f7987j = k8;
                context.getApplicationContext();
                q(nVar);
                return true;
            }
        }
        return false;
    }

    public static void o(Context context, double d7) {
        if (d7 <= 0.0d) {
            return;
        }
        context.getApplicationContext();
        n l7 = l();
        if (l7 == null) {
            l7 = new n();
            l7.f7978a = 1;
            l7.f7984g = a(System.currentTimeMillis());
        }
        l7.f7979b = d7;
        String str = l7.f7984g;
        if (str == null || str.isEmpty()) {
            l7.f7984g = a(System.currentTimeMillis());
        }
        q(l7);
    }

    public static String p(String str, String str2) {
        return (str == null || str.trim().isEmpty()) ? str2 : str;
    }

    public static void q(n nVar) {
        nVar.f7978a = 1;
        File file = new File(c(), ".base.json");
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("schemaVersion", nVar.f7978a);
            if (h(nVar.f7979b)) {
                jSONObject.put("tankCapacityLiters", nVar.f7979b);
            }
            if (h(nVar.f7980c)) {
                jSONObject.put("baseFuelPercent", nVar.f7980c);
            }
            if (h(nVar.f7981d)) {
                jSONObject.put("baseOdoKm", nVar.f7981d);
            }
            jSONObject.put("baseTime", nVar.f7982e);
            jSONObject.put("lastEngineStatus", p(nVar.f7983f, s5.h0.FRAGMENT_ENCODE_SET));
            jSONObject.put("currentCycleId", p(nVar.f7984g, s5.h0.FRAGMENT_ENCODE_SET));
            jSONObject.put("cycleStartTime", nVar.f7985h);
            if (h(nVar.f7986i)) {
                jSONObject.put("cycleStartFuelPercent", nVar.f7986i);
            }
            if (h(nVar.f7987j)) {
                jSONObject.put("cycleStartOdoKm", nVar.f7987j);
            }
            jSONObject.put("cycleTripCount", nVar.f7988k);
            jSONObject.put("cycleTotalKm", nVar.f7989l);
            jSONObject.put("cycleTotalUsedLiters", nVar.f7990m);
        } catch (JSONException unused) {
        }
        r(file, jSONObject);
    }

    public static void r(File file, JSONObject jSONObject) {
        File parentFile = file.getParentFile();
        if (parentFile != null && !parentFile.exists() && !parentFile.mkdirs()) {
            parentFile.getAbsolutePath();
            return;
        }
        try {
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(new FileOutputStream(file, false), StandardCharsets.UTF_8);
            try {
                outputStreamWriter.write(jSONObject.toString(2));
                outputStreamWriter.flush();
                outputStreamWriter.close();
            } catch (Throwable th) {
                try {
                    outputStreamWriter.close();
                } catch (Throwable th2) {
                    th.addSuppressed(th2);
                }
                throw th;
            }
        } catch (IOException | JSONException unused) {
            file.getAbsolutePath();
        }
    }

    public final void e() {
        if (f() && h(this.f8011e) && h(this.f8012f) && this.f8011e - this.f8010d.f7980c >= 2.0d) {
            long currentTimeMillis = System.currentTimeMillis();
            n nVar = this.f8010d;
            if (nVar.f7988k > 0 && nVar.f7989l > 0.0d && nVar.f7990m > 0.0d) {
                String str = new SimpleDateFormat("yyyyMMdd_HHmmss", Locale.US).format(Long.valueOf(currentTimeMillis)) + "_refuel_summary.json";
                n nVar2 = this.f8010d;
                String str2 = nVar2.f7984g;
                long j7 = nVar2.f7985h;
                if (j7 <= 0) {
                    j7 = nVar2.f7982e;
                }
                double d7 = nVar2.f7986i;
                double d8 = nVar2.f7980c;
                double d9 = this.f8011e;
                double d10 = nVar2.f7987j;
                double d11 = this.f8012f;
                int i7 = nVar2.f7988k;
                double d12 = nVar2.f7989l;
                double d13 = nVar2.f7990m;
                double d14 = (d13 / d12) * 100.0d;
                double d15 = nVar2.f7979b;
                Calendar calendar = Calendar.getInstance();
                calendar.setTimeInMillis(currentTimeMillis);
                File file = new File(b(calendar.get(1), calendar.get(2) + 1), str);
                JSONObject jSONObject = new JSONObject();
                try {
                    jSONObject.put("recordType", "refuel_summary");
                    jSONObject.put("summaryId", str);
                    jSONObject.put("cycleId", str2);
                    jSONObject.put("startTime", j7);
                    jSONObject.put("endTime", currentTimeMillis);
                    jSONObject.put("cycleStartFuelPercent", d7);
                    jSONObject.put("lastFuelPercentBeforeRefuel", d8);
                    jSONObject.put("refuelDetectedFuelPercent", d9);
                    jSONObject.put("cycleStartOdoKm", d10);
                    jSONObject.put("refuelDetectedOdoKm", d11);
                    jSONObject.put("tripCount", i7);
                    jSONObject.put("totalKm", d12);
                    jSONObject.put("totalUsedLiters", d13);
                    jSONObject.put("avgFuelPer100Km", d14);
                    jSONObject.put("tankCapacityLiters", d15);
                } catch (JSONException unused) {
                }
                r(file, jSONObject);
            }
            double d16 = this.f8011e;
            double d17 = this.f8012f;
            String str3 = this.f8013g;
            double d18 = d(this.f8008b);
            n nVar3 = new n();
            nVar3.f7979b = d18;
            nVar3.f7980c = d16;
            nVar3.f7981d = d17;
            nVar3.f7982e = currentTimeMillis;
            nVar3.f7983f = str3;
            nVar3.f7984g = a(currentTimeMillis);
            nVar3.f7985h = currentTimeMillis;
            nVar3.f7986i = d16;
            nVar3.f7987j = d17;
            q(nVar3);
            this.f8010d = nVar3;
            this.f8007a.sendBroadcast(new Intent(ACTION_OIL_RECORD_UPDATED));
        }
    }

    public final boolean f() {
        n nVar = this.f8010d;
        return nVar != null && h(nVar.f7980c) && h(this.f8010d.f7981d) && this.f8010d.f7979b > 0.0d;
    }

    public final boolean g() {
        SharedPreferences sharedPreferences = this.f8008b;
        return sharedPreferences.getBoolean(PREF_OIL_RECORD_ENABLED, false) && sharedPreferences.getBoolean("car_link_service_enabled", false) && !sharedPreferences.getBoolean("lion_sync_service_enabled", false) && d(sharedPreferences) > 0.0d;
    }

    public final void i() {
        n l7 = l();
        this.f8010d = l7;
        if (l7 == null || l7.f7979b > 0.0d) {
            return;
        }
        l7.f7979b = d(this.f8008b);
    }

    public final void j() {
        if (this.f8010d == null) {
            i();
        }
        if (g()) {
            if (!f()) {
                n(this.f8007a);
                i();
            }
            if (f() && h(this.f8011e) && this.f8011e - this.f8010d.f7980c >= 2.0d) {
                e();
            }
        }
    }
}
