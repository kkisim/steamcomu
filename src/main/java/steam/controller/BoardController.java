package steam.controller;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import steam.repository.ReviewRepository;
import steam.model.Review;

import java.util.List;

@Controller
public class BoardController {

    @Autowired
    private ReviewRepository reviewRepository;

    // 특정 게임 ID의 리뷰만 보여주는 페이지
    @GetMapping("/board/{gameId}")
    public String showBoardByGame(@PathVariable String gameId, Model model) {
        ObjectId objId = new ObjectId(gameId);
        List<Review> reviews = reviewRepository.findByGameId(objId);
        model.addAttribute("reviews", reviews);
        return "board";  // templates/board.html
    }
}
