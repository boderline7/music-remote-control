package a1;

import android.text.method.KeyListener;
import android.text.method.NumberKeyListener;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputConnection;
import android.widget.EditText;
import androidx.appcompat.widget.k3;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import n1.z;
import y0.l;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final class a extends p3.f {

    /* renamed from: e, reason: collision with root package name */
    public final EditText f195e;

    /* renamed from: f, reason: collision with root package name */
    public final j f196f;

    public a(EditText editText) {
        super(10, 0);
        this.f195e = editText;
        j jVar = new j(editText);
        this.f196f = jVar;
        editText.addTextChangedListener(jVar);
        if (c.f202b == null) {
            synchronized (c.f201a) {
                try {
                    if (c.f202b == null) {
                        c cVar = new c();
                        try {
                            c.f203c = Class.forName("android.text.DynamicLayout$ChangeWatcher", false, c.class.getClassLoader());
                        } catch (Throwable unused) {
                        }
                        c.f202b = cVar;
                    }
                } finally {
                }
            }
        }
        editText.setEditableFactory(c.f202b);
    }

    @Override // p3.f
    public final InputConnection B(InputConnection inputConnection, EditorInfo editorInfo) {
        return inputConnection instanceof d ? inputConnection : new d(this.f195e, inputConnection, editorInfo);
    }

    @Override // p3.f
    public final void G(boolean z6) {
        j jVar = this.f196f;
        if (jVar.f219d != z6) {
            if (jVar.f218c != null) {
                l a7 = l.a();
                k3 k3Var = jVar.f218c;
                a7.getClass();
                z.v(k3Var, "initCallback cannot be null");
                ReentrantReadWriteLock reentrantReadWriteLock = a7.f9978a;
                reentrantReadWriteLock.writeLock().lock();
                try {
                    a7.f9979b.remove(k3Var);
                } finally {
                    reentrantReadWriteLock.writeLock().unlock();
                }
            }
            jVar.f219d = z6;
            if (z6) {
                j.a(jVar.f216a, l.a().b());
            }
        }
    }

    @Override // p3.f
    public final KeyListener s(KeyListener keyListener) {
        if (keyListener instanceof g) {
            return keyListener;
        }
        if (keyListener == null) {
            return null;
        }
        return keyListener instanceof NumberKeyListener ? keyListener : new g(keyListener);
    }
}
