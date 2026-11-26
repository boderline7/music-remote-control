package f1;

import androidx.appcompat.app.g;
import androidx.lifecycle.o1;
import androidx.lifecycle.x;
import java.io.PrintWriter;
import q.l;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final class d extends a {

    /* renamed from: a, reason: collision with root package name */
    public final x f6398a;

    /* renamed from: b, reason: collision with root package name */
    public final c f6399b;

    public d(x xVar, o1 o1Var) {
        this.f6398a = xVar;
        this.f6399b = (c) new g(o1Var, c.f6396b).C(c.class);
    }

    public final void b(String str, PrintWriter printWriter) {
        l lVar = this.f6399b.f6397a;
        if (lVar.f8416c > 0) {
            printWriter.print(str);
            printWriter.println("Loaders:");
            if (lVar.f8416c <= 0) {
                return;
            }
            a.a.u(lVar.f8415b[0]);
            printWriter.print(str);
            printWriter.print("  #");
            printWriter.print(lVar.f8414a[0]);
            printWriter.print(": ");
            throw null;
        }
    }

    public final String toString() {
        int lastIndexOf;
        StringBuilder sb = new StringBuilder(128);
        sb.append("LoaderManager{");
        sb.append(Integer.toHexString(System.identityHashCode(this)));
        sb.append(" in ");
        x xVar = this.f6398a;
        if (xVar == null) {
            sb.append("null");
        } else {
            String simpleName = xVar.getClass().getSimpleName();
            if (simpleName.length() <= 0 && (lastIndexOf = (simpleName = xVar.getClass().getName()).lastIndexOf(46)) > 0) {
                simpleName = simpleName.substring(lastIndexOf + 1);
            }
            sb.append(simpleName);
            sb.append('{');
            sb.append(Integer.toHexString(System.identityHashCode(xVar)));
        }
        sb.append("}}");
        return sb.toString();
    }
}
