package com.example.models
import kotlinx.serialization.Serializable

@Serializable
class User (
    val username: String,
    val password: String
)