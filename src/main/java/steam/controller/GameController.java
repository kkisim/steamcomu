package steam.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import steam.model.Game;
import steam.repository.GameRepository;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;  // 추가

@Controller
public class GameController {

    @Autowired
    private GameRepository gameRepository;

    private static final String UPLOAD_DIR = System.getProperty("user.dir") + "/src/main/resources/static/uploads/";

    @GetMapping("/game/list")
    public String getGameList(Model model) {
        System.out.println("📌 GameController: /game/list 요청 도착");

        List<Game> games = gameRepository.findAll();

        System.out.println("📌 Mongo에서 불러온 게임 개수: " + games.size());
        games.forEach(game -> System.out.println("▶ " + game.getTitle()));

        model.addAttribute("games", games);
        return "game_list";  // templates/game_list.html
    }

    @GetMapping("/game/add")
    public String showAddGameForm() {
        return "game_add";
    }

    @PostMapping("/game/add")
    public String addGame(@RequestParam String title,
                          @RequestParam String description,
                          @RequestParam String releaseDate,
                          @RequestParam String developer,
                          @RequestParam("image") MultipartFile imageFile,
                          @RequestParam List<String> categories,
                          @RequestParam List<String> platforms,
                          @RequestParam String country,
                          @RequestParam String tags,
                          Model model) {

        File dir = new File(UPLOAD_DIR);
        if (!dir.exists()) dir.mkdirs();

        String imagePath;
        if (!imageFile.isEmpty()) {
            try {
                String originalName = imageFile.getOriginalFilename();
                String ext = originalName.substring(originalName.lastIndexOf("."));
                String uuidFileName = UUID.randomUUID().toString() + ext;

                String fullPath = UPLOAD_DIR + uuidFileName;
                imagePath = "/uploads/" + uuidFileName;

                imageFile.transferTo(new File(fullPath));
            } catch (IOException e) {
                e.printStackTrace();
                return "error";
            }
        } else {
            imagePath = "/uploads/default.png";
        }

        Game newGame = new Game(title, description, releaseDate, developer, imagePath,
                categories, platforms, country, Arrays.asList(tags.split("\\s*,\\s*")), LocalDateTime.now().toString());

        gameRepository.save(newGame);

        return "redirect:/game/list";
    }

    @GetMapping("/game/debug")
    @ResponseBody
    public List<Game> getAllGamesJson() {
        return gameRepository.findAll();
    }
}
