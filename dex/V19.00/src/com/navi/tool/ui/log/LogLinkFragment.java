package com.navi.tool.ui.log;

import android.R;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.media.ToneGenerator;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.TextView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.g2;
import androidx.recyclerview.widget.h1;
import androidx.recyclerview.widget.j2;
import androidx.recyclerview.widget.o1;
import androidx.recyclerview.widget.s1;
import com.google.android.material.switchmaterial.SwitchMaterial;
import com.navi.tool.model.AppInfo;
import com.navi.tool.model.LogAppProfile;
import com.navi.tool.model.LogAssistantProfile;
import com.navi.tool.model.LogMediaProfile;
import com.navi.tool.service.LogLinkageService;
import com.navi.tool.ui.adapter.AppAdapter;
import com.navi.tool.ui.common.DialogListDividers;
import com.navi.tool.utils.DialogImeNavBarHelper;
import com.navi.tool.utils.LogAppProfileStorage;
import com.navi.tool.utils.LogAssistantProfileStorage;
import com.navi.tool.utils.LogMediaProfileStorage;
import com.navi.tool.utils.NativeBridge;
import com.navi.tool.utils.SharedPreferencesUtils;
import com.navi.tool.utils.StorageMigrationHelper;
import com.navi.tool.utils.ToastHelper;
import java.io.File;
import java.io.FileFilter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import s5.h0;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public class LogLinkFragment extends androidx.fragment.app.z {
    private View appLinkContainer;
    private View appLinkManagerContainer;
    private View assistantLinkContainer;
    private View assistantLinkManagerContainer;
    private LogAssistantProfileAdapter assistantProfileAdapter;
    private Button buttonAddAppProfile;
    private Button buttonAddAssistantProfile;
    private Button buttonAddMediaProfile;
    private Button buttonDeleteSelectedAppProfile;
    private Button buttonDeleteSelectedAssistantProfile;
    private Button buttonDeleteSelectedMediaProfile;
    private Button buttonMultiSelectAppProfile;
    private Button buttonMultiSelectAssistantProfile;
    private Button buttonMultiSelectMediaProfile;
    private Button buttonSelectAllAppProfile;
    private Button buttonSelectAllAssistantProfile;
    private Button buttonSelectAllMediaProfile;
    private SwitchMaterial logLinkSwitch;
    private View mediaLinkContainer;
    private View mediaLinkManagerContainer;
    private LogMediaProfileAdapter mediaProfileAdapter;
    private View permissionStatusContainer;
    private LogProfileAdapter profileAdapter;
    private RecyclerView recyclerAppProfiles;
    private RecyclerView recyclerAssistantProfiles;
    private RecyclerView recyclerMediaProfiles;
    private SharedPreferences sharedPreferences;
    private SwitchMaterial switchAppLink;
    private SwitchMaterial switchAssistantLink;
    private SwitchMaterial switchMediaLink;
    private TextView textAppProfileEmpty;
    private TextView textAssistantProfileEmpty;
    private TextView textLogStatus;
    private TextView textMediaProfileEmpty;
    private TextView textStorageStatus;
    private final List<LogAppProfile> appProfiles = new ArrayList();
    private final List<LogMediaProfile> mediaProfiles = new ArrayList();
    private final List<LogAssistantProfile> assistantProfiles = new ArrayList();

    public static class MediaFileItem {
        String name;
        String path;
        boolean selected;

        private MediaFileItem() {
        }
    }

    public static class MediaViewHolder extends j2 {
        TextView nameView;
        SwitchMaterial switchView;

        public MediaViewHolder(View view) {
            super(view);
            this.nameView = (TextView) view.findViewById(g4.p.text_media_name);
            this.switchView = (SwitchMaterial) view.findViewById(g4.p.media_switch);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static /* synthetic */ void lambda$bind$0(List list, MediaFileItem mediaFileItem, h1 h1Var, View view) {
            Iterator it = list.iterator();
            while (it.hasNext()) {
                MediaFileItem mediaFileItem2 = (MediaFileItem) it.next();
                mediaFileItem2.selected = mediaFileItem2 == mediaFileItem;
            }
            h1Var.notifyDataSetChanged();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static /* synthetic */ void lambda$bind$1(List list, MediaFileItem mediaFileItem, h1 h1Var, CompoundButton compoundButton, boolean z6) {
            if (compoundButton.isPressed()) {
                Iterator it = list.iterator();
                while (it.hasNext()) {
                    MediaFileItem mediaFileItem2 = (MediaFileItem) it.next();
                    mediaFileItem2.selected = mediaFileItem2 == mediaFileItem && z6;
                }
                h1Var.notifyDataSetChanged();
            }
        }

        public void bind(MediaFileItem mediaFileItem, List<MediaFileItem> list, h1 h1Var) {
            this.nameView.setText(mediaFileItem.name);
            this.switchView.setOnCheckedChangeListener(null);
            this.switchView.setChecked(mediaFileItem.selected);
            int i7 = 1;
            this.itemView.setOnClickListener(new c(list, mediaFileItem, h1Var, i7));
            this.switchView.setOnCheckedChangeListener(new a(list, mediaFileItem, h1Var, i7));
        }
    }

    public static class ProfileDividerDecoration extends o1 {
        private final Drawable divider;
        private final int height;

        public ProfileDividerDecoration(Context context) {
            int i7 = g4.m.divider_dark;
            Object obj = g0.g.f6423a;
            int a7 = g0.d.a(context, i7);
            this.height = (int) ((context.getResources().getDisplayMetrics().density * 1.0f) + 0.5f);
            this.divider = new ColorDrawable(a7);
        }

        @Override // androidx.recyclerview.widget.o1
        public void getItemOffsets(Rect rect, View view, RecyclerView recyclerView, g2 g2Var) {
            h1 adapter = recyclerView.getAdapter();
            if (adapter == null) {
                rect.set(0, 0, 0, 0);
                return;
            }
            int I = RecyclerView.I(view);
            int itemCount = adapter.getItemCount();
            if (itemCount <= 1 || I == -1 || I >= itemCount - 1) {
                rect.set(0, 0, 0, 0);
            } else {
                rect.set(0, 0, 0, this.height);
            }
        }

        @Override // androidx.recyclerview.widget.o1
        public void onDraw(Canvas canvas, RecyclerView recyclerView, g2 g2Var) {
            int itemCount;
            h1 adapter = recyclerView.getAdapter();
            if (adapter != null && (itemCount = adapter.getItemCount()) > 1) {
                int paddingLeft = recyclerView.getPaddingLeft();
                int width = recyclerView.getWidth() - recyclerView.getPaddingRight();
                int childCount = recyclerView.getChildCount();
                for (int i7 = 0; i7 < childCount; i7++) {
                    View childAt = recyclerView.getChildAt(i7);
                    int I = RecyclerView.I(childAt);
                    if (I != -1 && I < itemCount - 1) {
                        int bottom = childAt.getBottom() + ((ViewGroup.MarginLayoutParams) ((s1) childAt.getLayoutParams())).bottomMargin;
                        this.divider.setBounds(paddingLeft, bottom, width, this.height + bottom);
                        this.divider.draw(canvas);
                    }
                }
            }
        }
    }

    private void copyAppProfile(LogAppProfile logAppProfile) {
        String str;
        if (logAppProfile == null || logAppProfile.getName() == null || logAppProfile.getName().trim().isEmpty()) {
            ToastHelper.show(requireContext(), "无法复制: 配置名称无效");
            return;
        }
        File appProfileDir = LogAppProfileStorage.getAppProfileDir(requireContext());
        String trim = logAppProfile.getName().trim();
        int i7 = 2;
        while (true) {
            if (i7 > 5) {
                str = null;
                break;
            }
            str = trim + i7;
            if (!new File(appProfileDir, a.a.g(str, ".xml")).exists()) {
                break;
            } else {
                i7++;
            }
        }
        if (str == null) {
            ToastHelper.show(requireContext(), "已达到最大复制数量");
            return;
        }
        String saveProfile = LogAppProfileStorage.saveProfile(requireContext(), new LogAppProfile(str, logAppProfile.getTag(), logAppProfile.getPattern(), logAppProfile.getAction(), logAppProfile.getPackageName(), logAppProfile.isEnabled(), logAppProfile.getConditionType(), logAppProfile.getConditionVariable(), logAppProfile.getConditionOperator(), logAppProfile.getConditionValue(), logAppProfile.getVariableName(), logAppProfile.getVariableOp(), logAppProfile.getVariableValue(), logAppProfile.getDelaySeconds(), logAppProfile.getCooldownSeconds(), logAppProfile.getComboSeconds(), logAppProfile.getComboCount()));
        if (saveProfile == null) {
            ToastHelper.show(requireContext(), "已复制配置: " + trim + " -> " + str);
        } else {
            ToastHelper.show(requireContext(), "复制失败: ".concat(saveProfile));
        }
        refreshAppProfiles();
        startLogMonitorServiceIfNeeded();
        sendReloadProfilesCommand();
    }

    private boolean copyFile(File file, File file2) {
        FileOutputStream fileOutputStream;
        FileInputStream fileInputStream = null;
        try {
            FileInputStream fileInputStream2 = new FileInputStream(file);
            try {
                fileOutputStream = new FileOutputStream(file2);
                try {
                    byte[] bArr = new byte[8192];
                    while (true) {
                        int read = fileInputStream2.read(bArr);
                        if (read <= 0) {
                            break;
                        }
                        fileOutputStream.write(bArr, 0, read);
                    }
                    fileOutputStream.flush();
                    try {
                        fileInputStream2.close();
                    } catch (Exception unused) {
                    }
                    try {
                        fileOutputStream.close();
                        return true;
                    } catch (Exception unused2) {
                        return true;
                    }
                } catch (Exception unused3) {
                    fileInputStream = fileInputStream2;
                    if (fileInputStream != null) {
                        try {
                            fileInputStream.close();
                        } catch (Exception unused4) {
                        }
                    }
                    if (fileOutputStream != null) {
                        try {
                            fileOutputStream.close();
                        } catch (Exception unused5) {
                        }
                    }
                    return false;
                } catch (Throwable th) {
                    th = th;
                    fileInputStream = fileInputStream2;
                    if (fileInputStream != null) {
                        try {
                            fileInputStream.close();
                        } catch (Exception unused6) {
                        }
                    }
                    if (fileOutputStream == null) {
                        throw th;
                    }
                    try {
                        fileOutputStream.close();
                        throw th;
                    } catch (Exception unused7) {
                        throw th;
                    }
                }
            } catch (Exception unused8) {
                fileOutputStream = null;
            } catch (Throwable th2) {
                th = th2;
                fileOutputStream = null;
            }
        } catch (Exception unused9) {
            fileOutputStream = null;
        } catch (Throwable th3) {
            th = th3;
            fileOutputStream = null;
        }
    }

    private void copyMediaProfile(LogMediaProfile logMediaProfile) {
        String str;
        if (logMediaProfile == null || logMediaProfile.getName() == null || logMediaProfile.getName().trim().isEmpty()) {
            ToastHelper.show(requireContext(), "无法复制: 配置名称无效");
            return;
        }
        File mediaProfileDir = LogMediaProfileStorage.getMediaProfileDir(requireContext());
        String trim = logMediaProfile.getName().trim();
        int i7 = 2;
        while (true) {
            if (i7 > 5) {
                str = null;
                break;
            }
            str = trim + i7;
            if (!new File(mediaProfileDir, a.a.g(str, ".xml")).exists()) {
                break;
            } else {
                i7++;
            }
        }
        if (str == null) {
            ToastHelper.show(requireContext(), "已达到最大复制数量");
            return;
        }
        String saveProfile = LogMediaProfileStorage.saveProfile(requireContext(), new LogMediaProfile(str, logMediaProfile.getTag(), logMediaProfile.getPattern(), logMediaProfile.getMediaPath(), logMediaProfile.getAction(), logMediaProfile.isEnabled(), logMediaProfile.getPlayMode(), logMediaProfile.getReleasePattern(), logMediaProfile.getConditionType(), logMediaProfile.getConditionVariable(), logMediaProfile.getConditionOperator(), logMediaProfile.getConditionValue(), logMediaProfile.getVariableName(), logMediaProfile.getVariableOp(), logMediaProfile.getVariableValue(), logMediaProfile.getDelaySeconds(), logMediaProfile.getCooldownSeconds(), logMediaProfile.getComboSeconds(), logMediaProfile.getComboCount()));
        if (saveProfile == null) {
            ToastHelper.show(requireContext(), "已复制配置: " + trim + " -> " + str);
        } else {
            ToastHelper.show(requireContext(), "复制失败: ".concat(saveProfile));
        }
        refreshMediaProfiles();
        startLogMonitorServiceIfNeeded();
        sendReloadProfilesCommand();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Code restructure failed: missing block: B:18:0x003e, code lost:
    
        if (r4 == false) goto L23;
     */
    /* JADX WARN: Code restructure failed: missing block: B:19:0x0040, code lost:
    
        com.navi.tool.utils.ToastHelper.show(requireContext(), "已创建目录: " + r1.getAbsolutePath());
     */
    /* JADX WARN: Code restructure failed: missing block: B:20:?, code lost:
    
        return;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void createNavitoolMp3FolderIfNeeded() {
        /*
            r6 = this;
            java.lang.String r0 = "已创建目录: "
            java.io.File r1 = android.os.Environment.getExternalStorageDirectory()     // Catch: java.lang.Throwable -> L2b
            java.io.File r2 = new java.io.File     // Catch: java.lang.Throwable -> L2b
            java.lang.String r3 = "navitool"
            r2.<init>(r1, r3)     // Catch: java.lang.Throwable -> L2b
            java.io.File r1 = new java.io.File     // Catch: java.lang.Throwable -> L2b
            java.lang.String r3 = "mp3"
            r1.<init>(r2, r3)     // Catch: java.lang.Throwable -> L2b
            boolean r3 = r1.exists()     // Catch: java.lang.Throwable -> L2b
            if (r3 != 0) goto L7b
            boolean r3 = r2.exists()     // Catch: java.lang.Throwable -> L2b
            r4 = 1
            r5 = 0
            if (r3 != 0) goto L2d
            boolean r2 = r2.mkdirs()     // Catch: java.lang.Throwable -> L2b
            if (r2 == 0) goto L29
            goto L2d
        L29:
            r2 = r5
            goto L2e
        L2b:
            r0 = move-exception
            goto L62
        L2d:
            r2 = r4
        L2e:
            boolean r3 = r1.exists()     // Catch: java.lang.Throwable -> L2b
            if (r3 != 0) goto L3c
            boolean r3 = r1.mkdirs()     // Catch: java.lang.Throwable -> L2b
            if (r3 == 0) goto L3b
            goto L3c
        L3b:
            r4 = r5
        L3c:
            if (r2 == 0) goto L58
            if (r4 == 0) goto L58
            android.content.Context r2 = r6.requireContext()     // Catch: java.lang.Throwable -> L2b
            java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> L2b
            r3.<init>(r0)     // Catch: java.lang.Throwable -> L2b
            java.lang.String r0 = r1.getAbsolutePath()     // Catch: java.lang.Throwable -> L2b
            r3.append(r0)     // Catch: java.lang.Throwable -> L2b
            java.lang.String r0 = r3.toString()     // Catch: java.lang.Throwable -> L2b
            com.navi.tool.utils.ToastHelper.show(r2, r0)     // Catch: java.lang.Throwable -> L2b
            goto L7b
        L58:
            android.content.Context r0 = r6.requireContext()     // Catch: java.lang.Throwable -> L2b
            java.lang.String r1 = "创建目录失败，请检查权限"
            com.navi.tool.utils.ToastHelper.show(r0, r1)     // Catch: java.lang.Throwable -> L2b
            goto L7b
        L62:
            android.content.Context r1 = r6.requireContext()
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            java.lang.String r3 = "创建目录失败: "
            r2.<init>(r3)
            java.lang.String r0 = r0.getMessage()
            r2.append(r0)
            java.lang.String r0 = r2.toString()
            com.navi.tool.utils.ToastHelper.show(r1, r0)
        L7b:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.navi.tool.ui.log.LogLinkFragment.createNavitoolMp3FolderIfNeeded():void");
    }

    private void deleteSelectedAppProfiles() {
        LogProfileAdapter logProfileAdapter = this.profileAdapter;
        if (logProfileAdapter == null) {
            return;
        }
        List<String> selectedProfileNames = logProfileAdapter.getSelectedProfileNames();
        if (selectedProfileNames.isEmpty()) {
            ToastHelper.show(requireContext(), "请先选择要删除的配置");
            return;
        }
        androidx.appcompat.app.n nVar = new androidx.appcompat.app.n(requireContext());
        nVar.t("删除配置文件");
        nVar.m("确定删除选中的 " + selectedProfileNames.size() + " 个配置文件？");
        nVar.o("取消", new g4.g(10));
        nVar.r("删除", new m(this, selectedProfileNames, 2));
        nVar.v();
    }

    private void deleteSelectedAssistantProfiles() {
        LogAssistantProfileAdapter logAssistantProfileAdapter = this.assistantProfileAdapter;
        if (logAssistantProfileAdapter == null) {
            return;
        }
        List<String> selectedProfileNames = logAssistantProfileAdapter.getSelectedProfileNames();
        if (selectedProfileNames.isEmpty()) {
            ToastHelper.show(requireContext(), "请先选择要删除的配置");
            return;
        }
        androidx.appcompat.app.n nVar = new androidx.appcompat.app.n(requireContext());
        nVar.t("删除配置文件");
        nVar.m("确定删除选中的 " + selectedProfileNames.size() + " 个配置文件？");
        nVar.o("取消", new g4.g(5));
        nVar.r("删除", new m(this, selectedProfileNames, 0));
        nVar.v();
    }

    private void deleteSelectedMediaProfiles() {
        LogMediaProfileAdapter logMediaProfileAdapter = this.mediaProfileAdapter;
        if (logMediaProfileAdapter == null) {
            return;
        }
        List<String> selectedProfileNames = logMediaProfileAdapter.getSelectedProfileNames();
        if (selectedProfileNames.isEmpty()) {
            ToastHelper.show(requireContext(), "请先选择要删除的配置");
            return;
        }
        androidx.appcompat.app.n nVar = new androidx.appcompat.app.n(requireContext());
        nVar.t("删除配置文件");
        nVar.m("确定删除选中的 " + selectedProfileNames.size() + " 个配置文件？");
        nVar.o("取消", new g4.g(9));
        nVar.r("删除", new m(this, selectedProfileNames, 1));
        nVar.v();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Code restructure failed: missing block: B:23:0x0053, code lost:
    
        if (r0 == false) goto L29;
     */
    /* JADX WARN: Code restructure failed: missing block: B:24:0x0055, code lost:
    
        if (r4 != false) goto L36;
     */
    /* JADX WARN: Code restructure failed: missing block: B:25:?, code lost:
    
        return;
     */
    /* JADX WARN: Removed duplicated region for block: B:18:0x0049 A[Catch: all -> 0x0030, TryCatch #0 {all -> 0x0030, blocks: (B:2:0x0000, B:4:0x001f, B:6:0x0027, B:10:0x0033, B:12:0x0039, B:16:0x0043, B:18:0x0049, B:27:0x0057), top: B:1:0x0000 }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void ensureAppProfileDir() {
        /*
            r6 = this;
            java.io.File r0 = android.os.Environment.getExternalStorageDirectory()     // Catch: java.lang.Throwable -> L30
            java.io.File r1 = new java.io.File     // Catch: java.lang.Throwable -> L30
            java.lang.String r2 = "navitool"
            r1.<init>(r0, r2)     // Catch: java.lang.Throwable -> L30
            java.io.File r0 = new java.io.File     // Catch: java.lang.Throwable -> L30
            java.lang.String r2 = "profile"
            r0.<init>(r1, r2)     // Catch: java.lang.Throwable -> L30
            java.io.File r2 = new java.io.File     // Catch: java.lang.Throwable -> L30
            java.lang.String r3 = "app"
            r2.<init>(r0, r3)     // Catch: java.lang.Throwable -> L30
            boolean r3 = r2.exists()     // Catch: java.lang.Throwable -> L30
            if (r3 != 0) goto L7a
            boolean r3 = r1.exists()     // Catch: java.lang.Throwable -> L30
            r4 = 1
            r5 = 0
            if (r3 != 0) goto L32
            boolean r1 = r1.mkdirs()     // Catch: java.lang.Throwable -> L30
            if (r1 == 0) goto L2e
            goto L32
        L2e:
            r1 = r5
            goto L33
        L30:
            r0 = move-exception
            goto L61
        L32:
            r1 = r4
        L33:
            boolean r3 = r0.exists()     // Catch: java.lang.Throwable -> L30
            if (r3 != 0) goto L42
            boolean r0 = r0.mkdirs()     // Catch: java.lang.Throwable -> L30
            if (r0 == 0) goto L40
            goto L42
        L40:
            r0 = r5
            goto L43
        L42:
            r0 = r4
        L43:
            boolean r3 = r2.exists()     // Catch: java.lang.Throwable -> L30
            if (r3 != 0) goto L51
            boolean r2 = r2.mkdirs()     // Catch: java.lang.Throwable -> L30
            if (r2 == 0) goto L50
            goto L51
        L50:
            r4 = r5
        L51:
            if (r1 == 0) goto L57
            if (r0 == 0) goto L57
            if (r4 != 0) goto L7a
        L57:
            android.content.Context r0 = r6.requireContext()     // Catch: java.lang.Throwable -> L30
            java.lang.String r1 = "创建配置目录失败"
            com.navi.tool.utils.ToastHelper.show(r0, r1)     // Catch: java.lang.Throwable -> L30
            goto L7a
        L61:
            android.content.Context r1 = r6.requireContext()
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            java.lang.String r3 = "创建配置目录失败: "
            r2.<init>(r3)
            java.lang.String r0 = r0.getMessage()
            r2.append(r0)
            java.lang.String r0 = r2.toString()
            com.navi.tool.utils.ToastHelper.show(r1, r0)
        L7a:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.navi.tool.ui.log.LogLinkFragment.ensureAppProfileDir():void");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void ensureAssistantProfileDir() {
        try {
            LogAssistantProfileStorage.getAssistantProfileDir(requireContext());
        } catch (Throwable th) {
            ToastHelper.show(requireContext(), "创建助手配置目录失败: " + th.getMessage());
        }
    }

    private static String ensureDollarPrefix(String str) {
        if (str == null) {
            return h0.FRAGMENT_ENCODE_SET;
        }
        String trim = str.trim();
        return trim.isEmpty() ? h0.FRAGMENT_ENCODE_SET : trim.charAt(0) == '$' ? trim : "$".concat(trim);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Code restructure failed: missing block: B:23:0x0053, code lost:
    
        if (r0 == false) goto L29;
     */
    /* JADX WARN: Code restructure failed: missing block: B:24:0x0055, code lost:
    
        if (r4 != false) goto L36;
     */
    /* JADX WARN: Code restructure failed: missing block: B:25:?, code lost:
    
        return;
     */
    /* JADX WARN: Removed duplicated region for block: B:18:0x0049 A[Catch: all -> 0x0030, TryCatch #0 {all -> 0x0030, blocks: (B:2:0x0000, B:4:0x001f, B:6:0x0027, B:10:0x0033, B:12:0x0039, B:16:0x0043, B:18:0x0049, B:27:0x0057), top: B:1:0x0000 }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void ensureMediaProfileDir() {
        /*
            r6 = this;
            java.io.File r0 = android.os.Environment.getExternalStorageDirectory()     // Catch: java.lang.Throwable -> L30
            java.io.File r1 = new java.io.File     // Catch: java.lang.Throwable -> L30
            java.lang.String r2 = "navitool"
            r1.<init>(r0, r2)     // Catch: java.lang.Throwable -> L30
            java.io.File r0 = new java.io.File     // Catch: java.lang.Throwable -> L30
            java.lang.String r2 = "profile"
            r0.<init>(r1, r2)     // Catch: java.lang.Throwable -> L30
            java.io.File r2 = new java.io.File     // Catch: java.lang.Throwable -> L30
            java.lang.String r3 = "media"
            r2.<init>(r0, r3)     // Catch: java.lang.Throwable -> L30
            boolean r3 = r2.exists()     // Catch: java.lang.Throwable -> L30
            if (r3 != 0) goto L7a
            boolean r3 = r1.exists()     // Catch: java.lang.Throwable -> L30
            r4 = 1
            r5 = 0
            if (r3 != 0) goto L32
            boolean r1 = r1.mkdirs()     // Catch: java.lang.Throwable -> L30
            if (r1 == 0) goto L2e
            goto L32
        L2e:
            r1 = r5
            goto L33
        L30:
            r0 = move-exception
            goto L61
        L32:
            r1 = r4
        L33:
            boolean r3 = r0.exists()     // Catch: java.lang.Throwable -> L30
            if (r3 != 0) goto L42
            boolean r0 = r0.mkdirs()     // Catch: java.lang.Throwable -> L30
            if (r0 == 0) goto L40
            goto L42
        L40:
            r0 = r5
            goto L43
        L42:
            r0 = r4
        L43:
            boolean r3 = r2.exists()     // Catch: java.lang.Throwable -> L30
            if (r3 != 0) goto L51
            boolean r2 = r2.mkdirs()     // Catch: java.lang.Throwable -> L30
            if (r2 == 0) goto L50
            goto L51
        L50:
            r4 = r5
        L51:
            if (r1 == 0) goto L57
            if (r0 == 0) goto L57
            if (r4 != 0) goto L7a
        L57:
            android.content.Context r0 = r6.requireContext()     // Catch: java.lang.Throwable -> L30
            java.lang.String r1 = "创建媒体配置目录失败"
            com.navi.tool.utils.ToastHelper.show(r0, r1)     // Catch: java.lang.Throwable -> L30
            goto L7a
        L61:
            android.content.Context r1 = r6.requireContext()
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            java.lang.String r3 = "创建媒体配置目录失败: "
            r2.<init>(r3)
            java.lang.String r0 = r0.getMessage()
            r2.append(r0)
            java.lang.String r0 = r2.toString()
            com.navi.tool.utils.ToastHelper.show(r1, r0)
        L7a:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.navi.tool.ui.log.LogLinkFragment.ensureMediaProfileDir():void");
    }

    private List<AppInfo> getAllLaunchableApps() {
        String str;
        Context context = getContext();
        if (context == null) {
            return Collections.emptyList();
        }
        ArrayList arrayList = new ArrayList();
        PackageManager packageManager = context.getPackageManager();
        Intent intent = new Intent("android.intent.action.MAIN");
        intent.addCategory("android.intent.category.LAUNCHER");
        List<ResolveInfo> queryIntentActivities = packageManager.queryIntentActivities(intent, 0);
        boolean z6 = SharedPreferencesUtils.getAppPrefs(context).getBoolean("file_assistant_enabled", false);
        Iterator<ResolveInfo> it = queryIntentActivities.iterator();
        while (it.hasNext()) {
            ActivityInfo activityInfo = it.next().activityInfo;
            String str2 = activityInfo.packageName;
            String str3 = activityInfo.name;
            if (str2.equals(context.getPackageName()) && str3.endsWith("DesktopAssistantStubActivity")) {
                arrayList.add(new AppInfo(str2.concat(".desktop_stub"), "桌面助手", false));
            } else if (!str2.equals(context.getPackageName()) || !str3.endsWith("FileAssistantActivity")) {
                try {
                    str = packageManager.getApplicationLabel(packageManager.getApplicationInfo(str2, 0)).toString();
                } catch (Exception unused) {
                    str = str2;
                }
                a.a.x(str2, str, false, arrayList);
            } else if (z6) {
                arrayList.add(new AppInfo(str2.concat(".file_stub"), "文件助手", false));
            }
        }
        arrayList.sort(new m0.a(7));
        return arrayList;
    }

    private boolean hasReadLogsPermission() {
        return g0.g.a(requireContext(), "android.permission.READ_LOGS") == 0;
    }

    private boolean hasStorageAccess() {
        boolean isExternalStorageManager;
        if (Build.VERSION.SDK_INT < 30) {
            return g0.g.a(requireContext(), "android.permission.READ_EXTERNAL_STORAGE") == 0 && g0.g.a(requireContext(), "android.permission.WRITE_EXTERNAL_STORAGE") == 0;
        }
        isExternalStorageManager = Environment.isExternalStorageManager();
        return isExternalStorageManager;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void initAppProfilesUi() {
        RecyclerView recyclerView = this.recyclerAppProfiles;
        if (recyclerView != null && recyclerView.getLayoutManager() == null) {
            RecyclerView recyclerView2 = this.recyclerAppProfiles;
            getContext();
            recyclerView2.setLayoutManager(new LinearLayoutManager(1));
            if (this.recyclerAppProfiles.getItemDecorationCount() == 0) {
                this.recyclerAppProfiles.g(new ProfileDividerDecoration(requireContext()));
            }
        }
        if (this.profileAdapter == null) {
            LogProfileAdapter logProfileAdapter = new LogProfileAdapter(new p(this), new p(this), new p(this));
            this.profileAdapter = logProfileAdapter;
            this.recyclerAppProfiles.setAdapter(logProfileAdapter);
        }
        Button button = this.buttonAddAppProfile;
        if (button != null) {
            button.setOnClickListener(new x(this, 8));
        }
        Button button2 = this.buttonMultiSelectAppProfile;
        if (button2 != null) {
            button2.setOnClickListener(new x(this, 9));
        }
        Button button3 = this.buttonDeleteSelectedAppProfile;
        if (button3 != null) {
            button3.setOnClickListener(new x(this, 10));
        }
        Button button4 = this.buttonSelectAllAppProfile;
        if (button4 != null) {
            button4.setOnClickListener(new x(this, 11));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void initAssistantProfilesUi() {
        RecyclerView recyclerView = this.recyclerAssistantProfiles;
        if (recyclerView != null && recyclerView.getLayoutManager() == null) {
            RecyclerView recyclerView2 = this.recyclerAssistantProfiles;
            getContext();
            recyclerView2.setLayoutManager(new LinearLayoutManager(1));
            if (this.recyclerAssistantProfiles.getItemDecorationCount() == 0) {
                this.recyclerAssistantProfiles.g(new ProfileDividerDecoration(requireContext()));
            }
        }
        if (this.assistantProfileAdapter == null) {
            LogAssistantProfileAdapter logAssistantProfileAdapter = new LogAssistantProfileAdapter(new p(this), new p(this), new p(this));
            this.assistantProfileAdapter = logAssistantProfileAdapter;
            this.recyclerAssistantProfiles.setAdapter(logAssistantProfileAdapter);
        }
        Button button = this.buttonAddAssistantProfile;
        if (button != null) {
            button.setOnClickListener(new x(this, 4));
        }
        Button button2 = this.buttonMultiSelectAssistantProfile;
        if (button2 != null) {
            button2.setOnClickListener(new x(this, 5));
        }
        Button button3 = this.buttonDeleteSelectedAssistantProfile;
        if (button3 != null) {
            button3.setOnClickListener(new x(this, 6));
        }
        Button button4 = this.buttonSelectAllAssistantProfile;
        if (button4 != null) {
            button4.setOnClickListener(new x(this, 7));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void initMediaProfilesUi() {
        RecyclerView recyclerView = this.recyclerMediaProfiles;
        int i7 = 1;
        if (recyclerView != null && recyclerView.getLayoutManager() == null) {
            RecyclerView recyclerView2 = this.recyclerMediaProfiles;
            getContext();
            recyclerView2.setLayoutManager(new LinearLayoutManager(1));
            if (this.recyclerMediaProfiles.getItemDecorationCount() == 0) {
                this.recyclerMediaProfiles.g(new ProfileDividerDecoration(requireContext()));
            }
        }
        if (this.mediaProfileAdapter == null) {
            LogMediaProfileAdapter logMediaProfileAdapter = new LogMediaProfileAdapter(new p(this), new p(this), new p(this));
            this.mediaProfileAdapter = logMediaProfileAdapter;
            this.recyclerMediaProfiles.setAdapter(logMediaProfileAdapter);
        }
        Button button = this.buttonAddMediaProfile;
        if (button != null) {
            button.setOnClickListener(new x(this, 0));
        }
        Button button2 = this.buttonMultiSelectMediaProfile;
        if (button2 != null) {
            button2.setOnClickListener(new x(this, i7));
        }
        Button button3 = this.buttonDeleteSelectedMediaProfile;
        if (button3 != null) {
            button3.setOnClickListener(new x(this, 2));
        }
        Button button4 = this.buttonSelectAllMediaProfile;
        if (button4 != null) {
            button4.setOnClickListener(new x(this, 3));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ void lambda$deleteSelectedAppProfiles$31(DialogInterface dialogInterface, int i7) {
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$deleteSelectedAppProfiles$32(List list, DialogInterface dialogInterface, int i7) {
        Iterator it = list.iterator();
        int i8 = 0;
        while (it.hasNext()) {
            if (LogAppProfileStorage.deleteProfile(requireContext(), (String) it.next())) {
                i8++;
            }
        }
        ToastHelper.show(requireContext(), "已删除 " + i8 + " 个配置");
        this.profileAdapter.setSelectionMode(false);
        Button button = this.buttonMultiSelectAppProfile;
        if (button != null) {
            button.setText("多选");
        }
        Button button2 = this.buttonDeleteSelectedAppProfile;
        if (button2 != null) {
            button2.setVisibility(8);
        }
        Button button3 = this.buttonSelectAllAppProfile;
        if (button3 != null) {
            button3.setVisibility(8);
        }
        refreshAppProfiles();
        startLogMonitorServiceIfNeeded();
        sendReloadProfilesCommand();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ void lambda$deleteSelectedAssistantProfiles$35(DialogInterface dialogInterface, int i7) {
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$deleteSelectedAssistantProfiles$36(List list, DialogInterface dialogInterface, int i7) {
        Iterator it = list.iterator();
        int i8 = 0;
        while (it.hasNext()) {
            if (LogAssistantProfileStorage.deleteProfile(requireContext(), (String) it.next())) {
                i8++;
            }
        }
        ToastHelper.show(requireContext(), "已删除 " + i8 + " 个配置");
        this.assistantProfileAdapter.setSelectionMode(false);
        Button button = this.buttonMultiSelectAssistantProfile;
        if (button != null) {
            button.setText("多选");
        }
        Button button2 = this.buttonDeleteSelectedAssistantProfile;
        if (button2 != null) {
            button2.setVisibility(8);
        }
        Button button3 = this.buttonSelectAllAssistantProfile;
        if (button3 != null) {
            button3.setVisibility(8);
        }
        Button button4 = this.buttonSelectAllAssistantProfile;
        if (button4 != null) {
            button4.setVisibility(8);
        }
        refreshAssistantProfiles();
        startLogMonitorServiceIfNeeded();
        sendReloadProfilesCommand();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ void lambda$deleteSelectedMediaProfiles$33(DialogInterface dialogInterface, int i7) {
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$deleteSelectedMediaProfiles$34(List list, DialogInterface dialogInterface, int i7) {
        Iterator it = list.iterator();
        int i8 = 0;
        while (it.hasNext()) {
            if (LogMediaProfileStorage.deleteProfile(requireContext(), (String) it.next())) {
                i8++;
            }
        }
        ToastHelper.show(requireContext(), "已删除 " + i8 + " 个配置");
        this.mediaProfileAdapter.setSelectionMode(false);
        Button button = this.buttonMultiSelectMediaProfile;
        if (button != null) {
            button.setText("多选");
        }
        Button button2 = this.buttonDeleteSelectedMediaProfile;
        if (button2 != null) {
            button2.setVisibility(8);
        }
        Button button3 = this.buttonSelectAllMediaProfile;
        if (button3 != null) {
            button3.setVisibility(8);
        }
        Button button4 = this.buttonSelectAllMediaProfile;
        if (button4 != null) {
            button4.setVisibility(8);
        }
        refreshMediaProfiles();
        startLogMonitorServiceIfNeeded();
        sendReloadProfilesCommand();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ int lambda$getAllLaunchableApps$108(AppInfo appInfo, AppInfo appInfo2) {
        return appInfo.getAppName().compareToIgnoreCase(appInfo2.getAppName());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$initAppProfilesUi$10(View view) {
        toggleAppSelectionMode();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$initAppProfilesUi$11(View view) {
        deleteSelectedAppProfiles();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$initAppProfilesUi$12(View view) {
        selectAllAppProfiles();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$initAppProfilesUi$4(LogAppProfile logAppProfile) {
        logAppProfile.setEnabled(!logAppProfile.isEnabled());
        String saveProfile = LogAppProfileStorage.saveProfile(requireContext(), logAppProfile);
        if (saveProfile != null) {
            ToastHelper.show(requireContext(), "保存失败: ".concat(saveProfile));
        }
        refreshAppProfiles();
        startLogMonitorServiceIfNeeded();
        sendReloadProfilesCommand();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ void lambda$initAppProfilesUi$6(DialogInterface dialogInterface, int i7) {
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$initAppProfilesUi$7(LogAppProfile logAppProfile, DialogInterface dialogInterface, int i7) {
        if (!LogAppProfileStorage.deleteProfile(requireContext(), logAppProfile.getName())) {
            ToastHelper.show(requireContext(), "删除失败");
            return;
        }
        ToastHelper.show(requireContext(), "已删除配置: " + logAppProfile.getName());
        refreshAppProfiles();
        startLogMonitorServiceIfNeeded();
        sendReloadProfilesCommand();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$initAppProfilesUi$8(LogAppProfile logAppProfile) {
        androidx.appcompat.app.n nVar = new androidx.appcompat.app.n(requireContext());
        nVar.t("删除配置文件");
        nVar.m("确定删除 \"" + logAppProfile.getName() + "\" ？");
        nVar.o("取消", new g4.g(4));
        nVar.r("删除", new k(this, logAppProfile, 0));
        nVar.v();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$initAppProfilesUi$9(View view) {
        lambda$initAppProfilesUi$5(null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$initAssistantProfilesUi$22(LogAssistantProfile logAssistantProfile) {
        logAssistantProfile.setEnabled(!logAssistantProfile.isEnabled());
        String saveProfile = LogAssistantProfileStorage.saveProfile(requireContext(), logAssistantProfile);
        if (saveProfile != null) {
            ToastHelper.show(requireContext(), "保存失败: ".concat(saveProfile));
        }
        refreshAssistantProfiles();
        startLogMonitorServiceIfNeeded();
        sendReloadProfilesCommand();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ void lambda$initAssistantProfilesUi$24(DialogInterface dialogInterface, int i7) {
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$initAssistantProfilesUi$25(LogAssistantProfile logAssistantProfile, DialogInterface dialogInterface, int i7) {
        if (!LogAssistantProfileStorage.deleteProfile(requireContext(), logAssistantProfile.getName())) {
            ToastHelper.show(requireContext(), "删除失败");
            return;
        }
        ToastHelper.show(requireContext(), "已删除配置: " + logAssistantProfile.getName());
        refreshAssistantProfiles();
        startLogMonitorServiceIfNeeded();
        sendReloadProfilesCommand();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$initAssistantProfilesUi$26(LogAssistantProfile logAssistantProfile) {
        androidx.appcompat.app.n nVar = new androidx.appcompat.app.n(requireContext());
        nVar.t("删除配置文件");
        nVar.m("确定删除 \"" + logAssistantProfile.getName() + "\" ？");
        nVar.o("取消", new g4.g(3));
        nVar.r("删除", new i(this, logAssistantProfile, 0));
        nVar.v();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$initAssistantProfilesUi$27(View view) {
        lambda$initAssistantProfilesUi$23(null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$initAssistantProfilesUi$28(View view) {
        toggleAssistantSelectionMode();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$initAssistantProfilesUi$29(View view) {
        deleteSelectedAssistantProfiles();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$initAssistantProfilesUi$30(View view) {
        selectAllAssistantProfiles();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$initMediaProfilesUi$13(LogMediaProfile logMediaProfile) {
        logMediaProfile.setEnabled(!logMediaProfile.isEnabled());
        String saveProfile = LogMediaProfileStorage.saveProfile(requireContext(), logMediaProfile);
        if (saveProfile != null) {
            ToastHelper.show(requireContext(), "保存失败: ".concat(saveProfile));
        }
        refreshMediaProfiles();
        startLogMonitorServiceIfNeeded();
        sendReloadProfilesCommand();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ void lambda$initMediaProfilesUi$15(DialogInterface dialogInterface, int i7) {
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$initMediaProfilesUi$16(LogMediaProfile logMediaProfile, DialogInterface dialogInterface, int i7) {
        if (!LogMediaProfileStorage.deleteProfile(requireContext(), logMediaProfile.getName())) {
            ToastHelper.show(requireContext(), "删除失败");
            return;
        }
        ToastHelper.show(requireContext(), "已删除配置: " + logMediaProfile.getName());
        refreshMediaProfiles();
        startLogMonitorServiceIfNeeded();
        sendReloadProfilesCommand();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$initMediaProfilesUi$17(LogMediaProfile logMediaProfile) {
        androidx.appcompat.app.n nVar = new androidx.appcompat.app.n(requireContext());
        nVar.t("删除配置文件");
        nVar.m("确定删除 \"" + logMediaProfile.getName() + "\" ？");
        nVar.o("取消", new g4.g(11));
        nVar.r("删除", new t(this, logMediaProfile, 0));
        nVar.v();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$initMediaProfilesUi$18(View view) {
        lambda$initMediaProfilesUi$14(null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$initMediaProfilesUi$19(View view) {
        toggleMediaSelectionMode();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$initMediaProfilesUi$20(View view) {
        deleteSelectedMediaProfiles();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$initMediaProfilesUi$21(View view) {
        selectAllMediaProfiles();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$onCreateView$0(RadioGroup radioGroup, int i7) {
        SharedPreferencesUtils.setLogMediaChannel(requireContext(), i7 == g4.p.radio_media_channel_navigation ? "navigation" : i7 == g4.p.radio_media_channel_notification ? "notification" : "media");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$onCreateView$1(CompoundButton compoundButton, boolean z6) {
        a.a.m(this.sharedPreferences, "log_link_apps_switch_state", z6);
        View view = this.appLinkManagerContainer;
        if (view != null) {
            view.setVisibility(z6 ? 0 : 8);
        }
        if (z6) {
            ensureAppProfileDir();
            initAppProfilesUi();
            refreshAppProfiles();
            startLogMonitorServiceIfNeeded();
        }
        SwitchMaterial switchMaterial = this.logLinkSwitch;
        if (switchMaterial == null || !switchMaterial.isChecked()) {
            return;
        }
        sendReloadProfilesCommand();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$onCreateView$2(CompoundButton compoundButton, boolean z6) {
        a.a.m(this.sharedPreferences, "log_link_media_switch_state", z6);
        View view = this.mediaLinkManagerContainer;
        if (view != null) {
            view.setVisibility(z6 ? 0 : 8);
        }
        if (z6) {
            ensureMediaProfileDir();
            initMediaProfilesUi();
            refreshMediaProfiles();
            startLogMonitorServiceIfNeeded();
        }
        SwitchMaterial switchMaterial = this.logLinkSwitch;
        if (switchMaterial == null || !switchMaterial.isChecked()) {
            return;
        }
        sendReloadProfilesCommand();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$onCreateView$3(CompoundButton compoundButton, boolean z6) {
        SharedPreferencesUtils.setLogAssistantLinkEnabled(requireContext(), z6);
        View view = this.assistantLinkManagerContainer;
        if (view != null) {
            view.setVisibility(z6 ? 0 : 8);
        }
        if (z6) {
            ensureAssistantProfileDir();
            initAssistantProfilesUi();
            refreshAssistantProfiles();
            startLogMonitorServiceIfNeeded();
        }
        SwitchMaterial switchMaterial = this.logLinkSwitch;
        if (switchMaterial == null || !switchMaterial.isChecked()) {
            return;
        }
        sendReloadProfilesCommand();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$reloadAppProfilesAsync$37(List list) {
        LogProfileAdapter logProfileAdapter;
        this.appProfiles.clear();
        this.appProfiles.addAll(list);
        LogProfileAdapter logProfileAdapter2 = this.profileAdapter;
        if (logProfileAdapter2 != null) {
            logProfileAdapter2.submitList(new ArrayList(this.appProfiles));
        }
        TextView textView = this.textAppProfileEmpty;
        if (textView != null) {
            textView.setVisibility(this.appProfiles.isEmpty() ? 0 : 8);
        }
        boolean z6 = this.appProfiles.size() > 1;
        Button button = this.buttonMultiSelectAppProfile;
        if (button != null) {
            button.setVisibility(z6 ? 0 : 8);
            if (!z6) {
                this.buttonMultiSelectAppProfile.setText("多选");
            }
        }
        if (!z6 && (logProfileAdapter = this.profileAdapter) != null && logProfileAdapter.isSelectionMode()) {
            this.profileAdapter.setSelectionMode(false);
        }
        Button button2 = this.buttonDeleteSelectedAppProfile;
        if (button2 != null) {
            button2.setVisibility(8);
        }
        Button button3 = this.buttonSelectAllAppProfile;
        if (button3 != null) {
            button3.setVisibility(8);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$reloadAppProfilesAsync$38(Context context) {
        List<LogAppProfile> emptyList;
        try {
            emptyList = LogAppProfileStorage.loadProfiles(context);
        } catch (Throwable unused) {
            emptyList = Collections.emptyList();
        }
        if (isAdded() && getActivity() != null) {
            getActivity().runOnUiThread(new l(this, emptyList, 0));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$reloadAssistantProfilesAsync$83(List list) {
        LogAssistantProfileAdapter logAssistantProfileAdapter;
        this.assistantProfiles.clear();
        this.assistantProfiles.addAll(list);
        LogAssistantProfileAdapter logAssistantProfileAdapter2 = this.assistantProfileAdapter;
        if (logAssistantProfileAdapter2 != null) {
            logAssistantProfileAdapter2.submitList(new ArrayList(this.assistantProfiles));
        }
        TextView textView = this.textAssistantProfileEmpty;
        if (textView != null) {
            textView.setVisibility(this.assistantProfiles.isEmpty() ? 0 : 8);
        }
        boolean z6 = this.assistantProfiles.size() > 1;
        Button button = this.buttonMultiSelectAssistantProfile;
        if (button != null) {
            button.setVisibility(z6 ? 0 : 8);
            if (!z6) {
                this.buttonMultiSelectAssistantProfile.setText("多选");
            }
        }
        if (!z6 && (logAssistantProfileAdapter = this.assistantProfileAdapter) != null && logAssistantProfileAdapter.isSelectionMode()) {
            this.assistantProfileAdapter.setSelectionMode(false);
        }
        Button button2 = this.buttonDeleteSelectedAssistantProfile;
        if (button2 != null) {
            button2.setVisibility(8);
        }
        Button button3 = this.buttonSelectAllAssistantProfile;
        if (button3 != null) {
            button3.setVisibility(8);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$reloadAssistantProfilesAsync$84(Context context) {
        List<LogAssistantProfile> emptyList;
        try {
            emptyList = LogAssistantProfileStorage.loadProfiles(context);
        } catch (Throwable unused) {
            emptyList = Collections.emptyList();
        }
        if (isAdded() && getActivity() != null) {
            getActivity().runOnUiThread(new l(this, emptyList, 2));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$reloadMediaProfilesAsync$81(List list) {
        LogMediaProfileAdapter logMediaProfileAdapter;
        this.mediaProfiles.clear();
        this.mediaProfiles.addAll(list);
        LogMediaProfileAdapter logMediaProfileAdapter2 = this.mediaProfileAdapter;
        if (logMediaProfileAdapter2 != null) {
            logMediaProfileAdapter2.submitList(new ArrayList(this.mediaProfiles));
        }
        TextView textView = this.textMediaProfileEmpty;
        if (textView != null) {
            textView.setVisibility(this.mediaProfiles.isEmpty() ? 0 : 8);
        }
        boolean z6 = this.mediaProfiles.size() > 1;
        Button button = this.buttonMultiSelectMediaProfile;
        if (button != null) {
            button.setVisibility(z6 ? 0 : 8);
            if (!z6) {
                this.buttonMultiSelectMediaProfile.setText("多选");
            }
        }
        if (!z6 && (logMediaProfileAdapter = this.mediaProfileAdapter) != null && logMediaProfileAdapter.isSelectionMode()) {
            this.mediaProfileAdapter.setSelectionMode(false);
        }
        Button button2 = this.buttonDeleteSelectedMediaProfile;
        if (button2 != null) {
            button2.setVisibility(8);
        }
        Button button3 = this.buttonSelectAllMediaProfile;
        if (button3 != null) {
            button3.setVisibility(8);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$reloadMediaProfilesAsync$82(Context context) {
        List<LogMediaProfile> emptyList;
        try {
            emptyList = LogMediaProfileStorage.loadProfiles(context);
        } catch (Throwable unused) {
            emptyList = Collections.emptyList();
        }
        if (isAdded() && getActivity() != null) {
            getActivity().runOnUiThread(new l(this, emptyList, 1));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$showCreateOrEditAssistantProfileDialog$63(DialogInterface dialogInterface, int i7) {
        if (i7 == 0) {
            showEditAssistantProfileDialogInternal(null, null);
            return;
        }
        if (i7 == 1) {
            showEditAssistantProfileDialogInternal(null, "瑶光上电");
            return;
        }
        if (i7 == 2) {
            showEditAssistantProfileDialogInternal(null, "瑶光下电");
        } else if (i7 == 3) {
            showEditAssistantProfileDialogInternal(null, "瑞虎9上电");
        } else if (i7 == 4) {
            showEditAssistantProfileDialogInternal(null, "艾瑞泽8上电");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ void lambda$showCreateOrEditAssistantProfileDialog$64(DialogInterface dialogInterface, int i7) {
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$showCreateOrEditAssistantProfileDialog$65(LogAssistantProfile logAssistantProfile, DialogInterface dialogInterface, int i7) {
        if (i7 == 0) {
            showEditAssistantProfileDialogInternal(logAssistantProfile, null);
            return;
        }
        if (i7 == 1) {
            String saveProfile = LogAssistantProfileStorage.saveProfile(requireContext(), new LogAssistantProfile(logAssistantProfile.getName() + "_copy", logAssistantProfile.getTag(), logAssistantProfile.getPattern(), logAssistantProfile.getAction(), logAssistantProfile.isEnabled(), logAssistantProfile.getConditionType(), logAssistantProfile.getConditionVariable(), logAssistantProfile.getConditionOperator(), logAssistantProfile.getConditionValue(), logAssistantProfile.getVariableName(), logAssistantProfile.getVariableOp(), logAssistantProfile.getVariableValue(), logAssistantProfile.getDelaySeconds(), logAssistantProfile.getCooldownSeconds(), logAssistantProfile.getComboSeconds(), logAssistantProfile.getComboCount()));
            if (saveProfile == null) {
                ToastHelper.show(requireContext(), "已复制配置: " + logAssistantProfile.getName() + " -> " + logAssistantProfile.getName() + "_copy");
            } else {
                ToastHelper.show(requireContext(), "复制失败: ".concat(saveProfile));
            }
            refreshAssistantProfiles();
            startLogMonitorServiceIfNeeded();
            sendReloadProfilesCommand();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ void lambda$showCreateOrEditAssistantProfileDialog$66(DialogInterface dialogInterface, int i7) {
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$showCreateOrEditMediaProfileDialog$85(LogMediaProfile logMediaProfile, DialogInterface dialogInterface, int i7) {
        if (i7 == 0) {
            showEditMediaProfileDialogInternal(logMediaProfile);
        } else if (i7 == 1) {
            copyMediaProfile(logMediaProfile);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ void lambda$showCreateOrEditMediaProfileDialog$86(DialogInterface dialogInterface, int i7) {
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$showCreateOrEditProfileDialog$39(LogAppProfile logAppProfile, DialogInterface dialogInterface, int i7) {
        if (i7 == 0) {
            showEditProfileDialogInternal(logAppProfile);
        } else if (i7 == 1) {
            copyAppProfile(logAppProfile);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ void lambda$showCreateOrEditProfileDialog$40(DialogInterface dialogInterface, int i7) {
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ void lambda$showEditAssistantProfileDialogInternal$67(View view, RadioGroup radioGroup, int i7) {
        view.setVisibility(i7 == g4.p.radio_condition_variable ? 0 : 8);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ void lambda$showEditAssistantProfileDialogInternal$68(View view, CompoundButton compoundButton, boolean z6) {
        view.setVisibility(z6 ? 0 : 8);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ void lambda$showEditAssistantProfileDialogInternal$69(View view, CompoundButton compoundButton, boolean z6) {
        view.setVisibility(z6 ? 0 : 8);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ void lambda$showEditAssistantProfileDialogInternal$70(View view, CompoundButton compoundButton, boolean z6) {
        if (view != null) {
            view.setVisibility(z6 ? 0 : 8);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ void lambda$showEditAssistantProfileDialogInternal$71(View view, View view2, RadioGroup radioGroup, int i7) {
        boolean z6 = i7 == g4.p.radio_variable_action_set;
        view.setVisibility(z6 ? 0 : 8);
        view2.setVisibility(z6 ? 8 : 0);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ void lambda$showEditAssistantProfileDialogInternal$72(View view, CompoundButton compoundButton, boolean z6) {
        view.setVisibility(z6 ? 0 : 8);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ void lambda$showEditAssistantProfileDialogInternal$73(LinearLayout linearLayout, LinearLayout linearLayout2, List list, EditText editText, List list2, EditText editText2, View view) {
        linearLayout.removeView(linearLayout2);
        list.remove(editText);
        list2.remove(editText2);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ void lambda$showEditAssistantProfileDialogInternal$74(CheckBox checkBox, View view, View view2, View view3, View view4, List list, List list2, View view5) {
        checkBox.setChecked(true);
        view.setVisibility(0);
        view2.setVisibility(0);
        view3.setVisibility(8);
        if (view2 instanceof LinearLayout) {
            LinearLayout linearLayout = (LinearLayout) view2;
            Context context = view4.getContext();
            LinearLayout linearLayout2 = new LinearLayout(context);
            linearLayout2.setOrientation(0);
            linearLayout2.setGravity(16);
            TextView textView = new TextView(context);
            textView.setText("变量名称 $");
            linearLayout2.addView(textView);
            EditText editText = new EditText(context);
            editText.setLayoutParams(new LinearLayout.LayoutParams(0, -2, 1.0f));
            editText.setInputType(1);
            linearLayout2.addView(editText);
            TextView textView2 = new TextView(context);
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(-2, -2);
            layoutParams.setMarginStart(8);
            textView2.setLayoutParams(layoutParams);
            textView2.setText("赋值");
            linearLayout2.addView(textView2);
            EditText editText2 = new EditText(context);
            LinearLayout.LayoutParams layoutParams2 = new LinearLayout.LayoutParams(0, -2, 1.0f);
            layoutParams2.setMarginStart(4);
            editText2.setLayoutParams(layoutParams2);
            editText2.setInputType(androidx.fragment.app.h1.TRANSIT_FRAGMENT_CLOSE);
            linearLayout2.addView(editText2);
            ImageButton imageButton = new ImageButton(context);
            imageButton.setImageResource(R.drawable.ic_delete);
            imageButton.setBackground(null);
            imageButton.setOnClickListener(new e(linearLayout, linearLayout2, list, editText, list2, editText2, 2));
            linearLayout2.addView(imageButton);
            linearLayout.addView(linearLayout2);
            list.add(editText);
            list2.add(editText2);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ void lambda$showEditAssistantProfileDialogInternal$75(LinearLayout linearLayout, LinearLayout linearLayout2, List list, EditText editText, View view) {
        linearLayout.removeView(linearLayout2);
        list.remove(editText);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ void lambda$showEditAssistantProfileDialogInternal$76(CheckBox checkBox, View view, View view2, View view3, View view4, List list, View view5) {
        checkBox.setChecked(true);
        view.setVisibility(0);
        view2.setVisibility(8);
        view3.setVisibility(0);
        if (view3 instanceof LinearLayout) {
            LinearLayout linearLayout = (LinearLayout) view3;
            Context context = view4.getContext();
            LinearLayout linearLayout2 = new LinearLayout(context);
            linearLayout2.setOrientation(0);
            linearLayout2.setGravity(16);
            TextView textView = new TextView(context);
            textView.setText("变量名称 $");
            linearLayout2.addView(textView);
            EditText editText = new EditText(context);
            editText.setLayoutParams(new LinearLayout.LayoutParams(0, -2, 1.0f));
            editText.setInputType(1);
            linearLayout2.addView(editText);
            ImageButton imageButton = new ImageButton(context);
            imageButton.setImageResource(R.drawable.ic_delete);
            imageButton.setBackground(null);
            imageButton.setOnClickListener(new a0(linearLayout, linearLayout2, list, editText, 1));
            linearLayout2.addView(imageButton);
            linearLayout.addView(linearLayout2);
            list.add(editText);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ void lambda$showEditAssistantProfileDialogInternal$77(LinearLayout linearLayout, LinearLayout linearLayout2, List list, EditText editText, View view) {
        linearLayout.removeView(linearLayout2);
        list.remove(editText);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ void lambda$showEditAssistantProfileDialogInternal$78(LinearLayout linearLayout, LinearLayout linearLayout2, List list, EditText editText, List list2, EditText editText2, View view) {
        linearLayout.removeView(linearLayout2);
        list.remove(editText);
        list2.remove(editText2);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ void lambda$showEditAssistantProfileDialogInternal$79(DialogInterface dialogInterface, int i7) {
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:127:0x0236  */
    /* JADX WARN: Removed duplicated region for block: B:128:0x0146 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:29:0x0108  */
    /* JADX WARN: Removed duplicated region for block: B:34:0x011b  */
    /* JADX WARN: Removed duplicated region for block: B:48:0x015a  */
    /* JADX WARN: Removed duplicated region for block: B:70:0x0248 A[ADDED_TO_REGION] */
    /* JADX WARN: Removed duplicated region for block: B:78:0x0289  */
    /* JADX WARN: Removed duplicated region for block: B:83:0x02b8  */
    /* JADX WARN: Removed duplicated region for block: B:90:0x02d5  */
    /* JADX WARN: Removed duplicated region for block: B:93:0x02df  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public /* synthetic */ void lambda$showEditAssistantProfileDialogInternal$80(android.widget.EditText r25, android.widget.EditText r26, android.widget.EditText r27, android.widget.RadioGroup r28, android.widget.RadioGroup r29, android.widget.EditText r30, android.widget.Spinner r31, android.widget.EditText r32, android.widget.CheckBox r33, android.widget.EditText r34, android.widget.CheckBox r35, android.widget.EditText r36, android.widget.CheckBox r37, android.widget.EditText r38, android.widget.EditText r39, android.widget.CheckBox r40, android.widget.RadioGroup r41, android.widget.EditText r42, java.util.List r43, android.widget.EditText r44, android.widget.EditText r45, java.util.List r46, java.util.List r47, com.navi.tool.model.LogAssistantProfile r48, java.lang.String r49, android.content.DialogInterface r50, int r51) {
        /*
            Method dump skipped, instructions count: 768
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.navi.tool.ui.log.LogLinkFragment.lambda$showEditAssistantProfileDialogInternal$80(android.widget.EditText, android.widget.EditText, android.widget.EditText, android.widget.RadioGroup, android.widget.RadioGroup, android.widget.EditText, android.widget.Spinner, android.widget.EditText, android.widget.CheckBox, android.widget.EditText, android.widget.CheckBox, android.widget.EditText, android.widget.CheckBox, android.widget.EditText, android.widget.EditText, android.widget.CheckBox, android.widget.RadioGroup, android.widget.EditText, java.util.List, android.widget.EditText, android.widget.EditText, java.util.List, java.util.List, com.navi.tool.model.LogAssistantProfile, java.lang.String, android.content.DialogInterface, int):void");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$showEditMediaProfileDialogInternal$100(List list, List list2, boolean[] zArr, boolean[] zArr2, boolean z6, File file, File file2, LogMediaProfile logMediaProfile, h1 h1Var) {
        String mediaPath;
        list.clear();
        list.addAll(list2);
        zArr[0] = true;
        zArr2[0] = false;
        if (z6 && list.isEmpty()) {
            ToastHelper.showLong(requireContext(), "在 " + file.getAbsolutePath() + " 或 " + file2.getAbsolutePath() + " 下未发现媒体文件");
        }
        if (logMediaProfile != null && (mediaPath = logMediaProfile.getMediaPath()) != null) {
            Iterator it = list.iterator();
            while (it.hasNext()) {
                MediaFileItem mediaFileItem = (MediaFileItem) it.next();
                mediaFileItem.selected = mediaPath.equals(mediaFileItem.path);
            }
            h1Var.notifyDataSetChanged();
        }
        h1Var.notifyDataSetChanged();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$showEditMediaProfileDialogInternal$101(final File file, final File file2, final boolean[] zArr, final List list, final boolean[] zArr2, final boolean z6, final LogMediaProfile logMediaProfile, final h1 h1Var) {
        final ArrayList arrayList = new ArrayList();
        final int i7 = 0;
        File[] listFiles = file.listFiles(new FileFilter() { // from class: com.navi.tool.ui.log.o
            @Override // java.io.FileFilter
            public final boolean accept(File file3) {
                boolean lambda$showEditMediaProfileDialogInternal$98;
                boolean lambda$showEditMediaProfileDialogInternal$99;
                switch (i7) {
                    case 0:
                        lambda$showEditMediaProfileDialogInternal$98 = LogLinkFragment.lambda$showEditMediaProfileDialogInternal$98(file3);
                        return lambda$showEditMediaProfileDialogInternal$98;
                    default:
                        lambda$showEditMediaProfileDialogInternal$99 = LogLinkFragment.lambda$showEditMediaProfileDialogInternal$99(file3);
                        return lambda$showEditMediaProfileDialogInternal$99;
                }
            }
        });
        if (listFiles != null) {
            for (File file3 : listFiles) {
                MediaFileItem mediaFileItem = new MediaFileItem();
                mediaFileItem.name = file3.getName();
                mediaFileItem.path = file3.getAbsolutePath();
                mediaFileItem.selected = false;
                arrayList.add(mediaFileItem);
            }
        }
        final int i8 = 1;
        File[] listFiles2 = file2.listFiles(new FileFilter() { // from class: com.navi.tool.ui.log.o
            @Override // java.io.FileFilter
            public final boolean accept(File file32) {
                boolean lambda$showEditMediaProfileDialogInternal$98;
                boolean lambda$showEditMediaProfileDialogInternal$99;
                switch (i8) {
                    case 0:
                        lambda$showEditMediaProfileDialogInternal$98 = LogLinkFragment.lambda$showEditMediaProfileDialogInternal$98(file32);
                        return lambda$showEditMediaProfileDialogInternal$98;
                    default:
                        lambda$showEditMediaProfileDialogInternal$99 = LogLinkFragment.lambda$showEditMediaProfileDialogInternal$99(file32);
                        return lambda$showEditMediaProfileDialogInternal$99;
                }
            }
        });
        if (listFiles2 != null) {
            for (File file4 : listFiles2) {
                MediaFileItem mediaFileItem2 = new MediaFileItem();
                mediaFileItem2.name = file4.getName();
                mediaFileItem2.path = file4.getAbsolutePath();
                mediaFileItem2.selected = false;
                arrayList.add(mediaFileItem2);
            }
        }
        if (!isAdded() || getActivity() == null) {
            zArr[0] = false;
        } else {
            getActivity().runOnUiThread(new Runnable() { // from class: com.navi.tool.ui.log.q
                @Override // java.lang.Runnable
                public final void run() {
                    LogLinkFragment.this.lambda$showEditMediaProfileDialogInternal$100(list, arrayList, zArr2, zArr, z6, file, file2, logMediaProfile, h1Var);
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$showEditMediaProfileDialogInternal$102(final boolean[] zArr, final boolean[] zArr2, boolean[] zArr3, final File file, final File file2, final List list, final LogMediaProfile logMediaProfile, final h1 h1Var) {
        if (zArr[0] || zArr2[0]) {
            return;
        }
        zArr2[0] = true;
        final boolean z6 = zArr3[0];
        zArr3[0] = false;
        new Thread(new Runnable() { // from class: com.navi.tool.ui.log.r
            @Override // java.lang.Runnable
            public final void run() {
                LogLinkFragment.this.lambda$showEditMediaProfileDialogInternal$101(file, file2, zArr2, list, zArr, z6, logMediaProfile, h1Var);
            }
        }).start();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$showEditMediaProfileDialogInternal$103(RecyclerView recyclerView, boolean[] zArr, List list, File file, File file2, boolean[] zArr2, Runnable runnable, RadioGroup radioGroup, int i7) {
        boolean z6 = i7 == g4.p.radio_media_action_play_file;
        recyclerView.setVisibility(z6 ? 0 : 8);
        if (z6) {
            if (!zArr[0]) {
                zArr2[0] = true;
                runnable.run();
            } else if (list.isEmpty()) {
                ToastHelper.showLong(requireContext(), "在 " + file.getAbsolutePath() + " 或 " + file2.getAbsolutePath() + " 下未发现媒体文件");
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ void lambda$showEditMediaProfileDialogInternal$104(LinearLayout linearLayout, LinearLayout linearLayout2, List list, EditText editText, View view) {
        linearLayout.removeView(linearLayout2);
        list.remove(editText);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ void lambda$showEditMediaProfileDialogInternal$105(LinearLayout linearLayout, LinearLayout linearLayout2, List list, EditText editText, List list2, EditText editText2, View view) {
        linearLayout.removeView(linearLayout2);
        list.remove(editText);
        list2.remove(editText2);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ void lambda$showEditMediaProfileDialogInternal$106(DialogInterface dialogInterface, int i7) {
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:101:0x02ab  */
    /* JADX WARN: Removed duplicated region for block: B:102:0x024e  */
    /* JADX WARN: Removed duplicated region for block: B:107:0x023a  */
    /* JADX WARN: Removed duplicated region for block: B:127:0x0203  */
    /* JADX WARN: Removed duplicated region for block: B:128:0x0118 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:29:0x00de  */
    /* JADX WARN: Removed duplicated region for block: B:34:0x00f0  */
    /* JADX WARN: Removed duplicated region for block: B:48:0x0127  */
    /* JADX WARN: Removed duplicated region for block: B:71:0x0216  */
    /* JADX WARN: Removed duplicated region for block: B:82:0x023e  */
    /* JADX WARN: Removed duplicated region for block: B:86:0x0249  */
    /* JADX WARN: Removed duplicated region for block: B:88:0x0251  */
    /* JADX WARN: Removed duplicated region for block: B:91:0x0284  */
    /* JADX WARN: Removed duplicated region for block: B:98:0x02a1  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public /* synthetic */ void lambda$showEditMediaProfileDialogInternal$107(android.widget.EditText r21, android.widget.EditText r22, android.widget.EditText r23, android.widget.RadioGroup r24, android.widget.RadioGroup r25, android.widget.EditText r26, android.widget.Spinner r27, android.widget.EditText r28, android.widget.CheckBox r29, android.widget.EditText r30, android.widget.CheckBox r31, android.widget.EditText r32, android.widget.CheckBox r33, android.widget.EditText r34, android.widget.EditText r35, android.widget.CheckBox r36, android.widget.RadioGroup r37, android.widget.EditText r38, java.util.List r39, android.widget.EditText r40, android.widget.EditText r41, java.util.List r42, java.util.List r43, java.util.List r44, com.navi.tool.model.LogMediaProfile r45, android.content.DialogInterface r46, int r47) {
        /*
            Method dump skipped, instructions count: 716
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.navi.tool.ui.log.LogLinkFragment.lambda$showEditMediaProfileDialogInternal$107(android.widget.EditText, android.widget.EditText, android.widget.EditText, android.widget.RadioGroup, android.widget.RadioGroup, android.widget.EditText, android.widget.Spinner, android.widget.EditText, android.widget.CheckBox, android.widget.EditText, android.widget.CheckBox, android.widget.EditText, android.widget.CheckBox, android.widget.EditText, android.widget.EditText, android.widget.CheckBox, android.widget.RadioGroup, android.widget.EditText, java.util.List, android.widget.EditText, android.widget.EditText, java.util.List, java.util.List, java.util.List, com.navi.tool.model.LogMediaProfile, android.content.DialogInterface, int):void");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ void lambda$showEditMediaProfileDialogInternal$87(View view, RadioGroup radioGroup, int i7) {
        view.setVisibility(i7 == g4.p.radio_condition_variable ? 0 : 8);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ void lambda$showEditMediaProfileDialogInternal$88(View view, CompoundButton compoundButton, boolean z6) {
        view.setVisibility(z6 ? 0 : 8);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ void lambda$showEditMediaProfileDialogInternal$89(View view, CompoundButton compoundButton, boolean z6) {
        view.setVisibility(z6 ? 0 : 8);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ void lambda$showEditMediaProfileDialogInternal$90(View view, CompoundButton compoundButton, boolean z6) {
        if (view != null) {
            view.setVisibility(z6 ? 0 : 8);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ void lambda$showEditMediaProfileDialogInternal$91(View view, View view2, RadioGroup radioGroup, int i7) {
        boolean z6 = i7 == g4.p.radio_variable_action_set;
        view.setVisibility(z6 ? 0 : 8);
        view2.setVisibility(z6 ? 8 : 0);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ void lambda$showEditMediaProfileDialogInternal$92(View view, CompoundButton compoundButton, boolean z6) {
        view.setVisibility(z6 ? 0 : 8);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ void lambda$showEditMediaProfileDialogInternal$93(LinearLayout linearLayout, LinearLayout linearLayout2, List list, EditText editText, List list2, EditText editText2, View view) {
        linearLayout.removeView(linearLayout2);
        list.remove(editText);
        list2.remove(editText2);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ void lambda$showEditMediaProfileDialogInternal$94(CheckBox checkBox, View view, View view2, View view3, View view4, List list, List list2, View view5) {
        checkBox.setChecked(true);
        view.setVisibility(0);
        view2.setVisibility(0);
        view3.setVisibility(8);
        if (view2 instanceof LinearLayout) {
            LinearLayout linearLayout = (LinearLayout) view2;
            Context context = view4.getContext();
            LinearLayout linearLayout2 = new LinearLayout(context);
            linearLayout2.setOrientation(0);
            linearLayout2.setGravity(16);
            TextView textView = new TextView(context);
            textView.setText("变量名称 $");
            linearLayout2.addView(textView);
            EditText editText = new EditText(context);
            editText.setLayoutParams(new LinearLayout.LayoutParams(0, -2, 1.0f));
            editText.setInputType(1);
            linearLayout2.addView(editText);
            TextView textView2 = new TextView(context);
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(-2, -2);
            layoutParams.setMarginStart(8);
            textView2.setLayoutParams(layoutParams);
            textView2.setText("赋值");
            linearLayout2.addView(textView2);
            EditText editText2 = new EditText(context);
            LinearLayout.LayoutParams layoutParams2 = new LinearLayout.LayoutParams(0, -2, 1.0f);
            layoutParams2.setMarginStart(4);
            editText2.setLayoutParams(layoutParams2);
            editText2.setInputType(androidx.fragment.app.h1.TRANSIT_FRAGMENT_CLOSE);
            linearLayout2.addView(editText2);
            ImageButton imageButton = new ImageButton(context);
            imageButton.setImageResource(R.drawable.ic_delete);
            imageButton.setBackground(null);
            imageButton.setOnClickListener(new e(linearLayout, linearLayout2, list, editText, list2, editText2, 4));
            linearLayout2.addView(imageButton);
            linearLayout.addView(linearLayout2);
            list.add(editText);
            list2.add(editText2);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ void lambda$showEditMediaProfileDialogInternal$95(LinearLayout linearLayout, LinearLayout linearLayout2, List list, EditText editText, View view) {
        linearLayout.removeView(linearLayout2);
        list.remove(editText);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ void lambda$showEditMediaProfileDialogInternal$96(CheckBox checkBox, View view, View view2, View view3, View view4, List list, View view5) {
        checkBox.setChecked(true);
        view.setVisibility(0);
        view2.setVisibility(8);
        view3.setVisibility(0);
        if (view3 instanceof LinearLayout) {
            LinearLayout linearLayout = (LinearLayout) view3;
            Context context = view4.getContext();
            LinearLayout linearLayout2 = new LinearLayout(context);
            linearLayout2.setOrientation(0);
            linearLayout2.setGravity(16);
            TextView textView = new TextView(context);
            textView.setText("变量名称 $");
            linearLayout2.addView(textView);
            EditText editText = new EditText(context);
            editText.setLayoutParams(new LinearLayout.LayoutParams(0, -2, 1.0f));
            editText.setInputType(1);
            linearLayout2.addView(editText);
            ImageButton imageButton = new ImageButton(context);
            imageButton.setImageResource(R.drawable.ic_delete);
            imageButton.setBackground(null);
            imageButton.setOnClickListener(new a0(linearLayout, linearLayout2, list, editText, 2));
            linearLayout2.addView(imageButton);
            linearLayout.addView(linearLayout2);
            list.add(editText);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ boolean lambda$showEditMediaProfileDialogInternal$98(File file) {
        return file.isFile() && file.getName().toLowerCase().endsWith(".mp3");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ boolean lambda$showEditMediaProfileDialogInternal$99(File file) {
        return file.isFile() && file.getName().toLowerCase().endsWith(".mp3");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ void lambda$showEditProfileDialogInternal$41(View view, RadioGroup radioGroup, int i7) {
        view.setVisibility(i7 == g4.p.radio_condition_variable ? 0 : 8);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ void lambda$showEditProfileDialogInternal$42(View view, CompoundButton compoundButton, boolean z6) {
        view.setVisibility(z6 ? 0 : 8);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ void lambda$showEditProfileDialogInternal$43(View view, CompoundButton compoundButton, boolean z6) {
        view.setVisibility(z6 ? 0 : 8);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ void lambda$showEditProfileDialogInternal$44(View view, CompoundButton compoundButton, boolean z6) {
        if (view != null) {
            view.setVisibility(z6 ? 0 : 8);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ void lambda$showEditProfileDialogInternal$45(View view, CompoundButton compoundButton, boolean z6) {
        view.setVisibility(z6 ? 0 : 8);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ void lambda$showEditProfileDialogInternal$46(View view, View view2, RadioGroup radioGroup, int i7) {
        boolean z6 = i7 == g4.p.radio_variable_action_set;
        view.setVisibility(z6 ? 0 : 8);
        view2.setVisibility(z6 ? 8 : 0);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ void lambda$showEditProfileDialogInternal$47(View view, CompoundButton compoundButton, boolean z6) {
        view.setVisibility(z6 ? 0 : 8);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ void lambda$showEditProfileDialogInternal$48(boolean[] zArr, List list, RadioButton radioButton, RadioButton radioButton2, View view, CompoundButton compoundButton, boolean z6) {
        if (zArr[0] || !z6) {
            return;
        }
        boolean z7 = true;
        zArr[0] = true;
        Iterator it = list.iterator();
        while (it.hasNext()) {
            RadioButton radioButton3 = (RadioButton) it.next();
            if (radioButton3 != null && radioButton3 != compoundButton) {
                radioButton3.setChecked(false);
            }
        }
        zArr[0] = false;
        if ((radioButton == null || !radioButton.isChecked()) && (radioButton2 == null || !radioButton2.isChecked())) {
            z7 = false;
        }
        if (view != null) {
            view.setVisibility(z7 ? 0 : 8);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ void lambda$showEditProfileDialogInternal$49(LinearLayout linearLayout, LinearLayout linearLayout2, List list, EditText editText, List list2, EditText editText2, View view) {
        linearLayout.removeView(linearLayout2);
        list.remove(editText);
        list2.remove(editText2);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ void lambda$showEditProfileDialogInternal$50(CheckBox checkBox, View view, View view2, View view3, View view4, List list, List list2, View view5) {
        checkBox.setChecked(true);
        view.setVisibility(0);
        view2.setVisibility(0);
        view3.setVisibility(8);
        if (view2 instanceof LinearLayout) {
            LinearLayout linearLayout = (LinearLayout) view2;
            Context context = view4.getContext();
            LinearLayout linearLayout2 = new LinearLayout(context);
            linearLayout2.setOrientation(0);
            linearLayout2.setGravity(16);
            TextView textView = new TextView(context);
            textView.setText("变量名称 $");
            linearLayout2.addView(textView);
            EditText editText = new EditText(context);
            editText.setLayoutParams(new LinearLayout.LayoutParams(0, -2, 1.0f));
            editText.setInputType(1);
            linearLayout2.addView(editText);
            TextView textView2 = new TextView(context);
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(-2, -2);
            layoutParams.setMarginStart(8);
            textView2.setLayoutParams(layoutParams);
            textView2.setText("赋值");
            linearLayout2.addView(textView2);
            EditText editText2 = new EditText(context);
            LinearLayout.LayoutParams layoutParams2 = new LinearLayout.LayoutParams(0, -2, 1.0f);
            layoutParams2.setMarginStart(4);
            editText2.setLayoutParams(layoutParams2);
            editText2.setInputType(androidx.fragment.app.h1.TRANSIT_FRAGMENT_CLOSE);
            linearLayout2.addView(editText2);
            ImageButton imageButton = new ImageButton(context);
            imageButton.setImageResource(R.drawable.ic_delete);
            imageButton.setBackground(null);
            imageButton.setOnClickListener(new e(linearLayout, linearLayout2, list, editText, list2, editText2, 0));
            linearLayout2.addView(imageButton);
            linearLayout.addView(linearLayout2);
            list.add(editText);
            list2.add(editText2);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ void lambda$showEditProfileDialogInternal$51(LinearLayout linearLayout, LinearLayout linearLayout2, List list, EditText editText, View view) {
        linearLayout.removeView(linearLayout2);
        list.remove(editText);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ void lambda$showEditProfileDialogInternal$52(CheckBox checkBox, View view, View view2, View view3, View view4, List list, View view5) {
        checkBox.setChecked(true);
        view.setVisibility(0);
        view2.setVisibility(8);
        view3.setVisibility(0);
        if (view3 instanceof LinearLayout) {
            LinearLayout linearLayout = (LinearLayout) view3;
            Context context = view4.getContext();
            LinearLayout linearLayout2 = new LinearLayout(context);
            linearLayout2.setOrientation(0);
            linearLayout2.setGravity(16);
            TextView textView = new TextView(context);
            textView.setText("变量名称 $");
            linearLayout2.addView(textView);
            EditText editText = new EditText(context);
            editText.setLayoutParams(new LinearLayout.LayoutParams(0, -2, 1.0f));
            editText.setInputType(1);
            linearLayout2.addView(editText);
            ImageButton imageButton = new ImageButton(context);
            imageButton.setImageResource(R.drawable.ic_delete);
            imageButton.setBackground(null);
            imageButton.setOnClickListener(new a0(linearLayout, linearLayout2, list, editText, 3));
            linearLayout2.addView(imageButton);
            linearLayout.addView(linearLayout2);
            list.add(editText);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ void lambda$showEditProfileDialogInternal$53(List list, RecyclerView recyclerView, AppInfo appInfo) {
        Iterator it = list.iterator();
        while (it.hasNext()) {
            AppInfo appInfo2 = (AppInfo) it.next();
            appInfo2.setEnabled(appInfo2.getPackageName().equals(appInfo.getPackageName()));
        }
        AppAdapter appAdapter = (AppAdapter) recyclerView.getAdapter();
        if (appAdapter != null) {
            appAdapter.submitList(new ArrayList(list));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ void lambda$showEditProfileDialogInternal$57(List list, List list2, AppAdapter appAdapter, RecyclerView recyclerView, LogAppProfile logAppProfile) {
        list.clear();
        list.addAll(list2);
        appAdapter.submitList(new ArrayList(list));
        recyclerView.post(new g(1, recyclerView));
        if (logAppProfile != null) {
            Iterator it = list.iterator();
            while (it.hasNext()) {
                AppInfo appInfo = (AppInfo) it.next();
                appInfo.setEnabled(appInfo.getPackageName().equals(logAppProfile.getPackageName()));
            }
            appAdapter.submitList(new ArrayList(list));
            recyclerView.post(new g(2, recyclerView));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$showEditProfileDialogInternal$58(List list, AppAdapter appAdapter, RecyclerView recyclerView, LogAppProfile logAppProfile) {
        List<AppInfo> allLaunchableApps = getAllLaunchableApps();
        if (isAdded() && getActivity() != null) {
            getActivity().runOnUiThread(new s(list, allLaunchableApps, appAdapter, recyclerView, logAppProfile));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ void lambda$showEditProfileDialogInternal$59(LinearLayout linearLayout, LinearLayout linearLayout2, List list, EditText editText, View view) {
        linearLayout.removeView(linearLayout2);
        list.remove(editText);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ void lambda$showEditProfileDialogInternal$60(LinearLayout linearLayout, LinearLayout linearLayout2, List list, EditText editText, List list2, EditText editText2, View view) {
        linearLayout.removeView(linearLayout2);
        list.remove(editText);
        list2.remove(editText2);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ void lambda$showEditProfileDialogInternal$61(DialogInterface dialogInterface, int i7) {
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:102:0x0257  */
    /* JADX WARN: Removed duplicated region for block: B:131:0x023f A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:152:0x0216  */
    /* JADX WARN: Removed duplicated region for block: B:153:0x010a A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:33:0x00d0  */
    /* JADX WARN: Removed duplicated region for block: B:38:0x00e2  */
    /* JADX WARN: Removed duplicated region for block: B:51:0x0115  */
    /* JADX WARN: Removed duplicated region for block: B:57:0x0133 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:61:0x0129 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:67:0x013d  */
    /* JADX WARN: Removed duplicated region for block: B:91:0x022e  */
    /* JADX WARN: Removed duplicated region for block: B:97:0x0247  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public /* synthetic */ void lambda$showEditProfileDialogInternal$62(android.widget.EditText r22, android.widget.EditText r23, android.widget.EditText r24, java.util.List r25, android.widget.RadioGroup r26, android.widget.EditText r27, android.widget.Spinner r28, android.widget.EditText r29, android.widget.CheckBox r30, android.widget.EditText r31, android.widget.CheckBox r32, android.widget.EditText r33, android.widget.CheckBox r34, android.widget.EditText r35, android.widget.EditText r36, android.widget.CheckBox r37, android.widget.RadioGroup r38, android.widget.EditText r39, java.util.List r40, android.widget.EditText r41, android.widget.EditText r42, java.util.List r43, java.util.List r44, java.util.List r45, com.navi.tool.model.LogAppProfile r46, android.content.DialogInterface r47, int r48) {
        /*
            Method dump skipped, instructions count: 746
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.navi.tool.ui.log.LogLinkFragment.lambda$showEditProfileDialogInternal$62(android.widget.EditText, android.widget.EditText, android.widget.EditText, java.util.List, android.widget.RadioGroup, android.widget.EditText, android.widget.Spinner, android.widget.EditText, android.widget.CheckBox, android.widget.EditText, android.widget.CheckBox, android.widget.EditText, android.widget.CheckBox, android.widget.EditText, android.widget.EditText, android.widget.CheckBox, android.widget.RadioGroup, android.widget.EditText, java.util.List, android.widget.EditText, android.widget.EditText, java.util.List, java.util.List, java.util.List, com.navi.tool.model.LogAppProfile, android.content.DialogInterface, int):void");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void refreshAppProfiles() {
        reloadAppProfilesAsync();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void refreshAssistantProfiles() {
        reloadAssistantProfilesAsync();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void refreshMediaProfiles() {
        reloadMediaProfilesAsync();
    }

    private void reloadAppProfilesAsync() {
        new Thread(new j(this, requireContext().getApplicationContext(), 1)).start();
    }

    private void reloadAssistantProfilesAsync() {
        new Thread(new j(this, requireContext().getApplicationContext(), 2)).start();
    }

    private void reloadMediaProfilesAsync() {
        new Thread(new j(this, requireContext().getApplicationContext(), 0)).start();
    }

    private void requestStorageAccess() {
        if (Build.VERSION.SDK_INT < 30) {
            requestPermissions(new String[]{"android.permission.READ_EXTERNAL_STORAGE", "android.permission.WRITE_EXTERNAL_STORAGE"}, 2001);
            return;
        }
        try {
            Intent intent = new Intent("android.settings.MANAGE_APP_ALL_FILES_ACCESS_PERMISSION");
            intent.setData(Uri.parse("package:" + requireContext().getPackageName()));
            startActivity(intent);
        } catch (Exception unused) {
            startActivity(new Intent("android.settings.MANAGE_ALL_FILES_ACCESS_PERMISSION"));
        }
    }

    private void selectAllAppProfiles() {
        LogProfileAdapter logProfileAdapter = this.profileAdapter;
        if (logProfileAdapter == null) {
            return;
        }
        logProfileAdapter.selectAll();
    }

    private void selectAllAssistantProfiles() {
        LogAssistantProfileAdapter logAssistantProfileAdapter = this.assistantProfileAdapter;
        if (logAssistantProfileAdapter == null) {
            return;
        }
        logAssistantProfileAdapter.selectAll();
    }

    private void selectAllMediaProfiles() {
        LogMediaProfileAdapter logMediaProfileAdapter = this.mediaProfileAdapter;
        if (logMediaProfileAdapter == null) {
            return;
        }
        logMediaProfileAdapter.selectAll();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void sendReloadProfilesCommand() {
        Intent intent = new Intent(requireContext(), (Class<?>) LogLinkageService.class);
        intent.setAction(LogLinkageService.ACTION_RELOAD_LOG_LINK_PROFILES);
        requireContext().startService(intent);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: showCreateOrEditAssistantProfileDialog, reason: merged with bridge method [inline-methods] */
    public void lambda$initAssistantProfilesUi$23(LogAssistantProfile logAssistantProfile) {
        int i7 = 1;
        int i8 = 0;
        if (logAssistantProfile == null) {
            androidx.appcompat.app.n nVar = new androidx.appcompat.app.n(requireContext());
            nVar.t("新建配置文件");
            nVar.l(new CharSequence[]{"自定义", "瑶光上电", "瑶光下电", "瑞虎9上电", "艾瑞泽8上电"}, new n(this, i8));
            nVar.o("取消", new g4.g(6));
            nVar.v();
            return;
        }
        androidx.appcompat.app.n nVar2 = new androidx.appcompat.app.n(requireContext());
        nVar2.t("配置文件操作");
        nVar2.l(new CharSequence[]{"编辑配置文件", "复制配置文件"}, new i(this, logAssistantProfile, i7));
        nVar2.o("取消", new g4.g(7));
        nVar2.v();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: showCreateOrEditMediaProfileDialog, reason: merged with bridge method [inline-methods] */
    public void lambda$initMediaProfilesUi$14(LogMediaProfile logMediaProfile) {
        if (logMediaProfile == null) {
            showEditMediaProfileDialogInternal(null);
            return;
        }
        androidx.appcompat.app.n nVar = new androidx.appcompat.app.n(requireContext());
        nVar.t("配置文件操作");
        nVar.l(new CharSequence[]{"编辑配置文件", "复制配置文件"}, new t(this, logMediaProfile, 1));
        nVar.o("取消", new g4.g(14));
        nVar.v();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: showCreateOrEditProfileDialog, reason: merged with bridge method [inline-methods] */
    public void lambda$initAppProfilesUi$5(LogAppProfile logAppProfile) {
        if (logAppProfile == null) {
            showEditProfileDialogInternal(null);
            return;
        }
        androidx.appcompat.app.n nVar = new androidx.appcompat.app.n(requireContext());
        nVar.t("配置文件操作");
        nVar.l(new CharSequence[]{"编辑配置文件", "复制配置文件"}, new k(this, logAppProfile, 1));
        nVar.o("取消", new g4.g(8));
        nVar.v();
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r2v59 */
    /* JADX WARN: Type inference failed for: r2v60, types: [boolean, int] */
    /* JADX WARN: Type inference failed for: r2v93 */
    private void showEditAssistantProfileDialogInternal(LogAssistantProfile logAssistantProfile, String str) {
        int i7;
        View view;
        View view2;
        View view3;
        View view4;
        CheckBox checkBox;
        CheckBox checkBox2;
        Spinner spinner;
        RadioButton radioButton;
        EditText editText;
        EditText editText2;
        EditText editText3;
        EditText editText4;
        RadioButton radioButton2;
        RadioButton radioButton3;
        RadioButton radioButton4;
        RadioButton radioButton5;
        RadioButton radioButton6;
        RadioButton radioButton7;
        RadioButton radioButton8;
        RadioButton radioButton9;
        RadioButton radioButton10;
        RadioButton radioButton11;
        RadioButton radioButton12;
        RadioButton radioButton13;
        RadioButton radioButton14;
        RadioButton radioButton15;
        String str2;
        CheckBox checkBox3;
        RadioGroup radioGroup;
        CheckBox checkBox4;
        EditText editText5;
        RadioButton radioButton16;
        RadioGroup radioGroup2;
        RadioButton radioButton17;
        CheckBox checkBox5;
        EditText editText6;
        CheckBox checkBox6;
        EditText editText7;
        EditText editText8;
        EditText editText9;
        ArrayList arrayList;
        ArrayList arrayList2;
        EditText editText10;
        EditText editText11;
        EditText editText12;
        boolean z6;
        boolean z7;
        ?? r22;
        EditText editText13;
        CheckBox checkBox7;
        EditText editText14;
        CheckBox checkBox8;
        CheckBox checkBox9;
        EditText editText15;
        EditText editText16;
        EditText editText17;
        Spinner spinner2;
        ArrayList arrayList3;
        EditText editText18;
        EditText editText19;
        String[] strArr;
        String str3;
        EditText editText20;
        EditText editText21;
        ArrayList arrayList4;
        String str4;
        ArrayList arrayList5;
        ArrayList arrayList6;
        EditText editText22;
        String[] strArr2;
        EditText editText23;
        EditText editText24;
        CheckBox checkBox10;
        CheckBox checkBox11;
        CheckBox checkBox12;
        ArrayList arrayList7;
        View inflate = LayoutInflater.from(getContext()).inflate(g4.q.dialog_log_profile_assistant, (ViewGroup) null, false);
        EditText editText25 = (EditText) inflate.findViewById(g4.p.edit_profile_name);
        EditText editText26 = (EditText) inflate.findViewById(g4.p.edit_profile_tag);
        EditText editText27 = (EditText) inflate.findViewById(g4.p.edit_profile_pattern);
        RadioGroup radioGroup3 = (RadioGroup) inflate.findViewById(g4.p.radio_condition_type_group);
        RadioButton radioButton18 = (RadioButton) inflate.findViewById(g4.p.radio_condition_none);
        RadioButton radioButton19 = (RadioButton) inflate.findViewById(g4.p.radio_condition_variable);
        CheckBox checkBox13 = (CheckBox) inflate.findViewById(g4.p.checkbox_condition_delay);
        CheckBox checkBox14 = (CheckBox) inflate.findViewById(g4.p.checkbox_condition_cooldown);
        View findViewById = inflate.findViewById(g4.p.layout_condition_variable);
        View findViewById2 = inflate.findViewById(g4.p.layout_condition_delay);
        View findViewById3 = inflate.findViewById(g4.p.layout_condition_cooldown);
        EditText editText28 = (EditText) inflate.findViewById(g4.p.edit_condition_delay_seconds);
        EditText editText29 = (EditText) inflate.findViewById(g4.p.edit_condition_cooldown_seconds);
        CheckBox checkBox15 = (CheckBox) inflate.findViewById(g4.p.checkbox_condition_combo);
        View findViewById4 = inflate.findViewById(g4.p.layout_condition_combo);
        EditText editText30 = (EditText) inflate.findViewById(g4.p.edit_condition_combo_seconds);
        EditText editText31 = (EditText) inflate.findViewById(g4.p.edit_condition_combo_count);
        EditText editText32 = (EditText) inflate.findViewById(g4.p.edit_condition_variable_name);
        Spinner spinner3 = (Spinner) inflate.findViewById(g4.p.spinner_condition_operator);
        EditText editText33 = (EditText) inflate.findViewById(g4.p.edit_condition_value);
        RadioGroup radioGroup4 = (RadioGroup) inflate.findViewById(g4.p.radio_assistant_action_group);
        RadioButton radioButton20 = (RadioButton) inflate.findViewById(g4.p.radio_assistant_action_none);
        RadioButton radioButton21 = (RadioButton) inflate.findViewById(g4.p.radio_assistant_action_activate_cast);
        RadioButton radioButton22 = (RadioButton) inflate.findViewById(g4.p.radio_assistant_action_drive_enjoy_mode);
        RadioButton radioButton23 = (RadioButton) inflate.findViewById(g4.p.radio_assistant_action_open_360_camera);
        RadioButton radioButton24 = (RadioButton) inflate.findViewById(g4.p.radio_assistant_action_instrument_cast);
        RadioButton radioButton25 = (RadioButton) inflate.findViewById(g4.p.radio_assistant_action_cluster_enlarge);
        RadioButton radioButton26 = (RadioButton) inflate.findViewById(g4.p.radio_assistant_action_cluster_shrink);
        RadioButton radioButton27 = (RadioButton) inflate.findViewById(g4.p.radio_assistant_action_close_windows);
        RadioButton radioButton28 = (RadioButton) inflate.findViewById(g4.p.radio_assistant_action_open_sunblind);
        RadioButton radioButton29 = (RadioButton) inflate.findViewById(g4.p.radio_assistant_action_close_sunblind);
        RadioButton radioButton30 = (RadioButton) inflate.findViewById(g4.p.radio_assistant_action_epb_release);
        RadioButton radioButton31 = (RadioButton) inflate.findViewById(g4.p.radio_assistant_action_epb_apply);
        RadioButton radioButton32 = (RadioButton) inflate.findViewById(g4.p.radio_assistant_action_radar_open);
        RadioButton radioButton33 = (RadioButton) inflate.findViewById(g4.p.radio_assistant_action_radar_close);
        RadioButton radioButton34 = (RadioButton) inflate.findViewById(g4.p.radio_assistant_action_open_ac_ui);
        RadioButton radioButton35 = (RadioButton) inflate.findViewById(g4.p.radio_assistant_action_close_ac_ui);
        RadioButton radioButton36 = (RadioButton) inflate.findViewById(g4.p.radio_assistant_action_open_systemui_ac_ui);
        RadioButton radioButton37 = (RadioButton) inflate.findViewById(g4.p.radio_assistant_action_close_systemui_ac_ui);
        CheckBox checkBox16 = (CheckBox) inflate.findViewById(g4.p.radio_assistant_action_variable);
        View findViewById5 = inflate.findViewById(g4.p.layout_variable_action);
        EditText editText34 = editText29;
        RadioGroup radioGroup5 = (RadioGroup) inflate.findViewById(g4.p.radio_variable_action_group);
        RadioButton radioButton38 = (RadioButton) inflate.findViewById(g4.p.radio_variable_action_set);
        RadioButton radioButton39 = (RadioButton) inflate.findViewById(g4.p.radio_variable_action_clear);
        View findViewById6 = inflate.findViewById(g4.p.layout_variable_set);
        View findViewById7 = inflate.findViewById(g4.p.layout_variable_clear);
        EditText editText35 = (EditText) inflate.findViewById(g4.p.edit_variable_name);
        EditText editText36 = (EditText) inflate.findViewById(g4.p.edit_variable_value);
        EditText editText37 = (EditText) inflate.findViewById(g4.p.edit_variable_name_clear);
        ImageButton imageButton = (ImageButton) inflate.findViewById(g4.p.button_add_variable_set);
        ImageButton imageButton2 = (ImageButton) inflate.findViewById(g4.p.button_add_variable_clear);
        ArrayList arrayList8 = new ArrayList();
        ArrayList arrayList9 = new ArrayList();
        ArrayList arrayList10 = new ArrayList();
        ArrayAdapter arrayAdapter = new ArrayAdapter(requireContext(), R.layout.simple_spinner_item, new String[]{"=", ">", "<"});
        arrayAdapter.setDropDownViewResource(R.layout.simple_spinner_dropdown_item);
        spinner3.setAdapter((SpinnerAdapter) arrayAdapter);
        if (findViewById6 instanceof LinearLayout) {
            LinearLayout linearLayout = (LinearLayout) findViewById6;
            LinearLayout linearLayout2 = new LinearLayout(getContext());
            int i8 = 0;
            linearLayout2.setOrientation(0);
            linearLayout2.setGravity(16);
            while (linearLayout.getChildCount() > 0) {
                View childAt = linearLayout.getChildAt(i8);
                linearLayout.removeViewAt(i8);
                linearLayout2.addView(childAt);
                i8 = 0;
            }
            linearLayout.setOrientation(1);
            linearLayout.addView(linearLayout2);
        }
        if (findViewById7 instanceof LinearLayout) {
            LinearLayout linearLayout3 = (LinearLayout) findViewById7;
            LinearLayout linearLayout4 = new LinearLayout(getContext());
            int i9 = 0;
            linearLayout4.setOrientation(0);
            linearLayout4.setGravity(16);
            while (linearLayout3.getChildCount() > 0) {
                View childAt2 = linearLayout3.getChildAt(i9);
                linearLayout3.removeViewAt(i9);
                linearLayout4.addView(childAt2);
                i9 = 0;
            }
            i7 = 1;
            linearLayout3.setOrientation(1);
            linearLayout3.addView(linearLayout4);
        } else {
            i7 = 1;
        }
        radioGroup3.setOnCheckedChangeListener(new y(i7, findViewById));
        checkBox13.setOnCheckedChangeListener(new c0(4, findViewById2));
        checkBox14.setOnCheckedChangeListener(new c0(5, findViewById3));
        if (checkBox15 != null) {
            checkBox15.setOnCheckedChangeListener(new c0(6, findViewById4));
        }
        radioGroup5.setOnCheckedChangeListener(new d0(findViewById6, findViewById7, 1));
        checkBox16.setOnCheckedChangeListener(new c0(7, findViewById5));
        if (imageButton != null) {
            view = findViewById4;
            view2 = findViewById3;
            view3 = findViewById2;
            view4 = findViewById;
            checkBox = checkBox14;
            checkBox2 = checkBox13;
            spinner = spinner3;
            radioButton = radioButton19;
            editText = editText30;
            editText2 = editText31;
            editText3 = editText32;
            radioGroup2 = radioGroup5;
            checkBox3 = checkBox16;
            editText5 = editText28;
            checkBox4 = checkBox15;
            editText4 = editText33;
            radioButton16 = radioButton20;
            radioButton17 = radioButton22;
            radioButton2 = radioButton24;
            radioButton3 = radioButton25;
            radioButton4 = radioButton26;
            radioButton5 = radioButton27;
            radioButton6 = radioButton28;
            radioButton7 = radioButton29;
            radioButton8 = radioButton30;
            radioButton9 = radioButton31;
            radioButton10 = radioButton32;
            radioButton11 = radioButton33;
            radioButton12 = radioButton34;
            radioButton13 = radioButton35;
            radioButton14 = radioButton36;
            radioButton15 = radioButton37;
            str2 = "<";
            radioGroup = radioGroup3;
            imageButton.setOnClickListener(new e0(checkBox16, findViewById5, findViewById6, findViewById7, inflate, arrayList8, arrayList9, 1));
        } else {
            view = findViewById4;
            view2 = findViewById3;
            view3 = findViewById2;
            view4 = findViewById;
            checkBox = checkBox14;
            checkBox2 = checkBox13;
            spinner = spinner3;
            radioButton = radioButton19;
            editText = editText30;
            editText2 = editText31;
            editText3 = editText32;
            editText4 = editText33;
            radioButton2 = radioButton24;
            radioButton3 = radioButton25;
            radioButton4 = radioButton26;
            radioButton5 = radioButton27;
            radioButton6 = radioButton28;
            radioButton7 = radioButton29;
            radioButton8 = radioButton30;
            radioButton9 = radioButton31;
            radioButton10 = radioButton32;
            radioButton11 = radioButton33;
            radioButton12 = radioButton34;
            radioButton13 = radioButton35;
            radioButton14 = radioButton36;
            radioButton15 = radioButton37;
            str2 = "<";
            checkBox3 = checkBox16;
            radioGroup = radioGroup3;
            checkBox4 = checkBox15;
            editText5 = editText28;
            radioButton16 = radioButton20;
            radioGroup2 = radioGroup5;
            radioButton17 = radioButton22;
        }
        if (imageButton2 != null) {
            imageButton2.setOnClickListener(new f(checkBox3, findViewById5, findViewById6, findViewById7, inflate, arrayList10, 1));
        }
        if (logAssistantProfile != null) {
            editText9 = editText25;
            editText9.setText(logAssistantProfile.getName());
            editText8 = editText26;
            editText8.setText(logAssistantProfile.getTag());
            editText7 = editText27;
            editText7.setText(logAssistantProfile.getPattern());
            String action = logAssistantProfile.getAction();
            if ("none".equalsIgnoreCase(action)) {
                z7 = true;
                if (radioButton16 != null) {
                    radioButton16.setChecked(true);
                }
            } else {
                z7 = true;
                if ("drive_enjoy_mode".equalsIgnoreCase(action)) {
                    if (radioButton17 != null) {
                        radioButton17.setChecked(true);
                    }
                } else if ("open_ac_ui".equalsIgnoreCase(action)) {
                    if (radioButton12 != null) {
                        radioButton12.setChecked(true);
                    }
                } else if ("close_ac_ui".equalsIgnoreCase(action)) {
                    RadioButton radioButton40 = radioButton13;
                    if (radioButton40 != null) {
                        radioButton40.setChecked(true);
                    }
                } else if ("open_systemui_ac_ui".equalsIgnoreCase(action)) {
                    RadioButton radioButton41 = radioButton14;
                    if (radioButton41 != null) {
                        radioButton41.setChecked(true);
                    }
                } else if ("close_systemui_ac_ui".equalsIgnoreCase(action)) {
                    RadioButton radioButton42 = radioButton15;
                    if (radioButton42 != null) {
                        radioButton42.setChecked(true);
                    }
                } else if ("open_360_camera".equalsIgnoreCase(action)) {
                    if (radioButton23 != null) {
                        radioButton23.setChecked(true);
                    }
                } else if ("instrument_cast".equalsIgnoreCase(action)) {
                    radioButton2.setChecked(true);
                } else if ("cluster_enlarge".equalsIgnoreCase(action)) {
                    radioButton3.setChecked(true);
                } else if ("cluster_shrink".equalsIgnoreCase(action)) {
                    RadioButton radioButton43 = radioButton4;
                    if (radioButton43 != null) {
                        radioButton43.setChecked(true);
                    }
                } else if ("close_windows".equalsIgnoreCase(action)) {
                    RadioButton radioButton44 = radioButton5;
                    if (radioButton44 != null) {
                        radioButton44.setChecked(true);
                    }
                } else if ("open_sunblind".equalsIgnoreCase(action)) {
                    RadioButton radioButton45 = radioButton6;
                    if (radioButton45 != null) {
                        radioButton45.setChecked(true);
                    }
                } else if ("close_sunblind".equalsIgnoreCase(action)) {
                    RadioButton radioButton46 = radioButton7;
                    if (radioButton46 != null) {
                        radioButton46.setChecked(true);
                    }
                } else if ("epb_release".equalsIgnoreCase(action)) {
                    RadioButton radioButton47 = radioButton8;
                    if (radioButton47 != null) {
                        radioButton47.setChecked(true);
                    }
                } else if ("epb_apply".equalsIgnoreCase(action)) {
                    RadioButton radioButton48 = radioButton9;
                    if (radioButton48 != null) {
                        radioButton48.setChecked(true);
                    }
                } else if ("radar_open".equalsIgnoreCase(action)) {
                    RadioButton radioButton49 = radioButton10;
                    if (radioButton49 != null) {
                        radioButton49.setChecked(true);
                    }
                } else if ("radar_close".equalsIgnoreCase(action)) {
                    RadioButton radioButton50 = radioButton11;
                    if (radioButton50 != null) {
                        radioButton50.setChecked(true);
                    }
                } else {
                    radioButton21.setChecked(true);
                }
            }
            if ("variable".equalsIgnoreCase(logAssistantProfile.getConditionType())) {
                radioButton.setChecked(z7);
                r22 = 0;
                view4.setVisibility(0);
            } else {
                r22 = 0;
                radioButton18.setChecked(z7);
                view4.setVisibility(8);
            }
            if (logAssistantProfile.getDelaySeconds() > 0) {
                CheckBox checkBox17 = checkBox2;
                checkBox17.setChecked(z7);
                view3.setVisibility(r22);
                editText13 = editText5;
                editText13.setText(String.valueOf(logAssistantProfile.getDelaySeconds()));
                checkBox7 = checkBox17;
            } else {
                editText13 = editText5;
                CheckBox checkBox18 = checkBox2;
                checkBox18.setChecked(r22);
                view3.setVisibility(8);
                checkBox7 = checkBox18;
            }
            if (logAssistantProfile.getCooldownSeconds() > 0) {
                CheckBox checkBox19 = checkBox;
                checkBox19.setChecked(z7);
                view2.setVisibility(r22);
                editText14 = editText34;
                editText14.setText(String.valueOf(logAssistantProfile.getCooldownSeconds()));
                checkBox8 = checkBox19;
            } else {
                editText14 = editText34;
                CheckBox checkBox20 = checkBox;
                checkBox20.setChecked(r22);
                view2.setVisibility(8);
                checkBox8 = checkBox20;
            }
            if (logAssistantProfile.getComboSeconds() <= 0 || logAssistantProfile.getComboCount() <= 0) {
                checkBox9 = checkBox4;
                View view5 = view;
                editText15 = editText;
                editText16 = editText2;
                if (checkBox9 != null) {
                    checkBox9.setChecked(false);
                }
                if (view5 != null) {
                    view5.setVisibility(8);
                }
            } else {
                checkBox9 = checkBox4;
                if (checkBox4 != null) {
                    checkBox9.setChecked(true);
                }
                View view6 = view;
                if (view6 != null) {
                    view6.setVisibility(0);
                }
                editText15 = editText;
                if (editText15 != null) {
                    editText15.setText(String.valueOf(logAssistantProfile.getComboSeconds()));
                }
                editText16 = editText2;
                if (editText16 != null) {
                    editText16.setText(String.valueOf(logAssistantProfile.getComboCount()));
                }
            }
            String conditionVariable = logAssistantProfile.getConditionVariable();
            if (conditionVariable == null || conditionVariable.isEmpty()) {
                editText11 = editText3;
            } else {
                editText11 = editText3;
                editText11.setText(stripDollarPrefix(conditionVariable));
            }
            String conditionOperator = logAssistantProfile.getConditionOperator();
            if (conditionOperator == null || conditionOperator.isEmpty() || "=".equals(conditionOperator)) {
                editText17 = editText16;
                spinner2 = spinner;
                spinner2.setSelection(0);
            } else if (">".equals(conditionOperator)) {
                editText17 = editText16;
                spinner2 = spinner;
                spinner2.setSelection(1);
            } else {
                editText17 = editText16;
                spinner2 = spinner;
                if (str2.equals(conditionOperator)) {
                    spinner2.setSelection(2);
                } else {
                    spinner2.setSelection(0);
                }
            }
            editText6 = editText13;
            editText12 = editText4;
            editText12.setText(logAssistantProfile.getConditionValue());
            String variableName = logAssistantProfile.getVariableName();
            String variableOp = logAssistantProfile.getVariableOp();
            String variableValue = logAssistantProfile.getVariableValue();
            if (variableName == null || variableName.isEmpty()) {
                spinner = spinner2;
                editText34 = editText14;
                editText = editText15;
                checkBox5 = checkBox9;
                checkBox = checkBox8;
                checkBox2 = checkBox7;
                checkBox6 = checkBox3;
                arrayList = arrayList8;
                arrayList2 = arrayList9;
                findViewById5.setVisibility(8);
            } else {
                spinner = spinner2;
                checkBox2 = checkBox7;
                CheckBox checkBox21 = checkBox3;
                checkBox21.setChecked(true);
                String str5 = "变量名称 $";
                editText34 = editText14;
                if (variableOp == null || !variableOp.equalsIgnoreCase("clear")) {
                    editText = editText15;
                    checkBox5 = checkBox9;
                    checkBox = checkBox8;
                    checkBox6 = checkBox21;
                    arrayList3 = arrayList10;
                    radioButton38.setChecked(true);
                    findViewById6.setVisibility(0);
                    findViewById7.setVisibility(8);
                    String[] split = variableName.split(";");
                    String str6 = h0.FRAGMENT_ENCODE_SET;
                    String[] split2 = (variableValue == null ? h0.FRAGMENT_ENCODE_SET : variableValue).split(";");
                    if (split.length > 0) {
                        editText18 = editText35;
                        editText18.setText(stripDollarPrefix(split[0]));
                        editText19 = editText36;
                        editText19.setText(split2.length > 0 ? split2[0] : h0.FRAGMENT_ENCODE_SET);
                    } else {
                        editText18 = editText35;
                        editText19 = editText36;
                    }
                    if (split.length > 1 && (findViewById6 instanceof LinearLayout)) {
                        LinearLayout linearLayout5 = (LinearLayout) findViewById6;
                        Context context = inflate.getContext();
                        int i10 = 1;
                        while (i10 < split.length) {
                            String str7 = split[i10];
                            if (str7 == null || str7.trim().isEmpty()) {
                                strArr = split;
                                str3 = str6;
                                editText20 = editText19;
                                editText21 = editText18;
                                arrayList4 = arrayList3;
                                str4 = str5;
                                arrayList5 = arrayList8;
                                arrayList6 = arrayList9;
                            } else {
                                strArr = split;
                                LinearLayout linearLayout6 = new LinearLayout(context);
                                str3 = str6;
                                linearLayout6.setOrientation(0);
                                linearLayout6.setGravity(16);
                                TextView textView = new TextView(context);
                                textView.setText(str5);
                                linearLayout6.addView(textView);
                                EditText editText38 = new EditText(context);
                                editText20 = editText19;
                                editText21 = editText18;
                                arrayList4 = arrayList3;
                                str4 = str5;
                                editText38.setLayoutParams(new LinearLayout.LayoutParams(0, -2, 1.0f));
                                editText38.setInputType(1);
                                editText38.setText(stripDollarPrefix(str7));
                                linearLayout6.addView(editText38);
                                TextView textView2 = new TextView(context);
                                LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(-2, -2);
                                layoutParams.setMarginStart(8);
                                textView2.setLayoutParams(layoutParams);
                                textView2.setText("赋值");
                                linearLayout6.addView(textView2);
                                EditText editText39 = new EditText(context);
                                LinearLayout.LayoutParams layoutParams2 = new LinearLayout.LayoutParams(0, -2, 1.0f);
                                layoutParams2.setMarginStart(4);
                                editText39.setLayoutParams(layoutParams2);
                                editText39.setInputType(androidx.fragment.app.h1.TRANSIT_FRAGMENT_CLOSE);
                                editText39.setText(i10 < split2.length ? split2[i10] : str3);
                                linearLayout6.addView(editText39);
                                ImageButton imageButton3 = new ImageButton(context);
                                imageButton3.setImageResource(R.drawable.ic_delete);
                                imageButton3.setBackground(null);
                                imageButton3.setOnClickListener(new e(linearLayout5, linearLayout6, arrayList8, editText38, arrayList9, editText39, 3));
                                linearLayout6.addView(imageButton3);
                                linearLayout5.addView(linearLayout6);
                                arrayList5 = arrayList8;
                                arrayList5.add(editText38);
                                arrayList6 = arrayList9;
                                arrayList6.add(editText39);
                            }
                            i10++;
                            arrayList9 = arrayList6;
                            arrayList8 = arrayList5;
                            split = strArr;
                            str5 = str4;
                            str6 = str3;
                            editText18 = editText21;
                            editText19 = editText20;
                            arrayList3 = arrayList4;
                        }
                    }
                    editText36 = editText19;
                    editText35 = editText18;
                } else {
                    radioButton39.setChecked(true);
                    findViewById6.setVisibility(8);
                    findViewById7.setVisibility(0);
                    String[] split3 = variableName.split(";");
                    if (split3.length > 0) {
                        editText22 = editText37;
                        editText22.setText(stripDollarPrefix(split3[0]));
                    } else {
                        editText22 = editText37;
                    }
                    if (split3.length > 1 && (findViewById7 instanceof LinearLayout)) {
                        LinearLayout linearLayout7 = (LinearLayout) findViewById7;
                        Context context2 = inflate.getContext();
                        int i11 = 1;
                        checkBox8 = checkBox8;
                        while (i11 < split3.length) {
                            String str8 = split3[i11];
                            if (str8 == null || str8.trim().isEmpty()) {
                                strArr2 = split3;
                                editText23 = editText22;
                                editText24 = editText15;
                                checkBox10 = checkBox9;
                                checkBox11 = checkBox8;
                                checkBox12 = checkBox21;
                                arrayList7 = arrayList10;
                            } else {
                                strArr2 = split3;
                                LinearLayout linearLayout8 = new LinearLayout(context2);
                                editText23 = editText22;
                                linearLayout8.setOrientation(0);
                                linearLayout8.setGravity(16);
                                TextView textView3 = new TextView(context2);
                                textView3.setText("变量名称 $");
                                linearLayout8.addView(textView3);
                                EditText editText40 = new EditText(context2);
                                editText24 = editText15;
                                checkBox10 = checkBox9;
                                checkBox11 = checkBox8;
                                checkBox12 = checkBox21;
                                editText40.setLayoutParams(new LinearLayout.LayoutParams(0, -2, 1.0f));
                                editText40.setInputType(1);
                                editText40.setText(stripDollarPrefix(str8));
                                linearLayout8.addView(editText40);
                                ImageButton imageButton4 = new ImageButton(context2);
                                imageButton4.setImageResource(R.drawable.ic_delete);
                                imageButton4.setBackground(null);
                                imageButton4.setOnClickListener(new a0(linearLayout7, linearLayout8, arrayList10, editText40, 4));
                                linearLayout8.addView(imageButton4);
                                linearLayout7.addView(linearLayout8);
                                arrayList7 = arrayList10;
                                arrayList7.add(editText40);
                            }
                            i11++;
                            arrayList10 = arrayList7;
                            split3 = strArr2;
                            checkBox9 = checkBox10;
                            checkBox21 = checkBox12;
                            editText22 = editText23;
                            checkBox8 = checkBox11;
                            editText15 = editText24;
                        }
                    }
                    editText37 = editText22;
                    editText = editText15;
                    checkBox5 = checkBox9;
                    checkBox = checkBox8;
                    checkBox6 = checkBox21;
                    arrayList3 = arrayList10;
                }
                arrayList10 = arrayList3;
                arrayList = arrayList8;
                arrayList2 = arrayList9;
            }
            editText10 = editText17;
            z6 = false;
        } else {
            checkBox5 = checkBox4;
            editText6 = editText5;
            checkBox6 = checkBox3;
            editText7 = editText27;
            editText8 = editText26;
            editText9 = editText25;
            arrayList = arrayList8;
            arrayList2 = arrayList9;
            View view7 = view4;
            editText10 = editText2;
            editText11 = editText3;
            editText12 = editText4;
            if (str != null) {
                if ("瑞虎9上电".equals(str)) {
                    editText9.setText(str);
                    editText8.setText("vehicledevice|VehicleHal");
                    editText7.setText("power_state:2");
                } else if ("艾瑞泽8上电".equals(str)) {
                    editText9.setText(str);
                    editText8.setText("AmbientLightService");
                    editText7.setText("onPowerModelChange=2");
                } else if ("瑶光上电".equals(str)) {
                    editText9.setText(str);
                    editText8.setText("AmbientLightService");
                    editText7.setText("onPowerModelChange=2");
                } else if ("瑶光下电".equals(str)) {
                    editText9.setText(str);
                    editText8.setText("AmbientLightService");
                    editText7.setText("onPowerModelChange=0");
                }
                view7.setVisibility(8);
                findViewById5.setVisibility(8);
                z6 = false;
                findViewById6.setVisibility(0);
                findViewById7.setVisibility(8);
            } else {
                z6 = false;
                view7.setVisibility(8);
                findViewById5.setVisibility(8);
                findViewById6.setVisibility(0);
                findViewById7.setVisibility(8);
            }
        }
        androidx.appcompat.app.n nVar = new androidx.appcompat.app.n(requireContext());
        nVar.t(logAssistantProfile == null ? "新建配置文件" : "编辑配置文件");
        nVar.u(inflate);
        nVar.o("取消", new g4.g(12));
        EditText editText41 = editText9;
        EditText editText42 = editText8;
        EditText editText43 = editText;
        RadioGroup radioGroup6 = radioGroup;
        boolean z8 = z6;
        CheckBox checkBox22 = checkBox5;
        EditText editText44 = editText11;
        EditText editText45 = editText35;
        CheckBox checkBox23 = checkBox;
        EditText editText46 = editText8;
        EditText editText47 = editText12;
        EditText editText48 = editText9;
        CheckBox checkBox24 = checkBox2;
        EditText editText49 = editText6;
        CheckBox checkBox25 = checkBox6;
        ArrayList arrayList11 = arrayList10;
        nVar.r("保存", new b0(this, editText41, editText42, editText7, radioGroup4, radioGroup6, editText44, spinner, editText47, checkBox24, editText49, checkBox23, editText34, checkBox22, editText43, editText10, checkBox25, radioGroup2, editText37, arrayList11, editText45, editText36, arrayList, arrayList2, logAssistantProfile, str, 1));
        androidx.appcompat.app.o e7 = nVar.e();
        e7.show();
        View[] viewArr = new View[3];
        viewArr[z8 ? 1 : 0] = editText48;
        viewArr[1] = editText46;
        viewArr[2] = editText7;
        DialogImeNavBarHelper.install(e7, viewArr);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v35 */
    /* JADX WARN: Type inference failed for: r0v36, types: [boolean, int] */
    /* JADX WARN: Type inference failed for: r0v64 */
    /* JADX WARN: Type inference failed for: r30v1, types: [com.navi.tool.ui.log.h] */
    private void showEditMediaProfileDialogInternal(final LogMediaProfile logMediaProfile) {
        View view;
        RadioGroup radioGroup;
        int i7;
        int i8;
        View view2;
        RadioGroup radioGroup2;
        RadioGroup radioGroup3;
        Spinner spinner;
        View view3;
        View view4;
        View view5;
        CheckBox checkBox;
        CheckBox checkBox2;
        CheckBox checkBox3;
        CheckBox checkBox4;
        String str;
        View view6;
        View view7;
        View view8;
        EditText editText;
        EditText editText2;
        RadioButton radioButton;
        RadioGroup radioGroup4;
        RadioButton radioButton2;
        RadioButton radioButton3;
        RadioButton radioButton4;
        RadioButton radioButton5;
        RadioButton radioButton6;
        RadioButton radioButton7;
        RadioButton radioButton8;
        RadioButton radioButton9;
        RadioButton radioButton10;
        RadioButton radioButton11;
        RadioButton radioButton12;
        RadioButton radioButton13;
        EditText editText3;
        EditText editText4;
        EditText editText5;
        View view9;
        RecyclerView recyclerView;
        int i9;
        CheckBox checkBox5;
        EditText editText6;
        EditText editText7;
        EditText editText8;
        ArrayList arrayList;
        ArrayList arrayList2;
        Spinner spinner2;
        CheckBox checkBox6;
        EditText editText9;
        boolean z6;
        boolean z7;
        ?? r02;
        EditText editText10;
        CheckBox checkBox7;
        EditText editText11;
        CheckBox checkBox8;
        CheckBox checkBox9;
        EditText editText12;
        EditText editText13;
        EditText editText14;
        Spinner spinner3;
        Spinner spinner4;
        EditText editText15;
        EditText editText16;
        String[] strArr;
        String str2;
        String str3;
        EditText editText17;
        EditText editText18;
        ArrayList arrayList3;
        ArrayList arrayList4;
        ArrayList arrayList5;
        EditText editText19;
        String[] strArr2;
        CheckBox checkBox10;
        EditText editText20;
        CheckBox checkBox11;
        EditText editText21;
        CheckBox checkBox12;
        ArrayList arrayList6;
        View inflate = LayoutInflater.from(getContext()).inflate(g4.q.dialog_log_profile_media, (ViewGroup) null, false);
        EditText editText22 = (EditText) inflate.findViewById(g4.p.edit_profile_name);
        EditText editText23 = (EditText) inflate.findViewById(g4.p.edit_profile_tag);
        EditText editText24 = (EditText) inflate.findViewById(g4.p.edit_profile_pattern);
        RadioGroup radioGroup5 = (RadioGroup) inflate.findViewById(g4.p.radio_condition_type_group);
        RadioButton radioButton14 = (RadioButton) inflate.findViewById(g4.p.radio_condition_none);
        RadioButton radioButton15 = (RadioButton) inflate.findViewById(g4.p.radio_condition_variable);
        CheckBox checkBox13 = (CheckBox) inflate.findViewById(g4.p.checkbox_condition_delay);
        CheckBox checkBox14 = (CheckBox) inflate.findViewById(g4.p.checkbox_condition_cooldown);
        View findViewById = inflate.findViewById(g4.p.layout_condition_variable);
        View findViewById2 = inflate.findViewById(g4.p.layout_condition_delay);
        View findViewById3 = inflate.findViewById(g4.p.layout_condition_cooldown);
        EditText editText25 = (EditText) inflate.findViewById(g4.p.edit_condition_delay_seconds);
        EditText editText26 = (EditText) inflate.findViewById(g4.p.edit_condition_cooldown_seconds);
        CheckBox checkBox15 = (CheckBox) inflate.findViewById(g4.p.checkbox_condition_combo);
        View findViewById4 = inflate.findViewById(g4.p.layout_condition_combo);
        EditText editText27 = (EditText) inflate.findViewById(g4.p.edit_condition_combo_seconds);
        EditText editText28 = (EditText) inflate.findViewById(g4.p.edit_condition_combo_count);
        EditText editText29 = (EditText) inflate.findViewById(g4.p.edit_condition_variable_name);
        Spinner spinner5 = (Spinner) inflate.findViewById(g4.p.spinner_condition_operator);
        EditText editText30 = (EditText) inflate.findViewById(g4.p.edit_condition_value);
        RadioGroup radioGroup6 = (RadioGroup) inflate.findViewById(g4.p.radio_media_action_group);
        RadioButton radioButton16 = (RadioButton) inflate.findViewById(g4.p.radio_media_action_none);
        RadioButton radioButton17 = (RadioButton) inflate.findViewById(g4.p.radio_media_action_play_file);
        RadioButton radioButton18 = (RadioButton) inflate.findViewById(g4.p.radio_media_action_qq_music_car);
        RadioButton radioButton19 = (RadioButton) inflate.findViewById(g4.p.radio_media_action_kuwo_music_car);
        RadioButton radioButton20 = (RadioButton) inflate.findViewById(g4.p.radio_media_action_netease_music_car);
        RadioButton radioButton21 = (RadioButton) inflate.findViewById(g4.p.radio_media_action_luna_music_car);
        RadioButton radioButton22 = (RadioButton) inflate.findViewById(g4.p.radio_media_action_auto_search);
        RadioButton radioButton23 = (RadioButton) inflate.findViewById(g4.p.radio_media_action_auto_favorite);
        RadioButton radioButton24 = (RadioButton) inflate.findViewById(g4.p.radio_media_action_cycle_loop_mode);
        RadioButton radioButton25 = (RadioButton) inflate.findViewById(g4.p.radio_media_action_next);
        RadioButton radioButton26 = (RadioButton) inflate.findViewById(g4.p.radio_media_action_previous);
        RadioButton radioButton27 = (RadioButton) inflate.findViewById(g4.p.radio_media_action_play);
        RadioButton radioButton28 = (RadioButton) inflate.findViewById(g4.p.radio_media_action_pause);
        RadioButton radioButton29 = (RadioButton) inflate.findViewById(g4.p.radio_media_action_toggle);
        CheckBox checkBox16 = (CheckBox) inflate.findViewById(g4.p.radio_media_action_variable);
        View findViewById5 = inflate.findViewById(g4.p.layout_variable_action);
        EditText editText31 = editText26;
        RadioGroup radioGroup7 = (RadioGroup) inflate.findViewById(g4.p.radio_variable_action_group);
        RadioButton radioButton30 = (RadioButton) inflate.findViewById(g4.p.radio_variable_action_set);
        RadioButton radioButton31 = (RadioButton) inflate.findViewById(g4.p.radio_variable_action_clear);
        View findViewById6 = inflate.findViewById(g4.p.layout_variable_set);
        View findViewById7 = inflate.findViewById(g4.p.layout_variable_clear);
        EditText editText32 = (EditText) inflate.findViewById(g4.p.edit_variable_name);
        EditText editText33 = (EditText) inflate.findViewById(g4.p.edit_variable_value);
        EditText editText34 = (EditText) inflate.findViewById(g4.p.edit_variable_name_clear);
        ImageButton imageButton = (ImageButton) inflate.findViewById(g4.p.button_add_variable_set);
        ImageButton imageButton2 = (ImageButton) inflate.findViewById(g4.p.button_add_variable_clear);
        ArrayList arrayList7 = new ArrayList();
        ArrayList arrayList8 = new ArrayList();
        ArrayList arrayList9 = new ArrayList();
        RecyclerView recyclerView2 = (RecyclerView) inflate.findViewById(g4.p.recycler_select_media);
        getContext();
        recyclerView2.setLayoutManager(new LinearLayoutManager(1));
        recyclerView2.setItemAnimator(null);
        recyclerView2.setNestedScrollingEnabled(false);
        if (recyclerView2.getItemDecorationCount() == 0) {
            androidx.recyclerview.widget.y yVar = new androidx.recyclerview.widget.y(requireContext());
            Context requireContext = requireContext();
            radioGroup = radioGroup7;
            int i10 = g4.m.divider_dark;
            Object obj = g0.g.f6423a;
            int a7 = g0.d.a(requireContext, i10);
            final int i11 = (int) ((requireContext().getResources().getDisplayMetrics().density * 1.0f) + 0.5f);
            view = findViewById4;
            yVar.f2710a = new ColorDrawable(a7) { // from class: com.navi.tool.ui.log.LogLinkFragment.3
                @Override // android.graphics.drawable.Drawable
                public int getIntrinsicHeight() {
                    return i11;
                }
            };
            recyclerView2.g(yVar);
        } else {
            view = findViewById4;
            radioGroup = radioGroup7;
        }
        ArrayAdapter arrayAdapter = new ArrayAdapter(requireContext(), R.layout.simple_spinner_item, new String[]{"=", ">", "<"});
        arrayAdapter.setDropDownViewResource(R.layout.simple_spinner_dropdown_item);
        spinner5.setAdapter((SpinnerAdapter) arrayAdapter);
        if (findViewById6 instanceof LinearLayout) {
            LinearLayout linearLayout = (LinearLayout) findViewById6;
            LinearLayout linearLayout2 = new LinearLayout(getContext());
            int i12 = 0;
            linearLayout2.setOrientation(0);
            linearLayout2.setGravity(16);
            while (linearLayout.getChildCount() > 0) {
                View childAt = linearLayout.getChildAt(i12);
                linearLayout.removeViewAt(i12);
                linearLayout2.addView(childAt);
                i12 = 0;
            }
            linearLayout.setOrientation(1);
            linearLayout.addView(linearLayout2);
        }
        if (findViewById7 instanceof LinearLayout) {
            LinearLayout linearLayout3 = (LinearLayout) findViewById7;
            LinearLayout linearLayout4 = new LinearLayout(getContext());
            i7 = 0;
            linearLayout4.setOrientation(0);
            linearLayout4.setGravity(16);
            while (linearLayout3.getChildCount() > 0) {
                View childAt2 = linearLayout3.getChildAt(0);
                linearLayout3.removeViewAt(0);
                linearLayout4.addView(childAt2);
            }
            i8 = 1;
            linearLayout3.setOrientation(1);
            linearLayout3.addView(linearLayout4);
        } else {
            i7 = 0;
            i8 = 1;
        }
        radioGroup5.setOnCheckedChangeListener(new y(i7, findViewById));
        checkBox13.setOnCheckedChangeListener(new c0(i7, findViewById2));
        checkBox14.setOnCheckedChangeListener(new c0(i8, findViewById3));
        int i13 = 2;
        if (checkBox15 != null) {
            view2 = view;
            checkBox15.setOnCheckedChangeListener(new c0(i13, view2));
        } else {
            view2 = view;
        }
        RadioGroup radioGroup8 = radioGroup;
        radioGroup8.setOnCheckedChangeListener(new d0(findViewById6, findViewById7, 0));
        View view10 = view2;
        checkBox16.setOnCheckedChangeListener(new c0(3, findViewById5));
        if (imageButton != null) {
            radioGroup2 = radioGroup5;
            imageButton.setOnClickListener(new e0(checkBox16, findViewById5, findViewById6, findViewById7, inflate, arrayList7, arrayList8, 0));
        } else {
            radioGroup2 = radioGroup5;
        }
        if (imageButton2 != null) {
            spinner = spinner5;
            str = "=";
            editText = editText28;
            editText2 = editText29;
            view4 = findViewById2;
            view5 = findViewById;
            checkBox = checkBox14;
            checkBox2 = checkBox13;
            radioGroup3 = radioGroup8;
            checkBox3 = checkBox16;
            checkBox4 = checkBox15;
            view3 = findViewById3;
            view6 = findViewById6;
            view7 = findViewById7;
            radioGroup4 = radioGroup6;
            radioButton2 = radioButton16;
            radioButton3 = radioButton18;
            radioButton4 = radioButton21;
            radioButton5 = radioButton22;
            radioButton6 = radioButton23;
            radioButton7 = radioButton24;
            radioButton8 = radioButton25;
            radioButton9 = radioButton26;
            radioButton10 = radioButton27;
            radioButton11 = radioButton28;
            radioButton12 = radioButton29;
            editText3 = editText32;
            editText4 = editText33;
            editText5 = editText34;
            recyclerView = recyclerView2;
            radioButton = radioButton15;
            view8 = findViewById5;
            radioButton13 = radioButton14;
            view9 = view10;
            i9 = 1;
            imageButton2.setOnClickListener(new f(checkBox16, findViewById5, findViewById6, findViewById7, inflate, arrayList9, 0));
        } else {
            radioGroup3 = radioGroup8;
            spinner = spinner5;
            view3 = findViewById3;
            view4 = findViewById2;
            view5 = findViewById;
            checkBox = checkBox14;
            checkBox2 = checkBox13;
            checkBox3 = checkBox16;
            checkBox4 = checkBox15;
            str = "=";
            view6 = findViewById6;
            view7 = findViewById7;
            view8 = findViewById5;
            editText = editText28;
            editText2 = editText29;
            radioButton = radioButton15;
            radioGroup4 = radioGroup6;
            radioButton2 = radioButton16;
            radioButton3 = radioButton18;
            radioButton4 = radioButton21;
            radioButton5 = radioButton22;
            radioButton6 = radioButton23;
            radioButton7 = radioButton24;
            radioButton8 = radioButton25;
            radioButton9 = radioButton26;
            radioButton10 = radioButton27;
            radioButton11 = radioButton28;
            radioButton12 = radioButton29;
            radioButton13 = radioButton14;
            editText3 = editText32;
            editText4 = editText33;
            editText5 = editText34;
            view9 = view10;
            recyclerView = recyclerView2;
            i9 = 1;
        }
        File file = new File(Environment.getExternalStorageDirectory(), "navitool");
        final File file2 = new File(file, "mp3");
        final File file3 = new File(file, ".mp3");
        final ArrayList arrayList10 = new ArrayList();
        final h1 h1Var = new h1() { // from class: com.navi.tool.ui.log.LogLinkFragment.4
            @Override // androidx.recyclerview.widget.h1
            public int getItemCount() {
                return arrayList10.size();
            }

            @Override // androidx.recyclerview.widget.h1
            public void onBindViewHolder(MediaViewHolder mediaViewHolder, int i14) {
                mediaViewHolder.bind((MediaFileItem) arrayList10.get(i14), arrayList10, this);
            }

            @Override // androidx.recyclerview.widget.h1
            public MediaViewHolder onCreateViewHolder(ViewGroup viewGroup, int i14) {
                return new MediaViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(g4.q.item_media_file, viewGroup, false));
            }
        };
        recyclerView.setAdapter(h1Var);
        recyclerView.post(new g(0, recyclerView));
        final boolean[] zArr = new boolean[i9];
        zArr[0] = false;
        final boolean[] zArr2 = new boolean[i9];
        zArr2[0] = false;
        final boolean[] zArr3 = new boolean[i9];
        zArr3[0] = false;
        RadioGroup radioGroup9 = radioGroup2;
        View view11 = view9;
        RadioGroup radioGroup10 = radioGroup3;
        final ?? r30 = new Runnable() { // from class: com.navi.tool.ui.log.h
            @Override // java.lang.Runnable
            public final void run() {
                LogLinkFragment.this.lambda$showEditMediaProfileDialogInternal$102(zArr, zArr2, zArr3, file2, file3, arrayList10, logMediaProfile, h1Var);
            }
        };
        final RecyclerView recyclerView3 = recyclerView;
        radioGroup4.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() { // from class: com.navi.tool.ui.log.z
            @Override // android.widget.RadioGroup.OnCheckedChangeListener
            public final void onCheckedChanged(RadioGroup radioGroup11, int i14) {
                LogLinkFragment.this.lambda$showEditMediaProfileDialogInternal$103(recyclerView3, zArr, arrayList10, file2, file3, zArr3, r30, radioGroup11, i14);
            }
        });
        recyclerView.setVisibility(radioGroup4.getCheckedRadioButtonId() == g4.p.radio_media_action_play_file ? 0 : 8);
        if (logMediaProfile != null) {
            editText8 = editText22;
            editText8.setText(logMediaProfile.getName());
            editText7 = editText23;
            editText7.setText(logMediaProfile.getTag());
            editText24.setText(logMediaProfile.getPattern());
            String action = logMediaProfile.getAction();
            if ("none".equalsIgnoreCase(action)) {
                z7 = true;
                if (radioButton2 != null) {
                    radioButton2.setChecked(true);
                }
            } else {
                z7 = true;
                if ("qq_music_car".equalsIgnoreCase(action)) {
                    if (radioButton3 != null) {
                        radioButton3.setChecked(true);
                    }
                } else if ("kuwo_music_car".equalsIgnoreCase(action)) {
                    if (radioButton19 != null) {
                        radioButton19.setChecked(true);
                    }
                } else if ("netease_music_car".equalsIgnoreCase(action)) {
                    if (radioButton20 != null) {
                        radioButton20.setChecked(true);
                    }
                } else if ("luna_music_car".equalsIgnoreCase(action)) {
                    RadioButton radioButton32 = radioButton4;
                    if (radioButton32 != null) {
                        radioButton32.setChecked(true);
                    }
                } else if ("auto_search".equalsIgnoreCase(action)) {
                    RadioButton radioButton33 = radioButton5;
                    if (radioButton33 != null) {
                        radioButton33.setChecked(true);
                    }
                } else if ("auto_favorite".equalsIgnoreCase(action)) {
                    RadioButton radioButton34 = radioButton6;
                    if (radioButton34 != null) {
                        radioButton34.setChecked(true);
                    }
                } else if ("cycle_loop_mode".equalsIgnoreCase(action)) {
                    RadioButton radioButton35 = radioButton7;
                    if (radioButton35 != null) {
                        radioButton35.setChecked(true);
                    }
                } else if ("next".equalsIgnoreCase(action)) {
                    radioButton8.setChecked(true);
                } else if ("previous".equalsIgnoreCase(action)) {
                    radioButton9.setChecked(true);
                } else if ("play".equalsIgnoreCase(action)) {
                    radioButton10.setChecked(true);
                } else if ("pause".equalsIgnoreCase(action)) {
                    radioButton11.setChecked(true);
                } else if ("toggle".equalsIgnoreCase(action)) {
                    radioButton12.setChecked(true);
                } else {
                    radioButton17.setChecked(true);
                }
            }
            if ("variable".equalsIgnoreCase(logMediaProfile.getConditionType())) {
                radioButton.setChecked(z7);
                r02 = 0;
                view5.setVisibility(0);
            } else {
                r02 = 0;
                radioButton13.setChecked(z7);
                view5.setVisibility(8);
            }
            if (logMediaProfile.getDelaySeconds() > 0) {
                CheckBox checkBox17 = checkBox2;
                checkBox17.setChecked(z7);
                view4.setVisibility(r02);
                editText10 = editText25;
                editText10.setText(String.valueOf(logMediaProfile.getDelaySeconds()));
                checkBox7 = checkBox17;
            } else {
                editText10 = editText25;
                CheckBox checkBox18 = checkBox2;
                checkBox18.setChecked(r02);
                view4.setVisibility(8);
                checkBox7 = checkBox18;
            }
            if (logMediaProfile.getCooldownSeconds() > 0) {
                CheckBox checkBox19 = checkBox;
                checkBox19.setChecked(z7);
                view3.setVisibility(r02);
                editText11 = editText31;
                editText11.setText(String.valueOf(logMediaProfile.getCooldownSeconds()));
                checkBox8 = checkBox19;
            } else {
                editText11 = editText31;
                CheckBox checkBox20 = checkBox;
                checkBox20.setChecked(r02);
                view3.setVisibility(8);
                checkBox8 = checkBox20;
            }
            if (logMediaProfile.getComboSeconds() <= 0 || logMediaProfile.getComboCount() <= 0) {
                checkBox9 = checkBox4;
                editText12 = editText27;
                editText13 = editText;
                if (checkBox9 != null) {
                    checkBox9.setChecked(false);
                }
                if (view11 != null) {
                    view11.setVisibility(8);
                }
            } else {
                checkBox9 = checkBox4;
                if (checkBox4 != null) {
                    checkBox9.setChecked(true);
                }
                if (view11 != null) {
                    view11.setVisibility(0);
                }
                if (editText27 != null) {
                    editText12 = editText27;
                    editText12.setText(String.valueOf(logMediaProfile.getComboSeconds()));
                } else {
                    editText12 = editText27;
                }
                editText13 = editText;
                if (editText13 != null) {
                    editText13.setText(String.valueOf(logMediaProfile.getComboCount()));
                }
            }
            String conditionVariable = logMediaProfile.getConditionVariable();
            if (conditionVariable == null || conditionVariable.isEmpty()) {
                editText14 = editText2;
            } else {
                editText14 = editText2;
                editText14.setText(stripDollarPrefix(conditionVariable));
            }
            String conditionOperator = logMediaProfile.getConditionOperator();
            if (conditionOperator == null || conditionOperator.isEmpty() || str.equals(conditionOperator)) {
                spinner3 = spinner;
                spinner3.setSelection(0);
            } else if (">".equals(conditionOperator)) {
                spinner3 = spinner;
                spinner3.setSelection(1);
            } else {
                spinner3 = spinner;
                if ("<".equals(conditionOperator)) {
                    spinner3.setSelection(2);
                } else {
                    spinner3.setSelection(0);
                }
            }
            editText30.setText(logMediaProfile.getConditionValue());
            String variableName = logMediaProfile.getVariableName();
            editText2 = editText14;
            String variableOp = logMediaProfile.getVariableOp();
            String variableValue = logMediaProfile.getVariableValue();
            if (variableName == null || variableName.isEmpty()) {
                editText30 = editText30;
                spinner4 = spinner3;
                editText = editText13;
                editText27 = editText12;
                checkBox5 = checkBox9;
                editText31 = editText11;
                checkBox = checkBox8;
                editText6 = editText10;
                arrayList = arrayList7;
                arrayList2 = arrayList8;
                editText9 = editText5;
                view8.setVisibility(8);
            } else {
                editText30 = editText30;
                CheckBox checkBox21 = checkBox3;
                checkBox21.setChecked(true);
                String str4 = "变量名称 $";
                spinner4 = spinner3;
                editText = editText13;
                if (variableOp == null || !variableOp.equalsIgnoreCase("clear")) {
                    editText27 = editText12;
                    checkBox5 = checkBox9;
                    editText31 = editText11;
                    checkBox = checkBox8;
                    editText6 = editText10;
                    checkBox3 = checkBox21;
                    View view12 = view6;
                    ArrayList arrayList11 = arrayList9;
                    editText9 = editText5;
                    radioButton30.setChecked(true);
                    view12.setVisibility(0);
                    view7.setVisibility(8);
                    String[] split = variableName.split(";");
                    String str5 = h0.FRAGMENT_ENCODE_SET;
                    String[] split2 = (variableValue == null ? h0.FRAGMENT_ENCODE_SET : variableValue).split(";");
                    if (split.length > 0) {
                        editText15 = editText3;
                        editText15.setText(stripDollarPrefix(split[0]));
                        editText16 = editText4;
                        editText16.setText(split2.length > 0 ? split2[0] : h0.FRAGMENT_ENCODE_SET);
                    } else {
                        editText15 = editText3;
                        editText16 = editText4;
                    }
                    if (split.length > 1 && (view12 instanceof LinearLayout)) {
                        LinearLayout linearLayout5 = (LinearLayout) view12;
                        Context context = inflate.getContext();
                        int i14 = 1;
                        while (i14 < split.length) {
                            String str6 = split[i14];
                            if (str6 == null || str6.trim().isEmpty()) {
                                strArr = split;
                                str2 = str4;
                                str3 = str5;
                                editText17 = editText16;
                                editText18 = editText15;
                                arrayList3 = arrayList11;
                                arrayList4 = arrayList7;
                                arrayList5 = arrayList8;
                            } else {
                                strArr = split;
                                LinearLayout linearLayout6 = new LinearLayout(context);
                                str3 = str5;
                                linearLayout6.setOrientation(0);
                                linearLayout6.setGravity(16);
                                TextView textView = new TextView(context);
                                textView.setText(str4);
                                linearLayout6.addView(textView);
                                EditText editText35 = new EditText(context);
                                str2 = str4;
                                editText17 = editText16;
                                editText18 = editText15;
                                arrayList3 = arrayList11;
                                editText35.setLayoutParams(new LinearLayout.LayoutParams(0, -2, 1.0f));
                                editText35.setInputType(1);
                                editText35.setText(stripDollarPrefix(str6));
                                linearLayout6.addView(editText35);
                                TextView textView2 = new TextView(context);
                                LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(-2, -2);
                                layoutParams.setMarginStart(8);
                                textView2.setLayoutParams(layoutParams);
                                textView2.setText("赋值");
                                linearLayout6.addView(textView2);
                                EditText editText36 = new EditText(context);
                                LinearLayout.LayoutParams layoutParams2 = new LinearLayout.LayoutParams(0, -2, 1.0f);
                                layoutParams2.setMarginStart(4);
                                editText36.setLayoutParams(layoutParams2);
                                editText36.setInputType(androidx.fragment.app.h1.TRANSIT_FRAGMENT_CLOSE);
                                editText36.setText(i14 < split2.length ? split2[i14] : str3);
                                linearLayout6.addView(editText36);
                                ImageButton imageButton3 = new ImageButton(context);
                                imageButton3.setImageResource(R.drawable.ic_delete);
                                imageButton3.setBackground(null);
                                imageButton3.setOnClickListener(new e(linearLayout5, linearLayout6, arrayList7, editText35, arrayList8, editText36, 1));
                                linearLayout6.addView(imageButton3);
                                linearLayout5.addView(linearLayout6);
                                arrayList4 = arrayList7;
                                arrayList4.add(editText35);
                                arrayList5 = arrayList8;
                                arrayList5.add(editText36);
                            }
                            i14++;
                            arrayList8 = arrayList5;
                            arrayList7 = arrayList4;
                            split = strArr;
                            str5 = str3;
                            str4 = str2;
                            arrayList11 = arrayList3;
                            editText15 = editText18;
                            editText16 = editText17;
                        }
                    }
                    editText4 = editText16;
                    editText3 = editText15;
                    arrayList9 = arrayList11;
                    arrayList = arrayList7;
                    arrayList2 = arrayList8;
                } else {
                    radioButton31.setChecked(true);
                    view6.setVisibility(8);
                    View view13 = view7;
                    view13.setVisibility(0);
                    String[] split3 = variableName.split(";");
                    if (split3.length > 0) {
                        editText19 = editText5;
                        editText19.setText(stripDollarPrefix(split3[0]));
                    } else {
                        editText19 = editText5;
                    }
                    EditText editText37 = editText19;
                    if (split3.length <= 1 || !(view13 instanceof LinearLayout)) {
                        editText27 = editText12;
                    } else {
                        LinearLayout linearLayout7 = (LinearLayout) view13;
                        Context context2 = inflate.getContext();
                        editText27 = editText12;
                        int i15 = 1;
                        checkBox8 = checkBox8;
                        while (i15 < split3.length) {
                            String str7 = split3[i15];
                            if (str7 == null || str7.trim().isEmpty()) {
                                strArr2 = split3;
                                checkBox10 = checkBox9;
                                editText20 = editText11;
                                checkBox11 = checkBox8;
                                editText21 = editText10;
                                checkBox12 = checkBox21;
                                arrayList6 = arrayList9;
                            } else {
                                strArr2 = split3;
                                LinearLayout linearLayout8 = new LinearLayout(context2);
                                checkBox10 = checkBox9;
                                linearLayout8.setOrientation(0);
                                linearLayout8.setGravity(16);
                                TextView textView3 = new TextView(context2);
                                textView3.setText("变量名称 $");
                                linearLayout8.addView(textView3);
                                EditText editText38 = new EditText(context2);
                                editText20 = editText11;
                                checkBox11 = checkBox8;
                                editText21 = editText10;
                                checkBox12 = checkBox21;
                                editText38.setLayoutParams(new LinearLayout.LayoutParams(0, -2, 1.0f));
                                editText38.setInputType(1);
                                editText38.setText(stripDollarPrefix(str7));
                                linearLayout8.addView(editText38);
                                ImageButton imageButton4 = new ImageButton(context2);
                                imageButton4.setImageResource(R.drawable.ic_delete);
                                imageButton4.setBackground(null);
                                imageButton4.setOnClickListener(new a0(linearLayout7, linearLayout8, arrayList9, editText38, 0));
                                linearLayout8.addView(imageButton4);
                                linearLayout7.addView(linearLayout8);
                                arrayList6 = arrayList9;
                                arrayList6.add(editText38);
                            }
                            i15++;
                            arrayList9 = arrayList6;
                            split3 = strArr2;
                            checkBox9 = checkBox10;
                            editText10 = editText21;
                            editText11 = editText20;
                            checkBox8 = checkBox11;
                            checkBox21 = checkBox12;
                        }
                    }
                    checkBox5 = checkBox9;
                    editText31 = editText11;
                    checkBox = checkBox8;
                    editText6 = editText10;
                    checkBox3 = checkBox21;
                    arrayList9 = arrayList9;
                    arrayList = arrayList7;
                    arrayList2 = arrayList8;
                    editText9 = editText37;
                }
            }
            spinner2 = spinner4;
            z6 = false;
            checkBox6 = checkBox7;
        } else {
            checkBox5 = checkBox4;
            editText6 = editText25;
            editText7 = editText23;
            editText8 = editText22;
            arrayList = arrayList7;
            arrayList2 = arrayList8;
            spinner2 = spinner;
            checkBox6 = checkBox2;
            editText9 = editText5;
            view5.setVisibility(8);
            view8.setVisibility(8);
            z6 = false;
            view6.setVisibility(0);
            view7.setVisibility(8);
        }
        androidx.appcompat.app.n nVar = new androidx.appcompat.app.n(requireContext());
        nVar.t(logMediaProfile == null ? "新建配置文件" : "编辑配置文件");
        nVar.u(inflate);
        nVar.o("取消", new g4.g(2));
        RadioGroup radioGroup11 = radioGroup4;
        CheckBox checkBox22 = checkBox5;
        EditText editText39 = editText31;
        ArrayList arrayList12 = arrayList;
        CheckBox checkBox23 = checkBox;
        EditText editText40 = editText3;
        Spinner spinner6 = spinner2;
        EditText editText41 = editText7;
        EditText editText42 = editText8;
        CheckBox checkBox24 = checkBox6;
        EditText editText43 = editText6;
        ArrayList arrayList13 = arrayList9;
        boolean z8 = z6;
        CheckBox checkBox25 = checkBox3;
        EditText editText44 = editText9;
        nVar.r("保存", new b0(this, editText8, editText7, editText24, radioGroup11, radioGroup9, editText2, spinner6, editText30, checkBox24, editText43, checkBox23, editText39, checkBox22, editText27, editText, checkBox25, radioGroup10, editText44, arrayList13, editText40, editText4, arrayList12, arrayList2, arrayList10, logMediaProfile, 0));
        androidx.appcompat.app.o e7 = nVar.e();
        e7.show();
        if (radioGroup4.getCheckedRadioButtonId() == g4.p.radio_media_action_play_file) {
            r30.run();
        }
        View[] viewArr = new View[3];
        viewArr[z8 ? 1 : 0] = editText42;
        viewArr[1] = editText41;
        viewArr[2] = editText24;
        DialogImeNavBarHelper.install(e7, viewArr);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v49 */
    /* JADX WARN: Type inference failed for: r0v50, types: [boolean, int] */
    /* JADX WARN: Type inference failed for: r0v77 */
    private void showEditProfileDialogInternal(LogAppProfile logAppProfile) {
        RadioGroup radioGroup;
        View view;
        int i7;
        int i8;
        View view2;
        View view3;
        View view4;
        View view5;
        View view6;
        View view7;
        CheckBox checkBox;
        View view8;
        View view9;
        EditText editText;
        EditText editText2;
        RadioButton radioButton;
        RadioButton radioButton2;
        RadioButton radioButton3;
        RadioButton radioButton4;
        RadioButton radioButton5;
        RadioGroup radioGroup2;
        String str;
        String str2;
        int i9;
        boolean z6;
        CheckBox checkBox2;
        CheckBox checkBox3;
        RadioButton radioButton6;
        EditText editText3;
        ArrayList arrayList;
        ArrayList arrayList2;
        EditText editText4;
        EditText editText5;
        EditText editText6;
        CheckBox checkBox4;
        EditText editText7;
        boolean z7;
        CheckBox checkBox5;
        ?? r02;
        int i10;
        CheckBox checkBox6;
        EditText editText8;
        CheckBox checkBox7;
        EditText editText9;
        EditText editText10;
        CheckBox checkBox8;
        EditText editText11;
        Spinner spinner;
        EditText editText12;
        EditText editText13;
        EditText editText14;
        String[] strArr;
        String str3;
        EditText editText15;
        EditText editText16;
        ArrayList arrayList3;
        String str4;
        ArrayList arrayList4;
        ArrayList arrayList5;
        EditText editText17;
        String[] strArr2;
        EditText editText18;
        CheckBox checkBox9;
        CheckBox checkBox10;
        EditText editText19;
        CheckBox checkBox11;
        ArrayList arrayList6;
        int i11;
        View inflate = LayoutInflater.from(getContext()).inflate(g4.q.dialog_log_profile_app, (ViewGroup) null, false);
        EditText editText20 = (EditText) inflate.findViewById(g4.p.edit_profile_name);
        EditText editText21 = (EditText) inflate.findViewById(g4.p.edit_profile_tag);
        EditText editText22 = (EditText) inflate.findViewById(g4.p.edit_profile_pattern);
        RadioGroup radioGroup3 = (RadioGroup) inflate.findViewById(g4.p.radio_condition_type_group);
        RadioButton radioButton7 = (RadioButton) inflate.findViewById(g4.p.radio_condition_none);
        RadioButton radioButton8 = (RadioButton) inflate.findViewById(g4.p.radio_condition_variable);
        CheckBox checkBox12 = (CheckBox) inflate.findViewById(g4.p.checkbox_condition_delay);
        CheckBox checkBox13 = (CheckBox) inflate.findViewById(g4.p.checkbox_condition_cooldown);
        View findViewById = inflate.findViewById(g4.p.layout_condition_variable);
        View findViewById2 = inflate.findViewById(g4.p.layout_condition_delay);
        View findViewById3 = inflate.findViewById(g4.p.layout_condition_cooldown);
        EditText editText23 = (EditText) inflate.findViewById(g4.p.edit_condition_delay_seconds);
        EditText editText24 = (EditText) inflate.findViewById(g4.p.edit_condition_cooldown_seconds);
        CheckBox checkBox14 = (CheckBox) inflate.findViewById(g4.p.checkbox_condition_combo);
        View findViewById4 = inflate.findViewById(g4.p.layout_condition_combo);
        EditText editText25 = (EditText) inflate.findViewById(g4.p.edit_condition_combo_seconds);
        EditText editText26 = (EditText) inflate.findViewById(g4.p.edit_condition_combo_count);
        EditText editText27 = (EditText) inflate.findViewById(g4.p.edit_condition_variable_name);
        Spinner spinner2 = (Spinner) inflate.findViewById(g4.p.spinner_condition_operator);
        EditText editText28 = (EditText) inflate.findViewById(g4.p.edit_condition_value);
        RadioButton radioButton9 = (RadioButton) inflate.findViewById(g4.p.radio_action_none);
        final RadioButton radioButton10 = (RadioButton) inflate.findViewById(g4.p.radio_action_open);
        final RadioButton radioButton11 = (RadioButton) inflate.findViewById(g4.p.radio_action_close);
        RadioButton radioButton12 = (RadioButton) inflate.findViewById(g4.p.radio_action_switch_floating_app);
        RadioButton radioButton13 = (RadioButton) inflate.findViewById(g4.p.radio_action_switch_lyrics_fx);
        RadioButton radioButton14 = (RadioButton) inflate.findViewById(g4.p.radio_action_home);
        RadioButton radioButton15 = (RadioButton) inflate.findViewById(g4.p.radio_action_switch_view);
        RadioButton radioButton16 = (RadioButton) inflate.findViewById(g4.p.radio_action_switch_clean_mode);
        RadioButton radioButton17 = (RadioButton) inflate.findViewById(g4.p.radio_action_switch_wallpaper_mode);
        RadioButton radioButton18 = (RadioButton) inflate.findViewById(g4.p.radio_action_displaysiri);
        RadioButton radioButton19 = (RadioButton) inflate.findViewById(g4.p.radio_action_adb_switch);
        CheckBox checkBox15 = (CheckBox) inflate.findViewById(g4.p.radio_action_variable);
        View findViewById5 = inflate.findViewById(g4.p.layout_variable_action);
        RadioGroup radioGroup4 = (RadioGroup) inflate.findViewById(g4.p.radio_variable_action_group);
        RadioButton radioButton20 = (RadioButton) inflate.findViewById(g4.p.radio_variable_action_set);
        RadioButton radioButton21 = (RadioButton) inflate.findViewById(g4.p.radio_variable_action_clear);
        View findViewById6 = inflate.findViewById(g4.p.layout_variable_set);
        View findViewById7 = inflate.findViewById(g4.p.layout_variable_clear);
        EditText editText29 = (EditText) inflate.findViewById(g4.p.edit_variable_name);
        EditText editText30 = (EditText) inflate.findViewById(g4.p.edit_variable_value);
        EditText editText31 = (EditText) inflate.findViewById(g4.p.edit_variable_name_clear);
        ImageButton imageButton = (ImageButton) inflate.findViewById(g4.p.button_add_variable_set);
        ImageButton imageButton2 = (ImageButton) inflate.findViewById(g4.p.button_add_variable_clear);
        ArrayList arrayList7 = new ArrayList();
        ArrayList arrayList8 = new ArrayList();
        ArrayList arrayList9 = new ArrayList();
        final ArrayList arrayList10 = new ArrayList();
        final View findViewById8 = inflate.findViewById(g4.p.layout_app_list_section);
        if (findViewById6 instanceof LinearLayout) {
            LinearLayout linearLayout = (LinearLayout) findViewById6;
            view = findViewById5;
            LinearLayout linearLayout2 = new LinearLayout(getContext());
            linearLayout2.setOrientation(0);
            linearLayout2.setGravity(16);
            while (linearLayout.getChildCount() > 0) {
                RadioGroup radioGroup5 = radioGroup4;
                View childAt = linearLayout.getChildAt(0);
                linearLayout.removeViewAt(0);
                linearLayout2.addView(childAt);
                radioGroup4 = radioGroup5;
            }
            radioGroup = radioGroup4;
            linearLayout.setOrientation(1);
            linearLayout.addView(linearLayout2);
        } else {
            radioGroup = radioGroup4;
            view = findViewById5;
        }
        if (findViewById7 instanceof LinearLayout) {
            LinearLayout linearLayout3 = (LinearLayout) findViewById7;
            LinearLayout linearLayout4 = new LinearLayout(getContext());
            int i12 = 0;
            linearLayout4.setOrientation(0);
            linearLayout4.setGravity(16);
            while (linearLayout3.getChildCount() > 0) {
                View childAt2 = linearLayout3.getChildAt(i12);
                linearLayout3.removeViewAt(i12);
                linearLayout4.addView(childAt2);
                i12 = 0;
            }
            i7 = 1;
            linearLayout3.setOrientation(1);
            linearLayout3.addView(linearLayout4);
        } else {
            i7 = 1;
        }
        final RecyclerView recyclerView = (RecyclerView) inflate.findViewById(g4.p.recycler_select_apps);
        getContext();
        recyclerView.setLayoutManager(new LinearLayoutManager(i7));
        recyclerView.setItemAnimator(null);
        recyclerView.setNestedScrollingEnabled(false);
        if (recyclerView.getItemDecorationCount() == 0) {
            DialogListDividers.applyAppList(recyclerView);
        }
        ArrayAdapter arrayAdapter = new ArrayAdapter(requireContext(), R.layout.simple_spinner_item, new String[]{"=", ">", "<"});
        arrayAdapter.setDropDownViewResource(R.layout.simple_spinner_dropdown_item);
        spinner2.setAdapter((SpinnerAdapter) arrayAdapter);
        int i13 = 2;
        radioGroup3.setOnCheckedChangeListener(new y(i13, findViewById));
        checkBox12.setOnCheckedChangeListener(new c0(8, findViewById2));
        checkBox13.setOnCheckedChangeListener(new c0(9, findViewById3));
        int i14 = 10;
        if (checkBox14 != null) {
            checkBox14.setOnCheckedChangeListener(new c0(i14, findViewById4));
        }
        checkBox14.setOnCheckedChangeListener(new c0(11, findViewById4));
        radioGroup.setOnCheckedChangeListener(new d0(findViewById6, findViewById7, i13));
        View view10 = view;
        checkBox15.setOnCheckedChangeListener(new c0(12, view10));
        final List<RadioButton> asList = Arrays.asList(radioButton10, radioButton11, radioButton12, radioButton13, radioButton14, radioButton15, radioButton16, radioButton17, radioButton18, radioButton19, radioButton9);
        final boolean[] zArr = {false};
        CompoundButton.OnCheckedChangeListener onCheckedChangeListener = new CompoundButton.OnCheckedChangeListener() { // from class: com.navi.tool.ui.log.w
            @Override // android.widget.CompoundButton.OnCheckedChangeListener
            public final void onCheckedChanged(CompoundButton compoundButton, boolean z8) {
                LogLinkFragment.lambda$showEditProfileDialogInternal$48(zArr, asList, radioButton10, radioButton11, findViewById8, compoundButton, z8);
            }
        };
        for (RadioButton radioButton22 : asList) {
            Spinner spinner3 = spinner2;
            if (radioButton22 != null) {
                radioButton22.setOnCheckedChangeListener(onCheckedChangeListener);
            }
            spinner2 = spinner3;
        }
        Spinner spinner4 = spinner2;
        Iterator it = asList.iterator();
        while (true) {
            if (!it.hasNext()) {
                i8 = -1;
                break;
            }
            RadioButton radioButton23 = (RadioButton) it.next();
            if (radioButton23 != null && radioButton23.isChecked()) {
                i8 = radioButton23.getId();
                break;
            }
        }
        if (findViewById8 != null) {
            if (i8 == g4.p.radio_action_open || i8 == g4.p.radio_action_close) {
                view2 = findViewById8;
                i11 = 0;
            } else {
                view2 = findViewById8;
                i11 = 8;
            }
            view2.setVisibility(i11);
        } else {
            view2 = findViewById8;
        }
        if (imageButton != null) {
            imageButton.setOnClickListener(new e0(checkBox15, view10, findViewById6, findViewById7, inflate, arrayList7, arrayList8, 2));
        }
        if (imageButton2 != null) {
            view4 = findViewById6;
            radioButton3 = radioButton12;
            radioButton4 = radioButton20;
            radioButton5 = radioButton21;
            editText = editText24;
            editText2 = editText23;
            radioButton2 = radioButton13;
            radioGroup2 = radioGroup;
            str = "<";
            z6 = true;
            view5 = findViewById3;
            view6 = findViewById2;
            view7 = findViewById;
            checkBox = checkBox13;
            view9 = findViewById4;
            view3 = view10;
            checkBox2 = checkBox14;
            view8 = view2;
            checkBox3 = checkBox12;
            radioButton = radioButton8;
            radioButton6 = radioButton14;
            str2 = ">";
            i9 = 3;
            imageButton2.setOnClickListener(new f(checkBox15, view10, view4, findViewById7, inflate, arrayList9, 2));
        } else {
            view3 = view10;
            view4 = findViewById6;
            view5 = findViewById3;
            view6 = findViewById2;
            view7 = findViewById;
            checkBox = checkBox13;
            view8 = view2;
            view9 = findViewById4;
            editText = editText24;
            editText2 = editText23;
            radioButton = radioButton8;
            radioButton2 = radioButton13;
            radioButton3 = radioButton12;
            radioButton4 = radioButton20;
            radioButton5 = radioButton21;
            radioGroup2 = radioGroup;
            str = "<";
            str2 = ">";
            i9 = 3;
            z6 = true;
            checkBox2 = checkBox14;
            checkBox3 = checkBox12;
            radioButton6 = radioButton14;
        }
        AppAdapter appAdapter = new AppAdapter(new AppAdapter.OnAppToggleListener() { // from class: com.navi.tool.ui.log.v
            @Override // com.navi.tool.ui.adapter.AppAdapter.OnAppToggleListener
            public final void onAppToggle(AppInfo appInfo) {
                LogLinkFragment.lambda$showEditProfileDialogInternal$53(arrayList10, recyclerView, appInfo);
            }
        });
        appAdapter.submitList(new ArrayList(arrayList10));
        recyclerView.setAdapter(appAdapter);
        recyclerView.post(new g(i9, recyclerView));
        RadioButton radioButton24 = radioButton;
        CheckBox checkBox16 = checkBox15;
        boolean z8 = z6;
        View view11 = view8;
        EditText editText32 = editText29;
        EditText editText33 = editText30;
        new Thread(new s(this, arrayList10, appAdapter, recyclerView, logAppProfile)).start();
        if (logAppProfile != null) {
            editText20.setText(logAppProfile.getName());
            editText21.setText(logAppProfile.getTag());
            editText22.setText(logAppProfile.getPattern());
            String action = logAppProfile.getAction();
            if ("none".equalsIgnoreCase(action)) {
                if (radioButton9 != null) {
                    radioButton9.setChecked(z8);
                }
            } else if ("close".equalsIgnoreCase(action)) {
                radioButton11.setChecked(z8);
            } else if ("switch_floating_app".equalsIgnoreCase(action)) {
                RadioButton radioButton25 = radioButton3;
                if (radioButton25 != null) {
                    radioButton25.setChecked(z8);
                }
            } else if ("switch_lyrics_fx".equalsIgnoreCase(action)) {
                RadioButton radioButton26 = radioButton2;
                if (radioButton26 != null) {
                    radioButton26.setChecked(z8);
                }
            } else if ("home".equalsIgnoreCase(action)) {
                if (radioButton6 != null) {
                    radioButton6.setChecked(z8);
                }
            } else if ("switch_view".equalsIgnoreCase(action)) {
                if (radioButton15 != null) {
                    radioButton15.setChecked(z8);
                }
            } else if ("switch_clean_mode".equalsIgnoreCase(action)) {
                if (radioButton16 != null) {
                    radioButton16.setChecked(z8);
                }
            } else if ("switch_wallpaper_mode".equalsIgnoreCase(action)) {
                if (radioButton17 != null) {
                    radioButton17.setChecked(z8);
                }
            } else if ("displaysiri".equalsIgnoreCase(action)) {
                if (radioButton18 != null) {
                    radioButton18.setChecked(z8);
                }
            } else if (!"adb_switch".equalsIgnoreCase(action)) {
                radioButton10.setChecked(z8);
            } else if (radioButton19 != null) {
                radioButton19.setChecked(z8);
            }
            if (view11 != null) {
                view11.setVisibility(("open".equalsIgnoreCase(action) || "close".equalsIgnoreCase(action)) ? 0 : 8);
            }
            if ("variable".equalsIgnoreCase(logAppProfile.getConditionType())) {
                radioButton24.setChecked(z8);
                r02 = 0;
                view7.setVisibility(0);
                i10 = 8;
            } else {
                r02 = 0;
                radioButton7.setChecked(z8);
                i10 = 8;
                view7.setVisibility(8);
            }
            if (logAppProfile.getDelaySeconds() > 0) {
                CheckBox checkBox17 = checkBox3;
                checkBox17.setChecked(z8);
                view6.setVisibility(r02);
                editText4 = editText2;
                editText4.setText(String.valueOf(logAppProfile.getDelaySeconds()));
                checkBox6 = checkBox17;
            } else {
                CheckBox checkBox18 = checkBox3;
                editText4 = editText2;
                checkBox18.setChecked(r02);
                view6.setVisibility(i10);
                checkBox6 = checkBox18;
            }
            if (logAppProfile.getCooldownSeconds() > 0) {
                CheckBox checkBox19 = checkBox;
                checkBox19.setChecked(z8);
                view5.setVisibility(r02);
                editText8 = editText;
                editText8.setText(String.valueOf(logAppProfile.getCooldownSeconds()));
                checkBox7 = checkBox19;
            } else {
                editText8 = editText;
                CheckBox checkBox20 = checkBox;
                checkBox20.setChecked(r02);
                view5.setVisibility(i10);
                checkBox7 = checkBox20;
            }
            if (logAppProfile.getComboSeconds() <= 0 || logAppProfile.getComboCount() <= 0) {
                View view12 = view9;
                editText9 = editText25;
                editText10 = editText26;
                CheckBox checkBox21 = checkBox2;
                checkBox21.setChecked(r02);
                checkBox8 = checkBox21;
                if (view12 != null) {
                    view12.setVisibility(8);
                    checkBox8 = checkBox21;
                }
            } else {
                CheckBox checkBox22 = checkBox2;
                checkBox22.setChecked(z8);
                if (view9 != null) {
                    view9.setVisibility(r02);
                }
                if (editText25 != null) {
                    editText9 = editText25;
                    editText9.setText(String.valueOf(logAppProfile.getComboSeconds()));
                } else {
                    editText9 = editText25;
                }
                if (editText26 != null) {
                    editText10 = editText26;
                    editText10.setText(String.valueOf(logAppProfile.getComboCount()));
                    checkBox8 = checkBox22;
                } else {
                    editText10 = editText26;
                    checkBox8 = checkBox22;
                }
            }
            String conditionVariable = logAppProfile.getConditionVariable();
            if (conditionVariable == null || conditionVariable.isEmpty()) {
                editText11 = editText27;
            } else {
                editText11 = editText27;
                editText11.setText(stripDollarPrefix(conditionVariable));
            }
            String conditionOperator = logAppProfile.getConditionOperator();
            if (conditionOperator == null || conditionOperator.isEmpty() || "=".equals(conditionOperator)) {
                spinner = spinner4;
                spinner.setSelection(0);
            } else if (str2.equals(conditionOperator)) {
                spinner = spinner4;
                spinner.setSelection(z8 ? 1 : 0);
            } else {
                spinner = spinner4;
                if (str.equals(conditionOperator)) {
                    spinner.setSelection(2);
                } else {
                    spinner.setSelection(0);
                }
            }
            editText28.setText(logAppProfile.getConditionValue());
            String variableName = logAppProfile.getVariableName();
            String variableOp = logAppProfile.getVariableOp();
            String variableValue = logAppProfile.getVariableValue();
            if (variableName == null || variableName.isEmpty()) {
                editText12 = editText28;
                spinner4 = spinner;
                editText3 = editText11;
                editText7 = editText10;
                checkBox4 = checkBox8;
                checkBox = checkBox7;
                editText = editText8;
                editText25 = editText9;
                arrayList = arrayList7;
                arrayList2 = arrayList8;
                editText6 = editText31;
                view3.setVisibility(8);
            } else {
                editText25 = editText9;
                CheckBox checkBox23 = checkBox16;
                checkBox23.setChecked(z8);
                String str5 = "变量名称 $";
                editText12 = editText28;
                spinner4 = spinner;
                if (variableOp == null || !variableOp.equalsIgnoreCase("clear")) {
                    editText3 = editText11;
                    editText7 = editText10;
                    checkBox4 = checkBox8;
                    checkBox = checkBox7;
                    editText = editText8;
                    checkBox16 = checkBox23;
                    View view13 = view4;
                    ArrayList arrayList11 = arrayList9;
                    editText6 = editText31;
                    radioButton4.setChecked(true);
                    view13.setVisibility(0);
                    findViewById7.setVisibility(8);
                    String[] split = variableName.split(";");
                    String str6 = h0.FRAGMENT_ENCODE_SET;
                    String[] split2 = (variableValue == null ? h0.FRAGMENT_ENCODE_SET : variableValue).split(";");
                    if (split.length > 0) {
                        editText13 = editText32;
                        editText13.setText(stripDollarPrefix(split[0]));
                        editText14 = editText33;
                        editText14.setText(split2.length > 0 ? split2[0] : h0.FRAGMENT_ENCODE_SET);
                    } else {
                        editText13 = editText32;
                        editText14 = editText33;
                    }
                    if (split.length > 1 && (view13 instanceof LinearLayout)) {
                        LinearLayout linearLayout5 = (LinearLayout) view13;
                        Context context = inflate.getContext();
                        int i15 = 1;
                        while (i15 < split.length) {
                            String str7 = split[i15];
                            if (str7 == null || str7.trim().isEmpty()) {
                                strArr = split;
                                str3 = str6;
                                editText15 = editText14;
                                editText16 = editText13;
                                arrayList3 = arrayList11;
                                str4 = str5;
                                arrayList4 = arrayList7;
                                arrayList5 = arrayList8;
                            } else {
                                strArr = split;
                                LinearLayout linearLayout6 = new LinearLayout(context);
                                str3 = str6;
                                linearLayout6.setOrientation(0);
                                linearLayout6.setGravity(16);
                                TextView textView = new TextView(context);
                                textView.setText(str5);
                                linearLayout6.addView(textView);
                                EditText editText34 = new EditText(context);
                                editText15 = editText14;
                                editText16 = editText13;
                                arrayList3 = arrayList11;
                                str4 = str5;
                                editText34.setLayoutParams(new LinearLayout.LayoutParams(0, -2, 1.0f));
                                editText34.setInputType(1);
                                editText34.setText(stripDollarPrefix(str7));
                                linearLayout6.addView(editText34);
                                TextView textView2 = new TextView(context);
                                LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(-2, -2);
                                layoutParams.setMarginStart(8);
                                textView2.setLayoutParams(layoutParams);
                                textView2.setText("赋值");
                                linearLayout6.addView(textView2);
                                EditText editText35 = new EditText(context);
                                LinearLayout.LayoutParams layoutParams2 = new LinearLayout.LayoutParams(0, -2, 1.0f);
                                layoutParams2.setMarginStart(4);
                                editText35.setLayoutParams(layoutParams2);
                                editText35.setInputType(androidx.fragment.app.h1.TRANSIT_FRAGMENT_CLOSE);
                                editText35.setText(i15 < split2.length ? split2[i15] : str3);
                                linearLayout6.addView(editText35);
                                ImageButton imageButton3 = new ImageButton(context);
                                imageButton3.setImageResource(R.drawable.ic_delete);
                                imageButton3.setBackground(null);
                                imageButton3.setOnClickListener(new e(linearLayout5, linearLayout6, arrayList7, editText34, arrayList8, editText35, 5));
                                linearLayout6.addView(imageButton3);
                                linearLayout5.addView(linearLayout6);
                                arrayList4 = arrayList7;
                                arrayList4.add(editText34);
                                arrayList5 = arrayList8;
                                arrayList5.add(editText35);
                            }
                            i15++;
                            arrayList8 = arrayList5;
                            arrayList7 = arrayList4;
                            str5 = str4;
                            split = strArr;
                            str6 = str3;
                            arrayList11 = arrayList3;
                            editText13 = editText16;
                            editText14 = editText15;
                        }
                    }
                    editText33 = editText14;
                    editText32 = editText13;
                    arrayList9 = arrayList11;
                    arrayList = arrayList7;
                    arrayList2 = arrayList8;
                } else {
                    radioButton5.setChecked(true);
                    view4.setVisibility(8);
                    findViewById7.setVisibility(0);
                    String[] split3 = variableName.split(";");
                    if (split3.length > 0) {
                        editText17 = editText31;
                        editText17.setText(stripDollarPrefix(split3[0]));
                    } else {
                        editText17 = editText31;
                    }
                    EditText editText36 = editText17;
                    if (split3.length <= 1 || !(findViewById7 instanceof LinearLayout)) {
                        editText3 = editText11;
                    } else {
                        LinearLayout linearLayout7 = (LinearLayout) findViewById7;
                        Context context2 = inflate.getContext();
                        editText3 = editText11;
                        int i16 = 1;
                        checkBox8 = checkBox8;
                        checkBox7 = checkBox7;
                        while (i16 < split3.length) {
                            String str8 = split3[i16];
                            if (str8 == null || str8.trim().isEmpty()) {
                                strArr2 = split3;
                                editText18 = editText10;
                                checkBox9 = checkBox8;
                                checkBox10 = checkBox7;
                                editText19 = editText8;
                                checkBox11 = checkBox23;
                                arrayList6 = arrayList9;
                            } else {
                                strArr2 = split3;
                                LinearLayout linearLayout8 = new LinearLayout(context2);
                                editText18 = editText10;
                                linearLayout8.setOrientation(0);
                                linearLayout8.setGravity(16);
                                TextView textView3 = new TextView(context2);
                                textView3.setText("变量名称 $");
                                linearLayout8.addView(textView3);
                                EditText editText37 = new EditText(context2);
                                checkBox9 = checkBox8;
                                checkBox10 = checkBox7;
                                editText19 = editText8;
                                checkBox11 = checkBox23;
                                editText37.setLayoutParams(new LinearLayout.LayoutParams(0, -2, 1.0f));
                                editText37.setInputType(1);
                                editText37.setText(stripDollarPrefix(str8));
                                linearLayout8.addView(editText37);
                                ImageButton imageButton4 = new ImageButton(context2);
                                imageButton4.setImageResource(R.drawable.ic_delete);
                                imageButton4.setBackground(null);
                                imageButton4.setOnClickListener(new a0(linearLayout7, linearLayout8, arrayList9, editText37, 5));
                                linearLayout8.addView(imageButton4);
                                linearLayout7.addView(linearLayout8);
                                arrayList6 = arrayList9;
                                arrayList6.add(editText37);
                            }
                            i16++;
                            arrayList9 = arrayList6;
                            split3 = strArr2;
                            editText10 = editText18;
                            checkBox8 = checkBox9;
                            editText8 = editText19;
                            checkBox7 = checkBox10;
                            checkBox23 = checkBox11;
                        }
                    }
                    editText7 = editText10;
                    checkBox4 = checkBox8;
                    checkBox = checkBox7;
                    editText = editText8;
                    checkBox16 = checkBox23;
                    arrayList9 = arrayList9;
                    arrayList = arrayList7;
                    arrayList2 = arrayList8;
                    editText6 = editText36;
                }
            }
            editText5 = editText12;
            z7 = false;
            checkBox5 = checkBox6;
        } else {
            editText3 = editText27;
            CheckBox checkBox24 = checkBox3;
            arrayList = arrayList7;
            arrayList2 = arrayList8;
            editText4 = editText2;
            editText5 = editText28;
            editText6 = editText31;
            checkBox4 = checkBox2;
            editText7 = editText26;
            view7.setVisibility(8);
            view3.setVisibility(8);
            z7 = false;
            view4.setVisibility(0);
            findViewById7.setVisibility(8);
            checkBox5 = checkBox24;
            if (view11 != null) {
                view11.setVisibility(0);
                checkBox5 = checkBox24;
            }
        }
        androidx.appcompat.app.n nVar = new androidx.appcompat.app.n(requireContext());
        nVar.t(logAppProfile == null ? "新建配置文件" : "编辑配置文件");
        nVar.u(inflate);
        nVar.o("取消", new g4.g(13));
        CheckBox checkBox25 = checkBox16;
        RadioGroup radioGroup6 = radioGroup2;
        EditText editText38 = editText6;
        nVar.r("保存", new b0(this, editText20, editText21, editText22, asList, radioGroup3, editText3, spinner4, editText5, checkBox5, editText4, checkBox, editText, checkBox4, editText25, editText7, checkBox25, radioGroup6, editText38, arrayList9, editText32, editText33, arrayList, arrayList2, arrayList10, logAppProfile));
        androidx.appcompat.app.o e7 = nVar.e();
        e7.show();
        View[] viewArr = new View[3];
        viewArr[z7 ? 1 : 0] = editText20;
        viewArr[1] = editText21;
        viewArr[2] = editText22;
        DialogImeNavBarHelper.install(e7, viewArr);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void startLogLinkageService() {
        Intent intent = new Intent(requireContext(), (Class<?>) LogLinkageService.class);
        intent.setAction(LogLinkageService.ACTION_START_LOG_LINKAGE);
        requireContext().startService(intent);
        ToastHelper.show(requireContext(), "日志联动已启动");
    }

    private void startLogMonitorServiceIfNeeded() {
        SwitchMaterial switchMaterial = this.logLinkSwitch;
        if (switchMaterial == null || !switchMaterial.isChecked()) {
            return;
        }
        Intent intent = new Intent(requireContext(), (Class<?>) LogLinkageService.class);
        intent.setAction(LogLinkageService.ACTION_START_LOG_LINKAGE);
        requireContext().startService(intent);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void stopLogLinkageService() {
        Intent intent = new Intent(requireContext(), (Class<?>) LogLinkageService.class);
        intent.setAction(LogLinkageService.ACTION_STOP_LOG_LINKAGE);
        requireContext().startService(intent);
        requireContext().stopService(intent);
        ToastHelper.show(requireContext(), "日志联动已停止");
    }

    private static String stripDollarPrefix(String str) {
        if (str == null) {
            return h0.FRAGMENT_ENCODE_SET;
        }
        String trim = str.trim();
        return trim.startsWith("$") ? trim.substring(1) : trim;
    }

    private void toggleAppSelectionMode() {
        LogProfileAdapter logProfileAdapter = this.profileAdapter;
        if (logProfileAdapter == null) {
            return;
        }
        boolean z6 = !logProfileAdapter.isSelectionMode();
        this.profileAdapter.setSelectionMode(z6);
        Button button = this.buttonMultiSelectAppProfile;
        if (button != null) {
            button.setText(z6 ? "取消多选" : "多选");
        }
        Button button2 = this.buttonDeleteSelectedAppProfile;
        if (button2 != null) {
            button2.setVisibility(z6 ? 0 : 8);
        }
        Button button3 = this.buttonSelectAllAppProfile;
        if (button3 != null) {
            button3.setVisibility(z6 ? 0 : 8);
        }
    }

    private void toggleAssistantSelectionMode() {
        LogAssistantProfileAdapter logAssistantProfileAdapter = this.assistantProfileAdapter;
        if (logAssistantProfileAdapter == null) {
            return;
        }
        boolean z6 = !logAssistantProfileAdapter.isSelectionMode();
        this.assistantProfileAdapter.setSelectionMode(z6);
        Button button = this.buttonMultiSelectAssistantProfile;
        if (button != null) {
            button.setText(z6 ? "取消多选" : "多选");
        }
        Button button2 = this.buttonDeleteSelectedAssistantProfile;
        if (button2 != null) {
            button2.setVisibility(z6 ? 0 : 8);
        }
        Button button3 = this.buttonSelectAllAssistantProfile;
        if (button3 != null) {
            button3.setVisibility(z6 ? 0 : 8);
        }
    }

    private void toggleMediaSelectionMode() {
        LogMediaProfileAdapter logMediaProfileAdapter = this.mediaProfileAdapter;
        if (logMediaProfileAdapter == null) {
            return;
        }
        boolean z6 = !logMediaProfileAdapter.isSelectionMode();
        this.mediaProfileAdapter.setSelectionMode(z6);
        Button button = this.buttonMultiSelectMediaProfile;
        if (button != null) {
            button.setText(z6 ? "取消多选" : "多选");
        }
        Button button2 = this.buttonDeleteSelectedMediaProfile;
        if (button2 != null) {
            button2.setVisibility(z6 ? 0 : 8);
        }
        Button button3 = this.buttonSelectAllMediaProfile;
        if (button3 != null) {
            button3.setVisibility(z6 ? 0 : 8);
        }
    }

    private void updateLogLinkSwitchByPermission() {
        if (this.logLinkSwitch == null) {
            return;
        }
        boolean z6 = hasReadLogsPermission() && hasStorageAccess();
        this.logLinkSwitch.setEnabled(z6);
        if (z6 || !this.logLinkSwitch.isChecked()) {
            return;
        }
        this.logLinkSwitch.setChecked(false);
        SharedPreferences sharedPreferences = this.sharedPreferences;
        if (sharedPreferences != null) {
            a.a.m(sharedPreferences, "log_link_switch_state", false);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void updatePermissionStatus() {
        boolean hasStorageAccess = hasStorageAccess();
        boolean hasReadLogsPermission = hasReadLogsPermission();
        TextView textView = this.textStorageStatus;
        if (textView != null) {
            textView.setText(hasStorageAccess ? "外部存储权限: 已获取" : "外部存储权限: 未获取");
            Context requireContext = requireContext();
            int i7 = hasStorageAccess ? g4.m.permission_granted : g4.m.permission_denied;
            Object obj = g0.g.f6423a;
            this.textStorageStatus.setTextColor(g0.d.a(requireContext, i7));
        }
        TextView textView2 = this.textLogStatus;
        if (textView2 != null) {
            textView2.setText(hasReadLogsPermission ? "READ_LOGS权限: 已获取" : "READ_LOGS权限: 未获取");
            Context requireContext2 = requireContext();
            int i8 = hasReadLogsPermission ? g4.m.permission_granted : g4.m.permission_denied;
            Object obj2 = g0.g.f6423a;
            this.textLogStatus.setTextColor(g0.d.a(requireContext2, i8));
        }
        if (hasStorageAccess && hasReadLogsPermission) {
            SwitchMaterial switchMaterial = this.logLinkSwitch;
            boolean z6 = switchMaterial != null && switchMaterial.isChecked();
            View view = this.permissionStatusContainer;
            if (view != null) {
                view.setVisibility(z6 ? 8 : 0);
            }
            View view2 = this.appLinkContainer;
            if (view2 != null) {
                view2.setVisibility(z6 ? 0 : 8);
            }
            View view3 = this.mediaLinkContainer;
            if (view3 != null) {
                view3.setVisibility(z6 ? 0 : 8);
            }
            View view4 = this.assistantLinkContainer;
            if (view4 != null) {
                view4.setVisibility(z6 ? 0 : 8);
            }
        } else {
            SwitchMaterial switchMaterial2 = this.logLinkSwitch;
            if (switchMaterial2 != null && switchMaterial2.isChecked()) {
                this.logLinkSwitch.setChecked(false);
                a.a.m(this.sharedPreferences, "log_link_switch_state", false);
            }
            View view5 = this.permissionStatusContainer;
            if (view5 != null) {
                view5.setVisibility(0);
            }
        }
        updateLogLinkSwitchByPermission();
    }

    @Override // androidx.lifecycle.k
    public e1.c getDefaultViewModelCreationExtras() {
        return e1.a.INSTANCE;
    }

    @Override // androidx.fragment.app.z
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        final int i7 = 0;
        View inflate = layoutInflater.inflate(g4.q.fragment_log_link, viewGroup, false);
        if ((NativeBridge.getCachedLicenseMask(requireContext()) & 32) == 0) {
            inflate.setVisibility(8);
            return inflate;
        }
        this.sharedPreferences = SharedPreferencesUtils.getAppPrefs(requireContext());
        this.logLinkSwitch = (SwitchMaterial) inflate.findViewById(g4.p.switch_log_link);
        this.textStorageStatus = (TextView) inflate.findViewById(g4.p.text_storage_permission_status);
        this.textLogStatus = (TextView) inflate.findViewById(g4.p.text_logs_permission_status);
        this.permissionStatusContainer = inflate.findViewById(g4.p.permission_status_container);
        this.appLinkContainer = inflate.findViewById(g4.p.log_app_link_container);
        this.mediaLinkContainer = inflate.findViewById(g4.p.log_media_link_container);
        this.assistantLinkContainer = inflate.findViewById(g4.p.log_assistant_link_container);
        this.switchAppLink = (SwitchMaterial) inflate.findViewById(g4.p.switch_log_app_link);
        this.switchMediaLink = (SwitchMaterial) inflate.findViewById(g4.p.switch_log_media_link);
        this.switchAssistantLink = (SwitchMaterial) inflate.findViewById(g4.p.switch_log_assistant_link);
        this.appLinkManagerContainer = inflate.findViewById(g4.p.app_link_manager_container);
        this.recyclerAppProfiles = (RecyclerView) inflate.findViewById(g4.p.recycler_app_profiles);
        this.textAppProfileEmpty = (TextView) inflate.findViewById(g4.p.text_app_profile_empty);
        this.buttonAddAppProfile = (Button) inflate.findViewById(g4.p.button_add_app_profile);
        this.buttonMultiSelectAppProfile = (Button) inflate.findViewById(g4.p.button_multi_select_app_profile);
        this.buttonDeleteSelectedAppProfile = (Button) inflate.findViewById(g4.p.button_delete_selected_app_profile);
        this.buttonSelectAllAppProfile = (Button) inflate.findViewById(g4.p.button_select_all_app_profile);
        this.mediaLinkManagerContainer = inflate.findViewById(g4.p.media_link_manager_container);
        this.recyclerMediaProfiles = (RecyclerView) inflate.findViewById(g4.p.recycler_media_profiles);
        this.textMediaProfileEmpty = (TextView) inflate.findViewById(g4.p.text_media_profile_empty);
        this.buttonAddMediaProfile = (Button) inflate.findViewById(g4.p.button_add_media_profile);
        this.buttonMultiSelectMediaProfile = (Button) inflate.findViewById(g4.p.button_multi_select_media_profile);
        this.buttonDeleteSelectedMediaProfile = (Button) inflate.findViewById(g4.p.button_delete_selected_media_profile);
        this.buttonSelectAllMediaProfile = (Button) inflate.findViewById(g4.p.button_select_all_media_profile);
        this.assistantLinkManagerContainer = inflate.findViewById(g4.p.assistant_link_manager_container);
        this.recyclerAssistantProfiles = (RecyclerView) inflate.findViewById(g4.p.recycler_assistant_profiles);
        this.textAssistantProfileEmpty = (TextView) inflate.findViewById(g4.p.text_assistant_profile_empty);
        this.buttonAddAssistantProfile = (Button) inflate.findViewById(g4.p.button_add_assistant_profile);
        this.buttonMultiSelectAssistantProfile = (Button) inflate.findViewById(g4.p.button_multi_select_assistant_profile);
        this.buttonDeleteSelectedAssistantProfile = (Button) inflate.findViewById(g4.p.button_delete_selected_assistant_profile);
        this.buttonSelectAllAssistantProfile = (Button) inflate.findViewById(g4.p.button_select_all_assistant_profile);
        SeekBar seekBar = (SeekBar) inflate.findViewById(g4.p.seek_media_volume);
        final TextView textView = (TextView) inflate.findViewById(g4.p.text_media_volume);
        RadioGroup radioGroup = (RadioGroup) inflate.findViewById(g4.p.radio_media_channel_group);
        RadioButton radioButton = (RadioButton) inflate.findViewById(g4.p.radio_media_channel_media);
        RadioButton radioButton2 = (RadioButton) inflate.findViewById(g4.p.radio_media_channel_navigation);
        RadioButton radioButton3 = (RadioButton) inflate.findViewById(g4.p.radio_media_channel_notification);
        int logMediaVolume = SharedPreferencesUtils.getLogMediaVolume(requireContext());
        if (logMediaVolume < 0) {
            logMediaVolume = 0;
        }
        if (logMediaVolume > 100) {
            logMediaVolume = 100;
        }
        String logMediaChannel = SharedPreferencesUtils.getLogMediaChannel(requireContext());
        final int i8 = 2;
        final int i9 = 1;
        if (radioGroup != null && radioButton != null && radioButton2 != null && radioButton3 != null) {
            if ("navigation".equalsIgnoreCase(logMediaChannel)) {
                radioButton2.setChecked(true);
            } else if ("notification".equalsIgnoreCase(logMediaChannel)) {
                radioButton3.setChecked(true);
            } else {
                radioButton.setChecked(true);
            }
            radioGroup.setOnCheckedChangeListener(new com.navi.tool.ui.theme.e(this, i8));
        }
        if (seekBar != null) {
            seekBar.setProgress(logMediaVolume);
            if (textView != null) {
                a.a.v("播放音量: ", logMediaVolume, "%", textView);
            }
            seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() { // from class: com.navi.tool.ui.log.LogLinkFragment.1
                private ToneGenerator toneGenerator;

                @Override // android.widget.SeekBar.OnSeekBarChangeListener
                public void onProgressChanged(SeekBar seekBar2, int i10, boolean z6) {
                    TextView textView2 = textView;
                    if (textView2 != null) {
                        a.a.v("播放音量: ", i10, "%", textView2);
                    }
                    SharedPreferencesUtils.setLogMediaVolume(LogLinkFragment.this.requireContext(), i10);
                    if (z6) {
                        ToneGenerator toneGenerator = this.toneGenerator;
                        if (toneGenerator != null) {
                            try {
                                toneGenerator.release();
                            } catch (Exception unused) {
                            }
                            this.toneGenerator = null;
                        }
                        int i11 = ((i10 * 80) / 100) + 20;
                        if (i11 < 0) {
                            i11 = 0;
                        }
                        if (i11 > 100) {
                            i11 = 100;
                        }
                        ToneGenerator toneGenerator2 = new ToneGenerator(3, i11);
                        this.toneGenerator = toneGenerator2;
                        try {
                            toneGenerator2.startTone(24, 100);
                        } catch (Exception unused2) {
                        }
                    }
                }

                @Override // android.widget.SeekBar.OnSeekBarChangeListener
                public void onStartTrackingTouch(SeekBar seekBar2) {
                }

                @Override // android.widget.SeekBar.OnSeekBarChangeListener
                public void onStopTrackingTouch(SeekBar seekBar2) {
                    ToneGenerator toneGenerator = this.toneGenerator;
                    if (toneGenerator != null) {
                        try {
                            toneGenerator.release();
                        } catch (Exception unused) {
                        }
                        this.toneGenerator = null;
                    }
                }
            });
        }
        this.logLinkSwitch.setChecked(this.sharedPreferences.getBoolean("log_link_switch_state", false));
        if (this.switchAppLink != null) {
            this.switchAppLink.setChecked(this.sharedPreferences.getBoolean("log_link_apps_switch_state", false));
            this.switchAppLink.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener(this) { // from class: com.navi.tool.ui.log.u

                /* renamed from: b, reason: collision with root package name */
                public final /* synthetic */ LogLinkFragment f5743b;

                {
                    this.f5743b = this;
                }

                @Override // android.widget.CompoundButton.OnCheckedChangeListener
                public final void onCheckedChanged(CompoundButton compoundButton, boolean z6) {
                    int i10 = i7;
                    LogLinkFragment logLinkFragment = this.f5743b;
                    switch (i10) {
                        case 0:
                            logLinkFragment.lambda$onCreateView$1(compoundButton, z6);
                            break;
                        case 1:
                            logLinkFragment.lambda$onCreateView$2(compoundButton, z6);
                            break;
                        default:
                            logLinkFragment.lambda$onCreateView$3(compoundButton, z6);
                            break;
                    }
                }
            });
        }
        if (this.switchMediaLink != null) {
            this.switchMediaLink.setChecked(this.sharedPreferences.getBoolean("log_link_media_switch_state", false));
            this.switchMediaLink.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener(this) { // from class: com.navi.tool.ui.log.u

                /* renamed from: b, reason: collision with root package name */
                public final /* synthetic */ LogLinkFragment f5743b;

                {
                    this.f5743b = this;
                }

                @Override // android.widget.CompoundButton.OnCheckedChangeListener
                public final void onCheckedChanged(CompoundButton compoundButton, boolean z6) {
                    int i10 = i9;
                    LogLinkFragment logLinkFragment = this.f5743b;
                    switch (i10) {
                        case 0:
                            logLinkFragment.lambda$onCreateView$1(compoundButton, z6);
                            break;
                        case 1:
                            logLinkFragment.lambda$onCreateView$2(compoundButton, z6);
                            break;
                        default:
                            logLinkFragment.lambda$onCreateView$3(compoundButton, z6);
                            break;
                    }
                }
            });
        }
        if (this.switchAssistantLink != null) {
            this.switchAssistantLink.setChecked(SharedPreferencesUtils.isLogAssistantLinkEnabled(requireContext()));
            this.switchAssistantLink.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener(this) { // from class: com.navi.tool.ui.log.u

                /* renamed from: b, reason: collision with root package name */
                public final /* synthetic */ LogLinkFragment f5743b;

                {
                    this.f5743b = this;
                }

                @Override // android.widget.CompoundButton.OnCheckedChangeListener
                public final void onCheckedChanged(CompoundButton compoundButton, boolean z6) {
                    int i10 = i8;
                    LogLinkFragment logLinkFragment = this.f5743b;
                    switch (i10) {
                        case 0:
                            logLinkFragment.lambda$onCreateView$1(compoundButton, z6);
                            break;
                        case 1:
                            logLinkFragment.lambda$onCreateView$2(compoundButton, z6);
                            break;
                        default:
                            logLinkFragment.lambda$onCreateView$3(compoundButton, z6);
                            break;
                    }
                }
            });
        }
        this.logLinkSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() { // from class: com.navi.tool.ui.log.LogLinkFragment.2
            @Override // android.widget.CompoundButton.OnCheckedChangeListener
            public void onCheckedChanged(CompoundButton compoundButton, boolean z6) {
                LogLinkFragment.this.sharedPreferences.edit().putBoolean("log_link_switch_state", z6).apply();
                if (z6) {
                    LogLinkFragment.this.startLogLinkageService();
                    LogLinkFragment.this.createNavitoolMp3FolderIfNeeded();
                    if (LogLinkFragment.this.permissionStatusContainer != null) {
                        LogLinkFragment.this.permissionStatusContainer.setVisibility(8);
                    }
                    if (LogLinkFragment.this.appLinkContainer != null) {
                        LogLinkFragment.this.appLinkContainer.setVisibility(0);
                    }
                    if (LogLinkFragment.this.mediaLinkContainer != null) {
                        LogLinkFragment.this.mediaLinkContainer.setVisibility(0);
                    }
                    if (LogLinkFragment.this.assistantLinkContainer != null) {
                        LogLinkFragment.this.assistantLinkContainer.setVisibility(0);
                    }
                    if (LogLinkFragment.this.switchAssistantLink != null && LogLinkFragment.this.switchAssistantLink.isChecked()) {
                        LogLinkFragment.this.ensureAssistantProfileDir();
                        LogLinkFragment.this.initAssistantProfilesUi();
                        LogLinkFragment.this.refreshAssistantProfiles();
                        if (LogLinkFragment.this.assistantLinkManagerContainer != null) {
                            LogLinkFragment.this.assistantLinkManagerContainer.setVisibility(0);
                        }
                        LogLinkFragment.this.sendReloadProfilesCommand();
                    }
                    if (LogLinkFragment.this.switchAppLink != null && LogLinkFragment.this.switchAppLink.isChecked()) {
                        LogLinkFragment.this.ensureAppProfileDir();
                        LogLinkFragment.this.initAppProfilesUi();
                        LogLinkFragment.this.refreshAppProfiles();
                        if (LogLinkFragment.this.appLinkManagerContainer != null) {
                            LogLinkFragment.this.appLinkManagerContainer.setVisibility(0);
                        }
                        LogLinkFragment.this.sendReloadProfilesCommand();
                    }
                    if (LogLinkFragment.this.switchMediaLink != null && LogLinkFragment.this.switchMediaLink.isChecked()) {
                        LogLinkFragment.this.ensureMediaProfileDir();
                        LogLinkFragment.this.initMediaProfilesUi();
                        LogLinkFragment.this.refreshMediaProfiles();
                        if (LogLinkFragment.this.mediaLinkManagerContainer != null) {
                            LogLinkFragment.this.mediaLinkManagerContainer.setVisibility(0);
                        }
                        LogLinkFragment.this.sendReloadProfilesCommand();
                    }
                } else {
                    LogLinkFragment.this.stopLogLinkageService();
                    if (LogLinkFragment.this.permissionStatusContainer != null) {
                        LogLinkFragment.this.permissionStatusContainer.setVisibility(0);
                    }
                    if (LogLinkFragment.this.appLinkContainer != null) {
                        LogLinkFragment.this.appLinkContainer.setVisibility(8);
                    }
                    if (LogLinkFragment.this.mediaLinkContainer != null) {
                        LogLinkFragment.this.mediaLinkContainer.setVisibility(8);
                    }
                    if (LogLinkFragment.this.assistantLinkContainer != null) {
                        LogLinkFragment.this.assistantLinkContainer.setVisibility(8);
                    }
                    if (LogLinkFragment.this.appLinkManagerContainer != null) {
                        LogLinkFragment.this.appLinkManagerContainer.setVisibility(8);
                    }
                    if (LogLinkFragment.this.mediaLinkManagerContainer != null) {
                        LogLinkFragment.this.mediaLinkManagerContainer.setVisibility(8);
                    }
                    if (LogLinkFragment.this.assistantLinkManagerContainer != null) {
                        LogLinkFragment.this.assistantLinkManagerContainer.setVisibility(8);
                    }
                }
                LogLinkFragment.this.updatePermissionStatus();
            }
        });
        updatePermissionStatus();
        return inflate;
    }

    @Override // androidx.fragment.app.z
    public void onResume() {
        SwitchMaterial switchMaterial;
        SwitchMaterial switchMaterial2;
        SwitchMaterial switchMaterial3;
        super.onResume();
        StorageMigrationHelper.performMigration(requireContext());
        updatePermissionStatus();
        SwitchMaterial switchMaterial4 = this.logLinkSwitch;
        if (switchMaterial4 != null && switchMaterial4.isChecked()) {
            createNavitoolMp3FolderIfNeeded();
        }
        SwitchMaterial switchMaterial5 = this.logLinkSwitch;
        if (switchMaterial5 != null && switchMaterial5.isChecked() && (switchMaterial3 = this.switchAppLink) != null && switchMaterial3.isChecked()) {
            ensureAppProfileDir();
            initAppProfilesUi();
            View view = this.appLinkManagerContainer;
            if (view != null) {
                view.setVisibility(0);
            }
            reloadAppProfilesAsync();
        }
        SwitchMaterial switchMaterial6 = this.logLinkSwitch;
        if (switchMaterial6 != null && switchMaterial6.isChecked() && (switchMaterial2 = this.switchMediaLink) != null && switchMaterial2.isChecked()) {
            ensureMediaProfileDir();
            initMediaProfilesUi();
            View view2 = this.mediaLinkManagerContainer;
            if (view2 != null) {
                view2.setVisibility(0);
            }
            reloadMediaProfilesAsync();
        }
        SwitchMaterial switchMaterial7 = this.logLinkSwitch;
        if (switchMaterial7 == null || !switchMaterial7.isChecked() || (switchMaterial = this.switchAssistantLink) == null || !switchMaterial.isChecked()) {
            return;
        }
        ensureAssistantProfileDir();
        initAssistantProfilesUi();
        View view3 = this.assistantLinkManagerContainer;
        if (view3 != null) {
            view3.setVisibility(0);
        }
        reloadAssistantProfilesAsync();
    }
}
