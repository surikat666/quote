package com.strokova.quote.model.converter;

import org.springframework.data.jpa.convert.threeten.Jsr310JpaConverters;

import java.time.LocalDateTime;
import java.util.Date;

import static java.time.temporal.ChronoUnit.MINUTES;

public class TimeConverter extends Jsr310JpaConverters.LocalDateTimeConverter {

    @Override
    public Date convertToDatabaseColumn(LocalDateTime date) {
        return super.convertToDatabaseColumn(date.truncatedTo(MINUTES));
    }
}
