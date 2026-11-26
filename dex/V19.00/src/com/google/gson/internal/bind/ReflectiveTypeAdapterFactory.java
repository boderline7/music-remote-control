package com.google.gson.internal.bind;

import a.a;
import com.google.gson.FieldNamingStrategy;
import com.google.gson.Gson;
import com.google.gson.JsonIOException;
import com.google.gson.JsonParseException;
import com.google.gson.JsonSyntaxException;
import com.google.gson.ReflectionAccessFilter;
import com.google.gson.TypeAdapter;
import com.google.gson.TypeAdapterFactory;
import com.google.gson.annotations.JsonAdapter;
import com.google.gson.annotations.SerializedName;
import com.google.gson.internal.C$Gson$Types;
import com.google.gson.internal.ConstructorConstructor;
import com.google.gson.internal.Excluder;
import com.google.gson.internal.ObjectConstructor;
import com.google.gson.internal.Primitives;
import com.google.gson.internal.ReflectionAccessFilterHelper;
import com.google.gson.internal.reflect.ReflectionHelper;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;
import com.yalantis.ucrop.view.CropImageView;
import java.lang.reflect.AccessibleObject;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Member;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_2_1633180.dex */
public final class ReflectiveTypeAdapterFactory implements TypeAdapterFactory {
    private final ConstructorConstructor constructorConstructor;
    private final Excluder excluder;
    private final FieldNamingStrategy fieldNamingPolicy;
    private final JsonAdapterAnnotationTypeAdapterFactory jsonAdapterFactory;
    private final List<ReflectionAccessFilter> reflectionFilters;

    public static abstract class Adapter<T, A> extends TypeAdapter<T> {
        final Map<String, BoundField> boundFields;

        public Adapter(Map<String, BoundField> map) {
            this.boundFields = map;
        }

        public abstract A createAccumulator();

        public abstract T finalize(A a7);

        @Override // com.google.gson.TypeAdapter
        /* renamed from: read */
        public T read2(JsonReader jsonReader) {
            if (jsonReader.peek() == JsonToken.NULL) {
                jsonReader.nextNull();
                return null;
            }
            A createAccumulator = createAccumulator();
            try {
                jsonReader.beginObject();
                while (jsonReader.hasNext()) {
                    BoundField boundField = this.boundFields.get(jsonReader.nextName());
                    if (boundField != null && boundField.deserialized) {
                        readField(createAccumulator, jsonReader, boundField);
                    }
                    jsonReader.skipValue();
                }
                jsonReader.endObject();
                return finalize(createAccumulator);
            } catch (IllegalAccessException e7) {
                throw ReflectionHelper.createExceptionForUnexpectedIllegalAccess(e7);
            } catch (IllegalStateException e8) {
                throw new JsonSyntaxException(e8);
            }
        }

        public abstract void readField(A a7, JsonReader jsonReader, BoundField boundField);

        @Override // com.google.gson.TypeAdapter
        public void write(JsonWriter jsonWriter, T t6) {
            if (t6 == null) {
                jsonWriter.nullValue();
                return;
            }
            jsonWriter.beginObject();
            try {
                Iterator<BoundField> it = this.boundFields.values().iterator();
                while (it.hasNext()) {
                    it.next().write(jsonWriter, t6);
                }
                jsonWriter.endObject();
            } catch (IllegalAccessException e7) {
                throw ReflectionHelper.createExceptionForUnexpectedIllegalAccess(e7);
            }
        }
    }

    public static abstract class BoundField {
        final boolean deserialized;
        final Field field;
        final String fieldName;
        final String name;
        final boolean serialized;

        public BoundField(String str, Field field, boolean z6, boolean z7) {
            this.name = str;
            this.field = field;
            this.fieldName = field.getName();
            this.serialized = z6;
            this.deserialized = z7;
        }

        public abstract void readIntoArray(JsonReader jsonReader, int i7, Object[] objArr);

        public abstract void readIntoField(JsonReader jsonReader, Object obj);

        public abstract void write(JsonWriter jsonWriter, Object obj);
    }

    public static final class FieldReflectionAdapter<T> extends Adapter<T, T> {
        private final ObjectConstructor<T> constructor;

        public FieldReflectionAdapter(ObjectConstructor<T> objectConstructor, Map<String, BoundField> map) {
            super(map);
            this.constructor = objectConstructor;
        }

        @Override // com.google.gson.internal.bind.ReflectiveTypeAdapterFactory.Adapter
        public T createAccumulator() {
            return this.constructor.construct();
        }

        @Override // com.google.gson.internal.bind.ReflectiveTypeAdapterFactory.Adapter
        public T finalize(T t6) {
            return t6;
        }

        @Override // com.google.gson.internal.bind.ReflectiveTypeAdapterFactory.Adapter
        public void readField(T t6, JsonReader jsonReader, BoundField boundField) {
            boundField.readIntoField(jsonReader, t6);
        }
    }

    public static final class RecordAdapter<T> extends Adapter<T, Object[]> {
        static final Map<Class<?>, Object> PRIMITIVE_DEFAULTS = primitiveDefaults();
        private final Map<String, Integer> componentIndices;
        private final Constructor<T> constructor;
        private final Object[] constructorArgsDefaults;

        public RecordAdapter(Class<T> cls, Map<String, BoundField> map, boolean z6) {
            super(map);
            this.componentIndices = new HashMap();
            Constructor<T> canonicalRecordConstructor = ReflectionHelper.getCanonicalRecordConstructor(cls);
            this.constructor = canonicalRecordConstructor;
            if (z6) {
                ReflectiveTypeAdapterFactory.checkAccessible(null, canonicalRecordConstructor);
            } else {
                ReflectionHelper.makeAccessible(canonicalRecordConstructor);
            }
            String[] recordComponentNames = ReflectionHelper.getRecordComponentNames(cls);
            for (int i7 = 0; i7 < recordComponentNames.length; i7++) {
                this.componentIndices.put(recordComponentNames[i7], Integer.valueOf(i7));
            }
            Class<?>[] parameterTypes = this.constructor.getParameterTypes();
            this.constructorArgsDefaults = new Object[parameterTypes.length];
            for (int i8 = 0; i8 < parameterTypes.length; i8++) {
                this.constructorArgsDefaults[i8] = PRIMITIVE_DEFAULTS.get(parameterTypes[i8]);
            }
        }

        private static Map<Class<?>, Object> primitiveDefaults() {
            HashMap hashMap = new HashMap();
            hashMap.put(Byte.TYPE, (byte) 0);
            hashMap.put(Short.TYPE, (short) 0);
            hashMap.put(Integer.TYPE, 0);
            hashMap.put(Long.TYPE, 0L);
            hashMap.put(Float.TYPE, Float.valueOf(CropImageView.DEFAULT_ASPECT_RATIO));
            hashMap.put(Double.TYPE, Double.valueOf(0.0d));
            hashMap.put(Character.TYPE, (char) 0);
            hashMap.put(Boolean.TYPE, Boolean.FALSE);
            return hashMap;
        }

        @Override // com.google.gson.internal.bind.ReflectiveTypeAdapterFactory.Adapter
        public Object[] createAccumulator() {
            return (Object[]) this.constructorArgsDefaults.clone();
        }

        @Override // com.google.gson.internal.bind.ReflectiveTypeAdapterFactory.Adapter
        public T finalize(Object[] objArr) {
            try {
                return this.constructor.newInstance(objArr);
            } catch (IllegalAccessException e7) {
                throw ReflectionHelper.createExceptionForUnexpectedIllegalAccess(e7);
            } catch (IllegalArgumentException e8) {
                e = e8;
                throw new RuntimeException("Failed to invoke constructor '" + ReflectionHelper.constructorToString(this.constructor) + "' with args " + Arrays.toString(objArr), e);
            } catch (InstantiationException e9) {
                e = e9;
                throw new RuntimeException("Failed to invoke constructor '" + ReflectionHelper.constructorToString(this.constructor) + "' with args " + Arrays.toString(objArr), e);
            } catch (InvocationTargetException e10) {
                throw new RuntimeException("Failed to invoke constructor '" + ReflectionHelper.constructorToString(this.constructor) + "' with args " + Arrays.toString(objArr), e10.getCause());
            }
        }

        @Override // com.google.gson.internal.bind.ReflectiveTypeAdapterFactory.Adapter
        public void readField(Object[] objArr, JsonReader jsonReader, BoundField boundField) {
            Integer num = this.componentIndices.get(boundField.fieldName);
            if (num != null) {
                boundField.readIntoArray(jsonReader, num.intValue(), objArr);
                return;
            }
            StringBuilder sb = new StringBuilder("Could not find the index in the constructor '");
            sb.append(ReflectionHelper.constructorToString(this.constructor));
            sb.append("' for field with name '");
            throw new IllegalStateException(a.i(sb, boundField.fieldName, "', unable to determine which argument in the constructor the field corresponds to. This is unexpected behavior, as we expect the RecordComponents to have the same names as the fields in the Java class, and that the order of the RecordComponents is the same as the order of the canonical constructor parameters."));
        }
    }

    public ReflectiveTypeAdapterFactory(ConstructorConstructor constructorConstructor, FieldNamingStrategy fieldNamingStrategy, Excluder excluder, JsonAdapterAnnotationTypeAdapterFactory jsonAdapterAnnotationTypeAdapterFactory, List<ReflectionAccessFilter> list) {
        this.constructorConstructor = constructorConstructor;
        this.fieldNamingPolicy = fieldNamingStrategy;
        this.excluder = excluder;
        this.jsonAdapterFactory = jsonAdapterAnnotationTypeAdapterFactory;
        this.reflectionFilters = list;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static <M extends AccessibleObject & Member> void checkAccessible(Object obj, M m6) {
        if (Modifier.isStatic(m6.getModifiers())) {
            obj = null;
        }
        if (!ReflectionAccessFilterHelper.canAccess(m6, obj)) {
            throw new JsonIOException(a.g(ReflectionHelper.getAccessibleObjectDescription(m6, true), " is not accessible and ReflectionAccessFilter does not permit making it accessible. Register a TypeAdapter for the declaring type, adjust the access filter or increase the visibility of the element and its declaring type."));
        }
    }

    private BoundField createBoundField(final Gson gson, Field field, final Method method, String str, final TypeToken<?> typeToken, boolean z6, boolean z7, final boolean z8) {
        final boolean isPrimitive = Primitives.isPrimitive(typeToken.getRawType());
        int modifiers = field.getModifiers();
        final boolean z9 = Modifier.isStatic(modifiers) && Modifier.isFinal(modifiers);
        JsonAdapter jsonAdapter = (JsonAdapter) field.getAnnotation(JsonAdapter.class);
        TypeAdapter<?> typeAdapter = jsonAdapter != null ? this.jsonAdapterFactory.getTypeAdapter(this.constructorConstructor, gson, typeToken, jsonAdapter) : null;
        final boolean z10 = typeAdapter != null;
        if (typeAdapter == null) {
            typeAdapter = gson.getAdapter(typeToken);
        }
        final TypeAdapter<?> typeAdapter2 = typeAdapter;
        return new BoundField(str, field, z6, z7) { // from class: com.google.gson.internal.bind.ReflectiveTypeAdapterFactory.1
            @Override // com.google.gson.internal.bind.ReflectiveTypeAdapterFactory.BoundField
            public void readIntoArray(JsonReader jsonReader, int i7, Object[] objArr) {
                Object read2 = typeAdapter2.read2(jsonReader);
                if (read2 != null || !isPrimitive) {
                    objArr[i7] = read2;
                    return;
                }
                throw new JsonParseException("null is not allowed as value for record component '" + this.fieldName + "' of primitive type; at path " + jsonReader.getPath());
            }

            @Override // com.google.gson.internal.bind.ReflectiveTypeAdapterFactory.BoundField
            public void readIntoField(JsonReader jsonReader, Object obj) {
                Object read2 = typeAdapter2.read2(jsonReader);
                if (read2 == null && isPrimitive) {
                    return;
                }
                if (z8) {
                    ReflectiveTypeAdapterFactory.checkAccessible(obj, this.field);
                } else if (z9) {
                    throw new JsonIOException(a.B("Cannot set value of 'static final' ", ReflectionHelper.getAccessibleObjectDescription(this.field, false)));
                }
                this.field.set(obj, read2);
            }

            @Override // com.google.gson.internal.bind.ReflectiveTypeAdapterFactory.BoundField
            public void write(JsonWriter jsonWriter, Object obj) {
                Object obj2;
                if (this.serialized) {
                    if (z8) {
                        Method method2 = method;
                        if (method2 == null) {
                            ReflectiveTypeAdapterFactory.checkAccessible(obj, this.field);
                        } else {
                            ReflectiveTypeAdapterFactory.checkAccessible(obj, method2);
                        }
                    }
                    Method method3 = method;
                    if (method3 != null) {
                        try {
                            obj2 = method3.invoke(obj, new Object[0]);
                        } catch (InvocationTargetException e7) {
                            throw new JsonIOException(a.h("Accessor ", ReflectionHelper.getAccessibleObjectDescription(method, false), " threw exception"), e7.getCause());
                        }
                    } else {
                        obj2 = this.field.get(obj);
                    }
                    if (obj2 == obj) {
                        return;
                    }
                    jsonWriter.name(this.name);
                    (z10 ? typeAdapter2 : new TypeAdapterRuntimeTypeWrapper(gson, typeAdapter2, typeToken.getType())).write(jsonWriter, obj2);
                }
            }
        };
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r1v16 */
    /* JADX WARN: Type inference failed for: r1v4 */
    /* JADX WARN: Type inference failed for: r1v5, types: [int] */
    private Map<String, BoundField> getBoundFields(Gson gson, TypeToken<?> typeToken, Class<?> cls, boolean z6, boolean z7) {
        boolean z8;
        Method method;
        int i7;
        int i8;
        boolean z9;
        ReflectiveTypeAdapterFactory reflectiveTypeAdapterFactory = this;
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        if (cls.isInterface()) {
            return linkedHashMap;
        }
        TypeToken<?> typeToken2 = typeToken;
        boolean z10 = z6;
        Class<?> cls2 = cls;
        while (cls2 != Object.class) {
            Field[] declaredFields = cls2.getDeclaredFields();
            boolean z11 = true;
            boolean z12 = false;
            if (cls2 != cls && declaredFields.length > 0) {
                ReflectionAccessFilter.FilterResult filterResult = ReflectionAccessFilterHelper.getFilterResult(reflectiveTypeAdapterFactory.reflectionFilters, cls2);
                if (filterResult == ReflectionAccessFilter.FilterResult.BLOCK_ALL) {
                    throw new JsonIOException("ReflectionAccessFilter does not permit using reflection for " + cls2 + " (supertype of " + cls + "). Register a TypeAdapter for this type or adjust the access filter.");
                }
                z10 = filterResult == ReflectionAccessFilter.FilterResult.BLOCK_INACCESSIBLE;
            }
            boolean z13 = z10;
            int length = declaredFields.length;
            int i9 = 0;
            while (i9 < length) {
                Field field = declaredFields[i9];
                boolean includeField = reflectiveTypeAdapterFactory.includeField(field, z11);
                boolean includeField2 = reflectiveTypeAdapterFactory.includeField(field, z12);
                if (includeField || includeField2) {
                    BoundField boundField = null;
                    if (!z7) {
                        z8 = includeField2;
                        method = null;
                    } else if (Modifier.isStatic(field.getModifiers())) {
                        method = null;
                        z8 = z12;
                    } else {
                        Method accessor = ReflectionHelper.getAccessor(cls2, field);
                        if (!z13) {
                            ReflectionHelper.makeAccessible(accessor);
                        }
                        if (accessor.getAnnotation(SerializedName.class) != null && field.getAnnotation(SerializedName.class) == null) {
                            throw new JsonIOException(a.h("@SerializedName on ", ReflectionHelper.getAccessibleObjectDescription(accessor, z12), " is not supported"));
                        }
                        z8 = includeField2;
                        method = accessor;
                    }
                    if (!z13 && method == null) {
                        ReflectionHelper.makeAccessible(field);
                    }
                    Type resolve = C$Gson$Types.resolve(typeToken2.getType(), cls2, field.getGenericType());
                    List<String> fieldNames = reflectiveTypeAdapterFactory.getFieldNames(field);
                    int size = fieldNames.size();
                    ?? r12 = z12;
                    while (r12 < size) {
                        String str = fieldNames.get(r12);
                        boolean z14 = r12 != 0 ? z12 : includeField;
                        int i10 = r12;
                        BoundField boundField2 = boundField;
                        int i11 = size;
                        List<String> list = fieldNames;
                        Field field2 = field;
                        int i12 = i9;
                        int i13 = length;
                        boolean z15 = z12;
                        boundField = boundField2 == null ? (BoundField) linkedHashMap.put(str, createBoundField(gson, field, method, str, TypeToken.get(resolve), z14, z8, z13)) : boundField2;
                        includeField = z14;
                        i9 = i12;
                        size = i11;
                        fieldNames = list;
                        field = field2;
                        length = i13;
                        z12 = z15;
                        r12 = i10 + 1;
                    }
                    BoundField boundField3 = boundField;
                    Field field3 = field;
                    i7 = i9;
                    i8 = length;
                    z9 = z12;
                    if (boundField3 != null) {
                        throw new IllegalArgumentException("Class " + cls.getName() + " declares multiple JSON fields named '" + boundField3.name + "'; conflict is caused by fields " + ReflectionHelper.fieldToString(boundField3.field) + " and " + ReflectionHelper.fieldToString(field3));
                    }
                } else {
                    i7 = i9;
                    i8 = length;
                    z9 = z12;
                }
                i9 = i7 + 1;
                z11 = true;
                reflectiveTypeAdapterFactory = this;
                length = i8;
                z12 = z9;
            }
            typeToken2 = TypeToken.get(C$Gson$Types.resolve(typeToken2.getType(), cls2, cls2.getGenericSuperclass()));
            cls2 = typeToken2.getRawType();
            reflectiveTypeAdapterFactory = this;
            z10 = z13;
        }
        return linkedHashMap;
    }

    private List<String> getFieldNames(Field field) {
        SerializedName serializedName = (SerializedName) field.getAnnotation(SerializedName.class);
        if (serializedName == null) {
            return Collections.singletonList(this.fieldNamingPolicy.translateName(field));
        }
        String value = serializedName.value();
        String[] alternate = serializedName.alternate();
        if (alternate.length == 0) {
            return Collections.singletonList(value);
        }
        ArrayList arrayList = new ArrayList(alternate.length + 1);
        arrayList.add(value);
        Collections.addAll(arrayList, alternate);
        return arrayList;
    }

    private boolean includeField(Field field, boolean z6) {
        return (this.excluder.excludeClass(field.getType(), z6) || this.excluder.excludeField(field, z6)) ? false : true;
    }

    @Override // com.google.gson.TypeAdapterFactory
    public <T> TypeAdapter<T> create(Gson gson, TypeToken<T> typeToken) {
        Class<? super T> rawType = typeToken.getRawType();
        if (!Object.class.isAssignableFrom(rawType)) {
            return null;
        }
        ReflectionAccessFilter.FilterResult filterResult = ReflectionAccessFilterHelper.getFilterResult(this.reflectionFilters, rawType);
        if (filterResult != ReflectionAccessFilter.FilterResult.BLOCK_ALL) {
            boolean z6 = filterResult == ReflectionAccessFilter.FilterResult.BLOCK_INACCESSIBLE;
            return ReflectionHelper.isRecord(rawType) ? new RecordAdapter(rawType, getBoundFields(gson, typeToken, rawType, z6, true), z6) : new FieldReflectionAdapter(this.constructorConstructor.get(typeToken), getBoundFields(gson, typeToken, rawType, z6, false));
        }
        throw new JsonIOException("ReflectionAccessFilter does not permit using reflection for " + rawType + ". Register a TypeAdapter for this type or adjust the access filter.");
    }
}
