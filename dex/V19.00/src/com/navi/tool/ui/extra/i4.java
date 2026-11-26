package com.navi.tool.ui.extra;

import android.graphics.Bitmap;
import com.navi.tool.ui.extra.FileManagerActivity;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final /* synthetic */ class i4 implements Runnable {

    /* renamed from: a, reason: collision with root package name */
    public final /* synthetic */ int f5230a;

    /* renamed from: b, reason: collision with root package name */
    public final /* synthetic */ FileManagerActivity.FileAdapter.ViewHolder f5231b;

    /* renamed from: c, reason: collision with root package name */
    public final /* synthetic */ String f5232c;

    /* renamed from: d, reason: collision with root package name */
    public final /* synthetic */ Bitmap f5233d;

    public /* synthetic */ i4(FileManagerActivity.FileAdapter.ViewHolder viewHolder, String str, Bitmap bitmap, int i7) {
        this.f5230a = i7;
        this.f5231b = viewHolder;
        this.f5232c = str;
        this.f5233d = bitmap;
    }

    @Override // java.lang.Runnable
    public final void run() {
        int i7 = this.f5230a;
        FileManagerActivity.FileAdapter.ViewHolder viewHolder = this.f5231b;
        Bitmap bitmap = this.f5233d;
        String str = this.f5232c;
        switch (i7) {
            case 0:
                FileManagerActivity.FileAdapter.lambda$loadVideoThumbnail$5(viewHolder, str, bitmap);
                break;
            default:
                FileManagerActivity.FileAdapter.lambda$loadVideoThumbnail$7(viewHolder, str, bitmap);
                break;
        }
    }
}
