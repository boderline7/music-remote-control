package o1;

import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.util.TypedValue;
import com.yalantis.ucrop.view.CropImageView;
import java.util.ArrayDeque;
import n1.z;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final class p extends g {

    /* renamed from: j, reason: collision with root package name */
    public static final PorterDuff.Mode f7867j = PorterDuff.Mode.SRC_IN;

    /* renamed from: b, reason: collision with root package name */
    public n f7868b;

    /* renamed from: c, reason: collision with root package name */
    public PorterDuffColorFilter f7869c;

    /* renamed from: d, reason: collision with root package name */
    public ColorFilter f7870d;

    /* renamed from: e, reason: collision with root package name */
    public boolean f7871e;

    /* renamed from: f, reason: collision with root package name */
    public boolean f7872f;

    /* renamed from: g, reason: collision with root package name */
    public final float[] f7873g;

    /* renamed from: h, reason: collision with root package name */
    public final Matrix f7874h;

    /* renamed from: i, reason: collision with root package name */
    public final Rect f7875i;

    public p() {
        this.f7872f = true;
        this.f7873g = new float[9];
        this.f7874h = new Matrix();
        this.f7875i = new Rect();
        n nVar = new n();
        nVar.f7856c = null;
        nVar.f7857d = f7867j;
        nVar.f7855b = new m();
        this.f7868b = nVar;
    }

    public p(n nVar) {
        this.f7872f = true;
        this.f7873g = new float[9];
        this.f7874h = new Matrix();
        this.f7875i = new Rect();
        this.f7868b = nVar;
        this.f7869c = a(nVar.f7856c, nVar.f7857d);
    }

    public final PorterDuffColorFilter a(ColorStateList colorStateList, PorterDuff.Mode mode) {
        if (colorStateList == null || mode == null) {
            return null;
        }
        return new PorterDuffColorFilter(colorStateList.getColorForState(getState(), 0), mode);
    }

    @Override // android.graphics.drawable.Drawable
    public final boolean canApplyTheme() {
        Drawable drawable = this.f7810a;
        if (drawable == null) {
            return false;
        }
        j0.b.b(drawable);
        return false;
    }

    @Override // android.graphics.drawable.Drawable
    public final void draw(Canvas canvas) {
        Paint paint;
        Drawable drawable = this.f7810a;
        if (drawable != null) {
            drawable.draw(canvas);
            return;
        }
        Rect rect = this.f7875i;
        copyBounds(rect);
        if (rect.width() <= 0 || rect.height() <= 0) {
            return;
        }
        ColorFilter colorFilter = this.f7870d;
        if (colorFilter == null) {
            colorFilter = this.f7869c;
        }
        Matrix matrix = this.f7874h;
        canvas.getMatrix(matrix);
        float[] fArr = this.f7873g;
        matrix.getValues(fArr);
        float abs = Math.abs(fArr[0]);
        float abs2 = Math.abs(fArr[4]);
        float abs3 = Math.abs(fArr[1]);
        float abs4 = Math.abs(fArr[3]);
        if (abs3 != CropImageView.DEFAULT_ASPECT_RATIO || abs4 != CropImageView.DEFAULT_ASPECT_RATIO) {
            abs = 1.0f;
            abs2 = 1.0f;
        }
        int width = (int) (rect.width() * abs);
        int min = Math.min(2048, width);
        int min2 = Math.min(2048, (int) (rect.height() * abs2));
        if (min <= 0 || min2 <= 0) {
            return;
        }
        int save = canvas.save();
        canvas.translate(rect.left, rect.top);
        if (isAutoMirrored() && j0.c.a(this) == 1) {
            canvas.translate(rect.width(), CropImageView.DEFAULT_ASPECT_RATIO);
            canvas.scale(-1.0f, 1.0f);
        }
        rect.offsetTo(0, 0);
        n nVar = this.f7868b;
        Bitmap bitmap = nVar.f7859f;
        if (bitmap == null || min != bitmap.getWidth() || min2 != nVar.f7859f.getHeight()) {
            nVar.f7859f = Bitmap.createBitmap(min, min2, Bitmap.Config.ARGB_8888);
            nVar.f7864k = true;
        }
        if (this.f7872f) {
            n nVar2 = this.f7868b;
            if (nVar2.f7864k || nVar2.f7860g != nVar2.f7856c || nVar2.f7861h != nVar2.f7857d || nVar2.f7863j != nVar2.f7858e || nVar2.f7862i != nVar2.f7855b.getRootAlpha()) {
                n nVar3 = this.f7868b;
                nVar3.f7859f.eraseColor(0);
                Canvas canvas2 = new Canvas(nVar3.f7859f);
                m mVar = nVar3.f7855b;
                mVar.a(mVar.f7845g, m.f7838p, canvas2, min, min2);
                n nVar4 = this.f7868b;
                nVar4.f7860g = nVar4.f7856c;
                nVar4.f7861h = nVar4.f7857d;
                nVar4.f7862i = nVar4.f7855b.getRootAlpha();
                nVar4.f7863j = nVar4.f7858e;
                nVar4.f7864k = false;
            }
        } else {
            n nVar5 = this.f7868b;
            nVar5.f7859f.eraseColor(0);
            Canvas canvas3 = new Canvas(nVar5.f7859f);
            m mVar2 = nVar5.f7855b;
            mVar2.a(mVar2.f7845g, m.f7838p, canvas3, min, min2);
        }
        n nVar6 = this.f7868b;
        if (nVar6.f7855b.getRootAlpha() >= 255 && colorFilter == null) {
            paint = null;
        } else {
            if (nVar6.f7865l == null) {
                Paint paint2 = new Paint();
                nVar6.f7865l = paint2;
                paint2.setFilterBitmap(true);
            }
            nVar6.f7865l.setAlpha(nVar6.f7855b.getRootAlpha());
            nVar6.f7865l.setColorFilter(colorFilter);
            paint = nVar6.f7865l;
        }
        canvas.drawBitmap(nVar6.f7859f, (Rect) null, rect, paint);
        canvas.restoreToCount(save);
    }

    @Override // android.graphics.drawable.Drawable
    public final int getAlpha() {
        Drawable drawable = this.f7810a;
        return drawable != null ? j0.a.a(drawable) : this.f7868b.f7855b.getRootAlpha();
    }

    @Override // android.graphics.drawable.Drawable
    public final int getChangingConfigurations() {
        Drawable drawable = this.f7810a;
        return drawable != null ? drawable.getChangingConfigurations() : super.getChangingConfigurations() | this.f7868b.getChangingConfigurations();
    }

    @Override // android.graphics.drawable.Drawable
    public final ColorFilter getColorFilter() {
        Drawable drawable = this.f7810a;
        return drawable != null ? j0.b.c(drawable) : this.f7870d;
    }

    @Override // android.graphics.drawable.Drawable
    public final Drawable.ConstantState getConstantState() {
        if (this.f7810a != null) {
            return new o(this.f7810a.getConstantState());
        }
        this.f7868b.f7854a = getChangingConfigurations();
        return this.f7868b;
    }

    @Override // android.graphics.drawable.Drawable
    public final int getIntrinsicHeight() {
        Drawable drawable = this.f7810a;
        return drawable != null ? drawable.getIntrinsicHeight() : (int) this.f7868b.f7855b.f7847i;
    }

    @Override // android.graphics.drawable.Drawable
    public final int getIntrinsicWidth() {
        Drawable drawable = this.f7810a;
        return drawable != null ? drawable.getIntrinsicWidth() : (int) this.f7868b.f7855b.f7846h;
    }

    @Override // android.graphics.drawable.Drawable
    public final int getOpacity() {
        Drawable drawable = this.f7810a;
        if (drawable != null) {
            return drawable.getOpacity();
        }
        return -3;
    }

    @Override // android.graphics.drawable.Drawable
    public final void inflate(Resources resources, XmlPullParser xmlPullParser, AttributeSet attributeSet) {
        Drawable drawable = this.f7810a;
        if (drawable != null) {
            drawable.inflate(resources, xmlPullParser, attributeSet);
        } else {
            inflate(resources, xmlPullParser, attributeSet, null);
        }
    }

    @Override // android.graphics.drawable.Drawable
    public final void inflate(Resources resources, XmlPullParser xmlPullParser, AttributeSet attributeSet, Resources.Theme theme) {
        m mVar;
        int i7;
        int i8;
        boolean z6;
        int i9;
        boolean z7;
        Paint.Join join;
        Paint.Cap cap;
        Drawable drawable = this.f7810a;
        if (drawable != null) {
            j0.b.d(drawable, resources, xmlPullParser, attributeSet, theme);
            return;
        }
        n nVar = this.f7868b;
        nVar.f7855b = new m();
        TypedArray O0 = z.O0(resources, theme, attributeSet, a.f7791a);
        n nVar2 = this.f7868b;
        m mVar2 = nVar2.f7855b;
        int i10 = !z.v0(xmlPullParser, "tintMode") ? -1 : O0.getInt(6, -1);
        PorterDuff.Mode mode = PorterDuff.Mode.SRC_IN;
        int i11 = 3;
        if (i10 == 3) {
            mode = PorterDuff.Mode.SRC_OVER;
        } else if (i10 != 5) {
            if (i10 != 9) {
                switch (i10) {
                    case 14:
                        mode = PorterDuff.Mode.MULTIPLY;
                        break;
                    case 15:
                        mode = PorterDuff.Mode.SCREEN;
                        break;
                    case 16:
                        mode = PorterDuff.Mode.ADD;
                        break;
                }
            } else {
                mode = PorterDuff.Mode.SRC_ATOP;
            }
        }
        nVar2.f7857d = mode;
        int i12 = 1;
        ColorStateList colorStateList = null;
        boolean z8 = false;
        if (z.v0(xmlPullParser, "tint")) {
            TypedValue typedValue = new TypedValue();
            O0.getValue(1, typedValue);
            int i13 = typedValue.type;
            if (i13 == 2) {
                throw new UnsupportedOperationException("Failed to resolve attribute at index 1: " + typedValue);
            }
            if (i13 < 28 || i13 > 31) {
                Resources resources2 = O0.getResources();
                int resourceId = O0.getResourceId(1, 0);
                ThreadLocal threadLocal = h0.c.f6619a;
                try {
                    colorStateList = h0.c.a(resources2, resources2.getXml(resourceId), theme);
                } catch (Exception unused) {
                }
            } else {
                colorStateList = ColorStateList.valueOf(typedValue.data);
            }
        }
        ColorStateList colorStateList2 = colorStateList;
        if (colorStateList2 != null) {
            nVar2.f7856c = colorStateList2;
        }
        boolean z9 = nVar2.f7858e;
        if (z.v0(xmlPullParser, "autoMirrored")) {
            z9 = O0.getBoolean(5, z9);
        }
        nVar2.f7858e = z9;
        float f7 = mVar2.f7848j;
        if (z.v0(xmlPullParser, "viewportWidth")) {
            f7 = O0.getFloat(7, f7);
        }
        mVar2.f7848j = f7;
        float f8 = mVar2.f7849k;
        if (z.v0(xmlPullParser, "viewportHeight")) {
            f8 = O0.getFloat(8, f8);
        }
        mVar2.f7849k = f8;
        if (mVar2.f7848j <= CropImageView.DEFAULT_ASPECT_RATIO) {
            throw new XmlPullParserException(O0.getPositionDescription() + "<vector> tag requires viewportWidth > 0");
        }
        if (f8 <= CropImageView.DEFAULT_ASPECT_RATIO) {
            throw new XmlPullParserException(O0.getPositionDescription() + "<vector> tag requires viewportHeight > 0");
        }
        mVar2.f7846h = O0.getDimension(3, mVar2.f7846h);
        float dimension = O0.getDimension(2, mVar2.f7847i);
        mVar2.f7847i = dimension;
        if (mVar2.f7846h <= CropImageView.DEFAULT_ASPECT_RATIO) {
            throw new XmlPullParserException(O0.getPositionDescription() + "<vector> tag requires width > 0");
        }
        if (dimension <= CropImageView.DEFAULT_ASPECT_RATIO) {
            throw new XmlPullParserException(O0.getPositionDescription() + "<vector> tag requires height > 0");
        }
        float alpha = mVar2.getAlpha();
        if (z.v0(xmlPullParser, y.c.ALPHA)) {
            alpha = O0.getFloat(4, alpha);
        }
        mVar2.setAlpha(alpha);
        String string = O0.getString(0);
        if (string != null) {
            mVar2.f7851m = string;
            mVar2.f7853o.put(string, mVar2);
        }
        O0.recycle();
        nVar.f7854a = getChangingConfigurations();
        nVar.f7864k = true;
        n nVar3 = this.f7868b;
        m mVar3 = nVar3.f7855b;
        ArrayDeque arrayDeque = new ArrayDeque();
        arrayDeque.push(mVar3.f7845g);
        int eventType = xmlPullParser.getEventType();
        int depth = xmlPullParser.getDepth() + 1;
        boolean z10 = true;
        while (eventType != i12 && (xmlPullParser.getDepth() >= depth || eventType != i11)) {
            if (eventType == 2) {
                String name = xmlPullParser.getName();
                j jVar = (j) arrayDeque.peek();
                boolean equals = "path".equals(name);
                i7 = depth;
                q.b bVar = mVar3.f7853o;
                if (equals) {
                    i iVar = new i();
                    iVar.f7812f = CropImageView.DEFAULT_ASPECT_RATIO;
                    iVar.f7814h = 1.0f;
                    iVar.f7815i = 1.0f;
                    iVar.f7816j = CropImageView.DEFAULT_ASPECT_RATIO;
                    iVar.f7817k = 1.0f;
                    iVar.f7818l = CropImageView.DEFAULT_ASPECT_RATIO;
                    Paint.Cap cap2 = Paint.Cap.BUTT;
                    iVar.f7819m = cap2;
                    Paint.Join join2 = Paint.Join.MITER;
                    iVar.f7820n = join2;
                    mVar = mVar3;
                    iVar.f7821o = 4.0f;
                    TypedArray O02 = z.O0(resources, theme, attributeSet, a.f7793c);
                    if (z.v0(xmlPullParser, "pathData")) {
                        String string2 = O02.getString(0);
                        if (string2 != null) {
                            iVar.f7835b = string2;
                        }
                        String string3 = O02.getString(2);
                        if (string3 != null) {
                            iVar.f7834a = z.N(string3);
                        }
                        iVar.f7813g = z.p0(O02, xmlPullParser, theme, "fillColor", 1);
                        float f9 = iVar.f7815i;
                        if (z.v0(xmlPullParser, "fillAlpha")) {
                            f9 = O02.getFloat(12, f9);
                        }
                        iVar.f7815i = f9;
                        int i14 = !z.v0(xmlPullParser, "strokeLineCap") ? -1 : O02.getInt(8, -1);
                        Paint.Cap cap3 = iVar.f7819m;
                        if (i14 != 0) {
                            join = join2;
                            cap = i14 != 1 ? i14 != 2 ? cap3 : Paint.Cap.SQUARE : Paint.Cap.ROUND;
                        } else {
                            join = join2;
                            cap = cap2;
                        }
                        iVar.f7819m = cap;
                        int i15 = !z.v0(xmlPullParser, "strokeLineJoin") ? -1 : O02.getInt(9, -1);
                        iVar.f7820n = i15 != 0 ? i15 != 1 ? i15 != 2 ? iVar.f7820n : Paint.Join.BEVEL : Paint.Join.ROUND : join;
                        float f10 = iVar.f7821o;
                        if (z.v0(xmlPullParser, "strokeMiterLimit")) {
                            f10 = O02.getFloat(10, f10);
                        }
                        iVar.f7821o = f10;
                        iVar.f7811e = z.p0(O02, xmlPullParser, theme, "strokeColor", 3);
                        float f11 = iVar.f7814h;
                        if (z.v0(xmlPullParser, "strokeAlpha")) {
                            f11 = O02.getFloat(11, f11);
                        }
                        iVar.f7814h = f11;
                        float f12 = iVar.f7812f;
                        if (z.v0(xmlPullParser, "strokeWidth")) {
                            f12 = O02.getFloat(4, f12);
                        }
                        iVar.f7812f = f12;
                        float f13 = iVar.f7817k;
                        if (z.v0(xmlPullParser, "trimPathEnd")) {
                            f13 = O02.getFloat(6, f13);
                        }
                        iVar.f7817k = f13;
                        float f14 = iVar.f7818l;
                        if (z.v0(xmlPullParser, "trimPathOffset")) {
                            f14 = O02.getFloat(7, f14);
                        }
                        iVar.f7818l = f14;
                        float f15 = iVar.f7816j;
                        if (z.v0(xmlPullParser, "trimPathStart")) {
                            f15 = O02.getFloat(5, f15);
                        }
                        iVar.f7816j = f15;
                        int i16 = iVar.f7836c;
                        if (z.v0(xmlPullParser, "fillType")) {
                            i16 = O02.getInt(13, i16);
                        }
                        iVar.f7836c = i16;
                    }
                    O02.recycle();
                    jVar.f7823b.add(iVar);
                    if (iVar.getPathName() != null) {
                        bVar.put(iVar.getPathName(), iVar);
                    }
                    nVar3.f7854a |= iVar.f7837d;
                    z7 = false;
                    i8 = 1;
                    z10 = false;
                } else {
                    mVar = mVar3;
                    if ("clip-path".equals(name)) {
                        h hVar = new h();
                        if (z.v0(xmlPullParser, "pathData")) {
                            TypedArray O03 = z.O0(resources, theme, attributeSet, a.f7794d);
                            String string4 = O03.getString(0);
                            if (string4 != null) {
                                hVar.f7835b = string4;
                            }
                            String string5 = O03.getString(1);
                            if (string5 != null) {
                                hVar.f7834a = z.N(string5);
                            }
                            hVar.f7836c = !z.v0(xmlPullParser, "fillType") ? 0 : O03.getInt(2, 0);
                            O03.recycle();
                        }
                        jVar.f7823b.add(hVar);
                        if (hVar.getPathName() != null) {
                            bVar.put(hVar.getPathName(), hVar);
                        }
                        nVar3.f7854a = hVar.f7837d | nVar3.f7854a;
                    } else if ("group".equals(name)) {
                        j jVar2 = new j();
                        TypedArray O04 = z.O0(resources, theme, attributeSet, a.f7792b);
                        float f16 = jVar2.f7824c;
                        if (z.v0(xmlPullParser, y.c.ROTATION)) {
                            f16 = O04.getFloat(5, f16);
                        }
                        jVar2.f7824c = f16;
                        i8 = 1;
                        jVar2.f7825d = O04.getFloat(1, jVar2.f7825d);
                        jVar2.f7826e = O04.getFloat(2, jVar2.f7826e);
                        float f17 = jVar2.f7827f;
                        if (z.v0(xmlPullParser, y.c.SCALE_X)) {
                            f17 = O04.getFloat(3, f17);
                        }
                        jVar2.f7827f = f17;
                        float f18 = jVar2.f7828g;
                        if (z.v0(xmlPullParser, y.c.SCALE_Y)) {
                            f18 = O04.getFloat(4, f18);
                        }
                        jVar2.f7828g = f18;
                        float f19 = jVar2.f7829h;
                        if (z.v0(xmlPullParser, "translateX")) {
                            f19 = O04.getFloat(6, f19);
                        }
                        jVar2.f7829h = f19;
                        float f20 = jVar2.f7830i;
                        if (z.v0(xmlPullParser, "translateY")) {
                            f20 = O04.getFloat(7, f20);
                        }
                        jVar2.f7830i = f20;
                        z7 = false;
                        String string6 = O04.getString(0);
                        if (string6 != null) {
                            jVar2.f7833l = string6;
                        }
                        jVar2.c();
                        O04.recycle();
                        jVar.f7823b.add(jVar2);
                        arrayDeque.push(jVar2);
                        if (jVar2.getGroupName() != null) {
                            bVar.put(jVar2.getGroupName(), jVar2);
                        }
                        nVar3.f7854a = jVar2.f7832k | nVar3.f7854a;
                    }
                    z7 = false;
                    i8 = 1;
                }
                z6 = z7;
                i9 = 3;
            } else {
                mVar = mVar3;
                i7 = depth;
                i8 = i12;
                z6 = z8;
                i9 = 3;
                if (eventType == 3 && "group".equals(xmlPullParser.getName())) {
                    arrayDeque.pop();
                }
            }
            eventType = xmlPullParser.next();
            i11 = i9;
            z8 = z6;
            i12 = i8;
            depth = i7;
            mVar3 = mVar;
        }
        if (z10) {
            throw new XmlPullParserException("no path defined");
        }
        this.f7869c = a(nVar.f7856c, nVar.f7857d);
    }

    @Override // android.graphics.drawable.Drawable
    public final void invalidateSelf() {
        Drawable drawable = this.f7810a;
        if (drawable != null) {
            drawable.invalidateSelf();
        } else {
            super.invalidateSelf();
        }
    }

    @Override // android.graphics.drawable.Drawable
    public final boolean isAutoMirrored() {
        Drawable drawable = this.f7810a;
        return drawable != null ? j0.a.d(drawable) : this.f7868b.f7858e;
    }

    @Override // android.graphics.drawable.Drawable
    public final boolean isStateful() {
        ColorStateList colorStateList;
        Drawable drawable = this.f7810a;
        if (drawable != null) {
            return drawable.isStateful();
        }
        if (!super.isStateful()) {
            n nVar = this.f7868b;
            if (nVar != null) {
                m mVar = nVar.f7855b;
                if (mVar.f7852n == null) {
                    mVar.f7852n = Boolean.valueOf(mVar.f7845g.a());
                }
                if (mVar.f7852n.booleanValue() || ((colorStateList = this.f7868b.f7856c) != null && colorStateList.isStateful())) {
                }
            }
            return false;
        }
        return true;
    }

    @Override // android.graphics.drawable.Drawable
    public final Drawable mutate() {
        Drawable drawable = this.f7810a;
        if (drawable != null) {
            drawable.mutate();
            return this;
        }
        if (!this.f7871e && super.mutate() == this) {
            n nVar = this.f7868b;
            n nVar2 = new n();
            nVar2.f7856c = null;
            nVar2.f7857d = f7867j;
            if (nVar != null) {
                nVar2.f7854a = nVar.f7854a;
                m mVar = new m(nVar.f7855b);
                nVar2.f7855b = mVar;
                if (nVar.f7855b.f7843e != null) {
                    mVar.f7843e = new Paint(nVar.f7855b.f7843e);
                }
                if (nVar.f7855b.f7842d != null) {
                    nVar2.f7855b.f7842d = new Paint(nVar.f7855b.f7842d);
                }
                nVar2.f7856c = nVar.f7856c;
                nVar2.f7857d = nVar.f7857d;
                nVar2.f7858e = nVar.f7858e;
            }
            this.f7868b = nVar2;
            this.f7871e = true;
        }
        return this;
    }

    @Override // android.graphics.drawable.Drawable
    public final void onBoundsChange(Rect rect) {
        Drawable drawable = this.f7810a;
        if (drawable != null) {
            drawable.setBounds(rect);
        }
    }

    @Override // android.graphics.drawable.Drawable
    public final boolean onStateChange(int[] iArr) {
        boolean z6;
        PorterDuff.Mode mode;
        Drawable drawable = this.f7810a;
        if (drawable != null) {
            return drawable.setState(iArr);
        }
        n nVar = this.f7868b;
        ColorStateList colorStateList = nVar.f7856c;
        if (colorStateList == null || (mode = nVar.f7857d) == null) {
            z6 = false;
        } else {
            this.f7869c = a(colorStateList, mode);
            invalidateSelf();
            z6 = true;
        }
        m mVar = nVar.f7855b;
        if (mVar.f7852n == null) {
            mVar.f7852n = Boolean.valueOf(mVar.f7845g.a());
        }
        if (mVar.f7852n.booleanValue()) {
            boolean b7 = nVar.f7855b.f7845g.b(iArr);
            nVar.f7864k |= b7;
            if (b7) {
                invalidateSelf();
                return true;
            }
        }
        return z6;
    }

    @Override // android.graphics.drawable.Drawable
    public final void scheduleSelf(Runnable runnable, long j7) {
        Drawable drawable = this.f7810a;
        if (drawable != null) {
            drawable.scheduleSelf(runnable, j7);
        } else {
            super.scheduleSelf(runnable, j7);
        }
    }

    @Override // android.graphics.drawable.Drawable
    public final void setAlpha(int i7) {
        Drawable drawable = this.f7810a;
        if (drawable != null) {
            drawable.setAlpha(i7);
        } else if (this.f7868b.f7855b.getRootAlpha() != i7) {
            this.f7868b.f7855b.setRootAlpha(i7);
            invalidateSelf();
        }
    }

    @Override // android.graphics.drawable.Drawable
    public final void setAutoMirrored(boolean z6) {
        Drawable drawable = this.f7810a;
        if (drawable != null) {
            j0.a.e(drawable, z6);
        } else {
            this.f7868b.f7858e = z6;
        }
    }

    @Override // android.graphics.drawable.Drawable
    public final void setColorFilter(ColorFilter colorFilter) {
        Drawable drawable = this.f7810a;
        if (drawable != null) {
            drawable.setColorFilter(colorFilter);
        } else {
            this.f7870d = colorFilter;
            invalidateSelf();
        }
    }

    @Override // android.graphics.drawable.Drawable
    public final void setTint(int i7) {
        Drawable drawable = this.f7810a;
        if (drawable != null) {
            z.b1(drawable, i7);
        } else {
            setTintList(ColorStateList.valueOf(i7));
        }
    }

    @Override // android.graphics.drawable.Drawable
    public final void setTintList(ColorStateList colorStateList) {
        Drawable drawable = this.f7810a;
        if (drawable != null) {
            j0.b.h(drawable, colorStateList);
            return;
        }
        n nVar = this.f7868b;
        if (nVar.f7856c != colorStateList) {
            nVar.f7856c = colorStateList;
            this.f7869c = a(colorStateList, nVar.f7857d);
            invalidateSelf();
        }
    }

    @Override // android.graphics.drawable.Drawable
    public final void setTintMode(PorterDuff.Mode mode) {
        Drawable drawable = this.f7810a;
        if (drawable != null) {
            j0.b.i(drawable, mode);
            return;
        }
        n nVar = this.f7868b;
        if (nVar.f7857d != mode) {
            nVar.f7857d = mode;
            this.f7869c = a(nVar.f7856c, mode);
            invalidateSelf();
        }
    }

    @Override // android.graphics.drawable.Drawable
    public final boolean setVisible(boolean z6, boolean z7) {
        Drawable drawable = this.f7810a;
        return drawable != null ? drawable.setVisible(z6, z7) : super.setVisible(z6, z7);
    }

    @Override // android.graphics.drawable.Drawable
    public final void unscheduleSelf(Runnable runnable) {
        Drawable drawable = this.f7810a;
        if (drawable != null) {
            drawable.unscheduleSelf(runnable);
        } else {
            super.unscheduleSelf(runnable);
        }
    }
}
