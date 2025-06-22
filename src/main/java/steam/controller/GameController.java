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

@Controller
public class GameController {

    @Autowired
    private GameRepository gameRepository;

    private static final String UPLOAD_DIR = "src/main/resources/static/uploads/";

    /** 🔹 게임 목록 페이지 */
    @GetMapping("/game_list")
    public String getGameList(Model model) {
        List<Game> games = gameRepository.findAll();
        model.addAttribute("games", games);
        return "game_list";  // templates/game_list.html
    }

    /** 🔹 게임 추가 폼으로 이동 */
    @GetMapping("/game_add")
    public String showAddGameForm() {
        return "game_add";  // templates/game_add.html
    }

    /** 🔹 게임 추가 처리 */
    @PostMapping("/game_add")
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

        // 이미지 저장 처리
        String fileName = imageFile.getOriginalFilename();
        String imagePath = "/uploads/" + fileName;

        try {
            File dir = new File(UPLOAD_DIR);
            if (!dir.exists()) dir.mkdirs();
            imageFile.transferTo(new File(UPLOAD_DIR + fileName));
        } catch (IOException e) {
            e.printStackTrace();
            return "error";  // 업로드 실패 시 error.html
        }

        // 입력값 전처리
        title = title.trim().replaceAll("[,;]+$", "");
        description = description.trim();
        developer = developer.trim().replaceAll("[,;]+$", "");
        country = country.trim();
        List<String> tagList = Arrays.asList(tags.split("\\s*,\\s*"));
        String createdAt = LocalDateTime.now().toString();

        // Game 객체 생성 후 저장
        Game newGame = new Game(title, description, releaseDate, developer, imagePath,
                categories, platforms, country, tagList, createdAt);
        gameRepository.save(newGame);

        return "redirect:/game_list";
    }
}
