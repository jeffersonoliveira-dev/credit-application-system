package me.jefferson.creditapplicationsystem.service

import me.jefferson.creditapplicationsystem.entity.Credit
import me.jefferson.creditapplicationsystem.entity.Customer
import java.util.UUID

interface ICustomerService {

    fun save(customer: Customer): Customer

    fun findById(id: Long): Customer

    fun delete(id: Long): Customer
}