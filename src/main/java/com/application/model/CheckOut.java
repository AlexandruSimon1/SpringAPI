package com.application.model;


import lombok.Data;
import lombok.RequiredArgsConstructor;

import javax.persistence.Table;
import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Data
@RequiredArgsConstructor
@Entity
@Table(name = "checkout")
public class CheckOut implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String paymentType;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "orders_id",referencedColumnName = "id")
    private Order order;
}
