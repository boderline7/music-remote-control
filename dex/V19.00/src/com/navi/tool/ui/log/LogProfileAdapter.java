package com.navi.tool.ui.log;

import android.content.pm.PackageManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import androidx.recyclerview.widget.j2;
import androidx.recyclerview.widget.z0;
import com.navi.tool.databinding.ItemLogProfileBinding;
import com.navi.tool.model.LogAppProfile;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import s5.h0;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public class LogProfileAdapter extends z0 {
    private final OnProfileDeleteListener deleteListener;
    private final OnProfileEditListener editListener;
    private final Set<String> selectedNames;
    private boolean selectionMode;
    private final OnProfileToggleListener toggleListener;

    public static class Diff extends androidx.recyclerview.widget.t {
        @Override // androidx.recyclerview.widget.t
        public boolean areContentsTheSame(LogAppProfile logAppProfile, LogAppProfile logAppProfile2) {
            StringBuilder sb = new StringBuilder();
            String tag = logAppProfile.getTag();
            String str = h0.FRAGMENT_ENCODE_SET;
            sb.append(tag == null ? h0.FRAGMENT_ENCODE_SET : logAppProfile.getTag());
            sb.append(logAppProfile.getPattern() == null ? h0.FRAGMENT_ENCODE_SET : logAppProfile.getPattern());
            sb.append(logAppProfile.getAction() == null ? h0.FRAGMENT_ENCODE_SET : logAppProfile.getAction());
            sb.append(logAppProfile.getPackageName() == null ? h0.FRAGMENT_ENCODE_SET : logAppProfile.getPackageName());
            sb.append(logAppProfile.isEnabled());
            sb.append(logAppProfile.getConditionType() == null ? h0.FRAGMENT_ENCODE_SET : logAppProfile.getConditionType());
            sb.append(logAppProfile.getConditionVariable() == null ? h0.FRAGMENT_ENCODE_SET : logAppProfile.getConditionVariable());
            sb.append(logAppProfile.getConditionOperator() == null ? h0.FRAGMENT_ENCODE_SET : logAppProfile.getConditionOperator());
            sb.append(logAppProfile.getConditionValue() == null ? h0.FRAGMENT_ENCODE_SET : logAppProfile.getConditionValue());
            sb.append(logAppProfile.getVariableName() == null ? h0.FRAGMENT_ENCODE_SET : logAppProfile.getVariableName());
            sb.append(logAppProfile.getVariableOp() == null ? h0.FRAGMENT_ENCODE_SET : logAppProfile.getVariableOp());
            sb.append(logAppProfile.getVariableValue() == null ? h0.FRAGMENT_ENCODE_SET : logAppProfile.getVariableValue());
            sb.append(logAppProfile.getDelaySeconds());
            sb.append(logAppProfile.getCooldownSeconds());
            sb.append(logAppProfile.getComboSeconds());
            sb.append(logAppProfile.getComboCount());
            String sb2 = sb.toString();
            StringBuilder sb3 = new StringBuilder();
            sb3.append(logAppProfile2.getTag() == null ? h0.FRAGMENT_ENCODE_SET : logAppProfile2.getTag());
            sb3.append(logAppProfile2.getPattern() == null ? h0.FRAGMENT_ENCODE_SET : logAppProfile2.getPattern());
            sb3.append(logAppProfile2.getAction() == null ? h0.FRAGMENT_ENCODE_SET : logAppProfile2.getAction());
            sb3.append(logAppProfile2.getPackageName() == null ? h0.FRAGMENT_ENCODE_SET : logAppProfile2.getPackageName());
            sb3.append(logAppProfile2.isEnabled());
            sb3.append(logAppProfile2.getConditionType() == null ? h0.FRAGMENT_ENCODE_SET : logAppProfile2.getConditionType());
            sb3.append(logAppProfile2.getConditionVariable() == null ? h0.FRAGMENT_ENCODE_SET : logAppProfile2.getConditionVariable());
            sb3.append(logAppProfile2.getConditionOperator() == null ? h0.FRAGMENT_ENCODE_SET : logAppProfile2.getConditionOperator());
            sb3.append(logAppProfile2.getConditionValue() == null ? h0.FRAGMENT_ENCODE_SET : logAppProfile2.getConditionValue());
            sb3.append(logAppProfile2.getVariableName() == null ? h0.FRAGMENT_ENCODE_SET : logAppProfile2.getVariableName());
            sb3.append(logAppProfile2.getVariableOp() == null ? h0.FRAGMENT_ENCODE_SET : logAppProfile2.getVariableOp());
            if (logAppProfile2.getVariableValue() != null) {
                str = logAppProfile2.getVariableValue();
            }
            sb3.append(str);
            sb3.append(logAppProfile2.getDelaySeconds());
            sb3.append(logAppProfile2.getCooldownSeconds());
            sb3.append(logAppProfile2.getComboSeconds());
            sb3.append(logAppProfile2.getComboCount());
            return sb2.equals(sb3.toString());
        }

        @Override // androidx.recyclerview.widget.t
        public boolean areItemsTheSame(LogAppProfile logAppProfile, LogAppProfile logAppProfile2) {
            return logAppProfile.getName() != null && logAppProfile.getName().equals(logAppProfile2.getName());
        }
    }

    public class LogProfileViewHolder extends j2 {
        private final ItemLogProfileBinding binding;

        public LogProfileViewHolder(ItemLogProfileBinding itemLogProfileBinding) {
            super(itemLogProfileBinding.f4836a);
            this.binding = itemLogProfileBinding;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public /* synthetic */ void lambda$bind$0(LogAppProfile logAppProfile, OnProfileToggleListener onProfileToggleListener, CompoundButton compoundButton, boolean z6) {
            if (LogProfileAdapter.this.selectionMode) {
                compoundButton.setChecked(logAppProfile.isEnabled());
            } else {
                if (!compoundButton.isPressed() || onProfileToggleListener == null) {
                    return;
                }
                onProfileToggleListener.onProfileToggle(logAppProfile);
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public /* synthetic */ void lambda$bind$1(LogAppProfile logAppProfile, View view) {
            if (!LogProfileAdapter.this.selectionMode) {
                this.binding.f4837b.setChecked(false);
                return;
            }
            String name = logAppProfile.getName();
            if (name == null) {
                return;
            }
            if (this.binding.f4837b.isChecked()) {
                LogProfileAdapter.this.selectedNames.add(name);
            } else {
                LogProfileAdapter.this.selectedNames.remove(name);
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public /* synthetic */ void lambda$bind$2(LogAppProfile logAppProfile, OnProfileEditListener onProfileEditListener, View view) {
            if (!LogProfileAdapter.this.selectionMode) {
                if (onProfileEditListener != null) {
                    onProfileEditListener.onProfileEdit(logAppProfile);
                    return;
                }
                return;
            }
            String name = logAppProfile.getName();
            if (name != null) {
                if (LogProfileAdapter.this.selectedNames.contains(name)) {
                    LogProfileAdapter.this.selectedNames.remove(name);
                } else {
                    LogProfileAdapter.this.selectedNames.add(name);
                }
                if (this.binding.f4837b != null) {
                    this.binding.f4837b.setChecked(LogProfileAdapter.this.selectedNames.contains(name));
                }
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public /* synthetic */ boolean lambda$bind$3(OnProfileDeleteListener onProfileDeleteListener, LogAppProfile logAppProfile, View view) {
            if (LogProfileAdapter.this.selectionMode) {
                view.performClick();
                return true;
            }
            if (onProfileDeleteListener != null) {
                onProfileDeleteListener.onProfileDelete(logAppProfile);
            }
            return true;
        }

        public void bind(LogAppProfile logAppProfile, OnProfileToggleListener onProfileToggleListener, OnProfileEditListener onProfileEditListener, OnProfileDeleteListener onProfileDeleteListener) {
            String packageName;
            this.binding.f4839d.setText(logAppProfile.getName());
            try {
                PackageManager packageManager = this.itemView.getContext().getPackageManager();
                packageName = packageManager.getApplicationLabel(packageManager.getApplicationInfo(logAppProfile.getPackageName(), 0)).toString();
            } catch (Exception unused) {
                packageName = logAppProfile.getPackageName();
            }
            StringBuilder sb = new StringBuilder("标签:");
            sb.append(logAppProfile.getTag());
            sb.append(" 匹配:");
            sb.append(logAppProfile.getPattern());
            String action = logAppProfile.getAction();
            String str = "open".equalsIgnoreCase(action) ? "打开" : "switch_floating_app".equalsIgnoreCase(action) ? "切换悬浮应用" : "switch_lyrics_fx".equalsIgnoreCase(action) ? "切换全屏歌词" : "home".equalsIgnoreCase(action) ? "返回原车桌面" : "switch_view".equalsIgnoreCase(action) ? "切换桌面助手视图" : "switch_clean_mode".equalsIgnoreCase(action) ? "切换壁纸清屏模式" : "switch_wallpaper_mode".equalsIgnoreCase(action) ? "切换壁纸模式" : "displaysiri".equalsIgnoreCase(action) ? "开启DiplaySiri" : "adb_switch".equalsIgnoreCase(action) ? "开启ADB界面" : "none".equalsIgnoreCase(action) ? "无" : "关闭";
            sb.append(" 动作:");
            sb.append(str);
            if (!"home".equalsIgnoreCase(action) && !"switch_floating_app".equalsIgnoreCase(action) && !"switch_lyrics_fx".equalsIgnoreCase(action) && !"switch_view".equalsIgnoreCase(action) && !"switch_clean_mode".equalsIgnoreCase(action) && !"switch_wallpaper_mode".equalsIgnoreCase(action) && !"displaysiri".equalsIgnoreCase(action) && !"adb_switch".equalsIgnoreCase(action) && !"none".equalsIgnoreCase(action)) {
                sb.append(" 应用:");
                sb.append(packageName);
                sb.append(" (");
                sb.append(logAppProfile.getPackageName());
                sb.append(")");
            }
            if ("variable".equalsIgnoreCase(logAppProfile.getConditionType())) {
                String conditionVariable = logAppProfile.getConditionVariable();
                String conditionOperator = logAppProfile.getConditionOperator();
                String conditionValue = logAppProfile.getConditionValue();
                if (conditionVariable != null && !conditionVariable.isEmpty()) {
                    if (conditionOperator == null || conditionOperator.isEmpty()) {
                        conditionOperator = "=";
                    }
                    sb.append(" 执行条件:");
                    sb.append(conditionVariable);
                    sb.append(" ");
                    sb.append(conditionOperator);
                    if (conditionValue != null && !conditionValue.isEmpty()) {
                        sb.append(" ");
                        sb.append(conditionValue);
                    }
                }
            }
            if (logAppProfile.getDelaySeconds() > 0) {
                sb.append(" [延时: ");
                sb.append(logAppProfile.getDelaySeconds());
                sb.append("秒]");
            }
            if (logAppProfile.getCooldownSeconds() > 0) {
                sb.append(" [冷却: ");
                sb.append(logAppProfile.getCooldownSeconds());
                sb.append("秒]");
            }
            String variableName = logAppProfile.getVariableName();
            String variableOp = logAppProfile.getVariableOp();
            String variableValue = logAppProfile.getVariableValue();
            if (variableName != null && !variableName.isEmpty()) {
                sb.append(" 变量动作:");
                sb.append(variableName);
                sb.append(" ");
                if ("clear".equalsIgnoreCase(variableOp)) {
                    sb.append("清空");
                } else {
                    sb.append("赋值");
                    if (variableValue != null && !variableValue.isEmpty()) {
                        sb.append(" ");
                        sb.append(variableValue);
                    }
                }
            }
            if (logAppProfile.getComboCount() > 0 && logAppProfile.getComboSeconds() > 0) {
                sb.append(" [连击: ");
                sb.append(logAppProfile.getComboSeconds());
                sb.append("秒");
                sb.append(logAppProfile.getComboCount());
                sb.append("次]");
            }
            this.binding.f4840e.setText(sb.toString());
            boolean z6 = LogProfileAdapter.this.selectionMode && logAppProfile.getName() != null && LogProfileAdapter.this.selectedNames.contains(logAppProfile.getName());
            CheckBox checkBox = this.binding.f4837b;
            if (checkBox != null) {
                checkBox.setVisibility(LogProfileAdapter.this.selectionMode ? 0 : 8);
                this.binding.f4837b.setChecked(z6);
            }
            this.binding.f4838c.setEnabled(true ^ LogProfileAdapter.this.selectionMode);
            this.binding.f4838c.setChecked(logAppProfile.isEnabled());
            int i7 = 3;
            this.binding.f4838c.setOnCheckedChangeListener(new a(this, logAppProfile, onProfileToggleListener, i7));
            CheckBox checkBox2 = this.binding.f4837b;
            int i8 = 2;
            if (checkBox2 != null) {
                checkBox2.setOnClickListener(new b(this, logAppProfile, i8));
            }
            this.binding.f4836a.setOnClickListener(new c(this, logAppProfile, onProfileEditListener, i7));
            this.binding.f4836a.setOnLongClickListener(new d(this, onProfileDeleteListener, logAppProfile, i8));
        }
    }

    public interface OnProfileDeleteListener {
        void onProfileDelete(LogAppProfile logAppProfile);
    }

    public interface OnProfileEditListener {
        void onProfileEdit(LogAppProfile logAppProfile);
    }

    public interface OnProfileToggleListener {
        void onProfileToggle(LogAppProfile logAppProfile);
    }

    public LogProfileAdapter(OnProfileToggleListener onProfileToggleListener, OnProfileEditListener onProfileEditListener, OnProfileDeleteListener onProfileDeleteListener) {
        super(new Diff());
        this.selectedNames = new HashSet();
        this.toggleListener = onProfileToggleListener;
        this.editListener = onProfileEditListener;
        this.deleteListener = onProfileDeleteListener;
    }

    public void clearSelection() {
        this.selectedNames.clear();
        notifyDataSetChanged();
    }

    public List<String> getSelectedProfileNames() {
        return new ArrayList(this.selectedNames);
    }

    public boolean isSelectionMode() {
        return this.selectionMode;
    }

    @Override // androidx.recyclerview.widget.h1
    public void onBindViewHolder(LogProfileViewHolder logProfileViewHolder, int i7) {
        logProfileViewHolder.bind((LogAppProfile) getItem(i7), this.toggleListener, this.editListener, this.deleteListener);
    }

    @Override // androidx.recyclerview.widget.h1
    public LogProfileViewHolder onCreateViewHolder(ViewGroup viewGroup, int i7) {
        return new LogProfileViewHolder(ItemLogProfileBinding.inflate(LayoutInflater.from(viewGroup.getContext()), viewGroup, false));
    }

    public void selectAll() {
        this.selectedNames.clear();
        Iterator<Object> it = getCurrentList().iterator();
        while (it.hasNext()) {
            LogAppProfile logAppProfile = (LogAppProfile) it.next();
            if (logAppProfile != null && logAppProfile.getName() != null) {
                this.selectedNames.add(logAppProfile.getName());
            }
        }
        notifyDataSetChanged();
    }

    public void setSelectionMode(boolean z6) {
        if (this.selectionMode == z6) {
            return;
        }
        this.selectionMode = z6;
        if (!z6) {
            this.selectedNames.clear();
        }
        notifyDataSetChanged();
    }
}
