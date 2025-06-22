package steam.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import steam.model.User;
import steam.repository.UserRepository;

@Controller
public class FindPasswordController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder encoder;

    @GetMapping("/find-password")
    public String showFindPasswordForm() {
        return "find_password";
    }

    @PostMapping("/find-password")
    public String resetPassword(@RequestParam String userId,
                                 @RequestParam String email,
                                 @RequestParam String newPassword,
                                 Model model) {

        Optional<User> optionalUser = userRepository.findByUserId(userId);

        if (optionalUser.isPresent()) {
            User user = optionalUser.get();

            if (user.getEmail() != null && user.getEmail().equals(email)) {
                user.setPassword(encoder.encode(newPassword));
                userRepository.save(user);
                model.addAttribute("message", "비밀번호가 성공적으로 재설정되었습니다.");
            } else {
                model.addAttribute("error", "이메일이 일치하지 않습니다.");
            }
        } else {
            model.addAttribute("error", "존재하지 않는 사용자입니다.");
        }

        return "find_password";
    }

}
