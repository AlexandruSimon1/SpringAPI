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
    private Integer id;
    private Integer orderNumber;
    @OneToOne( mappedBy = "order")
    private CheckOut checkOut;
    @OneToMany(mappedBy = "order",cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    private List<Menu> menus = new ArrayList<>();
    @OneToOne(mappedBy = "order")
    private com.application.model.Table table;

    public void addMenu(Menu menu) {
        if (menus == null) {
            menus = new ArrayList<>();
        }
        menus.add(menu);
    }
}
