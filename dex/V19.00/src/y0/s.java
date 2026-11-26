package y0;

import android.text.Editable;
import android.text.Selection;
import android.text.TextPaint;
import android.view.KeyEvent;
import java.util.LinkedList;
import javax.crypto.Cipher;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final class s implements j2.a {

    /* renamed from: a, reason: collision with root package name */
    public boolean f10007a;

    /* renamed from: b, reason: collision with root package name */
    public Object f10008b;

    /* renamed from: c, reason: collision with root package name */
    public final Object f10009c;

    /* renamed from: d, reason: collision with root package name */
    public final Object f10010d;

    /* renamed from: e, reason: collision with root package name */
    public final Object f10011e;

    public s(j.h hVar, p3.f fVar, e eVar) {
        this.f10008b = fVar;
        this.f10009c = hVar;
        this.f10010d = eVar;
        this.f10007a = false;
        this.f10011e = null;
    }

    public s(j2.a aVar) {
        this.f10008b = null;
        this.f10007a = false;
        this.f10010d = new LinkedList();
        this.f10011e = new byte[1];
        this.f10009c = aVar;
    }

    public static boolean d(Editable editable, KeyEvent keyEvent, boolean z6) {
        z[] zVarArr;
        if (!KeyEvent.metaStateHasNoModifiers(keyEvent.getMetaState())) {
            return false;
        }
        int selectionStart = Selection.getSelectionStart(editable);
        int selectionEnd = Selection.getSelectionEnd(editable);
        if (selectionStart != -1 && selectionEnd != -1 && selectionStart == selectionEnd && (zVarArr = (z[]) editable.getSpans(selectionStart, selectionEnd, z.class)) != null && zVarArr.length > 0) {
            for (z zVar : zVarArr) {
                int spanStart = editable.getSpanStart(zVar);
                int spanEnd = editable.getSpanEnd(zVar);
                if ((z6 && spanStart == selectionStart) || ((!z6 && spanEnd == selectionStart) || (selectionStart > spanStart && selectionStart < spanEnd))) {
                    editable.delete(spanStart, spanEnd);
                    return true;
                }
            }
        }
        return false;
    }

    @Override // j2.a
    public final void a(long j7) {
        ((j2.a) this.f10009c).a(j7);
    }

    @Override // j2.a
    public final int b(byte[] bArr, int i7) {
        boolean z6 = this.f10007a;
        Object obj = this.f10009c;
        if (!z6) {
            return ((j2.a) obj).b(bArr, i7);
        }
        LinkedList linkedList = (LinkedList) this.f10010d;
        int size = i7 - linkedList.size();
        int i8 = size + (((~size) + 1) & 15);
        byte[] bArr2 = new byte[i8];
        if (i8 > 0) {
            ((j2.a) obj).b(bArr2, i8);
            for (byte b7 : ((Cipher) this.f10008b).update(bArr2)) {
                linkedList.add(Byte.valueOf(b7));
            }
        }
        int i9 = 0;
        for (int i10 = 0; i10 < i7 && !linkedList.isEmpty(); i10++) {
            bArr[i10] = ((Byte) linkedList.poll()).byteValue();
            i9++;
        }
        return i9;
    }

    @Override // j2.a
    public final long c() {
        return ((j2.a) this.f10009c).c();
    }

    @Override // j2.a
    public final void close() {
        ((j2.a) this.f10009c).close();
    }

    public final boolean e(CharSequence charSequence, int i7, int i8, q qVar) {
        if (qVar.f9998c == 0) {
            i iVar = (i) this.f10010d;
            z0.a c7 = qVar.c();
            int a7 = c7.a(8);
            if (a7 != 0) {
                c7.f10245b.getShort(a7 + c7.f10244a);
            }
            e eVar = (e) iVar;
            eVar.getClass();
            ThreadLocal threadLocal = e.f9968b;
            if (threadLocal.get() == null) {
                threadLocal.set(new StringBuilder());
            }
            StringBuilder sb = (StringBuilder) threadLocal.get();
            sb.setLength(0);
            while (i7 < i8) {
                sb.append(charSequence.charAt(i7));
                i7++;
            }
            TextPaint textPaint = eVar.f9969a;
            String sb2 = sb.toString();
            int i9 = i0.e.f6777a;
            qVar.f9998c = i0.d.a(textPaint, sb2) ? 2 : 1;
        }
        return qVar.f9998c == 2;
    }
}
