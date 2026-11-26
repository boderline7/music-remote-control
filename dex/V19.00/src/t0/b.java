package t0;

import android.database.Cursor;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import androidx.appcompat.widget.b2;
import androidx.appcompat.widget.h3;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public abstract class b extends BaseAdapter implements Filterable, c {

    @Deprecated
    public static final int FLAG_AUTO_REQUERY = 1;
    public static final int FLAG_REGISTER_CONTENT_OBSERVER = 2;

    /* renamed from: a, reason: collision with root package name */
    public boolean f9014a;

    /* renamed from: b, reason: collision with root package name */
    public boolean f9015b;

    /* renamed from: c, reason: collision with root package name */
    public Cursor f9016c;

    /* renamed from: d, reason: collision with root package name */
    public int f9017d;

    /* renamed from: e, reason: collision with root package name */
    public a f9018e;

    /* renamed from: f, reason: collision with root package name */
    public b2 f9019f;

    /* renamed from: g, reason: collision with root package name */
    public d f9020g;

    public abstract void a(View view, Cursor cursor);

    public void b(Cursor cursor) {
        Cursor cursor2 = this.f9016c;
        if (cursor == cursor2) {
            cursor2 = null;
        } else {
            if (cursor2 != null) {
                a aVar = this.f9018e;
                if (aVar != null) {
                    cursor2.unregisterContentObserver(aVar);
                }
                b2 b2Var = this.f9019f;
                if (b2Var != null) {
                    cursor2.unregisterDataSetObserver(b2Var);
                }
            }
            this.f9016c = cursor;
            if (cursor != null) {
                a aVar2 = this.f9018e;
                if (aVar2 != null) {
                    cursor.registerContentObserver(aVar2);
                }
                b2 b2Var2 = this.f9019f;
                if (b2Var2 != null) {
                    cursor.registerDataSetObserver(b2Var2);
                }
                this.f9017d = cursor.getColumnIndexOrThrow("_id");
                this.f9014a = true;
                notifyDataSetChanged();
            } else {
                this.f9017d = -1;
                this.f9014a = false;
                notifyDataSetInvalidated();
            }
        }
        if (cursor2 != null) {
            cursor2.close();
        }
    }

    public abstract String c(Cursor cursor);

    public abstract View d(ViewGroup viewGroup);

    @Override // android.widget.Adapter
    public final int getCount() {
        Cursor cursor;
        if (!this.f9014a || (cursor = this.f9016c) == null) {
            return 0;
        }
        return cursor.getCount();
    }

    @Override // android.widget.BaseAdapter, android.widget.SpinnerAdapter
    public View getDropDownView(int i7, View view, ViewGroup viewGroup) {
        if (!this.f9014a) {
            return null;
        }
        this.f9016c.moveToPosition(i7);
        if (view == null) {
            h3 h3Var = (h3) this;
            view = h3Var.f1274j.inflate(h3Var.f1273i, viewGroup, false);
        }
        a(view, this.f9016c);
        return view;
    }

    @Override // android.widget.Filterable
    public final Filter getFilter() {
        if (this.f9020g == null) {
            d dVar = new d();
            dVar.f9021a = this;
            this.f9020g = dVar;
        }
        return this.f9020g;
    }

    @Override // android.widget.Adapter
    public final Object getItem(int i7) {
        Cursor cursor;
        if (!this.f9014a || (cursor = this.f9016c) == null) {
            return null;
        }
        cursor.moveToPosition(i7);
        return this.f9016c;
    }

    @Override // android.widget.Adapter
    public final long getItemId(int i7) {
        Cursor cursor;
        if (this.f9014a && (cursor = this.f9016c) != null && cursor.moveToPosition(i7)) {
            return this.f9016c.getLong(this.f9017d);
        }
        return 0L;
    }

    @Override // android.widget.Adapter
    public View getView(int i7, View view, ViewGroup viewGroup) {
        if (!this.f9014a) {
            throw new IllegalStateException("this should only be called when the cursor is valid");
        }
        if (!this.f9016c.moveToPosition(i7)) {
            throw new IllegalStateException(a.a.d("couldn't move cursor to position ", i7));
        }
        if (view == null) {
            view = d(viewGroup);
        }
        a(view, this.f9016c);
        return view;
    }
}
