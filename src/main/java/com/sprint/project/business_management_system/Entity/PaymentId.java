package com.sprint.project.business_management_system.Entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.*;

import java.io.Serializable;

@Embeddable
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class PaymentId implements Serializable {

    @Column(name = "customerNumber")
    private Integer customerNumber;

    @Column(name = "checkNumber")
    private String checkNumber;
}