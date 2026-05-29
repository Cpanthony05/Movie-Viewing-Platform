package com.example.platformaFilme.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.platformaFilme.model.Client;
import com.example.platformaFilme.service.ClientService;

@Controller
@RequestMapping("/clienti")
public class ClientController {

    private final ClientService service;

    public ClientController(ClientService service) {
        this.service = service;
    }

    @GetMapping
    public String list(Model model) {
        model.addAttribute("clienti", service.findAll());
        return "clienti/list";
    }

    @GetMapping("/nou")
    public String createForm(Model model) {
        model.addAttribute("client", new Client());
        return "clienti/form";
    }

    @PostMapping("/nou")
    public String create(@ModelAttribute Client client, RedirectAttributes ra) {
        try {
            service.save(client);
            ra.addFlashAttribute("success", "Client adăugat cu succes.");
        } catch (Exception e) {
            ra.addFlashAttribute("error", e.getMessage());
        }
        return "redirect:/clienti";
    }

    @GetMapping("/{id}/editare")
    public String editForm(@PathVariable Long id, Model model) {
        return service.findById(id).map(c -> {
            model.addAttribute("client", c);
            return "clienti/form";
        }).orElse("redirect:/clienti");
    }

    @PostMapping("/{id}/editare")
    public String edit(@PathVariable Long id, @ModelAttribute Client client, RedirectAttributes ra) {
        client.setIdClient(id);
        try {
            service.update(client);
            ra.addFlashAttribute("success", "Client actualizat.");
        } catch (Exception e) {
            ra.addFlashAttribute("error", e.getMessage());
        }
        return "redirect:/clienti";
    }

    @PostMapping("/{id}/stergere")
    public String delete(@PathVariable Long id, RedirectAttributes ra) {
    try {
        service.delete(id);
        ra.addFlashAttribute("success", "Client șters cu succes.");
    } catch (Exception e) {
       
        if (e.getMessage() != null && e.getMessage().contains("ORA-02292")) {
            ra.addFlashAttribute("error", "Nu se poate șterge clientul deoarece are istoric de vizionări sau închirieri active. Ștergeți acele înregistrări mai întâi.");
        } else {
            ra.addFlashAttribute("error", "Eroare la ștergere: " + e.getMessage());
        }
    }
    return "redirect:/clienti";
}
}