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
    @Column(name = "id")
    private Integer id;
    @Column(name = "order_number")
    private Integer orderNumber;
    @Column(name = "quantity")
    private Integer quantity;
    @ManyToMany(mappedBy = "order")
    private Set<CheckOut> checkOut;
    @ManyToMany(mappedBy = "order")
    private Set<Menu> menus = new HashSet<>();
    @ManyToMany(mappedBy = "order")
    private Set<com.application.model.Table> table;

    public void addMenu(Menu menu) {
        if (menus == null) {
            menus = new HashSet<>();
        }
        menus.add(menu);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Order order = (Order) o;
        return Objects.equals(id, order.id) &&
                Objects.equals(orderNumber, order.orderNumber) &&
                Objects.equals(quantity, order.quantity) &&
                Objects.equals(checkOut, order.checkOut) &&
                Objects.equals(menus, order.menus) &&
                Objects.equals(table, order.table);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, orderNumber, quantity, checkOut, menus, table);
    }
}
