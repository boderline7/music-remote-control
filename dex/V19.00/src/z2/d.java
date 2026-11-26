package z2;

import android.content.Context;
import android.graphics.Rect;
import android.graphics.RectF;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.accessibility.AccessibilityNodeInfo;
import com.google.android.material.chip.Chip;
import java.util.ArrayList;
import q2.k;
import s5.h0;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final class d extends u0.b {

    /* renamed from: q, reason: collision with root package name */
    public final /* synthetic */ Chip f10257q;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public d(Chip chip, Chip chip2) {
        super(chip2);
        this.f10257q = chip;
    }

    @Override // u0.b
    public final int n(float f7, float f8) {
        RectF closeIconTouchBounds;
        int i7 = Chip.f3529x;
        Chip chip = this.f10257q;
        if (chip.d()) {
            closeIconTouchBounds = chip.getCloseIconTouchBounds();
            if (closeIconTouchBounds.contains(f7, f8)) {
                return 1;
            }
        }
        return 0;
    }

    @Override // u0.b
    public final void o(ArrayList arrayList) {
        boolean z6 = false;
        arrayList.add(0);
        int i7 = Chip.f3529x;
        Chip chip = this.f10257q;
        if (chip.d()) {
            f fVar = chip.f3532e;
            if (fVar != null && fVar.L) {
                z6 = true;
            }
            if (!z6 || chip.f3535h == null) {
                return;
            }
            arrayList.add(1);
        }
    }

    @Override // u0.b
    public final boolean s(int i7, int i8, Bundle bundle) {
        boolean z6 = false;
        if (i8 == 16) {
            Chip chip = this.f10257q;
            if (i7 == 0) {
                return chip.performClick();
            }
            if (i7 == 1) {
                chip.playSoundEffect(0);
                View.OnClickListener onClickListener = chip.f3535h;
                if (onClickListener != null) {
                    onClickListener.onClick(chip);
                    z6 = true;
                }
                if (chip.f3547t) {
                    chip.f3546s.x(1, 1);
                }
            }
        }
        return z6;
    }

    @Override // u0.b
    public final void t(q0.i iVar) {
        Chip chip = this.f10257q;
        boolean e7 = chip.e();
        AccessibilityNodeInfo accessibilityNodeInfo = iVar.f8426a;
        accessibilityNodeInfo.setCheckable(e7);
        accessibilityNodeInfo.setClickable(chip.isClickable());
        iVar.f(chip.getAccessibilityClassName());
        accessibilityNodeInfo.setText(chip.getText());
    }

    @Override // u0.b
    public final void u(int i7, q0.i iVar) {
        Rect closeIconTouchBoundsInt;
        AccessibilityNodeInfo accessibilityNodeInfo = iVar.f8426a;
        CharSequence charSequence = h0.FRAGMENT_ENCODE_SET;
        if (i7 != 1) {
            accessibilityNodeInfo.setContentDescription(h0.FRAGMENT_ENCODE_SET);
            accessibilityNodeInfo.setBoundsInParent(Chip.f3530y);
            return;
        }
        Chip chip = this.f10257q;
        CharSequence closeIconContentDescription = chip.getCloseIconContentDescription();
        if (closeIconContentDescription != null) {
            accessibilityNodeInfo.setContentDescription(closeIconContentDescription);
        } else {
            CharSequence text = chip.getText();
            Context context = chip.getContext();
            int i8 = k.mtrl_chip_close_icon_content_description;
            Object[] objArr = new Object[1];
            if (!TextUtils.isEmpty(text)) {
                charSequence = text;
            }
            objArr[0] = charSequence;
            accessibilityNodeInfo.setContentDescription(context.getString(i8, objArr).trim());
        }
        closeIconTouchBoundsInt = chip.getCloseIconTouchBoundsInt();
        accessibilityNodeInfo.setBoundsInParent(closeIconTouchBoundsInt);
        iVar.b(q0.g.ACTION_CLICK);
        accessibilityNodeInfo.setEnabled(chip.isEnabled());
    }

    @Override // u0.b
    public final void v(int i7, boolean z6) {
        if (i7 == 1) {
            Chip chip = this.f10257q;
            chip.f3541n = z6;
            chip.refreshDrawableState();
        }
    }
}
