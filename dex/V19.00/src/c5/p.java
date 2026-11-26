package c5;

import java.util.ArrayList;
import java.util.List;
import n1.z;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public abstract class p extends n {
    public static final void H1(Iterable iterable, StringBuilder sb, CharSequence charSequence, CharSequence charSequence2, CharSequence charSequence3, int i7, CharSequence charSequence4, h5.l lVar) {
        i5.a.g("<this>", iterable);
        i5.a.g("separator", charSequence);
        i5.a.g("prefix", charSequence2);
        i5.a.g("postfix", charSequence3);
        i5.a.g("truncated", charSequence4);
        sb.append(charSequence2);
        int i8 = 0;
        for (Object obj : iterable) {
            i8++;
            if (i8 > 1) {
                sb.append(charSequence);
            }
            if (i7 >= 0 && i8 > i7) {
                break;
            } else {
                i5.a.b(sb, obj, lVar);
            }
        }
        if (i7 >= 0 && i8 > i7) {
            sb.append(charSequence4);
        }
        sb.append(charSequence3);
    }

    public static List I1(List list) {
        i5.a.g("<this>", list);
        int size = list.size();
        return size != 0 ? size != 1 ? new ArrayList(list) : z.G0(list.get(0)) : r.INSTANCE;
    }
}
