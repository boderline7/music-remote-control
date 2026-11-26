package androidx.appcompat.widget;

import android.content.res.Resources;
import android.graphics.Rect;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import androidx.appcompat.widget.SearchView;
import com.google.android.material.navigation.NavigationBarItemView;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_2_1633180.dex */
public final class u2 implements View.OnLayoutChangeListener {

    /* renamed from: a, reason: collision with root package name */
    public final /* synthetic */ int f1421a;

    /* renamed from: b, reason: collision with root package name */
    public final /* synthetic */ Object f1422b;

    public /* synthetic */ u2(int i7, Object obj) {
        this.f1421a = i7;
        this.f1422b = obj;
    }

    @Override // android.view.View.OnLayoutChangeListener
    public final void onLayoutChange(View view, int i7, int i8, int i9, int i10, int i11, int i12, int i13, int i14) {
        t2.a aVar;
        int i15 = this.f1421a;
        Object obj = this.f1422b;
        switch (i15) {
            case 0:
                SearchView searchView = (SearchView) obj;
                View view2 = searchView.f1118x;
                if (view2.getWidth() > 1) {
                    Resources resources = searchView.getContext().getResources();
                    int paddingLeft = searchView.f1112r.getPaddingLeft();
                    Rect rect = new Rect();
                    boolean a7 = a4.a(searchView);
                    int dimensionPixelSize = searchView.N ? resources.getDimensionPixelSize(f.d.abc_dropdownitem_icon_width) + resources.getDimensionPixelSize(f.d.abc_dropdownitem_text_padding_left) : 0;
                    SearchView.SearchAutoComplete searchAutoComplete = searchView.f1110p;
                    searchAutoComplete.getDropDownBackground().getPadding(rect);
                    searchAutoComplete.setDropDownHorizontalOffset(a7 ? -rect.left : paddingLeft - (rect.left + dimensionPixelSize));
                    searchAutoComplete.setDropDownWidth((((view2.getWidth() + rect.left) + rect.right) + dimensionPixelSize) - paddingLeft);
                    break;
                }
                break;
            case 1:
                NavigationBarItemView navigationBarItemView = (NavigationBarItemView) obj;
                if (navigationBarItemView.f3944n.getVisibility() == 0 && (aVar = navigationBarItemView.F) != null) {
                    boolean z6 = t2.c.USE_COMPAT_PARENT;
                    ImageView imageView = navigationBarItemView.f3944n;
                    FrameLayout frameLayout = z6 ? (FrameLayout) imageView.getParent() : null;
                    Rect rect2 = new Rect();
                    imageView.getDrawingRect(rect2);
                    aVar.setBounds(rect2);
                    aVar.i(imageView, frameLayout);
                    break;
                }
                break;
            default:
                u3.a aVar2 = (u3.a) obj;
                int i16 = u3.a.O;
                aVar2.getClass();
                int[] iArr = new int[2];
                view.getLocationOnScreen(iArr);
                aVar2.J = iArr[0];
                view.getWindowVisibleDisplayFrame(aVar2.D);
                break;
        }
    }
}
