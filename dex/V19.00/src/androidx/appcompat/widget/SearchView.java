package androidx.appcompat.widget;

import android.app.PendingIntent;
import android.app.SearchableInfo;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.database.Cursor;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.text.Editable;
import android.text.SpannableStringBuilder;
import android.text.TextUtils;
import android.text.style.ImageSpan;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputConnection;
import android.view.inputmethod.InputMethodManager;
import android.widget.ImageView;
import androidx.customview.view.AbsSavedState;
import java.lang.reflect.Method;
import java.util.WeakHashMap;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_2_1633180.dex */
public class SearchView extends LinearLayoutCompat implements j.d {

    /* renamed from: i0, reason: collision with root package name */
    public static final androidx.appcompat.app.g f1101i0;
    public final Rect A;
    public final int[] B;
    public final int[] C;
    public final ImageView D;
    public final Drawable E;
    public final int F;
    public final int G;
    public final Intent H;
    public final Intent I;
    public final CharSequence J;
    public b3 K;
    public View.OnFocusChangeListener L;
    public View.OnClickListener M;
    public boolean N;
    public boolean O;
    public t0.b P;
    public boolean Q;
    public CharSequence R;
    public boolean S;
    public boolean T;
    public int U;
    public boolean V;
    public String W;

    /* renamed from: a0, reason: collision with root package name */
    public CharSequence f1102a0;

    /* renamed from: b0, reason: collision with root package name */
    public boolean f1103b0;

    /* renamed from: c0, reason: collision with root package name */
    public int f1104c0;

    /* renamed from: d0, reason: collision with root package name */
    public SearchableInfo f1105d0;

    /* renamed from: e0, reason: collision with root package name */
    public Bundle f1106e0;

    /* renamed from: f0, reason: collision with root package name */
    public final s2 f1107f0;

    /* renamed from: g0, reason: collision with root package name */
    public final s2 f1108g0;

    /* renamed from: h0, reason: collision with root package name */
    public final WeakHashMap f1109h0;

    /* renamed from: p, reason: collision with root package name */
    public final SearchAutoComplete f1110p;

    /* renamed from: q, reason: collision with root package name */
    public final View f1111q;

    /* renamed from: r, reason: collision with root package name */
    public final View f1112r;

    /* renamed from: s, reason: collision with root package name */
    public final View f1113s;

    /* renamed from: t, reason: collision with root package name */
    public final ImageView f1114t;

    /* renamed from: u, reason: collision with root package name */
    public final ImageView f1115u;

    /* renamed from: v, reason: collision with root package name */
    public final ImageView f1116v;

    /* renamed from: w, reason: collision with root package name */
    public final ImageView f1117w;

    /* renamed from: x, reason: collision with root package name */
    public final View f1118x;

    /* renamed from: y, reason: collision with root package name */
    public f3 f1119y;

    /* renamed from: z, reason: collision with root package name */
    public final Rect f1120z;

    public static class SavedState extends AbsSavedState {
        public static final Parcelable.Creator<SavedState> CREATOR = new d3();

        /* renamed from: b, reason: collision with root package name */
        public boolean f1121b;

        public SavedState(Parcel parcel, ClassLoader classLoader) {
            super(parcel, classLoader);
            this.f1121b = ((Boolean) parcel.readValue(null)).booleanValue();
        }

        public final String toString() {
            return "SearchView.SavedState{" + Integer.toHexString(System.identityHashCode(this)) + " isIconified=" + this.f1121b + "}";
        }

        @Override // androidx.customview.view.AbsSavedState, android.os.Parcelable
        public final void writeToParcel(Parcel parcel, int i7) {
            parcel.writeParcelable(this.f1829a, i7);
            parcel.writeValue(Boolean.valueOf(this.f1121b));
        }
    }

    public static class SearchAutoComplete extends AppCompatAutoCompleteTextView {

        /* renamed from: e, reason: collision with root package name */
        public int f1122e;

        /* renamed from: f, reason: collision with root package name */
        public SearchView f1123f;

        /* renamed from: g, reason: collision with root package name */
        public boolean f1124g;

        /* renamed from: h, reason: collision with root package name */
        public final e3 f1125h;

        public SearchAutoComplete(Context context) {
            this(context, null);
        }

        public SearchAutoComplete(Context context, AttributeSet attributeSet) {
            this(context, attributeSet, f.a.autoCompleteTextViewStyle);
        }

        public SearchAutoComplete(Context context, AttributeSet attributeSet, int i7) {
            super(context, attributeSet, i7);
            this.f1125h = new e3(this);
            this.f1122e = getThreshold();
        }

        private int getSearchViewTextMinWidthDp() {
            Configuration configuration = getResources().getConfiguration();
            int i7 = configuration.screenWidthDp;
            int i8 = configuration.screenHeightDp;
            if (i7 < 960 || i8 < 720 || configuration.orientation != 2) {
                return i7 < 600 ? (i7 < 640 || i8 < 480) ? z1.c.AND_LONG : z1.c.AND_LONG_2ADDR : z1.c.AND_LONG_2ADDR;
            }
            return 256;
        }

        public final void a() {
            if (Build.VERSION.SDK_INT >= 29) {
                z2.b(this, 1);
                if (enoughToFilter()) {
                    showDropDown();
                    return;
                }
                return;
            }
            androidx.appcompat.app.g gVar = SearchView.f1101i0;
            gVar.getClass();
            androidx.appcompat.app.g.T();
            Object obj = gVar.f716d;
            if (((Method) obj) != null) {
                try {
                    ((Method) obj).invoke(this, Boolean.TRUE);
                } catch (Exception unused) {
                }
            }
        }

        @Override // android.widget.AutoCompleteTextView
        public final boolean enoughToFilter() {
            return this.f1122e <= 0 || super.enoughToFilter();
        }

        @Override // androidx.appcompat.widget.AppCompatAutoCompleteTextView, android.widget.TextView, android.view.View
        public final InputConnection onCreateInputConnection(EditorInfo editorInfo) {
            InputConnection onCreateInputConnection = super.onCreateInputConnection(editorInfo);
            if (this.f1124g) {
                e3 e3Var = this.f1125h;
                removeCallbacks(e3Var);
                post(e3Var);
            }
            return onCreateInputConnection;
        }

        @Override // android.view.View
        public final void onFinishInflate() {
            super.onFinishInflate();
            setMinWidth((int) TypedValue.applyDimension(1, getSearchViewTextMinWidthDp(), getResources().getDisplayMetrics()));
        }

        @Override // android.widget.AutoCompleteTextView, android.widget.TextView, android.view.View
        public final void onFocusChanged(boolean z6, int i7, Rect rect) {
            super.onFocusChanged(z6, i7, rect);
            SearchView searchView = this.f1123f;
            searchView.y(searchView.O);
            searchView.post(searchView.f1107f0);
            if (searchView.f1110p.hasFocus()) {
                searchView.n();
            }
        }

        @Override // android.widget.AutoCompleteTextView, android.widget.TextView, android.view.View
        public final boolean onKeyPreIme(int i7, KeyEvent keyEvent) {
            if (i7 == 4) {
                if (keyEvent.getAction() == 0 && keyEvent.getRepeatCount() == 0) {
                    KeyEvent.DispatcherState keyDispatcherState = getKeyDispatcherState();
                    if (keyDispatcherState != null) {
                        keyDispatcherState.startTracking(keyEvent, this);
                    }
                    return true;
                }
                if (keyEvent.getAction() == 1) {
                    KeyEvent.DispatcherState keyDispatcherState2 = getKeyDispatcherState();
                    if (keyDispatcherState2 != null) {
                        keyDispatcherState2.handleUpEvent(keyEvent);
                    }
                    if (keyEvent.isTracking() && !keyEvent.isCanceled()) {
                        this.f1123f.clearFocus();
                        setImeVisibility(false);
                        return true;
                    }
                }
            }
            return super.onKeyPreIme(i7, keyEvent);
        }

        @Override // android.widget.AutoCompleteTextView, android.widget.TextView, android.view.View
        public final void onWindowFocusChanged(boolean z6) {
            super.onWindowFocusChanged(z6);
            if (z6 && this.f1123f.hasFocus() && getVisibility() == 0) {
                this.f1124g = true;
                Context context = getContext();
                androidx.appcompat.app.g gVar = SearchView.f1101i0;
                if (context.getResources().getConfiguration().orientation == 2) {
                    a();
                }
            }
        }

        @Override // android.widget.AutoCompleteTextView
        public final void performCompletion() {
        }

        @Override // android.widget.AutoCompleteTextView
        public final void replaceText(CharSequence charSequence) {
        }

        public void setImeVisibility(boolean z6) {
            InputMethodManager inputMethodManager = (InputMethodManager) getContext().getSystemService("input_method");
            e3 e3Var = this.f1125h;
            if (!z6) {
                this.f1124g = false;
                removeCallbacks(e3Var);
                inputMethodManager.hideSoftInputFromWindow(getWindowToken(), 0);
            } else {
                if (!inputMethodManager.isActive(this)) {
                    this.f1124g = true;
                    return;
                }
                this.f1124g = false;
                removeCallbacks(e3Var);
                inputMethodManager.showSoftInput(this, 0);
            }
        }

        public void setSearchView(SearchView searchView) {
            this.f1123f = searchView;
        }

        @Override // android.widget.AutoCompleteTextView
        public void setThreshold(int i7) {
            super.setThreshold(i7);
            this.f1122e = i7;
        }
    }

    static {
        f1101i0 = Build.VERSION.SDK_INT < 29 ? new androidx.appcompat.app.g() : null;
    }

    public SearchView(Context context) {
        this(context, null);
    }

    public SearchView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, f.a.searchViewStyle);
    }

    public SearchView(Context context, AttributeSet attributeSet, int i7) {
        super(context, attributeSet, i7);
        this.f1120z = new Rect();
        this.A = new Rect();
        this.B = new int[2];
        this.C = new int[2];
        int i8 = 0;
        this.f1107f0 = new s2(this, i8);
        this.f1108g0 = new s2(this, 1);
        this.f1109h0 = new WeakHashMap();
        v2 v2Var = new v2(this);
        w2 w2Var = new w2(this);
        x2 x2Var = new x2(this);
        y2 y2Var = new y2(i8, this);
        y1 y1Var = new y1(1, this);
        r2 r2Var = new r2(this, 0);
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, f.j.SearchView, i7, 0);
        androidx.appcompat.app.g gVar = new androidx.appcompat.app.g(context, obtainStyledAttributes);
        p0.d1.n(this, context, f.j.SearchView, attributeSet, obtainStyledAttributes, i7);
        LayoutInflater.from(context).inflate(gVar.N(f.j.SearchView_layout, f.g.abc_search_view), (ViewGroup) this, true);
        SearchAutoComplete searchAutoComplete = (SearchAutoComplete) findViewById(f.f.search_src_text);
        this.f1110p = searchAutoComplete;
        searchAutoComplete.setSearchView(this);
        this.f1111q = findViewById(f.f.search_edit_frame);
        View findViewById = findViewById(f.f.search_plate);
        this.f1112r = findViewById;
        View findViewById2 = findViewById(f.f.submit_area);
        this.f1113s = findViewById2;
        ImageView imageView = (ImageView) findViewById(f.f.search_button);
        this.f1114t = imageView;
        ImageView imageView2 = (ImageView) findViewById(f.f.search_go_btn);
        this.f1115u = imageView2;
        ImageView imageView3 = (ImageView) findViewById(f.f.search_close_btn);
        this.f1116v = imageView3;
        ImageView imageView4 = (ImageView) findViewById(f.f.search_voice_btn);
        this.f1117w = imageView4;
        ImageView imageView5 = (ImageView) findViewById(f.f.search_mag_icon);
        this.D = imageView5;
        p0.l0.q(findViewById, gVar.I(f.j.SearchView_queryBackground));
        p0.l0.q(findViewById2, gVar.I(f.j.SearchView_submitBackground));
        imageView.setImageDrawable(gVar.I(f.j.SearchView_searchIcon));
        imageView2.setImageDrawable(gVar.I(f.j.SearchView_goIcon));
        imageView3.setImageDrawable(gVar.I(f.j.SearchView_closeIcon));
        imageView4.setImageDrawable(gVar.I(f.j.SearchView_voiceIcon));
        imageView5.setImageDrawable(gVar.I(f.j.SearchView_searchIcon));
        this.E = gVar.I(f.j.SearchView_searchHintIcon);
        x3.a(imageView, getResources().getString(f.h.abc_searchview_description_search));
        this.F = gVar.N(f.j.SearchView_suggestionRowLayout, f.g.abc_search_dropdown_item_icons_2line);
        this.G = gVar.N(f.j.SearchView_commitIcon, 0);
        imageView.setOnClickListener(v2Var);
        imageView3.setOnClickListener(v2Var);
        imageView2.setOnClickListener(v2Var);
        imageView4.setOnClickListener(v2Var);
        searchAutoComplete.setOnClickListener(v2Var);
        searchAutoComplete.addTextChangedListener(r2Var);
        searchAutoComplete.setOnEditorActionListener(x2Var);
        searchAutoComplete.setOnItemClickListener(y2Var);
        searchAutoComplete.setOnItemSelectedListener(y1Var);
        searchAutoComplete.setOnKeyListener(w2Var);
        searchAutoComplete.setOnFocusChangeListener(new t2(this));
        setIconifiedByDefault(gVar.E(f.j.SearchView_iconifiedByDefault, true));
        int H = gVar.H(f.j.SearchView_android_maxWidth, -1);
        if (H != -1) {
            setMaxWidth(H);
        }
        this.J = gVar.Q(f.j.SearchView_defaultQueryHint);
        this.R = gVar.Q(f.j.SearchView_queryHint);
        int L = gVar.L(f.j.SearchView_android_imeOptions, -1);
        if (L != -1) {
            setImeOptions(L);
        }
        int L2 = gVar.L(f.j.SearchView_android_inputType, -1);
        if (L2 != -1) {
            setInputType(L2);
        }
        setFocusable(gVar.E(f.j.SearchView_android_focusable, true));
        gVar.V();
        Intent intent = new Intent("android.speech.action.WEB_SEARCH");
        this.H = intent;
        intent.addFlags(268435456);
        intent.putExtra("android.speech.extra.LANGUAGE_MODEL", "web_search");
        Intent intent2 = new Intent("android.speech.action.RECOGNIZE_SPEECH");
        this.I = intent2;
        intent2.addFlags(268435456);
        View findViewById3 = findViewById(searchAutoComplete.getDropDownAnchor());
        this.f1118x = findViewById3;
        if (findViewById3 != null) {
            findViewById3.addOnLayoutChangeListener(new u2(0, this));
        }
        y(this.N);
        v();
    }

    private int getPreferredHeight() {
        return getContext().getResources().getDimensionPixelSize(f.d.abc_search_view_preferred_height);
    }

    private int getPreferredWidth() {
        return getContext().getResources().getDimensionPixelSize(f.d.abc_search_view_preferred_width);
    }

    private void setQuery(CharSequence charSequence) {
        SearchAutoComplete searchAutoComplete = this.f1110p;
        searchAutoComplete.setText(charSequence);
        searchAutoComplete.setSelection(TextUtils.isEmpty(charSequence) ? 0 : charSequence.length());
    }

    @Override // j.d
    public final void b() {
        if (this.f1103b0) {
            return;
        }
        this.f1103b0 = true;
        SearchAutoComplete searchAutoComplete = this.f1110p;
        int imeOptions = searchAutoComplete.getImeOptions();
        this.f1104c0 = imeOptions;
        searchAutoComplete.setImeOptions(imeOptions | 33554432);
        searchAutoComplete.setText(s5.h0.FRAGMENT_ENCODE_SET);
        setIconified(false);
    }

    @Override // android.view.ViewGroup, android.view.View
    public final void clearFocus() {
        this.T = true;
        super.clearFocus();
        SearchAutoComplete searchAutoComplete = this.f1110p;
        searchAutoComplete.clearFocus();
        searchAutoComplete.setImeVisibility(false);
        this.T = false;
    }

    @Override // j.d
    public final void e() {
        setQuery(s5.h0.FRAGMENT_ENCODE_SET, false);
        clearFocus();
        y(true);
        this.f1110p.setImeOptions(this.f1104c0);
        this.f1103b0 = false;
    }

    public int getImeOptions() {
        return this.f1110p.getImeOptions();
    }

    public int getInputType() {
        return this.f1110p.getInputType();
    }

    public int getMaxWidth() {
        return this.U;
    }

    public CharSequence getQuery() {
        return this.f1110p.getText();
    }

    public CharSequence getQueryHint() {
        CharSequence charSequence = this.R;
        if (charSequence != null) {
            return charSequence;
        }
        SearchableInfo searchableInfo = this.f1105d0;
        return (searchableInfo == null || searchableInfo.getHintId() == 0) ? this.J : getContext().getText(this.f1105d0.getHintId());
    }

    public int getSuggestionCommitIconResId() {
        return this.G;
    }

    public int getSuggestionRowLayout() {
        return this.F;
    }

    public t0.b getSuggestionsAdapter() {
        return this.P;
    }

    public final Intent l(String str, Uri uri, String str2, String str3) {
        Intent intent = new Intent(str);
        intent.addFlags(268435456);
        if (uri != null) {
            intent.setData(uri);
        }
        intent.putExtra("user_query", this.f1102a0);
        if (str3 != null) {
            intent.putExtra("query", str3);
        }
        if (str2 != null) {
            intent.putExtra("intent_extra_data_key", str2);
        }
        Bundle bundle = this.f1106e0;
        if (bundle != null) {
            intent.putExtra("app_data", bundle);
        }
        intent.setComponent(this.f1105d0.getSearchActivity());
        return intent;
    }

    public final Intent m(Intent intent, SearchableInfo searchableInfo) {
        ComponentName searchActivity = searchableInfo.getSearchActivity();
        Intent intent2 = new Intent("android.intent.action.SEARCH");
        intent2.setComponent(searchActivity);
        PendingIntent activity = PendingIntent.getActivity(getContext(), 0, intent2, 1107296256);
        Bundle bundle = new Bundle();
        Bundle bundle2 = this.f1106e0;
        if (bundle2 != null) {
            bundle.putParcelable("app_data", bundle2);
        }
        Intent intent3 = new Intent(intent);
        Resources resources = getResources();
        String string = searchableInfo.getVoiceLanguageModeId() != 0 ? resources.getString(searchableInfo.getVoiceLanguageModeId()) : "free_form";
        String string2 = searchableInfo.getVoicePromptTextId() != 0 ? resources.getString(searchableInfo.getVoicePromptTextId()) : null;
        String string3 = searchableInfo.getVoiceLanguageId() != 0 ? resources.getString(searchableInfo.getVoiceLanguageId()) : null;
        int voiceMaxResults = searchableInfo.getVoiceMaxResults() != 0 ? searchableInfo.getVoiceMaxResults() : 1;
        intent3.putExtra("android.speech.extra.LANGUAGE_MODEL", string);
        intent3.putExtra("android.speech.extra.PROMPT", string2);
        intent3.putExtra("android.speech.extra.LANGUAGE", string3);
        intent3.putExtra("android.speech.extra.MAX_RESULTS", voiceMaxResults);
        intent3.putExtra("calling_package", searchActivity != null ? searchActivity.flattenToShortString() : null);
        intent3.putExtra("android.speech.extra.RESULTS_PENDINGINTENT", activity);
        intent3.putExtra("android.speech.extra.RESULTS_PENDINGINTENT_BUNDLE", bundle);
        return intent3;
    }

    public final void n() {
        int i7 = Build.VERSION.SDK_INT;
        SearchAutoComplete searchAutoComplete = this.f1110p;
        if (i7 >= 29) {
            z2.a(searchAutoComplete);
            return;
        }
        androidx.appcompat.app.g gVar = f1101i0;
        gVar.getClass();
        androidx.appcompat.app.g.T();
        Object obj = gVar.f714b;
        if (((Method) obj) != null) {
            try {
                ((Method) obj).invoke(searchAutoComplete, new Object[0]);
            } catch (Exception unused) {
            }
        }
        gVar.getClass();
        androidx.appcompat.app.g.T();
        Object obj2 = gVar.f715c;
        if (((Method) obj2) != null) {
            try {
                ((Method) obj2).invoke(searchAutoComplete, new Object[0]);
            } catch (Exception unused2) {
            }
        }
    }

    public final void o() {
        SearchAutoComplete searchAutoComplete = this.f1110p;
        if (!TextUtils.isEmpty(searchAutoComplete.getText())) {
            searchAutoComplete.setText(s5.h0.FRAGMENT_ENCODE_SET);
            searchAutoComplete.requestFocus();
            searchAutoComplete.setImeVisibility(true);
        } else if (this.N) {
            clearFocus();
            y(true);
        }
    }

    @Override // android.view.ViewGroup, android.view.View
    public final void onDetachedFromWindow() {
        removeCallbacks(this.f1107f0);
        post(this.f1108g0);
        super.onDetachedFromWindow();
    }

    @Override // androidx.appcompat.widget.LinearLayoutCompat, android.view.ViewGroup, android.view.View
    public final void onLayout(boolean z6, int i7, int i8, int i9, int i10) {
        super.onLayout(z6, i7, i8, i9, i10);
        if (z6) {
            int[] iArr = this.B;
            SearchAutoComplete searchAutoComplete = this.f1110p;
            searchAutoComplete.getLocationInWindow(iArr);
            int[] iArr2 = this.C;
            getLocationInWindow(iArr2);
            int i11 = iArr[1] - iArr2[1];
            int i12 = iArr[0] - iArr2[0];
            int width = searchAutoComplete.getWidth() + i12;
            int height = searchAutoComplete.getHeight() + i11;
            Rect rect = this.f1120z;
            rect.set(i12, i11, width, height);
            int i13 = rect.left;
            int i14 = rect.right;
            int i15 = i10 - i8;
            Rect rect2 = this.A;
            rect2.set(i13, 0, i14, i15);
            f3 f3Var = this.f1119y;
            if (f3Var == null) {
                f3 f3Var2 = new f3(rect2, rect, searchAutoComplete);
                this.f1119y = f3Var2;
                setTouchDelegate(f3Var2);
            } else {
                f3Var.f1240b.set(rect2);
                Rect rect3 = f3Var.f1242d;
                rect3.set(rect2);
                int i16 = -f3Var.f1243e;
                rect3.inset(i16, i16);
                f3Var.f1241c.set(rect);
            }
        }
    }

    @Override // androidx.appcompat.widget.LinearLayoutCompat, android.view.View
    public final void onMeasure(int i7, int i8) {
        int i9;
        if (this.O) {
            super.onMeasure(i7, i8);
            return;
        }
        int mode = View.MeasureSpec.getMode(i7);
        int size = View.MeasureSpec.getSize(i7);
        if (mode == Integer.MIN_VALUE) {
            int i10 = this.U;
            size = i10 > 0 ? Math.min(i10, size) : Math.min(getPreferredWidth(), size);
        } else if (mode == 0) {
            size = this.U;
            if (size <= 0) {
                size = getPreferredWidth();
            }
        } else if (mode == 1073741824 && (i9 = this.U) > 0) {
            size = Math.min(i9, size);
        }
        int mode2 = View.MeasureSpec.getMode(i8);
        int size2 = View.MeasureSpec.getSize(i8);
        if (mode2 == Integer.MIN_VALUE) {
            size2 = Math.min(getPreferredHeight(), size2);
        } else if (mode2 == 0) {
            size2 = getPreferredHeight();
        }
        super.onMeasure(View.MeasureSpec.makeMeasureSpec(size, v.c.EXACTLY), View.MeasureSpec.makeMeasureSpec(size2, v.c.EXACTLY));
    }

    @Override // android.view.View
    public final void onRestoreInstanceState(Parcelable parcelable) {
        if (!(parcelable instanceof SavedState)) {
            super.onRestoreInstanceState(parcelable);
            return;
        }
        SavedState savedState = (SavedState) parcelable;
        super.onRestoreInstanceState(savedState.f1829a);
        y(savedState.f1121b);
        requestLayout();
    }

    @Override // android.view.View
    public final Parcelable onSaveInstanceState() {
        SavedState savedState = new SavedState(super.onSaveInstanceState());
        savedState.f1121b = this.O;
        return savedState;
    }

    @Override // android.view.View
    public final void onWindowFocusChanged(boolean z6) {
        super.onWindowFocusChanged(z6);
        post(this.f1107f0);
    }

    public final void p(int i7) {
        String h7;
        Cursor cursor = this.P.f9016c;
        if (cursor != null && cursor.moveToPosition(i7)) {
            Intent intent = null;
            try {
                try {
                    int i8 = h3.f1271x;
                    String h8 = h3.h(cursor, cursor.getColumnIndex("suggest_intent_action"));
                    if (h8 == null) {
                        h8 = this.f1105d0.getSuggestIntentAction();
                    }
                    if (h8 == null) {
                        h8 = "android.intent.action.SEARCH";
                    }
                    String h9 = h3.h(cursor, cursor.getColumnIndex("suggest_intent_data"));
                    if (h9 == null) {
                        h9 = this.f1105d0.getSuggestIntentData();
                    }
                    if (h9 != null && (h7 = h3.h(cursor, cursor.getColumnIndex("suggest_intent_data_id"))) != null) {
                        h9 = h9 + "/" + Uri.encode(h7);
                    }
                    intent = l(h8, h9 == null ? null : Uri.parse(h9), h3.h(cursor, cursor.getColumnIndex("suggest_intent_extra_data")), h3.h(cursor, cursor.getColumnIndex("suggest_intent_query")));
                } catch (RuntimeException unused) {
                }
            } catch (RuntimeException unused2) {
                cursor.getPosition();
            }
            if (intent != null) {
                try {
                    getContext().startActivity(intent);
                } catch (RuntimeException unused3) {
                    intent.toString();
                }
            }
        }
        SearchAutoComplete searchAutoComplete = this.f1110p;
        searchAutoComplete.setImeVisibility(false);
        searchAutoComplete.dismissDropDown();
    }

    public final void q(int i7) {
        Editable text = this.f1110p.getText();
        Cursor cursor = this.P.f9016c;
        if (cursor == null) {
            return;
        }
        if (!cursor.moveToPosition(i7)) {
            setQuery(text);
            return;
        }
        String c7 = this.P.c(cursor);
        if (c7 != null) {
            setQuery(c7);
        } else {
            setQuery(text);
        }
    }

    public final void r(CharSequence charSequence) {
        setQuery(charSequence);
    }

    @Override // android.view.ViewGroup, android.view.View
    public final boolean requestFocus(int i7, Rect rect) {
        if (this.T || !isFocusable()) {
            return false;
        }
        if (this.O) {
            return super.requestFocus(i7, rect);
        }
        boolean requestFocus = this.f1110p.requestFocus(i7, rect);
        if (requestFocus) {
            y(false);
        }
        return requestFocus;
    }

    public final void s() {
        SearchAutoComplete searchAutoComplete = this.f1110p;
        Editable text = searchAutoComplete.getText();
        if (text == null || TextUtils.getTrimmedLength(text) <= 0) {
            return;
        }
        b3 b3Var = this.K;
        if (b3Var == null || !b3Var.onQueryTextSubmit(text.toString())) {
            if (this.f1105d0 != null) {
                getContext().startActivity(l("android.intent.action.SEARCH", null, null, text.toString()));
            }
            searchAutoComplete.setImeVisibility(false);
            searchAutoComplete.dismissDropDown();
        }
    }

    public void setAppSearchData(Bundle bundle) {
        this.f1106e0 = bundle;
    }

    public void setIconified(boolean z6) {
        if (z6) {
            o();
            return;
        }
        y(false);
        SearchAutoComplete searchAutoComplete = this.f1110p;
        searchAutoComplete.requestFocus();
        searchAutoComplete.setImeVisibility(true);
        View.OnClickListener onClickListener = this.M;
        if (onClickListener != null) {
            onClickListener.onClick(this);
        }
    }

    public void setIconifiedByDefault(boolean z6) {
        if (this.N == z6) {
            return;
        }
        this.N = z6;
        y(z6);
        v();
    }

    public void setImeOptions(int i7) {
        this.f1110p.setImeOptions(i7);
    }

    public void setInputType(int i7) {
        this.f1110p.setInputType(i7);
    }

    public void setMaxWidth(int i7) {
        this.U = i7;
        requestLayout();
    }

    public void setOnCloseListener(a3 a3Var) {
    }

    public void setOnQueryTextFocusChangeListener(View.OnFocusChangeListener onFocusChangeListener) {
        this.L = onFocusChangeListener;
    }

    public void setOnQueryTextListener(b3 b3Var) {
        this.K = b3Var;
    }

    public void setOnSearchClickListener(View.OnClickListener onClickListener) {
        this.M = onClickListener;
    }

    public void setOnSuggestionListener(c3 c3Var) {
    }

    public void setQuery(CharSequence charSequence, boolean z6) {
        SearchAutoComplete searchAutoComplete = this.f1110p;
        searchAutoComplete.setText(charSequence);
        if (charSequence != null) {
            searchAutoComplete.setSelection(searchAutoComplete.length());
            this.f1102a0 = charSequence;
        }
        if (!z6 || TextUtils.isEmpty(charSequence)) {
            return;
        }
        s();
    }

    public void setQueryHint(CharSequence charSequence) {
        this.R = charSequence;
        v();
    }

    public void setQueryRefinementEnabled(boolean z6) {
        this.S = z6;
        t0.b bVar = this.P;
        if (bVar instanceof h3) {
            ((h3) bVar).f1280p = z6 ? 2 : 1;
        }
    }

    public void setSearchableInfo(SearchableInfo searchableInfo) {
        this.f1105d0 = searchableInfo;
        Intent intent = null;
        SearchAutoComplete searchAutoComplete = this.f1110p;
        if (searchableInfo != null) {
            searchAutoComplete.setThreshold(searchableInfo.getSuggestThreshold());
            searchAutoComplete.setImeOptions(this.f1105d0.getImeOptions());
            int inputType = this.f1105d0.getInputType();
            if ((inputType & 15) == 1) {
                inputType &= -65537;
                if (this.f1105d0.getSuggestAuthority() != null) {
                    inputType |= 589824;
                }
            }
            searchAutoComplete.setInputType(inputType);
            t0.b bVar = this.P;
            if (bVar != null) {
                bVar.b(null);
            }
            if (this.f1105d0.getSuggestAuthority() != null) {
                h3 h3Var = new h3(getContext(), this, this.f1105d0, this.f1109h0);
                this.P = h3Var;
                searchAutoComplete.setAdapter(h3Var);
                ((h3) this.P).f1280p = this.S ? 2 : 1;
            }
            v();
        }
        SearchableInfo searchableInfo2 = this.f1105d0;
        boolean z6 = false;
        if (searchableInfo2 != null && searchableInfo2.getVoiceSearchEnabled()) {
            if (this.f1105d0.getVoiceSearchLaunchWebSearch()) {
                intent = this.H;
            } else if (this.f1105d0.getVoiceSearchLaunchRecognizer()) {
                intent = this.I;
            }
            if (intent != null) {
                z6 = getContext().getPackageManager().resolveActivity(intent, 65536) != null;
            }
        }
        this.V = z6;
        if (z6) {
            searchAutoComplete.setPrivateImeOptions("nm");
        }
        y(this.O);
    }

    public void setSubmitButtonEnabled(boolean z6) {
        this.Q = z6;
        y(this.O);
    }

    public void setSuggestionsAdapter(t0.b bVar) {
        this.P = bVar;
        this.f1110p.setAdapter(bVar);
    }

    public final void t() {
        boolean z6 = true;
        boolean z7 = !TextUtils.isEmpty(this.f1110p.getText());
        if (!z7 && (!this.N || this.f1103b0)) {
            z6 = false;
        }
        int i7 = z6 ? 0 : 8;
        ImageView imageView = this.f1116v;
        imageView.setVisibility(i7);
        Drawable drawable = imageView.getDrawable();
        if (drawable != null) {
            drawable.setState(z7 ? ViewGroup.ENABLED_STATE_SET : ViewGroup.EMPTY_STATE_SET);
        }
    }

    public final void u() {
        int[] iArr = this.f1110p.hasFocus() ? ViewGroup.FOCUSED_STATE_SET : ViewGroup.EMPTY_STATE_SET;
        Drawable background = this.f1112r.getBackground();
        if (background != null) {
            background.setState(iArr);
        }
        Drawable background2 = this.f1113s.getBackground();
        if (background2 != null) {
            background2.setState(iArr);
        }
        invalidate();
    }

    public final void v() {
        Drawable drawable;
        CharSequence queryHint = getQueryHint();
        if (queryHint == null) {
            queryHint = s5.h0.FRAGMENT_ENCODE_SET;
        }
        boolean z6 = this.N;
        SearchAutoComplete searchAutoComplete = this.f1110p;
        if (z6 && (drawable = this.E) != null) {
            int textSize = (int) (searchAutoComplete.getTextSize() * 1.25d);
            drawable.setBounds(0, 0, textSize, textSize);
            SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder("   ");
            spannableStringBuilder.setSpan(new ImageSpan(drawable), 1, 2, 33);
            spannableStringBuilder.append(queryHint);
            queryHint = spannableStringBuilder;
        }
        searchAutoComplete.setHint(queryHint);
    }

    public final void w() {
        this.f1113s.setVisibility(((this.Q || this.V) && !this.O && (this.f1115u.getVisibility() == 0 || this.f1117w.getVisibility() == 0)) ? 0 : 8);
    }

    public final void x(boolean z6) {
        boolean z7 = this.Q;
        this.f1115u.setVisibility((!z7 || !(z7 || this.V) || this.O || !hasFocus() || (!z6 && this.V)) ? 8 : 0);
    }

    public final void y(boolean z6) {
        this.O = z6;
        int i7 = 8;
        int i8 = z6 ? 0 : 8;
        boolean isEmpty = TextUtils.isEmpty(this.f1110p.getText());
        this.f1114t.setVisibility(i8);
        x(!isEmpty);
        this.f1111q.setVisibility(z6 ? 8 : 0);
        ImageView imageView = this.D;
        imageView.setVisibility((imageView.getDrawable() == null || this.N) ? 8 : 0);
        t();
        if (this.V && !this.O && isEmpty) {
            this.f1115u.setVisibility(8);
            i7 = 0;
        }
        this.f1117w.setVisibility(i7);
        w();
    }
}
