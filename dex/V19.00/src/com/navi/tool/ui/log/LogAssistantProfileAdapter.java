package com.navi.tool.ui.log;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import androidx.recyclerview.widget.j2;
import androidx.recyclerview.widget.z0;
import com.navi.tool.databinding.ItemLogProfileBinding;
import com.navi.tool.model.LogAssistantProfile;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import s5.h0;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public class LogAssistantProfileAdapter extends z0 {
    private final OnProfileDeleteListener deleteListener;
    private final OnProfileEditListener editListener;
    private final Set<String> selectedNames;
    private boolean selectionMode;
    private final OnProfileToggleListener toggleListener;

    public static class Diff extends androidx.recyclerview.widget.t {
        @Override // androidx.recyclerview.widget.t
        public boolean areContentsTheSame(LogAssistantProfile logAssistantProfile, LogAssistantProfile logAssistantProfile2) {
            StringBuilder sb = new StringBuilder();
            String tag = logAssistantProfile.getTag();
            String str = h0.FRAGMENT_ENCODE_SET;
            sb.append(tag == null ? h0.FRAGMENT_ENCODE_SET : logAssistantProfile.getTag());
            sb.append(logAssistantProfile.getPattern() == null ? h0.FRAGMENT_ENCODE_SET : logAssistantProfile.getPattern());
            sb.append(logAssistantProfile.getAction() == null ? h0.FRAGMENT_ENCODE_SET : logAssistantProfile.getAction());
            sb.append(logAssistantProfile.isEnabled());
            sb.append(logAssistantProfile.getConditionType() == null ? h0.FRAGMENT_ENCODE_SET : logAssistantProfile.getConditionType());
            sb.append(logAssistantProfile.getConditionVariable() == null ? h0.FRAGMENT_ENCODE_SET : logAssistantProfile.getConditionVariable());
            sb.append(logAssistantProfile.getConditionOperator() == null ? h0.FRAGMENT_ENCODE_SET : logAssistantProfile.getConditionOperator());
            sb.append(logAssistantProfile.getConditionValue() == null ? h0.FRAGMENT_ENCODE_SET : logAssistantProfile.getConditionValue());
            sb.append(logAssistantProfile.getVariableName() == null ? h0.FRAGMENT_ENCODE_SET : logAssistantProfile.getVariableName());
            sb.append(logAssistantProfile.getVariableOp() == null ? h0.FRAGMENT_ENCODE_SET : logAssistantProfile.getVariableOp());
            sb.append(logAssistantProfile.getVariableValue() == null ? h0.FRAGMENT_ENCODE_SET : logAssistantProfile.getVariableValue());
            sb.append(logAssistantProfile.getDelaySeconds());
            sb.append(logAssistantProfile.getCooldownSeconds());
            sb.append(logAssistantProfile.getComboSeconds());
            sb.append(logAssistantProfile.getComboCount());
            String sb2 = sb.toString();
            StringBuilder sb3 = new StringBuilder();
            sb3.append(logAssistantProfile2.getTag() == null ? h0.FRAGMENT_ENCODE_SET : logAssistantProfile2.getTag());
            sb3.append(logAssistantProfile2.getPattern() == null ? h0.FRAGMENT_ENCODE_SET : logAssistantProfile2.getPattern());
            sb3.append(logAssistantProfile2.getAction() == null ? h0.FRAGMENT_ENCODE_SET : logAssistantProfile2.getAction());
            sb3.append(logAssistantProfile2.isEnabled());
            sb3.append(logAssistantProfile2.getConditionType() == null ? h0.FRAGMENT_ENCODE_SET : logAssistantProfile2.getConditionType());
            sb3.append(logAssistantProfile2.getConditionVariable() == null ? h0.FRAGMENT_ENCODE_SET : logAssistantProfile2.getConditionVariable());
            sb3.append(logAssistantProfile2.getConditionOperator() == null ? h0.FRAGMENT_ENCODE_SET : logAssistantProfile2.getConditionOperator());
            sb3.append(logAssistantProfile2.getConditionValue() == null ? h0.FRAGMENT_ENCODE_SET : logAssistantProfile2.getConditionValue());
            sb3.append(logAssistantProfile2.getVariableName() == null ? h0.FRAGMENT_ENCODE_SET : logAssistantProfile2.getVariableName());
            sb3.append(logAssistantProfile2.getVariableOp() == null ? h0.FRAGMENT_ENCODE_SET : logAssistantProfile2.getVariableOp());
            if (logAssistantProfile2.getVariableValue() != null) {
                str = logAssistantProfile2.getVariableValue();
            }
            sb3.append(str);
            sb3.append(logAssistantProfile2.getDelaySeconds());
            sb3.append(logAssistantProfile2.getCooldownSeconds());
            sb3.append(logAssistantProfile2.getComboSeconds());
            sb3.append(logAssistantProfile2.getComboCount());
            return sb2.equals(sb3.toString());
        }

        @Override // androidx.recyclerview.widget.t
        public boolean areItemsTheSame(LogAssistantProfile logAssistantProfile, LogAssistantProfile logAssistantProfile2) {
            return logAssistantProfile.getName() != null && logAssistantProfile.getName().equals(logAssistantProfile2.getName());
        }
    }

    public class LogAssistantProfileViewHolder extends j2 {
        private final ItemLogProfileBinding binding;

        public LogAssistantProfileViewHolder(ItemLogProfileBinding itemLogProfileBinding) {
            super(itemLogProfileBinding.f4836a);
            this.binding = itemLogProfileBinding;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public /* synthetic */ void lambda$bind$0(LogAssistantProfile logAssistantProfile, OnProfileToggleListener onProfileToggleListener, CompoundButton compoundButton, boolean z6) {
            if (LogAssistantProfileAdapter.this.selectionMode) {
                compoundButton.setChecked(logAssistantProfile.isEnabled());
            } else {
                if (!compoundButton.isPressed() || onProfileToggleListener == null) {
                    return;
                }
                onProfileToggleListener.onProfileToggle(logAssistantProfile);
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public /* synthetic */ void lambda$bind$1(LogAssistantProfile logAssistantProfile, View view) {
            if (!LogAssistantProfileAdapter.this.selectionMode) {
                this.binding.f4837b.setChecked(false);
                return;
            }
            String name = logAssistantProfile.getName();
            if (name == null) {
                return;
            }
            if (this.binding.f4837b.isChecked()) {
                LogAssistantProfileAdapter.this.selectedNames.add(name);
            } else {
                LogAssistantProfileAdapter.this.selectedNames.remove(name);
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public /* synthetic */ void lambda$bind$2(LogAssistantProfile logAssistantProfile, OnProfileEditListener onProfileEditListener, View view) {
            if (!LogAssistantProfileAdapter.this.selectionMode) {
                if (onProfileEditListener != null) {
                    onProfileEditListener.onProfileEdit(logAssistantProfile);
                    return;
                }
                return;
            }
            String name = logAssistantProfile.getName();
            if (name != null) {
                if (LogAssistantProfileAdapter.this.selectedNames.contains(name)) {
                    LogAssistantProfileAdapter.this.selectedNames.remove(name);
                } else {
                    LogAssistantProfileAdapter.this.selectedNames.add(name);
                }
                if (this.binding.f4837b != null) {
                    this.binding.f4837b.setChecked(LogAssistantProfileAdapter.this.selectedNames.contains(name));
                }
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public /* synthetic */ boolean lambda$bind$3(OnProfileDeleteListener onProfileDeleteListener, LogAssistantProfile logAssistantProfile, View view) {
            if (LogAssistantProfileAdapter.this.selectionMode) {
                view.performClick();
                return true;
            }
            if (onProfileDeleteListener != null) {
                onProfileDeleteListener.onProfileDelete(logAssistantProfile);
            }
            return true;
        }

        public void bind(LogAssistantProfile logAssistantProfile, OnProfileToggleListener onProfileToggleListener, OnProfileEditListener onProfileEditListener, OnProfileDeleteListener onProfileDeleteListener) {
            this.binding.f4839d.setText(logAssistantProfile.getName());
            String action = logAssistantProfile.getAction();
            if ("activate_cast".equalsIgnoreCase(action)) {
                action = "激活飞屏";
            } else if ("drive_enjoy_mode".equalsIgnoreCase(action)) {
                action = "驾享模式";
            } else if ("open_ac_ui".equalsIgnoreCase(action)) {
                action = "打开空调界面";
            } else if ("close_ac_ui".equalsIgnoreCase(action)) {
                action = "关闭空调界面";
            } else if ("open_systemui_ac_ui".equalsIgnoreCase(action)) {
                action = "打开UI级空调界面";
            } else if ("close_systemui_ac_ui".equalsIgnoreCase(action)) {
                action = "关闭UI级空调界面";
            } else if ("open_360_camera".equalsIgnoreCase(action)) {
                action = "打开360";
            } else if ("instrument_cast".equalsIgnoreCase(action)) {
                action = "仪表投屏";
            } else if ("cluster_enlarge".equalsIgnoreCase(action)) {
                action = "放大仪表";
            } else if ("cluster_shrink".equalsIgnoreCase(action)) {
                action = "缩小仪表";
            } else if ("close_windows".equalsIgnoreCase(action)) {
                action = "关闭窗户";
            } else if ("open_sunblind".equalsIgnoreCase(action)) {
                action = "打开遮阳帘";
            } else if ("close_sunblind".equalsIgnoreCase(action)) {
                action = "关闭遮阳帘";
            } else if ("epb_release".equalsIgnoreCase(action)) {
                action = "松开手刹";
            } else if ("epb_apply".equalsIgnoreCase(action)) {
                action = "拉起手刹";
            } else if ("radar_open".equalsIgnoreCase(action)) {
                action = "打开雷达";
            } else if ("radar_close".equalsIgnoreCase(action)) {
                action = "关闭雷达";
            } else if ("none".equalsIgnoreCase(action)) {
                action = "无";
            } else if (action == null) {
                action = h0.FRAGMENT_ENCODE_SET;
            }
            StringBuilder j7 = a.a.j("标签:");
            j7.append(logAssistantProfile.getTag());
            j7.append(" 匹配:");
            j7.append(logAssistantProfile.getPattern());
            j7.append(" 动作:");
            j7.append(action);
            if ("variable".equalsIgnoreCase(logAssistantProfile.getConditionType())) {
                String conditionVariable = logAssistantProfile.getConditionVariable();
                String conditionOperator = logAssistantProfile.getConditionOperator();
                String conditionValue = logAssistantProfile.getConditionValue();
                if (conditionVariable != null && !conditionVariable.isEmpty()) {
                    if (conditionOperator == null || conditionOperator.isEmpty()) {
                        conditionOperator = "=";
                    }
                    j7.append(" 执行条件:");
                    j7.append(conditionVariable);
                    j7.append(" ");
                    j7.append(conditionOperator);
                    if (conditionValue != null && !conditionValue.isEmpty()) {
                        j7.append(" ");
                        j7.append(conditionValue);
                    }
                }
            }
            if (logAssistantProfile.getDelaySeconds() > 0) {
                j7.append(" [延时: ");
                j7.append(logAssistantProfile.getDelaySeconds());
                j7.append("秒]");
            }
            if (logAssistantProfile.getCooldownSeconds() > 0) {
                j7.append(" [冷却: ");
                j7.append(logAssistantProfile.getCooldownSeconds());
                j7.append("秒]");
            }
            String variableName = logAssistantProfile.getVariableName();
            String variableOp = logAssistantProfile.getVariableOp();
            String variableValue = logAssistantProfile.getVariableValue();
            if (variableName != null && !variableName.isEmpty()) {
                j7.append(" 变量动作:");
                j7.append(variableName);
                j7.append(" ");
                if ("clear".equalsIgnoreCase(variableOp)) {
                    j7.append("清空");
                } else {
                    j7.append("赋值");
                    if (variableValue != null && !variableValue.isEmpty()) {
                        j7.append(" ");
                        j7.append(variableValue);
                    }
                }
            }
            if (logAssistantProfile.getComboCount() > 0 && logAssistantProfile.getComboSeconds() > 0) {
                if (j7.length() > 0) {
                    j7.append(" | ");
                }
                j7.append("连击:");
                j7.append(logAssistantProfile.getComboSeconds());
                j7.append("秒");
                j7.append(logAssistantProfile.getComboCount());
                j7.append("次");
            }
            this.binding.f4840e.setText(j7.toString());
            int i7 = 0;
            boolean z6 = LogAssistantProfileAdapter.this.selectionMode && logAssistantProfile.getName() != null && LogAssistantProfileAdapter.this.selectedNames.contains(logAssistantProfile.getName());
            CheckBox checkBox = this.binding.f4837b;
            if (checkBox != null) {
                checkBox.setVisibility(LogAssistantProfileAdapter.this.selectionMode ? 0 : 8);
                this.binding.f4837b.setChecked(z6);
            }
            this.binding.f4838c.setEnabled(true ^ LogAssistantProfileAdapter.this.selectionMode);
            this.binding.f4838c.setChecked(logAssistantProfile.isEnabled());
            this.binding.f4838c.setOnCheckedChangeListener(new a(this, logAssistantProfile, onProfileToggleListener, i7));
            CheckBox checkBox2 = this.binding.f4837b;
            if (checkBox2 != null) {
                checkBox2.setOnClickListener(new b(this, logAssistantProfile, i7));
            }
            this.binding.f4836a.setOnClickListener(new c(this, logAssistantProfile, onProfileEditListener, i7));
            this.binding.f4836a.setOnLongClickListener(new d(this, onProfileDeleteListener, logAssistantProfile, i7));
        }
    }

    public interface OnProfileDeleteListener {
        void onProfileDelete(LogAssistantProfile logAssistantProfile);
    }

    public interface OnProfileEditListener {
        void onProfileEdit(LogAssistantProfile logAssistantProfile);
    }

    public interface OnProfileToggleListener {
        void onProfileToggle(LogAssistantProfile logAssistantProfile);
    }

    public LogAssistantProfileAdapter(OnProfileToggleListener onProfileToggleListener, OnProfileEditListener onProfileEditListener, OnProfileDeleteListener onProfileDeleteListener) {
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
    public void onBindViewHolder(LogAssistantProfileViewHolder logAssistantProfileViewHolder, int i7) {
        logAssistantProfileViewHolder.bind((LogAssistantProfile) getItem(i7), this.toggleListener, this.editListener, this.deleteListener);
    }

    @Override // androidx.recyclerview.widget.h1
    public LogAssistantProfileViewHolder onCreateViewHolder(ViewGroup viewGroup, int i7) {
        return new LogAssistantProfileViewHolder(ItemLogProfileBinding.inflate(LayoutInflater.from(viewGroup.getContext()), viewGroup, false));
    }

    public void selectAll() {
        this.selectedNames.clear();
        Iterator<Object> it = getCurrentList().iterator();
        while (it.hasNext()) {
            LogAssistantProfile logAssistantProfile = (LogAssistantProfile) it.next();
            if (logAssistantProfile != null && logAssistantProfile.getName() != null) {
                this.selectedNames.add(logAssistantProfile.getName());
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
