package board.board_spring.order;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping("/orders/new")
    public String createForm() {
        return "orders/orderForm";
    }

    @PostMapping("/orders/new")
    public String create(Long memberId, String itemName, int itemPrice, Model model) {

        Order order = orderService.createOrder(memberId, itemName, itemPrice);
        model.addAttribute("order", order);
        return "orders/orderResult";
    }
}