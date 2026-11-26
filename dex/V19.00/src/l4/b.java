package l4;

import android.text.TextUtils;
import com.navi.tool.amapflying.util.kuwo.NativeKuwoEngine;
import h4.l;
import java.util.regex.Matcher;
import s5.h0;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final class b implements NativeKuwoEngine.Callback {

    /* renamed from: a, reason: collision with root package name */
    public final /* synthetic */ e f7423a;

    public b(e eVar) {
        this.f7423a = eVar;
    }

    @Override // com.navi.tool.amapflying.util.kuwo.NativeKuwoEngine.Callback
    public final void onLyricsResolved(String str) {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        e eVar = this.f7423a;
        synchronized (eVar.f7443o) {
            eVar.f7443o.clear();
            for (String str2 : str.split("\n")) {
                String trim = str2.replaceAll("<[^>]+>", h0.FRAGMENT_ENCODE_SET).trim();
                if (!TextUtils.isEmpty(trim)) {
                    Matcher matcher = e.f7427w.matcher(trim);
                    if (matcher.find()) {
                        try {
                            int parseInt = Integer.parseInt(matcher.group(1));
                            int parseInt2 = Integer.parseInt(matcher.group(2));
                            String group = matcher.group(3);
                            int parseInt3 = Integer.parseInt(group);
                            if (group.length() == 1) {
                                parseInt3 *= 100;
                            } else if (group.length() == 2) {
                                parseInt3 *= 10;
                            }
                            long j7 = (parseInt2 * 1000) + (parseInt * 60000) + parseInt3;
                            String trim2 = matcher.group(4).trim();
                            if (!TextUtils.isEmpty(trim2)) {
                                eVar.f7443o.put(Long.valueOf(j7), trim2);
                            }
                        } catch (Exception e7) {
                            e7.printStackTrace();
                        }
                    }
                }
            }
        }
        eVar.f7443o.size();
        i6.e b7 = i6.e.b();
        l lVar = new l(eVar.f7443o);
        lVar.f6756b = 6;
        b7.f(lVar);
    }
}
