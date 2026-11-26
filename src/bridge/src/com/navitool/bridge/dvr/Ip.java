package com.navitool.bridge.dvr;

import java.net.Inet4Address;
import java.net.NetworkInterface;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

/** 局域网 IPv4，优先 wlan / ap 网卡。设置页与记录仪都用它取本机地址。 */
final class Ip {
    static String local() {
        try {
            Enumeration<NetworkInterface> nis = NetworkInterface.getNetworkInterfaces();
            String best = "";
            while (nis != null && nis.hasMoreElements()) {
                NetworkInterface ni = nis.nextElement();
                if (!ni.isUp() || ni.isLoopback() || ni.isVirtual()) continue;
                Enumeration<java.net.InetAddress> addrs = ni.getInetAddresses();
                while (addrs.hasMoreElements()) {
                    java.net.InetAddress a = addrs.nextElement();
                    if (!(a instanceof Inet4Address) || !a.isSiteLocalAddress()) continue;
                    String ip = a.getHostAddress();
                    if (ip == null) continue;
                    String n = ni.getName().toLowerCase();
                    if (n.startsWith("wlan") || n.startsWith("ap")) return ip;
                    if (best.length() == 0) best = ip;
                }
            }
            return best;
        } catch (Throwable t) { return ""; }
    }

    /** 所有网卡的全部局域网 IPv4：车机可能同时有热点 / 无线 / 有线，全列出来让手机逐个试。 */
    static String[] all() {
        try {
            List<String> ips = new ArrayList<String>();
            Enumeration<NetworkInterface> nis = NetworkInterface.getNetworkInterfaces();
            while (nis != null && nis.hasMoreElements()) {
                NetworkInterface ni = nis.nextElement();
                if (!ni.isUp() || ni.isLoopback() || ni.isVirtual()) continue;
                Enumeration<java.net.InetAddress> addrs = ni.getInetAddresses();
                while (addrs.hasMoreElements()) {
                    java.net.InetAddress a = addrs.nextElement();
                    if (!(a instanceof Inet4Address) || !a.isSiteLocalAddress()) continue;
                    String ip = a.getHostAddress();
                    if (ip != null && !ips.contains(ip)) ips.add(ip);
                }
            }
            return ips.toArray(new String[0]);
        } catch (Throwable t) { return new String[0]; }
    }

    private Ip() {}
}
