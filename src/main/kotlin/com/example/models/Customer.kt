package com.example.models

import com.example.serializers.UUIDSerializer
import kotlinx.serialization.Serializable
import org.jetbrains.exposed.sql.*
import java.util.UUID

@Serializable
data class Customer(
    @Serializable(with = UUIDSerializer::class)
    val id: UUID? = null,
    val firstName: String,
    val lastName: String,
    val email: String
)

object Customers : Table() {
    val id = uuid("id")
    val firstName = varchar("first_name", 128)
    val lastName = varchar("last_name", 1024)
    val email = varchar("email", 128)

    override val primaryKey = PrimaryKey(id)
}

