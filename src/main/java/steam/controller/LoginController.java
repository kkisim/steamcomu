package steam.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.ui.Model;
import jakarta.servlet.http.HttpSession;

@Controller
public class LoginController {

    @GetMapping("/login")
    public String showLoginForm() {
        return "login"; // templates/login.html
    }

//    @PostMapping("/login")
//    public String processLogin(String userId, String password, HttpSession session, Model model) {
//        // 실제 구현에서는 사용자 인증을 위해 DB 조회 필요
//        // 예시: 아이디와 비밀번호가 "admin"일 때 로그인 성공 처리
//        if ("admin".equals(userId) && "admin".equals(password)) {
//            session.setAttribute("userId", userId); // 세션에 저장
//            return "redirect:/board"; // 로그인 성공 후 게시판 이동
//        } else {
//            model.addAttribute("error", true); // 로그인 실패 시 메시지 출력용
//            return "login";
//        }
//    }
    

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/login?logout";
    }
}
