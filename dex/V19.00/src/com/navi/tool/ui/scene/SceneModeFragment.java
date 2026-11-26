package com.navi.tool.ui.scene;

import android.R;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.TextView;
import androidx.appcompat.app.n;
import androidx.appcompat.app.o;
import androidx.fragment.app.z;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.material.switchmaterial.SwitchMaterial;
import com.navi.tool.model.SceneRule;
import com.navi.tool.service.SceneCatalog;
import com.navi.tool.service.SceneModeManager;
import com.navi.tool.utils.DialogImeNavBarHelper;
import com.navi.tool.utils.NativeBridge;
import com.navi.tool.utils.SceneRuleStorage;
import com.navi.tool.utils.SharedPreferencesUtils;
import com.navi.tool.utils.ToastHelper;
import g4.p;
import g4.q;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.function.Predicate;
import s5.h0;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public class SceneModeFragment extends z {
    private static final String KEY_SCENE_MODE_MASTER = "scene_mode_master_enabled";
    private SceneRuleAdapter adapter;
    private Button buttonAdd;
    private Button buttonDeleteSelected;
    private Button buttonMultiSelect;
    private Button buttonReload;
    private Button buttonSelectAll;
    private View layoutSceneContent;
    private SharedPreferences prefs;
    private RecyclerView recyclerView;
    private SwitchMaterial switchMaster;
    private TextView textEmpty;
    private final List<SceneRule> rules = new ArrayList();
    private boolean updatingMasterSwitch = false;

    public static class ActionRowHolder {
        boolean binding;
        EditText editValue;
        String pendingValue;
        LinearLayout root;
        SeekBar seekBar;
        TextView seekLabel;
        Spinner spinnerAction;
        Spinner spinnerValue;

        private ActionRowHolder() {
        }
    }

    public static class ConditionRowHolder {
        boolean binding;
        EditText editValue;
        String pendingEnumValue;
        Integer pendingOpSelection;
        LinearLayout root;
        Spinner spinnerField;
        Spinner spinnerOp;
        Spinner spinnerValue;
        TextView textHint;

        private ConditionRowHolder() {
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    private void addActionRow(LinearLayout linearLayout, List<ActionRowHolder> list, SceneRule.Action action) {
        final ActionRowHolder actionRowHolder = new ActionRowHolder();
        LinearLayout linearLayout2 = new LinearLayout(getContext());
        actionRowHolder.root = linearLayout2;
        linearLayout2.setOrientation(0);
        actionRowHolder.root.setGravity(16);
        actionRowHolder.root.setPadding(0, 8, 0, 8);
        actionRowHolder.spinnerAction = new Spinner(getContext());
        actionRowHolder.spinnerValue = new Spinner(getContext());
        actionRowHolder.editValue = new EditText(getContext());
        actionRowHolder.seekBar = new SeekBar(getContext());
        actionRowHolder.seekLabel = new TextView(getContext());
        ImageButton imageButton = new ImageButton(getContext());
        imageButton.setImageResource(R.drawable.ic_delete);
        imageButton.setBackground(null);
        actionRowHolder.spinnerAction.setLayoutParams(new LinearLayout.LayoutParams(0, -2, 1.0f));
        actionRowHolder.spinnerValue.setLayoutParams(new LinearLayout.LayoutParams(0, -2, 1.0f));
        actionRowHolder.editValue.setLayoutParams(new LinearLayout.LayoutParams(0, -2, 1.0f));
        actionRowHolder.editValue.setHint("动作参数");
        actionRowHolder.spinnerValue.setVisibility(8);
        actionRowHolder.editValue.setVisibility(0);
        actionRowHolder.seekBar.setMax(9);
        actionRowHolder.seekBar.setProgress(1);
        actionRowHolder.seekBar.setVisibility(8);
        actionRowHolder.seekLabel.setVisibility(8);
        actionRowHolder.seekLabel.setText("延时: 2秒");
        actionRowHolder.seekLabel.setTextSize(13.0f);
        actionRowHolder.seekLabel.setPadding(dp(4), 0, dp(4), 0);
        actionRowHolder.seekBar.setLayoutParams(new LinearLayout.LayoutParams(0, -2, 1.0f));
        actionRowHolder.root.addView(actionRowHolder.spinnerAction);
        actionRowHolder.root.addView(actionRowHolder.spinnerValue);
        actionRowHolder.root.addView(actionRowHolder.editValue);
        actionRowHolder.root.addView(actionRowHolder.seekLabel);
        actionRowHolder.root.addView(actionRowHolder.seekBar);
        actionRowHolder.root.addView(imageButton);
        linearLayout.addView(actionRowHolder.root);
        list.add(actionRowHolder);
        ArrayAdapter arrayAdapter = new ArrayAdapter(requireContext(), R.layout.simple_spinner_item, SceneCatalog.actionLabels());
        arrayAdapter.setDropDownViewResource(R.layout.simple_spinner_dropdown_item);
        actionRowHolder.spinnerAction.setAdapter((SpinnerAdapter) arrayAdapter);
        actionRowHolder.spinnerAction.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() { // from class: com.navi.tool.ui.scene.SceneModeFragment.3
            @Override // android.widget.AdapterView.OnItemSelectedListener
            public void onItemSelected(AdapterView<?> adapterView, View view, int i7, long j7) {
                SceneModeFragment.this.updateActionRowUi(actionRowHolder, SceneCatalog.findAction(SceneCatalog.actionKeyByLabel(actionRowHolder.spinnerAction.getSelectedItem() == null ? h0.FRAGMENT_ENCODE_SET : actionRowHolder.spinnerAction.getSelectedItem().toString())));
            }

            @Override // android.widget.AdapterView.OnItemSelectedListener
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });
        imageButton.setOnClickListener(new d(linearLayout, actionRowHolder, list, 1));
        actionRowHolder.seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() { // from class: com.navi.tool.ui.scene.SceneModeFragment.4
            @Override // android.widget.SeekBar.OnSeekBarChangeListener
            public void onProgressChanged(SeekBar seekBar, int i7, boolean z6) {
                a.a.v("延时: ", i7 + 1, "秒", actionRowHolder.seekLabel);
            }

            @Override // android.widget.SeekBar.OnSeekBarChangeListener
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override // android.widget.SeekBar.OnSeekBarChangeListener
            public void onStopTrackingTouch(SeekBar seekBar) {
            }
        });
        if (action == null) {
            actionRowHolder.spinnerAction.setSelection(0);
            Object selectedItem = actionRowHolder.spinnerAction.getSelectedItem();
            updateActionRowUi(actionRowHolder, selectedItem != null ? SceneCatalog.findAction(SceneCatalog.actionKeyByLabel(selectedItem.toString())) : null);
            return;
        }
        actionRowHolder.binding = true;
        actionRowHolder.pendingValue = action.getValue();
        int i7 = 0;
        while (true) {
            List<SceneCatalog.ActionDef> list2 = SceneCatalog.ACTIONS;
            if (i7 >= list2.size()) {
                break;
            }
            if (list2.get(i7).key.equals(action.getType())) {
                actionRowHolder.spinnerAction.setSelection(i7);
                break;
            }
            i7++;
        }
        SceneCatalog.ActionDef findAction = SceneCatalog.findAction(action.getType());
        if ((findAction == null || !findAction.hasValueOptions()) && (findAction == null || (!"close_ac_ui".equals(findAction.key) && !"open_ac_ui".equals(findAction.key) && !"close_systemui_ac_ui".equals(findAction.key)))) {
            actionRowHolder.editValue.setText(action.getValue());
            actionRowHolder.pendingValue = null;
        }
        actionRowHolder.binding = false;
    }

    /* JADX WARN: Multi-variable type inference failed */
    private void addConditionRow(LinearLayout linearLayout, List<ConditionRowHolder> list, SceneRule.Condition condition) {
        final ConditionRowHolder conditionRowHolder = new ConditionRowHolder();
        LinearLayout linearLayout2 = new LinearLayout(getContext());
        conditionRowHolder.root = linearLayout2;
        linearLayout2.setOrientation(1);
        conditionRowHolder.root.setPadding(0, 8, 0, 8);
        LinearLayout linearLayout3 = new LinearLayout(getContext());
        linearLayout3.setOrientation(0);
        linearLayout3.setGravity(16);
        conditionRowHolder.spinnerField = new Spinner(getContext());
        conditionRowHolder.spinnerOp = new Spinner(getContext());
        conditionRowHolder.spinnerValue = new Spinner(getContext());
        conditionRowHolder.editValue = new EditText(getContext());
        conditionRowHolder.textHint = new TextView(getContext());
        ImageButton imageButton = new ImageButton(getContext());
        imageButton.setImageResource(R.drawable.ic_delete);
        imageButton.setBackground(null);
        conditionRowHolder.spinnerField.setLayoutParams(new LinearLayout.LayoutParams(0, -2, 1.0f));
        conditionRowHolder.spinnerOp.setLayoutParams(new LinearLayout.LayoutParams(dp(122), -2));
        conditionRowHolder.spinnerValue.setLayoutParams(new LinearLayout.LayoutParams(0, -2, 1.0f));
        conditionRowHolder.editValue.setLayoutParams(new LinearLayout.LayoutParams(0, -2, 1.0f));
        conditionRowHolder.editValue.setHint("输入值");
        conditionRowHolder.editValue.setInputType(1);
        conditionRowHolder.spinnerValue.setVisibility(8);
        conditionRowHolder.editValue.setVisibility(0);
        conditionRowHolder.textHint.setText("请输入数值或文本");
        linearLayout3.addView(conditionRowHolder.spinnerField);
        linearLayout3.addView(conditionRowHolder.spinnerOp);
        linearLayout3.addView(conditionRowHolder.spinnerValue);
        linearLayout3.addView(conditionRowHolder.editValue);
        linearLayout3.addView(imageButton);
        conditionRowHolder.root.addView(linearLayout3);
        conditionRowHolder.root.addView(conditionRowHolder.textHint);
        linearLayout.addView(conditionRowHolder.root);
        list.add(conditionRowHolder);
        ArrayAdapter arrayAdapter = new ArrayAdapter(requireContext(), R.layout.simple_spinner_item, SceneCatalog.fieldLabels());
        arrayAdapter.setDropDownViewResource(R.layout.simple_spinner_dropdown_item);
        conditionRowHolder.spinnerField.setAdapter((SpinnerAdapter) arrayAdapter);
        bindOperatorOptions(conditionRowHolder.spinnerOp, false);
        conditionRowHolder.spinnerOp.setDropDownWidth(dp(z1.c.OR_INT));
        conditionRowHolder.spinnerOp.setSelection(0, false);
        conditionRowHolder.spinnerField.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() { // from class: com.navi.tool.ui.scene.SceneModeFragment.2
            @Override // android.widget.AdapterView.OnItemSelectedListener
            public void onItemSelected(AdapterView<?> adapterView, View view, int i7, long j7) {
                SceneModeFragment.this.updateConditionRowUi(conditionRowHolder, SceneCatalog.findField(SceneCatalog.fieldKeyByLabel(conditionRowHolder.spinnerField.getSelectedItem() == null ? h0.FRAGMENT_ENCODE_SET : conditionRowHolder.spinnerField.getSelectedItem().toString())));
            }

            @Override // android.widget.AdapterView.OnItemSelectedListener
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });
        imageButton.setOnClickListener(new d(linearLayout, conditionRowHolder, list, 0));
        if (condition == null) {
            conditionRowHolder.spinnerField.setSelection(0);
            conditionRowHolder.spinnerOp.setSelection(0, false);
            Object selectedItem = conditionRowHolder.spinnerField.getSelectedItem();
            updateConditionRowUi(conditionRowHolder, selectedItem != null ? SceneCatalog.findField(SceneCatalog.fieldKeyByLabel(selectedItem.toString())) : null);
            return;
        }
        conditionRowHolder.binding = true;
        conditionRowHolder.pendingEnumValue = condition.getValue();
        conditionRowHolder.pendingOpSelection = Integer.valueOf(operatorSelection(condition.getOperator()));
        int i7 = 0;
        while (true) {
            List<SceneCatalog.FieldDef> list2 = SceneCatalog.FIELDS;
            if (i7 >= list2.size()) {
                break;
            }
            if (list2.get(i7).key.equals(condition.getField())) {
                conditionRowHolder.spinnerField.setSelection(i7);
                break;
            }
            i7++;
        }
        SceneCatalog.FieldDef findField = SceneCatalog.findField(condition.getField());
        if (findField == null || !findField.isEnum()) {
            conditionRowHolder.editValue.setText(condition.getValue());
            conditionRowHolder.pendingEnumValue = null;
        }
        conditionRowHolder.binding = false;
        if (conditionRowHolder.pendingOpSelection != null) {
            conditionRowHolder.spinnerOp.setSelection((findField == null || !findField.isEnum()) ? conditionRowHolder.pendingOpSelection.intValue() : Math.min(conditionRowHolder.pendingOpSelection.intValue(), 1), false);
            conditionRowHolder.pendingOpSelection = null;
        }
    }

    private void bindOperatorOptions(Spinner spinner, boolean z6) {
        if (spinner == null) {
            return;
        }
        ArrayAdapter arrayAdapter = new ArrayAdapter(requireContext(), R.layout.simple_spinner_item, z6 ? new String[]{"等于", "不等于"} : new String[]{"等于", "不等于", "大于", "小于"});
        arrayAdapter.setDropDownViewResource(R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter((SpinnerAdapter) arrayAdapter);
    }

    private List<SceneRule.Action> collectActions(List<ActionRowHolder> list) {
        ArrayList arrayList = new ArrayList();
        for (ActionRowHolder actionRowHolder : list) {
            Object selectedItem = actionRowHolder.spinnerAction.getSelectedItem();
            String str = h0.FRAGMENT_ENCODE_SET;
            String actionKeyByLabel = SceneCatalog.actionKeyByLabel(selectedItem == null ? h0.FRAGMENT_ENCODE_SET : actionRowHolder.spinnerAction.getSelectedItem().toString());
            if ("close_ac_ui".equals(actionKeyByLabel) || "open_ac_ui".equals(actionKeyByLabel) || "close_systemui_ac_ui".equals(actionKeyByLabel)) {
                str = String.valueOf(actionRowHolder.seekBar.getProgress() + 1);
            } else if (actionRowHolder.spinnerValue.getVisibility() != 0) {
                str = a.a.c(actionRowHolder.editValue);
            } else if (actionRowHolder.spinnerValue.getSelectedItem() != null) {
                str = actionRowHolder.spinnerValue.getSelectedItem().toString();
            }
            if (!actionKeyByLabel.isEmpty() && !str.isEmpty()) {
                arrayList.add(new SceneRule.Action(actionKeyByLabel, str));
            }
        }
        return arrayList;
    }

    private List<SceneRule.Condition> collectConditions(List<ConditionRowHolder> list) {
        ArrayList arrayList = new ArrayList();
        for (ConditionRowHolder conditionRowHolder : list) {
            Object selectedItem = conditionRowHolder.spinnerField.getSelectedItem();
            String str = h0.FRAGMENT_ENCODE_SET;
            String fieldKeyByLabel = SceneCatalog.fieldKeyByLabel(selectedItem == null ? h0.FRAGMENT_ENCODE_SET : conditionRowHolder.spinnerField.getSelectedItem().toString());
            String operatorSymbol = toOperatorSymbol(conditionRowHolder.spinnerOp.getSelectedItem() == null ? "等于" : conditionRowHolder.spinnerOp.getSelectedItem().toString());
            if (conditionRowHolder.spinnerValue.getVisibility() != 0) {
                str = a.a.c(conditionRowHolder.editValue);
            } else if (conditionRowHolder.spinnerValue.getSelectedItem() != null) {
                str = conditionRowHolder.spinnerValue.getSelectedItem().toString();
            }
            if (!fieldKeyByLabel.isEmpty() && !str.isEmpty()) {
                arrayList.add(new SceneRule.Condition(fieldKeyByLabel, operatorSymbol, str));
            }
        }
        return arrayList;
    }

    private int currentOperatorSelection(Spinner spinner) {
        if (spinner != null && spinner.getSelectedItem() != null) {
            String obj = spinner.getSelectedItem().toString();
            if ("不等于".equals(obj)) {
                return 1;
            }
            if ("大于".equals(obj)) {
                return 2;
            }
            if ("小于".equals(obj)) {
                return 3;
            }
            if ("等于".equals(obj)) {
                return 0;
            }
        }
        return -1;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void deleteRule(SceneRule sceneRule) {
        if (this.adapter.isSelectionMode()) {
            return;
        }
        n nVar = new n(requireContext());
        nVar.t("删除配置");
        nVar.m("确定删除 \"" + sceneRule.getName() + "\" ?");
        nVar.o("取消", new g4.g(17));
        nVar.r("删除", new h(this, sceneRule, 1));
        nVar.v();
    }

    private void deleteSelectedRules() {
        List<String> selectedIds = this.adapter.getSelectedIds();
        if (selectedIds.isEmpty()) {
            ToastHelper.show(requireContext(), "请先选择要删除的配置");
            return;
        }
        n nVar = new n(requireContext());
        nVar.t("删除配置文件");
        nVar.m("确定删除选中的 " + selectedIds.size() + " 个配置文件？");
        nVar.o("取消", new g4.g(16));
        nVar.r("删除", new h(this, selectedIds, 0));
        nVar.v();
    }

    private int dp(int i7) {
        return (int) ((i7 * requireContext().getResources().getDisplayMetrics().density) + 0.5f);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void editRule(SceneRule sceneRule) {
        if (this.adapter.isSelectionMode()) {
            return;
        }
        showRuleEditorDialog(sceneRule);
    }

    private int findOptionIndex(String[] strArr, String str) {
        if (strArr != null && strArr.length != 0) {
            String normalizeOption = normalizeOption(str);
            for (int i7 = 0; i7 < strArr.length; i7++) {
                if (normalizeOption(strArr[i7]).equals(normalizeOption)) {
                    return i7;
                }
            }
        }
        return 0;
    }

    private boolean isDuplicateName(String str, SceneRule sceneRule) {
        String trim = str == null ? h0.FRAGMENT_ENCODE_SET : str.trim();
        for (SceneRule sceneRule2 : this.rules) {
            if (sceneRule == null || !sceneRule2.getId().equals(sceneRule.getId())) {
                if ((sceneRule2.getName() == null ? h0.FRAGMENT_ENCODE_SET : sceneRule2.getName().trim()).equalsIgnoreCase(trim)) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean isLionModeEnabled() {
        return this.prefs.getBoolean("lion_sync_service_enabled", false);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ void lambda$addActionRow$18(LinearLayout linearLayout, ActionRowHolder actionRowHolder, List list, View view) {
        linearLayout.removeView(actionRowHolder.root);
        list.remove(actionRowHolder);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ void lambda$addConditionRow$17(LinearLayout linearLayout, ConditionRowHolder conditionRowHolder, List list, View view) {
        linearLayout.removeView(conditionRowHolder.root);
        list.remove(conditionRowHolder);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ void lambda$deleteRule$6(DialogInterface dialogInterface, int i7) {
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$deleteRule$7(SceneRule sceneRule, DialogInterface dialogInterface, int i7) {
        int i8 = 0;
        while (true) {
            if (i8 >= this.rules.size()) {
                i8 = -1;
                break;
            } else if (this.rules.get(i8).getId().equals(sceneRule.getId())) {
                break;
            } else {
                i8++;
            }
        }
        if (i8 < 0 || !persistDeleteRuleByName(this.rules.get(i8).getName())) {
            return;
        }
        this.rules.remove(i8);
        onPersistSuccess();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$deleteSelectedRules$10(final List list, DialogInterface dialogInterface, int i7) {
        ArrayList arrayList = new ArrayList();
        for (SceneRule sceneRule : this.rules) {
            if (list.contains(sceneRule.getId())) {
                arrayList.add(sceneRule);
            }
        }
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            if (!SceneRuleStorage.deleteRuleFile(requireContext(), ((SceneRule) it.next()).getName())) {
                ToastHelper.show(requireContext(), "删除失败，请重试");
                loadRules();
                return;
            }
        }
        this.rules.removeIf(new Predicate() { // from class: com.navi.tool.ui.scene.c
            @Override // java.util.function.Predicate
            public final boolean test(Object obj) {
                boolean lambda$deleteSelectedRules$9;
                lambda$deleteSelectedRules$9 = SceneModeFragment.lambda$deleteSelectedRules$9(list, (SceneRule) obj);
                return lambda$deleteSelectedRules$9;
            }
        });
        this.adapter.setSelectionMode(false);
        onPersistSuccess();
        ToastHelper.show(requireContext(), "已删除 " + list.size() + " 个配置");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ void lambda$deleteSelectedRules$8(DialogInterface dialogInterface, int i7) {
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ boolean lambda$deleteSelectedRules$9(List list, SceneRule sceneRule) {
        return list.contains(sceneRule.getId());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$onCreateView$0(CompoundButton compoundButton, boolean z6) {
        if (this.updatingMasterSwitch) {
            return;
        }
        if (z6 && isLionModeEnabled()) {
            ToastHelper.show(requireContext(), "当前车联模式不支持此功能");
            setMasterCheckedSilently(false);
            return;
        }
        this.prefs.edit().putBoolean(KEY_SCENE_MODE_MASTER, z6).apply();
        SceneModeManager.notifyRulesChanged();
        this.adapter.setSelectionMode(false);
        ToastHelper.show(requireContext(), z6 ? "情景模式已开启" : "情景模式已关闭");
        updateSceneContentVisibility();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$onCreateView$1(View view) {
        showRuleEditorDialog(null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$onCreateView$2(View view) {
        loadRules();
        SceneModeManager.notifyRulesChanged();
        ToastHelper.show(requireContext(), "情景模式配置已重新加载");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$onCreateView$3(View view) {
        toggleSelectionMode();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$onCreateView$4(View view) {
        toggleSelectAll();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$onCreateView$5(View view) {
        deleteSelectedRules();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ void lambda$showRuleEditorDialog$11(RadioGroup radioGroup, CompoundButton compoundButton, boolean z6) {
        radioGroup.setVisibility(z6 ? 0 : 8);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$showRuleEditorDialog$12(LinearLayout linearLayout, List list, View view) {
        addConditionRow(linearLayout, list, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$showRuleEditorDialog$13(LinearLayout linearLayout, List list, View view) {
        addActionRow(linearLayout, list, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ void lambda$showRuleEditorDialog$14(DialogInterface dialogInterface, int i7) {
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$showRuleEditorDialog$15(EditText editText, SceneRule sceneRule, List list, List list2, RadioGroup radioGroup, SeekBar seekBar, SeekBar seekBar2, CheckBox checkBox, RadioGroup radioGroup2, o oVar, View view) {
        String c7 = a.a.c(editText);
        if (c7.isEmpty()) {
            ToastHelper.show(requireContext(), "请填写配置文件名称");
            return;
        }
        if (!SceneRuleStorage.isValidRuleName(c7)) {
            ToastHelper.show(requireContext(), "名称包含非法字符或格式无效");
            return;
        }
        if (isDuplicateName(c7, sceneRule)) {
            ToastHelper.show(requireContext(), "已存在同名配置文件，请更换名称");
            return;
        }
        List<SceneRule.Condition> collectConditions = collectConditions(list);
        if (collectConditions.isEmpty()) {
            ToastHelper.show(requireContext(), "请至少添加一个条件");
            return;
        }
        List<SceneRule.Action> collectActions = collectActions(list2);
        if (collectActions.isEmpty()) {
            ToastHelper.show(requireContext(), "请至少添加一个动作");
            return;
        }
        if (validateSeatMoveActions(collectActions)) {
            SceneRule sceneRule2 = new SceneRule();
            if (sceneRule != null) {
                sceneRule2.setId(sceneRule.getId());
                sceneRule2.setEnabled(sceneRule.isEnabled());
            } else {
                sceneRule2.setEnabled(true);
            }
            sceneRule2.setName(c7);
            sceneRule2.setLogic(radioGroup.getCheckedRadioButtonId() == p.radio_scene_logic_or ? "OR" : "AND");
            int progress = seekBar.getProgress();
            int progress2 = seekBar2.getProgress();
            int i7 = 0;
            if (progress >= 30) {
                progress2 = 0;
                progress = 30;
            }
            sceneRule2.setDelaySeconds((progress * 60) + progress2);
            sceneRule2.setConditions(collectConditions);
            sceneRule2.setActions(collectActions);
            sceneRule2.setResetEnabled(checkBox.isChecked());
            int checkedRadioButtonId = radioGroup2.getCheckedRadioButtonId();
            sceneRule2.setResetType(checkedRadioButtonId == p.radio_scene_reset_power ? "POWER_ON_TO_OFF" : checkedRadioButtonId == p.radio_scene_reset_reboot ? "DEVICE_REBOOT" : "ENGINE_RUN_TO_OFF");
            if (sceneRule == null) {
                if (!persistSingleRule(sceneRule2, null)) {
                    return;
                }
                this.rules.add(sceneRule2);
                onPersistSuccess();
            } else {
                if (!persistSingleRule(sceneRule2, sceneRule.getName())) {
                    return;
                }
                while (true) {
                    if (i7 >= this.rules.size()) {
                        break;
                    }
                    if (this.rules.get(i7).getId().equals(sceneRule.getId())) {
                        this.rules.set(i7, sceneRule2);
                        break;
                    }
                    i7++;
                }
                onPersistSuccess();
            }
            oVar.dismiss();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$showRuleEditorDialog$16(final o oVar, final EditText editText, final SceneRule sceneRule, final List list, final List list2, final RadioGroup radioGroup, final SeekBar seekBar, final SeekBar seekBar2, final CheckBox checkBox, final RadioGroup radioGroup2, DialogInterface dialogInterface) {
        oVar.e(-1).setOnClickListener(new View.OnClickListener() { // from class: com.navi.tool.ui.scene.e
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                this.lambda$showRuleEditorDialog$15(editText, sceneRule, list, list2, radioGroup, seekBar, seekBar2, checkBox, radioGroup2, oVar, view);
            }
        });
    }

    private void loadRules() {
        this.rules.clear();
        this.rules.addAll(SceneRuleStorage.loadRules(requireContext()));
        this.adapter.submitList(new ArrayList(this.rules));
        this.textEmpty.setVisibility(this.rules.isEmpty() ? 0 : 8);
        updateMultiSelectButtons();
    }

    private String normalizeOption(String str) {
        return str == null ? h0.FRAGMENT_ENCODE_SET : str.trim().replaceAll("\\s+", h0.FRAGMENT_ENCODE_SET).toLowerCase();
    }

    private void onPersistSuccess() {
        this.adapter.submitList(new ArrayList(this.rules));
        this.textEmpty.setVisibility(this.rules.isEmpty() ? 0 : 8);
        SceneModeManager.notifyRulesChangedIncremental();
        updateMultiSelectButtons();
    }

    private int operatorSelection(String str) {
        if ("!=".equals(str)) {
            return 1;
        }
        if (">".equals(str)) {
            return 2;
        }
        return "<".equals(str) ? 3 : 0;
    }

    private boolean persistDeleteRuleByName(String str) {
        if (SceneRuleStorage.deleteRuleFile(requireContext(), str)) {
            return true;
        }
        ToastHelper.show(requireContext(), "删除失败，请重试");
        loadRules();
        return false;
    }

    private boolean persistSingleRule(SceneRule sceneRule, String str) {
        if (SceneRuleStorage.saveRule(requireContext(), sceneRule, str)) {
            return true;
        }
        ToastHelper.show(requireContext(), "保存失败，请检查配置名称是否重复或包含非法字符");
        loadRules();
        return false;
    }

    private void setMasterCheckedSilently(boolean z6) {
        this.updatingMasterSwitch = true;
        this.switchMaster.setChecked(z6);
        this.updatingMasterSwitch = false;
    }

    private void showRuleEditorDialog(final SceneRule sceneRule) {
        View inflate = LayoutInflater.from(getContext()).inflate(q.dialog_scene_rule, (ViewGroup) null, false);
        inflate.setLayerType(1, null);
        final EditText editText = (EditText) inflate.findViewById(p.edit_scene_rule_name);
        final RadioGroup radioGroup = (RadioGroup) inflate.findViewById(p.radio_scene_logic_group);
        final SeekBar seekBar = (SeekBar) inflate.findViewById(p.seek_scene_delay);
        final SeekBar seekBar2 = (SeekBar) inflate.findViewById(p.seek_scene_delay_seconds);
        final TextView textView = (TextView) inflate.findViewById(p.text_scene_delay_value);
        final LinearLayout linearLayout = (LinearLayout) inflate.findViewById(p.layout_scene_conditions);
        final LinearLayout linearLayout2 = (LinearLayout) inflate.findViewById(p.layout_scene_actions);
        Button button = (Button) inflate.findViewById(p.button_add_scene_condition);
        Button button2 = (Button) inflate.findViewById(p.button_add_scene_action);
        final CheckBox checkBox = (CheckBox) inflate.findViewById(p.checkbox_scene_reset_enabled);
        final RadioGroup radioGroup2 = (RadioGroup) inflate.findViewById(p.radio_scene_reset_group);
        final ArrayList arrayList = new ArrayList();
        final ArrayList arrayList2 = new ArrayList();
        SeekBar.OnSeekBarChangeListener onSeekBarChangeListener = new SeekBar.OnSeekBarChangeListener() { // from class: com.navi.tool.ui.scene.SceneModeFragment.1
            @Override // android.widget.SeekBar.OnSeekBarChangeListener
            public void onProgressChanged(SeekBar seekBar3, int i7, boolean z6) {
                SceneModeFragment.this.updateDelayUi(seekBar, seekBar2, textView);
            }

            @Override // android.widget.SeekBar.OnSeekBarChangeListener
            public void onStartTrackingTouch(SeekBar seekBar3) {
            }

            @Override // android.widget.SeekBar.OnSeekBarChangeListener
            public void onStopTrackingTouch(SeekBar seekBar3) {
            }
        };
        seekBar.setOnSeekBarChangeListener(onSeekBarChangeListener);
        seekBar2.setOnSeekBarChangeListener(onSeekBarChangeListener);
        checkBox.setOnCheckedChangeListener(new z2.a(1, radioGroup2));
        final int i7 = 0;
        button.setOnClickListener(new View.OnClickListener(this) { // from class: com.navi.tool.ui.scene.a

            /* renamed from: b, reason: collision with root package name */
            public final /* synthetic */ SceneModeFragment f5770b;

            {
                this.f5770b = this;
            }

            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                int i8 = i7;
                SceneModeFragment sceneModeFragment = this.f5770b;
                List list = arrayList;
                LinearLayout linearLayout3 = linearLayout;
                switch (i8) {
                    case 0:
                        sceneModeFragment.lambda$showRuleEditorDialog$12(linearLayout3, list, view);
                        break;
                    default:
                        sceneModeFragment.lambda$showRuleEditorDialog$13(linearLayout3, list, view);
                        break;
                }
            }
        });
        final int i8 = 1;
        button2.setOnClickListener(new View.OnClickListener(this) { // from class: com.navi.tool.ui.scene.a

            /* renamed from: b, reason: collision with root package name */
            public final /* synthetic */ SceneModeFragment f5770b;

            {
                this.f5770b = this;
            }

            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                int i82 = i8;
                SceneModeFragment sceneModeFragment = this.f5770b;
                List list = arrayList2;
                LinearLayout linearLayout3 = linearLayout2;
                switch (i82) {
                    case 0:
                        sceneModeFragment.lambda$showRuleEditorDialog$12(linearLayout3, list, view);
                        break;
                    default:
                        sceneModeFragment.lambda$showRuleEditorDialog$13(linearLayout3, list, view);
                        break;
                }
            }
        });
        if (sceneRule != null) {
            editText.setText(sceneRule.getName());
            if ("OR".equalsIgnoreCase(sceneRule.getLogic())) {
                radioGroup.check(p.radio_scene_logic_or);
            } else {
                radioGroup.check(p.radio_scene_logic_and);
            }
            int delaySeconds = sceneRule.getDelaySeconds();
            seekBar.setProgress(Math.min(30, delaySeconds / 60));
            seekBar2.setProgress(delaySeconds % 60);
            textView.setText(SceneRule.formatDelay(delaySeconds));
            updateDelayUi(seekBar, seekBar2, textView);
            checkBox.setChecked(sceneRule.isResetEnabled());
            radioGroup2.setVisibility(sceneRule.isResetEnabled() ? 0 : 8);
            String resetType = sceneRule.getResetType();
            if ("POWER_ON_TO_OFF".equals(resetType)) {
                radioGroup2.check(p.radio_scene_reset_power);
            } else if ("DEVICE_REBOOT".equals(resetType)) {
                radioGroup2.check(p.radio_scene_reset_reboot);
            } else {
                radioGroup2.check(p.radio_scene_reset_engine);
            }
            Iterator<SceneRule.Condition> it = sceneRule.getConditions().iterator();
            while (it.hasNext()) {
                addConditionRow(linearLayout, arrayList, it.next());
            }
            Iterator<SceneRule.Action> it2 = sceneRule.getActions().iterator();
            while (it2.hasNext()) {
                addActionRow(linearLayout2, arrayList2, it2.next());
            }
        } else {
            radioGroup.check(p.radio_scene_logic_and);
            seekBar.setProgress(0);
            seekBar2.setProgress(0);
            updateDelayUi(seekBar, seekBar2, textView);
            checkBox.setChecked(false);
            radioGroup2.setVisibility(8);
            addConditionRow(linearLayout, arrayList, null);
            addActionRow(linearLayout2, arrayList2, null);
        }
        n nVar = new n(requireContext());
        nVar.t(sceneRule == null ? "新建配置文件" : "编辑配置文件");
        nVar.u(inflate);
        nVar.o("取消", new g4.g(15));
        nVar.r("保存", null);
        final o e7 = nVar.e();
        e7.setOnShowListener(new DialogInterface.OnShowListener() { // from class: com.navi.tool.ui.scene.b
            @Override // android.content.DialogInterface.OnShowListener
            public final void onShow(DialogInterface dialogInterface) {
                this.lambda$showRuleEditorDialog$16(e7, editText, sceneRule, arrayList, arrayList2, radioGroup, seekBar, seekBar2, checkBox, radioGroup2, dialogInterface);
            }
        });
        if (e7.getWindow() != null) {
            e7.getWindow().setSoftInputMode(2);
        }
        e7.show();
        DialogImeNavBarHelper.install(e7, editText);
    }

    private String toOperatorSymbol(String str) {
        return "不等于".equals(str) ? "!=" : "大于".equals(str) ? ">" : "小于".equals(str) ? "<" : "=";
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void toggleRule(SceneRule sceneRule) {
        if (this.adapter.isSelectionMode()) {
            return;
        }
        for (SceneRule sceneRule2 : this.rules) {
            if (sceneRule2.getId().equals(sceneRule.getId())) {
                sceneRule2.setEnabled(!sceneRule2.isEnabled());
                if (persistSingleRule(sceneRule2, null)) {
                    onPersistSuccess();
                    return;
                }
                return;
            }
        }
    }

    private void toggleSelectAll() {
        this.adapter.toggleSelectAll(!this.adapter.isAllSelected());
        updateMultiSelectButtons();
    }

    private void toggleSelectionMode() {
        this.adapter.setSelectionMode(!this.adapter.isSelectionMode());
        updateMultiSelectButtons();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void updateActionRowUi(ActionRowHolder actionRowHolder, SceneCatalog.ActionDef actionDef) {
        if (actionRowHolder == null) {
            return;
        }
        if (actionDef != null && ("close_ac_ui".equals(actionDef.key) || "open_ac_ui".equals(actionDef.key) || "close_systemui_ac_ui".equals(actionDef.key))) {
            actionRowHolder.spinnerValue.setVisibility(8);
            actionRowHolder.editValue.setVisibility(8);
            actionRowHolder.seekBar.setVisibility(0);
            actionRowHolder.seekLabel.setVisibility(0);
            String str = actionRowHolder.pendingValue;
            if (str != null) {
                try {
                    actionRowHolder.seekBar.setProgress(Math.max(1, Math.min(10, Integer.parseInt(str))) - 1);
                } catch (NumberFormatException unused) {
                    actionRowHolder.seekBar.setProgress(1);
                }
                actionRowHolder.pendingValue = null;
            } else if (!actionRowHolder.binding) {
                actionRowHolder.seekBar.setProgress(1);
            }
            actionRowHolder.editValue.setText(h0.FRAGMENT_ENCODE_SET);
            return;
        }
        actionRowHolder.seekBar.setVisibility(8);
        actionRowHolder.seekLabel.setVisibility(8);
        if (actionDef == null || !actionDef.hasValueOptions()) {
            actionRowHolder.spinnerValue.setVisibility(8);
            actionRowHolder.editValue.setVisibility(0);
            String str2 = actionRowHolder.pendingValue;
            if (str2 != null) {
                actionRowHolder.editValue.setText(str2);
                actionRowHolder.pendingValue = null;
                return;
            }
            return;
        }
        actionRowHolder.spinnerValue.setVisibility(0);
        actionRowHolder.editValue.setVisibility(8);
        ArrayAdapter arrayAdapter = new ArrayAdapter(requireContext(), R.layout.simple_spinner_item, actionDef.options);
        arrayAdapter.setDropDownViewResource(R.layout.simple_spinner_dropdown_item);
        actionRowHolder.spinnerValue.setAdapter((SpinnerAdapter) arrayAdapter);
        String str3 = actionRowHolder.pendingValue;
        if (str3 != null) {
            actionRowHolder.spinnerValue.setSelection(findOptionIndex(actionDef.options, str3), false);
            actionRowHolder.pendingValue = null;
        } else if (!actionRowHolder.binding && actionDef.options.length > 0) {
            actionRowHolder.spinnerValue.setSelection(0, false);
        }
        actionRowHolder.editValue.setText(h0.FRAGMENT_ENCODE_SET);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void updateConditionRowUi(ConditionRowHolder conditionRowHolder, SceneCatalog.FieldDef fieldDef) {
        String str;
        String str2;
        if (conditionRowHolder == null) {
            return;
        }
        int currentOperatorSelection = currentOperatorSelection(conditionRowHolder.spinnerOp);
        if (fieldDef == null || !fieldDef.isEnum()) {
            bindOperatorOptions(conditionRowHolder.spinnerOp, false);
            conditionRowHolder.spinnerValue.setVisibility(8);
            conditionRowHolder.editValue.setVisibility(0);
            Integer num = conditionRowHolder.pendingOpSelection;
            if (num != null) {
                conditionRowHolder.spinnerOp.setSelection(num.intValue(), false);
                if (!conditionRowHolder.binding) {
                    conditionRowHolder.pendingOpSelection = null;
                }
            } else if (currentOperatorSelection >= 0) {
                conditionRowHolder.spinnerOp.setSelection(currentOperatorSelection, false);
            }
            if (fieldDef == null || (str2 = fieldDef.unit) == null || str2.isEmpty()) {
                str = "请输入数值或文本";
            } else {
                str = "单位: " + fieldDef.unit;
            }
            conditionRowHolder.textHint.setText(str);
            return;
        }
        bindOperatorOptions(conditionRowHolder.spinnerOp, true);
        conditionRowHolder.spinnerValue.setVisibility(0);
        conditionRowHolder.editValue.setVisibility(8);
        ArrayAdapter arrayAdapter = new ArrayAdapter(requireContext(), R.layout.simple_spinner_item, fieldDef.options);
        arrayAdapter.setDropDownViewResource(R.layout.simple_spinner_dropdown_item);
        conditionRowHolder.spinnerValue.setAdapter((SpinnerAdapter) arrayAdapter);
        String str3 = conditionRowHolder.pendingEnumValue;
        if (str3 != null) {
            conditionRowHolder.spinnerValue.setSelection(findOptionIndex(fieldDef.options, str3), false);
            conditionRowHolder.pendingEnumValue = null;
        } else if (!conditionRowHolder.binding && fieldDef.options.length > 0) {
            conditionRowHolder.spinnerValue.setSelection(0, false);
        }
        Integer num2 = conditionRowHolder.pendingOpSelection;
        if (num2 != null) {
            conditionRowHolder.spinnerOp.setSelection(Math.min(num2.intValue(), 1), false);
            if (!conditionRowHolder.binding) {
                conditionRowHolder.pendingOpSelection = null;
            }
        } else if (currentOperatorSelection >= 0) {
            conditionRowHolder.spinnerOp.setSelection(Math.min(currentOperatorSelection, 1), false);
        } else if (!conditionRowHolder.binding) {
            conditionRowHolder.spinnerOp.setSelection(0, false);
        }
        conditionRowHolder.textHint.setText("可选: " + TextUtils.join(" / ", fieldDef.options));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void updateDelayUi(SeekBar seekBar, SeekBar seekBar2, TextView textView) {
        int progress = seekBar.getProgress();
        int progress2 = seekBar2.getProgress();
        if (progress >= 30) {
            if (progress2 != 0) {
                seekBar2.setProgress(0);
            }
            seekBar2.setEnabled(false);
            progress2 = 0;
            progress = 30;
        } else {
            seekBar2.setEnabled(true);
        }
        int i7 = (progress * 60) + progress2;
        if (i7 > 1800) {
            i7 = 1800;
        }
        textView.setText(SceneRule.formatDelay(i7));
    }

    private void updateMultiSelectButtons() {
        Button button;
        SceneRuleAdapter sceneRuleAdapter;
        SwitchMaterial switchMaterial = this.switchMaster;
        boolean z6 = switchMaterial != null && switchMaterial.isChecked() && this.rules.size() > 1;
        Button button2 = this.buttonMultiSelect;
        if (button2 != null) {
            button2.setVisibility(z6 ? 0 : 8);
        }
        SceneRuleAdapter sceneRuleAdapter2 = this.adapter;
        boolean z7 = sceneRuleAdapter2 != null && sceneRuleAdapter2.isSelectionMode() && z6;
        if (!z6 && (sceneRuleAdapter = this.adapter) != null && sceneRuleAdapter.isSelectionMode()) {
            this.adapter.setSelectionMode(false);
        }
        Button button3 = this.buttonSelectAll;
        if (button3 != null) {
            button3.setVisibility(z7 ? 0 : 8);
        }
        Button button4 = this.buttonDeleteSelected;
        if (button4 != null) {
            button4.setVisibility(z7 ? 0 : 8);
        }
        Button button5 = this.buttonMultiSelect;
        if (button5 != null) {
            button5.setText(z7 ? "取消多选" : "多选");
        }
        if (!z7 || (button = this.buttonSelectAll) == null) {
            return;
        }
        button.setText(this.adapter.isAllSelected() ? "取消全选" : "全选");
    }

    private void updateSceneContentVisibility() {
        SwitchMaterial switchMaterial = this.switchMaster;
        boolean z6 = switchMaterial != null && switchMaterial.isChecked();
        View view = this.layoutSceneContent;
        if (view != null) {
            view.setVisibility(z6 ? 0 : 8);
        }
    }

    private boolean validateSeatMoveActions(List<SceneRule.Action> list) {
        if (list == null) {
            return true;
        }
        for (SceneRule.Action action : list) {
            String type = action.getType();
            if ("seat_main_forward".equals(type) || "seat_main_backward".equals(type)) {
                try {
                    int parseInt = Integer.parseInt(action.getValue() == null ? h0.FRAGMENT_ENCODE_SET : action.getValue().trim());
                    if (parseInt < 1 || parseInt > 11) {
                        ToastHelper.show(requireContext(), "主驾前移/后移数值需在 1~11 之间");
                        return false;
                    }
                } catch (Exception unused) {
                    ToastHelper.show(requireContext(), "主驾前移/后移仅支持 1~11 的整数");
                    return false;
                }
            }
        }
        return true;
    }

    @Override // androidx.lifecycle.k
    public e1.c getDefaultViewModelCreationExtras() {
        return e1.a.INSTANCE;
    }

    @Override // androidx.fragment.app.z
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        final int i7 = 0;
        View inflate = layoutInflater.inflate(q.fragment_scene_mode, viewGroup, false);
        if ((NativeBridge.getCachedLicenseMask(requireContext()) & 1024) == 0) {
            inflate.setVisibility(8);
            return inflate;
        }
        this.prefs = SharedPreferencesUtils.getAppPrefs(requireContext());
        this.switchMaster = (SwitchMaterial) inflate.findViewById(p.switch_scene_mode_master);
        this.recyclerView = (RecyclerView) inflate.findViewById(p.recycler_scene_rules);
        this.textEmpty = (TextView) inflate.findViewById(p.text_scene_rule_empty);
        this.layoutSceneContent = inflate.findViewById(p.layout_scene_content);
        this.buttonAdd = (Button) inflate.findViewById(p.button_add_scene_rule);
        this.buttonReload = (Button) inflate.findViewById(p.button_reload_scene_rule);
        this.buttonMultiSelect = (Button) inflate.findViewById(p.button_multi_select_scene_rule);
        this.buttonSelectAll = (Button) inflate.findViewById(p.button_select_all_scene_rule);
        this.buttonDeleteSelected = (Button) inflate.findViewById(p.button_delete_selected_scene_rule);
        this.switchMaster.setChecked(this.prefs.getBoolean(KEY_SCENE_MODE_MASTER, false));
        final int i8 = 2;
        this.switchMaster.setOnCheckedChangeListener(new z2.a(i8, this));
        RecyclerView recyclerView = this.recyclerView;
        getContext();
        final int i9 = 4;
        recyclerView.setLayoutManager(new GridLayoutManager(4));
        SceneRuleAdapter sceneRuleAdapter = new SceneRuleAdapter(new f(this), new f(this), new f(this));
        this.adapter = sceneRuleAdapter;
        this.recyclerView.setAdapter(sceneRuleAdapter);
        this.buttonAdd.setOnClickListener(new View.OnClickListener(this) { // from class: com.navi.tool.ui.scene.g

            /* renamed from: b, reason: collision with root package name */
            public final /* synthetic */ SceneModeFragment f5802b;

            {
                this.f5802b = this;
            }

            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                int i10 = i7;
                SceneModeFragment sceneModeFragment = this.f5802b;
                switch (i10) {
                    case 0:
                        sceneModeFragment.lambda$onCreateView$1(view);
                        break;
                    case 1:
                        sceneModeFragment.lambda$onCreateView$2(view);
                        break;
                    case 2:
                        sceneModeFragment.lambda$onCreateView$3(view);
                        break;
                    case 3:
                        sceneModeFragment.lambda$onCreateView$4(view);
                        break;
                    default:
                        sceneModeFragment.lambda$onCreateView$5(view);
                        break;
                }
            }
        });
        final int i10 = 1;
        this.buttonReload.setOnClickListener(new View.OnClickListener(this) { // from class: com.navi.tool.ui.scene.g

            /* renamed from: b, reason: collision with root package name */
            public final /* synthetic */ SceneModeFragment f5802b;

            {
                this.f5802b = this;
            }

            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                int i102 = i10;
                SceneModeFragment sceneModeFragment = this.f5802b;
                switch (i102) {
                    case 0:
                        sceneModeFragment.lambda$onCreateView$1(view);
                        break;
                    case 1:
                        sceneModeFragment.lambda$onCreateView$2(view);
                        break;
                    case 2:
                        sceneModeFragment.lambda$onCreateView$3(view);
                        break;
                    case 3:
                        sceneModeFragment.lambda$onCreateView$4(view);
                        break;
                    default:
                        sceneModeFragment.lambda$onCreateView$5(view);
                        break;
                }
            }
        });
        this.buttonMultiSelect.setOnClickListener(new View.OnClickListener(this) { // from class: com.navi.tool.ui.scene.g

            /* renamed from: b, reason: collision with root package name */
            public final /* synthetic */ SceneModeFragment f5802b;

            {
                this.f5802b = this;
            }

            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                int i102 = i8;
                SceneModeFragment sceneModeFragment = this.f5802b;
                switch (i102) {
                    case 0:
                        sceneModeFragment.lambda$onCreateView$1(view);
                        break;
                    case 1:
                        sceneModeFragment.lambda$onCreateView$2(view);
                        break;
                    case 2:
                        sceneModeFragment.lambda$onCreateView$3(view);
                        break;
                    case 3:
                        sceneModeFragment.lambda$onCreateView$4(view);
                        break;
                    default:
                        sceneModeFragment.lambda$onCreateView$5(view);
                        break;
                }
            }
        });
        final int i11 = 3;
        this.buttonSelectAll.setOnClickListener(new View.OnClickListener(this) { // from class: com.navi.tool.ui.scene.g

            /* renamed from: b, reason: collision with root package name */
            public final /* synthetic */ SceneModeFragment f5802b;

            {
                this.f5802b = this;
            }

            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                int i102 = i11;
                SceneModeFragment sceneModeFragment = this.f5802b;
                switch (i102) {
                    case 0:
                        sceneModeFragment.lambda$onCreateView$1(view);
                        break;
                    case 1:
                        sceneModeFragment.lambda$onCreateView$2(view);
                        break;
                    case 2:
                        sceneModeFragment.lambda$onCreateView$3(view);
                        break;
                    case 3:
                        sceneModeFragment.lambda$onCreateView$4(view);
                        break;
                    default:
                        sceneModeFragment.lambda$onCreateView$5(view);
                        break;
                }
            }
        });
        this.buttonDeleteSelected.setOnClickListener(new View.OnClickListener(this) { // from class: com.navi.tool.ui.scene.g

            /* renamed from: b, reason: collision with root package name */
            public final /* synthetic */ SceneModeFragment f5802b;

            {
                this.f5802b = this;
            }

            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                int i102 = i9;
                SceneModeFragment sceneModeFragment = this.f5802b;
                switch (i102) {
                    case 0:
                        sceneModeFragment.lambda$onCreateView$1(view);
                        break;
                    case 1:
                        sceneModeFragment.lambda$onCreateView$2(view);
                        break;
                    case 2:
                        sceneModeFragment.lambda$onCreateView$3(view);
                        break;
                    case 3:
                        sceneModeFragment.lambda$onCreateView$4(view);
                        break;
                    default:
                        sceneModeFragment.lambda$onCreateView$5(view);
                        break;
                }
            }
        });
        loadRules();
        updateSceneContentVisibility();
        return inflate;
    }

    @Override // androidx.fragment.app.z
    public void onResume() {
        super.onResume();
        loadRules();
        updateSceneContentVisibility();
    }
}
