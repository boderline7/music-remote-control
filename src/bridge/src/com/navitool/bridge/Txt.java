package com.navitool.bridge;

/** 文本排版小工具：中文按两格算宽度，让诊断/快照里的列能对齐 */
final class Txt {

    private Txt() {
    }

    static int width(String s) {
        if (s == null) {
            return 0;
        }
        int n = 0;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            n += (c > 0x2E80 && c < 0xFF00) ? 2 : 1;
        }
        return n;
    }

    static String pad(String s, int n) {
        StringBuilder sb = new StringBuilder(s == null ? "" : s);
        int l = width(sb.toString());
        while (l < n) {
            sb.append(' ');
            l++;
        }
        return sb.toString();
    }

    static String rule(int n) {
        StringBuilder sb = new StringBuilder(n);
        for (int i = 0; i < n; i++) {
            sb.append('-');
        }
        return sb.toString();
    }
}
