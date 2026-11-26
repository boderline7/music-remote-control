package com.google.gson;

import com.google.gson.internal.Streams;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.MalformedJsonException;
import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_2_1633180.dex */
public final class JsonParser {
    @Deprecated
    public JsonParser() {
    }

    public static JsonElement parseReader(JsonReader jsonReader) {
        boolean isLenient = jsonReader.isLenient();
        jsonReader.setLenient(true);
        try {
            try {
                return Streams.parse(jsonReader);
            } catch (OutOfMemoryError e7) {
                throw new JsonParseException("Failed parsing JSON source: " + jsonReader + " to Json", e7);
            } catch (StackOverflowError e8) {
                throw new JsonParseException("Failed parsing JSON source: " + jsonReader + " to Json", e8);
            }
        } finally {
            jsonReader.setLenient(isLenient);
        }
    }

    public static JsonElement parseReader(Reader reader) {
        try {
            JsonReader jsonReader = new JsonReader(reader);
            JsonElement parseReader = parseReader(jsonReader);
            if (!parseReader.isJsonNull() && jsonReader.peek() != JsonToken.END_DOCUMENT) {
                throw new JsonSyntaxException("Did not consume the entire document.");
            }
            return parseReader;
        } catch (MalformedJsonException e7) {
            throw new JsonSyntaxException(e7);
        } catch (IOException e8) {
            throw new JsonIOException(e8);
        } catch (NumberFormatException e9) {
            throw new JsonSyntaxException(e9);
        }
    }

    public static JsonElement parseString(String str) {
        return parseReader(new StringReader(str));
    }

    @Deprecated
    public JsonElement parse(JsonReader jsonReader) {
        return parseReader(jsonReader);
    }

    @Deprecated
    public JsonElement parse(Reader reader) {
        return parseReader(reader);
    }

    @Deprecated
    public JsonElement parse(String str) {
        return parseString(str);
    }
}
