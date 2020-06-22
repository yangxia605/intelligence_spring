package com.intelligent.dao;

import com.intelligent.model.UseCase;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UseCaseDao extends JpaRepository<UseCase, Integer> {
}
