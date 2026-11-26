package androidx.media;

import android.media.AudioAttributes;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_2_1633180.dex */
public class AudioAttributesImplApi21 implements AudioAttributesImpl {

    /* renamed from: a, reason: collision with root package name */
    public AudioAttributes f2220a;

    /* renamed from: b, reason: collision with root package name */
    public int f2221b = -1;

    public final boolean equals(Object obj) {
        if (obj instanceof AudioAttributesImplApi21) {
            return this.f2220a.equals(((AudioAttributesImplApi21) obj).f2220a);
        }
        return false;
    }

    public final int hashCode() {
        return this.f2220a.hashCode();
    }

    public final String toString() {
        return "AudioAttributesCompat: audioattributes=" + this.f2220a;
    }
}
