package h2;

import com.navi.tool.service.SceneModeManager;
import java.util.Set;
import java.util.function.Function;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final /* synthetic */ class a implements Function {

    /* renamed from: a, reason: collision with root package name */
    public final /* synthetic */ int f6702a;

    public /* synthetic */ a(int i7) {
        this.f6702a = i7;
    }

    @Override // java.util.function.Function
    public final Object apply(Object obj) {
        Set lambda$rebuildFieldRuleIndex$18;
        String str = (String) obj;
        switch (this.f6702a) {
            case 0:
                return Integer.valueOf(Integer.parseInt(str));
            case 1:
                return Boolean.valueOf(Boolean.parseBoolean(str));
            default:
                lambda$rebuildFieldRuleIndex$18 = SceneModeManager.lambda$rebuildFieldRuleIndex$18(str);
                return lambda$rebuildFieldRuleIndex$18;
        }
    }
}
