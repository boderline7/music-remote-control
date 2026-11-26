package androidx.lifecycle;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_2_1633180.dex */
public interface f extends w {
    default void onCreate(x xVar) {
        i5.a.g("owner", xVar);
    }

    default void onDestroy(x xVar) {
        i5.a.g("owner", xVar);
    }

    default void onPause(x xVar) {
        i5.a.g("owner", xVar);
    }

    void onResume(x xVar);

    default void onStart(x xVar) {
        i5.a.g("owner", xVar);
    }

    default void onStop(x xVar) {
        i5.a.g("owner", xVar);
    }
}
