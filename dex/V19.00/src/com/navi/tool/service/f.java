package com.navi.tool.service;

import com.navi.tool.utils.AnrWatchdog;
import com.navi.tool.utils.CrashReportCollector;
import com.navi.tool.utils.SceneRuleStorage;
import java.io.File;
import java.io.FilenameFilter;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final /* synthetic */ class f implements FilenameFilter {

    /* renamed from: a, reason: collision with root package name */
    public final /* synthetic */ int f4861a;

    public /* synthetic */ f(int i7) {
        this.f4861a = i7;
    }

    @Override // java.io.FilenameFilter
    public final boolean accept(File file, String str) {
        boolean lambda$doBackup$2;
        boolean lambda$trimOldFiles$5;
        boolean lambda$retryPending$4;
        boolean lambda$latestCrashFile$0;
        boolean lambda$saveRules$2;
        boolean lambda$loadRules$0;
        switch (this.f4861a) {
            case 0:
                lambda$doBackup$2 = ConfigBackupService.lambda$doBackup$2(file, str);
                return lambda$doBackup$2;
            case 1:
                lambda$trimOldFiles$5 = AnrWatchdog.lambda$trimOldFiles$5(file, str);
                return lambda$trimOldFiles$5;
            case 2:
                lambda$retryPending$4 = AnrWatchdog.lambda$retryPending$4(file, str);
                return lambda$retryPending$4;
            case 3:
                lambda$latestCrashFile$0 = CrashReportCollector.lambda$latestCrashFile$0(file, str);
                return lambda$latestCrashFile$0;
            case 4:
                lambda$saveRules$2 = SceneRuleStorage.lambda$saveRules$2(file, str);
                return lambda$saveRules$2;
            default:
                lambda$loadRules$0 = SceneRuleStorage.lambda$loadRules$0(file, str);
                return lambda$loadRules$0;
        }
    }
}
