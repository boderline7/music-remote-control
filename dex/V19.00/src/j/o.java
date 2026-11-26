package j;

import android.view.ActionMode;
import android.view.SearchEvent;
import android.view.Window;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public abstract class o {
    public static boolean a(Window.Callback callback, SearchEvent searchEvent) {
        return callback.onSearchRequested(searchEvent);
    }

    public static ActionMode b(Window.Callback callback, ActionMode.Callback callback2, int i7) {
        return callback.onWindowStartingActionMode(callback2, i7);
    }
}
