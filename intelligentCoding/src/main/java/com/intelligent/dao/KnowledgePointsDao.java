package com.intelligent.dao;

import com.intelligent.model.KnowledgePoints;
import org.springframework.data.jpa.repository.JpaRepository;

public interface KnowledgePointsDao extends JpaRepository<KnowledgePoints, Integer> {
}
