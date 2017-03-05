package com.strokova.quote.repository;

import com.strokova.quote.model.QuoteScore;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface QuoteScoreRepository extends CrudRepository<QuoteScore, Long> {

    @Query("select qs.rateTime, sum(qs.score) FROM com.strokova.quote.model.QuoteScore qs where qs.quoteId =:id GROUP BY qs.rateTime order by qs.rateTime")
    List<Object[]> evolutionByQuoteId(@Param("id") Long id);
}
