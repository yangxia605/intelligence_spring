package com.intelligent.dao;

import com.intelligent.model.Discussion;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DiscussionDao extends JpaRepository<Discussion, Integer> {
}
