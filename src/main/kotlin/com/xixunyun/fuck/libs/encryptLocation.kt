package com.xixunyun.fuck.libs

import com.xixunyun.fuck.GetResources
import org.bouncycastle.asn1.x509.SubjectPublicKeyInfo
import org.bouncycastle.openssl.PEMParser
import org.bouncycastle.openssl.jcajce.JcaPEMKeyConverter
import java.security.PublicKey
import java.security.interfaces.RSAPublicKey
import java.util.*
import javax.crypto.Cipher

class EncryptLocation {
    // 加密经纬度
    fun encodeLocation(longitude: String, latitude: String): Pair<String, String> {
        val key = GetResources().getResourcesByFileName("public_key.pem")?.let { String(it) }
            ?: throw IllegalArgumentException("公钥文件 public_key.pem 未找到")

        val publicKey = parsePublicKey(key)

        val encryptedLongitude = Base64.getEncoder().encodeToString(encryptData(publicKey, longitude))
        val encryptedLatitude = Base64.getEncoder().encodeToString(encryptData(publicKey, latitude))

        return Pair(encryptedLongitude, encryptedLatitude)
    }

    private fun parsePublicKey(key: String): PublicKey {
        return key.reader().use { reader ->
            PEMParser(reader).use { pemParser ->
                val pemObject = pemParser.readObject() ?:
                throw IllegalArgumentException("无法读取PEM文件内容")

                val converter = JcaPEMKeyConverter()
                when (pemObject) {
                    is SubjectPublicKeyInfo -> converter.getPublicKey(pemObject)
                    else -> throw IllegalArgumentException("不支持的PEM对象类型: ${pemObject::class.java}")
                }
            }
        }
    }

    private fun encryptData(publicKey: PublicKey, data: String): ByteArray {
        if (publicKey !is RSAPublicKey) {
            throw IllegalArgumentException("只支持RSA公钥加密")
        }

        val cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding").apply {
            init(Cipher.ENCRYPT_MODE, publicKey)
        }

        val dataBytes = data.toByteArray(Charsets.UTF_8)
        val maxLength = publicKey.modulus.bitLength() / 8 - 11

        if (dataBytes.size > maxLength) {
            throw IllegalArgumentException("数据长度(${dataBytes.size} bytes)超过RSA加密最大限制($maxLength bytes)")
        }

        return cipher.doFinal(dataBytes)
    }
}