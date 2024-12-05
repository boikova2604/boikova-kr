package ru.kr.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import ru.kr.domain.Role;
import ru.kr.domain.User;
import ru.kr.repos.UsersRepo;

import java.util.Collections;

@Controller
public class RegistrationController {
    @Autowired
    private UsersRepo usersRepo;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @GetMapping("/registration")
    public String registration(){
        return "registration";
    }

    @PostMapping("/registration")
    public String addUser(User user, Model model){
        User userFromDb = usersRepo.findByUsername(user.getUsername());
        if (userFromDb != null){
            model.addAttribute("message", "Пользователь с таким логином уже существует.");
            return "registration";
        }
        user.setActive(true);
        user.setRoles(Collections.singleton(Role.USER));
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        usersRepo.save(user);

        return "redirect:/login";
    }
}
