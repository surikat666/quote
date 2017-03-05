package com.strokova.quote.service;

import com.strokova.quote.service.model.DataEvolution;


public interface QuoteScoreService {
    void rateQuote(Long quoteId, boolean rateUp, String username);

    void rateQuote(Long quoteId, boolean rateUp);

    DataEvolution getEvolutionQouteScores(Long quoteId);
}
