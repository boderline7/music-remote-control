package p1;

import android.os.Parcel;
import android.os.Parcelable;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public abstract class a {

    /* renamed from: a, reason: collision with root package name */
    public final q.b f8212a;

    /* renamed from: b, reason: collision with root package name */
    public final q.b f8213b;

    /* renamed from: c, reason: collision with root package name */
    public final q.b f8214c;

    public a(q.b bVar, q.b bVar2, q.b bVar3) {
        this.f8212a = bVar;
        this.f8213b = bVar2;
        this.f8214c = bVar3;
    }

    public abstract b a();

    public final Class b(Class cls) {
        String name = cls.getName();
        q.b bVar = this.f8214c;
        Class cls2 = (Class) bVar.getOrDefault(name, null);
        if (cls2 != null) {
            return cls2;
        }
        Class<?> cls3 = Class.forName(String.format("%s.%sParcelizer", cls.getPackage().getName(), cls.getSimpleName()), false, cls.getClassLoader());
        bVar.put(cls.getName(), cls3);
        return cls3;
    }

    public final Method c(String str) {
        q.b bVar = this.f8212a;
        Method method = (Method) bVar.getOrDefault(str, null);
        if (method != null) {
            return method;
        }
        System.currentTimeMillis();
        Method declaredMethod = Class.forName(str, true, a.class.getClassLoader()).getDeclaredMethod("read", a.class);
        bVar.put(str, declaredMethod);
        return declaredMethod;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final Method d(Class cls) {
        String name = cls.getName();
        q.b bVar = this.f8213b;
        Method method = (Method) bVar.getOrDefault(name, null);
        if (method != null) {
            return method;
        }
        Class b7 = b(cls);
        System.currentTimeMillis();
        Method declaredMethod = b7.getDeclaredMethod("write", cls, a.class);
        bVar.put(cls.getName(), declaredMethod);
        return declaredMethod;
    }

    public abstract boolean e(int i7);

    public final int f(int i7, int i8) {
        return !e(i8) ? i7 : ((b) this).f8216e.readInt();
    }

    public final Parcelable g(Parcelable parcelable, int i7) {
        if (!e(i7)) {
            return parcelable;
        }
        return ((b) this).f8216e.readParcelable(b.class.getClassLoader());
    }

    public final c h() {
        String readString = ((b) this).f8216e.readString();
        if (readString == null) {
            return null;
        }
        try {
            return (c) c(readString).invoke(null, a());
        } catch (ClassNotFoundException e7) {
            throw new RuntimeException("VersionedParcel encountered ClassNotFoundException", e7);
        } catch (IllegalAccessException e8) {
            throw new RuntimeException("VersionedParcel encountered IllegalAccessException", e8);
        } catch (NoSuchMethodException e9) {
            throw new RuntimeException("VersionedParcel encountered NoSuchMethodException", e9);
        } catch (InvocationTargetException e10) {
            if (e10.getCause() instanceof RuntimeException) {
                throw ((RuntimeException) e10.getCause());
            }
            throw new RuntimeException("VersionedParcel encountered InvocationTargetException", e10);
        }
    }

    public abstract void i(int i7);

    public final void j(int i7, int i8) {
        i(i8);
        ((b) this).f8216e.writeInt(i7);
    }

    public final void k(Parcelable parcelable, int i7) {
        i(i7);
        ((b) this).f8216e.writeParcelable(parcelable, 0);
    }

    public final void l(c cVar) {
        if (cVar == null) {
            ((b) this).f8216e.writeString(null);
            return;
        }
        try {
            ((b) this).f8216e.writeString(b(cVar.getClass()).getName());
            b a7 = a();
            try {
                d(cVar.getClass()).invoke(null, cVar, a7);
                int i7 = a7.f8220i;
                if (i7 >= 0) {
                    int i8 = a7.f8215d.get(i7);
                    Parcel parcel = a7.f8216e;
                    int dataPosition = parcel.dataPosition();
                    parcel.setDataPosition(i8);
                    parcel.writeInt(dataPosition - i8);
                    parcel.setDataPosition(dataPosition);
                }
            } catch (ClassNotFoundException e7) {
                throw new RuntimeException("VersionedParcel encountered ClassNotFoundException", e7);
            } catch (IllegalAccessException e8) {
                throw new RuntimeException("VersionedParcel encountered IllegalAccessException", e8);
            } catch (NoSuchMethodException e9) {
                throw new RuntimeException("VersionedParcel encountered NoSuchMethodException", e9);
            } catch (InvocationTargetException e10) {
                if (!(e10.getCause() instanceof RuntimeException)) {
                    throw new RuntimeException("VersionedParcel encountered InvocationTargetException", e10);
                }
                throw ((RuntimeException) e10.getCause());
            }
        } catch (ClassNotFoundException e11) {
            throw new RuntimeException(cVar.getClass().getSimpleName().concat(" does not have a Parcelizer"), e11);
        }
    }
}
