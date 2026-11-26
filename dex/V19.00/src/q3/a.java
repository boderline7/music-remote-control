package q3;

import android.view.View;
import com.google.android.material.sidesheet.SideSheetBehavior;
import com.yalantis.ucrop.view.CropImageView;
import l6.f;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final class a extends f {

    /* renamed from: d, reason: collision with root package name */
    public final /* synthetic */ int f8441d;

    /* renamed from: e, reason: collision with root package name */
    public final SideSheetBehavior f8442e;

    public /* synthetic */ a(SideSheetBehavior sideSheetBehavior, int i7) {
        this.f8441d = i7;
        this.f8442e = sideSheetBehavior;
    }

    public final float G0(int i7) {
        switch (this.f8441d) {
            case 0:
                float I0 = I0();
                return (i7 - I0) / (H0() - I0);
            default:
                float I02 = I0();
                return (I02 - i7) / (I02 - H0());
        }
    }

    public final int H0() {
        int i7 = this.f8441d;
        SideSheetBehavior sideSheetBehavior = this.f8442e;
        switch (i7) {
            case 0:
                return Math.max(0, sideSheetBehavior.f4131n + sideSheetBehavior.f4132o);
            default:
                return Math.max(0, (I0() - sideSheetBehavior.f4129l) - sideSheetBehavior.f4132o);
        }
    }

    public final int I0() {
        int i7 = this.f8441d;
        SideSheetBehavior sideSheetBehavior = this.f8442e;
        switch (i7) {
            case 0:
                return (-sideSheetBehavior.f4129l) - sideSheetBehavior.f4132o;
            default:
                return sideSheetBehavior.f4130m;
        }
    }

    public final boolean J0(float f7) {
        switch (this.f8441d) {
            case 0:
                if (f7 > CropImageView.DEFAULT_ASPECT_RATIO) {
                    break;
                }
                break;
            default:
                if (f7 < CropImageView.DEFAULT_ASPECT_RATIO) {
                    break;
                }
                break;
        }
        return true;
    }

    public final boolean K0(View view) {
        switch (this.f8441d) {
            case 0:
                if (view.getRight() < (H0() - I0()) / 2) {
                    break;
                }
                break;
            default:
                if (view.getLeft() > (H0() + I0()) / 2) {
                    break;
                }
                break;
        }
        return true;
    }

    public final boolean L0(float f7, float f8) {
        int i7 = this.f8441d;
        SideSheetBehavior sideSheetBehavior = this.f8442e;
        switch (i7) {
            case 0:
                if (Math.abs(f7) > Math.abs(f8)) {
                    float abs = Math.abs(f7);
                    sideSheetBehavior.getClass();
                    if (abs > CropImageView.DEFAULT_IMAGE_TO_CROP_BOUNDS_ANIM_DURATION) {
                        break;
                    }
                }
                break;
            default:
                if (Math.abs(f7) > Math.abs(f8)) {
                    float abs2 = Math.abs(f7);
                    sideSheetBehavior.getClass();
                    if (abs2 > CropImageView.DEFAULT_IMAGE_TO_CROP_BOUNDS_ANIM_DURATION) {
                        break;
                    }
                }
                break;
        }
        return true;
    }

    public final boolean M0(View view, float f7) {
        int i7 = this.f8441d;
        SideSheetBehavior sideSheetBehavior = this.f8442e;
        switch (i7) {
            case 0:
                if (Math.abs((f7 * sideSheetBehavior.f4128k) + view.getLeft()) > 0.5f) {
                    break;
                }
                break;
            default:
                if (Math.abs((f7 * sideSheetBehavior.f4128k) + view.getRight()) > 0.5f) {
                    break;
                }
                break;
        }
        return true;
    }
}
