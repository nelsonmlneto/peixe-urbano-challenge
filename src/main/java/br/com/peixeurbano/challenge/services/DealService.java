package br.com.peixeurbano.challenge.services;

import br.com.peixeurbano.challenge.models.Deal;

import java.util.List;

public interface DealService {

    List<Deal> getAllDeals();
    Deal getDealById(Integer id);
    Deal saveDeal(Deal deal);
}
