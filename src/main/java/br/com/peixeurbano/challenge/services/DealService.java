package br.com.peixeurbano.challenge.services;

import br.com.peixeurbano.challenge.models.Deal;

import java.util.List;

public interface DealService {

    List<Deal> getAllDeals();
    List<Deal> getVisibleDeals();
    Deal getDealById(Integer id);
    Deal getVisibleDealById(Integer id);
    Deal saveDeal(Deal deal);
    void removeBuyOptionById(Integer id);
    void buyDeal(Integer dealId, Integer optionId);
}
