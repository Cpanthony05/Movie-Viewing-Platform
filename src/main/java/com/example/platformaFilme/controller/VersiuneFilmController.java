package com.example.platformaFilme.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.platformaFilme.model.VersiuneFilm;
import com.example.platformaFilme.service.FilmService;
import com.example.platformaFilme.service.VersiuneFilmService;

@Controller
@RequestMapping("/versiuni")
public class VersiuneFilmController {

    private final VersiuneFilmService service;
    private final FilmService filmService;

    public VersiuneFilmController(VersiuneFilmService service, FilmService filmService) {
        this.service = service;
        this.filmService = filmService;
    }

    @GetMapping
    public String list(Model model) {
        model.addAttribute("versiuni", service.findAll());
        return "versiuni/list";
    }

    @GetMapping("/nou")
    public String createForm(Model model) {
        model.addAttribute("versiune", new VersiuneFilm());
        model.addAttribute("filme", filmService.findAll());
        return "versiuni/form";
    }

    @PostMapping("/nou")
    public String create(@ModelAttribute VersiuneFilm versiune, RedirectAttributes ra) {
        try {
            service.save(versiune);
            ra.addFlashAttribute("success", "Versiune adăugată.");
        } catch (Exception e) {
            ra.addFlashAttribute("error", e.getMessage());
        }
        return "redirect:/versiuni";
    }

    @GetMapping("/{id}/editare")
    public String editForm(@PathVariable Long id, Model model) {
        return service.findById(id).map(v -> {
            model.addAttribute("versiune", v);
            model.addAttribute("filme", filmService.findAll());
            return "versiuni/form";
        }).orElse("redirect:/versiuni");
    }

    @PostMapping("/{id}/editare")
    public String edit(@PathVariable Long id, @ModelAttribute VersiuneFilm versiune, RedirectAttributes ra) {
        versiune.setIdVersiune(id);
        try {
            service.update(versiune);
            ra.addFlashAttribute("success", "Versiune actualizată.");
        } catch (Exception e) {
            ra.addFlashAttribute("error", e.getMessage());
        }
        return "redirect:/versiuni";
    }

    @PostMapping("/{id}/stergere")
    public String delete(@PathVariable Long id, RedirectAttributes ra) {
    try {
        service.delete(id);
        ra.addFlashAttribute("success", "Versiunea a fost ștearsă cu succes.");
    } catch (Exception e) {
        if (e.getMessage().contains("ORA-02292") || e.getMessage().contains("integrity constraint")) {
            ra.addFlashAttribute("error", "Nu se poate șterge versiunea deoarece există vizualizări sau înregistrări asociate acesteia.");
        } else {
            ra.addFlashAttribute("error", "Eroare neprevăzută: " + e.getMessage());
        }
    }
    return "redirect:/versiuni";
    }
}