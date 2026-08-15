package com.byfood.api.exception;

import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;

import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

class GlobalExceptionHandlerTest {

    private final GlobalExceptionHandler handler = new GlobalExceptionHandler();

    @Test
    void shouldMapNotFoundTo404() {
        ProblemDetail problem = handler.handleNotFound(new NotFoundException("Restaurant not configured"));

        assertThat(problem.getStatus()).isEqualTo(HttpStatus.NOT_FOUND.value());
        assertThat(problem.getDetail()).isEqualTo("Restaurant not configured");
    }

    @Test
    void shouldMapValidationTo400() {
        BeanPropertyBindingResult binding = new BeanPropertyBindingResult(new Object(), "item");
        binding.addError(new FieldError("item", "name", "name is required"));

        MethodArgumentNotValidException ex = new MethodArgumentNotValidException(null, binding);
        ProblemDetail problem = handler.handleValidation(ex);

        assertThat(problem.getStatus()).isEqualTo(HttpStatus.BAD_REQUEST.value());
        assertThat(problem.getProperties().get("errors"))
                .isEqualTo(Map.of("name", "name is required"));
    }
}
