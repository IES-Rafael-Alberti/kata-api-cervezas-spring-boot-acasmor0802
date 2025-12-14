package com.example.kataapicervezasspringbootacasmor0802.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "beers")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Beer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "brewery_id")
    private Brewery brewery;

    private String name;

    @ManyToOne
    @JoinColumn(name = "cat_id")
    private Category category;

    @ManyToOne
    @JoinColumn(name = "style_id")
    private Style style;

    private Float abv;
    private Float ibu;
    private Float srm;
    private Long upc; // Changed from Integer to Long as UPC can be a larger number

    private String filepath;

    @Column(columnDefinition = "TEXT") // For text type in MySQL
    private String descript;

    @Column(name = "add_user")
    private Integer addUser;

    @Column(name = "last_mod")
    private LocalDateTime lastMod;
}
