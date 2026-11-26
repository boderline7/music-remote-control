package androidx.core.graphics.drawable;

import android.content.res.ColorStateList;
import android.graphics.Bitmap;
import android.graphics.PorterDuff;
import android.net.Uri;
import android.os.Build;
import android.os.Parcelable;
import androidx.versionedparcelable.CustomVersionedParcelable;
import j0.d;
import j0.f;
import java.lang.reflect.InvocationTargetException;
import s5.h0;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_2_1633180.dex */
public class IconCompat extends CustomVersionedParcelable {
    public static final int TYPE_ADAPTIVE_BITMAP = 5;
    public static final int TYPE_BITMAP = 1;
    public static final int TYPE_DATA = 3;
    public static final int TYPE_RESOURCE = 2;
    public static final int TYPE_UNKNOWN = -1;
    public static final int TYPE_URI = 4;
    public static final int TYPE_URI_ADAPTIVE_BITMAP = 6;

    /* renamed from: k, reason: collision with root package name */
    public static final PorterDuff.Mode f1752k = PorterDuff.Mode.SRC_IN;

    /* renamed from: b, reason: collision with root package name */
    public Object f1754b;

    /* renamed from: j, reason: collision with root package name */
    public String f1762j;

    /* renamed from: a, reason: collision with root package name */
    public int f1753a = -1;

    /* renamed from: c, reason: collision with root package name */
    public byte[] f1755c = null;

    /* renamed from: d, reason: collision with root package name */
    public Parcelable f1756d = null;

    /* renamed from: e, reason: collision with root package name */
    public int f1757e = 0;

    /* renamed from: f, reason: collision with root package name */
    public int f1758f = 0;

    /* renamed from: g, reason: collision with root package name */
    public ColorStateList f1759g = null;

    /* renamed from: h, reason: collision with root package name */
    public PorterDuff.Mode f1760h = f1752k;

    /* renamed from: i, reason: collision with root package name */
    public String f1761i = null;

    public static IconCompat a(int i7) {
        if (i7 == 0) {
            throw new IllegalArgumentException("Drawable resource ID must not be 0");
        }
        IconCompat iconCompat = new IconCompat();
        iconCompat.f1755c = null;
        iconCompat.f1756d = null;
        iconCompat.f1758f = 0;
        iconCompat.f1759g = null;
        iconCompat.f1760h = f1752k;
        iconCompat.f1761i = null;
        iconCompat.f1753a = 2;
        iconCompat.f1757e = i7;
        iconCompat.f1754b = h0.FRAGMENT_ENCODE_SET;
        iconCompat.f1762j = h0.FRAGMENT_ENCODE_SET;
        return iconCompat;
    }

    public final int b() {
        int i7 = this.f1753a;
        if (i7 != -1) {
            if (i7 == 2) {
                return this.f1757e;
            }
            throw new IllegalStateException("called getResId() on " + this);
        }
        int i8 = Build.VERSION.SDK_INT;
        Object obj = this.f1754b;
        if (i8 >= 28) {
            return f.a(obj);
        }
        try {
            return ((Integer) obj.getClass().getMethod("getResId", new Class[0]).invoke(obj, new Object[0])).intValue();
        } catch (IllegalAccessException | NoSuchMethodException | InvocationTargetException unused) {
            return 0;
        }
    }

    public final Uri c() {
        int i7 = this.f1753a;
        if (i7 == -1) {
            return d.a(this.f1754b);
        }
        if (i7 == 4 || i7 == 6) {
            return Uri.parse((String) this.f1754b);
        }
        throw new IllegalStateException("called getUri() on " + this);
    }

    public final String toString() {
        String str;
        if (this.f1753a == -1) {
            return String.valueOf(this.f1754b);
        }
        StringBuilder sb = new StringBuilder("Icon(typ=");
        switch (this.f1753a) {
            case 1:
                str = "BITMAP";
                break;
            case 2:
                str = "RESOURCE";
                break;
            case 3:
                str = "DATA";
                break;
            case 4:
                str = "URI";
                break;
            case 5:
                str = "BITMAP_MASKABLE";
                break;
            case 6:
                str = "URI_MASKABLE";
                break;
            default:
                str = "UNKNOWN";
                break;
        }
        sb.append(str);
        switch (this.f1753a) {
            case 1:
            case 5:
                sb.append(" size=");
                sb.append(((Bitmap) this.f1754b).getWidth());
                sb.append("x");
                sb.append(((Bitmap) this.f1754b).getHeight());
                break;
            case 2:
                sb.append(" pkg=");
                sb.append(this.f1762j);
                sb.append(" id=");
                sb.append(String.format("0x%08x", Integer.valueOf(b())));
                break;
            case 3:
                sb.append(" len=");
                sb.append(this.f1757e);
                if (this.f1758f != 0) {
                    sb.append(" off=");
                    sb.append(this.f1758f);
                    break;
                }
                break;
            case 4:
            case 6:
                sb.append(" uri=");
                sb.append(this.f1754b);
                break;
        }
        if (this.f1759g != null) {
            sb.append(" tint=");
            sb.append(this.f1759g);
        }
        if (this.f1760h != f1752k) {
            sb.append(" mode=");
            sb.append(this.f1760h);
        }
        sb.append(")");
        return sb.toString();
    }
}
