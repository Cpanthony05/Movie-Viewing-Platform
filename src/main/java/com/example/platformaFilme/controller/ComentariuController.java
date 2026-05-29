package com.example.platformaFilme.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.platformaFilme.model.Comentariu;
import com.example.platformaFilme.service.ActorService;
import com.example.platformaFilme.service.ClientService;
import com.example.platformaFilme.service.ComentariuService;
import com.example.platformaFilme.service.FilmService;

@Controller
@RequestMapping("/comentarii")
public class ComentariuController {

    private final ComentariuService service;
    private final ClientService clientService;
    private final FilmService filmService;
    private final ActorService actorService;

    public ComentariuController(ComentariuService service, ClientService clientService,
                                FilmService filmService, ActorService actorService) {
        this.service = service;
        this.clientService = clientService;
        this.filmService = filmService;
        this.actorService = actorService;
    }

    @GetMapping
    public String list(Model model) {
        model.addAttribute("comentarii", service.findAll());
        return "comentarii/list";
    }

    @GetMapping("/nou")
    public String createForm(Model model) {
        model.addAttribute("comentariu", new Comentariu());
        model.addAttribute("clienti", clientService.findAll());
        model.addAttribute("filme", filmService.findAll());
        model.addAttribute("actori", actorService.findAll());
        return "comentarii/form";
    }

    @PostMapping("/nou")
    public String create(@ModelAttribute Comentariu comentariu, RedirectAttributes ra) {
        try {
            service.save(comentariu);
            ra.addFlashAttribute("success", "Comentariu adăugat.");
        } catch (Exception e) {
            ra.addFlashAttribute("error", e.getMessage());
        }
        return "redirect:/comentarii";
    }

    @GetMapping("/{id}/editare")
    public String editForm(@PathVariable Long id, Model model) {
        return service.findById(id).map(c -> {
            model.addAttribute("comentariu", c);
            model.addAttribute("clienti", clientService.findAll());
            model.addAttribute("filme", filmService.findAll());
            model.addAttribute("actori", actorService.findAll());
            return "comentarii/form";
        }).orElse("redirect:/comentarii");
    }

    @PostMapping("/{id}/editare")
    public String edit(@PathVariable Long id, @ModelAttribute Comentariu comentariu, RedirectAttributes ra) {
        comentariu.setIdComentariu(id);
        try {
            service.update(comentariu);
            ra.addFlashAttribute("success", "Comentariu actualizat.");
        } catch (Exception e) {
            ra.addFlashAttribute("error", e.getMessage());
        }
        return "redirect:/comentarii";
    }

    @PostMapping("/{id}/stergere")
    public String delete(@PathVariable Long id, RedirectAttributes ra) {
        try {
            service.delete(id);
            ra.addFlashAttribute("success", "Comentariu șters.");
        } catch (Exception e) {
            ra.addFlashAttribute("error", "Nu se poate șterge: " + e.getMessage());
        }
        return "redirect:/comentarii";
    }
}