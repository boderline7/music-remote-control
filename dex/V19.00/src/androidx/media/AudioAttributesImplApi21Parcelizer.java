package androidx.media;

import android.media.AudioAttributes;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_2_1633180.dex */
public class AudioAttributesImplApi21Parcelizer {
    public static AudioAttributesImplApi21 read(p1.a aVar) {
        AudioAttributesImplApi21 audioAttributesImplApi21 = new AudioAttributesImplApi21();
        audioAttributesImplApi21.f2220a = (AudioAttributes) aVar.g(audioAttributesImplApi21.f2220a, 1);
        audioAttributesImplApi21.f2221b = aVar.f(audioAttributesImplApi21.f2221b, 2);
        return audioAttributesImplApi21;
    }

    public static void write(AudioAttributesImplApi21 audioAttributesImplApi21, p1.a aVar) {
        aVar.getClass();
        aVar.k(audioAttributesImplApi21.f2220a, 1);
        aVar.j(audioAttributesImplApi21.f2221b, 2);
    }
}
