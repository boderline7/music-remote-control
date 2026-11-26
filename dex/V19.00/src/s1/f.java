package s1;

import cn.kuwo.base.bean.Music;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public interface f {
    void sendSyncNotice_LyricsFailed(Music music);

    void sendSyncNotice_LyricsFinished(Music music, String str);

    void sendSyncNotice_LyricsNone(Music music);

    void sendSyncNotice_LyricsStart(Music music);
}
