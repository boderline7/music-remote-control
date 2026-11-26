package com.google.android.material.search;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.view.View;
import com.yalantis.ucrop.view.CropImageView;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_2_1633180.dex */
public final class m extends AnimatorListenerAdapter {

    /* renamed from: a, reason: collision with root package name */
    public final /* synthetic */ int f4099a;

    /* renamed from: b, reason: collision with root package name */
    public final /* synthetic */ n f4100b;

    public /* synthetic */ m(n nVar, int i7) {
        this.f4099a = i7;
        this.f4100b = nVar;
    }

    @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
    public final void onAnimationEnd(Animator animator) {
        int i7 = this.f4099a;
        n nVar = this.f4100b;
        switch (i7) {
            case 0:
                boolean h7 = nVar.f4101a.h();
                SearchView searchView = nVar.f4101a;
                if (!h7) {
                    searchView.j();
                }
                searchView.setTransitionState(j.SHOWN);
                break;
            case 1:
                nVar.f4103c.setVisibility(8);
                SearchView searchView2 = nVar.f4101a;
                if (!searchView2.h()) {
                    searchView2.f();
                }
                searchView2.setTransitionState(j.HIDDEN);
                break;
            case 2:
                boolean h8 = nVar.f4101a.h();
                SearchView searchView3 = nVar.f4101a;
                if (!h8) {
                    searchView3.j();
                }
                searchView3.setTransitionState(j.SHOWN);
                break;
            default:
                nVar.f4103c.setVisibility(8);
                SearchView searchView4 = nVar.f4101a;
                if (!searchView4.h()) {
                    searchView4.f();
                }
                searchView4.setTransitionState(j.HIDDEN);
                break;
        }
    }

    @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
    public final void onAnimationStart(Animator animator) {
        int i7 = this.f4099a;
        n nVar = this.f4100b;
        switch (i7) {
            case 0:
                nVar.f4103c.setVisibility(0);
                SearchBar searchBar = nVar.f4115o;
                searchBar.f4040a0.getClass();
                View centerView = searchBar.getCenterView();
                if (centerView != null) {
                    centerView.setAlpha(CropImageView.DEFAULT_ASPECT_RATIO);
                    break;
                }
                break;
            case 1:
                nVar.f4101a.setTransitionState(j.HIDING);
                break;
            case 2:
                nVar.f4103c.setVisibility(0);
                nVar.f4101a.setTransitionState(j.SHOWING);
                break;
            default:
                nVar.f4101a.setTransitionState(j.HIDING);
                break;
        }
    }
}
