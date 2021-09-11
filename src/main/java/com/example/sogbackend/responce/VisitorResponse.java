package com.example.sogbackend.responce;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Date;

@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class VisitorResponse {
    private String firstName;
    private String lastName;
    private String email;
    private String userPhone;
    private String address;
    private String role;
    private Date createdAtt;
}
