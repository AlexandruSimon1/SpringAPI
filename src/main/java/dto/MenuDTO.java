package dto;

import lombok.Data;
import model.enums.CategoryType;

@Data
public class MenuDTO {
    private Integer productId;
    private CategoryType categoryType;
    private String name;
    private String description;
    private int price;
}
