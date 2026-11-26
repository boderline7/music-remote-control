package k5;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final class a extends j5.a {
    @Override // j5.a
    public final Random a() {
        ThreadLocalRandom current = ThreadLocalRandom.current();
        i5.a.f("current()", current);
        return current;
    }
}
