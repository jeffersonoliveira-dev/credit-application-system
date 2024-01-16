package me.jefferson.creditapplicationsystem.entity

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Enumerated
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.ManyToOne
import jakarta.persistence.Table
import me.jefferson.creditapplicationsystem.enummeration.Status
import java.math.BigDecimal
import java.time.LocalDate
import java.util.UUID

@Entity
@Table(name = "Credit")
data class Credit(
    @Column(nullable = false, unique = true) var creditCode: UUID = UUID.randomUUID(),
    @Column(nullable = false) var creditValue: BigDecimal = BigDecimal.ZERO,
    @Column(nullable = false) var dayFirstInstallment: LocalDate,
    @Column(nullable = false) var numberOfInstallment: Int = 0,
    @Enumerated val status: Status = Status.IN_PROGRESS,
    @ManyToOne var customer: Customer? = null,
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) val id: Long? = null
)
