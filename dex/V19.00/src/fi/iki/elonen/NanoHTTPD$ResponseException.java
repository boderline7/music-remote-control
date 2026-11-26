package fi.iki.elonen;

import a5.k;
import java.io.IOException;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final class NanoHTTPD$ResponseException extends Exception {

    /* renamed from: a, reason: collision with root package name */
    public final k f6406a;

    public NanoHTTPD$ResponseException(k kVar, String str) {
        super(str);
        this.f6406a = kVar;
    }

    public NanoHTTPD$ResponseException(k kVar, String str, IOException iOException) {
        super(str, iOException);
        this.f6406a = kVar;
    }

    public final k a() {
        return this.f6406a;
    }
}
