package com.navi.tool.utils;

import android.content.Context;
import android.media.MediaScannerConnection;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import s5.h0;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public class ZipHelper {

    public interface ZipCallback {
        void onError(String str);

        void onStart();

        void onSuccess();
    }

    private static void collectFiles(File file, List<String> list) {
        File[] listFiles = file.listFiles();
        if (listFiles == null) {
            return;
        }
        for (File file2 : listFiles) {
            if (file2.isDirectory()) {
                collectFiles(file2, list);
            } else {
                list.add(file2.getAbsolutePath());
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ void lambda$unrar$0(File file, File file2, Context context, ZipCallback zipCallback) {
        try {
            if (!file.exists() && !file.mkdirs()) {
                throw new IOException("无法创建目标目录");
            }
            h2.e.a(file2, file);
            scanDirectory(context, file);
            if (zipCallback != null) {
                zipCallback.onSuccess();
            }
        } catch (Exception e7) {
            e7.printStackTrace();
            String str = "解压失败: " + e7.getMessage();
            if (e7.getMessage() == null || e7.getMessage().contains("null") || e7.getMessage().contains("bad header")) {
                str = "解压失败：不支持该RAR版本(可能是RAR5)，请使用RAR4格式压缩";
            }
            if (zipCallback != null) {
                zipCallback.onError(str);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ void lambda$unzip$1(File file, File file2, Context context, ZipCallback zipCallback) {
        String message;
        boolean z6 = true;
        try {
            unzipWithCharset(file, file2, StandardCharsets.UTF_8);
            message = h0.FRAGMENT_ENCODE_SET;
        } catch (Exception e7) {
            String message2 = e7.getMessage();
            try {
                unzipWithCharset(file, file2, Charset.forName("GBK"));
                message = message2;
            } catch (Exception e8) {
                message = e8.getMessage();
                z6 = false;
            }
        }
        if (!z6) {
            if (zipCallback != null) {
                zipCallback.onError(message);
            }
        } else {
            scanDirectory(context, file2);
            if (zipCallback != null) {
                zipCallback.onSuccess();
            }
        }
    }

    private static void scanDirectory(Context context, File file) {
        if (context == null || file == null || !file.exists()) {
            return;
        }
        ArrayList arrayList = new ArrayList();
        collectFiles(file, arrayList);
        if (arrayList.isEmpty()) {
            return;
        }
        MediaScannerConnection.scanFile(context, (String[]) arrayList.toArray(new String[0]), null, null);
    }

    public static void unrar(Context context, File file, File file2, ZipCallback zipCallback) {
        if (zipCallback != null) {
            zipCallback.onStart();
        }
        new Thread(new u(file2, file, context, zipCallback, 0)).start();
    }

    public static void unzip(Context context, File file, File file2, ZipCallback zipCallback) {
        if (zipCallback != null) {
            zipCallback.onStart();
        }
        new Thread(new u(file, file2, context, zipCallback, 1)).start();
    }

    private static void unzipWithCharset(File file, File file2, Charset charset) {
        String absolutePath = file2.getAbsolutePath();
        ZipFile zipFile = null;
        try {
            zipFile = charset != null ? new ZipFile(file, charset) : new ZipFile(file);
            Enumeration<? extends ZipEntry> entries = zipFile.entries();
            while (entries.hasMoreElements()) {
                ZipEntry nextElement = entries.nextElement();
                File file3 = new File(absolutePath, nextElement.getName());
                if (!nextElement.isDirectory()) {
                    File parentFile = file3.getParentFile();
                    if (parentFile != null && !parentFile.isDirectory() && !parentFile.mkdirs()) {
                        throw new IOException("Failed to create directory " + parentFile);
                    }
                    InputStream inputStream = zipFile.getInputStream(nextElement);
                    try {
                        BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(new FileOutputStream(file3));
                        try {
                            byte[] bArr = new byte[8192];
                            while (true) {
                                int read = inputStream.read(bArr);
                                if (read == -1) {
                                    break;
                                } else {
                                    bufferedOutputStream.write(bArr, 0, read);
                                }
                            }
                            bufferedOutputStream.close();
                            inputStream.close();
                        } catch (Throwable th) {
                            try {
                                bufferedOutputStream.close();
                            } catch (Throwable th2) {
                                th.addSuppressed(th2);
                            }
                            throw th;
                        }
                    } finally {
                    }
                } else if (!file3.isDirectory() && !file3.mkdirs()) {
                    throw new IOException("Failed to create directory " + file3);
                }
            }
            try {
                zipFile.close();
            } catch (IOException e7) {
                e7.printStackTrace();
            }
        } catch (Throwable th3) {
            if (zipFile != null) {
                try {
                    zipFile.close();
                } catch (IOException e8) {
                    e8.printStackTrace();
                }
            }
            throw th3;
        }
    }
}
