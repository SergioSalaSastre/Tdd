package com.sergioTdd.tdd_hexagonal_backend.application.ports.outputPort;

import com.sergioTdd.tdd_hexagonal_backend.domain.model.Task;

public interface TaskOutputPort {
    Task save(Task task);
}
