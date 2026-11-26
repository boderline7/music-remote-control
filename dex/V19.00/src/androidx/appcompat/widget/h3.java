package androidx.appcompat.widget;

import android.app.SearchableInfo;
import android.content.Context;
import android.content.pm.PackageManager;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.database.Cursor;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.WeakHashMap;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_2_1633180.dex */
public final class h3 extends t0.b implements View.OnClickListener {

    /* renamed from: x, reason: collision with root package name */
    public static final /* synthetic */ int f1271x = 0;

    /* renamed from: h, reason: collision with root package name */
    public final int f1272h;

    /* renamed from: i, reason: collision with root package name */
    public final int f1273i;

    /* renamed from: j, reason: collision with root package name */
    public final LayoutInflater f1274j;

    /* renamed from: k, reason: collision with root package name */
    public final SearchView f1275k;

    /* renamed from: l, reason: collision with root package name */
    public final SearchableInfo f1276l;

    /* renamed from: m, reason: collision with root package name */
    public final Context f1277m;

    /* renamed from: n, reason: collision with root package name */
    public final WeakHashMap f1278n;

    /* renamed from: o, reason: collision with root package name */
    public final int f1279o;

    /* renamed from: p, reason: collision with root package name */
    public int f1280p;

    /* renamed from: q, reason: collision with root package name */
    public ColorStateList f1281q;

    /* renamed from: r, reason: collision with root package name */
    public int f1282r;

    /* renamed from: s, reason: collision with root package name */
    public int f1283s;

    /* renamed from: t, reason: collision with root package name */
    public int f1284t;

    /* renamed from: u, reason: collision with root package name */
    public int f1285u;

    /* renamed from: v, reason: collision with root package name */
    public int f1286v;

    /* renamed from: w, reason: collision with root package name */
    public int f1287w;

    public h3(Context context, SearchView searchView, SearchableInfo searchableInfo, WeakHashMap weakHashMap) {
        int suggestionRowLayout = searchView.getSuggestionRowLayout();
        this.f9015b = true;
        this.f9016c = null;
        this.f9014a = false;
        this.f9017d = -1;
        this.f9018e = new t0.a(this);
        this.f9019f = new b2(1, this);
        this.f1273i = suggestionRowLayout;
        this.f1272h = suggestionRowLayout;
        this.f1274j = (LayoutInflater) context.getSystemService("layout_inflater");
        this.f1280p = 1;
        this.f1282r = -1;
        this.f1283s = -1;
        this.f1284t = -1;
        this.f1285u = -1;
        this.f1286v = -1;
        this.f1287w = -1;
        this.f1275k = searchView;
        this.f1276l = searchableInfo;
        this.f1279o = searchView.getSuggestionCommitIconResId();
        this.f1277m = context;
        this.f1278n = weakHashMap;
    }

    public static String h(Cursor cursor, int i7) {
        if (i7 == -1) {
            return null;
        }
        try {
            return cursor.getString(i7);
        } catch (Exception unused) {
            return null;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:55:0x0122  */
    /* JADX WARN: Removed duplicated region for block: B:57:0x0124  */
    @Override // t0.b
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void a(android.view.View r22, android.database.Cursor r23) {
        /*
            Method dump skipped, instructions count: 402
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.appcompat.widget.h3.a(android.view.View, android.database.Cursor):void");
    }

    @Override // t0.b
    public final void b(Cursor cursor) {
        try {
            super.b(cursor);
            if (cursor != null) {
                this.f1282r = cursor.getColumnIndex("suggest_text_1");
                this.f1283s = cursor.getColumnIndex("suggest_text_2");
                this.f1284t = cursor.getColumnIndex("suggest_text_2_url");
                this.f1285u = cursor.getColumnIndex("suggest_icon_1");
                this.f1286v = cursor.getColumnIndex("suggest_icon_2");
                this.f1287w = cursor.getColumnIndex("suggest_flags");
            }
        } catch (Exception unused) {
        }
    }

    @Override // t0.b
    public final String c(Cursor cursor) {
        String h7;
        String h8;
        if (cursor == null) {
            return null;
        }
        String h9 = h(cursor, cursor.getColumnIndex("suggest_intent_query"));
        if (h9 != null) {
            return h9;
        }
        SearchableInfo searchableInfo = this.f1276l;
        if (searchableInfo.shouldRewriteQueryFromData() && (h8 = h(cursor, cursor.getColumnIndex("suggest_intent_data"))) != null) {
            return h8;
        }
        if (!searchableInfo.shouldRewriteQueryFromText() || (h7 = h(cursor, cursor.getColumnIndex("suggest_text_1"))) == null) {
            return null;
        }
        return h7;
    }

    @Override // t0.b
    public final View d(ViewGroup viewGroup) {
        View inflate = this.f1274j.inflate(this.f1272h, viewGroup, false);
        inflate.setTag(new g3(inflate));
        ((ImageView) inflate.findViewById(f.f.edit_query)).setImageResource(this.f1279o);
        return inflate;
    }

    public final Drawable e(Uri uri) {
        int parseInt;
        String authority = uri.getAuthority();
        if (TextUtils.isEmpty(authority)) {
            throw new FileNotFoundException("No authority: " + uri);
        }
        try {
            Resources resourcesForApplication = this.f1277m.getPackageManager().getResourcesForApplication(authority);
            List<String> pathSegments = uri.getPathSegments();
            if (pathSegments == null) {
                throw new FileNotFoundException("No path: " + uri);
            }
            int size = pathSegments.size();
            if (size == 1) {
                try {
                    parseInt = Integer.parseInt(pathSegments.get(0));
                } catch (NumberFormatException unused) {
                    throw new FileNotFoundException("Single path segment is not a resource ID: " + uri);
                }
            } else {
                if (size != 2) {
                    throw new FileNotFoundException("More than two path segments: " + uri);
                }
                parseInt = resourcesForApplication.getIdentifier(pathSegments.get(1), pathSegments.get(0), authority);
            }
            if (parseInt != 0) {
                return resourcesForApplication.getDrawable(parseInt);
            }
            throw new FileNotFoundException("No resource found for: " + uri);
        } catch (PackageManager.NameNotFoundException unused2) {
            throw new FileNotFoundException("No package found for authority: " + uri);
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:41:0x00d7  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final android.graphics.drawable.Drawable f(java.lang.String r7) {
        /*
            r6 = this;
            java.lang.String r0 = "android.resource://"
            r1 = 0
            if (r7 == 0) goto Le0
            boolean r2 = r7.isEmpty()
            if (r2 != 0) goto Le0
            java.lang.String r2 = "0"
            boolean r2 = r2.equals(r7)
            if (r2 == 0) goto L15
            goto Le0
        L15:
            int r2 = java.lang.Integer.parseInt(r7)     // Catch: android.content.res.Resources.NotFoundException -> L5a java.lang.NumberFormatException -> L5b
            java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch: android.content.res.Resources.NotFoundException -> L5a java.lang.NumberFormatException -> L5b
            r3.<init>(r0)     // Catch: android.content.res.Resources.NotFoundException -> L5a java.lang.NumberFormatException -> L5b
            android.content.Context r0 = r6.f1277m     // Catch: android.content.res.Resources.NotFoundException -> L5a java.lang.NumberFormatException -> L5b
            java.lang.String r0 = r0.getPackageName()     // Catch: android.content.res.Resources.NotFoundException -> L5a java.lang.NumberFormatException -> L5b
            r3.append(r0)     // Catch: android.content.res.Resources.NotFoundException -> L5a java.lang.NumberFormatException -> L5b
            java.lang.String r0 = "/"
            r3.append(r0)     // Catch: android.content.res.Resources.NotFoundException -> L5a java.lang.NumberFormatException -> L5b
            r3.append(r2)     // Catch: android.content.res.Resources.NotFoundException -> L5a java.lang.NumberFormatException -> L5b
            java.lang.String r0 = r3.toString()     // Catch: android.content.res.Resources.NotFoundException -> L5a java.lang.NumberFormatException -> L5b
            java.util.WeakHashMap r3 = r6.f1278n     // Catch: android.content.res.Resources.NotFoundException -> L5a java.lang.NumberFormatException -> L5b
            java.lang.Object r3 = r3.get(r0)     // Catch: android.content.res.Resources.NotFoundException -> L5a java.lang.NumberFormatException -> L5b
            android.graphics.drawable.Drawable$ConstantState r3 = (android.graphics.drawable.Drawable.ConstantState) r3     // Catch: android.content.res.Resources.NotFoundException -> L5a java.lang.NumberFormatException -> L5b
            if (r3 != 0) goto L3f
            r3 = r1
            goto L43
        L3f:
            android.graphics.drawable.Drawable r3 = r3.newDrawable()     // Catch: android.content.res.Resources.NotFoundException -> L5a java.lang.NumberFormatException -> L5b
        L43:
            if (r3 == 0) goto L46
            return r3
        L46:
            android.content.Context r3 = r6.f1277m     // Catch: android.content.res.Resources.NotFoundException -> L5a java.lang.NumberFormatException -> L5b
            java.lang.Object r4 = g0.g.f6423a     // Catch: android.content.res.Resources.NotFoundException -> L5a java.lang.NumberFormatException -> L5b
            android.graphics.drawable.Drawable r2 = g0.c.b(r3, r2)     // Catch: android.content.res.Resources.NotFoundException -> L5a java.lang.NumberFormatException -> L5b
            if (r2 == 0) goto L59
            java.util.WeakHashMap r3 = r6.f1278n     // Catch: android.content.res.Resources.NotFoundException -> L5a java.lang.NumberFormatException -> L5b
            android.graphics.drawable.Drawable$ConstantState r4 = r2.getConstantState()     // Catch: android.content.res.Resources.NotFoundException -> L5a java.lang.NumberFormatException -> L5b
            r3.put(r0, r4)     // Catch: android.content.res.Resources.NotFoundException -> L5a java.lang.NumberFormatException -> L5b
        L59:
            return r2
        L5a:
            return r1
        L5b:
            java.util.WeakHashMap r0 = r6.f1278n
            java.lang.Object r0 = r0.get(r7)
            android.graphics.drawable.Drawable$ConstantState r0 = (android.graphics.drawable.Drawable.ConstantState) r0
            if (r0 != 0) goto L67
            r0 = r1
            goto L6b
        L67:
            android.graphics.drawable.Drawable r0 = r0.newDrawable()
        L6b:
            if (r0 == 0) goto L6e
            return r0
        L6e:
            android.net.Uri r0 = android.net.Uri.parse(r7)
            java.lang.String r2 = "Failed to open "
            java.lang.String r3 = "Resource does not exist: "
            java.lang.String r4 = r0.getScheme()     // Catch: java.io.FileNotFoundException -> L87
            java.lang.String r5 = "android.resource"
            boolean r4 = r5.equals(r4)     // Catch: java.io.FileNotFoundException -> L87
            if (r4 == 0) goto L9b
            android.graphics.drawable.Drawable r1 = r6.e(r0)     // Catch: java.io.FileNotFoundException -> L87 android.content.res.Resources.NotFoundException -> L89
            goto Ld5
        L87:
            r2 = move-exception
            goto Lcf
        L89:
            java.io.FileNotFoundException r2 = new java.io.FileNotFoundException     // Catch: java.io.FileNotFoundException -> L87
            java.lang.StringBuilder r4 = new java.lang.StringBuilder     // Catch: java.io.FileNotFoundException -> L87
            r4.<init>(r3)     // Catch: java.io.FileNotFoundException -> L87
            r4.append(r0)     // Catch: java.io.FileNotFoundException -> L87
            java.lang.String r3 = r4.toString()     // Catch: java.io.FileNotFoundException -> L87
            r2.<init>(r3)     // Catch: java.io.FileNotFoundException -> L87
            throw r2     // Catch: java.io.FileNotFoundException -> L87
        L9b:
            android.content.Context r3 = r6.f1277m     // Catch: java.io.FileNotFoundException -> L87
            android.content.ContentResolver r3 = r3.getContentResolver()     // Catch: java.io.FileNotFoundException -> L87
            java.io.InputStream r3 = r3.openInputStream(r0)     // Catch: java.io.FileNotFoundException -> L87
            if (r3 == 0) goto Lbd
            android.graphics.drawable.Drawable r2 = android.graphics.drawable.Drawable.createFromStream(r3, r1)     // Catch: java.lang.Throwable -> Lb4
            r3.close()     // Catch: java.io.IOException -> Laf
            goto Lb2
        Laf:
            r0.toString()     // Catch: java.io.FileNotFoundException -> L87
        Lb2:
            r1 = r2
            goto Ld5
        Lb4:
            r2 = move-exception
            r3.close()     // Catch: java.io.IOException -> Lb9
            goto Lbc
        Lb9:
            r0.toString()     // Catch: java.io.FileNotFoundException -> L87
        Lbc:
            throw r2     // Catch: java.io.FileNotFoundException -> L87
        Lbd:
            java.io.FileNotFoundException r3 = new java.io.FileNotFoundException     // Catch: java.io.FileNotFoundException -> L87
            java.lang.StringBuilder r4 = new java.lang.StringBuilder     // Catch: java.io.FileNotFoundException -> L87
            r4.<init>(r2)     // Catch: java.io.FileNotFoundException -> L87
            r4.append(r0)     // Catch: java.io.FileNotFoundException -> L87
            java.lang.String r2 = r4.toString()     // Catch: java.io.FileNotFoundException -> L87
            r3.<init>(r2)     // Catch: java.io.FileNotFoundException -> L87
            throw r3     // Catch: java.io.FileNotFoundException -> L87
        Lcf:
            java.util.Objects.toString(r0)
            r2.getMessage()
        Ld5:
            if (r1 == 0) goto Le0
            java.util.WeakHashMap r0 = r6.f1278n
            android.graphics.drawable.Drawable$ConstantState r2 = r1.getConstantState()
            r0.put(r7, r2)
        Le0:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.appcompat.widget.h3.f(java.lang.String):android.graphics.drawable.Drawable");
    }

    public final Cursor g(SearchableInfo searchableInfo, String str) {
        String suggestAuthority;
        String[] strArr = null;
        if (searchableInfo == null || (suggestAuthority = searchableInfo.getSuggestAuthority()) == null) {
            return null;
        }
        Uri.Builder fragment = new Uri.Builder().scheme("content").authority(suggestAuthority).query(s5.h0.FRAGMENT_ENCODE_SET).fragment(s5.h0.FRAGMENT_ENCODE_SET);
        String suggestPath = searchableInfo.getSuggestPath();
        if (suggestPath != null) {
            fragment.appendEncodedPath(suggestPath);
        }
        fragment.appendPath("search_suggest_query");
        String suggestSelection = searchableInfo.getSuggestSelection();
        if (suggestSelection != null) {
            strArr = new String[]{str};
        } else {
            fragment.appendPath(str);
        }
        String[] strArr2 = strArr;
        fragment.appendQueryParameter("limit", String.valueOf(50));
        return this.f1277m.getContentResolver().query(fragment.build(), null, suggestSelection, strArr2, null);
    }

    @Override // t0.b, android.widget.BaseAdapter, android.widget.SpinnerAdapter
    public final View getDropDownView(int i7, View view, ViewGroup viewGroup) {
        try {
            return super.getDropDownView(i7, view, viewGroup);
        } catch (RuntimeException e7) {
            View inflate = this.f1274j.inflate(this.f1273i, viewGroup, false);
            if (inflate != null) {
                ((g3) inflate.getTag()).f1264a.setText(e7.toString());
            }
            return inflate;
        }
    }

    @Override // t0.b, android.widget.Adapter
    public final View getView(int i7, View view, ViewGroup viewGroup) {
        try {
            return super.getView(i7, view, viewGroup);
        } catch (RuntimeException e7) {
            View d7 = d(viewGroup);
            ((g3) d7.getTag()).f1264a.setText(e7.toString());
            return d7;
        }
    }

    @Override // android.widget.BaseAdapter, android.widget.Adapter
    public final boolean hasStableIds() {
        return false;
    }

    @Override // android.widget.BaseAdapter
    public final void notifyDataSetChanged() {
        super.notifyDataSetChanged();
        Cursor cursor = this.f9016c;
        Bundle extras = cursor != null ? cursor.getExtras() : null;
        if (extras != null) {
            extras.getBoolean("in_progress");
        }
    }

    @Override // android.widget.BaseAdapter
    public final void notifyDataSetInvalidated() {
        super.notifyDataSetInvalidated();
        Cursor cursor = this.f9016c;
        Bundle extras = cursor != null ? cursor.getExtras() : null;
        if (extras != null) {
            extras.getBoolean("in_progress");
        }
    }

    @Override // android.view.View.OnClickListener
    public final void onClick(View view) {
        Object tag = view.getTag();
        if (tag instanceof CharSequence) {
            this.f1275k.r((CharSequence) tag);
        }
    }
}
