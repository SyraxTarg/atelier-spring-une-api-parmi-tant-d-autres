package com.atelier.spring.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;
import java.util.List;


@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "clients")
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String uid;

    private String first_name;

    private String last_name;

    private String gender;

    private String phone_number;

    private String email;

    private String username;

    @Temporal(TemporalType.DATE)
    private Date date_of_birth;

    @ManyToOne
    Address address;


}