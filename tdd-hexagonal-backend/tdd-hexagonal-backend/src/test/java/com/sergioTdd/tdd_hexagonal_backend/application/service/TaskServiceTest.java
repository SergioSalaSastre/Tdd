package com.sergioTdd.tdd_hexagonal_backend.application.service;

import com.sergioTdd.tdd_hexagonal_backend.application.ports.outputPort.TaskOutputPort;
import com.sergioTdd.tdd_hexagonal_backend.domain.model.Task;
import com.sergioTdd.tdd_hexagonal_backend.domain.model.TaskType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;
public class TaskServiceTest {
    private TaskOutputPort taskOutputPort;
    private TaskService taskService;

    @BeforeEach
    void setUp() {
        //creo el mock-simulación del puerto salida
        //para verificar comportamientos
        taskOutputPort = mock(TaskOutputPort.class);
        taskService = new TaskService(taskOutputPort);
    }

    @Test
    void shouldCreateTaskSuccessfully() {
        Task task = Task.builder()
                .id(1L)
                .title("Learn TDD")
                .description("Study and make tasks as a practice")
                .type(TaskType.STUDY)
                .build();

        when(taskOutputPort.save(task)).thenReturn(task);

        Task created = taskService.createTask(task);
        assertNotNull(created);
        assertEquals("Learn TDD", created.getTitle());
        assertEquals("Study and make tasks as a practice", created.getDescription());
        assertEquals(TaskType.STUDY, created.getType());
        verify(taskOutputPort, times(1)).save(task);
    }

    @Test
    void createTask_shouldThrowException_WhenTitleIsNull() {
        Task taskWithoutTitle  = Task.builder()
                .title(null)
                .description("Valid description")
                .type(TaskType.HOBBY)
                .build();

        assertThrows(IllegalArgumentException.class, () ->
                taskService.createTask(taskWithoutTitle ));
    }
    @Test
    void createTask_shouldThrowException_WhenDescriptionTooLong() {
        String longDescription = "a".repeat(501);

        Task taskLongDescription = Task.builder()
                .title("Valid title")
                .description((longDescription))
                .type(TaskType.PERSONAL)
                .build();

        assertThrows(IllegalArgumentException.class, () ->
                taskService.createTask(taskLongDescription));
    }

    @Test
    void createTask_shouldThrowException_WhenTaskTypeIsNull() {
        Task task = Task.builder()
                .title("Valid title")
                .description("Valid description")
                .type(null)
                .build();

        assertThrows(IllegalArgumentException.class, () ->
                taskService.createTask(task));
    }

    @Test
    void createTask_shouldNotCallSave_WhenValidationFails() {
        Task task = Task.builder()
                .title(null)
                .description(null)
                .type(TaskType.HOBBY)
                .build();

        assertThrows(IllegalArgumentException.class, () -> taskService.createTask(task));
        verify(taskOutputPort, never()).save(any());
    }
}
