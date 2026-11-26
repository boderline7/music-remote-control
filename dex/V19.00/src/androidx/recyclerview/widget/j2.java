package androidx.recyclerview.widget;

import android.view.View;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_2_1633180.dex */
public abstract class j2 {
    static final int FLAG_ADAPTER_FULLUPDATE = 1024;
    static final int FLAG_ADAPTER_POSITION_UNKNOWN = 512;
    static final int FLAG_APPEARED_IN_PRE_LAYOUT = 4096;
    static final int FLAG_BOUNCED_FROM_HIDDEN_LIST = 8192;
    static final int FLAG_BOUND = 1;
    static final int FLAG_IGNORE = 128;
    static final int FLAG_INVALID = 4;
    static final int FLAG_MOVED = 2048;
    static final int FLAG_NOT_RECYCLABLE = 16;
    static final int FLAG_REMOVED = 8;
    static final int FLAG_RETURNED_FROM_SCRAP = 32;
    static final int FLAG_TMP_DETACHED = 256;
    static final int FLAG_UPDATE = 2;
    private static final List<Object> FULLUPDATE_PAYLOADS = Collections.emptyList();
    static final int PENDING_ACCESSIBILITY_STATE_NOT_SET = -1;
    public final View itemView;
    int mFlags;
    WeakReference<RecyclerView> mNestedRecyclerView;
    RecyclerView mOwnerRecyclerView;
    int mPosition = -1;
    int mOldPosition = -1;
    long mItemId = -1;
    int mItemViewType = -1;
    int mPreLayoutPosition = -1;
    j2 mShadowedHolder = null;
    j2 mShadowingHolder = null;
    List<Object> mPayloads = null;
    List<Object> mUnmodifiedPayloads = null;
    private int mIsRecyclableCount = 0;
    z1 mScrapContainer = null;
    boolean mInChangeScrap = false;
    private int mWasImportantForAccessibilityBeforeHidden = 0;
    int mPendingAccessibilityState = -1;

    public j2(View view) {
        if (view == null) {
            throw new IllegalArgumentException("itemView may not be null");
        }
        this.itemView = view;
    }

    public void addChangePayload(Object obj) {
        if (obj == null) {
            addFlags(1024);
            return;
        }
        if ((1024 & this.mFlags) == 0) {
            if (this.mPayloads == null) {
                ArrayList arrayList = new ArrayList();
                this.mPayloads = arrayList;
                this.mUnmodifiedPayloads = Collections.unmodifiableList(arrayList);
            }
            this.mPayloads.add(obj);
        }
    }

    public void addFlags(int i7) {
        this.mFlags = i7 | this.mFlags;
    }

    public void clearOldPosition() {
        this.mOldPosition = -1;
        this.mPreLayoutPosition = -1;
    }

    public void clearPayload() {
        List<Object> list = this.mPayloads;
        if (list != null) {
            list.clear();
        }
        this.mFlags &= -1025;
    }

    public void clearReturnedFromScrapFlag() {
        this.mFlags &= -33;
    }

    public void clearTmpDetachFlag() {
        this.mFlags &= -257;
    }

    public boolean doesTransientStatePreventRecycling() {
        if ((this.mFlags & 16) == 0) {
            View view = this.itemView;
            int i7 = p0.d1.OVER_SCROLL_ALWAYS;
            if (p0.l0.i(view)) {
                return true;
            }
        }
        return false;
    }

    public void flagRemovedAndOffsetPosition(int i7, int i8, boolean z6) {
        addFlags(8);
        offsetPosition(i8, z6);
        this.mPosition = i7;
    }

    public final int getAdapterPosition() {
        RecyclerView recyclerView = this.mOwnerRecyclerView;
        if (recyclerView == null) {
            return -1;
        }
        return recyclerView.G(this);
    }

    public final long getItemId() {
        return this.mItemId;
    }

    public final int getItemViewType() {
        return this.mItemViewType;
    }

    public final int getLayoutPosition() {
        int i7 = this.mPreLayoutPosition;
        return i7 == -1 ? this.mPosition : i7;
    }

    public final int getOldPosition() {
        return this.mOldPosition;
    }

    @Deprecated
    public final int getPosition() {
        int i7 = this.mPreLayoutPosition;
        return i7 == -1 ? this.mPosition : i7;
    }

    public List<Object> getUnmodifiedPayloads() {
        if ((this.mFlags & 1024) != 0) {
            return FULLUPDATE_PAYLOADS;
        }
        List<Object> list = this.mPayloads;
        return (list == null || list.size() == 0) ? FULLUPDATE_PAYLOADS : this.mUnmodifiedPayloads;
    }

    public boolean hasAnyOfTheFlags(int i7) {
        return (i7 & this.mFlags) != 0;
    }

    public boolean isAdapterPositionUnknown() {
        return (this.mFlags & 512) != 0 || isInvalid();
    }

    public boolean isAttachedToTransitionOverlay() {
        return (this.itemView.getParent() == null || this.itemView.getParent() == this.mOwnerRecyclerView) ? false : true;
    }

    public boolean isBound() {
        return (this.mFlags & 1) != 0;
    }

    public boolean isInvalid() {
        return (this.mFlags & 4) != 0;
    }

    public final boolean isRecyclable() {
        if ((this.mFlags & 16) == 0) {
            View view = this.itemView;
            int i7 = p0.d1.OVER_SCROLL_ALWAYS;
            if (!p0.l0.i(view)) {
                return true;
            }
        }
        return false;
    }

    public boolean isRemoved() {
        return (this.mFlags & 8) != 0;
    }

    public boolean isScrap() {
        return this.mScrapContainer != null;
    }

    public boolean isTmpDetached() {
        return (this.mFlags & 256) != 0;
    }

    public boolean isUpdated() {
        return (this.mFlags & 2) != 0;
    }

    public boolean needsUpdate() {
        return (this.mFlags & 2) != 0;
    }

    public void offsetPosition(int i7, boolean z6) {
        if (this.mOldPosition == -1) {
            this.mOldPosition = this.mPosition;
        }
        if (this.mPreLayoutPosition == -1) {
            this.mPreLayoutPosition = this.mPosition;
        }
        if (z6) {
            this.mPreLayoutPosition += i7;
        }
        this.mPosition += i7;
        if (this.itemView.getLayoutParams() != null) {
            ((s1) this.itemView.getLayoutParams()).f2654c = true;
        }
    }

    public void onEnteredHiddenState(RecyclerView recyclerView) {
        int i7 = this.mPendingAccessibilityState;
        if (i7 != -1) {
            this.mWasImportantForAccessibilityBeforeHidden = i7;
        } else {
            View view = this.itemView;
            int i8 = p0.d1.OVER_SCROLL_ALWAYS;
            this.mWasImportantForAccessibilityBeforeHidden = p0.l0.c(view);
        }
        if (recyclerView.O()) {
            this.mPendingAccessibilityState = 4;
            recyclerView.f2336t0.add(this);
        } else {
            View view2 = this.itemView;
            int i9 = p0.d1.OVER_SCROLL_ALWAYS;
            p0.l0.s(view2, 4);
        }
    }

    public void onLeftHiddenState(RecyclerView recyclerView) {
        int i7 = this.mWasImportantForAccessibilityBeforeHidden;
        if (recyclerView.O()) {
            this.mPendingAccessibilityState = i7;
            recyclerView.f2336t0.add(this);
        } else {
            View view = this.itemView;
            int i8 = p0.d1.OVER_SCROLL_ALWAYS;
            p0.l0.s(view, i7);
        }
        this.mWasImportantForAccessibilityBeforeHidden = 0;
    }

    public void resetInternal() {
        this.mFlags = 0;
        this.mPosition = -1;
        this.mOldPosition = -1;
        this.mItemId = -1L;
        this.mPreLayoutPosition = -1;
        this.mIsRecyclableCount = 0;
        this.mShadowedHolder = null;
        this.mShadowingHolder = null;
        clearPayload();
        this.mWasImportantForAccessibilityBeforeHidden = 0;
        this.mPendingAccessibilityState = -1;
        RecyclerView.j(this);
    }

    public void saveOldPosition() {
        if (this.mOldPosition == -1) {
            this.mOldPosition = this.mPosition;
        }
    }

    public void setFlags(int i7, int i8) {
        this.mFlags = (i7 & i8) | (this.mFlags & (~i8));
    }

    public final void setIsRecyclable(boolean z6) {
        int i7 = this.mIsRecyclableCount;
        int i8 = z6 ? i7 - 1 : i7 + 1;
        this.mIsRecyclableCount = i8;
        if (i8 < 0) {
            this.mIsRecyclableCount = 0;
            toString();
        } else if (!z6 && i8 == 1) {
            this.mFlags |= 16;
        } else if (z6 && i8 == 0) {
            this.mFlags &= -17;
        }
    }

    public void setScrapContainer(z1 z1Var, boolean z6) {
        this.mScrapContainer = z1Var;
        this.mInChangeScrap = z6;
    }

    public boolean shouldBeKeptAsChild() {
        return (this.mFlags & 16) != 0;
    }

    public boolean shouldIgnore() {
        return (this.mFlags & 128) != 0;
    }

    public void stopIgnoring() {
        this.mFlags &= -129;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder((getClass().isAnonymousClass() ? "ViewHolder" : getClass().getSimpleName()) + "{" + Integer.toHexString(hashCode()) + " position=" + this.mPosition + " id=" + this.mItemId + ", oldPos=" + this.mOldPosition + ", pLpos:" + this.mPreLayoutPosition);
        if (isScrap()) {
            sb.append(" scrap ");
            sb.append(this.mInChangeScrap ? "[changeScrap]" : "[attachedScrap]");
        }
        if (isInvalid()) {
            sb.append(" invalid");
        }
        if (!isBound()) {
            sb.append(" unbound");
        }
        if (needsUpdate()) {
            sb.append(" update");
        }
        if (isRemoved()) {
            sb.append(" removed");
        }
        if (shouldIgnore()) {
            sb.append(" ignored");
        }
        if (isTmpDetached()) {
            sb.append(" tmpDetached");
        }
        if (!isRecyclable()) {
            sb.append(" not recyclable(" + this.mIsRecyclableCount + ")");
        }
        if (isAdapterPositionUnknown()) {
            sb.append(" undefined adapter position");
        }
        if (this.itemView.getParent() == null) {
            sb.append(" no parent");
        }
        sb.append("}");
        return sb.toString();
    }

    public void unScrap() {
        this.mScrapContainer.j(this);
    }

    public boolean wasReturnedFromScrap() {
        return (this.mFlags & 32) != 0;
    }
}
