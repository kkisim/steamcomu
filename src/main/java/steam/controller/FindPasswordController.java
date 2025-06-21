package steam.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import steam.model.User;
import steam.repository.UserRepository;

@Controller
public class FindPasswordController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/find-password")
    public String showFindPasswordForm() {
        return "find-password"; // templates/find-password.html
    }

    @PostMapping("/find-password")
    public String processFindPassword(@RequestParam String userId, Model model) {
        User user = userRepository.findByUserId(userId);

        if (user != null) {
            model.addAttribute("password", user.getPassword());
        } else {
            model.addAttribute("error", "잘못된 정보입니다.");
        }

        return "find-password";
    }
}
