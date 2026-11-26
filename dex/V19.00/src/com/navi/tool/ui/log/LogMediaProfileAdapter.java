package com.navi.tool.ui.log;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import androidx.recyclerview.widget.j2;
import androidx.recyclerview.widget.z0;
import com.navi.tool.databinding.ItemLogProfileBinding;
import com.navi.tool.model.LogMediaProfile;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import s5.h0;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public class LogMediaProfileAdapter extends z0 {
    private final OnProfileDeleteListener deleteListener;
    private final OnProfileEditListener editListener;
    private final Set<String> selectedNames;
    private boolean selectionMode;
    private final OnProfileToggleListener toggleListener;

    public static class Diff extends androidx.recyclerview.widget.t {
        @Override // androidx.recyclerview.widget.t
        public boolean areContentsTheSame(LogMediaProfile logMediaProfile, LogMediaProfile logMediaProfile2) {
            StringBuilder sb = new StringBuilder();
            String name = logMediaProfile.getName();
            String str = h0.FRAGMENT_ENCODE_SET;
            sb.append(name == null ? h0.FRAGMENT_ENCODE_SET : logMediaProfile.getName());
            sb.append(logMediaProfile.getTag() == null ? h0.FRAGMENT_ENCODE_SET : logMediaProfile.getTag());
            sb.append(logMediaProfile.getPattern() == null ? h0.FRAGMENT_ENCODE_SET : logMediaProfile.getPattern());
            sb.append(logMediaProfile.getMediaPath() == null ? h0.FRAGMENT_ENCODE_SET : logMediaProfile.getMediaPath());
            sb.append(logMediaProfile.getAction() == null ? h0.FRAGMENT_ENCODE_SET : logMediaProfile.getAction());
            sb.append(logMediaProfile.isEnabled());
            sb.append(logMediaProfile.getConditionType() == null ? h0.FRAGMENT_ENCODE_SET : logMediaProfile.getConditionType());
            sb.append(logMediaProfile.getConditionVariable() == null ? h0.FRAGMENT_ENCODE_SET : logMediaProfile.getConditionVariable());
            sb.append(logMediaProfile.getConditionOperator() == null ? h0.FRAGMENT_ENCODE_SET : logMediaProfile.getConditionOperator());
            sb.append(logMediaProfile.getConditionValue() == null ? h0.FRAGMENT_ENCODE_SET : logMediaProfile.getConditionValue());
            sb.append(logMediaProfile.getVariableName() == null ? h0.FRAGMENT_ENCODE_SET : logMediaProfile.getVariableName());
            sb.append(logMediaProfile.getVariableOp() == null ? h0.FRAGMENT_ENCODE_SET : logMediaProfile.getVariableOp());
            sb.append(logMediaProfile.getVariableValue() == null ? h0.FRAGMENT_ENCODE_SET : logMediaProfile.getVariableValue());
            sb.append(logMediaProfile.getDelaySeconds());
            sb.append(logMediaProfile.getCooldownSeconds());
            sb.append(logMediaProfile.getComboSeconds());
            sb.append(logMediaProfile.getComboCount());
            String sb2 = sb.toString();
            StringBuilder sb3 = new StringBuilder();
            sb3.append(logMediaProfile2.getName() == null ? h0.FRAGMENT_ENCODE_SET : logMediaProfile2.getName());
            sb3.append(logMediaProfile2.getTag() == null ? h0.FRAGMENT_ENCODE_SET : logMediaProfile2.getTag());
            sb3.append(logMediaProfile2.getPattern() == null ? h0.FRAGMENT_ENCODE_SET : logMediaProfile2.getPattern());
            sb3.append(logMediaProfile2.getMediaPath() == null ? h0.FRAGMENT_ENCODE_SET : logMediaProfile2.getMediaPath());
            sb3.append(logMediaProfile2.getAction() == null ? h0.FRAGMENT_ENCODE_SET : logMediaProfile2.getAction());
            sb3.append(logMediaProfile2.isEnabled());
            sb3.append(logMediaProfile2.getConditionType() == null ? h0.FRAGMENT_ENCODE_SET : logMediaProfile2.getConditionType());
            sb3.append(logMediaProfile2.getConditionVariable() == null ? h0.FRAGMENT_ENCODE_SET : logMediaProfile2.getConditionVariable());
            sb3.append(logMediaProfile2.getConditionOperator() == null ? h0.FRAGMENT_ENCODE_SET : logMediaProfile2.getConditionOperator());
            sb3.append(logMediaProfile2.getConditionValue() == null ? h0.FRAGMENT_ENCODE_SET : logMediaProfile2.getConditionValue());
            sb3.append(logMediaProfile2.getVariableName() == null ? h0.FRAGMENT_ENCODE_SET : logMediaProfile2.getVariableName());
            sb3.append(logMediaProfile2.getVariableOp() == null ? h0.FRAGMENT_ENCODE_SET : logMediaProfile2.getVariableOp());
            if (logMediaProfile2.getVariableValue() != null) {
                str = logMediaProfile2.getVariableValue();
            }
            sb3.append(str);
            sb3.append(logMediaProfile2.getDelaySeconds());
            sb3.append(logMediaProfile2.getCooldownSeconds());
            sb3.append(logMediaProfile2.getComboSeconds());
            sb3.append(logMediaProfile2.getComboCount());
            return sb2.equals(sb3.toString());
        }

        @Override // androidx.recyclerview.widget.t
        public boolean areItemsTheSame(LogMediaProfile logMediaProfile, LogMediaProfile logMediaProfile2) {
            return logMediaProfile.getName() != null && logMediaProfile.getName().equals(logMediaProfile2.getName());
        }
    }

    public class LogMediaProfileViewHolder extends j2 {
        private final ItemLogProfileBinding binding;

        public LogMediaProfileViewHolder(ItemLogProfileBinding itemLogProfileBinding) {
            super(itemLogProfileBinding.f4836a);
            this.binding = itemLogProfileBinding;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public /* synthetic */ void lambda$bind$0(LogMediaProfile logMediaProfile, OnProfileToggleListener onProfileToggleListener, CompoundButton compoundButton, boolean z6) {
            if (LogMediaProfileAdapter.this.selectionMode) {
                compoundButton.setChecked(logMediaProfile.isEnabled());
            } else {
                if (!compoundButton.isPressed() || onProfileToggleListener == null) {
                    return;
                }
                onProfileToggleListener.onProfileToggle(logMediaProfile);
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public /* synthetic */ void lambda$bind$1(LogMediaProfile logMediaProfile, View view) {
            if (!LogMediaProfileAdapter.this.selectionMode) {
                this.binding.f4837b.setChecked(false);
                return;
            }
            String name = logMediaProfile.getName();
            if (name == null) {
                return;
            }
            if (this.binding.f4837b.isChecked()) {
                LogMediaProfileAdapter.this.selectedNames.add(name);
            } else {
                LogMediaProfileAdapter.this.selectedNames.remove(name);
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public /* synthetic */ void lambda$bind$2(LogMediaProfile logMediaProfile, OnProfileEditListener onProfileEditListener, View view) {
            if (!LogMediaProfileAdapter.this.selectionMode) {
                if (onProfileEditListener != null) {
                    onProfileEditListener.onProfileEdit(logMediaProfile);
                    return;
                }
                return;
            }
            String name = logMediaProfile.getName();
            if (name != null) {
                if (LogMediaProfileAdapter.this.selectedNames.contains(name)) {
                    LogMediaProfileAdapter.this.selectedNames.remove(name);
                } else {
                    LogMediaProfileAdapter.this.selectedNames.add(name);
                }
                if (this.binding.f4837b != null) {
                    this.binding.f4837b.setChecked(LogMediaProfileAdapter.this.selectedNames.contains(name));
                }
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public /* synthetic */ boolean lambda$bind$3(OnProfileDeleteListener onProfileDeleteListener, LogMediaProfile logMediaProfile, View view) {
            if (LogMediaProfileAdapter.this.selectionMode) {
                view.performClick();
                return true;
            }
            if (onProfileDeleteListener != null) {
                onProfileDeleteListener.onProfileDelete(logMediaProfile);
            }
            return true;
        }

        public void bind(LogMediaProfile logMediaProfile, OnProfileToggleListener onProfileToggleListener, OnProfileEditListener onProfileEditListener, OnProfileDeleteListener onProfileDeleteListener) {
            String str;
            this.binding.f4839d.setText(logMediaProfile.getName());
            int i7 = 1;
            if (logMediaProfile.getMediaPath() == null || logMediaProfile.getMediaPath().isEmpty()) {
                str = "未选择";
            } else {
                int lastIndexOf = logMediaProfile.getMediaPath().lastIndexOf(47);
                str = (lastIndexOf < 0 || lastIndexOf >= logMediaProfile.getMediaPath().length() - 1) ? logMediaProfile.getMediaPath() : logMediaProfile.getMediaPath().substring(lastIndexOf + 1);
            }
            String action = logMediaProfile.getAction();
            String str2 = "next".equalsIgnoreCase(action) ? "下一曲" : "previous".equalsIgnoreCase(action) ? "上一曲" : "play".equalsIgnoreCase(action) ? "播放" : "pause".equalsIgnoreCase(action) ? "暂停" : "toggle".equalsIgnoreCase(action) ? "切换播放/暂停" : "qq_music_car".equalsIgnoreCase(action) ? "QQ音乐车机版" : "kuwo_music_car".equalsIgnoreCase(action) ? "酷我音乐车机版" : "netease_music_car".equalsIgnoreCase(action) ? "网易云音乐车机版" : "luna_music_car".equalsIgnoreCase(action) ? "汽水音乐车机版" : "auto_search".equalsIgnoreCase(action) ? "自动搜索歌曲" : "auto_favorite".equalsIgnoreCase(action) ? "自动收藏歌曲" : "cycle_loop_mode".equalsIgnoreCase(action) ? "切换播放模式" : "none".equalsIgnoreCase(action) ? "无" : "播放文件";
            StringBuilder j7 = a.a.j("标签:");
            j7.append(logMediaProfile.getTag());
            j7.append(" 匹配:");
            j7.append(logMediaProfile.getPattern());
            j7.append(" 动作:");
            j7.append(str2);
            if ("play_file".equalsIgnoreCase(action)) {
                j7.append(" 媒体:");
                j7.append(str);
            }
            if ("variable".equalsIgnoreCase(logMediaProfile.getConditionType())) {
                String conditionVariable = logMediaProfile.getConditionVariable();
                String conditionOperator = logMediaProfile.getConditionOperator();
                String conditionValue = logMediaProfile.getConditionValue();
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
            if (logMediaProfile.getDelaySeconds() > 0) {
                j7.append(" [延时: ");
                j7.append(logMediaProfile.getDelaySeconds());
                j7.append("秒]");
            }
            if (logMediaProfile.getCooldownSeconds() > 0) {
                j7.append(" [冷却: ");
                j7.append(logMediaProfile.getCooldownSeconds());
                j7.append("秒]");
            }
            String variableName = logMediaProfile.getVariableName();
            String variableOp = logMediaProfile.getVariableOp();
            String variableValue = logMediaProfile.getVariableValue();
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
            if (logMediaProfile.getComboCount() > 0 && logMediaProfile.getComboSeconds() > 0) {
                if (j7.length() > 0) {
                    j7.append(" | ");
                }
                j7.append("连击:");
                j7.append(logMediaProfile.getComboSeconds());
                j7.append("秒");
                j7.append(logMediaProfile.getComboCount());
                j7.append("次");
            }
            this.binding.f4840e.setText(j7.toString());
            boolean z6 = LogMediaProfileAdapter.this.selectionMode && logMediaProfile.getName() != null && LogMediaProfileAdapter.this.selectedNames.contains(logMediaProfile.getName());
            CheckBox checkBox = this.binding.f4837b;
            if (checkBox != null) {
                checkBox.setVisibility(LogMediaProfileAdapter.this.selectionMode ? 0 : 8);
                this.binding.f4837b.setChecked(z6);
            }
            this.binding.f4838c.setEnabled(!LogMediaProfileAdapter.this.selectionMode);
            this.binding.f4838c.setChecked(logMediaProfile.isEnabled());
            int i8 = 2;
            this.binding.f4838c.setOnCheckedChangeListener(new a(this, logMediaProfile, onProfileToggleListener, i8));
            CheckBox checkBox2 = this.binding.f4837b;
            if (checkBox2 != null) {
                checkBox2.setOnClickListener(new b(this, logMediaProfile, i7));
            }
            this.binding.f4836a.setOnClickListener(new c(this, logMediaProfile, onProfileEditListener, i8));
            this.binding.f4836a.setOnLongClickListener(new d(this, onProfileDeleteListener, logMediaProfile, i7));
        }
    }

    public interface OnProfileDeleteListener {
        void onProfileDelete(LogMediaProfile logMediaProfile);
    }

    public interface OnProfileEditListener {
        void onProfileEdit(LogMediaProfile logMediaProfile);
    }

    public interface OnProfileToggleListener {
        void onProfileToggle(LogMediaProfile logMediaProfile);
    }

    public LogMediaProfileAdapter(OnProfileToggleListener onProfileToggleListener, OnProfileEditListener onProfileEditListener, OnProfileDeleteListener onProfileDeleteListener) {
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
    public void onBindViewHolder(LogMediaProfileViewHolder logMediaProfileViewHolder, int i7) {
        logMediaProfileViewHolder.bind((LogMediaProfile) getItem(i7), this.toggleListener, this.editListener, this.deleteListener);
    }

    @Override // androidx.recyclerview.widget.h1
    public LogMediaProfileViewHolder onCreateViewHolder(ViewGroup viewGroup, int i7) {
        return new LogMediaProfileViewHolder(ItemLogProfileBinding.inflate(LayoutInflater.from(viewGroup.getContext()), viewGroup, false));
    }

    public void selectAll() {
        this.selectedNames.clear();
        Iterator<Object> it = getCurrentList().iterator();
        while (it.hasNext()) {
            LogMediaProfile logMediaProfile = (LogMediaProfile) it.next();
            if (logMediaProfile != null && logMediaProfile.getName() != null) {
                this.selectedNames.add(logMediaProfile.getName());
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
