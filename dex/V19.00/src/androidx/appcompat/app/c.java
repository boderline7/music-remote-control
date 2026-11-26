package androidx.appcompat.app;

import android.os.Message;
import android.view.View;
import androidx.appcompat.widget.Toolbar;
import androidx.appcompat.widget.s3;
import androidx.drawerlayout.widget.DrawerLayout;
import com.google.android.material.internal.NavigationMenuItemView;
import com.google.android.material.navigation.NavigationBarItemView;
import com.google.android.material.navigation.NavigationBarMenuView;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_2_1633180.dex */
public final class c implements View.OnClickListener {

    /* renamed from: a, reason: collision with root package name */
    public final /* synthetic */ int f699a;

    /* renamed from: b, reason: collision with root package name */
    public final /* synthetic */ Object f700b;

    public /* synthetic */ c(int i7, Object obj) {
        this.f699a = i7;
        this.f700b = obj;
    }

    @Override // android.view.View.OnClickListener
    public final void onClick(View view) {
        Message message;
        Message message2;
        Message message3;
        Message message4 = null;
        message4 = null;
        int i7 = this.f699a;
        boolean z6 = true;
        Object obj = this.f700b;
        switch (i7) {
            case 0:
                h hVar = (h) obj;
                if (hVar.f722e) {
                    DrawerLayout drawerLayout = hVar.f719b;
                    int i8 = drawerLayout.i(8388611);
                    View f7 = drawerLayout.f(8388611);
                    if (f7 != null && DrawerLayout.q(f7) && i8 != 2) {
                        drawerLayout.d();
                        break;
                    } else if (i8 != 1) {
                        drawerLayout.t();
                        break;
                    }
                }
                break;
            case 1:
                m mVar = (m) obj;
                if (view == mVar.f809k && (message3 = mVar.f811m) != null) {
                    message4 = Message.obtain(message3);
                } else if (view == mVar.f813o && (message2 = mVar.f815q) != null) {
                    message4 = Message.obtain(message2);
                } else if (view == mVar.f817s && (message = mVar.f819u) != null) {
                    message4 = Message.obtain(message);
                }
                if (message4 != null) {
                    message4.sendToTarget();
                }
                mVar.K.obtainMessage(1, mVar.f800b).sendToTarget();
                break;
            case 2:
            default:
                k.q itemData = ((NavigationBarItemView) view).getItemData();
                NavigationBarMenuView navigationBarMenuView = (NavigationBarMenuView) obj;
                if (!navigationBarMenuView.E.q(itemData, navigationBarMenuView.D, 0)) {
                    itemData.setChecked(true);
                    break;
                }
                break;
            case 3:
                s3 s3Var = ((Toolbar) obj).M;
                k.q qVar = s3Var != null ? s3Var.f1412b : null;
                if (qVar != null) {
                    qVar.collapseActionView();
                    break;
                }
                break;
            case 4:
                com.google.android.material.datepicker.t tVar = (com.google.android.material.datepicker.t) obj;
                com.google.android.material.datepicker.r rVar = tVar.f3663g;
                com.google.android.material.datepicker.r rVar2 = com.google.android.material.datepicker.r.YEAR;
                if (rVar != rVar2) {
                    if (rVar == com.google.android.material.datepicker.r.DAY) {
                        tVar.f(rVar2);
                        break;
                    }
                } else {
                    tVar.f(com.google.android.material.datepicker.r.DAY);
                    break;
                }
                break;
            case 5:
                NavigationMenuItemView navigationMenuItemView = (NavigationMenuItemView) view;
                com.google.android.material.internal.v vVar = (com.google.android.material.internal.v) obj;
                com.google.android.material.internal.n nVar = vVar.f3885e;
                if (nVar != null) {
                    nVar.f3874c = true;
                }
                k.q itemData2 = navigationMenuItemView.getItemData();
                boolean q6 = vVar.f3883c.q(itemData2, vVar, 0);
                if (itemData2 != null && itemData2.isCheckable() && q6) {
                    vVar.f3885e.b(itemData2);
                } else {
                    z6 = false;
                }
                com.google.android.material.internal.n nVar2 = vVar.f3885e;
                if (nVar2 != null) {
                    nVar2.f3874c = false;
                }
                if (z6) {
                    vVar.m(false);
                    break;
                }
                break;
        }
    }
}
