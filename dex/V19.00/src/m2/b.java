package m2;

import u.q;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final class b extends c {
    public b(int i7) {
        if (i7 == 1) {
            this.f7527d = new int[60];
            return;
        }
        if (i7 == 2) {
            this.f7527d = new int[299];
            return;
        }
        if (i7 == 3) {
            this.f7527d = new int[17];
            return;
        }
        if (i7 == 4) {
            this.f7527d = new int[q.OPTIMIZATION_STANDARD];
        } else if (i7 != 5) {
            this.f7527d = new int[20];
        } else {
            this.f7527d = new int[28];
        }
    }
}
