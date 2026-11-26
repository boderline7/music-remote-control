package com.navi.tool.utils;

import java.io.File;
import java.util.function.ToLongFunction;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final /* synthetic */ class a implements ToLongFunction {

    /* renamed from: a, reason: collision with root package name */
    public final /* synthetic */ int f5850a;

    public /* synthetic */ a(int i7) {
        this.f5850a = i7;
    }

    @Override // java.util.function.ToLongFunction
    public final long applyAsLong(Object obj) {
        File file = (File) obj;
        switch (this.f5850a) {
        }
        return file.lastModified();
    }
}
