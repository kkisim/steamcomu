package steam.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
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
    
    @Autowired
    private BCryptPasswordEncoder passwordEncoder; // 🔐 암호화기 추가

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
            @RequestParam String passwordConfirm,
            Model model
    ) {
        // 아이디 중복 체크
        if (userRepository.findByUserId(userId).isPresent()) {
            model.addAttribute("error", "이미 사용 중인 아이디입니다.");
            return "signup";
        }

        // 비밀번호 확인 일치 여부
        if (!password.equals(passwordConfirm)) {
            model.addAttribute("error", "비밀번호가 일치하지 않습니다.");
            return "signup";
        }

        // 암호화 + 생성일 설정
        String encodedPassword = passwordEncoder.encode(password);
        String createdAt = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));

        // 유저 객체 생성 및 저장
        User newUser = new User(userId, nickname, email, encodedPassword, createdAt);
        userRepository.save(newUser);

        // 로그인 페이지로 리다이렉트
        return "redirect:/login";
    }


}
