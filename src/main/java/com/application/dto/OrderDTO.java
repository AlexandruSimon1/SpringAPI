package com.application.dto;

import lombok.Data;

import java.util.Set;

@Data
public class OrderDTO {
    private Integer id;
    private Integer orderNumber;
    private Integer quantity;
    transient private Set<MenuDTO> menuDTO;
    transient private Set<TableDTO> tableDTO;
    transient private Set<CheckOutDTO> checkOutDTO;
}
