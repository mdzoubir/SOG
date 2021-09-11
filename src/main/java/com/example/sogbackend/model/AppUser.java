package com.example.sogbackend.model;

import lombok.*;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.*;


@Entity
@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Inheritance(strategy = InheritanceType.JOINED)
@Table(	name = "users")
public class AppUser implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String userId;

    @Column(nullable = false)
    @Size(max = 20)
    private String firstName;

    @Column(nullable = false)
    @Size(max = 20)
    private String lastName;

    @Column(nullable = false, unique = true)
    @Email
    private String email;

    @Column(nullable = false)
    @Size(max = 120, min = 8)
    private String password;

    @Column(nullable = false)
    private Boolean emailVerificationStatus = false;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name = "users_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private List<Role> roles= new ArrayList<>();

    @Column(nullable = false)
    private String userPhone;

    @Column(nullable = false)
    private String photo;

    @Column(nullable = true)
    private String address;

    @Column(nullable = false)
    private Date createdAtt = new Date();

}
