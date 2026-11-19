package com.navitool.bridge;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;

/**
 * 启动钩子。
 *
 * Android 会在进程启动时自动实例化声明在清单里的 ContentProvider，
 * 不需要任何代码去调用它。用这个特性把桥接服务挂上去，
 * 从而完全不用改车机助手的 Application 或 Activity。
 */
public final class BridgeProvider extends ContentProvider {

    @Override
    public boolean onCreate() {
        Context ctx = getContext();
        Bridge.note("provider onCreate");
        try {
            Bridge.install(ctx);
        } catch (Throwable t) {
            Bridge.note("install failed: " + Bridge.root(t));
        }
        // 先直接起一份（万一服务被系统限制），再拉前台服务把它钉住
        try {
            HttpServer.start(ctx);
        } catch (Throwable t) {
            Bridge.lastError = "server start: " + Bridge.root(t);
            Bridge.note(Bridge.lastError);
        }
        try {
            BridgeService.kick(ctx);
        } catch (Throwable t) {
            Bridge.note("kick failed: " + Bridge.root(t));
        }
        return true;
    }

    @Override
    public Cursor query(Uri uri, String[] projection, String selection,
                        String[] selectionArgs, String sortOrder) {
        return null;
    }

    @Override
    public String getType(Uri uri) {
        return null;
    }

    @Override
    public Uri insert(Uri uri, ContentValues values) {
        return null;
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        return 0;
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
        return 0;
    }
}
