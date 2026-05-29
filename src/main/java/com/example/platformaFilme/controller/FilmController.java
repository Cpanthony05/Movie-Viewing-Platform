package com.example.platformaFilme.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.platformaFilme.model.Film;
import com.example.platformaFilme.service.FilmService;

@Controller
@RequestMapping("/filme")
public class FilmController {

    private final FilmService service;

    public FilmController(FilmService service) {
        this.service = service;
    }

    @GetMapping
    public String list(Model model) {
        model.addAttribute("filme", service.findAll());
        return "filme/list";
    }

    @GetMapping("/nou")
    public String createForm(Model model) {
        model.addAttribute("film", new Film());
        return "filme/form";
    }

    @PostMapping("/nou")
    public String create(@ModelAttribute Film film, RedirectAttributes ra) {
        try {
            service.save(film);
            ra.addFlashAttribute("success", "Film adăugat cu succes.");
        } catch (Exception e) {
            ra.addFlashAttribute("error", e.getMessage());
        }
        return "redirect:/filme";
    }

    @GetMapping("/{id}/editare")
    public String editForm(@PathVariable Long id, Model model) {
        return service.findById(id).map(f -> {
            model.addAttribute("film", f);
            return "filme/form";
        }).orElse("redirect:/filme");
    }

    @PostMapping("/{id}/editare")
    public String edit(@PathVariable Long id, @ModelAttribute Film film, RedirectAttributes ra) {
        film.setIdFilm(id);
        try {
            service.update(film);
            ra.addFlashAttribute("success", "Film actualizat.");
        } catch (Exception e) {
            ra.addFlashAttribute("error", e.getMessage());
        }
        return "redirect:/filme";
    }

    @PostMapping("/{id}/stergere")
public String delete(@PathVariable Long id, RedirectAttributes ra) {
    try {
        service.delete(id);
        ra.addFlashAttribute("success", "Film șters cu succes.");
    } catch (Exception e) {
        if (e.getMessage() != null && e.getMessage().contains("ORA-02292")) {
            ra.addFlashAttribute("error", "Nu se poate șterge filmul deoarece există versiuni sau înregistrări dependente asociate acestuia. Ștergeți acele înregistrări mai întâi.");
        } else {
            ra.addFlashAttribute("error", "Eroare la ștergere: " + e.getMessage());
        }
    }
    return "redirect:/filme";
}
}