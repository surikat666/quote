package com.strokova.quote.controller;

import com.strokova.quote.model.Quote;
import com.strokova.quote.service.QuoteService;
import com.strokova.quote.service.exception.DeniedUpdateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.FORBIDDEN;
import static org.springframework.http.ResponseEntity.ok;
import static org.springframework.http.ResponseEntity.status;
import static org.springframework.util.Assert.notNull;

@Controller
@RequestMapping("/quotes")
public class QuotController {

    private final QuoteService quoteService;

    @Autowired
    public QuotController(QuoteService quoteService) {
        notNull(quoteService, "Quote Service must be not null");
        this.quoteService = quoteService;
    }

    @PreAuthorize("hasRole('ROLE_USER')")
    @PostMapping
    public ResponseEntity<Quote> create(String text, Principal principal) {
        return status(CREATED).body(quoteService.save(text, principal.getName()));
    }

    @PreAuthorize("hasRole('ROLE_USER')")
    @PutMapping
    public ResponseEntity<Quote> update(Quote quote, Principal principal) {
        return ok(quoteService.update(quote, principal.getName()));
    }

    @PreAuthorize("hasRole('ROLE_USER')")
    @DeleteMapping
    public ResponseEntity<Quote> delete(Long id, Principal principal) {
        return ok(quoteService.delete(id, principal.getName()));
    }

    @GetMapping("/top10")
    public ResponseEntity<List<Quote>> readTop() {
        return ok(quoteService.searchTop());
    }

    @GetMapping("/flop10")
    public ResponseEntity<List<Quote>> readFlop() {
        return ok(quoteService.searchFlop());
    }

    @GetMapping("/last")
    public ResponseEntity<List<Quote>> readLast() {
        return ok(quoteService.searchLast());
    }

    @PreAuthorize("hasRole('ROLE_USER')")
    @GetMapping("/me")
    public ResponseEntity<List<Quote>> read(Principal principal) {
        return ok(quoteService.searchByUsername(principal.getName()));
    }

    @PreAuthorize("hasRole('ROLE_USER')")
    @GetMapping("/me-last")
    public ResponseEntity<List<Quote>> last5(Principal principal) {
        return ok(quoteService.searchLastByUsername(principal.getName()));
    }

    @ExceptionHandler(DeniedUpdateException.class)
    public ResponseEntity<Quote> handleDeniedException(DeniedUpdateException e) {
        return status(FORBIDDEN).body(e.getQuote());
    }

}
