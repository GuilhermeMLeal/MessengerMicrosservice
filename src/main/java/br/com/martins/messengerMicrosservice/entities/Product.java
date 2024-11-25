package br.com.martins.messengerMicrosservice.entities;

import br.com.martins.messengerMicrosservice.entities.dto.CreateProductDTO;
import jakarta.persistence.Entity;
import jakarta.persistence.GenerationType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Column;

import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.apache.logging.log4j.core.config.plugins.validation.constraints.NotBlank;

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
    @NotBlank
    @Column(nullable = false, length = 100)
    private String name;
    @NotNull
    @Column(nullable = false)
    private Double price;
    @NotBlank
    @Column(length = 255)
    private String description;

    public void mapFromDto(
            CreateProductDTO createProductDTO) {
        this.name = createProductDTO.getName();
        this.description = createProductDTO.getDescription();
        this.price = createProductDTO.getPrice();
    }
}
