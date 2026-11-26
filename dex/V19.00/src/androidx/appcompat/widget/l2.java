package androidx.appcompat.widget;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.util.TypedValue;
import java.lang.ref.WeakReference;
import java.util.WeakHashMap;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_2_1633180.dex */
public final class l2 {

    /* renamed from: g, reason: collision with root package name */
    public static l2 f1309g;

    /* renamed from: a, reason: collision with root package name */
    public WeakHashMap f1311a;

    /* renamed from: b, reason: collision with root package name */
    public final WeakHashMap f1312b = new WeakHashMap(0);

    /* renamed from: c, reason: collision with root package name */
    public TypedValue f1313c;

    /* renamed from: d, reason: collision with root package name */
    public boolean f1314d;

    /* renamed from: e, reason: collision with root package name */
    public y f1315e;

    /* renamed from: f, reason: collision with root package name */
    public static final PorterDuff.Mode f1308f = PorterDuff.Mode.SRC_IN;

    /* renamed from: h, reason: collision with root package name */
    public static final k2 f1310h = new k2(6);

    public static synchronized l2 c() {
        l2 l2Var;
        synchronized (l2.class) {
            try {
                if (f1309g == null) {
                    f1309g = new l2();
                }
                l2Var = f1309g;
            } catch (Throwable th) {
                throw th;
            }
        }
        return l2Var;
    }

    public static synchronized PorterDuffColorFilter g(int i7, PorterDuff.Mode mode) {
        PorterDuffColorFilter porterDuffColorFilter;
        synchronized (l2.class) {
            k2 k2Var = f1310h;
            k2Var.getClass();
            int i8 = (31 + i7) * 31;
            porterDuffColorFilter = (PorterDuffColorFilter) k2Var.a(Integer.valueOf(mode.hashCode() + i8));
            if (porterDuffColorFilter == null) {
                porterDuffColorFilter = new PorterDuffColorFilter(i7, mode);
            }
        }
        return porterDuffColorFilter;
    }

    public final synchronized void a(Context context, long j7, Drawable drawable) {
        try {
            Drawable.ConstantState constantState = drawable.getConstantState();
            if (constantState != null) {
                q.e eVar = (q.e) this.f1312b.get(context);
                if (eVar == null) {
                    eVar = new q.e();
                    this.f1312b.put(context, eVar);
                }
                eVar.f(j7, new WeakReference(constantState));
            }
        } catch (Throwable th) {
            throw th;
        }
    }

    public final Drawable b(Context context, int i7) {
        if (this.f1313c == null) {
            this.f1313c = new TypedValue();
        }
        TypedValue typedValue = this.f1313c;
        context.getResources().getValue(i7, typedValue, true);
        long j7 = (typedValue.assetCookie << 32) | typedValue.data;
        Drawable d7 = d(context, j7);
        if (d7 != null) {
            return d7;
        }
        LayerDrawable layerDrawable = null;
        if (this.f1315e != null) {
            if (i7 == f.e.abc_cab_background_top_material) {
                layerDrawable = new LayerDrawable(new Drawable[]{e(context, f.e.abc_cab_background_internal_bg), e(context, f.e.abc_cab_background_top_mtrl_alpha)});
            } else if (i7 == f.e.abc_ratingbar_material) {
                layerDrawable = y.c(this, context, f.d.abc_star_big);
            } else if (i7 == f.e.abc_ratingbar_indicator_material) {
                layerDrawable = y.c(this, context, f.d.abc_star_medium);
            } else if (i7 == f.e.abc_ratingbar_small_material) {
                layerDrawable = y.c(this, context, f.d.abc_star_small);
            }
        }
        if (layerDrawable != null) {
            layerDrawable.setChangingConfigurations(typedValue.changingConfigurations);
            a(context, j7, layerDrawable);
        }
        return layerDrawable;
    }

    public final synchronized Drawable d(Context context, long j7) {
        q.e eVar = (q.e) this.f1312b.get(context);
        if (eVar == null) {
            return null;
        }
        WeakReference weakReference = (WeakReference) eVar.d(j7, null);
        if (weakReference != null) {
            Drawable.ConstantState constantState = (Drawable.ConstantState) weakReference.get();
            if (constantState != null) {
                return constantState.newDrawable(context.getResources());
            }
            eVar.g(j7);
        }
        return null;
    }

    public final synchronized Drawable e(Context context, int i7) {
        return f(context, i7, false);
    }

    /* JADX WARN: Code restructure failed: missing block: B:16:0x0050, code lost:
    
        j0.b.i(r0, r2);
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final synchronized android.graphics.drawable.Drawable f(android.content.Context r7, int r8, boolean r9) {
        /*
            r6 = this;
            monitor-enter(r6)
            boolean r0 = r6.f1314d     // Catch: java.lang.Throwable -> Lda
            if (r0 == 0) goto L6
            goto L25
        L6:
            r0 = 1
            r6.f1314d = r0     // Catch: java.lang.Throwable -> Lda
            int r0 = h.a.abc_vector_test     // Catch: java.lang.Throwable -> Lda
            android.graphics.drawable.Drawable r0 = r6.e(r7, r0)     // Catch: java.lang.Throwable -> Lda
            if (r0 == 0) goto Lde
            boolean r1 = r0 instanceof o1.p     // Catch: java.lang.Throwable -> Lda
            if (r1 != 0) goto L25
            java.lang.Class r0 = r0.getClass()     // Catch: java.lang.Throwable -> Lda
            java.lang.String r0 = r0.getName()     // Catch: java.lang.Throwable -> Lda
            java.lang.String r1 = "android.graphics.drawable.VectorDrawable"
            boolean r0 = r1.equals(r0)     // Catch: java.lang.Throwable -> Lda
            if (r0 == 0) goto Lde
        L25:
            android.graphics.drawable.Drawable r0 = r6.b(r7, r8)     // Catch: java.lang.Throwable -> Lda
            if (r0 != 0) goto L31
            java.lang.Object r0 = g0.g.f6423a     // Catch: java.lang.Throwable -> Lda
            android.graphics.drawable.Drawable r0 = g0.c.b(r7, r8)     // Catch: java.lang.Throwable -> Lda
        L31:
            if (r0 == 0) goto Ld4
            android.content.res.ColorStateList r1 = r6.h(r7, r8)     // Catch: java.lang.Throwable -> Lda
            r2 = 0
            if (r1 == 0) goto L55
            int[] r7 = androidx.appcompat.widget.m1.f1350a     // Catch: java.lang.Throwable -> Lda
            android.graphics.drawable.Drawable r0 = r0.mutate()     // Catch: java.lang.Throwable -> Lda
            j0.b.h(r0, r1)     // Catch: java.lang.Throwable -> Lda
            androidx.appcompat.widget.y r7 = r6.f1315e     // Catch: java.lang.Throwable -> Lda
            if (r7 != 0) goto L48
            goto L4e
        L48:
            int r7 = f.e.abc_switch_thumb_material     // Catch: java.lang.Throwable -> Lda
            if (r8 != r7) goto L4e
            android.graphics.PorterDuff$Mode r2 = android.graphics.PorterDuff.Mode.MULTIPLY     // Catch: java.lang.Throwable -> Lda
        L4e:
            if (r2 == 0) goto Ld4
            j0.b.i(r0, r2)     // Catch: java.lang.Throwable -> Lda
            goto Ld4
        L55:
            androidx.appcompat.widget.y r1 = r6.f1315e     // Catch: java.lang.Throwable -> Lda
            if (r1 == 0) goto Lcb
            int r1 = f.e.abc_seekbar_track_material     // Catch: java.lang.Throwable -> Lda
            r3 = 16908301(0x102000d, float:2.3877265E-38)
            r4 = 16908303(0x102000f, float:2.387727E-38)
            r5 = 16908288(0x1020000, float:2.387723E-38)
            if (r8 != r1) goto L92
            r8 = r0
            android.graphics.drawable.LayerDrawable r8 = (android.graphics.drawable.LayerDrawable) r8     // Catch: java.lang.Throwable -> Lda
            android.graphics.drawable.Drawable r9 = r8.findDrawableByLayerId(r5)     // Catch: java.lang.Throwable -> Lda
            int r1 = f.a.colorControlNormal     // Catch: java.lang.Throwable -> Lda
            int r1 = androidx.appcompat.widget.l3.c(r7, r1)     // Catch: java.lang.Throwable -> Lda
            android.graphics.PorterDuff$Mode r2 = androidx.appcompat.widget.z.f1492b     // Catch: java.lang.Throwable -> Lda
            androidx.appcompat.widget.y.g(r9, r1, r2)     // Catch: java.lang.Throwable -> Lda
            android.graphics.drawable.Drawable r9 = r8.findDrawableByLayerId(r4)     // Catch: java.lang.Throwable -> Lda
            int r1 = f.a.colorControlNormal     // Catch: java.lang.Throwable -> Lda
            int r1 = androidx.appcompat.widget.l3.c(r7, r1)     // Catch: java.lang.Throwable -> Lda
            androidx.appcompat.widget.y.g(r9, r1, r2)     // Catch: java.lang.Throwable -> Lda
            android.graphics.drawable.Drawable r8 = r8.findDrawableByLayerId(r3)     // Catch: java.lang.Throwable -> Lda
            int r9 = f.a.colorControlActivated     // Catch: java.lang.Throwable -> Lda
            int r7 = androidx.appcompat.widget.l3.c(r7, r9)     // Catch: java.lang.Throwable -> Lda
            androidx.appcompat.widget.y.g(r8, r7, r2)     // Catch: java.lang.Throwable -> Lda
            goto Ld4
        L92:
            int r1 = f.e.abc_ratingbar_material     // Catch: java.lang.Throwable -> Lda
            if (r8 == r1) goto L9e
            int r1 = f.e.abc_ratingbar_indicator_material     // Catch: java.lang.Throwable -> Lda
            if (r8 == r1) goto L9e
            int r1 = f.e.abc_ratingbar_small_material     // Catch: java.lang.Throwable -> Lda
            if (r8 != r1) goto Lcb
        L9e:
            r8 = r0
            android.graphics.drawable.LayerDrawable r8 = (android.graphics.drawable.LayerDrawable) r8     // Catch: java.lang.Throwable -> Lda
            android.graphics.drawable.Drawable r9 = r8.findDrawableByLayerId(r5)     // Catch: java.lang.Throwable -> Lda
            int r1 = f.a.colorControlNormal     // Catch: java.lang.Throwable -> Lda
            int r1 = androidx.appcompat.widget.l3.b(r7, r1)     // Catch: java.lang.Throwable -> Lda
            android.graphics.PorterDuff$Mode r2 = androidx.appcompat.widget.z.f1492b     // Catch: java.lang.Throwable -> Lda
            androidx.appcompat.widget.y.g(r9, r1, r2)     // Catch: java.lang.Throwable -> Lda
            android.graphics.drawable.Drawable r9 = r8.findDrawableByLayerId(r4)     // Catch: java.lang.Throwable -> Lda
            int r1 = f.a.colorControlActivated     // Catch: java.lang.Throwable -> Lda
            int r1 = androidx.appcompat.widget.l3.c(r7, r1)     // Catch: java.lang.Throwable -> Lda
            androidx.appcompat.widget.y.g(r9, r1, r2)     // Catch: java.lang.Throwable -> Lda
            android.graphics.drawable.Drawable r8 = r8.findDrawableByLayerId(r3)     // Catch: java.lang.Throwable -> Lda
            int r9 = f.a.colorControlActivated     // Catch: java.lang.Throwable -> Lda
            int r7 = androidx.appcompat.widget.l3.c(r7, r9)     // Catch: java.lang.Throwable -> Lda
            androidx.appcompat.widget.y.g(r8, r7, r2)     // Catch: java.lang.Throwable -> Lda
            goto Ld4
        Lcb:
            boolean r7 = r6.i(r7, r8, r0)     // Catch: java.lang.Throwable -> Lda
            if (r7 != 0) goto Ld4
            if (r9 == 0) goto Ld4
            r0 = r2
        Ld4:
            if (r0 == 0) goto Ldc
            androidx.appcompat.widget.m1.a(r0)     // Catch: java.lang.Throwable -> Lda
            goto Ldc
        Lda:
            r7 = move-exception
            goto Le9
        Ldc:
            monitor-exit(r6)
            return r0
        Lde:
            r7 = 0
            r6.f1314d = r7     // Catch: java.lang.Throwable -> Lda
            java.lang.IllegalStateException r7 = new java.lang.IllegalStateException     // Catch: java.lang.Throwable -> Lda
            java.lang.String r8 = "This app has been built with an incorrect configuration. Please configure your build for VectorDrawableCompat."
            r7.<init>(r8)     // Catch: java.lang.Throwable -> Lda
            throw r7     // Catch: java.lang.Throwable -> Lda
        Le9:
            monitor-exit(r6)
            throw r7
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.appcompat.widget.l2.f(android.content.Context, int, boolean):android.graphics.drawable.Drawable");
    }

    public final synchronized ColorStateList h(Context context, int i7) {
        ColorStateList colorStateList;
        q.l lVar;
        WeakHashMap weakHashMap = this.f1311a;
        ColorStateList colorStateList2 = null;
        colorStateList = (weakHashMap == null || (lVar = (q.l) weakHashMap.get(context)) == null) ? null : (ColorStateList) lVar.c(i7, null);
        if (colorStateList == null) {
            y yVar = this.f1315e;
            if (yVar != null) {
                colorStateList2 = yVar.d(context, i7);
            }
            if (colorStateList2 != null) {
                if (this.f1311a == null) {
                    this.f1311a = new WeakHashMap();
                }
                q.l lVar2 = (q.l) this.f1311a.get(context);
                if (lVar2 == null) {
                    lVar2 = new q.l();
                    this.f1311a.put(context, lVar2);
                }
                lVar2.a(i7, colorStateList2);
            }
            colorStateList = colorStateList2;
        }
        return colorStateList;
    }

    /* JADX WARN: Removed duplicated region for block: B:15:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:9:0x0053  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final boolean i(android.content.Context r8, int r9, android.graphics.drawable.Drawable r10) {
        /*
            r7 = this;
            androidx.appcompat.widget.y r0 = r7.f1315e
            r1 = 0
            if (r0 == 0) goto L6a
            android.graphics.PorterDuff$Mode r2 = androidx.appcompat.widget.z.f1492b
            java.lang.Object r3 = r0.f1482b
            int[] r3 = (int[]) r3
            boolean r3 = androidx.appcompat.widget.y.a(r9, r3)
            r4 = 1
            r5 = -1
            if (r3 == 0) goto L18
            int r9 = f.a.colorControlNormal
        L15:
            r3 = r4
        L16:
            r0 = r5
            goto L51
        L18:
            java.lang.Object r3 = r0.f1484d
            int[] r3 = (int[]) r3
            boolean r3 = androidx.appcompat.widget.y.a(r9, r3)
            if (r3 == 0) goto L25
            int r9 = f.a.colorControlActivated
            goto L15
        L25:
            java.lang.Object r0 = r0.f1485e
            int[] r0 = (int[]) r0
            boolean r0 = androidx.appcompat.widget.y.a(r9, r0)
            r3 = 16842801(0x1010031, float:2.3693695E-38)
            if (r0 == 0) goto L36
            android.graphics.PorterDuff$Mode r2 = android.graphics.PorterDuff.Mode.MULTIPLY
        L34:
            r9 = r3
            goto L15
        L36:
            int r0 = f.e.abc_list_divider_mtrl_alpha
            if (r9 != r0) goto L49
            r9 = 1109603123(0x42233333, float:40.8)
            int r9 = java.lang.Math.round(r9)
            r0 = 16842800(0x1010030, float:2.3693693E-38)
            r3 = r4
            r6 = r0
            r0 = r9
            r9 = r6
            goto L51
        L49:
            int r0 = f.e.abc_dialog_material_background
            if (r9 != r0) goto L4e
            goto L34
        L4e:
            r9 = r1
            r3 = r9
            goto L16
        L51:
            if (r3 == 0) goto L6a
            int[] r1 = androidx.appcompat.widget.m1.f1350a
            android.graphics.drawable.Drawable r10 = r10.mutate()
            int r8 = androidx.appcompat.widget.l3.c(r8, r9)
            android.graphics.PorterDuffColorFilter r8 = androidx.appcompat.widget.z.c(r8, r2)
            r10.setColorFilter(r8)
            if (r0 == r5) goto L69
            r10.setAlpha(r0)
        L69:
            r1 = r4
        L6a:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.appcompat.widget.l2.i(android.content.Context, int, android.graphics.drawable.Drawable):boolean");
    }
}
