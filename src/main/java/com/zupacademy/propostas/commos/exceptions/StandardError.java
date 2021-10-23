package com.zupacademy.propostas.commos.exceptions;

import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;
import java.util.List;

public class StandardError {
    private LocalDateTime timestamp;
    private String type;
    private String title;
    private HttpStatus status;
    private String detail;
    private List<?> errors;

    public StandardError(LocalDateTime timestamp, String type, String title, HttpStatus status, String detail, List<?> errors) {
        this.timestamp = timestamp;
        this.type = type;
        this.title = title;
        this.status = status;
        this.detail = detail;
        this.errors = errors;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public String getType() {
        return type;
    }

    public String getTitle() {
        return title;
    }

    public HttpStatus getStatus() {
        return status;
    }

    public String getDetail() {
        return detail;
    }

    public List<?> getErrors() {
        return errors;
    }
}
