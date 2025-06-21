package steam.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import steam.model.Game;
import steam.repository.GameRepository;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;

@Controller
public class GameController {

    @Autowired
    private GameRepository gameRepository;

    @GetMapping("/game/add")
    public String showAddGameForm() {
        return "game_add"; // templates/game_add.html
    }

    @PostMapping("/game/add")
    public String addGame(@RequestParam String title,
                          @RequestParam String description,
                          @RequestParam String releaseDate,
                          @RequestParam String developer,
                          @RequestParam String image,
                          @RequestParam List<String> categories,
                          @RequestParam List<String> platforms,
                          @RequestParam String country,
                          @RequestParam String tags,
                          Model model) {

        String createdAt = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));

        List<String> tagList = Arrays.asList(tags.split("\\s*,\\s*")); 

        Game newGame = new Game(title, description, releaseDate, developer, image,
                                categories, platforms, country, tagList, createdAt);

        gameRepository.save(newGame);

        return "redirect:/board";
    }

    }

