package com.navi.tool.utils;

import android.content.Context;
import android.os.Environment;
import android.util.Xml;
import com.navi.tool.model.LogAssistantProfile;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlSerializer;
import s5.h0;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public class LogAssistantProfileStorage {
    private static final String TAG = "LogAssistantProfileStorage";

    public static boolean deleteProfile(Context context, String str) {
        if (str != null && !str.trim().isEmpty()) {
            File file = new File(getAssistantProfileDir(context), str.trim() + ".xml");
            if (file.exists()) {
                try {
                    return file.delete();
                } catch (Exception unused) {
                }
            }
        }
        return false;
    }

    public static File getAssistantProfileDir(Context context) {
        File file = new File(Environment.getExternalStorageDirectory(), "navitool");
        File file2 = new File(file, "profile");
        File file3 = new File(file2, "tool");
        if (file3.exists() || !(file.exists() || file.mkdirs())) {
            return file3;
        }
        if ((file2.exists() || file2.mkdirs()) && !file3.exists()) {
            file3.mkdirs();
        }
        return file3;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ int lambda$loadProfiles$0(LogAssistantProfile logAssistantProfile, LogAssistantProfile logAssistantProfile2) {
        return logAssistantProfile.getName().compareToIgnoreCase(logAssistantProfile2.getName());
    }

    public static List<LogAssistantProfile> loadProfiles(Context context) {
        LogAssistantProfile readProfile;
        File assistantProfileDir = getAssistantProfileDir(context);
        ArrayList arrayList = new ArrayList();
        File[] listFiles = assistantProfileDir.listFiles();
        if (listFiles == null) {
            return arrayList;
        }
        for (File file : listFiles) {
            if (file.isFile() && file.getName().toLowerCase().endsWith(".xml") && (readProfile = readProfile(file)) != null) {
                arrayList.add(readProfile);
            }
        }
        arrayList.sort(new m0.a(11));
        return arrayList;
    }

    private static String nextTextSafe(XmlPullParser xmlPullParser) {
        String nextText = xmlPullParser.nextText();
        return nextText == null ? h0.FRAGMENT_ENCODE_SET : nextText.trim();
    }

    public static LogAssistantProfile readProfile(File file) {
        String str;
        try {
            FileInputStream fileInputStream = new FileInputStream(file);
            XmlPullParser newPullParser = Xml.newPullParser();
            newPullParser.setInput(fileInputStream, "UTF-8");
            boolean z6 = false;
            int i7 = 0;
            int i8 = 0;
            int i9 = 0;
            int i10 = 0;
            String str2 = null;
            String str3 = null;
            String str4 = null;
            String str5 = null;
            String str6 = null;
            String str7 = null;
            String str8 = null;
            String str9 = null;
            String str10 = null;
            String str11 = null;
            String str12 = null;
            while (true) {
                int next = newPullParser.next();
                if (next == 1) {
                    break;
                }
                if (next == 2) {
                    String name = newPullParser.getName();
                    if ("name".equals(name)) {
                        str2 = nextTextSafe(newPullParser);
                    } else if ("tag".equals(name)) {
                        str3 = nextTextSafe(newPullParser);
                    } else if ("pattern".equals(name)) {
                        str4 = nextTextSafe(newPullParser);
                    } else if ("action".equals(name)) {
                        str5 = nextTextSafe(newPullParser);
                    } else if ("enabled".equals(name)) {
                        z6 = "true".equalsIgnoreCase(nextTextSafe(newPullParser));
                    } else if ("conditionType".equals(name)) {
                        str6 = nextTextSafe(newPullParser);
                    } else if ("conditionVariable".equals(name)) {
                        str7 = nextTextSafe(newPullParser);
                    } else if ("conditionOperator".equals(name)) {
                        str8 = nextTextSafe(newPullParser);
                    } else if ("conditionValue".equals(name)) {
                        str9 = nextTextSafe(newPullParser);
                    } else if ("variableName".equals(name)) {
                        str10 = nextTextSafe(newPullParser);
                    } else if ("variableOp".equals(name)) {
                        str11 = nextTextSafe(newPullParser);
                    } else if ("variableValue".equals(name)) {
                        str12 = nextTextSafe(newPullParser);
                    } else if ("delaySeconds".equals(name)) {
                        try {
                            i7 = Integer.parseInt(nextTextSafe(newPullParser));
                        } catch (Exception unused) {
                        }
                    } else if ("cooldownSeconds".equals(name)) {
                        i8 = Integer.parseInt(nextTextSafe(newPullParser));
                    } else if ("comboSeconds".equals(name)) {
                        i9 = Integer.parseInt(nextTextSafe(newPullParser));
                    } else if ("comboCount".equals(name)) {
                        i10 = Integer.parseInt(nextTextSafe(newPullParser));
                    }
                }
            }
            fileInputStream.close();
            if (str3 == null || str4 == null || str5 == null) {
                return null;
            }
            String name2 = file.getName();
            if (name2.toLowerCase().endsWith(".xml") && name2.length() > 4) {
                name2 = name2.substring(0, name2.length() - 4);
            }
            if (str2 != null && !str2.trim().isEmpty()) {
                str = str2;
                return new LogAssistantProfile(str, str3, str4, str5, z6, str6, str7, str8, str9, str10, str11, str12, i7, i8, i9, i10);
            }
            str = name2;
            return new LogAssistantProfile(str, str3, str4, str5, z6, str6, str7, str8, str9, str10, str11, str12, i7, i8, i9, i10);
        } catch (Exception unused2) {
            return null;
        }
    }

    public static String saveProfile(Context context, LogAssistantProfile logAssistantProfile) {
        File assistantProfileDir = getAssistantProfileDir(context);
        String trim = logAssistantProfile.getName() == null ? "profile" : logAssistantProfile.getName().trim();
        if (trim.isEmpty()) {
            trim = "profile";
        }
        File file = new File(assistantProfileDir, trim.concat(".xml"));
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(file);
            XmlSerializer newSerializer = Xml.newSerializer();
            newSerializer.setOutput(fileOutputStream, "UTF-8");
            newSerializer.startDocument("UTF-8", Boolean.TRUE);
            newSerializer.startTag(null, "profile");
            writeTag(newSerializer, "name", logAssistantProfile.getName());
            writeTag(newSerializer, "tag", logAssistantProfile.getTag());
            writeTag(newSerializer, "pattern", logAssistantProfile.getPattern());
            writeTag(newSerializer, "action", logAssistantProfile.getAction());
            writeTag(newSerializer, "enabled", String.valueOf(logAssistantProfile.isEnabled()));
            writeTag(newSerializer, "conditionType", logAssistantProfile.getConditionType());
            writeTag(newSerializer, "conditionVariable", logAssistantProfile.getConditionVariable());
            writeTag(newSerializer, "conditionOperator", logAssistantProfile.getConditionOperator());
            writeTag(newSerializer, "conditionValue", logAssistantProfile.getConditionValue());
            writeTag(newSerializer, "variableName", logAssistantProfile.getVariableName());
            writeTag(newSerializer, "variableOp", logAssistantProfile.getVariableOp());
            writeTag(newSerializer, "variableValue", logAssistantProfile.getVariableValue());
            writeTag(newSerializer, "delaySeconds", String.valueOf(logAssistantProfile.getDelaySeconds()));
            writeTag(newSerializer, "cooldownSeconds", String.valueOf(logAssistantProfile.getCooldownSeconds()));
            writeTag(newSerializer, "comboSeconds", String.valueOf(logAssistantProfile.getComboSeconds()));
            writeTag(newSerializer, "comboCount", String.valueOf(logAssistantProfile.getComboCount()));
            newSerializer.endTag(null, "profile");
            newSerializer.endDocument();
            fileOutputStream.flush();
            fileOutputStream.close();
            return null;
        } catch (Exception e7) {
            file.getAbsolutePath();
            return e7.getMessage() != null ? e7.getMessage() : e7.getClass().getSimpleName();
        }
    }

    private static void writeTag(XmlSerializer xmlSerializer, String str, String str2) {
        xmlSerializer.startTag(null, str);
        if (str2 == null) {
            str2 = h0.FRAGMENT_ENCODE_SET;
        }
        xmlSerializer.text(str2);
        xmlSerializer.endTag(null, str);
    }
}
