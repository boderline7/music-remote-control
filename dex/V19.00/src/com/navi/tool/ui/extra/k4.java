package com.navi.tool.ui.extra;

import android.view.View;
import com.navi.tool.ui.extra.DesktopAssistantMusicUiHelper;
import com.navi.tool.ui.extra.FileManagerActivity;
import com.navi.tool.ui.extra.StorageAnalysisActivity;
import java.io.File;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final /* synthetic */ class k4 implements View.OnClickListener {

    /* renamed from: a, reason: collision with root package name */
    public final /* synthetic */ int f5284a;

    /* renamed from: b, reason: collision with root package name */
    public final /* synthetic */ Object f5285b;

    /* renamed from: c, reason: collision with root package name */
    public final /* synthetic */ Object f5286c;

    /* renamed from: d, reason: collision with root package name */
    public final /* synthetic */ Object f5287d;

    public /* synthetic */ k4(Object obj, Object obj2, Object obj3, int i7) {
        this.f5284a = i7;
        this.f5285b = obj;
        this.f5286c = obj2;
        this.f5287d = obj3;
    }

    @Override // android.view.View.OnClickListener
    public final void onClick(View view) {
        int i7 = this.f5284a;
        Object obj = this.f5287d;
        Object obj2 = this.f5286c;
        Object obj3 = this.f5285b;
        switch (i7) {
            case 0:
                ((FileManagerActivity.FileAdapter) obj3).lambda$onBindViewHolder$0((FileManagerActivity.FileAdapter.ViewHolder) obj2, (FileManagerActivity.FileItem) obj, view);
                break;
            case 1:
                ((StorageAnalysisActivity.LargeFileAdapter) obj3).lambda$onBindViewHolder$2((StorageAnalysisActivity.FileItem) obj2, (StorageAnalysisActivity.LargeFileAdapter.ViewHolder) obj, view);
                break;
            case 2:
                ((DesktopAssistantActivity) obj3).lambda$showMusicSourcePickerDialog$37((DesktopAssistantMusicUiHelper.MusicSourceCandidate) obj2, (androidx.appcompat.app.o) obj, view);
                break;
            default:
                ((FileManagerActivity) obj3).lambda$showUnzipOptionsDialog$83((File) obj2, (androidx.appcompat.app.o) obj, view);
                break;
        }
    }
}
