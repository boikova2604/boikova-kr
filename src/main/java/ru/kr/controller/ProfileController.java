package ru.kr.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.kr.Service.UserService;
import ru.kr.domain.Order;
import ru.kr.domain.User;
import ru.kr.repos.OrderRepo;
import ru.kr.repos.UsersRepo;

import java.util.List;

@Controller
@RequestMapping("/profile")
public class ProfileController {

    @Autowired
    private OrderRepo orderRepo;
    @Autowired
    private UsersRepo usersRepo;
    @Autowired
    private UserService userService;

    @GetMapping
    public String showProfile(@AuthenticationPrincipal User user, Model model) {
        List<Order> orders = orderRepo.findAllByUserId(user.getId());
        model.addAttribute("user", user);
        model.addAttribute("orders", orders);
        return "profile";
    }


    @GetMapping("/orders/{orderId}")
    public String viewOrderDetails(@PathVariable Long orderId, Model model) {
        Order order = orderRepo.findById(orderId).orElseThrow(() ->
                new IllegalArgumentException("Order not found: " + orderId));
        model.addAttribute("order", order);
        return "orderDetails";
    }


    @PostMapping("/orders/{orderId}/cancel")
    public String cancelOrder(@PathVariable Long orderId) {
        orderRepo.deleteById(orderId);
        return "redirect:/profile";
    }


}
