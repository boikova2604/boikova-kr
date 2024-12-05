package ru.kr.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.*;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.kr.domain.*;
import ru.kr.repos.*;

import javax.transaction.Transactional;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;



@Controller
@RequestMapping("/order")
public class OrdersController {
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

    @GetMapping("/{id}")
    public String showSession(@PathVariable Long id, Model model) {

        Session session = sessionRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Session not found"));


        Hall hall = session.getHall();
        if (hall == null) {
            throw new RuntimeException("Hall not found for session with ID: " + id);
        }


        Map<String, Boolean> seatStatus = session.getSeatStatus(session.getOrders(), hall);

        System.out.println("Session ID: " + session.getId());
        System.out.println("Hall Name: " + hall.getName());
        System.out.println("Seat Status: " + seatStatus);

        model.addAttribute("session", session);
        model.addAttribute("hall", hall);
        model.addAttribute("seatStatus", seatStatus);

        return "hallForSession";
    }

    @Transactional
    @PostMapping("/confirm")
    public String confirmOrder(@RequestParam Long sessionId,
                               @RequestParam String selectedSeats,
                               @AuthenticationPrincipal User user) {

        Session session = sessionRepo.findById(sessionId)
                .orElseThrow(() -> new RuntimeException("Session not found"));

        Set<Seat> seats = new HashSet<>();
        for (String seatKey : selectedSeats.split(",")) {
            String[] parts = seatKey.split("-");
            Byte row = Byte.valueOf(parts[0]);
            Byte place = Byte.valueOf(parts[1]);
            Seat seat = seatRepo.findByRowAndPlaceAndHall(row, place, session.getHall());
            if (seat == null) {
                throw new RuntimeException("Seat not found: " + row + "-" + place);
            }
            seats.add(seat);
        }

        Order order = new Order();
        order.setUser(user);
        order.setSession(session);
        order.setSeats(seats);
        order.setCost(seats.size() * session.getPrice());
        order.setPurchase(true);

        orderRepo.save(order);
        return "redirect:/profile";
    }


}
