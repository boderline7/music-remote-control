package com.navi.tool.ui.extra;

import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import com.navi.tool.model.CloudDiskCredential;
import com.navi.tool.ui.extra.DesktopAssistantActivity;
import com.navi.tool.ui.extra.FileManagerActivity;
import com.navi.tool.ui.extra.LyricsAdapter;
import com.navi.tool.ui.extra.PermissionManagerActivity;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final /* synthetic */ class d1 implements View.OnClickListener {

    /* renamed from: a, reason: collision with root package name */
    public final /* synthetic */ int f5095a;

    /* renamed from: b, reason: collision with root package name */
    public final /* synthetic */ Object f5096b;

    /* renamed from: c, reason: collision with root package name */
    public final /* synthetic */ Object f5097c;

    public /* synthetic */ d1(int i7, Object obj, Object obj2) {
        this.f5095a = i7;
        this.f5096b = obj;
        this.f5097c = obj2;
    }

    @Override // android.view.View.OnClickListener
    public final void onClick(View view) {
        int i7 = this.f5095a;
        Object obj = this.f5097c;
        Object obj2 = this.f5096b;
        switch (i7) {
            case 0:
                ((DesktopAssistantActivity.DesktopAppAdapter) obj2).lambda$onBindViewHolder$0((DesktopAssistantActivity.AppItem) obj, view);
                break;
            case 1:
                ((FileManagerActivity.InstalledAppAdapter) obj2).lambda$onBindViewHolder$0((PackageInfo) obj, view);
                break;
            case 2:
                ((LyricsAdapter) obj2).lambda$onCreateViewHolder$0((LyricsAdapter.LyricViewHolder) obj, view);
                break;
            case 3:
                ((PermissionManagerActivity.AppAdapter) obj2).lambda$onBindViewHolder$0((PermissionManagerActivity.AppInfo) obj, view);
                break;
            case 4:
                ((FileManagerActivity) obj2).lambda$refreshCloudDisks$10((CloudDiskCredential) obj, view);
                break;
            case 5:
                ((FileManagerActivity) obj2).lambda$executePaste$99((Button) obj, view);
                break;
            case 6:
                ((FileManagerActivity) obj2).lambda$showSendDialog$27((EditText) obj, view);
                break;
            case 7:
                ((FileManagerActivity) obj2).lambda$showSendDialog$28((androidx.appcompat.app.o) obj, view);
                break;
            default:
                ((InstalledAppAdapter) obj2).lambda$onBindViewHolder$0((ApplicationInfo) obj, view);
                break;
        }
    }
}
