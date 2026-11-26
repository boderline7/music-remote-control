package y0;

import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import androidx.emoji2.text.EmojiCompatInitializer;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final class m implements androidx.lifecycle.f {

    /* renamed from: a, reason: collision with root package name */
    public final /* synthetic */ androidx.lifecycle.s f9986a;

    /* renamed from: b, reason: collision with root package name */
    public final /* synthetic */ EmojiCompatInitializer f9987b;

    public m(EmojiCompatInitializer emojiCompatInitializer, androidx.lifecycle.s sVar) {
        this.f9987b = emojiCompatInitializer;
        this.f9986a = sVar;
    }

    @Override // androidx.lifecycle.f
    public final void onResume(androidx.lifecycle.x xVar) {
        this.f9987b.getClass();
        (Build.VERSION.SDK_INT >= 28 ? b.a(Looper.getMainLooper()) : new Handler(Looper.getMainLooper())).postDelayed(new p(0), 500L);
        this.f9986a.b(this);
    }
}
