package com.application.model;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;

@Data
@RequiredArgsConstructor
@Entity
@javax.persistence.Table(name = "tables")
public class Table {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "table_id")
    private Integer tableId;

    @Column(name = "number")
    private Integer number;

//    @OneToOne(mappedBy = "table",cascade = CascadeType.ALL)
//    @JoinColumn(name = "order_id")
//    private Order order;
}
