package com.yuhan.dvrcar;

import android.util.Base64;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.security.MessageDigest;

/** 握手 / 帧读取 / 极简 HTTP 响应的静态工具。记录仪（18767）与遥控服务共用。 */
final class ServerIo {
    static String readLine(InputStream in) throws Exception {
        ByteArrayOutputStream b = new ByteArrayOutputStream();
        int c;
        while ((c = in.read()) >= 0) {
            if (c == '\n') break;
            if (c != '\r') b.write(c);
            if (b.size() > 8192) throw new Exception("header too long");
        }
        if (c < 0 && b.size() == 0) return null;
        return new String(b.toByteArray(), "ISO-8859-1");
    }

    static void fully(InputStream in, byte[] b) throws Exception {
        int off = 0;
        while (off < b.length) {
            int n = in.read(b, off, b.length - off);
            if (n < 0) throw new Exception("eof");
            off += n;
        }
    }

    static String accept(String key) throws Exception {
        MessageDigest md = MessageDigest.getInstance("SHA-1");
        byte[] digest = md.digest((key + "258EAFA5-E914-47DA-95CA-C5AB0DC85B11").getBytes("ISO-8859-1"));
        return Base64.encodeToString(digest, Base64.NO_WRAP);
    }

    static void http(OutputStream out, int code, String type, byte[] body) throws Exception {
        String head = "HTTP/1.1 " + code + (code == 200 ? " OK" : " Not Found") + "\r\n"
                + "Content-Type: " + type + "\r\n"
                + "Content-Length: " + body.length + "\r\n"
                + "Access-Control-Allow-Origin: *\r\n"
                + "Connection: close\r\n"
                + "Cache-Control: no-store\r\n\r\n";
        out.write(head.getBytes("ISO-8859-1"));
        if (body.length > 0) out.write(body);
        out.flush();
    }

    /** 带 Content-Disposition: attachment 的下载响应，文件名 ASCII 化，避免编码问题。 */
    static void httpDownload(OutputStream out, int code, String type, String filename, byte[] body) throws Exception {
        String head = "HTTP/1.1 " + code + (code == 200 ? " OK" : " Not Found") + "\r\n"
                + "Content-Type: " + type + "\r\n"
                + "Content-Disposition: attachment; filename=\"" + filename + "\"\r\n"
                + "Content-Length: " + body.length + "\r\n"
                + "Access-Control-Allow-Origin: *\r\n"
                + "Connection: close\r\n"
                + "Cache-Control: no-store\r\n\r\n";
        out.write(head.getBytes("ISO-8859-1"));
        if (body.length > 0) out.write(body);
        out.flush();
    }

    private ServerIo() {}
}
