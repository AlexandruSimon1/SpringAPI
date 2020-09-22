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
    private int orderId;
    @Column(name = "order_number")
    private int orderNumber;
    @Column(name = "quantity")
    private int quantity;

    @OneToOne(mappedBy = "order")
    private CheckOut checkOut;

    @ManyToMany(mappedBy = "order", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id", referencedColumnName = "product_id")
    private Set<Menu> menus = new HashSet<>();

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "table_id", referencedColumnName = "table_id")
    private com.application.model.Table table;

    public void addMenu(Menu menu) {
        if (menus == null) {
            menus = new HashSet<>();
        }
        menus.add(menu);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Order)) return false;
        Order order = (Order) o;
        return getOrderId() == order.getOrderId() &&
                getQuantity() == order.getQuantity() &&
                getCheckOut().equals(order.getCheckOut()) &&
                getMenus().equals(order.getMenus()) &&
                getTable().equals(order.getTable());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getOrderId(), getQuantity(), getCheckOut(), getMenus(), getTable());
    }
}
