package ru.kr.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.kr.domain.Hall;
import ru.kr.domain.Seat;
import ru.kr.repos.HallRepo;
import ru.kr.repos.SeatRepo;

@Controller
@RequestMapping("/hall")
public class HallsController {
    @Autowired
    private HallRepo hallRepo;
    @Autowired
    private SeatRepo seatRepo;

    @GetMapping
    public String hallsList(Model model) {
        model.addAttribute("halls", hallRepo.findAll());
        return "hallList";
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping("/addHall")
    public String addHall(@RequestParam String hallName,
                          @RequestParam int rows,
                          @RequestParam int seatsPerRow,
                          Model model) {
        if (hallName == null || hallName.isBlank() || rows <= 0 || seatsPerRow <= 0) {
            model.addAttribute("error", "Invalid input");
            return "hallsAdmin";
        }

        Hall hall = new Hall();
        hall.setName(hallName);
        hall.setRows(rows);
        hall.setSeatsPerRow(seatsPerRow);
        hallRepo.save(hall);

        for (int row = 1; row <= hall.getRows(); row++) {
            for (int place = 1; place <= hall.getSeatsPerRow(); place++) {
                Seat seat = new Seat();
                seat.setRow((byte) row);
                seat.setPlace((byte) place);
                seat.setHall(hall);
                seatRepo.save(seat);
            }
        }

        return "redirect:/hall/admin";
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping("/delete")
    public String deleteHall(@RequestParam Long hallId, Model model) {
        if (!hallRepo.existsById(hallId)) {
            model.addAttribute("error", "Hall not found");
            return "hallsAdmin";
        }

        hallRepo.deleteById(hallId);
        return "redirect:/hall/admin";
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/admin")
    public String adminHallsList(Model model) {
        model.addAttribute("halls", hallRepo.findAll());
        return "hallsAdmin";
    }
}
