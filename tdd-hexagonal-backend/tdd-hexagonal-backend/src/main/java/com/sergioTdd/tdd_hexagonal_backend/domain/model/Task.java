package com.sergioTdd.tdd_hexagonal_backend.domain.model;

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
        if(this.title == null || this.title.trim().isEmpty()) {
            throw new IllegalArgumentException("Title is mandatory"); // Mensaje que espera tu test
        }
    }

    public void validateDescription() {
        if(this.description != null && this.description.length() > 500) {
            throw new IllegalArgumentException("Description should no have more than 500 chars"); // Exactamente como en tu test
        }
    }

    public void validateType() {
        if(this.type == null) {
            throw new IllegalArgumentException("Task type is required"); // Mensaje EXACTO
        }
    }

    public void validate() {
        validateTitle();
        validateDescription();
        validateType();
    }
}
