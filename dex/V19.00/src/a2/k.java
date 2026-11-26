package a2;

import java.text.SimpleDateFormat;
import java.util.Locale;
import java.util.Random;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final class k extends ThreadLocal {

    /* renamed from: a, reason: collision with root package name */
    public final /* synthetic */ int f232a;

    @Override // java.lang.ThreadLocal
    public final Object initialValue() {
        switch (this.f232a) {
            case 0:
                return new l();
            case 1:
                return new Random();
            default:
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss 'GMT'", Locale.US);
                simpleDateFormat.setLenient(false);
                simpleDateFormat.setTimeZone(t5.b.UTC);
                return simpleDateFormat;
        }
    }
}
