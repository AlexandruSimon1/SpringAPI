package com.application.model;


import com.application.model.enums.CategoryType;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import javax.persistence.Table;
import javax.persistence.*;

@Data
@RequiredArgsConstructor
@Entity
@Table(name = "menus")
public class Menu {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column (name = "product_id")
    private Integer productId;

    @Enumerated(EnumType.STRING)
    @Column (name = "category")
    private CategoryType categoryType;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "price")
    private Integer price;

    @ManyToOne
    @JoinColumn(name = "order_id")
    private Order order;

}