package br.com.martins.menssagerMicrosservice.service.components;

import br.com.martins.menssagerMicrosservice.entities.Product;
import br.com.martins.menssagerMicrosservice.repository.ProductRepository;
import br.com.martins.menssagerMicrosservice.response.exceptions.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ProductFinder {

    @Autowired
    private ProductRepository productRepository;

    public Product findProductById(Long id) {
        return productRepository
                .findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Product", id));
    }
}