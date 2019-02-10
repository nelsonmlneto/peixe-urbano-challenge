package br.com.peixeurbano.challenge.services;

import br.com.peixeurbano.challenge.expection.UnavailableBuyOptionException;
import br.com.peixeurbano.challenge.expection.UnavailableDealException;
import br.com.peixeurbano.challenge.models.BuyOption;
import br.com.peixeurbano.challenge.models.Deal;
import br.com.peixeurbano.challenge.repositories.BuyOptionRepository;
import br.com.peixeurbano.challenge.repositories.DealRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class DealServiceImpl implements DealService{

    @Autowired
    private DealRepository dealRepository;

    @Autowired
    private BuyOptionRepository buyOptionRepository;

    @Override
    public List<Deal> getAllDeals() {
        return (List<Deal>) dealRepository.findAll();
    }

    @Override
    public List<Deal> getVisibleDeals() {
        List<Deal> deals = (List<Deal>) dealRepository.findAll();
        List<Deal> visibleDeals = new ArrayList<>();
        for (Deal deal : deals){
            if (deal.isVisible()){
                visibleDeals.add(deal);
            }
        }
        return visibleDeals;
    }

    @Override
    public Deal getDealById(Integer id) {
        return dealRepository.findById(id).orElse(null);
    }

    @Override
    public Deal getVisibleDealById(Integer id) {
        Deal deal = dealRepository.findById(id).orElse(null);
        if (!deal.isVisible()){
            return null;
        }
        Set<BuyOption> visibleOptions = new HashSet<>();
        for (BuyOption option : deal.getBuyOptions()){
            if (option.isVisible()){
                visibleOptions.add(option);
            }
        }
        deal.setBuyOptions(visibleOptions);
        return deal;
    }

    @Override
    public Deal saveDeal(Deal deal) {
        return dealRepository.save(deal);
    }

    @Override
    public void removeBuyOptionById(Integer id) {
        buyOptionRepository.deleteById(id);
    }

    @Override
    public void buyDeal(Integer dealId, Integer optionId) throws UnavailableBuyOptionException, UnavailableDealException {
        Deal deal = dealRepository.findById(dealId).orElse(null);
        BuyOption option = buyOptionRepository.findById(optionId).orElse(null);

        if (deal != null && deal.isVisible()) {
            if (option != null && option.isVisible() && !option.isOutOfStock()) {
                option.removeCupom();
                deal.addSale();
                dealRepository.save(deal);
                buyOptionRepository.save(option);
            }else{
                throw new UnavailableBuyOptionException();
            }
        } else {
            throw new UnavailableDealException();
        }
    }


}
