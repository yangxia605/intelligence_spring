package com.intelligent.dao;

import com.intelligent.model.Solution;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SolutionDao extends JpaRepository<Solution, Integer> {
}
