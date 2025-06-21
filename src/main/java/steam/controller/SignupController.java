package steam.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;
import steam.model.User;
import steam.repository.UserRepository;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Controller
public class SignupController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/signup")
    public String showSignupForm() {
        return "signup"; // templates/signup.html
    }

    @PostMapping("/signup")
    public String processSignup(
            @RequestParam String userId,
            @RequestParam String nickname,
            @RequestParam String email,
            @RequestParam String password,
            Model model
    ) {
        // 중복 체크 예시 (실제는 userId나 email 중복 확인 필요)
        if (userRepository.findByUserId(userId) != null) {
            model.addAttribute("error", "이미 사용 중인 아이디입니다.");
            return "signup";
        }

        String createdAt = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        User newUser = new User(userId, nickname, email, password, createdAt);

        userRepository.save(newUser);
        return "redirect:/login";
    }
}
