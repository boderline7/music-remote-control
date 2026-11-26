package com.navi.tool.ui.extra;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.media.AudioManager;
import android.net.Uri;
import android.os.Handler;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.navi.tool.amapflying.receiver.QQMusicLyricReceiver;
import com.navi.tool.amapflying.util.FloatingWindowService;
import com.navi.tool.amapflying.util.kugou.KugouMusicManager;
import com.navi.tool.amapflying.util.kuwo.KuwoMusicManager;
import com.navi.tool.amapflying.util.kuwo.NewKuwoMusicManager;
import com.navi.tool.amapflying.util.netease.NeteaseMusicManager;
import com.navi.tool.amapflying.util.qq.QQMusicManager;
import com.navi.tool.utils.MusicCoverLoader;
import com.navi.tool.view.SpectrumView;
import com.yalantis.ucrop.view.CropImageView;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.TreeMap;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public class DesktopAssistantMusicUiHelper {
    public static final String ACTION_MUSIC_APP_UPDATED = "com.navi.tool.ACTION_MUSIC_APP_UPDATED";
    public static final String ACTION_MUSIC_UI_UPDATED = "com.navi.tool.ACTION_MUSIC_UI_UPDATED";
    public static final int CONTROL_NEXT = 2;
    public static final int CONTROL_PLAY_PAUSE = 0;
    public static final int CONTROL_PREV = 1;
    public static final int CONTROL_SEEK = 3;

    /* renamed from: com.navi.tool.ui.extra.DesktopAssistantMusicUiHelper$2, reason: invalid class name */
    public class AnonymousClass2 implements CoverLoadCallback {
        final /* synthetic */ CoverRequestCallback val$callback;
        final /* synthetic */ CoverRequest val$coverRequest;

        public AnonymousClass2(CoverRequestCallback coverRequestCallback, CoverRequest coverRequest) {
            this.val$callback = coverRequestCallback;
            this.val$coverRequest = coverRequest;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static /* synthetic */ void lambda$onLoaded$0(CoverRequestCallback coverRequestCallback, CoverRequest coverRequest, Bitmap bitmap) {
            if (coverRequestCallback.isCurrentTitle(coverRequest.title)) {
                coverRequestCallback.applyCoverBitmap(bitmap, false);
            }
        }

        @Override // com.navi.tool.ui.extra.DesktopAssistantMusicUiHelper.CoverLoadCallback
        public void onError(String str) {
            this.val$callback.logError("Cover load failed for: " + this.val$coverRequest.title + " Error: " + str);
            DesktopAssistantMusicUiHelper.applyDefaultCoverIfCurrent(this.val$coverRequest, this.val$callback);
        }

        @Override // com.navi.tool.ui.extra.DesktopAssistantMusicUiHelper.CoverLoadCallback
        public void onLoaded(Bitmap bitmap) {
            this.val$callback.logDebug("Cover loaded successfully for: " + this.val$coverRequest.title);
            this.val$callback.saveMusicCover(bitmap);
            CoverRequestCallback coverRequestCallback = this.val$callback;
            coverRequestCallback.runOnUiThread(new y2(coverRequestCallback, this.val$coverRequest, bitmap, 0));
        }
    }

    public interface BitmapCallback {
        void onLoaded(Bitmap bitmap);
    }

    public interface CoverLoadCallback {
        void onError(String str);

        void onLoaded(Bitmap bitmap);
    }

    public static class CoverRequest {
        public static final int TYPE_DIRECT = 1;
        public static final int TYPE_METADATA = 2;
        public static final int TYPE_NONE = 0;
        public boolean allowMetadataFallback;
        public String artist;
        public String directUrl;
        public String hash;
        public boolean preserveCurrentCoverOnFailure;
        public String rid;
        public String songId;
        public int source;
        public String title;
        public int type = 0;
        public boolean updateSmallArt;
    }

    public interface CoverRequestCallback {
        void applyCoverBitmap(Bitmap bitmap, boolean z6);

        void applyDefaultCover();

        boolean isCurrentTitle(String str);

        void logDebug(String str);

        void logError(String str);

        void runOnUiThread(Runnable runnable);

        void saveMusicCover(Bitmap bitmap);
    }

    public interface FavoriteCallback {
        void onError(String str);

        void onSuccess(boolean z6);
    }

    public static class MusicSourceCandidate {
        public final String displayName;
        public final String packageName;
        public final int source;

        public MusicSourceCandidate(int i7, String str, String str2) {
            this.source = i7;
            this.packageName = str;
            this.displayName = str2;
        }
    }

    private static void addCandidateIfInstalled(List<MusicSourceCandidate> list, Context context, String str, String str2, boolean z6) {
        if (list == null || context == null || TextUtils.isEmpty(str) || !isPackageInstalled(context, str)) {
            return;
        }
        list.add(new MusicSourceCandidate(z6 ? resolveMusicSourceFromPackage(context, str) : 6, str, firstNonEmpty(resolveAppLabel(context, str), str2, str)));
    }

    public static void applyCoverBitmap(Bitmap bitmap, ImageView imageView) {
        if (imageView != null) {
            imageView.setImageBitmap(bitmap);
        }
    }

    public static void applyDefaultCover(ImageView imageView) {
        if (imageView != null) {
            imageView.setImageResource(g4.o.ic_music_note);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void applyDefaultCoverIfCurrent(CoverRequest coverRequest, CoverRequestCallback coverRequestCallback) {
        coverRequestCallback.runOnUiThread(new x2(coverRequestCallback, coverRequest));
    }

    public static CoverRequest buildCoverRequest(h4.m mVar, SharedPreferences sharedPreferences, String str, String str2) {
        String str3 = mVar.f6758b;
        if (str3 == null || str3.isEmpty() || !(!str3.equals(str))) {
            return null;
        }
        CoverRequest coverRequest = new CoverRequest();
        coverRequest.title = str3;
        coverRequest.artist = mVar.f6759c;
        coverRequest.rid = mVar.f6763g;
        coverRequest.hash = mVar.f6764h;
        coverRequest.songId = mVar.f6765i;
        coverRequest.source = resolveCoverSource(mVar, sharedPreferences);
        coverRequest.allowMetadataFallback = canUseMetadataFallback(mVar.f6768l);
        coverRequest.preserveCurrentCoverOnFailure = mVar.f6768l == 0;
        String str4 = mVar.f6762f;
        if (isUsableDirectCoverUrl(str4)) {
            coverRequest.type = 1;
            coverRequest.directUrl = str4;
            return coverRequest;
        }
        if (!coverRequest.allowMetadataFallback) {
            return null;
        }
        coverRequest.type = 2;
        return coverRequest;
    }

    private static boolean canUseMetadataFallback(int i7) {
        return (i7 == 1 || i7 == 3 || i7 == 4) ? false : true;
    }

    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:34:0x0048 -> B:19:0x004b). Please report as a decompilation issue!!! */
    public static void dispatchLoopMode(Context context, int i7, int i8) {
        int i9;
        if (context == null) {
            return;
        }
        try {
            if (i7 == 0) {
                if (i8 == 0) {
                    i9 = z1.c.SPUT;
                } else if (i8 == 1) {
                    i9 = z1.c.SGET_CHAR;
                } else if (i8 != 2) {
                    return;
                } else {
                    i9 = z1.c.SPUT_OBJECT;
                }
                sendQQMusicActionViaUri(context, i9, -1);
            } else if (i7 == 1) {
                KuwoMusicManager.getInstance(context).setPlayMode(i8);
            } else if (i7 != 4) {
                int i10 = 5;
                if (i7 != 5) {
                    return;
                }
                if (i8 != 0) {
                    if (i8 == 1) {
                        i10 = 6;
                    } else if (i8 != 2) {
                        return;
                    } else {
                        i10 = 7;
                    }
                }
                k4.a.c(context).b(i10);
            } else {
                NewKuwoMusicManager.getInstance(context).setPlayMode(i8);
            }
        } catch (Exception e7) {
            e7.printStackTrace();
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:62:?, code lost:
    
        return false;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private static boolean dispatchMediaSessionSeek(android.content.Context r8, int r9, long r10) {
        /*
            r0 = 0
            if (r8 != 0) goto L4
            return r0
        L4:
            java.lang.String r1 = "navi_tool_prefs"
            android.content.SharedPreferences r1 = r8.getSharedPreferences(r1, r0)
            java.lang.String r9 = resolveMusicPackageForSource(r9, r1)
            boolean r1 = android.text.TextUtils.isEmpty(r9)
            if (r1 == 0) goto L15
            return r0
        L15:
            l4.e r8 = l4.e.b(r8)
            r1 = 0
            long r10 = java.lang.Math.max(r1, r10)
            r8.getClass()
            boolean r3 = android.text.TextUtils.isEmpty(r9)
            r4 = 0
            if (r3 != 0) goto L98
            android.media.session.MediaSessionManager r3 = r8.f7430b
            if (r3 != 0) goto L2f
            goto L98
        L2f:
            android.media.session.MediaController r5 = r8.f7433e     // Catch: java.lang.Throwable -> L98
            if (r5 == 0) goto L43
            java.lang.String r5 = r5.getPackageName()     // Catch: java.lang.Throwable -> L98
            boolean r5 = r9.equals(r5)     // Catch: java.lang.Throwable -> L98
            if (r5 == 0) goto L43
            android.support.v4.media.session.r r5 = r8.f7432d     // Catch: java.lang.Throwable -> L98
            if (r5 == 0) goto L43
            r4 = r5
            goto L98
        L43:
            android.content.ComponentName r5 = r8.f7431c     // Catch: java.lang.Throwable -> L98
            java.util.List r3 = r3.getActiveSessions(r5)     // Catch: java.lang.Throwable -> L98
            if (r3 == 0) goto L98
            boolean r5 = r3.isEmpty()     // Catch: java.lang.Throwable -> L98
            if (r5 == 0) goto L52
            goto L98
        L52:
            java.util.Iterator r3 = r3.iterator()     // Catch: java.lang.Throwable -> L98
            r5 = r4
        L57:
            boolean r6 = r3.hasNext()     // Catch: java.lang.Throwable -> L98
            if (r6 == 0) goto L85
            java.lang.Object r6 = r3.next()     // Catch: java.lang.Throwable -> L98
            android.media.session.MediaController r6 = (android.media.session.MediaController) r6     // Catch: java.lang.Throwable -> L98
            if (r6 == 0) goto L57
            java.lang.String r7 = r6.getPackageName()     // Catch: java.lang.Throwable -> L98
            boolean r7 = r9.equals(r7)     // Catch: java.lang.Throwable -> L98
            if (r7 != 0) goto L70
            goto L57
        L70:
            android.media.session.PlaybackState r5 = r6.getPlaybackState()     // Catch: java.lang.Throwable -> L98
            if (r5 == 0) goto L83
            android.media.session.PlaybackState r5 = r6.getPlaybackState()     // Catch: java.lang.Throwable -> L98
            int r5 = r5.getState()     // Catch: java.lang.Throwable -> L98
            r7 = 3
            if (r5 != r7) goto L83
            r5 = r6
            goto L85
        L83:
            r5 = r6
            goto L57
        L85:
            if (r5 != 0) goto L88
            goto L98
        L88:
            android.media.session.MediaSession$Token r9 = r5.getSessionToken()     // Catch: java.lang.Throwable -> L98
            android.support.v4.media.session.MediaSessionCompat$Token r9 = android.support.v4.media.session.MediaSessionCompat$Token.l(r9, r4)     // Catch: java.lang.Throwable -> L98
            android.support.v4.media.session.r r3 = new android.support.v4.media.session.r     // Catch: java.lang.Throwable -> L98
            android.content.Context r8 = r8.f7429a     // Catch: java.lang.Throwable -> L98
            r3.<init>(r8, r9)     // Catch: java.lang.Throwable -> L98
            r4 = r3
        L98:
            if (r4 != 0) goto L9b
            goto La9
        L9b:
            android.support.v4.media.session.p r8 = r4.c()
            long r9 = java.lang.Math.max(r1, r10)
            android.media.session.MediaController$TransportControls r8 = r8.f599a     // Catch: java.lang.Exception -> La9
            r8.seekTo(r9)     // Catch: java.lang.Exception -> La9
            r0 = 1
        La9:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.navi.tool.ui.extra.DesktopAssistantMusicUiHelper.dispatchMediaSessionSeek(android.content.Context, int, long):boolean");
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r9v1, types: [int] */
    /* JADX WARN: Type inference failed for: r9v2 */
    /* JADX WARN: Type inference failed for: r9v3 */
    /* JADX WARN: Type inference failed for: r9v4 */
    public static void dispatchMusicControl(Context context, SharedPreferences sharedPreferences, boolean z6, int i7) {
        if (context == null || sharedPreferences == null) {
            return;
        }
        int i8 = sharedPreferences.getInt("desktop_music_source", 0);
        ?? r9 = z6;
        if (i8 == 0) {
            if (i7 != 0) {
                r9 = i7 == 1 ? 2 : 3;
            }
            sendQQMusicAction(context, r9);
            return;
        }
        if (i8 == 1) {
            KuwoMusicManager.getInstance(context).dispatchControl(z6, i7);
            return;
        }
        int i9 = 87;
        if (i8 == 2) {
            if (i7 == 0) {
                i9 = z6 ? 127 : 126;
            } else if (i7 == 1) {
                i9 = 88;
            }
            sendMediaKey(context, i9);
            return;
        }
        if (i8 == 3) {
            NeteaseMusicManager.getInstance(context).dispatchControl(z6, i7);
            return;
        }
        if (i8 == 4) {
            NewKuwoMusicManager.getInstance(context).dispatchControl(z6, i7);
            return;
        }
        if (i8 == 5) {
            k4.a.c(context).b(i7);
            return;
        }
        if (i7 == 0) {
            i9 = z6 ? 127 : 126;
        } else if (i7 == 1) {
            i9 = 88;
        }
        sendMediaKey(context, i9);
    }

    public static void dispatchMusicSeek(Context context, SharedPreferences sharedPreferences, long j7, long j8) {
        if (context == null || sharedPreferences == null) {
            return;
        }
        int i7 = sharedPreferences.getInt("desktop_music_source", 0);
        long max = Math.max(0L, j8);
        if (isSeekSupportedSource(i7)) {
            dispatchMediaSessionSeek(context, i7, max);
            if (i7 != 2) {
                refreshMusicStateBySource(context, i7);
            }
        }
    }

    public static void downloadCoverAsync(String str, BitmapCallback bitmapCallback) {
        new Thread(new z0(12, (Object) bitmapCallback, str)).start();
    }

    public static void executeCoverRequest(CoverRequest coverRequest, CoverRequestCallback coverRequestCallback) {
        if (coverRequest == null || coverRequestCallback == null) {
            return;
        }
        int i7 = coverRequest.type;
        if (i7 == 1) {
            new Thread(new x2(coverRequest, coverRequestCallback)).start();
        } else if (i7 == 2) {
            executeMetadataCoverRequest(coverRequest, coverRequestCallback);
        }
    }

    private static void executeMetadataCoverRequest(CoverRequest coverRequest, CoverRequestCallback coverRequestCallback) {
        if (coverRequest == null || coverRequestCallback == null) {
            return;
        }
        if (!coverRequest.allowMetadataFallback) {
            applyDefaultCoverIfCurrent(coverRequest, coverRequestCallback);
            return;
        }
        coverRequestCallback.logDebug("Requesting cover for: " + coverRequest.title + " (Source ID: " + coverRequest.rid + "/" + coverRequest.hash + "/" + coverRequest.songId + ")");
        loadCoverAsync(coverRequest.source, coverRequest.title, coverRequest.artist, coverRequest.rid, coverRequest.hash, coverRequest.songId, new AnonymousClass2(coverRequestCallback, coverRequest));
    }

    private static String firstNonEmpty(String... strArr) {
        if (strArr == null) {
            return s5.h0.FRAGMENT_ENCODE_SET;
        }
        for (String str : strArr) {
            if (!TextUtils.isEmpty(str)) {
                return str;
            }
        }
        return s5.h0.FRAGMENT_ENCODE_SET;
    }

    private static String formatDuration(long j7) {
        long max = Math.max(0L, j7) / 1000;
        return String.format(Locale.getDefault(), "%02d:%02d", Long.valueOf(max / 60), Long.valueOf(max % 60));
    }

    public static List<MusicSourceCandidate> getInstalledMusicSourceCandidates(Context context) {
        ArrayList arrayList = new ArrayList();
        if (context == null) {
            return arrayList;
        }
        addCandidateIfInstalled(arrayList, context, "com.tencent.qqmusiccar", "QQ音乐车机版", true);
        addCandidateIfInstalled(arrayList, context, cn.kuwo.autosdk.m.PREFERENCE_PACKAGE, "酷我音乐车机版", true);
        addCandidateIfInstalled(arrayList, context, "com.kugou.android.auto", "酷狗音乐车机版", true);
        addCandidateIfInstalled(arrayList, context, "com.netease.cloudmusic.iot", "网易云音乐车机版", true);
        addCandidateIfInstalled(arrayList, context, "com.luna.music.car", "汽水音乐车机版", true);
        addCandidateIfInstalled(arrayList, context, "cn.toside.music.mobile", "落雪音乐", false);
        addCandidateIfInstalled(arrayList, context, "com.maxmpz.audioplayer", "Poweramp", false);
        addCandidateIfInstalled(arrayList, context, "com.tencent.qqmusic", "QQ音乐", false);
        addCandidateIfInstalled(arrayList, context, "com.netease.cloudmusic", "网易云音乐", false);
        addCandidateIfInstalled(arrayList, context, "com.kugou.android", "酷狗音乐", false);
        addCandidateIfInstalled(arrayList, context, "cn.kuwo.player", "酷我音乐", false);
        addCandidateIfInstalled(arrayList, context, "com.apple.android.music", "Apple Music", false);
        addCandidateIfInstalled(arrayList, context, "com.spotify.music", "Spotify", false);
        addCandidateIfInstalled(arrayList, context, "com.google.android.apps.youtube.music", "YouTube Music", false);
        addCandidateIfInstalled(arrayList, context, "com.aimp.player", "AIMP", false);
        addCandidateIfInstalled(arrayList, context, "in.krosbits.musicolet", "Musicolet", false);
        return arrayList;
    }

    private static String getPackageVersionName(Context context, String str) {
        if (context == null || TextUtils.isEmpty(str)) {
            return s5.h0.FRAGMENT_ENCODE_SET;
        }
        try {
            String str2 = context.getPackageManager().getPackageInfo(str, 0).versionName;
            return str2 == null ? s5.h0.FRAGMENT_ENCODE_SET : str2;
        } catch (Exception unused) {
            return s5.h0.FRAGMENT_ENCODE_SET;
        }
    }

    public static boolean isFavorite(SharedPreferences sharedPreferences) {
        if (sharedPreferences == null) {
            return false;
        }
        return sharedPreferences.getBoolean("music_is_favorite", false);
    }

    private static boolean isPackageInstalled(Context context, String str) {
        if (context != null && !TextUtils.isEmpty(str)) {
            try {
                context.getPackageManager().getPackageInfo(str, 0);
                return true;
            } catch (Exception unused) {
            }
        }
        return false;
    }

    public static boolean isSeekSupportedSource(int i7) {
        return i7 == 0 || i7 == 1 || i7 == 2 || i7 == 3 || i7 == 4 || i7 == 5 || i7 == 6;
    }

    private static boolean isUsableDirectCoverUrl(String str) {
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        try {
            Uri parse = Uri.parse(str.trim());
            String scheme = parse.getScheme();
            String host = parse.getHost();
            String path = parse.getPath();
            if (!TextUtils.isEmpty(host) && !TextUtils.isEmpty(path)) {
                if (("http".equalsIgnoreCase(scheme) || "https".equalsIgnoreCase(scheme)) && !"/".equals(path) && !"/img".equalsIgnoreCase(path) && !"/img/".equalsIgnoreCase(path)) {
                    if (path.length() <= 5) {
                        if (TextUtils.isEmpty(parse.getQuery())) {
                            return false;
                        }
                    }
                    return true;
                }
                return false;
            }
            return false;
        } catch (Exception unused) {
            return false;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ void lambda$applyDefaultCoverIfCurrent$3(CoverRequestCallback coverRequestCallback, CoverRequest coverRequest) {
        if (coverRequestCallback.isCurrentTitle(coverRequest.title) && !coverRequest.preserveCurrentCoverOnFailure) {
            coverRequestCallback.applyDefaultCover();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ void lambda$downloadCoverAsync$2(String str, BitmapCallback bitmapCallback) {
        Bitmap downloadBitmap = MusicCoverLoader.downloadBitmap(str);
        if (downloadBitmap == null || bitmapCallback == null) {
            return;
        }
        bitmapCallback.onLoaded(downloadBitmap);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ void lambda$executeCoverRequest$4(CoverRequestCallback coverRequestCallback, CoverRequest coverRequest, Bitmap bitmap) {
        if (coverRequestCallback.isCurrentTitle(coverRequest.title)) {
            coverRequestCallback.applyCoverBitmap(bitmap, false);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ void lambda$executeCoverRequest$5(CoverRequest coverRequest, CoverRequestCallback coverRequestCallback) {
        Bitmap downloadBitmap = MusicCoverLoader.downloadBitmap(coverRequest.directUrl);
        if (downloadBitmap != null) {
            coverRequestCallback.saveMusicCover(downloadBitmap);
            coverRequestCallback.runOnUiThread(new y2(coverRequestCallback, coverRequest, downloadBitmap, 1));
        } else if (coverRequest.allowMetadataFallback) {
            executeMetadataCoverRequest(coverRequest, coverRequestCallback);
        } else {
            applyDefaultCoverIfCurrent(coverRequest, coverRequestCallback);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ void lambda$refreshMusicStateBySource$0(Context context) {
        QQMusicManager.getInstance(context).queryCurrentState();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ void lambda$refreshMusicStateBySource$1(Context context) {
        KugouMusicManager.getInstance(context).refreshState();
    }

    public static void loadCoverAsync(int i7, String str, String str2, String str3, String str4, String str5, final CoverLoadCallback coverLoadCallback) {
        MusicCoverLoader.loadCover(i7, str, str2, str3, str4, str5, new MusicCoverLoader.Callback() { // from class: com.navi.tool.ui.extra.DesktopAssistantMusicUiHelper.1
            @Override // com.navi.tool.utils.MusicCoverLoader.Callback
            public void onCoverLoaded(Bitmap bitmap) {
                CoverLoadCallback coverLoadCallback2 = CoverLoadCallback.this;
                if (coverLoadCallback2 != null) {
                    coverLoadCallback2.onLoaded(bitmap);
                }
            }

            @Override // com.navi.tool.utils.MusicCoverLoader.Callback
            public void onError(String str6) {
                CoverLoadCallback coverLoadCallback2 = CoverLoadCallback.this;
                if (coverLoadCallback2 != null) {
                    coverLoadCallback2.onError(str6);
                }
            }
        });
    }

    public static void refreshMusicStateBySource(Context context, int i7) {
        int i8 = 1;
        if (i7 == 0) {
            new Handler().postDelayed(new f1(context, i8), 500L);
            return;
        }
        if (i7 == 1) {
            KuwoMusicManager.getInstance(context).refreshState();
            return;
        }
        int i9 = 2;
        if (i7 == 2) {
            new Handler().postDelayed(new f1(context, i9), 500L);
            return;
        }
        if (i7 == 3) {
            NeteaseMusicManager.getInstance(context).refreshState();
            return;
        }
        if (i7 == 4) {
            NewKuwoMusicManager.getInstance(context).refreshState();
        } else if (i7 == 5) {
            k4.a.c(context).f();
        } else if (i7 == 6) {
            l4.e.b(context).c();
        }
    }

    public static Drawable resolveAppIcon(Context context, String str) {
        if (context != null && !TextUtils.isEmpty(str)) {
            try {
                return context.getPackageManager().getApplicationIcon(str);
            } catch (Exception unused) {
            }
        }
        return null;
    }

    public static String resolveAppLabel(Context context, String str) {
        if (context == null || TextUtils.isEmpty(str)) {
            return null;
        }
        try {
            PackageManager packageManager = context.getPackageManager();
            CharSequence applicationLabel = packageManager.getApplicationLabel(packageManager.getApplicationInfo(str, 0));
            if (applicationLabel == null) {
                return null;
            }
            return applicationLabel.toString();
        } catch (Exception unused) {
            return null;
        }
    }

    private static int resolveCoverSource(h4.m mVar, SharedPreferences sharedPreferences) {
        if (mVar.f6768l == 5) {
            return 3;
        }
        return sharedPreferences.getInt("desktop_music_source", 0);
    }

    public static String resolveMusicPackageForSource(int i7, SharedPreferences sharedPreferences) {
        if (i7 == 0) {
            return "com.tencent.qqmusiccar";
        }
        if (i7 == 1 || i7 == 4) {
            return cn.kuwo.autosdk.m.PREFERENCE_PACKAGE;
        }
        if (i7 == 2) {
            return "com.kugou.android.auto";
        }
        if (i7 == 3) {
            return "com.netease.cloudmusic.iot";
        }
        if (i7 == 5) {
            return "com.luna.music.car";
        }
        if (i7 != 6 || sharedPreferences == null) {
            return null;
        }
        return sharedPreferences.getString("desktop_music_package", null);
    }

    public static String resolveMusicSourceDisplayName(int i7) {
        switch (i7) {
            case 0:
                return "QQ音乐";
            case 1:
                return "酷我音乐";
            case 2:
                return "酷狗音乐";
            case 3:
                return "网易云音乐";
            case 4:
                return "新版酷我";
            case 5:
                return "汽水音乐";
            case 6:
                return "通用音源";
            default:
                return "当前音源";
        }
    }

    public static String resolveMusicSourceDisplayName(Context context, int i7, SharedPreferences sharedPreferences) {
        if (i7 == 6 && context != null && sharedPreferences != null) {
            String resolveAppLabel = resolveAppLabel(context, sharedPreferences.getString("desktop_music_package", null));
            if (!TextUtils.isEmpty(resolveAppLabel)) {
                return resolveAppLabel;
            }
        }
        return resolveMusicSourceDisplayName(i7);
    }

    public static int resolveMusicSourceFromPackage(Context context, String str) {
        if ("com.tencent.qqmusiccar".equals(str)) {
            return 0;
        }
        if (cn.kuwo.autosdk.m.PREFERENCE_PACKAGE.equals(str)) {
            String packageVersionName = getPackageVersionName(context, str);
            if (packageVersionName.startsWith("6")) {
                return 1;
            }
            return packageVersionName.startsWith("7") ? 4 : 6;
        }
        if ("com.kugou.android.auto".equals(str)) {
            return 2;
        }
        if ("com.netease.cloudmusic.iot".equals(str)) {
            return 3;
        }
        return "com.luna.music.car".equals(str) ? 5 : 6;
    }

    public static void sendMediaKey(Context context, int i7) {
        AudioManager audioManager = (AudioManager) context.getSystemService("audio");
        if (audioManager != null) {
            audioManager.dispatchMediaKeyEvent(new KeyEvent(0, i7));
            audioManager.dispatchMediaKeyEvent(new KeyEvent(1, i7));
        }
    }

    public static void sendQQMusicAction(Context context, int i7) {
        sendQQMusicActionViaUri(context, i7, -1);
    }

    public static void sendQQMusicAction(Context context, int i7, int i8) {
        sendQQMusicActionViaUri(context, i7, i8);
    }

    private static void sendQQMusicActionViaUri(Context context, int i7, int i8) {
        try {
            StringBuilder sb = new StringBuilder("qqmusiccar://qqmusiccar?action=20&m0=");
            sb.append(i7);
            if (i8 >= 0) {
                sb.append("&m1=");
                sb.append(i8);
            }
            Intent intent = new Intent("com.tencent.qqmusiccar.action");
            intent.setClassName("com.tencent.qqmusiccar", "com.tencent.qqmusiccar.app.reciver.BroadcastReceiverCenterForThird");
            intent.setData(Uri.parse(sb.toString()));
            context.sendBroadcast(intent);
        } catch (Exception e7) {
            e7.printStackTrace();
        }
    }

    public static void startMusicManagerBySource(Context context, int i7, LyricsAdapter lyricsAdapter) {
        if (context == null) {
        }
        switch (i7) {
            case 0:
                QQMusicManager.getInstance(context).start();
                Map<Long, String> lyrics = QQMusicLyricReceiver.getLyrics();
                if (lyrics != null && !lyrics.isEmpty() && lyricsAdapter != null) {
                    lyricsAdapter.setLyrics(new TreeMap<>(lyrics));
                    break;
                }
                break;
            case 1:
                KuwoMusicManager.getInstance(context).start();
                break;
            case 2:
                KugouMusicManager.getInstance(context).start();
                break;
            case 3:
                NeteaseMusicManager.getInstance(context).start();
                break;
            case 4:
                NewKuwoMusicManager.getInstance(context).start();
                break;
            case 5:
                k4.a.c(context).g();
                break;
            case 6:
                l4.e.b(context).d();
                break;
        }
    }

    public static void stopAllMusicServices(Activity activity, TextView textView, TextView textView2, TextView textView3, TextView textView4, LyricsAdapter lyricsAdapter, ImageButton imageButton, SpectrumView spectrumView) {
        try {
            QQMusicManager.getInstance(activity).stop();
            KugouMusicManager.getInstance(activity).stop();
            KuwoMusicManager.getInstance(activity).stop();
            NewKuwoMusicManager.getInstance(activity).stop();
            k4.a.c(activity).h();
            NeteaseMusicManager.getInstance(activity).stop();
            l4.e.b(activity).e();
        } catch (Exception e7) {
            e7.printStackTrace();
        }
        if (textView != null) {
            textView.setText("暂无音乐播放");
        }
        if (textView2 != null) {
            textView2.setText("未知歌手");
        }
        if (textView3 != null) {
            textView3.setText("00:00");
        }
        if (textView4 != null) {
            textView4.setText("00:00");
        }
        if (lyricsAdapter != null) {
            lyricsAdapter.setLyrics(null);
            lyricsAdapter.notifyDataSetChanged();
        }
        if (imageButton != null) {
            imageButton.setImageResource(g4.o.ic_play);
        }
        if (spectrumView != null) {
            spectrumView.f();
        }
    }

    public static void stopMusicManagers(Context context) {
        if (context == null) {
            return;
        }
        try {
            QQMusicManager.getInstance(context).stop();
            KugouMusicManager.getInstance(context).stop();
            KuwoMusicManager.getInstance(context).stop();
            NewKuwoMusicManager.getInstance(context).stop();
            k4.a.c(context).h();
            NeteaseMusicManager.getInstance(context).stop();
            l4.e.b(context).e();
        } catch (Exception e7) {
            e7.printStackTrace();
        }
    }

    public static boolean switchMusicSource(Context context, SharedPreferences sharedPreferences, int i7, String str) {
        if (context == null || sharedPreferences == null) {
            return false;
        }
        if (TextUtils.isEmpty(str)) {
            str = resolveMusicPackageForSource(i7, sharedPreferences);
        }
        if (i7 == 6 && TextUtils.isEmpty(str)) {
            return false;
        }
        sharedPreferences.edit().putInt("desktop_music_source", i7).putInt(FloatingWindowService.KEY_MUSIC_SOURCE, i7).putString("desktop_music_package", str).apply();
        context.sendBroadcast(new Intent(ACTION_MUSIC_APP_UPDATED));
        a.a.w(ACTION_MUSIC_UI_UPDATED, context);
        return true;
    }

    public static void toggleFavorite(Context context, boolean z6, SharedPreferences sharedPreferences, FavoriteCallback favoriteCallback) {
        if (context == null || sharedPreferences == null) {
            if (favoriteCallback != null) {
                favoriteCallback.onError("上下文为空");
                return;
            }
            return;
        }
        int i7 = sharedPreferences.getInt("desktop_music_source", 0);
        int i8 = 5;
        try {
            if (i7 != 0) {
                if (i7 != 1) {
                    int i9 = 3;
                    if (i7 == 3) {
                        toggleNeteaseFavorite(context, z6, sharedPreferences, favoriteCallback);
                    } else if (i7 != 4) {
                        if (i7 == 5) {
                            k4.a c7 = k4.a.c(context);
                            if (!z6) {
                                i9 = 4;
                            }
                            c7.b(i9);
                            if (favoriteCallback != null) {
                                favoriteCallback.onSuccess(z6);
                            }
                        } else if (favoriteCallback == null) {
                            return;
                        } else {
                            favoriteCallback.onError("当前音源不支持收藏");
                        }
                    }
                }
                toggleKuwoFavorite(context, z6, sharedPreferences, favoriteCallback);
            } else {
                if (!z6) {
                    i8 = 6;
                }
                sendQQMusicAction(context, i8);
                if (favoriteCallback != null) {
                    favoriteCallback.onSuccess(z6);
                }
            }
        } catch (Exception e7) {
            if (favoriteCallback != null) {
                favoriteCallback.onError("操作异常: " + e7.getMessage());
            }
        }
    }

    private static void toggleKuwoFavorite(Context context, boolean z6, SharedPreferences sharedPreferences, final FavoriteCallback favoriteCallback) {
        KuwoMusicManager kuwoMusicManager = KuwoMusicManager.getInstance(context);
        if (kuwoMusicManager == null) {
            if (favoriteCallback != null) {
                favoriteCallback.onError("酷我服务未初始化");
                return;
            }
            return;
        }
        try {
            kuwoMusicManager.toggleFavorite(z6, new FavoriteCallback() { // from class: com.navi.tool.ui.extra.DesktopAssistantMusicUiHelper.3
                @Override // com.navi.tool.ui.extra.DesktopAssistantMusicUiHelper.FavoriteCallback
                public void onError(String str) {
                    FavoriteCallback favoriteCallback2 = FavoriteCallback.this;
                    if (favoriteCallback2 != null) {
                        favoriteCallback2.onError(str);
                    }
                }

                @Override // com.navi.tool.ui.extra.DesktopAssistantMusicUiHelper.FavoriteCallback
                public void onSuccess(boolean z7) {
                    FavoriteCallback favoriteCallback2 = FavoriteCallback.this;
                    if (favoriteCallback2 != null) {
                        favoriteCallback2.onSuccess(z7);
                    }
                }
            });
        } catch (Exception e7) {
            if (favoriteCallback != null) {
                favoriteCallback.onError("酷我收藏失败: " + e7.getMessage());
            }
        }
    }

    private static void toggleNeteaseFavorite(Context context, boolean z6, SharedPreferences sharedPreferences, FavoriteCallback favoriteCallback) {
        NeteaseMusicManager neteaseMusicManager = NeteaseMusicManager.getInstance(context);
        if (neteaseMusicManager == null) {
            if (favoriteCallback != null) {
                favoriteCallback.onError("网易云服务未初始化");
                return;
            }
            return;
        }
        if (!neteaseMusicManager.isControllerReady()) {
            if (favoriteCallback != null) {
                favoriteCallback.onError("网易云未连接，请先播放歌曲");
                return;
            }
            return;
        }
        try {
            if (z6) {
                neteaseMusicManager.favoriteCurrentSong();
            } else {
                neteaseMusicManager.unfavoriteCurrentSong();
            }
            if (favoriteCallback != null) {
                favoriteCallback.onSuccess(z6);
            }
        } catch (Exception e7) {
            if (favoriteCallback != null) {
                favoriteCallback.onError("网易云收藏失败: " + e7.getMessage());
            }
        }
    }

    public static void updateLyricsColor(SharedPreferences sharedPreferences, LyricsAdapter lyricsAdapter) {
        if (lyricsAdapter == null) {
            return;
        }
        int i7 = sharedPreferences.getInt("desktop_lyrics_color_index", 0);
        int i8 = -1;
        if (i7 != 0) {
            if (i7 == 1) {
                i8 = -256;
            } else if (i7 == 2) {
                i8 = -16711936;
            } else if (i7 == 3) {
                i8 = -16728065;
            } else if (i7 == 4) {
                i8 = -47872;
            } else if (i7 == 5) {
                i8 = p0.d1.MEASURED_STATE_MASK;
            }
        }
        lyricsAdapter.setHighlightColor(i8);
        lyricsAdapter.setNormalColor(-3355444);
    }

    public static void updateLyricsProgress(LyricsAdapter lyricsAdapter, RecyclerView recyclerView, androidx.recyclerview.widget.x0 x0Var, long j7) {
        updateLyricsProgress(lyricsAdapter, recyclerView, x0Var, j7, false);
    }

    public static void updateLyricsProgress(LyricsAdapter lyricsAdapter, RecyclerView recyclerView, androidx.recyclerview.widget.x0 x0Var, long j7, boolean z6) {
        int updateProgress;
        androidx.recyclerview.widget.r1 layoutManager;
        if (lyricsAdapter == null || (updateProgress = lyricsAdapter.updateProgress(j7)) == -1 || recyclerView == null || (layoutManager = recyclerView.getLayoutManager()) == null) {
            return;
        }
        if (!z6) {
            x0Var.setTargetPosition(updateProgress);
            layoutManager.D0(x0Var);
            return;
        }
        recyclerView.j0();
        if (!(layoutManager instanceof LinearLayoutManager)) {
            layoutManager.s0(updateProgress);
            return;
        }
        LinearLayoutManager linearLayoutManager = (LinearLayoutManager) layoutManager;
        View childAt = recyclerView.getChildAt(0);
        linearLayoutManager.f1(updateProgress, Math.max(0, (recyclerView.getHeight() - (childAt != null ? childAt.getHeight() : 0)) / 2));
    }

    public static void updateMusicUI(Context context, SharedPreferences sharedPreferences, boolean z6, boolean z7, View view, CardView cardView, Runnable runnable, Runnable runnable2) {
        if (z6) {
            boolean z8 = sharedPreferences.getBoolean("desktop_music_show_album_art", false);
            int i7 = sharedPreferences.getInt("desktop_music_album_art_mode", 0);
            boolean z9 = sharedPreferences.getBoolean("desktop_music_album_art_anim", false);
            if (view != null) {
                view.setVisibility(z8 ? 0 : 8);
            }
            if (!z8) {
                if (cardView != null) {
                    cardView.setVisibility(8);
                }
                if (runnable2 != null) {
                    runnable2.run();
                    return;
                }
                return;
            }
            if (cardView != null) {
                cardView.setVisibility(0);
                float f7 = context.getResources().getDisplayMetrics().density;
                if (i7 != 1) {
                    cardView.setRadius(f7 * 12.0f);
                    cardView.setClipToOutline(true);
                    if (runnable2 != null) {
                        runnable2.run();
                    }
                    cardView.setRotation(CropImageView.DEFAULT_ASPECT_RATIO);
                    return;
                }
                cardView.setRadius(f7 * 70.0f);
                cardView.setClipToOutline(true);
                if (z9 && z7) {
                    if (runnable != null) {
                        runnable.run();
                    }
                } else if (runnable2 != null) {
                    runnable2.run();
                }
            }
        }
    }

    public static void updatePlaybackVisualState(SharedPreferences sharedPreferences, boolean z6, ImageButton imageButton, SpectrumView spectrumView, Runnable runnable, Runnable runnable2) {
        if (imageButton != null) {
            imageButton.setImageResource(z6 ? g4.o.ic_pause : g4.o.ic_play);
        }
        if (sharedPreferences.getBoolean("desktop_music_show_album_art", false) && sharedPreferences.getInt("desktop_music_album_art_mode", 0) == 1 && sharedPreferences.getBoolean("desktop_music_album_art_anim", false)) {
            if (z6) {
                if (runnable != null) {
                    runnable.run();
                }
            } else if (runnable2 != null) {
                runnable2.run();
            }
        }
        if (spectrumView != null) {
            if (z6) {
                spectrumView.e();
            } else {
                spectrumView.f();
            }
        }
    }

    public static long updateProgressBarAndDuration(View view, TextView textView, TextView textView2, long j7, long j8, String str, String str2, long j9) {
        return updateProgressBarAndDuration(view, textView, textView2, j7, j8, str, str2, j9, true);
    }

    public static long updateProgressBarAndDuration(View view, TextView textView, TextView textView2, long j7, long j8, String str, String str2, long j9, boolean z6) {
        if (view == null) {
            return j9;
        }
        long j10 = (str == null || str.equals(str2)) ? j9 : 0L;
        if (j7 > 0 && (j7 >= 3600 || (j7 >= 10000 && j8 <= j7 * 100))) {
            j10 = j7;
        }
        long j11 = (j7 <= 0 || j7 >= 3600 || (j7 >= 10000 && j8 <= j7 * 100)) ? j7 : j7 * 1000;
        if (j11 > 0) {
            j10 = j11;
        }
        if (j11 <= 0 && j10 > 0) {
            j11 = j10;
        }
        float f7 = CropImageView.DEFAULT_ASPECT_RATIO;
        float f8 = j11 >= 1000 ? j8 / j11 : 0.0f;
        if (f8 > 1.0f) {
            f8 = 1.0f;
        }
        if (f8 >= CropImageView.DEFAULT_ASPECT_RATIO) {
            f7 = f8;
        }
        if (view instanceof GlowProgressView) {
            ((GlowProgressView) view).setProgress(f7, z6);
        } else {
            a0.f fVar = (a0.f) view.getLayoutParams();
            if (Math.abs(fVar.R - f7) > 0.001f) {
                fVar.R = f7;
                view.setLayoutParams(fVar);
            }
        }
        if (textView != null) {
            textView.setText(formatDuration(j8));
        }
        if (textView2 != null) {
            textView2.setText(formatDuration(j11));
        }
        return j10;
    }
}
