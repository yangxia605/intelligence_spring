package com.intelligent.dao;

import com.intelligent.model.Books;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BooksDao extends JpaRepository<Books, Integer> {

}
