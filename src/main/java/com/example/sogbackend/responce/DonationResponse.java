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
public class DonationResponse {
    private String donationId;
    private Double price;
    private Date createDate;
    private String girlName;
    private String ambassadorEmail;
    private Double target;
}
