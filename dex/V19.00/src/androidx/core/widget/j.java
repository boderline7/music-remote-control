package androidx.core.widget;

import android.widget.ListView;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_2_1633180.dex */
public abstract class j {
    public static boolean a(ListView listView, int i7) {
        return listView.canScrollList(i7);
    }

    public static void b(ListView listView, int i7) {
        listView.scrollListBy(i7);
    }
}
