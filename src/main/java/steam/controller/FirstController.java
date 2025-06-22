package steam.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class FirstController {

    @GetMapping("/")
    public String showFirstPage() {
        return "first";  // src/main/resources/templates/first.html 렌더링
    }
}
