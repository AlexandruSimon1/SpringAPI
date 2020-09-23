package com.application.model;


import lombok.Data;
import lombok.RequiredArgsConstructor;

import javax.persistence.Table;
import javax.persistence.*;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;


@Data
@RequiredArgsConstructor
@Entity
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_id")
    private Integer orderId;
    @Column(name = "order_number")
    private Integer orderNumber;
    @Column(name = "quantity")
    private Integer quantity;

//    @OneToOne(mappedBy = "order")
//    @JoinColumn(name = "order_id")
//    private CheckOut checkOut;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<Menu> menus = new HashSet<>();

//    @OneToOne(mappedBy = "order")
//    @JoinColumn(name = "table_id", referencedColumnName = "table_id")
//    private com.application.model.Table table;

    public void addMenu(Menu menu) {
        if (menus == null) {
            menus = new HashSet<>();
        }
        menus.add(menu);
    }

}
