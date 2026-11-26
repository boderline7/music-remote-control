package t1;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public enum e {
    HOST_URL_SUB_LIST("nmsublist.kuwo.cn", "/mobi.s?f=kuwo&q="),
    HOST_URL_N("nmobi.kuwo.cn", "/mobi.s?f=kuwo&q="),
    HOST_URL_SEARCH("search.kuwo.cn", "/r.s?client=kt"),
    SONGLIST_TAG_URL("st.kuwo.cn/", "/mobicase/");


    /* renamed from: a, reason: collision with root package name */
    public final String f9041a;

    /* renamed from: b, reason: collision with root package name */
    public final String f9042b;

    e(String str, String str2) {
        this.f9042b = str;
        this.f9041a = str2;
    }

    public final String a() {
        return "http://" + this.f9042b + this.f9041a;
    }
}
