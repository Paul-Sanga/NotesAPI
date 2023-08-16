package com.phantom.labs.notesApi.service;

import com.phantom.labs.notesApi.entity.Note;
import com.phantom.labs.notesApi.error.NoteNotFoundException;
import com.phantom.labs.notesApi.models.NoteModel;

import java.util.List;
import java.util.Optional;

public interface NoteService {
    public Optional<Note> findNoteById(Integer id) throws NoteNotFoundException;

    public Optional<List> getAllNotes();

    public Note saveNote(NoteModel noteModel);

    public Note updateNote(NoteModel noteModel, Integer id) throws NoteNotFoundException;

    public void deleteNote(Integer id) throws NoteNotFoundException;
}
