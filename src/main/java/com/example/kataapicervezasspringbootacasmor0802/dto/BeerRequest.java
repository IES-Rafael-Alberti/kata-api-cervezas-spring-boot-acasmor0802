package com.example.kataapicervezasspringbootacasmor0802.dto;

import lombok.Data;

@Data
public class BeerRequest {
    private String name;
    private Long breweryId;
    private Long categoryId;
    private Long styleId;
    private Float abv;
    private Float ibu;
    private Float srm;
    private Long upc;
    private String filepath;
    private String descript;
    private Integer addUser;
}
