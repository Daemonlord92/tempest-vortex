package com.horrorcore.backendv1.exceptions;

public class PlayerCharacterNotFoundException extends RuntimeException {
    public PlayerCharacterNotFoundException(String s) {
        super(s);
    }
}
