package h0;

import android.content.res.ColorStateList;
import android.content.res.Configuration;
import android.content.res.Resources;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final class l {

    /* renamed from: a, reason: collision with root package name */
    public final ColorStateList f6631a;

    /* renamed from: b, reason: collision with root package name */
    public final Configuration f6632b;

    /* renamed from: c, reason: collision with root package name */
    public final int f6633c;

    public l(ColorStateList colorStateList, Configuration configuration, Resources.Theme theme) {
        this.f6631a = colorStateList;
        this.f6632b = configuration;
        this.f6633c = theme == null ? 0 : theme.hashCode();
    }
}
