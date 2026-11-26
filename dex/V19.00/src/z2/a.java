package z2;

import android.widget.CompoundButton;
import android.widget.RadioGroup;
import com.google.android.material.chip.Chip;
import com.navi.tool.ui.scene.SceneModeFragment;
import com.navi.tool.ui.theme.AutoBrightnessFragment;
import com.navi.tool.ui.theme.AutoThemeFragment;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final /* synthetic */ class a implements CompoundButton.OnCheckedChangeListener {

    /* renamed from: a, reason: collision with root package name */
    public final /* synthetic */ int f10251a;

    /* renamed from: b, reason: collision with root package name */
    public final /* synthetic */ Object f10252b;

    public /* synthetic */ a(int i7, Object obj) {
        this.f10251a = i7;
        this.f10252b = obj;
    }

    @Override // android.widget.CompoundButton.OnCheckedChangeListener
    public final void onCheckedChanged(CompoundButton compoundButton, boolean z6) {
        int i7 = this.f10251a;
        Object obj = this.f10252b;
        switch (i7) {
            case 0:
                Chip chip = (Chip) obj;
                com.google.android.material.internal.h hVar = chip.f3537j;
                if (hVar != null) {
                    v2.a aVar = (v2.a) hVar;
                    com.google.android.material.internal.a aVar2 = (com.google.android.material.internal.a) aVar.f9388b;
                    if (!z6 ? aVar2.e(chip, aVar2.f3795e) : aVar2.a(chip)) {
                        ((com.google.android.material.internal.a) aVar.f9388b).d();
                    }
                }
                CompoundButton.OnCheckedChangeListener onCheckedChangeListener = chip.f3536i;
                if (onCheckedChangeListener != null) {
                    onCheckedChangeListener.onCheckedChanged(compoundButton, z6);
                    break;
                }
                break;
            case 1:
                SceneModeFragment.lambda$showRuleEditorDialog$11((RadioGroup) obj, compoundButton, z6);
                break;
            case 2:
                ((SceneModeFragment) obj).lambda$onCreateView$0(compoundButton, z6);
                break;
            case 3:
                ((AutoBrightnessFragment) obj).lambda$onViewCreated$1(compoundButton, z6);
                break;
            default:
                ((AutoThemeFragment) obj).lambda$onViewCreated$1(compoundButton, z6);
                break;
        }
    }
}
