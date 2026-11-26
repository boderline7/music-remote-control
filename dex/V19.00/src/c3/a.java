package c3;

import android.R;
import android.app.Dialog;
import android.graphics.Rect;
import android.graphics.RectF;
import android.os.Build;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final class a implements View.OnTouchListener {

    /* renamed from: a, reason: collision with root package name */
    public final Dialog f3045a;

    /* renamed from: b, reason: collision with root package name */
    public final int f3046b;

    /* renamed from: c, reason: collision with root package name */
    public final int f3047c;

    /* renamed from: d, reason: collision with root package name */
    public final int f3048d;

    public a(Dialog dialog, Rect rect) {
        this.f3045a = dialog;
        this.f3046b = rect.left;
        this.f3047c = rect.top;
        this.f3048d = ViewConfiguration.get(dialog.getContext()).getScaledWindowTouchSlop();
    }

    @Override // android.view.View.OnTouchListener
    public final boolean onTouch(View view, MotionEvent motionEvent) {
        View findViewById = view.findViewById(R.id.content);
        int left = findViewById.getLeft() + this.f3046b;
        int width = findViewById.getWidth() + left;
        if (new RectF(left, findViewById.getTop() + this.f3047c, width, findViewById.getHeight() + r4).contains(motionEvent.getX(), motionEvent.getY())) {
            return false;
        }
        MotionEvent obtain = MotionEvent.obtain(motionEvent);
        if (motionEvent.getAction() == 1) {
            obtain.setAction(4);
        }
        if (Build.VERSION.SDK_INT < 28) {
            obtain.setAction(0);
            int i7 = this.f3048d;
            obtain.setLocation((-i7) - 1, (-i7) - 1);
        }
        view.performClick();
        return this.f3045a.onTouchEvent(obtain);
    }
}
