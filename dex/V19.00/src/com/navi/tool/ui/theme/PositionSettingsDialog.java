package com.navi.tool.ui.theme;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import androidx.appcompat.app.n;
import androidx.appcompat.app.o;
import androidx.fragment.app.q;
import com.google.android.material.datepicker.u;
import com.navi.tool.utils.DialogImeNavBarHelper;
import com.navi.tool.utils.ToastHelper;
import g4.p;
import p0.p1;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public class PositionSettingsDialog extends q {
    private static final String ARG_INITIAL_X = "initial_x";
    private static final String ARG_INITIAL_Y = "initial_y";
    private static final String ARG_TITLE = "title";
    private EditText etXPosition;
    private EditText etYPosition;
    private OnPositionSetListener listener;

    public interface OnPositionSetListener {
        void onPositionSet(int i7, int i8);
    }

    private int dpToPx(int i7) {
        return Math.round(i7 * requireContext().getResources().getDisplayMetrics().density);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$onCreateDialog$0(View view) {
        submitPosition();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$onCreateDialog$1(o oVar, DialogInterface dialogInterface) {
        oVar.e(-1).setOnClickListener(new u(4, this));
    }

    public static PositionSettingsDialog newInstance(String str, int i7, int i8) {
        PositionSettingsDialog positionSettingsDialog = new PositionSettingsDialog();
        Bundle bundle = new Bundle();
        bundle.putString(ARG_TITLE, str);
        bundle.putInt(ARG_INITIAL_X, i7);
        bundle.putInt(ARG_INITIAL_Y, i8);
        positionSettingsDialog.setArguments(bundle);
        return positionSettingsDialog;
    }

    private void submitPosition() {
        try {
            String trim = this.etXPosition.getText().toString().trim();
            String trim2 = this.etYPosition.getText().toString().trim();
            if (!trim.isEmpty() && !trim2.isEmpty()) {
                int parseInt = Integer.parseInt(trim);
                int parseInt2 = Integer.parseInt(trim2);
                if (parseInt >= 0 && parseInt <= 1920) {
                    if (parseInt2 >= 0 && parseInt2 <= 720) {
                        OnPositionSetListener onPositionSetListener = this.listener;
                        if (onPositionSetListener != null) {
                            onPositionSetListener.onPositionSet(parseInt, parseInt2);
                        }
                        dismiss();
                        return;
                    }
                    ToastHelper.show(getContext(), "Y坐标必须在0-720范围内");
                    return;
                }
                ToastHelper.show(getContext(), "X坐标必须在0-1920范围内");
                return;
            }
            ToastHelper.show(getContext(), "请输入完整的坐标值");
        } catch (NumberFormatException unused) {
            ToastHelper.show(getContext(), "请输入有效的数字");
        }
    }

    @Override // androidx.lifecycle.k
    public e1.c getDefaultViewModelCreationExtras() {
        return e1.a.INSTANCE;
    }

    @Override // androidx.fragment.app.q
    public Dialog onCreateDialog(Bundle bundle) {
        Bundle arguments = getArguments();
        String string = arguments != null ? arguments.getString(ARG_TITLE, "设置位置") : "设置位置";
        int i7 = arguments != null ? arguments.getInt(ARG_INITIAL_X, 0) : 0;
        int i8 = arguments != null ? arguments.getInt(ARG_INITIAL_Y, 0) : 0;
        View inflate = LayoutInflater.from(requireContext()).inflate(g4.q.dialog_position_settings, (ViewGroup) null);
        this.etXPosition = (EditText) inflate.findViewById(p.etXPosition);
        this.etYPosition = (EditText) inflate.findViewById(p.etYPosition);
        this.etXPosition.setText(String.valueOf(i7));
        this.etYPosition.setText(String.valueOf(i8));
        n nVar = new n(requireContext());
        nVar.t(string);
        nVar.u(inflate);
        nVar.n(g4.u.cancel);
        nVar.q(g4.u.ok, null);
        final o e7 = nVar.e();
        e7.setOnShowListener(new DialogInterface.OnShowListener() { // from class: com.navi.tool.ui.theme.k
            @Override // android.content.DialogInterface.OnShowListener
            public final void onShow(DialogInterface dialogInterface) {
                PositionSettingsDialog.this.lambda$onCreateDialog$1(e7, dialogInterface);
            }
        });
        DialogImeNavBarHelper.install(e7, this.etXPosition, this.etYPosition);
        return e7;
    }

    @Override // androidx.fragment.app.q, androidx.fragment.app.z
    public void onStart() {
        super.onStart();
        Dialog dialog = getDialog();
        if (dialog != null) {
            Window window = dialog.getWindow();
            if (window != null) {
                window.setLayout(Math.round(requireContext().getResources().getDisplayMetrics().widthPixels * 0.5f), -2);
                window.setGravity(17);
                WindowManager.LayoutParams attributes = window.getAttributes();
                p1.a(window, true);
                window.setSoftInputMode(20);
                window.clearFlags(768);
                window.setAttributes(attributes);
            }
            DialogImeNavBarHelper.hideAfterShow(dialog);
        }
    }

    public void setOnPositionSetListener(OnPositionSetListener onPositionSetListener) {
        this.listener = onPositionSetListener;
    }
}
