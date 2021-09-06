package com.example.sogbackend.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Collection;


@Entity
@Getter
@Setter
@NoArgsConstructor
@PrimaryKeyJoinColumn(name = "userId")
public class Ambassador extends AppUser {

    @Column(nullable = false)
    private String region;

    @OneToMany(mappedBy = "ambassador", cascade = CascadeType.REMOVE)
    private Collection<Girl> Girls;
}
