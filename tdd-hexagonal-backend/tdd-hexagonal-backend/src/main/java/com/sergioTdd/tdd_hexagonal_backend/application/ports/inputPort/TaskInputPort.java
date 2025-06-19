package com.sergioTdd.tdd_hexagonal_backend.application.ports.inputPort;

import com.sergioTdd.tdd_hexagonal_backend.domain.model.Task;

public interface TaskInputPort {
    Task createTask (Task task);
}
