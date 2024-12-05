package ru.kr.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.kr.domain.*;
import ru.kr.repos.*;

import java.util.*;

@Controller
@RequestMapping("/session")
public class SessionsController {
    @Autowired
    private SessionRepo sessionRepo;
    @Autowired
    private ShowRepo showRepo;
    @Autowired
    private HallRepo hallRepo;
    @Autowired
    private UsersRepo usersRepo;
    @Autowired
    private SeatRepo seatRepo;
    @Autowired
    private OrderRepo orderRepo;
    @GetMapping
    public String sessionList(@RequestParam(value = "filter", required = false, defaultValue = "") String filter,
                              @RequestParam(value = "hallId", required = false) Long hallId,
                              @RequestParam(value = "sortSession", required = false, defaultValue = "asc") String sortSession,
                              Model model) {
        List<Show> shows =  showRepo.findByTitle(filter);
        List<Session> sessions;
        Optional<Hall> hall = hallId == null ? Optional.empty() : hallRepo.findById(hallId);
        Date currentDate = new Date();
        if ((filter != null && !filter.isEmpty()) && hallId != null) {
            sessions = sessionRepo.findByShowInAndHallAndDateAfter(shows, hall.get(), currentDate);
        } else if ((filter != null && !filter.isEmpty())) {
            sessions = sessionRepo.findByShowInAndDateAfter(shows, currentDate);
        } else if (hallId != null) {
            sessions = sessionRepo.findByHallAndDateAfter(hall.get(), currentDate);
        } else {
            sessions = sessionRepo.findByDateAfter(currentDate);
        }
        if ("mny".equalsIgnoreCase(sortSession)) {
            sessions.sort(Comparator.comparing(Session::getPrice));
        } else {
            sessions.sort(Comparator.comparing(Session::getDate));
        }

        model.addAttribute("shows", shows);
        model.addAttribute("halls", hallRepo.findAll());
        model.addAttribute("sessions", sessions);
        model.addAttribute("filter", filter);
        model.addAttribute("sortSession", sortSession);
        return "sessionList";
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping("/addSession")
    public String addSession(@RequestParam Long showId,
                             @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm") Date date,
                             @RequestParam Long hallId,
                             @RequestParam double price,
                             Model model) {
        Show show = showRepo.findById(showId).orElse(null);
        Hall hall = hallRepo.findById(hallId).orElse(null);

        if (show == null || hall == null) {
            model.addAttribute("error", "Invalid show or hall ID");
            return "sessionsAdmin";
        }

        if (price <= 0) {
            model.addAttribute("error", "Price must be positive");
            return "sessionsAdmin";
        }

        Session session = new Session();
        session.setShow(show);
        session.setDate(date);
        session.setHall(hall);
        session.setPrice(price);
        sessionRepo.save(session);



        return "redirect:/session/admin";
    }

    @GetMapping("/edit")
    @PreAuthorize("hasAuthority('ADMIN')")
    public String editSessionForm(@RequestParam Long sessionId, Model model){
        Session session = sessionRepo.findById(sessionId).orElse(null);
        if (session == null){
            return "redirect:/session/admin";
        }
        model.addAttribute("session", session);
        model.addAttribute("shows", showRepo.findAll());
        return "editSession";
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping("/edit")
    public String editSession(@RequestParam Long sessionId,
                              @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm") Date date,
                              @RequestParam double price,
                              Model model) {
        Session session = sessionRepo.findById(sessionId).orElse(null);


        if (session == null){
            model.addAttribute("error", "Сеанс не найден.");
            return "redirect:/session/admin";
        }

        if (price <= 0) {
            model.addAttribute("error", "Цена должна быть положительной.");
            model.addAttribute("session", session);
            return "editSession";
        }

        session.setDate(date);
        session.setPrice(price);
        sessionRepo.save(session);
        return "redirect:/session/admin";
    }


    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping("/delete")
    public String deleteSession(@RequestParam Long sessionId) {
        if (!sessionRepo.existsById(sessionId)) {
            throw new IllegalArgumentException("Invalid session ID: " + sessionId);
        }
        sessionRepo.deleteById(sessionId);
        return "redirect:/session/admin";
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/admin")
    public String adminSessions(Model model) {
        model.addAttribute("sessions", sessionRepo.findAll());
        model.addAttribute("shows", showRepo.findAll());
        model.addAttribute("halls", hallRepo.findAll());
        return "sessionsAdmin";
    }




}
