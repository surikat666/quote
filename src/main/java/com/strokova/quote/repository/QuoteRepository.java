package com.strokova.quote.repository;

import com.strokova.quote.model.Quote;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface QuoteRepository extends JpaRepository<Quote, Long> {

    List<Quote> findAllByUsername(String username);

    @Query("select q from com.strokova.quote.model.Quote q left join q.quoteScores qs group by q.id, q.text, q.username order by coalesce(sum(qs.score),0) desc")
    List<Quote> searchTop();

    @Query("select q from com.strokova.quote.model.Quote q left join q.quoteScores qs group by q.id, q.text, q.username order by coalesce(sum(qs.score),0) asc")
    List<Quote> searchFlop();

    @Query("select q from com.strokova.quote.model.Quote q order by q.postTime desc")
    List<Quote> searchLast();

    @Query("select q from com.strokova.quote.model.Quote q left join q.quoteScores qs where qs.voter = :username order by qs.rateTime desc ")
    List<Quote> searchLast5ByUsername(@Param("username") String username);

}
