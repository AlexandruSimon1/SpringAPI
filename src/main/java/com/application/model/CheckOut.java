package com.application.model;


import lombok.Data;
import lombok.RequiredArgsConstructor;

import javax.persistence.Table;
import javax.persistence.*;

@Data
@RequiredArgsConstructor
@Entity
@Table(name = "checkout")

public class CheckOut {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "check_out_id")
    private Integer checkOutId;

    @Column(name = "payment_type")
    private String paymentType;

//    @OneToOne(mappedBy = "checkOut",cascade = CascadeType.ALL)
//    @JoinColumn(name = "order_id")
//    private Order order;
}
