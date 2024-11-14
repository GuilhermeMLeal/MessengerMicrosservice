package br.com.martins.menssagerMicrosservice.service.implementations;


import br.com.martins.menssagerMicrosservice.entities.Product;
import br.com.martins.menssagerMicrosservice.entities.dto.CreateProductDTO;
import br.com.martins.menssagerMicrosservice.entities.dto.GetProductByIdDTO;
import br.com.martins.menssagerMicrosservice.entities.dto.UpdateProductDTO;
import br.com.martins.menssagerMicrosservice.repository.ProductRepository;
import br.com.martins.menssagerMicrosservice.response.exceptions.EntityNotFoundException;
import br.com.martins.menssagerMicrosservice.service.components.ProductFinder;
import br.com.martins.menssagerMicrosservice.service.interfaces.ProductService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private ProductFinder productFinder;


    @Override
    @Transactional
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    @Override
    @Transactional
    public Product getProductById(GetProductByIdDTO getProductByIdDTO) {
        return productFinder.findProductById(getProductByIdDTO.id());
    }

    @Override
    @Transactional
    public Product createProduct(CreateProductDTO createProductDTO) {
        Product card = new Product();
        card.mapFromDto(
                createProductDTO);
        return productRepository.save(card);
    }

    @Override
    @Transactional
    public Product updateProduct(UpdateProductDTO updateProductDTO) {
        Product product = productFinder.findProductById(updateProductDTO.getId());

        product.mapFromDto(
                updateProductDTO);
        return productRepository.save(product);
    }

    @Override
    public void deleteProduct(GetProductByIdDTO getProductByIdDTO) {
        Product card = productFinder.findProductById(getProductByIdDTO.id());
        productRepository.deleteById(card.getId());
    }
}
