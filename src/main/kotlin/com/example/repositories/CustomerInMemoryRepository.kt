package com.example.repositories

import com.example.models.Customer

class CustomerInMemoryRepository {
    private var items: MutableList<Customer> = mutableListOf<Customer>()

    fun add(customer: Customer) {
        this.items.add(customer)
    }

    fun all(): List<Customer> {
        return this.items
    }

    fun isNotEmpty(): Boolean {
        return this.items.isNotEmpty()
    }

    fun items(): MutableList<Customer> { return this.items }
}

val customerStorage = CustomerInMemoryRepository()