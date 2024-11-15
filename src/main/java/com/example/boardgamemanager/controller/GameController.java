package com.example.boardgamemanager.controller;

import com.example.boardgamemanager.model.Game;
import com.example.boardgamemanager.repository.GameRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/games")
public class GameController {

    private final GameRepository gameRepository;

    public GameController(GameRepository gameRepository) {
        this.gameRepository = gameRepository;
    }

    @GetMapping
    public String listGames(Model model) {
    	System.out.println("Fetching all games from the database...");
        var games = gameRepository.findAll();
        System.out.println("Number of games found: " + games.size());  // 输出找到的游戏数量
        model.addAttribute("games", games);
        return "game_list";
    }

    @GetMapping("/{id}")
    public String gameDetails(@PathVariable Long id, Model model) {
        model.addAttribute("game", gameRepository.findById(id).orElse(null));
        return "game_details";
    }

    @GetMapping("/add")
    public String addGameForm(Model model) {
        model.addAttribute("game", new Game());
        return "add_game";
    }

    @PostMapping("/add")
    public String addGame(Game game) {
        gameRepository.save(game);
        return "redirect:/games";
    }

    @GetMapping("/update/{id}")
    public String updateGameForm(@PathVariable Long id, Model model) {
        model.addAttribute("game", gameRepository.findById(id).orElse(null));
        return "update_game";
    }

    @PostMapping("/update/{id}")
    public String updateGame(@PathVariable Long id, Game game) {
        game.setId(id);
        gameRepository.save(game);
        return "redirect:/games";
    }

    @GetMapping("/delete/{id}")
    public String deleteGame(@PathVariable Long id) {
        gameRepository.deleteById(id);
        return "redirect:/games";
    }
}
