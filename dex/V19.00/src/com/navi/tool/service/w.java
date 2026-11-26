package com.navi.tool.service;

import java.io.IOException;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final /* synthetic */ class w implements Runnable {

    /* renamed from: a, reason: collision with root package name */
    public final /* synthetic */ int f4937a;

    /* renamed from: b, reason: collision with root package name */
    public final /* synthetic */ IOException f4938b;

    public /* synthetic */ w(IOException iOException, int i7) {
        this.f4937a = i7;
        this.f4938b = iOException;
    }

    @Override // java.lang.Runnable
    public final void run() {
        int i7 = this.f4937a;
        IOException iOException = this.f4938b;
        switch (i7) {
            case 0:
                iOException.getMessage();
                break;
            case 1:
                iOException.getMessage();
                break;
            default:
                iOException.getMessage();
                break;
        }
    }
}
