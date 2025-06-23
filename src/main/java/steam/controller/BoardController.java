package steam.controller;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import steam.repository.ReviewRepository;
import steam.model.Review;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;  // ★ 이 부분 꼭 추가

@Controller
public class BoardController {

    @Autowired
    private ReviewRepository reviewRepository;

    @GetMapping("/board/{gameId}")
    public String showBoardByGame(
            @PathVariable String gameId,
            @RequestParam(defaultValue = "recent") String sort,
            @RequestParam(required = false) String userId,
            Model model) {

        ObjectId objId;
        try {
            objId = new ObjectId(gameId);
        } catch (IllegalArgumentException e) {
            model.addAttribute("errorMessage", "잘못된 게임 ID입니다.");
            return "error"; // 에러 뷰 필요
        }

        List<Review> reviews = reviewRepository.findByGameId(objId);

        if (userId != null && !userId.trim().isEmpty()) {
            String keyword = userId.trim().toLowerCase();
            reviews = reviews.stream()
                    .filter(r -> r.getUserId().toLowerCase().contains(keyword))
                    .collect(Collectors.toList());  // ← 여기 수정됨
        }

        switch (sort) {
            case "ratingDesc" -> reviews.sort(Comparator.comparingDouble(Review::getRating).reversed());
            case "ratingAsc" -> reviews.sort(Comparator.comparingDouble(Review::getRating));
            case "user" -> reviews.sort(Comparator.comparing(Review::getUserId));
            default -> reviews.sort(Comparator.comparing(Review::getCreatedAt).reversed());
        }

        model.addAttribute("reviews", reviews);
        model.addAttribute("gameId", gameId);
        model.addAttribute("sort", sort);
        model.addAttribute("userId", userId);

        return "board";
    }

}
