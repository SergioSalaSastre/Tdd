package com.sergioTdd.tdd_hexagonal_backend.domain.Model;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Task {

    private Long id;
    private String title;
    private String description;
    private boolean completed;
    private TaskType type;


    public void validateTitle() {
        if(this.title == null) {
            throw new IllegalArgumentException("Title cannot be null or empty");
        }
    }

    public void validateDescription() {
        if(this.description != null && this.description.length() > 500) {
            throw new IllegalArgumentException("Description must be 500 chars max");
        }
    }

    public void validateType() {
        if(this.type == null) {
            throw new IllegalArgumentException("Task type is required");
        }
    }
}
