package t0;

import android.content.Context;
import android.database.ContentObserver;
import android.database.Cursor;
import android.net.Uri;
import android.os.Handler;
import com.navi.tool.broadcast.BrightnessChangeReceiver;
import y0.u;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final class a extends ContentObserver {

    /* renamed from: a, reason: collision with root package name */
    public final /* synthetic */ int f9012a = 2;

    /* renamed from: b, reason: collision with root package name */
    public final /* synthetic */ Object f9013b;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public a(BrightnessChangeReceiver brightnessChangeReceiver) {
        super(brightnessChangeReceiver.f4683c);
        this.f9013b = brightnessChangeReceiver;
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public a(b bVar) {
        super(new Handler());
        this.f9013b = bVar;
    }

    @Override // android.database.ContentObserver
    public final boolean deliverSelfNotifications() {
        switch (this.f9012a) {
            case 0:
                return true;
            default:
                return super.deliverSelfNotifications();
        }
    }

    @Override // android.database.ContentObserver
    public final void onChange(boolean z6) {
        Cursor cursor;
        int i7 = this.f9012a;
        Object obj = this.f9013b;
        switch (i7) {
            case 0:
                b bVar = (b) obj;
                if (bVar.f9015b && (cursor = bVar.f9016c) != null && !cursor.isClosed()) {
                    bVar.f9014a = bVar.f9016c.requery();
                    break;
                }
                break;
            case 1:
            default:
                super.onChange(z6);
                break;
            case 2:
                super.onChange(z6);
                BrightnessChangeReceiver brightnessChangeReceiver = (BrightnessChangeReceiver) obj;
                Context context = brightnessChangeReceiver.f4681a;
                if (context != null) {
                    brightnessChangeReceiver.a(context);
                    break;
                }
                break;
        }
    }

    @Override // android.database.ContentObserver
    public final void onChange(boolean z6, Uri uri) {
        switch (this.f9012a) {
            case 1:
                ((u) this.f9013b).c();
                break;
            default:
                super.onChange(z6, uri);
                break;
        }
    }
}
