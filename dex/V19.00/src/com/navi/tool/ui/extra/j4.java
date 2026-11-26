package com.navi.tool.ui.extra;

import android.graphics.drawable.Drawable;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import androidx.recyclerview.widget.RecyclerView;
import com.navi.tool.ui.extra.FileManagerActivity;
import com.navi.tool.ui.extra.PermissionManagerActivity;
import java.io.File;
import java.util.List;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final /* synthetic */ class j4 implements Runnable {

    /* renamed from: a, reason: collision with root package name */
    public final /* synthetic */ int f5258a;

    /* renamed from: b, reason: collision with root package name */
    public final /* synthetic */ Object f5259b;

    /* renamed from: c, reason: collision with root package name */
    public final /* synthetic */ Object f5260c;

    /* renamed from: d, reason: collision with root package name */
    public final /* synthetic */ Object f5261d;

    /* renamed from: e, reason: collision with root package name */
    public final /* synthetic */ Object f5262e;

    /* renamed from: f, reason: collision with root package name */
    public final /* synthetic */ Object f5263f;

    public /* synthetic */ j4(FileManagerActivity.FileAdapter.ViewHolder viewHolder, File file, Drawable drawable, String str, String str2) {
        this.f5258a = 1;
        this.f5259b = viewHolder;
        this.f5260c = file;
        this.f5262e = drawable;
        this.f5261d = str;
        this.f5263f = str2;
    }

    public /* synthetic */ j4(FileManagerActivity.FileAdapter fileAdapter, File file, String str, File file2, FileManagerActivity.FileAdapter.ViewHolder viewHolder) {
        this.f5258a = 0;
        this.f5262e = fileAdapter;
        this.f5260c = file;
        this.f5261d = str;
        this.f5263f = file2;
        this.f5259b = viewHolder;
    }

    public /* synthetic */ j4(Object obj, Object obj2, LinearLayout linearLayout, Object obj3, Object obj4, int i7) {
        this.f5258a = i7;
        this.f5262e = obj;
        this.f5260c = obj2;
        this.f5263f = linearLayout;
        this.f5261d = obj3;
        this.f5259b = obj4;
    }

    @Override // java.lang.Runnable
    public final void run() {
        int i7 = this.f5258a;
        Object obj = this.f5259b;
        Object obj2 = this.f5261d;
        Object obj3 = this.f5263f;
        Object obj4 = this.f5260c;
        Object obj5 = this.f5262e;
        switch (i7) {
            case 0:
                ((FileManagerActivity.FileAdapter) obj5).lambda$loadVideoThumbnail$8((File) obj4, (String) obj2, (File) obj3, (FileManagerActivity.FileAdapter.ViewHolder) obj);
                break;
            case 1:
                FileManagerActivity.FileAdapter.lambda$loadApkInfo$9((FileManagerActivity.FileAdapter.ViewHolder) obj, (File) obj4, (Drawable) obj5, (String) obj2, (String) obj3);
                break;
            case 2:
                ((PermissionManagerActivity) obj5).lambda$showPermissionDialog$44((PermissionManagerActivity.AppInfo) obj4, (LinearLayout) obj3, (List) obj2, (List) obj);
                break;
            default:
                ((AdditionalFeaturesFragment) obj5).lambda$showSelectPanelAppsDialog$33((RecyclerView) obj4, (RadioGroup) obj3, (RadioButton) obj2, (RadioButton) obj);
                break;
        }
    }
}
