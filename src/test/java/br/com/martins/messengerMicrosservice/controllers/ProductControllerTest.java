package br.com.martins.messengerMicrosservice.controllers;

import br.com.martins.messengerMicrosservice.controller.ProductController;
import br.com.martins.messengerMicrosservice.entities.Product;
import br.com.martins.messengerMicrosservice.entities.dto.CreateProductDTO;
import br.com.martins.messengerMicrosservice.entities.dto.GetProductByIdDTO;
import br.com.martins.messengerMicrosservice.entities.dto.UpdateProductDTO;
import br.com.martins.messengerMicrosservice.service.interfaces.ProductService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ProductControllerTest {

    @Mock
    private ProductService productService;

    @InjectMocks
    private ProductController productController;

    private MockMvc mockMvc;

    private Product product;
    private CreateProductDTO createProductDTO;
    private UpdateProductDTO updateProductDTO;
    private GetProductByIdDTO getProductByIdDTO;

    @BeforeEach
    public void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(productController).build();

        product = new Product();
        product.setId(1L);
        product.setName("Cadeira");
        product.setPrice(10.50);
        product.setDescription("Cadeira confortável");

        createProductDTO = new CreateProductDTO("Cadeira", 10.50, "Cadeira confortável");
        updateProductDTO = new UpdateProductDTO("Cadeira Atualizada", 15.75, "Cadeira super confortável");
        updateProductDTO.setId(1L);
        getProductByIdDTO = new GetProductByIdDTO(1L);
    }

    @Test
    void shouldGetAllProducts() throws Exception {
        // Arrange
        List<Product> products = Arrays.asList(product);
        when(productService.getAllProducts()).thenReturn(products);

        // Act & Assert
        mockMvc.perform(get("/api/v1/products/listProducts")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()").value(1))
                .andExpect(jsonPath("$[0].name").value("Cadeira"));

        verify(productService, times(1)).getAllProducts();
    }

    @Test
    void shouldGetOneProduct() throws Exception {
        // Arrange
        when(productService.getProductById(any(GetProductByIdDTO.class))).thenReturn(product);

        // Act & Assert
        mockMvc.perform(post("/api/v1/products/getOneProduct")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(getProductByIdDTO)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.name").value("Cadeira"));

        verify(productService, times(1)).getProductById(any(GetProductByIdDTO.class));
    }

    @Test
    void shouldCreateProduct() throws Exception {
        // Arrange
        when(productService.createProduct(any(CreateProductDTO.class))).thenReturn(product);

        // Act & Assert
        mockMvc.perform(post("/api/v1/products/createProduct")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(createProductDTO)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Cadeira"))
                .andExpect(jsonPath("$.price").value(10.50));

        verify(productService, times(1)).createProduct(any(CreateProductDTO.class));
    }

    @Test
    void shouldUpdateProduct() throws Exception {
        // Arrange
        when(productService.updateProduct(any(UpdateProductDTO.class))).thenReturn(product);

        // Act & Assert
        mockMvc.perform(put("/api/v1/products/updateProduct")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(updateProductDTO)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Cadeira"))
                .andExpect(jsonPath("$.description").value("Cadeira confortável"));

        verify(productService, times(1)).updateProduct(any(UpdateProductDTO.class));
    }

    @Test
    void shouldDeleteProduct() throws Exception {
        // Act & Assert
        mockMvc.perform(delete("/api/v1/products/delete")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(getProductByIdDTO)))
                .andExpect(status().isOk());

        verify(productService, times(1)).deleteProduct(any(GetProductByIdDTO.class));
    }

    private static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
