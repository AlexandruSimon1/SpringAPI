package com.application.model;


import com.application.model.enums.CategoryType;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import javax.persistence.Table;
import javax.persistence.*;

@Data
@RequiredArgsConstructor
@Entity
@Table(name = "menu")
public class Menu {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column (name = "product_id")
    private int productId;

    @Enumerated(EnumType.STRING)
    @Column (name = "category")
    private CategoryType categoryType;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "price")
    private int price;

    @ManyToMany
    private Order order;

}