package ru.kr.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.kr.Service.UserService;
import ru.kr.domain.Role;
import ru.kr.domain.User;
import ru.kr.repos.OrderRepo;

import java.util.*;

@Controller
@RequestMapping("/user")
@PreAuthorize("hasAuthority('ADMIN')")
public class UserController {

    @Autowired
    UserService userService;
    @Autowired
    OrderRepo orderRepo;

    @GetMapping
    public String userList(@RequestParam(required = false, defaultValue = "") String filter, Model model){
        List<User> users;
        if (filter != null && !filter.isEmpty()) {
            User user = userService.findByUsername(filter);
            if (user != null) {
                users = new ArrayList<>();
                users.add(user);
            } else {
                users = new ArrayList<>();
            }
        } else {
           users = userService.findAll();
        }
        model.addAttribute("users", users);
        model.addAttribute("filter", filter);
        return "userList";
    }

    @GetMapping("{user}")
    public String userEditForm(@PathVariable User user, Model model){
        model.addAttribute("user", user);
        model.addAttribute("roles", Role.values());
        return "userEdit";
    }

    @PostMapping
    public String userSave(
            @RequestParam String username,
            @RequestParam(required = false) String[] roles,
            @RequestParam("userId") User user) {

        userService.saveUser(user, username ,roles);
        return "redirect:/user";
    }

    @PostMapping("/deleteUser")
    public String deleteUser(@RequestParam Long userId) {
        userService.deleteById(userId);
        return "redirect:/user";
    }
    @PostMapping("/order/{orderId}/cancel")
    public String cancelOrder(@PathVariable Long orderId) {
        orderRepo.deleteById(orderId);
        return "redirect:/user";
    }

}
