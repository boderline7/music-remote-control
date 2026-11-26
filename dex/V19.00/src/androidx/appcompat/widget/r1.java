package androidx.appcompat.widget;

import android.widget.AbsListView;
import java.lang.reflect.Field;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_2_1633180.dex */
public abstract class r1 {

    /* renamed from: a, reason: collision with root package name */
    public static final Field f1389a;

    static {
        Field field = null;
        try {
            field = AbsListView.class.getDeclaredField("mIsChildViewEnabled");
            field.setAccessible(true);
        } catch (NoSuchFieldException e7) {
            e7.printStackTrace();
        }
        f1389a = field;
    }
}
