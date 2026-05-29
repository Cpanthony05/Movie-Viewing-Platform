package com.example.platformaFilme.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.platformaFilme.model.Vizualizare;
import com.example.platformaFilme.service.ClientService;
import com.example.platformaFilme.service.VersiuneFilmService;
import com.example.platformaFilme.service.VizualizareService;

@Controller
@RequestMapping("/vizualizari")
public class VizualizareController {

    private final VizualizareService service;
    private final ClientService clientService;
    private final VersiuneFilmService versiuneService;

    public VizualizareController(VizualizareService service, ClientService clientService, VersiuneFilmService versiuneService) {
        this.service = service;
        this.clientService = clientService;
        this.versiuneService = versiuneService;
    }

    @GetMapping
    public String list(Model model) {
        model.addAttribute("vizualizari", service.findAll());
        return "vizualizari/list";
    }

    @GetMapping("/nou")
    public String createForm(Model model) {
        model.addAttribute("vizualizare", new Vizualizare());
        model.addAttribute("clienti", clientService.findAll());
        model.addAttribute("versiuni", versiuneService.findAll());
        return "vizualizari/form";
    }

    @PostMapping("/nou")
    public String create(@ModelAttribute Vizualizare vizualizare, RedirectAttributes ra) {
        try {
            service.save(vizualizare);
            ra.addFlashAttribute("success", "Vizualizare adăugată.");
        } catch (Exception e) {
            ra.addFlashAttribute("error", e.getMessage());
        }
        return "redirect:/vizualizari";
    }

    @GetMapping("/{id}/editare")
    public String editForm(@PathVariable Long id, Model model) {
        return service.findById(id).map(v -> {
            model.addAttribute("vizualizare", v);
            model.addAttribute("clienti", clientService.findAll());
            model.addAttribute("versiuni", versiuneService.findAll());
            return "vizualizari/form";
        }).orElse("redirect:/vizualizari");
    }

    @PostMapping("/{id}/editare")
    public String edit(@PathVariable Long id, @ModelAttribute Vizualizare viz, RedirectAttributes ra) {
        viz.setIdVizualizare(id);
        try {
            service.update(viz);
            ra.addFlashAttribute("success", "Vizualizare actualizată.");
        } catch (Exception e) {
            ra.addFlashAttribute("error", e.getMessage());
        }
        return "redirect:/vizualizari";
    }

    @PostMapping("/{id}/stergere")
    public String delete(@PathVariable Long id, RedirectAttributes ra) {
        try {
            service.delete(id);
            ra.addFlashAttribute("success", "Vizualizare ștearsă.");
        } catch (Exception e) {
            ra.addFlashAttribute("error", "Nu se poate șterge: " + e.getMessage());
        }
        return "redirect:/vizualizari";
    }
}