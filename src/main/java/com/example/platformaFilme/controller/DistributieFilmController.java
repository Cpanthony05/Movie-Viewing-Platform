package com.example.platformaFilme.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.platformaFilme.model.DistributieFilm;
import com.example.platformaFilme.service.ActorService;
import com.example.platformaFilme.service.DistributieFilmService;
import com.example.platformaFilme.service.FilmService;

@Controller
@RequestMapping("/distributie")
public class DistributieFilmController {

    private final DistributieFilmService service;
    private final FilmService filmService;
    private final ActorService actorService;

    public DistributieFilmController(DistributieFilmService service, FilmService filmService, ActorService actorService) {
        this.service = service;
        this.filmService = filmService;
        this.actorService = actorService;
    }

    @GetMapping
    public String list(Model model) {
        model.addAttribute("distributii", service.findAll());
        return "distributie/list";
    }

    @GetMapping("/nou")
    public String createForm(Model model) {
        model.addAttribute("distributie", new DistributieFilm());
        model.addAttribute("filme", filmService.findAll());
        model.addAttribute("actori", actorService.findAll());
        return "distributie/form";
    }

    @PostMapping("/nou")
    public String create(@ModelAttribute DistributieFilm distributie, RedirectAttributes ra) {
        try {
            service.save(distributie);
            ra.addFlashAttribute("success", "Distribuție adăugată.");
        } catch (Exception e) {
            ra.addFlashAttribute("error", e.getMessage());
        }
        return "redirect:/distributie";
    }

    @GetMapping("/{idFilm}/{idActor}/editare")
    public String editForm(@PathVariable Long idFilm, @PathVariable Long idActor, Model model) {
        model.addAttribute("filme", filmService.findAll());
        model.addAttribute("actori", actorService.findAll());
        DistributieFilm d = new DistributieFilm();
        d.setIdFilm(idFilm);
        d.setIdActor(idActor);
        model.addAttribute("distributie", d);
        model.addAttribute("editMode", true);
        return "distributie/form";
    }

    @PostMapping("/{idFilm}/{idActor}/editare")
    public String edit(@PathVariable Long idFilm, @PathVariable Long idActor,
                       @RequestParam String rol, RedirectAttributes ra) {
        try {
            service.update(idFilm, idActor, rol);
            ra.addFlashAttribute("success", "Rol actualizat.");
        } catch (Exception e) {
            ra.addFlashAttribute("error", e.getMessage());
        }
        return "redirect:/distributie";
    }

    @PostMapping("/{idFilm}/{idActor}/stergere")
    public String delete(@PathVariable Long idFilm, @PathVariable Long idActor, RedirectAttributes ra) {
        try {
            service.delete(idFilm, idActor);
            ra.addFlashAttribute("success", "Distribuție ștearsă.");
        } catch (Exception e) {
            ra.addFlashAttribute("error", "Nu se poate șterge: " + e.getMessage());
        }
        return "redirect:/distributie";
    }
}