package com.api.tarefas.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;

import java.time.Instant;

public class EntityNotFoundException extends RuntimeException {
    public EntityNotFoundException(String message) {
        super(message);
    }

    public ProblemDetail toProblemDetail() {
        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(HttpStatus.NOT_FOUND, super.getLocalizedMessage());
        problemDetail.setTitle("Não encontrado");
        problemDetail.setProperty("timestamp", Instant.now());
        problemDetail.setProperty("stacktrace", super.getStackTrace());
        return problemDetail;
    }
}
