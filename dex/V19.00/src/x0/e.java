package x0;

import android.view.View;
import p0.d1;
import p0.r0;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final class e extends g {

    /* renamed from: b, reason: collision with root package name */
    public final /* synthetic */ int f9556b;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public e(String str, int i7) {
        super(str);
        this.f9556b = i7;
    }

    @Override // n0.k
    public final /* bridge */ /* synthetic */ float b(Object obj) {
        switch (this.f9556b) {
        }
        return e((View) obj);
    }

    @Override // n0.k
    public final /* bridge */ /* synthetic */ void d(Object obj, float f7) {
        switch (this.f9556b) {
            case 0:
                f((View) obj, f7);
                break;
            case 1:
                f((View) obj, f7);
                break;
            case 2:
                f((View) obj, f7);
                break;
            case 3:
                f((View) obj, f7);
                break;
            case 4:
                f((View) obj, f7);
                break;
            case 5:
                f((View) obj, f7);
                break;
            case 6:
                f((View) obj, f7);
                break;
            case 7:
                f((View) obj, f7);
                break;
            case 8:
                f((View) obj, f7);
                break;
            case 9:
                f((View) obj, f7);
                break;
            case 10:
                f((View) obj, f7);
                break;
            case 11:
                f((View) obj, f7);
                break;
            case 12:
                f((View) obj, f7);
                break;
            default:
                f((View) obj, f7);
                break;
        }
    }

    public final float e(View view) {
        switch (this.f9556b) {
            case 0:
                return view.getY();
            case 1:
                int i7 = d1.OVER_SCROLL_ALWAYS;
                return r0.m(view);
            case 2:
                return view.getAlpha();
            case 3:
                return view.getScrollX();
            case 4:
                return view.getScrollY();
            case 5:
                return view.getTranslationX();
            case 6:
                return view.getTranslationY();
            case 7:
                int i8 = d1.OVER_SCROLL_ALWAYS;
                return r0.l(view);
            case 8:
                return view.getScaleX();
            case 9:
                return view.getScaleY();
            case 10:
                return view.getRotation();
            case 11:
                return view.getRotationX();
            case 12:
                return view.getRotationY();
            default:
                return view.getX();
        }
    }

    public final void f(View view, float f7) {
        switch (this.f9556b) {
            case 0:
                view.setY(f7);
                break;
            case 1:
                int i7 = d1.OVER_SCROLL_ALWAYS;
                r0.x(view, f7);
                break;
            case 2:
                view.setAlpha(f7);
                break;
            case 3:
                view.setScrollX((int) f7);
                break;
            case 4:
                view.setScrollY((int) f7);
                break;
            case 5:
                view.setTranslationX(f7);
                break;
            case 6:
                view.setTranslationY(f7);
                break;
            case 7:
                int i8 = d1.OVER_SCROLL_ALWAYS;
                r0.w(view, f7);
                break;
            case 8:
                view.setScaleX(f7);
                break;
            case 9:
                view.setScaleY(f7);
                break;
            case 10:
                view.setRotation(f7);
                break;
            case 11:
                view.setRotationX(f7);
                break;
            case 12:
                view.setRotationY(f7);
                break;
            default:
                view.setX(f7);
                break;
        }
    }
}
