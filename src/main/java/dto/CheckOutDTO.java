package dto;

import lombok.Data;

@Data
public class CheckOutDTO {
    private Integer checkOutId;
    private OrderDTO orderDTO;
    private String paymentType;
}
