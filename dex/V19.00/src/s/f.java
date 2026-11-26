package s;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.button.MaterialButtonToggleGroup;
import com.navi.tool.amapflying.util.TmcBarFloatingView;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Comparator;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final class f implements Comparator {

    /* renamed from: a, reason: collision with root package name */
    public final /* synthetic */ int f8630a;

    /* renamed from: b, reason: collision with root package name */
    public final /* synthetic */ Object f8631b;

    public /* synthetic */ f(int i7, Object obj) {
        this.f8630a = i7;
        this.f8631b = obj;
    }

    @Override // java.util.Comparator
    public final int compare(Object obj, Object obj2) {
        int i7 = this.f8630a;
        Object obj3 = this.f8631b;
        switch (i7) {
            case 0:
                return ((i) obj).f8638b - ((i) obj2).f8638b;
            case 1:
                a.a.u(obj);
                a.a.u(obj2);
                throw null;
            case 2:
                return ((int[]) obj)[0] - ((int[]) obj2)[0];
            case 3:
                Method method = (Method) obj;
                Method method2 = (Method) obj2;
                return (method.getDeclaringClass() + method.getName() + Arrays.toString(method.getParameterTypes()) + method.getReturnType()).compareTo(method2.getDeclaringClass() + method2.getName() + Arrays.toString(method2.getParameterTypes()) + method2.getReturnType());
            case 4:
                MaterialButton materialButton = (MaterialButton) obj;
                MaterialButton materialButton2 = (MaterialButton) obj2;
                int compareTo = Boolean.valueOf(materialButton.isChecked()).compareTo(Boolean.valueOf(materialButton2.isChecked()));
                if (compareTo != 0) {
                    return compareTo;
                }
                int compareTo2 = Boolean.valueOf(materialButton.isPressed()).compareTo(Boolean.valueOf(materialButton2.isPressed()));
                if (compareTo2 != 0) {
                    return compareTo2;
                }
                MaterialButtonToggleGroup materialButtonToggleGroup = (MaterialButtonToggleGroup) obj3;
                return Integer.valueOf(materialButtonToggleGroup.indexOfChild(materialButton)).compareTo(Integer.valueOf(materialButtonToggleGroup.indexOfChild(materialButton2)));
            case 5:
                return ((x3.e) obj).f9625d - ((x3.e) obj2).f9625d;
            default:
                h4.f fVar = (h4.f) obj;
                h4.f fVar2 = (h4.f) obj2;
                return ((TmcBarFloatingView) obj3).f4619g == 0 ? Integer.compare(fVar2.f6742a, fVar.f6742a) : Integer.compare(fVar.f6742a, fVar2.f6742a);
        }
    }
}
