package com.application.model;


import lombok.Data;
import lombok.RequiredArgsConstructor;

import javax.persistence.Table;
import javax.persistence.*;

@Data
@RequiredArgsConstructor
@Entity
@Table(name = "check_out")

public class CheckOut {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "check_out_id")
    private int checkOutId;

    @Column(name = "payment_type")
    private String paymentType;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "order_id", referencedColumnName = "order_id")
    private Order order;
}
