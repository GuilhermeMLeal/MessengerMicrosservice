package br.com.martins.messengerMicrosservice.service.interfaces;

import br.com.martins.messengerMicrosservice.entities.Product;
import br.com.martins.messengerMicrosservice.entities.dto.CreateProductDTO;
import br.com.martins.messengerMicrosservice.entities.dto.GetProductByIdDTO;
import br.com.martins.messengerMicrosservice.entities.dto.UpdateProductDTO;

import java.util.List;

public interface ProductService {
    List<Product> getAllProducts();

    Product getProductById(GetProductByIdDTO getProductByIdDTO);

    Product createProduct(CreateProductDTO createProductDTO);

    Product updateProduct(UpdateProductDTO updateProductDTO);

    void deleteProduct(GetProductByIdDTO getProductByIdDTO);
}
