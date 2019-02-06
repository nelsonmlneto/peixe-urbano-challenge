package br.com.peixeurbano.challenge.controllers;

import br.com.peixeurbano.challenge.models.BuyOption;
import br.com.peixeurbano.challenge.models.Deal;
import br.com.peixeurbano.challenge.models.DealType;
import br.com.peixeurbano.challenge.repositories.DealRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import br.com.peixeurbano.challenge.repositories.UserRepository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Controller
public class GreetingController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private DealRepository dealRepository;

    @GetMapping("/greeting")
    public String greeting(Model model) {
        //TODO remove
        model.addAttribute("name", "name");

        Deal deal = new Deal();
        deal.setTitle("Title 1");
        deal.setText("Text 1");
        deal.setCreateDate(new Date());
        deal.setType(DealType.PRODUCT);

        BuyOption buyOption = new BuyOption();
        buyOption.setTitle("Buy Option 2");

        deal.addBuyOption(buyOption);

        dealRepository.save(deal);

        Deal newDeal = dealRepository.findById(2).orElse(null);

        List<Deal> deals = (List<Deal>) dealRepository.findAll();

        return "greeting";
    }

}
