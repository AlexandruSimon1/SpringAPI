package com.application.model;


import lombok.Data;
import lombok.RequiredArgsConstructor;

import javax.persistence.Table;
import javax.persistence.*;
import java.io.Serializable;
import java.util.*;


@Data
@RequiredArgsConstructor
@Entity
@Table(name = "orders")
public class Order implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_id")
    private Integer orderId;
    @Column(name = "order_number")
    private Integer orderNumber;
    @Column(name = "quantity")
    private Integer quantity;

    @OneToOne(mappedBy = "order")
    private CheckOut checkOut;

    @ManyToMany
    @JoinTable(name = "order_products",joinColumns = {@JoinColumn(name = "order_id")},
    inverseJoinColumns = {@JoinColumn(name = "product_id")})
    private List<Menu> menus = new ArrayList<>();

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "table_id", referencedColumnName = "table_id")
    private com.application.model.Table table;

    public void addMenu(Menu menu) {
        if (menus == null) {
            menus = new ArrayList<>();
        }
        menus.add(menu);
    }

}
