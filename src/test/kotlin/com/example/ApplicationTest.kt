package com.example

import com.example.plugins.*
import com.example.serializers.UUIDSerializer
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.http.*
import io.ktor.server.testing.*
import kotlinx.serialization.Serializable
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import java.util.UUID
import kotlin.test.*

class ApplicationTest {
//    @Test
//    fun testRoot() = testApplication {
//        application {
//            configureRouting()
//        }
//        client.get("/").apply {
//            assertEquals(HttpStatusCode.OK, status)
//            assertEquals("Hello World!", bodyAsText())
//        }
//    }

    @Test
    fun testTrue() {
        assertTrue(true)
    }

    @Test
    fun createAnUUID() {
        val uuid = UUID.randomUUID()

        assertNotNull(uuid)
    }

    @Test
    fun convertUUIDToString() {
        val uuid = UUID.fromString("13e74a8b-e60a-499a-ac25-6207c5b8e0fd")

        assertEquals(uuid.toString(), "13e74a8b-e60a-499a-ac25-6207c5b8e0fd")
    }

    @Test
    fun testUUIDSerialization() {

        @Serializable
        class User (
            @Serializable(with = UUIDSerializer::class)
            val id: UUID,
            val name: String
        )

        val randomId = UUID.randomUUID()

        val user = User(
            id = randomId,
            name = "Victor"
        )

        println(Json.encodeToString(user))

        assertEquals("{\"id\":\"$randomId\",\"name\":\"Victor\"}", Json.encodeToString(user))
    }
}
