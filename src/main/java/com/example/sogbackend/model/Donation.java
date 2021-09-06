package com.example.sogbackend.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Donation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String donationId;

    @Column(nullable = false)
    private Double price;

    @Column(nullable = false)
    private Date createDate = new Date();

    @ManyToOne
    private Girl girl;

    @ManyToOne
    private Visitor visitor;
}