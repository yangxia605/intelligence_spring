package com.intelligent.dao;

import com.intelligent.model.BookChapters;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookChaptersDao extends JpaRepository<BookChapters, Integer> {
}
