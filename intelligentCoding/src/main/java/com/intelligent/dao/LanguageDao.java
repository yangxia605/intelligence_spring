package com.intelligent.dao;

import com.intelligent.model.Language;
import org.springframework.data.jpa.repository.JpaRepository;


public interface LanguageDao extends JpaRepository<Language, Integer> {

}
