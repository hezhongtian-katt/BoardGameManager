package com.example.boardgamemanager.controller;

import com.example.boardgamemanager.model.Game;
import com.example.boardgamemanager.repository.GameRepository;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
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
    
 // 获取当前用户名的方法
    private String getCurrentUsername() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.isAuthenticated() &&
            !"anonymousUser".equals(authentication.getPrincipal())) {
            return authentication.getName();
        }
        return "游客"; // 如果未登录，显示“游客”
    }

    @GetMapping
    public String listGames(Model model) {
        var games = gameRepository.findAll();
        model.addAttribute("games", games);
        // 获取当前用户名
        String username = getCurrentUsername();
        model.addAttribute("username", username);
        return "game_list";
    }

    @GetMapping("/{id}")
    public String gameDetails(@PathVariable Long id, Model model) {
        model.addAttribute("game", gameRepository.findById(id).orElse(null));
     // 获取当前用户名
        String username = getCurrentUsername();
        model.addAttribute("username", username);
        return "game_details";
    }

    @GetMapping("/add")
    public String addGameForm(Model model) {
        model.addAttribute("game", new Game());
        // 获取当前用户名
        String username = getCurrentUsername();
        model.addAttribute("username", username);
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
        // 获取当前用户名
        String username = getCurrentUsername();
        model.addAttribute("username", username);
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
