package model;


import lombok.Data;
import lombok.RequiredArgsConstructor;
import model.enums.CategoryType;

import javax.persistence.*;
import javax.persistence.Table;

@Data
@RequiredArgsConstructor
@Entity
@Table(name = "menu")
public class Menu {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column (name = "product_id")
    private int menuId;

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