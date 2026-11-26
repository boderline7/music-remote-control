package com.navi.tool.ui.scene;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;
import androidx.recyclerview.widget.j2;
import androidx.recyclerview.widget.t;
import androidx.recyclerview.widget.z0;
import com.google.android.material.switchmaterial.SwitchMaterial;
import com.navi.tool.model.SceneRule;
import com.navi.tool.ui.scene.SceneRuleAdapter;
import g4.p;
import g4.q;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public class SceneRuleAdapter extends z0 {
    private final OnDeleteListener deleteListener;
    private final OnEditListener editListener;
    private final Set<String> selectedIds;
    private boolean selectionMode;
    private final OnToggleListener toggleListener;

    public static class Diff extends t {
        @Override // androidx.recyclerview.widget.t
        public boolean areContentsTheSame(SceneRule sceneRule, SceneRule sceneRule2) {
            return sceneRule.toJson().toString().equals(sceneRule2.toJson().toString());
        }

        @Override // androidx.recyclerview.widget.t
        public boolean areItemsTheSame(SceneRule sceneRule, SceneRule sceneRule2) {
            return sceneRule.getId().equals(sceneRule2.getId());
        }
    }

    public interface OnDeleteListener {
        void onDelete(SceneRule sceneRule);
    }

    public interface OnEditListener {
        void onEdit(SceneRule sceneRule);
    }

    public interface OnToggleListener {
        void onToggle(SceneRule sceneRule);
    }

    public static class SceneRuleViewHolder extends j2 {
        private final CheckBox checkboxSelect;
        private final SwitchMaterial switchEnabled;
        private final TextView textMeta;
        private final TextView textName;

        public SceneRuleViewHolder(View view) {
            super(view);
            this.textName = (TextView) view.findViewById(p.text_scene_name);
            this.textMeta = (TextView) view.findViewById(p.text_scene_meta);
            this.switchEnabled = (SwitchMaterial) view.findViewById(p.switch_scene_enabled);
            this.checkboxSelect = (CheckBox) view.findViewById(p.checkbox_scene_select);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static /* synthetic */ void lambda$bind$0(Set set, SceneRule sceneRule, CompoundButton compoundButton, boolean z6) {
            if (z6) {
                set.add(sceneRule.getId());
            } else {
                set.remove(sceneRule.getId());
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static /* synthetic */ void lambda$bind$1(OnToggleListener onToggleListener, SceneRule sceneRule, CompoundButton compoundButton, boolean z6) {
            if (!compoundButton.isPressed() || onToggleListener == null) {
                return;
            }
            onToggleListener.onToggle(sceneRule);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public /* synthetic */ void lambda$bind$2(boolean z6, Set set, SceneRule sceneRule, OnEditListener onEditListener, View view) {
            if (!z6) {
                if (onEditListener != null) {
                    onEditListener.onEdit(sceneRule);
                }
            } else {
                boolean z7 = !set.contains(sceneRule.getId());
                if (z7) {
                    set.add(sceneRule.getId());
                } else {
                    set.remove(sceneRule.getId());
                }
                this.checkboxSelect.setChecked(z7);
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static /* synthetic */ boolean lambda$bind$3(boolean z6, OnDeleteListener onDeleteListener, SceneRule sceneRule, View view) {
            if (z6 || onDeleteListener == null) {
                return true;
            }
            onDeleteListener.onDelete(sceneRule);
            return true;
        }

        public void bind(final SceneRule sceneRule, final boolean z6, final Set<String> set, final OnToggleListener onToggleListener, final OnEditListener onEditListener, final OnDeleteListener onDeleteListener) {
            this.textName.setText(sceneRule.getName());
            StringBuilder sb = new StringBuilder("条件");
            sb.append(sceneRule.getConditions().size());
            sb.append("项(");
            sb.append(sceneRule.getLogic());
            sb.append(") · 动作");
            sb.append(sceneRule.getActions().size());
            sb.append("项 · ");
            sb.append(SceneRule.formatDelay(sceneRule.getDelaySeconds()));
            if (sceneRule.isResetEnabled()) {
                sb.append(" · 已启用重置");
            }
            this.textMeta.setText(sb.toString());
            final int i7 = 0;
            if (z6) {
                this.switchEnabled.setVisibility(8);
                this.checkboxSelect.setVisibility(0);
                this.checkboxSelect.setOnCheckedChangeListener(null);
                this.checkboxSelect.setChecked(set.contains(sceneRule.getId()));
                this.checkboxSelect.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() { // from class: com.navi.tool.ui.scene.i
                    @Override // android.widget.CompoundButton.OnCheckedChangeListener
                    public final void onCheckedChanged(CompoundButton compoundButton, boolean z7) {
                        int i8 = i7;
                        SceneRule sceneRule2 = sceneRule;
                        Object obj = set;
                        switch (i8) {
                            case 0:
                                SceneRuleAdapter.SceneRuleViewHolder.lambda$bind$0((Set) obj, sceneRule2, compoundButton, z7);
                                break;
                            default:
                                SceneRuleAdapter.SceneRuleViewHolder.lambda$bind$1((SceneRuleAdapter.OnToggleListener) obj, sceneRule2, compoundButton, z7);
                                break;
                        }
                    }
                });
            } else {
                this.checkboxSelect.setOnCheckedChangeListener(null);
                this.checkboxSelect.setVisibility(8);
                this.switchEnabled.setVisibility(0);
                this.switchEnabled.setChecked(sceneRule.isEnabled());
                final int i8 = 1;
                this.switchEnabled.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() { // from class: com.navi.tool.ui.scene.i
                    @Override // android.widget.CompoundButton.OnCheckedChangeListener
                    public final void onCheckedChanged(CompoundButton compoundButton, boolean z7) {
                        int i82 = i8;
                        SceneRule sceneRule2 = sceneRule;
                        Object obj = onToggleListener;
                        switch (i82) {
                            case 0:
                                SceneRuleAdapter.SceneRuleViewHolder.lambda$bind$0((Set) obj, sceneRule2, compoundButton, z7);
                                break;
                            default:
                                SceneRuleAdapter.SceneRuleViewHolder.lambda$bind$1((SceneRuleAdapter.OnToggleListener) obj, sceneRule2, compoundButton, z7);
                                break;
                        }
                    }
                });
            }
            this.itemView.setOnClickListener(new View.OnClickListener() { // from class: com.navi.tool.ui.scene.j
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    SceneRuleAdapter.SceneRuleViewHolder.this.lambda$bind$2(z6, set, sceneRule, onEditListener, view);
                }
            });
            this.itemView.setOnLongClickListener(new View.OnLongClickListener() { // from class: com.navi.tool.ui.scene.k
                @Override // android.view.View.OnLongClickListener
                public final boolean onLongClick(View view) {
                    boolean lambda$bind$3;
                    lambda$bind$3 = SceneRuleAdapter.SceneRuleViewHolder.lambda$bind$3(z6, onDeleteListener, sceneRule, view);
                    return lambda$bind$3;
                }
            });
        }
    }

    public SceneRuleAdapter(OnToggleListener onToggleListener, OnEditListener onEditListener, OnDeleteListener onDeleteListener) {
        super(new Diff());
        this.selectionMode = false;
        this.selectedIds = new HashSet();
        this.toggleListener = onToggleListener;
        this.editListener = onEditListener;
        this.deleteListener = onDeleteListener;
    }

    public List<String> getSelectedIds() {
        return new ArrayList(this.selectedIds);
    }

    public boolean isAllSelected() {
        return !getCurrentList().isEmpty() && this.selectedIds.size() == getCurrentList().size();
    }

    public boolean isSelectionMode() {
        return this.selectionMode;
    }

    @Override // androidx.recyclerview.widget.h1
    public void onBindViewHolder(SceneRuleViewHolder sceneRuleViewHolder, int i7) {
        sceneRuleViewHolder.bind((SceneRule) getItem(i7), this.selectionMode, this.selectedIds, this.toggleListener, this.editListener, this.deleteListener);
    }

    @Override // androidx.recyclerview.widget.h1
    public SceneRuleViewHolder onCreateViewHolder(ViewGroup viewGroup, int i7) {
        return new SceneRuleViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(q.item_scene_rule, viewGroup, false));
    }

    public void setSelectionMode(boolean z6) {
        this.selectionMode = z6;
        if (!z6) {
            this.selectedIds.clear();
        }
        notifyDataSetChanged();
    }

    public void toggleSelectAll(boolean z6) {
        this.selectedIds.clear();
        if (z6) {
            for (int i7 = 0; i7 < getCurrentList().size(); i7++) {
                this.selectedIds.add(((SceneRule) getCurrentList().get(i7)).getId());
            }
        }
        notifyDataSetChanged();
    }
}
