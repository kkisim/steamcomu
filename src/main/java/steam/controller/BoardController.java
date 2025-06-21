package steam.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import steam.model.Review;
import steam.repository.ReviewRepository;

import java.util.List;

@Controller
public class BoardController {

    @Autowired
    private ReviewRepository reviewRepository;

    @GetMapping("/board")
    public String showBoard(Model model) {
        List<Review> reviews = reviewRepository.findAll(); // 전체 리뷰 목록을 불러옴
        model.addAttribute("reviews", reviews);
        return "board"; // templates/board.html
    }
}
