package a;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Paint;
import android.graphics.Path;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import androidx.fragment.app.z;
import androidx.lifecycle.g0;
import com.google.android.material.switchmaterial.SwitchMaterial;
import com.navi.tool.amapflying.util.FloatingWindowService;
import com.navi.tool.model.AppInfo;
import com.navi.tool.ui.extra.CarLinkFragment;
import com.navi.tool.ui.extra.DesktopAssistantActivity;
import com.navi.tool.ui.extra.DesktopAssistantCarControlHelper;
import com.navi.tool.ui.extra.DesktopAssistantFragment;
import com.navi.tool.ui.extra.h1;
import com.navi.tool.ui.extra.l0;
import com.navi.tool.ui.extra.n;
import com.navi.tool.ui.extra.q2;
import com.navi.tool.ui.extra.s2;
import java.util.ArrayList;
import java.util.Random;
import w1.j;
import w1.k;
import z1.b;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public abstract /* synthetic */ class a {
    public static float A(float f7, float f8, float f9, float f10) {
        return (f7 * f8 * f9) + f10;
    }

    public static String B(String str, String str2) {
        return str + str2;
    }

    public static /* synthetic */ void C(Object obj) {
        throw new ClassCastException();
    }

    public static float a(float f7, float f8, float f9, float f10) {
        return ((f7 - f8) * f9) + f10;
    }

    public static float b(Random random, float f7, float f8, float f9) {
        return ((random.nextFloat() * f7) + f8) * f9;
    }

    public static String c(EditText editText) {
        return editText.getText().toString().trim();
    }

    public static String d(String str, int i7) {
        return str + i7;
    }

    public static String e(String str, int i7, String str2) {
        return str + i7 + str2;
    }

    public static String f(String str, z zVar, String str2) {
        return str + zVar + str2;
    }

    public static String g(String str, String str2) {
        return str + str2;
    }

    public static String h(String str, String str2, String str3) {
        return str + str2 + str3;
    }

    public static String i(StringBuilder sb, String str, String str2) {
        sb.append(str);
        sb.append(str2);
        return sb.toString();
    }

    public static StringBuilder j(String str) {
        StringBuilder sb = new StringBuilder();
        sb.append(str);
        return sb;
    }

    public static void k(Context context, Class cls, String str, Context context2) {
        Intent intent = new Intent(context, (Class<?>) cls);
        intent.setAction(str);
        FloatingWindowService.n(context2, intent);
    }

    public static void l(SharedPreferences sharedPreferences, String str, int i7) {
        sharedPreferences.edit().putInt(str, i7).apply();
    }

    public static void m(SharedPreferences sharedPreferences, String str, boolean z6) {
        sharedPreferences.edit().putBoolean(str, z6).apply();
    }

    public static void n(Paint paint, int i7, Path path, float f7, float f8) {
        paint.setColor(i7);
        path.reset();
        path.moveTo(f7, f8);
    }

    public static void o(Path path, float f7, float f8, float f9, float f10) {
        path.lineTo(f7, f8);
        path.lineTo(f9, f10);
        path.close();
    }

    public static void p(CarLinkFragment carLinkFragment, int i7, SwitchMaterial switchMaterial) {
        switchMaterial.setOnCheckedChangeListener(new n(carLinkFragment, i7));
    }

    public static void q(com.navi.tool.ui.extra.z zVar, int i7, g0 g0Var, DesktopAssistantActivity desktopAssistantActivity) {
        g0Var.e(desktopAssistantActivity, new l0(zVar, i7));
    }

    public static void r(DesktopAssistantCarControlHelper desktopAssistantCarControlHelper, int i7, View view) {
        view.setOnClickListener(new h1(desktopAssistantCarControlHelper, i7));
    }

    public static void s(DesktopAssistantFragment desktopAssistantFragment, int i7, Button button) {
        button.setOnClickListener(new s2(desktopAssistantFragment, i7));
    }

    public static void t(DesktopAssistantFragment desktopAssistantFragment, int i7, SwitchMaterial switchMaterial) {
        switchMaterial.setOnCheckedChangeListener(new q2(desktopAssistantFragment, i7));
    }

    public static /* synthetic */ void u(Object obj) {
        if (obj != null) {
            throw new ClassCastException();
        }
    }

    public static void v(String str, int i7, String str2, TextView textView) {
        textView.setText(str + i7 + str2);
    }

    public static void w(String str, Context context) {
        context.sendBroadcast(new Intent(str));
    }

    public static void x(String str, String str2, boolean z6, ArrayList arrayList) {
        arrayList.add(new AppInfo(str, str2, z6));
    }

    public static void y(j jVar, j jVar2, j jVar3, j jVar4, j jVar5) {
        k.a(jVar);
        k.a(jVar2);
        k.a(jVar3);
        k.a(jVar4);
        k.a(jVar5);
    }

    public static void z(z1.a aVar, z1.a aVar2, z1.a aVar3, z1.a aVar4, z1.a aVar5) {
        b.a(aVar);
        b.a(aVar2);
        b.a(aVar3);
        b.a(aVar4);
        b.a(aVar5);
    }
}
