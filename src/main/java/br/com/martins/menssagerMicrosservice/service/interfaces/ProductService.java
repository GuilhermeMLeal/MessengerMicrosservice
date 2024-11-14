package br.com.martins.menssagerMicrosservice.service.interfaces;

import br.com.martins.menssagerMicrosservice.entities.Product;
import br.com.martins.menssagerMicrosservice.entities.dto.CreateProductDTO;
import br.com.martins.menssagerMicrosservice.entities.dto.GetProductByIdDTO;
import br.com.martins.menssagerMicrosservice.entities.dto.UpdateProductDTO;

import java.util.List;
import java.util.Optional;

public interface ProductService {
    List<Product> getAllProducts();

    Product getProductById(GetProductByIdDTO getProductByIdDTO);

    Product createProduct(CreateProductDTO createProductDTO);

    Product updateProduct(UpdateProductDTO updateProductDTO);

    void deleteProduct(GetProductByIdDTO getProductByIdDTO);
}
