package com.example.repositories

import com.example.database.DatabaseSingleton.dbQuery
import com.example.models.*
import com.example.repositories.ports.CustomerRepository
import kotlinx.coroutines.runBlocking
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq
import java.util.UUID


class CustomerExposedRepository : CustomerRepository {
    private fun resultRowToCustomer(row: ResultRow) = Customer(
        id = row[Customers.id],
        firstName = row[Customers.firstName],
        lastName = row[Customers.lastName],
        email = row[Customers.email]
    )

    override suspend fun allCustomers(): List<Customer> = dbQuery {
        Customers.selectAll().map(::resultRowToCustomer)
    }


    override suspend fun customer(id: UUID): Customer? = dbQuery {
        Customers
            .select { Customers.id eq id }
            .map(::resultRowToCustomer)
            .singleOrNull()
    }

    override suspend fun addNewCustomer(
        firstName: String,
        lastName: String,
        email: String
    ): Customer? = dbQuery {
        val insertStatement = Customers.insert {
            it[id] = UUID.randomUUID()
            it[Customers.firstName] = firstName
            it[Customers.lastName] = lastName
            it[Customers.email] = email
        }
        insertStatement.resultedValues?.singleOrNull()?.let(::resultRowToCustomer)
    }

    override suspend fun editCustomer(
        id: UUID,
        firstName: String,
        lastName: String,
        email: String
    ): Boolean = dbQuery {
        Customers.update({ Customers.id eq id }) {
            it[Customers.firstName] = firstName
            it[Customers.lastName] = lastName
            it[Customers.email] = email
        } > 0
    }

    override suspend fun deleteCustomer(id: UUID): Boolean = dbQuery {
        Customers.deleteWhere { Customers.id eq id } > 0
    }
}

val customerRepository = CustomerExposedRepository()