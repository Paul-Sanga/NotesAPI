package com.phantom.labs.notesApi.service;

import com.phantom.labs.notesApi.entity.Note;
import com.phantom.labs.notesApi.error.NoteNotFoundException;
import com.phantom.labs.notesApi.models.NoteModel;
import com.phantom.labs.notesApi.repository.NoteRespository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class NoteServiceImpl implements NoteService{

    private final NoteRespository noteRespository;

    @Override
    public Optional<Note> findNoteById(Integer id) throws NoteNotFoundException {
        Optional<Note> optionalNote = noteRespository.findById(id);
        if(optionalNote.isPresent()){
            log.info("Fetch successful");
            return Optional.of(optionalNote.get());
        }
        throw new NoteNotFoundException("Invalid note id");
    }

    @Override
    public Optional<List> getAllNotes() {
        Optional<List<Note>> optionalNotes = Optional.ofNullable(noteRespository.findAll());
        return Optional.of(optionalNotes.get());
    }


    @Override
    public Note saveNote(NoteModel noteModel) {
        var note = Note.builder()
                .title(noteModel.getTitle())
                .content(noteModel.getContent())
                .build();
        log.info("New note created successfully");
        return noteRespository.save(note);
    }

    @Override
    public Note updateNote(NoteModel noteModel, Integer id) throws NoteNotFoundException {
        Optional<Note> optionalNote = noteRespository.findById(id);
        if(optionalNote.isPresent()) {
            var updatableNote = optionalNote.get();
            updatableNote.setTitle(noteModel.getTitle());
            updatableNote.setContent(noteModel.getContent());
            log.info("Updated note successfully");
            return noteRespository.save(updatableNote);
        }
        throw new NoteNotFoundException("Invalid note id");
    }

    @Override
    public void deleteNote(Integer id) throws NoteNotFoundException {
        Optional<Note> optionalNote = noteRespository.findById(id);
        if(optionalNote.isPresent()) {
            log.info("Deleted note successfully");
//            System.out.println(optionalNote.get());
            noteRespository.deleteById(id);
        }else {
            throw new NoteNotFoundException("Invalid note id");
        }
    }
}
