package androidx.core.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ProgressBar;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_2_1633180.dex */
public class ContentLoadingProgressBar extends ProgressBar {

    /* renamed from: c, reason: collision with root package name */
    public static final /* synthetic */ int f1763c = 0;

    /* renamed from: a, reason: collision with root package name */
    public final e f1764a;

    /* renamed from: b, reason: collision with root package name */
    public final e f1765b;

    public ContentLoadingProgressBar(Context context) {
        this(context, null);
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    /* JADX WARN: Type inference failed for: r2v1, types: [androidx.core.widget.e] */
    /* JADX WARN: Type inference failed for: r2v2, types: [androidx.core.widget.e] */
    public ContentLoadingProgressBar(Context context, AttributeSet attributeSet) {
        super(context, attributeSet, 0);
        final int i7 = 0;
        this.f1764a = new Runnable(this) { // from class: androidx.core.widget.e

            /* renamed from: b, reason: collision with root package name */
            public final /* synthetic */ ContentLoadingProgressBar f1820b;

            {
                this.f1820b = this;
            }

            @Override // java.lang.Runnable
            public final void run() {
                int i8 = i7;
                ContentLoadingProgressBar contentLoadingProgressBar = this.f1820b;
                switch (i8) {
                    case 0:
                        int i9 = ContentLoadingProgressBar.f1763c;
                        contentLoadingProgressBar.setVisibility(8);
                        break;
                    default:
                        int i10 = ContentLoadingProgressBar.f1763c;
                        contentLoadingProgressBar.getClass();
                        System.currentTimeMillis();
                        contentLoadingProgressBar.setVisibility(0);
                        break;
                }
            }
        };
        final int i8 = 1;
        this.f1765b = new Runnable(this) { // from class: androidx.core.widget.e

            /* renamed from: b, reason: collision with root package name */
            public final /* synthetic */ ContentLoadingProgressBar f1820b;

            {
                this.f1820b = this;
            }

            @Override // java.lang.Runnable
            public final void run() {
                int i82 = i8;
                ContentLoadingProgressBar contentLoadingProgressBar = this.f1820b;
                switch (i82) {
                    case 0:
                        int i9 = ContentLoadingProgressBar.f1763c;
                        contentLoadingProgressBar.setVisibility(8);
                        break;
                    default:
                        int i10 = ContentLoadingProgressBar.f1763c;
                        contentLoadingProgressBar.getClass();
                        System.currentTimeMillis();
                        contentLoadingProgressBar.setVisibility(0);
                        break;
                }
            }
        };
    }

    @Override // android.widget.ProgressBar, android.view.View
    public final void onAttachedToWindow() {
        super.onAttachedToWindow();
        removeCallbacks(this.f1764a);
        removeCallbacks(this.f1765b);
    }

    @Override // android.widget.ProgressBar, android.view.View
    public final void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        removeCallbacks(this.f1764a);
        removeCallbacks(this.f1765b);
    }
}
