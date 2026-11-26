package n0;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final class i implements j {

    /* renamed from: a, reason: collision with root package name */
    public static final i f7581a = new i();

    @Override // n0.j
    public final int a(CharSequence charSequence, int i7) {
        int i8 = 2;
        for (int i9 = 0; i9 < i7 && i8 == 2; i9++) {
            byte directionality = Character.getDirectionality(charSequence.charAt(i9));
            g gVar = n.LTR;
            if (directionality != 0) {
                if (directionality != 1 && directionality != 2) {
                    switch (directionality) {
                        case 14:
                        case 15:
                            break;
                        case 16:
                        case 17:
                            break;
                        default:
                            i8 = 2;
                            break;
                    }
                }
                i8 = 0;
            }
            i8 = 1;
        }
        return i8;
    }
}
