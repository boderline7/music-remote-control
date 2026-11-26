package com.sagittarius.v6.b;

import dalvik.system.DexFile;
import java.io.File;
import java.lang.reflect.Constructor;
import java.util.zip.ZipFile;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_2_1633180.dex */
public final class k implements j {

    /* renamed from: a, reason: collision with root package name */
    private final Constructor f6125a;

    k(Class cls) {
        Constructor constructor = cls.getConstructor(File.class, ZipFile.class, DexFile.class);
        this.f6125a = constructor;
        constructor.setAccessible(true);
    }

    @Override // com.sagittarius.v6.b.j
    public final Object a(File file, DexFile dexFile) {
        return this.f6125a.newInstance(file, new ZipFile(file), dexFile);
    }
}
