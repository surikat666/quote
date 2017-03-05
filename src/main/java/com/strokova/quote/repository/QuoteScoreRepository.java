package com.strokova.quote.repository;

import com.strokova.quote.model.QuoteScore;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface QuoteScoreRepository extends CrudRepository<QuoteScore, Long> {

    @Query(value = "SELECT qs.rate_time, sum(qs.score) OVER (PARTITION BY qs.quote_id ORDER BY qs.rate_time) FROM quote_scores qs WHERE qs.quote_id = :id ORDER BY qs.rate_time", nativeQuery = true)
    List<Object[]> evolutionByQuoteId(@Param("id") Long id);
}
