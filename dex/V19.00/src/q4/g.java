package q4;

import java.io.File;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public interface g {
    void onConnected(String str, String str2);

    void onDeviceAlive(String str, String str2);

    void onDeviceFound(String str, String str2);

    void onError(String str);

    void onFileProgress(String str, int i7);

    void onFileReceived(String str, File file);

    void onMessageReceived(String str);
}
