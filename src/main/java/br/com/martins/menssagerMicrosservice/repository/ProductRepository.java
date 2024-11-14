package br.com.martins.menssagerMicrosservice.repository;

import br.com.martins.menssagerMicrosservice.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
}
