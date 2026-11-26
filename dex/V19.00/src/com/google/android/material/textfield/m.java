package com.google.android.material.textfield;

import android.content.Context;
import android.view.View;
import android.view.accessibility.AccessibilityEvent;
import android.widget.EditText;
import com.google.android.material.internal.CheckableImageButton;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_2_1633180.dex */
public abstract class m {

    /* renamed from: a, reason: collision with root package name */
    public final TextInputLayout f4380a;

    /* renamed from: b, reason: collision with root package name */
    public final l f4381b;

    /* renamed from: c, reason: collision with root package name */
    public final Context f4382c;

    /* renamed from: d, reason: collision with root package name */
    public final CheckableImageButton f4383d;

    public m(l lVar) {
        this.f4380a = lVar.f4358a;
        this.f4381b = lVar;
        this.f4382c = lVar.getContext();
        this.f4383d = lVar.f4364g;
    }

    public void a() {
    }

    public void b() {
    }

    public int c() {
        return 0;
    }

    public int d() {
        return 0;
    }

    public View.OnFocusChangeListener e() {
        return null;
    }

    public View.OnClickListener f() {
        return null;
    }

    public View.OnFocusChangeListener g() {
        return null;
    }

    public q0.d h() {
        return null;
    }

    public boolean i(int i7) {
        return true;
    }

    public boolean j() {
        return false;
    }

    public boolean k() {
        return this instanceof i;
    }

    public boolean l() {
        return false;
    }

    public void m(EditText editText) {
    }

    public void n(q0.i iVar) {
    }

    public void o(AccessibilityEvent accessibilityEvent) {
    }

    public void p(boolean z6) {
    }

    public final void q() {
        this.f4381b.f(false);
    }

    public abstract void r();

    public void s() {
    }
}
