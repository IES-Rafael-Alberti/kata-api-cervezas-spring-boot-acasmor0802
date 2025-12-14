package com.example.kataapicervezasspringbootacasmor0802.repository;

import com.example.kataapicervezasspringbootacasmor0802.model.Style;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StyleRepository extends JpaRepository<Style, Long> {
}
