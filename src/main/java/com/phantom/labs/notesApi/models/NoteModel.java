package com.phantom.labs.notesApi.models;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Note object")
public class NoteModel {
    @Schema(description = "Note title", example = "Java")
    private String title;
    @Schema(description = "Note Content", example = "The most prolific programming language in the universe. It dominance has spilled from web engineering to mobile engineering!")
    private String content;
}
