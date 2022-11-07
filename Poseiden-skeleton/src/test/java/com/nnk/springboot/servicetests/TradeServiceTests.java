package com.nnk.springboot.servicetests;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.nnk.springboot.domain.Trade;
import com.nnk.springboot.repositories.TradeRepository;
import com.nnk.springboot.services.TradeServiceImpl;

@ExtendWith(MockitoExtension.class)
public class TradeServiceTests {
	
	@Mock
	TradeRepository tradeRepository;
	
	@InjectMocks
	TradeServiceImpl tradeService;
	
	@Test
	void testSaveTrade() {
		Trade trade = new Trade(1, "Trade Account", "Type");
		
		given(tradeRepository.save(trade)).willReturn(trade);
		
		Trade tradeSaved = tradeService.createTrade(trade);
		
		assertThat(tradeSaved).isNotNull();
		assertEquals(trade, tradeSaved);
		verify(tradeRepository).save(any(Trade.class));
	}
	
	@Test
	void testUpdateTrade() {
		Trade trade = new Trade(1, "Trade Account", "Type");
		
		given(tradeRepository.findById(trade.getTradeId())).willReturn(Optional.of(trade));
		given(tradeRepository.save(trade)).willReturn(trade);
		
		Trade tradeUpdated = tradeService.updateTrade(trade, 1);
		
		assertThat(tradeUpdated).isNotNull();
		assertEquals(trade, tradeUpdated);
		verify(tradeRepository).save(any(Trade.class));
	}
	
	@Test
	void testTradeList() {
		List<Trade> trades = new ArrayList<>();
		trades.add(new Trade(1, "Trade Account", "Type"));
		trades.add(new Trade(1, "Trade Account", "Type"));
		trades.add(new Trade(1, "Trade Account", "Type"));
		
		
		given(tradeRepository.findAll()).willReturn(trades);
		
		List<Trade> tradeList = tradeService.getTradeList();
		
		assertEquals(trades, tradeList);
	}
	
	@Test
	void testGetById() {
		Integer id = 1;
		Trade trade = new Trade(1, "Trade Account", "Type");
		
		given(tradeRepository.findById(trade.getTradeId())).willReturn(Optional.of(trade));
		
		Trade tradeExcepted = tradeService.getById(id);
		
		assertThat(tradeExcepted).isNotNull();
		assertEquals(trade, tradeExcepted);
	}
	
	@Test
	void testDeleteTrade() throws Exception {
		Integer id = 1;
		
		tradeService.deleteTrade(id);
		tradeService.deleteTrade(id);
		
		verify(tradeRepository, times(2)).deleteById(id);
	}

}
