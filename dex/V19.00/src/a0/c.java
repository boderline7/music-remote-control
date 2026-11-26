package a0;

import android.content.Context;
import android.content.res.TypedArray;
import android.content.res.XmlResourceParser;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.util.TypedValue;
import android.util.Xml;
import android.view.View;
import com.yalantis.ucrop.view.CropImageView;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final class c {

    /* renamed from: a, reason: collision with root package name */
    public boolean f2a = false;

    /* renamed from: b, reason: collision with root package name */
    public String f3b;

    /* renamed from: c, reason: collision with root package name */
    public b f4c;

    /* renamed from: d, reason: collision with root package name */
    public int f5d;

    /* renamed from: e, reason: collision with root package name */
    public float f6e;

    /* renamed from: f, reason: collision with root package name */
    public String f7f;

    /* renamed from: g, reason: collision with root package name */
    public boolean f8g;

    /* renamed from: h, reason: collision with root package name */
    public int f9h;

    public c(c cVar, Object obj) {
        this.f3b = cVar.f3b;
        this.f4c = cVar.f4c;
        f(obj);
    }

    public static void d(Context context, XmlResourceParser xmlResourceParser, HashMap hashMap) {
        b bVar;
        Object valueOf;
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(Xml.asAttributeSet(xmlResourceParser), t.CustomAttribute);
        int indexCount = obtainStyledAttributes.getIndexCount();
        String str = null;
        Object obj = null;
        b bVar2 = null;
        boolean z6 = false;
        for (int i7 = 0; i7 < indexCount; i7++) {
            int index = obtainStyledAttributes.getIndex(i7);
            if (index == t.CustomAttribute_attributeName) {
                str = obtainStyledAttributes.getString(index);
                if (str != null && str.length() > 0) {
                    str = Character.toUpperCase(str.charAt(0)) + str.substring(1);
                }
            } else if (index == t.CustomAttribute_methodName) {
                str = obtainStyledAttributes.getString(index);
                z6 = true;
            } else if (index == t.CustomAttribute_customBoolean) {
                obj = Boolean.valueOf(obtainStyledAttributes.getBoolean(index, false));
                bVar2 = b.BOOLEAN_TYPE;
            } else {
                if (index == t.CustomAttribute_customColorValue) {
                    bVar = b.COLOR_TYPE;
                    valueOf = Integer.valueOf(obtainStyledAttributes.getColor(index, 0));
                } else if (index == t.CustomAttribute_customColorDrawableValue) {
                    bVar = b.COLOR_DRAWABLE_TYPE;
                    valueOf = Integer.valueOf(obtainStyledAttributes.getColor(index, 0));
                } else if (index == t.CustomAttribute_customPixelDimension) {
                    bVar = b.DIMENSION_TYPE;
                    valueOf = Float.valueOf(TypedValue.applyDimension(1, obtainStyledAttributes.getDimension(index, CropImageView.DEFAULT_ASPECT_RATIO), context.getResources().getDisplayMetrics()));
                } else if (index == t.CustomAttribute_customDimension) {
                    bVar = b.DIMENSION_TYPE;
                    valueOf = Float.valueOf(obtainStyledAttributes.getDimension(index, CropImageView.DEFAULT_ASPECT_RATIO));
                } else if (index == t.CustomAttribute_customFloatValue) {
                    bVar = b.FLOAT_TYPE;
                    valueOf = Float.valueOf(obtainStyledAttributes.getFloat(index, Float.NaN));
                } else if (index == t.CustomAttribute_customIntegerValue) {
                    bVar = b.INT_TYPE;
                    valueOf = Integer.valueOf(obtainStyledAttributes.getInteger(index, -1));
                } else if (index == t.CustomAttribute_customStringValue) {
                    bVar = b.STRING_TYPE;
                    valueOf = obtainStyledAttributes.getString(index);
                } else if (index == t.CustomAttribute_customReference) {
                    bVar = b.REFERENCE_TYPE;
                    int resourceId = obtainStyledAttributes.getResourceId(index, -1);
                    if (resourceId == -1) {
                        resourceId = obtainStyledAttributes.getInt(index, -1);
                    }
                    valueOf = Integer.valueOf(resourceId);
                }
                Object obj2 = valueOf;
                bVar2 = bVar;
                obj = obj2;
            }
        }
        if (str != null && obj != null) {
            c cVar = new c();
            cVar.f3b = str;
            cVar.f4c = bVar2;
            cVar.f2a = z6;
            cVar.f(obj);
            hashMap.put(str, cVar);
        }
        obtainStyledAttributes.recycle();
    }

    public static void e(View view, HashMap hashMap) {
        Class<?> cls = view.getClass();
        for (String str : hashMap.keySet()) {
            c cVar = (c) hashMap.get(str);
            if (!cVar.f2a) {
                str = a.a.B("set", str);
            }
            try {
                switch (a.f0a[cVar.f4c.ordinal()]) {
                    case 1:
                        cls.getMethod(str, Integer.TYPE).invoke(view, Integer.valueOf(cVar.f5d));
                        break;
                    case 2:
                        cls.getMethod(str, Boolean.TYPE).invoke(view, Boolean.valueOf(cVar.f8g));
                        break;
                    case 3:
                        cls.getMethod(str, CharSequence.class).invoke(view, cVar.f7f);
                        break;
                    case 4:
                        cls.getMethod(str, Integer.TYPE).invoke(view, Integer.valueOf(cVar.f9h));
                        break;
                    case 5:
                        Method method = cls.getMethod(str, Drawable.class);
                        ColorDrawable colorDrawable = new ColorDrawable();
                        colorDrawable.setColor(cVar.f9h);
                        method.invoke(view, colorDrawable);
                        break;
                    case 6:
                        cls.getMethod(str, Integer.TYPE).invoke(view, Integer.valueOf(cVar.f5d));
                        break;
                    case 7:
                        cls.getMethod(str, Float.TYPE).invoke(view, Float.valueOf(cVar.f6e));
                        break;
                    case 8:
                        cls.getMethod(str, Float.TYPE).invoke(view, Float.valueOf(cVar.f6e));
                        break;
                }
            } catch (IllegalAccessException e7) {
                e7.printStackTrace();
            } catch (NoSuchMethodException e8) {
                e8.getMessage();
            } catch (InvocationTargetException e9) {
                e9.printStackTrace();
            }
        }
    }

    public final float a() {
        switch (a.f0a[this.f4c.ordinal()]) {
            case 2:
                if (this.f8g) {
                    return 1.0f;
                }
                return CropImageView.DEFAULT_ASPECT_RATIO;
            case 3:
                throw new RuntimeException("Cannot interpolate String");
            case 4:
            case 5:
                throw new RuntimeException("Color does not have a single color to interpolate");
            case 6:
                return this.f5d;
            case 7:
                return this.f6e;
            case 8:
                return this.f6e;
            default:
                return Float.NaN;
        }
    }

    public final void b(float[] fArr) {
        switch (a.f0a[this.f4c.ordinal()]) {
            case 2:
                fArr[0] = this.f8g ? 1.0f : CropImageView.DEFAULT_ASPECT_RATIO;
                return;
            case 3:
                throw new RuntimeException("Color does not have a single color to interpolate");
            case 4:
            case 5:
                int i7 = (this.f9h >> 24) & 255;
                float pow = (float) Math.pow(((r0 >> 16) & 255) / 255.0f, 2.2d);
                float pow2 = (float) Math.pow(((r0 >> 8) & 255) / 255.0f, 2.2d);
                float pow3 = (float) Math.pow((r0 & 255) / 255.0f, 2.2d);
                fArr[0] = pow;
                fArr[1] = pow2;
                fArr[2] = pow3;
                fArr[3] = i7 / 255.0f;
                return;
            case 6:
                fArr[0] = this.f5d;
                return;
            case 7:
                fArr[0] = this.f6e;
                return;
            case 8:
                fArr[0] = this.f6e;
                return;
            default:
                return;
        }
    }

    public final int c() {
        int i7 = a.f0a[this.f4c.ordinal()];
        return (i7 == 4 || i7 == 5) ? 4 : 1;
    }

    public final void f(Object obj) {
        switch (a.f0a[this.f4c.ordinal()]) {
            case 1:
            case 6:
                this.f5d = ((Integer) obj).intValue();
                break;
            case 2:
                this.f8g = ((Boolean) obj).booleanValue();
                break;
            case 3:
                this.f7f = (String) obj;
                break;
            case 4:
            case 5:
                this.f9h = ((Integer) obj).intValue();
                break;
            case 7:
                this.f6e = ((Float) obj).floatValue();
                break;
            case 8:
                this.f6e = ((Float) obj).floatValue();
                break;
        }
    }
}
