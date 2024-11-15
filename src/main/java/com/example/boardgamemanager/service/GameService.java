package com.example.boardgamemanager.service;

import com.example.boardgamemanager.model.Game;
import com.example.boardgamemanager.repository.GameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class GameService {

    @Autowired
    private GameRepository gameRepository;

    // ゲーム取得
    public List<Game> getAllGames() {
        return gameRepository.findAll();
    }

    // IDからゲーム取得
    public Optional<Game> getGameById(Long id) {
        return gameRepository.findById(id);
    }

    // ゲーム追加
    public Game addGame(Game game) {
        return gameRepository.save(game);
    }

    // ゲーム削除
    public void deleteGame(Long id) {
        gameRepository.deleteById(id);
    }
}
