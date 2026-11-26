package j0;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.Icon;
import android.net.Uri;
import android.os.Build;
import android.text.TextUtils;
import androidx.core.graphics.drawable.IconCompat;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public abstract class d {
    public static Uri a(Object obj) {
        if (Build.VERSION.SDK_INT >= 28) {
            return f.d(obj);
        }
        try {
            return (Uri) obj.getClass().getMethod("getUri", new Class[0]).invoke(obj, new Object[0]);
        } catch (IllegalAccessException | NoSuchMethodException | InvocationTargetException unused) {
            return null;
        }
    }

    public static Drawable b(Icon icon, Context context) {
        return icon.loadDrawable(context);
    }

    public static Icon c(IconCompat iconCompat, Context context) {
        Icon createWithBitmap;
        int i7 = iconCompat.f1753a;
        String str = null;
        r1 = null;
        InputStream openInputStream = null;
        switch (i7) {
            case -1:
                return (Icon) iconCompat.f1754b;
            case 0:
            default:
                throw new IllegalArgumentException("Unknown type");
            case 1:
                createWithBitmap = Icon.createWithBitmap((Bitmap) iconCompat.f1754b);
                break;
            case 2:
                if (i7 == -1) {
                    int i8 = Build.VERSION.SDK_INT;
                    Object obj = iconCompat.f1754b;
                    if (i8 >= 28) {
                        str = f.b(obj);
                    } else {
                        try {
                            str = (String) obj.getClass().getMethod("getResPackage", new Class[0]).invoke(obj, new Object[0]);
                        } catch (IllegalAccessException | NoSuchMethodException | InvocationTargetException unused) {
                        }
                    }
                } else {
                    if (i7 != 2) {
                        throw new IllegalStateException("called getResPackage() on " + iconCompat);
                    }
                    String str2 = iconCompat.f1762j;
                    str = (str2 == null || TextUtils.isEmpty(str2)) ? ((String) iconCompat.f1754b).split(":", -1)[0] : iconCompat.f1762j;
                }
                createWithBitmap = Icon.createWithResource(str, iconCompat.f1757e);
                break;
            case 3:
                createWithBitmap = Icon.createWithData((byte[]) iconCompat.f1754b, iconCompat.f1757e, iconCompat.f1758f);
                break;
            case 4:
                createWithBitmap = Icon.createWithContentUri((String) iconCompat.f1754b);
                break;
            case 5:
                createWithBitmap = e.b((Bitmap) iconCompat.f1754b);
                break;
            case 6:
                if (Build.VERSION.SDK_INT >= 30) {
                    createWithBitmap = g.a(iconCompat.c());
                    break;
                } else {
                    if (context == null) {
                        throw new IllegalArgumentException("Context is required to resolve the file uri of the icon: " + iconCompat.c());
                    }
                    Uri c7 = iconCompat.c();
                    String scheme = c7.getScheme();
                    if ("content".equals(scheme) || "file".equals(scheme)) {
                        try {
                            openInputStream = context.getContentResolver().openInputStream(c7);
                        } catch (Exception unused2) {
                            c7.toString();
                        }
                    } else {
                        try {
                            openInputStream = new FileInputStream(new File((String) iconCompat.f1754b));
                        } catch (FileNotFoundException unused3) {
                            c7.toString();
                        }
                    }
                    if (openInputStream == null) {
                        throw new IllegalStateException("Cannot load adaptive icon from uri: " + iconCompat.c());
                    }
                    createWithBitmap = e.b(BitmapFactory.decodeStream(openInputStream));
                    break;
                }
                break;
        }
        ColorStateList colorStateList = iconCompat.f1759g;
        if (colorStateList != null) {
            createWithBitmap.setTintList(colorStateList);
        }
        PorterDuff.Mode mode = iconCompat.f1760h;
        if (mode != IconCompat.f1752k) {
            createWithBitmap.setTintMode(mode);
        }
        return createWithBitmap;
    }
}
