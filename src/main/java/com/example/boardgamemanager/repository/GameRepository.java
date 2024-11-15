package com.example.boardgamemanager.repository;

import com.example.boardgamemanager.model.Game;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GameRepository extends JpaRepository<Game, Long> {
    
}
