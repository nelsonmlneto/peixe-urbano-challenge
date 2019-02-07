package br.com.peixeurbano.challenge.controllers;

import br.com.peixeurbano.challenge.services.DealService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AdminController {

    @Autowired
    private DealService dealService;

    @GetMapping("/admin/deals")
    public String dealsListPage(Model model) {

        model.addAttribute("deals", dealService.getAllDeals());

        return "admin/deals-list";
    }

}
