package com.example.plugins

import com.example.routes.customerRouting
import com.example.routes.userRouting
import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.routing.*

fun Application.configureRouting() {
    routing {
        userRouting()
        authenticate("auth-jwt") {
            customerRouting()
        }
    }
}
