package q0;

import android.R;
import android.os.Build;
import android.view.accessibility.AccessibilityNodeInfo;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final class g {
    public static final g ACTION_CONTEXT_CLICK;
    public static final g ACTION_DRAG_CANCEL;
    public static final g ACTION_DRAG_DROP;
    public static final g ACTION_DRAG_START;
    public static final g ACTION_HIDE_TOOLTIP;
    public static final g ACTION_IME_ENTER;
    public static final g ACTION_MOVE_WINDOW;
    public static final g ACTION_PAGE_DOWN;
    public static final g ACTION_PAGE_LEFT;
    public static final g ACTION_PAGE_RIGHT;
    public static final g ACTION_PAGE_UP;
    public static final g ACTION_PRESS_AND_HOLD;
    public static final g ACTION_SCROLL_DOWN;
    public static final g ACTION_SCROLL_LEFT;
    public static final g ACTION_SCROLL_RIGHT;
    public static final g ACTION_SCROLL_TO_POSITION;
    public static final g ACTION_SCROLL_UP;
    public static final g ACTION_SET_PROGRESS;
    public static final g ACTION_SHOW_ON_SCREEN;
    public static final g ACTION_SHOW_TEXT_SUGGESTIONS;
    public static final g ACTION_SHOW_TOOLTIP;

    /* renamed from: a, reason: collision with root package name */
    public final Object f8421a;

    /* renamed from: b, reason: collision with root package name */
    public final int f8422b;

    /* renamed from: c, reason: collision with root package name */
    public final Class f8423c;

    /* renamed from: d, reason: collision with root package name */
    public final w f8424d;
    public static final g ACTION_FOCUS = new g(1);
    public static final g ACTION_CLEAR_FOCUS = new g(2);
    public static final g ACTION_SELECT = new g(4);
    public static final g ACTION_CLEAR_SELECTION = new g(8);
    public static final g ACTION_CLICK = new g(16);
    public static final g ACTION_LONG_CLICK = new g(32);
    public static final g ACTION_ACCESSIBILITY_FOCUS = new g(64);
    public static final g ACTION_CLEAR_ACCESSIBILITY_FOCUS = new g(128);
    public static final g ACTION_NEXT_AT_MOVEMENT_GRANULARITY = new g(256, p.class);
    public static final g ACTION_PREVIOUS_AT_MOVEMENT_GRANULARITY = new g(512, p.class);
    public static final g ACTION_NEXT_HTML_ELEMENT = new g(1024, q.class);
    public static final g ACTION_PREVIOUS_HTML_ELEMENT = new g(2048, q.class);
    public static final g ACTION_SCROLL_FORWARD = new g(4096);
    public static final g ACTION_SCROLL_BACKWARD = new g(8192);
    public static final g ACTION_COPY = new g(16384);
    public static final g ACTION_PASTE = new g(32768);
    public static final g ACTION_CUT = new g(65536);
    public static final g ACTION_SET_SELECTION = new g(131072, u.class);
    public static final g ACTION_EXPAND = new g(262144);
    public static final g ACTION_COLLAPSE = new g(i.ACTION_COLLAPSE);
    public static final g ACTION_DISMISS = new g(i.ACTION_DISMISS);
    public static final g ACTION_SET_TEXT = new g(i.ACTION_SET_TEXT, v.class);

    static {
        AccessibilityNodeInfo.AccessibilityAction accessibilityAction;
        AccessibilityNodeInfo.AccessibilityAction accessibilityAction2;
        AccessibilityNodeInfo.AccessibilityAction accessibilityAction3;
        AccessibilityNodeInfo.AccessibilityAction accessibilityAction4;
        AccessibilityNodeInfo.AccessibilityAction accessibilityAction5;
        AccessibilityNodeInfo.AccessibilityAction accessibilityAction6;
        AccessibilityNodeInfo.AccessibilityAction accessibilityAction7;
        AccessibilityNodeInfo.AccessibilityAction accessibilityAction8;
        AccessibilityNodeInfo.AccessibilityAction accessibilityAction9;
        AccessibilityNodeInfo.AccessibilityAction accessibilityAction10;
        AccessibilityNodeInfo.AccessibilityAction accessibilityAction11;
        AccessibilityNodeInfo.AccessibilityAction accessibilityAction12;
        AccessibilityNodeInfo.AccessibilityAction accessibilityAction13;
        AccessibilityNodeInfo.AccessibilityAction accessibilityAction14;
        AccessibilityNodeInfo.AccessibilityAction accessibilityAction15;
        AccessibilityNodeInfo.AccessibilityAction accessibilityAction16;
        AccessibilityNodeInfo.AccessibilityAction accessibilityAction17;
        AccessibilityNodeInfo.AccessibilityAction accessibilityAction18;
        AccessibilityNodeInfo.AccessibilityAction accessibilityAction19;
        AccessibilityNodeInfo.AccessibilityAction accessibilityAction20;
        AccessibilityNodeInfo.AccessibilityAction accessibilityAction21;
        AccessibilityNodeInfo.AccessibilityAction accessibilityAction22;
        int i7 = Build.VERSION.SDK_INT;
        ACTION_SHOW_ON_SCREEN = new g(AccessibilityNodeInfo.AccessibilityAction.ACTION_SHOW_ON_SCREEN, R.id.accessibilityActionShowOnScreen, null, null, null);
        ACTION_SCROLL_TO_POSITION = new g(AccessibilityNodeInfo.AccessibilityAction.ACTION_SCROLL_TO_POSITION, R.id.accessibilityActionScrollToPosition, null, null, s.class);
        ACTION_SCROLL_UP = new g(AccessibilityNodeInfo.AccessibilityAction.ACTION_SCROLL_UP, R.id.accessibilityActionScrollUp, null, null, null);
        ACTION_SCROLL_LEFT = new g(AccessibilityNodeInfo.AccessibilityAction.ACTION_SCROLL_LEFT, R.id.accessibilityActionScrollLeft, null, null, null);
        ACTION_SCROLL_DOWN = new g(AccessibilityNodeInfo.AccessibilityAction.ACTION_SCROLL_DOWN, R.id.accessibilityActionScrollDown, null, null, null);
        ACTION_SCROLL_RIGHT = new g(AccessibilityNodeInfo.AccessibilityAction.ACTION_SCROLL_RIGHT, R.id.accessibilityActionScrollRight, null, null, null);
        if (i7 >= 29) {
            accessibilityAction22 = AccessibilityNodeInfo.AccessibilityAction.ACTION_PAGE_UP;
            accessibilityAction = accessibilityAction22;
        } else {
            accessibilityAction = null;
        }
        ACTION_PAGE_UP = new g(accessibilityAction, R.id.accessibilityActionPageUp, null, null, null);
        if (i7 >= 29) {
            accessibilityAction21 = AccessibilityNodeInfo.AccessibilityAction.ACTION_PAGE_DOWN;
            accessibilityAction2 = accessibilityAction21;
        } else {
            accessibilityAction2 = null;
        }
        ACTION_PAGE_DOWN = new g(accessibilityAction2, R.id.accessibilityActionPageDown, null, null, null);
        if (i7 >= 29) {
            accessibilityAction20 = AccessibilityNodeInfo.AccessibilityAction.ACTION_PAGE_LEFT;
            accessibilityAction3 = accessibilityAction20;
        } else {
            accessibilityAction3 = null;
        }
        ACTION_PAGE_LEFT = new g(accessibilityAction3, R.id.accessibilityActionPageLeft, null, null, null);
        if (i7 >= 29) {
            accessibilityAction19 = AccessibilityNodeInfo.AccessibilityAction.ACTION_PAGE_RIGHT;
            accessibilityAction4 = accessibilityAction19;
        } else {
            accessibilityAction4 = null;
        }
        ACTION_PAGE_RIGHT = new g(accessibilityAction4, R.id.accessibilityActionPageRight, null, null, null);
        ACTION_CONTEXT_CLICK = new g(AccessibilityNodeInfo.AccessibilityAction.ACTION_CONTEXT_CLICK, R.id.accessibilityActionContextClick, null, null, null);
        ACTION_SET_PROGRESS = new g(AccessibilityNodeInfo.AccessibilityAction.ACTION_SET_PROGRESS, R.id.accessibilityActionSetProgress, null, null, t.class);
        ACTION_MOVE_WINDOW = new g(AccessibilityNodeInfo.AccessibilityAction.ACTION_MOVE_WINDOW, R.id.accessibilityActionMoveWindow, null, null, r.class);
        if (i7 >= 28) {
            accessibilityAction18 = AccessibilityNodeInfo.AccessibilityAction.ACTION_SHOW_TOOLTIP;
            accessibilityAction5 = accessibilityAction18;
        } else {
            accessibilityAction5 = null;
        }
        ACTION_SHOW_TOOLTIP = new g(accessibilityAction5, R.id.accessibilityActionShowTooltip, null, null, null);
        if (i7 >= 28) {
            accessibilityAction17 = AccessibilityNodeInfo.AccessibilityAction.ACTION_HIDE_TOOLTIP;
            accessibilityAction6 = accessibilityAction17;
        } else {
            accessibilityAction6 = null;
        }
        ACTION_HIDE_TOOLTIP = new g(accessibilityAction6, R.id.accessibilityActionHideTooltip, null, null, null);
        if (i7 >= 30) {
            accessibilityAction16 = AccessibilityNodeInfo.AccessibilityAction.ACTION_PRESS_AND_HOLD;
            accessibilityAction7 = accessibilityAction16;
        } else {
            accessibilityAction7 = null;
        }
        ACTION_PRESS_AND_HOLD = new g(accessibilityAction7, R.id.accessibilityActionPressAndHold, null, null, null);
        if (i7 >= 30) {
            accessibilityAction15 = AccessibilityNodeInfo.AccessibilityAction.ACTION_IME_ENTER;
            accessibilityAction8 = accessibilityAction15;
        } else {
            accessibilityAction8 = null;
        }
        ACTION_IME_ENTER = new g(accessibilityAction8, R.id.accessibilityActionImeEnter, null, null, null);
        if (i7 >= 32) {
            accessibilityAction14 = AccessibilityNodeInfo.AccessibilityAction.ACTION_DRAG_START;
            accessibilityAction9 = accessibilityAction14;
        } else {
            accessibilityAction9 = null;
        }
        ACTION_DRAG_START = new g(accessibilityAction9, R.id.accessibilityActionDragStart, null, null, null);
        if (i7 >= 32) {
            accessibilityAction13 = AccessibilityNodeInfo.AccessibilityAction.ACTION_DRAG_DROP;
            accessibilityAction10 = accessibilityAction13;
        } else {
            accessibilityAction10 = null;
        }
        ACTION_DRAG_DROP = new g(accessibilityAction10, R.id.accessibilityActionDragDrop, null, null, null);
        if (i7 >= 32) {
            accessibilityAction12 = AccessibilityNodeInfo.AccessibilityAction.ACTION_DRAG_CANCEL;
            accessibilityAction11 = accessibilityAction12;
        } else {
            accessibilityAction11 = null;
        }
        ACTION_DRAG_CANCEL = new g(accessibilityAction11, R.id.accessibilityActionDragCancel, null, null, null);
        ACTION_SHOW_TEXT_SUGGESTIONS = new g(i7 >= 33 ? AccessibilityNodeInfo.AccessibilityAction.ACTION_SHOW_TEXT_SUGGESTIONS : null, R.id.accessibilityActionShowTextSuggestions, null, null, null);
    }

    public g(int i7) {
        this(null, i7, null, null, null);
    }

    public g(int i7, Class cls) {
        this(null, i7, null, null, cls);
    }

    public g(Object obj, int i7, String str, w wVar, Class cls) {
        this.f8422b = i7;
        this.f8424d = wVar;
        if (obj == null) {
            this.f8421a = new AccessibilityNodeInfo.AccessibilityAction(i7, str);
        } else {
            this.f8421a = obj;
        }
        this.f8423c = cls;
    }

    public final int a() {
        return ((AccessibilityNodeInfo.AccessibilityAction) this.f8421a).getId();
    }

    public final boolean equals(Object obj) {
        if (obj == null || !(obj instanceof g)) {
            return false;
        }
        Object obj2 = ((g) obj).f8421a;
        Object obj3 = this.f8421a;
        return obj3 == null ? obj2 == null : obj3.equals(obj2);
    }

    public final int hashCode() {
        Object obj = this.f8421a;
        if (obj != null) {
            return obj.hashCode();
        }
        return 0;
    }
}
