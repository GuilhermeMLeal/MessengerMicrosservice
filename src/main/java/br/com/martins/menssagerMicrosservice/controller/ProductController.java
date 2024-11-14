package br.com.martins.menssagerMicrosservice.controller;

import br.com.martins.menssagerMicrosservice.entities.Product;
import br.com.martins.menssagerMicrosservice.entities.dto.CreateProductDTO;
import br.com.martins.menssagerMicrosservice.entities.dto.GetProductByIdDTO;
import br.com.martins.menssagerMicrosservice.entities.dto.UpdateProductDTO;
import br.com.martins.menssagerMicrosservice.service.interfaces.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping("/listProducts")
    public List<Product> getAllProducts() {
        return productService.getAllProducts();
    }

    @PostMapping("/getOneProduct")
    public Product getProductById(@RequestBody GetProductByIdDTO getProductByIdDTO) {
        return productService.getProductById(getProductByIdDTO);
    }

    @PostMapping("/createProduct")
    public Product createProduct(@RequestBody CreateProductDTO createProductDTO) {
        return productService.createProduct(createProductDTO);
    }

    @PutMapping("/updateProduct")
    public ResponseEntity<Product> updateProduct(@RequestBody UpdateProductDTO updateProductDTO) {
        return ResponseEntity.ok(productService.updateProduct(updateProductDTO));
    }

    @DeleteMapping("/delete")
    public ResponseEntity<Void> deleteProduct(@RequestBody GetProductByIdDTO getProductByIdDTO) {
        productService.deleteProduct(getProductByIdDTO);
        return ResponseEntity.ok().build();
    }
}
