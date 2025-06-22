package steam.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import steam.model.Game;
import steam.repository.GameRepository;

import java.util.List;

@Controller
public class GameController {

    @Autowired
    private GameRepository gameRepository;

    @GetMapping("/game/list")
    public String showGameList(@RequestParam(required = false) String keyword, Model model) {
        List<Game> games;

        if (keyword != null && !keyword.trim().isEmpty()) {
            games = gameRepository.findByTitleContainingIgnoreCase(keyword.trim());
        } else {
            games = gameRepository.findAll();
        }

        model.addAttribute("games", games);
        model.addAttribute("keyword", keyword);

        return "game_list"; // templates/game_list.html
    }
}
