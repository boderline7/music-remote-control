package cn.kuwo.autosdk;

import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Handler;
import android.text.TextUtils;
import cn.kuwo.base.bean.Music;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final class d {
    public static final String KUWO_KEY = "kuwo_key";

    /* renamed from: n, reason: collision with root package name */
    public static String f3106n = "";

    /* renamed from: a, reason: collision with root package name */
    public final Handler f3107a;

    /* renamed from: b, reason: collision with root package name */
    public final ah$e f3108b;

    /* renamed from: c, reason: collision with root package name */
    public s1.h f3109c;

    /* renamed from: d, reason: collision with root package name */
    public final ah$b f3110d;

    /* renamed from: e, reason: collision with root package name */
    public final ah$c f3111e;

    /* renamed from: f, reason: collision with root package name */
    public s1.b f3112f;

    /* renamed from: g, reason: collision with root package name */
    public s1.e f3113g;

    /* renamed from: h, reason: collision with root package name */
    public s1.i f3114h;

    /* renamed from: i, reason: collision with root package name */
    public final ah$f f3115i;

    /* renamed from: j, reason: collision with root package name */
    public boolean f3116j;

    /* renamed from: k, reason: collision with root package name */
    public boolean f3117k;

    /* renamed from: l, reason: collision with root package name */
    public boolean f3118l;

    /* renamed from: m, reason: collision with root package name */
    public boolean f3119m;

    /* JADX WARN: Type inference failed for: r0v1, types: [cn.kuwo.autosdk.ah$e] */
    /* JADX WARN: Type inference failed for: r0v3, types: [cn.kuwo.autosdk.ah$f] */
    /* JADX WARN: Type inference failed for: r1v0, types: [cn.kuwo.autosdk.ah$b] */
    /* JADX WARN: Type inference failed for: r1v1, types: [cn.kuwo.autosdk.ah$c] */
    public d(Handler handler) {
        new BroadcastReceiver() { // from class: cn.kuwo.autosdk.ah$d
            @Override // android.content.BroadcastReceiver
            public final void onReceive(Context context, Intent intent) {
                String action = intent.getAction();
                if (!TextUtils.isEmpty(action) && action.equals("cn.kuwo.kwmusicauto.action.PLAY_END")) {
                    d.this.getClass();
                }
            }
        };
        this.f3108b = new BroadcastReceiver() { // from class: cn.kuwo.autosdk.ah$e
            @Override // android.content.BroadcastReceiver
            public final void onReceive(Context context, Intent intent) {
                String action = intent.getAction();
                if (!TextUtils.isEmpty(action) && action.equals("cn.kuwo.kwmusicauto.action.PLAYER_MODE")) {
                    d dVar = d.this;
                    if (dVar.f3109c != null) {
                        dVar.f3109c.onPlayerMode(intent.getIntExtra("PLAYERMODE", 0));
                    }
                }
            }
        };
        this.f3109c = null;
        this.f3110d = new BroadcastReceiver() { // from class: cn.kuwo.autosdk.ah$b
            @Override // android.content.BroadcastReceiver
            public final void onReceive(Context context, Intent intent) {
                String action = intent.getAction();
                if (!TextUtils.isEmpty(action) && action.equals("cn.kuwo.kwmusicauto.action.ENTER")) {
                    d dVar = d.this;
                    dVar.b(context);
                    s1.b bVar = dVar.f3112f;
                    if (bVar != null) {
                        bVar.f8653a.getClass();
                    }
                }
            }
        };
        this.f3111e = new BroadcastReceiver() { // from class: cn.kuwo.autosdk.ah$c
            @Override // android.content.BroadcastReceiver
            public final void onReceive(Context context, Intent intent) {
                s1.e eVar;
                String action = intent.getAction();
                if (TextUtils.isEmpty(action)) {
                    return;
                }
                d dVar = d.this;
                dVar.getClass();
                if (context != null) {
                    try {
                        context.unbindService(s1.a.a());
                    } catch (Throwable th) {
                        th.printStackTrace();
                    }
                }
                if (!action.equals("cn.kuwo.kwmusicauto.action.EXIT") || (eVar = dVar.f3113g) == null) {
                    return;
                }
                eVar.onExit();
            }
        };
        this.f3113g = null;
        this.f3114h = null;
        this.f3115i = new BroadcastReceiver() { // from class: cn.kuwo.autosdk.ah$f
            @Override // android.content.BroadcastReceiver
            public final void onReceive(Context context, Intent intent) {
                Music music;
                String action = intent.getAction();
                if (!TextUtils.isEmpty(action) && action.equals("cn.kuwo.kwmusicauto.action.PLAYER_STATUS")) {
                    d dVar = d.this;
                    if (dVar.f3114h != null) {
                        int intExtra = intent.getIntExtra("PLAYERSTATUS", 0);
                        s1.v vVar = s1.v.INIT;
                        if (intExtra == 1) {
                            vVar = s1.v.PLAYING;
                        } else if (intExtra == 2) {
                            vVar = s1.v.BUFFERING;
                        } else if (intExtra == 3) {
                            vVar = s1.v.PAUSE;
                        } else if (intExtra == 4) {
                            vVar = s1.v.STOP;
                        }
                        String stringExtra = intent.getStringExtra("play_music_name");
                        String stringExtra2 = intent.getStringExtra("play_music_artist");
                        String stringExtra3 = intent.getStringExtra("play_music_album");
                        if (stringExtra != null) {
                            music = new Music();
                            music.f3164c = stringExtra;
                            music.f3167f = stringExtra3;
                            music.f3165d = stringExtra2;
                        } else {
                            music = null;
                        }
                        dVar.f3114h.onPlayerStatus(vVar, music);
                    }
                }
            }
        };
        this.f3116j = false;
        this.f3117k = false;
        this.f3118l = false;
        this.f3119m = false;
        new BroadcastReceiver() { // from class: cn.kuwo.autosdk.ah$g
            @Override // android.content.BroadcastReceiver
            public final void onReceive(Context context, Intent intent) {
                if ("cn.kuwo.kwmusicauto.action.PLAY_CLIENT_MUSICS".equals(intent.getAction())) {
                    d.this.getClass();
                }
            }
        };
        f3106n = "auto";
        this.f3107a = handler;
    }

    public static void a(Context context, String str, BroadcastReceiver broadcastReceiver) {
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.setPriority(androidx.appcompat.widget.u.MAX_ACTIVITY_COUNT_UNLIMITED);
        intentFilter.addAction(str);
        context.registerReceiver(broadcastReceiver, intentFilter);
    }

    public final boolean b(Context context) {
        if (context != null) {
            try {
                Intent intent = new Intent();
                intent.setAction("cn.kuwo.service.AutoSdkService");
                intent.setComponent(new ComponentName(m.PREFERENCE_PACKAGE, "cn.kuwo.autosdk.AutoSdkService"));
                context.startService(intent);
                return context.bindService(intent, s1.a.a(), 1);
            } catch (Throwable th) {
                th.printStackTrace();
            }
        }
        return false;
    }
}
