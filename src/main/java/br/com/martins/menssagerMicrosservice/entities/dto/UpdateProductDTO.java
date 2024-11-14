package br.com.martins.menssagerMicrosservice.entities.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateProductDTO extends CreateProductDTO{
    @NotNull private Long id;

    public UpdateProductDTO(@NotEmpty String name, @NotNull Double price, @NotEmpty String description) {
        super(name, price, description);
    }
}
