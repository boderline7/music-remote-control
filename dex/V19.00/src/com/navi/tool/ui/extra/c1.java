package com.navi.tool.ui.extra;

import android.content.DialogInterface;
import android.content.pm.PackageInfo;
import com.navi.tool.ui.extra.DesktopAssistantActivity;
import com.navi.tool.ui.extra.FileManagerActivity;
import com.navi.tool.utils.Pan123Api;
import java.io.File;
import java.util.List;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final /* synthetic */ class c1 implements DialogInterface.OnClickListener {

    /* renamed from: a, reason: collision with root package name */
    public final /* synthetic */ int f5064a;

    /* renamed from: b, reason: collision with root package name */
    public final /* synthetic */ Object f5065b;

    /* renamed from: c, reason: collision with root package name */
    public final /* synthetic */ Object f5066c;

    public /* synthetic */ c1(int i7, Object obj, Object obj2) {
        this.f5064a = i7;
        this.f5065b = obj;
        this.f5066c = obj2;
    }

    @Override // android.content.DialogInterface.OnClickListener
    public final void onClick(DialogInterface dialogInterface, int i7) {
        int i8 = this.f5064a;
        Object obj = this.f5066c;
        Object obj2 = this.f5065b;
        switch (i8) {
            case 0:
                ((DesktopAssistantActivity.DesktopAppAdapter) obj2).lambda$onBindViewHolder$1((DesktopAssistantActivity.AppItem) obj, dialogInterface, i7);
                break;
            case 1:
                ((FileManagerActivity.AnonymousClass4) obj2).lambda$onMessageReceived$2((String) obj, dialogInterface, i7);
                break;
            case 2:
                ((FileManagerActivity.FileAdapter) obj2).lambda$onBindViewHolder$1((Pan123Api.CloudFileInfo) obj, dialogInterface, i7);
                break;
            case 3:
                ((DesktopAssistantFragment) obj2).lambda$initLogic$45((List) obj, dialogInterface, i7);
                break;
            case 4:
                ((DesktopAssistantFragment) obj2).lambda$initLogic$42((File[]) obj, dialogInterface, i7);
                break;
            case 5:
                ((DesktopAssistantFragment) obj2).lambda$showVideoWallpaperMenuDialog$72((String) obj, dialogInterface, i7);
                break;
            default:
                ((FileManagerActivity) obj2).lambda$showAppActionDialog$112((PackageInfo) obj, dialogInterface, i7);
                break;
        }
    }
}
