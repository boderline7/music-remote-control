package com.navi.tool;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import com.navi.tool.ui.extra.DesktopAssistantActivity;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public class DesktopAssistantStubActivity extends Activity {
    @Override // android.app.Activity
    public final void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        Intent intent = new Intent(this, (Class<?>) DesktopAssistantActivity.class);
        intent.addFlags(268435456);
        startActivity(intent);
        finish();
    }
}
