package com.navi.tool.ui.log;

import android.content.DialogInterface;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Spinner;
import com.navi.tool.model.LogAppProfile;
import com.navi.tool.model.LogAssistantProfile;
import com.navi.tool.model.LogMediaProfile;
import java.util.ArrayList;
import java.util.List;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final /* synthetic */ class b0 implements DialogInterface.OnClickListener {
    public final /* synthetic */ Object A;

    /* renamed from: a, reason: collision with root package name */
    public final /* synthetic */ int f5623a;

    /* renamed from: b, reason: collision with root package name */
    public final /* synthetic */ LogLinkFragment f5624b;

    /* renamed from: c, reason: collision with root package name */
    public final /* synthetic */ EditText f5625c;

    /* renamed from: d, reason: collision with root package name */
    public final /* synthetic */ EditText f5626d;

    /* renamed from: e, reason: collision with root package name */
    public final /* synthetic */ EditText f5627e;

    /* renamed from: f, reason: collision with root package name */
    public final /* synthetic */ RadioGroup f5628f;

    /* renamed from: g, reason: collision with root package name */
    public final /* synthetic */ RadioGroup f5629g;

    /* renamed from: h, reason: collision with root package name */
    public final /* synthetic */ EditText f5630h;

    /* renamed from: i, reason: collision with root package name */
    public final /* synthetic */ Spinner f5631i;

    /* renamed from: j, reason: collision with root package name */
    public final /* synthetic */ EditText f5632j;

    /* renamed from: k, reason: collision with root package name */
    public final /* synthetic */ CheckBox f5633k;

    /* renamed from: l, reason: collision with root package name */
    public final /* synthetic */ EditText f5634l;

    /* renamed from: m, reason: collision with root package name */
    public final /* synthetic */ CheckBox f5635m;

    /* renamed from: n, reason: collision with root package name */
    public final /* synthetic */ EditText f5636n;

    /* renamed from: o, reason: collision with root package name */
    public final /* synthetic */ CheckBox f5637o;

    /* renamed from: p, reason: collision with root package name */
    public final /* synthetic */ EditText f5638p;

    /* renamed from: q, reason: collision with root package name */
    public final /* synthetic */ EditText f5639q;

    /* renamed from: r, reason: collision with root package name */
    public final /* synthetic */ CheckBox f5640r;

    /* renamed from: s, reason: collision with root package name */
    public final /* synthetic */ Object f5641s;

    /* renamed from: t, reason: collision with root package name */
    public final /* synthetic */ EditText f5642t;

    /* renamed from: u, reason: collision with root package name */
    public final /* synthetic */ List f5643u;

    /* renamed from: v, reason: collision with root package name */
    public final /* synthetic */ EditText f5644v;

    /* renamed from: w, reason: collision with root package name */
    public final /* synthetic */ EditText f5645w;

    /* renamed from: x, reason: collision with root package name */
    public final /* synthetic */ List f5646x;

    /* renamed from: y, reason: collision with root package name */
    public final /* synthetic */ List f5647y;

    /* renamed from: z, reason: collision with root package name */
    public final /* synthetic */ Object f5648z;

    public /* synthetic */ b0(LogLinkFragment logLinkFragment, EditText editText, EditText editText2, EditText editText3, RadioGroup radioGroup, RadioGroup radioGroup2, EditText editText4, Spinner spinner, EditText editText5, CheckBox checkBox, EditText editText6, CheckBox checkBox2, EditText editText7, CheckBox checkBox3, EditText editText8, EditText editText9, CheckBox checkBox4, RadioGroup radioGroup3, EditText editText10, ArrayList arrayList, EditText editText11, EditText editText12, ArrayList arrayList2, ArrayList arrayList3, Object obj, Object obj2, int i7) {
        this.f5623a = i7;
        this.f5624b = logLinkFragment;
        this.f5625c = editText;
        this.f5626d = editText2;
        this.f5627e = editText3;
        this.f5628f = radioGroup;
        this.f5629g = radioGroup2;
        this.f5630h = editText4;
        this.f5631i = spinner;
        this.f5632j = editText5;
        this.f5633k = checkBox;
        this.f5634l = editText6;
        this.f5635m = checkBox2;
        this.f5636n = editText7;
        this.f5637o = checkBox3;
        this.f5638p = editText8;
        this.f5639q = editText9;
        this.f5640r = checkBox4;
        this.f5641s = radioGroup3;
        this.f5642t = editText10;
        this.f5643u = arrayList;
        this.f5644v = editText11;
        this.f5645w = editText12;
        this.f5646x = arrayList2;
        this.f5647y = arrayList3;
        this.f5648z = obj;
        this.A = obj2;
    }

    public /* synthetic */ b0(LogLinkFragment logLinkFragment, EditText editText, EditText editText2, EditText editText3, List list, RadioGroup radioGroup, EditText editText4, Spinner spinner, EditText editText5, CheckBox checkBox, EditText editText6, CheckBox checkBox2, EditText editText7, CheckBox checkBox3, EditText editText8, EditText editText9, CheckBox checkBox4, RadioGroup radioGroup2, EditText editText10, ArrayList arrayList, EditText editText11, EditText editText12, ArrayList arrayList2, ArrayList arrayList3, ArrayList arrayList4, LogAppProfile logAppProfile) {
        this.f5623a = 2;
        this.f5624b = logLinkFragment;
        this.f5625c = editText;
        this.f5626d = editText2;
        this.f5627e = editText3;
        this.f5643u = list;
        this.f5628f = radioGroup;
        this.f5630h = editText4;
        this.f5631i = spinner;
        this.f5632j = editText5;
        this.f5633k = checkBox;
        this.f5634l = editText6;
        this.f5635m = checkBox2;
        this.f5636n = editText7;
        this.f5637o = checkBox3;
        this.f5638p = editText8;
        this.f5639q = editText9;
        this.f5640r = checkBox4;
        this.f5629g = radioGroup2;
        this.f5642t = editText10;
        this.f5646x = arrayList;
        this.f5644v = editText11;
        this.f5645w = editText12;
        this.f5647y = arrayList2;
        this.f5648z = arrayList3;
        this.f5641s = arrayList4;
        this.A = logAppProfile;
    }

    @Override // android.content.DialogInterface.OnClickListener
    public final void onClick(DialogInterface dialogInterface, int i7) {
        int i8 = this.f5623a;
        Object obj = this.A;
        Object obj2 = this.f5641s;
        Object obj3 = this.f5648z;
        switch (i8) {
            case 0:
                this.f5624b.lambda$showEditMediaProfileDialogInternal$107(this.f5625c, this.f5626d, this.f5627e, this.f5628f, this.f5629g, this.f5630h, this.f5631i, this.f5632j, this.f5633k, this.f5634l, this.f5635m, this.f5636n, this.f5637o, this.f5638p, this.f5639q, this.f5640r, (RadioGroup) obj2, this.f5642t, this.f5643u, this.f5644v, this.f5645w, this.f5646x, this.f5647y, (List) obj3, (LogMediaProfile) obj, dialogInterface, i7);
                break;
            case 1:
                this.f5624b.lambda$showEditAssistantProfileDialogInternal$80(this.f5625c, this.f5626d, this.f5627e, this.f5628f, this.f5629g, this.f5630h, this.f5631i, this.f5632j, this.f5633k, this.f5634l, this.f5635m, this.f5636n, this.f5637o, this.f5638p, this.f5639q, this.f5640r, (RadioGroup) obj2, this.f5642t, this.f5643u, this.f5644v, this.f5645w, this.f5646x, this.f5647y, (LogAssistantProfile) obj3, (String) obj, dialogInterface, i7);
                break;
            default:
                this.f5624b.lambda$showEditProfileDialogInternal$62(this.f5625c, this.f5626d, this.f5627e, this.f5643u, this.f5628f, this.f5630h, this.f5631i, this.f5632j, this.f5633k, this.f5634l, this.f5635m, this.f5636n, this.f5637o, this.f5638p, this.f5639q, this.f5640r, this.f5629g, this.f5642t, this.f5646x, this.f5644v, this.f5645w, this.f5647y, (List) obj3, (List) obj2, (LogAppProfile) obj, dialogInterface, i7);
                break;
        }
    }
}
