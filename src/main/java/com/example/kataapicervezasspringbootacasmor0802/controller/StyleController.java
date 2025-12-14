package com.example.kataapicervezasspringbootacasmor0802.controller;

import com.example.kataapicervezasspringbootacasmor0802.model.Style;
import com.example.kataapicervezasspringbootacasmor0802.repository.StyleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
public class StyleController {

    @Autowired
    private StyleRepository styleRepository;

    // GET /styles - Get all styles
    @GetMapping("/styles")
    public List<Style> getAllStyles() {
        return styleRepository.findAll();
    }

    // GET /style/{id} - Get style by ID
    @GetMapping("/style/{id}")
    public ResponseEntity<Style> getStyleById(@PathVariable Long id) {
        Optional<Style> style = styleRepository.findById(id);
        return style.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }
}
