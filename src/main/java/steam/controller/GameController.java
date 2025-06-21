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
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@Controller
public class GameController {

    @Autowired
    private GameRepository gameRepository;

    private static final String UPLOAD_DIR = "src/main/resources/static/uploads/";

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

        // 이미지 저장 경로 설정
        String imageName = imageFile.getOriginalFilename();
        String uploadDir = "src/main/resources/static/uploads/";
        String imagePath = "/uploads/" + imageName;

        try {
            File saveFile = new File(uploadDir + imageName);
            imageFile.transferTo(saveFile); // 실제 저장
        } catch (IOException e) {
            e.printStackTrace();
            return "error"; // 에러 처리
        }

        // 태그 문자열을 리스트로 변환
        List<String> tagList = Arrays.asList(tags.split("\\s*,\\s*"));
        String createdAt = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));

        Game newGame = new Game(title, description, releaseDate, developer, imagePath,
                                categories, platforms, country, tagList, createdAt);

        gameRepository.save(newGame);
        return "redirect:/board";
    }


       
}
