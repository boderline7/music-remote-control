package com.google.android.material.search;

/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX WARN: Unknown enum class pattern. Please report as an issue! */
/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_2_1633180.dex */
public final class j {
    public static final j HIDDEN;
    public static final j HIDING;
    public static final j SHOWING;
    public static final j SHOWN;

    /* renamed from: a, reason: collision with root package name */
    public static final /* synthetic */ j[] f4092a;

    static {
        j jVar = new j("HIDING", 0);
        HIDING = jVar;
        j jVar2 = new j("HIDDEN", 1);
        HIDDEN = jVar2;
        j jVar3 = new j("SHOWING", 2);
        SHOWING = jVar3;
        j jVar4 = new j("SHOWN", 3);
        SHOWN = jVar4;
        f4092a = new j[]{jVar, jVar2, jVar3, jVar4};
    }

    public static j valueOf(String str) {
        return (j) Enum.valueOf(j.class, str);
    }

    public static j[] values() {
        return (j[]) f4092a.clone();
    }
}
