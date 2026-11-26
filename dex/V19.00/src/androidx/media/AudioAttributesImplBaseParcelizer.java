package androidx.media;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_2_1633180.dex */
public class AudioAttributesImplBaseParcelizer {
    public static AudioAttributesImplBase read(p1.a aVar) {
        AudioAttributesImplBase audioAttributesImplBase = new AudioAttributesImplBase();
        audioAttributesImplBase.f2222a = aVar.f(audioAttributesImplBase.f2222a, 1);
        audioAttributesImplBase.f2223b = aVar.f(audioAttributesImplBase.f2223b, 2);
        audioAttributesImplBase.f2224c = aVar.f(audioAttributesImplBase.f2224c, 3);
        audioAttributesImplBase.f2225d = aVar.f(audioAttributesImplBase.f2225d, 4);
        return audioAttributesImplBase;
    }

    public static void write(AudioAttributesImplBase audioAttributesImplBase, p1.a aVar) {
        aVar.getClass();
        aVar.j(audioAttributesImplBase.f2222a, 1);
        aVar.j(audioAttributesImplBase.f2223b, 2);
        aVar.j(audioAttributesImplBase.f2224c, 3);
        aVar.j(audioAttributesImplBase.f2225d, 4);
    }
}
