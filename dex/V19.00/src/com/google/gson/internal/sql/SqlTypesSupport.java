package com.google.gson.internal.sql;

import com.google.gson.TypeAdapterFactory;
import com.google.gson.internal.bind.DefaultDateTypeAdapter;
import java.sql.Timestamp;
import java.util.Date;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_2_1633180.dex */
public final class SqlTypesSupport {
    public static final DefaultDateTypeAdapter.DateType<? extends Date> DATE_DATE_TYPE;
    public static final TypeAdapterFactory DATE_FACTORY;
    public static final boolean SUPPORTS_SQL_TYPES;
    public static final DefaultDateTypeAdapter.DateType<? extends Date> TIMESTAMP_DATE_TYPE;
    public static final TypeAdapterFactory TIMESTAMP_FACTORY;
    public static final TypeAdapterFactory TIME_FACTORY;

    static {
        boolean z6;
        try {
            Class.forName("java.sql.Date");
            z6 = true;
        } catch (ClassNotFoundException unused) {
            z6 = false;
        }
        SUPPORTS_SQL_TYPES = z6;
        if (z6) {
            DATE_DATE_TYPE = new DefaultDateTypeAdapter.DateType<java.sql.Date>(java.sql.Date.class) { // from class: com.google.gson.internal.sql.SqlTypesSupport.1
                /* JADX WARN: Can't rename method to resolve collision */
                @Override // com.google.gson.internal.bind.DefaultDateTypeAdapter.DateType
                public java.sql.Date deserialize(Date date) {
                    return new java.sql.Date(date.getTime());
                }
            };
            TIMESTAMP_DATE_TYPE = new DefaultDateTypeAdapter.DateType<Timestamp>(Timestamp.class) { // from class: com.google.gson.internal.sql.SqlTypesSupport.2
                /* JADX WARN: Can't rename method to resolve collision */
                @Override // com.google.gson.internal.bind.DefaultDateTypeAdapter.DateType
                public Timestamp deserialize(Date date) {
                    return new Timestamp(date.getTime());
                }
            };
            DATE_FACTORY = SqlDateTypeAdapter.FACTORY;
            TIME_FACTORY = SqlTimeTypeAdapter.FACTORY;
            TIMESTAMP_FACTORY = SqlTimestampTypeAdapter.FACTORY;
            return;
        }
        DATE_DATE_TYPE = null;
        TIMESTAMP_DATE_TYPE = null;
        DATE_FACTORY = null;
        TIME_FACTORY = null;
        TIMESTAMP_FACTORY = null;
    }

    private SqlTypesSupport() {
    }
}
