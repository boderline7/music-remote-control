package com.yalantis.ucrop;

import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.PorterDuff;
import android.graphics.RectF;
import android.graphics.drawable.Animatable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.activity.j0;
import androidx.activity.k0;
import androidx.activity.m0;
import androidx.activity.s;
import androidx.activity.v;
import androidx.appcompat.app.q0;
import androidx.appcompat.app.r;
import androidx.appcompat.app.w;
import androidx.appcompat.widget.Toolbar;
import androidx.appcompat.widget.y3;
import com.yalantis.ucrop.UCropActivity;
import com.yalantis.ucrop.model.AspectRatio;
import com.yalantis.ucrop.view.CropImageView;
import com.yalantis.ucrop.view.GestureCropImageView;
import com.yalantis.ucrop.view.OverlayView;
import com.yalantis.ucrop.view.UCropView;
import com.yalantis.ucrop.view.widget.AspectRatioTextView;
import com.yalantis.ucrop.view.widget.HorizontalProgressWheelView;
import g0.c;
import g0.d;
import g0.g;
import java.util.ArrayList;
import java.util.Iterator;
import n1.a;
import n1.u;
import n1.z;
import p0.b0;
import p0.d1;
import p0.l2;
import p0.r0;
import t4.b;
import t4.e;
import t4.f;
import t4.h;
import t4.k;
import t4.l;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public class UCropActivity extends r {
    public static final int ALL = 3;
    public static final Bitmap.CompressFormat DEFAULT_COMPRESS_FORMAT = Bitmap.CompressFormat.JPEG;
    public static final int DEFAULT_COMPRESS_QUALITY = 90;
    public static final int NONE = 0;
    public static final int ROTATE = 2;
    public static final int SCALE = 1;

    /* renamed from: a, reason: collision with root package name */
    public String f6140a;

    /* renamed from: b, reason: collision with root package name */
    public int f6141b;

    /* renamed from: c, reason: collision with root package name */
    public int f6142c;

    /* renamed from: d, reason: collision with root package name */
    public int f6143d;

    /* renamed from: e, reason: collision with root package name */
    public int f6144e;

    /* renamed from: f, reason: collision with root package name */
    public int f6145f;

    /* renamed from: g, reason: collision with root package name */
    public int f6146g;

    /* renamed from: h, reason: collision with root package name */
    public int f6147h;

    /* renamed from: i, reason: collision with root package name */
    public boolean f6148i;

    /* renamed from: k, reason: collision with root package name */
    public UCropView f6150k;

    /* renamed from: l, reason: collision with root package name */
    public GestureCropImageView f6151l;

    /* renamed from: m, reason: collision with root package name */
    public OverlayView f6152m;

    /* renamed from: n, reason: collision with root package name */
    public ViewGroup f6153n;

    /* renamed from: o, reason: collision with root package name */
    public ViewGroup f6154o;

    /* renamed from: p, reason: collision with root package name */
    public ViewGroup f6155p;

    /* renamed from: q, reason: collision with root package name */
    public ViewGroup f6156q;

    /* renamed from: r, reason: collision with root package name */
    public ViewGroup f6157r;

    /* renamed from: s, reason: collision with root package name */
    public ViewGroup f6158s;

    /* renamed from: u, reason: collision with root package name */
    public TextView f6160u;

    /* renamed from: v, reason: collision with root package name */
    public TextView f6161v;

    /* renamed from: w, reason: collision with root package name */
    public View f6162w;

    /* renamed from: x, reason: collision with root package name */
    public a f6163x;

    /* renamed from: j, reason: collision with root package name */
    public boolean f6149j = true;

    /* renamed from: t, reason: collision with root package name */
    public final ArrayList f6159t = new ArrayList();

    /* renamed from: y, reason: collision with root package name */
    public Bitmap.CompressFormat f6164y = DEFAULT_COMPRESS_FORMAT;

    /* renamed from: z, reason: collision with root package name */
    public int f6165z = 90;
    public int[] A = {1, 2, 3};
    public final k B = new k(this, 0);
    public final l C = new l(this, 3);

    static {
        q0 q0Var = w.f866a;
        int i7 = y3.MAX_SDK_WHERE_REQUIRED;
    }

    public final void e(int i7) {
        GestureCropImageView gestureCropImageView = this.f6151l;
        int i8 = this.A[i7];
        gestureCropImageView.setScaleEnabled(i8 == 3 || i8 == 1);
        GestureCropImageView gestureCropImageView2 = this.f6151l;
        int i9 = this.A[i7];
        gestureCropImageView2.setRotateEnabled(i9 == 3 || i9 == 2);
    }

    public final void f(Throwable th) {
        setResult(96, new Intent().putExtra("com.yalantis.ucrop.Error", th));
    }

    public final void g(int i7) {
        if (this.f6148i) {
            this.f6153n.setSelected(i7 == e.state_aspect_ratio);
            this.f6154o.setSelected(i7 == e.state_rotate);
            this.f6155p.setSelected(i7 == e.state_scale);
            this.f6156q.setVisibility(i7 == e.state_aspect_ratio ? 0 : 8);
            this.f6157r.setVisibility(i7 == e.state_rotate ? 0 : 8);
            this.f6158s.setVisibility(i7 == e.state_scale ? 0 : 8);
            u.a((ViewGroup) findViewById(e.ucrop_photobox), this.f6163x);
            this.f6155p.findViewById(e.text_view_scale).setVisibility(i7 == e.state_scale ? 0 : 8);
            this.f6153n.findViewById(e.text_view_crop).setVisibility(i7 == e.state_aspect_ratio ? 0 : 8);
            this.f6154o.findViewById(e.text_view_rotate).setVisibility(i7 == e.state_rotate ? 0 : 8);
            if (i7 == e.state_scale) {
                e(0);
            } else if (i7 == e.state_rotate) {
                e(1);
            } else {
                e(2);
            }
        }
    }

    @Override // androidx.fragment.app.d0, androidx.activity.o, e0.n, android.app.Activity
    public final void onCreate(Bundle bundle) {
        m0 m0Var;
        m0 m0Var2;
        ArrayList arrayList;
        super.onCreate(bundle);
        Intent intent = getIntent();
        int i7 = 1;
        boolean booleanExtra = intent.getBooleanExtra("com.yalantis.ucrop.StatusBarLight", true);
        int i8 = 0;
        boolean booleanExtra2 = intent.getBooleanExtra("com.yalantis.ucrop.NavigationBarLight", false);
        int i9 = 2;
        if (booleanExtra) {
            m0.Companion.getClass();
            m0Var = new m0(1, k0.INSTANCE);
        } else {
            m0.Companion.getClass();
            m0Var = new m0(2, j0.INSTANCE);
        }
        m0 m0Var3 = m0Var;
        if (booleanExtra2) {
            m0.Companion.getClass();
            m0Var2 = new m0(1, k0.INSTANCE);
        } else {
            m0.Companion.getClass();
            m0Var2 = new m0(2, j0.INSTANCE);
        }
        m0 m0Var4 = m0Var2;
        int i10 = androidx.activity.r.f647a;
        View decorView = getWindow().getDecorView();
        i5.a.f("window.decorView", decorView);
        Resources resources = decorView.getResources();
        i5.a.f("view.resources", resources);
        boolean booleanValue = ((Boolean) m0Var3.f639d.b(resources)).booleanValue();
        Resources resources2 = decorView.getResources();
        i5.a.f("view.resources", resources2);
        boolean booleanValue2 = ((Boolean) m0Var4.f639d.b(resources2)).booleanValue();
        v uVar = Build.VERSION.SDK_INT >= 29 ? new androidx.activity.u() : new s();
        Window window = getWindow();
        i5.a.f("window", window);
        uVar.a(m0Var3, m0Var4, window, decorView, booleanValue, booleanValue2);
        setContentView(f.ucrop_activity_photobox);
        int i11 = b.ucrop_color_toolbar;
        Object obj = g.f6423a;
        this.f6141b = intent.getIntExtra("com.yalantis.ucrop.ToolbarColor", d.a(this, i11));
        this.f6142c = intent.getIntExtra("com.yalantis.ucrop.UcropColorControlsWidgetActive", d.a(this, b.ucrop_color_active_controls_color));
        this.f6143d = intent.getIntExtra("com.yalantis.ucrop.UcropToolbarWidgetColor", d.a(this, b.ucrop_color_toolbar_widget));
        this.f6145f = intent.getIntExtra("com.yalantis.ucrop.UcropToolbarCancelDrawable", t4.d.ucrop_ic_cross);
        this.f6146g = intent.getIntExtra("com.yalantis.ucrop.UcropToolbarCropDrawable", t4.d.ucrop_ic_done);
        String stringExtra = intent.getStringExtra("com.yalantis.ucrop.UcropToolbarTitleText");
        this.f6140a = stringExtra;
        if (stringExtra == null) {
            stringExtra = getResources().getString(h.ucrop_label_edit_photo);
        }
        this.f6140a = stringExtra;
        this.f6147h = intent.getIntExtra("com.yalantis.ucrop.UcropLogoColor", d.a(this, b.ucrop_color_default_logo));
        this.f6148i = !intent.getBooleanExtra("com.yalantis.ucrop.HideBottomControls", false);
        this.f6144e = intent.getIntExtra("com.yalantis.ucrop.UcropRootViewBackgroundColor", d.a(this, b.ucrop_color_crop_background));
        Toolbar toolbar = (Toolbar) findViewById(e.toolbar);
        y2.l lVar = new y2.l(7);
        int i12 = d1.OVER_SCROLL_ALWAYS;
        r0.u(toolbar, lVar);
        toolbar.setBackgroundColor(this.f6141b);
        toolbar.setTitleTextColor(this.f6143d);
        TextView textView = (TextView) toolbar.findViewById(e.toolbar_title);
        textView.setTextColor(this.f6143d);
        textView.setText(this.f6140a);
        Drawable mutate = c.b(this, this.f6145f).mutate();
        int i13 = this.f6143d;
        PorterDuff.Mode mode = PorterDuff.Mode.SRC_ATOP;
        mutate.setColorFilter(i13, mode);
        toolbar.setNavigationIcon(mutate);
        setSupportActionBar(toolbar);
        androidx.appcompat.app.b supportActionBar = getSupportActionBar();
        if (supportActionBar != null) {
            supportActionBar.p();
        }
        UCropView uCropView = (UCropView) findViewById(e.ucrop);
        this.f6150k = uCropView;
        this.f6151l = uCropView.getCropImageView();
        this.f6152m = this.f6150k.getOverlayView();
        this.f6151l.setTransformImageListener(this.B);
        ((ImageView) findViewById(e.image_view_logo)).setColorFilter(this.f6147h, mode);
        findViewById(e.ucrop_frame).setBackgroundColor(this.f6144e);
        if (!this.f6148i) {
            ((RelativeLayout.LayoutParams) findViewById(e.ucrop_frame).getLayoutParams()).bottomMargin = 0;
            findViewById(e.ucrop_frame).requestLayout();
        }
        boolean z6 = this.f6148i;
        float f7 = CropImageView.DEFAULT_ASPECT_RATIO;
        ViewGroup viewGroup = null;
        if (z6) {
            ViewGroup viewGroup2 = (ViewGroup) ((ViewGroup) findViewById(e.ucrop_photobox)).findViewById(e.controls_wrapper);
            viewGroup2.setVisibility(0);
            LayoutInflater.from(this).inflate(f.ucrop_controls, viewGroup2, true);
            a aVar = new a();
            this.f6163x = aVar;
            aVar.z(50L);
            ViewGroup viewGroup3 = (ViewGroup) findViewById(e.state_aspect_ratio);
            this.f6153n = viewGroup3;
            viewGroup3.setOnClickListener(this.C);
            ViewGroup viewGroup4 = (ViewGroup) findViewById(e.state_rotate);
            this.f6154o = viewGroup4;
            viewGroup4.setOnClickListener(this.C);
            ViewGroup viewGroup5 = (ViewGroup) findViewById(e.state_scale);
            this.f6155p = viewGroup5;
            viewGroup5.setOnClickListener(this.C);
            this.f6156q = (ViewGroup) findViewById(e.layout_aspect_ratio);
            this.f6157r = (ViewGroup) findViewById(e.layout_rotate_wheel);
            this.f6158s = (ViewGroup) findViewById(e.layout_scale_wheel);
            View findViewById = findViewById(e.controls_wrapper);
            final int dimensionPixelSize = getResources().getDimensionPixelSize(t4.c.ucrop_height_wrapper_states);
            r0.u(findViewById.findViewById(e.wrapper_states), new b0() { // from class: t4.j
                @Override // p0.b0
                public final l2 e(View view, l2 l2Var) {
                    int i14 = UCropActivity.DEFAULT_COMPRESS_QUALITY;
                    i0.c f8 = l2Var.f8161a.f(7);
                    int i15 = f8.f6773a;
                    int i16 = f8.f6775c;
                    int i17 = f8.f6776d;
                    view.setPaddingRelative(i15, 0, i16, i17);
                    ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
                    int i18 = dimensionPixelSize + i17;
                    if (layoutParams.height != i18) {
                        layoutParams.height = i18;
                        view.setLayoutParams(layoutParams);
                    }
                    return l2Var;
                }
            });
            int intExtra = intent.getIntExtra("com.yalantis.ucrop.AspectRatioSelectedByDefault", 0);
            ArrayList parcelableArrayListExtra = intent.getParcelableArrayListExtra("com.yalantis.ucrop.AspectRatioOptions");
            if (parcelableArrayListExtra == null || parcelableArrayListExtra.isEmpty()) {
                parcelableArrayListExtra = new ArrayList();
                parcelableArrayListExtra.add(new AspectRatio(null, 1.0f, 1.0f));
                parcelableArrayListExtra.add(new AspectRatio(null, 3.0f, 4.0f));
                parcelableArrayListExtra.add(new AspectRatio(getString(h.ucrop_label_original).toUpperCase(), CropImageView.DEFAULT_ASPECT_RATIO, CropImageView.DEFAULT_ASPECT_RATIO));
                parcelableArrayListExtra.add(new AspectRatio(null, 3.0f, 2.0f));
                parcelableArrayListExtra.add(new AspectRatio(null, 16.0f, 9.0f));
                intExtra = 2;
            }
            LinearLayout linearLayout = (LinearLayout) findViewById(e.layout_aspect_ratio);
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(0, -1);
            layoutParams.weight = 1.0f;
            Iterator it = parcelableArrayListExtra.iterator();
            while (true) {
                boolean hasNext = it.hasNext();
                arrayList = this.f6159t;
                if (!hasNext) {
                    break;
                }
                AspectRatio aspectRatio = (AspectRatio) it.next();
                FrameLayout frameLayout = (FrameLayout) getLayoutInflater().inflate(f.ucrop_aspect_ratio, viewGroup);
                frameLayout.setLayoutParams(layoutParams);
                AspectRatioTextView aspectRatioTextView = (AspectRatioTextView) frameLayout.getChildAt(0);
                aspectRatioTextView.setActiveColor(this.f6142c);
                aspectRatioTextView.setAspectRatio(aspectRatio);
                linearLayout.addView(frameLayout);
                arrayList.add(frameLayout);
                viewGroup = null;
            }
            ((ViewGroup) arrayList.get(intExtra)).setSelected(true);
            Iterator it2 = arrayList.iterator();
            while (it2.hasNext()) {
                ((ViewGroup) it2.next()).setOnClickListener(new l(this, i8));
            }
            this.f6160u = (TextView) findViewById(e.text_view_rotate);
            ((HorizontalProgressWheelView) findViewById(e.rotate_scroll_wheel)).setScrollingListener(new k(this, 1));
            ((HorizontalProgressWheelView) findViewById(e.rotate_scroll_wheel)).setMiddleLineColor(this.f6142c);
            findViewById(e.wrapper_reset_rotate).setOnClickListener(new l(this, i7));
            findViewById(e.wrapper_rotate_by_angle).setOnClickListener(new l(this, i9));
            int i14 = this.f6142c;
            TextView textView2 = this.f6160u;
            if (textView2 != null) {
                textView2.setTextColor(i14);
            }
            this.f6161v = (TextView) findViewById(e.text_view_scale);
            ((HorizontalProgressWheelView) findViewById(e.scale_scroll_wheel)).setScrollingListener(new k(this, 2));
            ((HorizontalProgressWheelView) findViewById(e.scale_scroll_wheel)).setMiddleLineColor(this.f6142c);
            int i15 = this.f6142c;
            TextView textView3 = this.f6161v;
            if (textView3 != null) {
                textView3.setTextColor(i15);
            }
            ImageView imageView = (ImageView) findViewById(e.image_view_state_scale);
            ImageView imageView2 = (ImageView) findViewById(e.image_view_state_rotate);
            ImageView imageView3 = (ImageView) findViewById(e.image_view_state_aspect_ratio);
            imageView.setImageDrawable(new x4.d(imageView.getDrawable(), this.f6142c));
            imageView2.setImageDrawable(new x4.d(imageView2.getDrawable(), this.f6142c));
            imageView3.setImageDrawable(new x4.d(imageView3.getDrawable(), this.f6142c));
        }
        Uri uri = (Uri) intent.getParcelableExtra("com.yalantis.ucrop.InputUri");
        Uri uri2 = (Uri) intent.getParcelableExtra("com.yalantis.ucrop.OutputUri");
        String stringExtra2 = intent.getStringExtra("com.yalantis.ucrop.CompressionFormatName");
        Bitmap.CompressFormat valueOf = !TextUtils.isEmpty(stringExtra2) ? Bitmap.CompressFormat.valueOf(stringExtra2) : null;
        if (valueOf == null) {
            valueOf = DEFAULT_COMPRESS_FORMAT;
        }
        this.f6164y = valueOf;
        this.f6165z = intent.getIntExtra("com.yalantis.ucrop.CompressionQuality", 90);
        int[] intArrayExtra = intent.getIntArrayExtra("com.yalantis.ucrop.AllowedGestures");
        if (intArrayExtra != null && intArrayExtra.length == 3) {
            this.A = intArrayExtra;
        }
        this.f6151l.setMaxBitmapSize(intent.getIntExtra("com.yalantis.ucrop.MaxBitmapSize", 0));
        this.f6151l.setMaxScaleMultiplier(intent.getFloatExtra("com.yalantis.ucrop.MaxScaleMultiplier", 10.0f));
        this.f6151l.setImageToWrapCropBoundsAnimDuration(intent.getIntExtra("com.yalantis.ucrop.ImageToCropBoundsAnimDuration", CropImageView.DEFAULT_IMAGE_TO_CROP_BOUNDS_ANIM_DURATION));
        this.f6152m.setFreestyleCropEnabled(intent.getBooleanExtra("com.yalantis.ucrop.FreeStyleCrop", false));
        this.f6152m.setDimmedColor(intent.getIntExtra("com.yalantis.ucrop.DimmedLayerColor", getResources().getColor(b.ucrop_color_default_dimmed)));
        this.f6152m.setCircleDimmedLayer(intent.getBooleanExtra("com.yalantis.ucrop.CircleDimmedLayer", false));
        this.f6152m.setShowCropFrame(intent.getBooleanExtra("com.yalantis.ucrop.ShowCropFrame", true));
        this.f6152m.setCropFrameColor(intent.getIntExtra("com.yalantis.ucrop.CropFrameColor", getResources().getColor(b.ucrop_color_default_crop_frame)));
        this.f6152m.setCropFrameStrokeWidth(intent.getIntExtra("com.yalantis.ucrop.CropFrameStrokeWidth", getResources().getDimensionPixelSize(t4.c.ucrop_default_crop_frame_stoke_width)));
        this.f6152m.setShowCropGrid(intent.getBooleanExtra("com.yalantis.ucrop.ShowCropGrid", true));
        this.f6152m.setCropGridRowCount(intent.getIntExtra("com.yalantis.ucrop.CropGridRowCount", 2));
        this.f6152m.setCropGridColumnCount(intent.getIntExtra("com.yalantis.ucrop.CropGridColumnCount", 2));
        this.f6152m.setCropGridColor(intent.getIntExtra("com.yalantis.ucrop.CropGridColor", getResources().getColor(b.ucrop_color_default_crop_grid)));
        this.f6152m.setCropGridStrokeWidth(intent.getIntExtra("com.yalantis.ucrop.CropGridStrokeWidth", getResources().getDimensionPixelSize(t4.c.ucrop_default_crop_grid_stoke_width)));
        float floatExtra = intent.getFloatExtra("com.yalantis.ucrop.AspectRatioX", -1.0f);
        float floatExtra2 = intent.getFloatExtra("com.yalantis.ucrop.AspectRatioY", -1.0f);
        int intExtra2 = intent.getIntExtra("com.yalantis.ucrop.AspectRatioSelectedByDefault", 0);
        ArrayList parcelableArrayListExtra2 = intent.getParcelableArrayListExtra("com.yalantis.ucrop.AspectRatioOptions");
        if (floatExtra >= CropImageView.DEFAULT_ASPECT_RATIO && floatExtra2 >= CropImageView.DEFAULT_ASPECT_RATIO) {
            ViewGroup viewGroup6 = this.f6153n;
            if (viewGroup6 != null) {
                viewGroup6.setVisibility(8);
            }
            float f8 = floatExtra / floatExtra2;
            GestureCropImageView gestureCropImageView = this.f6151l;
            if (!Float.isNaN(f8)) {
                f7 = f8;
            }
            gestureCropImageView.setTargetAspectRatio(f7);
        } else if (parcelableArrayListExtra2 == null || intExtra2 >= parcelableArrayListExtra2.size()) {
            this.f6151l.setTargetAspectRatio(CropImageView.DEFAULT_ASPECT_RATIO);
        } else {
            float f9 = ((AspectRatio) parcelableArrayListExtra2.get(intExtra2)).f6167b / ((AspectRatio) parcelableArrayListExtra2.get(intExtra2)).f6168c;
            GestureCropImageView gestureCropImageView2 = this.f6151l;
            if (!Float.isNaN(f9)) {
                f7 = f9;
            }
            gestureCropImageView2.setTargetAspectRatio(f7);
        }
        int intExtra3 = intent.getIntExtra("com.yalantis.ucrop.MaxSizeX", 0);
        int intExtra4 = intent.getIntExtra("com.yalantis.ucrop.MaxSizeY", 0);
        if (intExtra3 > 0 && intExtra4 > 0) {
            this.f6151l.setMaxResultImageSizeX(intExtra3);
            this.f6151l.setMaxResultImageSizeY(intExtra4);
        }
        if (uri == null || uri2 == null) {
            f(new NullPointerException(getString(h.ucrop_error_input_data_is_absent)));
            finish();
        } else {
            try {
                this.f6151l.setImageUri(uri, uri2);
            } catch (Exception e7) {
                f(e7);
                finish();
            }
        }
        if (!this.f6148i) {
            e(0);
        } else if (this.f6153n.getVisibility() == 0) {
            g(e.state_aspect_ratio);
        } else {
            g(e.state_scale);
        }
        if (this.f6162w == null) {
            this.f6162w = new View(this);
            RelativeLayout.LayoutParams layoutParams2 = new RelativeLayout.LayoutParams(-1, -1);
            layoutParams2.addRule(3, e.toolbar);
            this.f6162w.setLayoutParams(layoutParams2);
            this.f6162w.setClickable(true);
        }
        ((RelativeLayout) findViewById(e.ucrop_photobox)).addView(this.f6162w);
    }

    @Override // android.app.Activity
    public final boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(t4.g.ucrop_menu_activity, menu);
        MenuItem findItem = menu.findItem(e.menu_loader);
        Drawable icon = findItem.getIcon();
        if (icon != null) {
            try {
                icon.mutate();
                icon.setColorFilter(this.f6143d, PorterDuff.Mode.SRC_ATOP);
                findItem.setIcon(icon);
            } catch (IllegalStateException e7) {
                String.format("%s - %s", e7.getMessage(), getString(h.ucrop_mutate_exception_hint));
            }
            ((Animatable) findItem.getIcon()).start();
        }
        MenuItem findItem2 = menu.findItem(e.menu_crop);
        int i7 = this.f6146g;
        Object obj = g.f6423a;
        Drawable b7 = c.b(this, i7);
        if (b7 == null) {
            return true;
        }
        b7.mutate();
        b7.setColorFilter(this.f6143d, PorterDuff.Mode.SRC_ATOP);
        findItem2.setIcon(b7);
        return true;
    }

    @Override // android.app.Activity
    public final boolean onOptionsItemSelected(MenuItem menuItem) {
        if (menuItem.getItemId() != e.menu_crop) {
            if (menuItem.getItemId() != 16908332) {
                return super.onOptionsItemSelected(menuItem);
            }
            onBackPressed();
            return true;
        }
        this.f6162w.setClickable(true);
        this.f6149j = true;
        supportInvalidateOptionsMenu();
        GestureCropImageView gestureCropImageView = this.f6151l;
        Bitmap.CompressFormat compressFormat = this.f6164y;
        int i7 = this.f6165z;
        k kVar = new k(this, 3);
        gestureCropImageView.h();
        gestureCropImageView.setImageToWrapCropBounds(false);
        RectF rectF = gestureCropImageView.f6169u;
        RectF j12 = z.j1(gestureCropImageView.f6201d);
        float currentScale = gestureCropImageView.getCurrentScale();
        float currentAngle = gestureCropImageView.getCurrentAngle();
        y2.d dVar = new y2.d();
        dVar.f10135c = rectF;
        dVar.f10136d = j12;
        dVar.f10133a = currentScale;
        dVar.f10134b = currentAngle;
        int i8 = gestureCropImageView.D;
        int i9 = gestureCropImageView.E;
        String imageInputPath = gestureCropImageView.getImageInputPath();
        String imageOutputPath = gestureCropImageView.getImageOutputPath();
        v4.a exifInfo = gestureCropImageView.getExifInfo();
        a4.c cVar = new a4.c();
        cVar.f267a = i8;
        cVar.f268b = i9;
        cVar.f271e = compressFormat;
        cVar.f269c = i7;
        cVar.f270d = imageInputPath;
        cVar.f272f = imageOutputPath;
        cVar.f273g = exifInfo;
        cVar.f274h = gestureCropImageView.getImageInputUri();
        cVar.f275i = gestureCropImageView.getImageOutputUri();
        new w4.a(gestureCropImageView.getContext(), gestureCropImageView.getViewBitmap(), dVar, cVar, kVar).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new Void[0]);
        return true;
    }

    @Override // android.app.Activity
    public final boolean onPrepareOptionsMenu(Menu menu) {
        menu.findItem(e.menu_crop).setVisible(!this.f6149j);
        menu.findItem(e.menu_loader).setVisible(this.f6149j);
        return super.onPrepareOptionsMenu(menu);
    }

    @Override // androidx.appcompat.app.r, androidx.fragment.app.d0, android.app.Activity
    public final void onStop() {
        super.onStop();
        GestureCropImageView gestureCropImageView = this.f6151l;
        if (gestureCropImageView != null) {
            gestureCropImageView.h();
        }
    }
}
