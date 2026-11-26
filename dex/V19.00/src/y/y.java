package y;

import android.content.Context;
import android.content.res.TypedArray;
import android.content.res.XmlResourceParser;
import android.util.Xml;
import android.view.View;
import androidx.constraintlayout.motion.widget.MotionLayout;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final class y implements View.OnClickListener {
    public static final int ANIM_TOGGLE = 17;
    public static final int ANIM_TO_END = 1;
    public static final int ANIM_TO_START = 16;
    public static final int JUMP_TO_END = 256;
    public static final int JUMP_TO_START = 4096;

    /* renamed from: a, reason: collision with root package name */
    public final z f9944a;

    /* renamed from: b, reason: collision with root package name */
    public final int f9945b;

    /* renamed from: c, reason: collision with root package name */
    public final int f9946c;

    public y(Context context, z zVar, XmlResourceParser xmlResourceParser) {
        this.f9945b = -1;
        this.f9946c = 17;
        this.f9944a = zVar;
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(Xml.asAttributeSet(xmlResourceParser), a0.t.OnClick);
        int indexCount = obtainStyledAttributes.getIndexCount();
        for (int i7 = 0; i7 < indexCount; i7++) {
            int index = obtainStyledAttributes.getIndex(i7);
            if (index == a0.t.OnClick_targetId) {
                this.f9945b = obtainStyledAttributes.getResourceId(index, this.f9945b);
            } else if (index == a0.t.OnClick_clickAction) {
                this.f9946c = obtainStyledAttributes.getInt(index, this.f9946c);
            }
        }
        obtainStyledAttributes.recycle();
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r8v2, types: [android.view.View] */
    public final void a(MotionLayout motionLayout, int i7, z zVar) {
        int i8 = this.f9945b;
        MotionLayout motionLayout2 = motionLayout;
        if (i8 != -1) {
            motionLayout2 = motionLayout.findViewById(i8);
        }
        if (motionLayout2 == null) {
            return;
        }
        int i9 = zVar.f9950d;
        int i10 = zVar.f9949c;
        if (i9 == -1) {
            motionLayout2.setOnClickListener(this);
            return;
        }
        int i11 = this.f9946c;
        int i12 = i11 & 1;
        if (((i12 != 0 && i7 == i9) | (i12 != 0 && i7 == i9) | ((i11 & 256) != 0 && i7 == i9) | ((i11 & 16) != 0 && i7 == i10)) || ((i11 & 4096) != 0 && i7 == i10)) {
            motionLayout2.setOnClickListener(this);
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:40:0x008a  */
    @Override // android.view.View.OnClickListener
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void onClick(android.view.View r14) {
        /*
            Method dump skipped, instructions count: 206
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: y.y.onClick(android.view.View):void");
    }
}
