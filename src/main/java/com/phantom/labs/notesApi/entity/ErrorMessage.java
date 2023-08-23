package com.phantom.labs.notesApi.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Error Message")
public class ErrorMessage {

    private HttpStatus status;
    private String message;
}
