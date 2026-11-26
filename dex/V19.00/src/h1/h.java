package h1;

import android.view.Choreographer;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public abstract class h {
    public static void a(final Runnable runnable) {
        Choreographer.getInstance().postFrameCallback(new Choreographer.FrameCallback() { // from class: h1.g
            @Override // android.view.Choreographer.FrameCallback
            public final void doFrame(long j7) {
                runnable.run();
            }
        });
    }
}
