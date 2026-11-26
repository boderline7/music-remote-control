package y0;

import android.content.Context;
import android.content.pm.PackageManager;
import android.graphics.Typeface;
import android.os.Handler;
import java.nio.MappedByteBuffer;
import java.util.concurrent.Executor;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final class u implements k {

    /* renamed from: a, reason: collision with root package name */
    public final Context f10014a;

    /* renamed from: b, reason: collision with root package name */
    public final androidx.appcompat.widget.w f10015b;

    /* renamed from: c, reason: collision with root package name */
    public final p3.f f10016c;

    /* renamed from: d, reason: collision with root package name */
    public final Object f10017d;

    /* renamed from: e, reason: collision with root package name */
    public Handler f10018e;

    /* renamed from: f, reason: collision with root package name */
    public Executor f10019f;

    /* renamed from: g, reason: collision with root package name */
    public ThreadPoolExecutor f10020g;

    /* renamed from: h, reason: collision with root package name */
    public l6.f f10021h;

    /* renamed from: i, reason: collision with root package name */
    public t0.a f10022i;

    public u(Context context, androidx.appcompat.widget.w wVar) {
        p3.f fVar = v.f10023d;
        this.f10017d = new Object();
        if (context == null) {
            throw new NullPointerException("Context cannot be null");
        }
        this.f10014a = context.getApplicationContext();
        this.f10015b = wVar;
        this.f10016c = fVar;
    }

    @Override // y0.k
    public final void a(l6.f fVar) {
        synchronized (this.f10017d) {
            this.f10021h = fVar;
        }
        c();
    }

    public final void b() {
        synchronized (this.f10017d) {
            try {
                this.f10021h = null;
                t0.a aVar = this.f10022i;
                if (aVar != null) {
                    p3.f fVar = this.f10016c;
                    Context context = this.f10014a;
                    fVar.getClass();
                    context.getContentResolver().unregisterContentObserver(aVar);
                    this.f10022i = null;
                }
                Handler handler = this.f10018e;
                if (handler != null) {
                    handler.removeCallbacks(null);
                }
                this.f10018e = null;
                ThreadPoolExecutor threadPoolExecutor = this.f10020g;
                if (threadPoolExecutor != null) {
                    threadPoolExecutor.shutdown();
                }
                this.f10019f = null;
                this.f10020g = null;
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    public final void c() {
        synchronized (this.f10017d) {
            try {
                if (this.f10021h == null) {
                    return;
                }
                if (this.f10019f == null) {
                    ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(0, 1, 15L, TimeUnit.SECONDS, new LinkedBlockingDeque(), new a("emojiCompat"));
                    threadPoolExecutor.allowCoreThreadTimeOut(true);
                    this.f10020g = threadPoolExecutor;
                    this.f10019f = threadPoolExecutor;
                }
                final int i7 = 0;
                this.f10019f.execute(new Runnable(this) { // from class: y0.t

                    /* renamed from: b, reason: collision with root package name */
                    public final /* synthetic */ u f10013b;

                    {
                        this.f10013b = this;
                    }

                    @Override // java.lang.Runnable
                    public final void run() {
                        switch (i7) {
                            case 0:
                                u uVar = this.f10013b;
                                synchronized (uVar.f10017d) {
                                    try {
                                        if (uVar.f10021h == null) {
                                            return;
                                        }
                                        try {
                                            m0.i d7 = uVar.d();
                                            int i8 = d7.f7507e;
                                            if (i8 == 2) {
                                                synchronized (uVar.f10017d) {
                                                }
                                            }
                                            if (i8 != 0) {
                                                throw new RuntimeException("fetchFonts result is not OK. (" + i8 + ")");
                                            }
                                            try {
                                                int i9 = l0.k.f7276a;
                                                l0.j.a("EmojiCompat.FontRequestEmojiCompatConfig.buildTypeface");
                                                p3.f fVar = uVar.f10016c;
                                                Context context = uVar.f10014a;
                                                fVar.getClass();
                                                Typeface u6 = i0.h.f6781a.u(context, new m0.i[]{d7}, 0);
                                                MappedByteBuffer M0 = n1.z.M0(uVar.f10014a, d7.f7503a);
                                                if (M0 == null || u6 == null) {
                                                    throw new RuntimeException("Unable to open file.");
                                                }
                                                try {
                                                    l0.j.a("EmojiCompat.MetadataRepo.create");
                                                    j.h hVar = new j.h(u6, n1.z.T0(M0));
                                                    l0.j.b();
                                                    l0.j.b();
                                                    synchronized (uVar.f10017d) {
                                                        try {
                                                            l6.f fVar2 = uVar.f10021h;
                                                            if (fVar2 != null) {
                                                                fVar2.e0(hVar);
                                                            }
                                                        } finally {
                                                        }
                                                    }
                                                    uVar.b();
                                                    return;
                                                } finally {
                                                    int i10 = l0.k.f7276a;
                                                    l0.j.b();
                                                }
                                            } catch (Throwable th) {
                                                throw th;
                                            }
                                        } catch (Throwable th2) {
                                            synchronized (uVar.f10017d) {
                                                try {
                                                    l6.f fVar3 = uVar.f10021h;
                                                    if (fVar3 != null) {
                                                        fVar3.Y(th2);
                                                    }
                                                    uVar.b();
                                                    return;
                                                } finally {
                                                }
                                            }
                                        }
                                    } finally {
                                    }
                                }
                            default:
                                this.f10013b.c();
                                return;
                        }
                    }
                });
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    public final m0.i d() {
        try {
            p3.f fVar = this.f10016c;
            Context context = this.f10014a;
            androidx.appcompat.widget.w wVar = this.f10015b;
            fVar.getClass();
            m0.h a7 = m0.c.a(context, wVar);
            int i7 = a7.f7501a;
            if (i7 != 0) {
                throw new RuntimeException(a.a.e("fetchFonts failed (", i7, ")"));
            }
            m0.i[] iVarArr = a7.f7502b;
            if (iVarArr == null || iVarArr.length == 0) {
                throw new RuntimeException("fetchFonts failed (empty result)");
            }
            return iVarArr[0];
        } catch (PackageManager.NameNotFoundException e7) {
            throw new RuntimeException("provider not found", e7);
        }
    }
}
