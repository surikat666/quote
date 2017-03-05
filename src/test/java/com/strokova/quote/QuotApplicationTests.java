package com.strokova.quote;

import com.strokova.quote.model.Quote;
import com.strokova.quote.model.QuoteScore;
import com.strokova.quote.repository.QuoteRepository;
import com.strokova.quote.repository.QuoteScoreRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class QuotApplicationTests {

	@Autowired
	private QuoteRepository quoteRepository;

	@Autowired
	private QuoteScoreRepository quotScoreRepository;

	@Test
	public void contextLoads() {
        Iterable<QuoteScore> all = quotScoreRepository.findAll();
        List<Quote> quotes = quoteRepository.searchTop();
	}

}
