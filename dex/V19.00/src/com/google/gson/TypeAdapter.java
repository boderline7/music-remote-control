package com.google.gson;

import com.google.gson.internal.bind.JsonTreeReader;
import com.google.gson.internal.bind.JsonTreeWriter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;
import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import java.io.StringWriter;
import java.io.Writer;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_2_1633180.dex */
public abstract class TypeAdapter<T> {
    public final T fromJson(Reader reader) {
        return read(new JsonReader(reader));
    }

    public final T fromJson(String str) {
        return fromJson(new StringReader(str));
    }

    public final T fromJsonTree(JsonElement jsonElement) {
        try {
            return read(new JsonTreeReader(jsonElement));
        } catch (IOException e7) {
            throw new JsonIOException(e7);
        }
    }

    public final TypeAdapter<T> nullSafe() {
        return new TypeAdapter<T>() { // from class: com.google.gson.TypeAdapter.1
            @Override // com.google.gson.TypeAdapter
            public T read(JsonReader jsonReader) {
                if (jsonReader.peek() != JsonToken.NULL) {
                    return (T) TypeAdapter.this.read(jsonReader);
                }
                jsonReader.nextNull();
                return null;
            }

            @Override // com.google.gson.TypeAdapter
            public void write(JsonWriter jsonWriter, T t6) {
                if (t6 == null) {
                    jsonWriter.nullValue();
                } else {
                    TypeAdapter.this.write(jsonWriter, t6);
                }
            }
        };
    }

    public abstract T read(JsonReader jsonReader);

    public final String toJson(T t6) {
        StringWriter stringWriter = new StringWriter();
        try {
            toJson(stringWriter, t6);
            return stringWriter.toString();
        } catch (IOException e7) {
            throw new JsonIOException(e7);
        }
    }

    public final void toJson(Writer writer, T t6) {
        write(new JsonWriter(writer), t6);
    }

    public final JsonElement toJsonTree(T t6) {
        try {
            JsonTreeWriter jsonTreeWriter = new JsonTreeWriter();
            write(jsonTreeWriter, t6);
            return jsonTreeWriter.get();
        } catch (IOException e7) {
            throw new JsonIOException(e7);
        }
    }

    public abstract void write(JsonWriter jsonWriter, T t6);
}
