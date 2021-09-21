package com.zupacademy.propostas.commos.exceptions;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.ArrayList;
import java.util.List;

@RestControllerAdvice
public class ValidationErroHandler {

    @Autowired
    private MessageSource messageSource;

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public List<ErroResponseDTO> handle(MethodArgumentNotValidException exception){
        List<ErroResponseDTO> erros = new ArrayList<>();
        List<FieldError> fieldErrors = exception.getBindingResult().getFieldErrors();
        fieldErrors.forEach(e -> {
            String message = messageSource.getMessage(e, LocaleContextHolder.getLocale());
            ErroResponseDTO erro = new ErroResponseDTO(e.getField(), message);
            erros.add(erro);
        });

        return erros;
    }


    @ExceptionHandler(ApiErroException.class)
    public ResponseEntity<List<ErroResponseDTO>> handleValorDuplicado(ApiErroException exception){
        List<ErroResponseDTO> erros = new ArrayList<>();
        ErroResponseDTO erro = new ErroResponseDTO(exception.getCampo(), exception.getMessage());

        erros.add(erro);

        return ResponseEntity.status(exception.getHttpStatus()).body(erros);
    }
}