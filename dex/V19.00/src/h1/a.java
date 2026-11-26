package h1;

import com.navi.tool.service.AutoStartService;
import com.navi.tool.service.SceneModeManager;
import com.navi.tool.service.WeatherAlertOverlayService;
import java.io.Serializable;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final /* synthetic */ class a implements Runnable {

    /* renamed from: a, reason: collision with root package name */
    public final /* synthetic */ int f6656a;

    /* renamed from: b, reason: collision with root package name */
    public final /* synthetic */ int f6657b;

    /* renamed from: c, reason: collision with root package name */
    public final /* synthetic */ Object f6658c;

    /* renamed from: d, reason: collision with root package name */
    public final /* synthetic */ Object f6659d;

    public /* synthetic */ a(Object obj, int i7, Serializable serializable, int i8) {
        this.f6656a = i8;
        this.f6659d = obj;
        this.f6657b = i7;
        this.f6658c = serializable;
    }

    public /* synthetic */ a(Object obj, String str, int i7, int i8) {
        this.f6656a = i8;
        this.f6659d = obj;
        this.f6658c = str;
        this.f6657b = i7;
    }

    @Override // java.lang.Runnable
    public final void run() {
        int i7 = this.f6656a;
        int i8 = this.f6657b;
        Object obj = this.f6658c;
        Object obj2 = this.f6659d;
        switch (i7) {
            case 0:
                ((e) ((b) obj2).f6663d).n(i8, obj);
                break;
            case 1:
                ((e) obj2).n(i8, obj);
                break;
            case 2:
                ((AutoStartService) obj2).lambda$checkAndSyncTheme$2((String) obj, i8);
                break;
            case 3:
                ((SceneModeManager) obj2).lambda$executeRuleAction$13((String) obj, i8);
                break;
            case 4:
                ((WeatherAlertOverlayService) obj2).lambda$speakByAutoProtocol$1((String) obj, i8);
                break;
            default:
                String str = (String) obj;
                q4.g gVar = ((q4.h) obj2).f8481q;
                if (gVar != null) {
                    gVar.onFileProgress(str, i8);
                    break;
                }
                break;
        }
    }
}
