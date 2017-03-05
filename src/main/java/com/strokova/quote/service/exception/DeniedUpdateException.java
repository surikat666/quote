package com.strokova.quote.service.exception;

import com.strokova.quote.model.Quote;
import lombok.Getter;


@Getter
public class DeniedUpdateException extends RuntimeException {

    private final Quote quote;

    public DeniedUpdateException(Quote quote) {
        this.quote = quote;
    }
}
