package h2;

import k2.q;
import k2.s;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public abstract /* synthetic */ class c {

    /* renamed from: a, reason: collision with root package name */
    public static final /* synthetic */ int[] f6704a;

    /* renamed from: b, reason: collision with root package name */
    public static final /* synthetic */ int[] f6705b;

    static {
        int[] iArr = new int[s.values().length];
        f6705b = iArr;
        try {
            iArr[s.NewSubHeader.ordinal()] = 1;
        } catch (NoSuchFieldError unused) {
        }
        try {
            f6705b[s.FileHeader.ordinal()] = 2;
        } catch (NoSuchFieldError unused2) {
        }
        try {
            f6705b[s.ProtectHeader.ordinal()] = 3;
        } catch (NoSuchFieldError unused3) {
        }
        try {
            f6705b[s.SubHeader.ordinal()] = 4;
        } catch (NoSuchFieldError unused4) {
        }
        try {
            f6705b[s.MarkHeader.ordinal()] = 5;
        } catch (NoSuchFieldError unused5) {
        }
        try {
            f6705b[s.MainHeader.ordinal()] = 6;
        } catch (NoSuchFieldError unused6) {
        }
        try {
            f6705b[s.SignHeader.ordinal()] = 7;
        } catch (NoSuchFieldError unused7) {
        }
        try {
            f6705b[s.AvHeader.ordinal()] = 8;
        } catch (NoSuchFieldError unused8) {
        }
        try {
            f6705b[s.CommHeader.ordinal()] = 9;
        } catch (NoSuchFieldError unused9) {
        }
        try {
            f6705b[s.EndArcHeader.ordinal()] = 10;
        } catch (NoSuchFieldError unused10) {
        }
        int[] iArr2 = new int[q.values().length];
        f6704a = iArr2;
        try {
            iArr2[q.MAC_HEAD.ordinal()] = 1;
        } catch (NoSuchFieldError unused11) {
        }
        try {
            f6704a[q.BEEA_HEAD.ordinal()] = 2;
        } catch (NoSuchFieldError unused12) {
        }
        try {
            f6704a[q.EA_HEAD.ordinal()] = 3;
        } catch (NoSuchFieldError unused13) {
        }
        try {
            f6704a[q.NTACL_HEAD.ordinal()] = 4;
        } catch (NoSuchFieldError unused14) {
        }
        try {
            f6704a[q.STREAM_HEAD.ordinal()] = 5;
        } catch (NoSuchFieldError unused15) {
        }
        try {
            f6704a[q.UO_HEAD.ordinal()] = 6;
        } catch (NoSuchFieldError unused16) {
        }
    }
}
