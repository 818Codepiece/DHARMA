package com.DHARMA.userTicketBooking.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Entity
@Data
//@Table(name = "temples")
public class Temple {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    private String name;
    private String description;
    private String speciality;
    private Integer duration;
    private LocalDate closingDate;


    @OneToMany(mappedBy = "temple", fetch = FetchType.LAZY)
    private List<Darshan> darshans;
}
