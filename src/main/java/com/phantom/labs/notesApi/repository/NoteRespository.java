package com.phantom.labs.notesApi.repository;

import com.phantom.labs.notesApi.entity.Note;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NoteRespository extends JpaRepository<Note, Integer> {}
