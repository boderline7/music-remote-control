package com.navi.tool.utils;

import android.content.Context;
import android.os.Environment;
import com.navi.tool.model.SceneRule;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import org.json.JSONObject;
import s5.h0;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public class SceneRuleStorage {
    private static final String INVALID_NAME_CHARS = "\\\\/:*?\"<>|";
    private static final String JSON_SUFFIX = ".json";
    private static final String TAG = "SceneRuleStorage";

    public static boolean deleteRuleFile(Context context, String str) {
        try {
            String normalizeRuleName = normalizeRuleName(str);
            if (normalizeRuleName.isEmpty()) {
                return false;
            }
            File ruleFile = getRuleFile(context, normalizeRuleName);
            if (ruleFile.exists()) {
                if (!ruleFile.delete()) {
                    return false;
                }
            }
            return true;
        } catch (Exception unused) {
            return false;
        }
    }

    private static File getRuleFile(Context context, String str) {
        return new File(getSceneDir(context), a.a.i(new StringBuilder(), normalizeRuleName(str), JSON_SUFFIX));
    }

    public static File getSceneDir(Context context) {
        File file = new File(Environment.getExternalStorageDirectory(), "navitool");
        File file2 = new File(file, "scenefile");
        if (!file2.exists() && ((file.exists() || file.mkdirs()) && !file2.exists())) {
            file2.mkdirs();
        }
        return file2;
    }

    public static boolean isValidRuleName(String str) {
        String normalizeRuleName = normalizeRuleName(str);
        if (normalizeRuleName.isEmpty() || normalizeRuleName.endsWith(".") || normalizeRuleName.endsWith(" ")) {
            return false;
        }
        for (int i7 = 0; i7 < normalizeRuleName.length(); i7++) {
            if (INVALID_NAME_CHARS.indexOf(normalizeRuleName.charAt(i7)) >= 0) {
                return false;
            }
        }
        return true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ boolean lambda$loadRules$0(File file, String str) {
        return str != null && str.toLowerCase().endsWith(JSON_SUFFIX);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ int lambda$loadRules$1(SceneRule sceneRule, SceneRule sceneRule2) {
        return sceneRule.getName().compareToIgnoreCase(sceneRule2.getName());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ boolean lambda$saveRules$2(File file, String str) {
        return str != null && str.toLowerCase().endsWith(JSON_SUFFIX);
    }

    public static List<SceneRule> loadRules(Context context) {
        ArrayList arrayList = new ArrayList();
        File[] listFiles = getSceneDir(context).listFiles(new com.navi.tool.service.f(5));
        if (listFiles != null && listFiles.length != 0) {
            for (File file : listFiles) {
                if (file != null && file.isFile()) {
                    try {
                        FileInputStream fileInputStream = new FileInputStream(file);
                        byte[] bArr = new byte[(int) file.length()];
                        int read = fileInputStream.read(bArr);
                        fileInputStream.close();
                        if (read > 0) {
                            SceneRule fromJson = SceneRule.fromJson(new JSONObject(new String(bArr, "UTF-8")));
                            if (fromJson.getName().isEmpty()) {
                                String name = file.getName();
                                if (name.toLowerCase().endsWith(JSON_SUFFIX)) {
                                    name = name.substring(0, name.length() - 5);
                                }
                                fromJson.setName(name);
                            }
                            arrayList.add(fromJson);
                        }
                    } catch (Exception unused) {
                    }
                }
            }
            Collections.sort(arrayList, new m0.a(13));
        }
        return arrayList;
    }

    private static String normalizeRuleName(String str) {
        return str == null ? h0.FRAGMENT_ENCODE_SET : str.trim();
    }

    public static boolean saveRule(Context context, SceneRule sceneRule, String str) {
        if (sceneRule == null) {
            return false;
        }
        try {
            String normalizeRuleName = normalizeRuleName(sceneRule.getName());
            if (!isValidRuleName(normalizeRuleName)) {
                return false;
            }
            sceneRule.setName(normalizeRuleName);
            File ruleFile = getRuleFile(context, normalizeRuleName);
            byte[] bytes = sceneRule.toJson().toString().getBytes(StandardCharsets.UTF_8);
            FileOutputStream fileOutputStream = new FileOutputStream(ruleFile, false);
            try {
                fileOutputStream.write(bytes);
                fileOutputStream.flush();
                fileOutputStream.close();
                String normalizeRuleName2 = normalizeRuleName(str);
                if (normalizeRuleName2.isEmpty() || normalizeRuleName2.equalsIgnoreCase(normalizeRuleName)) {
                    return true;
                }
                File ruleFile2 = getRuleFile(context, normalizeRuleName2);
                if (!ruleFile2.exists() || ruleFile2.getAbsolutePath().equalsIgnoreCase(ruleFile.getAbsolutePath())) {
                    return true;
                }
                ruleFile2.delete();
                return true;
            } finally {
            }
        } catch (Exception unused) {
            return false;
        }
    }

    public static boolean saveRules(Context context, List<SceneRule> list) {
        try {
            File sceneDir = getSceneDir(context);
            if (list == null) {
                list = new ArrayList<>();
            }
            HashSet hashSet = new HashSet();
            HashSet hashSet2 = new HashSet();
            LinkedHashMap linkedHashMap = new LinkedHashMap();
            for (SceneRule sceneRule : list) {
                String normalizeRuleName = normalizeRuleName(sceneRule.getName());
                if (!isValidRuleName(normalizeRuleName)) {
                    return false;
                }
                String lowerCase = normalizeRuleName.toLowerCase();
                if (hashSet.contains(lowerCase)) {
                    return false;
                }
                hashSet.add(lowerCase);
                sceneRule.setName(normalizeRuleName);
                String str = normalizeRuleName + JSON_SUFFIX;
                hashSet2.add(str.toLowerCase());
                linkedHashMap.put(str, sceneRule.toJson().toString().getBytes(StandardCharsets.UTF_8));
            }
            for (Map.Entry entry : linkedHashMap.entrySet()) {
                FileOutputStream fileOutputStream = new FileOutputStream(new File(sceneDir, (String) entry.getKey()), false);
                try {
                    fileOutputStream.write((byte[]) entry.getValue());
                    fileOutputStream.flush();
                    fileOutputStream.close();
                } finally {
                }
            }
            File[] listFiles = sceneDir.listFiles(new com.navi.tool.service.f(4));
            if (listFiles == null) {
                return true;
            }
            for (File file : listFiles) {
                if (file != null && file.isFile() && !hashSet2.contains(file.getName().toLowerCase())) {
                    file.delete();
                }
            }
            return true;
        } catch (Exception unused) {
            return false;
        }
    }
}
