package com.cloudmusic.car.api

import java.security.MessageDigest
import javax.crypto.Cipher
import javax.crypto.spec.SecretKeySpec

/**
 * 网易云音乐请求加密（eapi），与 Kumone 现行 `RequestCrypto.swift` 一致。
 *
 * eapi：对 "url + payload + md5" 串做 AES-128-ECB（PKCS7），输出大写 hex。
 *
 * 上游 Kumone 自 2026-10-28 起已全量转 eapi 并删除 weapi，这里同步跟随：
 * 旧的 weapi（双 AES-CBC + 预置 RSA 密文）已移除，不再保留回退路径。
 */
object NeteaseCrypto {
    private const val EAPI_KEY = "e82ckenh8dichen8"

    /** @param apiPath 内部接口路径，例如 "/api/song/enhance/player/url/v1" */
    fun eapi(apiPath: String, json: String): Map<String, String> {
        val digest = md5Hex("nobody${apiPath}use${json}md5forencrypt")
        val data = "$apiPath-36cd479b6b5-$json-36cd479b6b5-$digest"
        val keySpec = SecretKeySpec(EAPI_KEY.toByteArray(), "AES")
        val cipher = Cipher.getInstance("AES/ECB/PKCS5Padding").apply { init(Cipher.ENCRYPT_MODE, keySpec) }
        val encrypted = cipher.doFinal(data.toByteArray())
        return mapOf("params" to encrypted.joinToString("") { "%02X".format(it) })
    }

    private fun md5Hex(text: String): String =
        MessageDigest.getInstance("MD5").digest(text.toByteArray())
            .joinToString("") { "%02x".format(it) }
}
