package com.intelligent.dao;

import com.intelligent.model.Notes;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NotesDao extends JpaRepository<Notes, Integer> {
}
