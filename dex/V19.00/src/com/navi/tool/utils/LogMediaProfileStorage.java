package com.navi.tool.utils;

import android.content.Context;
import android.os.Environment;
import android.util.Xml;
import com.navi.tool.model.LogMediaProfile;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlSerializer;
import s5.h0;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public class LogMediaProfileStorage {
    private static final String TAG = "LogMediaProfileStorage";

    public static boolean deleteProfile(Context context, String str) {
        if (str != null && !str.trim().isEmpty()) {
            File file = new File(getMediaProfileDir(context), str.trim() + ".xml");
            if (file.exists()) {
                try {
                    return file.delete();
                } catch (Exception unused) {
                }
            }
        }
        return false;
    }

    public static File getMediaProfileDir(Context context) {
        File file = new File(Environment.getExternalStorageDirectory(), "navitool");
        File file2 = new File(file, "profile");
        File file3 = new File(file2, "media");
        if (file3.exists() || !(file.exists() || file.mkdirs())) {
            return file3;
        }
        if ((file2.exists() || file2.mkdirs()) && !file3.exists()) {
            file3.mkdirs();
        }
        return file3;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ int lambda$loadProfiles$0(LogMediaProfile logMediaProfile, LogMediaProfile logMediaProfile2) {
        return logMediaProfile.getName().compareToIgnoreCase(logMediaProfile2.getName());
    }

    public static List<LogMediaProfile> loadProfiles(Context context) {
        LogMediaProfile readProfile;
        File mediaProfileDir = getMediaProfileDir(context);
        ArrayList arrayList = new ArrayList();
        File[] listFiles = mediaProfileDir.listFiles();
        if (listFiles == null) {
            return arrayList;
        }
        for (File file : listFiles) {
            if (file.isFile() && file.getName().toLowerCase().endsWith(".xml") && (readProfile = readProfile(file)) != null) {
                arrayList.add(readProfile);
            }
        }
        arrayList.sort(new m0.a(12));
        return arrayList;
    }

    private static String nextTextSafe(XmlPullParser xmlPullParser) {
        String nextText = xmlPullParser.nextText();
        return nextText == null ? h0.FRAGMENT_ENCODE_SET : nextText.trim();
    }

    public static LogMediaProfile readProfile(File file) {
        String str;
        String name;
        try {
            FileInputStream fileInputStream = new FileInputStream(file);
            XmlPullParser newPullParser = Xml.newPullParser();
            newPullParser.setInput(fileInputStream, "UTF-8");
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
            String str13 = null;
            String str14 = null;
            boolean z6 = false;
            int i7 = 0;
            int i8 = 0;
            int i9 = 0;
            int i10 = 0;
            while (true) {
                int next = newPullParser.next();
                if (next == 1) {
                    break;
                }
                if (next == 2) {
                    String name2 = newPullParser.getName();
                    if ("tag".equals(name2)) {
                        str3 = nextTextSafe(newPullParser);
                    } else if ("pattern".equals(name2)) {
                        str4 = nextTextSafe(newPullParser);
                    } else if ("mediaPath".equals(name2)) {
                        str5 = nextTextSafe(newPullParser);
                    } else if ("action".equals(name2)) {
                        str2 = nextTextSafe(newPullParser);
                    } else if ("enabled".equals(name2)) {
                        z6 = "true".equalsIgnoreCase(nextTextSafe(newPullParser));
                    } else if ("playMode".equals(name2)) {
                        str6 = nextTextSafe(newPullParser);
                    } else if ("releasePattern".equals(name2)) {
                        str7 = nextTextSafe(newPullParser);
                    } else if ("conditionType".equals(name2)) {
                        str8 = nextTextSafe(newPullParser);
                    } else if ("conditionVariable".equals(name2)) {
                        str9 = nextTextSafe(newPullParser);
                    } else if ("conditionOperator".equals(name2)) {
                        str10 = nextTextSafe(newPullParser);
                    } else if ("conditionValue".equals(name2)) {
                        str11 = nextTextSafe(newPullParser);
                    } else if ("variableName".equals(name2)) {
                        str12 = nextTextSafe(newPullParser);
                    } else if ("variableOp".equals(name2)) {
                        str13 = nextTextSafe(newPullParser);
                    } else if ("variableValue".equals(name2)) {
                        str14 = nextTextSafe(newPullParser);
                    } else if ("delaySeconds".equals(name2)) {
                        try {
                            i7 = Integer.parseInt(nextTextSafe(newPullParser));
                        } catch (Exception unused) {
                        }
                    } else if ("cooldownSeconds".equals(name2)) {
                        i8 = Integer.parseInt(nextTextSafe(newPullParser));
                    } else if ("comboSeconds".equals(name2)) {
                        i9 = Integer.parseInt(nextTextSafe(newPullParser));
                    } else if ("comboCount".equals(name2)) {
                        i10 = Integer.parseInt(nextTextSafe(newPullParser));
                    }
                }
            }
            fileInputStream.close();
            if (str3 != null && str4 != null && str5 != null) {
                if (str2 != null && !str2.trim().isEmpty()) {
                    str = str2.trim();
                    String str15 = str;
                    name = file.getName();
                    if (name.toLowerCase().endsWith(".xml") && name.length() > 4) {
                        name = name.substring(0, name.length() - 4);
                    }
                    return new LogMediaProfile(name, str3, str4, str5, str15, z6, str6, str7, str8, str9, str10, str11, str12, str13, str14, i7, i8, i9, i10);
                }
                str = "play_file";
                String str152 = str;
                name = file.getName();
                if (name.toLowerCase().endsWith(".xml")) {
                    name = name.substring(0, name.length() - 4);
                }
                return new LogMediaProfile(name, str3, str4, str5, str152, z6, str6, str7, str8, str9, str10, str11, str12, str13, str14, i7, i8, i9, i10);
            }
        } catch (Exception unused2) {
        }
        return null;
    }

    public static String saveProfile(Context context, LogMediaProfile logMediaProfile) {
        File mediaProfileDir = getMediaProfileDir(context);
        String trim = logMediaProfile.getName() == null ? "profile" : logMediaProfile.getName().trim();
        if (trim.isEmpty()) {
            trim = "profile";
        }
        File file = new File(mediaProfileDir, trim.concat(".xml"));
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(file);
            XmlSerializer newSerializer = Xml.newSerializer();
            newSerializer.setOutput(fileOutputStream, "UTF-8");
            newSerializer.startDocument("UTF-8", Boolean.TRUE);
            newSerializer.startTag(null, "profile");
            writeTag(newSerializer, "name", logMediaProfile.getName());
            writeTag(newSerializer, "tag", logMediaProfile.getTag());
            writeTag(newSerializer, "pattern", logMediaProfile.getPattern());
            writeTag(newSerializer, "mediaPath", logMediaProfile.getMediaPath());
            writeTag(newSerializer, "action", logMediaProfile.getAction());
            writeTag(newSerializer, "enabled", String.valueOf(logMediaProfile.isEnabled()));
            writeTag(newSerializer, "playMode", logMediaProfile.getPlayMode());
            writeTag(newSerializer, "releasePattern", logMediaProfile.getReleasePattern());
            writeTag(newSerializer, "conditionType", logMediaProfile.getConditionType());
            writeTag(newSerializer, "conditionVariable", logMediaProfile.getConditionVariable());
            writeTag(newSerializer, "conditionOperator", logMediaProfile.getConditionOperator());
            writeTag(newSerializer, "conditionValue", logMediaProfile.getConditionValue());
            writeTag(newSerializer, "variableName", logMediaProfile.getVariableName());
            writeTag(newSerializer, "variableOp", logMediaProfile.getVariableOp());
            writeTag(newSerializer, "variableValue", logMediaProfile.getVariableValue());
            writeTag(newSerializer, "delaySeconds", String.valueOf(logMediaProfile.getDelaySeconds()));
            writeTag(newSerializer, "cooldownSeconds", String.valueOf(logMediaProfile.getCooldownSeconds()));
            writeTag(newSerializer, "comboSeconds", String.valueOf(logMediaProfile.getComboSeconds()));
            writeTag(newSerializer, "comboCount", String.valueOf(logMediaProfile.getComboCount()));
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
