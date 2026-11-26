package y0;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Handler;
import android.os.SystemClock;
import android.text.TextUtils;
import com.navi.tool.amapflying.receiver.LunaMusicLyricReceiver;
import com.navi.tool.amapflying.receiver.QQMusicLyricReceiver;
import java.util.TreeMap;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final class p implements Runnable {

    /* renamed from: a, reason: collision with root package name */
    public final /* synthetic */ int f9994a;

    public /* synthetic */ p(int i7) {
        this.f9994a = i7;
    }

    @Override // java.lang.Runnable
    public final void run() {
        Bitmap bitmap;
        boolean z6;
        long j7;
        String str;
        String str2;
        long j8;
        String str3;
        boolean z7;
        Handler handler;
        switch (this.f9994a) {
            case 0:
                try {
                    int i7 = l0.k.f7276a;
                    l0.j.a("EmojiCompat.EmojiCompatInitializer.run");
                    if (l.f9977j != null) {
                        l.a().c();
                    }
                    l0.j.b();
                    return;
                } catch (Throwable th) {
                    int i8 = l0.k.f7276a;
                    l0.j.b();
                    throw th;
                }
            case 1:
                if (LunaMusicLyricReceiver.f4523t) {
                    LunaMusicLyricReceiver.f4523t = false;
                    h4.m mVar = new h4.m(TextUtils.isEmpty(LunaMusicLyricReceiver.f4515l) ? "汽水音乐" : LunaMusicLyricReceiver.f4515l, LunaMusicLyricReceiver.f4516m, false, LunaMusicLyricReceiver.e(), LunaMusicLyricReceiver.f4522s);
                    mVar.f6768l = 5;
                    mVar.f6766j = LunaMusicLyricReceiver.f4525v;
                    mVar.f6767k = LunaMusicLyricReceiver.f4526w;
                    i6.e.b().f(mVar);
                    return;
                }
                return;
            case 2:
                Context context = LunaMusicLyricReceiver.f4510g;
                if (context == null) {
                    return;
                }
                TreeMap treeMap = LunaMusicLyricReceiver.f4508e;
                synchronized (treeMap) {
                    try {
                        if (!treeMap.isEmpty()) {
                            LunaMusicLyricReceiver.f4511h = 0;
                        } else if (LunaMusicLyricReceiver.f4511h >= 3) {
                            LunaMusicLyricReceiver.f4511h = 0;
                        } else {
                            try {
                                context.sendBroadcast(new Intent("com.luna.music.car.PLAYER_SYNC"));
                            } catch (Exception unused) {
                            }
                            LunaMusicLyricReceiver.f4511h++;
                            LunaMusicLyricReceiver.f4509f.postDelayed(this, LunaMusicLyricReceiver.f4504a[LunaMusicLyricReceiver.f4511h - 1]);
                        }
                    } finally {
                    }
                }
                return;
            case 3:
                z6 = QQMusicLyricReceiver.sIsPlaying;
                if (z6) {
                    long elapsedRealtime = SystemClock.elapsedRealtime();
                    j7 = QQMusicLyricReceiver.sSongStartTime;
                    long j9 = elapsedRealtime - j7;
                    QQMusicLyricReceiver.updateLyricDisplay(j9);
                    str = QQMusicLyricReceiver.sCurrentTitle;
                    String str4 = !TextUtils.isEmpty(str) ? QQMusicLyricReceiver.sCurrentTitle : "QQ音乐";
                    str2 = QQMusicLyricReceiver.sCurrentArtist;
                    j8 = QQMusicLyricReceiver.sTotalDuration;
                    h4.m mVar2 = new h4.m(str4, str2, true, j9, j8);
                    mVar2.f6768l = 0;
                    str3 = QQMusicLyricReceiver.sCurrentCoverUrl;
                    mVar2.f6762f = str3;
                    z7 = QQMusicLyricReceiver.sIsFavorite;
                    mVar2.f6766j = z7;
                    mVar2.f6767k = QQMusicLyricReceiver.sPlayMode;
                    i6.e.b().f(mVar2);
                    handler = QQMusicLyricReceiver.sHandler;
                    handler.postDelayed(this, 200L);
                    return;
                }
                return;
            default:
                try {
                    bitmap = n1.z.b();
                } catch (Throwable unused2) {
                    bitmap = null;
                }
                n1.z.f7690c = bitmap;
                n1.z.f7691d = false;
                return;
        }
    }
}
