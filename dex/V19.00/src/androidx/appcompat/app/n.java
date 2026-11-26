package androidx.appcompat.app;

import android.R;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.drawable.Drawable;
import android.view.ContextThemeWrapper;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.TextView;
import com.google.android.material.bottomsheet.BottomSheetBehavior;
import java.lang.reflect.Array;
import java.nio.charset.Charset;
import java.nio.charset.CharsetEncoder;
import java.nio.file.attribute.FileTime;
import java.util.ArrayList;
import java.util.List;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_2_1633180.dex */
public class n implements q0.w, y3.e {

    /* renamed from: a, reason: collision with root package name */
    public final /* synthetic */ int f829a;

    /* renamed from: b, reason: collision with root package name */
    public int f830b;

    /* renamed from: c, reason: collision with root package name */
    public final Object f831c;

    public n(int i7, int i8) {
        this.f829a = i8;
        if (i8 != 5) {
            this.f831c = new e2.f(i7);
        } else {
            this.f831c = new byte[i7];
            this.f830b = 0;
        }
    }

    public /* synthetic */ n(int i7, int i8, Object obj) {
        this.f829a = i8;
        this.f830b = i7;
        this.f831c = obj;
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public /* synthetic */ n(int i7, FileTime fileTime) {
        this(i7, 2, fileTime);
        this.f829a = 2;
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public n(Context context) {
        this(context, o.f(context, 0));
        this.f829a = 0;
    }

    public n(Context context, int i7) {
        this.f829a = 0;
        this.f831c = new j(new ContextThemeWrapper(context, o.f(context, i7)));
        this.f830b = i7;
    }

    public n(BottomSheetBehavior bottomSheetBehavior, int i7) {
        this.f829a = 3;
        this.f831c = bottomSheetBehavior;
        this.f830b = i7;
    }

    public n(String str, Charset charset, int i7) {
        int i8;
        int i9;
        int i10;
        this.f829a = 4;
        this.f830b = i7;
        y3.d dVar = new y3.d(str, charset, i7);
        CharsetEncoder[] charsetEncoderArr = dVar.f10184a;
        int i11 = 0;
        if (charsetEncoderArr.length != 1) {
            int length = str.length();
            y3.f[][] fVarArr = (y3.f[][]) Array.newInstance((Class<?>) y3.f.class, length + 1, charsetEncoderArr.length);
            c(str, dVar, fVarArr, 0, null, i7);
            int i12 = 1;
            while (i12 <= length) {
                int i13 = 0;
                while (i13 < charsetEncoderArr.length) {
                    y3.f fVar = fVarArr[i12][i13];
                    if (fVar == null || i12 >= length) {
                        i9 = i13;
                        i10 = i12;
                    } else {
                        i9 = i13;
                        i10 = i12;
                        c(str, dVar, fVarArr, i12, fVar, i7);
                    }
                    i13 = i9 + 1;
                    i12 = i10;
                }
                int i14 = i12;
                for (int i15 = 0; i15 < charsetEncoderArr.length; i15++) {
                    fVarArr[i14 - 1][i15] = null;
                }
                i12 = i14 + 1;
            }
            int i16 = -1;
            int i17 = androidx.appcompat.widget.u.MAX_ACTIVITY_COUNT_UNLIMITED;
            for (int i18 = 0; i18 < charsetEncoderArr.length; i18++) {
                y3.f fVar2 = fVarArr[length][i18];
                if (fVar2 != null && (i8 = fVar2.f10189d) < i17) {
                    i16 = i18;
                    i17 = i8;
                }
            }
            if (i16 < 0) {
                throw new IllegalStateException(a.a.h("Failed to encode \"", str, "\""));
            }
            ArrayList arrayList = new ArrayList();
            y3.f fVar3 = fVarArr[length][i16];
            while (fVar3 != null) {
                int i19 = fVar3.f10187b;
                char c7 = fVar3.f10186a;
                if (c7 == 1000) {
                    arrayList.add(0, 1000);
                } else {
                    byte[] b7 = dVar.b(c7, i19);
                    for (int length2 = b7.length - 1; length2 >= 0; length2--) {
                        arrayList.add(0, Integer.valueOf(b7[length2] & 255));
                    }
                }
                fVar3 = fVar3.f10188c;
                if ((fVar3 == null ? 0 : fVar3.f10187b) != i19) {
                    arrayList.add(0, Integer.valueOf(y3.c.a(charsetEncoderArr[i19].charset()).f10181a[0] + 256));
                }
            }
            int size = arrayList.size();
            int[] iArr = new int[size];
            while (i11 < size) {
                iArr[i11] = ((Integer) arrayList.get(i11)).intValue();
                i11++;
            }
            this.f831c = iArr;
            return;
        }
        this.f831c = new int[str.length()];
        while (true) {
            int[] iArr2 = (int[]) this.f831c;
            if (i11 >= iArr2.length) {
                return;
            }
            char charAt = str.charAt(i11);
            if (charAt == i7) {
                charAt = 1000;
            }
            iArr2[i11] = charAt;
            i11++;
        }
    }

    public n(ArrayList arrayList) {
        this.f829a = 7;
        this.f831c = arrayList;
    }

    public static void c(String str, y3.d dVar, y3.f[][] fVarArr, int i7, y3.f fVar, int i8) {
        char charAt = str.charAt(i7);
        int length = dVar.f10184a.length;
        int i9 = dVar.f10185b;
        if (i9 < 0 || !(charAt == i8 || dVar.a(charAt, i9))) {
            i9 = 0;
        } else {
            length = i9 + 1;
        }
        int i10 = length;
        for (int i11 = i9; i11 < i10; i11++) {
            if (charAt == i8 || dVar.a(charAt, i11)) {
                y3.f fVar2 = new y3.f(charAt, dVar, i11, fVar, i8);
                y3.f[] fVarArr2 = fVarArr[i7 + 1];
                y3.f fVar3 = fVarArr2[i11];
                if (fVar3 != null) {
                    if (fVar3.f10189d <= fVar2.f10189d) {
                    }
                }
                fVarArr2[i11] = fVar2;
            }
        }
    }

    @Override // y3.e
    public final boolean a(int i7) {
        if (i7 >= 0) {
            Object obj = this.f831c;
            if (i7 < ((int[]) obj).length) {
                return ((int[]) obj)[i7] > 255 && ((int[]) obj)[i7] <= 999;
            }
        }
        throw new IndexOutOfBoundsException(a.a.d(s5.h0.FRAGMENT_ENCODE_SET, i7));
    }

    @Override // y3.e
    public final int b(int i7) {
        if (i7 >= 0) {
            if (i7 < ((int[]) this.f831c).length) {
                if (a(i7)) {
                    return ((int[]) r0)[i7] - 256;
                }
                throw new IllegalArgumentException(a.a.e("value at ", i7, " is not an ECI but a character"));
            }
        }
        throw new IndexOutOfBoundsException(a.a.d(s5.h0.FRAGMENT_ENCODE_SET, i7));
    }

    @Override // y3.e
    public final char charAt(int i7) {
        if (i7 >= 0) {
            Object obj = this.f831c;
            if (i7 < ((int[]) obj).length) {
                if (a(i7)) {
                    throw new IllegalArgumentException(a.a.e("value at ", i7, " is not a character but an ECI"));
                }
                return (char) (i(i7) ? this.f830b : ((int[]) obj)[i7]);
            }
        }
        throw new IndexOutOfBoundsException(a.a.d(s5.h0.FRAGMENT_ENCODE_SET, i7));
    }

    public final void d(int i7, int i8) {
        e2.f fVar = (e2.f) this.f831c;
        int i9 = fVar.f6362c;
        if (i7 >= i9) {
            for (int i10 = i7 - i9; i10 >= 0; i10--) {
                fVar.f(-1);
            }
        }
        fVar.h(i7, i8);
        int i11 = i8 + 1;
        if (this.f830b < i11) {
            this.f830b = i11;
        }
    }

    public o e() {
        j jVar = (j) this.f831c;
        o oVar = new o(jVar.f745a, this.f830b);
        View view = jVar.f749e;
        m mVar = oVar.f840f;
        int i7 = 0;
        if (view != null) {
            mVar.C = view;
        } else {
            CharSequence charSequence = jVar.f748d;
            if (charSequence != null) {
                mVar.f803e = charSequence;
                TextView textView = mVar.A;
                if (textView != null) {
                    textView.setText(charSequence);
                }
            }
            Drawable drawable = jVar.f747c;
            if (drawable != null) {
                mVar.f823y = drawable;
                mVar.f822x = 0;
                ImageView imageView = mVar.f824z;
                if (imageView != null) {
                    imageView.setVisibility(0);
                    mVar.f824z.setImageDrawable(drawable);
                }
            }
        }
        CharSequence charSequence2 = jVar.f750f;
        if (charSequence2 != null) {
            mVar.f804f = charSequence2;
            TextView textView2 = mVar.B;
            if (textView2 != null) {
                textView2.setText(charSequence2);
            }
        }
        CharSequence charSequence3 = jVar.f751g;
        if (charSequence3 != null) {
            mVar.d(-1, charSequence3, jVar.f752h);
        }
        CharSequence charSequence4 = jVar.f753i;
        if (charSequence4 != null) {
            mVar.d(-2, charSequence4, jVar.f754j);
        }
        CharSequence charSequence5 = jVar.f755k;
        if (charSequence5 != null) {
            mVar.d(-3, charSequence5, jVar.f756l);
        }
        if (jVar.f760p != null || jVar.f761q != null) {
            AlertController$RecycleListView alertController$RecycleListView = (AlertController$RecycleListView) jVar.f746b.inflate(mVar.G, (ViewGroup) null);
            int i8 = jVar.f764t ? mVar.H : mVar.I;
            ListAdapter listAdapter = jVar.f761q;
            if (listAdapter == null) {
                listAdapter = new l(jVar.f745a, i8, R.id.text1, jVar.f760p);
            }
            mVar.D = listAdapter;
            mVar.E = jVar.f765u;
            if (jVar.f762r != null) {
                alertController$RecycleListView.setOnItemClickListener(new i(i7, jVar, mVar));
            }
            if (jVar.f764t) {
                alertController$RecycleListView.setChoiceMode(1);
            }
            mVar.f805g = alertController$RecycleListView;
        }
        View view2 = jVar.f763s;
        if (view2 != null) {
            mVar.f806h = view2;
            mVar.f807i = 0;
            mVar.f808j = false;
        }
        oVar.setCancelable(jVar.f757m);
        if (jVar.f757m) {
            oVar.setCanceledOnTouchOutside(true);
        }
        jVar.getClass();
        oVar.setOnCancelListener(null);
        oVar.setOnDismissListener(jVar.f758n);
        DialogInterface.OnKeyListener onKeyListener = jVar.f759o;
        if (onKeyListener != null) {
            oVar.setOnKeyListener(onKeyListener);
        }
        return oVar;
    }

    public final boolean f() {
        return this.f830b < ((List) this.f831c).size();
    }

    @Override // q0.w
    public final boolean g(View view) {
        ((BottomSheetBehavior) this.f831c).I(this.f830b);
        return true;
    }

    public final boolean h(int i7, int i8) {
        if ((i7 + i8) - 1 >= ((int[]) this.f831c).length) {
            return false;
        }
        for (int i9 = 0; i9 < i8; i9++) {
            if (a(i7 + i9)) {
                return false;
            }
        }
        return true;
    }

    public final boolean i(int i7) {
        if (i7 >= 0) {
            Object obj = this.f831c;
            if (i7 < ((int[]) obj).length) {
                return ((int[]) obj)[i7] == 1000;
            }
        }
        throw new IndexOutOfBoundsException(a.a.d(s5.h0.FRAGMENT_ENCODE_SET, i7));
    }

    public final a2.m j(a2.m mVar) {
        int i7;
        if (mVar == null) {
            return null;
        }
        try {
            i7 = ((e2.f) this.f831c).g(mVar.f237a);
        } catch (IndexOutOfBoundsException unused) {
            i7 = -1;
        }
        if (i7 >= 0) {
            return mVar.f237a == i7 ? mVar : a2.m.f(i7, mVar.f238b);
        }
        throw new RuntimeException("no mapping specified for register");
    }

    public n k() {
        ((j) this.f831c).f757m = false;
        return this;
    }

    public n l(CharSequence[] charSequenceArr, DialogInterface.OnClickListener onClickListener) {
        j jVar = (j) this.f831c;
        jVar.f760p = charSequenceArr;
        jVar.f762r = onClickListener;
        return this;
    }

    @Override // y3.e
    public final int length() {
        return ((int[]) this.f831c).length;
    }

    public n m(CharSequence charSequence) {
        ((j) this.f831c).f750f = charSequence;
        return this;
    }

    public n n(int i7) {
        j jVar = (j) this.f831c;
        jVar.f753i = jVar.f745a.getText(i7);
        jVar.f754j = null;
        return this;
    }

    public n o(CharSequence charSequence, DialogInterface.OnClickListener onClickListener) {
        j jVar = (j) this.f831c;
        jVar.f753i = charSequence;
        jVar.f754j = onClickListener;
        return this;
    }

    public n p(CharSequence charSequence, DialogInterface.OnClickListener onClickListener) {
        j jVar = (j) this.f831c;
        jVar.f755k = charSequence;
        jVar.f756l = onClickListener;
        return this;
    }

    public n q(int i7, DialogInterface.OnClickListener onClickListener) {
        Object obj = this.f831c;
        ((j) obj).f751g = ((j) obj).f745a.getText(i7);
        ((j) obj).f752h = onClickListener;
        return this;
    }

    public n r(CharSequence charSequence, DialogInterface.OnClickListener onClickListener) {
        j jVar = (j) this.f831c;
        jVar.f751g = charSequence;
        jVar.f752h = onClickListener;
        return this;
    }

    public n s(int i7) {
        Object obj = this.f831c;
        ((j) obj).f748d = ((j) obj).f745a.getText(i7);
        return this;
    }

    @Override // y3.e
    public final CharSequence subSequence(int i7, int i8) {
        if (i7 < 0 || i7 > i8 || i8 > ((int[]) this.f831c).length) {
            throw new IndexOutOfBoundsException(a.a.d(s5.h0.FRAGMENT_ENCODE_SET, i7));
        }
        StringBuilder sb = new StringBuilder();
        while (i7 < i8) {
            if (a(i7)) {
                throw new IllegalArgumentException(a.a.e("value at ", i7, " is not a character but an ECI"));
            }
            sb.append(charAt(i7));
            i7++;
        }
        return sb;
    }

    public n t(CharSequence charSequence) {
        ((j) this.f831c).f748d = charSequence;
        return this;
    }

    public final String toString() {
        switch (this.f829a) {
            case 4:
                StringBuilder sb = new StringBuilder();
                for (int i7 = 0; i7 < ((int[]) this.f831c).length; i7++) {
                    if (i7 > 0) {
                        sb.append(", ");
                    }
                    if (a(i7)) {
                        sb.append("ECI(");
                        sb.append(b(i7));
                        sb.append(')');
                    } else if (charAt(i7) < 128) {
                        sb.append('\'');
                        sb.append(charAt(i7));
                        sb.append('\'');
                    } else {
                        sb.append((int) charAt(i7));
                    }
                }
                return sb.toString();
            default:
                return super.toString();
        }
    }

    public n u(View view) {
        j jVar = (j) this.f831c;
        jVar.f763s = view;
        jVar.getClass();
        jVar.getClass();
        return this;
    }

    public final o v() {
        o e7 = e();
        e7.show();
        return e7;
    }
}
