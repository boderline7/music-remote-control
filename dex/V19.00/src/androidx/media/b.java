package androidx.media;

import android.os.Bundle;
import android.support.v4.os.ResultReceiver;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_2_1633180.dex */
public final class b extends j {

    /* renamed from: d, reason: collision with root package name */
    public final /* synthetic */ int f2239d;

    /* renamed from: e, reason: collision with root package name */
    public final /* synthetic */ ResultReceiver f2240e;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public /* synthetic */ b(String str, ResultReceiver resultReceiver, int i7) {
        super(str);
        this.f2239d = i7;
        this.f2240e = resultReceiver;
    }

    @Override // androidx.media.j
    public final void b() {
        int i7 = this.f2239d;
        ResultReceiver resultReceiver = this.f2240e;
        switch (i7) {
            case 0:
                if ((this.f2259c & 2) == 0) {
                    Bundle bundle = new Bundle();
                    bundle.putParcelable(MediaBrowserServiceCompat.KEY_MEDIA_ITEM, null);
                    resultReceiver.m(0, bundle);
                    break;
                } else {
                    resultReceiver.m(-1, null);
                    break;
                }
            case 1:
                resultReceiver.m(-1, null);
                break;
            default:
                resultReceiver.m(0, null);
                break;
        }
    }
}
