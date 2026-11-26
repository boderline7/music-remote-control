package androidx.appcompat.view.menu;

import android.R;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.TextView;
import androidx.appcompat.app.g;
import f.a;
import f.f;
import f.j;
import k.d0;
import k.q;
import p0.d1;
import p0.l0;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_2_1633180.dex */
public class ListMenuItemView extends LinearLayout implements d0, AbsListView.SelectionBoundsAdjuster {

    /* renamed from: a, reason: collision with root package name */
    public q f907a;

    /* renamed from: b, reason: collision with root package name */
    public ImageView f908b;

    /* renamed from: c, reason: collision with root package name */
    public RadioButton f909c;

    /* renamed from: d, reason: collision with root package name */
    public TextView f910d;

    /* renamed from: e, reason: collision with root package name */
    public CheckBox f911e;

    /* renamed from: f, reason: collision with root package name */
    public TextView f912f;

    /* renamed from: g, reason: collision with root package name */
    public ImageView f913g;

    /* renamed from: h, reason: collision with root package name */
    public ImageView f914h;

    /* renamed from: i, reason: collision with root package name */
    public LinearLayout f915i;

    /* renamed from: j, reason: collision with root package name */
    public final Drawable f916j;

    /* renamed from: k, reason: collision with root package name */
    public final int f917k;

    /* renamed from: l, reason: collision with root package name */
    public final Context f918l;

    /* renamed from: m, reason: collision with root package name */
    public boolean f919m;

    /* renamed from: n, reason: collision with root package name */
    public final Drawable f920n;

    /* renamed from: o, reason: collision with root package name */
    public final boolean f921o;

    /* renamed from: p, reason: collision with root package name */
    public LayoutInflater f922p;

    /* renamed from: q, reason: collision with root package name */
    public boolean f923q;

    public ListMenuItemView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, a.listMenuViewStyle);
    }

    public ListMenuItemView(Context context, AttributeSet attributeSet, int i7) {
        super(context, attributeSet);
        g S = g.S(getContext(), attributeSet, j.MenuView, i7);
        this.f916j = S.I(j.MenuView_android_itemBackground);
        this.f917k = S.N(j.MenuView_android_itemTextAppearance, -1);
        this.f919m = S.E(j.MenuView_preserveIconSpacing, false);
        this.f918l = context;
        this.f920n = S.I(j.MenuView_subMenuArrow);
        TypedArray obtainStyledAttributes = context.getTheme().obtainStyledAttributes(null, new int[]{R.attr.divider}, a.dropDownListViewStyle, 0);
        this.f921o = obtainStyledAttributes.hasValue(0);
        S.V();
        obtainStyledAttributes.recycle();
    }

    private LayoutInflater getInflater() {
        if (this.f922p == null) {
            this.f922p = LayoutInflater.from(getContext());
        }
        return this.f922p;
    }

    private void setSubMenuArrowVisible(boolean z6) {
        ImageView imageView = this.f913g;
        if (imageView != null) {
            imageView.setVisibility(z6 ? 0 : 8);
        }
    }

    @Override // android.widget.AbsListView.SelectionBoundsAdjuster
    public final void adjustListItemSelectionBounds(Rect rect) {
        ImageView imageView = this.f914h;
        if (imageView == null || imageView.getVisibility() != 0) {
            return;
        }
        LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) this.f914h.getLayoutParams();
        rect.top = this.f914h.getHeight() + layoutParams.topMargin + layoutParams.bottomMargin + rect.top;
    }

    @Override // k.d0
    public final void c(q qVar) {
        this.f907a = qVar;
        boolean z6 = false;
        setVisibility(qVar.isVisible() ? 0 : 8);
        setTitle(qVar.f7170e);
        setCheckable(qVar.isCheckable());
        if (qVar.f7179n.o()) {
            if ((qVar.f7179n.n() ? qVar.f7175j : qVar.f7173h) != 0) {
                z6 = true;
            }
        }
        setShortcut(z6, qVar.f7179n.n() ? qVar.f7175j : qVar.f7173h);
        setIcon(qVar.getIcon());
        setEnabled(qVar.isEnabled());
        setSubMenuArrowVisible(qVar.hasSubMenu());
        setContentDescription(qVar.f7182q);
    }

    @Override // k.d0
    public q getItemData() {
        return this.f907a;
    }

    @Override // android.view.View
    public final void onFinishInflate() {
        super.onFinishInflate();
        int i7 = d1.OVER_SCROLL_ALWAYS;
        l0.q(this, this.f916j);
        TextView textView = (TextView) findViewById(f.title);
        this.f910d = textView;
        int i8 = this.f917k;
        if (i8 != -1) {
            textView.setTextAppearance(this.f918l, i8);
        }
        this.f912f = (TextView) findViewById(f.shortcut);
        ImageView imageView = (ImageView) findViewById(f.submenuarrow);
        this.f913g = imageView;
        if (imageView != null) {
            imageView.setImageDrawable(this.f920n);
        }
        this.f914h = (ImageView) findViewById(f.group_divider);
        this.f915i = (LinearLayout) findViewById(f.content);
    }

    @Override // android.widget.LinearLayout, android.view.View
    public final void onMeasure(int i7, int i8) {
        if (this.f908b != null && this.f919m) {
            ViewGroup.LayoutParams layoutParams = getLayoutParams();
            LinearLayout.LayoutParams layoutParams2 = (LinearLayout.LayoutParams) this.f908b.getLayoutParams();
            int i9 = layoutParams.height;
            if (i9 > 0 && layoutParams2.width <= 0) {
                layoutParams2.width = i9;
            }
        }
        super.onMeasure(i7, i8);
    }

    public void setCheckable(boolean z6) {
        CompoundButton compoundButton;
        View view;
        if (!z6 && this.f909c == null && this.f911e == null) {
            return;
        }
        if ((this.f907a.f7189x & 4) != 0) {
            if (this.f909c == null) {
                RadioButton radioButton = (RadioButton) getInflater().inflate(f.g.abc_list_menu_item_radio, (ViewGroup) this, false);
                this.f909c = radioButton;
                LinearLayout linearLayout = this.f915i;
                if (linearLayout != null) {
                    linearLayout.addView(radioButton, -1);
                } else {
                    addView(radioButton, -1);
                }
            }
            compoundButton = this.f909c;
            view = this.f911e;
        } else {
            if (this.f911e == null) {
                CheckBox checkBox = (CheckBox) getInflater().inflate(f.g.abc_list_menu_item_checkbox, (ViewGroup) this, false);
                this.f911e = checkBox;
                LinearLayout linearLayout2 = this.f915i;
                if (linearLayout2 != null) {
                    linearLayout2.addView(checkBox, -1);
                } else {
                    addView(checkBox, -1);
                }
            }
            compoundButton = this.f911e;
            view = this.f909c;
        }
        if (z6) {
            compoundButton.setChecked(this.f907a.isChecked());
            if (compoundButton.getVisibility() != 0) {
                compoundButton.setVisibility(0);
            }
            if (view == null || view.getVisibility() == 8) {
                return;
            }
            view.setVisibility(8);
            return;
        }
        CheckBox checkBox2 = this.f911e;
        if (checkBox2 != null) {
            checkBox2.setVisibility(8);
        }
        RadioButton radioButton2 = this.f909c;
        if (radioButton2 != null) {
            radioButton2.setVisibility(8);
        }
    }

    public void setChecked(boolean z6) {
        CompoundButton compoundButton;
        if ((this.f907a.f7189x & 4) != 0) {
            if (this.f909c == null) {
                RadioButton radioButton = (RadioButton) getInflater().inflate(f.g.abc_list_menu_item_radio, (ViewGroup) this, false);
                this.f909c = radioButton;
                LinearLayout linearLayout = this.f915i;
                if (linearLayout != null) {
                    linearLayout.addView(radioButton, -1);
                } else {
                    addView(radioButton, -1);
                }
            }
            compoundButton = this.f909c;
        } else {
            if (this.f911e == null) {
                CheckBox checkBox = (CheckBox) getInflater().inflate(f.g.abc_list_menu_item_checkbox, (ViewGroup) this, false);
                this.f911e = checkBox;
                LinearLayout linearLayout2 = this.f915i;
                if (linearLayout2 != null) {
                    linearLayout2.addView(checkBox, -1);
                } else {
                    addView(checkBox, -1);
                }
            }
            compoundButton = this.f911e;
        }
        compoundButton.setChecked(z6);
    }

    public void setForceShowIcon(boolean z6) {
        this.f923q = z6;
        this.f919m = z6;
    }

    public void setGroupDividerEnabled(boolean z6) {
        ImageView imageView = this.f914h;
        if (imageView != null) {
            imageView.setVisibility((this.f921o || !z6) ? 8 : 0);
        }
    }

    public void setIcon(Drawable drawable) {
        this.f907a.f7179n.getClass();
        boolean z6 = this.f923q;
        if (z6 || this.f919m) {
            ImageView imageView = this.f908b;
            if (imageView == null && drawable == null && !this.f919m) {
                return;
            }
            if (imageView == null) {
                ImageView imageView2 = (ImageView) getInflater().inflate(f.g.abc_list_menu_item_icon, (ViewGroup) this, false);
                this.f908b = imageView2;
                LinearLayout linearLayout = this.f915i;
                if (linearLayout != null) {
                    linearLayout.addView(imageView2, 0);
                } else {
                    addView(imageView2, 0);
                }
            }
            if (drawable == null && !this.f919m) {
                this.f908b.setVisibility(8);
                return;
            }
            ImageView imageView3 = this.f908b;
            if (!z6) {
                drawable = null;
            }
            imageView3.setImageDrawable(drawable);
            if (this.f908b.getVisibility() != 0) {
                this.f908b.setVisibility(0);
            }
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:11:0x0022  */
    /* JADX WARN: Removed duplicated region for block: B:38:0x00dd  */
    /* JADX WARN: Removed duplicated region for block: B:41:? A[RETURN, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void setShortcut(boolean r8, char r9) {
        /*
            r7 = this;
            r9 = 8
            if (r8 == 0) goto L1f
            k.q r8 = r7.f907a
            k.o r0 = r8.f7179n
            boolean r0 = r0.o()
            if (r0 == 0) goto L1f
            k.o r0 = r8.f7179n
            boolean r0 = r0.n()
            if (r0 == 0) goto L19
            char r8 = r8.f7175j
            goto L1b
        L19:
            char r8 = r8.f7173h
        L1b:
            if (r8 == 0) goto L1f
            r8 = 0
            goto L20
        L1f:
            r8 = r9
        L20:
            if (r8 != 0) goto Ld5
            android.widget.TextView r0 = r7.f912f
            k.q r1 = r7.f907a
            k.o r2 = r1.f7179n
            boolean r2 = r2.n()
            if (r2 == 0) goto L31
            char r2 = r1.f7175j
            goto L33
        L31:
            char r2 = r1.f7173h
        L33:
            if (r2 != 0) goto L39
            java.lang.String r9 = ""
            goto Ld2
        L39:
            k.o r3 = r1.f7179n
            android.content.Context r4 = r3.f7139a
            android.content.res.Resources r4 = r4.getResources()
            java.lang.StringBuilder r5 = new java.lang.StringBuilder
            r5.<init>()
            android.content.Context r6 = r3.f7139a
            android.view.ViewConfiguration r6 = android.view.ViewConfiguration.get(r6)
            boolean r6 = r6.hasPermanentMenuKey()
            if (r6 == 0) goto L5b
            int r6 = f.h.abc_prepend_shortcut_label
            java.lang.String r6 = r4.getString(r6)
            r5.append(r6)
        L5b:
            boolean r3 = r3.n()
            if (r3 == 0) goto L64
            int r1 = r1.f7176k
            goto L66
        L64:
            int r1 = r1.f7174i
        L66:
            int r3 = f.h.abc_menu_meta_shortcut_label
            java.lang.String r3 = r4.getString(r3)
            r6 = 65536(0x10000, float:9.1835E-41)
            k.q.c(r1, r6, r3, r5)
            int r3 = f.h.abc_menu_ctrl_shortcut_label
            java.lang.String r3 = r4.getString(r3)
            r6 = 4096(0x1000, float:5.74E-42)
            k.q.c(r1, r6, r3, r5)
            int r3 = f.h.abc_menu_alt_shortcut_label
            java.lang.String r3 = r4.getString(r3)
            r6 = 2
            k.q.c(r1, r6, r3, r5)
            int r3 = f.h.abc_menu_shift_shortcut_label
            java.lang.String r3 = r4.getString(r3)
            r6 = 1
            k.q.c(r1, r6, r3, r5)
            int r3 = f.h.abc_menu_sym_shortcut_label
            java.lang.String r3 = r4.getString(r3)
            r6 = 4
            k.q.c(r1, r6, r3, r5)
            int r3 = f.h.abc_menu_function_shortcut_label
            java.lang.String r3 = r4.getString(r3)
            k.q.c(r1, r9, r3, r5)
            if (r2 == r9) goto Lc5
            r9 = 10
            if (r2 == r9) goto Lbb
            r9 = 32
            if (r2 == r9) goto Lb1
            r5.append(r2)
            goto Lce
        Lb1:
            int r9 = f.h.abc_menu_space_shortcut_label
            java.lang.String r9 = r4.getString(r9)
            r5.append(r9)
            goto Lce
        Lbb:
            int r9 = f.h.abc_menu_enter_shortcut_label
            java.lang.String r9 = r4.getString(r9)
            r5.append(r9)
            goto Lce
        Lc5:
            int r9 = f.h.abc_menu_delete_shortcut_label
            java.lang.String r9 = r4.getString(r9)
            r5.append(r9)
        Lce:
            java.lang.String r9 = r5.toString()
        Ld2:
            r0.setText(r9)
        Ld5:
            android.widget.TextView r9 = r7.f912f
            int r9 = r9.getVisibility()
            if (r9 == r8) goto Le2
            android.widget.TextView r9 = r7.f912f
            r9.setVisibility(r8)
        Le2:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.appcompat.view.menu.ListMenuItemView.setShortcut(boolean, char):void");
    }

    public void setTitle(CharSequence charSequence) {
        if (charSequence == null) {
            if (this.f910d.getVisibility() != 8) {
                this.f910d.setVisibility(8);
            }
        } else {
            this.f910d.setText(charSequence);
            if (this.f910d.getVisibility() != 0) {
                this.f910d.setVisibility(0);
            }
        }
    }
}
