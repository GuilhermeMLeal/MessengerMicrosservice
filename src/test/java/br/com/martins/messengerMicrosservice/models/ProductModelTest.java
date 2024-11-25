package br.com.martins.messengerMicrosservice.models;

import br.com.martins.messengerMicrosservice.entities.Product;
import br.com.martins.messengerMicrosservice.repository.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.junit.jupiter.api.extension.ExtendWith;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(MockitoExtension.class)
public class ProductModelTest {

    @Mock
    private ProductRepository productRepository;

    private Product productTest;

    @BeforeEach
    public void setUp() {
        productTest = new Product(1L, "Cadeira", 10.50, "Cadeira bem confortável");

        Mockito.when(productRepository.save(Mockito.any(Product.class))).thenReturn(productTest);
    }

    @Test
    void shouldCreateAProduct() {
        Product savedProduct = productRepository.save(productTest);

        assertNotNull(savedProduct);
        assertEquals(productTest.getId(), savedProduct.getId());
        assertEquals(productTest.getName(), savedProduct.getName());
        assertEquals(productTest.getPrice(), savedProduct.getPrice());
        assertEquals(productTest.getDescription(), savedProduct.getDescription());
    }
}