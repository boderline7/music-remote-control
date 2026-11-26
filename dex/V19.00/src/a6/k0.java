package a6;

import android.animation.ValueAnimator;
import android.graphics.drawable.Drawable;
import androidx.cardview.widget.CardView;
import java.io.PrintStream;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import org.slf4j.ILoggerFactory;
import s5.b1;
import s5.x0;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final class k0 implements m0, e1.b, i6.h, ILoggerFactory, s5.c, s5.t, s5.v {
    public k0(int[] iArr, ValueAnimator valueAnimator) {
    }

    @Override // s5.c
    public androidx.appcompat.widget.y a(b1 b1Var, x0 x0Var) {
        i5.a.g("response", x0Var);
        return null;
    }

    @Override // i6.h
    public void b(Level level, String str) {
        System.out.println("[" + level + "] " + str);
    }

    @Override // org.slf4j.ILoggerFactory
    public j6.a c(String str) {
        return l6.b.NOP_LOGGER;
    }

    public List d(String str) {
        i5.a.g("hostname", str);
        try {
            InetAddress[] allByName = InetAddress.getAllByName(str);
            i5.a.f("getAllByName(hostname)", allByName);
            int length = allByName.length;
            return length != 0 ? length != 1 ? new ArrayList(new c5.i(allByName, false)) : n1.z.G0(allByName[0]) : c5.r.INSTANCE;
        } catch (NullPointerException e7) {
            UnknownHostException unknownHostException = new UnknownHostException("Broken system behaviour for dns lookup of ".concat(str));
            unknownHostException.initCause(e7);
            throw unknownHostException;
        }
    }

    public void e(float f7, i6.i iVar) {
        p.a aVar = (p.a) ((Drawable) iVar.f6848b);
        boolean useCompatPadding = ((CardView) iVar.f6849c).getUseCompatPadding();
        boolean preventCornerOverlap = ((CardView) iVar.f6849c).getPreventCornerOverlap();
        if (f7 != aVar.f8083e || aVar.f8084f != useCompatPadding || aVar.f8085g != preventCornerOverlap) {
            aVar.f8083e = f7;
            aVar.f8084f = useCompatPadding;
            aVar.f8085g = preventCornerOverlap;
            aVar.b(null);
            aVar.invalidateSelf();
        }
        g(iVar);
    }

    @Override // i6.h
    public void f(Level level, String str, Throwable th) {
        PrintStream printStream = System.out;
        printStream.println("[" + level + "] " + str);
        th.printStackTrace(printStream);
    }

    public void g(i6.i iVar) {
        if (!((CardView) iVar.f6849c).getUseCompatPadding()) {
            iVar.v(0, 0, 0, 0);
            return;
        }
        p.a aVar = (p.a) ((Drawable) iVar.f6848b);
        float f7 = aVar.f8083e;
        float f8 = aVar.f8079a;
        int ceil = (int) Math.ceil(p.b.a(f7, f8, ((CardView) iVar.f6849c).getPreventCornerOverlap()));
        int ceil2 = (int) Math.ceil(p.b.b(f7, f8, ((CardView) iVar.f6849c).getPreventCornerOverlap()));
        iVar.v(ceil, ceil2, ceil, ceil2);
    }
}
