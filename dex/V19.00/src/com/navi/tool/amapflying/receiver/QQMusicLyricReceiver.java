package com.navi.tool.amapflying.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import h4.k;
import i6.e;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;
import java.util.regex.Pattern;
import s5.h0;
import y0.p;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public class QQMusicLyricReceiver extends BroadcastReceiver {
    private static final String ACTION_QQ_MUSIC_CHANGED = "com.tencent.qqmusiccar.ACTION_PLAYSONG_CHANGEDQQMusicCar";
    private static final String ACTION_QQ_MUSIC_COMMAND = "com.tencent.qqmusiccar.action.PLAY_COMMAND_SEND_FOR_THIRD";
    private static final String EXTRA_COMMAND_DATA = "com.tencent.qqmusiccar.EXTRA_COMMAND_DATA";
    private static final String TAG = "QQMusicLyricReceiver";
    private static final Pattern LRC_PATTERN = Pattern.compile("\\[(\\d{1,2}):(\\d{1,2})\\.(\\d{1,3})\\](.*)");
    private static final Pattern TAG_PATTERN = Pattern.compile("\\[(ti|ar|al):(.+)\\]");
    private static final TreeMap<Long, String> sLyricMap = new TreeMap<>();
    private static String sLastPostedLyric = h0.FRAGMENT_ENCODE_SET;
    private static String sCurrentTitle = h0.FRAGMENT_ENCODE_SET;
    private static String sCurrentArtist = h0.FRAGMENT_ENCODE_SET;
    private static String sCurrentCoverUrl = h0.FRAGMENT_ENCODE_SET;
    private static long sTotalDuration = 0;
    private static final Handler sHandler = new Handler(Looper.getMainLooper());
    private static boolean sIsPlaying = false;
    private static long sSongStartTime = 0;
    private static boolean sIsFavorite = false;
    public static int sPlayMode = -1;
    private static boolean sIsDoubleLine = false;
    private static final Runnable sUpdateTask = new p(3);

    public static Map<Long, String> getLyrics() {
        TreeMap treeMap;
        TreeMap<Long, String> treeMap2 = sLyricMap;
        synchronized (treeMap2) {
            treeMap = new TreeMap((SortedMap) treeMap2);
        }
        return treeMap;
    }

    /* JADX WARN: Removed duplicated region for block: B:103:0x026a  */
    /* JADX WARN: Removed duplicated region for block: B:152:0x00fb A[Catch: Exception -> 0x009f, TRY_ENTER, TRY_LEAVE, TryCatch #1 {Exception -> 0x009f, blocks: (B:14:0x003a, B:126:0x0093, B:128:0x0097, B:131:0x00ae, B:135:0x00bf, B:138:0x00c9, B:142:0x00d5, B:152:0x00fb, B:155:0x0105, B:158:0x010f, B:161:0x0117, B:165:0x0120, B:168:0x012a, B:170:0x0131, B:172:0x0139, B:175:0x0144, B:178:0x0149, B:180:0x0151, B:183:0x015c, B:187:0x0163, B:189:0x016b, B:193:0x017a, B:195:0x0181, B:17:0x0196, B:19:0x01a2, B:21:0x01b7, B:24:0x01ca, B:26:0x01d4, B:28:0x01fb, B:29:0x01ff, B:31:0x0205, B:32:0x0209, B:34:0x020f, B:42:0x022f, B:44:0x0235, B:45:0x0239, B:47:0x023f, B:48:0x0245, B:50:0x024b, B:51:0x024d, B:53:0x0253, B:54:0x0255, B:56:0x025b, B:59:0x0266, B:61:0x026d, B:63:0x0273, B:66:0x027e, B:68:0x0285, B:70:0x028d, B:73:0x0298, B:77:0x029f, B:79:0x02a7, B:82:0x02b1, B:84:0x02bd, B:85:0x02ca, B:87:0x02cf, B:89:0x02d3, B:91:0x02e9, B:93:0x02f1, B:95:0x0308, B:97:0x030d, B:107:0x022b, B:108:0x0313, B:110:0x031d, B:117:0x033e, B:37:0x0214, B:39:0x021a, B:41:0x0220), top: B:13:0x003a, inners: #2 }] */
    /* JADX WARN: Removed duplicated region for block: B:155:0x0105 A[Catch: Exception -> 0x009f, TRY_ENTER, TRY_LEAVE, TryCatch #1 {Exception -> 0x009f, blocks: (B:14:0x003a, B:126:0x0093, B:128:0x0097, B:131:0x00ae, B:135:0x00bf, B:138:0x00c9, B:142:0x00d5, B:152:0x00fb, B:155:0x0105, B:158:0x010f, B:161:0x0117, B:165:0x0120, B:168:0x012a, B:170:0x0131, B:172:0x0139, B:175:0x0144, B:178:0x0149, B:180:0x0151, B:183:0x015c, B:187:0x0163, B:189:0x016b, B:193:0x017a, B:195:0x0181, B:17:0x0196, B:19:0x01a2, B:21:0x01b7, B:24:0x01ca, B:26:0x01d4, B:28:0x01fb, B:29:0x01ff, B:31:0x0205, B:32:0x0209, B:34:0x020f, B:42:0x022f, B:44:0x0235, B:45:0x0239, B:47:0x023f, B:48:0x0245, B:50:0x024b, B:51:0x024d, B:53:0x0253, B:54:0x0255, B:56:0x025b, B:59:0x0266, B:61:0x026d, B:63:0x0273, B:66:0x027e, B:68:0x0285, B:70:0x028d, B:73:0x0298, B:77:0x029f, B:79:0x02a7, B:82:0x02b1, B:84:0x02bd, B:85:0x02ca, B:87:0x02cf, B:89:0x02d3, B:91:0x02e9, B:93:0x02f1, B:95:0x0308, B:97:0x030d, B:107:0x022b, B:108:0x0313, B:110:0x031d, B:117:0x033e, B:37:0x0214, B:39:0x021a, B:41:0x0220), top: B:13:0x003a, inners: #2 }] */
    /* JADX WARN: Removed duplicated region for block: B:158:0x010f A[Catch: Exception -> 0x009f, TRY_ENTER, TRY_LEAVE, TryCatch #1 {Exception -> 0x009f, blocks: (B:14:0x003a, B:126:0x0093, B:128:0x0097, B:131:0x00ae, B:135:0x00bf, B:138:0x00c9, B:142:0x00d5, B:152:0x00fb, B:155:0x0105, B:158:0x010f, B:161:0x0117, B:165:0x0120, B:168:0x012a, B:170:0x0131, B:172:0x0139, B:175:0x0144, B:178:0x0149, B:180:0x0151, B:183:0x015c, B:187:0x0163, B:189:0x016b, B:193:0x017a, B:195:0x0181, B:17:0x0196, B:19:0x01a2, B:21:0x01b7, B:24:0x01ca, B:26:0x01d4, B:28:0x01fb, B:29:0x01ff, B:31:0x0205, B:32:0x0209, B:34:0x020f, B:42:0x022f, B:44:0x0235, B:45:0x0239, B:47:0x023f, B:48:0x0245, B:50:0x024b, B:51:0x024d, B:53:0x0253, B:54:0x0255, B:56:0x025b, B:59:0x0266, B:61:0x026d, B:63:0x0273, B:66:0x027e, B:68:0x0285, B:70:0x028d, B:73:0x0298, B:77:0x029f, B:79:0x02a7, B:82:0x02b1, B:84:0x02bd, B:85:0x02ca, B:87:0x02cf, B:89:0x02d3, B:91:0x02e9, B:93:0x02f1, B:95:0x0308, B:97:0x030d, B:107:0x022b, B:108:0x0313, B:110:0x031d, B:117:0x033e, B:37:0x0214, B:39:0x021a, B:41:0x0220), top: B:13:0x003a, inners: #2 }] */
    /* JADX WARN: Removed duplicated region for block: B:161:0x0117 A[Catch: Exception -> 0x009f, TRY_ENTER, TRY_LEAVE, TryCatch #1 {Exception -> 0x009f, blocks: (B:14:0x003a, B:126:0x0093, B:128:0x0097, B:131:0x00ae, B:135:0x00bf, B:138:0x00c9, B:142:0x00d5, B:152:0x00fb, B:155:0x0105, B:158:0x010f, B:161:0x0117, B:165:0x0120, B:168:0x012a, B:170:0x0131, B:172:0x0139, B:175:0x0144, B:178:0x0149, B:180:0x0151, B:183:0x015c, B:187:0x0163, B:189:0x016b, B:193:0x017a, B:195:0x0181, B:17:0x0196, B:19:0x01a2, B:21:0x01b7, B:24:0x01ca, B:26:0x01d4, B:28:0x01fb, B:29:0x01ff, B:31:0x0205, B:32:0x0209, B:34:0x020f, B:42:0x022f, B:44:0x0235, B:45:0x0239, B:47:0x023f, B:48:0x0245, B:50:0x024b, B:51:0x024d, B:53:0x0253, B:54:0x0255, B:56:0x025b, B:59:0x0266, B:61:0x026d, B:63:0x0273, B:66:0x027e, B:68:0x0285, B:70:0x028d, B:73:0x0298, B:77:0x029f, B:79:0x02a7, B:82:0x02b1, B:84:0x02bd, B:85:0x02ca, B:87:0x02cf, B:89:0x02d3, B:91:0x02e9, B:93:0x02f1, B:95:0x0308, B:97:0x030d, B:107:0x022b, B:108:0x0313, B:110:0x031d, B:117:0x033e, B:37:0x0214, B:39:0x021a, B:41:0x0220), top: B:13:0x003a, inners: #2 }] */
    /* JADX WARN: Removed duplicated region for block: B:164:0x011f  */
    /* JADX WARN: Removed duplicated region for block: B:174:0x0141  */
    /* JADX WARN: Removed duplicated region for block: B:176:0x0143  */
    /* JADX WARN: Removed duplicated region for block: B:182:0x0159  */
    /* JADX WARN: Removed duplicated region for block: B:184:0x015b  */
    /* JADX WARN: Removed duplicated region for block: B:192:0x0178  */
    /* JADX WARN: Removed duplicated region for block: B:198:0x018d  */
    /* JADX WARN: Removed duplicated region for block: B:202:0x012e  */
    /* JADX WARN: Removed duplicated region for block: B:44:0x0235 A[Catch: Exception -> 0x009f, TryCatch #1 {Exception -> 0x009f, blocks: (B:14:0x003a, B:126:0x0093, B:128:0x0097, B:131:0x00ae, B:135:0x00bf, B:138:0x00c9, B:142:0x00d5, B:152:0x00fb, B:155:0x0105, B:158:0x010f, B:161:0x0117, B:165:0x0120, B:168:0x012a, B:170:0x0131, B:172:0x0139, B:175:0x0144, B:178:0x0149, B:180:0x0151, B:183:0x015c, B:187:0x0163, B:189:0x016b, B:193:0x017a, B:195:0x0181, B:17:0x0196, B:19:0x01a2, B:21:0x01b7, B:24:0x01ca, B:26:0x01d4, B:28:0x01fb, B:29:0x01ff, B:31:0x0205, B:32:0x0209, B:34:0x020f, B:42:0x022f, B:44:0x0235, B:45:0x0239, B:47:0x023f, B:48:0x0245, B:50:0x024b, B:51:0x024d, B:53:0x0253, B:54:0x0255, B:56:0x025b, B:59:0x0266, B:61:0x026d, B:63:0x0273, B:66:0x027e, B:68:0x0285, B:70:0x028d, B:73:0x0298, B:77:0x029f, B:79:0x02a7, B:82:0x02b1, B:84:0x02bd, B:85:0x02ca, B:87:0x02cf, B:89:0x02d3, B:91:0x02e9, B:93:0x02f1, B:95:0x0308, B:97:0x030d, B:107:0x022b, B:108:0x0313, B:110:0x031d, B:117:0x033e, B:37:0x0214, B:39:0x021a, B:41:0x0220), top: B:13:0x003a, inners: #2 }] */
    /* JADX WARN: Removed duplicated region for block: B:47:0x023f A[Catch: Exception -> 0x009f, TryCatch #1 {Exception -> 0x009f, blocks: (B:14:0x003a, B:126:0x0093, B:128:0x0097, B:131:0x00ae, B:135:0x00bf, B:138:0x00c9, B:142:0x00d5, B:152:0x00fb, B:155:0x0105, B:158:0x010f, B:161:0x0117, B:165:0x0120, B:168:0x012a, B:170:0x0131, B:172:0x0139, B:175:0x0144, B:178:0x0149, B:180:0x0151, B:183:0x015c, B:187:0x0163, B:189:0x016b, B:193:0x017a, B:195:0x0181, B:17:0x0196, B:19:0x01a2, B:21:0x01b7, B:24:0x01ca, B:26:0x01d4, B:28:0x01fb, B:29:0x01ff, B:31:0x0205, B:32:0x0209, B:34:0x020f, B:42:0x022f, B:44:0x0235, B:45:0x0239, B:47:0x023f, B:48:0x0245, B:50:0x024b, B:51:0x024d, B:53:0x0253, B:54:0x0255, B:56:0x025b, B:59:0x0266, B:61:0x026d, B:63:0x0273, B:66:0x027e, B:68:0x0285, B:70:0x028d, B:73:0x0298, B:77:0x029f, B:79:0x02a7, B:82:0x02b1, B:84:0x02bd, B:85:0x02ca, B:87:0x02cf, B:89:0x02d3, B:91:0x02e9, B:93:0x02f1, B:95:0x0308, B:97:0x030d, B:107:0x022b, B:108:0x0313, B:110:0x031d, B:117:0x033e, B:37:0x0214, B:39:0x021a, B:41:0x0220), top: B:13:0x003a, inners: #2 }] */
    /* JADX WARN: Removed duplicated region for block: B:50:0x024b A[Catch: Exception -> 0x009f, TryCatch #1 {Exception -> 0x009f, blocks: (B:14:0x003a, B:126:0x0093, B:128:0x0097, B:131:0x00ae, B:135:0x00bf, B:138:0x00c9, B:142:0x00d5, B:152:0x00fb, B:155:0x0105, B:158:0x010f, B:161:0x0117, B:165:0x0120, B:168:0x012a, B:170:0x0131, B:172:0x0139, B:175:0x0144, B:178:0x0149, B:180:0x0151, B:183:0x015c, B:187:0x0163, B:189:0x016b, B:193:0x017a, B:195:0x0181, B:17:0x0196, B:19:0x01a2, B:21:0x01b7, B:24:0x01ca, B:26:0x01d4, B:28:0x01fb, B:29:0x01ff, B:31:0x0205, B:32:0x0209, B:34:0x020f, B:42:0x022f, B:44:0x0235, B:45:0x0239, B:47:0x023f, B:48:0x0245, B:50:0x024b, B:51:0x024d, B:53:0x0253, B:54:0x0255, B:56:0x025b, B:59:0x0266, B:61:0x026d, B:63:0x0273, B:66:0x027e, B:68:0x0285, B:70:0x028d, B:73:0x0298, B:77:0x029f, B:79:0x02a7, B:82:0x02b1, B:84:0x02bd, B:85:0x02ca, B:87:0x02cf, B:89:0x02d3, B:91:0x02e9, B:93:0x02f1, B:95:0x0308, B:97:0x030d, B:107:0x022b, B:108:0x0313, B:110:0x031d, B:117:0x033e, B:37:0x0214, B:39:0x021a, B:41:0x0220), top: B:13:0x003a, inners: #2 }] */
    /* JADX WARN: Removed duplicated region for block: B:53:0x0253 A[Catch: Exception -> 0x009f, TryCatch #1 {Exception -> 0x009f, blocks: (B:14:0x003a, B:126:0x0093, B:128:0x0097, B:131:0x00ae, B:135:0x00bf, B:138:0x00c9, B:142:0x00d5, B:152:0x00fb, B:155:0x0105, B:158:0x010f, B:161:0x0117, B:165:0x0120, B:168:0x012a, B:170:0x0131, B:172:0x0139, B:175:0x0144, B:178:0x0149, B:180:0x0151, B:183:0x015c, B:187:0x0163, B:189:0x016b, B:193:0x017a, B:195:0x0181, B:17:0x0196, B:19:0x01a2, B:21:0x01b7, B:24:0x01ca, B:26:0x01d4, B:28:0x01fb, B:29:0x01ff, B:31:0x0205, B:32:0x0209, B:34:0x020f, B:42:0x022f, B:44:0x0235, B:45:0x0239, B:47:0x023f, B:48:0x0245, B:50:0x024b, B:51:0x024d, B:53:0x0253, B:54:0x0255, B:56:0x025b, B:59:0x0266, B:61:0x026d, B:63:0x0273, B:66:0x027e, B:68:0x0285, B:70:0x028d, B:73:0x0298, B:77:0x029f, B:79:0x02a7, B:82:0x02b1, B:84:0x02bd, B:85:0x02ca, B:87:0x02cf, B:89:0x02d3, B:91:0x02e9, B:93:0x02f1, B:95:0x0308, B:97:0x030d, B:107:0x022b, B:108:0x0313, B:110:0x031d, B:117:0x033e, B:37:0x0214, B:39:0x021a, B:41:0x0220), top: B:13:0x003a, inners: #2 }] */
    /* JADX WARN: Removed duplicated region for block: B:56:0x025b A[Catch: Exception -> 0x009f, TryCatch #1 {Exception -> 0x009f, blocks: (B:14:0x003a, B:126:0x0093, B:128:0x0097, B:131:0x00ae, B:135:0x00bf, B:138:0x00c9, B:142:0x00d5, B:152:0x00fb, B:155:0x0105, B:158:0x010f, B:161:0x0117, B:165:0x0120, B:168:0x012a, B:170:0x0131, B:172:0x0139, B:175:0x0144, B:178:0x0149, B:180:0x0151, B:183:0x015c, B:187:0x0163, B:189:0x016b, B:193:0x017a, B:195:0x0181, B:17:0x0196, B:19:0x01a2, B:21:0x01b7, B:24:0x01ca, B:26:0x01d4, B:28:0x01fb, B:29:0x01ff, B:31:0x0205, B:32:0x0209, B:34:0x020f, B:42:0x022f, B:44:0x0235, B:45:0x0239, B:47:0x023f, B:48:0x0245, B:50:0x024b, B:51:0x024d, B:53:0x0253, B:54:0x0255, B:56:0x025b, B:59:0x0266, B:61:0x026d, B:63:0x0273, B:66:0x027e, B:68:0x0285, B:70:0x028d, B:73:0x0298, B:77:0x029f, B:79:0x02a7, B:82:0x02b1, B:84:0x02bd, B:85:0x02ca, B:87:0x02cf, B:89:0x02d3, B:91:0x02e9, B:93:0x02f1, B:95:0x0308, B:97:0x030d, B:107:0x022b, B:108:0x0313, B:110:0x031d, B:117:0x033e, B:37:0x0214, B:39:0x021a, B:41:0x0220), top: B:13:0x003a, inners: #2 }] */
    /* JADX WARN: Removed duplicated region for block: B:72:0x0295  */
    /* JADX WARN: Removed duplicated region for block: B:74:0x0297  */
    /* JADX WARN: Removed duplicated region for block: B:82:0x02b1 A[Catch: Exception -> 0x009f, TryCatch #1 {Exception -> 0x009f, blocks: (B:14:0x003a, B:126:0x0093, B:128:0x0097, B:131:0x00ae, B:135:0x00bf, B:138:0x00c9, B:142:0x00d5, B:152:0x00fb, B:155:0x0105, B:158:0x010f, B:161:0x0117, B:165:0x0120, B:168:0x012a, B:170:0x0131, B:172:0x0139, B:175:0x0144, B:178:0x0149, B:180:0x0151, B:183:0x015c, B:187:0x0163, B:189:0x016b, B:193:0x017a, B:195:0x0181, B:17:0x0196, B:19:0x01a2, B:21:0x01b7, B:24:0x01ca, B:26:0x01d4, B:28:0x01fb, B:29:0x01ff, B:31:0x0205, B:32:0x0209, B:34:0x020f, B:42:0x022f, B:44:0x0235, B:45:0x0239, B:47:0x023f, B:48:0x0245, B:50:0x024b, B:51:0x024d, B:53:0x0253, B:54:0x0255, B:56:0x025b, B:59:0x0266, B:61:0x026d, B:63:0x0273, B:66:0x027e, B:68:0x0285, B:70:0x028d, B:73:0x0298, B:77:0x029f, B:79:0x02a7, B:82:0x02b1, B:84:0x02bd, B:85:0x02ca, B:87:0x02cf, B:89:0x02d3, B:91:0x02e9, B:93:0x02f1, B:95:0x0308, B:97:0x030d, B:107:0x022b, B:108:0x0313, B:110:0x031d, B:117:0x033e, B:37:0x0214, B:39:0x021a, B:41:0x0220), top: B:13:0x003a, inners: #2 }] */
    /* JADX WARN: Removed duplicated region for block: B:87:0x02cf A[Catch: Exception -> 0x009f, TryCatch #1 {Exception -> 0x009f, blocks: (B:14:0x003a, B:126:0x0093, B:128:0x0097, B:131:0x00ae, B:135:0x00bf, B:138:0x00c9, B:142:0x00d5, B:152:0x00fb, B:155:0x0105, B:158:0x010f, B:161:0x0117, B:165:0x0120, B:168:0x012a, B:170:0x0131, B:172:0x0139, B:175:0x0144, B:178:0x0149, B:180:0x0151, B:183:0x015c, B:187:0x0163, B:189:0x016b, B:193:0x017a, B:195:0x0181, B:17:0x0196, B:19:0x01a2, B:21:0x01b7, B:24:0x01ca, B:26:0x01d4, B:28:0x01fb, B:29:0x01ff, B:31:0x0205, B:32:0x0209, B:34:0x020f, B:42:0x022f, B:44:0x0235, B:45:0x0239, B:47:0x023f, B:48:0x0245, B:50:0x024b, B:51:0x024d, B:53:0x0253, B:54:0x0255, B:56:0x025b, B:59:0x0266, B:61:0x026d, B:63:0x0273, B:66:0x027e, B:68:0x0285, B:70:0x028d, B:73:0x0298, B:77:0x029f, B:79:0x02a7, B:82:0x02b1, B:84:0x02bd, B:85:0x02ca, B:87:0x02cf, B:89:0x02d3, B:91:0x02e9, B:93:0x02f1, B:95:0x0308, B:97:0x030d, B:107:0x022b, B:108:0x0313, B:110:0x031d, B:117:0x033e, B:37:0x0214, B:39:0x021a, B:41:0x0220), top: B:13:0x003a, inners: #2 }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private void handleQQMusicCommand(android.content.Context r28, android.content.Intent r29) {
        /*
            Method dump skipped, instructions count: 863
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.navi.tool.amapflying.receiver.QQMusicLyricReceiver.handleQQMusicCommand(android.content.Context, android.content.Intent):void");
    }

    public static boolean isFavorite() {
        return sIsFavorite;
    }

    /* JADX WARN: Removed duplicated region for block: B:56:0x0086 A[Catch: all -> 0x0012, Exception -> 0x010e, TRY_LEAVE, TryCatch #0 {Exception -> 0x010e, blocks: (B:50:0x003e, B:53:0x0063, B:54:0x006e, B:56:0x0086, B:59:0x0065), top: B:49:0x003e }] */
    /* JADX WARN: Removed duplicated region for block: B:58:0x010e A[SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private void parseLyrics(java.lang.String r17) {
        /*
            Method dump skipped, instructions count: 302
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.navi.tool.amapflying.receiver.QQMusicLyricReceiver.parseLyrics(java.lang.String):void");
    }

    private static void postLyric(String str) {
        sLastPostedLyric = str;
        e b7 = e.b();
        k kVar = new k(str);
        kVar.f6754b = 0;
        b7.f(kVar);
    }

    public static void requestRefresh(Context context) {
        if (context == null) {
            return;
        }
        try {
            Intent intent = new Intent(ACTION_QQ_MUSIC_CHANGED);
            intent.setPackage("com.tencent.qqmusiccar");
            intent.addFlags(32);
            context.sendBroadcast(intent);
        } catch (Throwable unused) {
        }
    }

    public static void setDoubleLine(boolean z6) {
        sIsDoubleLine = z6;
    }

    public static TreeMap<Long, String> snapshotLyrics() {
        TreeMap<Long, String> treeMap;
        TreeMap<Long, String> treeMap2 = sLyricMap;
        synchronized (treeMap2) {
            treeMap = new TreeMap<>((SortedMap<Long, ? extends String>) treeMap2);
        }
        return treeMap;
    }

    public static void stop() {
        sIsPlaying = false;
        sIsFavorite = false;
        sHandler.removeCallbacks(sUpdateTask);
        TreeMap<Long, String> treeMap = sLyricMap;
        synchronized (treeMap) {
            treeMap.clear();
        }
        sLastPostedLyric = h0.FRAGMENT_ENCODE_SET;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void updateLyricDisplay(long j7) {
        Map.Entry<Long, String> higherEntry;
        String str = h0.FRAGMENT_ENCODE_SET;
        TreeMap<Long, String> treeMap = sLyricMap;
        synchronized (treeMap) {
            try {
                if (treeMap.isEmpty()) {
                    return;
                }
                Map.Entry<Long, String> floorEntry = treeMap.floorEntry(Long.valueOf(j7));
                if (floorEntry != null) {
                    str = floorEntry.getValue();
                    if (sIsDoubleLine && (higherEntry = treeMap.higherEntry(floorEntry.getKey())) != null) {
                        str = str + "\n" + higherEntry.getValue();
                    }
                }
                if (TextUtils.equals(str, sLastPostedLyric)) {
                    return;
                }
                postLyric(str);
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    @Override // android.content.BroadcastReceiver
    public void onReceive(Context context, Intent intent) {
        if (intent == null || intent.getAction() == null) {
            return;
        }
        String action = intent.getAction();
        if (ACTION_QQ_MUSIC_COMMAND.equals(action) || ACTION_QQ_MUSIC_CHANGED.equals(action)) {
            handleQQMusicCommand(context, intent);
        }
    }
}
