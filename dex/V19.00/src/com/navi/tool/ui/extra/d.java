package com.navi.tool.ui.extra;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.Context;
import android.net.Uri;
import android.view.KeyEvent;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import androidx.recyclerview.widget.RecyclerView;
import java.io.File;
import java.util.List;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final /* synthetic */ class d implements Runnable {

    /* renamed from: a, reason: collision with root package name */
    public final /* synthetic */ int f5080a;

    /* renamed from: b, reason: collision with root package name */
    public final /* synthetic */ androidx.fragment.app.z f5081b;

    /* renamed from: c, reason: collision with root package name */
    public final /* synthetic */ Object f5082c;

    /* renamed from: d, reason: collision with root package name */
    public final /* synthetic */ Object f5083d;

    /* renamed from: e, reason: collision with root package name */
    public final /* synthetic */ KeyEvent.Callback f5084e;

    /* renamed from: f, reason: collision with root package name */
    public final /* synthetic */ Object f5085f;

    /* renamed from: g, reason: collision with root package name */
    public final /* synthetic */ Object f5086g;

    /* renamed from: h, reason: collision with root package name */
    public final /* synthetic */ Object f5087h;

    public /* synthetic */ d(androidx.fragment.app.z zVar, Object obj, Object obj2, KeyEvent.Callback callback, Object obj3, Object obj4, Object obj5, int i7) {
        this.f5080a = i7;
        this.f5081b = zVar;
        this.f5082c = obj;
        this.f5083d = obj2;
        this.f5084e = callback;
        this.f5085f = obj3;
        this.f5086g = obj4;
        this.f5087h = obj5;
    }

    @Override // java.lang.Runnable
    public final void run() {
        int i7 = this.f5080a;
        Object obj = this.f5087h;
        Object obj2 = this.f5086g;
        Object obj3 = this.f5085f;
        KeyEvent.Callback callback = this.f5084e;
        Object obj4 = this.f5083d;
        Object obj5 = this.f5082c;
        androidx.fragment.app.z zVar = this.f5081b;
        switch (i7) {
            case 0:
                ((AdditionalFeaturesFragment) zVar).lambda$showSelectPanelAppsDialog$32((List) obj5, (int[]) obj4, (RecyclerView) callback, (RadioGroup) obj3, (RadioButton) obj2, (RadioButton) obj);
                break;
            default:
                ((DesktopAssistantFragment) zVar).lambda$saveVideoWallpaper$66((Context) obj5, (Uri) obj4, (Activity) callback, (File) obj3, (String) obj2, (ContentResolver) obj);
                break;
        }
    }
}
