package com.shoplite;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.BDDMockito.*;

@ExtendWith(MockitoExtension.class)
class ProductServiceTest {
    @Mock
    ProductRepository repository;
    @InjectMocks
    ProductService service;

    @Test
    void findById_existing_returnsProduct() {
        Product mouse = new Product(1L, "Mouse", 120);

        given(repository.findById(1L)).willReturn(Optional.of(mouse));

        assertThat(service.findById(1L)).isSameAs(mouse);
    }

    @Test
    void findById_missing_throwsNotFound() {
        given(repository.findById(99L)).willReturn(Optional.empty());

        assertThatThrownBy(() -> service.findById(99L))
                .isInstanceOf(ProductNotFoundException.class)
                .hasMessage("Product 99 not found");
    }
}