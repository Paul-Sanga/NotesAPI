package com.phantom.labs.notesApi.controller;

import com.phantom.labs.notesApi.error.NoteNotFoundException;
import com.phantom.labs.notesApi.models.NoteModel;
import com.phantom.labs.notesApi.service.NoteService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@Controller
@RequiredArgsConstructor
@RequestMapping("/api/note/v2")
public class NoteController {

    private final NoteService noteService;

    @PostMapping("/")
    public ResponseEntity createNote(@RequestBody NoteModel noteModel)
    {
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentRequest().path("/api/note/v2").toUriString());
        return ResponseEntity.created(uri).body(noteService.saveNote(noteModel));
    }

    @GetMapping("/")
    public  ResponseEntity getNote() throws NoteNotFoundException {
        return  ResponseEntity.ok(noteService.getAllNotes());
    }

    @GetMapping("/{noteId}")
    public ResponseEntity getNoteById(@PathVariable Integer noteId) throws  NoteNotFoundException{
        return  ResponseEntity.ok(noteService.findNoteById(noteId));
    }

    @PutMapping("/{noteId}")
    public ResponseEntity updateNote(@PathVariable Integer noteId, @RequestBody NoteModel noteModel) throws NoteNotFoundException{
        return ResponseEntity.ok(noteService.updateNote(noteModel, noteId));
    }

    @DeleteMapping("/{noteId}")
    public ResponseEntity deleteNote(@PathVariable Integer noteId) throws NoteNotFoundException {
        noteService.deleteNote(noteId);
        return ResponseEntity.ok("Note deleted successfully");
    }
}

