package com.application.dto;

import lombok.Data;

@Data
public class TableDTO {
    private Integer tableId;
    private Integer number;
    private OrderDTO order;
}
