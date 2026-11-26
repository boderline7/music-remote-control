package com.navi.tool.service;

import android.security.keystore.KeyGenParameterSpec;
import android.util.Base64;
import com.navi.tool.utils.NativeBridge;
import java.security.Key;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.KeyStore;
import java.security.PrivateKey;
import java.security.Signature;
import java.security.cert.Certificate;
import javax.crypto.Cipher;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final class ModuleCrypto {
    private static final String ALIAS = "navi_module_key";
    private static final String KEYSTORE = "AndroidKeyStore";
    private static final Object LOCK = new Object();
    private static final String TAG = "ModuleCrypto";

    private ModuleCrypto() {
    }

    public static byte[] decryptDex(byte[] bArr, byte[] bArr2, byte[] bArr3, byte[] bArr4) {
        byte[] decryptModule;
        if (!NativeBridge.checkRuntimeSafe()) {
            throw new Exception("runtime unsafe");
        }
        synchronized (LOCK) {
            decryptModule = NativeBridge.decryptModule(bArr, bArr2, bArr3, bArr4);
        }
        if (decryptModule == null || decryptModule.length == 0) {
            throw new Exception("module decrypt failed");
        }
        return decryptModule;
    }

    private static KeyPair getOrCreateKeyPair() {
        Certificate certificate;
        Key key;
        synchronized (LOCK) {
            try {
                KeyStore keyStore = KeyStore.getInstance(KEYSTORE);
                keyStore.load(null);
                if (keyStore.containsAlias(ALIAS)) {
                    Exception e7 = null;
                    int i7 = 0;
                    for (int i8 = 0; i8 < 5; i8++) {
                        try {
                            certificate = keyStore.getCertificate(ALIAS);
                            key = keyStore.getKey(ALIAS, null);
                        } catch (Exception e8) {
                            e7 = e8;
                        }
                        if (certificate != null && (key instanceof PrivateKey)) {
                            return new KeyPair(certificate.getPublicKey(), (PrivateKey) key);
                        }
                        i7++;
                        Thread.sleep(300L);
                    }
                    if (i7 < 5) {
                        throw new Exception("keystore key temporarily unavailable", e7);
                    }
                    try {
                        keyStore.deleteEntry(ALIAS);
                    } catch (Exception unused) {
                    }
                }
                KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA", KEYSTORE);
                keyPairGenerator.initialize(new KeyGenParameterSpec.Builder(ALIAS, 7).setKeySize(2048).setEncryptionPaddings("OAEPPadding").setSignaturePaddings("PKCS1").setDigests("SHA-1", "SHA-256").build());
                return keyPairGenerator.generateKeyPair();
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    public static String getPublicKeyBase64() {
        String encodeToString;
        synchronized (LOCK) {
            try {
                KeyPair orCreateKeyPair = getOrCreateKeyPair();
                if (!selfCheck(orCreateKeyPair)) {
                    for (int i7 = 0; i7 < 3; i7++) {
                        Thread.sleep(300L);
                        orCreateKeyPair = getOrCreateKeyPair();
                        if (selfCheck(orCreateKeyPair)) {
                            break;
                        }
                    }
                    if (!selfCheck(orCreateKeyPair)) {
                        orCreateKeyPair = recreateKeyPair();
                        if (!selfCheck(orCreateKeyPair)) {
                            throw new Exception("Keystore key incompatible");
                        }
                    }
                }
                encodeToString = Base64.encodeToString(orCreateKeyPair.getPublic().getEncoded(), 2);
            } catch (Throwable th) {
                throw th;
            }
        }
        return encodeToString;
    }

    private static KeyPair recreateKeyPair() {
        KeyStore keyStore = KeyStore.getInstance(KEYSTORE);
        keyStore.load(null);
        if (keyStore.containsAlias(ALIAS)) {
            try {
                keyStore.deleteEntry(ALIAS);
            } catch (Exception unused) {
            }
        }
        return getOrCreateKeyPair();
    }

    private static boolean selfCheck(KeyPair keyPair) {
        return supportsOaepSha1(keyPair) && supportsSign(keyPair);
    }

    private static boolean supportsOaepSha1(KeyPair keyPair) {
        try {
            Cipher cipher = Cipher.getInstance("RSA/ECB/OAEPWithSHA-1AndMGF1Padding");
            cipher.init(1, keyPair.getPublic());
            byte[] doFinal = cipher.doFinal(new byte[]{1, 2, 3, 4});
            Cipher cipher2 = Cipher.getInstance("RSA/ECB/OAEPWithSHA-1AndMGF1Padding");
            cipher2.init(2, keyPair.getPrivate());
            return cipher2.doFinal(doFinal).length == 4;
        } catch (Exception unused) {
            return false;
        }
    }

    private static boolean supportsSign(KeyPair keyPair) {
        try {
            Signature signature = Signature.getInstance("SHA256withRSA");
            signature.initSign(keyPair.getPrivate());
            signature.update(new byte[]{1, 2, 3, 4});
            signature.sign();
            return true;
        } catch (Exception unused) {
            return false;
        }
    }
}
