package org.example.taller2.dto;

import lombok.Getter;

@Getter
public class FlashMessage {
    private final FlashMessageType type;
    private final String message;

    public FlashMessage(FlashMessageType flashMessageType, String message) {
        this.type = flashMessageType;
        this.message = message;
    }

}