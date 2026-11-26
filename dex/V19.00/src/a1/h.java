package a1;

import android.text.InputFilter;
import android.text.method.PasswordTransformationMethod;
import android.text.method.TransformationMethod;
import android.util.SparseArray;
import android.widget.TextView;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final class h extends p3.f {

    /* renamed from: e, reason: collision with root package name */
    public final TextView f212e;

    /* renamed from: f, reason: collision with root package name */
    public final f f213f;

    /* renamed from: g, reason: collision with root package name */
    public boolean f214g;

    public h(TextView textView) {
        super(13, 0);
        this.f212e = textView;
        this.f214g = true;
        this.f213f = new f(textView);
    }

    @Override // p3.f
    public final void D(boolean z6) {
        if (z6) {
            TextView textView = this.f212e;
            textView.setTransformationMethod(L(textView.getTransformationMethod()));
        }
    }

    @Override // p3.f
    public final void G(boolean z6) {
        this.f214g = z6;
        TextView textView = this.f212e;
        textView.setTransformationMethod(L(textView.getTransformationMethod()));
        textView.setFilters(r(textView.getFilters()));
    }

    @Override // p3.f
    public final TransformationMethod L(TransformationMethod transformationMethod) {
        return this.f214g ? ((transformationMethod instanceof k) || (transformationMethod instanceof PasswordTransformationMethod)) ? transformationMethod : new k(transformationMethod) : transformationMethod instanceof k ? ((k) transformationMethod).f220a : transformationMethod;
    }

    @Override // p3.f
    public final InputFilter[] r(InputFilter[] inputFilterArr) {
        if (!this.f214g) {
            SparseArray sparseArray = new SparseArray(1);
            for (int i7 = 0; i7 < inputFilterArr.length; i7++) {
                InputFilter inputFilter = inputFilterArr[i7];
                if (inputFilter instanceof f) {
                    sparseArray.put(i7, inputFilter);
                }
            }
            if (sparseArray.size() == 0) {
                return inputFilterArr;
            }
            int length = inputFilterArr.length;
            InputFilter[] inputFilterArr2 = new InputFilter[inputFilterArr.length - sparseArray.size()];
            int i8 = 0;
            for (int i9 = 0; i9 < length; i9++) {
                if (sparseArray.indexOfKey(i9) < 0) {
                    inputFilterArr2[i8] = inputFilterArr[i9];
                    i8++;
                }
            }
            return inputFilterArr2;
        }
        int length2 = inputFilterArr.length;
        int i10 = 0;
        while (true) {
            f fVar = this.f213f;
            if (i10 >= length2) {
                InputFilter[] inputFilterArr3 = new InputFilter[inputFilterArr.length + 1];
                System.arraycopy(inputFilterArr, 0, inputFilterArr3, 0, length2);
                inputFilterArr3[length2] = fVar;
                return inputFilterArr3;
            }
            if (inputFilterArr[i10] == fVar) {
                return inputFilterArr;
            }
            i10++;
        }
    }

    @Override // p3.f
    public final boolean z() {
        return this.f214g;
    }
}
