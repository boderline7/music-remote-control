package androidx.media;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_2_1633180.dex */
public class AudioAttributesCompatParcelizer {
    public static AudioAttributesCompat read(p1.a aVar) {
        AudioAttributesCompat audioAttributesCompat = new AudioAttributesCompat();
        p1.c cVar = audioAttributesCompat.f2219a;
        if (aVar.e(1)) {
            cVar = aVar.h();
        }
        audioAttributesCompat.f2219a = (AudioAttributesImpl) cVar;
        return audioAttributesCompat;
    }

    public static void write(AudioAttributesCompat audioAttributesCompat, p1.a aVar) {
        aVar.getClass();
        AudioAttributesImpl audioAttributesImpl = audioAttributesCompat.f2219a;
        aVar.i(1);
        aVar.l(audioAttributesImpl);
    }
}
