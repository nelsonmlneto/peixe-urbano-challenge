package br.com.peixeurbano.challenge.controllers;

import br.com.peixeurbano.challenge.models.BuyOption;
import br.com.peixeurbano.challenge.models.Deal;
import br.com.peixeurbano.challenge.services.DealService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.text.SimpleDateFormat;
import java.util.Calendar;
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
        model.addAttribute("buyOption", new BuyOption());
        return "admin/deal-details";
    }

    @GetMapping("/admin/deals/{id}")
    public String editDealPage(@PathVariable Integer id, Model model) {
        model.addAttribute("deal", dealService.getDealById(id));
        model.addAttribute("buyOption", new BuyOption());
        return "admin/deal-details";
    }

    @PostMapping("/admin/deals/save")
    public String saveDealSubmit(@ModelAttribute Deal deal) {

        deal = this.prepareDealForSaving(deal);

        deal = dealService.saveDeal(deal);

        return "redirect:/admin/deals/" + deal.getId() + "?save=success";
    }

    @PostMapping("/admin/deals/{id}/save-buy-option")
    public String saveBuyOptionSubmit(@PathVariable Integer id, @ModelAttribute BuyOption buyOption) {

        Deal deal = dealService.getDealById(id);
        buyOption = prepareBuyOptionForSaving(buyOption);
        buyOption.setDeal(deal);
        deal.addBuyOption(buyOption);

        dealService.saveDeal(deal);

        return "redirect:/admin/deals/" + id + "?save=success";
    }

    @PostMapping("/admin/deals/{dealId}/remove-buy-option/{optionId}")
    public String saveBuyOptionSubmit(@PathVariable Integer dealId, @PathVariable Integer optionId) {

        dealService.removeBuyOptionById(optionId);

        return "redirect:/admin/deals/" + dealId + "?save=success";
    }

    private Deal prepareDealForSaving(Deal deal){
        if (deal.getId() == null) {
            deal.setCreateDate(new Date());
            deal.setTotalSold(0);
            // TODO URL - Slug before saving
        }

        deal.setEndDate(this.calculateEndDate(deal.getPublishDate(), deal.getValidDays()));

        return deal;
    }

    private Date calculateEndDate(Date date, Integer validDays){
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.add(Calendar.DATE, validDays.intValue());
        return c.getTime();
    }

    private BuyOption prepareBuyOptionForSaving(BuyOption buyOption){
        Double salePrice = this.calculateSaleOptionPrice(buyOption);
        buyOption.setSalePrice(salePrice);

        return buyOption;
    }

    private Double calculateSaleOptionPrice(BuyOption buyOption){

        double salePrice = ((100.00 - buyOption.getPercentageDiscount().doubleValue()) * buyOption.getNormalPrice().doubleValue())/100.00;

        return new Double(salePrice);
    }

}
