package br.com.fiap.orbitaverde.config;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

/**
 * Tratamento global de erros para a API REST.
 * Retorna respostas JSON padronizadas para erros de validacao e excecoes gerais.
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

    /**
     * Trata erros de validacao (@Valid).
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, Object>> handleValidationErrors(
            MethodArgumentNotValidException ex) {

        Map<String, String> erros = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach(error -> {
            String campo = ((FieldError) error).getField();
            String mensagem = error.getDefaultMessage();
            erros.put(campo, mensagem);
        });

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Map.of(
            "erro", "Dados invalidos",
            "campos", erros,
            "timestamp", LocalDateTime.now().toString()
        ));
    }

    /**
     * Trata excecoes gerais nao tratadas.
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<Map<String, Object>> handleGenericError(Exception ex) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Map.of(
            "erro", "Erro interno no servidor",
            "detalhe", ex.getMessage(),
            "timestamp", LocalDateTime.now().toString()
        ));
    }
}
