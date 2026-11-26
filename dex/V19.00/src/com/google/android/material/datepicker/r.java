package com.google.android.material.datepicker;

/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX WARN: Unknown enum class pattern. Please report as an issue! */
/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_2_1633180.dex */
public final class r {
    public static final r DAY;
    public static final r YEAR;

    /* renamed from: a, reason: collision with root package name */
    public static final /* synthetic */ r[] f3656a;

    static {
        r rVar = new r("DAY", 0);
        DAY = rVar;
        r rVar2 = new r("YEAR", 1);
        YEAR = rVar2;
        f3656a = new r[]{rVar, rVar2};
    }

    public static r valueOf(String str) {
        return (r) Enum.valueOf(r.class, str);
    }

    public static r[] values() {
        return (r[]) f3656a.clone();
    }
}
