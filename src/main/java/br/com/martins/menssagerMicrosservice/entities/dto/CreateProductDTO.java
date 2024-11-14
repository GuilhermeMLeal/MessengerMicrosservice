package br.com.martins.menssagerMicrosservice.entities.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class CreateProductDTO {
    @NotEmpty
    private String name;
    @NotNull
    private Double price;
    @NotEmpty
    private String description;
}
