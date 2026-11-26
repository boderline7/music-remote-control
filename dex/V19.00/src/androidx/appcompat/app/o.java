package androidx.appcompat.app;

import android.R;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.TypedValue;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.TextView;
import androidx.appcompat.widget.w1;
import androidx.core.widget.NestedScrollView;
import com.yalantis.ucrop.view.CropImageView;
import p0.d1;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_2_1633180.dex */
public final class o extends l0 implements DialogInterface {

    /* renamed from: f, reason: collision with root package name */
    public final m f840f;

    public o(Context context, int i7) {
        super(context, f(context, i7));
        this.f840f = new m(getContext(), this, getWindow());
    }

    public static int f(Context context, int i7) {
        if (((i7 >>> 24) & 255) >= 1) {
            return i7;
        }
        TypedValue typedValue = new TypedValue();
        context.getTheme().resolveAttribute(f.a.alertDialogTheme, typedValue, true);
        return typedValue.resourceId;
    }

    public final Button e(int i7) {
        m mVar = this.f840f;
        if (i7 == -3) {
            return mVar.f817s;
        }
        if (i7 == -2) {
            return mVar.f813o;
        }
        if (i7 == -1) {
            return mVar.f809k;
        }
        mVar.getClass();
        return null;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // androidx.appcompat.app.l0, androidx.activity.q, android.app.Dialog
    public final void onCreate(Bundle bundle) {
        int i7;
        ListAdapter listAdapter;
        View findViewById;
        super.onCreate(bundle);
        m mVar = this.f840f;
        mVar.f800b.setContentView(mVar.F);
        int i8 = f.f.parentPanel;
        Window window = mVar.f801c;
        View findViewById2 = window.findViewById(i8);
        View findViewById3 = findViewById2.findViewById(f.f.topPanel);
        View findViewById4 = findViewById2.findViewById(f.f.contentPanel);
        View findViewById5 = findViewById2.findViewById(f.f.buttonPanel);
        ViewGroup viewGroup = (ViewGroup) findViewById2.findViewById(f.f.customPanel);
        View view = mVar.f806h;
        Context context = mVar.f799a;
        if (view == null) {
            view = mVar.f807i != 0 ? LayoutInflater.from(context).inflate(mVar.f807i, viewGroup, false) : null;
        }
        boolean z6 = view != null;
        if (!z6 || !m.a(view)) {
            window.setFlags(131072, 131072);
        }
        if (z6) {
            FrameLayout frameLayout = (FrameLayout) window.findViewById(f.f.custom);
            frameLayout.addView(view, new ViewGroup.LayoutParams(-1, -1));
            if (mVar.f808j) {
                frameLayout.setPadding(0, 0, 0, 0);
            }
            if (mVar.f805g != null) {
                ((LinearLayout.LayoutParams) ((w1) viewGroup.getLayoutParams())).weight = CropImageView.DEFAULT_ASPECT_RATIO;
            }
        } else {
            viewGroup.setVisibility(8);
        }
        View findViewById6 = viewGroup.findViewById(f.f.topPanel);
        View findViewById7 = viewGroup.findViewById(f.f.contentPanel);
        View findViewById8 = viewGroup.findViewById(f.f.buttonPanel);
        ViewGroup c7 = m.c(findViewById6, findViewById3);
        ViewGroup c8 = m.c(findViewById7, findViewById4);
        ViewGroup c9 = m.c(findViewById8, findViewById5);
        NestedScrollView nestedScrollView = (NestedScrollView) window.findViewById(f.f.scrollView);
        mVar.f821w = nestedScrollView;
        nestedScrollView.setFocusable(false);
        mVar.f821w.setNestedScrollingEnabled(false);
        TextView textView = (TextView) c8.findViewById(R.id.message);
        mVar.B = textView;
        if (textView != null) {
            CharSequence charSequence = mVar.f804f;
            if (charSequence != null) {
                textView.setText(charSequence);
            } else {
                textView.setVisibility(8);
                mVar.f821w.removeView(mVar.B);
                if (mVar.f805g != null) {
                    ViewGroup viewGroup2 = (ViewGroup) mVar.f821w.getParent();
                    int indexOfChild = viewGroup2.indexOfChild(mVar.f821w);
                    viewGroup2.removeViewAt(indexOfChild);
                    viewGroup2.addView(mVar.f805g, indexOfChild, new ViewGroup.LayoutParams(-1, -1));
                } else {
                    c8.setVisibility(8);
                }
            }
        }
        Button button = (Button) c9.findViewById(R.id.button1);
        mVar.f809k = button;
        c cVar = mVar.L;
        button.setOnClickListener(cVar);
        boolean isEmpty = TextUtils.isEmpty(mVar.f810l);
        int i9 = mVar.f802d;
        if (isEmpty && mVar.f812n == null) {
            mVar.f809k.setVisibility(8);
            i7 = 0;
        } else {
            mVar.f809k.setText(mVar.f810l);
            Drawable drawable = mVar.f812n;
            if (drawable != null) {
                drawable.setBounds(0, 0, i9, i9);
                mVar.f809k.setCompoundDrawables(mVar.f812n, null, null, null);
            }
            mVar.f809k.setVisibility(0);
            i7 = 1;
        }
        Button button2 = (Button) c9.findViewById(R.id.button2);
        mVar.f813o = button2;
        button2.setOnClickListener(cVar);
        if (TextUtils.isEmpty(mVar.f814p) && mVar.f816r == null) {
            mVar.f813o.setVisibility(8);
        } else {
            mVar.f813o.setText(mVar.f814p);
            Drawable drawable2 = mVar.f816r;
            if (drawable2 != null) {
                drawable2.setBounds(0, 0, i9, i9);
                mVar.f813o.setCompoundDrawables(mVar.f816r, null, null, null);
            }
            mVar.f813o.setVisibility(0);
            i7 |= 2;
        }
        Button button3 = (Button) c9.findViewById(R.id.button3);
        mVar.f817s = button3;
        button3.setOnClickListener(cVar);
        if (TextUtils.isEmpty(mVar.f818t) && mVar.f820v == null) {
            mVar.f817s.setVisibility(8);
        } else {
            mVar.f817s.setText(mVar.f818t);
            Drawable drawable3 = mVar.f820v;
            if (drawable3 != null) {
                drawable3.setBounds(0, 0, i9, i9);
                mVar.f817s.setCompoundDrawables(mVar.f820v, null, null, null);
            }
            mVar.f817s.setVisibility(0);
            i7 |= 4;
        }
        TypedValue typedValue = new TypedValue();
        context.getTheme().resolveAttribute(f.a.alertDialogCenterButtons, typedValue, true);
        if (typedValue.data != 0) {
            if (i7 == 1) {
                Button button4 = mVar.f809k;
                LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) button4.getLayoutParams();
                layoutParams.gravity = 1;
                layoutParams.weight = 0.5f;
                button4.setLayoutParams(layoutParams);
            } else if (i7 == 2) {
                Button button5 = mVar.f813o;
                LinearLayout.LayoutParams layoutParams2 = (LinearLayout.LayoutParams) button5.getLayoutParams();
                layoutParams2.gravity = 1;
                layoutParams2.weight = 0.5f;
                button5.setLayoutParams(layoutParams2);
            } else if (i7 == 4) {
                Button button6 = mVar.f817s;
                LinearLayout.LayoutParams layoutParams3 = (LinearLayout.LayoutParams) button6.getLayoutParams();
                layoutParams3.gravity = 1;
                layoutParams3.weight = 0.5f;
                button6.setLayoutParams(layoutParams3);
            }
        }
        if (i7 == 0) {
            c9.setVisibility(8);
        }
        if (mVar.C != null) {
            c7.addView(mVar.C, 0, new ViewGroup.LayoutParams(-1, -2));
            window.findViewById(f.f.title_template).setVisibility(8);
        } else {
            mVar.f824z = (ImageView) window.findViewById(R.id.icon);
            if ((!TextUtils.isEmpty(mVar.f803e)) && mVar.J) {
                TextView textView2 = (TextView) window.findViewById(f.f.alertTitle);
                mVar.A = textView2;
                textView2.setText(mVar.f803e);
                int i10 = mVar.f822x;
                if (i10 != 0) {
                    mVar.f824z.setImageResource(i10);
                } else {
                    Drawable drawable4 = mVar.f823y;
                    if (drawable4 != null) {
                        mVar.f824z.setImageDrawable(drawable4);
                    } else {
                        mVar.A.setPadding(mVar.f824z.getPaddingLeft(), mVar.f824z.getPaddingTop(), mVar.f824z.getPaddingRight(), mVar.f824z.getPaddingBottom());
                        mVar.f824z.setVisibility(8);
                    }
                }
            } else {
                window.findViewById(f.f.title_template).setVisibility(8);
                mVar.f824z.setVisibility(8);
                c7.setVisibility(8);
            }
        }
        boolean z7 = viewGroup.getVisibility() != 8;
        boolean z8 = (c7 == null || c7.getVisibility() == 8) ? 0 : 1;
        boolean z9 = c9.getVisibility() != 8;
        if (!z9 && (findViewById = c8.findViewById(f.f.textSpacerNoButtons)) != null) {
            findViewById.setVisibility(0);
        }
        if (z8 != 0) {
            NestedScrollView nestedScrollView2 = mVar.f821w;
            if (nestedScrollView2 != null) {
                nestedScrollView2.setClipToPadding(true);
            }
            View findViewById9 = (mVar.f804f == null && mVar.f805g == null) ? null : c7.findViewById(f.f.titleDividerNoCustom);
            if (findViewById9 != null) {
                findViewById9.setVisibility(0);
            }
        } else {
            View findViewById10 = c8.findViewById(f.f.textSpacerNoTitle);
            if (findViewById10 != null) {
                findViewById10.setVisibility(0);
            }
        }
        AlertController$RecycleListView alertController$RecycleListView = mVar.f805g;
        if (alertController$RecycleListView instanceof AlertController$RecycleListView) {
            alertController$RecycleListView.setHasDecor(z8, z9);
        }
        if (!z7) {
            View view2 = mVar.f805g;
            if (view2 == null) {
                view2 = mVar.f821w;
            }
            if (view2 != null) {
                int i11 = z9 ? 2 : 0;
                View findViewById11 = window.findViewById(f.f.scrollIndicatorUp);
                View findViewById12 = window.findViewById(f.f.scrollIndicatorDown);
                int i12 = d1.OVER_SCROLL_ALWAYS;
                p0.s0.d(view2, z8 | i11, 3);
                if (findViewById11 != null) {
                    c8.removeView(findViewById11);
                }
                if (findViewById12 != null) {
                    c8.removeView(findViewById12);
                }
            }
        }
        AlertController$RecycleListView alertController$RecycleListView2 = mVar.f805g;
        if (alertController$RecycleListView2 == null || (listAdapter = mVar.D) == null) {
            return;
        }
        alertController$RecycleListView2.setAdapter(listAdapter);
        int i13 = mVar.E;
        if (i13 > -1) {
            alertController$RecycleListView2.setItemChecked(i13, true);
            alertController$RecycleListView2.setSelection(i13);
        }
    }

    @Override // android.app.Dialog, android.view.KeyEvent.Callback
    public final boolean onKeyDown(int i7, KeyEvent keyEvent) {
        NestedScrollView nestedScrollView = this.f840f.f821w;
        if (nestedScrollView == null || !nestedScrollView.h(keyEvent)) {
            return super.onKeyDown(i7, keyEvent);
        }
        return true;
    }

    @Override // android.app.Dialog, android.view.KeyEvent.Callback
    public final boolean onKeyUp(int i7, KeyEvent keyEvent) {
        NestedScrollView nestedScrollView = this.f840f.f821w;
        if (nestedScrollView == null || !nestedScrollView.h(keyEvent)) {
            return super.onKeyUp(i7, keyEvent);
        }
        return true;
    }

    @Override // androidx.appcompat.app.l0, android.app.Dialog
    public final void setTitle(CharSequence charSequence) {
        super.setTitle(charSequence);
        m mVar = this.f840f;
        mVar.f803e = charSequence;
        TextView textView = mVar.A;
        if (textView != null) {
            textView.setText(charSequence);
        }
    }
}
