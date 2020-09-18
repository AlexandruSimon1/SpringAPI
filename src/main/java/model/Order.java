package model;


import lombok.*;

import javax.persistence.*;
import javax.persistence.Table;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Data
@RequiredArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_id")
    private int orderId;

    @Column(name = "quantity")
    private int quantity;

    @Column(name = "menuId")
    private int menuId;

    @OneToOne(mappedBy = "order")
    private CheckOut checkOut;

    @ManyToMany(mappedBy = "order", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id", referencedColumnName = "product_id")
    private Set<Menu> menus = new HashSet<>();

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "table_id", referencedColumnName = "table_id")
    private model.Table table;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Order)) return false;
        Order order = (Order) o;
        return getOrderId() == order.getOrderId() &&
                getQuantity() == order.getQuantity() &&
                getMenuId() == order.getMenuId() &&
                getCheckOut().equals(order.getCheckOut()) &&
                getMenus().equals(order.getMenus()) &&
                getTable().equals(order.getTable());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getOrderId(), getQuantity(), getMenuId(), getCheckOut(), getMenus(), getTable());
    }
}
