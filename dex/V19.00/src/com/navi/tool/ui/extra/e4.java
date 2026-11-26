package com.navi.tool.ui.extra;

import com.navi.tool.ui.extra.FileManagerActivity;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final /* synthetic */ class e4 implements Runnable {

    /* renamed from: a, reason: collision with root package name */
    public final /* synthetic */ int f5137a;

    /* renamed from: b, reason: collision with root package name */
    public final /* synthetic */ FileManagerActivity.AnonymousClass4 f5138b;

    /* renamed from: c, reason: collision with root package name */
    public final /* synthetic */ String f5139c;

    public /* synthetic */ e4(FileManagerActivity.AnonymousClass4 anonymousClass4, String str, int i7) {
        this.f5137a = i7;
        this.f5138b = anonymousClass4;
        this.f5139c = str;
    }

    @Override // java.lang.Runnable
    public final void run() {
        int i7 = this.f5137a;
        String str = this.f5139c;
        FileManagerActivity.AnonymousClass4 anonymousClass4 = this.f5138b;
        switch (i7) {
            case 0:
                anonymousClass4.lambda$onMessageReceived$3(str);
                break;
            case 1:
                anonymousClass4.lambda$onConnected$1(str);
                break;
            default:
                anonymousClass4.lambda$onError$6(str);
                break;
        }
    }
}
