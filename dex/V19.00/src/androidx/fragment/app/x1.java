package androidx.fragment.app;

import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import com.yalantis.ucrop.view.CropImageView;
import java.util.Objects;

/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX WARN: Unknown enum class pattern. Please report as an issue! */
/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_2_1633180.dex */
public final class x1 {
    public static final x1 GONE;
    public static final x1 INVISIBLE;
    public static final x1 REMOVED;
    public static final x1 VISIBLE;

    /* renamed from: a, reason: collision with root package name */
    public static final /* synthetic */ x1[] f2128a;

    static {
        x1 x1Var = new x1("REMOVED", 0);
        REMOVED = x1Var;
        x1 x1Var2 = new x1("VISIBLE", 1);
        VISIBLE = x1Var2;
        x1 x1Var3 = new x1("GONE", 2);
        GONE = x1Var3;
        x1 x1Var4 = new x1("INVISIBLE", 3);
        INVISIBLE = x1Var4;
        f2128a = new x1[]{x1Var, x1Var2, x1Var3, x1Var4};
    }

    public static x1 b(int i7) {
        if (i7 == 0) {
            return VISIBLE;
        }
        if (i7 == 4) {
            return INVISIBLE;
        }
        if (i7 == 8) {
            return GONE;
        }
        throw new IllegalArgumentException(a.a.d("Unknown visibility ", i7));
    }

    public static x1 c(View view) {
        return (view.getAlpha() == CropImageView.DEFAULT_ASPECT_RATIO && view.getVisibility() == 0) ? INVISIBLE : b(view.getVisibility());
    }

    public static x1 valueOf(String str) {
        return (x1) Enum.valueOf(x1.class, str);
    }

    public static x1[] values() {
        return (x1[]) f2128a.clone();
    }

    public final void a(View view) {
        int i7 = u1.f2060a[ordinal()];
        if (i7 == 1) {
            ViewGroup viewGroup = (ViewGroup) view.getParent();
            if (viewGroup != null) {
                if (Log.isLoggable("FragmentManager", 2)) {
                    view.toString();
                    viewGroup.toString();
                }
                viewGroup.removeView(view);
                return;
            }
            return;
        }
        if (i7 == 2) {
            if (Log.isLoggable("FragmentManager", 2)) {
                Objects.toString(view);
            }
            view.setVisibility(0);
        } else if (i7 == 3) {
            if (Log.isLoggable("FragmentManager", 2)) {
                Objects.toString(view);
            }
            view.setVisibility(8);
        } else {
            if (i7 != 4) {
                return;
            }
            if (Log.isLoggable("FragmentManager", 2)) {
                Objects.toString(view);
            }
            view.setVisibility(4);
        }
    }
}
