package com.sergioTdd.tdd_hexagonal_backend.application.service;

import com.sergioTdd.tdd_hexagonal_backend.application.ports.inputPort.TaskInputPort;
import com.sergioTdd.tdd_hexagonal_backend.application.ports.outputPort.TaskOutputPort;
import com.sergioTdd.tdd_hexagonal_backend.domain.model.Task;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class TaskService implements TaskInputPort {
    private final TaskOutputPort taskOutputPort;

    public TaskService(TaskOutputPort taskOutputPort) {
        this.taskOutputPort = taskOutputPort;
    }

    @Override
    public Task createTask(Task task) {
        // uso todos los métodos de validación primero
        task.validateTitle();
        task.validateDescription();
        task.validateType();
        task.validate();
        return taskOutputPort.save(task);
    }
}



