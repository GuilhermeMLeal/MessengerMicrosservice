package br.com.martins.menssagerMicrosservice.entities;

import br.com.martins.menssagerMicrosservice.entities.dto.CreateProductDTO;
import jakarta.persistence.Entity;
import jakarta.persistence.GenerationType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Column;

import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "products")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100)
    private String name;

    @Column(nullable = false)
    private Double price;

    @Column(length = 255)
    private String description;

    public void mapFromDto(
            CreateProductDTO createProductDTO) {
        this.name = createProductDTO.getName();
        this.description = createProductDTO.getDescription();
        this.price = createProductDTO.getPrice();
    }
}
