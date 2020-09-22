package com.application.dto;

import lombok.Data;

@Data
public class WaiterDTO {
    private Integer waiterId;
    private String firstName;
    private String lastName;
    private int dateOfBirth;
    private String address;
    private Long phoneNumber;
    private String email;
}
