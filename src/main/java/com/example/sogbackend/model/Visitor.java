package com.example.sogbackend.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Table(	name = "visitor")
public class Visitor extends AppUser {

    @OneToMany(mappedBy = "visitor", cascade = CascadeType.REMOVE)
    private Collection<Donation> donations;

}
