package com.example.kataapicervezasspringbootacasmor0802.controller;

import com.example.kataapicervezasspringbootacasmor0802.model.Beer;
import com.example.kataapicervezasspringbootacasmor0802.model.Brewery;
import com.example.kataapicervezasspringbootacasmor0802.model.Category;
import com.example.kataapicervezasspringbootacasmor0802.model.Style;
import com.example.kataapicervezasspringbootacasmor0802.repository.BeerRepository;
import com.example.kataapicervezasspringbootacasmor0802.repository.BreweryRepository;
import com.example.kataapicervezasspringbootacasmor0802.repository.CategoryRepository;
import com.example.kataapicervezasspringbootacasmor0802.repository.StyleRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.transaction.annotation.Transactional; // Rollback changes after each test to ensure clean state

import java.time.LocalDateTime;
import java.util.Optional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional // Rollback changes after each test to ensure clean state
class BeerControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private BeerRepository beerRepository;

    @Autowired
    private BreweryRepository breweryRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private StyleRepository styleRepository;

    private Beer existingTestBeer; // An existing beer from the initial dataset
    private Brewery testBrewery;
    private Category testCategory;
    private Style testStyle;

    @BeforeEach
    void setUp() {
        // Ensure test relationships exist or create them (for a truly isolated test)
        // For now, let's use existing IDs from initSQL.
        // In a more robust setup, test-specific data would be inserted.
        testBrewery = breweryRepository.findById(1L).orElseGet(() -> {
            Brewery newBrewery = new Brewery(null, "Test Brewery", "123 Test St", "", "Test City", "TS", "12345", "Test Country", "123-456-7890", "http://test.com", "", "Test Description", 0, LocalDateTime.now());
            return breweryRepository.save(newBrewery);
        });
        testCategory = categoryRepository.findById(1L).orElseGet(() -> {
            Category newCategory = new Category(null, "Test Category", LocalDateTime.now());
            return categoryRepository.save(newCategory);
        });
        testStyle = styleRepository.findById(1L).orElseGet(() -> {
            Style newStyle = new Style(null, testCategory, "Test Style", LocalDateTime.now());
            return styleRepository.save(newStyle);
        });

        // Create a specific beer for testing CRUD operations
        existingTestBeer = new Beer(null, testBrewery, "Initial Test Beer", testCategory, testStyle, 5.0f, 20.0f, 10.0f, 123456789L, "filepath.jpg", "Initial description.", 1, LocalDateTime.now());
        existingTestBeer = beerRepository.save(existingTestBeer);
    }

    @AfterEach
    void tearDown() {
        // Since @Transactional is used, changes are rolled back.
        // However, if for some reason a test commits, this ensures cleanup.
        if (beerRepository.existsById(existingTestBeer.getId())) {
            beerRepository.delete(existingTestBeer);
        }
    }

    @Test
    void testGetAllBeers() throws Exception {
        mockMvc.perform(get("/beers"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$").isArray());
    }

    @Test
    void testGetBeerById() throws Exception {
        mockMvc.perform(get("/beer/{id}", existingTestBeer.getId()))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(existingTestBeer.getId()))
                .andExpect(jsonPath("$.name").value("Initial Test Beer"));
    }

    @Test
    void testCreateBeer() throws Exception {
        String newBeerJson = "{\"name\": \"Newly Created Beer\", \"breweryId\": " + testBrewery.getId() + ", \"categoryId\": " + testCategory.getId() + ", \"styleId\": " + testStyle.getId() + ", \"abv\": 5.5, \"ibu\": 25.0, \"srm\": 11.0, \"upc\": 12345, \"filepath\": \"new.jpg\", \"descript\": \"Desc for new beer\", \"addUser\": 1}";

        MvcResult result = mockMvc.perform(post("/beer")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(newBeerJson))
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.name").value("Newly Created Beer"))
                .andReturn();

        // Extract ID to verify persistence
        Beer createdBeer = objectMapper.readValue(result.getResponse().getContentAsString(), Beer.class);
        Optional<Beer> fetchedBeer = beerRepository.findById(createdBeer.getId());
        assertTrue(fetchedBeer.isPresent());
        assertEquals("Newly Created Beer", fetchedBeer.get().getName());

        // Cleanup created beer (though @Transactional should handle this)
        beerRepository.deleteById(createdBeer.getId());
    }

    @Test
    void testUpdateBeer() throws Exception {
        String updatedName = "Updated Name";
        String updatedDescript = "Updated description";
        String updatedBeerJson = "{\"name\": \"" + updatedName + "\", \"breweryId\": " + testBrewery.getId() + ", \"categoryId\": " + testCategory.getId() + ", \"styleId\": " + testStyle.getId() + ", \"abv\": 6.0, \"ibu\": 30.0, \"srm\": 12.0, \"upc\": 67890, \"filepath\": \"updated.jpg\", \"descript\": \"" + updatedDescript + "\", \"addUser\": 2}";

        mockMvc.perform(put("/beer/{id}", existingTestBeer.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(updatedBeerJson))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.name").value(updatedName))
                .andExpect(jsonPath("$.descript").value(updatedDescript));

        Optional<Beer> fetchedBeer = beerRepository.findById(existingTestBeer.getId());
        assertTrue(fetchedBeer.isPresent());
        assertEquals(updatedName, fetchedBeer.get().getName());
        assertEquals(updatedDescript, fetchedBeer.get().getDescript());
    }

    @Test
    void testDeleteBeer() throws Exception {
        mockMvc.perform(delete("/beer/{id}", existingTestBeer.getId()))
                .andExpect(status().isNoContent());

        Optional<Beer> fetchedBeer = beerRepository.findById(existingTestBeer.getId());
        assertFalse(fetchedBeer.isPresent()); // Should be deleted
    }
}
