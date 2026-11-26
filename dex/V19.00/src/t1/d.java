package t1;

import android.support.v4.media.session.PlaybackStateCompat;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public abstract class d {
    public static final byte[] SECRET_KEY;
    public static final int SECRET_KEY_LENGTH;

    /* renamed from: a, reason: collision with root package name */
    public static final long[] f9025a;

    /* renamed from: b, reason: collision with root package name */
    public static final int[] f9026b;

    /* renamed from: c, reason: collision with root package name */
    public static final int[] f9027c;

    /* renamed from: d, reason: collision with root package name */
    public static final char[][] f9028d;

    /* renamed from: e, reason: collision with root package name */
    public static final int[] f9029e;

    /* renamed from: f, reason: collision with root package name */
    public static final int[] f9030f;

    /* renamed from: g, reason: collision with root package name */
    public static final int[] f9031g;

    /* renamed from: h, reason: collision with root package name */
    public static final int[] f9032h;

    /* renamed from: i, reason: collision with root package name */
    public static final int[] f9033i;

    /* renamed from: j, reason: collision with root package name */
    public static final long[] f9034j;

    /* renamed from: k, reason: collision with root package name */
    public static long f9035k;

    /* renamed from: l, reason: collision with root package name */
    public static final int[] f9036l;

    /* renamed from: m, reason: collision with root package name */
    public static final byte[] f9037m;

    /* renamed from: n, reason: collision with root package name */
    public static int f9038n;

    /* renamed from: o, reason: collision with root package name */
    public static int f9039o;

    static {
        byte[] bytes = "ylzsxkwm".getBytes();
        SECRET_KEY = bytes;
        SECRET_KEY_LENGTH = bytes.length;
        f9025a = new long[]{1, 2, 4, 8, 16, 32, 64, 128, 256, 512, PlaybackStateCompat.ACTION_PLAY_FROM_MEDIA_ID, PlaybackStateCompat.ACTION_PLAY_FROM_SEARCH, PlaybackStateCompat.ACTION_SKIP_TO_QUEUE_ITEM, PlaybackStateCompat.ACTION_PLAY_FROM_URI, 16384, PlaybackStateCompat.ACTION_PREPARE_FROM_MEDIA_ID, PlaybackStateCompat.ACTION_PREPARE_FROM_SEARCH, PlaybackStateCompat.ACTION_PREPARE_FROM_URI, PlaybackStateCompat.ACTION_SET_REPEAT_MODE, PlaybackStateCompat.ACTION_SET_SHUFFLE_MODE_ENABLED, PlaybackStateCompat.ACTION_SET_CAPTIONING_ENABLED, PlaybackStateCompat.ACTION_SET_SHUFFLE_MODE, PlaybackStateCompat.ACTION_SET_PLAYBACK_SPEED, 8388608, 16777216, 33554432, 67108864, 134217728, 268435456, 536870912, 1073741824, 2147483648L, 4294967296L, 8589934592L, 17179869184L, 34359738368L, 68719476736L, 137438953472L, 274877906944L, 549755813888L, 1099511627776L, 2199023255552L, 4398046511104L, 8796093022208L, 17592186044416L, 35184372088832L, 70368744177664L, 140737488355328L, 281474976710656L, 562949953421312L, 1125899906842624L, 2251799813685248L, 4503599627370496L, 9007199254740992L, 18014398509481984L, 36028797018963968L, 72057594037927936L, 144115188075855872L, 288230376151711744L, 576460752303423488L, 1152921504606846976L, 2305843009213693952L, 4611686018427387904L, Long.MIN_VALUE};
        f9026b = new int[]{57, 49, 41, 33, 25, 17, 9, 1, 59, 51, 43, 35, 27, 19, 11, 3, 61, 53, 45, 37, 29, 21, 13, 5, 63, 55, 47, 39, 31, 23, 15, 7, 56, 48, 40, 32, 24, 16, 8, 0, 58, 50, 42, 34, 26, 18, 10, 2, 60, 52, 44, 36, 28, 20, 12, 4, 62, 54, 46, 38, 30, 22, 14, 6};
        f9027c = new int[]{31, 0, 1, 2, 3, 4, -1, -1, 3, 4, 5, 6, 7, 8, -1, -1, 7, 8, 9, 10, 11, 12, -1, -1, 11, 12, 13, 14, 15, 16, -1, -1, 15, 16, 17, 18, 19, 20, -1, -1, 19, 20, 21, 22, 23, 24, -1, -1, 23, 24, 25, 26, 27, 28, -1, -1, 27, 28, 29, 30, 31, 30, -1, -1};
        f9028d = new char[][]{new char[]{14, 4, 3, 15, 2, '\r', 5, 3, '\r', 14, 6, '\t', 11, 2, 0, 5, 4, 1, '\n', '\f', 15, 6, '\t', '\n', 1, '\b', '\f', 7, '\b', 11, 7, 0, 0, 15, '\n', 5, 14, 4, '\t', '\n', 7, '\b', '\f', 3, '\r', 1, 3, 6, 15, '\f', 6, 11, 2, '\t', 5, 0, 4, 2, 11, 14, 1, 7, '\b', '\r'}, new char[]{15, 0, '\t', 5, 6, '\n', '\f', '\t', '\b', 7, 2, '\f', 3, '\r', 5, 2, 1, 14, 7, '\b', 11, 4, 0, 3, 14, 11, '\r', 6, 4, 1, '\n', 15, 3, '\r', '\f', 11, 15, 3, 6, 0, 4, '\n', 1, 7, '\b', 4, 11, 14, '\r', '\b', 0, 6, 2, 15, '\t', 5, 7, 1, '\n', '\f', 14, 2, 5, '\t'}, new char[]{'\n', '\r', 1, 11, 6, '\b', 11, 5, '\t', 4, '\f', 2, 15, 3, 2, 14, 0, 6, '\r', 1, 3, 15, 4, '\n', 14, '\t', 7, '\f', 5, 0, '\b', 7, '\r', 1, 2, 4, 3, 6, '\f', 11, 0, '\r', 5, 14, 6, '\b', 15, 2, 7, '\n', '\b', 15, 4, '\t', 11, 5, '\t', 0, 14, 3, '\n', 7, 1, '\f'}, new char[]{7, '\n', 1, 15, 0, '\f', 11, 5, 14, '\t', '\b', 3, '\t', 7, 4, '\b', '\r', 6, 2, 1, 6, 11, '\f', 2, 3, 0, 5, 14, '\n', '\r', 15, 4, '\r', 3, 4, '\t', 6, '\n', 1, '\f', 11, 0, 2, 5, 0, '\r', 14, 2, '\b', 15, 7, 4, 15, 1, '\n', 7, 5, 6, '\f', 11, 3, '\b', '\t', 14}, new char[]{2, 4, '\b', 15, 7, '\n', '\r', 6, 4, 1, 3, '\f', 11, 7, 14, 0, '\f', 2, 5, '\t', '\n', '\r', 0, 3, 1, 11, 15, 5, 6, '\b', '\t', 14, 14, 11, 5, 6, 4, 1, 3, '\n', 2, '\f', 15, 0, '\r', 2, '\b', 5, 11, '\b', 0, 15, 7, 14, '\t', 4, '\f', 7, '\n', '\t', 1, '\r', 6, 3}, new char[]{'\f', '\t', 0, 7, '\t', 2, 14, 1, '\n', 15, 3, 4, 6, '\f', 5, 11, 1, 14, '\r', 0, 2, '\b', 7, '\r', 15, 5, 4, '\n', '\b', 3, 11, 6, '\n', 4, 6, 11, 7, '\t', 0, 6, 4, 2, '\r', 1, '\t', 15, 3, '\b', 15, 3, 1, 14, '\f', 5, 11, 0, 2, '\f', 14, 7, 5, '\n', '\b', '\r'}, new char[]{4, 1, 3, '\n', 15, '\f', 5, 0, 2, 11, '\t', 6, '\b', 7, 6, '\t', 11, 4, '\f', 15, 0, 3, '\n', 5, 14, '\r', 7, '\b', '\r', 14, 1, 2, '\r', 6, 14, '\t', 4, 1, 2, 14, 11, '\r', 5, 0, 1, '\n', '\b', 3, 0, 11, 3, 5, '\t', 4, 15, 2, 7, '\b', '\f', 15, '\n', 7, 6, '\f'}, new char[]{'\r', 7, '\n', 0, 6, '\t', 5, 15, '\b', 4, 3, '\n', 11, 14, '\f', 5, 2, 11, '\t', 6, 15, '\f', 0, 3, 4, 1, 14, '\r', 1, 2, 7, '\b', 1, 2, '\f', 15, '\n', 4, 0, 3, '\r', 14, 6, '\t', 7, '\b', '\t', 6, 15, 1, 5, '\f', 3, '\n', 14, 5, '\b', 7, 11, 0, 4, '\r', 2, 11}};
        f9029e = new int[]{15, 6, 19, 20, 28, 11, 27, 16, 0, 14, 22, 25, 4, 17, 30, 9, 1, 7, 23, 13, 31, 26, 2, 8, 18, 12, 29, 5, 21, 10, 3, 24};
        f9030f = new int[]{39, 7, 47, 15, 55, 23, 63, 31, 38, 6, 46, 14, 54, 22, 62, 30, 37, 5, 45, 13, 53, 21, 61, 29, 36, 4, 44, 12, 52, 20, 60, 28, 35, 3, 43, 11, 51, 19, 59, 27, 34, 2, 42, 10, 50, 18, 58, 26, 33, 1, 41, 9, 49, 17, 57, 25, 32, 0, 40, 8, 48, 16, 56, 24};
        f9031g = new int[]{56, 48, 40, 32, 24, 16, 8, 0, 57, 49, 41, 33, 25, 17, 9, 1, 58, 50, 42, 34, 26, 18, 10, 2, 59, 51, 43, 35, 62, 54, 46, 38, 30, 22, 14, 6, 61, 53, 45, 37, 29, 21, 13, 5, 60, 52, 44, 36, 28, 20, 12, 4, 27, 19, 11, 3};
        f9032h = new int[]{13, 16, 10, 23, 0, 4, -1, -1, 2, 27, 14, 5, 20, 9, -1, -1, 22, 18, 11, 3, 25, 7, -1, -1, 15, 6, 26, 19, 12, 1, -1, -1, 40, 51, 30, 36, 46, 54, -1, -1, 29, 39, 50, 44, 32, 47, -1, -1, 43, 48, 38, 55, 33, 52, -1, -1, 45, 41, 49, 35, 28, 31, -1, -1};
        f9033i = new int[]{1, 1, 2, 2, 2, 2, 2, 2, 1, 2, 2, 2, 2, 2, 2, 1};
        f9034j = new long[]{0, 1048577, 3145731};
        f9036l = new int[2];
        f9037m = new byte[8];
    }

    public static long a(int[] iArr, int i7, long j7) {
        long j8 = 0;
        for (int i8 = 0; i8 < i7; i8++) {
            int i9 = iArr[i8];
            if (i9 >= 0) {
                long[] jArr = f9025a;
                if ((jArr[i9] & j7) != 0) {
                    j8 |= jArr[i8];
                }
            }
        }
        return j8;
    }

    public static long b(long[] jArr, long j7) {
        byte[] bArr;
        long a7 = a(f9026b, 64, j7);
        int[] iArr = f9036l;
        iArr[0] = (int) (a7 & 4294967295L);
        iArr[1] = (int) ((a7 & (-4294967296L)) >> 32);
        for (int i7 = 0; i7 < 16; i7++) {
            long j8 = iArr[1];
            f9035k = j8;
            f9035k = a(f9027c, 64, j8) ^ jArr[i7];
            int i8 = 0;
            while (true) {
                bArr = f9037m;
                if (i8 >= 8) {
                    break;
                }
                bArr[i8] = (byte) ((f9035k >> (i8 * 8)) & 255);
                i8++;
            }
            f9038n = 0;
            int i9 = 7;
            while (true) {
                f9039o = i9;
                int i10 = f9039o;
                if (i10 >= 0) {
                    f9038n = (f9038n << 4) | f9028d[i10][bArr[i10]];
                    i9 = i10 - 1;
                }
            }
            long j9 = f9038n;
            f9035k = j9;
            long a8 = a(f9029e, 32, j9);
            f9035k = a8;
            long j10 = iArr[0];
            iArr[0] = iArr[1];
            iArr[1] = (int) (a8 ^ j10);
        }
        int i11 = iArr[0];
        int i12 = iArr[1];
        iArr[0] = i12;
        iArr[1] = i11;
        return a(f9030f, 64, ((i11 << 32) & (-4294967296L)) | (4294967295L & i12));
    }
}
