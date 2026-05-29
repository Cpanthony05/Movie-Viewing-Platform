package com.example.platformaFilme.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.platformaFilme.model.OptiuneFeedback;
import com.example.platformaFilme.service.OptiuneFeedbackService;

@Controller
@RequestMapping("/optiuni-feedback")
public class OptiuneFeedbackController {

    private final OptiuneFeedbackService service;

    public OptiuneFeedbackController(OptiuneFeedbackService service) {
        this.service = service;
    }

    @GetMapping
    public String list(Model model) {
        model.addAttribute("optiuni", service.findAll());
        return "feedback/optiuni-list";
    }

    @GetMapping("/nou")
    public String createForm(Model model) {
        model.addAttribute("optiune", new OptiuneFeedback());
        return "feedback/optiuni-form";
    }

    @PostMapping("/nou")
    public String create(@ModelAttribute OptiuneFeedback optiune, RedirectAttributes ra) {
        try {
            service.save(optiune);
            ra.addFlashAttribute("success", "Opțiune adăugată.");
        } catch (Exception e) {
            ra.addFlashAttribute("error", e.getMessage());
        }
        return "redirect:/optiuni-feedback";
    }

    @GetMapping("/{id}/editare")
    public String editForm(@PathVariable Long id, Model model) {
        return service.findById(id).map(o -> {
            model.addAttribute("optiune", o);
            return "feedback/optiuni-form";
        }).orElse("redirect:/optiuni-feedback");
    }

    @PostMapping("/{id}/editare")
    public String edit(@PathVariable Long id, @ModelAttribute OptiuneFeedback optiune, RedirectAttributes ra) {
        optiune.setIdOptiune(id);
        try {
            service.update(optiune);
            ra.addFlashAttribute("success", "Opțiune actualizată.");
        } catch (Exception e) {
            ra.addFlashAttribute("error", e.getMessage());
        }
        return "redirect:/optiuni-feedback";
    }

    @PostMapping("/{id}/stergere")
    public String delete(@PathVariable Long id, RedirectAttributes ra) {
        try {
            service.delete(id);
            ra.addFlashAttribute("success", "Opțiune ștearsă.");
        } catch (Exception e) {
            ra.addFlashAttribute("error", "Nu se poate șterge: " + e.getMessage());
        }
        return "redirect:/optiuni-feedback";
    }
}