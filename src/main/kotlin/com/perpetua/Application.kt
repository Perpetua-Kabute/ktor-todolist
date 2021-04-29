package com.perpetua


import com.perpetua.plugins.configureRouting
import io.ktor.application.*
import io.ktor.response.*
import io.ktor.routing.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*

fun main() {
    embeddedServer(Netty, port = 8080, host = "localhost") {
        configureRouting()

    }.start(wait = true)

}




