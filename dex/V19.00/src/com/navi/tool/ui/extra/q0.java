package com.navi.tool.ui.extra;

import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final /* synthetic */ class q0 implements View.OnTouchListener {

    /* renamed from: a, reason: collision with root package name */
    public final /* synthetic */ int f5411a;

    /* renamed from: b, reason: collision with root package name */
    public final /* synthetic */ Object f5412b;

    public /* synthetic */ q0(int i7, Object obj) {
        this.f5411a = i7;
        this.f5412b = obj;
    }

    @Override // android.view.View.OnTouchListener
    public final boolean onTouch(View view, MotionEvent motionEvent) {
        boolean lambda$setupMusicPluginInteraction$104;
        boolean lambda$bindFrontCarModelSeatPanelDialog$48;
        boolean lambda$bindTireCardGesture$46;
        boolean lambda$initViews$3;
        int i7 = this.f5411a;
        Object obj = this.f5412b;
        switch (i7) {
            case 0:
                lambda$setupMusicPluginInteraction$104 = DesktopAssistantActivity.lambda$setupMusicPluginInteraction$104((GestureDetector) obj, view, motionEvent);
                return lambda$setupMusicPluginInteraction$104;
            case 1:
                lambda$bindFrontCarModelSeatPanelDialog$48 = DesktopAssistantCarControlHelper.lambda$bindFrontCarModelSeatPanelDialog$48((GestureDetector) obj, view, motionEvent);
                return lambda$bindFrontCarModelSeatPanelDialog$48;
            case 2:
                lambda$bindTireCardGesture$46 = DesktopAssistantCarControlHelper.lambda$bindTireCardGesture$46((GestureDetector) obj, view, motionEvent);
                return lambda$bindTireCardGesture$46;
            default:
                lambda$initViews$3 = ((FileVideoPreviewActivity) obj).lambda$initViews$3(view, motionEvent);
                return lambda$initViews$3;
        }
    }
}
