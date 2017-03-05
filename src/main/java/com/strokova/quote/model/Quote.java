package com.strokova.quote.model;


import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.data.jpa.convert.threeten.Jsr310JpaConverters;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

import static java.time.LocalDateTime.now;
import static javax.persistence.FetchType.EAGER;
import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Table(name = "quotes")
@Data
@Accessors(chain = true)
public class Quote {

    public Quote(){}

    @Id
    @Column
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    @Column
    private String text;

    @Column
    private String username;

    @Column
    @Convert(converter = Jsr310JpaConverters.LocalDateTimeConverter.class)
    private LocalDateTime postTime;

    @OneToMany(fetch = EAGER)
    @JoinColumn(referencedColumnName = "id", name = "quote_id")
    private List<QuoteScore> quoteScores;

    @Transient
    private Long score;

    @PostLoad
    private void postLoad() {
        this.score = quoteScores == null ? 0 : quoteScores.stream().mapToLong(QuoteScore::getScore).sum();
    }


    public Quote(String text, String username) {
        this.username = username;
        this.text = text;
        this.postTime = now();
    }
}
