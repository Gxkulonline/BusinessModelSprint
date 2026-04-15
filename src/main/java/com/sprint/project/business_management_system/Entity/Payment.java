package com.sprint.project.business_management_system.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "payments")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Payment {

	@EmbeddedId
	private PaymentId paymentId;

	@ManyToOne
	@MapsId("customerNumber")
	@JoinColumn(name = "customerNumber", referencedColumnName = "customerNumber")
	private Customer customer;

	@Column(name = "paymentDate", nullable = false)
	private LocalDate paymentDate;

	@Column(name = "amount", nullable = false)
	private BigDecimal amount;

}