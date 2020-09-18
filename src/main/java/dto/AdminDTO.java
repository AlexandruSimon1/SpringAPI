package dto;

import lombok.Data;

import java.time.format.DateTimeFormatter;

@Data
public class AdminDTO {
    private Integer adminId;
    private String firstName;
    private String lastName;
    private DateTimeFormatter dateOfBirth;
    private String address;
    private Integer phoneNumber;
    private String email;
}
