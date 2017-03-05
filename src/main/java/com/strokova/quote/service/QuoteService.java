package com.strokova.quote.service;

import com.strokova.quote.model.Quote;

import java.util.List;


public interface QuoteService {
    Quote save(String text, String username);

    Quote update(Quote quote, String username);

    Quote delete(Long quoteId, String username);

    List<Quote> searchTop();

    List<Quote> searchFlop();

    List<Quote> searchLast();

    List<Quote> searchByUsername(String username);

    List<Quote> searchLastByUsername(String username);
}
