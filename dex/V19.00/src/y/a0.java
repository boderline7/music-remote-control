package y;

import android.content.Context;
import android.content.res.TypedArray;
import android.content.res.XmlResourceParser;
import android.util.SparseArray;
import android.util.SparseIntArray;
import android.util.Xml;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.AnimationUtils;
import android.view.animation.AnticipateInterpolator;
import android.view.animation.BounceInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.Interpolator;
import android.view.animation.OvershootInterpolator;
import androidx.constraintlayout.motion.widget.MotionLayout;
import androidx.constraintlayout.widget.Barrier;
import androidx.constraintlayout.widget.ConstraintHelper;
import androidx.constraintlayout.widget.ConstraintLayout;
import com.navi.tool.service.PhoneNotificationRelayService;
import com.yalantis.ucrop.view.CropImageView;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import org.xmlpull.v1.XmlPullParserException;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final class a0 {
    public static final int LAYOUT_CALL_MEASURE = 2;
    public static final int LAYOUT_HONOR_REQUEST = 1;
    public static final int LAYOUT_IGNORE_REQUEST = 0;
    public static final int UNSET = -1;

    /* renamed from: a, reason: collision with root package name */
    public final MotionLayout f9733a;

    /* renamed from: b, reason: collision with root package name */
    public final a0.y f9734b;

    /* renamed from: c, reason: collision with root package name */
    public z f9735c;

    /* renamed from: d, reason: collision with root package name */
    public final ArrayList f9736d;

    /* renamed from: e, reason: collision with root package name */
    public final z f9737e;

    /* renamed from: f, reason: collision with root package name */
    public final ArrayList f9738f;

    /* renamed from: g, reason: collision with root package name */
    public final SparseArray f9739g;

    /* renamed from: h, reason: collision with root package name */
    public final HashMap f9740h;

    /* renamed from: i, reason: collision with root package name */
    public final SparseIntArray f9741i;

    /* renamed from: j, reason: collision with root package name */
    public int f9742j;

    /* renamed from: k, reason: collision with root package name */
    public int f9743k;

    /* renamed from: l, reason: collision with root package name */
    public MotionEvent f9744l;

    /* renamed from: m, reason: collision with root package name */
    public boolean f9745m;

    /* renamed from: n, reason: collision with root package name */
    public boolean f9746n;

    /* renamed from: o, reason: collision with root package name */
    public t f9747o;

    /* renamed from: p, reason: collision with root package name */
    public boolean f9748p;

    /* renamed from: q, reason: collision with root package name */
    public final androidx.appcompat.widget.y f9749q;

    /* renamed from: r, reason: collision with root package name */
    public float f9750r;

    /* renamed from: s, reason: collision with root package name */
    public float f9751s;

    public a0(Context context, MotionLayout motionLayout, int i7) {
        int eventType;
        z zVar;
        this.f9734b = null;
        this.f9735c = null;
        ArrayList arrayList = new ArrayList();
        this.f9736d = arrayList;
        this.f9737e = null;
        this.f9738f = new ArrayList();
        this.f9739g = new SparseArray();
        this.f9740h = new HashMap();
        this.f9741i = new SparseIntArray();
        this.f9742j = 400;
        this.f9743k = 0;
        this.f9745m = false;
        this.f9746n = false;
        this.f9733a = motionLayout;
        this.f9749q = new androidx.appcompat.widget.y(motionLayout);
        XmlResourceParser xml = context.getResources().getXml(i7);
        try {
            eventType = xml.getEventType();
            zVar = null;
        } catch (IOException e7) {
            e7.printStackTrace();
        } catch (XmlPullParserException e8) {
            e8.printStackTrace();
        }
        while (true) {
            char c7 = 1;
            if (eventType == 1) {
                this.f9739g.put(a0.s.motion_base, new a0.p());
                this.f9740h.put("motion_base", Integer.valueOf(a0.s.motion_base));
                return;
            }
            if (eventType == 0) {
                xml.getName();
            } else if (eventType == 2) {
                String name = xml.getName();
                switch (name.hashCode()) {
                    case -1349929691:
                        if (name.equals("ConstraintSet")) {
                            c7 = 5;
                            break;
                        }
                        c7 = 65535;
                        break;
                    case -1239391468:
                        if (name.equals(e0.KEY_FRAME_SET_TAG)) {
                            c7 = '\b';
                            break;
                        }
                        c7 = 65535;
                        break;
                    case -687739768:
                        if (name.equals("Include")) {
                            c7 = 7;
                            break;
                        }
                        c7 = 65535;
                        break;
                    case 61998586:
                        if (name.equals(e0.VIEW_TRANSITION_TAG)) {
                            c7 = '\t';
                            break;
                        }
                        c7 = 65535;
                        break;
                    case 269306229:
                        if (name.equals("Transition")) {
                            break;
                        }
                        c7 = 65535;
                        break;
                    case 312750793:
                        if (name.equals("OnClick")) {
                            c7 = 3;
                            break;
                        }
                        c7 = 65535;
                        break;
                    case 327855227:
                        if (name.equals("OnSwipe")) {
                            c7 = 2;
                            break;
                        }
                        c7 = 65535;
                        break;
                    case 793277014:
                        if (name.equals("MotionScene")) {
                            c7 = 0;
                            break;
                        }
                        c7 = 65535;
                        break;
                    case 1382829617:
                        if (name.equals("StateSet")) {
                            c7 = 4;
                            break;
                        }
                        c7 = 65535;
                        break;
                    case 1942574248:
                        if (name.equals("include")) {
                            c7 = 6;
                            break;
                        }
                        c7 = 65535;
                        break;
                    default:
                        c7 = 65535;
                        break;
                }
                switch (c7) {
                    case 0:
                        l(context, xml);
                        break;
                    case 1:
                        zVar = new z(this, context, xml);
                        arrayList.add(zVar);
                        if (this.f9735c == null && !zVar.f9948b) {
                            this.f9735c = zVar;
                            c0 c0Var = zVar.f9958l;
                            if (c0Var != null) {
                                c0Var.c(this.f9748p);
                            }
                        }
                        if (!zVar.f9948b) {
                            break;
                        } else {
                            if (zVar.f9949c == -1) {
                                this.f9737e = zVar;
                            } else {
                                this.f9738f.add(zVar);
                            }
                            arrayList.remove(zVar);
                            break;
                        }
                    case 2:
                        if (zVar == null) {
                            context.getResources().getResourceEntryName(i7);
                            xml.getLineNumber();
                        }
                        if (zVar == null) {
                            break;
                        } else {
                            zVar.f9958l = new c0(context, this.f9733a, xml);
                            break;
                        }
                    case 3:
                        if (zVar == null) {
                            break;
                        } else {
                            zVar.f9959m.add(new y(context, zVar, xml));
                            break;
                        }
                    case 4:
                        this.f9734b = new a0.y(context, xml);
                        break;
                    case 5:
                        i(context, xml);
                        break;
                    case 6:
                    case 7:
                        k(context, xml);
                        break;
                    case '\b':
                        g gVar = new g(context, xml);
                        if (zVar == null) {
                            break;
                        } else {
                            zVar.f9957k.add(gVar);
                            break;
                        }
                    case '\t':
                        e0 e0Var = new e0(context, xml);
                        androidx.appcompat.widget.y yVar = this.f9749q;
                        ((ArrayList) yVar.f1483c).add(e0Var);
                        yVar.f1484d = null;
                        int i8 = e0Var.f9813b;
                        if (i8 != 4) {
                            if (i8 != 5) {
                                break;
                            } else {
                                ConstraintLayout.getSharedValues().a(e0Var.f9832u, new f0());
                                break;
                            }
                        } else {
                            ConstraintLayout.getSharedValues().a(e0Var.f9832u, new f0());
                            break;
                        }
                }
            }
            eventType = xml.next();
        }
    }

    public final boolean a(int i7, MotionLayout motionLayout) {
        z zVar;
        if (this.f9747o != null) {
            return false;
        }
        Iterator it = this.f9736d.iterator();
        while (it.hasNext()) {
            z zVar2 = (z) it.next();
            int i8 = zVar2.f9960n;
            if (i8 != 0 && ((zVar = this.f9735c) != zVar2 || (zVar.f9964r & 2) == 0)) {
                if (i7 == zVar2.f9950d && (i8 == 4 || i8 == 2)) {
                    w wVar = w.FINISHED;
                    motionLayout.setState(wVar);
                    motionLayout.setTransition(zVar2);
                    if (zVar2.f9960n == 4) {
                        motionLayout.q(1.0f);
                        motionLayout.f1588x0 = null;
                        motionLayout.setState(w.SETUP);
                        motionLayout.setState(w.MOVING);
                    } else {
                        motionLayout.setProgress(1.0f);
                        motionLayout.s(true);
                        motionLayout.setState(w.SETUP);
                        motionLayout.setState(w.MOVING);
                        motionLayout.setState(wVar);
                        motionLayout.z();
                    }
                    return true;
                }
                if (i7 == zVar2.f9949c && (i8 == 3 || i8 == 1)) {
                    w wVar2 = w.FINISHED;
                    motionLayout.setState(wVar2);
                    motionLayout.setTransition(zVar2);
                    if (zVar2.f9960n == 3) {
                        motionLayout.q(CropImageView.DEFAULT_ASPECT_RATIO);
                        motionLayout.setState(w.SETUP);
                        motionLayout.setState(w.MOVING);
                    } else {
                        motionLayout.setProgress(CropImageView.DEFAULT_ASPECT_RATIO);
                        motionLayout.s(true);
                        motionLayout.setState(w.SETUP);
                        motionLayout.setState(w.MOVING);
                        motionLayout.setState(wVar2);
                        motionLayout.z();
                    }
                    return true;
                }
            }
        }
        return false;
    }

    public final a0.p b(int i7) {
        int a7;
        SparseArray sparseArray = this.f9739g;
        a0.y yVar = this.f9734b;
        if (yVar != null && (a7 = yVar.a(i7)) != -1) {
            i7 = a7;
        }
        if (sparseArray.get(i7) != null) {
            return (a0.p) sparseArray.get(i7);
        }
        l6.f.F(this.f9733a.getContext(), i7);
        return (a0.p) sparseArray.get(sparseArray.keyAt(0));
    }

    public final int c() {
        z zVar = this.f9735c;
        return zVar != null ? zVar.f9954h : this.f9742j;
    }

    public final int d(Context context, String str) {
        int identifier = str.contains("/") ? context.getResources().getIdentifier(str.substring(str.indexOf(47) + 1), "id", context.getPackageName()) : -1;
        return (identifier != -1 || str.length() <= 1) ? identifier : Integer.parseInt(str.substring(1));
    }

    public final Interpolator e() {
        z zVar = this.f9735c;
        int i7 = zVar.f9951e;
        if (i7 == -2) {
            return AnimationUtils.loadInterpolator(this.f9733a.getContext(), this.f9735c.f9953g);
        }
        if (i7 == -1) {
            return new m(t.f.c(zVar.f9952f), 1);
        }
        if (i7 == 0) {
            return new AccelerateDecelerateInterpolator();
        }
        if (i7 == 1) {
            return new AccelerateInterpolator();
        }
        if (i7 == 2) {
            return new DecelerateInterpolator();
        }
        if (i7 == 4) {
            return new BounceInterpolator();
        }
        if (i7 == 5) {
            return new OvershootInterpolator();
        }
        if (i7 != 6) {
            return null;
        }
        return new AnticipateInterpolator();
    }

    public final void f(n nVar) {
        z zVar = this.f9735c;
        if (zVar != null) {
            Iterator it = zVar.f9957k.iterator();
            while (it.hasNext()) {
                ((g) it.next()).a(nVar);
            }
        } else {
            z zVar2 = this.f9737e;
            if (zVar2 != null) {
                Iterator it2 = zVar2.f9957k.iterator();
                while (it2.hasNext()) {
                    ((g) it2.next()).a(nVar);
                }
            }
        }
    }

    public final float g() {
        c0 c0Var;
        z zVar = this.f9735c;
        return (zVar == null || (c0Var = zVar.f9958l) == null) ? CropImageView.DEFAULT_ASPECT_RATIO : c0Var.f9776t;
    }

    public final int h() {
        z zVar = this.f9735c;
        if (zVar == null) {
            return -1;
        }
        return zVar.f9950d;
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    public final int i(Context context, XmlResourceParser xmlResourceParser) {
        boolean z6;
        boolean z7;
        a0.p pVar = new a0.p();
        pVar.f173e = false;
        int attributeCount = xmlResourceParser.getAttributeCount();
        int i7 = -1;
        int i8 = -1;
        for (int i9 = 0; i9 < attributeCount; i9++) {
            String attributeName = xmlResourceParser.getAttributeName(i9);
            String attributeValue = xmlResourceParser.getAttributeValue(i9);
            attributeName.getClass();
            switch (attributeName.hashCode()) {
                case -1496482599:
                    if (attributeName.equals("deriveConstraintsFrom")) {
                        z6 = false;
                        break;
                    }
                    z6 = -1;
                    break;
                case -1153153640:
                    if (attributeName.equals("constraintRotate")) {
                        z6 = true;
                        break;
                    }
                    z6 = -1;
                    break;
                case 3355:
                    if (attributeName.equals("id")) {
                        z6 = 2;
                        break;
                    }
                    z6 = -1;
                    break;
                default:
                    z6 = -1;
                    break;
            }
            switch (z6) {
                case false:
                    i8 = d(context, attributeValue);
                    break;
                case true:
                    try {
                        pVar.f171c = Integer.parseInt(attributeValue);
                        break;
                    } catch (NumberFormatException unused) {
                        attributeValue.getClass();
                        switch (attributeValue.hashCode()) {
                            case -768416914:
                                if (attributeValue.equals("x_left")) {
                                    z7 = false;
                                    break;
                                }
                                z7 = -1;
                                break;
                            case 3317767:
                                if (attributeValue.equals(PhoneNotificationRelayService.OVERLAY_POSITION_LEFT)) {
                                    z7 = true;
                                    break;
                                }
                                z7 = -1;
                                break;
                            case 3387192:
                                if (attributeValue.equals("none")) {
                                    z7 = 2;
                                    break;
                                }
                                z7 = -1;
                                break;
                            case 108511772:
                                if (attributeValue.equals(PhoneNotificationRelayService.OVERLAY_POSITION_RIGHT)) {
                                    z7 = 3;
                                    break;
                                }
                                z7 = -1;
                                break;
                            case 1954540437:
                                if (attributeValue.equals("x_right")) {
                                    z7 = 4;
                                    break;
                                }
                                z7 = -1;
                                break;
                            default:
                                z7 = -1;
                                break;
                        }
                        switch (z7) {
                            case false:
                                pVar.f171c = 4;
                                break;
                            case true:
                                pVar.f171c = 2;
                                break;
                            case true:
                                pVar.f171c = 0;
                                break;
                            case true:
                                pVar.f171c = 1;
                                break;
                            case true:
                                pVar.f171c = 3;
                                break;
                        }
                    }
                case true:
                    i7 = d(context, attributeValue);
                    int indexOf = attributeValue.indexOf(47);
                    if (indexOf >= 0) {
                        attributeValue = attributeValue.substring(indexOf + 1);
                    }
                    this.f9740h.put(attributeValue, Integer.valueOf(i7));
                    pVar.f169a = l6.f.F(context, i7);
                    break;
            }
        }
        if (i7 != -1) {
            int i10 = this.f9733a.M;
            pVar.k(context, xmlResourceParser);
            if (i8 != -1) {
                this.f9741i.put(i7, i8);
            }
            this.f9739g.put(i7, pVar);
        }
        return i7;
    }

    public final int j(Context context, int i7) {
        XmlResourceParser xml = context.getResources().getXml(i7);
        try {
            for (int eventType = xml.getEventType(); eventType != 1; eventType = xml.next()) {
                String name = xml.getName();
                if (2 == eventType && "ConstraintSet".equals(name)) {
                    return i(context, xml);
                }
            }
            return -1;
        } catch (IOException e7) {
            e7.printStackTrace();
            return -1;
        } catch (XmlPullParserException e8) {
            e8.printStackTrace();
            return -1;
        }
    }

    public final void k(Context context, XmlResourceParser xmlResourceParser) {
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(Xml.asAttributeSet(xmlResourceParser), a0.t.include);
        int indexCount = obtainStyledAttributes.getIndexCount();
        for (int i7 = 0; i7 < indexCount; i7++) {
            int index = obtainStyledAttributes.getIndex(i7);
            if (index == a0.t.include_constraintSet) {
                j(context, obtainStyledAttributes.getResourceId(index, -1));
            }
        }
        obtainStyledAttributes.recycle();
    }

    public final void l(Context context, XmlResourceParser xmlResourceParser) {
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(Xml.asAttributeSet(xmlResourceParser), a0.t.MotionScene);
        int indexCount = obtainStyledAttributes.getIndexCount();
        for (int i7 = 0; i7 < indexCount; i7++) {
            int index = obtainStyledAttributes.getIndex(i7);
            if (index == a0.t.MotionScene_defaultDuration) {
                int i8 = obtainStyledAttributes.getInt(index, this.f9742j);
                this.f9742j = i8;
                if (i8 < 8) {
                    this.f9742j = 8;
                }
            } else if (index == a0.t.MotionScene_layoutDuringTransition) {
                this.f9743k = obtainStyledAttributes.getInteger(index, 0);
            }
        }
        obtainStyledAttributes.recycle();
    }

    public final void m(int i7, MotionLayout motionLayout) {
        SparseArray sparseArray = this.f9739g;
        a0.p pVar = (a0.p) sparseArray.get(i7);
        pVar.f170b = pVar.f169a;
        int i8 = this.f9741i.get(i7);
        HashMap hashMap = pVar.f174f;
        if (i8 > 0) {
            m(i8, motionLayout);
            a0.p pVar2 = (a0.p) sparseArray.get(i8);
            if (pVar2 == null) {
                l6.f.F(this.f9733a.getContext(), i8);
                return;
            }
            pVar.f170b += "/" + pVar2.f170b;
            HashMap hashMap2 = pVar2.f174f;
            for (Integer num : hashMap2.keySet()) {
                int intValue = num.intValue();
                a0.k kVar = (a0.k) hashMap2.get(num);
                if (!hashMap.containsKey(Integer.valueOf(intValue))) {
                    hashMap.put(Integer.valueOf(intValue), new a0.k());
                }
                a0.k kVar2 = (a0.k) hashMap.get(Integer.valueOf(intValue));
                if (kVar2 != null) {
                    a0.l lVar = kVar2.f85e;
                    if (!lVar.f92b) {
                        lVar.a(kVar.f85e);
                    }
                    a0.n nVar = kVar2.f83c;
                    if (!nVar.f146a) {
                        a0.n nVar2 = kVar.f83c;
                        nVar.f146a = nVar2.f146a;
                        nVar.f147b = nVar2.f147b;
                        nVar.f149d = nVar2.f149d;
                        nVar.f150e = nVar2.f150e;
                        nVar.f148c = nVar2.f148c;
                    }
                    a0.o oVar = kVar2.f86f;
                    if (!oVar.f152a) {
                        oVar.a(kVar.f86f);
                    }
                    a0.m mVar = kVar2.f84d;
                    if (!mVar.f133a) {
                        mVar.a(kVar.f84d);
                    }
                    for (String str : kVar.f87g.keySet()) {
                        if (!kVar2.f87g.containsKey(str)) {
                            kVar2.f87g.put(str, (a0.c) kVar.f87g.get(str));
                        }
                    }
                }
            }
        } else {
            pVar.f170b = a.a.i(new StringBuilder(), pVar.f170b, "  layout");
            int childCount = motionLayout.getChildCount();
            for (int i9 = 0; i9 < childCount; i9++) {
                View childAt = motionLayout.getChildAt(i9);
                a0.f fVar = (a0.f) childAt.getLayoutParams();
                int id = childAt.getId();
                if (pVar.f173e && id == -1) {
                    throw new RuntimeException("All children of ConstraintLayout must have ids to use ConstraintSet");
                }
                if (!hashMap.containsKey(Integer.valueOf(id))) {
                    hashMap.put(Integer.valueOf(id), new a0.k());
                }
                a0.k kVar3 = (a0.k) hashMap.get(Integer.valueOf(id));
                if (kVar3 != null) {
                    a0.l lVar2 = kVar3.f85e;
                    if (!lVar2.f92b) {
                        kVar3.c(id, fVar);
                        if (childAt instanceof ConstraintHelper) {
                            lVar2.f109j0 = ((ConstraintHelper) childAt).getReferencedIds();
                            if (childAt instanceof Barrier) {
                                Barrier barrier = (Barrier) childAt;
                                lVar2.f119o0 = barrier.getAllowsGoneWidget();
                                lVar2.f103g0 = barrier.getType();
                                lVar2.f105h0 = barrier.getMargin();
                            }
                        }
                        lVar2.f92b = true;
                    }
                    a0.n nVar3 = kVar3.f83c;
                    if (!nVar3.f146a) {
                        nVar3.f147b = childAt.getVisibility();
                        nVar3.f149d = childAt.getAlpha();
                        nVar3.f146a = true;
                    }
                    a0.o oVar2 = kVar3.f86f;
                    if (!oVar2.f152a) {
                        oVar2.f152a = true;
                        oVar2.f153b = childAt.getRotation();
                        oVar2.f154c = childAt.getRotationX();
                        oVar2.f155d = childAt.getRotationY();
                        oVar2.f156e = childAt.getScaleX();
                        oVar2.f157f = childAt.getScaleY();
                        float pivotX = childAt.getPivotX();
                        float pivotY = childAt.getPivotY();
                        if (pivotX != 0.0d || pivotY != 0.0d) {
                            oVar2.f158g = pivotX;
                            oVar2.f159h = pivotY;
                        }
                        oVar2.f161j = childAt.getTranslationX();
                        oVar2.f162k = childAt.getTranslationY();
                        oVar2.f163l = childAt.getTranslationZ();
                        if (oVar2.f164m) {
                            oVar2.f165n = childAt.getElevation();
                        }
                    }
                }
            }
        }
        for (a0.k kVar4 : hashMap.values()) {
            if (kVar4.f88h != null) {
                if (kVar4.f82b != null) {
                    Iterator it = hashMap.keySet().iterator();
                    while (it.hasNext()) {
                        a0.k i10 = pVar.i(((Integer) it.next()).intValue());
                        String str2 = i10.f85e.f113l0;
                        if (str2 != null && kVar4.f82b.matches(str2)) {
                            kVar4.f88h.e(i10);
                            i10.f87g.putAll((HashMap) kVar4.f87g.clone());
                        }
                    }
                } else {
                    kVar4.f88h.e(pVar.i(kVar4.f81a));
                }
            }
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:7:0x0013, code lost:
    
        if (r2 != (-1)) goto L13;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void n(int r9, int r10) {
        /*
            r8 = this;
            a0.y r0 = r8.f9734b
            r1 = -1
            if (r0 == 0) goto L18
            int r0 = r0.a(r9)
            if (r0 == r1) goto Lc
            goto Ld
        Lc:
            r0 = r9
        Ld:
            a0.y r2 = r8.f9734b
            int r2 = r2.a(r10)
            if (r2 == r1) goto L16
            goto L1a
        L16:
            r2 = r10
            goto L1a
        L18:
            r0 = r9
            goto L16
        L1a:
            y.z r3 = r8.f9735c
            if (r3 == 0) goto L27
            int r4 = r3.f9949c
            if (r4 != r10) goto L27
            int r3 = r3.f9950d
            if (r3 != r9) goto L27
            return
        L27:
            java.util.ArrayList r3 = r8.f9736d
            java.util.Iterator r4 = r3.iterator()
        L2d:
            boolean r5 = r4.hasNext()
            if (r5 == 0) goto L53
            java.lang.Object r5 = r4.next()
            y.z r5 = (y.z) r5
            int r6 = r5.f9949c
            if (r6 != r2) goto L41
            int r7 = r5.f9950d
            if (r7 == r0) goto L47
        L41:
            if (r6 != r10) goto L2d
            int r6 = r5.f9950d
            if (r6 != r9) goto L2d
        L47:
            r8.f9735c = r5
            y.c0 r9 = r5.f9958l
            if (r9 == 0) goto L52
            boolean r10 = r8.f9748p
            r9.c(r10)
        L52:
            return
        L53:
            y.z r9 = r8.f9737e
            java.util.ArrayList r4 = r8.f9738f
            java.util.Iterator r4 = r4.iterator()
        L5b:
            boolean r5 = r4.hasNext()
            if (r5 == 0) goto L6d
            java.lang.Object r5 = r4.next()
            y.z r5 = (y.z) r5
            int r6 = r5.f9949c
            if (r6 != r10) goto L5b
            r9 = r5
            goto L5b
        L6d:
            y.z r10 = new y.z
            r10.<init>(r8, r9)
            r10.f9950d = r0
            r10.f9949c = r2
            if (r0 == r1) goto L7b
            r3.add(r10)
        L7b:
            r8.f9735c = r10
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: y.a0.n(int, int):void");
    }

    public final boolean o() {
        Iterator it = this.f9736d.iterator();
        while (it.hasNext()) {
            if (((z) it.next()).f9958l != null) {
                return true;
            }
        }
        z zVar = this.f9735c;
        return (zVar == null || zVar.f9958l == null) ? false : true;
    }
}
