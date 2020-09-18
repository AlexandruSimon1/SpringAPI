package model;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;
import javax.persistence.Table;

@Data
@RequiredArgsConstructor
@Entity
@Table(name = "waiters")
public class Waiter {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "waiter_id")
    private int waiterId;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "date_of_birth")
    private int dateOfBirth;

    @Column(name = "address")
    private String address;

    @Column(name = "phone_number")
    private Long phoneNumber;

    @Column(name = "email")
    private String email;

}
