package n1;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.TypeEvaluator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.opengl.EGL14;
import android.opengl.EGLConfig;
import android.opengl.EGLContext;
import android.opengl.EGLDisplay;
import android.opengl.EGLSurface;
import android.opengl.GLES20;
import android.os.Build;
import android.os.ParcelFileDescriptor;
import android.os.Process;
import android.os.StrictMode;
import android.os.Trace;
import android.util.AttributeSet;
import android.util.Base64;
import android.util.Property;
import android.util.SparseArray;
import android.util.TypedValue;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.view.ViewGroup;
import android.widget.EdgeEffect;
import androidx.lifecycle.p1;
import androidx.recyclerview.widget.d1;
import androidx.recyclerview.widget.g2;
import androidx.recyclerview.widget.r1;
import com.navi.tool.NaviApplication;
import com.navi.tool.utils.SharedPreferencesUtils;
import com.yalantis.ucrop.view.CropImageView;
import java.io.ByteArrayOutputStream;
import java.io.Closeable;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.ref.WeakReference;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.security.InvalidAlgorithmParameterException;
import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.zip.Deflater;
import java.util.zip.DeflaterOutputStream;
import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import org.xmlpull.v1.XmlPullParser;
import p0.c1;
import p0.o0;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public abstract class z {

    /* renamed from: a, reason: collision with root package name */
    public static long f7688a;

    /* renamed from: b, reason: collision with root package name */
    public static Method f7689b;

    /* renamed from: c, reason: collision with root package name */
    public static volatile Bitmap f7690c;

    /* renamed from: d, reason: collision with root package name */
    public static volatile boolean f7691d;

    /* renamed from: e, reason: collision with root package name */
    public static boolean f7692e;

    /* renamed from: f, reason: collision with root package name */
    public static Method f7693f;

    /* renamed from: g, reason: collision with root package name */
    public static boolean f7694g;

    /* renamed from: h, reason: collision with root package name */
    public static Field f7695h;

    public static final void A(g6.a0 a0Var, Throwable th) {
        if (a0Var != null) {
            if (th == null) {
                a0Var.close();
                return;
            }
            try {
                a0Var.close();
            } catch (Throwable th2) {
                e(th, th2);
            }
        }
    }

    public static int A0(int i7) {
        if (i7 == 1) {
            return 0;
        }
        if (i7 == 2) {
            return 1;
        }
        if (i7 == 4) {
            return 2;
        }
        if (i7 == 8) {
            return 3;
        }
        if (i7 == 16) {
            return 4;
        }
        if (i7 == 32) {
            return 5;
        }
        if (i7 == 64) {
            return 6;
        }
        if (i7 == 128) {
            return 7;
        }
        if (i7 == 256) {
            return 8;
        }
        throw new IllegalArgumentException(a.a.d("type needs to be >= FIRST and <= LAST, type=", i7));
    }

    public static void B(Closeable closeable) {
        if (closeable != null) {
            try {
                closeable.close();
            } catch (IOException unused) {
            }
        }
    }

    public static boolean B0() {
        int i7 = Build.VERSION.SDK_INT;
        if (i7 < 33) {
            if (i7 >= 32) {
                String str = Build.VERSION.CODENAME;
                if (!"REL".equals(str)) {
                    Locale locale = Locale.ROOT;
                    if (str.toUpperCase(locale).compareTo("Tiramisu".toUpperCase(locale)) >= 0) {
                    }
                }
            }
            return false;
        }
        return true;
    }

    public static void C(Canvas canvas, Paint paint, Paint paint2) {
        paint2.setStrokeWidth(4.5f);
        paint2.setColor(1432663542);
        canvas.drawLine(-28.0f, 26.0f, 2.0f, -2.0f, paint2);
        paint2.setStrokeWidth(2.4f);
        paint2.setColor(-1433351431);
        canvas.drawLine(-22.0f, 20.0f, 2.0f, -2.0f, paint2);
        paint2.setStrokeWidth(1.2f);
        paint2.setColor(-1);
        canvas.drawLine(-16.0f, 14.0f, 1.0f, -1.0f, paint2);
        paint.setColor(-1);
        canvas.drawCircle(4.0f, -4.0f, 4.6f, paint);
    }

    public static boolean C0(int i7, Rect rect, Rect rect2) {
        if (i7 == 17) {
            int i8 = rect.right;
            int i9 = rect2.right;
            return (i8 > i9 || rect.left >= i9) && rect.left > rect2.left;
        }
        if (i7 == 33) {
            int i10 = rect.bottom;
            int i11 = rect2.bottom;
            return (i10 > i11 || rect.top >= i11) && rect.top > rect2.top;
        }
        if (i7 == 66) {
            int i12 = rect.left;
            int i13 = rect2.left;
            return (i12 < i13 || rect.right <= i13) && rect.right < rect2.right;
        }
        if (i7 != 130) {
            throw new IllegalArgumentException("direction must be one of {FOCUS_UP, FOCUS_DOWN, FOCUS_LEFT, FOCUS_RIGHT}.");
        }
        int i14 = rect.top;
        int i15 = rect2.top;
        return (i14 < i15 || rect.bottom <= i15) && rect.bottom < rect2.bottom;
    }

    public static byte[] D(byte[] bArr) {
        Deflater deflater = new Deflater(1);
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        try {
            DeflaterOutputStream deflaterOutputStream = new DeflaterOutputStream(byteArrayOutputStream, deflater);
            try {
                deflaterOutputStream.write(bArr);
                deflaterOutputStream.close();
                deflater.end();
                return byteArrayOutputStream.toByteArray();
            } finally {
            }
        } catch (Throwable th) {
            deflater.end();
            throw th;
        }
    }

    public static boolean D0(char c7) {
        return c7 >= '0' && c7 <= '9';
    }

    public static int E(g2 g2Var, d1 d1Var, View view, View view2, r1 r1Var, boolean z6) {
        if (r1Var.w() == 0 || g2Var.b() == 0 || view == null || view2 == null) {
            return 0;
        }
        if (!z6) {
            return Math.abs(r1.K(view) - r1.K(view2)) + 1;
        }
        return Math.min(d1Var.g(), d1Var.b(view2) - d1Var.d(view));
    }

    public static boolean E0() {
        boolean isEnabled;
        try {
            if (f7689b == null) {
                isEnabled = Trace.isEnabled();
                return isEnabled;
            }
        } catch (NoClassDefFoundError | NoSuchMethodError unused) {
        }
        try {
            if (f7689b == null) {
                f7688a = Trace.class.getField("TRACE_TAG_APP").getLong(null);
                f7689b = Trace.class.getMethod("isTagEnabled", Long.TYPE);
            }
            return ((Boolean) f7689b.invoke(null, Long.valueOf(f7688a))).booleanValue();
        } catch (Exception e7) {
            if (!(e7 instanceof InvocationTargetException)) {
                return false;
            }
            Throwable cause = e7.getCause();
            if (cause instanceof RuntimeException) {
                throw ((RuntimeException) cause);
            }
            throw new RuntimeException(cause);
        }
    }

    public static int F(g2 g2Var, d1 d1Var, View view, View view2, r1 r1Var, boolean z6, boolean z7) {
        if (r1Var.w() == 0 || g2Var.b() == 0 || view == null || view2 == null) {
            return 0;
        }
        int max = z7 ? Math.max(0, (g2Var.b() - Math.max(r1.K(view), r1.K(view2))) - 1) : Math.max(0, Math.min(r1.K(view), r1.K(view2)));
        if (z6) {
            return Math.round((max * (Math.abs(d1Var.b(view2) - d1Var.d(view)) / (Math.abs(r1.K(view) - r1.K(view2)) + 1))) + (d1Var.f() - d1Var.d(view)));
        }
        return max;
    }

    public static void F0(Canvas canvas, Paint paint, Path path, Paint paint2, int i7, int i8, int i9) {
        a.a.n(paint, i7, path, CropImageView.DEFAULT_ASPECT_RATIO, -22.0f);
        path.lineTo(16.0f, CropImageView.DEFAULT_ASPECT_RATIO);
        a.a.o(path, CropImageView.DEFAULT_ASPECT_RATIO, 20.0f, -16.0f, CropImageView.DEFAULT_ASPECT_RATIO);
        canvas.drawPath(path, paint);
        paint.setColor(i8);
        path.reset();
        path.moveTo(CropImageView.DEFAULT_ASPECT_RATIO, -22.0f);
        a.a.o(path, CropImageView.DEFAULT_ASPECT_RATIO, 20.0f, -16.0f, CropImageView.DEFAULT_ASPECT_RATIO);
        canvas.drawPath(path, paint);
        paint2.setColor(i8);
        paint2.setStrokeWidth(1.6f);
        Path path2 = new Path();
        path2.moveTo(CropImageView.DEFAULT_ASPECT_RATIO, 20.0f);
        path2.quadTo(10.0f, 24.0f, 2.0f, 27.0f);
        path2.quadTo(-8.0f, 29.0f, 4.0f, 31.0f);
        canvas.drawPath(path2, paint2);
        if (i9 > 3) {
            canvas.drawLine(6.0f, 24.0f, 12.0f, 24.0f, paint2);
        }
    }

    public static int G(g2 g2Var, d1 d1Var, View view, View view2, r1 r1Var, boolean z6) {
        if (r1Var.w() == 0 || g2Var.b() == 0 || view == null || view2 == null) {
            return 0;
        }
        if (!z6) {
            return g2Var.b();
        }
        return (int) (((d1Var.b(view2) - d1Var.d(view)) / (Math.abs(r1.K(view) - r1.K(view2)) + 1)) * g2Var.b());
    }

    public static List G0(Object obj) {
        List singletonList = Collections.singletonList(obj);
        i5.a.f("singletonList(element)", singletonList);
        return singletonList;
    }

    public static float[] H(float[] fArr, int i7) {
        if (i7 < 0) {
            throw new IllegalArgumentException();
        }
        int length = fArr.length;
        if (length < 0) {
            throw new ArrayIndexOutOfBoundsException();
        }
        int min = Math.min(i7, length);
        float[] fArr2 = new float[i7];
        System.arraycopy(fArr, 0, fArr2, 0, min);
        return fArr2;
    }

    public static int H0(int i7, Rect rect, Rect rect2) {
        int i8;
        int i9;
        if (i7 == 17) {
            i8 = rect.left;
            i9 = rect2.right;
        } else if (i7 == 33) {
            i8 = rect.top;
            i9 = rect2.bottom;
        } else if (i7 == 66) {
            i8 = rect2.left;
            i9 = rect.right;
        } else {
            if (i7 != 130) {
                throw new IllegalArgumentException("direction must be one of {FOCUS_UP, FOCUS_DOWN, FOCUS_LEFT, FOCUS_RIGHT}.");
            }
            i8 = rect2.top;
            i9 = rect.bottom;
        }
        return Math.max(0, i8 - i9);
    }

    public static boolean I(File file, Resources resources, int i7) {
        InputStream inputStream;
        try {
            inputStream = resources.openRawResource(i7);
            try {
                boolean J = J(file, inputStream);
                B(inputStream);
                return J;
            } catch (Throwable th) {
                th = th;
                B(inputStream);
                throw th;
            }
        } catch (Throwable th2) {
            th = th2;
            inputStream = null;
        }
    }

    public static int I0(int i7) {
        return i7 < 0 ? i7 : i7 < 3 ? i7 + 1 : i7 < 1073741824 ? (int) ((i7 / 0.75f) + 1.0f) : androidx.appcompat.widget.u.MAX_ACTIVITY_COUNT_UNLIMITED;
    }

    public static boolean J(File file, InputStream inputStream) {
        FileOutputStream fileOutputStream;
        StrictMode.ThreadPolicy allowThreadDiskWrites = StrictMode.allowThreadDiskWrites();
        FileOutputStream fileOutputStream2 = null;
        try {
            try {
                fileOutputStream = new FileOutputStream(file, false);
            } catch (IOException e7) {
                e = e7;
            }
        } catch (Throwable th) {
            th = th;
        }
        try {
            byte[] bArr = new byte[1024];
            while (true) {
                int read = inputStream.read(bArr);
                if (read == -1) {
                    B(fileOutputStream);
                    StrictMode.setThreadPolicy(allowThreadDiskWrites);
                    return true;
                }
                fileOutputStream.write(bArr, 0, read);
            }
        } catch (IOException e8) {
            e = e8;
            fileOutputStream2 = fileOutputStream;
            e.getMessage();
            B(fileOutputStream2);
            StrictMode.setThreadPolicy(allowThreadDiskWrites);
            return false;
        } catch (Throwable th2) {
            th = th2;
            fileOutputStream2 = fileOutputStream;
            B(fileOutputStream2);
            StrictMode.setThreadPolicy(allowThreadDiskWrites);
            throw th;
        }
    }

    public static void J0(Canvas canvas, Paint paint, Paint paint2) {
        paint2.setStrokeWidth(3.0f);
        paint2.setColor(-2659);
        canvas.drawLine(-24.0f, 22.0f, 4.0f, -6.0f, paint2);
        paint2.setStrokeWidth(1.6f);
        paint2.setColor(-1);
        canvas.drawLine(-16.0f, 14.0f, 2.0f, -4.0f, paint2);
        paint.setColor(-1);
        canvas.drawCircle(6.0f, -8.0f, 4.0f, paint);
    }

    public static void K(Canvas canvas, Paint paint, Paint paint2, Path path) {
        a.a.n(paint, -657931, path, CropImageView.DEFAULT_ASPECT_RATIO, CropImageView.DEFAULT_ASPECT_RATIO);
        path.quadTo(-14.0f, -12.0f, -26.0f, -6.0f);
        path.quadTo(-14.0f, 2.0f, CropImageView.DEFAULT_ASPECT_RATIO, 5.0f);
        path.close();
        canvas.drawPath(path, paint);
        path.reset();
        path.moveTo(CropImageView.DEFAULT_ASPECT_RATIO, CropImageView.DEFAULT_ASPECT_RATIO);
        path.quadTo(14.0f, -12.0f, 26.0f, -6.0f);
        path.quadTo(14.0f, 2.0f, CropImageView.DEFAULT_ASPECT_RATIO, 5.0f);
        path.close();
        canvas.drawPath(path, paint);
        paint.setColor(-14606047);
        path.reset();
        path.moveTo(-20.0f, -7.0f);
        path.quadTo(-26.0f, -6.0f, -26.0f, -6.0f);
        path.quadTo(-22.0f, -3.0f, -18.0f, -4.0f);
        path.close();
        canvas.drawPath(path, paint);
        path.reset();
        path.moveTo(20.0f, -7.0f);
        path.quadTo(26.0f, -6.0f, 26.0f, -6.0f);
        path.quadTo(22.0f, -3.0f, 18.0f, -4.0f);
        path.close();
        canvas.drawPath(path, paint);
        paint2.setColor(-657931);
        paint2.setStrokeWidth(2.6f);
        canvas.drawLine(CropImageView.DEFAULT_ASPECT_RATIO, 2.0f, 4.0f, 12.0f, paint2);
        paint.setColor(-657931);
        canvas.drawCircle(4.6f, 13.0f, 2.6f, paint);
        paint.setColor(-2937041);
        canvas.drawCircle(4.6f, 11.4f, 1.4f, paint);
        paint2.setColor(-9079435);
        paint2.setStrokeWidth(1.1f);
        canvas.drawLine(-1.0f, 5.0f, -6.0f, 16.0f, paint2);
        canvas.drawLine(1.0f, 5.0f, 6.0f, 16.0f, paint2);
    }

    public static void K0(Canvas canvas, Paint paint, Paint paint2) {
        float[][] fArr = {new float[]{-24.0f, 24.0f, 4.0f, -4.0f}, new float[]{-6.0f, 28.0f, 16.0f, 2.0f}, new float[]{6.0f, 16.0f, 24.0f, -6.0f}};
        for (int i7 = 0; i7 < 3; i7++) {
            float[] fArr2 = fArr[i7];
            paint2.setStrokeWidth(2.0f);
            paint2.setColor(-855640675);
            canvas.drawLine(fArr2[0], fArr2[1], fArr2[2], fArr2[3], paint2);
            paint.setColor(-1);
            canvas.drawCircle(fArr2[2] + 1.0f, fArr2[3] - 1.0f, 2.2f, paint);
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:19:0x007d  */
    /* JADX WARN: Removed duplicated region for block: B:22:? A[RETURN, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static y0.v L(android.content.Context r8) {
        /*
            int r0 = android.os.Build.VERSION.SDK_INT
            r1 = 28
            if (r0 < r1) goto Lc
            y0.d r0 = new y0.d
            r0.<init>()
            goto L11
        Lc:
            y0.c r0 = new y0.c
            r0.<init>()
        L11:
            android.content.pm.PackageManager r1 = r8.getPackageManager()
            java.lang.String r2 = "Package manager required to locate emoji font provider"
            v(r1, r2)
            android.content.Intent r2 = new android.content.Intent
            java.lang.String r3 = "androidx.content.action.LOAD_EMOJI_FONT"
            r2.<init>(r3)
            r3 = 0
            java.util.List r2 = r1.queryIntentContentProviders(r2, r3)
            java.util.Iterator r2 = r2.iterator()
        L2a:
            boolean r4 = r2.hasNext()
            r5 = 0
            if (r4 == 0) goto L46
            java.lang.Object r4 = r2.next()
            android.content.pm.ResolveInfo r4 = (android.content.pm.ResolveInfo) r4
            android.content.pm.ProviderInfo r4 = r4.providerInfo
            if (r4 == 0) goto L2a
            android.content.pm.ApplicationInfo r6 = r4.applicationInfo
            if (r6 == 0) goto L2a
            int r6 = r6.flags
            r7 = 1
            r6 = r6 & r7
            if (r6 != r7) goto L2a
            goto L47
        L46:
            r4 = r5
        L47:
            if (r4 != 0) goto L4b
        L49:
            r1 = r5
            goto L7a
        L4b:
            java.lang.String r2 = r4.authority     // Catch: android.content.pm.PackageManager.NameNotFoundException -> L73
            java.lang.String r4 = r4.packageName     // Catch: android.content.pm.PackageManager.NameNotFoundException -> L73
            android.content.pm.Signature[] r0 = r0.u(r1, r4)     // Catch: android.content.pm.PackageManager.NameNotFoundException -> L73
            java.util.ArrayList r1 = new java.util.ArrayList     // Catch: android.content.pm.PackageManager.NameNotFoundException -> L73
            r1.<init>()     // Catch: android.content.pm.PackageManager.NameNotFoundException -> L73
            int r6 = r0.length     // Catch: android.content.pm.PackageManager.NameNotFoundException -> L73
        L59:
            if (r3 >= r6) goto L67
            r7 = r0[r3]     // Catch: android.content.pm.PackageManager.NameNotFoundException -> L73
            byte[] r7 = r7.toByteArray()     // Catch: android.content.pm.PackageManager.NameNotFoundException -> L73
            r1.add(r7)     // Catch: android.content.pm.PackageManager.NameNotFoundException -> L73
            int r3 = r3 + 1
            goto L59
        L67:
            java.util.List r0 = java.util.Collections.singletonList(r1)     // Catch: android.content.pm.PackageManager.NameNotFoundException -> L73
            androidx.appcompat.widget.w r1 = new androidx.appcompat.widget.w     // Catch: android.content.pm.PackageManager.NameNotFoundException -> L73
            java.lang.String r3 = "emojicompat-emoji-font"
            r1.<init>(r2, r4, r3, r0)     // Catch: android.content.pm.PackageManager.NameNotFoundException -> L73
            goto L7a
        L73:
            r0 = move-exception
            java.lang.String r1 = "emoji2.text.DefaultEmojiConfig"
            android.util.Log.wtf(r1, r0)
            goto L49
        L7a:
            if (r1 != 0) goto L7d
            goto L87
        L7d:
            y0.v r5 = new y0.v
            y0.u r0 = new y0.u
            r0.<init>(r8, r1)
            r5.<init>(r0)
        L87:
            return r5
        */
        throw new UnsupportedOperationException("Method not decompiled: n1.z.L(android.content.Context):y0.v");
    }

    public static int L0(int i7, Rect rect, Rect rect2) {
        if (i7 != 17) {
            if (i7 != 33) {
                if (i7 != 66) {
                    if (i7 != 130) {
                        throw new IllegalArgumentException("direction must be one of {FOCUS_UP, FOCUS_DOWN, FOCUS_LEFT, FOCUS_RIGHT}.");
                    }
                }
            }
            return Math.abs(((rect.width() / 2) + rect.left) - ((rect2.width() / 2) + rect2.left));
        }
        return Math.abs(((rect.height() / 2) + rect.top) - ((rect2.height() / 2) + rect2.top));
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static AnimatorSet M(a3.g gVar, float f7, float f8, float f9) {
        ObjectAnimator ofObject = ObjectAnimator.ofObject(gVar, (Property<a3.g, V>) a3.d.CIRCULAR_REVEAL, (TypeEvaluator) a3.c.CIRCULAR_REVEAL, (Object[]) new a3.f[]{new a3.f(f7, f8, f9)});
        a3.f revealInfo = gVar.getRevealInfo();
        if (revealInfo == null) {
            throw new IllegalStateException("Caller must set a non-null RevealInfo before calling this.");
        }
        Animator createCircularReveal = ViewAnimationUtils.createCircularReveal((View) gVar, (int) f7, (int) f8, revealInfo.f266c, f9);
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.playTogether(ofObject, createCircularReveal);
        return animatorSet;
    }

    public static MappedByteBuffer M0(Context context, Uri uri) {
        try {
            ParcelFileDescriptor a7 = i0.m.a(context.getContentResolver(), uri, "r", null);
            if (a7 == null) {
                if (a7 != null) {
                    a7.close();
                }
                return null;
            }
            try {
                FileInputStream fileInputStream = new FileInputStream(a7.getFileDescriptor());
                try {
                    FileChannel channel = fileInputStream.getChannel();
                    MappedByteBuffer map = channel.map(FileChannel.MapMode.READ_ONLY, 0L, channel.size());
                    fileInputStream.close();
                    a7.close();
                    return map;
                } finally {
                }
            } finally {
            }
        } catch (IOException unused) {
            return null;
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:21:0x0046  */
    /* JADX WARN: Removed duplicated region for block: B:39:0x009b A[Catch: NumberFormatException -> 0x00af, LOOP:3: B:29:0x006d->B:39:0x009b, LOOP_END, TryCatch #0 {NumberFormatException -> 0x00af, blocks: (B:26:0x0059, B:29:0x006d, B:31:0x0073, B:35:0x007f, B:39:0x009b, B:43:0x00a1, B:48:0x00b6, B:60:0x00b9), top: B:25:0x0059 }] */
    /* JADX WARN: Removed duplicated region for block: B:40:0x009a A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:43:0x00a1 A[Catch: NumberFormatException -> 0x00af, TryCatch #0 {NumberFormatException -> 0x00af, blocks: (B:26:0x0059, B:29:0x006d, B:31:0x0073, B:35:0x007f, B:39:0x009b, B:43:0x00a1, B:48:0x00b6, B:60:0x00b9), top: B:25:0x0059 }] */
    /* JADX WARN: Removed duplicated region for block: B:45:0x00b3  */
    /* JADX WARN: Removed duplicated region for block: B:48:0x00b6 A[Catch: NumberFormatException -> 0x00af, TryCatch #0 {NumberFormatException -> 0x00af, blocks: (B:26:0x0059, B:29:0x006d, B:31:0x0073, B:35:0x007f, B:39:0x009b, B:43:0x00a1, B:48:0x00b6, B:60:0x00b9), top: B:25:0x0059 }] */
    /* JADX WARN: Removed duplicated region for block: B:72:0x00e0 A[SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static i0.f[] N(java.lang.String r17) {
        /*
            Method dump skipped, instructions count: 284
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: n1.z.N(java.lang.String):i0.f[]");
    }

    public static String N0(double d7, double d8) {
        String str;
        String str2 = s5.h0.FRAGMENT_ENCODE_SET;
        StringBuilder sb = new StringBuilder();
        sb.append(h());
        sb.append("/wxproxy.php?uid=");
        try {
            str = SharedPreferencesUtils.getLicenseUid(NaviApplication.getInstance());
        } catch (Throwable unused) {
            str = s5.h0.FRAGMENT_ENCODE_SET;
        }
        sb.append(Z(str));
        sb.append("&tk=");
        try {
            str2 = SharedPreferencesUtils.getLicenseToken(NaviApplication.getInstance());
        } catch (Throwable unused2) {
        }
        sb.append(Z(str2));
        sb.append("&p=");
        sb.append(Z("/v7/weather/now"));
        sb.append("&location=");
        sb.append(d8);
        sb.append(",");
        sb.append(d7);
        return sb.toString();
    }

    public static Path O(String str) {
        Path path = new Path();
        i0.f[] N = N(str);
        if (N == null) {
            return null;
        }
        try {
            i0.f.b(N, path);
            return path;
        } catch (RuntimeException e7) {
            throw new RuntimeException(a.a.B("Error in parsing ", str), e7);
        }
    }

    public static TypedArray O0(Resources resources, Resources.Theme theme, AttributeSet attributeSet, int[] iArr) {
        return theme == null ? resources.obtainAttributes(attributeSet, iArr) : theme.obtainStyledAttributes(attributeSet, iArr, 0, 0);
    }

    public static String P(String str, String str2) {
        SecretKeySpec secretKeySpec = new SecretKeySpec(Base64.decode(str, 0), "AES");
        Cipher cipher = Cipher.getInstance("AES/GCM/NoPadding");
        cipher.init(2, secretKeySpec, new IvParameterSpec("7777889654578967".getBytes("utf-8")));
        return new String(cipher.doFinal(Base64.decode(str2, 0)), "utf-8");
    }

    public static float P0(EdgeEffect edgeEffect, float f7, float f8) {
        if (Build.VERSION.SDK_INT >= 31) {
            return androidx.core.widget.g.c(edgeEffect, f7, f8);
        }
        androidx.core.widget.f.a(edgeEffect, f7, f8);
        return f7;
    }

    public static i0.f[] Q(i0.f[] fVarArr) {
        if (fVarArr == null) {
            return null;
        }
        i0.f[] fVarArr2 = new i0.f[fVarArr.length];
        for (int i7 = 0; i7 < fVarArr.length; i7++) {
            i0.f fVar = fVarArr[i7];
            i0.f fVar2 = new i0.f();
            fVar2.f6778a = fVar.f6778a;
            float[] fArr = fVar.f6779b;
            fVar2.f6779b = H(fArr, fArr.length);
            fVarArr2[i7] = fVar2;
        }
        return fVarArr2;
    }

    public static void Q0(Canvas canvas, Paint paint, Path path, RectF rectF) {
        paint.setColor(-9614271);
        rectF.set(-12.0f, -14.0f, 12.0f, 12.0f);
        canvas.drawOval(rectF, paint);
        path.reset();
        path.moveTo(-11.0f, -12.0f);
        path.lineTo(-13.0f, -21.0f);
        path.lineTo(-5.0f, -15.0f);
        path.close();
        canvas.drawPath(path, paint);
        path.reset();
        path.moveTo(11.0f, -12.0f);
        a.a.o(path, 13.0f, -21.0f, 5.0f, -15.0f);
        canvas.drawPath(path, paint);
        paint.setColor(-1823);
        canvas.drawCircle(-5.0f, -6.0f, 4.4f, paint);
        canvas.drawCircle(5.0f, -6.0f, 4.4f, paint);
        paint.setColor(-14606047);
        canvas.drawCircle(-5.0f, -6.0f, 2.2f, paint);
        canvas.drawCircle(5.0f, -6.0f, 2.2f, paint);
        a.a.n(paint, -19712, path, CropImageView.DEFAULT_ASPECT_RATIO, -1.0f);
        a.a.o(path, 2.6f, 3.0f, -2.6f, 3.0f);
        canvas.drawPath(path, paint);
    }

    public static final e1.c R(p1 p1Var) {
        i5.a.g("owner", p1Var);
        return p1Var instanceof androidx.lifecycle.k ? ((androidx.lifecycle.k) p1Var).getDefaultViewModelCreationExtras() : e1.a.INSTANCE;
    }

    public static void R0(Canvas canvas, Paint paint, Path path) {
        a.a.n(paint, -12409355, path, -22.0f, -8.0f);
        path.quadTo(CropImageView.DEFAULT_ASPECT_RATIO, -26.0f, 22.0f, -8.0f);
        path.quadTo(12.0f, -2.0f, CropImageView.DEFAULT_ASPECT_RATIO, -3.0f);
        path.quadTo(-12.0f, -2.0f, -22.0f, -8.0f);
        path.close();
        canvas.drawPath(path, paint);
        paint.setColor(-12232092);
        canvas.drawLine(-14.0f, -5.0f, -2.0f, 12.0f, paint);
        canvas.drawLine(14.0f, -5.0f, 2.0f, 12.0f, paint);
        paint.setColor(-14273992);
        canvas.drawCircle(CropImageView.DEFAULT_ASPECT_RATIO, 13.0f, 3.0f, paint);
        canvas.drawRect(-2.4f, 15.0f, 2.4f, 21.0f, paint);
    }

    public static boolean S(File file) {
        if (!file.isDirectory()) {
            file.delete();
            return true;
        }
        File[] listFiles = file.listFiles();
        if (listFiles == null) {
            return false;
        }
        boolean z6 = true;
        for (File file2 : listFiles) {
            z6 = S(file2) && z6;
        }
        return z6;
    }

    public static void S0(AnimatorSet animatorSet, ArrayList arrayList) {
        int size = arrayList.size();
        long j7 = 0;
        for (int i7 = 0; i7 < size; i7++) {
            Animator animator = (Animator) arrayList.get(i7);
            j7 = Math.max(j7, animator.getDuration() + animator.getStartDelay());
        }
        ValueAnimator ofInt = ValueAnimator.ofInt(0, 0);
        ofInt.setDuration(j7);
        arrayList.add(0, ofInt);
        animatorSet.playTogether(arrayList);
    }

    public static boolean T(View view, KeyEvent keyEvent) {
        WeakReference weakReference;
        ArrayList arrayList;
        int size;
        int indexOfKey;
        int i7 = p0.d1.OVER_SCROLL_ALWAYS;
        if (Build.VERSION.SDK_INT >= 28) {
            return false;
        }
        ArrayList arrayList2 = c1.f8105d;
        c1 c1Var = (c1) view.getTag(d0.c.tag_unhandled_key_event_manager);
        if (c1Var == null) {
            c1Var = new c1();
            c1Var.f8106a = null;
            c1Var.f8107b = null;
            c1Var.f8108c = null;
            view.setTag(d0.c.tag_unhandled_key_event_manager, c1Var);
        }
        WeakReference weakReference2 = c1Var.f8108c;
        if (weakReference2 != null && weakReference2.get() == keyEvent) {
            return false;
        }
        c1Var.f8108c = new WeakReference(keyEvent);
        if (c1Var.f8107b == null) {
            c1Var.f8107b = new SparseArray();
        }
        SparseArray sparseArray = c1Var.f8107b;
        if (keyEvent.getAction() != 1 || (indexOfKey = sparseArray.indexOfKey(keyEvent.getKeyCode())) < 0) {
            weakReference = null;
        } else {
            weakReference = (WeakReference) sparseArray.valueAt(indexOfKey);
            sparseArray.removeAt(indexOfKey);
        }
        if (weakReference == null) {
            weakReference = (WeakReference) sparseArray.get(keyEvent.getKeyCode());
        }
        if (weakReference == null) {
            return false;
        }
        View view2 = (View) weakReference.get();
        if (view2 == null || !o0.b(view2) || (arrayList = (ArrayList) view2.getTag(d0.c.tag_unhandled_key_listeners)) == null || (size = arrayList.size() - 1) < 0) {
            return true;
        }
        a.a.u(arrayList.get(size));
        throw null;
    }

    public static z0.b T0(MappedByteBuffer mappedByteBuffer) {
        long j7;
        ByteBuffer duplicate = mappedByteBuffer.duplicate();
        duplicate.order(ByteOrder.BIG_ENDIAN);
        duplicate.position(duplicate.position() + 4);
        int i7 = duplicate.getShort() & 65535;
        if (i7 > 100) {
            throw new IOException("Cannot read metadata.");
        }
        duplicate.position(duplicate.position() + 6);
        int i8 = 0;
        while (true) {
            if (i8 >= i7) {
                j7 = -1;
                break;
            }
            int i9 = duplicate.getInt();
            duplicate.position(duplicate.position() + 4);
            j7 = duplicate.getInt() & 4294967295L;
            duplicate.position(duplicate.position() + 4);
            if (1835365473 == i9) {
                break;
            }
            i8++;
        }
        if (j7 != -1) {
            duplicate.position(duplicate.position() + ((int) (j7 - duplicate.position())));
            duplicate.position(duplicate.position() + 12);
            long j8 = duplicate.getInt() & 4294967295L;
            for (int i10 = 0; i10 < j8; i10++) {
                int i11 = duplicate.getInt();
                long j9 = duplicate.getInt() & 4294967295L;
                duplicate.getInt();
                if (1164798569 == i11 || 1701669481 == i11) {
                    duplicate.position((int) (j9 + j7));
                    z0.b bVar = new z0.b();
                    duplicate.order(ByteOrder.LITTLE_ENDIAN);
                    int position = duplicate.position() + duplicate.getInt(duplicate.position());
                    bVar.f10245b = duplicate;
                    bVar.f10244a = position;
                    int i12 = position - duplicate.getInt(position);
                    bVar.f10246c = i12;
                    bVar.f10247d = bVar.f10245b.getShort(i12);
                    return bVar;
                }
            }
        }
        throw new IOException("Cannot read metadata.");
    }

    /* JADX WARN: Removed duplicated region for block: B:57:0x00be  */
    /* JADX WARN: Removed duplicated region for block: B:64:? A[RETURN, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static boolean U(p0.n r7, android.view.View r8, android.view.Window.Callback r9, android.view.KeyEvent r10) {
        /*
            r0 = 0
            if (r7 != 0) goto L4
            return r0
        L4:
            int r1 = android.os.Build.VERSION.SDK_INT
            r2 = 28
            if (r1 < r2) goto Lf
            boolean r7 = r7.superDispatchKeyEvent(r10)
            return r7
        Lf:
            boolean r1 = r9 instanceof android.app.Activity
            r2 = 0
            r3 = 1
            if (r1 == 0) goto L81
            android.app.Activity r9 = (android.app.Activity) r9
            r9.onUserInteraction()
            android.view.Window r7 = r9.getWindow()
            r8 = 8
            boolean r8 = r7.hasFeature(r8)
            if (r8 == 0) goto L64
            android.app.ActionBar r8 = r9.getActionBar()
            int r1 = r10.getKeyCode()
            r4 = 82
            if (r1 != r4) goto L64
            if (r8 == 0) goto L64
            boolean r1 = n1.z.f7692e
            if (r1 != 0) goto L4c
            java.lang.Class r1 = r8.getClass()     // Catch: java.lang.NoSuchMethodException -> L4a
            java.lang.String r4 = "onMenuKeyEvent"
            java.lang.Class[] r5 = new java.lang.Class[r3]     // Catch: java.lang.NoSuchMethodException -> L4a
            java.lang.Class<android.view.KeyEvent> r6 = android.view.KeyEvent.class
            r5[r0] = r6     // Catch: java.lang.NoSuchMethodException -> L4a
            java.lang.reflect.Method r0 = r1.getMethod(r4, r5)     // Catch: java.lang.NoSuchMethodException -> L4a
            n1.z.f7693f = r0     // Catch: java.lang.NoSuchMethodException -> L4a
        L4a:
            n1.z.f7692e = r3
        L4c:
            java.lang.reflect.Method r0 = n1.z.f7693f
            if (r0 == 0) goto L64
            java.lang.Object[] r1 = new java.lang.Object[]{r10}     // Catch: java.lang.Throwable -> L64
            java.lang.Object r8 = r0.invoke(r8, r1)     // Catch: java.lang.Throwable -> L64
            if (r8 != 0) goto L5b
            goto L64
        L5b:
            java.lang.Boolean r8 = (java.lang.Boolean) r8     // Catch: java.lang.Throwable -> L64
            boolean r8 = r8.booleanValue()     // Catch: java.lang.Throwable -> L64
            if (r8 == 0) goto L64
            goto L80
        L64:
            boolean r8 = r7.superDispatchKeyEvent(r10)
            if (r8 == 0) goto L6b
            goto L80
        L6b:
            android.view.View r7 = r7.getDecorView()
            boolean r8 = p0.d1.c(r7, r10)
            if (r8 == 0) goto L76
            goto L80
        L76:
            if (r7 == 0) goto L7c
            android.view.KeyEvent$DispatcherState r2 = r7.getKeyDispatcherState()
        L7c:
            boolean r3 = r10.dispatch(r9, r2, r9)
        L80:
            return r3
        L81:
            boolean r1 = r9 instanceof android.app.Dialog
            if (r1 == 0) goto Ld4
            android.app.Dialog r9 = (android.app.Dialog) r9
            boolean r7 = n1.z.f7694g
            if (r7 != 0) goto L9a
            java.lang.Class<android.app.Dialog> r7 = android.app.Dialog.class
            java.lang.String r8 = "mOnKeyListener"
            java.lang.reflect.Field r7 = r7.getDeclaredField(r8)     // Catch: java.lang.NoSuchFieldException -> L98
            n1.z.f7695h = r7     // Catch: java.lang.NoSuchFieldException -> L98
            r7.setAccessible(r3)     // Catch: java.lang.NoSuchFieldException -> L98
        L98:
            n1.z.f7694g = r3
        L9a:
            java.lang.reflect.Field r7 = n1.z.f7695h
            if (r7 == 0) goto La5
            java.lang.Object r7 = r7.get(r9)     // Catch: java.lang.IllegalAccessException -> La5
            android.content.DialogInterface$OnKeyListener r7 = (android.content.DialogInterface.OnKeyListener) r7     // Catch: java.lang.IllegalAccessException -> La5
            goto La6
        La5:
            r7 = r2
        La6:
            if (r7 == 0) goto Lb3
            int r8 = r10.getKeyCode()
            boolean r7 = r7.onKey(r9, r8, r10)
            if (r7 == 0) goto Lb3
            goto Ld3
        Lb3:
            android.view.Window r7 = r9.getWindow()
            boolean r8 = r7.superDispatchKeyEvent(r10)
            if (r8 == 0) goto Lbe
            goto Ld3
        Lbe:
            android.view.View r7 = r7.getDecorView()
            boolean r8 = p0.d1.c(r7, r10)
            if (r8 == 0) goto Lc9
            goto Ld3
        Lc9:
            if (r7 == 0) goto Lcf
            android.view.KeyEvent$DispatcherState r2 = r7.getKeyDispatcherState()
        Lcf:
            boolean r3 = r10.dispatch(r9, r2, r9)
        Ld3:
            return r3
        Ld4:
            if (r8 == 0) goto Ldc
            boolean r8 = p0.d1.c(r8, r10)
            if (r8 != 0) goto Le2
        Ldc:
            boolean r7 = r7.superDispatchKeyEvent(r10)
            if (r7 == 0) goto Le3
        Le2:
            r0 = r3
        Le3:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: n1.z.U(p0.n, android.view.View, android.view.Window$Callback, android.view.KeyEvent):boolean");
    }

    public static byte[] U0(InputStream inputStream, int i7) {
        byte[] bArr = new byte[i7];
        int i8 = 0;
        while (i8 < i7) {
            int read = inputStream.read(bArr, i8, i7 - i8);
            if (read < 0) {
                throw new IllegalStateException(a.a.d("Not enough bytes to read: ", i7));
            }
            i8 += read;
        }
        return bArr;
    }

    public static void V(Canvas canvas, int i7, Paint paint, Paint paint2, Path path, RectF rectF) {
        float f7 = 1.8f;
        float f8 = 14.0f;
        float f9 = 8.0f;
        float f10 = 4.0f;
        switch (i7) {
            case 0:
                o(canvas, paint, 27.0f, 0.55f, -10665929, -12703965, CropImageView.DEFAULT_ASPECT_RATIO);
                break;
            case 1:
                o(canvas, paint, 16.0f, 0.75f, -7508381, -10665929, CropImageView.DEFAULT_ASPECT_RATIO);
                break;
            case 2:
                o(canvas, paint, 21.0f, 0.62f, -14273992, -14983648, 1.0f);
                break;
            case 3:
                o(canvas, paint, 28.0f, 0.42f, -9614271, -11652050, CropImageView.DEFAULT_ASPECT_RATIO);
                d0(canvas, paint);
                break;
            case 4:
                o(canvas, paint, 19.0f, 0.7f, -1249295, -3155748, CropImageView.DEFAULT_ASPECT_RATIO);
                break;
            case 5:
                o(canvas, paint, 24.0f, 0.5f, -14606047, p0.d1.MEASURED_STATE_MASK, CropImageView.DEFAULT_ASPECT_RATIO);
                break;
            case 6:
                k(canvas, paint, path, 26.0f);
                break;
            case 7:
                f(canvas, paint, -1, -5194043, false);
                break;
            case 8:
                o(canvas, paint, 24.0f, 0.6f, -1, -2039584, CropImageView.DEFAULT_ASPECT_RATIO);
                l(canvas, paint);
                break;
            case 9:
                z0(canvas, paint, path, rectF);
                break;
            case 10:
                y0(canvas, paint, paint2, rectF, -13154481, false);
                break;
            case 11:
                R0(canvas, paint, path);
                break;
            case 12:
                J0(canvas, paint, paint2);
                break;
            case 13:
                f(canvas, paint, -12232092, -44462, true);
                break;
            case 14:
                e1(canvas, paint, rectF);
                break;
            case 15:
                o(canvas, paint, 22.0f, 0.66f, -8875876, -11243910, CropImageView.DEFAULT_ASPECT_RATIO);
                break;
            case 16:
                o(canvas, paint, 15.0f, 0.85f, -13154481, -14273992, CropImageView.DEFAULT_ASPECT_RATIO);
                break;
            case 17:
                g1(canvas, paint, path);
                break;
            case 18:
                K(canvas, paint, paint2, path);
                break;
            case 19:
                o(canvas, paint, 19.0f, 0.72f, -7508381, -11652050, CropImageView.DEFAULT_ASPECT_RATIO);
                break;
            case 20:
                p(canvas, paint, path, rectF);
                break;
            case 21:
                j(canvas, paint);
                break;
            case 22:
                g(canvas, paint, path);
                break;
            case 23:
                F0(canvas, paint, path, paint2, -1092784, -13784, 3);
                break;
            case 24:
                h1(canvas, paint, rectF);
                break;
            case 25:
                f1(canvas, paint, path);
                break;
            case 26:
                u0(canvas, paint, path);
                break;
            case 27:
                W(canvas, paint, paint2);
                break;
            case 28:
                a.a.n(paint, -328966, path, -22.0f, -10.0f);
                path.lineTo(24.0f, 2.0f);
                path.lineTo(-6.0f, 8.0f);
                a.a.o(path, -10.0f, 18.0f, -12.0f, 6.0f);
                canvas.drawPath(path, paint);
                paint.setColor(-3155748);
                path.reset();
                path.moveTo(-22.0f, -10.0f);
                a.a.o(path, 24.0f, 2.0f, -12.0f, 6.0f);
                canvas.drawPath(path, paint);
                break;
            case 29:
                r(canvas, paint, paint2, -44462, 8, 1.0f);
                break;
            case 30:
                X(canvas, paint, paint2);
                break;
            case 31:
                a1(canvas, paint, rectF);
                break;
            case 32:
                C(canvas, paint, paint2);
                break;
            case 33:
                K0(canvas, paint, paint2);
                break;
            case 34:
                Q0(canvas, paint, path, rectF);
                break;
            case 35:
                y0(canvas, paint, paint2, rectF, -14273992, true);
                break;
            case 36:
                paint.setColor(872411510);
                Path path2 = new Path();
                path2.moveTo(CropImageView.DEFAULT_ASPECT_RATIO, 20.0f);
                path2.lineTo(-6.0f, 20.0f);
                a.a.o(path2, -22.0f, -24.0f, 6.0f, -24.0f);
                canvas.drawPath(path2, paint);
                paint.setColor(1442836854);
                Path path3 = new Path();
                path3.moveTo(CropImageView.DEFAULT_ASPECT_RATIO, 20.0f);
                path3.lineTo(-3.0f, 20.0f);
                a.a.o(path3, -12.0f, -24.0f, 2.0f, -24.0f);
                canvas.drawPath(path3, paint);
                paint.setColor(-13154481);
                canvas.drawRect(-7.0f, 18.0f, 7.0f, 24.0f, paint);
                paint.setColor(-5317);
                canvas.drawCircle(CropImageView.DEFAULT_ASPECT_RATIO, 20.0f, 3.0f, paint);
                break;
            case 37:
                F0(canvas, paint, path, paint2, -8630785, -16718337, 2);
                paint.setColor(1711334911);
                canvas.drawCircle(CropImageView.DEFAULT_ASPECT_RATIO, CropImageView.DEFAULT_ASPECT_RATIO, 20.0f, paint);
                break;
            case 38:
                a.a.n(paint, -2937041, path, -20.0f, -6.0f);
                path.quadTo(-4.0f, -16.0f, 10.0f, -6.0f);
                path.quadTo(14.0f, 4.0f, CropImageView.DEFAULT_ASPECT_RATIO, 8.0f);
                path.quadTo(-14.0f, 6.0f, -20.0f, -6.0f);
                path.close();
                canvas.drawPath(path, paint);
                paint.setColor(-10929);
                canvas.drawCircle(-6.0f, -4.0f, 2.6f, paint);
                paint2.setColor(-10929);
                paint2.setStrokeWidth(2.0f);
                Path path4 = new Path();
                path4.moveTo(-18.0f, -6.0f);
                path4.quadTo(-26.0f, -14.0f, -22.0f, -20.0f);
                canvas.drawPath(path4, paint2);
                for (int i8 = 0; i8 < 3; i8++) {
                    paint.setColor(i8 % 2 == 0 ? -13784 : -36797);
                    float f11 = i8;
                    canvas.drawCircle((f11 * 6.0f) + 12.0f, (f11 * 4.0f) + 2.0f, 5.0f - (f11 * 0.8f), paint);
                }
                break;
            case 39:
                x0(path, CropImageView.DEFAULT_ASPECT_RATIO, -6.0f, 15.0f);
                paint.setColor(-37238);
                canvas.drawPath(path, paint);
                paint.setColor(1442840575);
                canvas.drawCircle(-5.0f, -12.0f, 3.4f, paint);
                paint.setColor(-6381922);
                canvas.drawLine(CropImageView.DEFAULT_ASPECT_RATIO, 8.0f, CropImageView.DEFAULT_ASPECT_RATIO, 24.0f, paint);
                break;
            case 40:
                float f12 = 11.0f;
                float f13 = -8.0f;
                int[] iArr = {-30080, -11904, -8333057};
                int i9 = 0;
                while (i9 < 3) {
                    float f14 = (i9 * f12) - 11.0f;
                    float abs = (Math.abs(i9 - 1) * 6.0f) + f13;
                    paint.setColor(iArr[i9]);
                    float f15 = abs + 8.0f;
                    rectF.set(f14 - 7.0f, abs - 8.0f, 7.0f + f14, f15);
                    canvas.drawOval(rectF, paint);
                    paint.setColor(1157627903);
                    canvas.drawCircle(f14 - 2.0f, abs - 3.0f, 2.0f, paint);
                    paint.setColor(-1426063361);
                    canvas.drawLine(f14, f15, CropImageView.DEFAULT_ASPECT_RATIO, 24.0f, paint);
                    i9++;
                    f12 = 11.0f;
                    f13 = -8.0f;
                }
                break;
            case 41:
                o(canvas, paint, 21.0f, 0.58f, -14606047, -14273992, CropImageView.DEFAULT_ASPECT_RATIO);
                paint.setColor(-657931);
                canvas.drawRect(-2.6f, -1.0f, 2.6f, 9.0f, paint);
                break;
            case 42:
                paint.setColor(-7508381);
                canvas.drawRect(-2.0f, CropImageView.DEFAULT_ASPECT_RATIO, 2.0f, 22.0f, paint);
                a.a.n(paint, -19712, path, -2.0f, 20.0f);
                path.lineTo(-11.0f, 28.0f);
                a.a.o(path, 11.0f, 28.0f, 2.0f, 20.0f);
                canvas.drawPath(path, paint);
                paint.setColor(-11922292);
                path.reset();
                path.moveTo(-9.0f, 2.0f);
                path.quadTo(CropImageView.DEFAULT_ASPECT_RATIO, -6.0f, 9.0f, 2.0f);
                a.a.o(path, 5.0f, 14.0f, -5.0f, 14.0f);
                canvas.drawPath(path, paint);
                paint.setColor(-13558894);
                path.reset();
                path.moveTo(-9.0f, 1.0f);
                a.a.o(path, 2.0f, -22.0f, 9.0f, 1.0f);
                canvas.drawPath(path, paint);
                paint.setColor(-8630785);
                canvas.drawRect(-9.0f, CropImageView.DEFAULT_ASPECT_RATIO, 9.0f, 2.6f, paint);
                break;
            case 43:
                paint.setColor(-7508381);
                rectF.set(-24.0f, -8.0f, -6.0f, 4.0f);
                canvas.drawOval(rectF, paint);
                paint.setColor(-10665929);
                canvas.drawRect(-24.0f, 2.0f, -21.0f, 12.0f, paint);
                canvas.drawRect(-11.0f, 2.0f, -8.0f, 12.0f, paint);
                canvas.drawCircle(-25.0f, -10.0f, 3.4f, paint);
                paint.setColor(-10929);
                canvas.drawCircle(-25.5f, -11.0f, 1.2f, paint);
                a.a.n(paint, -3790808, path, -4.0f, CropImageView.DEFAULT_ASPECT_RATIO);
                path.lineTo(20.0f, CropImageView.DEFAULT_ASPECT_RATIO);
                a.a.o(path, 24.0f, 10.0f, -4.0f, 10.0f);
                canvas.drawPath(path, paint);
                paint.setColor(-10929);
                canvas.drawRect(-4.0f, 6.0f, 22.0f, 8.0f, paint);
                paint.setColor(-7508381);
                canvas.drawRect(-6.0f, 10.0f, 26.0f, 12.0f, paint);
                break;
            case 44:
                paint.setColor(-870702210);
                rectF.set(-16.0f, -16.0f, 16.0f, 16.0f);
                canvas.drawRoundRect(rectF, 6.0f, 6.0f, paint);
                paint2.setColor(-10929);
                paint2.setStrokeWidth(2.0f);
                canvas.drawRoundRect(rectF, 6.0f, 6.0f, paint2);
                paint.setColor(-10929);
                canvas.drawCircle(CropImageView.DEFAULT_ASPECT_RATIO, CropImageView.DEFAULT_ASPECT_RATIO, 6.0f, paint);
                break;
            case 45:
                a.a.n(paint, -16121, path, -22.0f, -2.0f);
                path.quadTo(-6.0f, -18.0f, 8.0f, -4.0f);
                path.quadTo(16.0f, 4.0f, 4.0f, 8.0f);
                path.quadTo(-10.0f, 8.0f, -22.0f, -2.0f);
                path.close();
                canvas.drawPath(path, paint);
                paint.setColor(-3722);
                canvas.drawCircle(-12.0f, -4.0f, 2.8f, paint);
                for (int i10 = 0; i10 < 4; i10++) {
                    paint.setColor(i10 % 2 == 0 ? -19712 : -28928);
                    float f16 = i10;
                    canvas.drawCircle((f16 * 5.0f) + 10.0f, (f16 * 4.0f) + 2.0f, 5.4f - (f16 * 0.7f), paint);
                }
                paint.setColor(-2659);
                canvas.drawCircle(8.0f, -12.0f, 4.2f, paint);
                paint2.setColor(1728050589);
                paint2.setStrokeWidth(1.4f);
                canvas.drawCircle(8.0f, -12.0f, 6.6f, paint2);
                break;
            case 46:
                paint.setColor(-2937041);
                rectF.set(-13.0f, -14.0f, 13.0f, 14.0f);
                canvas.drawOval(rectF, paint);
                paint.setColor(-10929);
                canvas.drawRect(-9.0f, -16.0f, 9.0f, -13.0f, paint);
                canvas.drawRect(-9.0f, 13.0f, 9.0f, 16.0f, paint);
                paint.setColor(-10929);
                canvas.drawLine(CropImageView.DEFAULT_ASPECT_RATIO, 16.0f, CropImageView.DEFAULT_ASPECT_RATIO, 23.0f, paint);
                a.a.n(paint, -5317, path, -6.0f, -6.0f);
                path.lineTo(6.0f, -6.0f);
                path.lineTo(4.0f, -1.0f);
                path.lineTo(6.0f, 6.0f);
                a.a.o(path, -6.0f, 6.0f, -4.0f, -1.0f);
                canvas.drawPath(path, paint);
                break;
            case 47:
                r(canvas, paint, paint2, -10929, 12, 0.9f);
                break;
            case 48:
                for (int i11 = 0; i11 < 8; i11++) {
                    double d7 = (i11 * 3.141592653589793d) / 4.0d;
                    Math.cos(d7);
                    Math.sin(d7);
                    paint.setColor(i11 % 2 == 0 ? -30080 : -12846);
                    canvas.save();
                    canvas.rotate(i11 * 45);
                    rectF.set(-5.0f, -16.0f, 5.0f, 2.0f);
                    canvas.drawOval(rectF, paint);
                    canvas.restore();
                }
                paint.setColor(-10929);
                canvas.drawCircle(CropImageView.DEFAULT_ASPECT_RATIO, 2.0f, 6.0f, paint);
                paint.setColor(-21760);
                canvas.drawCircle(CropImageView.DEFAULT_ASPECT_RATIO, 2.0f, 3.0f, paint);
                break;
            case 49:
                x0(path, CropImageView.DEFAULT_ASPECT_RATIO, CropImageView.DEFAULT_ASPECT_RATIO, 18.0f);
                paint2.setColor(1442840575);
                paint2.setStrokeWidth(5.0f);
                canvas.drawPath(path, paint2);
                paint2.setColor(-1426063361);
                paint2.setStrokeWidth(2.2f);
                canvas.drawPath(path, paint2);
                break;
            case 50:
                f(canvas, paint, -1, -7288071, false);
                break;
            case 51:
                x0(path, -6.0f, 4.0f, 13.0f);
                paint.setColor(-42374);
                canvas.drawPath(path, paint);
                paint.setColor(-10929);
                canvas.drawLine(14.0f, -16.0f, -4.0f, 2.0f, paint);
                path.reset();
                path.moveTo(16.0f, -18.0f);
                path.lineTo(20.0f, -12.0f);
                path.lineTo(13.0f, -13.0f);
                path.close();
                canvas.drawPath(path, paint);
                paint.setColor(-1);
                canvas.drawCircle(8.0f, -8.0f, 2.6f, paint);
                break;
            case 52:
                paint.setColor(-1086464);
                canvas.drawCircle(-20.0f, -6.0f, 6.0f, paint);
                paint.setColor(-14606047);
                canvas.drawCircle(-22.0f, -8.0f, 1.6f, paint);
                canvas.drawCircle(-18.0f, -8.0f, 1.6f, paint);
                int i12 = 0;
                while (true) {
                    if (i12 >= 5) {
                        paint2.setColor(-14606047);
                        paint2.setStrokeWidth(1.0f);
                        int i13 = 0;
                        for (int i14 = 5; i13 < i14; i14 = 5) {
                            float f17 = i13;
                            float f18 = (f17 * 7.0f) - 12.0f;
                            float f19 = (f17 * 4.0f) - 2.0f;
                            float f20 = f19 + 5.0f;
                            canvas.drawLine(f18, f19, f18 - 2.0f, f20, paint2);
                            canvas.drawLine(f18, f19, f18 + 2.0f, f20, paint2);
                            i13++;
                        }
                        break;
                    } else {
                        paint.setColor(i12 % 2 == 0 ? -19712 : -28928);
                        canvas.drawCircle((7.0f * r1) - 12.0f, (r1 * 4.0f) - 2.0f, 4.4f - (i12 * 0.3f), paint);
                        i12++;
                    }
                }
            case 53:
                paint2.setColor(-2001476100);
                paint2.setStrokeWidth(1.6f);
                rectF.set(-13.0f, -13.0f, 13.0f, 13.0f);
                canvas.drawOval(rectF, paint2);
                paint.setColor(870446590);
                canvas.drawOval(rectF, paint);
                paint.setColor(-855638017);
                canvas.drawCircle(-4.0f, -5.0f, 3.0f, paint);
                break;
            case 54:
                a.a.n(paint, -7508381, path, -24.0f, 4.0f);
                path.quadTo(CropImageView.DEFAULT_ASPECT_RATIO, 14.0f, 24.0f, 2.0f);
                path.lineTo(22.0f, 8.0f);
                path.quadTo(CropImageView.DEFAULT_ASPECT_RATIO, 20.0f, -22.0f, 10.0f);
                path.close();
                canvas.drawPath(path, paint);
                paint.setColor(-2937041);
                path.reset();
                path.moveTo(20.0f, 2.0f);
                path.quadTo(28.0f, -12.0f, 18.0f, -14.0f);
                path.quadTo(14.0f, -4.0f, 18.0f, 2.0f);
                path.close();
                canvas.drawPath(path, paint);
                paint.setColor(-10929);
                canvas.drawCircle(21.0f, -9.0f, 1.8f, paint);
                paint.setColor(-19712);
                int i15 = 0;
                while (i15 < 4) {
                    float f21 = (i15 * f9) - 14.0f;
                    canvas.drawLine(f21, -4.0f, f21 - 4.0f, 12.0f, paint);
                    i15++;
                    f9 = f9;
                }
                paint.setColor(-3790808);
                canvas.drawCircle(-2.0f, -6.0f, 5.0f, paint);
                break;
            case 55:
                paint2.setColor(-7288071);
                paint2.setStrokeWidth(3.0f);
                Path path5 = new Path();
                float f22 = -24.0f;
                path5.moveTo(-24.0f, 14.0f);
                path5.quadTo(CropImageView.DEFAULT_ASPECT_RATIO, -22.0f, 24.0f, 14.0f);
                canvas.drawPath(path5, paint2);
                int i16 = 0;
                while (i16 < 5) {
                    float f23 = i16 / 4.0f;
                    float f24 = (48.0f * f23) + f22;
                    float sin = f8 - (((float) Math.sin(f23 * 3.141592653589793d)) * 30.0f);
                    paint.setColor(-14606047);
                    canvas.drawCircle(f24, sin - 4.0f, 3.2f, paint);
                    paint.setColor(-657931);
                    canvas.drawRect(f24 - 1.8f, sin - 2.0f, f24 + 1.8f, sin + 3.0f, paint);
                    i16++;
                    f22 = f22;
                    f8 = f8;
                }
                break;
            case z1.c.IF_EQZ /* 56 */:
                paint2.setColor(-1998471449);
                paint2.setStrokeWidth(2.4f);
                Path path6 = new Path();
                path6.moveTo(-4.0f, -2.0f);
                path6.quadTo(-20.0f, 6.0f, -10.0f, 20.0f);
                canvas.drawPath(path6, paint2);
                path6.reset();
                path6.moveTo(4.0f, -2.0f);
                path6.quadTo(20.0f, 8.0f, 8.0f, 22.0f);
                canvas.drawPath(path6, paint2);
                a.a.n(paint, -476208, path, CropImageView.DEFAULT_ASPECT_RATIO, -8.0f);
                a.a.o(path, 10.0f, 18.0f, -10.0f, 18.0f);
                canvas.drawPath(path, paint);
                paint.setColor(-8014);
                canvas.drawCircle(CropImageView.DEFAULT_ASPECT_RATIO, -13.0f, 5.0f, paint);
                paint.setColor(-12703965);
                canvas.drawCircle(-4.0f, -17.0f, 3.0f, paint);
                canvas.drawCircle(4.0f, -17.0f, 3.0f, paint);
                paint.setColor(-2659);
                canvas.drawCircle(18.0f, -18.0f, 6.0f, paint);
                break;
            case z1.c.IF_NEZ /* 57 */:
                paint.setColor(-328966);
                rectF.set(-9.0f, -6.0f, 9.0f, 12.0f);
                canvas.drawOval(rectF, paint);
                canvas.drawCircle(CropImageView.DEFAULT_ASPECT_RATIO, -10.0f, 7.0f, paint);
                a.a.n(paint, -328966, path, -5.0f, -15.0f);
                path.quadTo(-8.0f, -26.0f, -3.0f, -16.0f);
                path.close();
                canvas.drawPath(path, paint);
                path.reset();
                path.moveTo(5.0f, -15.0f);
                path.quadTo(8.0f, -26.0f, 3.0f, -16.0f);
                path.close();
                canvas.drawPath(path, paint);
                paint.setColor(-30080);
                canvas.drawCircle(-3.4f, -10.0f, 1.1f, paint);
                canvas.drawCircle(3.4f, -10.0f, 1.1f, paint);
                paint.setColor(-36797);
                canvas.drawCircle(12.0f, 2.0f, 5.0f, paint);
                paint.setColor(-8062);
                canvas.drawCircle(12.0f, 2.0f, 2.6f, paint);
                paint.setColor(-7508381);
                canvas.drawLine(9.0f, -4.0f, 12.0f, -3.0f, paint);
                break;
            case z1.c.IF_LTZ /* 58 */:
                r(canvas, paint, paint2, -5552196, 10, 1.1f);
                break;
            case z1.c.IF_GEZ /* 59 */:
                float f25 = 20.0f;
                float f26 = 11.0f;
                int i17 = 0;
                while (i17 < 2) {
                    int i18 = i17 == 0 ? 12 : 8;
                    float f27 = i17 == 0 ? f25 : f26;
                    int i19 = 0;
                    while (i19 < i18) {
                        double d8 = (i17 * 0.3d) + (((i19 * 3.141592653589793d) * 2.0d) / i18);
                        float cos = ((float) Math.cos(d8)) * f27;
                        float sin2 = ((float) Math.sin(d8)) * f27;
                        paint2.setColor(i17 == 0 ? -19712 : -3722);
                        paint2.setStrokeWidth(i17 == 0 ? f7 : 2.4f);
                        int i20 = i18;
                        canvas.drawLine(CropImageView.DEFAULT_ASPECT_RATIO, CropImageView.DEFAULT_ASPECT_RATIO, cos, sin2, paint2);
                        paint.setColor(i17 == 0 ? -10929 : -1);
                        canvas.drawCircle(cos, sin2, 2.2f, paint);
                        i19++;
                        f25 = 20.0f;
                        f10 = 4.0f;
                        i18 = i20;
                        f7 = 1.8f;
                    }
                    i17++;
                    f26 = 11.0f;
                    f7 = 1.8f;
                }
                paint.setColor(-2659);
                canvas.drawCircle(CropImageView.DEFAULT_ASPECT_RATIO, CropImageView.DEFAULT_ASPECT_RATIO, f10, paint);
                break;
            case 60:
                paint.setColor(-1086464);
                rectF.set(-16.0f, -12.0f, 16.0f, 16.0f);
                canvas.drawOval(rectF, paint);
                paint.setColor(-689152);
                rectF.set(-11.0f, -13.0f, 4.0f, 15.0f);
                canvas.drawOval(rectF, paint);
                rectF.set(-4.0f, -13.0f, 11.0f, 15.0f);
                canvas.drawOval(rectF, paint);
                paint.setColor(-11171025);
                canvas.drawRect(-2.0f, -18.0f, 3.0f, -12.0f, paint);
                a.a.n(paint, -3722, path, -9.0f, -4.0f);
                a.a.o(path, -3.0f, -4.0f, -6.0f, 2.0f);
                canvas.drawPath(path, paint);
                path.reset();
                path.moveTo(3.0f, -4.0f);
                a.a.o(path, 9.0f, -4.0f, 6.0f, 2.0f);
                canvas.drawPath(path, paint);
                path.reset();
                path.moveTo(-9.0f, 5.0f);
                path.lineTo(9.0f, 5.0f);
                path.lineTo(6.0f, 10.0f);
                a.a.o(path, CropImageView.DEFAULT_ASPECT_RATIO, 7.0f, -6.0f, 10.0f);
                canvas.drawPath(path, paint);
                break;
            case z1.c.IF_LEZ /* 61 */:
                a.a.n(paint, -218103809, path, -11.0f, 16.0f);
                path.lineTo(-11.0f, -6.0f);
                path.quadTo(CropImageView.DEFAULT_ASPECT_RATIO, -22.0f, 11.0f, -6.0f);
                path.lineTo(11.0f, 16.0f);
                path.lineTo(6.0f, 11.0f);
                path.lineTo(2.0f, 16.0f);
                a.a.o(path, -2.0f, 11.0f, -6.0f, 16.0f);
                canvas.drawPath(path, paint);
                paint.setColor(-13154481);
                canvas.drawCircle(-4.4f, -6.0f, 2.0f, paint);
                canvas.drawCircle(4.4f, -6.0f, 2.0f, paint);
                paint.setColor(1722859205);
                canvas.drawCircle(CropImageView.DEFAULT_ASPECT_RATIO, 2.0f, 2.4f, paint);
                break;
            case 62:
                k(canvas, paint, path, 20.0f);
                break;
            case 63:
                a.a.n(paint, -36797, path, -19.0f, -8.0f);
                path.quadTo(CropImageView.DEFAULT_ASPECT_RATIO, -28.0f, 19.0f, -8.0f);
                path.quadTo(CropImageView.DEFAULT_ASPECT_RATIO, -1.0f, -19.0f, -8.0f);
                path.close();
                canvas.drawPath(path, paint);
                paint.setColor(-1);
                path.reset();
                path.moveTo(-9.0f, -10.0f);
                path.quadTo(CropImageView.DEFAULT_ASPECT_RATIO, -25.0f, 9.0f, -10.0f);
                path.close();
                canvas.drawPath(path, paint);
                paint.setColor(-7297874);
                canvas.drawLine(-16.0f, -7.0f, -4.0f, 8.0f, paint);
                canvas.drawLine(16.0f, -7.0f, 4.0f, 8.0f, paint);
                paint.setColor(-3790808);
                canvas.drawRect(-8.0f, 8.0f, 8.0f, 20.0f, paint);
                paint.setColor(-10929);
                canvas.drawRect(-8.0f, 13.0f, 8.0f, 16.0f, paint);
                canvas.drawRect(-1.6f, 8.0f, 1.6f, 20.0f, paint);
                break;
            case 64:
                paint2.setColor(-12232092);
                paint2.setStrokeWidth(1.2f);
                Path path7 = new Path();
                path7.moveTo(-24.0f, -10.0f);
                path7.quadTo(-12.0f, 6.0f, CropImageView.DEFAULT_ASPECT_RATIO, -6.0f);
                path7.quadTo(12.0f, 6.0f, 24.0f, -10.0f);
                canvas.drawPath(path7, paint2);
                int[] iArr2 = {-44462, -10929, -9834322, -12532481, -49023};
                for (int i21 = 0; i21 < 5; i21++) {
                    float f28 = i21 / 4.0f;
                    float f29 = (48.0f * f28) - 24.0f;
                    float sin3 = ((float) (Math.sin((f28 * 3.141592653589793d) * 2.0d) * 6.0d)) - 10.0f;
                    paint.setColor(iArr2[i21]);
                    canvas.drawCircle(f29, sin3, 3.4f, paint);
                    paint.setColor(1442840575);
                    canvas.drawCircle(f29, sin3, 5.6f, paint);
                }
                break;
            case a0.e.LAYOUT_CONSTRAINT_HEIGHT /* 65 */:
                paint.setColor(-6982195);
                rectF.set(-10.0f, -16.0f, 10.0f, 12.0f);
                canvas.drawOval(rectF, paint);
                paint.setColor(1157627903);
                canvas.drawCircle(-4.0f, -9.0f, 3.2f, paint);
                for (int i22 = 0; i22 < 6; i22++) {
                    canvas.save();
                    canvas.rotate(i22 * 60.0f);
                    paint.setColor(-32597);
                    rectF.set(-3.0f, -30.0f, 3.0f, -20.0f);
                    canvas.drawOval(rectF, paint);
                    canvas.restore();
                }
                paint.setColor(-10929);
                canvas.drawCircle(CropImageView.DEFAULT_ASPECT_RATIO, -25.0f, 3.0f, paint);
                paint.setColor(-7297874);
                canvas.drawLine(CropImageView.DEFAULT_ASPECT_RATIO, 12.0f, CropImageView.DEFAULT_ASPECT_RATIO, 24.0f, paint);
                break;
            case a0.e.LAYOUT_WRAP_BEHAVIOR_IN_PARENT /* 66 */:
                a.a.n(paint, -14776091, path, -3.0f, -22.0f);
                path.lineTo(6.0f, -18.0f);
                path.lineTo(4.0f, -12.0f);
                path.lineTo(10.0f, 12.0f);
                path.lineTo(CropImageView.DEFAULT_ASPECT_RATIO, 20.0f);
                path.lineTo(-8.0f, 10.0f);
                float f30 = -6.0f;
                a.a.o(path, -2.0f, -12.0f, -6.0f, -18.0f);
                canvas.drawPath(path, paint);
                paint.setColor(-10929);
                int i23 = 0;
                while (i23 < 3) {
                    canvas.drawCircle(1.0f, (i23 * 8.0f) + f30, 1.8f, paint);
                    i23++;
                    f30 = -6.0f;
                }
                break;
            case a0.e.GUIDELINE_USE_RTL /* 67 */:
                for (int i24 = 0; i24 < 6; i24++) {
                    canvas.save();
                    canvas.rotate(i24 * 60.0f);
                    paint2.setColor(-1968642);
                    paint2.setStrokeWidth(1.8f);
                    canvas.drawLine(CropImageView.DEFAULT_ASPECT_RATIO, CropImageView.DEFAULT_ASPECT_RATIO, CropImageView.DEFAULT_ASPECT_RATIO, -22.0f, paint2);
                    canvas.drawLine(CropImageView.DEFAULT_ASPECT_RATIO, -14.0f, -5.0f, -18.0f, paint2);
                    canvas.drawLine(CropImageView.DEFAULT_ASPECT_RATIO, -14.0f, 5.0f, -18.0f, paint2);
                    paint.setColor(-1);
                    canvas.drawCircle(CropImageView.DEFAULT_ASPECT_RATIO, -22.0f, 2.2f, paint);
                    canvas.restore();
                }
                paint.setColor(-1);
                canvas.drawCircle(CropImageView.DEFAULT_ASPECT_RATIO, CropImageView.DEFAULT_ASPECT_RATIO, 3.6f, paint);
                break;
            case z1.c.AGET /* 68 */:
                paint.setColor(872411510);
                canvas.drawCircle(CropImageView.DEFAULT_ASPECT_RATIO, CropImageView.DEFAULT_ASPECT_RATIO, 16.0f, paint);
                paint.setColor(1728049526);
                canvas.drawCircle(CropImageView.DEFAULT_ASPECT_RATIO, CropImageView.DEFAULT_ASPECT_RATIO, 8.0f, paint);
                paint.setColor(-2659);
                canvas.drawCircle(CropImageView.DEFAULT_ASPECT_RATIO, CropImageView.DEFAULT_ASPECT_RATIO, 4.0f, paint);
                break;
            case z1.c.AGET_WIDE /* 69 */:
                a.a.n(paint, -2842538, path, CropImageView.DEFAULT_ASPECT_RATIO, -16.0f);
                path.quadTo(14.0f, CropImageView.DEFAULT_ASPECT_RATIO, CropImageView.DEFAULT_ASPECT_RATIO, 18.0f);
                path.quadTo(-14.0f, CropImageView.DEFAULT_ASPECT_RATIO, CropImageView.DEFAULT_ASPECT_RATIO, -16.0f);
                path.close();
                canvas.drawPath(path, paint);
                paint.setColor(-7508381);
                canvas.drawLine(CropImageView.DEFAULT_ASPECT_RATIO, -14.0f, CropImageView.DEFAULT_ASPECT_RATIO, 18.0f, paint);
                canvas.drawLine(CropImageView.DEFAULT_ASPECT_RATIO, -4.0f, 7.0f, 2.0f, paint);
                canvas.drawLine(CropImageView.DEFAULT_ASPECT_RATIO, 4.0f, -7.0f, 10.0f, paint);
                break;
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:27:0x005d, code lost:
    
        if (r0.finished() == false) goto L27;
     */
    /* JADX WARN: Code restructure failed: missing block: B:29:0x0062, code lost:
    
        return r1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:31:0x006a, code lost:
    
        throw new java.lang.IllegalStateException("Inflater did not finish");
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static byte[] V0(java.io.FileInputStream r8, int r9, int r10) {
        /*
            java.util.zip.Inflater r0 = new java.util.zip.Inflater
            r0.<init>()
            byte[] r1 = new byte[r10]     // Catch: java.lang.Throwable -> L2e
            r2 = 2048(0x800, float:2.87E-42)
            byte[] r2 = new byte[r2]     // Catch: java.lang.Throwable -> L2e
            r3 = 0
            r4 = r3
            r5 = r4
        Le:
            boolean r6 = r0.finished()     // Catch: java.lang.Throwable -> L2e
            if (r6 != 0) goto L57
            boolean r6 = r0.needsDictionary()     // Catch: java.lang.Throwable -> L2e
            if (r6 != 0) goto L57
            if (r4 >= r9) goto L57
            int r6 = r8.read(r2)     // Catch: java.lang.Throwable -> L2e
            if (r6 < 0) goto L3b
            r0.setInput(r2, r3, r6)     // Catch: java.lang.Throwable -> L2e
            int r7 = r10 - r5
            int r7 = r0.inflate(r1, r5, r7)     // Catch: java.lang.Throwable -> L2e java.util.zip.DataFormatException -> L30
            int r5 = r5 + r7
            int r4 = r4 + r6
            goto Le
        L2e:
            r8 = move-exception
            goto L8a
        L30:
            r8 = move-exception
            java.lang.String r8 = r8.getMessage()     // Catch: java.lang.Throwable -> L2e
            java.lang.IllegalStateException r9 = new java.lang.IllegalStateException     // Catch: java.lang.Throwable -> L2e
            r9.<init>(r8)     // Catch: java.lang.Throwable -> L2e
            throw r9     // Catch: java.lang.Throwable -> L2e
        L3b:
            java.lang.StringBuilder r8 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> L2e
            r8.<init>()     // Catch: java.lang.Throwable -> L2e
            java.lang.String r10 = "Invalid zip data. Stream ended after $totalBytesRead bytes. Expected "
            r8.append(r10)     // Catch: java.lang.Throwable -> L2e
            r8.append(r9)     // Catch: java.lang.Throwable -> L2e
            java.lang.String r9 = " bytes"
            r8.append(r9)     // Catch: java.lang.Throwable -> L2e
            java.lang.String r8 = r8.toString()     // Catch: java.lang.Throwable -> L2e
            java.lang.IllegalStateException r9 = new java.lang.IllegalStateException     // Catch: java.lang.Throwable -> L2e
            r9.<init>(r8)     // Catch: java.lang.Throwable -> L2e
            throw r9     // Catch: java.lang.Throwable -> L2e
        L57:
            if (r4 != r9) goto L6b
            boolean r8 = r0.finished()     // Catch: java.lang.Throwable -> L2e
            if (r8 == 0) goto L63
            r0.end()
            return r1
        L63:
            java.lang.String r8 = "Inflater did not finish"
            java.lang.IllegalStateException r9 = new java.lang.IllegalStateException     // Catch: java.lang.Throwable -> L2e
            r9.<init>(r8)     // Catch: java.lang.Throwable -> L2e
            throw r9     // Catch: java.lang.Throwable -> L2e
        L6b:
            java.lang.StringBuilder r8 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> L2e
            r8.<init>()     // Catch: java.lang.Throwable -> L2e
            java.lang.String r10 = "Didn't read enough bytes during decompression. expected="
            r8.append(r10)     // Catch: java.lang.Throwable -> L2e
            r8.append(r9)     // Catch: java.lang.Throwable -> L2e
            java.lang.String r9 = " actual="
            r8.append(r9)     // Catch: java.lang.Throwable -> L2e
            r8.append(r4)     // Catch: java.lang.Throwable -> L2e
            java.lang.String r8 = r8.toString()     // Catch: java.lang.Throwable -> L2e
            java.lang.IllegalStateException r9 = new java.lang.IllegalStateException     // Catch: java.lang.Throwable -> L2e
            r9.<init>(r8)     // Catch: java.lang.Throwable -> L2e
            throw r9     // Catch: java.lang.Throwable -> L2e
        L8a:
            r0.end()
            throw r8
        */
        throw new UnsupportedOperationException("Method not decompiled: n1.z.V0(java.io.FileInputStream, int, int):byte[]");
    }

    public static void W(Canvas canvas, Paint paint, Paint paint2) {
        paint.setColor(-7297874);
        canvas.drawCircle(CropImageView.DEFAULT_ASPECT_RATIO, CropImageView.DEFAULT_ASPECT_RATIO, 4.6f, paint);
        paint2.setColor(-7297874);
        paint2.setStrokeWidth(2.0f);
        canvas.drawLine(-13.0f, -9.0f, 13.0f, 9.0f, paint2);
        canvas.drawLine(13.0f, -9.0f, -13.0f, 9.0f, paint2);
        paint.setColor(-59580);
        canvas.drawCircle(CropImageView.DEFAULT_ASPECT_RATIO, 2.0f, 2.0f, paint);
    }

    public static int W0(byte[] bArr, int i7) {
        return (bArr[i7] & 255) | ((bArr[i7 + 3] & 255) << 24) | ((bArr[i7 + 2] & 255) << 16) | ((bArr[i7 + 1] & 255) << 8);
    }

    public static void X(Canvas canvas, Paint paint, Paint paint2) {
        for (int i7 = 0; i7 < 9; i7++) {
            float f7 = ((i7 % 3) * 14.0f) - 14.0f;
            float f8 = ((i7 / 3) * 12.0f) - 12.0f;
            paint.setColor(i7 % 2 == 0 ? -16718337 : -49023);
            canvas.drawCircle(f7, f8, 2.2f, paint);
            paint2.setColor(-2013207041);
            paint2.setStrokeWidth(0.9f);
            canvas.drawCircle(f7, f8, 4.4f, paint2);
        }
    }

    public static long X0(byte[] bArr) {
        return ((bArr[3] & 255) << 24) | ((bArr[2] & 255) << 16) | ((bArr[1] & 255) << 8) | (255 & bArr[0]);
    }

    public static float Y(float f7, float f8, float f9) {
        float f10 = f7 / (f9 / 2.0f);
        float f11 = f8 / 2.0f;
        if (f10 < 1.0f) {
            return (f11 * f10 * f10 * f10) + CropImageView.DEFAULT_ASPECT_RATIO;
        }
        float f12 = f10 - 2.0f;
        return (((f12 * f12 * f12) + 2.0f) * f11) + CropImageView.DEFAULT_ASPECT_RATIO;
    }

    public static short Y0(byte[] bArr, int i7) {
        return (short) (((short) (((short) (bArr[i7 + 1] & 255)) << 8)) + (bArr[i7] & 255));
    }

    public static String Z(String str) {
        if (str == null) {
            str = s5.h0.FRAGMENT_ENCODE_SET;
        }
        return Uri.encode(str);
    }

    public static long Z0(InputStream inputStream, int i7) {
        byte[] U0 = U0(inputStream, i7);
        long j7 = 0;
        for (int i8 = 0; i8 < i7; i8++) {
            j7 += (U0[i8] & 255) << (i8 * 8);
        }
        return j7;
    }

    /* JADX WARN: Removed duplicated region for block: B:7:0x0043 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:9:0x0044  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static java.lang.String a(cn.kuwo.base.bean.Music r4, cn.kuwo.autosdk.i r5) {
        /*
            long r0 = r4.f3163b
            r2 = 0
            int r2 = (r0 > r2 ? 1 : (r0 == r2 ? 0 : -1))
            if (r2 > 0) goto L36
            java.lang.String r0 = r4.f3164c
            boolean r0 = android.text.TextUtils.isEmpty(r0)
            if (r0 == 0) goto L17
            java.lang.String r4 = r4.f3182u
            java.lang.String r4 = t1.i.a(r4)
            goto L41
        L17:
            java.lang.String r0 = r4.f3165d
            boolean r0 = android.text.TextUtils.isEmpty(r0)
            if (r0 == 0) goto L22
            java.lang.String r4 = r4.f3164c
            goto L3a
        L22:
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            java.lang.String r1 = r4.f3164c
            r0.append(r1)
            java.lang.String r4 = r4.f3165d
            r0.append(r4)
            java.lang.String r4 = r0.toString()
            goto L3a
        L36:
            java.lang.String r4 = java.lang.String.valueOf(r0)
        L3a:
            int r0 = r4.length()
            if (r0 != 0) goto L41
            r4 = 0
        L41:
            if (r4 != 0) goto L44
            return r4
        L44:
            cn.kuwo.autosdk.i r0 = cn.kuwo.autosdk.i.LRCX
            if (r5 != r0) goto L4f
            java.lang.String r5 = ".lrcx"
            java.lang.String r4 = r4.concat(r5)
            goto L55
        L4f:
            java.lang.String r5 = ".lrc"
            java.lang.String r4 = r4.concat(r5)
        L55:
            return r4
        */
        throw new UnsupportedOperationException("Method not decompiled: n1.z.a(cn.kuwo.base.bean.Music, cn.kuwo.autosdk.i):java.lang.String");
    }

    public static String a0(String str, String str2) {
        SecretKeySpec secretKeySpec = new SecretKeySpec(Base64.decode(str, 0), "AES");
        Cipher cipher = Cipher.getInstance("AES/GCM/NoPadding");
        cipher.init(1, secretKeySpec, new IvParameterSpec("7777889654578967".getBytes("utf-8")));
        return Base64.encodeToString(cipher.doFinal(str2.getBytes("utf-8")), 0);
    }

    public static void a1(Canvas canvas, Paint paint, RectF rectF) {
        paint.setColor(-3155748);
        rectF.set(-6.0f, -6.0f, 6.0f, 6.0f);
        canvas.drawOval(rectF, paint);
        paint.setColor(-15374912);
        canvas.drawRect(-24.0f, -9.0f, -8.0f, 9.0f, paint);
        canvas.drawRect(8.0f, -9.0f, 24.0f, 9.0f, paint);
        paint.setColor(-10177034);
        canvas.drawRect(-22.0f, -7.0f, -10.0f, -1.0f, paint);
        canvas.drawRect(10.0f, -7.0f, 22.0f, -1.0f, paint);
        paint.setColor(-10929);
        canvas.drawCircle(CropImageView.DEFAULT_ASPECT_RATIO, -7.0f, 2.4f, paint);
    }

    public static Bitmap b() {
        Bitmap createBitmap = Bitmap.createBitmap(512, 576, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(createBitmap);
        Paint paint = new Paint(1);
        paint.setStyle(Paint.Style.FILL);
        Paint paint2 = new Paint(1);
        paint2.setStyle(Paint.Style.STROKE);
        paint2.setStrokeCap(Paint.Cap.ROUND);
        paint2.setStrokeJoin(Paint.Join.ROUND);
        Path path = new Path();
        RectF rectF = new RectF();
        for (int i7 = 0; i7 < 70; i7++) {
            int save = canvas.save();
            canvas.translate(((i7 % 8) * 64) + 32.0f, ((i7 / 8) * 64) + 32.0f);
            canvas.clipRect(-32.0f, -32.0f, 32.0f, 32.0f);
            try {
                V(canvas, i7, paint, paint2, path, rectF);
            } catch (Throwable unused) {
            }
            canvas.restoreToCount(save);
        }
        return createBitmap;
    }

    public static void b0() {
        if (f7690c != null || f7691d) {
            return;
        }
        f7691d = true;
        Thread thread = new Thread(new y0.p(4), "ambient-atlas");
        thread.setPriority(1);
        thread.start();
    }

    public static void b1(Drawable drawable, int i7) {
        j0.b.g(drawable, i7);
    }

    public static float c(float f7, float f8, int i7) {
        return (Math.max(0, i7 - 1) * f8) + f7;
    }

    public static View c0(int i7, View view) {
        if (!(view instanceof ViewGroup)) {
            return null;
        }
        ViewGroup viewGroup = (ViewGroup) view;
        int childCount = viewGroup.getChildCount();
        for (int i8 = 0; i8 < childCount; i8++) {
            View findViewById = viewGroup.getChildAt(i8).findViewById(i7);
            if (findViewById != null) {
                return findViewById;
            }
        }
        return null;
    }

    public static void c1(Drawable drawable, ColorStateList colorStateList) {
        j0.b.h(drawable, colorStateList);
    }

    public static float d(float f7, float f8, int i7) {
        return i7 > 0 ? (f8 / 2.0f) + f7 : f7;
    }

    public static void d0(Canvas canvas, Paint paint) {
        paint.setColor(-12703965);
        Path path = new Path();
        for (int i7 = -1; i7 <= 1; i7 += 2) {
            for (int i8 = 0; i8 < 4; i8++) {
                float f7 = i8 / 3.0f;
                float f8 = i7 * 28.0f;
                float f9 = ((0.36f * f7) + 0.62f) * f8;
                float f10 = ((f7 * 0.06f) + 0.28f) * (-28.0f);
                path.reset();
                path.moveTo(f9, f10);
                path.lineTo((0.09f * f8) + f9, f10 - 3.6399999f);
                path.lineTo((f8 * 0.02f) + f9, 1.12f + f10);
                path.close();
                canvas.drawPath(path, paint);
            }
        }
    }

    public static void d1(Drawable drawable, PorterDuff.Mode mode) {
        j0.b.i(drawable, mode);
    }

    public static void e(Throwable th, Throwable th2) {
        i5.a.g("<this>", th);
        i5.a.g("exception", th2);
        if (th != th2) {
            ((g5.b) e5.c.IMPLEMENTATIONS).getClass();
            Integer num = f5.a.sdkVersion;
            if (num == null || num.intValue() >= 19) {
                th.addSuppressed(th2);
                return;
            }
            Method method = e5.a.addSuppressed;
            if (method != null) {
                method.invoke(th, th2);
            }
        }
    }

    public static boolean e0(int i7, int[] iArr) {
        return ((1 << (i7 & 31)) & iArr[i7 >> 5]) != 0;
    }

    public static void e1(Canvas canvas, Paint paint, RectF rectF) {
        paint.setColor(-36797);
        rectF.set(-10.0f, -16.0f, 10.0f, 10.0f);
        canvas.drawOval(rectF, paint);
        paint.setColor(-8062);
        rectF.set(-5.0f, -9.0f, 5.0f, 6.0f);
        canvas.drawOval(rectF, paint);
        paint.setColor(-4246004);
        canvas.drawRect(-6.0f, 10.0f, 6.0f, 13.0f, paint);
    }

    public static void f(Canvas canvas, Paint paint, int i7, int i8, boolean z6) {
        Path path = new Path();
        paint.setColor(i7);
        path.reset();
        path.moveTo(-24.0f, 1.0f);
        path.quadTo(-20.0f, -5.0f, 4.0f, -5.0f);
        path.quadTo(20.0f, -5.0f, 24.0f, CropImageView.DEFAULT_ASPECT_RATIO);
        path.quadTo(18.0f, 5.0f, -2.0f, 5.0f);
        path.quadTo(-18.0f, 5.0f, -24.0f, 1.0f);
        path.close();
        canvas.drawPath(path, paint);
        path.reset();
        path.moveTo(-2.0f, 1.0f);
        path.lineTo(-14.0f, 13.0f);
        a.a.o(path, -4.0f, 13.0f, 6.0f, 2.0f);
        canvas.drawPath(path, paint);
        path.reset();
        path.moveTo(-20.0f, -1.0f);
        path.lineTo(-26.0f, -13.0f);
        a.a.o(path, -17.0f, -13.0f, -13.0f, -1.0f);
        canvas.drawPath(path, paint);
        paint.setColor(i8);
        canvas.drawRect(-22.0f, 1.0f, 22.0f, 3.0f, paint);
        if (z6) {
            paint.setColor(-59580);
            canvas.drawCircle(-22.0f, CropImageView.DEFAULT_ASPECT_RATIO, 1.8f, paint);
            paint.setColor(-8978685);
            canvas.drawCircle(22.0f, CropImageView.DEFAULT_ASPECT_RATIO, 1.8f, paint);
        }
    }

    public static b2.w f0(Object obj) {
        if (obj == null) {
            return b2.l.THE_ONE;
        }
        if (obj instanceof Boolean) {
            return ((Boolean) obj).booleanValue() ? b2.e.VALUE_TRUE : b2.e.VALUE_FALSE;
        }
        if (obj instanceof Byte) {
            return new b2.f(((Byte) obj).byteValue());
        }
        if (obj instanceof Character) {
            return new b2.g(((Character) obj).charValue());
        }
        if (obj instanceof Double) {
            return new b2.h(Double.doubleToLongBits(((Double) obj).doubleValue()));
        }
        if (obj instanceof Float) {
            return new b2.j(Float.floatToIntBits(((Float) obj).floatValue()));
        }
        if (obj instanceof Integer) {
            return b2.k.j(((Integer) obj).intValue());
        }
        if (obj instanceof Long) {
            return new b2.p(((Long) obj).longValue());
        }
        if (obj instanceof Short) {
            return new b2.t(((Short) obj).shortValue());
        }
        if (obj instanceof String) {
            return new b2.u((String) obj);
        }
        if (obj instanceof Class) {
            return new b2.v(u1.q.a((Class) obj).f9303b);
        }
        if (obj instanceof u1.q) {
            return new b2.v(((u1.q) obj).f9303b);
        }
        throw new UnsupportedOperationException("Not a constant: " + obj);
    }

    public static void f1(Canvas canvas, Paint paint, Path path) {
        a.a.n(paint, -1092784, path, -20.0f, -14.0f);
        path.quadTo(CropImageView.DEFAULT_ASPECT_RATIO, -30.0f, 20.0f, -14.0f);
        path.quadTo(CropImageView.DEFAULT_ASPECT_RATIO, -6.0f, -20.0f, -14.0f);
        path.close();
        canvas.drawPath(path, paint);
        paint.setColor(-657931);
        path.reset();
        path.moveTo(-10.0f, -16.0f);
        path.quadTo(CropImageView.DEFAULT_ASPECT_RATIO, -26.0f, 10.0f, -16.0f);
        path.quadTo(CropImageView.DEFAULT_ASPECT_RATIO, -12.0f, -10.0f, -16.0f);
        path.close();
        canvas.drawPath(path, paint);
        paint.setColor(-12232092);
        canvas.drawLine(-18.0f, -12.0f, CropImageView.DEFAULT_ASPECT_RATIO, 10.0f, paint);
        canvas.drawLine(18.0f, -12.0f, CropImageView.DEFAULT_ASPECT_RATIO, 10.0f, paint);
        paint.setColor(-14273992);
        canvas.drawCircle(CropImageView.DEFAULT_ASPECT_RATIO, 12.0f, 3.2f, paint);
        canvas.drawRect(-2.4f, 14.0f, 2.4f, 22.0f, paint);
    }

    public static void g(Canvas canvas, Paint paint, Path path) {
        int i7 = 0;
        while (i7 < 3) {
            canvas.save();
            canvas.translate((i7 * 16.0f) - 16.0f, (Math.abs(i7 - 1) * 8.0f) - 8.0f);
            a.a.n(paint, i7 == 1 ? -1 : -2039584, path, CropImageView.DEFAULT_ASPECT_RATIO, -6.0f);
            path.lineTo(3.0f, 5.0f);
            a.a.o(path, CropImageView.DEFAULT_ASPECT_RATIO, 3.0f, -3.0f, 5.0f);
            canvas.drawPath(path, paint);
            paint.setColor(-36797);
            canvas.drawRect(-1.0f, 5.0f, 1.0f, 16.0f, paint);
            canvas.restore();
            i7++;
        }
    }

    public static float[] g0(RectF rectF) {
        float f7 = rectF.left;
        float f8 = rectF.top;
        float f9 = rectF.right;
        float f10 = rectF.bottom;
        return new float[]{f7, f8, f9, f8, f9, f10, f7, f10};
    }

    public static void g1(Canvas canvas, Paint paint, Path path) {
        a.a.n(paint, -10665929, path, CropImageView.DEFAULT_ASPECT_RATIO, -14.0f);
        path.quadTo(-16.0f, -6.0f, -26.0f, 6.0f);
        path.quadTo(-12.0f, 2.0f, CropImageView.DEFAULT_ASPECT_RATIO, 8.0f);
        path.quadTo(12.0f, 2.0f, 26.0f, 6.0f);
        path.quadTo(16.0f, -6.0f, CropImageView.DEFAULT_ASPECT_RATIO, -14.0f);
        path.close();
        canvas.drawPath(path, paint);
        paint.setColor(-657931);
        canvas.drawCircle(CropImageView.DEFAULT_ASPECT_RATIO, -2.0f, 3.4f, paint);
    }

    /* JADX WARN: Removed duplicated region for block: B:12:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:9:0x0016  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static java.lang.String h() {
        /*
            java.lang.String r0 = ""
            java.lang.String r1 = com.navi.tool.utils.NativeBridge.getWeatherProxyBase()     // Catch: java.lang.Throwable -> Le
            if (r1 != 0) goto L9
            goto Le
        L9:
            java.lang.String r1 = r1.trim()     // Catch: java.lang.Throwable -> Le
            goto Lf
        Le:
            r1 = r0
        Lf:
            boolean r2 = r1.isEmpty()
            if (r2 == 0) goto L16
            goto L1c
        L16:
            java.lang.String r0 = "/t/data"
            java.lang.String r0 = r1.concat(r0)
        L1c:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: n1.z.h():java.lang.String");
    }

    public static o0.c h0(Long l7, Long l8) {
        o0.c cVar;
        if (l7 == null && l8 == null) {
            return new o0.c(null, null);
        }
        if (l7 == null) {
            cVar = new o0.c(null, i0(l8.longValue()));
        } else {
            if (l8 != null) {
                Calendar f7 = com.google.android.material.datepicker.j0.f();
                Calendar g7 = com.google.android.material.datepicker.j0.g(null);
                g7.setTimeInMillis(l7.longValue());
                Calendar g8 = com.google.android.material.datepicker.j0.g(null);
                g8.setTimeInMillis(l8.longValue());
                return g7.get(1) == g8.get(1) ? g7.get(1) == f7.get(1) ? new o0.c(o0(l7.longValue(), Locale.getDefault()), o0(l8.longValue(), Locale.getDefault())) : new o0.c(o0(l7.longValue(), Locale.getDefault()), t0(l8.longValue(), Locale.getDefault())) : new o0.c(t0(l7.longValue(), Locale.getDefault()), t0(l8.longValue(), Locale.getDefault()));
            }
            cVar = new o0.c(i0(l7.longValue()), null);
        }
        return cVar;
    }

    public static void h1(Canvas canvas, Paint paint, RectF rectF) {
        paint.setColor(-657931);
        rectF.set(-11.0f, -22.0f, 11.0f, 2.0f);
        canvas.drawOval(rectF, paint);
        paint.setColor(-3155748);
        rectF.set(-4.0f, -16.0f, 1.0f, -8.0f);
        canvas.drawOval(rectF, paint);
        paint.setColor(-10395295);
        canvas.drawRect(-5.0f, 2.0f, 5.0f, 9.0f, paint);
        paint.setColor(-6381922);
        canvas.drawLine(CropImageView.DEFAULT_ASPECT_RATIO, 9.0f, CropImageView.DEFAULT_ASPECT_RATIO, 22.0f, paint);
    }

    public static final boolean i(int i7, int i8, int i9, byte[] bArr, byte[] bArr2) {
        i5.a.g("a", bArr);
        i5.a.g("b", bArr2);
        for (int i10 = 0; i10 < i9; i10++) {
            if (bArr[i10 + i7] != bArr2[i10 + i8]) {
                return false;
            }
        }
        return true;
    }

    public static String i0(long j7) {
        Calendar f7 = com.google.android.material.datepicker.j0.f();
        Calendar g7 = com.google.android.material.datepicker.j0.g(null);
        g7.setTimeInMillis(j7);
        return f7.get(1) == g7.get(1) ? o0(j7, Locale.getDefault()) : t0(j7, Locale.getDefault());
    }

    public static final Map i1(LinkedHashMap linkedHashMap) {
        i5.a.g("<this>", linkedHashMap);
        Map.Entry entry = (Map.Entry) linkedHashMap.entrySet().iterator().next();
        Map singletonMap = Collections.singletonMap(entry.getKey(), entry.getValue());
        i5.a.f("with(entries.iterator().…ingletonMap(key, value) }", singletonMap);
        return singletonMap;
    }

    public static void j(Canvas canvas, Paint paint) {
        f(canvas, paint, -1, -12409355, false);
        paint.setColor(-1092784);
        canvas.drawRect(-30.0f, 8.0f, -8.0f, 18.0f, paint);
        paint.setColor(-1);
        canvas.drawRect(-28.0f, 10.0f, -10.0f, 11.5f, paint);
        canvas.drawRect(-28.0f, 14.5f, -14.0f, 16.0f, paint);
        paint.setColor(-13154481);
        canvas.drawLine(-8.0f, 12.0f, -2.0f, 6.0f, paint);
    }

    public static float j0(EdgeEffect edgeEffect) {
        return Build.VERSION.SDK_INT >= 31 ? androidx.core.widget.g.b(edgeEffect) : CropImageView.DEFAULT_ASPECT_RATIO;
    }

    public static RectF j1(float[] fArr) {
        RectF rectF = new RectF(Float.POSITIVE_INFINITY, Float.POSITIVE_INFINITY, Float.NEGATIVE_INFINITY, Float.NEGATIVE_INFINITY);
        for (int i7 = 1; i7 < fArr.length; i7 += 2) {
            float round = Math.round(fArr[i7 - 1] * 10.0f) / 10.0f;
            float round2 = Math.round(fArr[i7] * 10.0f) / 10.0f;
            float f7 = rectF.left;
            if (round < f7) {
                f7 = round;
            }
            rectF.left = f7;
            float f8 = rectF.top;
            if (round2 < f8) {
                f8 = round2;
            }
            rectF.top = f8;
            float f9 = rectF.right;
            if (round <= f9) {
                round = f9;
            }
            rectF.right = round;
            float f10 = rectF.bottom;
            if (round2 <= f10) {
                round2 = f10;
            }
            rectF.bottom = round2;
        }
        rectF.sort();
        return rectF;
    }

    public static void k(Canvas canvas, Paint paint, Path path, float f7) {
        a.a.n(paint, -15066598, path, CropImageView.DEFAULT_ASPECT_RATIO, CropImageView.DEFAULT_ASPECT_RATIO);
        float f8 = -f7;
        float f9 = 0.34f * f8;
        path.lineTo(f8 * 0.35f, f9);
        float f10 = f8 * 0.02f;
        path.lineTo(f8 * 0.48f, f10);
        float f11 = f8 * 0.72f;
        float f12 = 0.2f * f8;
        path.lineTo(f11, f12);
        float f13 = f7 * 0.1f;
        path.lineTo(f8 * 0.86f, f13);
        float f14 = 0.02f * f7;
        path.lineTo(f8, f14);
        float f15 = 0.3f * f7;
        path.lineTo(f11, f15);
        float f16 = f7 * 0.16f;
        path.lineTo(f8 * 0.4f, f16);
        path.lineTo(CropImageView.DEFAULT_ASPECT_RATIO, 0.26f * f7);
        path.lineTo(f7 * 0.4f, f16);
        float f17 = f7 * 0.72f;
        path.lineTo(f17, f15);
        path.lineTo(f7, f14);
        path.lineTo(0.86f * f7, f13);
        path.lineTo(f17, f12);
        path.lineTo(0.48f * f7, f10);
        path.lineTo(0.35f * f7, f9);
        path.close();
        canvas.drawPath(path, paint);
        paint.setColor(-13558894);
        float f18 = f8 * 0.12f;
        float f19 = f8 * 0.08f;
        float f20 = 0.07f * f7;
        canvas.drawCircle(f18, f19, f20, paint);
        canvas.drawCircle(0.12f * f7, f19, f20, paint);
    }

    public static int k0(Context context, String str) {
        String trim = (str == null || str.trim().isEmpty()) ? "999" : str.trim();
        int identifier = context.getResources().getIdentifier(a.a.h("qw_", trim, "_fill"), "drawable", context.getPackageName());
        if (identifier == 0) {
            identifier = context.getResources().getIdentifier(a.a.B("qw_", trim), "drawable", context.getPackageName());
        }
        return identifier == 0 ? context.getResources().getIdentifier("qw_999_fill", "drawable", context.getPackageName()) : identifier;
    }

    public static String k1(int i7) {
        char[] cArr = new char[2];
        for (int i8 = 0; i8 < 2; i8++) {
            cArr[1 - i8] = Character.forDigit(i7 & 15, 16);
            i7 >>= 4;
        }
        return new String(cArr);
    }

    public static void l(Canvas canvas, Paint paint) {
        paint.setColor(-18611);
        Path path = new Path();
        path.moveTo(CropImageView.DEFAULT_ASPECT_RATIO, -4.0f);
        a.a.o(path, 9.0f, -2.0f, CropImageView.DEFAULT_ASPECT_RATIO, 1.0f);
        canvas.drawPath(path, paint);
    }

    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue
    java.lang.NullPointerException: Cannot invoke "java.util.List.iterator()" because the return value of "jadx.core.dex.visitors.regions.SwitchOverStringVisitor$SwitchData.getNewCases()" is null
    	at jadx.core.dex.visitors.regions.SwitchOverStringVisitor.restoreSwitchOverString(SwitchOverStringVisitor.java:109)
    	at jadx.core.dex.visitors.regions.SwitchOverStringVisitor.visitRegion(SwitchOverStringVisitor.java:66)
    	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverseIterativeStepInternal(DepthRegionTraversal.java:77)
    	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverseIterativeStepInternal(DepthRegionTraversal.java:82)
     */
    public static final Class l0(m5.a aVar) {
        i5.a.g("<this>", aVar);
        Class a7 = ((i5.d) aVar).a();
        if (!a7.isPrimitive()) {
            return a7;
        }
        String name = a7.getName();
        switch (name.hashCode()) {
            case -1325958191:
                if (!name.equals("double")) {
                }
                break;
            case 104431:
                if (!name.equals("int")) {
                }
                break;
            case 3039496:
                if (!name.equals("byte")) {
                }
                break;
            case 3052374:
                if (!name.equals("char")) {
                }
                break;
            case 3327612:
                if (!name.equals("long")) {
                }
                break;
            case 3625364:
                if (!name.equals("void")) {
                }
                break;
            case 64711720:
                if (!name.equals("boolean")) {
                }
                break;
            case 97526364:
                if (!name.equals("float")) {
                }
                break;
            case 109413500:
                if (!name.equals("short")) {
                }
                break;
        }
        return a7;
    }

    public static String l1(int i7) {
        char[] cArr = new char[4];
        for (int i8 = 0; i8 < 4; i8++) {
            cArr[3 - i8] = Character.forDigit(i7 & 15, 16);
            i7 >>= 4;
        }
        return new String(cArr);
    }

    /* JADX WARN: Code restructure failed: missing block: B:10:0x0026, code lost:
    
        if (r10.bottom <= r12.top) goto L24;
     */
    /* JADX WARN: Code restructure failed: missing block: B:13:0x0043, code lost:
    
        if (r9 == 17) goto L43;
     */
    /* JADX WARN: Code restructure failed: missing block: B:14:0x0045, code lost:
    
        if (r9 != 66) goto L27;
     */
    /* JADX WARN: Code restructure failed: missing block: B:15:0x0048, code lost:
    
        r11 = H0(r9, r10, r11);
     */
    /* JADX WARN: Code restructure failed: missing block: B:16:0x004c, code lost:
    
        if (r9 == 17) goto L38;
     */
    /* JADX WARN: Code restructure failed: missing block: B:17:0x004e, code lost:
    
        if (r9 == 33) goto L37;
     */
    /* JADX WARN: Code restructure failed: missing block: B:18:0x0050, code lost:
    
        if (r9 == 66) goto L36;
     */
    /* JADX WARN: Code restructure failed: missing block: B:19:0x0052, code lost:
    
        if (r9 != 130) goto L34;
     */
    /* JADX WARN: Code restructure failed: missing block: B:20:0x0054, code lost:
    
        r9 = r12.bottom;
        r10 = r10.bottom;
     */
    /* JADX WARN: Code restructure failed: missing block: B:23:0x0073, code lost:
    
        if (r11 >= java.lang.Math.max(1, r9 - r10)) goto L45;
     */
    /* JADX WARN: Code restructure failed: missing block: B:25:0x0076, code lost:
    
        return true;
     */
    /* JADX WARN: Code restructure failed: missing block: B:26:?, code lost:
    
        return false;
     */
    /* JADX WARN: Code restructure failed: missing block: B:28:0x005f, code lost:
    
        throw new java.lang.IllegalArgumentException("direction must be one of {FOCUS_UP, FOCUS_DOWN, FOCUS_LEFT, FOCUS_RIGHT}.");
     */
    /* JADX WARN: Code restructure failed: missing block: B:29:0x0060, code lost:
    
        r9 = r12.right;
        r10 = r10.right;
     */
    /* JADX WARN: Code restructure failed: missing block: B:30:0x0065, code lost:
    
        r9 = r10.top;
        r10 = r12.top;
     */
    /* JADX WARN: Code restructure failed: missing block: B:31:0x006a, code lost:
    
        r9 = r10.left;
        r10 = r12.left;
     */
    /* JADX WARN: Code restructure failed: missing block: B:35:0x0033, code lost:
    
        if (r10.right <= r12.left) goto L24;
     */
    /* JADX WARN: Code restructure failed: missing block: B:37:0x003a, code lost:
    
        if (r10.top >= r12.bottom) goto L24;
     */
    /* JADX WARN: Code restructure failed: missing block: B:39:0x0041, code lost:
    
        if (r10.left >= r12.right) goto L24;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static boolean m(int r9, android.graphics.Rect r10, android.graphics.Rect r11, android.graphics.Rect r12) {
        /*
            boolean r0 = n(r9, r10, r11)
            boolean r1 = n(r9, r10, r12)
            r2 = 0
            if (r1 != 0) goto L78
            if (r0 != 0) goto Lf
            goto L78
        Lf:
            java.lang.String r0 = "direction must be one of {FOCUS_UP, FOCUS_DOWN, FOCUS_LEFT, FOCUS_RIGHT}."
            r1 = 130(0x82, float:1.82E-43)
            r3 = 33
            r4 = 66
            r5 = 17
            r6 = 1
            if (r9 == r5) goto L3d
            if (r9 == r3) goto L36
            if (r9 == r4) goto L2f
            if (r9 != r1) goto L29
            int r7 = r10.bottom
            int r8 = r12.top
            if (r7 > r8) goto L77
            goto L43
        L29:
            java.lang.IllegalArgumentException r9 = new java.lang.IllegalArgumentException
            r9.<init>(r0)
            throw r9
        L2f:
            int r7 = r10.right
            int r8 = r12.left
            if (r7 > r8) goto L77
            goto L43
        L36:
            int r7 = r10.top
            int r8 = r12.bottom
            if (r7 < r8) goto L77
            goto L43
        L3d:
            int r7 = r10.left
            int r8 = r12.right
            if (r7 < r8) goto L77
        L43:
            if (r9 == r5) goto L77
            if (r9 != r4) goto L48
            goto L77
        L48:
            int r11 = H0(r9, r10, r11)
            if (r9 == r5) goto L6a
            if (r9 == r3) goto L65
            if (r9 == r4) goto L60
            if (r9 != r1) goto L5a
            int r9 = r12.bottom
            int r10 = r10.bottom
        L58:
            int r9 = r9 - r10
            goto L6f
        L5a:
            java.lang.IllegalArgumentException r9 = new java.lang.IllegalArgumentException
            r9.<init>(r0)
            throw r9
        L60:
            int r9 = r12.right
            int r10 = r10.right
            goto L58
        L65:
            int r9 = r10.top
            int r10 = r12.top
            goto L58
        L6a:
            int r9 = r10.left
            int r10 = r12.left
            goto L58
        L6f:
            int r9 = java.lang.Math.max(r6, r9)
            if (r11 >= r9) goto L76
            r2 = r6
        L76:
            return r2
        L77:
            return r6
        L78:
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: n1.z.m(int, android.graphics.Rect, android.graphics.Rect, android.graphics.Rect):boolean");
    }

    public static int m0(List list) {
        i5.a.g("<this>", list);
        return list.size() - 1;
    }

    public static String m1(int i7) {
        char[] cArr = new char[8];
        for (int i8 = 0; i8 < 8; i8++) {
            cArr[7 - i8] = Character.forDigit(i7 & 15, 16);
            i7 >>= 4;
        }
        return new String(cArr);
    }

    public static boolean n(int i7, Rect rect, Rect rect2) {
        if (i7 != 17) {
            if (i7 != 33) {
                if (i7 != 66) {
                    if (i7 != 130) {
                        throw new IllegalArgumentException("direction must be one of {FOCUS_UP, FOCUS_DOWN, FOCUS_LEFT, FOCUS_RIGHT}.");
                    }
                }
            }
            return rect2.right >= rect.left && rect2.left <= rect.right;
        }
        return rect2.bottom >= rect.top && rect2.top <= rect.bottom;
    }

    public static int n0() {
        EGLDisplay eglGetDisplay = EGL14.eglGetDisplay(0);
        int[] iArr = new int[2];
        EGL14.eglInitialize(eglGetDisplay, iArr, 0, iArr, 1);
        EGLConfig[] eGLConfigArr = new EGLConfig[1];
        int[] iArr2 = new int[1];
        EGL14.eglChooseConfig(eglGetDisplay, new int[]{12351, 12430, 12329, 0, 12352, 4, 12339, 1, 12344}, 0, eGLConfigArr, 0, 1, iArr2, 0);
        if (iArr2[0] == 0) {
            return 0;
        }
        EGLConfig eGLConfig = eGLConfigArr[0];
        EGLSurface eglCreatePbufferSurface = EGL14.eglCreatePbufferSurface(eglGetDisplay, eGLConfig, new int[]{12375, 64, 12374, 64, 12344}, 0);
        EGLContext eglCreateContext = EGL14.eglCreateContext(eglGetDisplay, eGLConfig, EGL14.EGL_NO_CONTEXT, new int[]{12440, 2, 12344}, 0);
        EGL14.eglMakeCurrent(eglGetDisplay, eglCreatePbufferSurface, eglCreatePbufferSurface, eglCreateContext);
        int[] iArr3 = new int[1];
        GLES20.glGetIntegerv(3379, iArr3, 0);
        EGLSurface eGLSurface = EGL14.EGL_NO_SURFACE;
        EGL14.eglMakeCurrent(eglGetDisplay, eGLSurface, eGLSurface, EGL14.EGL_NO_CONTEXT);
        EGL14.eglDestroySurface(eglGetDisplay, eglCreatePbufferSurface);
        EGL14.eglDestroyContext(eglGetDisplay, eglCreateContext);
        EGL14.eglTerminate(eglGetDisplay);
        return iArr3[0];
    }

    public static String n1(long j7) {
        char[] cArr = new char[16];
        for (int i7 = 0; i7 < 16; i7++) {
            cArr[15 - i7] = Character.forDigit(((int) j7) & 15, 16);
            j7 >>= 4;
        }
        return new String(cArr);
    }

    public static void o(Canvas canvas, Paint paint, float f7, float f8, int i7, int i8, float f9) {
        Path path = new Path();
        path.reset();
        path.moveTo(CropImageView.DEFAULT_ASPECT_RATIO, CropImageView.DEFAULT_ASPECT_RATIO);
        float f10 = -f7;
        float f11 = f10 * f8;
        float f12 = 0.35f * f11;
        path.quadTo(f10 * 0.45f, f11, f10, f12);
        float f13 = 0.14f * f7;
        float f14 = f7 * 0.1f;
        path.quadTo(f10 * 0.55f, f13, CropImageView.DEFAULT_ASPECT_RATIO, f14);
        path.close();
        paint.setColor(i8);
        canvas.drawPath(path, paint);
        path.reset();
        path.moveTo(CropImageView.DEFAULT_ASPECT_RATIO, CropImageView.DEFAULT_ASPECT_RATIO);
        path.quadTo(0.45f * f7, f11, f7, f12);
        path.quadTo(f7 * 0.55f, f13, CropImageView.DEFAULT_ASPECT_RATIO, f14);
        path.close();
        canvas.drawPath(path, paint);
        paint.setColor(i7);
        float f15 = 0.34f * f7;
        canvas.drawOval(new RectF(f10 * 0.11f, 0.1f * f10, 0.11f * f7, f15), paint);
        if (f9 > CropImageView.DEFAULT_ASPECT_RATIO) {
            path.reset();
            float f16 = 0.28f * f7;
            path.moveTo(f10 * 0.06f, f16);
            float f17 = 0.52f * f7;
            path.lineTo(f10 * 0.2f, f17);
            path.lineTo(CropImageView.DEFAULT_ASPECT_RATIO, f15);
            path.lineTo(f7 * 0.2f, f17);
            path.lineTo(f7 * 0.06f, f16);
            path.close();
            canvas.drawPath(path, paint);
        }
    }

    public static String o0(long j7, Locale locale) {
        return com.google.android.material.datepicker.j0.b("MMMd", locale).format(new Date(j7));
    }

    public static int o1(int i7) {
        int i8 = i7 >> 7;
        int i9 = 0;
        while (i8 != 0) {
            i8 >>= 7;
            i9++;
        }
        return i9 + 1;
    }

    public static void p(Canvas canvas, Paint paint, Path path, RectF rectF) {
        paint.setColor(-3155748);
        rectF.set(-26.0f, -9.0f, 26.0f, 9.0f);
        canvas.drawOval(rectF, paint);
        paint.setColor(-7297874);
        rectF.set(-18.0f, -8.0f, 4.0f, 8.0f);
        canvas.drawOval(rectF, paint);
        paint.setColor(-8875876);
        path.reset();
        path.moveTo(-20.0f, CropImageView.DEFAULT_ASPECT_RATIO);
        a.a.o(path, -30.0f, -12.0f, -22.0f, -2.0f);
        canvas.drawPath(path, paint);
        path.reset();
        path.moveTo(-20.0f, CropImageView.DEFAULT_ASPECT_RATIO);
        a.a.o(path, -30.0f, 12.0f, -22.0f, 2.0f);
        canvas.drawPath(path, paint);
        paint.setColor(-13154481);
        canvas.drawRect(-7.0f, 9.0f, 7.0f, 14.0f, paint);
    }

    public static k4.a p0(TypedArray typedArray, XmlPullParser xmlPullParser, Resources.Theme theme, String str, int i7) {
        k4.a aVar;
        if (v0(xmlPullParser, str)) {
            TypedValue typedValue = new TypedValue();
            typedArray.getValue(i7, typedValue);
            int i8 = typedValue.type;
            if (i8 >= 28 && i8 <= 31) {
                return new k4.a(null, null, typedValue.data);
            }
            try {
                aVar = k4.a.a(typedArray.getResources(), typedArray.getResourceId(i7, 0), theme);
            } catch (Exception unused) {
                aVar = null;
            }
            if (aVar != null) {
                return aVar;
            }
        }
        return new k4.a(null, null, 0);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static Drawable p1(Drawable drawable) {
        if (!(drawable instanceof j0.h)) {
            return drawable;
        }
        ((j0.i) ((j0.h) drawable)).getClass();
        return null;
    }

    public static Cipher q(String str, byte[] bArr) {
        if (str == null) {
            throw new InvalidAlgorithmParameterException("password should be specified");
        }
        byte[] bArr2 = new byte[16];
        byte[] bArr3 = new byte[16];
        int length = str.length() * 2;
        byte[] bArr4 = new byte[length + 8];
        byte[] bytes = str.getBytes();
        for (int i7 = 0; i7 < str.length(); i7++) {
            int i8 = i7 * 2;
            bArr4[i8] = bytes[i7];
            bArr4[i8 + 1] = 0;
        }
        System.arraycopy(bArr, 0, bArr4, length, bArr.length);
        MessageDigest messageDigest = MessageDigest.getInstance("sha-1");
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        for (int i9 = 0; i9 < 262144; i9++) {
            byteArrayOutputStream.write(bArr4);
            byteArrayOutputStream.write((byte) i9);
            byteArrayOutputStream.write((byte) (i9 >>> 8));
            byteArrayOutputStream.write((byte) (i9 >>> 16));
            if (i9 % 16384 == 0) {
                messageDigest.update(byteArrayOutputStream.toByteArray());
                bArr2[i9 / 16384] = messageDigest.digest()[19];
            }
        }
        messageDigest.update(byteArrayOutputStream.toByteArray());
        byte[] digest = messageDigest.digest();
        for (int i10 = 0; i10 < 4; i10++) {
            for (int i11 = 0; i11 < 4; i11++) {
                int i12 = i10 * 4;
                bArr3[i12 + i11] = (byte) (((digest[i12 + 3] & 255) | ((((digest[i12] * o2.d.VMCF_OP0) & p0.d1.MEASURED_STATE_MASK) | ((digest[i12 + 1] * o2.d.VMCF_OP0) & 16711680)) | ((digest[i12 + 2] * o2.d.VMCF_OP0) & p0.v.ACTION_POINTER_INDEX_MASK))) >>> (i11 * 8));
            }
        }
        Cipher cipher = Cipher.getInstance("AES/CBC/NoPadding");
        cipher.init(2, new SecretKeySpec(bArr3, "AES"), new IvParameterSpec(bArr2));
        return cipher;
    }

    public static final int q0(int i7, int i8, int i9) {
        if (i9 > 0) {
            if (i7 >= i8) {
                return i8;
            }
            int i10 = i8 % i9;
            if (i10 < 0) {
                i10 += i9;
            }
            int i11 = i7 % i9;
            if (i11 < 0) {
                i11 += i9;
            }
            int i12 = (i10 - i11) % i9;
            if (i12 < 0) {
                i12 += i9;
            }
            return i8 - i12;
        }
        if (i9 >= 0) {
            throw new IllegalArgumentException("Step is zero.");
        }
        if (i7 <= i8) {
            return i8;
        }
        int i13 = -i9;
        int i14 = i7 % i13;
        if (i14 < 0) {
            i14 += i13;
        }
        int i15 = i8 % i13;
        if (i15 < 0) {
            i15 += i13;
        }
        int i16 = (i14 - i15) % i13;
        if (i16 < 0) {
            i16 += i13;
        }
        return i8 + i16;
    }

    public static float q1(float f7, float f8, float f9, int i7) {
        return i7 > 0 ? (f9 / 2.0f) + f8 : f7;
    }

    public static void r(Canvas canvas, Paint paint, Paint paint2, int i7, int i8, float f7) {
        paint2.setStrokeWidth(1.7f);
        for (int i9 = 0; i9 < i8; i9++) {
            float f8 = 6.0f * f7;
            int i10 = i9 % 3;
            float f9 = ((i10 * 4.0f) + 18.0f) * f7;
            int i11 = 255 - (i10 * 45);
            paint2.setColor(i7);
            paint2.setAlpha(i11);
            double d7 = (float) (((i9 * 3.141592653589793d) * 2.0d) / i8);
            canvas.drawLine(((float) Math.cos(d7)) * f8, f8 * ((float) Math.sin(d7)), ((float) Math.cos(d7)) * f9, ((float) Math.sin(d7)) * f9, paint2);
            paint.setColor(-1);
            paint.setAlpha(i11);
            canvas.drawCircle(((float) Math.cos(d7)) * f9, ((float) Math.sin(d7)) * f9, 1.9f, paint);
        }
        paint2.setAlpha(255);
        paint.setAlpha(255);
        paint.setColor(-1);
        canvas.drawCircle(CropImageView.DEFAULT_ASPECT_RATIO, CropImageView.DEFAULT_ASPECT_RATIO, 3.4f, paint);
    }

    public static int r0(String str) {
        try {
            return g4.o.class.getDeclaredField(str).getInt(null);
        } catch (Exception unused) {
            return g4.o.ic_launcher_foreground;
        }
    }

    public static void r1(byte[] bArr, int i7, int i8) {
        bArr[i7 + 3] = (byte) (i8 >>> 24);
        bArr[i7 + 2] = (byte) (i8 >>> 16);
        bArr[i7 + 1] = (byte) (i8 >>> 8);
        bArr[i7] = (byte) (i8 & 255);
    }

    public static boolean s(i0.f[] fVarArr, i0.f[] fVarArr2) {
        if (fVarArr == null || fVarArr2 == null || fVarArr.length != fVarArr2.length) {
            return false;
        }
        for (int i7 = 0; i7 < fVarArr.length; i7++) {
            i0.f fVar = fVarArr[i7];
            char c7 = fVar.f6778a;
            i0.f fVar2 = fVarArr2[i7];
            if (c7 != fVar2.f6778a || fVar.f6779b.length != fVar2.f6779b.length) {
                return false;
            }
        }
        return true;
    }

    public static File s0(Context context) {
        File cacheDir = context.getCacheDir();
        if (cacheDir == null) {
            return null;
        }
        String str = ".font" + Process.myPid() + "-" + Process.myTid() + "-";
        for (int i7 = 0; i7 < 100; i7++) {
            File file = new File(cacheDir, str + i7);
            if (file.createNewFile()) {
                return file;
            }
        }
        return null;
    }

    public static void s1(e2.a aVar, int i7, long j7) {
        int numberOfTrailingZeros = 64 - Long.numberOfTrailingZeros(j7);
        if (numberOfTrailingZeros == 0) {
            numberOfTrailingZeros = 1;
        }
        int i8 = (numberOfTrailingZeros + 7) >> 3;
        long j8 = j7 >> (64 - (i8 * 8));
        e2.c cVar = (e2.c) aVar;
        cVar.i(i7 | ((i8 - 1) << 5));
        while (i8 > 0) {
            cVar.i((byte) j8);
            j8 >>= 8;
            i8--;
        }
    }

    public static void t(String str, boolean z6) {
        if (!z6) {
            throw new IllegalArgumentException(str);
        }
    }

    public static String t0(long j7, Locale locale) {
        return com.google.android.material.datepicker.j0.b("yMMMd", locale).format(new Date(j7));
    }

    public static void t1(int i7, short s6, byte[] bArr) {
        bArr[i7 + 1] = (byte) (s6 >>> 8);
        bArr[i7] = (byte) (s6 & 255);
    }

    public static void u(int i7) {
        if (i7 < 0) {
            throw new IllegalArgumentException();
        }
    }

    public static void u0(Canvas canvas, Paint paint, Path path) {
        a.a.n(paint, -1118482, path, CropImageView.DEFAULT_ASPECT_RATIO, -3.0f);
        path.quadTo(-14.0f, -6.0f, -26.0f, -2.0f);
        path.quadTo(-14.0f, 1.0f, CropImageView.DEFAULT_ASPECT_RATIO, CropImageView.DEFAULT_ASPECT_RATIO);
        path.close();
        canvas.drawPath(path, paint);
        path.reset();
        path.moveTo(CropImageView.DEFAULT_ASPECT_RATIO, -3.0f);
        path.quadTo(14.0f, -6.0f, 26.0f, -2.0f);
        path.quadTo(14.0f, 1.0f, CropImageView.DEFAULT_ASPECT_RATIO, CropImageView.DEFAULT_ASPECT_RATIO);
        path.close();
        canvas.drawPath(path, paint);
        paint.setColor(-7297874);
        path.reset();
        path.moveTo(-2.0f, -3.0f);
        path.lineTo(2.0f, -3.0f);
        a.a.o(path, 4.0f, 10.0f, -4.0f, 10.0f);
        canvas.drawPath(path, paint);
        paint.setColor(-11243910);
        canvas.drawCircle(CropImageView.DEFAULT_ASPECT_RATIO, 12.0f, 2.0f, paint);
    }

    public static void u1(ByteArrayOutputStream byteArrayOutputStream, long j7, int i7) {
        byte[] bArr = new byte[i7];
        for (int i8 = 0; i8 < i7; i8++) {
            bArr[i8] = (byte) ((j7 >> (i8 * 8)) & 255);
        }
        byteArrayOutputStream.write(bArr);
    }

    public static void v(Object obj, String str) {
        if (obj == null) {
            throw new NullPointerException(str);
        }
    }

    public static boolean v0(XmlPullParser xmlPullParser, String str) {
        return xmlPullParser.getAttributeValue("http://schemas.android.com/apk/res/android", str) != null;
    }

    public static void v1(ByteArrayOutputStream byteArrayOutputStream, int i7) {
        u1(byteArrayOutputStream, i7, 2);
    }

    public static final void w(long j7, long j8, long j9) {
        if ((j8 | j9) < 0 || j8 > j7 || j7 - j8 < j9) {
            throw new ArrayIndexOutOfBoundsException("size=" + j7 + " offset=" + j8 + " byteCount=" + j9);
        }
    }

    public static boolean w0(Uri uri) {
        return uri != null && "content".equals(uri.getScheme());
    }

    public static void w1(e2.a aVar, int i7, long j7) {
        int numberOfLeadingZeros = 64 - Long.numberOfLeadingZeros(j7);
        if (numberOfLeadingZeros == 0) {
            numberOfLeadingZeros = 1;
        }
        int i8 = (numberOfLeadingZeros + 7) >> 3;
        e2.c cVar = (e2.c) aVar;
        cVar.i(i7 | ((i8 - 1) << 5));
        while (i8 > 0) {
            cVar.i((byte) j7);
            j7 >>= 8;
            i8--;
        }
    }

    public static float x(float f7, float f8, float f9) {
        return f7 < f8 ? f8 : f7 > f9 ? f9 : f7;
    }

    public static void x0(Path path, float f7, float f8, float f9) {
        path.reset();
        float f10 = (0.85f * f9) + f8;
        path.moveTo(f7, f10);
        float f11 = f9 * 1.25f;
        float f12 = f8 - (0.15f * f9);
        float f13 = f9 * 0.55f;
        float f14 = f8 - (1.05f * f9);
        path.cubicTo(f7 - f11, f12, f7 - f13, f14, f7, f8 - (0.32f * f9));
        path.cubicTo(f7 + f13, f14, f7 + f11, f12, f7, f10);
        path.close();
    }

    public static int y(int i7, int i8, int i9) {
        return i7 < i8 ? i8 : i7 > i9 ? i9 : i7;
    }

    public static void y0(Canvas canvas, Paint paint, Paint paint2, RectF rectF, int i7, boolean z6) {
        paint.setColor(i7);
        rectF.set(-14.0f, -6.0f, 12.0f, 8.0f);
        canvas.drawOval(rectF, paint);
        paint2.setColor(i7);
        paint2.setStrokeWidth(3.2f);
        canvas.drawLine(-10.0f, -1.0f, -24.0f, -5.0f, paint2);
        paint2.setStrokeWidth(1.6f);
        canvas.drawLine(-24.0f, -9.0f, -24.0f, -1.0f, paint2);
        paint2.setStrokeWidth(1.4f);
        canvas.drawLine(-8.0f, 8.0f, -8.0f, 12.0f, paint2);
        canvas.drawLine(8.0f, 8.0f, 8.0f, 12.0f, paint2);
        if (z6) {
            paint.setColor(-59580);
            canvas.drawCircle(10.0f, 1.0f, 1.8f, paint);
            paint.setColor(-1);
            canvas.drawCircle(-12.0f, 1.0f, 1.4f, paint);
        }
    }

    public static void z(Closeable closeable) {
        if (closeable != null) {
            try {
                closeable.close();
            } catch (IOException unused) {
            }
        }
    }

    public static void z0(Canvas canvas, Paint paint, Path path, RectF rectF) {
        paint.setColor(-36797);
        rectF.set(-17.0f, -26.0f, 17.0f, 8.0f);
        canvas.drawOval(rectF, paint);
        paint.setColor(-13784);
        path.reset();
        path.moveTo(-6.0f, -25.0f);
        path.quadTo(-9.0f, -8.0f, -3.0f, 5.0f);
        path.lineTo(3.0f, 5.0f);
        path.quadTo(9.0f, -8.0f, 6.0f, -25.0f);
        path.close();
        canvas.drawPath(path, paint);
        paint.setColor(-9614271);
        canvas.drawRect(-6.0f, 9.0f, 6.0f, 16.0f, paint);
        paint.setColor(-7508381);
        canvas.drawRect(-7.0f, 8.0f, 7.0f, 10.0f, paint);
        paint.setColor(-11652050);
        canvas.drawLine(-5.0f, 6.0f, -5.0f, 9.0f, paint);
        canvas.drawLine(5.0f, 6.0f, 5.0f, 9.0f, paint);
    }
}
