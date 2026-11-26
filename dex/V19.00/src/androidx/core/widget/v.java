package androidx.core.widget;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.text.Editable;
import android.view.ActionMode;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_2_1633180.dex */
public final class v implements ActionMode.Callback {

    /* renamed from: a, reason: collision with root package name */
    public final ActionMode.Callback f1823a;

    /* renamed from: b, reason: collision with root package name */
    public final TextView f1824b;

    /* renamed from: c, reason: collision with root package name */
    public Class f1825c;

    /* renamed from: d, reason: collision with root package name */
    public Method f1826d;

    /* renamed from: e, reason: collision with root package name */
    public boolean f1827e;

    /* renamed from: f, reason: collision with root package name */
    public boolean f1828f = false;

    public v(ActionMode.Callback callback, TextView textView) {
        this.f1823a = callback;
        this.f1824b = textView;
    }

    @Override // android.view.ActionMode.Callback
    public final boolean onActionItemClicked(ActionMode actionMode, MenuItem menuItem) {
        return this.f1823a.onActionItemClicked(actionMode, menuItem);
    }

    @Override // android.view.ActionMode.Callback
    public final boolean onCreateActionMode(ActionMode actionMode, Menu menu) {
        return this.f1823a.onCreateActionMode(actionMode, menu);
    }

    @Override // android.view.ActionMode.Callback
    public final void onDestroyActionMode(ActionMode actionMode) {
        this.f1823a.onDestroyActionMode(actionMode);
    }

    @Override // android.view.ActionMode.Callback
    public final boolean onPrepareActionMode(ActionMode actionMode, Menu menu) {
        TextView textView = this.f1824b;
        Context context = textView.getContext();
        PackageManager packageManager = context.getPackageManager();
        if (!this.f1828f) {
            this.f1828f = true;
            try {
                Class<?> cls = Class.forName("com.android.internal.view.menu.MenuBuilder");
                this.f1825c = cls;
                this.f1826d = cls.getDeclaredMethod("removeItemAt", Integer.TYPE);
                this.f1827e = true;
            } catch (ClassNotFoundException | NoSuchMethodException unused) {
                this.f1825c = null;
                this.f1826d = null;
                this.f1827e = false;
            }
        }
        try {
            Method declaredMethod = (this.f1827e && this.f1825c.isInstance(menu)) ? this.f1826d : menu.getClass().getDeclaredMethod("removeItemAt", Integer.TYPE);
            for (int size = menu.size() - 1; size >= 0; size--) {
                MenuItem item = menu.getItem(size);
                if (item.getIntent() != null && "android.intent.action.PROCESS_TEXT".equals(item.getIntent().getAction())) {
                    declaredMethod.invoke(menu, Integer.valueOf(size));
                }
            }
            ArrayList arrayList = new ArrayList();
            if (context instanceof Activity) {
                for (ResolveInfo resolveInfo : packageManager.queryIntentActivities(new Intent().setAction("android.intent.action.PROCESS_TEXT").setType(a5.n.MIME_PLAINTEXT), 0)) {
                    if (!context.getPackageName().equals(resolveInfo.activityInfo.packageName)) {
                        ActivityInfo activityInfo = resolveInfo.activityInfo;
                        if (activityInfo.exported) {
                            String str = activityInfo.permission;
                            if (str != null && context.checkSelfPermission(str) != 0) {
                            }
                        }
                    }
                    arrayList.add(resolveInfo);
                }
            }
            for (int i7 = 0; i7 < arrayList.size(); i7++) {
                ResolveInfo resolveInfo2 = (ResolveInfo) arrayList.get(i7);
                MenuItem add = menu.add(0, 0, i7 + 100, resolveInfo2.loadLabel(packageManager));
                Intent putExtra = new Intent().setAction("android.intent.action.PROCESS_TEXT").setType(a5.n.MIME_PLAINTEXT).putExtra("android.intent.extra.PROCESS_TEXT_READONLY", !((textView instanceof Editable) && textView.onCheckIsTextEditor() && textView.isEnabled()));
                ActivityInfo activityInfo2 = resolveInfo2.activityInfo;
                add.setIntent(putExtra.setClassName(activityInfo2.packageName, activityInfo2.name)).setShowAsAction(1);
            }
        } catch (IllegalAccessException | NoSuchMethodException | InvocationTargetException unused2) {
        }
        return this.f1823a.onPrepareActionMode(actionMode, menu);
    }
}
