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

        ObjectId objId = new ObjectId(gameId);
        List<Review> reviews = reviewRepository.findByGameId(objId);

        // 🔍 작성자 필터링
        if (userId != null && !userId.trim().isEmpty()) {
            String keyword = userId.trim().toLowerCase();
            reviews = reviews.stream()
                    .filter(r -> r.getUserId().toLowerCase().contains(keyword))
                    .toList();
        }

        // 📊 정렬 처리
        switch (sort) {
            case "ratingDesc" -> reviews.sort(Comparator.comparingDouble(Review::getRating).reversed());
            case "ratingAsc" -> reviews.sort(Comparator.comparingDouble(Review::getRating));
            case "user" -> reviews.sort(Comparator.comparing(Review::getUserId));
            case "recent" -> reviews.sort(Comparator.comparing(Review::getCreatedAt).reversed());
            default -> reviews.sort(Comparator.comparing(Review::getCreatedAt).reversed());
        }

        model.addAttribute("reviews", reviews);
        model.addAttribute("gameId", gameId);
        model.addAttribute("sort", sort);
        model.addAttribute("userId", userId); // 검색값 유지

        return "board";
    }
}
