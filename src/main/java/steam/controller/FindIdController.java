package steam.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import steam.repository.UserRepository;
import steam.model.User;

@Controller
public class FindIdController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/find-id")
    public String showFindIdForm() {
        return "find-id"; // templates/find-id.html
    }

    @PostMapping("/find-id")
    public String processFindId(@RequestParam String email, Model model) {
    	Optional<User> optionalUser = userRepository.findByEmail(email);
    	if (optionalUser.isPresent()) {
    	    User user = optionalUser.get();
    	    model.addAttribute("userId", user.getUserId());
    	} else {
    	    model.addAttribute("error", "잘못된 정보입니다.");
    	}


        return "find-id";
    }
}
