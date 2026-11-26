package androidx.media;

import android.os.Bundle;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_2_1633180.dex */
public final class c {
    public static final String EXTRA_OFFLINE = "android.service.media.extra.OFFLINE";
    public static final String EXTRA_RECENT = "android.service.media.extra.RECENT";
    public static final String EXTRA_SUGGESTED = "android.service.media.extra.SUGGESTED";

    @Deprecated
    public static final String EXTRA_SUGGESTION_KEYWORDS = "android.service.media.extra.SUGGESTION_KEYWORDS";

    /* renamed from: a, reason: collision with root package name */
    public final String f2241a;

    /* renamed from: b, reason: collision with root package name */
    public final Bundle f2242b;

    public c(Bundle bundle, String str) {
        if (str == null) {
            throw new IllegalArgumentException("The root id in BrowserRoot cannot be null. Use null for BrowserRoot instead");
        }
        this.f2241a = str;
        this.f2242b = bundle;
    }
}
