package com.sapphirebroking.pdfsigner

import com.itextpdf.kernel.pdf.PdfReader
import com.itextpdf.kernel.pdf.PdfWriter
import com.itextpdf.signatures.*
import java.io.File
import java.io.FileInputStream
import java.io.FileOutputStream
import java.security.PrivateKey
import java.security.cert.X509Certificate

object PdfSignerService {
    fun signPdf(inputPdf: File, outputPdf: File, privateKeyFile: File, certFile: File) {
        val privateKey: PrivateKey = PemUtils.loadPrivateKey(privateKeyFile)
        val certificate: X509Certificate = PemUtils.loadCertificate(certFile)
        val chain = arrayOf(certificate)

        val reader = PdfReader(FileInputStream(inputPdf))
        val signer = PdfSigner(reader, FileOutputStream(outputPdf), PdfSigner.CryptoStandard.CADES)

        val appearance = signer.signatureAppearance
        appearance.reason = "Digitally signed"
        appearance.location = "India"
        appearance.setPageNumber(1)
        appearance.setReuseAppearance(false)
        signer.setFieldName("Signature1")

        val digest = BouncyCastleDigest()
        val signature = PrivateKeySignature(privateKey, DigestAlgorithms.SHA256, "BC")
        signer.signDetached(digest, signature, chain, null, null, null, 0, PdfSigner.CryptoStandard.CADES)
    }
}