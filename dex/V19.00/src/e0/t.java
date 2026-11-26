package e0;

import android.app.PendingIntent;
import android.os.Bundle;
import androidx.core.graphics.drawable.IconCompat;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final class t {
    public static final int SEMANTIC_ACTION_ARCHIVE = 5;
    public static final int SEMANTIC_ACTION_CALL = 10;
    public static final int SEMANTIC_ACTION_DELETE = 4;
    public static final int SEMANTIC_ACTION_MARK_AS_READ = 2;
    public static final int SEMANTIC_ACTION_MARK_AS_UNREAD = 3;
    public static final int SEMANTIC_ACTION_MUTE = 6;
    public static final int SEMANTIC_ACTION_NONE = 0;
    public static final int SEMANTIC_ACTION_REPLY = 1;
    public static final int SEMANTIC_ACTION_THUMBS_DOWN = 9;
    public static final int SEMANTIC_ACTION_THUMBS_UP = 8;
    public static final int SEMANTIC_ACTION_UNMUTE = 7;

    /* renamed from: a, reason: collision with root package name */
    public final Bundle f6315a;

    /* renamed from: b, reason: collision with root package name */
    public IconCompat f6316b;

    /* renamed from: c, reason: collision with root package name */
    public final boolean f6317c;

    /* renamed from: d, reason: collision with root package name */
    public final boolean f6318d;

    /* renamed from: e, reason: collision with root package name */
    public final CharSequence f6319e;

    /* renamed from: f, reason: collision with root package name */
    public final PendingIntent f6320f;

    public t(PendingIntent pendingIntent) {
        Bundle bundle = new Bundle();
        this.f6318d = true;
        this.f6316b = null;
        this.f6319e = v.c("取消");
        this.f6320f = pendingIntent;
        this.f6315a = bundle;
        this.f6317c = true;
        this.f6318d = true;
    }

    public final IconCompat a() {
        return this.f6316b;
    }
}
