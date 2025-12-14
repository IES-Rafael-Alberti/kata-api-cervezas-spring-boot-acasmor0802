package com.example.kataapicervezasspringbootacasmor0802.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "breweries")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Brewery {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String address1;
    private String address2;
    private String city;
    private String state;
    private String code; // Renamed from 'zipCode' to match column name 'code' more closely
    private String country;
    private String phone;
    private String website;
    private String filepath; // Changed from 'filePath' to match column name 'filepath'
    private String descript; // Changed from 'description' to match column name 'descript'

    @Column(name = "add_user")
    private Integer addUser;

    @Column(name = "last_mod")
    private LocalDateTime lastMod;
}
