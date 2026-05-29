package com.example.platformaFilme.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.platformaFilme.service.ClientService;
import com.example.platformaFilme.service.FilmService;
import com.example.platformaFilme.service.OptiuneFeedbackService;
import com.example.platformaFilme.service.StatisticiService;

@Controller
@RequestMapping("/statistici")
public class StatisticiController {

    private final StatisticiService service;
    private final ClientService clientService;
    private final FilmService filmService;
    private final OptiuneFeedbackService optiuneService;

    public StatisticiController(StatisticiService service, ClientService clientService,
                                FilmService filmService, OptiuneFeedbackService optiuneService) {
        this.service = service;
        this.clientService = clientService;
        this.filmService = filmService;
        this.optiuneService = optiuneService;
    }

    @GetMapping
    public String dashboard(Model model) {
        model.addAttribute("clienti", clientService.findAll());
        return "statistici/dashboard";
    }

    @GetMapping("/profil")
    public String profil(@RequestParam Long idClient, Model model) {
        model.addAttribute("clienti", clientService.findAll());
        model.addAttribute("idClientSelectat", idClient);
        try {
            model.addAttribute("profil", service.getProfilClientView(idClient));
        } catch (Exception e) {
            model.addAttribute("error", e.getMessage());
        }
        return "statistici/profil";
    }

    @GetMapping("/recomandari")
    public String recomandari(@RequestParam Long idClient,
                              @RequestParam(defaultValue = "5") int numar,
                              Model model) {
        model.addAttribute("clienti", clientService.findAll());
        model.addAttribute("idClientSelectat", idClient);
        model.addAttribute("numar", numar);
        try {
            model.addAttribute("recomandari", service.genereazaRecomandari(idClient, numar));
        } catch (Exception e) {
            model.addAttribute("error", e.getMessage());
        }
        return "statistici/recomandari";
    }
    @GetMapping("/actori-frecventi")
    public String actoriFrecventi(@RequestParam Long idClient, Model model) {
        model.addAttribute("clienti", clientService.findAll());
        model.addAttribute("idClientSelectat", idClient);
        try {
            model.addAttribute("actori", service.actoriFrecventi(idClient));
        } catch (Exception e) {
            model.addAttribute("error", e.getMessage());
        }
        return "statistici/actori-frecventi";
    }
}