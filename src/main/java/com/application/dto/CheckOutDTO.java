package com.application.dto;

import lombok.Data;

import java.util.Set;

@Data
public class CheckOutDTO {
    private Integer id;
    private String paymentType;
    transient private Set<OrderDTO> orderDTO;
}
