package com.intelligent.dao;

import com.intelligent.model.UserFavoriteSolution;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserFavoriteSolutionDao extends JpaRepository<UserFavoriteSolution, Integer> {
}
