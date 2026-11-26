package q0;

import android.R;
import android.annotation.SuppressLint;
import android.graphics.Rect;
import android.text.SpannableString;
import android.text.TextUtils;
import android.view.accessibility.AccessibilityNodeInfo;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import n1.z;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final class i {
    public static final int ACTION_ACCESSIBILITY_FOCUS = 64;
    public static final String ACTION_ARGUMENT_COLUMN_INT = "android.view.accessibility.action.ARGUMENT_COLUMN_INT";
    public static final String ACTION_ARGUMENT_EXTEND_SELECTION_BOOLEAN = "ACTION_ARGUMENT_EXTEND_SELECTION_BOOLEAN";
    public static final String ACTION_ARGUMENT_HTML_ELEMENT_STRING = "ACTION_ARGUMENT_HTML_ELEMENT_STRING";
    public static final String ACTION_ARGUMENT_MOVEMENT_GRANULARITY_INT = "ACTION_ARGUMENT_MOVEMENT_GRANULARITY_INT";
    public static final String ACTION_ARGUMENT_MOVE_WINDOW_X = "ACTION_ARGUMENT_MOVE_WINDOW_X";
    public static final String ACTION_ARGUMENT_MOVE_WINDOW_Y = "ACTION_ARGUMENT_MOVE_WINDOW_Y";

    @SuppressLint({"ActionValue"})
    public static final String ACTION_ARGUMENT_PRESS_AND_HOLD_DURATION_MILLIS_INT = "android.view.accessibility.action.ARGUMENT_PRESS_AND_HOLD_DURATION_MILLIS_INT";
    public static final String ACTION_ARGUMENT_PROGRESS_VALUE = "android.view.accessibility.action.ARGUMENT_PROGRESS_VALUE";
    public static final String ACTION_ARGUMENT_ROW_INT = "android.view.accessibility.action.ARGUMENT_ROW_INT";
    public static final String ACTION_ARGUMENT_SELECTION_END_INT = "ACTION_ARGUMENT_SELECTION_END_INT";
    public static final String ACTION_ARGUMENT_SELECTION_START_INT = "ACTION_ARGUMENT_SELECTION_START_INT";
    public static final String ACTION_ARGUMENT_SET_TEXT_CHARSEQUENCE = "ACTION_ARGUMENT_SET_TEXT_CHARSEQUENCE";
    public static final int ACTION_CLEAR_ACCESSIBILITY_FOCUS = 128;
    public static final int ACTION_CLEAR_FOCUS = 2;
    public static final int ACTION_CLEAR_SELECTION = 8;
    public static final int ACTION_CLICK = 16;
    public static final int ACTION_COLLAPSE = 524288;
    public static final int ACTION_COPY = 16384;
    public static final int ACTION_CUT = 65536;
    public static final int ACTION_DISMISS = 1048576;
    public static final int ACTION_EXPAND = 262144;
    public static final int ACTION_FOCUS = 1;
    public static final int ACTION_LONG_CLICK = 32;
    public static final int ACTION_NEXT_AT_MOVEMENT_GRANULARITY = 256;
    public static final int ACTION_NEXT_HTML_ELEMENT = 1024;
    public static final int ACTION_PASTE = 32768;
    public static final int ACTION_PREVIOUS_AT_MOVEMENT_GRANULARITY = 512;
    public static final int ACTION_PREVIOUS_HTML_ELEMENT = 2048;
    public static final int ACTION_SCROLL_BACKWARD = 8192;
    public static final int ACTION_SCROLL_FORWARD = 4096;
    public static final int ACTION_SELECT = 4;
    public static final int ACTION_SET_SELECTION = 131072;
    public static final int ACTION_SET_TEXT = 2097152;
    public static final String EXTRA_DATA_TEXT_CHARACTER_LOCATION_ARG_LENGTH = "android.core.view.accessibility.extra.DATA_TEXT_CHARACTER_LOCATION_ARG_LENGTH";
    public static final int EXTRA_DATA_TEXT_CHARACTER_LOCATION_ARG_MAX_LENGTH = 20000;
    public static final String EXTRA_DATA_TEXT_CHARACTER_LOCATION_ARG_START_INDEX = "android.core.view.accessibility.extra.DATA_TEXT_CHARACTER_LOCATION_ARG_START_INDEX";
    public static final String EXTRA_DATA_TEXT_CHARACTER_LOCATION_KEY = "android.core.view.accessibility.extra.DATA_TEXT_CHARACTER_LOCATION_KEY";
    public static final int FOCUS_ACCESSIBILITY = 2;
    public static final int FOCUS_INPUT = 1;
    public static final int MOVEMENT_GRANULARITY_CHARACTER = 1;
    public static final int MOVEMENT_GRANULARITY_LINE = 4;
    public static final int MOVEMENT_GRANULARITY_PAGE = 16;
    public static final int MOVEMENT_GRANULARITY_PARAGRAPH = 8;
    public static final int MOVEMENT_GRANULARITY_WORD = 2;

    /* renamed from: a, reason: collision with root package name */
    public final AccessibilityNodeInfo f8426a;

    /* renamed from: b, reason: collision with root package name */
    public int f8427b = -1;

    /* renamed from: c, reason: collision with root package name */
    public int f8428c = -1;

    public i(AccessibilityNodeInfo accessibilityNodeInfo) {
        this.f8426a = accessibilityNodeInfo;
    }

    public final void a(int i7) {
        this.f8426a.addAction(i7);
    }

    public final void b(g gVar) {
        this.f8426a.addAction((AccessibilityNodeInfo.AccessibilityAction) gVar.f8421a);
    }

    public final ArrayList c(String str) {
        AccessibilityNodeInfo accessibilityNodeInfo = this.f8426a;
        ArrayList<Integer> integerArrayList = accessibilityNodeInfo.getExtras().getIntegerArrayList(str);
        if (integerArrayList != null) {
            return integerArrayList;
        }
        ArrayList<Integer> arrayList = new ArrayList<>();
        accessibilityNodeInfo.getExtras().putIntegerArrayList(str, arrayList);
        return arrayList;
    }

    public final void d(Rect rect) {
        this.f8426a.getBoundsInParent(rect);
    }

    public final CharSequence e() {
        boolean z6 = !c("androidx.view.accessibility.AccessibilityNodeInfoCompat.SPANS_START_KEY").isEmpty();
        AccessibilityNodeInfo accessibilityNodeInfo = this.f8426a;
        if (!z6) {
            return accessibilityNodeInfo.getText();
        }
        ArrayList c7 = c("androidx.view.accessibility.AccessibilityNodeInfoCompat.SPANS_START_KEY");
        ArrayList c8 = c("androidx.view.accessibility.AccessibilityNodeInfoCompat.SPANS_END_KEY");
        ArrayList c9 = c("androidx.view.accessibility.AccessibilityNodeInfoCompat.SPANS_FLAGS_KEY");
        ArrayList c10 = c("androidx.view.accessibility.AccessibilityNodeInfoCompat.SPANS_ID_KEY");
        SpannableString spannableString = new SpannableString(TextUtils.substring(accessibilityNodeInfo.getText(), 0, accessibilityNodeInfo.getText().length()));
        for (int i7 = 0; i7 < c7.size(); i7++) {
            spannableString.setSpan(new a(((Integer) c10.get(i7)).intValue(), this, accessibilityNodeInfo.getExtras().getInt("androidx.view.accessibility.AccessibilityNodeInfoCompat.SPANS_ACTION_ID_KEY")), ((Integer) c7.get(i7)).intValue(), ((Integer) c8.get(i7)).intValue(), ((Integer) c9.get(i7)).intValue());
        }
        return spannableString;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof i)) {
            return false;
        }
        i iVar = (i) obj;
        AccessibilityNodeInfo accessibilityNodeInfo = iVar.f8426a;
        AccessibilityNodeInfo accessibilityNodeInfo2 = this.f8426a;
        if (accessibilityNodeInfo2 == null) {
            if (accessibilityNodeInfo != null) {
                return false;
            }
        } else if (!accessibilityNodeInfo2.equals(accessibilityNodeInfo)) {
            return false;
        }
        return this.f8428c == iVar.f8428c && this.f8427b == iVar.f8427b;
    }

    public final void f(CharSequence charSequence) {
        this.f8426a.setClassName(charSequence);
    }

    public final void g(androidx.fragment.app.o oVar) {
        this.f8426a.setCollectionItemInfo((AccessibilityNodeInfo.CollectionItemInfo) oVar.f2027a);
    }

    public final void h(boolean z6) {
        this.f8426a.setScrollable(z6);
    }

    public final int hashCode() {
        AccessibilityNodeInfo accessibilityNodeInfo = this.f8426a;
        if (accessibilityNodeInfo == null) {
            return 0;
        }
        return accessibilityNodeInfo.hashCode();
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r3v5, types: [java.util.List] */
    /* JADX WARN: Type inference failed for: r3v6, types: [java.util.List] */
    /* JADX WARN: Type inference failed for: r3v7, types: [java.util.ArrayList] */
    public final String toString() {
        ?? emptyList;
        String str;
        StringBuilder sb = new StringBuilder();
        sb.append(super.toString());
        Rect rect = new Rect();
        d(rect);
        sb.append("; boundsInParent: " + rect);
        AccessibilityNodeInfo accessibilityNodeInfo = this.f8426a;
        accessibilityNodeInfo.getBoundsInScreen(rect);
        sb.append("; boundsInScreen: " + rect);
        sb.append("; packageName: ");
        sb.append(accessibilityNodeInfo.getPackageName());
        sb.append("; className: ");
        sb.append(accessibilityNodeInfo.getClassName());
        sb.append("; text: ");
        sb.append(e());
        sb.append("; contentDescription: ");
        sb.append(accessibilityNodeInfo.getContentDescription());
        sb.append("; viewId: ");
        sb.append(accessibilityNodeInfo.getViewIdResourceName());
        sb.append("; uniqueId: ");
        sb.append(z.B0() ? accessibilityNodeInfo.getUniqueId() : accessibilityNodeInfo.getExtras().getString("androidx.view.accessibility.AccessibilityNodeInfoCompat.UNIQUE_ID_KEY"));
        sb.append("; checkable: ");
        sb.append(accessibilityNodeInfo.isCheckable());
        sb.append("; checked: ");
        sb.append(accessibilityNodeInfo.isChecked());
        sb.append("; focusable: ");
        sb.append(accessibilityNodeInfo.isFocusable());
        sb.append("; focused: ");
        sb.append(accessibilityNodeInfo.isFocused());
        sb.append("; selected: ");
        sb.append(accessibilityNodeInfo.isSelected());
        sb.append("; clickable: ");
        sb.append(accessibilityNodeInfo.isClickable());
        sb.append("; longClickable: ");
        sb.append(accessibilityNodeInfo.isLongClickable());
        sb.append("; enabled: ");
        sb.append(accessibilityNodeInfo.isEnabled());
        sb.append("; password: ");
        sb.append(accessibilityNodeInfo.isPassword());
        sb.append("; scrollable: " + accessibilityNodeInfo.isScrollable());
        sb.append("; [");
        List<AccessibilityNodeInfo.AccessibilityAction> actionList = accessibilityNodeInfo.getActionList();
        if (actionList != null) {
            emptyList = new ArrayList();
            int size = actionList.size();
            for (int i7 = 0; i7 < size; i7++) {
                emptyList.add(new g(actionList.get(i7), 0, null, null, null));
            }
        } else {
            emptyList = Collections.emptyList();
        }
        for (int i8 = 0; i8 < emptyList.size(); i8++) {
            g gVar = (g) emptyList.get(i8);
            int a7 = gVar.a();
            if (a7 == 1) {
                str = "ACTION_FOCUS";
            } else if (a7 != 2) {
                switch (a7) {
                    case 4:
                        str = "ACTION_SELECT";
                        break;
                    case 8:
                        str = "ACTION_CLEAR_SELECTION";
                        break;
                    case 16:
                        str = "ACTION_CLICK";
                        break;
                    case 32:
                        str = "ACTION_LONG_CLICK";
                        break;
                    case 64:
                        str = "ACTION_ACCESSIBILITY_FOCUS";
                        break;
                    case 128:
                        str = "ACTION_CLEAR_ACCESSIBILITY_FOCUS";
                        break;
                    case 256:
                        str = "ACTION_NEXT_AT_MOVEMENT_GRANULARITY";
                        break;
                    case 512:
                        str = "ACTION_PREVIOUS_AT_MOVEMENT_GRANULARITY";
                        break;
                    case 1024:
                        str = "ACTION_NEXT_HTML_ELEMENT";
                        break;
                    case 2048:
                        str = "ACTION_PREVIOUS_HTML_ELEMENT";
                        break;
                    case 4096:
                        str = "ACTION_SCROLL_FORWARD";
                        break;
                    case 8192:
                        str = "ACTION_SCROLL_BACKWARD";
                        break;
                    case 16384:
                        str = "ACTION_COPY";
                        break;
                    case 32768:
                        str = "ACTION_PASTE";
                        break;
                    case 65536:
                        str = "ACTION_CUT";
                        break;
                    case 131072:
                        str = "ACTION_SET_SELECTION";
                        break;
                    case 262144:
                        str = "ACTION_EXPAND";
                        break;
                    case ACTION_COLLAPSE /* 524288 */:
                        str = "ACTION_COLLAPSE";
                        break;
                    case ACTION_SET_TEXT /* 2097152 */:
                        str = "ACTION_SET_TEXT";
                        break;
                    case R.id.accessibilityActionMoveWindow:
                        str = "ACTION_MOVE_WINDOW";
                        break;
                    default:
                        switch (a7) {
                            case R.id.accessibilityActionShowOnScreen:
                                str = "ACTION_SHOW_ON_SCREEN";
                                break;
                            case R.id.accessibilityActionScrollToPosition:
                                str = "ACTION_SCROLL_TO_POSITION";
                                break;
                            case R.id.accessibilityActionScrollUp:
                                str = "ACTION_SCROLL_UP";
                                break;
                            case R.id.accessibilityActionScrollLeft:
                                str = "ACTION_SCROLL_LEFT";
                                break;
                            case R.id.accessibilityActionScrollDown:
                                str = "ACTION_SCROLL_DOWN";
                                break;
                            case R.id.accessibilityActionScrollRight:
                                str = "ACTION_SCROLL_RIGHT";
                                break;
                            case R.id.accessibilityActionContextClick:
                                str = "ACTION_CONTEXT_CLICK";
                                break;
                            case R.id.accessibilityActionSetProgress:
                                str = "ACTION_SET_PROGRESS";
                                break;
                            default:
                                switch (a7) {
                                    case R.id.accessibilityActionShowTooltip:
                                        str = "ACTION_SHOW_TOOLTIP";
                                        break;
                                    case R.id.accessibilityActionHideTooltip:
                                        str = "ACTION_HIDE_TOOLTIP";
                                        break;
                                    case R.id.accessibilityActionPageUp:
                                        str = "ACTION_PAGE_UP";
                                        break;
                                    case R.id.accessibilityActionPageDown:
                                        str = "ACTION_PAGE_DOWN";
                                        break;
                                    case R.id.accessibilityActionPageLeft:
                                        str = "ACTION_PAGE_LEFT";
                                        break;
                                    case R.id.accessibilityActionPageRight:
                                        str = "ACTION_PAGE_RIGHT";
                                        break;
                                    case R.id.accessibilityActionPressAndHold:
                                        str = "ACTION_PRESS_AND_HOLD";
                                        break;
                                    default:
                                        switch (a7) {
                                            case R.id.accessibilityActionImeEnter:
                                                str = "ACTION_IME_ENTER";
                                                break;
                                            case R.id.accessibilityActionDragStart:
                                                str = "ACTION_DRAG_START";
                                                break;
                                            case R.id.accessibilityActionDragDrop:
                                                str = "ACTION_DRAG_DROP";
                                                break;
                                            case R.id.accessibilityActionDragCancel:
                                                str = "ACTION_DRAG_CANCEL";
                                                break;
                                            default:
                                                str = "ACTION_UNKNOWN";
                                                break;
                                        }
                                }
                        }
                }
            } else {
                str = "ACTION_CLEAR_FOCUS";
            }
            if (str.equals("ACTION_UNKNOWN")) {
                Object obj = gVar.f8421a;
                if (((AccessibilityNodeInfo.AccessibilityAction) obj).getLabel() != null) {
                    str = ((AccessibilityNodeInfo.AccessibilityAction) obj).getLabel().toString();
                }
            }
            sb.append(str);
            if (i8 != emptyList.size() - 1) {
                sb.append(", ");
            }
        }
        sb.append("]");
        return sb.toString();
    }
}
