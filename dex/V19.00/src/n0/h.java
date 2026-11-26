package n0;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final class h implements j {

    /* renamed from: a, reason: collision with root package name */
    public static final h f7580a = new h();

    @Override // n0.j
    public final int a(CharSequence charSequence, int i7) {
        int i8 = 0;
        boolean z6 = false;
        while (true) {
            char c7 = 2;
            if (i8 >= i7) {
                return z6 ? 1 : 2;
            }
            byte directionality = Character.getDirectionality(charSequence.charAt(i8));
            g gVar = n.LTR;
            if (directionality == 0) {
                c7 = 1;
            } else if (directionality == 1 || directionality == 2) {
                c7 = 0;
            }
            if (c7 == 0) {
                return 0;
            }
            if (c7 == 1) {
                z6 = true;
            }
            i8++;
        }
    }
}
