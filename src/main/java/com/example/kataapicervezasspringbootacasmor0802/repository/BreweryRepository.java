package com.example.kataapicervezasspringbootacasmor0802.repository;

import com.example.kataapicervezasspringbootacasmor0802.model.Brewery;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BreweryRepository extends JpaRepository<Brewery, Long> {
}
