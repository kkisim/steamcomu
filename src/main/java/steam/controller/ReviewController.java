package steam.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import steam.model.Game;
import steam.model.Review;
import steam.repository.GameRepository;
import steam.repository.ReviewRepository;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Controller
public class ReviewController {

    @Autowired
    private GameRepository gameRepository;

    @Autowired
    private ReviewRepository reviewRepository;

    // 리뷰 작성 폼
    @GetMapping("/review/add")
    public String showReviewForm(Model model) {
        List<Game> games = gameRepository.findAll();
        System.out.println("게임 수: " + games.size()); // ★ 로그 확인용
        for (Game g : games) {
            System.out.println("게임 제목: " + g.getTitle() + " / ID: " + g.getId());
        }
        model.addAttribute("games", games);
        return "review_add";
    }


    // 리뷰 작성 처리
    @PostMapping("/review/add")
    public String submitReview(@RequestParam String userId,
                                @RequestParam String gameId,
                                @RequestParam String comment,
                                @RequestParam double rating) {

        String createdAt = LocalDateTime.now()
            .format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));

        Review review = new Review(userId, gameId, comment, rating, createdAt);
        reviewRepository.save(review);

        return "redirect:/board";
    }
}
