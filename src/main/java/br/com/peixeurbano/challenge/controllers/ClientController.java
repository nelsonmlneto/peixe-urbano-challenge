package br.com.peixeurbano.challenge.controllers;

import br.com.peixeurbano.challenge.expection.UnavailableBuyOptionException;
import br.com.peixeurbano.challenge.expection.UnavailableDealException;
import br.com.peixeurbano.challenge.services.DealService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ClientController {

    @Autowired
    private DealService dealService;

    @GetMapping("/client/deals")
    public String dealsListPage(Model model) {

        model.addAttribute("deals", dealService.getVisibleDeals());
        return "client/deals-list";
    }

    @GetMapping("/client/deals/{id}")
    public String viewDealPage(@PathVariable Integer id, Model model) {

        model.addAttribute("deal", dealService.getVisibleDealById(id));
        return "client/deal-details";
    }

    @PostMapping("/client/deals/{dealId}/buy-option/{optionId}")
    public String buyDeal(@PathVariable Integer dealId, @PathVariable Integer optionId) {

        try {
            dealService.buyDeal(dealId,optionId);
        } catch (UnavailableBuyOptionException e) {
            e.printStackTrace();
            return "redirect:/client/deals/" + dealId + "?buy=unavailableBuyOption";
        } catch (UnavailableDealException e) {
            e.printStackTrace();
            return "redirect:/client/deals/" + dealId + "?buy=unavailableDeal";
        }

        return "redirect:/client/deals/" + dealId + "?buy=success";
    }
}
