package com.example.sogbackend.responce;

import com.example.sogbackend.model.Role;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Data
@NoArgsConstructor
public class UserResponse implements Serializable {
    private String userId;
    private String userName;
    private Boolean emailVerificationStatus;
    private Set<Role> roles = new HashSet<>();
}

