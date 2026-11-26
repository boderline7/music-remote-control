package com.navi.tool.ui.extra;

import android.content.Context;
import android.content.Intent;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout;
import com.navi.tool.utils.ToastHelper;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public class AppDisplayView extends FrameLayout {
    private float downX;
    private float downY;
    private boolean isLongPressTriggered;
    private Runnable longPressRunnable;
    private View.OnLongClickListener onLongClickListener;

    public AppDisplayView(Context context) {
        this(context, null);
    }

    public AppDisplayView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.longPressRunnable = new Runnable() { // from class: com.navi.tool.ui.extra.AppDisplayView.1
            @Override // java.lang.Runnable
            public void run() {
                if (AppDisplayView.this.onLongClickListener != null) {
                    AppDisplayView.this.onLongClickListener.onLongClick(AppDisplayView.this);
                    AppDisplayView.this.isLongPressTriggered = true;
                }
            }
        };
        this.isLongPressTriggered = false;
    }

    public void launchApp(String str) {
        try {
            Intent launchIntentForPackage = getContext().getPackageManager().getLaunchIntentForPackage(str);
            if (launchIntentForPackage != null) {
                launchIntentForPackage.addFlags(268435456);
                getContext().startActivity(launchIntentForPackage);
            } else {
                ToastHelper.show(getContext(), "无法启动应用: " + str);
            }
        } catch (Exception e7) {
            e7.printStackTrace();
            ToastHelper.show(getContext(), "启动应用失败: " + e7.getMessage());
        }
    }

    @Override // android.view.ViewGroup
    public boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        return true;
    }

    /* JADX WARN: Code restructure failed: missing block: B:7:0x000d, code lost:
    
        if (r0 != 3) goto L21;
     */
    @Override // android.view.View
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public boolean onTouchEvent(android.view.MotionEvent r5) {
        /*
            r4 = this;
            int r0 = r5.getAction()
            r1 = 1
            if (r0 == 0) goto L49
            if (r0 == r1) goto L36
            r2 = 2
            if (r0 == r2) goto L10
            r2 = 3
            if (r0 == r2) goto L36
            goto L5f
        L10:
            float r0 = r5.getX()
            float r2 = r4.downX
            float r0 = r0 - r2
            float r0 = java.lang.Math.abs(r0)
            r2 = 1101004800(0x41a00000, float:20.0)
            int r0 = (r0 > r2 ? 1 : (r0 == r2 ? 0 : -1))
            if (r0 > 0) goto L30
            float r5 = r5.getY()
            float r0 = r4.downY
            float r5 = r5 - r0
            float r5 = java.lang.Math.abs(r5)
            int r5 = (r5 > r2 ? 1 : (r5 == r2 ? 0 : -1))
            if (r5 <= 0) goto L5f
        L30:
            java.lang.Runnable r5 = r4.longPressRunnable
            r4.removeCallbacks(r5)
            goto L5f
        L36:
            java.lang.Runnable r0 = r4.longPressRunnable
            r4.removeCallbacks(r0)
            boolean r0 = r4.isLongPressTriggered
            if (r0 != 0) goto L5f
            int r5 = r5.getAction()
            if (r5 != r1) goto L5f
            r4.performClick()
            goto L5f
        L49:
            r0 = 0
            r4.isLongPressTriggered = r0
            float r0 = r5.getX()
            r4.downX = r0
            float r5 = r5.getY()
            r4.downY = r5
            java.lang.Runnable r5 = r4.longPressRunnable
            r2 = 1000(0x3e8, double:4.94E-321)
            r4.postDelayed(r5, r2)
        L5f:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.navi.tool.ui.extra.AppDisplayView.onTouchEvent(android.view.MotionEvent):boolean");
    }

    public void releaseVirtualDisplay() {
    }

    @Override // android.view.View
    public void setOnLongClickListener(View.OnLongClickListener onLongClickListener) {
        this.onLongClickListener = onLongClickListener;
    }
}
