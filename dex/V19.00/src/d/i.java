package d;

import android.util.SparseArray;
import android.view.View;
import com.google.android.material.datepicker.x;
import com.google.android.material.textfield.l;
import java.util.Arrays;
import p0.b0;
import p0.l2;
import q2.m;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final class i implements b0 {

    /* renamed from: a, reason: collision with root package name */
    public final int f6257a;

    /* renamed from: b, reason: collision with root package name */
    public final int f6258b;

    /* renamed from: c, reason: collision with root package name */
    public final Object f6259c;

    /* renamed from: d, reason: collision with root package name */
    public final Object f6260d;

    public i(x xVar, int i7, View view, int i8) {
        this.f6260d = xVar;
        this.f6257a = i7;
        this.f6259c = view;
        this.f6258b = i8;
    }

    public i(l lVar, androidx.appcompat.app.g gVar) {
        this.f6259c = new SparseArray();
        this.f6260d = lVar;
        this.f6257a = gVar.N(m.TextInputLayout_endIconDrawable, 0);
        this.f6258b = gVar.N(m.TextInputLayout_passwordToggleDrawable, 0);
    }

    public i(String str, int i7, int i8) {
        this.f6259c = str;
        this.f6258b = i7;
        this.f6257a = i8;
        byte[] bArr = new byte[i7 * i8];
        this.f6260d = bArr;
        Arrays.fill(bArr, (byte) -1);
    }

    public final void a(int i7, int i8, int i9, int i10) {
        if (i7 < 0) {
            int i11 = this.f6257a;
            i7 += i11;
            i8 += 4 - ((i11 + 4) % 8);
        }
        if (i8 < 0) {
            int i12 = this.f6258b;
            i8 += i12;
            i7 += 4 - ((i12 + 4) % 8);
        }
        ((byte[]) this.f6260d)[(i7 * this.f6258b) + i8] = (byte) ((((CharSequence) this.f6259c).charAt(i9) & (1 << (8 - i10))) == 0 ? 0 : 1);
    }

    public final boolean b(int i7, int i8) {
        return ((byte[]) this.f6260d)[(i8 * this.f6258b) + i7] < 0;
    }

    public final void c(int i7, int i8, int i9) {
        int i10 = i7 - 2;
        int i11 = i8 - 2;
        a(i10, i11, i9, 1);
        int i12 = i8 - 1;
        a(i10, i12, i9, 2);
        int i13 = i7 - 1;
        a(i13, i11, i9, 3);
        a(i13, i12, i9, 4);
        a(i13, i8, i9, 5);
        a(i7, i11, i9, 6);
        a(i7, i12, i9, 7);
        a(i7, i8, i9, 8);
    }

    @Override // p0.b0
    public final l2 e(View view, l2 l2Var) {
        int i7 = l2Var.f8161a.f(7).f6774b;
        int i8 = this.f6257a;
        Object obj = this.f6259c;
        if (i8 >= 0) {
            View view2 = (View) obj;
            view2.getLayoutParams().height = this.f6257a + i7;
            view2.setLayoutParams(view2.getLayoutParams());
        }
        View view3 = (View) obj;
        view3.setPadding(view3.getPaddingLeft(), this.f6258b + i7, view3.getPaddingRight(), view3.getPaddingBottom());
        return l2Var;
    }
}
