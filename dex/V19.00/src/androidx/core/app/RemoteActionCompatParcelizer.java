package androidx.core.app;

import android.app.PendingIntent;
import android.os.Parcel;
import android.text.TextUtils;
import androidx.core.graphics.drawable.IconCompat;
import p1.a;
import p1.b;
import p1.c;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_2_1633180.dex */
public class RemoteActionCompatParcelizer {
    public static RemoteActionCompat read(a aVar) {
        RemoteActionCompat remoteActionCompat = new RemoteActionCompat();
        c cVar = remoteActionCompat.f1741a;
        if (aVar.e(1)) {
            cVar = aVar.h();
        }
        remoteActionCompat.f1741a = (IconCompat) cVar;
        CharSequence charSequence = remoteActionCompat.f1742b;
        if (aVar.e(2)) {
            charSequence = (CharSequence) TextUtils.CHAR_SEQUENCE_CREATOR.createFromParcel(((b) aVar).f8216e);
        }
        remoteActionCompat.f1742b = charSequence;
        CharSequence charSequence2 = remoteActionCompat.f1743c;
        if (aVar.e(3)) {
            charSequence2 = (CharSequence) TextUtils.CHAR_SEQUENCE_CREATOR.createFromParcel(((b) aVar).f8216e);
        }
        remoteActionCompat.f1743c = charSequence2;
        remoteActionCompat.f1744d = (PendingIntent) aVar.g(remoteActionCompat.f1744d, 4);
        boolean z6 = remoteActionCompat.f1745e;
        if (aVar.e(5)) {
            z6 = ((b) aVar).f8216e.readInt() != 0;
        }
        remoteActionCompat.f1745e = z6;
        boolean z7 = remoteActionCompat.f1746f;
        if (aVar.e(6)) {
            z7 = ((b) aVar).f8216e.readInt() != 0;
        }
        remoteActionCompat.f1746f = z7;
        return remoteActionCompat;
    }

    public static void write(RemoteActionCompat remoteActionCompat, a aVar) {
        aVar.getClass();
        IconCompat iconCompat = remoteActionCompat.f1741a;
        aVar.i(1);
        aVar.l(iconCompat);
        CharSequence charSequence = remoteActionCompat.f1742b;
        aVar.i(2);
        Parcel parcel = ((b) aVar).f8216e;
        TextUtils.writeToParcel(charSequence, parcel, 0);
        CharSequence charSequence2 = remoteActionCompat.f1743c;
        aVar.i(3);
        TextUtils.writeToParcel(charSequence2, parcel, 0);
        aVar.k(remoteActionCompat.f1744d, 4);
        boolean z6 = remoteActionCompat.f1745e;
        aVar.i(5);
        parcel.writeInt(z6 ? 1 : 0);
        boolean z7 = remoteActionCompat.f1746f;
        aVar.i(6);
        parcel.writeInt(z7 ? 1 : 0);
    }
}
