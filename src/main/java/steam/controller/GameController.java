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

// 정적 이미지가 저장될 실제 경로
private static final String UPLOAD_DIR = System.getProperty("user.dir") + "/src/main/resources/static/images/";

// 게임 목록 + 검색
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

// 게임 추가 폼
@GetMapping("/game/add")
public String showAddGameForm() {
return "game_add"; // templates/game_add.html
}

// 게임 추가 처리
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
String ext = "";

if (originalName != null && originalName.contains(".")) {
ext = originalName.substring(originalName.lastIndexOf("."));
}

String safeTitle = title.replaceAll("[^a-zA-Z0-9가-힣]", "_").toLowerCase();
String fileName = safeTitle + ext;

String fullPath = UPLOAD_DIR + fileName;
imageFile.transferTo(new File(fullPath));

// 경로를 static 하위인 /images/ 기준으로 저장
imagePath = "/images/" + fileName;

} catch (IOException e) {
e.printStackTrace();
return "error";
}
} else {
// 기본 이미지
imagePath = "/images/default.png";
}

Game newGame = new Game(
title,
description,
releaseDate,
developer,
imagePath,
categories,
platforms,
country,
Arrays.asList(tags.split("\\s*,\\s*")),
LocalDateTime.now().toString()
);

gameRepository.save(newGame);
return "redirect:/game/list";
}

// JSON으로 전체 게임 디버깅 출력
@GetMapping("/game/debug")
@ResponseBody
public List<Game> getAllGamesJson() {
return gameRepository.findAll();
}
}
