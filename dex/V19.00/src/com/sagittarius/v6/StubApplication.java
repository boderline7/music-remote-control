package com.sagittarius.v6;

import android.app.Application;
import android.content.Context;
import android.os.Build;
import android.os.Debug;
import android.os.Process;
import com.sagittarius.v6.a.a;
import com.sagittarius.v6.b.f;
import com.sagittarius.v6.b.g;
import com.sagittarius.v6.b.r;
import com.sagittarius.v6.b.t;
import java.util.ArrayList;
import java.util.Iterator;
import q0.i;
import s5.h0;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_2_1633180.dex */
public class StubApplication extends Application {
    private static final String[] lllIIl = null;
    public static Context mContext;
    public static int mInstallProviderCounter;
    public static Application mRealApplication;
    public static boolean mRealApplicationPatched;
    public static boolean skipLoad;
    private ArrayList mActivityCallbacks = null;

    static {
        lllIIl();
        mContext = null;
        mRealApplication = null;
        mRealApplicationPatched = false;
        mInstallProviderCounter = 0;
        skipLoad = false;
        f.a().init();
    }

    private static String IllIIl(String str, String str2) {
        byte[] a7 = a.a(str);
        StringBuilder sb = new StringBuilder();
        char[] charArray = str2.toCharArray();
        int i7 = 0;
        for (char c7 : a7) {
            sb.append((char) (c7 ^ charArray[i7 % charArray.length]));
            i7++;
        }
        return sb.toString();
    }

    private static void lllIIl() {
        String[] strArr = new String[4];
        lllIIl = strArr;
        strArr[0] = IllIIl("ADosbQ8MNygvB000MTMLByZvKhEMOSA3Bwc=", "cUACb");
        lllIIl[1] = IllIIl("EQ0UAh8ZB14RAABNMRMEGRUZBAkkCwIVERQ=", "pcppp");
        lllIIl[2] = IllIIl("ACIJLTkFICorNx8lHjwq", "iLzYX");
        lllIIl[3] = IllIIl(h0.FRAGMENT_ENCODE_SET, "jdjZq");
    }

    @Override // android.content.ContextWrapper
    protected void attachBaseContext(Context context) {
        if ((AppInfo.FLAGS & i.ACTION_DISMISS) != 0 && t.a().contains(lllIIl[0])) {
            skipLoad = true;
        }
        if (skipLoad) {
            super.attachBaseContext(context);
        } else {
            t.a(this, context);
            mContext = context;
            AppInfo.PKGNAME = context.getPackageName();
            AppInfo.APKPATH = context.getApplicationInfo().sourceDir;
            AppInfo.DATAPATH = t.a(context.getApplicationInfo().dataDir);
            if (Debug.isDebuggerConnected()) {
                Process.killProcess(Process.myPid());
            } else {
                if (Build.VERSION.SDK_INT > 27) {
                    r.a();
                }
                t.b();
                A.n1(context, AppInfo.PKGNAME, AppInfo.APKPATH, AppInfo.DATAPATH, Build.VERSION.SDK_INT);
            }
            if (AppInfo.APPNAME != null && AppInfo.APPNAME.length() > 0) {
                mRealApplication = g.a(AppInfo.APPNAME);
            }
            super.attachBaseContext(context);
            Application application = mRealApplication;
            if (application != null) {
                g.a(context, application);
                ArrayList arrayList = this.mActivityCallbacks;
                if (arrayList != null && !arrayList.isEmpty()) {
                    Iterator it = this.mActivityCallbacks.iterator();
                    while (it.hasNext()) {
                        mRealApplication.registerActivityLifecycleCallbacks((Application.ActivityLifecycleCallbacks) it.next());
                    }
                }
            }
            t.c();
        }
        f.a().attachBaseContext(context, this, mRealApplication);
    }

    @Override // android.content.ContextWrapper, android.content.Context
    public String getPackageName() {
        if (skipLoad) {
            return super.getPackageName();
        }
        if (mInstallProviderCounter < 2) {
            StackTraceElement[] stackTrace = new Exception().getStackTrace();
            if (stackTrace.length >= 2) {
                String className = stackTrace[1].getClassName();
                String methodName = stackTrace[1].getMethodName();
                if (className.compareTo(lllIIl[1]) == 0 && methodName.compareTo(lllIIl[2]) == 0) {
                    int i7 = mInstallProviderCounter;
                    if (i7 == 0) {
                        mInstallProviderCounter = i7 + 1;
                        return lllIIl[3];
                    }
                    if (1 == i7) {
                        mInstallProviderCounter = i7 + 1;
                        t.a((Application) this);
                    }
                }
            }
        }
        return AppInfo.PKGNAME;
    }

    @Override // android.app.Application
    public void onCreate() {
        if (skipLoad) {
            super.onCreate();
        } else {
            t.a((Application) this);
            super.onCreate();
            t.a((Context) this);
            if (mRealApplication != null) {
                t.b(this);
                mRealApplication.onCreate();
            }
            A.n2(this);
        }
        f.a().onCreate(this, mRealApplication);
    }

    @Override // android.app.Application
    public void registerActivityLifecycleCallbacks(Application.ActivityLifecycleCallbacks activityLifecycleCallbacks) {
        f.a().registerActivityLifecycleCallbacks(activityLifecycleCallbacks, this, mRealApplication);
        if (skipLoad) {
            return;
        }
        Application application = mRealApplication;
        if (application != null) {
            application.registerActivityLifecycleCallbacks(activityLifecycleCallbacks);
            return;
        }
        if (this.mActivityCallbacks == null) {
            this.mActivityCallbacks = new ArrayList();
        }
        this.mActivityCallbacks.add(activityLifecycleCallbacks);
    }
}
