package com.shoplite;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
// gives MockMvc on top of the full app
@Transactional
// roll back DB changes after each test
class ShopLiteIntegrationTest {
    @Autowired
    MockMvc mockMvc;
    @Autowired
    ProductRepository repository;

    @Test
    void createThenFetch_fullFlow() throws Exception {
        mockMvc.perform(post("/api/products")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {"name": "Keyboard", "price": 250}
                                """))
                .andExpect(status().isCreated());
        Product saved = repository.findByNameIgnoreCase("Keyboard").orElseThrow();
        assertThat(saved.getPrice()).isEqualTo(250.0);
        mockMvc.perform(get("/api/products/" + saved.getId()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Keyboard"));
    }

    @Test
    void unknownProduct_returns404_endToEnd() throws Exception {
        mockMvc.perform(get("/api/products/123456"))
                .andExpect(status().isNotFound());
    }
}
