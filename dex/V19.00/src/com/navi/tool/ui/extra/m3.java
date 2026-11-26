package com.navi.tool.ui.extra;

import com.navi.tool.ui.extra.FileManagerActivity;
import java.io.File;
import java.util.Comparator;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final /* synthetic */ class m3 implements Comparator {

    /* renamed from: a, reason: collision with root package name */
    public final /* synthetic */ int f5335a;

    /* renamed from: b, reason: collision with root package name */
    public final /* synthetic */ FileManagerActivity f5336b;

    public /* synthetic */ m3(FileManagerActivity fileManagerActivity, int i7) {
        this.f5335a = i7;
        this.f5336b = fileManagerActivity;
    }

    @Override // java.util.Comparator
    public final int compare(Object obj, Object obj2) {
        int lambda$sortFiles$72;
        int lambda$sortFileItemsByName$74;
        int lambda$sortFileItems$73;
        int i7 = this.f5335a;
        FileManagerActivity fileManagerActivity = this.f5336b;
        switch (i7) {
            case 0:
                lambda$sortFiles$72 = fileManagerActivity.lambda$sortFiles$72((File) obj, (File) obj2);
                return lambda$sortFiles$72;
            case 1:
                lambda$sortFileItemsByName$74 = fileManagerActivity.lambda$sortFileItemsByName$74((FileManagerActivity.FileItem) obj, (FileManagerActivity.FileItem) obj2);
                return lambda$sortFileItemsByName$74;
            default:
                lambda$sortFileItems$73 = fileManagerActivity.lambda$sortFileItems$73((FileManagerActivity.FileItem) obj, (FileManagerActivity.FileItem) obj2);
                return lambda$sortFileItems$73;
        }
    }
}
