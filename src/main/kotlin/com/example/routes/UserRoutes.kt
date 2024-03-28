package com.example.routes

import com.auth0.jwt.JWT
import com.auth0.jwt.algorithms.Algorithm
import com.example.models.User
import io.ktor.server.routing.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.application.*
import java.util.Date



fun Route.userRouting() {


    post("/login") {
        fun env(configPath: String): String {
            return call.application.environment.config.property(configPath).getString()
        }

        val user = call.receive<User>()
        val secret = env("jwt.secret")
        val issuer = env("jwt.issuer")
        val audience = env("jwt.audience")

        val token = JWT.create()
            .withAudience(audience)
            .withIssuer(issuer)
            .withClaim("username", user.username)
                .withExpiresAt(Date(System.currentTimeMillis() + 3600000))
            .sign(Algorithm.HMAC256(secret))

        call.respond(hashMapOf("token" to token))
    }
}