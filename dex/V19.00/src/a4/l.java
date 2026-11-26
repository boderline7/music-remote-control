package a4;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final class l extends p3.f {

    /* renamed from: e, reason: collision with root package name */
    public final /* synthetic */ int f305e;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public /* synthetic */ l(int i7) {
        super(25, 0);
        this.f305e = i7;
    }

    @Override // p3.f, a4.b
    public final void b(c cVar) {
        int i7;
        int i8 = this.f305e;
        switch (i8) {
            case 1:
                StringBuilder sb = new StringBuilder();
                while (true) {
                    if (cVar.c()) {
                        char b7 = cVar.b();
                        cVar.f267a++;
                        l(b7, sb);
                        int i9 = 3;
                        if (sb.length() % 3 == 0) {
                            p3.f.M(cVar, sb);
                            int i10 = cVar.f267a;
                            switch (i8) {
                                case 0:
                                    i7 = 2;
                                    break;
                                default:
                                    i7 = 3;
                                    break;
                            }
                            int T = l6.f.T(cVar.f270d, i10, i7);
                            switch (i8) {
                                case 0:
                                    i9 = 2;
                                    break;
                            }
                            if (T != i9) {
                                cVar.f268b = 0;
                            }
                        }
                    }
                }
                w(cVar, sb);
                break;
            default:
                super.b(cVar);
                break;
        }
    }

    @Override // p3.f
    public final int l(char c7, StringBuilder sb) {
        switch (this.f305e) {
            case 0:
                if (c7 == ' ') {
                    sb.append((char) 3);
                } else if (c7 >= '0' && c7 <= '9') {
                    sb.append((char) (c7 - ','));
                } else {
                    if (c7 < 'a' || c7 > 'z') {
                        if (c7 < ' ') {
                            sb.append((char) 0);
                            sb.append(c7);
                            return 2;
                        }
                        if (c7 <= '/') {
                            sb.append((char) 1);
                            sb.append((char) (c7 - '!'));
                            return 2;
                        }
                        if (c7 <= '@') {
                            sb.append((char) 1);
                            sb.append((char) (c7 - '+'));
                            return 2;
                        }
                        if (c7 >= '[' && c7 <= '_') {
                            sb.append((char) 1);
                            sb.append((char) (c7 - 'E'));
                            return 2;
                        }
                        if (c7 == '`') {
                            sb.append((char) 2);
                            sb.append((char) 0);
                            return 2;
                        }
                        if (c7 <= 'Z') {
                            sb.append((char) 2);
                            sb.append((char) (c7 - '@'));
                            return 2;
                        }
                        if (c7 > 127) {
                            sb.append("\u0001\u001e");
                            return 2 + l((char) (c7 - 128), sb);
                        }
                        sb.append((char) 2);
                        sb.append((char) (c7 - '`'));
                        return 2;
                    }
                    sb.append((char) (c7 - 'S'));
                }
                return 1;
            default:
                if (c7 == '\r') {
                    sb.append((char) 0);
                } else if (c7 == ' ') {
                    sb.append((char) 3);
                } else if (c7 == '*') {
                    sb.append((char) 1);
                } else if (c7 == '>') {
                    sb.append((char) 2);
                } else if (c7 >= '0' && c7 <= '9') {
                    sb.append((char) (c7 - ','));
                } else {
                    if (c7 < 'A' || c7 > 'Z') {
                        l6.f.K(c7);
                        throw null;
                    }
                    sb.append((char) (c7 - '3'));
                }
                return 1;
        }
    }

    @Override // p3.f
    public final int q() {
        switch (this.f305e) {
            case 0:
                return 2;
            default:
                return 3;
        }
    }

    @Override // p3.f
    public final void w(c cVar, StringBuilder sb) {
        switch (this.f305e) {
            case 1:
                cVar.d(cVar.a());
                int a7 = ((j) cVar.f275i).f297b - cVar.a();
                cVar.f267a -= sb.length();
                if ((cVar.f270d.length() - cVar.f269c) - cVar.f267a > 1 || a7 > 1 || (cVar.f270d.length() - cVar.f269c) - cVar.f267a != a7) {
                    cVar.e((char) 254);
                }
                if (cVar.f268b < 0) {
                    cVar.f268b = 0;
                    break;
                }
                break;
            default:
                super.w(cVar, sb);
                break;
        }
    }
}
