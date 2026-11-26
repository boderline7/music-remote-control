package androidx.core.graphics.drawable;

import android.content.res.ColorStateList;
import android.graphics.PorterDuff;
import android.os.Parcel;
import android.os.Parcelable;
import java.nio.charset.Charset;
import p1.a;
import p1.b;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_2_1633180.dex */
public class IconCompatParcelizer {
    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    public static IconCompat read(a aVar) {
        IconCompat iconCompat = new IconCompat();
        iconCompat.f1753a = aVar.f(iconCompat.f1753a, 1);
        byte[] bArr = iconCompat.f1755c;
        if (aVar.e(2)) {
            Parcel parcel = ((b) aVar).f8216e;
            int readInt = parcel.readInt();
            if (readInt < 0) {
                bArr = null;
            } else {
                byte[] bArr2 = new byte[readInt];
                parcel.readByteArray(bArr2);
                bArr = bArr2;
            }
        }
        iconCompat.f1755c = bArr;
        iconCompat.f1756d = aVar.g(iconCompat.f1756d, 3);
        iconCompat.f1757e = aVar.f(iconCompat.f1757e, 4);
        iconCompat.f1758f = aVar.f(iconCompat.f1758f, 5);
        iconCompat.f1759g = (ColorStateList) aVar.g(iconCompat.f1759g, 6);
        String str = iconCompat.f1761i;
        if (aVar.e(7)) {
            str = ((b) aVar).f8216e.readString();
        }
        iconCompat.f1761i = str;
        String str2 = iconCompat.f1762j;
        if (aVar.e(8)) {
            str2 = ((b) aVar).f8216e.readString();
        }
        iconCompat.f1762j = str2;
        iconCompat.f1760h = PorterDuff.Mode.valueOf(iconCompat.f1761i);
        switch (iconCompat.f1753a) {
            case -1:
                Parcelable parcelable = iconCompat.f1756d;
                if (parcelable == null) {
                    throw new IllegalArgumentException("Invalid icon");
                }
                iconCompat.f1754b = parcelable;
                return iconCompat;
            case 0:
            default:
                return iconCompat;
            case 1:
            case 5:
                Parcelable parcelable2 = iconCompat.f1756d;
                if (parcelable2 != null) {
                    iconCompat.f1754b = parcelable2;
                } else {
                    byte[] bArr3 = iconCompat.f1755c;
                    iconCompat.f1754b = bArr3;
                    iconCompat.f1753a = 3;
                    iconCompat.f1757e = 0;
                    iconCompat.f1758f = bArr3.length;
                }
                return iconCompat;
            case 2:
            case 4:
            case 6:
                String str3 = new String(iconCompat.f1755c, Charset.forName("UTF-16"));
                iconCompat.f1754b = str3;
                if (iconCompat.f1753a == 2 && iconCompat.f1762j == null) {
                    iconCompat.f1762j = str3.split(":", -1)[0];
                }
                return iconCompat;
            case 3:
                iconCompat.f1754b = iconCompat.f1755c;
                return iconCompat;
        }
    }

    public static void write(IconCompat iconCompat, a aVar) {
        aVar.getClass();
        iconCompat.f1761i = iconCompat.f1760h.name();
        switch (iconCompat.f1753a) {
            case -1:
                iconCompat.f1756d = (Parcelable) iconCompat.f1754b;
                break;
            case 1:
            case 5:
                iconCompat.f1756d = (Parcelable) iconCompat.f1754b;
                break;
            case 2:
                iconCompat.f1755c = ((String) iconCompat.f1754b).getBytes(Charset.forName("UTF-16"));
                break;
            case 3:
                iconCompat.f1755c = (byte[]) iconCompat.f1754b;
                break;
            case 4:
            case 6:
                iconCompat.f1755c = iconCompat.f1754b.toString().getBytes(Charset.forName("UTF-16"));
                break;
        }
        int i7 = iconCompat.f1753a;
        if (-1 != i7) {
            aVar.j(i7, 1);
        }
        byte[] bArr = iconCompat.f1755c;
        if (bArr != null) {
            aVar.i(2);
            int length = bArr.length;
            Parcel parcel = ((b) aVar).f8216e;
            parcel.writeInt(length);
            parcel.writeByteArray(bArr);
        }
        Parcelable parcelable = iconCompat.f1756d;
        if (parcelable != null) {
            aVar.k(parcelable, 3);
        }
        int i8 = iconCompat.f1757e;
        if (i8 != 0) {
            aVar.j(i8, 4);
        }
        int i9 = iconCompat.f1758f;
        if (i9 != 0) {
            aVar.j(i9, 5);
        }
        ColorStateList colorStateList = iconCompat.f1759g;
        if (colorStateList != null) {
            aVar.k(colorStateList, 6);
        }
        String str = iconCompat.f1761i;
        if (str != null) {
            aVar.i(7);
            ((b) aVar).f8216e.writeString(str);
        }
        String str2 = iconCompat.f1762j;
        if (str2 != null) {
            aVar.i(8);
            ((b) aVar).f8216e.writeString(str2);
        }
    }
}
