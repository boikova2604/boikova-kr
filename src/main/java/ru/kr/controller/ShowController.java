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
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

@Controller
@RequestMapping("/show")
public class ShowController {
    @Autowired
    private ShowRepo showRepo;
    @Autowired
    private GanreRepo ganreRepo;
    @Value("${upload.path}")
    private String uploadPath;

    @GetMapping("/showAdminList")
    @PreAuthorize("hasAuthority('ADMIN')")
    public String showAdminList(Model model){
        model.addAttribute("shows", showRepo.findAll());
        model.addAttribute("ganres", ganreRepo.findAll());
        return "showsAdmin";
    }

    @GetMapping
    public String showList(
            @RequestParam(value = "filter", required = false, defaultValue = "") String filter,
            @RequestParam(value = "ganreId", required = false, defaultValue = "") Long ganreId,
            Model model) {
        List<Show> shows;
        Optional<Object> ganre = ganreRepo.findById(ganreId);
        if ((filter != null && !filter.isEmpty()) && ganreId != null) {
            shows = showRepo.findByTitleAndGanre(filter, ganre);
        } else if (filter != null && !filter.isEmpty()) {
            shows = showRepo.findByTitle(filter);
        } else if (ganreId != null) {
            shows = showRepo.findByGanre(ganre);
        } else {
            shows = showRepo.findAll();
        }



        model.addAttribute("shows", shows);
        model.addAttribute("ganres", ganreRepo.findAll());
        model.addAttribute("filter", filter);
        model.addAttribute("ganreId", ganreId);

        return "showsList";
    }



    @PostMapping("/addShow")
    @PreAuthorize("hasAuthority('ADMIN')")
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
        return "redirect:/show/showAdminList";
    }
    @PostMapping("/deleteShow")
    @PreAuthorize("hasAuthority('ADMIN')")
    public String deleteShow(@RequestParam Long showId) {
        showRepo.deleteById(showId);
        return "redirect:/show/showAdminList";
    }

    @GetMapping("/editShow")
    @PreAuthorize("hasAuthority('ADMIN')")
    public String editShowForm(@RequestParam Long showId, Model model){
        Show show = showRepo.findById(showId).orElse(null);
        if (show == null){
            return "redirect:/show/showAdminList";
        }
        model.addAttribute("show", show);
        model.addAttribute("ganres", ganreRepo.findAll());
        return "editShow";
    }

    @PostMapping("/editShow")
    @PreAuthorize("hasAuthority('ADMIN')")
    public String editShow(@RequestParam Long showId,
                           @RequestParam String title,
                           @RequestParam int duration,
                           @RequestParam int ageRating,
                           @RequestParam Long ganreId,
                           @RequestParam String description,
                           @RequestParam("file") MultipartFile file,
                           Map<String, Object> model) throws IOException {
        Show show = showRepo.findById(showId).orElse(null);
        if (show == null){
            return "redirect:/show/showAdminList";
        }

        Ganre ganre = (Ganre) ganreRepo.findById(ganreId).orElse(null);
        show.setTitle(title);
        show.setDuration(duration);
        show.setAgeRating(ageRating);
        show.setGanre(ganre);
        show.setDescription(description);

        if (!file.isEmpty()){
            if (show.getFilename() != null && !show.getFilename().isEmpty()){
                File oldFile = new File(uploadPath + File.separator + show.getFilename());
                if (oldFile.exists()){
                    oldFile.delete();
                }
            }
            File uploadDir = new File(uploadPath);
            if (!uploadDir.exists()){
                uploadDir.mkdirs();
            }
            String uuidFile = UUID.randomUUID().toString();
            String resultFilename = uuidFile + "_" + file.getOriginalFilename();
            File destinationFile = new File(uploadPath + File.separator + resultFilename);
            file.transferTo(destinationFile);
            show.setFilename(resultFilename);
        }

        showRepo.save(show);
        return "redirect:/show/showAdminList";
    }

}
