package com.example.kataapicervezasspringbootacasmor0802.controller;

import com.example.kataapicervezasspringbootacasmor0802.dto.BeerRequest;
import com.example.kataapicervezasspringbootacasmor0802.model.Beer;
import com.example.kataapicervezasspringbootacasmor0802.model.Brewery;
import com.example.kataapicervezasspringbootacasmor0802.model.Category;
import com.example.kataapicervezasspringbootacasmor0802.model.Style;
import com.example.kataapicervezasspringbootacasmor0802.repository.BeerRepository;
import com.example.kataapicervezasspringbootacasmor0802.repository.BreweryRepository;
import com.example.kataapicervezasspringbootacasmor0802.repository.CategoryRepository;
import com.example.kataapicervezasspringbootacasmor0802.repository.StyleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RestController
public class BeerController {

    @Autowired
    private BeerRepository beerRepository;

    @Autowired
    private BreweryRepository breweryRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private StyleRepository styleRepository;

    // GET /beers - Get all beers
    @GetMapping("/beers")
    public List<Beer> getAllBeers() {
        return beerRepository.findAll();
    }

    // GET /beer/{id} - Get beer by ID
    @GetMapping("/beer/{id}")
    public ResponseEntity<Beer> getBeerById(@PathVariable Long id) {
        Optional<Beer> beer = beerRepository.findById(id);
        return beer.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // POST /beer - Create a new beer
    @PostMapping("/beer")
    public ResponseEntity<Beer> createBeer(@RequestBody BeerRequest beerRequest) {
        Brewery brewery = breweryRepository.findById(beerRequest.getBreweryId())
                .orElseThrow(() -> new RuntimeException("Brewery not found with id: " + beerRequest.getBreweryId()));
        Category category = categoryRepository.findById(beerRequest.getCategoryId())
                .orElseThrow(() -> new RuntimeException("Category not found with id: " + beerRequest.getCategoryId()));
        Style style = styleRepository.findById(beerRequest.getStyleId())
                .orElseThrow(() -> new RuntimeException("Style not found with id: " + beerRequest.getStyleId()));

        Beer beer = new Beer();
        beer.setName(beerRequest.getName());
        beer.setBrewery(brewery);
        beer.setCategory(category);
        beer.setStyle(style);
        beer.setAbv(beerRequest.getAbv());
        beer.setIbu(beerRequest.getIbu());
        beer.setSrm(beerRequest.getSrm());
        beer.setUpc(beerRequest.getUpc());
        beer.setFilepath(beerRequest.getFilepath());
        beer.setDescript(beerRequest.getDescript());
        beer.setAddUser(beerRequest.getAddUser());
        beer.setLastMod(LocalDateTime.now());

        Beer savedBeer = beerRepository.save(beer);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedBeer);
    }

    // PUT /beer/{id} - Update a beer (partial or full)
    @PutMapping("/beer/{id}")
    public ResponseEntity<Beer> updateBeer(@PathVariable Long id, @RequestBody BeerRequest beerRequest) {
        Optional<Beer> optionalBeer = beerRepository.findById(id);

        if (optionalBeer.isPresent()) {
            Beer existingBeer = optionalBeer.get();

            if (beerRequest.getName() != null) existingBeer.setName(beerRequest.getName());
            if (beerRequest.getAbv() != null) existingBeer.setAbv(beerRequest.getAbv());
            if (beerRequest.getIbu() != null) existingBeer.setIbu(beerRequest.getIbu());
            if (beerRequest.getSrm() != null) existingBeer.setSrm(beerRequest.getSrm());
            if (beerRequest.getUpc() != null) existingBeer.setUpc(beerRequest.getUpc());
            if (beerRequest.getFilepath() != null) existingBeer.setFilepath(beerRequest.getFilepath());
            if (beerRequest.getDescript() != null) existingBeer.setDescript(beerRequest.getDescript());
            if (beerRequest.getAddUser() != null) existingBeer.setAddUser(beerRequest.getAddUser());

            if (beerRequest.getBreweryId() != null) {
                Brewery brewery = breweryRepository.findById(beerRequest.getBreweryId())
                        .orElseThrow(() -> new RuntimeException("Brewery not found with id: " + beerRequest.getBreweryId()));
                existingBeer.setBrewery(brewery);
            }
            if (beerRequest.getCategoryId() != null) {
                Category category = categoryRepository.findById(beerRequest.getCategoryId())
                        .orElseThrow(() -> new RuntimeException("Category not found with id: " + beerRequest.getCategoryId()));
                existingBeer.setCategory(category);
            }
            if (beerRequest.getStyleId() != null) {
                Style style = styleRepository.findById(beerRequest.getStyleId())
                        .orElseThrow(() -> new RuntimeException("Style not found with id: " + beerRequest.getStyleId()));
                existingBeer.setStyle(style);
            }

            existingBeer.setLastMod(LocalDateTime.now());
            Beer updatedBeer = beerRepository.save(existingBeer);
            return ResponseEntity.ok(updatedBeer);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // DELETE /beer/{id} - Delete a beer
    @DeleteMapping("/beer/{id}")
    public ResponseEntity<Void> deleteBeer(@PathVariable Long id) {
        if (beerRepository.existsById(id)) {
            beerRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}

