package android.support.v4.media.session;

import android.os.Bundle;
import android.os.IInterface;
import android.support.v4.media.MediaMetadataCompat;
import java.util.ArrayList;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_2_1633180.dex */
public interface a extends IInterface {
    public static final String DESCRIPTOR = "android.support.v4.media.session.IMediaControllerCallback";

    void a(CharSequence charSequence);

    void b();

    void c(MediaMetadataCompat mediaMetadataCompat);

    void d(Bundle bundle);

    void e(ArrayList arrayList);

    void g(ParcelableVolumeInfo parcelableVolumeInfo);
}
