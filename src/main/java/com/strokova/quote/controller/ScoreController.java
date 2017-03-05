package com.strokova.quote.controller;

import com.strokova.quote.service.QuoteScoreService;
import com.strokova.quote.service.model.DataEvolution;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.security.Principal;

import static org.springframework.http.ResponseEntity.ok;
import static org.springframework.util.Assert.notNull;


@Controller
public class ScoreController {

    private final QuoteScoreService quoteScoreService;

    @Autowired
    public ScoreController(QuoteScoreService quoteScoreService) {
        notNull(quoteScoreService, "Quote Scores Service must be not null");
        this.quoteScoreService = quoteScoreService;
    }

    /* TODO: 04.03.2017 сделать так, чтобы нельзя было голосовать за одну цитату в одну сторону большего одного раза.
     То есть, можно только один раз поставить + цитате. Если поставлен плюс, то можно поставить минус. Как на пикабу короче
     */
    @PostMapping("/rate/{id}/{up}")
    public ResponseEntity rate(@PathVariable Long id, @PathVariable Boolean up, Principal principal) {
        if (principal == null) {
            quoteScoreService.rateQuote(id, up);
        } else {
            quoteScoreService.rateQuote(id, up, principal.getName());
        }
        return ok().build();
    }

    @GetMapping("/evolution/{id}")
    public ResponseEntity<DataEvolution> evolution(@PathVariable Long id) {
        return ok(quoteScoreService.getEvolutionQouteScores(id));
    }
}
