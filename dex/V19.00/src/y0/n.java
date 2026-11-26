package y0;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ResolveInfo;
import com.navi.tool.MainActivity;
import com.navi.tool.amapflying.AmapBroadcastService;
import com.navi.tool.amapflying.util.kuwo.NativeKuwoEngine;
import com.navi.tool.carinfo.TirePollingService;
import com.navi.tool.log.api.LogEngine;
import com.navi.tool.model.LogAppProfile;
import com.navi.tool.model.LogAssistantProfile;
import com.navi.tool.navi.api.NaviEngine;
import com.navi.tool.protocol.AutoProtocolManager;
import com.navi.tool.protocol.DestinationWeatherBroadcastReceiver;
import com.navi.tool.scene.api.SceneEngine;
import com.navi.tool.service.AutoStartService;
import com.navi.tool.service.LogLinkageManager;
import com.navi.tool.service.LogModuleLoader;
import com.navi.tool.service.LogMonitorService;
import com.navi.tool.service.NaviModuleLoader;
import com.navi.tool.service.SceneModuleLoader;
import com.navi.tool.tire.api.TireAccount;
import com.navi.tool.tire.api.TireEngine;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.List;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import s5.h0;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final /* synthetic */ class n implements Runnable {

    /* renamed from: a, reason: collision with root package name */
    public final /* synthetic */ int f9988a;

    /* renamed from: b, reason: collision with root package name */
    public final /* synthetic */ Object f9989b;

    /* renamed from: c, reason: collision with root package name */
    public final /* synthetic */ Object f9990c;

    /* renamed from: d, reason: collision with root package name */
    public final /* synthetic */ Object f9991d;

    public /* synthetic */ n(Object obj, Object obj2, Object obj3, int i7) {
        this.f9988a = i7;
        this.f9989b = obj;
        this.f9990c = obj2;
        this.f9991d = obj3;
    }

    @Override // java.lang.Runnable
    public final void run() {
        int i7 = 3;
        switch (this.f9988a) {
            case 0:
                j.a aVar = (j.a) this.f9989b;
                l6.f fVar = (l6.f) this.f9990c;
                ThreadPoolExecutor threadPoolExecutor = (ThreadPoolExecutor) this.f9991d;
                aVar.getClass();
                try {
                    v L = n1.z.L(aVar.f6874a);
                    if (L == null) {
                        throw new RuntimeException("EmojiCompat font provider not available on this device.");
                    }
                    u uVar = (u) L.f9973a;
                    synchronized (uVar.f10017d) {
                        uVar.f10019f = threadPoolExecutor;
                    }
                    L.f9973a.a(new o(fVar, threadPoolExecutor));
                    return;
                } catch (Throwable th) {
                    fVar.Y(th);
                    threadPoolExecutor.shutdown();
                    return;
                }
            case 1:
                ((MainActivity) this.f9989b).lambda$onCreate$4((String) this.f9990c, (String) this.f9991d);
                return;
            case 2:
                AmapBroadcastService amapBroadcastService = (AmapBroadcastService) this.f9989b;
                Intent intent = (Intent) this.f9990c;
                Intent intent2 = (Intent) this.f9991d;
                String str = AmapBroadcastService.ACTION_TRIGGER_EXTSCREEN_ACTIVATION;
                amapBroadcastService.getClass();
                try {
                    try {
                        amapBroadcastService.startService(intent);
                        amapBroadcastService.startService(intent2);
                        return;
                    } catch (Throwable unused) {
                        return;
                    }
                } catch (Throwable unused2) {
                    amapBroadcastService.startForegroundService(intent);
                    amapBroadcastService.startForegroundService(intent2);
                    return;
                }
            case 3:
                l4.e eVar = (l4.e) this.f9989b;
                String str2 = (String) this.f9990c;
                String str3 = (String) this.f9991d;
                l4.e eVar2 = l4.e.f7426v;
                eVar.getClass();
                try {
                    String str4 = str2 + " " + str3;
                    HttpURLConnection httpURLConnection = (HttpURLConnection) new URL("http://search.kuwo.cn/r.s?all=" + URLEncoder.encode(str4, "UTF-8") + "&pn=0&rn=1&client=kt&ver=kwplayer_ar_9.2.2.1&vipver=1&show_copyright_off=1&newver=1&ft=music&cluster=0&strategy=2012&encoding=utf8&rformat=json&vermerge=1&mobi=1").openConnection();
                    httpURLConnection.setConnectTimeout(a5.n.SOCKET_READ_TIMEOUT);
                    httpURLConnection.setRequestMethod("GET");
                    httpURLConnection.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/91.0.4472.124 Safari/537.36");
                    if (httpURLConnection.getResponseCode() == 200) {
                        InputStream inputStream = httpURLConnection.getInputStream();
                        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                        byte[] bArr = new byte[1024];
                        while (true) {
                            int read = inputStream.read(bArr);
                            if (read != -1) {
                                byteArrayOutputStream.write(bArr, 0, read);
                            } else {
                                String byteArrayOutputStream2 = byteArrayOutputStream.toString("UTF-8");
                                inputStream.close();
                                Matcher matcher = Pattern.compile("['\"]?MUSICRID['\"]?\\s*[:=]\\s*['\"]?MUSIC_(\\d+)['\"]?").matcher(byteArrayOutputStream2);
                                if (matcher.find()) {
                                    NativeKuwoEngine.getInstance().fetchLyrics(matcher.group(1));
                                } else if (str4.contains(" ")) {
                                    new Thread(new n(eVar, str2, h0.FRAGMENT_ENCODE_SET, i7)).start();
                                }
                            }
                        }
                    }
                    httpURLConnection.disconnect();
                    return;
                } catch (Exception unused3) {
                    return;
                }
            case 4:
                com.navi.tool.baidu.e eVar3 = (com.navi.tool.baidu.e) this.f9989b;
                String str5 = (String) this.f9990c;
                String str6 = (String) this.f9991d;
                com.navi.tool.baidu.e eVar4 = com.navi.tool.baidu.e.f4632p;
                eVar3.s(str5, str6);
                return;
            case 5:
                TirePollingService tirePollingService = (TirePollingService) this.f9989b;
                TireEngine tireEngine = (TireEngine) this.f9990c;
                TireAccount tireAccount = (TireAccount) this.f9991d;
                AtomicBoolean atomicBoolean = TirePollingService.f4725g;
                tirePollingService.getClass();
                try {
                    TirePollingService.c(tirePollingService, tireEngine.fetch(tireAccount));
                    o4.z.j(tirePollingService, System.currentTimeMillis());
                    o4.z.h(tirePollingService);
                    return;
                } catch (Throwable th2) {
                    String th3 = th2.getMessage() == null ? th2.toString() : th2.getMessage();
                    if (th3.contains("登录已过期") || th3.contains("401") || th3.contains("Unauthorized")) {
                        o4.z.g(tirePollingService);
                        o4.z.b(tirePollingService);
                        tirePollingService.stopSelf();
                        return;
                    } else {
                        SharedPreferences sharedPreferences = tirePollingService.getApplicationContext().getSharedPreferences("tire_account", 0);
                        int i8 = sharedPreferences.getInt("tire_consecutive_fail", 0) + 1;
                        a.a.l(sharedPreferences, "tire_consecutive_fail", i8);
                        if (i8 >= 3) {
                            tirePollingService.f4735f = System.currentTimeMillis() + o4.s.STALE_MS;
                            return;
                        }
                        return;
                    }
                }
            case 6:
                ((AutoProtocolManager) this.f9989b).lambda$bindServiceToPackage$0((Context) this.f9990c, (String) this.f9991d);
                return;
            case 7:
                ((DestinationWeatherBroadcastReceiver) this.f9989b).lambda$requestWeatherApi$0((String) this.f9990c, (String) this.f9991d);
                return;
            case 8:
                ((AutoStartService) this.f9989b).lambda$scheduleLaunches$0((String) this.f9990c, (ResolveInfo) this.f9991d);
                return;
            case 9:
                ((LogLinkageManager) this.f9989b).lambda$dispatchAppDecision$2((LogAppProfile) this.f9990c, (String) this.f9991d);
                return;
            case 10:
                ((LogLinkageManager) this.f9989b).lambda$dispatchAssistantDecision$4((LogAssistantProfile) this.f9990c, (String) this.f9991d);
                return;
            case 11:
                LogModuleLoader.lambda$finish$2((List) this.f9989b, (LogEngine) this.f9990c, (String) this.f9991d);
                return;
            case 12:
                ((LogMonitorService) this.f9989b).lambda$startLogcatThreads$9((String) this.f9990c, (NaviEngine) this.f9991d);
                return;
            case 13:
                NaviModuleLoader.lambda$finish$2((List) this.f9989b, (NaviEngine) this.f9990c, (String) this.f9991d);
                return;
            default:
                SceneModuleLoader.lambda$finish$2((List) this.f9989b, (SceneEngine) this.f9990c, (String) this.f9991d);
                return;
        }
    }
}
