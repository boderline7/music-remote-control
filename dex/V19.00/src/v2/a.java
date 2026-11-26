package v2;

import a4.e;
import a4.f;
import a4.g;
import a4.i;
import a5.n;
import android.graphics.Rect;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import b4.l;
import com.google.android.material.behavior.SwipeDismissBehavior;
import com.google.android.material.button.MaterialButtonToggleGroup;
import com.google.android.material.internal.ScrimInsetsFrameLayout;
import com.google.android.material.internal.h;
import com.google.android.material.internal.j0;
import com.google.android.material.internal.k0;
import com.google.android.material.navigation.NavigationBarView;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.navigationrail.NavigationRailView;
import com.kugou.auto.proxy.KgMusic;
import com.yalantis.ucrop.view.CropImageView;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import k.m;
import p0.b0;
import p0.d1;
import p0.j2;
import p0.l0;
import p0.l2;
import p0.m0;
import p0.v;
import w3.d;
import x5.o;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final class a implements j0, com.google.android.material.button.a, h, b0, m, d, i6.h {

    /* renamed from: a, reason: collision with root package name */
    public final /* synthetic */ int f9387a;

    /* renamed from: b, reason: collision with root package name */
    public Object f9388b;

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public a(int i7) {
        this(TimeUnit.MINUTES);
        this.f9387a = i7;
        if (i7 == 14) {
            this.f9388b = new l(0);
            return;
        }
        switch (i7) {
            case 18:
                this.f9388b = new ArrayList();
                break;
            case 19:
                break;
            case 20:
                this.f9388b = new LinkedHashSet();
                break;
        }
    }

    public /* synthetic */ a(int i7, Object obj) {
        this.f9387a = i7;
        this.f9388b = obj;
    }

    public a(f fVar) {
        f fVar2;
        byte[] a7;
        f fVar3;
        int i7;
        byte[] a8;
        int i8;
        this.f9387a = 13;
        g gVar = fVar.f285a;
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        ArrayList arrayList3 = new ArrayList();
        a4.h hVar = a4.h.C40;
        int i9 = 0;
        a4.h hVar2 = fVar.f286b;
        int h7 = ((hVar2 == hVar || hVar2 == a4.h.TEXT || hVar2 == a4.h.X12) && fVar.e() != a4.h.ASCII) ? h(f.a(z1.c.CONST_METHOD_HANDLE), arrayList) : 0;
        f fVar4 = fVar;
        while (fVar4 != null) {
            int[] iArr = e.f280a;
            a4.h hVar3 = fVar4.f286b;
            int i10 = iArr[hVar3.ordinal()];
            int i11 = 4;
            int i12 = fVar4.f288d;
            int i13 = fVar4.f287c;
            g gVar2 = fVar4.f285a;
            switch (i10) {
                case 1:
                    fVar2 = fVar4;
                    if (gVar2.a(i13)) {
                        a7 = f.b(241, gVar2.b(i13) + 1);
                        break;
                    } else if (i.d(gVar2.charAt(i13), gVar2.f830b)) {
                        a7 = f.b(235, gVar2.charAt(i13) - 127);
                        break;
                    } else if (i12 == 2) {
                        a7 = f.a(gVar2.charAt(i13 + 1) + ((gVar2.charAt(i13) - '0') * 10) + 82);
                        break;
                    } else if (gVar2.i(i13)) {
                        a7 = f.a(232);
                        break;
                    } else {
                        a7 = f.a(gVar2.charAt(i13) + 1);
                        break;
                    }
                case 2:
                    fVar2 = fVar4;
                    a7 = f.a(gVar2.charAt(i13));
                    break;
                case 3:
                    fVar2 = fVar4;
                    a7 = fVar2.d(gVar2.f830b, true);
                    break;
                case 4:
                    fVar2 = fVar4;
                    a7 = fVar2.d(gVar2.f830b, false);
                    break;
                case 5:
                    fVar3 = fVar4;
                    int i14 = (i12 / 3) * 2;
                    byte[] bArr = new byte[i14];
                    for (int i15 = 0; i15 < i14; i15 += 2) {
                        int i16 = ((i15 / 2) * 3) + i13;
                        f.k(bArr, i15, f.j(gVar2.charAt(i16)), f.j(gVar2.charAt(i16 + 1)), f.j(gVar2.charAt(i16 + 2)));
                    }
                    a7 = bArr;
                    fVar2 = fVar3;
                    break;
                case 6:
                    fVar3 = fVar4;
                    int ceil = (int) Math.ceil(i12 / 4.0d);
                    byte[] bArr2 = new byte[ceil * 3];
                    int min = Math.min((i12 + i13) - 1, ((int[]) gVar2.f831c).length - 1);
                    int i17 = 0;
                    while (i17 < ceil) {
                        int[] iArr2 = new int[i11];
                        int i18 = ceil;
                        int i19 = 0;
                        while (i19 < i11) {
                            if (i13 <= min) {
                                iArr2[i19] = gVar2.charAt(i13) & '?';
                                i13++;
                            } else {
                                iArr2[i19] = i13 == min + 1 ? 31 : 0;
                            }
                            i19++;
                            i11 = 4;
                        }
                        int i20 = i17;
                        int i21 = (iArr2[0] << 18) | (iArr2[1] << 12) | (iArr2[2] << 6) | iArr2[3];
                        bArr2[i20] = (byte) ((i21 >> 16) & 255);
                        bArr2[i20 + 1] = (byte) ((i21 >> 8) & 255);
                        bArr2[i20 + 2] = (byte) (i21 & 255);
                        i17 = i20 + 3;
                        ceil = i18;
                        i11 = 4;
                    }
                    a7 = bArr2;
                    fVar2 = fVar3;
                    break;
                default:
                    a7 = new byte[i9];
                    fVar2 = fVar4;
                    break;
            }
            int h8 = h(a7, arrayList) + h7;
            f fVar5 = fVar2.f289e;
            if (fVar5 == null || fVar5.f286b != hVar3) {
                if (hVar3 == a4.h.B256) {
                    if (h8 <= 249) {
                        arrayList.add(0, Byte.valueOf((byte) h8));
                        i8 = h8 + 1;
                    } else {
                        arrayList.add(0, Byte.valueOf((byte) (h8 % 250)));
                        arrayList.add(0, Byte.valueOf((byte) ((h8 / 250) + 249)));
                        i8 = h8 + 2;
                    }
                    arrayList2.add(Integer.valueOf(arrayList.size()));
                    arrayList3.add(Integer.valueOf(i8));
                }
                int[] iArr3 = e.f280a;
                int i22 = iArr3[fVar2.h().ordinal()];
                if (i22 == 1 || i22 == 2) {
                    int i23 = iArr3[hVar3.ordinal()];
                    if (i23 == 2) {
                        i7 = 0;
                        a8 = f.a(231);
                    } else if (i23 == 3) {
                        i7 = 0;
                        a8 = f.a(230);
                    } else if (i23 == 4) {
                        i7 = 0;
                        a8 = f.a(239);
                    } else if (i23 != 5) {
                        if (i23 == 6) {
                            i7 = 0;
                            a8 = f.a(KgMusic.imgUrlSize240);
                        }
                        i7 = 0;
                        a8 = new byte[0];
                    } else {
                        i7 = 0;
                        a8 = f.a(238);
                    }
                    h(a8, arrayList);
                    h8 = i7;
                } else {
                    if ((i22 == 3 || i22 == 4 || i22 == 5) && hVar3 != fVar2.h()) {
                        switch (iArr3[hVar3.ordinal()]) {
                            case 1:
                                a8 = f.a(z1.c.CONST_METHOD_HANDLE);
                                i7 = 0;
                                break;
                            case 2:
                                a8 = f.b(z1.c.CONST_METHOD_HANDLE, 231);
                                i7 = 0;
                                break;
                            case 3:
                                a8 = f.b(z1.c.CONST_METHOD_HANDLE, 230);
                                i7 = 0;
                                break;
                            case 4:
                                a8 = f.b(z1.c.CONST_METHOD_HANDLE, 239);
                                i7 = 0;
                                break;
                            case 5:
                                a8 = f.b(z1.c.CONST_METHOD_HANDLE, 238);
                                i7 = 0;
                                break;
                            case 6:
                                a8 = f.b(z1.c.CONST_METHOD_HANDLE, KgMusic.imgUrlSize240);
                                i7 = 0;
                                break;
                        }
                        h(a8, arrayList);
                        h8 = i7;
                    }
                    i7 = 0;
                    a8 = new byte[0];
                    h(a8, arrayList);
                    h8 = i7;
                }
            } else {
                i7 = 0;
            }
            fVar4 = fVar5;
            h7 = h8;
            i9 = i7;
        }
        int i24 = i9;
        int i25 = gVar.f292e;
        if (i25 == 5) {
            h(f.a(236), arrayList);
        } else if (i25 == 6) {
            h(f.a(237), arrayList);
        }
        if (gVar.f830b > 0) {
            h(f.a(232), arrayList);
        }
        for (int i26 = i24; i26 < arrayList2.size(); i26++) {
            int size = arrayList.size() - ((Integer) arrayList2.get(i26)).intValue();
            int intValue = ((Integer) arrayList3.get(i26)).intValue();
            for (int i27 = i24; i27 < intValue; i27++) {
                int i28 = size + i27;
                int byteValue = (((i28 + 1) * z1.c.AND_INT) % 255) + 1 + (((Byte) arrayList.get(i28)).byteValue() & 255);
                if (byteValue > 255) {
                    byteValue -= 256;
                }
                arrayList.set(i28, Byte.valueOf((byte) byteValue));
            }
        }
        int g7 = fVar.g(arrayList.size());
        if (arrayList.size() < g7) {
            arrayList.add((byte) -127);
        }
        while (arrayList.size() < g7) {
            int size2 = ((arrayList.size() + 1) * z1.c.AND_INT) % z1.c.INVOKE_CUSTOM_RANGE;
            int i29 = size2 + z1.c.INT_TO_FLOAT;
            if (i29 > 254) {
                i29 = size2 - 124;
            }
            arrayList.add(Byte.valueOf((byte) i29));
        }
        this.f9388b = new byte[arrayList.size()];
        int i30 = i24;
        while (true) {
            byte[] bArr3 = (byte[]) this.f9388b;
            if (i30 >= bArr3.length) {
                return;
            }
            bArr3[i30] = ((Byte) arrayList.get(i30)).byteValue();
            i30++;
        }
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public /* synthetic */ a(n nVar) {
        this(17, nVar);
        this.f9387a = 17;
    }

    public a(SwipeDismissBehavior swipeDismissBehavior) {
        this.f9387a = 12;
        swipeDismissBehavior.getClass();
        swipeDismissBehavior.f3324f = Math.min(Math.max(CropImageView.DEFAULT_ASPECT_RATIO, 0.1f), 1.0f);
        swipeDismissBehavior.f3325g = Math.min(Math.max(CropImageView.DEFAULT_ASPECT_RATIO, 0.6f), 1.0f);
        swipeDismissBehavior.f3322d = 0;
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public /* synthetic */ a(MaterialButtonToggleGroup materialButtonToggleGroup) {
        this(1, materialButtonToggleGroup);
        this.f9387a = 1;
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public a(TimeUnit timeUnit) {
        this(new o(w5.g.INSTANCE, timeUnit));
        this.f9387a = 19;
        i5.a.g("timeUnit", timeUnit);
    }

    public a(o oVar) {
        this.f9387a = 19;
        this.f9388b = oVar;
    }

    public static int g(Level level) {
        int intValue = level.intValue();
        if (intValue < 800) {
            return intValue < 500 ? 2 : 3;
        }
        if (intValue < 900) {
            return 4;
        }
        return intValue < 1000 ? 5 : 6;
    }

    public static int h(byte[] bArr, ArrayList arrayList) {
        for (int length = bArr.length - 1; length >= 0; length--) {
            arrayList.add(0, Byte.valueOf(bArr[length]));
        }
        return bArr.length;
    }

    @Override // w3.d
    public final y3.b a(String str, w3.a aVar, int i7, int i8, HashMap hashMap) {
        if (aVar == w3.a.UPC_A) {
            return ((l) this.f9388b).a(a.a.B("0", str), w3.a.EAN_13, i7, i8, hashMap);
        }
        throw new IllegalArgumentException("Can only encode UPC-A, but got " + aVar);
    }

    @Override // i6.h
    public final void b(Level level, String str) {
        if (level != Level.OFF) {
            Log.println(g(level), (String) this.f9388b, str);
        }
    }

    public final int c() {
        return ((((InputStream) this.f9388b).read() << 8) & v.ACTION_POINTER_INDEX_MASK) | (((InputStream) this.f9388b).read() & 255);
    }

    @Override // com.google.android.material.internal.j0
    public final l2 d(View view, l2 l2Var, androidx.media.a aVar) {
        boolean b7;
        boolean b8;
        boolean b9;
        switch (this.f9387a) {
            case 0:
                aVar.f2238d = l2Var.a() + aVar.f2238d;
                int i7 = d1.OVER_SCROLL_ALWAYS;
                boolean z6 = m0.d(view) == 1;
                int b10 = l2Var.b();
                int c7 = l2Var.c();
                int i8 = aVar.f2235a + (z6 ? c7 : b10);
                aVar.f2235a = i8;
                int i9 = aVar.f2237c;
                if (!z6) {
                    b10 = c7;
                }
                int i10 = i9 + b10;
                aVar.f2237c = i10;
                m0.k(view, i8, aVar.f2236b, i10, aVar.f2238d);
                break;
            default:
                i0.c f7 = l2Var.f8161a.f(7);
                NavigationRailView navigationRailView = (NavigationRailView) this.f9388b;
                Boolean bool = navigationRailView.f4019g;
                if (bool != null) {
                    b7 = bool.booleanValue();
                } else {
                    int i11 = d1.OVER_SCROLL_ALWAYS;
                    b7 = l0.b(navigationRailView);
                }
                if (b7) {
                    aVar.f2236b += f7.f6774b;
                }
                NavigationRailView navigationRailView2 = (NavigationRailView) this.f9388b;
                Boolean bool2 = navigationRailView2.f4020h;
                if (bool2 != null) {
                    b8 = bool2.booleanValue();
                } else {
                    int i12 = d1.OVER_SCROLL_ALWAYS;
                    b8 = l0.b(navigationRailView2);
                }
                if (b8) {
                    aVar.f2238d += f7.f6776d;
                }
                NavigationRailView navigationRailView3 = (NavigationRailView) this.f9388b;
                Boolean bool3 = navigationRailView3.f4021i;
                if (bool3 != null) {
                    b9 = bool3.booleanValue();
                } else {
                    int i13 = d1.OVER_SCROLL_ALWAYS;
                    b9 = l0.b(navigationRailView3);
                }
                if (b9) {
                    aVar.f2235a += k0.f(view) ? f7.f6775c : f7.f6773a;
                }
                int i14 = aVar.f2235a;
                int i15 = aVar.f2236b;
                int i16 = aVar.f2237c;
                int i17 = aVar.f2238d;
                int i18 = d1.OVER_SCROLL_ALWAYS;
                m0.k(view, i14, i15, i16, i17);
                break;
        }
        return l2Var;
    }

    @Override // p0.b0
    public final l2 e(View view, l2 l2Var) {
        ScrimInsetsFrameLayout scrimInsetsFrameLayout = (ScrimInsetsFrameLayout) this.f9388b;
        if (scrimInsetsFrameLayout.f3783b == null) {
            scrimInsetsFrameLayout.f3783b = new Rect();
        }
        ((ScrimInsetsFrameLayout) this.f9388b).f3783b.set(l2Var.b(), l2Var.d(), l2Var.c(), l2Var.a());
        ((ScrimInsetsFrameLayout) this.f9388b).e(l2Var);
        ScrimInsetsFrameLayout scrimInsetsFrameLayout2 = (ScrimInsetsFrameLayout) this.f9388b;
        j2 j2Var = l2Var.f8161a;
        boolean z6 = true;
        if ((!j2Var.j().equals(i0.c.NONE)) && ((ScrimInsetsFrameLayout) this.f9388b).f3782a != null) {
            z6 = false;
        }
        scrimInsetsFrameLayout2.setWillNotDraw(z6);
        ScrimInsetsFrameLayout scrimInsetsFrameLayout3 = (ScrimInsetsFrameLayout) this.f9388b;
        int i7 = d1.OVER_SCROLL_ALWAYS;
        l0.k(scrimInsetsFrameLayout3);
        return j2Var.c();
    }

    @Override // i6.h
    public final void f(Level level, String str, Throwable th) {
        if (level != Level.OFF) {
            Log.println(g(level), (String) this.f9388b, str + "\n" + Log.getStackTraceString(th));
        }
    }

    @Override // k.m
    public final boolean l(k.o oVar, MenuItem menuItem) {
        switch (this.f9387a) {
            case 8:
                NavigationBarView navigationBarView = (NavigationBarView) this.f9388b;
                int i7 = NavigationBarView.LABEL_VISIBILITY_AUTO;
                navigationBarView.getClass();
                ((NavigationBarView) this.f9388b).getClass();
                break;
            default:
                com.google.android.material.navigation.l lVar = ((NavigationView) this.f9388b).f3995j;
                if (lVar == null || !lVar.onNavigationItemSelected(menuItem)) {
                }
                break;
        }
        return false;
    }

    @Override // k.m
    public final void s(k.o oVar) {
    }
}
