package j;

import android.content.Context;
import android.content.ContextWrapper;
import android.content.res.AssetManager;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.view.LayoutInflater;
import com.yalantis.ucrop.view.CropImageView;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final class f extends ContextWrapper {

    /* renamed from: f, reason: collision with root package name */
    public static Configuration f6877f;

    /* renamed from: a, reason: collision with root package name */
    public int f6878a;

    /* renamed from: b, reason: collision with root package name */
    public Resources.Theme f6879b;

    /* renamed from: c, reason: collision with root package name */
    public LayoutInflater f6880c;

    /* renamed from: d, reason: collision with root package name */
    public Configuration f6881d;

    /* renamed from: e, reason: collision with root package name */
    public Resources f6882e;

    public f(Context context, int i7) {
        super(context);
        this.f6878a = i7;
    }

    public final void a(Configuration configuration) {
        if (this.f6882e != null) {
            throw new IllegalStateException("getResources() or getAssets() has already been called");
        }
        if (this.f6881d != null) {
            throw new IllegalStateException("Override configuration has already been set");
        }
        this.f6881d = new Configuration(configuration);
    }

    @Override // android.content.ContextWrapper
    public final void attachBaseContext(Context context) {
        super.attachBaseContext(context);
    }

    public final void b() {
        if (this.f6879b == null) {
            this.f6879b = getResources().newTheme();
            Resources.Theme theme = getBaseContext().getTheme();
            if (theme != null) {
                this.f6879b.setTo(theme);
            }
        }
        this.f6879b.applyStyle(this.f6878a, true);
    }

    @Override // android.content.ContextWrapper, android.content.Context
    public final AssetManager getAssets() {
        return getResources().getAssets();
    }

    @Override // android.content.ContextWrapper, android.content.Context
    public final Resources getResources() {
        if (this.f6882e == null) {
            Configuration configuration = this.f6881d;
            if (configuration != null) {
                if (f6877f == null) {
                    Configuration configuration2 = new Configuration();
                    configuration2.fontScale = CropImageView.DEFAULT_ASPECT_RATIO;
                    f6877f = configuration2;
                }
                if (!configuration.equals(f6877f)) {
                    this.f6882e = e.a(this, this.f6881d).getResources();
                }
            }
            this.f6882e = super.getResources();
        }
        return this.f6882e;
    }

    @Override // android.content.ContextWrapper, android.content.Context
    public final Object getSystemService(String str) {
        if (!"layout_inflater".equals(str)) {
            return getBaseContext().getSystemService(str);
        }
        if (this.f6880c == null) {
            this.f6880c = LayoutInflater.from(getBaseContext()).cloneInContext(this);
        }
        return this.f6880c;
    }

    @Override // android.content.ContextWrapper, android.content.Context
    public final Resources.Theme getTheme() {
        Resources.Theme theme = this.f6879b;
        if (theme != null) {
            return theme;
        }
        if (this.f6878a == 0) {
            this.f6878a = f.i.Theme_AppCompat_Light;
        }
        b();
        return this.f6879b;
    }

    @Override // android.content.ContextWrapper, android.content.Context
    public final void setTheme(int i7) {
        if (this.f6878a != i7) {
            this.f6878a = i7;
            b();
        }
    }
}
