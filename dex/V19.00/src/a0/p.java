package a0;

import android.content.Context;
import android.content.res.TypedArray;
import android.content.res.XmlResourceParser;
import android.graphics.drawable.ColorDrawable;
import android.util.AttributeSet;
import android.util.SparseIntArray;
import android.util.Xml;
import android.view.View;
import androidx.constraintlayout.motion.widget.MotionLayout;
import androidx.constraintlayout.widget.Barrier;
import androidx.constraintlayout.widget.ConstraintHelper;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.Guideline;
import com.yalantis.ucrop.view.CropImageView;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import org.xmlpull.v1.XmlPullParserException;
import s5.h0;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final class p {
    public static final int BASELINE = 5;
    public static final int BOTTOM = 4;
    public static final int CHAIN_PACKED = 2;
    public static final int CHAIN_SPREAD = 0;
    public static final int CHAIN_SPREAD_INSIDE = 1;
    public static final int CIRCLE_REFERENCE = 8;
    public static final int END = 7;
    public static final int GONE = 8;
    public static final int HORIZONTAL = 0;
    public static final int HORIZONTAL_GUIDELINE = 0;
    public static final int INVISIBLE = 4;
    public static final int LEFT = 1;
    public static final int MATCH_CONSTRAINT = 0;
    public static final int MATCH_CONSTRAINT_PERCENT = 2;
    public static final int MATCH_CONSTRAINT_SPREAD = 0;
    public static final int MATCH_CONSTRAINT_WRAP = 1;
    public static final int PARENT_ID = 0;
    public static final int RIGHT = 2;
    public static final int ROTATE_LEFT_OF_PORTRATE = 4;
    public static final int ROTATE_NONE = 0;
    public static final int ROTATE_PORTRATE_OF_LEFT = 2;
    public static final int ROTATE_PORTRATE_OF_RIGHT = 1;
    public static final int ROTATE_RIGHT_OF_PORTRATE = 3;
    public static final int START = 6;
    public static final int TOP = 3;
    public static final int UNSET = -1;
    public static final int VERTICAL = 1;
    public static final int VERTICAL_GUIDELINE = 1;
    public static final int VISIBILITY_MODE_IGNORE = 1;
    public static final int VISIBILITY_MODE_NORMAL = 0;
    public static final int VISIBLE = 0;
    public static final int WRAP_CONTENT = -2;

    /* renamed from: g, reason: collision with root package name */
    public static final int[] f166g = {0, 4, 8};

    /* renamed from: h, reason: collision with root package name */
    public static final SparseIntArray f167h;

    /* renamed from: i, reason: collision with root package name */
    public static final SparseIntArray f168i;

    /* renamed from: a, reason: collision with root package name */
    public String f169a;

    /* renamed from: b, reason: collision with root package name */
    public String f170b = h0.FRAGMENT_ENCODE_SET;

    /* renamed from: c, reason: collision with root package name */
    public int f171c = 0;

    /* renamed from: d, reason: collision with root package name */
    public final HashMap f172d = new HashMap();

    /* renamed from: e, reason: collision with root package name */
    public boolean f173e = true;

    /* renamed from: f, reason: collision with root package name */
    public final HashMap f174f = new HashMap();

    static {
        SparseIntArray sparseIntArray = new SparseIntArray();
        f167h = sparseIntArray;
        SparseIntArray sparseIntArray2 = new SparseIntArray();
        f168i = sparseIntArray2;
        sparseIntArray.append(t.Constraint_layout_constraintLeft_toLeftOf, 25);
        sparseIntArray.append(t.Constraint_layout_constraintLeft_toRightOf, 26);
        sparseIntArray.append(t.Constraint_layout_constraintRight_toLeftOf, 29);
        sparseIntArray.append(t.Constraint_layout_constraintRight_toRightOf, 30);
        sparseIntArray.append(t.Constraint_layout_constraintTop_toTopOf, 36);
        sparseIntArray.append(t.Constraint_layout_constraintTop_toBottomOf, 35);
        sparseIntArray.append(t.Constraint_layout_constraintBottom_toTopOf, 4);
        sparseIntArray.append(t.Constraint_layout_constraintBottom_toBottomOf, 3);
        sparseIntArray.append(t.Constraint_layout_constraintBaseline_toBaselineOf, 1);
        sparseIntArray.append(t.Constraint_layout_constraintBaseline_toTopOf, 91);
        sparseIntArray.append(t.Constraint_layout_constraintBaseline_toBottomOf, 92);
        sparseIntArray.append(t.Constraint_layout_editor_absoluteX, 6);
        sparseIntArray.append(t.Constraint_layout_editor_absoluteY, 7);
        sparseIntArray.append(t.Constraint_layout_constraintGuide_begin, 17);
        sparseIntArray.append(t.Constraint_layout_constraintGuide_end, 18);
        sparseIntArray.append(t.Constraint_layout_constraintGuide_percent, 19);
        sparseIntArray.append(t.Constraint_guidelineUseRtl, 99);
        sparseIntArray.append(t.Constraint_android_orientation, 27);
        sparseIntArray.append(t.Constraint_layout_constraintStart_toEndOf, 32);
        sparseIntArray.append(t.Constraint_layout_constraintStart_toStartOf, 33);
        sparseIntArray.append(t.Constraint_layout_constraintEnd_toStartOf, 10);
        sparseIntArray.append(t.Constraint_layout_constraintEnd_toEndOf, 9);
        sparseIntArray.append(t.Constraint_layout_goneMarginLeft, 13);
        sparseIntArray.append(t.Constraint_layout_goneMarginTop, 16);
        sparseIntArray.append(t.Constraint_layout_goneMarginRight, 14);
        sparseIntArray.append(t.Constraint_layout_goneMarginBottom, 11);
        sparseIntArray.append(t.Constraint_layout_goneMarginStart, 15);
        sparseIntArray.append(t.Constraint_layout_goneMarginEnd, 12);
        sparseIntArray.append(t.Constraint_layout_constraintVertical_weight, 40);
        sparseIntArray.append(t.Constraint_layout_constraintHorizontal_weight, 39);
        sparseIntArray.append(t.Constraint_layout_constraintHorizontal_chainStyle, 41);
        sparseIntArray.append(t.Constraint_layout_constraintVertical_chainStyle, 42);
        sparseIntArray.append(t.Constraint_layout_constraintHorizontal_bias, 20);
        sparseIntArray.append(t.Constraint_layout_constraintVertical_bias, 37);
        sparseIntArray.append(t.Constraint_layout_constraintDimensionRatio, 5);
        sparseIntArray.append(t.Constraint_layout_constraintLeft_creator, 87);
        sparseIntArray.append(t.Constraint_layout_constraintTop_creator, 87);
        sparseIntArray.append(t.Constraint_layout_constraintRight_creator, 87);
        sparseIntArray.append(t.Constraint_layout_constraintBottom_creator, 87);
        sparseIntArray.append(t.Constraint_layout_constraintBaseline_creator, 87);
        sparseIntArray.append(t.Constraint_android_layout_marginLeft, 24);
        sparseIntArray.append(t.Constraint_android_layout_marginRight, 28);
        sparseIntArray.append(t.Constraint_android_layout_marginStart, 31);
        sparseIntArray.append(t.Constraint_android_layout_marginEnd, 8);
        sparseIntArray.append(t.Constraint_android_layout_marginTop, 34);
        sparseIntArray.append(t.Constraint_android_layout_marginBottom, 2);
        sparseIntArray.append(t.Constraint_android_layout_width, 23);
        sparseIntArray.append(t.Constraint_android_layout_height, 21);
        sparseIntArray.append(t.Constraint_layout_constraintWidth, 95);
        sparseIntArray.append(t.Constraint_layout_constraintHeight, 96);
        sparseIntArray.append(t.Constraint_android_visibility, 22);
        sparseIntArray.append(t.Constraint_android_alpha, 43);
        sparseIntArray.append(t.Constraint_android_elevation, 44);
        sparseIntArray.append(t.Constraint_android_rotationX, 45);
        sparseIntArray.append(t.Constraint_android_rotationY, 46);
        sparseIntArray.append(t.Constraint_android_rotation, 60);
        sparseIntArray.append(t.Constraint_android_scaleX, 47);
        sparseIntArray.append(t.Constraint_android_scaleY, 48);
        sparseIntArray.append(t.Constraint_android_transformPivotX, 49);
        sparseIntArray.append(t.Constraint_android_transformPivotY, 50);
        sparseIntArray.append(t.Constraint_android_translationX, 51);
        sparseIntArray.append(t.Constraint_android_translationY, 52);
        sparseIntArray.append(t.Constraint_android_translationZ, 53);
        sparseIntArray.append(t.Constraint_layout_constraintWidth_default, 54);
        sparseIntArray.append(t.Constraint_layout_constraintHeight_default, 55);
        sparseIntArray.append(t.Constraint_layout_constraintWidth_max, 56);
        sparseIntArray.append(t.Constraint_layout_constraintHeight_max, 57);
        sparseIntArray.append(t.Constraint_layout_constraintWidth_min, 58);
        sparseIntArray.append(t.Constraint_layout_constraintHeight_min, 59);
        sparseIntArray.append(t.Constraint_layout_constraintCircle, 61);
        sparseIntArray.append(t.Constraint_layout_constraintCircleRadius, 62);
        sparseIntArray.append(t.Constraint_layout_constraintCircleAngle, 63);
        sparseIntArray.append(t.Constraint_animateRelativeTo, 64);
        sparseIntArray.append(t.Constraint_transitionEasing, 65);
        sparseIntArray.append(t.Constraint_drawPath, 66);
        sparseIntArray.append(t.Constraint_transitionPathRotate, 67);
        sparseIntArray.append(t.Constraint_motionStagger, 79);
        sparseIntArray.append(t.Constraint_android_id, 38);
        sparseIntArray.append(t.Constraint_motionProgress, 68);
        sparseIntArray.append(t.Constraint_layout_constraintWidth_percent, 69);
        sparseIntArray.append(t.Constraint_layout_constraintHeight_percent, 70);
        sparseIntArray.append(t.Constraint_layout_wrapBehaviorInParent, 97);
        sparseIntArray.append(t.Constraint_chainUseRtl, 71);
        sparseIntArray.append(t.Constraint_barrierDirection, 72);
        sparseIntArray.append(t.Constraint_barrierMargin, 73);
        sparseIntArray.append(t.Constraint_constraint_referenced_ids, 74);
        sparseIntArray.append(t.Constraint_barrierAllowsGoneWidgets, 75);
        sparseIntArray.append(t.Constraint_pathMotionArc, 76);
        sparseIntArray.append(t.Constraint_layout_constraintTag, 77);
        sparseIntArray.append(t.Constraint_visibilityMode, 78);
        sparseIntArray.append(t.Constraint_layout_constrainedWidth, 80);
        sparseIntArray.append(t.Constraint_layout_constrainedHeight, 81);
        sparseIntArray.append(t.Constraint_polarRelativeTo, 82);
        sparseIntArray.append(t.Constraint_transformPivotTarget, 83);
        sparseIntArray.append(t.Constraint_quantizeMotionSteps, 84);
        sparseIntArray.append(t.Constraint_quantizeMotionPhase, 85);
        sparseIntArray.append(t.Constraint_quantizeMotionInterpolator, 86);
        sparseIntArray2.append(t.ConstraintOverride_layout_editor_absoluteY, 6);
        sparseIntArray2.append(t.ConstraintOverride_layout_editor_absoluteY, 7);
        sparseIntArray2.append(t.ConstraintOverride_android_orientation, 27);
        sparseIntArray2.append(t.ConstraintOverride_layout_goneMarginLeft, 13);
        sparseIntArray2.append(t.ConstraintOverride_layout_goneMarginTop, 16);
        sparseIntArray2.append(t.ConstraintOverride_layout_goneMarginRight, 14);
        sparseIntArray2.append(t.ConstraintOverride_layout_goneMarginBottom, 11);
        sparseIntArray2.append(t.ConstraintOverride_layout_goneMarginStart, 15);
        sparseIntArray2.append(t.ConstraintOverride_layout_goneMarginEnd, 12);
        sparseIntArray2.append(t.ConstraintOverride_layout_constraintVertical_weight, 40);
        sparseIntArray2.append(t.ConstraintOverride_layout_constraintHorizontal_weight, 39);
        sparseIntArray2.append(t.ConstraintOverride_layout_constraintHorizontal_chainStyle, 41);
        sparseIntArray2.append(t.ConstraintOverride_layout_constraintVertical_chainStyle, 42);
        sparseIntArray2.append(t.ConstraintOverride_layout_constraintHorizontal_bias, 20);
        sparseIntArray2.append(t.ConstraintOverride_layout_constraintVertical_bias, 37);
        sparseIntArray2.append(t.ConstraintOverride_layout_constraintDimensionRatio, 5);
        sparseIntArray2.append(t.ConstraintOverride_layout_constraintLeft_creator, 87);
        sparseIntArray2.append(t.ConstraintOverride_layout_constraintTop_creator, 87);
        sparseIntArray2.append(t.ConstraintOverride_layout_constraintRight_creator, 87);
        sparseIntArray2.append(t.ConstraintOverride_layout_constraintBottom_creator, 87);
        sparseIntArray2.append(t.ConstraintOverride_layout_constraintBaseline_creator, 87);
        sparseIntArray2.append(t.ConstraintOverride_android_layout_marginLeft, 24);
        sparseIntArray2.append(t.ConstraintOverride_android_layout_marginRight, 28);
        sparseIntArray2.append(t.ConstraintOverride_android_layout_marginStart, 31);
        sparseIntArray2.append(t.ConstraintOverride_android_layout_marginEnd, 8);
        sparseIntArray2.append(t.ConstraintOverride_android_layout_marginTop, 34);
        sparseIntArray2.append(t.ConstraintOverride_android_layout_marginBottom, 2);
        sparseIntArray2.append(t.ConstraintOverride_android_layout_width, 23);
        sparseIntArray2.append(t.ConstraintOverride_android_layout_height, 21);
        sparseIntArray2.append(t.ConstraintOverride_layout_constraintWidth, 95);
        sparseIntArray2.append(t.ConstraintOverride_layout_constraintHeight, 96);
        sparseIntArray2.append(t.ConstraintOverride_android_visibility, 22);
        sparseIntArray2.append(t.ConstraintOverride_android_alpha, 43);
        sparseIntArray2.append(t.ConstraintOverride_android_elevation, 44);
        sparseIntArray2.append(t.ConstraintOverride_android_rotationX, 45);
        sparseIntArray2.append(t.ConstraintOverride_android_rotationY, 46);
        sparseIntArray2.append(t.ConstraintOverride_android_rotation, 60);
        sparseIntArray2.append(t.ConstraintOverride_android_scaleX, 47);
        sparseIntArray2.append(t.ConstraintOverride_android_scaleY, 48);
        sparseIntArray2.append(t.ConstraintOverride_android_transformPivotX, 49);
        sparseIntArray2.append(t.ConstraintOverride_android_transformPivotY, 50);
        sparseIntArray2.append(t.ConstraintOverride_android_translationX, 51);
        sparseIntArray2.append(t.ConstraintOverride_android_translationY, 52);
        sparseIntArray2.append(t.ConstraintOverride_android_translationZ, 53);
        sparseIntArray2.append(t.ConstraintOverride_layout_constraintWidth_default, 54);
        sparseIntArray2.append(t.ConstraintOverride_layout_constraintHeight_default, 55);
        sparseIntArray2.append(t.ConstraintOverride_layout_constraintWidth_max, 56);
        sparseIntArray2.append(t.ConstraintOverride_layout_constraintHeight_max, 57);
        sparseIntArray2.append(t.ConstraintOverride_layout_constraintWidth_min, 58);
        sparseIntArray2.append(t.ConstraintOverride_layout_constraintHeight_min, 59);
        sparseIntArray2.append(t.ConstraintOverride_layout_constraintCircleRadius, 62);
        sparseIntArray2.append(t.ConstraintOverride_layout_constraintCircleAngle, 63);
        sparseIntArray2.append(t.ConstraintOverride_animateRelativeTo, 64);
        sparseIntArray2.append(t.ConstraintOverride_transitionEasing, 65);
        sparseIntArray2.append(t.ConstraintOverride_drawPath, 66);
        sparseIntArray2.append(t.ConstraintOverride_transitionPathRotate, 67);
        sparseIntArray2.append(t.ConstraintOverride_motionStagger, 79);
        sparseIntArray2.append(t.ConstraintOverride_android_id, 38);
        sparseIntArray2.append(t.ConstraintOverride_motionTarget, 98);
        sparseIntArray2.append(t.ConstraintOverride_motionProgress, 68);
        sparseIntArray2.append(t.ConstraintOverride_layout_constraintWidth_percent, 69);
        sparseIntArray2.append(t.ConstraintOverride_layout_constraintHeight_percent, 70);
        sparseIntArray2.append(t.ConstraintOverride_chainUseRtl, 71);
        sparseIntArray2.append(t.ConstraintOverride_barrierDirection, 72);
        sparseIntArray2.append(t.ConstraintOverride_barrierMargin, 73);
        sparseIntArray2.append(t.ConstraintOverride_constraint_referenced_ids, 74);
        sparseIntArray2.append(t.ConstraintOverride_barrierAllowsGoneWidgets, 75);
        sparseIntArray2.append(t.ConstraintOverride_pathMotionArc, 76);
        sparseIntArray2.append(t.ConstraintOverride_layout_constraintTag, 77);
        sparseIntArray2.append(t.ConstraintOverride_visibilityMode, 78);
        sparseIntArray2.append(t.ConstraintOverride_layout_constrainedWidth, 80);
        sparseIntArray2.append(t.ConstraintOverride_layout_constrainedHeight, 81);
        sparseIntArray2.append(t.ConstraintOverride_polarRelativeTo, 82);
        sparseIntArray2.append(t.ConstraintOverride_transformPivotTarget, 83);
        sparseIntArray2.append(t.ConstraintOverride_quantizeMotionSteps, 84);
        sparseIntArray2.append(t.ConstraintOverride_quantizeMotionPhase, 85);
        sparseIntArray2.append(t.ConstraintOverride_quantizeMotionInterpolator, 86);
        sparseIntArray2.append(t.ConstraintOverride_layout_wrapBehaviorInParent, 97);
    }

    public static k d(Context context, XmlResourceParser xmlResourceParser) {
        AttributeSet asAttributeSet = Xml.asAttributeSet(xmlResourceParser);
        k kVar = new k();
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(asAttributeSet, t.ConstraintOverride);
        o(kVar, obtainStyledAttributes);
        obtainStyledAttributes.recycle();
        return kVar;
    }

    public static int[] f(Barrier barrier, String str) {
        int i7;
        HashMap hashMap;
        String[] split = str.split(",");
        Context context = barrier.getContext();
        int[] iArr = new int[split.length];
        int i8 = 0;
        int i9 = 0;
        while (i8 < split.length) {
            String trim = split[i8].trim();
            Object obj = null;
            try {
                i7 = s.class.getField(trim).getInt(null);
            } catch (Exception unused) {
                i7 = 0;
            }
            if (i7 == 0) {
                i7 = context.getResources().getIdentifier(trim, "id", context.getPackageName());
            }
            if (i7 == 0 && barrier.isInEditMode() && (barrier.getParent() instanceof ConstraintLayout)) {
                ConstraintLayout constraintLayout = (ConstraintLayout) barrier.getParent();
                constraintLayout.getClass();
                if ((trim instanceof String) && (hashMap = constraintLayout.f1698m) != null && hashMap.containsKey(trim)) {
                    obj = constraintLayout.f1698m.get(trim);
                }
                if (obj != null && (obj instanceof Integer)) {
                    i7 = ((Integer) obj).intValue();
                }
            }
            iArr[i9] = i7;
            i8++;
            i9++;
        }
        return i9 != split.length ? Arrays.copyOf(iArr, i9) : iArr;
    }

    public static k g(Context context, AttributeSet attributeSet, boolean z6) {
        k kVar = new k();
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, z6 ? t.ConstraintOverride : t.Constraint);
        if (z6) {
            o(kVar, obtainStyledAttributes);
        } else {
            int indexCount = obtainStyledAttributes.getIndexCount();
            int i7 = 0;
            while (true) {
                l lVar = kVar.f85e;
                if (i7 < indexCount) {
                    int index = obtainStyledAttributes.getIndex(i7);
                    int i8 = t.Constraint_android_id;
                    n nVar = kVar.f83c;
                    o oVar = kVar.f86f;
                    m mVar = kVar.f84d;
                    if (index != i8 && t.Constraint_android_layout_marginStart != index && t.Constraint_android_layout_marginEnd != index) {
                        mVar.f133a = true;
                        lVar.f92b = true;
                        nVar.f146a = true;
                        oVar.f152a = true;
                    }
                    SparseIntArray sparseIntArray = f167h;
                    switch (sparseIntArray.get(index)) {
                        case 1:
                            lVar.f122q = l(obtainStyledAttributes, index, lVar.f122q);
                            break;
                        case 2:
                            lVar.J = obtainStyledAttributes.getDimensionPixelSize(index, lVar.J);
                            break;
                        case 3:
                            lVar.f120p = l(obtainStyledAttributes, index, lVar.f120p);
                            break;
                        case 4:
                            lVar.f118o = l(obtainStyledAttributes, index, lVar.f118o);
                            break;
                        case 5:
                            lVar.f131z = obtainStyledAttributes.getString(index);
                            break;
                        case 6:
                            lVar.D = obtainStyledAttributes.getDimensionPixelOffset(index, lVar.D);
                            break;
                        case 7:
                            lVar.E = obtainStyledAttributes.getDimensionPixelOffset(index, lVar.E);
                            break;
                        case 8:
                            lVar.K = obtainStyledAttributes.getDimensionPixelSize(index, lVar.K);
                            break;
                        case 9:
                            lVar.f128w = l(obtainStyledAttributes, index, lVar.f128w);
                            break;
                        case 10:
                            lVar.f127v = l(obtainStyledAttributes, index, lVar.f127v);
                            break;
                        case 11:
                            lVar.Q = obtainStyledAttributes.getDimensionPixelSize(index, lVar.Q);
                            break;
                        case 12:
                            lVar.R = obtainStyledAttributes.getDimensionPixelSize(index, lVar.R);
                            break;
                        case 13:
                            lVar.N = obtainStyledAttributes.getDimensionPixelSize(index, lVar.N);
                            break;
                        case 14:
                            lVar.P = obtainStyledAttributes.getDimensionPixelSize(index, lVar.P);
                            break;
                        case 15:
                            lVar.S = obtainStyledAttributes.getDimensionPixelSize(index, lVar.S);
                            break;
                        case 16:
                            lVar.O = obtainStyledAttributes.getDimensionPixelSize(index, lVar.O);
                            break;
                        case 17:
                            lVar.f98e = obtainStyledAttributes.getDimensionPixelOffset(index, lVar.f98e);
                            break;
                        case 18:
                            lVar.f100f = obtainStyledAttributes.getDimensionPixelOffset(index, lVar.f100f);
                            break;
                        case 19:
                            lVar.f102g = obtainStyledAttributes.getFloat(index, lVar.f102g);
                            break;
                        case 20:
                            lVar.f129x = obtainStyledAttributes.getFloat(index, lVar.f129x);
                            break;
                        case 21:
                            lVar.f96d = obtainStyledAttributes.getLayoutDimension(index, lVar.f96d);
                            break;
                        case 22:
                            nVar.f147b = f166g[obtainStyledAttributes.getInt(index, nVar.f147b)];
                            break;
                        case 23:
                            lVar.f94c = obtainStyledAttributes.getLayoutDimension(index, lVar.f94c);
                            break;
                        case 24:
                            lVar.G = obtainStyledAttributes.getDimensionPixelSize(index, lVar.G);
                            break;
                        case 25:
                            lVar.f106i = l(obtainStyledAttributes, index, lVar.f106i);
                            break;
                        case 26:
                            lVar.f108j = l(obtainStyledAttributes, index, lVar.f108j);
                            break;
                        case 27:
                            lVar.F = obtainStyledAttributes.getInt(index, lVar.F);
                            break;
                        case 28:
                            lVar.H = obtainStyledAttributes.getDimensionPixelSize(index, lVar.H);
                            break;
                        case 29:
                            lVar.f110k = l(obtainStyledAttributes, index, lVar.f110k);
                            break;
                        case 30:
                            lVar.f112l = l(obtainStyledAttributes, index, lVar.f112l);
                            break;
                        case 31:
                            lVar.L = obtainStyledAttributes.getDimensionPixelSize(index, lVar.L);
                            break;
                        case 32:
                            lVar.f125t = l(obtainStyledAttributes, index, lVar.f125t);
                            break;
                        case 33:
                            lVar.f126u = l(obtainStyledAttributes, index, lVar.f126u);
                            break;
                        case 34:
                            lVar.I = obtainStyledAttributes.getDimensionPixelSize(index, lVar.I);
                            break;
                        case 35:
                            lVar.f116n = l(obtainStyledAttributes, index, lVar.f116n);
                            break;
                        case 36:
                            lVar.f114m = l(obtainStyledAttributes, index, lVar.f114m);
                            break;
                        case 37:
                            lVar.f130y = obtainStyledAttributes.getFloat(index, lVar.f130y);
                            break;
                        case 38:
                            kVar.f81a = obtainStyledAttributes.getResourceId(index, kVar.f81a);
                            break;
                        case 39:
                            lVar.V = obtainStyledAttributes.getFloat(index, lVar.V);
                            break;
                        case 40:
                            lVar.U = obtainStyledAttributes.getFloat(index, lVar.U);
                            break;
                        case 41:
                            lVar.W = obtainStyledAttributes.getInt(index, lVar.W);
                            break;
                        case 42:
                            lVar.X = obtainStyledAttributes.getInt(index, lVar.X);
                            break;
                        case 43:
                            nVar.f149d = obtainStyledAttributes.getFloat(index, nVar.f149d);
                            break;
                        case 44:
                            oVar.f164m = true;
                            oVar.f165n = obtainStyledAttributes.getDimension(index, oVar.f165n);
                            break;
                        case 45:
                            oVar.f154c = obtainStyledAttributes.getFloat(index, oVar.f154c);
                            break;
                        case 46:
                            oVar.f155d = obtainStyledAttributes.getFloat(index, oVar.f155d);
                            break;
                        case 47:
                            oVar.f156e = obtainStyledAttributes.getFloat(index, oVar.f156e);
                            break;
                        case 48:
                            oVar.f157f = obtainStyledAttributes.getFloat(index, oVar.f157f);
                            break;
                        case 49:
                            oVar.f158g = obtainStyledAttributes.getDimension(index, oVar.f158g);
                            break;
                        case 50:
                            oVar.f159h = obtainStyledAttributes.getDimension(index, oVar.f159h);
                            break;
                        case 51:
                            oVar.f161j = obtainStyledAttributes.getDimension(index, oVar.f161j);
                            break;
                        case 52:
                            oVar.f162k = obtainStyledAttributes.getDimension(index, oVar.f162k);
                            break;
                        case 53:
                            oVar.f163l = obtainStyledAttributes.getDimension(index, oVar.f163l);
                            break;
                        case 54:
                            lVar.Y = obtainStyledAttributes.getInt(index, lVar.Y);
                            break;
                        case 55:
                            lVar.Z = obtainStyledAttributes.getInt(index, lVar.Z);
                            break;
                        case z1.c.IF_EQZ /* 56 */:
                            lVar.f91a0 = obtainStyledAttributes.getDimensionPixelSize(index, lVar.f91a0);
                            break;
                        case z1.c.IF_NEZ /* 57 */:
                            lVar.f93b0 = obtainStyledAttributes.getDimensionPixelSize(index, lVar.f93b0);
                            break;
                        case z1.c.IF_LTZ /* 58 */:
                            lVar.f95c0 = obtainStyledAttributes.getDimensionPixelSize(index, lVar.f95c0);
                            break;
                        case z1.c.IF_GEZ /* 59 */:
                            lVar.f97d0 = obtainStyledAttributes.getDimensionPixelSize(index, lVar.f97d0);
                            break;
                        case 60:
                            oVar.f153b = obtainStyledAttributes.getFloat(index, oVar.f153b);
                            break;
                        case z1.c.IF_LEZ /* 61 */:
                            lVar.A = l(obtainStyledAttributes, index, lVar.A);
                            break;
                        case 62:
                            lVar.B = obtainStyledAttributes.getDimensionPixelSize(index, lVar.B);
                            break;
                        case 63:
                            lVar.C = obtainStyledAttributes.getFloat(index, lVar.C);
                            break;
                        case 64:
                            mVar.f134b = l(obtainStyledAttributes, index, mVar.f134b);
                            break;
                        case e.LAYOUT_CONSTRAINT_HEIGHT /* 65 */:
                            if (obtainStyledAttributes.peekValue(index).type != 3) {
                                mVar.f136d = t.f.f8958c[obtainStyledAttributes.getInteger(index, 0)];
                                break;
                            } else {
                                mVar.f136d = obtainStyledAttributes.getString(index);
                                break;
                            }
                        case e.LAYOUT_WRAP_BEHAVIOR_IN_PARENT /* 66 */:
                            mVar.f138f = obtainStyledAttributes.getInt(index, 0);
                            break;
                        case e.GUIDELINE_USE_RTL /* 67 */:
                            mVar.f140h = obtainStyledAttributes.getFloat(index, mVar.f140h);
                            break;
                        case z1.c.AGET /* 68 */:
                            nVar.f150e = obtainStyledAttributes.getFloat(index, nVar.f150e);
                            break;
                        case z1.c.AGET_WIDE /* 69 */:
                            lVar.f99e0 = obtainStyledAttributes.getFloat(index, 1.0f);
                            break;
                        case z1.c.AGET_OBJECT /* 70 */:
                            lVar.f101f0 = obtainStyledAttributes.getFloat(index, 1.0f);
                            break;
                        case z1.c.AGET_BOOLEAN /* 71 */:
                            break;
                        case z1.c.AGET_BYTE /* 72 */:
                            lVar.f103g0 = obtainStyledAttributes.getInt(index, lVar.f103g0);
                            break;
                        case z1.c.AGET_CHAR /* 73 */:
                            lVar.f105h0 = obtainStyledAttributes.getDimensionPixelSize(index, lVar.f105h0);
                            break;
                        case z1.c.AGET_SHORT /* 74 */:
                            lVar.f111k0 = obtainStyledAttributes.getString(index);
                            break;
                        case z1.c.APUT /* 75 */:
                            lVar.f119o0 = obtainStyledAttributes.getBoolean(index, lVar.f119o0);
                            break;
                        case z1.c.APUT_WIDE /* 76 */:
                            mVar.f137e = obtainStyledAttributes.getInt(index, mVar.f137e);
                            break;
                        case z1.c.APUT_OBJECT /* 77 */:
                            lVar.f113l0 = obtainStyledAttributes.getString(index);
                            break;
                        case z1.c.APUT_BOOLEAN /* 78 */:
                            nVar.f148c = obtainStyledAttributes.getInt(index, nVar.f148c);
                            break;
                        case z1.c.APUT_BYTE /* 79 */:
                            mVar.f139g = obtainStyledAttributes.getFloat(index, mVar.f139g);
                            break;
                        case z1.c.APUT_CHAR /* 80 */:
                            lVar.f115m0 = obtainStyledAttributes.getBoolean(index, lVar.f115m0);
                            break;
                        case z1.c.APUT_SHORT /* 81 */:
                            lVar.f117n0 = obtainStyledAttributes.getBoolean(index, lVar.f117n0);
                            break;
                        case z1.c.IGET /* 82 */:
                            mVar.f135c = obtainStyledAttributes.getInteger(index, mVar.f135c);
                            break;
                        case z1.c.IGET_WIDE /* 83 */:
                            oVar.f160i = l(obtainStyledAttributes, index, oVar.f160i);
                            break;
                        case z1.c.IGET_OBJECT /* 84 */:
                            mVar.f142j = obtainStyledAttributes.getInteger(index, mVar.f142j);
                            break;
                        case z1.c.IGET_BOOLEAN /* 85 */:
                            mVar.f141i = obtainStyledAttributes.getFloat(index, mVar.f141i);
                            break;
                        case z1.c.IGET_BYTE /* 86 */:
                            int i9 = obtainStyledAttributes.peekValue(index).type;
                            if (i9 != 1) {
                                if (i9 != 3) {
                                    mVar.f144l = obtainStyledAttributes.getInteger(index, mVar.f145m);
                                    break;
                                } else {
                                    String string = obtainStyledAttributes.getString(index);
                                    mVar.f143k = string;
                                    if (string.indexOf("/") <= 0) {
                                        mVar.f144l = -1;
                                        break;
                                    } else {
                                        mVar.f145m = obtainStyledAttributes.getResourceId(index, -1);
                                        mVar.f144l = -2;
                                        break;
                                    }
                                }
                            } else {
                                int resourceId = obtainStyledAttributes.getResourceId(index, -1);
                                mVar.f145m = resourceId;
                                if (resourceId == -1) {
                                    break;
                                } else {
                                    mVar.f144l = -2;
                                    break;
                                }
                            }
                        case z1.c.IGET_CHAR /* 87 */:
                            Integer.toHexString(index);
                            sparseIntArray.get(index);
                            break;
                        case z1.c.IGET_SHORT /* 88 */:
                        case z1.c.IPUT /* 89 */:
                        case 90:
                        default:
                            Integer.toHexString(index);
                            sparseIntArray.get(index);
                            break;
                        case z1.c.IPUT_OBJECT /* 91 */:
                            lVar.f123r = l(obtainStyledAttributes, index, lVar.f123r);
                            break;
                        case z1.c.IPUT_BOOLEAN /* 92 */:
                            lVar.f124s = l(obtainStyledAttributes, index, lVar.f124s);
                            break;
                        case z1.c.IPUT_BYTE /* 93 */:
                            lVar.M = obtainStyledAttributes.getDimensionPixelSize(index, lVar.M);
                            break;
                        case z1.c.IPUT_CHAR /* 94 */:
                            lVar.T = obtainStyledAttributes.getDimensionPixelSize(index, lVar.T);
                            break;
                        case z1.c.IPUT_SHORT /* 95 */:
                            m(lVar, obtainStyledAttributes, index, 0);
                            break;
                        case z1.c.SGET /* 96 */:
                            m(lVar, obtainStyledAttributes, index, 1);
                            break;
                        case z1.c.SGET_WIDE /* 97 */:
                            lVar.f121p0 = obtainStyledAttributes.getInt(index, lVar.f121p0);
                            break;
                    }
                    i7++;
                } else if (lVar.f111k0 != null) {
                    lVar.f109j0 = null;
                }
            }
        }
        obtainStyledAttributes.recycle();
        return kVar;
    }

    public static int l(TypedArray typedArray, int i7, int i8) {
        int resourceId = typedArray.getResourceId(i7, i8);
        return resourceId == -1 ? typedArray.getInt(i7, -1) : resourceId;
    }

    /* JADX WARN: Removed duplicated region for block: B:17:0x0035  */
    /* JADX WARN: Removed duplicated region for block: B:23:0x0043  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static void m(java.lang.Object r7, android.content.res.TypedArray r8, int r9, int r10) {
        /*
            Method dump skipped, instructions count: 376
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: a0.p.m(java.lang.Object, android.content.res.TypedArray, int, int):void");
    }

    public static void n(f fVar, String str) {
        if (str != null) {
            int length = str.length();
            int indexOf = str.indexOf(44);
            int i7 = -1;
            if (indexOf > 0 && indexOf < length - 1) {
                String substring = str.substring(0, indexOf);
                i7 = substring.equalsIgnoreCase("W") ? 0 : substring.equalsIgnoreCase("H") ? 1 : -1;
                r2 = indexOf + 1;
            }
            int indexOf2 = str.indexOf(58);
            try {
                if (indexOf2 < 0 || indexOf2 >= length - 1) {
                    String substring2 = str.substring(r2);
                    if (substring2.length() > 0) {
                        Float.parseFloat(substring2);
                    }
                } else {
                    String substring3 = str.substring(r2, indexOf2);
                    String substring4 = str.substring(indexOf2 + 1);
                    if (substring3.length() > 0 && substring4.length() > 0) {
                        float parseFloat = Float.parseFloat(substring3);
                        float parseFloat2 = Float.parseFloat(substring4);
                        if (parseFloat > CropImageView.DEFAULT_ASPECT_RATIO && parseFloat2 > CropImageView.DEFAULT_ASPECT_RATIO) {
                            if (i7 == 1) {
                                Math.abs(parseFloat2 / parseFloat);
                            } else {
                                Math.abs(parseFloat / parseFloat2);
                            }
                        }
                    }
                }
            } catch (NumberFormatException unused) {
            }
        }
        fVar.G = str;
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    public static void o(k kVar, TypedArray typedArray) {
        char c7;
        int indexCount = typedArray.getIndexCount();
        j jVar = new j();
        jVar.f69a = new int[10];
        jVar.f70b = new int[10];
        int i7 = 0;
        jVar.f71c = 0;
        jVar.f72d = new int[10];
        jVar.f73e = new float[10];
        jVar.f74f = 0;
        jVar.f75g = new int[5];
        jVar.f76h = new String[5];
        jVar.f77i = 0;
        jVar.f78j = new int[4];
        jVar.f79k = new boolean[4];
        jVar.f80l = 0;
        kVar.f88h = jVar;
        m mVar = kVar.f84d;
        mVar.f133a = false;
        l lVar = kVar.f85e;
        lVar.f92b = false;
        n nVar = kVar.f83c;
        nVar.f146a = false;
        o oVar = kVar.f86f;
        oVar.f152a = false;
        for (int i8 = 0; i8 < indexCount; i8++) {
            int index = typedArray.getIndex(i8);
            int i9 = f168i.get(index);
            SparseIntArray sparseIntArray = f167h;
            switch (i9) {
                case 2:
                    c7 = 5;
                    jVar.b(2, typedArray.getDimensionPixelSize(index, lVar.J));
                    break;
                case 3:
                case 4:
                case 9:
                case 10:
                case 25:
                case 26:
                case 29:
                case 30:
                case 32:
                case 33:
                case 35:
                case 36:
                case z1.c.IF_LEZ /* 61 */:
                case z1.c.IGET_SHORT /* 88 */:
                case z1.c.IPUT /* 89 */:
                case 90:
                case z1.c.IPUT_OBJECT /* 91 */:
                case z1.c.IPUT_BOOLEAN /* 92 */:
                default:
                    Integer.toHexString(index);
                    sparseIntArray.get(index);
                    c7 = 5;
                    break;
                case 5:
                    c7 = 5;
                    jVar.c(5, typedArray.getString(index));
                    break;
                case 6:
                    jVar.b(6, typedArray.getDimensionPixelOffset(index, lVar.D));
                    c7 = 5;
                    break;
                case 7:
                    jVar.b(7, typedArray.getDimensionPixelOffset(index, lVar.E));
                    c7 = 5;
                    break;
                case 8:
                    jVar.b(8, typedArray.getDimensionPixelSize(index, lVar.K));
                    c7 = 5;
                    break;
                case 11:
                    jVar.b(11, typedArray.getDimensionPixelSize(index, lVar.Q));
                    c7 = 5;
                    break;
                case 12:
                    jVar.b(12, typedArray.getDimensionPixelSize(index, lVar.R));
                    c7 = 5;
                    break;
                case 13:
                    jVar.b(13, typedArray.getDimensionPixelSize(index, lVar.N));
                    c7 = 5;
                    break;
                case 14:
                    jVar.b(14, typedArray.getDimensionPixelSize(index, lVar.P));
                    c7 = 5;
                    break;
                case 15:
                    jVar.b(15, typedArray.getDimensionPixelSize(index, lVar.S));
                    c7 = 5;
                    break;
                case 16:
                    jVar.b(16, typedArray.getDimensionPixelSize(index, lVar.O));
                    c7 = 5;
                    break;
                case 17:
                    jVar.b(17, typedArray.getDimensionPixelOffset(index, lVar.f98e));
                    c7 = 5;
                    break;
                case 18:
                    jVar.b(18, typedArray.getDimensionPixelOffset(index, lVar.f100f));
                    c7 = 5;
                    break;
                case 19:
                    jVar.a(typedArray.getFloat(index, lVar.f102g), 19);
                    c7 = 5;
                    break;
                case 20:
                    jVar.a(typedArray.getFloat(index, lVar.f129x), 20);
                    c7 = 5;
                    break;
                case 21:
                    jVar.b(21, typedArray.getLayoutDimension(index, lVar.f96d));
                    c7 = 5;
                    break;
                case 22:
                    jVar.b(22, f166g[typedArray.getInt(index, nVar.f147b)]);
                    c7 = 5;
                    break;
                case 23:
                    jVar.b(23, typedArray.getLayoutDimension(index, lVar.f94c));
                    c7 = 5;
                    break;
                case 24:
                    jVar.b(24, typedArray.getDimensionPixelSize(index, lVar.G));
                    c7 = 5;
                    break;
                case 27:
                    jVar.b(27, typedArray.getInt(index, lVar.F));
                    c7 = 5;
                    break;
                case 28:
                    jVar.b(28, typedArray.getDimensionPixelSize(index, lVar.H));
                    c7 = 5;
                    break;
                case 31:
                    jVar.b(31, typedArray.getDimensionPixelSize(index, lVar.L));
                    c7 = 5;
                    break;
                case 34:
                    jVar.b(34, typedArray.getDimensionPixelSize(index, lVar.I));
                    c7 = 5;
                    break;
                case 37:
                    jVar.a(typedArray.getFloat(index, lVar.f130y), 37);
                    c7 = 5;
                    break;
                case 38:
                    int resourceId = typedArray.getResourceId(index, kVar.f81a);
                    kVar.f81a = resourceId;
                    jVar.b(38, resourceId);
                    c7 = 5;
                    break;
                case 39:
                    jVar.a(typedArray.getFloat(index, lVar.V), 39);
                    c7 = 5;
                    break;
                case 40:
                    jVar.a(typedArray.getFloat(index, lVar.U), 40);
                    c7 = 5;
                    break;
                case 41:
                    jVar.b(41, typedArray.getInt(index, lVar.W));
                    c7 = 5;
                    break;
                case 42:
                    jVar.b(42, typedArray.getInt(index, lVar.X));
                    c7 = 5;
                    break;
                case 43:
                    jVar.a(typedArray.getFloat(index, nVar.f149d), 43);
                    c7 = 5;
                    break;
                case 44:
                    jVar.d(44, true);
                    jVar.a(typedArray.getDimension(index, oVar.f165n), 44);
                    c7 = 5;
                    break;
                case 45:
                    jVar.a(typedArray.getFloat(index, oVar.f154c), 45);
                    c7 = 5;
                    break;
                case 46:
                    jVar.a(typedArray.getFloat(index, oVar.f155d), 46);
                    c7 = 5;
                    break;
                case 47:
                    jVar.a(typedArray.getFloat(index, oVar.f156e), 47);
                    c7 = 5;
                    break;
                case 48:
                    jVar.a(typedArray.getFloat(index, oVar.f157f), 48);
                    c7 = 5;
                    break;
                case 49:
                    jVar.a(typedArray.getDimension(index, oVar.f158g), 49);
                    c7 = 5;
                    break;
                case 50:
                    jVar.a(typedArray.getDimension(index, oVar.f159h), 50);
                    c7 = 5;
                    break;
                case 51:
                    jVar.a(typedArray.getDimension(index, oVar.f161j), 51);
                    c7 = 5;
                    break;
                case 52:
                    jVar.a(typedArray.getDimension(index, oVar.f162k), 52);
                    c7 = 5;
                    break;
                case 53:
                    jVar.a(typedArray.getDimension(index, oVar.f163l), 53);
                    c7 = 5;
                    break;
                case 54:
                    jVar.b(54, typedArray.getInt(index, lVar.Y));
                    c7 = 5;
                    break;
                case 55:
                    jVar.b(55, typedArray.getInt(index, lVar.Z));
                    c7 = 5;
                    break;
                case z1.c.IF_EQZ /* 56 */:
                    jVar.b(56, typedArray.getDimensionPixelSize(index, lVar.f91a0));
                    c7 = 5;
                    break;
                case z1.c.IF_NEZ /* 57 */:
                    jVar.b(57, typedArray.getDimensionPixelSize(index, lVar.f93b0));
                    c7 = 5;
                    break;
                case z1.c.IF_LTZ /* 58 */:
                    jVar.b(58, typedArray.getDimensionPixelSize(index, lVar.f95c0));
                    c7 = 5;
                    break;
                case z1.c.IF_GEZ /* 59 */:
                    jVar.b(59, typedArray.getDimensionPixelSize(index, lVar.f97d0));
                    c7 = 5;
                    break;
                case 60:
                    jVar.a(typedArray.getFloat(index, oVar.f153b), 60);
                    c7 = 5;
                    break;
                case 62:
                    jVar.b(62, typedArray.getDimensionPixelSize(index, lVar.B));
                    c7 = 5;
                    break;
                case 63:
                    jVar.a(typedArray.getFloat(index, lVar.C), 63);
                    c7 = 5;
                    break;
                case 64:
                    jVar.b(64, l(typedArray, index, mVar.f134b));
                    c7 = 5;
                    break;
                case e.LAYOUT_CONSTRAINT_HEIGHT /* 65 */:
                    if (typedArray.peekValue(index).type == 3) {
                        jVar.c(65, typedArray.getString(index));
                    } else {
                        jVar.c(65, t.f.f8958c[typedArray.getInteger(index, i7)]);
                    }
                    c7 = 5;
                    break;
                case e.LAYOUT_WRAP_BEHAVIOR_IN_PARENT /* 66 */:
                    i7 = 0;
                    jVar.b(66, typedArray.getInt(index, 0));
                    c7 = 5;
                    break;
                case e.GUIDELINE_USE_RTL /* 67 */:
                    jVar.a(typedArray.getFloat(index, mVar.f140h), 67);
                    i7 = 0;
                    c7 = 5;
                    break;
                case z1.c.AGET /* 68 */:
                    jVar.a(typedArray.getFloat(index, nVar.f150e), 68);
                    i7 = 0;
                    c7 = 5;
                    break;
                case z1.c.AGET_WIDE /* 69 */:
                    jVar.a(typedArray.getFloat(index, 1.0f), 69);
                    i7 = 0;
                    c7 = 5;
                    break;
                case z1.c.AGET_OBJECT /* 70 */:
                    jVar.a(typedArray.getFloat(index, 1.0f), 70);
                    i7 = 0;
                    c7 = 5;
                    break;
                case z1.c.AGET_BOOLEAN /* 71 */:
                    c7 = 5;
                    break;
                case z1.c.AGET_BYTE /* 72 */:
                    jVar.b(72, typedArray.getInt(index, lVar.f103g0));
                    i7 = 0;
                    c7 = 5;
                    break;
                case z1.c.AGET_CHAR /* 73 */:
                    jVar.b(73, typedArray.getDimensionPixelSize(index, lVar.f105h0));
                    i7 = 0;
                    c7 = 5;
                    break;
                case z1.c.AGET_SHORT /* 74 */:
                    jVar.c(74, typedArray.getString(index));
                    i7 = 0;
                    c7 = 5;
                    break;
                case z1.c.APUT /* 75 */:
                    jVar.d(75, typedArray.getBoolean(index, lVar.f119o0));
                    i7 = 0;
                    c7 = 5;
                    break;
                case z1.c.APUT_WIDE /* 76 */:
                    jVar.b(76, typedArray.getInt(index, mVar.f137e));
                    i7 = 0;
                    c7 = 5;
                    break;
                case z1.c.APUT_OBJECT /* 77 */:
                    jVar.c(77, typedArray.getString(index));
                    i7 = 0;
                    c7 = 5;
                    break;
                case z1.c.APUT_BOOLEAN /* 78 */:
                    jVar.b(78, typedArray.getInt(index, nVar.f148c));
                    i7 = 0;
                    c7 = 5;
                    break;
                case z1.c.APUT_BYTE /* 79 */:
                    jVar.a(typedArray.getFloat(index, mVar.f139g), 79);
                    i7 = 0;
                    c7 = 5;
                    break;
                case z1.c.APUT_CHAR /* 80 */:
                    jVar.d(80, typedArray.getBoolean(index, lVar.f115m0));
                    i7 = 0;
                    c7 = 5;
                    break;
                case z1.c.APUT_SHORT /* 81 */:
                    jVar.d(81, typedArray.getBoolean(index, lVar.f117n0));
                    i7 = 0;
                    c7 = 5;
                    break;
                case z1.c.IGET /* 82 */:
                    jVar.b(82, typedArray.getInteger(index, mVar.f135c));
                    i7 = 0;
                    c7 = 5;
                    break;
                case z1.c.IGET_WIDE /* 83 */:
                    jVar.b(83, l(typedArray, index, oVar.f160i));
                    i7 = 0;
                    c7 = 5;
                    break;
                case z1.c.IGET_OBJECT /* 84 */:
                    jVar.b(84, typedArray.getInteger(index, mVar.f142j));
                    i7 = 0;
                    c7 = 5;
                    break;
                case z1.c.IGET_BOOLEAN /* 85 */:
                    jVar.a(typedArray.getFloat(index, mVar.f141i), 85);
                    i7 = 0;
                    c7 = 5;
                    break;
                case z1.c.IGET_BYTE /* 86 */:
                    int i10 = typedArray.peekValue(index).type;
                    if (i10 == 1) {
                        int resourceId2 = typedArray.getResourceId(index, -1);
                        mVar.f145m = resourceId2;
                        jVar.b(89, resourceId2);
                        if (mVar.f145m != -1) {
                            mVar.f144l = -2;
                            jVar.b(88, -2);
                        }
                    } else if (i10 == 3) {
                        String string = typedArray.getString(index);
                        mVar.f143k = string;
                        jVar.c(90, string);
                        if (mVar.f143k.indexOf("/") > 0) {
                            int resourceId3 = typedArray.getResourceId(index, -1);
                            mVar.f145m = resourceId3;
                            jVar.b(89, resourceId3);
                            mVar.f144l = -2;
                            jVar.b(88, -2);
                        } else {
                            mVar.f144l = -1;
                            jVar.b(88, -1);
                        }
                    } else {
                        int integer = typedArray.getInteger(index, mVar.f145m);
                        mVar.f144l = integer;
                        jVar.b(88, integer);
                    }
                    i7 = 0;
                    c7 = 5;
                    break;
                case z1.c.IGET_CHAR /* 87 */:
                    Integer.toHexString(index);
                    sparseIntArray.get(index);
                    c7 = 5;
                    break;
                case z1.c.IPUT_BYTE /* 93 */:
                    jVar.b(93, typedArray.getDimensionPixelSize(index, lVar.M));
                    c7 = 5;
                    break;
                case z1.c.IPUT_CHAR /* 94 */:
                    jVar.b(94, typedArray.getDimensionPixelSize(index, lVar.T));
                    c7 = 5;
                    break;
                case z1.c.IPUT_SHORT /* 95 */:
                    m(jVar, typedArray, index, i7);
                    c7 = 5;
                    break;
                case z1.c.SGET /* 96 */:
                    m(jVar, typedArray, index, 1);
                    c7 = 5;
                    break;
                case z1.c.SGET_WIDE /* 97 */:
                    jVar.b(97, typedArray.getInt(index, lVar.f121p0));
                    c7 = 5;
                    break;
                case z1.c.SGET_OBJECT /* 98 */:
                    if (MotionLayout.H0) {
                        int resourceId4 = typedArray.getResourceId(index, kVar.f81a);
                        kVar.f81a = resourceId4;
                        if (resourceId4 == -1) {
                            kVar.f82b = typedArray.getString(index);
                        }
                    } else if (typedArray.peekValue(index).type == 3) {
                        kVar.f82b = typedArray.getString(index);
                    } else {
                        kVar.f81a = typedArray.getResourceId(index, kVar.f81a);
                    }
                    c7 = 5;
                    break;
                case z1.c.SGET_BOOLEAN /* 99 */:
                    jVar.d(99, typedArray.getBoolean(index, lVar.f104h));
                    c7 = 5;
                    break;
            }
        }
    }

    public final void a(ConstraintLayout constraintLayout) {
        k kVar;
        int childCount = constraintLayout.getChildCount();
        for (int i7 = 0; i7 < childCount; i7++) {
            View childAt = constraintLayout.getChildAt(i7);
            int id = childAt.getId();
            HashMap hashMap = this.f174f;
            if (!hashMap.containsKey(Integer.valueOf(id))) {
                l6.f.G(childAt);
            } else {
                if (this.f173e && id == -1) {
                    throw new RuntimeException("All children of ConstraintLayout must have ids to use ConstraintSet");
                }
                if (hashMap.containsKey(Integer.valueOf(id)) && (kVar = (k) hashMap.get(Integer.valueOf(id))) != null) {
                    c.e(childAt, kVar.f87g);
                }
            }
        }
    }

    public final void b(ConstraintLayout constraintLayout) {
        c(constraintLayout);
        constraintLayout.setConstraintSet(null);
        constraintLayout.requestLayout();
    }

    public final void c(ConstraintLayout constraintLayout) {
        int childCount = constraintLayout.getChildCount();
        HashMap hashMap = this.f174f;
        HashSet hashSet = new HashSet(hashMap.keySet());
        for (int i7 = 0; i7 < childCount; i7++) {
            View childAt = constraintLayout.getChildAt(i7);
            int id = childAt.getId();
            if (!hashMap.containsKey(Integer.valueOf(id))) {
                l6.f.G(childAt);
            } else {
                if (this.f173e && id == -1) {
                    throw new RuntimeException("All children of ConstraintLayout must have ids to use ConstraintSet");
                }
                if (id != -1 && hashMap.containsKey(Integer.valueOf(id))) {
                    hashSet.remove(Integer.valueOf(id));
                    k kVar = (k) hashMap.get(Integer.valueOf(id));
                    if (kVar != null) {
                        if (childAt instanceof Barrier) {
                            l lVar = kVar.f85e;
                            lVar.f107i0 = 1;
                            Barrier barrier = (Barrier) childAt;
                            barrier.setId(id);
                            barrier.setType(lVar.f103g0);
                            barrier.setMargin(lVar.f105h0);
                            barrier.setAllowsGoneWidget(lVar.f119o0);
                            int[] iArr = lVar.f109j0;
                            if (iArr != null) {
                                barrier.setReferencedIds(iArr);
                            } else {
                                String str = lVar.f111k0;
                                if (str != null) {
                                    int[] f7 = f(barrier, str);
                                    lVar.f109j0 = f7;
                                    barrier.setReferencedIds(f7);
                                }
                            }
                        }
                        f fVar = (f) childAt.getLayoutParams();
                        fVar.a();
                        kVar.a(fVar);
                        c.e(childAt, kVar.f87g);
                        childAt.setLayoutParams(fVar);
                        n nVar = kVar.f83c;
                        if (nVar.f148c == 0) {
                            childAt.setVisibility(nVar.f147b);
                        }
                        childAt.setAlpha(nVar.f149d);
                        o oVar = kVar.f86f;
                        childAt.setRotation(oVar.f153b);
                        childAt.setRotationX(oVar.f154c);
                        childAt.setRotationY(oVar.f155d);
                        childAt.setScaleX(oVar.f156e);
                        childAt.setScaleY(oVar.f157f);
                        if (oVar.f160i != -1) {
                            if (((View) childAt.getParent()).findViewById(oVar.f160i) != null) {
                                float bottom = (r7.getBottom() + r7.getTop()) / 2.0f;
                                float right = (r7.getRight() + r7.getLeft()) / 2.0f;
                                if (childAt.getRight() - childAt.getLeft() > 0 && childAt.getBottom() - childAt.getTop() > 0) {
                                    childAt.setPivotX(right - childAt.getLeft());
                                    childAt.setPivotY(bottom - childAt.getTop());
                                }
                            }
                        } else {
                            if (!Float.isNaN(oVar.f158g)) {
                                childAt.setPivotX(oVar.f158g);
                            }
                            if (!Float.isNaN(oVar.f159h)) {
                                childAt.setPivotY(oVar.f159h);
                            }
                        }
                        childAt.setTranslationX(oVar.f161j);
                        childAt.setTranslationY(oVar.f162k);
                        childAt.setTranslationZ(oVar.f163l);
                        if (oVar.f164m) {
                            childAt.setElevation(oVar.f165n);
                        }
                    }
                }
            }
        }
        Iterator it = hashSet.iterator();
        while (it.hasNext()) {
            Integer num = (Integer) it.next();
            k kVar2 = (k) hashMap.get(num);
            if (kVar2 != null) {
                l lVar2 = kVar2.f85e;
                if (lVar2.f107i0 == 1) {
                    Barrier barrier2 = new Barrier(constraintLayout.getContext());
                    barrier2.setId(num.intValue());
                    int[] iArr2 = lVar2.f109j0;
                    if (iArr2 != null) {
                        barrier2.setReferencedIds(iArr2);
                    } else {
                        String str2 = lVar2.f111k0;
                        if (str2 != null) {
                            int[] f8 = f(barrier2, str2);
                            lVar2.f109j0 = f8;
                            barrier2.setReferencedIds(f8);
                        }
                    }
                    barrier2.setType(lVar2.f103g0);
                    barrier2.setMargin(lVar2.f105h0);
                    String str3 = ConstraintLayout.VERSION;
                    f fVar2 = new f();
                    barrier2.r();
                    kVar2.a(fVar2);
                    constraintLayout.addView(barrier2, fVar2);
                }
                if (lVar2.f90a) {
                    View guideline = new Guideline(constraintLayout.getContext());
                    guideline.setId(num.intValue());
                    String str4 = ConstraintLayout.VERSION;
                    f fVar3 = new f();
                    kVar2.a(fVar3);
                    constraintLayout.addView(guideline, fVar3);
                }
            }
        }
        for (int i8 = 0; i8 < childCount; i8++) {
            View childAt2 = constraintLayout.getChildAt(i8);
            if (childAt2 instanceof ConstraintHelper) {
                ((ConstraintHelper) childAt2).h(constraintLayout);
            }
        }
    }

    public final void e(ConstraintLayout constraintLayout) {
        int i7;
        int i8;
        p pVar = this;
        int childCount = constraintLayout.getChildCount();
        HashMap hashMap = pVar.f174f;
        hashMap.clear();
        int i9 = 0;
        while (i9 < childCount) {
            View childAt = constraintLayout.getChildAt(i9);
            f fVar = (f) childAt.getLayoutParams();
            int id = childAt.getId();
            if (pVar.f173e && id == -1) {
                throw new RuntimeException("All children of ConstraintLayout must have ids to use ConstraintSet");
            }
            if (!hashMap.containsKey(Integer.valueOf(id))) {
                hashMap.put(Integer.valueOf(id), new k());
            }
            k kVar = (k) hashMap.get(Integer.valueOf(id));
            if (kVar == null) {
                i7 = childCount;
            } else {
                HashMap hashMap2 = pVar.f172d;
                HashMap hashMap3 = new HashMap();
                Class<?> cls = childAt.getClass();
                for (String str : hashMap2.keySet()) {
                    c cVar = (c) hashMap2.get(str);
                    try {
                        if (str.equals("BackgroundColor")) {
                            hashMap3.put(str, new c(cVar, Integer.valueOf(((ColorDrawable) childAt.getBackground()).getColor())));
                            i8 = childCount;
                        } else {
                            i8 = childCount;
                            try {
                                hashMap3.put(str, new c(cVar, cls.getMethod("getMap" + str, new Class[0]).invoke(childAt, new Object[0])));
                            } catch (IllegalAccessException e7) {
                                e = e7;
                                e.printStackTrace();
                                childCount = i8;
                            } catch (NoSuchMethodException e8) {
                                e = e8;
                                e.printStackTrace();
                                childCount = i8;
                            } catch (InvocationTargetException e9) {
                                e = e9;
                                e.printStackTrace();
                                childCount = i8;
                            }
                        }
                    } catch (IllegalAccessException e10) {
                        e = e10;
                        i8 = childCount;
                    } catch (NoSuchMethodException e11) {
                        e = e11;
                        i8 = childCount;
                    } catch (InvocationTargetException e12) {
                        e = e12;
                        i8 = childCount;
                    }
                    childCount = i8;
                }
                i7 = childCount;
                kVar.f87g = hashMap3;
                kVar.c(id, fVar);
                int visibility = childAt.getVisibility();
                n nVar = kVar.f83c;
                nVar.f147b = visibility;
                nVar.f149d = childAt.getAlpha();
                float rotation = childAt.getRotation();
                o oVar = kVar.f86f;
                oVar.f153b = rotation;
                oVar.f154c = childAt.getRotationX();
                oVar.f155d = childAt.getRotationY();
                oVar.f156e = childAt.getScaleX();
                oVar.f157f = childAt.getScaleY();
                float pivotX = childAt.getPivotX();
                float pivotY = childAt.getPivotY();
                if (pivotX != 0.0d || pivotY != 0.0d) {
                    oVar.f158g = pivotX;
                    oVar.f159h = pivotY;
                }
                oVar.f161j = childAt.getTranslationX();
                oVar.f162k = childAt.getTranslationY();
                oVar.f163l = childAt.getTranslationZ();
                if (oVar.f164m) {
                    oVar.f165n = childAt.getElevation();
                }
                if (childAt instanceof Barrier) {
                    Barrier barrier = (Barrier) childAt;
                    boolean allowsGoneWidget = barrier.getAllowsGoneWidget();
                    l lVar = kVar.f85e;
                    lVar.f119o0 = allowsGoneWidget;
                    lVar.f109j0 = barrier.getReferencedIds();
                    lVar.f103g0 = barrier.getType();
                    lVar.f105h0 = barrier.getMargin();
                }
            }
            i9++;
            pVar = this;
            childCount = i7;
        }
    }

    public final k h(int i7) {
        HashMap hashMap = this.f174f;
        if (!hashMap.containsKey(Integer.valueOf(i7))) {
            hashMap.put(Integer.valueOf(i7), new k());
        }
        return (k) hashMap.get(Integer.valueOf(i7));
    }

    public final k i(int i7) {
        HashMap hashMap = this.f174f;
        if (hashMap.containsKey(Integer.valueOf(i7))) {
            return (k) hashMap.get(Integer.valueOf(i7));
        }
        return null;
    }

    public final void j(Context context, int i7) {
        XmlResourceParser xml = context.getResources().getXml(i7);
        try {
            for (int eventType = xml.getEventType(); eventType != 1; eventType = xml.next()) {
                if (eventType == 0) {
                    xml.getName();
                } else if (eventType == 2) {
                    String name = xml.getName();
                    k g7 = g(context, Xml.asAttributeSet(xml), false);
                    if (name.equalsIgnoreCase("Guideline")) {
                        g7.f85e.f90a = true;
                    }
                    this.f174f.put(Integer.valueOf(g7.f81a), g7);
                }
            }
        } catch (IOException e7) {
            e7.printStackTrace();
        } catch (XmlPullParserException e8) {
            e8.printStackTrace();
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:81:0x01cf, code lost:
    
        continue;
     */
    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue
    java.lang.NullPointerException: Cannot invoke "java.util.List.iterator()" because the return value of "jadx.core.dex.visitors.regions.SwitchOverStringVisitor$SwitchData.getNewCases()" is null
    	at jadx.core.dex.visitors.regions.SwitchOverStringVisitor.restoreSwitchOverString(SwitchOverStringVisitor.java:109)
    	at jadx.core.dex.visitors.regions.SwitchOverStringVisitor.visitRegion(SwitchOverStringVisitor.java:66)
    	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverseIterativeStepInternal(DepthRegionTraversal.java:77)
    	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverseIterativeStepInternal(DepthRegionTraversal.java:82)
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void k(android.content.Context r10, android.content.res.XmlResourceParser r11) {
        /*
            Method dump skipped, instructions count: 562
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: a0.p.k(android.content.Context, android.content.res.XmlResourceParser):void");
    }
}
