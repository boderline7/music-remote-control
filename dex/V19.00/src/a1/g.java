package a1;

import android.text.Editable;
import android.text.method.KeyListener;
import android.view.KeyEvent;
import android.view.View;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final class g implements KeyListener {

    /* renamed from: a, reason: collision with root package name */
    public final KeyListener f210a;

    /* renamed from: b, reason: collision with root package name */
    public final p3.f f211b;

    public g(KeyListener keyListener) {
        p3.f fVar = new p3.f(12, 0);
        this.f210a = keyListener;
        this.f211b = fVar;
    }

    @Override // android.text.method.KeyListener
    public final void clearMetaKeyState(View view, Editable editable, int i7) {
        this.f210a.clearMetaKeyState(view, editable, i7);
    }

    @Override // android.text.method.KeyListener
    public final int getInputType() {
        return this.f210a.getInputType();
    }

    /* JADX WARN: Removed duplicated region for block: B:12:0x002a  */
    /* JADX WARN: Removed duplicated region for block: B:14:? A[RETURN, SYNTHETIC] */
    @Override // android.text.method.KeyListener
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final boolean onKeyDown(android.view.View r4, android.text.Editable r5, int r6, android.view.KeyEvent r7) {
        /*
            r3 = this;
            p3.f r0 = r3.f211b
            r0.getClass()
            java.lang.String r0 = y0.l.EDITOR_INFO_METAVERSION_KEY
            r0 = 67
            r1 = 1
            r2 = 0
            if (r6 == r0) goto L17
            r0 = 112(0x70, float:1.57E-43)
            if (r6 == r0) goto L12
            goto L21
        L12:
            boolean r0 = y0.s.d(r5, r7, r1)
            goto L1b
        L17:
            boolean r0 = y0.s.d(r5, r7, r2)
        L1b:
            if (r0 == 0) goto L21
            android.text.method.MetaKeyKeyListener.adjustMetaAfterKeypress(r5)
            goto L2b
        L21:
            android.text.method.KeyListener r0 = r3.f210a
            boolean r4 = r0.onKeyDown(r4, r5, r6, r7)
            if (r4 == 0) goto L2a
            goto L2b
        L2a:
            r1 = r2
        L2b:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: a1.g.onKeyDown(android.view.View, android.text.Editable, int, android.view.KeyEvent):boolean");
    }

    @Override // android.text.method.KeyListener
    public final boolean onKeyOther(View view, Editable editable, KeyEvent keyEvent) {
        return this.f210a.onKeyOther(view, editable, keyEvent);
    }

    @Override // android.text.method.KeyListener
    public final boolean onKeyUp(View view, Editable editable, int i7, KeyEvent keyEvent) {
        return this.f210a.onKeyUp(view, editable, i7, keyEvent);
    }
}
