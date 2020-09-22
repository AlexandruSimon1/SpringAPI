package com.application.dto;

import lombok.Data;

@Data
public class OrderDTO {
    private Integer orderId;
    private int orderNumber;
    private int quantity;
    private MenuDTO menuDTO;
    private TableDTO tableDTO;
}
