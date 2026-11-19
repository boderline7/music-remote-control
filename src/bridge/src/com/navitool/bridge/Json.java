package com.navitool.bridge;

/** 极简 JSON 取值，够用就行，不引第三方库 */
final class Json {

    private Json() {
    }

    static String pick(String json, String key) {
        if (json == null) {
            return null;
        }
        String needle = "\"" + key + "\"";
        int i = json.indexOf(needle);
        if (i < 0) {
            return null;
        }
        int c = json.indexOf(':', i + needle.length());
        if (c < 0) {
            return null;
        }
        int p = c + 1;
        while (p < json.length() && Character.isWhitespace(json.charAt(p))) {
            p++;
        }
        if (p >= json.length()) {
            return null;
        }
        if (json.charAt(p) == '"') {
            StringBuilder sb = new StringBuilder();
            for (int k = p + 1; k < json.length(); k++) {
                char ch = json.charAt(k);
                if (ch == '\\' && k + 1 < json.length()) {
                    char n = json.charAt(++k);
                    sb.append(n == 'n' ? '\n' : n == 't' ? '\t' : n);
                } else if (ch == '"') {
                    break;
                } else {
                    sb.append(ch);
                }
            }
            return sb.toString();
        }
        int e = p;
        while (e < json.length() && ",}] \n\r\t".indexOf(json.charAt(e)) < 0) {
            e++;
        }
        return json.substring(p, e);
    }
}
