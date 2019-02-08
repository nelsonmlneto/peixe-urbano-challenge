package br.com.peixeurbano.challenge.controllers;

import br.com.peixeurbano.challenge.models.Deal;
import br.com.peixeurbano.challenge.services.DealService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Date;

@Controller
public class AdminController {

    @Autowired
    private DealService dealService;

    @GetMapping("/admin/deals")
    public String dealsListPage(Model model) {
        model.addAttribute("deals", dealService.getAllDeals());
        return "admin/deals-list";
    }

    @GetMapping("/admin/deals/new")
    public String newDealPage(Model model) {
        model.addAttribute("deal", new Deal());
        return "admin/deal-details";
    }

    @GetMapping("/admin/deals/{id}")
    public String editDealPage(@PathVariable Integer id, Model model) {
        model.addAttribute("deal", dealService.getDealById(id));
        return "admin/deal-details";
    }

    @PostMapping("/admin/save-deal")
    public String saveDealSubmit(@ModelAttribute Deal deal) {

        deal.setCreateDate(new Date());
        deal.setTotalSold(new Long(0));
        // TODO URL - Slug before saving

        deal = dealService.saveDeal(deal);
        return "redirect:/admin/deals/" + deal.getId() + "?save=success";
    }

}
