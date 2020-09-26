package com.application.dto;

import lombok.Data;

import java.util.Set;

@Data
public class TableDTO {
    private Integer id;
    private Integer number;
    private Set<OrderDTO> orders;
}
