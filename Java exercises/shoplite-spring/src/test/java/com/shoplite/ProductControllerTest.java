package com.shoplite;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.BDDMockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(ProductController.class)
class ProductControllerTest {
    @Autowired
    MockMvc mockMvc;// provided by @WebMvcTest
    @MockitoBean
    ProductService service;// mock injected into the controller

    @Test
    void getById_existing_returns200AndJson() throws Exception {
        given(service.findById(1L)).willReturn(new Product(1L, "Mouse", 120));
        mockMvc.perform(get("/api/products/1"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.name").value("Mouse"))
                .andExpect(jsonPath("$.price").value(120.0));
    }

    @Test
    void getById_missing_returns404() throws Exception {
        given(service.findById(99L)).willThrow(new ProductNotFoundException(99L));
        mockMvc.perform(get("/api/products/99"))
                .andExpect(status().isNotFound());
    }

    @Test
    void cheaperThan_returnsJsonArray() throws Exception {
        given(service.findCheaperThan(120.0)).willReturn(List.of(
                new Product(1L, "Mouse", 120),
                new Product(2L, "Pad", 30)));
        mockMvc.perform(get("/api/products").param("maxPrice", "120"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(2))
                .andExpect(jsonPath("$[0].name").value("Mouse"))
                .andExpect(jsonPath("$[1].name").value("Pad"));
    }

    @Test
    void cheaperThan_missingParam_returns400() throws Exception {
        mockMvc.perform(get("/api/products"))
// no maxPrice
                .andExpect(status().isBadRequest());
    }

    @Test
    void create_validBody_returns201() throws Exception {
        given(service.create("Mouse", 120.0)).willReturn(new Product(1L, "Mouse", 120));
        mockMvc.perform(post("/api/products")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {"name": "Mouse", "price": 120}
                                """))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(1));
        then(service).should().create("Mouse", 120.0);
// JSON became the right call
    }

    @Test
    void create_invalidBody_returns400AndServiceNotCalled() throws Exception {
        mockMvc.perform(post("/api/products")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {"name": "", "price": -5}
                                """))
                .andExpect(status().isBadRequest());
        then(service).shouldHaveNoInteractions();
    }
}