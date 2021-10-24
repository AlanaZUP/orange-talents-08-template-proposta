package com.zupacademy.propostas.commos.exceptions;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@RestControllerAdvice
public class ValidationErroHandler {

    @Autowired
    private MessageSource messageSource;

    private Logger LOGGER = LoggerFactory.getLogger(ValidationErroHandler.class);

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public StandardError handle(MethodArgumentNotValidException exception){
        List<ErroResponse> erros = new ArrayList<>();
        List<FieldError> fieldErrors = exception.getBindingResult().getFieldErrors();
        fieldErrors.forEach(e -> {
            String message = messageSource.getMessage(e, LocaleContextHolder.getLocale());
            ErroResponse erro = new ErroResponse(e.getField(), e.getRejectedValue(), message);
            erros.add(erro);
        });

        LOGGER.warn("ERROR BAD REQUEST -> \n" + erros.stream().map(it -> it.toString()+"\n").toString());

        return new StandardError(
                LocalDateTime.now(),
                "/erros/validations",
                "Falha ao validar dados",
                HttpStatus.BAD_REQUEST,
                "Os dados recebidos não estão no formato adequado, tente novamente",
                erros
        );
    }


    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
    @ExceptionHandler(RegraDeNegocioException.class)
    public StandardError handleRegraDeNegocio(RegraDeNegocioException exception){
        List<ErroResponse> erros = new ArrayList<>();
        ErroResponse erro = new ErroResponse(exception.getField(), exception.getValue(), exception.getMessage());

        erros.add(erro);

        LOGGER.warn("ERROR UNPROCESSABLE_ENTITY -> \n" + erros.stream().map(it -> it.toString()+"\n").toString());

        return new StandardError(
                LocalDateTime.now(),
                "/errors/regra-de-negocio",
                "Não atende ás regras da aplicação",
                HttpStatus.UNPROCESSABLE_ENTITY,
                "Os dados recebidos não estão de acordo com as regras de negócio da aplicação, tente novamente",
                erros
        );
    }


    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(NotFoundException.class)
    public StandardError handleNotFound(NotFoundException exception){
        List<ErroResponseNotFound> erros = new ArrayList<>();
        ErroResponseNotFound erro = new ErroResponseNotFound(exception.getEntity(), exception.getField(), exception.getValue(), exception.getMessage());

        erros.add(erro);

        LOGGER.warn("ERROR NOT FOUND -> \n" + erros.stream().map(it -> it.toString()+"\n").toString());

        return new StandardError(
                LocalDateTime.now(),
                "/errors/regra-de-negocio",
                "Não atende ás regras da aplicação",
                HttpStatus.UNPROCESSABLE_ENTITY,
                "Os dados recebidos não estão de acordo com as regras de negócio da aplicação, tente novamente",
                erros
        );
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public StandardError handleException(Exception ex) {

        List<Exception> erros = new ArrayList<>();
        erros.add(ex);
        LOGGER.error("ERROR INTERNAL -> \n" + erros.stream().map(it -> it.toString()+"\n").toString());

        return new StandardError(
                LocalDateTime.now(),
                "/erros/internal-error",
                "Ocorreu um erro no servidor",
                HttpStatus.INTERNAL_SERVER_ERROR,
                "Não foi possível executar a requisição devido a um erro interno no servidor, tente novamente mais tarde",
                erros
        );
    }
}