package com.navi.tool.ui.extra;

import android.view.View;
import android.widget.CheckBox;
import android.widget.Spinner;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final /* synthetic */ class r3 implements View.OnClickListener {

    /* renamed from: a, reason: collision with root package name */
    public final /* synthetic */ int f5433a;

    /* renamed from: b, reason: collision with root package name */
    public final /* synthetic */ Object f5434b;

    /* renamed from: c, reason: collision with root package name */
    public final /* synthetic */ androidx.appcompat.app.o f5435c;

    /* renamed from: d, reason: collision with root package name */
    public final /* synthetic */ Object f5436d;

    /* renamed from: e, reason: collision with root package name */
    public final /* synthetic */ Object f5437e;

    /* renamed from: f, reason: collision with root package name */
    public final /* synthetic */ Object f5438f;

    public /* synthetic */ r3(DesktopAssistantCarControlHelper desktopAssistantCarControlHelper, Spinner spinner, ArrayList arrayList, Set set, androidx.appcompat.app.o oVar) {
        this.f5433a = 3;
        this.f5434b = desktopAssistantCarControlHelper;
        this.f5436d = spinner;
        this.f5437e = arrayList;
        this.f5438f = set;
        this.f5435c = oVar;
    }

    public /* synthetic */ r3(FileManagerActivity fileManagerActivity, androidx.appcompat.app.o oVar, int[] iArr, CheckBox checkBox, boolean[] zArr, int i7) {
        this.f5433a = i7;
        this.f5434b = fileManagerActivity;
        this.f5435c = oVar;
        this.f5436d = iArr;
        this.f5437e = checkBox;
        this.f5438f = zArr;
    }

    @Override // android.view.View.OnClickListener
    public final void onClick(View view) {
        int i7 = this.f5433a;
        Object obj = this.f5438f;
        Object obj2 = this.f5437e;
        Object obj3 = this.f5436d;
        Object obj4 = this.f5434b;
        switch (i7) {
            case 0:
                ((FileManagerActivity) obj4).lambda$promptForConflict$106(this.f5435c, (int[]) obj3, (CheckBox) obj2, (boolean[]) obj, view);
                break;
            case 1:
                ((FileManagerActivity) obj4).lambda$promptForConflict$107(this.f5435c, (int[]) obj3, (CheckBox) obj2, (boolean[]) obj, view);
                break;
            case 2:
                ((FileManagerActivity) obj4).lambda$promptForConflict$108(this.f5435c, (int[]) obj3, (CheckBox) obj2, (boolean[]) obj, view);
                break;
            default:
                ((DesktopAssistantCarControlHelper) obj4).lambda$showSeatPanelOrderDialog$55((Spinner) obj3, (List) obj2, (Set) obj, this.f5435c, view);
                break;
        }
    }
}
