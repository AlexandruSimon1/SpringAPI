package com.application.dto;

import lombok.Data;

@Data
public class OrderDTO {
    private Integer orderId;
    private Integer orderNumber;
    private Integer quantity;
    private MenuDTO menuDTO;
    private TableDTO tableDTO;
}
