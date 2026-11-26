package com.navi.tool.ui.extra;

import android.view.KeyEvent;
import android.widget.ProgressBar;
import android.widget.TextView;
import com.navi.tool.ui.extra.FileManagerActivity;
import java.io.File;
import java.util.List;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final /* synthetic */ class g4 implements Runnable {

    /* renamed from: a, reason: collision with root package name */
    public final /* synthetic */ int f5181a;

    /* renamed from: b, reason: collision with root package name */
    public final /* synthetic */ int f5182b;

    /* renamed from: c, reason: collision with root package name */
    public final /* synthetic */ Object f5183c;

    /* renamed from: d, reason: collision with root package name */
    public final /* synthetic */ Object f5184d;

    public /* synthetic */ g4(KeyEvent.Callback callback, int i7, Object obj, int i8) {
        this.f5181a = i8;
        this.f5183c = callback;
        this.f5182b = i7;
        this.f5184d = obj;
    }

    public /* synthetic */ g4(Object obj, Object obj2, int i7, int i8) {
        this.f5181a = i8;
        this.f5183c = obj;
        this.f5184d = obj2;
        this.f5182b = i7;
    }

    @Override // java.lang.Runnable
    public final void run() {
        int i7 = this.f5181a;
        int i8 = this.f5182b;
        Object obj = this.f5184d;
        Object obj2 = this.f5183c;
        switch (i7) {
            case 0:
                ((FileManagerActivity.AnonymousClass4) obj2).lambda$onFileProgress$4((String) obj, i8);
                break;
            case 1:
                ((DesktopAssistantActivity) obj2).lambda$updateCoverGradientFromBitmap$45(i8, (int[]) obj);
                break;
            case 2:
                FileManagerActivity.lambda$uploadToCloudDisk$93((ProgressBar) obj2, i8, (TextView) obj);
                break;
            case 3:
                ((FileManagerActivity) obj2).lambda$refreshCloudBackupAvailability$7(i8, (List) obj);
                break;
            default:
                ((FileManagerActivity) obj2).lambda$loadFiles$77((File) obj, i8);
                break;
        }
    }
}
