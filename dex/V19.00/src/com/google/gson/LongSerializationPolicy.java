package com.google.gson;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_2_1633180.dex */
public enum LongSerializationPolicy {
    DEFAULT { // from class: com.google.gson.LongSerializationPolicy.1
        @Override // com.google.gson.LongSerializationPolicy
        public JsonElement serialize(Long l7) {
            return l7 == null ? JsonNull.INSTANCE : new JsonPrimitive(l7);
        }
    },
    STRING { // from class: com.google.gson.LongSerializationPolicy.2
        @Override // com.google.gson.LongSerializationPolicy
        public JsonElement serialize(Long l7) {
            return l7 == null ? JsonNull.INSTANCE : new JsonPrimitive(l7.toString());
        }
    };

    public abstract JsonElement serialize(Long l7);
}
