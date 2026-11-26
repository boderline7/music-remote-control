package y;

import android.content.Context;
import android.content.res.TypedArray;
import android.content.res.XmlResourceParser;
import android.util.Xml;
import android.view.View;
import androidx.constraintlayout.motion.widget.MotionLayout;
import java.io.IOException;
import org.xmlpull.v1.XmlPullParserException;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final class e0 {
    public static final String CONSTRAINT_OVERRIDE = "ConstraintOverride";
    public static final String CUSTOM_ATTRIBUTE = "CustomAttribute";
    public static final String CUSTOM_METHOD = "CustomMethod";
    public static final String KEY_FRAME_SET_TAG = "KeyFrameSet";
    public static final int ONSTATE_ACTION_DOWN = 1;
    public static final int ONSTATE_ACTION_DOWN_UP = 3;
    public static final int ONSTATE_ACTION_UP = 2;
    public static final int ONSTATE_SHARED_VALUE_SET = 4;
    public static final int ONSTATE_SHARED_VALUE_UNSET = 5;
    public static final String VIEW_TRANSITION_TAG = "ViewTransition";

    /* renamed from: a, reason: collision with root package name */
    public int f9812a;

    /* renamed from: e, reason: collision with root package name */
    public int f9816e;

    /* renamed from: f, reason: collision with root package name */
    public final g f9817f;

    /* renamed from: g, reason: collision with root package name */
    public final a0.k f9818g;

    /* renamed from: j, reason: collision with root package name */
    public int f9821j;

    /* renamed from: k, reason: collision with root package name */
    public String f9822k;

    /* renamed from: o, reason: collision with root package name */
    public final Context f9826o;

    /* renamed from: b, reason: collision with root package name */
    public int f9813b = -1;

    /* renamed from: c, reason: collision with root package name */
    public boolean f9814c = false;

    /* renamed from: d, reason: collision with root package name */
    public int f9815d = 0;

    /* renamed from: h, reason: collision with root package name */
    public int f9819h = -1;

    /* renamed from: i, reason: collision with root package name */
    public int f9820i = -1;

    /* renamed from: l, reason: collision with root package name */
    public int f9823l = 0;

    /* renamed from: m, reason: collision with root package name */
    public String f9824m = null;

    /* renamed from: n, reason: collision with root package name */
    public int f9825n = -1;

    /* renamed from: p, reason: collision with root package name */
    public int f9827p = -1;

    /* renamed from: q, reason: collision with root package name */
    public int f9828q = -1;

    /* renamed from: r, reason: collision with root package name */
    public int f9829r = -1;

    /* renamed from: s, reason: collision with root package name */
    public int f9830s = -1;

    /* renamed from: t, reason: collision with root package name */
    public int f9831t = -1;

    /* renamed from: u, reason: collision with root package name */
    public int f9832u = -1;

    public e0(Context context, XmlResourceParser xmlResourceParser) {
        char c7;
        this.f9826o = context;
        try {
            int eventType = xmlResourceParser.getEventType();
            while (eventType != 1) {
                if (eventType == 2) {
                    String name = xmlResourceParser.getName();
                    switch (name.hashCode()) {
                        case -1962203927:
                            if (name.equals(CONSTRAINT_OVERRIDE)) {
                                c7 = 2;
                                break;
                            }
                            c7 = 65535;
                            break;
                        case -1239391468:
                            if (name.equals(KEY_FRAME_SET_TAG)) {
                                c7 = 1;
                                break;
                            }
                            c7 = 65535;
                            break;
                        case 61998586:
                            if (name.equals(VIEW_TRANSITION_TAG)) {
                                c7 = 0;
                                break;
                            }
                            c7 = 65535;
                            break;
                        case 366511058:
                            if (name.equals(CUSTOM_METHOD)) {
                                c7 = 4;
                                break;
                            }
                            c7 = 65535;
                            break;
                        case 1791837707:
                            if (name.equals(CUSTOM_ATTRIBUTE)) {
                                c7 = 3;
                                break;
                            }
                            c7 = 65535;
                            break;
                        default:
                            c7 = 65535;
                            break;
                    }
                    if (c7 == 0) {
                        b(context, xmlResourceParser);
                    } else if (c7 == 1) {
                        this.f9817f = new g(context, xmlResourceParser);
                    } else if (c7 == 2) {
                        this.f9818g = a0.p.d(context, xmlResourceParser);
                    } else if (c7 == 3 || c7 == 4) {
                        a0.c.d(context, xmlResourceParser, this.f9818g.f87g);
                    } else {
                        l6.f.D();
                        xmlResourceParser.getLineNumber();
                    }
                } else if (eventType != 3) {
                    continue;
                } else if (VIEW_TRANSITION_TAG.equals(xmlResourceParser.getName())) {
                    return;
                }
                eventType = xmlResourceParser.next();
            }
        } catch (IOException e7) {
            e7.printStackTrace();
        } catch (XmlPullParserException e8) {
            e8.printStackTrace();
        }
    }

    public final boolean a(View view) {
        String str;
        if (view == null) {
            return false;
        }
        if (this.f9821j == -1 && this.f9822k == null) {
            return false;
        }
        int i7 = this.f9829r;
        boolean z6 = i7 == -1 || view.getTag(i7) != null;
        int i8 = this.f9830s;
        boolean z7 = i8 == -1 || view.getTag(i8) == null;
        if (z6 && z7) {
            if (view.getId() == this.f9821j) {
                return true;
            }
            if (this.f9822k != null && (view.getLayoutParams() instanceof a0.f) && (str = ((a0.f) view.getLayoutParams()).Y) != null && str.matches(this.f9822k)) {
                return true;
            }
        }
        return false;
    }

    public final void b(Context context, XmlResourceParser xmlResourceParser) {
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(Xml.asAttributeSet(xmlResourceParser), a0.t.ViewTransition);
        int indexCount = obtainStyledAttributes.getIndexCount();
        for (int i7 = 0; i7 < indexCount; i7++) {
            int index = obtainStyledAttributes.getIndex(i7);
            if (index == a0.t.ViewTransition_android_id) {
                this.f9812a = obtainStyledAttributes.getResourceId(index, this.f9812a);
            } else if (index == a0.t.ViewTransition_motionTarget) {
                if (MotionLayout.H0) {
                    int resourceId = obtainStyledAttributes.getResourceId(index, this.f9821j);
                    this.f9821j = resourceId;
                    if (resourceId == -1) {
                        this.f9822k = obtainStyledAttributes.getString(index);
                    }
                } else if (obtainStyledAttributes.peekValue(index).type == 3) {
                    this.f9822k = obtainStyledAttributes.getString(index);
                } else {
                    this.f9821j = obtainStyledAttributes.getResourceId(index, this.f9821j);
                }
            } else if (index == a0.t.ViewTransition_onStateTransition) {
                this.f9813b = obtainStyledAttributes.getInt(index, this.f9813b);
            } else if (index == a0.t.ViewTransition_transitionDisable) {
                this.f9814c = obtainStyledAttributes.getBoolean(index, this.f9814c);
            } else if (index == a0.t.ViewTransition_pathMotionArc) {
                this.f9815d = obtainStyledAttributes.getInt(index, this.f9815d);
            } else if (index == a0.t.ViewTransition_duration) {
                this.f9819h = obtainStyledAttributes.getInt(index, this.f9819h);
            } else if (index == a0.t.ViewTransition_upDuration) {
                this.f9820i = obtainStyledAttributes.getInt(index, this.f9820i);
            } else if (index == a0.t.ViewTransition_viewTransitionMode) {
                this.f9816e = obtainStyledAttributes.getInt(index, this.f9816e);
            } else if (index == a0.t.ViewTransition_motionInterpolator) {
                int i8 = obtainStyledAttributes.peekValue(index).type;
                if (i8 == 1) {
                    int resourceId2 = obtainStyledAttributes.getResourceId(index, -1);
                    this.f9825n = resourceId2;
                    if (resourceId2 != -1) {
                        this.f9823l = -2;
                    }
                } else if (i8 == 3) {
                    String string = obtainStyledAttributes.getString(index);
                    this.f9824m = string;
                    if (string == null || string.indexOf("/") <= 0) {
                        this.f9823l = -1;
                    } else {
                        this.f9825n = obtainStyledAttributes.getResourceId(index, -1);
                        this.f9823l = -2;
                    }
                } else {
                    this.f9823l = obtainStyledAttributes.getInteger(index, this.f9823l);
                }
            } else if (index == a0.t.ViewTransition_setsTag) {
                this.f9827p = obtainStyledAttributes.getResourceId(index, this.f9827p);
            } else if (index == a0.t.ViewTransition_clearsTag) {
                this.f9828q = obtainStyledAttributes.getResourceId(index, this.f9828q);
            } else if (index == a0.t.ViewTransition_ifTagSet) {
                this.f9829r = obtainStyledAttributes.getResourceId(index, this.f9829r);
            } else if (index == a0.t.ViewTransition_ifTagNotSet) {
                this.f9830s = obtainStyledAttributes.getResourceId(index, this.f9830s);
            } else if (index == a0.t.ViewTransition_SharedValueId) {
                this.f9832u = obtainStyledAttributes.getResourceId(index, this.f9832u);
            } else if (index == a0.t.ViewTransition_SharedValue) {
                this.f9831t = obtainStyledAttributes.getInteger(index, this.f9831t);
            }
        }
        obtainStyledAttributes.recycle();
    }

    public final String toString() {
        return "ViewTransition(" + l6.f.F(this.f9826o, this.f9812a) + ")";
    }
}
