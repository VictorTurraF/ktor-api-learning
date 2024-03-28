package com.example.plugins

import com.example.database.DatabaseSingleton
import io.ktor.server.application.*

fun Application.configureDatabases() {
    DatabaseSingleton.init()
}


