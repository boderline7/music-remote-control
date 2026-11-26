package com.navi.tool.utils;

import android.content.Context;
import android.widget.Button;
import android.widget.TextView;
import com.navi.tool.utils.UpdateManager;
import java.io.IOException;
import s5.x0;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final /* synthetic */ class q implements Runnable {

    /* renamed from: a, reason: collision with root package name */
    public final /* synthetic */ int f5886a;

    /* renamed from: b, reason: collision with root package name */
    public final /* synthetic */ Object f5887b;

    /* renamed from: c, reason: collision with root package name */
    public final /* synthetic */ Object f5888c;

    /* renamed from: d, reason: collision with root package name */
    public final /* synthetic */ Object f5889d;

    public /* synthetic */ q(Object obj, Object obj2, Object obj3, int i7) {
        this.f5886a = i7;
        this.f5887b = obj;
        this.f5889d = obj2;
        this.f5888c = obj3;
    }

    @Override // java.lang.Runnable
    public final void run() {
        int i7 = this.f5886a;
        Object obj = this.f5888c;
        Object obj2 = this.f5889d;
        Object obj3 = this.f5887b;
        switch (i7) {
            case 0:
                UpdateManager.AnonymousClass1.lambda$onResponse$1((TextView) obj3, (x0) obj2, (Button) obj);
                break;
            case 1:
                UpdateManager.AnonymousClass1.lambda$onResponse$5((TextView) obj3, (Exception) obj2, (Button) obj);
                break;
            case 2:
                UpdateManager.AnonymousClass1.lambda$onFailure$0((TextView) obj3, (IOException) obj2, (Button) obj);
                break;
            default:
                AccessibilityGuard.lambda$rebindIfNeeded$1((String) obj3, (String) obj2, (Context) obj);
                break;
        }
    }
}
