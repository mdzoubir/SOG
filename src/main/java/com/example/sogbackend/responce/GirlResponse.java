package com.example.sogbackend.responce;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class GirlResponse {
    private String userId;
    private String description;
    private String fullName;
    private String region;
    private Double target;
    private Double raised;
}
