package com.strokova.quote.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.strokova.quote.model.converter.TimeConverter;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Table(name = "quote_scores")
@Data
public class QuoteScore {

    @Id
    @Column
    @GeneratedValue(strategy = IDENTITY)
    @JsonIgnore
    private Long id;

    @Column(name = "quote_id")
    @JsonIgnore
    private Long quoteId;

    @Column
    private Integer score;

    @Column
    @Convert(converter = TimeConverter.class)
    private LocalDateTime rateTime;

    @Column
    private String voter;

    @Override
    public String toString() {
        return "QuoteScore{" +
                "quoteId=" + quoteId +
                ", upvote=" + score +
                '}';
    }
}
