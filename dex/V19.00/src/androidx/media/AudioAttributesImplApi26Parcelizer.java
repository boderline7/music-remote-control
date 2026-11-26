package androidx.media;

import android.media.AudioAttributes;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_2_1633180.dex */
public class AudioAttributesImplApi26Parcelizer {
    public static AudioAttributesImplApi26 read(p1.a aVar) {
        AudioAttributesImplApi26 audioAttributesImplApi26 = new AudioAttributesImplApi26();
        audioAttributesImplApi26.f2220a = (AudioAttributes) aVar.g(audioAttributesImplApi26.f2220a, 1);
        audioAttributesImplApi26.f2221b = aVar.f(audioAttributesImplApi26.f2221b, 2);
        return audioAttributesImplApi26;
    }

    public static void write(AudioAttributesImplApi26 audioAttributesImplApi26, p1.a aVar) {
        aVar.getClass();
        aVar.k(audioAttributesImplApi26.f2220a, 1);
        aVar.j(audioAttributesImplApi26.f2221b, 2);
    }
}
