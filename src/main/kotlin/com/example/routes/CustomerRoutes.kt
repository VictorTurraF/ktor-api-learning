package com.example.routes

import com.example.models.Customer
import com.example.repositories.customerRepository
import com.example.repositories.customerStorage
import io.ktor.server.routing.*
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.response.*
import io.ktor.server.request.*
import java.util.*

fun Route.customerRouting() {
    route("/customer") {
        get {
            val customers = customerRepository.allCustomers()

            if (customers.isNotEmpty()) {
                call.respond(customers)
            } else {
                call.respondText("No customers found", status = HttpStatusCode.OK)
            }
        }
        get("{id?}") {
            val id = call.parameters["id"] ?: return@get call.respondText(
                "Missing id",
                status = HttpStatusCode.BadRequest
            )

            val customer =
                customerRepository.customer(UUID.fromString(id)) ?: return@get call.respondText(
                    "No customer with id $id",
                    status = HttpStatusCode.NotFound
                )

            call.respond(customer)
        }
        post {
            val customer = call.receive<Customer>()

            customerRepository.addNewCustomer(
                firstName = customer.firstName,
                lastName = customer.lastName,
                email = customer.email
            )

            call.respondText("Customer stored correctly", status = HttpStatusCode.Created)
        }
        delete("{id?}") {
            val id = call.parameters["id"] ?: return@delete call.respond(HttpStatusCode.BadRequest)

            if (customerRepository.deleteCustomer(UUID.fromString(id))) {
                call.respondText("Customer removed correctly", status = HttpStatusCode.Accepted)
            } else {
                call.respondText("Not Found Customer with id = $id", status = HttpStatusCode.NotFound)
            }
        }
    }
}
