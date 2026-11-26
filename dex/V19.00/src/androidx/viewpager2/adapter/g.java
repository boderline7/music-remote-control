package androidx.viewpager2.adapter;

import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.FrameLayout;
import androidx.fragment.app.Fragment$SavedState;
import androidx.fragment.app.d0;
import androidx.fragment.app.x0;
import androidx.fragment.app.z;
import androidx.lifecycle.r;
import androidx.lifecycle.s;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.b2;
import androidx.recyclerview.widget.h1;
import androidx.viewpager2.widget.ViewPager2;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import p0.d1;
import p0.m0;
import p0.o0;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_2_1633180.dex */
public abstract class g extends h1 {
    private static final long GRACE_WINDOW_TIME_MS = 10000;
    private static final String KEY_PREFIX_FRAGMENT = "f#";
    private static final String KEY_PREFIX_STATE = "s#";
    final x0 mFragmentManager;
    private f mFragmentMaxLifecycleEnforcer;
    final q.e mFragments;
    private boolean mHasStaleFragments;
    boolean mIsInGracePeriod;
    private final q.e mItemIdToViewHolder;
    final s mLifecycle;
    private final q.e mSavedStates;

    public g(d0 d0Var) {
        x0 supportFragmentManager = d0Var.getSupportFragmentManager();
        s lifecycle = d0Var.getLifecycle();
        this.mFragments = new q.e();
        this.mSavedStates = new q.e();
        this.mItemIdToViewHolder = new q.e();
        this.mIsInGracePeriod = false;
        this.mHasStaleFragments = false;
        this.mFragmentManager = supportFragmentManager;
        this.mLifecycle = lifecycle;
        super.setHasStableIds(true);
    }

    public final Long a(int i7) {
        Long l7 = null;
        for (int i8 = 0; i8 < this.mItemIdToViewHolder.h(); i8++) {
            if (((Integer) this.mItemIdToViewHolder.i(i8)).intValue() == i7) {
                if (l7 != null) {
                    throw new IllegalStateException("Design assumption violated: a ViewHolder can only be bound to one item at a time.");
                }
                l7 = Long.valueOf(this.mItemIdToViewHolder.e(i8));
            }
        }
        return l7;
    }

    public void addViewToContainer(View view, FrameLayout frameLayout) {
        if (frameLayout.getChildCount() > 1) {
            throw new IllegalStateException("Design assumption violated.");
        }
        if (view.getParent() == frameLayout) {
            return;
        }
        if (frameLayout.getChildCount() > 0) {
            frameLayout.removeAllViews();
        }
        if (view.getParent() != null) {
            ((ViewGroup) view.getParent()).removeView(view);
        }
        frameLayout.addView(view);
    }

    public final void b(long j7) {
        ViewParent parent;
        z zVar = (z) this.mFragments.d(j7, null);
        if (zVar == null) {
            return;
        }
        if (zVar.getView() != null && (parent = zVar.getView().getParent()) != null) {
            ((FrameLayout) parent).removeAllViews();
        }
        if (!containsItem(j7)) {
            this.mSavedStates.g(j7);
        }
        if (!zVar.isAdded()) {
            this.mFragments.g(j7);
            return;
        }
        if (shouldDelayFragmentTransactions()) {
            this.mHasStaleFragments = true;
            return;
        }
        if (zVar.isAdded() && containsItem(j7)) {
            this.mSavedStates.f(j7, this.mFragmentManager.O(zVar));
        }
        x0 x0Var = this.mFragmentManager;
        x0Var.getClass();
        androidx.fragment.app.a aVar = new androidx.fragment.app.a(x0Var);
        aVar.i(zVar);
        if (aVar.f1980g) {
            throw new IllegalStateException("This transaction is already being added to the back stack");
        }
        aVar.f1981h = false;
        aVar.f1910q.t(aVar, false);
        this.mFragments.g(j7);
    }

    public boolean containsItem(long j7) {
        return j7 >= 0 && j7 < ((long) getItemCount());
    }

    public abstract z createFragment(int i7);

    public void gcFragments() {
        z zVar;
        View view;
        if (!this.mHasStaleFragments || shouldDelayFragmentTransactions()) {
            return;
        }
        q.c cVar = new q.c(0);
        for (int i7 = 0; i7 < this.mFragments.h(); i7++) {
            long e7 = this.mFragments.e(i7);
            if (!containsItem(e7)) {
                cVar.add(Long.valueOf(e7));
                this.mItemIdToViewHolder.g(e7);
            }
        }
        if (!this.mIsInGracePeriod) {
            this.mHasStaleFragments = false;
            for (int i8 = 0; i8 < this.mFragments.h(); i8++) {
                long e8 = this.mFragments.e(i8);
                q.e eVar = this.mItemIdToViewHolder;
                if (eVar.f8385a) {
                    eVar.c();
                }
                if (q.d.b(eVar.f8386b, eVar.f8388d, e8) < 0 && ((zVar = (z) this.mFragments.d(e8, null)) == null || (view = zVar.getView()) == null || view.getParent() == null)) {
                    cVar.add(Long.valueOf(e8));
                }
            }
        }
        Iterator it = cVar.iterator();
        while (it.hasNext()) {
            b(((Long) it.next()).longValue());
        }
    }

    @Override // androidx.recyclerview.widget.h1
    public long getItemId(int i7) {
        return i7;
    }

    @Override // androidx.recyclerview.widget.h1
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        if (this.mFragmentMaxLifecycleEnforcer != null) {
            throw new IllegalArgumentException();
        }
        f fVar = new f(this);
        this.mFragmentMaxLifecycleEnforcer = fVar;
        ViewPager2 a7 = f.a(recyclerView);
        fVar.f2864d = a7;
        d dVar = new d(0, fVar);
        fVar.f2861a = dVar;
        ((List) a7.f2870c.f2859b).add(dVar);
        b2 b2Var = new b2(fVar);
        fVar.f2862b = b2Var;
        registerAdapterDataObserver(b2Var);
        e eVar = new e(fVar);
        fVar.f2863c = eVar;
        this.mLifecycle.a(eVar);
    }

    @Override // androidx.recyclerview.widget.h1
    public final void onBindViewHolder(h hVar, int i7) {
        long itemId = hVar.getItemId();
        int id = ((FrameLayout) hVar.itemView).getId();
        Long a7 = a(id);
        if (a7 != null && a7.longValue() != itemId) {
            b(a7.longValue());
            this.mItemIdToViewHolder.g(a7.longValue());
        }
        this.mItemIdToViewHolder.f(itemId, Integer.valueOf(id));
        long itemId2 = getItemId(i7);
        q.e eVar = this.mFragments;
        if (eVar.f8385a) {
            eVar.c();
        }
        if (q.d.b(eVar.f8386b, eVar.f8388d, itemId2) < 0) {
            z createFragment = createFragment(i7);
            createFragment.setInitialSavedState((Fragment$SavedState) this.mSavedStates.d(itemId2, null));
            this.mFragments.f(itemId2, createFragment);
        }
        FrameLayout frameLayout = (FrameLayout) hVar.itemView;
        int i8 = d1.OVER_SCROLL_ALWAYS;
        if (o0.b(frameLayout)) {
            if (frameLayout.getParent() != null) {
                throw new IllegalStateException("Design assumption violated.");
            }
            frameLayout.addOnLayoutChangeListener(new a(this, frameLayout, hVar));
        }
        gcFragments();
    }

    @Override // androidx.recyclerview.widget.h1
    public final h onCreateViewHolder(ViewGroup viewGroup, int i7) {
        int i8 = h.f2867a;
        FrameLayout frameLayout = new FrameLayout(viewGroup.getContext());
        frameLayout.setLayoutParams(new ViewGroup.LayoutParams(-1, -1));
        int i9 = d1.OVER_SCROLL_ALWAYS;
        frameLayout.setId(m0.a());
        frameLayout.setSaveEnabled(false);
        return new h(frameLayout);
    }

    @Override // androidx.recyclerview.widget.h1
    public void onDetachedFromRecyclerView(RecyclerView recyclerView) {
        f fVar = this.mFragmentMaxLifecycleEnforcer;
        fVar.getClass();
        ViewPager2 a7 = f.a(recyclerView);
        ((List) a7.f2870c.f2859b).remove(fVar.f2861a);
        b2 b2Var = fVar.f2862b;
        g gVar = fVar.f2866f;
        gVar.unregisterAdapterDataObserver(b2Var);
        gVar.mLifecycle.b(fVar.f2863c);
        fVar.f2864d = null;
        this.mFragmentMaxLifecycleEnforcer = null;
    }

    @Override // androidx.recyclerview.widget.h1
    public final boolean onFailedToRecycleView(h hVar) {
        return true;
    }

    @Override // androidx.recyclerview.widget.h1
    public final void onViewAttachedToWindow(h hVar) {
        placeFragmentInViewHolder(hVar);
        gcFragments();
    }

    @Override // androidx.recyclerview.widget.h1
    public final void onViewRecycled(h hVar) {
        Long a7 = a(((FrameLayout) hVar.itemView).getId());
        if (a7 != null) {
            b(a7.longValue());
            this.mItemIdToViewHolder.g(a7.longValue());
        }
    }

    public void placeFragmentInViewHolder(h hVar) {
        z zVar = (z) this.mFragments.d(hVar.getItemId(), null);
        if (zVar == null) {
            throw new IllegalStateException("Design assumption violated.");
        }
        FrameLayout frameLayout = (FrameLayout) hVar.itemView;
        View view = zVar.getView();
        if (!zVar.isAdded() && view != null) {
            throw new IllegalStateException("Design assumption violated.");
        }
        if (zVar.isAdded() && view == null) {
            ((CopyOnWriteArrayList) this.mFragmentManager.f2114m.f1936a).add(new androidx.fragment.app.o0(new androidx.appcompat.app.g(this, zVar, frameLayout)));
            return;
        }
        if (zVar.isAdded() && view.getParent() != null) {
            if (view.getParent() != frameLayout) {
                addViewToContainer(view, frameLayout);
                return;
            }
            return;
        }
        if (zVar.isAdded()) {
            addViewToContainer(view, frameLayout);
            return;
        }
        if (shouldDelayFragmentTransactions()) {
            if (this.mFragmentManager.C) {
                return;
            }
            this.mLifecycle.a(new androidx.lifecycle.h(this, hVar));
            return;
        }
        ((CopyOnWriteArrayList) this.mFragmentManager.f2114m.f1936a).add(new androidx.fragment.app.o0(new androidx.appcompat.app.g(this, zVar, frameLayout)));
        x0 x0Var = this.mFragmentManager;
        x0Var.getClass();
        androidx.fragment.app.a aVar = new androidx.fragment.app.a(x0Var);
        aVar.c(0, zVar, "f" + hVar.getItemId(), 1);
        aVar.j(zVar, r.STARTED);
        if (aVar.f1980g) {
            throw new IllegalStateException("This transaction is already being added to the back stack");
        }
        aVar.f1981h = false;
        aVar.f1910q.t(aVar, false);
        this.mFragmentMaxLifecycleEnforcer.b(false);
    }

    /* JADX WARN: Code restructure failed: missing block: B:30:0x00c2, code lost:
    
        throw new java.lang.IllegalArgumentException("Unexpected key in savedState: ".concat(r1));
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void restoreState(android.os.Parcelable r9) {
        /*
            r8 = this;
            q.e r0 = r8.mSavedStates
            int r0 = r0.h()
            if (r0 != 0) goto Lf1
            q.e r0 = r8.mFragments
            int r0 = r0.h()
            if (r0 != 0) goto Lf1
            android.os.Bundle r9 = (android.os.Bundle) r9
            java.lang.ClassLoader r0 = r9.getClassLoader()
            if (r0 != 0) goto L23
            java.lang.Class r0 = r8.getClass()
            java.lang.ClassLoader r0 = r0.getClassLoader()
            r9.setClassLoader(r0)
        L23:
            java.util.Set r0 = r9.keySet()
            java.util.Iterator r0 = r0.iterator()
        L2b:
            boolean r1 = r0.hasNext()
            r2 = 1
            if (r1 == 0) goto Lc3
            java.lang.Object r1 = r0.next()
            java.lang.String r1 = (java.lang.String) r1
            java.lang.String r3 = "f#"
            boolean r3 = r1.startsWith(r3)
            r4 = 2
            if (r3 == 0) goto L48
            int r3 = r1.length()
            if (r3 <= r4) goto L48
            goto L49
        L48:
            r2 = 0
        L49:
            if (r2 == 0) goto L8e
            java.lang.String r2 = r1.substring(r4)
            long r2 = java.lang.Long.parseLong(r2)
            androidx.fragment.app.x0 r4 = r8.mFragmentManager
            r4.getClass()
            java.lang.String r5 = r9.getString(r1)
            r6 = 0
            if (r5 != 0) goto L60
            goto L69
        L60:
            androidx.fragment.app.e1 r7 = r4.f2104c
            androidx.fragment.app.z r7 = r7.b(r5)
            if (r7 == 0) goto L6f
            r6 = r7
        L69:
            q.e r1 = r8.mFragments
            r1.f(r2, r6)
            goto L2b
        L6f:
            java.lang.IllegalStateException r9 = new java.lang.IllegalStateException
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            java.lang.String r2 = "Fragment no longer exists for key "
            r0.<init>(r2)
            r0.append(r1)
            java.lang.String r1 = ": unique id "
            r0.append(r1)
            r0.append(r5)
            java.lang.String r0 = r0.toString()
            r9.<init>(r0)
            r4.U(r9)
            throw r6
        L8e:
            java.lang.String r2 = "s#"
            boolean r2 = r1.startsWith(r2)
            if (r2 == 0) goto Lb7
            int r2 = r1.length()
            if (r2 <= r4) goto Lb7
            java.lang.String r2 = r1.substring(r4)
            long r2 = java.lang.Long.parseLong(r2)
            android.os.Parcelable r1 = r9.getParcelable(r1)
            androidx.fragment.app.Fragment$SavedState r1 = (androidx.fragment.app.Fragment$SavedState) r1
            boolean r4 = r8.containsItem(r2)
            if (r4 == 0) goto L2b
            q.e r4 = r8.mSavedStates
            r4.f(r2, r1)
            goto L2b
        Lb7:
            java.lang.IllegalArgumentException r9 = new java.lang.IllegalArgumentException
            java.lang.String r0 = "Unexpected key in savedState: "
            java.lang.String r0 = r0.concat(r1)
            r9.<init>(r0)
            throw r9
        Lc3:
            q.e r9 = r8.mFragments
            int r9 = r9.h()
            if (r9 != 0) goto Lcc
            goto Lf0
        Lcc:
            r8.mHasStaleFragments = r2
            r8.mIsInGracePeriod = r2
            r8.gcFragments()
            android.os.Handler r9 = new android.os.Handler
            android.os.Looper r0 = android.os.Looper.getMainLooper()
            r9.<init>(r0)
            androidx.viewpager2.adapter.b r0 = new androidx.viewpager2.adapter.b
            r0.<init>(r8)
            androidx.lifecycle.s r1 = r8.mLifecycle
            androidx.viewpager2.adapter.c r2 = new androidx.viewpager2.adapter.c
            r2.<init>(r9, r0)
            r1.a(r2)
            r1 = 10000(0x2710, double:4.9407E-320)
            r9.postDelayed(r0, r1)
        Lf0:
            return
        Lf1:
            java.lang.IllegalStateException r9 = new java.lang.IllegalStateException
            java.lang.String r0 = "Expected the adapter to be 'fresh' while restoring state."
            r9.<init>(r0)
            throw r9
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.viewpager2.adapter.g.restoreState(android.os.Parcelable):void");
    }

    public final Parcelable saveState() {
        Bundle bundle = new Bundle(this.mSavedStates.h() + this.mFragments.h());
        for (int i7 = 0; i7 < this.mFragments.h(); i7++) {
            long e7 = this.mFragments.e(i7);
            z zVar = (z) this.mFragments.d(e7, null);
            if (zVar != null && zVar.isAdded()) {
                this.mFragmentManager.J(bundle, KEY_PREFIX_FRAGMENT + e7, zVar);
            }
        }
        for (int i8 = 0; i8 < this.mSavedStates.h(); i8++) {
            long e8 = this.mSavedStates.e(i8);
            if (containsItem(e8)) {
                bundle.putParcelable(KEY_PREFIX_STATE + e8, (Parcelable) this.mSavedStates.d(e8, null));
            }
        }
        return bundle;
    }

    @Override // androidx.recyclerview.widget.h1
    public final void setHasStableIds(boolean z6) {
        throw new UnsupportedOperationException("Stable Ids are required for the adapter to function properly, and the adapter takes care of setting the flag.");
    }

    public boolean shouldDelayFragmentTransactions() {
        return this.mFragmentManager.D();
    }
}
