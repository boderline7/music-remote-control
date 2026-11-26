package s0;

import android.content.ClipData;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputConnection;
import android.view.inputmethod.InputConnectionWrapper;
import android.view.inputmethod.InputContentInfo;
import e0.f;
import p0.d1;
import p0.e;
import p0.g;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final class c extends InputConnectionWrapper {

    /* renamed from: a, reason: collision with root package name */
    public final /* synthetic */ f f8649a;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public /* synthetic */ c(InputConnection inputConnection, f fVar) {
        super(inputConnection, false);
        this.f8649a = fVar;
    }

    @Override // android.view.inputmethod.InputConnectionWrapper, android.view.inputmethod.InputConnection
    public final boolean commitContent(InputContentInfo inputContentInfo, int i7, Bundle bundle) {
        Bundle bundle2;
        e.a aVar = inputContentInfo == null ? null : new e.a(17, new e.a(inputContentInfo));
        View view = (View) this.f8649a.f6294b;
        if ((i7 & 1) != 0) {
            try {
                aVar.f();
                InputContentInfo inputContentInfo2 = (InputContentInfo) ((d) aVar.f6290b).y();
                bundle2 = bundle == null ? new Bundle() : new Bundle(bundle);
                bundle2.putParcelable("androidx.core.view.extra.INPUT_CONTENT_INFO", inputContentInfo2);
            } catch (Exception unused) {
            }
        } else {
            bundle2 = bundle;
        }
        ClipData clipData = new ClipData(aVar.r(), new ClipData.Item(aVar.z()));
        p0.f eVar = Build.VERSION.SDK_INT >= 31 ? new e(clipData, 2) : new g(clipData, 2);
        eVar.d(aVar.h());
        eVar.c(bundle2);
        if (d1.k(view, eVar.a()) == null) {
            return true;
        }
        return super.commitContent(inputContentInfo, i7, bundle);
    }

    @Override // android.view.inputmethod.InputConnectionWrapper, android.view.inputmethod.InputConnection
    public final boolean performPrivateCommand(String str, Bundle bundle) {
        return super.performPrivateCommand(str, bundle);
    }
}
