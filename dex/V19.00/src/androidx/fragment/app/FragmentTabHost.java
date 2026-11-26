package androidx.fragment.app;

import android.R;
import android.content.Context;
import android.content.res.TypedArray;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TabHost;
import android.widget.TabWidget;
import com.yalantis.ucrop.view.CropImageView;
import java.util.ArrayList;

@Deprecated
/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_2_1633180.dex */
public class FragmentTabHost extends TabHost implements TabHost.OnTabChangeListener {

    /* renamed from: a, reason: collision with root package name */
    public final ArrayList f1904a;

    /* renamed from: b, reason: collision with root package name */
    public FrameLayout f1905b;

    /* renamed from: c, reason: collision with root package name */
    public int f1906c;

    /* renamed from: d, reason: collision with root package name */
    public TabHost.OnTabChangeListener f1907d;

    /* renamed from: e, reason: collision with root package name */
    public boolean f1908e;

    public static class SavedState extends View.BaseSavedState {
        public static final Parcelable.Creator<SavedState> CREATOR = new f1();

        /* renamed from: a, reason: collision with root package name */
        public String f1909a;

        public final String toString() {
            StringBuilder sb = new StringBuilder("FragmentTabHost.SavedState{");
            sb.append(Integer.toHexString(System.identityHashCode(this)));
            sb.append(" curTab=");
            return a.a.i(sb, this.f1909a, "}");
        }

        @Override // android.view.View.BaseSavedState, android.view.AbsSavedState, android.os.Parcelable
        public final void writeToParcel(Parcel parcel, int i7) {
            super.writeToParcel(parcel, i7);
            parcel.writeString(this.f1909a);
        }
    }

    @Deprecated
    public FragmentTabHost(Context context) {
        super(context, null);
        this.f1904a = new ArrayList();
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(null, new int[]{R.attr.inflatedId}, 0, 0);
        this.f1906c = obtainStyledAttributes.getResourceId(0, 0);
        obtainStyledAttributes.recycle();
        super.setOnTabChangedListener(this);
    }

    @Deprecated
    public FragmentTabHost(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.f1904a = new ArrayList();
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, new int[]{R.attr.inflatedId}, 0, 0);
        this.f1906c = obtainStyledAttributes.getResourceId(0, 0);
        obtainStyledAttributes.recycle();
        super.setOnTabChangedListener(this);
    }

    public final void a() {
        if (this.f1905b == null) {
            FrameLayout frameLayout = (FrameLayout) findViewById(this.f1906c);
            this.f1905b = frameLayout;
            if (frameLayout != null) {
                return;
            }
            throw new IllegalStateException("No tab content FrameLayout found for id " + this.f1906c);
        }
    }

    public final void b(Context context) {
        if (findViewById(R.id.tabs) == null) {
            LinearLayout linearLayout = new LinearLayout(context);
            linearLayout.setOrientation(1);
            addView(linearLayout, new FrameLayout.LayoutParams(-1, -1));
            TabWidget tabWidget = new TabWidget(context);
            tabWidget.setId(R.id.tabs);
            tabWidget.setOrientation(0);
            linearLayout.addView(tabWidget, new LinearLayout.LayoutParams(-1, -2, CropImageView.DEFAULT_ASPECT_RATIO));
            FrameLayout frameLayout = new FrameLayout(context);
            frameLayout.setId(R.id.tabcontent);
            linearLayout.addView(frameLayout, new LinearLayout.LayoutParams(0, 0, CropImageView.DEFAULT_ASPECT_RATIO));
            FrameLayout frameLayout2 = new FrameLayout(context);
            this.f1905b = frameLayout2;
            frameLayout2.setId(this.f1906c);
            linearLayout.addView(frameLayout2, new LinearLayout.LayoutParams(-1, 0, 1.0f));
        }
    }

    @Override // android.view.ViewGroup, android.view.View
    public final void onAttachedToWindow() {
        super.onAttachedToWindow();
        getCurrentTabTag();
        ArrayList arrayList = this.f1904a;
        if (arrayList.size() > 0) {
            a.a.u(arrayList.get(0));
            throw null;
        }
        this.f1908e = true;
        if (arrayList.size() <= 0) {
            return;
        }
        a.a.u(arrayList.get(0));
        throw null;
    }

    @Override // android.view.ViewGroup, android.view.View
    public final void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        this.f1908e = false;
    }

    @Override // android.view.View
    public final void onRestoreInstanceState(Parcelable parcelable) {
        if (!(parcelable instanceof SavedState)) {
            super.onRestoreInstanceState(parcelable);
            return;
        }
        SavedState savedState = (SavedState) parcelable;
        super.onRestoreInstanceState(savedState.getSuperState());
        setCurrentTabByTag(savedState.f1909a);
    }

    @Override // android.view.View
    public final Parcelable onSaveInstanceState() {
        SavedState savedState = new SavedState(super.onSaveInstanceState());
        savedState.f1909a = getCurrentTabTag();
        return savedState;
    }

    @Override // android.widget.TabHost.OnTabChangeListener
    public final void onTabChanged(String str) {
        if (this.f1908e) {
            ArrayList arrayList = this.f1904a;
            if (arrayList.size() > 0) {
                a.a.u(arrayList.get(0));
                throw null;
            }
        }
        TabHost.OnTabChangeListener onTabChangeListener = this.f1907d;
        if (onTabChangeListener != null) {
            onTabChangeListener.onTabChanged(str);
        }
    }

    @Override // android.widget.TabHost
    @Deprecated
    public void setOnTabChangedListener(TabHost.OnTabChangeListener onTabChangeListener) {
        this.f1907d = onTabChangeListener;
    }

    @Override // android.widget.TabHost
    @Deprecated
    public void setup() {
        throw new IllegalStateException("Must call setup() that takes a Context and FragmentManager");
    }

    @Deprecated
    public void setup(Context context, x0 x0Var) {
        b(context);
        super.setup();
        a();
    }

    @Deprecated
    public void setup(Context context, x0 x0Var, int i7) {
        b(context);
        super.setup();
        this.f1906c = i7;
        a();
        this.f1905b.setId(i7);
        if (getId() == -1) {
            setId(R.id.tabhost);
        }
    }
}
