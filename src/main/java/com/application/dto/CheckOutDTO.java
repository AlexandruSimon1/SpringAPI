package com.application.dto;

import lombok.Data;

import java.util.Set;

@Data
public class CheckOutDTO {
    private Integer id;
    private String paymentType;
    private Set<OrderDTO> orders;
}
