package m0;

import com.navi.tool.model.AppInfo;
import com.navi.tool.model.LogAppProfile;
import com.navi.tool.model.LogAssistantProfile;
import com.navi.tool.model.LogMediaProfile;
import com.navi.tool.model.SceneRule;
import com.navi.tool.service.ConfigBackupService;
import com.navi.tool.service.MobileHudService;
import com.navi.tool.ui.log.LogLinkFragment;
import com.navi.tool.ui.voice.VoiceNavigationFragment;
import com.navi.tool.utils.AppDetectionUtils;
import com.navi.tool.utils.LogAppProfileStorage;
import com.navi.tool.utils.LogAssistantProfileStorage;
import com.navi.tool.utils.LogMediaProfileStorage;
import com.navi.tool.utils.SceneRuleStorage;
import java.io.File;
import java.util.Comparator;
import java.util.HashMap;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final /* synthetic */ class a implements Comparator {

    /* renamed from: a, reason: collision with root package name */
    public final /* synthetic */ int f7486a;

    public /* synthetic */ a(int i7) {
        this.f7486a = i7;
    }

    @Override // java.util.Comparator
    public final int compare(Object obj, Object obj2) {
        int lambda$walk$4;
        int lambda$onTmcInfo$2;
        int lambda$getAllLaunchableApps$108;
        int lambda$getLaunchableUserApps$16;
        int lambda$getInstalledNavigationApps$1;
        int lambda$loadProfiles$0;
        int lambda$loadProfiles$02;
        int lambda$loadRules$1;
        switch (this.f7486a) {
            case 0:
                byte[] bArr = (byte[]) obj;
                byte[] bArr2 = (byte[]) obj2;
                if (bArr.length != bArr2.length) {
                    return bArr.length - bArr2.length;
                }
                for (int i7 = 0; i7 < bArr.length; i7++) {
                    byte b7 = bArr[i7];
                    byte b8 = bArr2[i7];
                    if (b7 != b8) {
                        return b7 - b8;
                    }
                }
                return 0;
            case 1:
                com.navi.tool.baidu.e eVar = com.navi.tool.baidu.e.f4632p;
                return ((com.navi.tool.baidu.d) obj).f4629b - ((com.navi.tool.baidu.d) obj2).f4629b;
            case 2:
                com.navi.tool.baidu.e eVar2 = com.navi.tool.baidu.e.f4632p;
                return Integer.compare(((int[]) obj)[0], ((int[]) obj2)[0]);
            case 3:
                lambda$walk$4 = ConfigBackupService.lambda$walk$4((File) obj, (File) obj2);
                return lambda$walk$4;
            case 4:
                lambda$onTmcInfo$2 = MobileHudService.lambda$onTmcInfo$2((h4.f) obj, (h4.f) obj2);
                return lambda$onTmcInfo$2;
            case 5:
                return ((String) obj).compareToIgnoreCase((String) obj2);
            case 6:
                File file = (File) obj;
                File file2 = (File) obj2;
                HashMap hashMap = q4.a.f8450n;
                if (file.isDirectory() && !file2.isDirectory()) {
                    return -1;
                }
                if (file.isDirectory() || !file2.isDirectory()) {
                    return file.getName().compareToIgnoreCase(file2.getName());
                }
                return 1;
            case 7:
                lambda$getAllLaunchableApps$108 = LogLinkFragment.lambda$getAllLaunchableApps$108((AppInfo) obj, (AppInfo) obj2);
                return lambda$getAllLaunchableApps$108;
            case 8:
                lambda$getLaunchableUserApps$16 = VoiceNavigationFragment.lambda$getLaunchableUserApps$16((AppInfo) obj, (AppInfo) obj2);
                return lambda$getLaunchableUserApps$16;
            case 9:
                lambda$getInstalledNavigationApps$1 = AppDetectionUtils.lambda$getInstalledNavigationApps$1((AppInfo) obj, (AppInfo) obj2);
                return lambda$getInstalledNavigationApps$1;
            case 10:
                lambda$loadProfiles$0 = LogAppProfileStorage.lambda$loadProfiles$0((LogAppProfile) obj, (LogAppProfile) obj2);
                return lambda$loadProfiles$0;
            case 11:
                return LogAssistantProfileStorage.lambda$loadProfiles$0((LogAssistantProfile) obj, (LogAssistantProfile) obj2);
            case 12:
                lambda$loadProfiles$02 = LogMediaProfileStorage.lambda$loadProfiles$0((LogMediaProfile) obj, (LogMediaProfile) obj2);
                return lambda$loadProfiles$02;
            default:
                lambda$loadRules$1 = SceneRuleStorage.lambda$loadRules$1((SceneRule) obj, (SceneRule) obj2);
                return lambda$loadRules$1;
        }
    }
}
