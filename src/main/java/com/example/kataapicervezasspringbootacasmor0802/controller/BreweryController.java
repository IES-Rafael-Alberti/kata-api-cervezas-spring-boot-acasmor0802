package com.example.kataapicervezasspringbootacasmor0802.controller;

import com.example.kataapicervezasspringbootacasmor0802.model.Brewery;
import com.example.kataapicervezasspringbootacasmor0802.repository.BreweryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
public class BreweryController {

    @Autowired
    private BreweryRepository breweryRepository;

    // GET /breweries - Get all breweries
    @GetMapping("/breweries")
    public List<Brewery> getAllBreweries() {
        return breweryRepository.findAll();
    }

    // GET /brewerie/{id} - Get brewery by ID
    @GetMapping("/brewerie/{id}")
    public ResponseEntity<Brewery> getBreweryById(@PathVariable Long id) {
        Optional<Brewery> brewery = breweryRepository.findById(id);
        return brewery.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }
}
