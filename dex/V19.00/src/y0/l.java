package y0;

import android.os.Handler;
import android.os.Looper;
import java.util.ArrayList;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final class l {
    public static final String EDITOR_INFO_METAVERSION_KEY = "android.support.text.emoji.emojiCompat_metadataVersion";
    public static final String EDITOR_INFO_REPLACE_ALL_KEY = "android.support.text.emoji.emojiCompat_replaceAll";
    public static final int EMOJI_FALLBACK = 2;
    public static final int EMOJI_SUPPORTED = 1;
    public static final int EMOJI_UNSUPPORTED = 0;
    public static final int LOAD_STATE_DEFAULT = 3;
    public static final int LOAD_STATE_FAILED = 2;
    public static final int LOAD_STATE_LOADING = 0;
    public static final int LOAD_STATE_SUCCEEDED = 1;
    public static final int LOAD_STRATEGY_DEFAULT = 0;
    public static final int LOAD_STRATEGY_MANUAL = 1;
    public static final int REPLACE_STRATEGY_ALL = 1;
    public static final int REPLACE_STRATEGY_DEFAULT = 0;
    public static final int REPLACE_STRATEGY_NON_EXISTENT = 2;

    /* renamed from: i, reason: collision with root package name */
    public static final Object f9976i = new Object();

    /* renamed from: j, reason: collision with root package name */
    public static volatile l f9977j;

    /* renamed from: a, reason: collision with root package name */
    public final ReentrantReadWriteLock f9978a;

    /* renamed from: b, reason: collision with root package name */
    public final q.c f9979b;

    /* renamed from: c, reason: collision with root package name */
    public volatile int f9980c;

    /* renamed from: d, reason: collision with root package name */
    public final Handler f9981d;

    /* renamed from: e, reason: collision with root package name */
    public final g f9982e;

    /* renamed from: f, reason: collision with root package name */
    public final k f9983f;

    /* renamed from: g, reason: collision with root package name */
    public final int f9984g;

    /* renamed from: h, reason: collision with root package name */
    public final e f9985h;

    public l(v vVar) {
        ReentrantReadWriteLock reentrantReadWriteLock = new ReentrantReadWriteLock();
        this.f9978a = reentrantReadWriteLock;
        this.f9980c = 3;
        this.f9983f = vVar.f9973a;
        int i7 = vVar.f9974b;
        this.f9984g = i7;
        this.f9985h = vVar.f9975c;
        this.f9981d = new Handler(Looper.getMainLooper());
        this.f9979b = new q.c(0);
        g gVar = new g(21, this);
        this.f9982e = gVar;
        reentrantReadWriteLock.writeLock().lock();
        if (i7 == 0) {
            try {
                this.f9980c = 0;
            } catch (Throwable th) {
                this.f9978a.writeLock().unlock();
                throw th;
            }
        }
        reentrantReadWriteLock.writeLock().unlock();
        if (b() == 0) {
            gVar.D();
        }
    }

    public static l a() {
        l lVar;
        synchronized (f9976i) {
            try {
                lVar = f9977j;
                if (!(lVar != null)) {
                    throw new IllegalStateException("EmojiCompat is not initialized.\n\nYou must initialize EmojiCompat prior to referencing the EmojiCompat instance.\n\nThe most likely cause of this error is disabling the EmojiCompatInitializer\neither explicitly in AndroidManifest.xml, or by including\nandroidx.emoji2:emoji2-bundled.\n\nAutomatic initialization is typically performed by EmojiCompatInitializer. If\nyou are not expecting to initialize EmojiCompat manually in your application,\nplease check to ensure it has not been removed from your APK's manifest. You can\ndo this in Android Studio using Build > Analyze APK.\n\nIn the APK Analyzer, ensure that the startup entry for\nEmojiCompatInitializer and InitializationProvider is present in\n AndroidManifest.xml. If it is missing or contains tools:node=\"remove\", and you\nintend to use automatic configuration, verify:\n\n  1. Your application does not include emoji2-bundled\n  2. All modules do not contain an exclusion manifest rule for\n     EmojiCompatInitializer or InitializationProvider. For more information\n     about manifest exclusions see the documentation for the androidx startup\n     library.\n\nIf you intend to use emoji2-bundled, please call EmojiCompat.init. You can\nlearn more in the documentation for BundledEmojiCompatConfig.\n\nIf you intended to perform manual configuration, it is recommended that you call\nEmojiCompat.init immediately on application startup.\n\nIf you still cannot resolve this issue, please open a bug with your specific\nconfiguration to help improve error message.");
                }
            } finally {
            }
        }
        return lVar;
    }

    public final int b() {
        this.f9978a.readLock().lock();
        try {
            return this.f9980c;
        } finally {
            this.f9978a.readLock().unlock();
        }
    }

    public final void c() {
        if (!(this.f9984g == 1)) {
            throw new IllegalStateException("Set metadataLoadStrategy to LOAD_STRATEGY_MANUAL to execute manual loading");
        }
        if (b() == 1) {
            return;
        }
        this.f9978a.writeLock().lock();
        try {
            if (this.f9980c == 0) {
                return;
            }
            this.f9980c = 0;
            this.f9978a.writeLock().unlock();
            this.f9982e.D();
        } finally {
            this.f9978a.writeLock().unlock();
        }
    }

    public final void d(Throwable th) {
        ArrayList arrayList = new ArrayList();
        this.f9978a.writeLock().lock();
        try {
            this.f9980c = 2;
            arrayList.addAll(this.f9979b);
            this.f9979b.clear();
            this.f9978a.writeLock().unlock();
            this.f9981d.post(new b.d(arrayList, this.f9980c, th));
        } catch (Throwable th2) {
            this.f9978a.writeLock().unlock();
            throw th2;
        }
    }

    public final void e() {
        ArrayList arrayList = new ArrayList();
        this.f9978a.writeLock().lock();
        try {
            this.f9980c = 1;
            arrayList.addAll(this.f9979b);
            this.f9979b.clear();
            this.f9978a.writeLock().unlock();
            this.f9981d.post(new b.d(this.f9980c, arrayList));
        } catch (Throwable th) {
            this.f9978a.writeLock().unlock();
            throw th;
        }
    }

    public final CharSequence f(int i7, int i8, CharSequence charSequence) {
        if (!(b() == 1)) {
            throw new IllegalStateException("Not initialized yet");
        }
        if (i7 < 0) {
            throw new IllegalArgumentException("start cannot be negative");
        }
        if (i8 < 0) {
            throw new IllegalArgumentException("end cannot be negative");
        }
        n1.z.t("start should be <= than end", i7 <= i8);
        if (charSequence == null) {
            return null;
        }
        n1.z.t("start should be < than charSequence length", i7 <= charSequence.length());
        n1.z.t("end should be < than charSequence length", i8 <= charSequence.length());
        return (charSequence.length() == 0 || i7 == i8) ? charSequence : this.f9982e.E(charSequence, i7, i8, false);
    }

    public final void g(j jVar) {
        if (jVar == null) {
            throw new NullPointerException("initCallback cannot be null");
        }
        this.f9978a.writeLock().lock();
        try {
            if (this.f9980c != 1 && this.f9980c != 2) {
                this.f9979b.add(jVar);
                this.f9978a.writeLock().unlock();
            }
            this.f9981d.post(new b.d(jVar, this.f9980c));
            this.f9978a.writeLock().unlock();
        } catch (Throwable th) {
            this.f9978a.writeLock().unlock();
            throw th;
        }
    }
}
