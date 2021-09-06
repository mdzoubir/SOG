package com.example.sogbackend.model;

import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Collection;
import java.util.Date;


@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "girls")
public class Girl extends AppUser {

    @Column(nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date birthDay;

    @Column(nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createDate = new Date();

    @Column(nullable = false)
    private String description;

    @Column(nullable = false)
    private String region;

    @Column(nullable = false)
    private Double target;

    @OneToMany(mappedBy = "girl", cascade = CascadeType.REMOVE)
    private Collection<Donation> donations;

    @ManyToOne
    private Ambassador ambassador;

}
