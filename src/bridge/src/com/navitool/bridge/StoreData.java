package com.navitool.bridge;

import android.content.Context;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;

/**
 * 商店清单离线版：读 assets/store.json 直接返回 JSON 字符串。
 *
 * 原厂 fetchStoreConfigs() 是先问 native 要商城地址（getStoreUrl），再拿这个地址去要
 * 真正的 JSON 地址，两次网络跳转。去 so 后 getStoreUrl 被桩成空串，商店页只剩
 * 「商城地址不可用」。这里把第二步的结果直接放进包里：清单本身不进网络，
 * 里面每条的 download_url 仍指向 123 网盘 CDN，点「下载并解压」还是走原厂下载器。
 *
 * 清单来源：analysis/0919configs_商店配置清单.json（2026-09-19 抓的原厂商店数据）。
 */
public final class StoreData {

    private StoreData() {
    }

    public static String json(Context c) {
        if (c == null) {
            return "[]";
        }
        InputStream in = null;
        try {
            in = c.getAssets().open("store.json");
            ByteArrayOutputStream bos = new ByteArrayOutputStream(8192);
            byte[] buf = new byte[4096];
            int n;
            while ((n = in.read(buf)) > 0) {
                bos.write(buf, 0, n);
            }
            return new String(bos.toByteArray(), "UTF-8");
        } catch (Throwable t) {
            Bridge.note("store: assets/store.json 读取失败 " + Bridge.root(t));
            return "[]";
        } finally {
            if (in != null) {
                try {
                    in.close();
                } catch (Throwable ignored) {
                }
            }
        }
    }
}
