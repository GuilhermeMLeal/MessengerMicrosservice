package br.com.martins.messengerMicrosservice.services;

import br.com.martins.messengerMicrosservice.entities.Product;
import br.com.martins.messengerMicrosservice.entities.dto.CreateProductDTO;
import br.com.martins.messengerMicrosservice.entities.dto.GetProductByIdDTO;
import br.com.martins.messengerMicrosservice.entities.dto.UpdateProductDTO;
import br.com.martins.messengerMicrosservice.repository.ProductRepository;
import br.com.martins.messengerMicrosservice.service.components.ProductFinder;
import br.com.martins.messengerMicrosservice.service.implementations.ProductServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.junit.jupiter.api.extension.ExtendWith;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class ProductServiceTest {

    @Mock
    private ProductRepository productRepository;

    @Mock
    private ProductFinder productFinder;

    @InjectMocks
    private ProductServiceImpl productService;

    private CreateProductDTO createProductDTO;
    private UpdateProductDTO updateProductDTO;
    private GetProductByIdDTO getProductByIdDTO;

    @BeforeEach
    public void setUp() {
        createProductDTO = new CreateProductDTO("Cadeira", 10.50, "Cadeira confortável");
        updateProductDTO = new UpdateProductDTO("Cadeira Atualizada", 15.75, "Cadeira super confortável");
        updateProductDTO.setId(1L);
        getProductByIdDTO = new GetProductByIdDTO(1L);
    }

    @Test
    void shouldCreateProduct() {
        // Arrange
        Product product = new Product();
        product.mapFromDto(createProductDTO);

        Mockito.when(productRepository.save(Mockito.any(Product.class))).thenReturn(product);

        // Act
        Product savedProduct = productService.createProduct(createProductDTO);

        // Assert
        assertNotNull(savedProduct);
        assertEquals(createProductDTO.getName(), savedProduct.getName());
        assertEquals(createProductDTO.getPrice(), savedProduct.getPrice());
        assertEquals(createProductDTO.getDescription(), savedProduct.getDescription());

        // Verify the interaction
        verify(productRepository, times(1)).save(Mockito.any(Product.class));
    }

    @Test
    void shouldGetProductById() {
        // Arrange
        Product product = new Product();
        product.setId(1L);
        product.setName("Cadeira");
        product.setPrice(10.50);
        product.setDescription("Cadeira confortável");

        Mockito.when(productFinder.findProductById(getProductByIdDTO.id())).thenReturn(product);

        // Act
        Product foundProduct = productService.getProductById(getProductByIdDTO);

        // Assert
        assertNotNull(foundProduct);
        assertEquals(1L, foundProduct.getId());
        assertEquals("Cadeira", foundProduct.getName());

        // Verify the interaction
        verify(productFinder, times(1)).findProductById(getProductByIdDTO.id());
    }

    @Test
    void shouldUpdateProduct() {
        // Arrange
        Product existingProduct = new Product();
        existingProduct.setId(1L);
        existingProduct.setName("Cadeira Antiga");
        existingProduct.setPrice(8.00);
        existingProduct.setDescription("Cadeira simples");

        Mockito.when(productFinder.findProductById(updateProductDTO.getId())).thenReturn(existingProduct);
        Mockito.when(productRepository.save(Mockito.any(Product.class))).thenReturn(existingProduct);

        // Act
        Product updatedProduct = productService.updateProduct(updateProductDTO);

        // Assert
        assertNotNull(updatedProduct);
        assertEquals(updateProductDTO.getName(), updatedProduct.getName());
        assertEquals(updateProductDTO.getPrice(), updatedProduct.getPrice());
        assertEquals(updateProductDTO.getDescription(), updatedProduct.getDescription());

        // Verify the interaction
        verify(productFinder, times(1)).findProductById(updateProductDTO.getId());
        verify(productRepository, times(1)).save(Mockito.any(Product.class));
    }

    @Test
    void shouldDeleteProduct() {
        // Arrange
        Product product = new Product();
        product.setId(1L);

        Mockito.when(productFinder.findProductById(getProductByIdDTO.id())).thenReturn(product);

        // Act
        productService.deleteProduct(getProductByIdDTO);

        // Verify the interaction
        verify(productFinder, times(1)).findProductById(getProductByIdDTO.id());
        verify(productRepository, times(1)).deleteById(product.getId());
    }
}