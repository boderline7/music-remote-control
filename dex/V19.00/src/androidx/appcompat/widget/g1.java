package androidx.appcompat.widget;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.RectF;
import android.os.Build;
import android.text.Layout;
import android.text.StaticLayout;
import android.text.TextPaint;
import android.text.method.TransformationMethod;
import android.util.TypedValue;
import android.widget.TextView;
import com.yalantis.ucrop.view.CropImageView;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.concurrent.ConcurrentHashMap;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_2_1633180.dex */
public final class g1 {

    /* renamed from: l, reason: collision with root package name */
    public static final RectF f1251l = new RectF();

    /* renamed from: m, reason: collision with root package name */
    public static final ConcurrentHashMap f1252m = new ConcurrentHashMap();

    /* renamed from: a, reason: collision with root package name */
    public int f1253a = 0;

    /* renamed from: b, reason: collision with root package name */
    public boolean f1254b = false;

    /* renamed from: c, reason: collision with root package name */
    public float f1255c = -1.0f;

    /* renamed from: d, reason: collision with root package name */
    public float f1256d = -1.0f;

    /* renamed from: e, reason: collision with root package name */
    public float f1257e = -1.0f;

    /* renamed from: f, reason: collision with root package name */
    public int[] f1258f = new int[0];

    /* renamed from: g, reason: collision with root package name */
    public boolean f1259g = false;

    /* renamed from: h, reason: collision with root package name */
    public TextPaint f1260h;

    /* renamed from: i, reason: collision with root package name */
    public final TextView f1261i;

    /* renamed from: j, reason: collision with root package name */
    public final Context f1262j;

    /* renamed from: k, reason: collision with root package name */
    public final d1 f1263k;

    static {
        new ConcurrentHashMap();
    }

    public g1(TextView textView) {
        this.f1261i = textView;
        this.f1262j = textView.getContext();
        if (Build.VERSION.SDK_INT >= 29) {
            this.f1263k = new e1();
        } else {
            this.f1263k = new d1();
        }
    }

    public static int[] b(int[] iArr) {
        int length = iArr.length;
        if (length == 0) {
            return iArr;
        }
        Arrays.sort(iArr);
        ArrayList arrayList = new ArrayList();
        for (int i7 : iArr) {
            if (i7 > 0 && Collections.binarySearch(arrayList, Integer.valueOf(i7)) < 0) {
                arrayList.add(Integer.valueOf(i7));
            }
        }
        if (length == arrayList.size()) {
            return iArr;
        }
        int size = arrayList.size();
        int[] iArr2 = new int[size];
        for (int i8 = 0; i8 < size; i8++) {
            iArr2[i8] = ((Integer) arrayList.get(i8)).intValue();
        }
        return iArr2;
    }

    public static Method d(String str) {
        try {
            ConcurrentHashMap concurrentHashMap = f1252m;
            Method method = (Method) concurrentHashMap.get(str);
            if (method == null && (method = TextView.class.getDeclaredMethod(str, new Class[0])) != null) {
                method.setAccessible(true);
                concurrentHashMap.put(str, method);
            }
            return method;
        } catch (Exception unused) {
            return null;
        }
    }

    public static Object e(Object obj, Object obj2, String str) {
        try {
            return d(str).invoke(obj, new Object[0]);
        } catch (Exception unused) {
            return obj2;
        }
    }

    public final void a() {
        if (f()) {
            if (this.f1254b) {
                if (this.f1261i.getMeasuredHeight() <= 0 || this.f1261i.getMeasuredWidth() <= 0) {
                    return;
                }
                int measuredWidth = this.f1263k.b(this.f1261i) ? q0.i.ACTION_DISMISS : (this.f1261i.getMeasuredWidth() - this.f1261i.getTotalPaddingLeft()) - this.f1261i.getTotalPaddingRight();
                int height = (this.f1261i.getHeight() - this.f1261i.getCompoundPaddingBottom()) - this.f1261i.getCompoundPaddingTop();
                if (measuredWidth <= 0 || height <= 0) {
                    return;
                }
                RectF rectF = f1251l;
                synchronized (rectF) {
                    try {
                        rectF.setEmpty();
                        rectF.right = measuredWidth;
                        rectF.bottom = height;
                        float c7 = c(rectF);
                        if (c7 != this.f1261i.getTextSize()) {
                            g(c7, 0);
                        }
                    } finally {
                    }
                }
            }
            this.f1254b = true;
        }
    }

    public final int c(RectF rectF) {
        CharSequence transformation;
        int length = this.f1258f.length;
        if (length == 0) {
            throw new IllegalStateException("No available text sizes to choose from.");
        }
        int i7 = length - 1;
        int i8 = 0;
        int i9 = 1;
        while (i9 <= i7) {
            int i10 = (i9 + i7) / 2;
            int i11 = this.f1258f[i10];
            TextView textView = this.f1261i;
            CharSequence text = textView.getText();
            TransformationMethod transformationMethod = textView.getTransformationMethod();
            if (transformationMethod != null && (transformation = transformationMethod.getTransformation(text, textView)) != null) {
                text = transformation;
            }
            int b7 = a1.b(textView);
            TextPaint textPaint = this.f1260h;
            if (textPaint == null) {
                this.f1260h = new TextPaint();
            } else {
                textPaint.reset();
            }
            this.f1260h.set(textView.getPaint());
            this.f1260h.setTextSize(i11);
            StaticLayout a7 = c1.a(text, (Layout.Alignment) e(textView, Layout.Alignment.ALIGN_NORMAL, "getLayoutAlignment"), Math.round(rectF.right), b7, this.f1261i, this.f1260h, this.f1263k);
            if ((b7 == -1 || (a7.getLineCount() <= b7 && a7.getLineEnd(a7.getLineCount() - 1) == text.length())) && a7.getHeight() <= rectF.bottom) {
                int i12 = i10 + 1;
                i8 = i9;
                i9 = i12;
            } else {
                i8 = i10 - 1;
                i7 = i8;
            }
        }
        return this.f1258f[i8];
    }

    public final boolean f() {
        return j() && this.f1253a != 0;
    }

    public final void g(float f7, int i7) {
        Context context = this.f1262j;
        float applyDimension = TypedValue.applyDimension(i7, f7, (context == null ? Resources.getSystem() : context.getResources()).getDisplayMetrics());
        TextView textView = this.f1261i;
        if (applyDimension != textView.getPaint().getTextSize()) {
            textView.getPaint().setTextSize(applyDimension);
            boolean a7 = b1.a(textView);
            if (textView.getLayout() != null) {
                this.f1254b = false;
                try {
                    Method d7 = d("nullLayouts");
                    if (d7 != null) {
                        d7.invoke(textView, new Object[0]);
                    }
                } catch (Exception unused) {
                }
                if (a7) {
                    textView.forceLayout();
                } else {
                    textView.requestLayout();
                }
                textView.invalidate();
            }
        }
    }

    public final boolean h() {
        if (j() && this.f1253a == 1) {
            if (!this.f1259g || this.f1258f.length == 0) {
                int floor = ((int) Math.floor((this.f1257e - this.f1256d) / this.f1255c)) + 1;
                int[] iArr = new int[floor];
                for (int i7 = 0; i7 < floor; i7++) {
                    iArr[i7] = Math.round((i7 * this.f1255c) + this.f1256d);
                }
                this.f1258f = b(iArr);
            }
            this.f1254b = true;
        } else {
            this.f1254b = false;
        }
        return this.f1254b;
    }

    public final boolean i() {
        boolean z6 = this.f1258f.length > 0;
        this.f1259g = z6;
        if (z6) {
            this.f1253a = 1;
            this.f1256d = r0[0];
            this.f1257e = r0[r1 - 1];
            this.f1255c = -1.0f;
        }
        return z6;
    }

    public final boolean j() {
        return !(this.f1261i instanceof AppCompatEditText);
    }

    public final void k(float f7, float f8, float f9) {
        if (f7 <= CropImageView.DEFAULT_ASPECT_RATIO) {
            throw new IllegalArgumentException("Minimum auto-size text size (" + f7 + "px) is less or equal to (0px)");
        }
        if (f8 <= f7) {
            throw new IllegalArgumentException("Maximum auto-size text size (" + f8 + "px) is less or equal to minimum auto-size text size (" + f7 + "px)");
        }
        if (f9 <= CropImageView.DEFAULT_ASPECT_RATIO) {
            throw new IllegalArgumentException("The auto-size step granularity (" + f9 + "px) is less or equal to (0px)");
        }
        this.f1253a = 1;
        this.f1256d = f7;
        this.f1257e = f8;
        this.f1255c = f9;
        this.f1259g = false;
    }
}
