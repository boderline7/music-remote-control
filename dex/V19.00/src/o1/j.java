package o1;

import android.graphics.Matrix;
import android.graphics.Paint;
import com.yalantis.ucrop.view.CropImageView;
import java.util.ArrayList;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final class j extends k {

    /* renamed from: a, reason: collision with root package name */
    public final Matrix f7822a;

    /* renamed from: b, reason: collision with root package name */
    public final ArrayList f7823b;

    /* renamed from: c, reason: collision with root package name */
    public float f7824c;

    /* renamed from: d, reason: collision with root package name */
    public float f7825d;

    /* renamed from: e, reason: collision with root package name */
    public float f7826e;

    /* renamed from: f, reason: collision with root package name */
    public float f7827f;

    /* renamed from: g, reason: collision with root package name */
    public float f7828g;

    /* renamed from: h, reason: collision with root package name */
    public float f7829h;

    /* renamed from: i, reason: collision with root package name */
    public float f7830i;

    /* renamed from: j, reason: collision with root package name */
    public final Matrix f7831j;

    /* renamed from: k, reason: collision with root package name */
    public final int f7832k;

    /* renamed from: l, reason: collision with root package name */
    public String f7833l;

    public j() {
        this.f7822a = new Matrix();
        this.f7823b = new ArrayList();
        this.f7824c = CropImageView.DEFAULT_ASPECT_RATIO;
        this.f7825d = CropImageView.DEFAULT_ASPECT_RATIO;
        this.f7826e = CropImageView.DEFAULT_ASPECT_RATIO;
        this.f7827f = 1.0f;
        this.f7828g = 1.0f;
        this.f7829h = CropImageView.DEFAULT_ASPECT_RATIO;
        this.f7830i = CropImageView.DEFAULT_ASPECT_RATIO;
        this.f7831j = new Matrix();
        this.f7833l = null;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public j(j jVar, q.b bVar) {
        h hVar;
        this.f7822a = new Matrix();
        this.f7823b = new ArrayList();
        this.f7824c = CropImageView.DEFAULT_ASPECT_RATIO;
        this.f7825d = CropImageView.DEFAULT_ASPECT_RATIO;
        this.f7826e = CropImageView.DEFAULT_ASPECT_RATIO;
        this.f7827f = 1.0f;
        this.f7828g = 1.0f;
        this.f7829h = CropImageView.DEFAULT_ASPECT_RATIO;
        this.f7830i = CropImageView.DEFAULT_ASPECT_RATIO;
        Matrix matrix = new Matrix();
        this.f7831j = matrix;
        this.f7833l = null;
        this.f7824c = jVar.f7824c;
        this.f7825d = jVar.f7825d;
        this.f7826e = jVar.f7826e;
        this.f7827f = jVar.f7827f;
        this.f7828g = jVar.f7828g;
        this.f7829h = jVar.f7829h;
        this.f7830i = jVar.f7830i;
        String str = jVar.f7833l;
        this.f7833l = str;
        this.f7832k = jVar.f7832k;
        if (str != null) {
            bVar.put(str, this);
        }
        matrix.set(jVar.f7831j);
        ArrayList arrayList = jVar.f7823b;
        for (int i7 = 0; i7 < arrayList.size(); i7++) {
            Object obj = arrayList.get(i7);
            if (obj instanceof j) {
                this.f7823b.add(new j((j) obj, bVar));
            } else {
                if (obj instanceof i) {
                    i iVar = (i) obj;
                    i iVar2 = new i(iVar);
                    iVar2.f7812f = CropImageView.DEFAULT_ASPECT_RATIO;
                    iVar2.f7814h = 1.0f;
                    iVar2.f7815i = 1.0f;
                    iVar2.f7816j = CropImageView.DEFAULT_ASPECT_RATIO;
                    iVar2.f7817k = 1.0f;
                    iVar2.f7818l = CropImageView.DEFAULT_ASPECT_RATIO;
                    iVar2.f7819m = Paint.Cap.BUTT;
                    iVar2.f7820n = Paint.Join.MITER;
                    iVar2.f7821o = 4.0f;
                    iVar2.f7811e = iVar.f7811e;
                    iVar2.f7812f = iVar.f7812f;
                    iVar2.f7814h = iVar.f7814h;
                    iVar2.f7813g = iVar.f7813g;
                    iVar2.f7836c = iVar.f7836c;
                    iVar2.f7815i = iVar.f7815i;
                    iVar2.f7816j = iVar.f7816j;
                    iVar2.f7817k = iVar.f7817k;
                    iVar2.f7818l = iVar.f7818l;
                    iVar2.f7819m = iVar.f7819m;
                    iVar2.f7820n = iVar.f7820n;
                    iVar2.f7821o = iVar.f7821o;
                    hVar = iVar2;
                } else {
                    if (!(obj instanceof h)) {
                        throw new IllegalStateException("Unknown object in the tree!");
                    }
                    hVar = new h((h) obj);
                }
                this.f7823b.add(hVar);
                Object obj2 = hVar.f7835b;
                if (obj2 != null) {
                    bVar.put(obj2, hVar);
                }
            }
        }
    }

    @Override // o1.k
    public final boolean a() {
        int i7 = 0;
        while (true) {
            ArrayList arrayList = this.f7823b;
            if (i7 >= arrayList.size()) {
                return false;
            }
            if (((k) arrayList.get(i7)).a()) {
                return true;
            }
            i7++;
        }
    }

    @Override // o1.k
    public final boolean b(int[] iArr) {
        int i7 = 0;
        boolean z6 = false;
        while (true) {
            ArrayList arrayList = this.f7823b;
            if (i7 >= arrayList.size()) {
                return z6;
            }
            z6 |= ((k) arrayList.get(i7)).b(iArr);
            i7++;
        }
    }

    public final void c() {
        Matrix matrix = this.f7831j;
        matrix.reset();
        matrix.postTranslate(-this.f7825d, -this.f7826e);
        matrix.postScale(this.f7827f, this.f7828g);
        matrix.postRotate(this.f7824c, CropImageView.DEFAULT_ASPECT_RATIO, CropImageView.DEFAULT_ASPECT_RATIO);
        matrix.postTranslate(this.f7829h + this.f7825d, this.f7830i + this.f7826e);
    }

    public String getGroupName() {
        return this.f7833l;
    }

    public Matrix getLocalMatrix() {
        return this.f7831j;
    }

    public float getPivotX() {
        return this.f7825d;
    }

    public float getPivotY() {
        return this.f7826e;
    }

    public float getRotation() {
        return this.f7824c;
    }

    public float getScaleX() {
        return this.f7827f;
    }

    public float getScaleY() {
        return this.f7828g;
    }

    public float getTranslateX() {
        return this.f7829h;
    }

    public float getTranslateY() {
        return this.f7830i;
    }

    public void setPivotX(float f7) {
        if (f7 != this.f7825d) {
            this.f7825d = f7;
            c();
        }
    }

    public void setPivotY(float f7) {
        if (f7 != this.f7826e) {
            this.f7826e = f7;
            c();
        }
    }

    public void setRotation(float f7) {
        if (f7 != this.f7824c) {
            this.f7824c = f7;
            c();
        }
    }

    public void setScaleX(float f7) {
        if (f7 != this.f7827f) {
            this.f7827f = f7;
            c();
        }
    }

    public void setScaleY(float f7) {
        if (f7 != this.f7828g) {
            this.f7828g = f7;
            c();
        }
    }

    public void setTranslateX(float f7) {
        if (f7 != this.f7829h) {
            this.f7829h = f7;
            c();
        }
    }

    public void setTranslateY(float f7) {
        if (f7 != this.f7830i) {
            this.f7830i = f7;
            c();
        }
    }
}
