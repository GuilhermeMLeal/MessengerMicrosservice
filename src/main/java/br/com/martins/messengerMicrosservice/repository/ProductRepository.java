package br.com.martins.messengerMicrosservice.repository;

import br.com.martins.messengerMicrosservice.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
}
