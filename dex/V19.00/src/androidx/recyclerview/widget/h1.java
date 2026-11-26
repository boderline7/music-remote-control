package androidx.recyclerview.widget;

import android.view.ViewGroup;
import java.util.List;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_2_1633180.dex */
public abstract class h1 {
    private final i1 mObservable = new i1();
    private boolean mHasStableIds = false;

    public final void bindViewHolder(j2 j2Var, int i7) {
        j2Var.mPosition = i7;
        if (hasStableIds()) {
            j2Var.mItemId = getItemId(i7);
        }
        j2Var.setFlags(1, 519);
        int i8 = l0.k.f7276a;
        l0.j.a("RV OnBindView");
        onBindViewHolder(j2Var, i7, j2Var.getUnmodifiedPayloads());
        j2Var.clearPayload();
        ViewGroup.LayoutParams layoutParams = j2Var.itemView.getLayoutParams();
        if (layoutParams instanceof s1) {
            ((s1) layoutParams).f2654c = true;
        }
        l0.j.b();
    }

    public final j2 createViewHolder(ViewGroup viewGroup, int i7) {
        try {
            int i8 = l0.k.f7276a;
            l0.j.a("RV CreateView");
            j2 onCreateViewHolder = onCreateViewHolder(viewGroup, i7);
            if (onCreateViewHolder.itemView.getParent() != null) {
                throw new IllegalStateException("ViewHolder views must not be attached when created. Ensure that you are not passing 'true' to the attachToRoot parameter of LayoutInflater.inflate(..., boolean attachToRoot)");
            }
            onCreateViewHolder.mItemViewType = i7;
            l0.j.b();
            return onCreateViewHolder;
        } catch (Throwable th) {
            int i9 = l0.k.f7276a;
            l0.j.b();
            throw th;
        }
    }

    public abstract int getItemCount();

    public long getItemId(int i7) {
        return -1L;
    }

    public int getItemViewType(int i7) {
        return 0;
    }

    public final boolean hasObservers() {
        return this.mObservable.a();
    }

    public final boolean hasStableIds() {
        return this.mHasStableIds;
    }

    public final void notifyDataSetChanged() {
        this.mObservable.b();
    }

    public final void notifyItemChanged(int i7) {
        this.mObservable.d(i7, 1, null);
    }

    public final void notifyItemChanged(int i7, Object obj) {
        this.mObservable.d(i7, 1, obj);
    }

    public final void notifyItemInserted(int i7) {
        this.mObservable.e(i7, 1);
    }

    public final void notifyItemMoved(int i7, int i8) {
        this.mObservable.c(i7, i8);
    }

    public final void notifyItemRangeChanged(int i7, int i8) {
        this.mObservable.d(i7, i8, null);
    }

    public final void notifyItemRangeChanged(int i7, int i8, Object obj) {
        this.mObservable.d(i7, i8, obj);
    }

    public final void notifyItemRangeInserted(int i7, int i8) {
        this.mObservable.e(i7, i8);
    }

    public final void notifyItemRangeRemoved(int i7, int i8) {
        this.mObservable.f(i7, i8);
    }

    public final void notifyItemRemoved(int i7) {
        this.mObservable.f(i7, 1);
    }

    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
    }

    public abstract void onBindViewHolder(j2 j2Var, int i7);

    public void onBindViewHolder(j2 j2Var, int i7, List<Object> list) {
        onBindViewHolder(j2Var, i7);
    }

    public abstract j2 onCreateViewHolder(ViewGroup viewGroup, int i7);

    public void onDetachedFromRecyclerView(RecyclerView recyclerView) {
    }

    public boolean onFailedToRecycleView(j2 j2Var) {
        return false;
    }

    public void onViewAttachedToWindow(j2 j2Var) {
    }

    public void onViewDetachedFromWindow(j2 j2Var) {
    }

    public void onViewRecycled(j2 j2Var) {
    }

    public void registerAdapterDataObserver(j1 j1Var) {
        this.mObservable.registerObserver(j1Var);
    }

    public void setHasStableIds(boolean z6) {
        if (hasObservers()) {
            throw new IllegalStateException("Cannot change whether this adapter has stable IDs while the adapter has registered observers.");
        }
        this.mHasStableIds = z6;
    }

    public void unregisterAdapterDataObserver(j1 j1Var) {
        this.mObservable.unregisterObserver(j1Var);
    }
}
