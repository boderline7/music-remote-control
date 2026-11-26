package com.google.android.material.textfield;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.widget.TextView;
import androidx.appcompat.widget.AppCompatTextView;
import com.yalantis.ucrop.view.CropImageView;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_2_1633180.dex */
public final class n extends AnimatorListenerAdapter {

    /* renamed from: a, reason: collision with root package name */
    public final /* synthetic */ int f4384a;

    /* renamed from: b, reason: collision with root package name */
    public final /* synthetic */ TextView f4385b;

    /* renamed from: c, reason: collision with root package name */
    public final /* synthetic */ int f4386c;

    /* renamed from: d, reason: collision with root package name */
    public final /* synthetic */ TextView f4387d;

    /* renamed from: e, reason: collision with root package name */
    public final /* synthetic */ o f4388e;

    public n(o oVar, int i7, TextView textView, int i8, TextView textView2) {
        this.f4388e = oVar;
        this.f4384a = i7;
        this.f4385b = textView;
        this.f4386c = i8;
        this.f4387d = textView2;
    }

    @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
    public final void onAnimationEnd(Animator animator) {
        AppCompatTextView appCompatTextView;
        int i7 = this.f4384a;
        o oVar = this.f4388e;
        oVar.f4402n = i7;
        oVar.f4400l = null;
        TextView textView = this.f4385b;
        if (textView != null) {
            textView.setVisibility(4);
            if (this.f4386c == 1 && (appCompatTextView = oVar.f4406r) != null) {
                appCompatTextView.setText((CharSequence) null);
            }
        }
        TextView textView2 = this.f4387d;
        if (textView2 != null) {
            textView2.setTranslationY(CropImageView.DEFAULT_ASPECT_RATIO);
            textView2.setAlpha(1.0f);
        }
    }

    @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
    public final void onAnimationStart(Animator animator) {
        TextView textView = this.f4387d;
        if (textView != null) {
            textView.setVisibility(0);
            textView.setAlpha(CropImageView.DEFAULT_ASPECT_RATIO);
        }
    }
}
