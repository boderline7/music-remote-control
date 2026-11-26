package e2;

import androidx.appcompat.widget.u;
import java.io.FilterWriter;
import java.io.StringWriter;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final class e extends FilterWriter {

    /* renamed from: a, reason: collision with root package name */
    public final String f6355a;

    /* renamed from: b, reason: collision with root package name */
    public final int f6356b;

    /* renamed from: c, reason: collision with root package name */
    public final int f6357c;

    /* renamed from: d, reason: collision with root package name */
    public int f6358d;

    /* renamed from: e, reason: collision with root package name */
    public boolean f6359e;

    /* renamed from: f, reason: collision with root package name */
    public int f6360f;

    public e(StringWriter stringWriter, int i7, String str) {
        super(stringWriter);
        if (i7 < 0) {
            throw new IllegalArgumentException("width < 0");
        }
        this.f6356b = i7 != 0 ? i7 : u.MAX_ACTIVITY_COUNT_UNLIMITED;
        int i8 = i7 >> 1;
        this.f6357c = i8;
        this.f6355a = str.length() == 0 ? null : str;
        this.f6358d = 0;
        this.f6359e = i8 != 0;
        this.f6360f = 0;
    }

    @Override // java.io.FilterWriter, java.io.Writer
    public final void write(int i7) {
        int i8;
        synchronized (((FilterWriter) this).lock) {
            try {
                boolean z6 = true;
                if (this.f6359e) {
                    if (i7 == 32) {
                        int i9 = this.f6360f + 1;
                        this.f6360f = i9;
                        int i10 = this.f6357c;
                        if (i9 >= i10) {
                            this.f6360f = i10;
                            this.f6359e = false;
                        }
                    } else {
                        this.f6359e = false;
                    }
                }
                if (this.f6358d == this.f6356b && i7 != 10) {
                    ((FilterWriter) this).out.write(10);
                    this.f6358d = 0;
                }
                if (this.f6358d == 0) {
                    String str = this.f6355a;
                    if (str != null) {
                        ((FilterWriter) this).out.write(str);
                    }
                    if (!this.f6359e) {
                        int i11 = 0;
                        while (true) {
                            i8 = this.f6360f;
                            if (i11 >= i8) {
                                break;
                            }
                            ((FilterWriter) this).out.write(32);
                            i11++;
                        }
                        this.f6358d = i8;
                    }
                }
                ((FilterWriter) this).out.write(i7);
                if (i7 == 10) {
                    this.f6358d = 0;
                    if (this.f6357c == 0) {
                        z6 = false;
                    }
                    this.f6359e = z6;
                    this.f6360f = 0;
                } else {
                    this.f6358d++;
                }
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    @Override // java.io.FilterWriter, java.io.Writer
    public final void write(String str, int i7, int i8) {
        synchronized (((FilterWriter) this).lock) {
            while (i8 > 0) {
                try {
                    write(str.charAt(i7));
                    i7++;
                    i8--;
                } catch (Throwable th) {
                    throw th;
                }
            }
        }
    }

    @Override // java.io.FilterWriter, java.io.Writer
    public final void write(char[] cArr, int i7, int i8) {
        synchronized (((FilterWriter) this).lock) {
            while (i8 > 0) {
                try {
                    write(cArr[i7]);
                    i7++;
                    i8--;
                } catch (Throwable th) {
                    throw th;
                }
            }
        }
    }
}
