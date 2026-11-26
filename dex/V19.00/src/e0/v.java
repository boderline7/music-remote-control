package e0;

import android.app.Notification;
import android.app.PendingIntent;
import android.content.Context;
import android.graphics.Bitmap;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import androidx.core.graphics.drawable.IconCompat;
import java.util.ArrayList;
import java.util.Iterator;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final class v {

    /* renamed from: a, reason: collision with root package name */
    public final Context f6323a;

    /* renamed from: e, reason: collision with root package name */
    public CharSequence f6327e;

    /* renamed from: f, reason: collision with root package name */
    public CharSequence f6328f;

    /* renamed from: g, reason: collision with root package name */
    public PendingIntent f6329g;

    /* renamed from: h, reason: collision with root package name */
    public Bitmap f6330h;

    /* renamed from: i, reason: collision with root package name */
    public int f6331i;

    /* renamed from: k, reason: collision with root package name */
    public u f6333k;

    /* renamed from: l, reason: collision with root package name */
    public int f6334l;

    /* renamed from: m, reason: collision with root package name */
    public int f6335m;

    /* renamed from: n, reason: collision with root package name */
    public boolean f6336n;

    /* renamed from: o, reason: collision with root package name */
    public String f6337o;

    /* renamed from: p, reason: collision with root package name */
    public Bundle f6338p;

    /* renamed from: q, reason: collision with root package name */
    public final String f6339q;

    /* renamed from: r, reason: collision with root package name */
    public final boolean f6340r;

    /* renamed from: s, reason: collision with root package name */
    public final Notification f6341s;

    /* renamed from: t, reason: collision with root package name */
    public final ArrayList f6342t;

    /* renamed from: b, reason: collision with root package name */
    public final ArrayList f6324b = new ArrayList();

    /* renamed from: c, reason: collision with root package name */
    public final ArrayList f6325c = new ArrayList();

    /* renamed from: d, reason: collision with root package name */
    public final ArrayList f6326d = new ArrayList();

    /* renamed from: j, reason: collision with root package name */
    public final boolean f6332j = true;

    public v(Context context, String str) {
        Notification notification = new Notification();
        this.f6341s = notification;
        this.f6323a = context;
        this.f6339q = str;
        notification.when = System.currentTimeMillis();
        notification.audioStreamType = -1;
        this.f6331i = 0;
        this.f6342t = new ArrayList();
        this.f6340r = true;
    }

    public static CharSequence c(CharSequence charSequence) {
        return (charSequence != null && charSequence.length() > 5120) ? charSequence.subSequence(0, 5120) : charSequence;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r6v48, types: [java.util.List] */
    public final Notification a() {
        Bundle bundle;
        ArrayList arrayList;
        int i7;
        ArrayList arrayList2;
        new ArrayList();
        Bundle bundle2 = new Bundle();
        Context context = this.f6323a;
        String str = this.f6339q;
        Notification.Builder builder = new Notification.Builder(context, str);
        Notification notification = this.f6341s;
        builder.setWhen(notification.when).setSmallIcon(notification.icon, notification.iconLevel).setContent(notification.contentView).setTicker(notification.tickerText, null).setVibrate(notification.vibrate).setLights(notification.ledARGB, notification.ledOnMS, notification.ledOffMS).setOngoing((notification.flags & 2) != 0).setOnlyAlertOnce((notification.flags & 8) != 0).setAutoCancel((notification.flags & 16) != 0).setDefaults(notification.defaults).setContentTitle(this.f6327e).setContentText(this.f6328f).setContentInfo(null).setContentIntent(this.f6329g).setDeleteIntent(notification.deleteIntent).setFullScreenIntent(null, (notification.flags & 128) != 0).setLargeIcon(this.f6330h).setNumber(0).setProgress(this.f6334l, this.f6335m, this.f6336n);
        builder.setSubText(null).setUsesChronometer(false).setPriority(this.f6331i);
        Iterator it = this.f6324b.iterator();
        while (it.hasNext()) {
            t tVar = (t) it.next();
            IconCompat a7 = tVar.a();
            Notification.Action.Builder builder2 = new Notification.Action.Builder(a7 != null ? j0.d.c(a7, null) : null, tVar.f6319e, tVar.f6320f);
            Bundle bundle3 = tVar.f6315a;
            Bundle bundle4 = bundle3 != null ? new Bundle(bundle3) : new Bundle();
            boolean z6 = tVar.f6317c;
            bundle4.putBoolean("android.support.allowGeneratedReplies", z6);
            int i8 = Build.VERSION.SDK_INT;
            builder2.setAllowGeneratedReplies(z6);
            bundle4.putInt("android.support.action.semanticAction", 0);
            if (i8 >= 28) {
                builder2.setSemanticAction(0);
            }
            if (i8 >= 29) {
                builder2.setContextual(false);
            }
            if (i8 >= 31) {
                builder2.setAuthenticationRequired(false);
            }
            bundle4.putBoolean("android.support.action.showsUserInterface", tVar.f6318d);
            builder2.addExtras(bundle4);
            builder.addAction(builder2.build());
        }
        Bundle bundle5 = this.f6338p;
        if (bundle5 != null) {
            bundle2.putAll(bundle5);
        }
        int i9 = Build.VERSION.SDK_INT;
        builder.setShowWhen(this.f6332j);
        builder.setLocalOnly(false).setGroup(null).setGroupSummary(false).setSortKey(null);
        builder.setCategory(this.f6337o).setColor(0).setVisibility(0).setPublicVersion(null).setSound(notification.sound, notification.audioAttributes);
        ArrayList arrayList3 = this.f6325c;
        ArrayList arrayList4 = this.f6342t;
        ArrayList arrayList5 = arrayList4;
        if (i9 < 28) {
            if (arrayList3 == null) {
                arrayList2 = null;
            } else {
                arrayList2 = new ArrayList(arrayList3.size());
                Iterator it2 = arrayList3.iterator();
                if (it2.hasNext()) {
                    a.a.u(it2.next());
                    throw null;
                }
            }
            arrayList5 = l6.f.p(arrayList2, arrayList4);
        }
        if (arrayList5 != null && !arrayList5.isEmpty()) {
            Iterator it3 = arrayList5.iterator();
            while (it3.hasNext()) {
                builder.addPerson((String) it3.next());
            }
        }
        ArrayList arrayList6 = this.f6326d;
        if (arrayList6.size() > 0) {
            Bundle bundle6 = b().getBundle("android.car.EXTENSIONS");
            if (bundle6 == null) {
                bundle6 = new Bundle();
            }
            Bundle bundle7 = new Bundle(bundle6);
            Bundle bundle8 = new Bundle();
            int i10 = 0;
            while (i10 < arrayList6.size()) {
                String num = Integer.toString(i10);
                t tVar2 = (t) arrayList6.get(i10);
                String str2 = x.TAG;
                Bundle bundle9 = new Bundle();
                IconCompat a8 = tVar2.a();
                if (a8 != null) {
                    arrayList = arrayList6;
                    i7 = a8.b();
                } else {
                    arrayList = arrayList6;
                    i7 = 0;
                }
                bundle9.putInt("icon", i7);
                bundle9.putCharSequence("title", tVar2.f6319e);
                bundle9.putParcelable("actionIntent", tVar2.f6320f);
                Bundle bundle10 = tVar2.f6315a;
                Bundle bundle11 = bundle10 != null ? new Bundle(bundle10) : new Bundle();
                bundle11.putBoolean("android.support.allowGeneratedReplies", tVar2.f6317c);
                bundle9.putBundle("extras", bundle11);
                bundle9.putParcelableArray("remoteInputs", null);
                bundle9.putBoolean("showsUserInterface", tVar2.f6318d);
                bundle9.putInt("semanticAction", 0);
                bundle8.putBundle(num, bundle9);
                i10++;
                arrayList6 = arrayList;
            }
            bundle6.putBundle("invisible_actions", bundle8);
            bundle7.putBundle("invisible_actions", bundle8);
            b().putBundle("android.car.EXTENSIONS", bundle6);
            bundle2.putBundle("android.car.EXTENSIONS", bundle7);
        }
        int i11 = Build.VERSION.SDK_INT;
        builder.setExtras(this.f6338p).setRemoteInputHistory(null);
        builder.setBadgeIconType(0).setSettingsText(null).setShortcutId(null).setTimeoutAfter(0L).setGroupAlertBehavior(0);
        if (!TextUtils.isEmpty(str)) {
            builder.setSound(null).setDefaults(0).setLights(0, 0, 0).setVibrate(null);
        }
        if (i11 >= 28) {
            Iterator it4 = arrayList3.iterator();
            if (it4.hasNext()) {
                a.a.u(it4.next());
                throw null;
            }
        }
        if (i11 >= 29) {
            builder.setAllowSystemGeneratedContextualActions(this.f6340r);
            builder.setBubbleMetadata(null);
        }
        u uVar = this.f6333k;
        if (uVar != null) {
            new Notification.BigTextStyle(builder).setBigContentTitle(null).bigText(uVar.f6322b);
        }
        Notification build = builder.build();
        if (uVar != null) {
            this.f6333k.getClass();
        }
        if (uVar != null && (bundle = build.extras) != null) {
            bundle.putString("androidx.core.app.extra.COMPAT_TEMPLATE", "androidx.core.app.NotificationCompat$BigTextStyle");
        }
        return build;
    }

    public final Bundle b() {
        if (this.f6338p == null) {
            this.f6338p = new Bundle();
        }
        return this.f6338p;
    }

    public final void d(int i7, boolean z6) {
        Notification notification = this.f6341s;
        if (z6) {
            notification.flags = i7 | notification.flags;
        } else {
            notification.flags = (~i7) & notification.flags;
        }
    }

    public final void e(u uVar) {
        if (this.f6333k != uVar) {
            this.f6333k = uVar;
            if (uVar.f6321a != this) {
                uVar.f6321a = this;
                e(uVar);
            }
        }
    }
}
