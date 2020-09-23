package com.application.dto;

import com.application.model.enums.CategoryType;
import lombok.Data;

@Data
public class MenuDTO {
    private Integer productId;
    private CategoryType categoryType;
    private String name;
    private String description;
    private Integer price;
    private OrderDTO orderDTO;
}
