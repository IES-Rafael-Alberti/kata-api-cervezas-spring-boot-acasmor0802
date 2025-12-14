package com.example.kataapicervezasspringbootacasmor0802.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "styles")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Style {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "cat_id") // This is the foreign key in the styles table
    private Category category;

    @Column(name = "style_name")
    private String styleName;

    @Column(name = "last_mod")
    private LocalDateTime lastMod;
}
