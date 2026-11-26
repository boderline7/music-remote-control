package com.navi.tool.ui.extra;

import android.widget.ProgressBar;
import android.widget.TextView;
import com.navi.tool.utils.MusicCoverLoader;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final /* synthetic */ class p3 implements Runnable {

    /* renamed from: a, reason: collision with root package name */
    public final /* synthetic */ int f5396a = 1;

    /* renamed from: b, reason: collision with root package name */
    public final /* synthetic */ int f5397b;

    /* renamed from: c, reason: collision with root package name */
    public final /* synthetic */ String f5398c;

    /* renamed from: d, reason: collision with root package name */
    public final /* synthetic */ String f5399d;

    /* renamed from: e, reason: collision with root package name */
    public final /* synthetic */ Object f5400e;

    /* renamed from: f, reason: collision with root package name */
    public final /* synthetic */ Object f5401f;

    /* renamed from: g, reason: collision with root package name */
    public final /* synthetic */ Object f5402g;

    /* renamed from: h, reason: collision with root package name */
    public final /* synthetic */ Object f5403h;

    public /* synthetic */ p3(int i7, String str, String str2, String str3, String str4, String str5, MusicCoverLoader.Callback callback) {
        this.f5397b = i7;
        this.f5398c = str;
        this.f5399d = str2;
        this.f5400e = str3;
        this.f5401f = str4;
        this.f5402g = str5;
        this.f5403h = callback;
    }

    public /* synthetic */ p3(FileManagerActivity fileManagerActivity, TextView textView, String str, String str2, ProgressBar progressBar, int i7, TextView textView2) {
        this.f5400e = fileManagerActivity;
        this.f5401f = textView;
        this.f5398c = str;
        this.f5399d = str2;
        this.f5403h = progressBar;
        this.f5397b = i7;
        this.f5402g = textView2;
    }

    @Override // java.lang.Runnable
    public final void run() {
        int i7 = this.f5396a;
        Object obj = this.f5403h;
        Object obj2 = this.f5402g;
        Object obj3 = this.f5401f;
        Object obj4 = this.f5400e;
        switch (i7) {
            case 0:
                ((FileManagerActivity) obj4).lambda$executePaste$102((TextView) obj3, this.f5398c, this.f5399d, (ProgressBar) obj, this.f5397b, (TextView) obj2);
                break;
            default:
                MusicCoverLoader.lambda$loadCover$0(this.f5397b, this.f5398c, this.f5399d, (String) obj4, (String) obj3, (String) obj2, (MusicCoverLoader.Callback) obj);
                break;
        }
    }
}
