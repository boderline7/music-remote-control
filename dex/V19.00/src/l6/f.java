package l6;

import android.animation.TimeInterpolator;
import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Configuration;
import android.content.res.TypedArray;
import android.graphics.PorterDuff;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.TypedValue;
import android.util.Xml;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.view.animation.AnimationUtils;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputConnection;
import android.widget.ImageView;
import androidx.appcompat.app.p0;
import androidx.appcompat.widget.l2;
import androidx.appcompat.widget.u;
import androidx.constraintlayout.motion.widget.MotionLayout;
import com.google.android.material.carousel.CarouselLayoutManager;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.internal.CheckableImageButton;
import com.google.android.material.textfield.TextInputLayout;
import com.yalantis.ucrop.view.CropImageView;
import g0.g;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import n1.z;
import org.xmlpull.v1.XmlSerializer;
import p0.d1;
import p0.k0;
import p0.l0;
import p0.m1;
import p0.r0;
import p3.i;
import p3.j;
import p3.m;
import s5.h0;
import u.h;
import u.n;
import u.p;
import v.s;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public abstract class f implements m1 {

    /* renamed from: a, reason: collision with root package name */
    public static e f7468a = null;

    /* renamed from: b, reason: collision with root package name */
    public static boolean f7469b = false;

    /* renamed from: c, reason: collision with root package name */
    public static boolean f7470c = true;

    public static Drawable A(Context context, int i7) {
        return l2.c().e(context, i7);
    }

    public static void A0(View view) {
        Drawable background = view.getBackground();
        if (background instanceof j) {
            B0(view, (j) background);
        }
    }

    public static Drawable B(Context context, TypedArray typedArray, int i7) {
        int resourceId;
        Drawable A;
        return (!typedArray.hasValue(i7) || (resourceId = typedArray.getResourceId(i7, 0)) == 0 || (A = A(context, resourceId)) == null) ? typedArray.getDrawable(i7) : A;
    }

    public static void B0(View view, j jVar) {
        e3.a aVar = jVar.f8263a.f8242b;
        if (aVar == null || !aVar.f6366a) {
            return;
        }
        float f7 = CropImageView.DEFAULT_ASPECT_RATIO;
        for (ViewParent parent = view.getParent(); parent instanceof View; parent = parent.getParent()) {
            int i7 = d1.OVER_SCROLL_ALWAYS;
            f7 += r0.i((View) parent);
        }
        i iVar = jVar.f8263a;
        if (iVar.f8253m != f7) {
            iVar.f8253m = f7;
            jVar.x();
        }
    }

    public static float C(String[] strArr, int i7) {
        float parseFloat = Float.parseFloat(strArr[i7]);
        if (parseFloat >= CropImageView.DEFAULT_ASPECT_RATIO && parseFloat <= 1.0f) {
            return parseFloat;
        }
        throw new IllegalArgumentException("Motion easing control point value must be between 0 and 1; instead got: " + parseFloat);
    }

    public static void D() {
        StackTraceElement stackTraceElement = new Throwable().getStackTrace()[1];
        stackTraceElement.getFileName();
        stackTraceElement.getLineNumber();
        stackTraceElement.getMethodName();
    }

    public static void D0(ViewGroup viewGroup, boolean z6) {
        if (Build.VERSION.SDK_INT >= 29) {
            viewGroup.suppressLayout(z6);
        } else if (f7470c) {
            try {
                viewGroup.suppressLayout(z6);
            } catch (NoSuchMethodError unused) {
                f7470c = false;
            }
        }
    }

    public static void E() {
        StackTraceElement stackTraceElement = new Throwable().getStackTrace()[1];
        stackTraceElement.getFileName();
        stackTraceElement.getLineNumber();
    }

    public static String F(Context context, int i7) {
        if (i7 == -1) {
            return "UNKNOWN";
        }
        try {
            return context.getResources().getResourceEntryName(i7);
        } catch (Exception unused) {
            return a.a.d("?", i7);
        }
    }

    public static boolean F0(h hVar, h hVar2, h hVar3, h hVar4) {
        h hVar5;
        h hVar6;
        h hVar7 = h.FIXED;
        return (hVar3 == hVar7 || hVar3 == (hVar6 = h.WRAP_CONTENT) || (hVar3 == h.MATCH_PARENT && hVar != hVar6)) || (hVar4 == hVar7 || hVar4 == (hVar5 = h.WRAP_CONTENT) || (hVar4 == h.MATCH_PARENT && hVar2 != hVar5));
    }

    public static String G(View view) {
        try {
            return view.getContext().getResources().getResourceEntryName(view.getId());
        } catch (Exception unused) {
            return "UNKNOWN";
        }
    }

    public static String H(int i7, MotionLayout motionLayout) {
        return i7 == -1 ? "UNDEFINED" : motionLayout.getContext().getResources().getResourceEntryName(i7);
    }

    public static void K(char c7) {
        String hexString = Integer.toHexString(c7);
        throw new IllegalArgumentException("Illegal character: " + c7 + " (0x" + ("0000".substring(0, 4 - hexString.length()) + hexString) + ')');
    }

    public static boolean L(char c7) {
        return c7 >= '0' && c7 <= '9';
    }

    public static boolean M(char c7) {
        return c7 >= 128 && c7 <= 255;
    }

    public static boolean N(Context context) {
        return context.getResources().getConfiguration().fontScale >= 1.3f;
    }

    public static boolean O(String str, String str2) {
        return str.startsWith(str2.concat("(")) && str.endsWith(")");
    }

    public static boolean P(char c7) {
        return c7 == ' ' || (c7 >= '0' && c7 <= '9') || (c7 >= 'A' && c7 <= 'Z');
    }

    public static boolean Q(char c7) {
        return c7 >= ' ' && c7 <= '^';
    }

    public static boolean R(char c7) {
        return c7 == ' ' || (c7 >= '0' && c7 <= '9') || (c7 >= 'a' && c7 <= 'z');
    }

    public static boolean S(char c7) {
        return c7 == '\r' || c7 == '*' || c7 == '>' || c7 == ' ' || (c7 >= '0' && c7 <= '9') || (c7 >= 'A' && c7 <= 'Z');
    }

    /* JADX WARN: Code restructure failed: missing block: B:82:0x0225, code lost:
    
        r13 = 1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:83:0x016d, code lost:
    
        r3 = 3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:99:0x0251, code lost:
    
        r13 = 5;
     */
    /* JADX WARN: Removed duplicated region for block: B:22:0x0283  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static int T(java.lang.String r19, int r20, int r21) {
        /*
            Method dump skipped, instructions count: 698
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: l6.f.T(java.lang.String, int, int):int");
    }

    public static Typeface U(Configuration configuration, Typeface typeface) {
        int i7;
        int i8;
        int weight;
        int i9;
        Typeface create;
        if (Build.VERSION.SDK_INT < 31) {
            return null;
        }
        i7 = configuration.fontWeightAdjustment;
        if (i7 == Integer.MAX_VALUE) {
            return null;
        }
        i8 = configuration.fontWeightAdjustment;
        if (i8 == 0 || typeface == null) {
            return null;
        }
        weight = typeface.getWeight();
        i9 = configuration.fontWeightAdjustment;
        create = Typeface.create(typeface, z.y(i9 + weight, 1, 1000), typeface.isItalic());
        return create;
    }

    public static void V(View view, EditorInfo editorInfo, InputConnection inputConnection) {
        if (inputConnection == null || editorInfo.hintText != null) {
            return;
        }
        for (ViewParent parent = view.getParent(); parent instanceof View; parent = parent.getParent()) {
        }
    }

    public static Object d(Parcel parcel, Parcelable.Creator creator) {
        if (parcel.readInt() != 0) {
            return creator.createFromParcel(parcel);
        }
        return null;
    }

    public static void e(TextInputLayout textInputLayout, CheckableImageButton checkableImageButton, ColorStateList colorStateList, PorterDuff.Mode mode) {
        Drawable drawable = checkableImageButton.getDrawable();
        if (drawable != null) {
            drawable = drawable.mutate();
            if (colorStateList == null || !colorStateList.isStateful()) {
                j0.b.h(drawable, colorStateList);
            } else {
                int[] drawableState = textInputLayout.getDrawableState();
                int[] drawableState2 = checkableImageButton.getDrawableState();
                int length = drawableState.length;
                int[] copyOf = Arrays.copyOf(drawableState, drawableState.length + drawableState2.length);
                System.arraycopy(drawableState2, 0, copyOf, length, drawableState2.length);
                j0.b.h(drawable, ColorStateList.valueOf(colorStateList.getColorForState(copyOf, colorStateList.getDefaultColor())));
            }
            if (mode != null) {
                j0.b.i(drawable, mode);
            }
        }
        if (checkableImageButton.getDrawable() != drawable) {
            checkableImageButton.setImageDrawable(drawable);
        }
    }

    public static int f(a1.b bVar, boolean z6) {
        int i7 = z6 ? bVar.f199c : bVar.f198b;
        int i8 = z6 ? bVar.f198b : bVar.f199c;
        byte[][] bArr = (byte[][]) bVar.f200d;
        int i9 = 0;
        for (int i10 = 0; i10 < i7; i10++) {
            byte b7 = -1;
            int i11 = 0;
            for (int i12 = 0; i12 < i8; i12++) {
                byte b8 = z6 ? bArr[i10][i12] : bArr[i12][i10];
                if (b8 == b7) {
                    i11++;
                } else {
                    if (i11 >= 5) {
                        i9 += i11 - 2;
                    }
                    i11 = 1;
                    b7 = b8;
                }
            }
            if (i11 >= 5) {
                i9 = (i11 - 2) + i9;
            }
        }
        return i9;
    }

    public static void g(float f7, float[] fArr) {
        if (f7 <= 0.5f) {
            fArr[0] = 1.0f - (f7 * 2.0f);
            fArr[1] = 0.0f;
        } else {
            fArr[0] = 0.0f;
            fArr[1] = (f7 * 2.0f) - 1.0f;
        }
    }

    public static void l0(Context context, String str) {
        if (str.equals(h0.FRAGMENT_ENCODE_SET)) {
            context.deleteFile("androidx.appcompat.app.AppCompatDelegate.application_locales_record_file");
            return;
        }
        try {
            FileOutputStream openFileOutput = context.openFileOutput("androidx.appcompat.app.AppCompatDelegate.application_locales_record_file", 0);
            XmlSerializer newSerializer = Xml.newSerializer();
            try {
                newSerializer.setOutput(openFileOutput, null);
                newSerializer.startDocument("UTF-8", Boolean.TRUE);
                newSerializer.startTag(null, "locales");
                newSerializer.attribute(null, "application_locales", str);
                newSerializer.endTag(null, "locales");
                newSerializer.endDocument();
                if (openFileOutput == null) {
                    return;
                }
            } catch (Exception unused) {
                if (openFileOutput == null) {
                    return;
                }
            } catch (Throwable th) {
                if (openFileOutput != null) {
                    try {
                        openFileOutput.close();
                    } catch (IOException unused2) {
                    }
                }
                throw th;
            }
            try {
                openFileOutput.close();
            } catch (IOException unused3) {
            }
        } catch (FileNotFoundException unused4) {
            String.format("Storing App Locales : FileNotFoundException: Cannot open file %s for writing ", "androidx.appcompat.app.AppCompatDelegate.application_locales_record_file");
        }
    }

    public static int m(int i7) {
        int i8 = (i7 & (~(i7 >> 31))) - 255;
        return (i8 & (i8 >> 31)) + 255;
    }

    /* JADX WARN: Code restructure failed: missing block: B:15:0x0041, code lost:
    
        if (r2 != null) goto L37;
     */
    /* JADX WARN: Code restructure failed: missing block: B:22:0x0043, code lost:
    
        r2.close();
     */
    /* JADX WARN: Code restructure failed: missing block: B:28:0x002b, code lost:
    
        if (r5 != 4) goto L48;
     */
    /* JADX WARN: Code restructure failed: missing block: B:31:0x0038, code lost:
    
        if (r3.getName().equals("locales") == false) goto L53;
     */
    /* JADX WARN: Code restructure failed: missing block: B:33:0x003a, code lost:
    
        r1 = r3.getAttributeValue(null, "application_locales");
     */
    /* JADX WARN: Code restructure failed: missing block: B:42:0x004d, code lost:
    
        if (r2 != null) goto L37;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static java.lang.String o0(android.content.Context r8) {
        /*
            java.lang.String r0 = "androidx.appcompat.app.AppCompatDelegate.application_locales_record_file"
            java.lang.String r1 = ""
            java.io.FileInputStream r2 = r8.openFileInput(r0)     // Catch: java.io.FileNotFoundException -> L5a
            org.xmlpull.v1.XmlPullParser r3 = android.util.Xml.newPullParser()     // Catch: java.lang.Throwable -> L26 java.lang.Throwable -> L4d
            java.lang.String r4 = "UTF-8"
            r3.setInput(r2, r4)     // Catch: java.lang.Throwable -> L26 java.lang.Throwable -> L4d
            int r4 = r3.getDepth()     // Catch: java.lang.Throwable -> L26 java.lang.Throwable -> L4d
        L15:
            int r5 = r3.next()     // Catch: java.lang.Throwable -> L26 java.lang.Throwable -> L4d
            r6 = 1
            if (r5 == r6) goto L41
            r6 = 3
            if (r5 != r6) goto L28
            int r7 = r3.getDepth()     // Catch: java.lang.Throwable -> L26 java.lang.Throwable -> L4d
            if (r7 <= r4) goto L41
            goto L28
        L26:
            r8 = move-exception
            goto L47
        L28:
            if (r5 == r6) goto L15
            r6 = 4
            if (r5 != r6) goto L2e
            goto L15
        L2e:
            java.lang.String r5 = r3.getName()     // Catch: java.lang.Throwable -> L26 java.lang.Throwable -> L4d
            java.lang.String r6 = "locales"
            boolean r5 = r5.equals(r6)     // Catch: java.lang.Throwable -> L26 java.lang.Throwable -> L4d
            if (r5 == 0) goto L15
            java.lang.String r4 = "application_locales"
            r5 = 0
            java.lang.String r1 = r3.getAttributeValue(r5, r4)     // Catch: java.lang.Throwable -> L26 java.lang.Throwable -> L4d
        L41:
            if (r2 == 0) goto L50
        L43:
            r2.close()     // Catch: java.io.IOException -> L50
            goto L50
        L47:
            if (r2 == 0) goto L4c
            r2.close()     // Catch: java.io.IOException -> L4c
        L4c:
            throw r8
        L4d:
            if (r2 == 0) goto L50
            goto L43
        L50:
            boolean r2 = r1.isEmpty()
            if (r2 != 0) goto L57
            goto L5a
        L57:
            r8.deleteFile(r0)
        L5a:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: l6.f.o0(android.content.Context):java.lang.String");
    }

    public static List p(ArrayList arrayList, ArrayList arrayList2) {
        if (arrayList == null) {
            return arrayList2;
        }
        if (arrayList2 == null) {
            return arrayList;
        }
        q.c cVar = new q.c(arrayList2.size() + arrayList.size());
        cVar.addAll(arrayList);
        cVar.addAll(arrayList2);
        return new ArrayList(cVar);
    }

    public static void p0(TextInputLayout textInputLayout, CheckableImageButton checkableImageButton, ColorStateList colorStateList) {
        Drawable drawable = checkableImageButton.getDrawable();
        if (checkableImageButton.getDrawable() == null || colorStateList == null || !colorStateList.isStateful()) {
            return;
        }
        int[] drawableState = textInputLayout.getDrawableState();
        int[] drawableState2 = checkableImageButton.getDrawableState();
        int length = drawableState.length;
        int[] copyOf = Arrays.copyOf(drawableState, drawableState.length + drawableState2.length);
        System.arraycopy(drawableState2, 0, copyOf, length, drawableState2.length);
        int colorForState = colorStateList.getColorForState(copyOf, colorStateList.getDefaultColor());
        Drawable mutate = drawable.mutate();
        j0.b.h(mutate, ColorStateList.valueOf(colorForState));
        checkableImageButton.setImageDrawable(mutate);
    }

    public static ImageView.ScaleType q(int i7) {
        return i7 != 0 ? i7 != 1 ? i7 != 2 ? i7 != 3 ? i7 != 5 ? i7 != 6 ? ImageView.ScaleType.CENTER : ImageView.ScaleType.CENTER_INSIDE : ImageView.ScaleType.CENTER_CROP : ImageView.ScaleType.FIT_END : ImageView.ScaleType.FIT_CENTER : ImageView.ScaleType.FIT_START : ImageView.ScaleType.FIT_XY;
    }

    public static final void q0(String str) {
        System.err.println("SLF4J: " + str);
    }

    public static f r(int i7) {
        if (i7 != 0 && i7 == 1) {
            return new p3.e();
        }
        return new m();
    }

    public static final void r0(String str, Throwable th) {
        PrintStream printStream = System.err;
        printStream.println(str);
        printStream.println("Reported exception:");
        th.printStackTrace();
    }

    public static p3.f s() {
        int i7 = 0;
        return new p3.f(i7, i7);
    }

    public static TypedValue s0(Context context, int i7) {
        TypedValue typedValue = new TypedValue();
        if (context.getTheme().resolveAttribute(i7, typedValue, true)) {
            return typedValue;
        }
        return null;
    }

    public static s t(u.i iVar, int i7, ArrayList arrayList, s sVar) {
        int i8;
        int i9 = i7 == 0 ? iVar.f9167t0 : iVar.f9169u0;
        if (i9 != -1 && (sVar == null || i9 != sVar.f9370b)) {
            int i10 = 0;
            while (true) {
                if (i10 >= arrayList.size()) {
                    break;
                }
                s sVar2 = (s) arrayList.get(i10);
                if (sVar2.f9370b == i9) {
                    if (sVar != null) {
                        sVar.c(i7, sVar2);
                        arrayList.remove(sVar);
                    }
                    sVar = sVar2;
                } else {
                    i10++;
                }
            }
        } else if (i9 != -1) {
            return sVar;
        }
        if (sVar == null) {
            if (iVar instanceof p) {
                p pVar = (p) iVar;
                int i11 = 0;
                while (true) {
                    if (i11 >= pVar.f9211w0) {
                        i8 = -1;
                        break;
                    }
                    u.i iVar2 = pVar.f9210v0[i11];
                    if ((i7 == 0 && (i8 = iVar2.f9167t0) != -1) || (i7 == 1 && (i8 = iVar2.f9169u0) != -1)) {
                        break;
                    }
                    i11++;
                }
                if (i8 != -1) {
                    int i12 = 0;
                    while (true) {
                        if (i12 >= arrayList.size()) {
                            break;
                        }
                        s sVar3 = (s) arrayList.get(i12);
                        if (sVar3.f9370b == i8) {
                            sVar = sVar3;
                            break;
                        }
                        i12++;
                    }
                }
            }
            if (sVar == null) {
                sVar = new s();
                sVar.f9369a = new ArrayList();
                sVar.f9372d = null;
                sVar.f9373e = -1;
                int i13 = s.f9368f;
                s.f9368f = i13 + 1;
                sVar.f9370b = i13;
                sVar.f9371c = i7;
            }
            arrayList.add(sVar);
        }
        ArrayList arrayList2 = sVar.f9369a;
        if (!arrayList2.contains(iVar)) {
            arrayList2.add(iVar);
            if (iVar instanceof n) {
                n nVar = (n) iVar;
                nVar.f9208y0.c(nVar.f9209z0 == 0 ? 1 : 0, sVar, arrayList);
            }
            int i14 = sVar.f9370b;
            if (i7 == 0) {
                iVar.f9167t0 = i14;
                iVar.J.c(i7, sVar, arrayList);
                iVar.L.c(i7, sVar, arrayList);
            } else {
                iVar.f9169u0 = i14;
                iVar.K.c(i7, sVar, arrayList);
                iVar.N.c(i7, sVar, arrayList);
                iVar.M.c(i7, sVar, arrayList);
            }
            iVar.Q.c(i7, sVar, arrayList);
        }
        return sVar;
    }

    public static boolean t0(Context context, int i7, boolean z6) {
        TypedValue s02 = s0(context, i7);
        return (s02 == null || s02.type != 18) ? z6 : s02.data != 0;
    }

    public static int u(float[] fArr, int[] iArr, byte[] bArr) {
        int i7 = u.MAX_ACTIVITY_COUNT_UNLIMITED;
        for (int i8 = 0; i8 < 6; i8++) {
            int ceil = (int) Math.ceil(fArr[i8]);
            iArr[i8] = ceil;
            if (i7 > ceil) {
                Arrays.fill(bArr, (byte) 0);
                i7 = ceil;
            }
            if (i7 == ceil) {
                bArr[i8] = (byte) (bArr[i8] + 1);
            }
        }
        return i7;
    }

    public static int u0(Context context, int i7, int i8) {
        TypedValue s02 = s0(context, i7);
        return (s02 == null || s02.type != 16) ? i8 : s02.data;
    }

    public static float v(float f7, float f8, float f9) {
        return 1.0f - ((f7 - f9) / (f8 - f9));
    }

    public static TimeInterpolator v0(Context context, int i7, TimeInterpolator timeInterpolator) {
        TypedValue typedValue = new TypedValue();
        if (!context.getTheme().resolveAttribute(i7, typedValue, true)) {
            return timeInterpolator;
        }
        if (typedValue.type != 3) {
            throw new IllegalArgumentException("Motion easing theme attribute must be an @interpolator resource for ?attr/motionEasing*Interpolator attributes or a string for ?attr/motionEasing* attributes.");
        }
        String valueOf = String.valueOf(typedValue.string);
        if (!O(valueOf, "cubic-bezier") && !O(valueOf, "path")) {
            return AnimationUtils.loadInterpolator(context, typedValue.resourceId);
        }
        if (!O(valueOf, "cubic-bezier")) {
            if (O(valueOf, "path")) {
                return r0.a.c(z.O(valueOf.substring(5, valueOf.length() - 1)));
            }
            throw new IllegalArgumentException("Invalid motion easing type: ".concat(valueOf));
        }
        String[] split = valueOf.substring(13, valueOf.length() - 1).split(",");
        if (split.length == 4) {
            return r0.a.b(C(split, 0), C(split, 1), C(split, 2), C(split, 3));
        }
        throw new IllegalArgumentException("Motion easing theme attribute must have 4 control points if using bezier curve format; instead got: " + split.length);
    }

    public static ColorStateList w(Context context, TypedArray typedArray, int i7) {
        int resourceId;
        ColorStateList b7;
        return (!typedArray.hasValue(i7) || (resourceId = typedArray.getResourceId(i7, 0)) == 0 || (b7 = g.b(context, resourceId)) == null) ? typedArray.getColorStateList(i7) : b7;
    }

    public static TypedValue w0(Context context, int i7, String str) {
        TypedValue s02 = s0(context, i7);
        if (s02 != null) {
            return s02;
        }
        throw new IllegalArgumentException(String.format("%1$s requires a value for the %2$s attribute to be set in your app theme. You can either set the attribute in your theme or update your theme to inherit from Theme.MaterialComponents (or a descendant).", str, context.getResources().getResourceName(i7)));
    }

    public static ColorStateList x(Context context, androidx.appcompat.app.g gVar, int i7) {
        int N;
        ColorStateList b7;
        return (!gVar.R(i7) || (N = gVar.N(i7, 0)) == 0 || (b7 = g.b(context, N)) == null) ? gVar.F(i7) : b7;
    }

    public static void x0(View view, float f7) {
        Drawable background = view.getBackground();
        if (background instanceof j) {
            ((j) background).n(f7);
        }
    }

    public static void y0(CheckableImageButton checkableImageButton, View.OnLongClickListener onLongClickListener) {
        int i7 = d1.OVER_SCROLL_ALWAYS;
        boolean a7 = k0.a(checkableImageButton);
        boolean z6 = onLongClickListener != null;
        boolean z7 = a7 || z6;
        checkableImageButton.setFocusable(z7);
        checkableImageButton.setClickable(a7);
        checkableImageButton.setPressable(a7);
        checkableImageButton.setLongClickable(z6);
        l0.s(checkableImageButton, z7 ? 1 : 2);
    }

    public static int z(Context context, TypedArray typedArray, int i7, int i8) {
        TypedValue typedValue = new TypedValue();
        if (!typedArray.getValue(i7, typedValue) || typedValue.type != 2) {
            return typedArray.getDimensionPixelSize(i7, i8);
        }
        TypedArray obtainStyledAttributes = context.getTheme().obtainStyledAttributes(new int[]{typedValue.data});
        int dimensionPixelSize = obtainStyledAttributes.getDimensionPixelSize(0, i8);
        obtainStyledAttributes.recycle();
        return dimensionPixelSize;
    }

    public static void z0(a0.c cVar, View view, float[] fArr) {
        Class<?> cls = view.getClass();
        String str = "set" + cVar.f3b;
        try {
            boolean z6 = true;
            switch (x.a.f9520a[cVar.f4c.ordinal()]) {
                case 1:
                    cls.getMethod(str, Integer.TYPE).invoke(view, Integer.valueOf((int) fArr[0]));
                    return;
                case 2:
                    cls.getMethod(str, Float.TYPE).invoke(view, Float.valueOf(fArr[0]));
                    return;
                case 3:
                    Method method = cls.getMethod(str, Drawable.class);
                    int m6 = (m((int) (fArr[3] * 255.0f)) << 24) | (m((int) (((float) Math.pow(fArr[0], 0.45454545454545453d)) * 255.0f)) << 16) | (m((int) (((float) Math.pow(fArr[1], 0.45454545454545453d)) * 255.0f)) << 8) | m((int) (((float) Math.pow(fArr[2], 0.45454545454545453d)) * 255.0f));
                    ColorDrawable colorDrawable = new ColorDrawable();
                    colorDrawable.setColor(m6);
                    method.invoke(view, colorDrawable);
                    return;
                case 4:
                    cls.getMethod(str, Integer.TYPE).invoke(view, Integer.valueOf((m((int) (fArr[3] * 255.0f)) << 24) | (m((int) (((float) Math.pow(fArr[0], 0.45454545454545453d)) * 255.0f)) << 16) | (m((int) (((float) Math.pow(fArr[1], 0.45454545454545453d)) * 255.0f)) << 8) | m((int) (((float) Math.pow(fArr[2], 0.45454545454545453d)) * 255.0f))));
                    return;
                case 5:
                    throw new RuntimeException("unable to interpolate strings " + cVar.f3b);
                case 6:
                    Method method2 = cls.getMethod(str, Boolean.TYPE);
                    Object[] objArr = new Object[1];
                    if (fArr[0] <= 0.5f) {
                        z6 = false;
                    }
                    objArr[0] = Boolean.valueOf(z6);
                    method2.invoke(view, objArr);
                    return;
                case 7:
                    cls.getMethod(str, Float.TYPE).invoke(view, Float.valueOf(fArr[0]));
                    return;
                default:
                    return;
            }
        } catch (IllegalAccessException e7) {
            G(view);
            e7.printStackTrace();
        } catch (NoSuchMethodException e8) {
            G(view);
            e8.printStackTrace();
        } catch (InvocationTargetException e9) {
            e9.printStackTrace();
        }
    }

    public abstract boolean C0(CarouselLayoutManager carouselLayoutManager, int i7);

    public abstract boolean E0(int i7, View view);

    public int I(View view) {
        return 0;
    }

    public int J() {
        return 0;
    }

    public void W(int i7, int i8) {
    }

    public void X() {
    }

    public abstract void Y(Throwable th);

    public abstract y2.i Z(CarouselLayoutManager carouselLayoutManager, View view);

    public abstract void a0(int i7);

    @Override // p0.m1
    public void b(View view) {
    }

    public abstract void b0(Typeface typeface);

    @Override // p0.m1
    public void c() {
    }

    public abstract void c0(Typeface typeface, boolean z6);

    public void d0(FloatingActionButton floatingActionButton) {
    }

    public abstract void e0(j.h hVar);

    public abstract void f0(x4.c cVar);

    public void g0() {
    }

    public void h(int i7) {
        new Handler(Looper.getMainLooper()).post(new h0.n(i7, 0, this));
    }

    public void h0(int i7, View view) {
    }

    public void i(Typeface typeface) {
        new Handler(Looper.getMainLooper()).post(new p0(2, this, typeface));
    }

    public abstract void i0(int i7);

    public abstract boolean j(r.f fVar, r.c cVar);

    public abstract void j0(View view, int i7, int i8);

    public abstract boolean k(r.f fVar, Object obj, Object obj2);

    public abstract void k0(View view, float f7, float f8);

    public abstract boolean l(r.f fVar, r.e eVar, r.e eVar2);

    public abstract void m0(r.e eVar, r.e eVar2);

    public abstract int n(View view, int i7);

    public abstract void n0(r.e eVar, Thread thread);

    public abstract int o(View view, int i7);

    public abstract void y(float f7, float f8, p3.z zVar);
}
