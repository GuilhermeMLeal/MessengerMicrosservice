package br.com.martins.messengerMicrosservice.response.exceptions;

import lombok.Getter;

@Getter
public class EntityNotFoundException extends RuntimeException {

    private String entityName;
    private Long entityId;
    private String name;

    public EntityNotFoundException(String entityName, Long entityId) {
        super(String.format("%s with ID %d not found.", entityName, entityId));
        this.entityName = entityName;
        this.entityId = entityId;
    }

    public EntityNotFoundException(String entityName, String name) {
        super(String.format("%s with name %s not found.", entityName, name));
        this.entityName = entityName;
        this.name = name;
    }

    public EntityNotFoundException(String message) {
        super(message);
    }

    public EntityNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
