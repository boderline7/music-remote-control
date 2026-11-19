package com.luna.music.car;

/**
 * 爱心状态的唯一真相。
 *
 * 官方 App 收到 {@code star_action} 不会回头改 USER_RATING，所以收藏态只能自己记。
 * 车机助手卡片（{@link Bridge}）与网页遥控（{@link Ncm}）是同进程里的两条链路，
 * 谁点了爱心都写这里，另一边下一次推状态自然就读到，两端不会再各说各话。
 */
final class Star {

    static final Star I = new Star();

    /** 当前记着的歌（MEDIA_ID）。 */
    private String id = "";
    /** -1 未知 / 0 未收藏 / 1 已收藏。 */
    private int state = -1;

    private Star() {
    }

    /** 记下某首歌的收藏态；换了歌旧值自动作废。 */
    synchronized void set(String mediaId, boolean on) {
        id = mediaId == null ? "" : mediaId;
        state = on ? 1 : 0;
    }

    /** -1 表示这首还没人点过，调用处回退到官方元数据。 */
    synchronized int get(String mediaId) {
        if (state < 0 || mediaId == null || mediaId.length() == 0) return -1;
        return mediaId.equals(id) ? state : -1;
    }

    synchronized void clear() {
        id = "";
        state = -1;
    }
}
