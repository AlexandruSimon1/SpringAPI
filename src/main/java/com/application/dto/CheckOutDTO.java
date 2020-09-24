package com.application.dto;

import lombok.Data;

@Data
public class CheckOutDTO {
    private Integer checkOutId;
    private String paymentType;
    private OrderDTO orderDTO;
}
