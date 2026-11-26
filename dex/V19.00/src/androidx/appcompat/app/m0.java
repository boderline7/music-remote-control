package androidx.appcompat.app;

import android.content.Context;
import android.content.ContextWrapper;
import android.view.View;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_2_1633180.dex */
public final class m0 implements View.OnClickListener {

    /* renamed from: a, reason: collision with root package name */
    public final View f825a;

    /* renamed from: b, reason: collision with root package name */
    public final String f826b;

    /* renamed from: c, reason: collision with root package name */
    public Method f827c;

    /* renamed from: d, reason: collision with root package name */
    public Context f828d;

    public m0(View view, String str) {
        this.f825a = view;
        this.f826b = str;
    }

    @Override // android.view.View.OnClickListener
    public final void onClick(View view) {
        String str;
        Method method;
        if (this.f827c == null) {
            View view2 = this.f825a;
            Context context = view2.getContext();
            while (true) {
                String str2 = this.f826b;
                if (context == null) {
                    int id = view2.getId();
                    if (id == -1) {
                        str = s5.h0.FRAGMENT_ENCODE_SET;
                    } else {
                        str = " with id '" + view2.getContext().getResources().getResourceEntryName(id) + "'";
                    }
                    throw new IllegalStateException("Could not find method " + str2 + "(View) in a parent or ancestor Context for android:onClick attribute defined on view " + view2.getClass() + str);
                }
                try {
                    if (!context.isRestricted() && (method = context.getClass().getMethod(str2, View.class)) != null) {
                        this.f827c = method;
                        this.f828d = context;
                    }
                } catch (NoSuchMethodException unused) {
                }
                context = context instanceof ContextWrapper ? ((ContextWrapper) context).getBaseContext() : null;
            }
        }
        try {
            this.f827c.invoke(this.f828d, view);
        } catch (IllegalAccessException e7) {
            throw new IllegalStateException("Could not execute non-public method for android:onClick", e7);
        } catch (InvocationTargetException e8) {
            throw new IllegalStateException("Could not execute method for android:onClick", e8);
        }
    }
}
