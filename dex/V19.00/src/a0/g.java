package a0;

import android.content.Context;
import android.content.res.TypedArray;
import android.content.res.XmlResourceParser;
import android.util.Xml;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import androidx.constraintlayout.widget.ConstraintLayout;
import java.util.ArrayList;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final class g {

    /* renamed from: a, reason: collision with root package name */
    public final int f54a;

    /* renamed from: b, reason: collision with root package name */
    public final ArrayList f55b = new ArrayList();

    /* renamed from: c, reason: collision with root package name */
    public final int f56c;

    /* renamed from: d, reason: collision with root package name */
    public final p f57d;

    public g(Context context, XmlResourceParser xmlResourceParser) {
        this.f56c = -1;
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(Xml.asAttributeSet(xmlResourceParser), t.State);
        int indexCount = obtainStyledAttributes.getIndexCount();
        for (int i7 = 0; i7 < indexCount; i7++) {
            int index = obtainStyledAttributes.getIndex(i7);
            if (index == t.State_android_id) {
                this.f54a = obtainStyledAttributes.getResourceId(index, this.f54a);
            } else if (index == t.State_constraints) {
                int resourceId = obtainStyledAttributes.getResourceId(index, this.f56c);
                this.f56c = resourceId;
                String resourceTypeName = context.getResources().getResourceTypeName(resourceId);
                context.getResources().getResourceName(resourceId);
                if ("layout".equals(resourceTypeName)) {
                    p pVar = new p();
                    this.f57d = pVar;
                    pVar.e((ConstraintLayout) LayoutInflater.from(context).inflate(resourceId, (ViewGroup) null));
                }
            }
        }
        obtainStyledAttributes.recycle();
    }
}
