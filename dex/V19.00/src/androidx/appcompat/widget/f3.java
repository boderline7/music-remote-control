package androidx.appcompat.widget;

import android.graphics.Rect;
import android.view.MotionEvent;
import android.view.TouchDelegate;
import android.view.View;
import android.view.ViewConfiguration;
import androidx.appcompat.widget.SearchView;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_2_1633180.dex */
public final class f3 extends TouchDelegate {

    /* renamed from: a, reason: collision with root package name */
    public final View f1239a;

    /* renamed from: b, reason: collision with root package name */
    public final Rect f1240b;

    /* renamed from: c, reason: collision with root package name */
    public final Rect f1241c;

    /* renamed from: d, reason: collision with root package name */
    public final Rect f1242d;

    /* renamed from: e, reason: collision with root package name */
    public final int f1243e;

    /* renamed from: f, reason: collision with root package name */
    public boolean f1244f;

    public f3(Rect rect, Rect rect2, SearchView.SearchAutoComplete searchAutoComplete) {
        super(rect, searchAutoComplete);
        int scaledTouchSlop = ViewConfiguration.get(searchAutoComplete.getContext()).getScaledTouchSlop();
        this.f1243e = scaledTouchSlop;
        Rect rect3 = new Rect();
        this.f1240b = rect3;
        Rect rect4 = new Rect();
        this.f1242d = rect4;
        Rect rect5 = new Rect();
        this.f1241c = rect5;
        rect3.set(rect);
        rect4.set(rect);
        int i7 = -scaledTouchSlop;
        rect4.inset(i7, i7);
        rect5.set(rect2);
        this.f1239a = searchAutoComplete;
    }

    @Override // android.view.TouchDelegate
    public final boolean onTouchEvent(MotionEvent motionEvent) {
        boolean z6;
        boolean z7;
        int x6 = (int) motionEvent.getX();
        int y6 = (int) motionEvent.getY();
        int action = motionEvent.getAction();
        boolean z8 = true;
        if (action != 0) {
            if (action == 1 || action == 2) {
                z7 = this.f1244f;
                if (z7 && !this.f1242d.contains(x6, y6)) {
                    z8 = z7;
                    z6 = false;
                }
            } else {
                if (action == 3) {
                    z7 = this.f1244f;
                    this.f1244f = false;
                }
                z6 = true;
                z8 = false;
            }
            z8 = z7;
            z6 = true;
        } else {
            if (this.f1240b.contains(x6, y6)) {
                this.f1244f = true;
                z6 = true;
            }
            z6 = true;
            z8 = false;
        }
        if (!z8) {
            return false;
        }
        Rect rect = this.f1241c;
        View view = this.f1239a;
        if (!z6 || rect.contains(x6, y6)) {
            motionEvent.setLocation(x6 - rect.left, y6 - rect.top);
        } else {
            motionEvent.setLocation(view.getWidth() / 2, view.getHeight() / 2);
        }
        return view.dispatchTouchEvent(motionEvent);
    }
}
