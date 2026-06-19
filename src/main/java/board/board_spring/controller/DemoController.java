package board.board_spring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DemoController {
    // GET /demo 요청이 들어오면 해당 메서드가 실행된다.
    @GetMapping("demo")
    public String demo(Model model) {
        // 1. Model은 Controller에서 View로 데이터를 전달하는 역할을 한다.
        // 2. addAttribute("data", value)로 등록한 데이터는 View에서 ${data}로 접근할 수 있다.
        model.addAttribute("data", "hello!");

        // 3. View 이름을 반환하면 ViewResolver가 templates/board.html을 렌더링한다.
        return "demo";
    }
}
