package br.com.peixeurbano.challenge.services;

import br.com.peixeurbano.challenge.models.Deal;
import br.com.peixeurbano.challenge.repositories.BuyOptionRepository;
import br.com.peixeurbano.challenge.repositories.DealRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
    public Deal getDealById(Integer id) {
        return dealRepository.findById(id).orElse(null);
    }

    @Override
    public Deal saveDeal(Deal deal) {
        return dealRepository.save(deal);
    }

    @Override
    public void removeBuyOptionById(Integer id) {
        buyOptionRepository.deleteById(id);
    }


}
