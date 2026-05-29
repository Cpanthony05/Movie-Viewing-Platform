package com.example.platformaFilme.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.platformaFilme.model.FeedbackSelectat;
import com.example.platformaFilme.service.ClientService;
import com.example.platformaFilme.service.FeedbackSelectatService;
import com.example.platformaFilme.service.FilmService;
import com.example.platformaFilme.service.OptiuneFeedbackService;

@Controller
@RequestMapping("/feedback-selectat")
public class FeedbackSelectatController {

    private final FeedbackSelectatService service;
    private final ClientService clientService;
    private final FilmService filmService;
    private final OptiuneFeedbackService optiuneService;

    public FeedbackSelectatController(FeedbackSelectatService service, ClientService clientService,
                                      FilmService filmService, OptiuneFeedbackService optiuneService) {
        this.service = service;
        this.clientService = clientService;
        this.filmService = filmService;
        this.optiuneService = optiuneService;
    }

    @GetMapping
    public String list(Model model) {
        model.addAttribute("feedbackuri", service.findAll());
        return "feedback/selectat-list";
    }

    @GetMapping("/nou")
    public String createForm(Model model) {
        model.addAttribute("feedback", new FeedbackSelectat());
        model.addAttribute("clienti", clientService.findAll());
        model.addAttribute("filme", filmService.findAll());
        model.addAttribute("optiuni", optiuneService.findAll());
        return "feedback/selectat-form";
    }

    @PostMapping("/nou")
    public String create(@ModelAttribute FeedbackSelectat feedback, RedirectAttributes ra) {
        try {
            service.save(feedback);
            ra.addFlashAttribute("success", "Feedback adăugat.");
        } catch (Exception e) {
            ra.addFlashAttribute("error", e.getMessage());
        }
        return "redirect:/feedback-selectat";
    }

    @GetMapping("/{id}/editare")
    public String editForm(@PathVariable Long id, Model model) {
        return service.findById(id).map(fs -> {
            model.addAttribute("feedback", fs);
            model.addAttribute("clienti", clientService.findAll());
            model.addAttribute("filme", filmService.findAll());
            model.addAttribute("optiuni", optiuneService.findAll());
            return "feedback/selectat-form";
        }).orElse("redirect:/feedback-selectat");
    }

    @PostMapping("/{id}/editare")
    public String edit(@PathVariable Long id, @ModelAttribute FeedbackSelectat feedback, RedirectAttributes ra) {
        feedback.setIdFeedback(id);
        try {
            service.update(feedback);
            ra.addFlashAttribute("success", "Feedback actualizat.");
        } catch (Exception e) {
            ra.addFlashAttribute("error", e.getMessage());
        }
        return "redirect:/feedback-selectat";
    }

    @PostMapping("/{id}/stergere")
    public String delete(@PathVariable Long id, RedirectAttributes ra) {
        try {
            service.delete(id);
            ra.addFlashAttribute("success", "Feedback șters.");
        } catch (Exception e) {
            ra.addFlashAttribute("error", "Nu se poate șterge: " + e.getMessage());
        }
        return "redirect:/feedback-selectat";
    }
}