package me.jefferson.creditapplicationsystem.controller

import me.jefferson.creditapplicationsystem.dto.CreditDto
import me.jefferson.creditapplicationsystem.dto.CreditView
import me.jefferson.creditapplicationsystem.dto.CreditViewList
import me.jefferson.creditapplicationsystem.entity.Credit
import me.jefferson.creditapplicationsystem.service.impl.CreditService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import java.util.UUID
import java.util.stream.Collectors

@RestController
@RequestMapping("/api/credits")
class CreditResource(
    private val creditService: CreditService
) {
    @PostMapping
    fun saveCredit(creditDto: CreditDto): String {
        val credit: Credit = this.creditService.save(creditDto.toEntity())
        return "Credit ${credit.creditCode} - Customer ${credit.customer?.firstName} saved!"
    }

    @GetMapping("/{id}")
    fun findAllByCustomerId(customerId: Long): List<CreditViewList> {
      return  this.creditService.findAllByCustomer(customerId).stream()
           .map { credit: Credit -> CreditViewList(credit) }.collect(Collectors.toList())
    }

    @GetMapping("/{creditCode}")
    fun findByCreditCode(@RequestParam(value = "customerId") customerId: Long, @PathVariable creditCode: UUID): CreditView {
       val credit: Credit = this.creditService.findByCreditCode(creditCode, customerId)
        return CreditView(credit)
    }

}