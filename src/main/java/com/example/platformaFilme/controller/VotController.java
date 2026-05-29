package com.example.platformaFilme.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.platformaFilme.model.Vot;
import com.example.platformaFilme.service.ClientService;
import com.example.platformaFilme.service.FilmService;
import com.example.platformaFilme.service.VotService;

@Controller
@RequestMapping("/voturi")
public class VotController {

    private final VotService service;
    private final ClientService clientService;
    private final FilmService filmService;

    public VotController(VotService service, ClientService clientService, FilmService filmService) {
        this.service = service;
        this.clientService = clientService;
        this.filmService = filmService;
    }

    @GetMapping
    public String list(Model model) {
        model.addAttribute("voturi", service.findAll());
        return "voturi/list";
    }

    @GetMapping("/nou")
    public String createForm(Model model) {
        model.addAttribute("vot", new Vot());
        model.addAttribute("clienti", clientService.findAll());
        model.addAttribute("filme", filmService.findAll());
        return "voturi/form";
    }

    @PostMapping("/nou")
    public String create(@ModelAttribute Vot vot, RedirectAttributes ra) {
    try {
        service.save(vot);
        ra.addFlashAttribute("success", "Vot adăugat.");
    } catch (Exception e) {
        String errorMessage = e.getMessage();
    
        if (errorMessage != null && errorMessage.contains("ORA-20011")) {
            ra.addFlashAttribute("error", "Nu puteți vota acest film: trebuie să îl vizionați complet înainte de a acorda o notă.");
        } else {
            ra.addFlashAttribute("error", "A apărut o eroare la salvarea votului: " + errorMessage);
        }
    }
    return "redirect:/voturi";
    }

    @GetMapping("/{id}/editare")
    public String editForm(@PathVariable Long id, Model model) {
        return service.findById(id).map(v -> {
            model.addAttribute("vot", v);
            model.addAttribute("clienti", clientService.findAll());
            model.addAttribute("filme", filmService.findAll());
            return "voturi/form";
        }).orElse("redirect:/voturi");
    }

    @PostMapping("/{id}/editare")
    public String edit(@PathVariable Long id, @ModelAttribute Vot vot, RedirectAttributes ra) {
        vot.setIdVot(id);
        try {
            service.update(vot);
            ra.addFlashAttribute("success", "Vot actualizat.");
        } catch (Exception e) {
            ra.addFlashAttribute("error", e.getMessage());
        }
        return "redirect:/voturi";
    }

    @PostMapping("/{id}/stergere")
    public String delete(@PathVariable Long id, RedirectAttributes ra) {
        try {
            service.delete(id);
            ra.addFlashAttribute("success", "Vot șters.");
        } catch (Exception e) {
            ra.addFlashAttribute("error", "Nu se poate șterge: " + e.getMessage());
        }
        return "redirect:/voturi";
    }
}