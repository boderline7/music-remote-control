package com.navi.tool.ui.extra;

import android.content.DialogInterface;
import com.navi.tool.ui.extra.DesktopAssistantFragment;
import java.io.File;
import java.util.List;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final /* synthetic */ class u2 implements DialogInterface.OnClickListener {

    /* renamed from: a, reason: collision with root package name */
    public final /* synthetic */ int f5486a;

    /* renamed from: b, reason: collision with root package name */
    public final /* synthetic */ File f5487b;

    /* renamed from: c, reason: collision with root package name */
    public final /* synthetic */ androidx.recyclerview.widget.j2 f5488c;

    /* renamed from: d, reason: collision with root package name */
    public final /* synthetic */ List f5489d;

    /* renamed from: e, reason: collision with root package name */
    public final /* synthetic */ boolean[] f5490e;

    /* renamed from: f, reason: collision with root package name */
    public final /* synthetic */ androidx.appcompat.app.o f5491f;

    /* renamed from: g, reason: collision with root package name */
    public final /* synthetic */ androidx.recyclerview.widget.h1 f5492g;

    public /* synthetic */ u2(androidx.recyclerview.widget.h1 h1Var, File file, androidx.recyclerview.widget.j2 j2Var, List list, boolean[] zArr, androidx.appcompat.app.o oVar, int i7) {
        this.f5486a = i7;
        this.f5492g = h1Var;
        this.f5487b = file;
        this.f5488c = j2Var;
        this.f5489d = list;
        this.f5490e = zArr;
        this.f5491f = oVar;
    }

    @Override // android.content.DialogInterface.OnClickListener
    public final void onClick(DialogInterface dialogInterface, int i7) {
        int i8 = this.f5486a;
        androidx.recyclerview.widget.h1 h1Var = this.f5492g;
        switch (i8) {
            case 0:
                ((DesktopAssistantFragment.AnonymousClass12) h1Var).lambda$onBindViewHolder$0(this.f5487b, this.f5488c, this.f5489d, this.f5490e, this.f5491f, dialogInterface, i7);
                break;
            default:
                ((DesktopAssistantFragment.AnonymousClass21) h1Var).lambda$onBindViewHolder$2(this.f5487b, this.f5488c, this.f5489d, this.f5490e, this.f5491f, dialogInterface, i7);
                break;
        }
    }
}
