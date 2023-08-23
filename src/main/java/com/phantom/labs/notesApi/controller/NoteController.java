package com.phantom.labs.notesApi.controller;

import com.phantom.labs.notesApi.error.NoteNotFoundException;
import com.phantom.labs.notesApi.models.NoteModel;
import com.phantom.labs.notesApi.service.NoteService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@Tag(name = "Notes")
@Controller
@RequiredArgsConstructor
@RequestMapping("/api/note/v2")
public class NoteController {

    private final NoteService noteService;

    @PostMapping("/")
    @Operation(
            summary = "Create a new note",
            description = "Endpoint to create a new note"
    )
    @ApiResponse(
            responseCode = "201",
            description = "Note created successfully",
            content = @Content(schema = @Schema(implementation = NoteModel.class))
    )
    @ApiResponse(
            responseCode = "500",
            description = "Internal server error"
    )
    public ResponseEntity createNote(@RequestBody NoteModel noteModel)
    {
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentRequest().path("/api/note/v2").toUriString());
        return ResponseEntity.created(uri).body(noteService.saveNote(noteModel));
    }

    @GetMapping("/")
    @Operation(
            summary = "Get all notes present in the database",
            description = "This is the endpoint that returns all the tuples on the notes table"
    )
    @ApiResponse(
            responseCode = "200",
            description = "Fetch successful",
            content = @Content(schema = @Schema(implementation = NoteModel.class, type = "array"))
    )
    @ApiResponse(
            responseCode = "500",
            description = "Internal server error"
    )
    public  ResponseEntity getNote() throws NoteNotFoundException {
        return  ResponseEntity.ok(noteService.getAllNotes());
    }

    @GetMapping("/{noteId}")
    @Operation(
            summary = "Retrieve a single note record from the database",
            description = "Retrieve a single note base on the id passed in the url"
    )
    @ApiResponse(
            responseCode = "200",
            description = "Note fetched successfully",
            content = @Content(schema = @Schema(implementation = NoteModel.class))
    )
    @ApiResponse(
            responseCode = "404",
            description = "Invalid noted id; note not found."
    )
    @ApiResponse(
            responseCode = "500",
            description = "Internal server error!"
    )
    public ResponseEntity getNoteById(@Parameter(description = "Id of the note to fetch.") @PathVariable Integer noteId) throws  NoteNotFoundException{
        return  ResponseEntity.ok(noteService.findNoteById(noteId));
    }

    @PutMapping("/{noteId}")
    @Operation(
            summary = "Update a note",
            description = "Update a note based off the note id provided as the path variable"
    )
    @ApiResponse(
            responseCode = "200",
            description = "Note updated successfully",
            content = @Content(schema = @Schema(implementation = NoteModel.class))
    )
    @ApiResponse(
            responseCode = "404",
            description = "Invalid noted id; note not found."
    )
    @ApiResponse(
            responseCode = "500",
            description = "Internal server error!"
    )
    public ResponseEntity updateNote(@Parameter(description = "Id of the note to update.") @PathVariable Integer noteId, @RequestBody NoteModel noteModel) throws NoteNotFoundException{
        return ResponseEntity.ok(noteService.updateNote(noteModel, noteId));
    }

    @DeleteMapping("/{noteId}")
    @Operation(
            summary = "Delete a note",
            description = "Delete a note based off the note id provided as the path variable"
    )
    @ApiResponse(
            responseCode = "200",
            description = "Note deleted successfully",
            content = @Content(schema = @Schema(implementation = NoteModel.class))
    )
    @ApiResponse(
            responseCode = "404",
            description = "Invalid noted id; note not found."
    )
    @ApiResponse(
            responseCode = "500",
            description = "Internal server error!"
    )
    public ResponseEntity deleteNote(@Parameter(description = "Id of the note to delete.") @PathVariable Integer noteId) throws NoteNotFoundException {
        noteService.deleteNote(noteId);
        return ResponseEntity.ok("Note deleted successfully");
    }
}

