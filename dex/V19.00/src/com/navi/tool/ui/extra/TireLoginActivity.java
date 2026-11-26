package com.navi.tool.ui.extra;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.ColorStateList;
import android.graphics.BitmapFactory;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Base64;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.ScrollView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;
import com.navi.tool.carinfo.TirePollingService;
import com.navi.tool.tire.api.TireAccount;
import com.navi.tool.tire.api.TireBrand;
import com.navi.tool.tire.api.TireEngine;
import com.navi.tool.tire.api.TireSnapshot;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Locale;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.RejectedExecutionException;
import org.json.JSONObject;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public class TireLoginActivity extends Activity {
    private static final int COLOR_OK = -11751600;
    private static final int COLOR_WARN = -689152;
    private RadioGroup brandGroup;
    private TextView captchaHint;
    private ImageView captchaImage;
    private EditText captchaInput;
    private LinearLayout captchaRow;
    private RadioButton cheryRadio;
    private EditText codeInput;
    private int colorBg;
    private int colorCard;
    private int colorCardStroke;
    private int colorHint;
    private int colorOnPrimary;
    private int colorPrimary;
    private int colorText;
    private TireEngine engine;
    private RadioButton exeedRadio;
    private Button fetchButton;
    private TextView flTemp;
    private TextView flValue;
    private TextView frTemp;
    private TextView frValue;
    private SeekBar intervalBar;
    private TextView intervalText;
    private LinearLayout layoutData;
    private LinearLayout layoutLogin;
    private TextView logText;
    private Button loginButton;
    private Button logoutButton;
    private TextView nextFetchText;
    private EditText phoneInput;
    private TextView rlTemp;
    private TextView rlValue;
    private TextView rrTemp;
    private TextView rrValue;
    private Button sendCodeButton;
    private TextView statusText;
    private TextView tireTimeText;
    private final Handler main = new Handler(Looper.getMainLooper());
    private final ExecutorService executor = Executors.newSingleThreadExecutor();
    private final LinkedList<String> logLines = new LinkedList<>();
    private int smsCountdown = 0;
    private boolean fetching = false;
    private final Runnable smsCountdownTick = new Runnable() { // from class: com.navi.tool.ui.extra.TireLoginActivity.1
        @Override // java.lang.Runnable
        public void run() {
            if (TireLoginActivity.this.smsCountdown <= 0) {
                TireLoginActivity.this.stopSmsCountdown();
                return;
            }
            TireLoginActivity.this.sendCodeButton.setText(TireLoginActivity.this.smsCountdown + "s");
            TireLoginActivity.access$010(TireLoginActivity.this);
            TireLoginActivity.this.main.postDelayed(this, 1000L);
        }
    };
    private final Runnable countdownTick = new Runnable() { // from class: com.navi.tool.ui.extra.TireLoginActivity.2
        @Override // java.lang.Runnable
        public void run() {
            TireLoginActivity.this.updateNextFetchText();
            TireLoginActivity.this.main.postDelayed(this, 1000L);
        }
    };

    /* renamed from: com.navi.tool.ui.extra.TireLoginActivity$3, reason: invalid class name */
    public class AnonymousClass3 implements o4.t {
        public AnonymousClass3() {
        }

        /* JADX INFO: Access modifiers changed from: private */
        public /* synthetic */ void lambda$onError$1(String str) {
            if (TireLoginActivity.this.isActivityAlive()) {
                TireLoginActivity.this.statusText.setText("模块加载失败：" + str);
                TireLoginActivity.this.statusText.setTextColor(TireLoginActivity.COLOR_WARN);
                TireLoginActivity.this.appendLog("模块加载失败：" + str);
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public /* synthetic */ void lambda$onReady$0() {
            if (TireLoginActivity.this.isActivityAlive()) {
                TireLoginActivity.this.refreshLoginUi();
                TireLoginActivity.this.autoFetchIfNeeded();
            }
        }

        @Override // o4.t
        public void onError(String str) {
            TireLoginActivity.this.main.post(new z0(4, (Object) this, (Object) str));
        }

        @Override // o4.t
        public void onReady(TireEngine tireEngine) {
            TireLoginActivity.this.engine = tireEngine;
            TireLoginActivity.this.main.post(new f4(2, this));
        }
    }

    public static /* synthetic */ int access$010(TireLoginActivity tireLoginActivity) {
        int i7 = tireLoginActivity.smsCountdown;
        tireLoginActivity.smsCountdown = i7 - 1;
        return i7;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void appendLog(String str) {
        String format = new SimpleDateFormat("HH:mm:ss", Locale.US).format(new Date());
        this.logLines.addFirst("[" + format + "] " + str);
        while (this.logLines.size() > 10) {
            this.logLines.removeLast();
        }
        StringBuilder sb = new StringBuilder();
        Iterator<String> it = this.logLines.iterator();
        while (it.hasNext()) {
            sb.append(it.next());
            sb.append('\n');
        }
        this.logText.setText(sb.toString());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void autoFetchIfNeeded() {
        if (o4.z.e(this)) {
            doFetch();
        }
    }

    private static String brandName(TireBrand tireBrand) {
        return tireBrand == TireBrand.CHERY ? "奇瑞" : "星途";
    }

    private View buildContentView() {
        ScrollView scrollView = new ScrollView(this);
        scrollView.setBackgroundColor(this.colorBg);
        LinearLayout linearLayout = new LinearLayout(this);
        linearLayout.setOrientation(1);
        linearLayout.setPadding(dp(20), dp(20), dp(20), dp(20));
        scrollView.addView(linearLayout);
        LinearLayout linearLayout2 = new LinearLayout(this);
        linearLayout2.setOrientation(0);
        linearLayout2.setGravity(16);
        TextView makeText = makeText("←", 22, this.colorText, false);
        makeText.setGravity(17);
        makeText.setPadding(0, 0, dp(10), 0);
        makeText.setContentDescription("返回");
        makeText.setOnClickListener(new c6(this, 4));
        linearLayout2.addView(makeText);
        linearLayout2.addView(makeText("胎压（云端车况）", 20, this.colorText, true));
        linearLayout.addView(linearLayout2, matchWrap());
        TextView makeText2 = makeText("未登录", 13, this.colorHint, false);
        this.statusText = makeText2;
        makeText2.setPadding(0, dp(6), 0, dp(14));
        linearLayout.addView(this.statusText);
        buildLoginArea(linearLayout);
        buildDataArea(linearLayout);
        buildIntervalArea(linearLayout);
        Button makeButton = makeButton("退出登录", this.colorCard, this.colorHint, this.colorCardStroke);
        this.logoutButton = makeButton;
        makeButton.setVisibility(8);
        this.logoutButton.setOnClickListener(new c6(this, 5));
        linearLayout.addView(this.logoutButton, matchWrap());
        TextView makeText3 = makeText(s5.h0.FRAGMENT_ENCODE_SET, 12, this.colorHint, false);
        this.logText = makeText3;
        makeText3.setPadding(0, dp(16), 0, 0);
        this.logText.setTextIsSelectable(true);
        linearLayout.addView(this.logText);
        return scrollView;
    }

    private void buildDataArea(LinearLayout linearLayout) {
        LinearLayout linearLayout2 = new LinearLayout(this);
        this.layoutData = linearLayout2;
        linearLayout2.setOrientation(1);
        this.layoutData.setVisibility(8);
        this.flValue = new TextView(this);
        this.flTemp = new TextView(this);
        this.frValue = new TextView(this);
        this.frTemp = new TextView(this);
        this.rlValue = new TextView(this);
        this.rlTemp = new TextView(this);
        this.rrValue = new TextView(this);
        this.rrTemp = new TextView(this);
        LinearLayout linearLayout3 = new LinearLayout(this);
        linearLayout3.setOrientation(0);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(0, -2, 1.0f);
        layoutParams.setMargins(0, 0, dp(8), dp(8));
        linearLayout3.addView(tireCell("左前", this.flValue, this.flTemp), layoutParams);
        LinearLayout.LayoutParams layoutParams2 = new LinearLayout.LayoutParams(0, -2, 1.0f);
        layoutParams2.setMargins(0, 0, 0, dp(8));
        linearLayout3.addView(tireCell("右前", this.frValue, this.frTemp), layoutParams2);
        this.layoutData.addView(linearLayout3, matchWrap());
        LinearLayout linearLayout4 = new LinearLayout(this);
        linearLayout4.setOrientation(0);
        LinearLayout.LayoutParams layoutParams3 = new LinearLayout.LayoutParams(0, -2, 1.0f);
        layoutParams3.setMargins(0, 0, dp(8), 0);
        linearLayout4.addView(tireCell("左后", this.rlValue, this.rlTemp), layoutParams3);
        linearLayout4.addView(tireCell("右后", this.rrValue, this.rrTemp), new LinearLayout.LayoutParams(0, -2, 1.0f));
        this.layoutData.addView(linearLayout4, matchWrap());
        TextView makeText = makeText("车端更新：--", 12, this.colorHint, false);
        this.tireTimeText = makeText;
        makeText.setPadding(0, dp(10), 0, dp(10));
        this.layoutData.addView(this.tireTimeText, matchWrap());
        Button makeButton = makeButton("刷新胎压", this.colorPrimary, this.colorOnPrimary, 0);
        this.fetchButton = makeButton;
        makeButton.setOnClickListener(new c6(this, 3));
        this.layoutData.addView(this.fetchButton, matchWrap());
        linearLayout.addView(this.layoutData, matchWrap());
    }

    private void buildIntervalArea(LinearLayout linearLayout) {
        TextView makeText = makeText("后台拉取间隔：每 " + o4.z.d(this) + " 分钟", 14, this.colorText, false);
        this.intervalText = makeText;
        makeText.setPadding(0, dp(20), 0, dp(8));
        linearLayout.addView(this.intervalText, matchWrap());
        SeekBar seekBar = new SeekBar(this);
        this.intervalBar = seekBar;
        seekBar.setMax(10);
        this.intervalBar.setProgress((o4.z.d(this) - 10) / 5);
        this.intervalBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() { // from class: com.navi.tool.ui.extra.TireLoginActivity.4
            @Override // android.widget.SeekBar.OnSeekBarChangeListener
            public void onProgressChanged(SeekBar seekBar2, int i7, boolean z6) {
                if (z6) {
                    int i8 = (i7 * 5) + 10;
                    TireLoginActivity tireLoginActivity = TireLoginActivity.this;
                    tireLoginActivity.getApplicationContext().getSharedPreferences("tire_account", 0).edit().putInt("tire_interval_min", Math.max(10, Math.min(60, i8))).apply();
                    a.a.v("后台拉取间隔：每 ", i8, " 分钟", TireLoginActivity.this.intervalText);
                    TireLoginActivity.this.updateNextFetchText();
                }
            }

            @Override // android.widget.SeekBar.OnSeekBarChangeListener
            public void onStartTrackingTouch(SeekBar seekBar2) {
            }

            @Override // android.widget.SeekBar.OnSeekBarChangeListener
            public void onStopTrackingTouch(SeekBar seekBar2) {
                TireLoginActivity.this.doFetch();
            }
        });
        linearLayout.addView(this.intervalBar, matchWrap());
        TextView makeText2 = makeText(s5.h0.FRAGMENT_ENCODE_SET, 13, COLOR_OK, false);
        this.nextFetchText = makeText2;
        makeText2.setPadding(0, dp(6), 0, dp(6));
        this.nextFetchText.setVisibility(8);
        linearLayout.addView(this.nextFetchText, matchWrap());
    }

    private void buildLoginArea(LinearLayout linearLayout) {
        LinearLayout linearLayout2 = new LinearLayout(this);
        this.layoutLogin = linearLayout2;
        int i7 = 1;
        linearLayout2.setOrientation(1);
        RadioGroup radioGroup = new RadioGroup(this);
        this.brandGroup = radioGroup;
        radioGroup.setOrientation(0);
        RadioButton radioButton = new RadioButton(this);
        this.exeedRadio = radioButton;
        radioButton.setText("星途");
        this.exeedRadio.setTextColor(this.colorText);
        this.exeedRadio.setButtonTintList(ColorStateList.valueOf(this.colorPrimary));
        this.exeedRadio.setId(View.generateViewId());
        RadioButton radioButton2 = new RadioButton(this);
        this.cheryRadio = radioButton2;
        radioButton2.setText("奇瑞");
        this.cheryRadio.setTextColor(this.colorText);
        this.cheryRadio.setButtonTintList(ColorStateList.valueOf(this.colorPrimary));
        this.cheryRadio.setId(View.generateViewId());
        this.brandGroup.addView(this.exeedRadio, new RadioGroup.LayoutParams(0, -2, 1.0f));
        this.brandGroup.addView(this.cheryRadio, new RadioGroup.LayoutParams(0, -2, 1.0f));
        this.brandGroup.setOnCheckedChangeListener(new s4(i7, this));
        this.layoutLogin.addView(this.brandGroup, matchWrap());
        EditText makeEditText = makeEditText("手机号", 3);
        this.phoneInput = makeEditText;
        this.layoutLogin.addView(makeEditText, matchWrap());
        LinearLayout linearLayout3 = new LinearLayout(this);
        this.captchaRow = linearLayout3;
        linearLayout3.setOrientation(0);
        this.captchaRow.setGravity(16);
        this.captchaRow.setPadding(0, dp(8), 0, 0);
        EditText makeEditText2 = makeEditText("图形验证码", 1);
        this.captchaInput = makeEditText2;
        this.captchaRow.addView(makeEditText2, new LinearLayout.LayoutParams(0, -2, 1.0f));
        FrameLayout frameLayout = new FrameLayout(this);
        GradientDrawable gradientDrawable = new GradientDrawable();
        gradientDrawable.setColor(this.colorCard);
        gradientDrawable.setCornerRadius(dp(6));
        gradientDrawable.setStroke(dp(1), this.colorCardStroke);
        frameLayout.setBackground(gradientDrawable);
        frameLayout.setOnClickListener(new c6(this, 0));
        ImageView imageView = new ImageView(this);
        this.captchaImage = imageView;
        imageView.setScaleType(ImageView.ScaleType.FIT_CENTER);
        this.captchaImage.setPadding(dp(4), dp(4), dp(4), dp(4));
        frameLayout.addView(this.captchaImage, new FrameLayout.LayoutParams(-1, -1));
        TextView makeText = makeText("点击获取", 12, this.colorHint, false);
        this.captchaHint = makeText;
        makeText.setGravity(17);
        frameLayout.addView(this.captchaHint, new FrameLayout.LayoutParams(-1, -1));
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(dp(z1.c.INVOKE_INTERFACE_RANGE), dp(48));
        layoutParams.setMargins(dp(8), 0, 0, 0);
        this.captchaRow.addView(frameLayout, layoutParams);
        this.layoutLogin.addView(this.captchaRow, matchWrap());
        int i8 = this.colorCard;
        int i9 = this.colorPrimary;
        Button makeButton = makeButton("发送短信验证码", i8, i9, i9);
        this.sendCodeButton = makeButton;
        makeButton.setOnClickListener(new c6(this, i7));
        this.layoutLogin.addView(this.sendCodeButton, matchWrap());
        EditText makeEditText3 = makeEditText("短信验证码", 2);
        this.codeInput = makeEditText3;
        this.layoutLogin.addView(makeEditText3, matchWrap());
        Button makeButton2 = makeButton("登录并保存", this.colorPrimary, this.colorOnPrimary, 0);
        this.loginButton = makeButton2;
        makeButton2.setOnClickListener(new c6(this, 2));
        this.layoutLogin.addView(this.loginButton, matchWrap());
        linearLayout.addView(this.layoutLogin, matchWrap());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void doFetch() {
        if (this.fetching || !isActivityAlive()) {
            return;
        }
        TireAccount f7 = o4.z.f(this);
        if (f7 == null || !f7.isUsable()) {
            refreshLoginUi();
            return;
        }
        if (this.engine == null) {
            Toast.makeText(this, "模块尚未加载完成", 0).show();
            return;
        }
        this.fetching = true;
        this.fetchButton.setEnabled(false);
        this.fetchButton.setText("正在拉取…");
        appendLog("拉取车况…");
        try {
            this.executor.execute(new z0(22, this, f7));
        } catch (RejectedExecutionException unused) {
            this.fetching = false;
        }
    }

    private int dp(int i7) {
        return (int) ((i7 * getResources().getDisplayMetrics().density) + 0.5f);
    }

    private void focusCaptchaInput() {
        this.captchaInput.requestFocus();
        EditText editText = this.captchaInput;
        editText.setSelection(editText.getText().length());
        this.captchaInput.post(new e6(this, 1));
    }

    private void hideKeyboard() {
        View currentFocus = getCurrentFocus();
        if (currentFocus != null) {
            currentFocus.clearFocus();
        }
        InputMethodManager inputMethodManager = (InputMethodManager) getSystemService("input_method");
        if (inputMethodManager != null) {
            if (currentFocus == null) {
                currentFocus = getWindow().getDecorView();
            }
            inputMethodManager.hideSoftInputFromWindow(currentFocus.getWindowToken(), 0);
        }
    }

    private void hideSystemUI() {
        getWindow().getDecorView().setSystemUiVisibility(5894);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean isActivityAlive() {
        if (isFinishing()) {
            return false;
        }
        return !isDestroyed();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$buildContentView$0(View view) {
        finish();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$buildContentView$1(View view) {
        logout();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$buildDataArea$6(View view) {
        doFetch();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$buildLoginArea$2(RadioGroup radioGroup, int i7) {
        onBrandChanged();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$buildLoginArea$3(View view) {
        refreshCaptcha(true);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$buildLoginArea$4(View view) {
        sendCode();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$buildLoginArea$5(View view) {
        login();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$doFetch$17(TireSnapshot tireSnapshot) {
        this.fetching = false;
        this.fetchButton.setEnabled(true);
        this.fetchButton.setText("刷新胎压");
        renderSnapshot(tireSnapshot);
        appendLog(tireSnapshot.online ? "拉取成功" : "拉取成功（车辆离线，显示最后上报数据）");
        updateNextFetchText();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$doFetch$18(String str) {
        this.fetching = false;
        this.fetchButton.setEnabled(true);
        this.fetchButton.setText("刷新胎压");
        appendLog("拉取失败：" + str);
        updateNextFetchText();
        if (str.contains("登录已过期") || str.contains("401")) {
            o4.z.g(this);
            o4.z.b(this);
            TirePollingService.e(this);
            refreshLoginUi();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$doFetch$19(TireAccount tireAccount) {
        try {
            TireSnapshot fetch = this.engine.fetch(tireAccount);
            TirePollingService.c(this, fetch);
            o4.z.j(this, System.currentTimeMillis());
            o4.z.h(this);
            this.main.post(new z0(24, this, fetch));
        } catch (Throwable th) {
            this.main.post(new d6(this, th.getMessage() == null ? "拉取失败" : th.getMessage(), 2));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$focusCaptchaInput$10() {
        InputMethodManager inputMethodManager = (InputMethodManager) getSystemService("input_method");
        if (inputMethodManager != null) {
            inputMethodManager.showSoftInput(this.captchaInput, 1);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$login$14() {
        if (isActivityAlive()) {
            this.loginButton.setEnabled(true);
            this.codeInput.setText(s5.h0.FRAGMENT_ENCODE_SET);
            appendLog("登录成功");
            Toast.makeText(this, "登录成功", 0).show();
            hideKeyboard();
            refreshLoginUi();
            TirePollingService.d(this);
            doFetch();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$login$15(String str) {
        if (isActivityAlive()) {
            this.loginButton.setEnabled(true);
            this.statusText.setText("登录失败：" + str);
            this.statusText.setTextColor(COLOR_WARN);
            appendLog("登录失败：" + str);
            Toast.makeText(this, str, 1).show();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void lambda$login$16(TireBrand tireBrand, String str, String str2) {
        try {
            TireAccount login = this.engine.login(tireBrand, str, str2);
            if (login == null || !login.isUsable()) {
                throw new Exception("登录返回的 token 已失效");
            }
            getApplicationContext().getSharedPreferences("tire_account", 0).edit().putString("tire_brand", tireBrand.name()).apply();
            o4.z.i(this, login);
            this.main.post(new e6(this, 0));
        } catch (Throwable th) {
            this.main.post(new d6(this, th.getMessage() == null ? "登录失败" : th.getMessage(), 1));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$refreshCaptcha$8(String str) {
        this.captchaImage.setImageDrawable(null);
        this.captchaHint.setText("获取失败，点击重试");
        this.captchaHint.setVisibility(0);
        appendLog("图形码失败：" + str);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$refreshCaptcha$9(String str, boolean z6) {
        try {
            this.main.post(new a6(this, this.engine.fetchCaptcha(TireBrand.EXEED, str), z6, 1));
        } catch (Throwable th) {
            this.main.post(new d6(this, th.getMessage() == null ? "图形验证码获取失败" : th.getMessage(), 0));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$sendCode$11(TireBrand tireBrand) {
        appendLog("短信验证码已发送");
        Toast.makeText(this, "验证码已发送", 0).show();
        this.codeInput.requestFocus();
        if (tireBrand == TireBrand.EXEED) {
            this.captchaInput.setText(s5.h0.FRAGMENT_ENCODE_SET);
            refreshCaptcha(false);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$sendCode$12(String str, TireBrand tireBrand) {
        appendLog(a.a.B("发送失败：", str));
        Toast.makeText(this, str, 1).show();
        stopSmsCountdown();
        if (tireBrand == TireBrand.EXEED) {
            refreshCaptcha(true);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$sendCode$13(TireBrand tireBrand, String str, String str2) {
        try {
            this.engine.sendSmsCode(tireBrand, str, str2);
            this.main.post(new z0(23, this, tireBrand));
        } catch (Throwable th) {
            this.main.post(new w2(this, th.getMessage() == null ? "发送失败" : th.getMessage(), tireBrand, 15));
        }
    }

    private void login() {
        if (this.engine == null) {
            Toast.makeText(this, "模块尚未就绪", 0).show();
            return;
        }
        String c7 = a.a.c(this.phoneInput);
        String c8 = a.a.c(this.codeInput);
        if (c7.length() < 11 || c8.isEmpty()) {
            Toast.makeText(this, "请填写手机号和短信验证码", 0).show();
            return;
        }
        TireBrand selectedBrand = selectedBrand();
        this.loginButton.setEnabled(false);
        this.statusText.setText("登录中…");
        this.statusText.setTextColor(this.colorHint);
        this.executor.execute(new b6(this, selectedBrand, c7, c8, 0));
    }

    private void logout() {
        o4.z.a(this);
        o4.z.b(this);
        TirePollingService.e(this);
        this.captchaImage.setImageDrawable(null);
        this.codeInput.setText(s5.h0.FRAGMENT_ENCODE_SET);
        appendLog("已退出登录");
        refreshLoginUi();
        Toast.makeText(this, "已退出登录，恢复使用手机推送", 0).show();
    }

    private Button makeButton(String str, int i7, int i8, int i9) {
        Button button = new Button(this);
        button.setText(str);
        button.setTextColor(i8);
        button.setTextSize(15.0f);
        button.setAllCaps(false);
        GradientDrawable gradientDrawable = new GradientDrawable();
        gradientDrawable.setColor(i7);
        gradientDrawable.setCornerRadius(dp(8));
        if (i9 != 0) {
            gradientDrawable.setStroke(dp(1), i9);
        }
        button.setBackground(gradientDrawable);
        button.setPadding(dp(12), dp(10), dp(12), dp(10));
        button.setStateListAnimator(null);
        button.setMinHeight(0);
        button.setMinimumHeight(0);
        return button;
    }

    private EditText makeEditText(String str, int i7) {
        EditText editText = new EditText(this);
        editText.setHint(str);
        editText.setHintTextColor(this.colorHint);
        editText.setTextColor(this.colorText);
        editText.setInputType(i7);
        editText.setSingleLine(true);
        return editText;
    }

    private TextView makeText(String str, int i7, int i8, boolean z6) {
        TextView textView = new TextView(this);
        textView.setText(str);
        textView.setTextSize(i7);
        textView.setTextColor(i8);
        if (z6) {
            textView.setTypeface(null, 1);
        }
        return textView;
    }

    private LinearLayout.LayoutParams matchWrap() {
        return new LinearLayout.LayoutParams(-1, -2);
    }

    private void onBrandChanged() {
        TireBrand selectedBrand = selectedBrand();
        if (selectedBrand != o4.z.c(this)) {
            getApplicationContext().getSharedPreferences("tire_account", 0).edit().putString("tire_brand", selectedBrand.name()).apply();
            if (o4.z.e(this)) {
                o4.z.a(this);
                o4.z.b(this);
                TirePollingService.e(this);
                Toast.makeText(this, "已切换到" + brandName(selectedBrand) + "，请重新登录", 0).show();
            }
            this.captchaInput.setText(s5.h0.FRAGMENT_ENCODE_SET);
            this.codeInput.setText(s5.h0.FRAGMENT_ENCODE_SET);
            this.captchaImage.setImageDrawable(null);
        }
        updateCaptchaVisible();
        refreshLoginUi();
    }

    private void refreshCaptcha(boolean z6) {
        int i7 = 0;
        if (this.engine == null) {
            Toast.makeText(this, "模块尚未就绪", 0).show();
            return;
        }
        String c7 = a.a.c(this.phoneInput);
        if (c7.length() < 11) {
            Toast.makeText(this, "请先填写手机号，再获取图形验证码", 0).show();
            return;
        }
        this.captchaHint.setText("加载中…");
        this.captchaHint.setVisibility(0);
        this.executor.execute(new a6(this, c7, z6, i7));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void refreshLoginUi() {
        String str;
        TireBrand c7 = o4.z.c(this);
        this.exeedRadio.setChecked(c7 == TireBrand.EXEED);
        this.cheryRadio.setChecked(c7 == TireBrand.CHERY);
        updateCaptchaVisible();
        boolean e7 = o4.z.e(this);
        this.layoutLogin.setVisibility(e7 ? 8 : 0);
        this.layoutData.setVisibility(e7 ? 0 : 8);
        this.logoutButton.setVisibility(e7 ? 0 : 8);
        this.nextFetchText.setVisibility(e7 ? 0 : 8);
        if (!e7) {
            this.statusText.setText("未登录 · " + brandName(c7));
            this.statusText.setTextColor(this.colorHint);
            return;
        }
        TireAccount f7 = o4.z.f(this);
        if (f7 == null) {
            this.statusText.setText("未登录 · " + brandName(c7));
            this.statusText.setTextColor(this.colorHint);
            return;
        }
        TirePollingService.d(this);
        String str2 = f7.phone;
        if (str2 != null && str2.length() >= 11) {
            str2 = str2.substring(0, 3) + "****" + str2.substring(7);
        }
        String str3 = f7.tspUid;
        if (str3 == null || str3.isEmpty()) {
            str = s5.h0.FRAGMENT_ENCODE_SET;
        } else {
            str = " · UID " + f7.tspUid;
        }
        this.statusText.setText(brandName(f7.brand) + " · " + str2 + str);
        this.statusText.setTextColor(COLOR_OK);
        renderCachedSnapshot();
        updateNextFetchText();
    }

    private void renderCachedSnapshot() {
        SharedPreferences sharedPreferences = getApplicationContext().getSharedPreferences("tire_account", 0);
        String str = s5.h0.FRAGMENT_ENCODE_SET;
        String string = sharedPreferences.getString("tire_last_snapshot", s5.h0.FRAGMENT_ENCODE_SET);
        if (string == null || string.isEmpty()) {
            return;
        }
        try {
            JSONObject jSONObject = new JSONObject(string);
            setTireCell(this.flValue, this.flTemp, jSONObject.optInt("fl", -1), jSONObject.optInt("flT", 255));
            setTireCell(this.frValue, this.frTemp, jSONObject.optInt("fr", -1), jSONObject.optInt("frT", 255));
            setTireCell(this.rlValue, this.rlTemp, jSONObject.optInt("rl", -1), jSONObject.optInt("rlT", 255));
            setTireCell(this.rrValue, this.rrTemp, jSONObject.optInt("rr", -1), jSONObject.optInt("rrT", 255));
            String optString = jSONObject.optString("src", s5.h0.FRAGMENT_ENCODE_SET);
            long optLong = jSONObject.optLong("fetchedAt", 0L);
            String format = optLong > 0 ? new SimpleDateFormat("HH:mm:ss", Locale.US).format(new Date(optLong)) : "--";
            TextView textView = this.tireTimeText;
            StringBuilder sb = new StringBuilder();
            if (jSONObject.optInt("online", 0) != 1) {
                str = "车辆离线 · ";
            }
            sb.append(str);
            sb.append("车端更新 ");
            if (optString.isEmpty()) {
                optString = "--";
            }
            sb.append(optString);
            sb.append(" · 本次拉取 ");
            sb.append(format);
            textView.setText(sb.toString());
        } catch (Exception unused) {
        }
    }

    private void renderSnapshot(TireSnapshot tireSnapshot) {
        setTireCell(this.flValue, this.flTemp, tireSnapshot.flKpa, tireSnapshot.flTemp);
        setTireCell(this.frValue, this.frTemp, tireSnapshot.frKpa, tireSnapshot.frTemp);
        setTireCell(this.rlValue, this.rlTemp, tireSnapshot.rlKpa, tireSnapshot.rlTemp);
        setTireCell(this.rrValue, this.rrTemp, tireSnapshot.rrKpa, tireSnapshot.rrTemp);
        String str = tireSnapshot.updateTime;
        String trim = (str == null || str.trim().isEmpty()) ? "--" : tireSnapshot.updateTime.trim();
        String format = tireSnapshot.fetchedAt > 0 ? new SimpleDateFormat("HH:mm:ss", Locale.US).format(new Date(tireSnapshot.fetchedAt)) : "--";
        TextView textView = this.tireTimeText;
        StringBuilder sb = new StringBuilder();
        sb.append(tireSnapshot.online ? s5.h0.FRAGMENT_ENCODE_SET : "车辆离线 · ");
        sb.append("车端更新 ");
        sb.append(trim);
        sb.append(" · 本次拉取 ");
        sb.append(format);
        textView.setText(sb.toString());
    }

    private void restoreFormState(Bundle bundle) {
        if (bundle == null) {
            return;
        }
        this.phoneInput.setText(bundle.getString("tire_phone", s5.h0.FRAGMENT_ENCODE_SET));
        this.captchaInput.setText(bundle.getString("tire_captcha", s5.h0.FRAGMENT_ENCODE_SET));
        this.codeInput.setText(bundle.getString("tire_code", s5.h0.FRAGMENT_ENCODE_SET));
    }

    private TireBrand selectedBrand() {
        return this.brandGroup.getCheckedRadioButtonId() == this.cheryRadio.getId() ? TireBrand.CHERY : TireBrand.EXEED;
    }

    private void sendCode() {
        if (this.engine == null) {
            Toast.makeText(this, "模块尚未就绪", 0).show();
            return;
        }
        String c7 = a.a.c(this.phoneInput);
        if (c7.length() < 11) {
            Toast.makeText(this, "请输入正确手机号", 0).show();
            return;
        }
        TireBrand selectedBrand = selectedBrand();
        String c8 = a.a.c(this.captchaInput);
        if (selectedBrand == TireBrand.EXEED && c8.isEmpty()) {
            Toast.makeText(this, "请输入图形验证码", 0).show();
            refreshCaptcha(true);
        } else {
            this.sendCodeButton.setEnabled(false);
            startSmsCountdown();
            this.executor.execute(new b6(this, selectedBrand, c7, c8, 1));
        }
    }

    private void setTireCell(TextView textView, TextView textView2, int i7, int i8) {
        String str;
        String str2;
        if (i7 > 0) {
            str = i7 + " kPa";
        } else {
            str = "--";
        }
        textView.setText(str);
        if (i8 == Integer.MIN_VALUE || i8 <= -100 || i8 >= 254) {
            str2 = "-- ℃";
        } else {
            str2 = i8 + " ℃";
        }
        textView2.setText(str2);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: showCaptcha, reason: merged with bridge method [inline-methods] */
    public void lambda$refreshCaptcha$7(String str, boolean z6) {
        if (str == null || str.isEmpty()) {
            this.captchaImage.setImageDrawable(null);
            this.captchaHint.setText("获取失败，点击重试");
            this.captchaHint.setVisibility(0);
            return;
        }
        int indexOf = str.indexOf("base64,");
        if (indexOf >= 0) {
            str = str.substring(indexOf + 7);
        }
        try {
            byte[] decode = Base64.decode(str, 0);
            this.captchaImage.setImageBitmap(BitmapFactory.decodeByteArray(decode, 0, decode.length));
            this.captchaHint.setVisibility(8);
            if (z6) {
                focusCaptchaInput();
            }
        } catch (Exception unused) {
            this.captchaImage.setImageDrawable(null);
            this.captchaHint.setText("解析失败，点击重试");
            this.captchaHint.setVisibility(0);
        }
    }

    private void startSmsCountdown() {
        this.smsCountdown = 60;
        this.sendCodeButton.setEnabled(false);
        this.main.removeCallbacks(this.smsCountdownTick);
        this.main.post(this.smsCountdownTick);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void stopSmsCountdown() {
        this.main.removeCallbacks(this.smsCountdownTick);
        this.smsCountdown = 0;
        this.sendCodeButton.setEnabled(true);
        this.sendCodeButton.setText("发送短信验证码");
    }

    private View tireCell(String str, TextView textView, TextView textView2) {
        LinearLayout linearLayout = new LinearLayout(this);
        linearLayout.setOrientation(1);
        GradientDrawable gradientDrawable = new GradientDrawable();
        gradientDrawable.setColor(this.colorCard);
        gradientDrawable.setCornerRadius(dp(10));
        gradientDrawable.setStroke(dp(1), this.colorCardStroke);
        linearLayout.setBackground(gradientDrawable);
        linearLayout.setPadding(dp(14), dp(12), dp(14), dp(12));
        linearLayout.addView(makeText(str, 12, this.colorHint, false));
        textView.setTextSize(20.0f);
        textView.setTextColor(this.colorText);
        textView.setTypeface(null, 1);
        textView.setText("--");
        linearLayout.addView(textView);
        textView2.setTextSize(12.0f);
        textView2.setTextColor(this.colorHint);
        textView2.setText("-- ℃");
        linearLayout.addView(textView2);
        return linearLayout;
    }

    private void updateCaptchaVisible() {
        boolean z6 = selectedBrand() == TireBrand.EXEED;
        this.captchaRow.setVisibility(z6 ? 0 : 8);
        if (!z6) {
            this.captchaInput.setText(s5.h0.FRAGMENT_ENCODE_SET);
            this.captchaImage.setImageDrawable(null);
            this.captchaHint.setText("点击获取");
            this.captchaHint.setVisibility(0);
            return;
        }
        if (this.engine == null || this.layoutLogin.getVisibility() != 0 || this.captchaImage.getDrawable() != null || this.phoneInput.getText().toString().trim().length() < 11) {
            return;
        }
        refreshCaptcha(false);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void updateNextFetchText() {
        if (this.nextFetchText.getVisibility() != 0) {
            return;
        }
        if (this.fetching) {
            this.nextFetchText.setText("正在拉取车况…");
            return;
        }
        long j7 = getApplicationContext().getSharedPreferences("tire_account", 0).getLong("tire_last_fetch_at", 0L);
        long d7 = o4.z.d(this) * t1.f.T_MS_MINUTE;
        if (j7 <= 0) {
            this.nextFetchText.setText("等待首次自动拉取…");
            return;
        }
        long currentTimeMillis = (j7 + d7) - System.currentTimeMillis();
        if (currentTimeMillis <= 0) {
            this.nextFetchText.setText("已到间隔，等待后台拉取…");
        } else {
            long j8 = (currentTimeMillis + 999) / 1000;
            this.nextFetchText.setText(String.format(Locale.US, "距下次自动拉取还有 %02d:%02d", Long.valueOf(j8 / 60), Long.valueOf(j8 % 60)));
        }
    }

    @Override // android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.colorBg = getColor(g4.m.tire_bg);
        this.colorCard = getColor(g4.m.tire_card_bg);
        this.colorCardStroke = getColor(g4.m.tire_card_stroke);
        this.colorText = getColor(g4.m.tire_text_primary);
        this.colorHint = getColor(g4.m.tire_text_secondary);
        this.colorPrimary = getColor(g4.m.tire_accent);
        this.colorOnPrimary = getColor(g4.m.tire_on_accent);
        hideSystemUI();
        setContentView(buildContentView());
        restoreFormState(bundle);
        o4.u.f8031e.c(this, new AnonymousClass3());
        refreshLoginUi();
    }

    @Override // android.app.Activity
    public void onDestroy() {
        this.main.removeCallbacksAndMessages(null);
        this.executor.shutdownNow();
        super.onDestroy();
    }

    @Override // android.app.Activity
    public void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        autoFetchIfNeeded();
    }

    @Override // android.app.Activity
    public void onPause() {
        this.main.removeCallbacks(this.countdownTick);
        super.onPause();
    }

    @Override // android.app.Activity
    public void onResume() {
        super.onResume();
        this.main.removeCallbacks(this.countdownTick);
        this.main.post(this.countdownTick);
    }

    @Override // android.app.Activity
    public void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        EditText editText = this.phoneInput;
        if (editText != null) {
            bundle.putString("tire_phone", editText.getText().toString());
            bundle.putString("tire_captcha", this.captchaInput.getText().toString());
            bundle.putString("tire_code", this.codeInput.getText().toString());
        }
    }

    @Override // android.app.Activity, android.view.Window.Callback
    public void onWindowFocusChanged(boolean z6) {
        super.onWindowFocusChanged(z6);
        if (z6) {
            hideSystemUI();
        }
    }
}
