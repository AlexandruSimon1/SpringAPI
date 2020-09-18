package dto;

import lombok.Data;

@Data
public class AdminDTO {
    private Integer adminId;
    private String firstName;
    private String lastName;
    private int dateOfBirth;
    private String address;
    private Long phoneNumber;
    private String email;
}
