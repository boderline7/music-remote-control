package com.carremote.client

import android.content.Context
import android.content.SharedPreferences
import java.util.UUID

/**
 * 本地凭证。
 *
 * Token 存在普通 SharedPreferences(MODE_PRIVATE)：root 设备上可被读取。
 * 第一版接受这个局限（没有 TLS 也谈不上真正安全），不用"已加密"粉饰。
 */
object Prefs {
    private const val FILE = "remote"
    private lateinit var sp: SharedPreferences

    fun init(context: Context) {
        sp = context.applicationContext.getSharedPreferences(FILE, Context.MODE_PRIVATE)
    }

    var token: String?
        get() = sp.getString("token", null)
        set(value) = sp.edit().putString("token", value).apply()

    /** 设备标识：首次生成后固定，用于车机侧区分设备。 */
    val deviceId: String
        get() {
            val existing = sp.getString("deviceId", null)
            if (existing != null) return existing
            val created = UUID.randomUUID().toString().replace("-", "").take(12)
            sp.edit().putString("deviceId", created).apply()
            return created
        }

    var lastHost: String
        get() = sp.getString("host", "").orEmpty()
        set(value) = sp.edit().putString("host", value).apply()

    var lastPort: Int
        get() = sp.getInt("port", Protocol.PORT)
        set(value) = sp.edit().putInt("port", value).apply()

    var lastName: String
        get() = sp.getString("carName", "").orEmpty()
        set(value) = sp.edit().putString("carName", value).apply()

    fun clearToken() {
        sp.edit().remove("token").apply()
    }
}
