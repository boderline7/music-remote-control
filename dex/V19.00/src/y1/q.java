package y1;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public enum q implements e2.i {
    TYPE_HEADER_ITEM("TYPE_HEADER_ITEM", "header_item"),
    TYPE_STRING_ID_ITEM("TYPE_STRING_ID_ITEM", "string_id_item"),
    TYPE_TYPE_ID_ITEM("TYPE_TYPE_ID_ITEM", "type_id_item"),
    TYPE_PROTO_ID_ITEM("TYPE_PROTO_ID_ITEM", "proto_id_item"),
    TYPE_FIELD_ID_ITEM("TYPE_FIELD_ID_ITEM", "field_id_item"),
    TYPE_METHOD_ID_ITEM("TYPE_METHOD_ID_ITEM", "method_id_item"),
    TYPE_CLASS_DEF_ITEM("TYPE_CLASS_DEF_ITEM", "class_def_item"),
    TYPE_CALL_SITE_ID_ITEM("TYPE_CALL_SITE_ID_ITEM", "call_site_id_item"),
    TYPE_METHOD_HANDLE_ITEM("TYPE_METHOD_HANDLE_ITEM", "method_handle_item"),
    TYPE_MAP_LIST("TYPE_MAP_LIST", "map_list"),
    TYPE_TYPE_LIST("TYPE_TYPE_LIST", "type_list"),
    TYPE_ANNOTATION_SET_REF_LIST("TYPE_ANNOTATION_SET_REF_LIST", "annotation_set_ref_list"),
    TYPE_ANNOTATION_SET_ITEM("TYPE_ANNOTATION_SET_ITEM", "annotation_set_item"),
    TYPE_CLASS_DATA_ITEM("TYPE_CLASS_DATA_ITEM", "class_data_item"),
    TYPE_CODE_ITEM("TYPE_CODE_ITEM", "code_item"),
    TYPE_STRING_DATA_ITEM("TYPE_STRING_DATA_ITEM", "string_data_item"),
    TYPE_DEBUG_INFO_ITEM("TYPE_DEBUG_INFO_ITEM", "debug_info_item"),
    TYPE_ANNOTATION_ITEM("TYPE_ANNOTATION_ITEM", "annotation_item"),
    TYPE_ENCODED_ARRAY_ITEM("TYPE_ENCODED_ARRAY_ITEM", "encoded_array_item"),
    TYPE_ANNOTATIONS_DIRECTORY_ITEM("TYPE_ANNOTATIONS_DIRECTORY_ITEM", "annotations_directory_item"),
    TYPE_MAP_ITEM("TYPE_MAP_ITEM", "map_item"),
    TYPE_TYPE_ITEM("TYPE_TYPE_ITEM", "type_item"),
    TYPE_EXCEPTION_HANDLER_ITEM("TYPE_EXCEPTION_HANDLER_ITEM", "exception_handler_item"),
    TYPE_ANNOTATION_SET_REF_ITEM("TYPE_ANNOTATION_SET_REF_ITEM", "annotation_set_ref_item");


    /* renamed from: a, reason: collision with root package name */
    public final int f10103a;

    /* renamed from: b, reason: collision with root package name */
    public final String f10104b;

    /* renamed from: c, reason: collision with root package name */
    public final String f10105c;

    q(String str, String str2) {
        this.f10103a = r2;
        this.f10104b = str2;
        this.f10105c = (str2.endsWith("_item") ? str2.substring(0, str2.length() - 5) : str2).replace('_', ' ');
    }

    @Override // e2.i
    public final String d() {
        return this.f10105c;
    }
}
