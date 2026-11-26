package q4;

import a5.k;
import a5.l;
import a5.n;
import android.support.v4.media.session.PlaybackStateCompat;
import com.navi.tool.ui.extra.y3;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final class a extends n {

    /* renamed from: n, reason: collision with root package name */
    public static final HashMap f8450n;

    /* renamed from: l, reason: collision with root package name */
    public File f8451l;

    /* renamed from: m, reason: collision with root package name */
    public y3 f8452m;

    static {
        HashMap hashMap = new HashMap();
        f8450n = hashMap;
        hashMap.put("apk", "application/vnd.android.package-archive");
        hashMap.put("jpg", "image/jpeg");
        hashMap.put("jpeg", "image/jpeg");
        hashMap.put("png", "image/png");
        hashMap.put("gif", "image/gif");
        hashMap.put("mp3", "audio/mpeg");
        hashMap.put("mp4", "video/mp4");
        hashMap.put("zip", "application/zip");
        hashMap.put("rar", "application/x-rar-compressed");
        hashMap.put("txt", n.MIME_PLAINTEXT);
        hashMap.put("html", n.MIME_HTML);
    }

    public static void i(File file, File file2) {
        FileInputStream fileInputStream = new FileInputStream(file);
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(file2);
            try {
                byte[] bArr = new byte[131072];
                while (true) {
                    int read = fileInputStream.read(bArr);
                    if (read <= 0) {
                        fileOutputStream.close();
                        fileInputStream.close();
                        return;
                    }
                    fileOutputStream.write(bArr, 0, read);
                }
            } finally {
            }
        } catch (Throwable th) {
            try {
                fileInputStream.close();
            } catch (Throwable th2) {
                th.addSuppressed(th2);
            }
            throw th;
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:42:0x00c0 A[Catch: Exception -> 0x004e, TRY_LEAVE, TryCatch #0 {Exception -> 0x004e, blocks: (B:3:0x0002, B:4:0x0014, B:6:0x001a, B:9:0x002e, B:12:0x0035, B:14:0x003d, B:18:0x0045, B:19:0x0052, B:22:0x0072, B:28:0x007e, B:30:0x0083, B:33:0x0096, B:36:0x00af, B:40:0x00b8, B:42:0x00c0, B:65:0x00f2, B:68:0x00f9, B:71:0x0100, B:73:0x0112, B:77:0x011c, B:79:0x0122, B:80:0x0125, B:82:0x0138, B:83:0x013e, B:87:0x009c, B:88:0x0089, B:99:0x014b, B:102:0x0157, B:25:0x0078, B:51:0x00ce, B:52:0x00db, B:54:0x00e1), top: B:2:0x0002, inners: #1, #2 }] */
    /* JADX WARN: Removed duplicated region for block: B:50:0x00ce A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:79:0x0122 A[Catch: Exception -> 0x004e, TryCatch #0 {Exception -> 0x004e, blocks: (B:3:0x0002, B:4:0x0014, B:6:0x001a, B:9:0x002e, B:12:0x0035, B:14:0x003d, B:18:0x0045, B:19:0x0052, B:22:0x0072, B:28:0x007e, B:30:0x0083, B:33:0x0096, B:36:0x00af, B:40:0x00b8, B:42:0x00c0, B:65:0x00f2, B:68:0x00f9, B:71:0x0100, B:73:0x0112, B:77:0x011c, B:79:0x0122, B:80:0x0125, B:82:0x0138, B:83:0x013e, B:87:0x009c, B:88:0x0089, B:99:0x014b, B:102:0x0157, B:25:0x0078, B:51:0x00ce, B:52:0x00db, B:54:0x00e1), top: B:2:0x0002, inners: #1, #2 }] */
    /* JADX WARN: Removed duplicated region for block: B:82:0x0138 A[Catch: Exception -> 0x004e, TryCatch #0 {Exception -> 0x004e, blocks: (B:3:0x0002, B:4:0x0014, B:6:0x001a, B:9:0x002e, B:12:0x0035, B:14:0x003d, B:18:0x0045, B:19:0x0052, B:22:0x0072, B:28:0x007e, B:30:0x0083, B:33:0x0096, B:36:0x00af, B:40:0x00b8, B:42:0x00c0, B:65:0x00f2, B:68:0x00f9, B:71:0x0100, B:73:0x0112, B:77:0x011c, B:79:0x0122, B:80:0x0125, B:82:0x0138, B:83:0x013e, B:87:0x009c, B:88:0x0089, B:99:0x014b, B:102:0x0157, B:25:0x0078, B:51:0x00ce, B:52:0x00db, B:54:0x00e1), top: B:2:0x0002, inners: #1, #2 }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static a5.l j(a5.f r12, java.io.File r13, java.lang.String r14) {
        /*
            Method dump skipped, instructions count: 406
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: q4.a.j(a5.f, java.io.File, java.lang.String):a5.l");
    }

    public static String k(File file, String str) {
        String format;
        StringBuilder sb = new StringBuilder(a.a.h("<html><head><title>Index of ", str, "</title>"));
        sb.append("<meta name='viewport' content='width=device-width, initial-scale=1.0'><style>body{font-family: sans-serif; background-color: #f0f0f0; color:333;} table{border-collapse: collapse; width: 95%; margin: 20px auto;} td,th{padding: 10px 15px; border: 1px solid #ccc; text-align: left;} th{background-color: #007bff; color: white;} tr:nth-child(even){background-color: #f2f2f2;} a{text-decoration: none; color: #007bff;} a:hover{text-decoration: underline;} .upload-form{width: 95%; margin: 20px auto; padding: 15px; background-color: #fff; border: 1px solid #ccc; border-radius: 5px;}.toast{position: fixed; top: 20px; right: 20px; background-color: #4CAF50; color: white; padding: 16px 24px; border-radius: 8px; box-shadow: 0 4px 12px rgba(0,0,0,0.15); z-index: 1000; animation: slideIn 0.3s ease-out;} @keyframes slideIn{from{transform: translateX(400px); opacity: 0;} to{transform: translateX(0); opacity: 1;}} .toast.fadeOut{animation: fadeOut 0.3s ease-out forwards;} @keyframes fadeOut{from{opacity: 1;} to{opacity: 0;}}</style><script>function encodeFilename(filename) { return encodeURIComponent(filename); }function prepareForm(event, form) {   var fileInput = form.querySelector('input[type=file]');  if (!fileInput || fileInput.files.length === 0) {    event.preventDefault();    alert('请先选择要上传的文件！');    return false;  }  for (var i = 0; i < fileInput.files.length; i++) {    var encoded = encodeURIComponent(fileInput.files[i].name);    var hidden = document.createElement('input');    hidden.type = 'hidden';    hidden.name = 'fn_' + i;    hidden.value = encoded;    form.appendChild(hidden);  }  return true;}function showUploadSuccess() {  var urlParams = new URLSearchParams(window.location.search);  if (urlParams.get('upload') === 'success') {    var count = urlParams.get('count');    var message = '✓ 文件上传成功！';    if (count && count > 1) {      message = '✓ 成功上传 ' + count + ' 个文件！';    }    setTimeout(function() { alert(message); }, 100);    var cleanUrl = window.location.pathname;    window.history.replaceState({}, document.title, cleanUrl);  }}window.onload = showUploadSuccess;</script>");
        sb.append("</head><body><h1>Index of " + str + "</h1>");
        sb.append("<div class='upload-form'><form action='" + str + "' method='post' enctype='multipart/form-data' onsubmit='return prepareForm(event, this)'>");
        sb.append("<h3>上传文件</h3><input type='file' name='uploaded_file' multiple><input type='submit' value='上传'></form></div><table><tr><th>Name</th><th>Size</th><th>Last Modified</th></tr>");
        if (!str.equals("/")) {
            String parent = new File(str).getParent();
            if (parent == null) {
                parent = "/";
            }
            sb.append("<tr><td><a href='" + parent + "'>.. (Parent Directory)</a></td><td>-</td><td>-</td></tr>");
        }
        File[] listFiles = file.listFiles();
        if (listFiles != null) {
            Arrays.sort(listFiles, new m0.a(6));
            for (File file2 : listFiles) {
                String name = file2.getName();
                String replace = URLEncoder.encode(name, "UTF-8").replace("+", "%20");
                String g7 = str.endsWith("/") ? a.a.g(str, replace) : str + "/" + replace;
                if (file2.isDirectory()) {
                    format = "-";
                } else {
                    long length = file2.length();
                    format = length < PlaybackStateCompat.ACTION_PLAY_FROM_MEDIA_ID ? length + " B" : length < PlaybackStateCompat.ACTION_SET_CAPTIONING_ENABLED ? String.format("%.1f KB", Double.valueOf(length / 1024.0d)) : length < 1073741824 ? String.format("%.1f MB", Double.valueOf(length / 1048576.0d)) : String.format("%.1f GB", Double.valueOf(length / 1.073741824E9d));
                }
                sb.append("<tr><td><a href='" + g7 + "'>" + name + "</a></td><td>" + format + "</td><td>" + new SimpleDateFormat("yyyy-MM-dd HH:mm").format(new Date(file2.lastModified())) + "</td></tr>");
            }
        }
        sb.append("</table></body></html>");
        return sb.toString();
    }

    @Override // a5.n
    public final l e(a5.f fVar) {
        String str;
        y3 y3Var = this.f8452m;
        if (y3Var != null) {
            y3Var.f5583a.lambda$startHttpServer$121();
        }
        try {
            String decode = URLDecoder.decode(fVar.f327f, "UTF-8");
            File file = new File(this.f8451l, decode);
            if (a5.g.POST.equals(fVar.f328g)) {
                return j(fVar, file, decode);
            }
            if (!file.exists()) {
                return n.c(k.NOT_FOUND, n.MIME_PLAINTEXT, "404 Not Found");
            }
            if (file.isDirectory()) {
                l c7 = n.c(k.OK, "text/html; charset=utf-8", k(file, decode));
                a5.h hVar = c7.f344e;
                hVar.put("Cache-Control", "no-cache, no-store, must-revalidate");
                hVar.put("Pragma", "no-cache");
                hVar.put("Expires", "0");
                return c7;
            }
            String lowerCase = file.getName().toLowerCase();
            int lastIndexOf = lowerCase.lastIndexOf(46);
            if (lastIndexOf > 0) {
                String substring = lowerCase.substring(lastIndexOf + 1);
                HashMap hashMap = f8450n;
                if (hashMap.containsKey(substring)) {
                    str = (String) hashMap.get(substring);
                    return new l(k.OK, str, new FileInputStream(file), -1L);
                }
            }
            str = "application/octet-stream";
            return new l(k.OK, str, new FileInputStream(file), -1L);
        } catch (Exception e7) {
            e7.printStackTrace();
            return n.c(k.INTERNAL_ERROR, n.MIME_PLAINTEXT, "Internal Server Error: " + e7.getMessage());
        }
    }
}
