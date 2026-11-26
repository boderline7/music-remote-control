package org.greenrobot.eventbus.android;

import a6.k0;
import i5.a;
import i6.h;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_2_1633180.dex */
public class AndroidComponentsImpl {

    /* renamed from: c, reason: collision with root package name */
    public static final AndroidComponentsImpl f8076c;

    /* renamed from: a, reason: collision with root package name */
    public final h f8077a;

    /* renamed from: b, reason: collision with root package name */
    public final k0 f8078b;

    static {
        AndroidComponentsImpl androidComponentsImpl = null;
        if (a.n()) {
            try {
                androidComponentsImpl = (AndroidComponentsImpl) AndroidComponentsImpl.class.getConstructor(new Class[0]).newInstance(new Object[0]);
            } catch (Throwable unused) {
            }
        }
        f8076c = androidComponentsImpl;
    }

    public AndroidComponentsImpl() {
        v2.a aVar = new v2.a(21, "EventBus");
        k0 k0Var = new k0();
        this.f8077a = aVar;
        this.f8078b = k0Var;
    }
}
