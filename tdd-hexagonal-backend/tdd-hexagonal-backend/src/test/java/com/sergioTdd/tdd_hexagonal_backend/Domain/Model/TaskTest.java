package com.sergioTdd.tdd_hexagonal_backend.Domain.Model;

import com.sergioTdd.tdd_hexagonal_backend.domain.Model.Task;
import com.sergioTdd.tdd_hexagonal_backend.domain.Model.TaskType;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

class TaskTest {

    @Test
    void shouldThrowExceptionWhenTitleIsNull() {
        // Configuración
        Task task = Task.builder()
                .id(1L)
                .title(null) // ← Esto debería fallar
                .description("Descripción válida")
                .type(TaskType.WORK)
                .build();

        // Assert: Verifica que lanza una excepción
        assertThrows(IllegalArgumentException.class, () -> {
            task.validateTitle(); // Método que implementaremos después
        });
    }

    @Test
    void shouldThrowExceptionWhenDescriptionExceeds500Chars() {
        String longDescription = "a".repeat(501);
        Task task = Task.builder()
                .title("Valid title")
                .description(longDescription)
                .build();

        assertThrows(IllegalArgumentException.class, () -> {
            task.validateDescription();
        });
    }

    @Test
    void shouldThrowEceptionWhenTypeIsNull() {
        Task task = Task.builder()
                .title("Valid title")
                .type(null)
                .build();

        assertThrows(IllegalArgumentException.class, () -> {
            task.validateType();
        });
    }
}