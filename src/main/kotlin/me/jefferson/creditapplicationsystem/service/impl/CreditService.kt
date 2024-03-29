package me.jefferson.creditapplicationsystem.service.impl

import me.jefferson.creditapplicationsystem.entity.Credit
import me.jefferson.creditapplicationsystem.repository.CreditRepository
import me.jefferson.creditapplicationsystem.service.ICreditService
import org.springframework.stereotype.Service
import java.util.*

@Service
class CreditService(
    private val creditRepository: CreditRepository,
    private val customerService: CustomerService
): ICreditService {
    override fun save(credit: Credit): Credit {
        credit.apply {
            customer = customerService.findById(credit.customer?.id!!)
        }
        return this.creditRepository.save(credit)
    }

    override fun findAllByCustomer(customerId: Long): List<Credit> =
        this.creditRepository.findAllByCustomerId(customerId)

    override fun findByCreditCode(creditCode: UUID, customerId: Long): Credit {
       val credit: Credit =  this.creditRepository.findByCreditCode(creditCode)
            ?: throw RuntimeException("CreditCode $creditCode not found")
       return if(credit.customer?.id == customerId) credit else throw RuntimeException("Contact Admin")
    }

}