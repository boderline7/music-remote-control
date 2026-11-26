package com.navi.tool.service;

import android.view.MotionEvent;
import android.view.View;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final /* synthetic */ class h implements View.OnTouchListener {

    /* renamed from: a, reason: collision with root package name */
    public final /* synthetic */ int f4869a;

    /* renamed from: b, reason: collision with root package name */
    public final /* synthetic */ GestureActionService f4870b;

    public /* synthetic */ h(GestureActionService gestureActionService, int i7) {
        this.f4869a = i7;
        this.f4870b = gestureActionService;
    }

    @Override // android.view.View.OnTouchListener
    public final boolean onTouch(View view, MotionEvent motionEvent) {
        boolean onTriggerTouch;
        boolean lambda$addAppToPanel$3;
        boolean lambda$ensurePanelView$0;
        boolean onTriggerTouch2;
        int i7 = this.f4869a;
        GestureActionService gestureActionService = this.f4870b;
        switch (i7) {
            case 0:
                onTriggerTouch = gestureActionService.onTriggerTouch(view, motionEvent);
                return onTriggerTouch;
            case 1:
                lambda$addAppToPanel$3 = gestureActionService.lambda$addAppToPanel$3(view, motionEvent);
                return lambda$addAppToPanel$3;
            case 2:
                lambda$ensurePanelView$0 = gestureActionService.lambda$ensurePanelView$0(view, motionEvent);
                return lambda$ensurePanelView$0;
            default:
                onTriggerTouch2 = gestureActionService.onTriggerTouch(view, motionEvent);
                return onTriggerTouch2;
        }
    }
}
