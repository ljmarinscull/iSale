package com.webproject.isale.model.dto;

import javax.validation.constraints.*;

public class ProductUpdateRequest {

    @NotNull(message = "The field id is required")
    @Positive(message = "The field id must be a valid ID")
    private Long id;

    @Size(min = 4, max = 30, message = "The field title must be between 4 and 30 characters" )
    private String title;

    @Size(min = 4, message = "The field description must be at least 4 characters" )
    private String description;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}

