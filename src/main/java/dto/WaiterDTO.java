package dto;

import lombok.Data;

import java.time.format.DateTimeFormatter;

@Data
public class WaiterDTO {
    private Integer waiterId;
    private String firstName;
    private String lastName;
    private DateTimeFormatter dateOfBirth;
    private String address;
    private Integer phoneNumber;
    private String email;
}
