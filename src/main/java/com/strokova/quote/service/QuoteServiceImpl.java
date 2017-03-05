package com.strokova.quote.service;

import com.strokova.quote.model.Quote;
import com.strokova.quote.repository.QuoteRepository;
import com.strokova.quote.service.exception.DeniedUpdateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

import static org.springframework.util.Assert.notNull;


@Service
public class QuoteServiceImpl implements QuoteService {

    private final QuoteRepository quoteRepository;

    @Autowired
    public QuoteServiceImpl(QuoteRepository quoteRepository) {
        notNull(quoteRepository, "Quote Repository nust be not null");
        this.quoteRepository = quoteRepository;
    }


    @Override
    public Quote save(String text, String username) {
        Quote quote = new Quote(text, username);
        return quoteRepository.save(quote);
    }

    @Override
    public Quote update(Quote quote, String username) {
        Quote oldQuote = quoteRepository.findOne(quote.getId());
        if (!Objects.equals(oldQuote.getUsername(), username)) {
            throw new DeniedUpdateException(quote);
        }
        oldQuote.setText(quote.getText());
        return quoteRepository.save(oldQuote);
    }

    @Override
    public Quote delete(Long quoteId, String username) {
        Quote quote = quoteRepository.findOne(quoteId);
        if (!Objects.equals(quote.getUsername(), username)) {
            throw  new DeniedUpdateException(quote);
        }
        quoteRepository.delete(quoteId);
        return quote;
    }

    @Override
    public List<Quote> searchTop() {
        return quoteRepository.searchTop();
    }

    @Override
    public List<Quote> searchFlop() {
        return quoteRepository.searchFlop();
    }

    @Override
    public List<Quote> searchLast() {
        return quoteRepository.searchLast();
    }

    @Override
    public List<Quote> searchByUsername(String username) {
        return quoteRepository.findAllByUsername(username);
    }

    @Override
    public List<Quote> serachLastByUsername(String username) {
        return quoteRepository.searchLast5ByUsername(username);
    }


}
