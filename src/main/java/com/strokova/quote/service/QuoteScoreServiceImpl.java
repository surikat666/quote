package com.strokova.quote.service;

import com.strokova.quote.model.QuoteScore;
import com.strokova.quote.repository.QuoteScoreRepository;
import com.strokova.quote.service.model.DataEvolution;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.sql.Timestamp;
import java.time.format.DateTimeFormatter;
import java.util.List;

import static java.time.LocalDateTime.now;
import static java.time.format.DateTimeFormatter.ISO_LOCAL_DATE_TIME;
import static java.util.stream.Collectors.toList;
import static org.springframework.util.Assert.notNull;

@Service
public class QuoteScoreServiceImpl implements QuoteScoreService {

    private final QuoteScoreRepository quoteScoreRepository;

    private static final DateTimeFormatter formatter = ISO_LOCAL_DATE_TIME;

    @Autowired
    public QuoteScoreServiceImpl(QuoteScoreRepository quoteScoreRepository) {
        notNull(quoteScoreRepository, "Quote Score Repository must be not null");
        this.quoteScoreRepository = quoteScoreRepository;
    }

    @Override
    public void rateQuote(Long quoteId, boolean rateUp, String username) {
        QuoteScore score = new QuoteScore();
        score.setQuoteId(quoteId);
        score.setScore(rateUp ? 1 : -1);
        score.setRateTime(now());
        score.setVoter(username);
        quoteScoreRepository.save(score);
    }

    @Override
    public void rateQuote(Long quoteId, boolean rateUp) {
        rateQuote(quoteId, rateUp, null);
    }

    @Override
    public DataEvolution getEvolutionQouteScores(Long quoteId) {
        List<Object[]> quoteScores = quoteScoreRepository
                .evolutionByQuoteId(quoteId);
        val labels = quoteScores.stream().map(arr -> arr[0]).map(time -> (Timestamp) time).map(Timestamp::toLocalDateTime).map(formatter::format).collect(toList());
        val scoresList = quoteScores.stream().map(arr -> arr[1]).map(time -> (BigInteger) time).map(BigInteger::longValue).collect(toList());
        return new DataEvolution(labels, scoresList);
    }
}
