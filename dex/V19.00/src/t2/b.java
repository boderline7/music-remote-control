package t2;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.content.res.XmlResourceParser;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.Xml;
import com.google.android.material.badge.BadgeState$State;
import com.google.android.material.internal.e0;
import com.yalantis.ucrop.view.CropImageView;
import java.io.IOException;
import java.util.Locale;
import l6.f;
import org.xmlpull.v1.XmlPullParserException;
import q2.e;
import q2.j;
import q2.k;
import q2.l;
import q2.m;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final class b {

    /* renamed from: a, reason: collision with root package name */
    public final BadgeState$State f9071a;

    /* renamed from: b, reason: collision with root package name */
    public final BadgeState$State f9072b;

    /* renamed from: c, reason: collision with root package name */
    public final float f9073c;

    /* renamed from: d, reason: collision with root package name */
    public final float f9074d;

    /* renamed from: e, reason: collision with root package name */
    public final float f9075e;

    /* renamed from: f, reason: collision with root package name */
    public final float f9076f;

    /* renamed from: g, reason: collision with root package name */
    public final float f9077g;

    /* renamed from: h, reason: collision with root package name */
    public final float f9078h;

    /* renamed from: i, reason: collision with root package name */
    public final int f9079i;

    /* renamed from: j, reason: collision with root package name */
    public final int f9080j;

    /* renamed from: k, reason: collision with root package name */
    public final int f9081k;

    public b(Context context, BadgeState$State badgeState$State) {
        AttributeSet attributeSet;
        int i7;
        int next;
        int i8 = a.f9057o;
        int i9 = a.f9056n;
        this.f9072b = new BadgeState$State();
        badgeState$State = badgeState$State == null ? new BadgeState$State() : badgeState$State;
        int i10 = badgeState$State.f3281a;
        if (i10 != 0) {
            try {
                XmlResourceParser xml = context.getResources().getXml(i10);
                do {
                    next = xml.next();
                    if (next == 2) {
                        break;
                    }
                } while (next != 1);
                if (next != 2) {
                    throw new XmlPullParserException("No start tag found");
                }
                if (!TextUtils.equals(xml.getName(), "badge")) {
                    throw new XmlPullParserException("Must have a <" + ((Object) "badge") + "> start tag");
                }
                AttributeSet asAttributeSet = Xml.asAttributeSet(xml);
                i7 = asAttributeSet.getStyleAttribute();
                attributeSet = asAttributeSet;
            } catch (IOException | XmlPullParserException e7) {
                Resources.NotFoundException notFoundException = new Resources.NotFoundException("Can't load badge resource ID #0x" + Integer.toHexString(i10));
                notFoundException.initCause(e7);
                throw notFoundException;
            }
        } else {
            attributeSet = null;
            i7 = 0;
        }
        int i11 = i7 == 0 ? i9 : i7;
        int[] iArr = m.Badge;
        e0.a(context, attributeSet, i8, i11);
        e0.b(context, attributeSet, iArr, i8, i11, new int[0]);
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, iArr, i8, i11);
        Resources resources = context.getResources();
        this.f9073c = obtainStyledAttributes.getDimensionPixelSize(m.Badge_badgeRadius, -1);
        this.f9079i = context.getResources().getDimensionPixelSize(e.mtrl_badge_horizontal_edge_offset);
        this.f9080j = context.getResources().getDimensionPixelSize(e.mtrl_badge_text_horizontal_edge_offset);
        this.f9074d = obtainStyledAttributes.getDimensionPixelSize(m.Badge_badgeWithTextRadius, -1);
        this.f9075e = obtainStyledAttributes.getDimension(m.Badge_badgeWidth, resources.getDimension(e.m3_badge_size));
        this.f9077g = obtainStyledAttributes.getDimension(m.Badge_badgeWithTextWidth, resources.getDimension(e.m3_badge_with_text_size));
        this.f9076f = obtainStyledAttributes.getDimension(m.Badge_badgeHeight, resources.getDimension(e.m3_badge_size));
        this.f9078h = obtainStyledAttributes.getDimension(m.Badge_badgeWithTextHeight, resources.getDimension(e.m3_badge_with_text_size));
        this.f9081k = obtainStyledAttributes.getInt(m.Badge_offsetAlignmentMode, 1);
        BadgeState$State badgeState$State2 = this.f9072b;
        int i12 = badgeState$State.f3289i;
        badgeState$State2.f3289i = i12 == -2 ? 255 : i12;
        int i13 = badgeState$State.f3291k;
        if (i13 != -2) {
            badgeState$State2.f3291k = i13;
        } else if (obtainStyledAttributes.hasValue(m.Badge_number)) {
            this.f9072b.f3291k = obtainStyledAttributes.getInt(m.Badge_number, 0);
        } else {
            this.f9072b.f3291k = -1;
        }
        String str = badgeState$State.f3290j;
        if (str != null) {
            this.f9072b.f3290j = str;
        } else if (obtainStyledAttributes.hasValue(m.Badge_badgeText)) {
            this.f9072b.f3290j = obtainStyledAttributes.getString(m.Badge_badgeText);
        }
        BadgeState$State badgeState$State3 = this.f9072b;
        badgeState$State3.f3295o = badgeState$State.f3295o;
        CharSequence charSequence = badgeState$State.f3296p;
        badgeState$State3.f3296p = charSequence == null ? context.getString(k.mtrl_badge_numberless_content_description) : charSequence;
        BadgeState$State badgeState$State4 = this.f9072b;
        int i14 = badgeState$State.f3297q;
        badgeState$State4.f3297q = i14 == 0 ? j.mtrl_badge_content_description : i14;
        int i15 = badgeState$State.f3298r;
        badgeState$State4.f3298r = i15 == 0 ? k.mtrl_exceed_max_badge_number_content_description : i15;
        Boolean bool = badgeState$State.f3300t;
        badgeState$State4.f3300t = Boolean.valueOf(bool == null || bool.booleanValue());
        BadgeState$State badgeState$State5 = this.f9072b;
        int i16 = badgeState$State.f3292l;
        badgeState$State5.f3292l = i16 == -2 ? obtainStyledAttributes.getInt(m.Badge_maxCharacterCount, -2) : i16;
        BadgeState$State badgeState$State6 = this.f9072b;
        int i17 = badgeState$State.f3293m;
        badgeState$State6.f3293m = i17 == -2 ? obtainStyledAttributes.getInt(m.Badge_maxNumber, -2) : i17;
        BadgeState$State badgeState$State7 = this.f9072b;
        Integer num = badgeState$State.f3285e;
        badgeState$State7.f3285e = Integer.valueOf(num == null ? obtainStyledAttributes.getResourceId(m.Badge_badgeShapeAppearance, l.ShapeAppearance_M3_Sys_Shape_Corner_Full) : num.intValue());
        BadgeState$State badgeState$State8 = this.f9072b;
        Integer num2 = badgeState$State.f3286f;
        badgeState$State8.f3286f = Integer.valueOf(num2 == null ? obtainStyledAttributes.getResourceId(m.Badge_badgeShapeAppearanceOverlay, 0) : num2.intValue());
        BadgeState$State badgeState$State9 = this.f9072b;
        Integer num3 = badgeState$State.f3287g;
        badgeState$State9.f3287g = Integer.valueOf(num3 == null ? obtainStyledAttributes.getResourceId(m.Badge_badgeWithTextShapeAppearance, l.ShapeAppearance_M3_Sys_Shape_Corner_Full) : num3.intValue());
        BadgeState$State badgeState$State10 = this.f9072b;
        Integer num4 = badgeState$State.f3288h;
        badgeState$State10.f3288h = Integer.valueOf(num4 == null ? obtainStyledAttributes.getResourceId(m.Badge_badgeWithTextShapeAppearanceOverlay, 0) : num4.intValue());
        BadgeState$State badgeState$State11 = this.f9072b;
        Integer num5 = badgeState$State.f3282b;
        badgeState$State11.f3282b = Integer.valueOf(num5 == null ? f.w(context, obtainStyledAttributes, m.Badge_backgroundColor).getDefaultColor() : num5.intValue());
        BadgeState$State badgeState$State12 = this.f9072b;
        Integer num6 = badgeState$State.f3284d;
        badgeState$State12.f3284d = Integer.valueOf(num6 == null ? obtainStyledAttributes.getResourceId(m.Badge_badgeTextAppearance, l.TextAppearance_MaterialComponents_Badge) : num6.intValue());
        Integer num7 = badgeState$State.f3283c;
        if (num7 != null) {
            this.f9072b.f3283c = num7;
        } else if (obtainStyledAttributes.hasValue(m.Badge_badgeTextColor)) {
            this.f9072b.f3283c = Integer.valueOf(f.w(context, obtainStyledAttributes, m.Badge_badgeTextColor).getDefaultColor());
        } else {
            int intValue = this.f9072b.f3284d.intValue();
            TypedArray obtainStyledAttributes2 = context.obtainStyledAttributes(intValue, m.TextAppearance);
            obtainStyledAttributes2.getDimension(m.TextAppearance_android_textSize, CropImageView.DEFAULT_ASPECT_RATIO);
            ColorStateList w6 = f.w(context, obtainStyledAttributes2, m.TextAppearance_android_textColor);
            f.w(context, obtainStyledAttributes2, m.TextAppearance_android_textColorHint);
            f.w(context, obtainStyledAttributes2, m.TextAppearance_android_textColorLink);
            obtainStyledAttributes2.getInt(m.TextAppearance_android_textStyle, 0);
            obtainStyledAttributes2.getInt(m.TextAppearance_android_typeface, 1);
            int i18 = m.TextAppearance_fontFamily;
            i18 = obtainStyledAttributes2.hasValue(i18) ? i18 : m.TextAppearance_android_fontFamily;
            obtainStyledAttributes2.getResourceId(i18, 0);
            obtainStyledAttributes2.getString(i18);
            obtainStyledAttributes2.getBoolean(m.TextAppearance_textAllCaps, false);
            f.w(context, obtainStyledAttributes2, m.TextAppearance_android_shadowColor);
            obtainStyledAttributes2.getFloat(m.TextAppearance_android_shadowDx, CropImageView.DEFAULT_ASPECT_RATIO);
            obtainStyledAttributes2.getFloat(m.TextAppearance_android_shadowDy, CropImageView.DEFAULT_ASPECT_RATIO);
            obtainStyledAttributes2.getFloat(m.TextAppearance_android_shadowRadius, CropImageView.DEFAULT_ASPECT_RATIO);
            obtainStyledAttributes2.recycle();
            TypedArray obtainStyledAttributes3 = context.obtainStyledAttributes(intValue, m.MaterialTextAppearance);
            obtainStyledAttributes3.hasValue(m.MaterialTextAppearance_android_letterSpacing);
            obtainStyledAttributes3.getFloat(m.MaterialTextAppearance_android_letterSpacing, CropImageView.DEFAULT_ASPECT_RATIO);
            obtainStyledAttributes3.recycle();
            this.f9072b.f3283c = Integer.valueOf(w6.getDefaultColor());
        }
        BadgeState$State badgeState$State13 = this.f9072b;
        Integer num8 = badgeState$State.f3299s;
        badgeState$State13.f3299s = Integer.valueOf(num8 == null ? obtainStyledAttributes.getInt(m.Badge_badgeGravity, 8388661) : num8.intValue());
        BadgeState$State badgeState$State14 = this.f9072b;
        Integer num9 = badgeState$State.f3301u;
        badgeState$State14.f3301u = Integer.valueOf(num9 == null ? obtainStyledAttributes.getDimensionPixelSize(m.Badge_badgeWidePadding, resources.getDimensionPixelSize(e.mtrl_badge_long_text_horizontal_padding)) : num9.intValue());
        BadgeState$State badgeState$State15 = this.f9072b;
        Integer num10 = badgeState$State.f3302v;
        badgeState$State15.f3302v = Integer.valueOf(num10 == null ? obtainStyledAttributes.getDimensionPixelSize(m.Badge_badgeVerticalPadding, resources.getDimensionPixelSize(e.m3_badge_with_text_vertical_padding)) : num10.intValue());
        BadgeState$State badgeState$State16 = this.f9072b;
        Integer num11 = badgeState$State.f3303w;
        badgeState$State16.f3303w = Integer.valueOf(num11 == null ? obtainStyledAttributes.getDimensionPixelOffset(m.Badge_horizontalOffset, 0) : num11.intValue());
        BadgeState$State badgeState$State17 = this.f9072b;
        Integer num12 = badgeState$State.f3304x;
        badgeState$State17.f3304x = Integer.valueOf(num12 == null ? obtainStyledAttributes.getDimensionPixelOffset(m.Badge_verticalOffset, 0) : num12.intValue());
        BadgeState$State badgeState$State18 = this.f9072b;
        Integer num13 = badgeState$State.f3305y;
        badgeState$State18.f3305y = Integer.valueOf(num13 == null ? obtainStyledAttributes.getDimensionPixelOffset(m.Badge_horizontalOffsetWithText, badgeState$State18.f3303w.intValue()) : num13.intValue());
        BadgeState$State badgeState$State19 = this.f9072b;
        Integer num14 = badgeState$State.f3306z;
        badgeState$State19.f3306z = Integer.valueOf(num14 == null ? obtainStyledAttributes.getDimensionPixelOffset(m.Badge_verticalOffsetWithText, badgeState$State19.f3304x.intValue()) : num14.intValue());
        BadgeState$State badgeState$State20 = this.f9072b;
        Integer num15 = badgeState$State.C;
        badgeState$State20.C = Integer.valueOf(num15 == null ? obtainStyledAttributes.getDimensionPixelOffset(m.Badge_largeFontVerticalOffsetAdjustment, 0) : num15.intValue());
        BadgeState$State badgeState$State21 = this.f9072b;
        Integer num16 = badgeState$State.A;
        badgeState$State21.A = Integer.valueOf(num16 == null ? 0 : num16.intValue());
        BadgeState$State badgeState$State22 = this.f9072b;
        Integer num17 = badgeState$State.B;
        badgeState$State22.B = Integer.valueOf(num17 == null ? 0 : num17.intValue());
        BadgeState$State badgeState$State23 = this.f9072b;
        Boolean bool2 = badgeState$State.D;
        badgeState$State23.D = Boolean.valueOf(bool2 == null ? obtainStyledAttributes.getBoolean(m.Badge_autoAdjustToWithinGrandparentBounds, false) : bool2.booleanValue());
        obtainStyledAttributes.recycle();
        Locale locale = badgeState$State.f3294n;
        if (locale == null) {
            this.f9072b.f3294n = Locale.getDefault(Locale.Category.FORMAT);
        } else {
            this.f9072b.f3294n = locale;
        }
        this.f9071a = badgeState$State;
    }
}
