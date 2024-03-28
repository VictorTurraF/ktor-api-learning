package com.example.repositories.ports

import com.example.models.Customer
import java.util.UUID

interface CustomerRepository {
    suspend fun allCustomers(): List<Customer>
    suspend fun customer(id: UUID): Customer?
    suspend fun addNewCustomer(firstName: String, lastName: String, email: String): Customer?
    suspend fun editCustomer(id: UUID, firstName: String, lastName: String, email: String): Boolean
    suspend fun deleteCustomer(id: UUID): Boolean
}