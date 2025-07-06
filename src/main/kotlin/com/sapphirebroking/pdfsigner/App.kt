package com.sapphirebroking.pdfsigner

import io.ktor.server.engine.*
import io.ktor.server.netty.*
import io.ktor.server.application.*
import io.ktor.server.plugins.contentnegotiation.*
import io.ktor.serialization.jackson.*
import io.ktor.server.response.*
import io.ktor.server.request.*
import io.ktor.server.routing.*

fun main() {
    embeddedServer(Netty, port = 8080) {
        module()
    }.start(wait = true)
}

fun Application.module() {
    install(ContentNegotiation) {
        jackson()
    }

    routing {
        get("/") {
            call.respondText("PDF Signer API is running!")
        }

        post("/sign") {
            val multipart = call.receiveMultipart()
            var uploadedFile: File? = null

            multipart.forEachPart { part ->
                if (part is PartData.FileItem && part.name == "pdf") {
                    val ext = File(part.originalFileName ?: "temp.pdf").extension
                    val file = File("uploads/input.pdf")
                    part.streamProvider().use { input -> file.outputStream().buffered().use { input.copyTo(it) } }
                    uploadedFile = file
                }
                part.dispose()
            }

            if (uploadedFile == null) {
                call.respondText("PDF file is missing", status = HttpStatusCode.BadRequest)
                return@post
            }

            val signedFile = File("uploads/signed.pdf")
            PdfSignerService.signPdf(
                inputPdf = uploadedFile!!,
                outputPdf = signedFile,
                privateKeyFile = File("certs/private_key.pem"),
                certFile = File("certs/certificate.pem")
            )

            call.response.header("Content-Disposition", "attachment; filename=\"signed.pdf\"")
            call.respondFile(signedFile)
        }
    }
}
