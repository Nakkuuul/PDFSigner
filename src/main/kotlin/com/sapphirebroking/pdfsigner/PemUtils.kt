package com.sapphirebroking.pdfsigner

import java.io.File
import java.security.KeyFactory
import java.security.PrivateKey
import java.security.cert.CertificateFactory
import java.security.cert.X509Certificate
import java.security.spec.PKCS8EncodedKeySpec
import java.util.*

object PemUtils {
    fun loadPrivateKey(pemFile: File): PrivateKey {
        val content = pemFile.readText()
            .replace("-----BEGIN PRIVATE KEY-----", "")
            .replace("-----END PRIVATE KEY-----", "")
            .replace("\\s+".toRegex(), "")
        val decoded = Base64.getDecoder().decode(content)
        val keySpec = PKCS8EncodedKeySpec(decoded)
        return KeyFactory.getInstance("RSA").generatePrivate(keySpec)
    }

    fun loadCertificate(certFile: File): X509Certificate {
        val factory = CertificateFactory.getInstance("X.509")
        return factory.generateCertificate(certFile.inputStream()) as X509Certificate
    }
}