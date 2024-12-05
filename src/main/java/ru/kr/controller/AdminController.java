package ru.kr.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import ru.kr.domain.Ganre;
import ru.kr.domain.Show;
import ru.kr.repos.GanreRepo;
import ru.kr.repos.ShowRepo;

import java.io.File;
import java.io.IOException;
import java.util.Map;
import java.util.UUID;

@Controller
@RequestMapping("/admin")
@PreAuthorize("hasAuthority('ADMIN')")
public class AdminController {

    public String adminPanel(Model model) {
        return "admin";
    }
    @Autowired
    private ShowRepo showRepo;
    @Autowired
    private GanreRepo ganreRepo;
    @Value("${upload.path}")
    private String uploadPath;
    @GetMapping("/")
    public String greeting(Map<String, Object> model) {

        return "greeting";
    }

    @GetMapping
    public String admin(@RequestParam(required = false, defaultValue = "") String filter, Model model) {
        Iterable<Show> show = showRepo.findAll();
        if (filter != null && !filter.isEmpty()) {
            show = showRepo.findByTitle(filter);
        } else {
            show = showRepo.findAll();
        }
        Iterable<Ganre> ganre = ganreRepo.findAll();
        model.addAttribute("shows", show);
        model.addAttribute("filter", filter);
        model.addAttribute("ganres", ganre);
        return "admin";
    }


    @PostMapping("/addGanre")

    public String addGanre(@RequestParam String ganreName, Map<String, Object> model){
        Ganre genre = new Ganre(ganreName);
        if (ganreName != null && !ganreName.isEmpty()) {
            ganreRepo.save(genre);
        }
        Iterable<Ganre> ganres = ganreRepo.findAll();
        Iterable<Show> shows = showRepo.findAll();
        model.put("ganres", ganres);
        model.put("shows", shows);
        return "redirect:/admin";
    }


    @PostMapping("/addShow")

    public String addShow(@RequestParam String title,
                          @RequestParam int duration,
                          @RequestParam int ageRating,
                          @RequestParam Long ganreId,
                          @RequestParam String description,
                          @RequestParam("file") MultipartFile file,
                          Map<String, Object> model) throws IOException {
        Ganre ganre = (Ganre) ganreRepo.findById(ganreId).orElse(null);
        Show show = new Show(title, duration, ageRating, ganre);
        show.setDescription(description);
        if (!file.isEmpty()){
            File upladDir = new File(uploadPath);
            if (!upladDir.exists()){
                upladDir.mkdirs();
            }
            String uuidFile = UUID.randomUUID().toString();
            String resultFilename = uuidFile + "_" + file.getOriginalFilename();
            File destinationFile = new File(uploadPath + File.separator + resultFilename);
            file.transferTo(destinationFile);
            show.setFilename(resultFilename);
        }
        showRepo.save(show);
        Iterable<Ganre> ganres = ganreRepo.findAll();
        Iterable<Show> shows = showRepo.findAll();
        model.put("ganres", ganres);
        model.put("shows", shows);
        return "redirect:/admin";
    }
    @PostMapping("/deleteShow")
    public String deleteShow(@RequestParam Long showId) {
        showRepo.deleteById(showId);
        return "redirect:/admin";
    }

    @PostMapping("/addHall")
    public String addHall(Model model)
    {
        return "redirect:/admin";
    }

}
