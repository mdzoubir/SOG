package com.example.sogbackend.model;

import lombok.*;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.Collection;
import java.util.List;

@Entity
@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "admin")
public class Admin extends AppUser{
    @OneToMany(mappedBy = "createdBy", cascade = CascadeType.REMOVE)
    private Collection<Stories> stories;
}
