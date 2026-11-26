package com.navi.tool.ui.extra;

import android.view.MotionEvent;
import android.view.View;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final /* synthetic */ class x0 implements View.OnTouchListener {

    /* renamed from: a, reason: collision with root package name */
    public final /* synthetic */ int f5550a;

    /* renamed from: b, reason: collision with root package name */
    public final /* synthetic */ DesktopAssistantActivity f5551b;

    public /* synthetic */ x0(DesktopAssistantActivity desktopAssistantActivity, int i7) {
        this.f5550a = i7;
        this.f5551b = desktopAssistantActivity;
    }

    @Override // android.view.View.OnTouchListener
    public final boolean onTouch(View view, MotionEvent motionEvent) {
        boolean lambda$showHandles$106;
        boolean lambda$initViews$52;
        boolean handleMusicProgressTouch;
        boolean handleMusicProgressTouch2;
        int i7 = this.f5550a;
        DesktopAssistantActivity desktopAssistantActivity = this.f5551b;
        switch (i7) {
            case 0:
                lambda$showHandles$106 = desktopAssistantActivity.lambda$showHandles$106(view, motionEvent);
                return lambda$showHandles$106;
            case 1:
                lambda$initViews$52 = desktopAssistantActivity.lambda$initViews$52(view, motionEvent);
                return lambda$initViews$52;
            case 2:
                handleMusicProgressTouch = desktopAssistantActivity.handleMusicProgressTouch(view, motionEvent);
                return handleMusicProgressTouch;
            default:
                handleMusicProgressTouch2 = desktopAssistantActivity.handleMusicProgressTouch(view, motionEvent);
                return handleMusicProgressTouch2;
        }
    }
}
