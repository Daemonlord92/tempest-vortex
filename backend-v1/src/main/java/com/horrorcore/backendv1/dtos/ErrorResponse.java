package com.horrorcore.backendv1.dtos;

import java.time.LocalDateTime;
import java.util.List;

public record ErrorResponse(
        int status,
        String error,
        String message,
        List<String> details,
        String path,
        LocalDateTime timestamp
) {
    public ErrorResponse(int status, String error, String message, String path) {
        this(status, error, message, null, path, LocalDateTime.now());
    }

    public ErrorResponse(int status, String error, String message, List<String> details, String path) {
        this(status, error, message, details, path, LocalDateTime.now());
    }
}


