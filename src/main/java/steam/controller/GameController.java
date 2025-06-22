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

    @GetMapping("/game/list")
    public String getGameList(Model model) {
        System.out.println("📌 GameController: /game/list 요청 도착");
        

        List<Game> games = gameRepository.findAll();

        System.out.println("📌 Mongo에서 불러온 게임 개수: " + games.size());
        System.out.println("🔥 저장된 게임들: ");
        games.forEach(game -> System.out.println("▶ " + game.getTitle()));


        model.addAttribute("games", games);
        return "game_list";
    }


    @PostMapping("/game/add")
    public String addGame(@RequestParam String title,
                          @RequestParam String description,
                          @RequestParam String releaseDate,
                          @RequestParam String developer,
                          @RequestParam("image") MultipartFile imageFile, // 수정!
                          @RequestParam List<String> categories,
                          @RequestParam List<String> platforms,
                          @RequestParam String country,
                          @RequestParam String tags,
                          Model model) {

        // 이미지 저장 디렉토리 설정
        String uploadDir = "src/main/resources/static/uploads/";
        String fileName = imageFile.getOriginalFilename();
        String imagePath = "/uploads/" + fileName;

        try {
            File dir = new File(uploadDir);
            if (!dir.exists()) dir.mkdirs(); // 디렉토리 없으면 생성
            imageFile.transferTo(new File(uploadDir + fileName)); // 이미지 저장
        } catch (IOException e) {
            e.printStackTrace();
            return "error";
        }

        // 전처리
        title = title.trim().replaceAll("[,;]+$", "");
        description = description.trim();
        developer = developer.trim().replaceAll("[,;]+$", "");
        country = country.trim();
        List<String> tagList = Arrays.asList(tags.split("\\s*,\\s*"));
        String createdAt = LocalDateTime.now().toString(); // ISO-8601 형식


        // 객체 생성 및 저장
        Game newGame = new Game(title, description, releaseDate, developer, imagePath,
                categories, platforms, country, tagList, createdAt);
        gameRepository.save(newGame);

        return "redirect:/board";
    }




       
}
