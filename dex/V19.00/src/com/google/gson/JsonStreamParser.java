package com.google.gson;

import com.google.gson.internal.Streams;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.MalformedJsonException;
import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import java.util.Iterator;
import java.util.NoSuchElementException;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_2_1633180.dex */
public final class JsonStreamParser implements Iterator<JsonElement> {
    private final Object lock;
    private final JsonReader parser;

    public JsonStreamParser(Reader reader) {
        JsonReader jsonReader = new JsonReader(reader);
        this.parser = jsonReader;
        jsonReader.setLenient(true);
        this.lock = new Object();
    }

    public JsonStreamParser(String str) {
        this(new StringReader(str));
    }

    @Override // java.util.Iterator
    public boolean hasNext() {
        boolean z6;
        synchronized (this.lock) {
            try {
                try {
                    try {
                        z6 = this.parser.peek() != JsonToken.END_DOCUMENT;
                    } catch (IOException e7) {
                        throw new JsonIOException(e7);
                    }
                } catch (MalformedJsonException e8) {
                    throw new JsonSyntaxException(e8);
                }
            } catch (Throwable th) {
                throw th;
            }
        }
        return z6;
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // java.util.Iterator
    public JsonElement next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        try {
            return Streams.parse(this.parser);
        } catch (OutOfMemoryError e7) {
            throw new JsonParseException("Failed parsing JSON source to Json", e7);
        } catch (StackOverflowError e8) {
            throw new JsonParseException("Failed parsing JSON source to Json", e8);
        }
    }

    @Override // java.util.Iterator
    public void remove() {
        throw new UnsupportedOperationException();
    }
}
