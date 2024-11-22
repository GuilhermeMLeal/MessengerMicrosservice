package br.com.martins.messengerMicrosservice.models;

import br.com.martins.messengerMicrosservice.entities.Product;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.ValidatorFactory;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import javax.xml.validation.Validator;
import java.util.Set;

public class ProductModelTest {

    private static Validator validator;

    @BeforeAll
    public static void setUpValidator() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    public void testNameNotBlank() {
        // Arrange: Cria um produto com um campo inválido (nome em branco)
        Product product = new Product(1L, "", 10.50, "Cadeira de 4 pés");

        // Act: Valida o produto
        Set<ConstraintViolation<Product>> violations = validator.validate(product);

        // Assert: Verifica se há violações e se a mensagem corresponde
        assertThat(violations).isNotEmpty();
        assertThat(violations.iterator().next().getMessage()).isEqualTo("must not be blank");
    }
}
