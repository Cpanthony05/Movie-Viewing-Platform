package com.example.platformaFilme.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.platformaFilme.model.Actor;
import com.example.platformaFilme.service.ActorService;

@Controller
@RequestMapping("/actori")
public class ActorController {

    private final ActorService service;

    public ActorController(ActorService service) {
        this.service = service;
    }

    @GetMapping
    public String list(Model model) {
        model.addAttribute("actori", service.findAll());
        return "actori/list";
    }

    @GetMapping("/nou")
    public String createForm(Model model) {
        model.addAttribute("actor", new Actor());
        return "actori/form";
    }

    @PostMapping("/nou")
    public String create(@ModelAttribute Actor actor, RedirectAttributes ra) {
        try {
            service.save(actor);
            ra.addFlashAttribute("success", "Actor adăugat.");
        } catch (Exception e) {
            ra.addFlashAttribute("error", e.getMessage());
        }
        return "redirect:/actori";
    }

    @GetMapping("/{id}/editare")
    public String editForm(@PathVariable Long id, Model model) {
        return service.findById(id).map(a -> {
            model.addAttribute("actor", a);
            return "actori/form";
        }).orElse("redirect:/actori");
    }

    @PostMapping("/{id}/editare")
    public String edit(@PathVariable Long id, @ModelAttribute Actor actor, RedirectAttributes ra) {
        actor.setIdActor(id);
        try {
            service.update(actor);
            ra.addFlashAttribute("success", "Actor actualizat.");
        } catch (Exception e) {
            ra.addFlashAttribute("error", e.getMessage());
        }
        return "redirect:/actori";
    }

    @PostMapping("/{id}/stergere")
    public String delete(@PathVariable Long id, RedirectAttributes ra) {
    try {
        service.delete(id);
        ra.addFlashAttribute("success", "Actor șters cu succes.");
    } catch (Exception e) {
        if (e.getMessage().contains("ORA-02292")) {
            ra.addFlashAttribute("error", "Nu se poate șterge actorul deoarece acesta apare în distribuția unor filme. Ștergeți legăturile din filme mai întâi.");
        } else {
            ra.addFlashAttribute("error", "Eroare neprevăzută: " + e.getMessage());
        }
    }
    return "redirect:/actori";
}
}