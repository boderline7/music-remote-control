package com.navi.tool.utils;

import android.R;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ResolveInfo;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.GradientDrawable;
import android.os.Environment;
import android.support.v4.media.session.PlaybackStateCompat;
import android.text.TextPaint;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.ScrollView;
import android.widget.TextView;
import androidx.core.content.FileProvider;
import com.yalantis.ucrop.view.CropImageView;
import java.io.File;
import java.io.IOException;
import s5.h0;
import s5.o0;
import s5.r0;
import s5.x0;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public class UpdateManager {

    /* renamed from: com.navi.tool.utils.UpdateManager$1, reason: invalid class name */
    public class AnonymousClass1 implements s5.g {
        final /* synthetic */ Activity val$activity;
        final /* synthetic */ Button val$btn;
        final /* synthetic */ ProgressBar val$progressBar;
        final /* synthetic */ TextView val$statusText;

        public AnonymousClass1(Activity activity, TextView textView, Button button, ProgressBar progressBar) {
            this.val$activity = activity;
            this.val$statusText = textView;
            this.val$btn = button;
            this.val$progressBar = progressBar;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static /* synthetic */ void lambda$onFailure$0(TextView textView, IOException iOException, Button button) {
            textView.setText("下载失败: " + iOException.getMessage());
            button.setEnabled(true);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static void lambda$onResponse$1(TextView textView, x0 x0Var, Button button) {
            textView.setText("下载失败，服务器错误: " + x0Var.f8916d);
            button.setEnabled(true);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static /* synthetic */ void lambda$onResponse$2(long j7, long j8, ProgressBar progressBar, TextView textView) {
            int i7 = (int) ((j7 * 100) / j8);
            progressBar.setProgress(i7);
            textView.setText("正在下载: " + i7 + "%");
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static /* synthetic */ void lambda$onResponse$3(TextView textView, long j7) {
            textView.setText("正在下载: " + ((j7 / PlaybackStateCompat.ACTION_PLAY_FROM_MEDIA_ID) / PlaybackStateCompat.ACTION_PLAY_FROM_MEDIA_ID) + "MB");
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static /* synthetic */ void lambda$onResponse$4(TextView textView, Activity activity, File file, Button button) {
            textView.setText("下载完成，准备安装...");
            UpdateManager.installApk(activity, file);
            button.setEnabled(true);
            button.setText("重新安装");
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static /* synthetic */ void lambda$onResponse$5(TextView textView, Exception exc, Button button) {
            textView.setText("下载出错: " + exc.getMessage());
            button.setEnabled(true);
        }

        @Override // s5.g
        public void onFailure(s5.f fVar, IOException iOException) {
            this.val$activity.runOnUiThread(new q(this.val$statusText, iOException, this.val$btn, 2));
        }

        /* JADX WARN: Removed duplicated region for block: B:44:0x00de  */
        /* JADX WARN: Removed duplicated region for block: B:46:0x00e3  */
        @Override // s5.g
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public void onResponse(s5.f r21, s5.x0 r22) {
            /*
                Method dump skipped, instructions count: 231
                To view this dump add '--comments-level debug' option
            */
            throw new UnsupportedOperationException("Method not decompiled: com.navi.tool.utils.UpdateManager.AnonymousClass1.onResponse(s5.f, s5.x0):void");
        }
    }

    private static void downloadApk(Activity activity, String str, ProgressBar progressBar, TextView textView, Button button) {
        o0 o0Var = new o0();
        r0 r0Var = new r0();
        r0Var.f(str);
        o0Var.a(r0Var.a()).e(new AnonymousClass1(activity, textView, button, progressBar));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void installApk(Context context, File file) {
        try {
            Intent intent = new Intent("android.intent.action.VIEW");
            intent.addFlags(268435456);
            intent.addFlags(1);
            intent.setDataAndType(FileProvider.b(context, context.getPackageName() + ".fileprovider", file), "application/vnd.android.package-archive");
            String str = null;
            for (ResolveInfo resolveInfo : context.getPackageManager().queryIntentActivities(intent, 0)) {
                String str2 = resolveInfo.activityInfo.packageName;
                if (!str2.equals("com.android.packageinstaller") && !str2.equals("com.google.android.packageinstaller")) {
                    if ((resolveInfo.activityInfo.applicationInfo.flags & 1) != 0) {
                        str = str2;
                    }
                }
                str = str2;
            }
            if (str != null) {
                intent.setPackage(str);
            }
            context.startActivity(intent);
        } catch (Exception e7) {
            ToastHelper.show(context, "启动安装失败: " + e7.getMessage());
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ void lambda$showUpdateDialog$0(Activity activity, Button button, TextView textView, ProgressBar progressBar, String str, View view) {
        File file = new File(activity.getExternalFilesDir(Environment.DIRECTORY_DOWNLOADS), "update.apk");
        if (button.getText().toString().contains("重新安装") && file.exists() && file.length() > 0) {
            textView.setVisibility(0);
            textView.setText("正在尝试重新启动安装界面...");
            installApk(activity, file);
        } else {
            button.setEnabled(false);
            progressBar.setVisibility(0);
            textView.setVisibility(0);
            textView.setText("准备下载...");
            downloadApk(activity, str, progressBar, textView, button);
        }
    }

    public static void showUpdateDialog(final Activity activity, String str, final String str2, byte[] bArr, boolean z6) {
        TextView textView;
        if (activity == null || activity.isFinishing()) {
            return;
        }
        boolean isCurrentNightMode = BroadcastUtils.isCurrentNightMode(activity);
        int i7 = isCurrentNightMode ? -14736341 : -1;
        int i8 = isCurrentNightMode ? -657414 : -15066598;
        int i9 = isCurrentNightMode ? -2235672 : -13421773;
        int i10 = isCurrentNightMode ? -4274738 : -11184811;
        int i11 = isCurrentNightMode ? 872415231 : 855638016;
        int i12 = isCurrentNightMode ? -12750936 : -13673319;
        int i13 = isCurrentNightMode ? -6306832 : -13673319;
        LinearLayout linearLayout = new LinearLayout(activity);
        linearLayout.setOrientation(1);
        int i14 = (int) ((activity.getResources().getDisplayMetrics().density * 16.0f) + 0.5f);
        int i15 = (int) ((activity.getResources().getDisplayMetrics().density * 12.0f) + 0.5f);
        int i16 = (int) ((activity.getResources().getDisplayMetrics().density * 8.0f) + 0.5f);
        linearLayout.setPadding(i14, i14, i14, i14);
        int i17 = (int) (activity.getResources().getDisplayMetrics().widthPixels * 0.85f);
        TextView textView2 = new TextView(activity);
        textView2.setText(z6 ? "检测到需要强制升级的版本" : "检测到新版本");
        textView2.setTextSize(18.0f);
        textView2.setTextColor(i8);
        textView2.setTypeface(textView2.getTypeface(), 1);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(-1, -2);
        layoutParams.bottomMargin = i16;
        textView2.setLayoutParams(layoutParams);
        textView2.setMaxWidth(i17);
        TextView textView3 = new TextView(activity);
        textView3.setText("更新内容如下：");
        textView3.setTextSize(16.0f);
        textView3.setTextColor(i9);
        LinearLayout.LayoutParams layoutParams2 = new LinearLayout.LayoutParams(-1, -2);
        layoutParams2.bottomMargin = i16;
        textView3.setLayoutParams(layoutParams2);
        textView3.setMaxWidth(i17);
        ScrollView scrollView = new ScrollView(activity);
        scrollView.setLayoutParams(new LinearLayout.LayoutParams(-2, -2));
        TextView textView4 = new TextView(activity);
        String str3 = h0.FRAGMENT_ENCODE_SET;
        if (str != null) {
            str3 = str;
        }
        textView4.setText(str3);
        textView4.setTextSize(14.0f);
        textView4.setTextColor(i10);
        int i18 = i7;
        textView4.setLineSpacing(CropImageView.DEFAULT_ASPECT_RATIO, 1.2f);
        textView4.setPadding(0, 0, 0, i15);
        textView4.setMaxWidth(i17);
        scrollView.addView(textView4);
        View view = new View(activity);
        LinearLayout.LayoutParams layoutParams3 = new LinearLayout.LayoutParams(-1, 1);
        layoutParams3.topMargin = i16;
        layoutParams3.bottomMargin = i15;
        view.setLayoutParams(layoutParams3);
        view.setBackgroundColor(i11);
        linearLayout.addView(textView2);
        linearLayout.addView(textView3);
        linearLayout.addView(scrollView);
        linearLayout.addView(view);
        if (str2 == null || str2.isEmpty()) {
            textView = textView3;
        } else {
            final Button button = new Button(activity);
            button.setText("立即下载更新");
            button.setTextColor(-1);
            button.setAllCaps(false);
            GradientDrawable gradientDrawable = new GradientDrawable();
            gradientDrawable.setColor(i12);
            gradientDrawable.setCornerRadius(i16);
            button.setBackground(gradientDrawable);
            button.setPadding(i14, i15, i14, i15);
            linearLayout.addView(button, new LinearLayout.LayoutParams(-1, -2));
            final ProgressBar progressBar = new ProgressBar(activity, null, R.attr.progressBarStyleHorizontal);
            progressBar.setVisibility(8);
            LinearLayout.LayoutParams layoutParams4 = new LinearLayout.LayoutParams(-1, -2);
            layoutParams4.topMargin = i16;
            linearLayout.addView(progressBar, layoutParams4);
            final TextView textView5 = new TextView(activity);
            textView5.setVisibility(8);
            textView5.setGravity(17);
            textView5.setTextColor(i10);
            linearLayout.addView(textView5);
            textView = textView3;
            button.setOnClickListener(new View.OnClickListener() { // from class: com.navi.tool.utils.p
                @Override // android.view.View.OnClickListener
                public final void onClick(View view2) {
                    UpdateManager.lambda$showUpdateDialog$0(activity, button, textView5, progressBar, str2, view2);
                }
            });
        }
        int i19 = (int) ((activity.getResources().getDisplayMetrics().density * 240.0f) + 0.5f);
        int i20 = (int) (activity.getResources().getDisplayMetrics().widthPixels * 0.85f);
        float measureText = textView2.getPaint().measureText(textView2.getText().toString());
        float measureText2 = textView.getPaint().measureText(textView.getText().toString());
        TextPaint paint = textView4.getPaint();
        String[] split = (str == null ? h0.FRAGMENT_ENCODE_SET : str).split("\n");
        float f7 = CropImageView.DEFAULT_ASPECT_RATIO;
        for (String str4 : split) {
            float measureText3 = paint.measureText(str4);
            if (measureText3 > f7) {
                f7 = measureText3;
            }
        }
        int max = Math.max(i19, Math.min(i20, (i14 * 2) + ((int) Math.ceil(Math.max(Math.max(measureText, measureText2), f7)))));
        AlertDialog.Builder view2 = new AlertDialog.Builder(activity).setView(linearLayout);
        if (!z6) {
            view2.setPositiveButton("关闭", (DialogInterface.OnClickListener) null);
        }
        AlertDialog create = view2.create();
        create.setCancelable(!z6);
        create.show();
        Button button2 = create.getButton(-1);
        if (button2 != null) {
            button2.setTextColor(i13);
        }
        DialogImeNavBarHelper.hideAfterShow(create);
        Window window = create.getWindow();
        if (window != null) {
            window.setBackgroundDrawable(new ColorDrawable(0));
            WindowManager.LayoutParams attributes = window.getAttributes();
            attributes.width = max;
            attributes.height = -2;
            window.setAttributes(attributes);
        }
        GradientDrawable gradientDrawable2 = new GradientDrawable();
        gradientDrawable2.setColor(i18);
        gradientDrawable2.setCornerRadius((int) ((activity.getResources().getDisplayMetrics().density * 16.0f) + 0.5f));
        linearLayout.setBackground(gradientDrawable2);
    }
}
