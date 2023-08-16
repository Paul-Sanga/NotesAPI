package com.phantom.labs.notesApi.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(
        name = "Notes_Table"
)
public class Note {
    @Id
    @SequenceGenerator(
            name = "notes_sequence",
            sequenceName = "note_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "note_sequence"
    )
    private Integer id;
    @Column(name = "note_title")
    private String title;
    @Column(name = "note_content")
    private String content;
}
