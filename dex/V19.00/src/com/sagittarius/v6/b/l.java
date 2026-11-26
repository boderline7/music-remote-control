package com.sagittarius.v6.b;

import dalvik.system.DexFile;
import java.io.File;
import java.lang.reflect.Constructor;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_2_1633180.dex */
final class l implements j {

    /* renamed from: a, reason: collision with root package name */
    private final Constructor f6126a;

    l(Class cls) {
        Constructor constructor = cls.getConstructor(File.class, File.class, DexFile.class);
        this.f6126a = constructor;
        constructor.setAccessible(true);
    }

    @Override // com.sagittarius.v6.b.j
    public final Object a(File file, DexFile dexFile) {
        return this.f6126a.newInstance(file, file, dexFile);
    }
}
