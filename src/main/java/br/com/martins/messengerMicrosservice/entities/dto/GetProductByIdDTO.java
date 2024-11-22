package br.com.martins.messengerMicrosservice.entities.dto;

import jakarta.validation.constraints.NotNull;

public record GetProductByIdDTO(@NotNull(message = "The Id can't be null, try again") Long id) {
}
